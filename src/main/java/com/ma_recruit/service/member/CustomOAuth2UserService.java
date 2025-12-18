package com.ma_recruit.service.member;

import com.ma_recruit.entity.member.CustomOAuth2User;
import com.ma_recruit.entity.member.Member;
import com.ma_recruit.entity.member.Role;
import com.ma_recruit.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if (!"discord".equals(registrationId)) {
            return oAuth2User;
        }

        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 디스코드 응답 구조 기준
        String discordId = String.valueOf(attributes.get("id"));
        String username = (String) attributes.get("username");
        String avatar = (String) attributes.get("avatar");

        // avatar not null -> load discordPhoto
        String avatarUrl = (avatar != null) ? "https://cdn.discordapp.com/avatars/" + discordId + "/" + avatar + ".png" : null;

        // DB에 Member 생성/업데이트
        Member member = memberRepository.findByDiscordId(discordId)
                .map(m -> {
                    // Member 벤 상태 유무 체크
                    if (m.isMember_ban()) {
                        OAuth2Error error = new OAuth2Error("access_denied", "해당 계정은 이용이 제한되었습니다.", null);
                        throw new OAuth2AuthenticationException(error);
                    }

                    // 밴이 아니라면 프로필만 업데이트
                    m.updateDiscordPhoto(avatarUrl);
                    return m;
                })
                .orElseGet(() -> {
                    // 신규 회원이면 바로 생성
                    Member newMember = Member.builder()
                            .discord_id(discordId)
                            .discord_photo(avatarUrl)
                            .role(Role.USER)
                            .user_ban(false)
                            .discordName(username)
                            .profile(null)
                            .build();
                    return memberRepository.save(newMember);
                });
        return new CustomOAuth2User(member, attributes);
    }
}
