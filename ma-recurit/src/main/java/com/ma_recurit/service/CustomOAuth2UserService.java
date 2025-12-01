package com.ma_recurit.service;

import com.ma_recurit.entity.member.CustomOAuth2User;
import com.ma_recurit.entity.member.Member;
import com.ma_recurit.entity.member.Role;
import com.ma_recurit.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("=== CustomOAuth2UserService.loadUser CALLED ===");
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("registrationId = " + registrationId);
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
                    m.updateDiscordPhoto(avatarUrl);
                    return m;
                })
                .orElseGet(() -> {
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
