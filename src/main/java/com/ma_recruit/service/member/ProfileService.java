package com.ma_recruit.service.member;

import com.ma_recruit.dto.member.request.ProfileCreateRequestDto;
import com.ma_recruit.dto.member.request.ProfileUpdateRequestDto;
import com.ma_recruit.dto.member.response.ProfileResponseDto;
import com.ma_recruit.entity.member.Member;
import com.ma_recruit.entity.member.Profile;
import com.ma_recruit.global.exception.ProfileException;
import com.ma_recruit.repository.member.MemberRepository;
import com.ma_recruit.repository.member.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    /**
     * 프로필 생성
     */
    @Transactional
    public ProfileResponseDto createProfile(int memberId, ProfileCreateRequestDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProfileException("Member Not Found"));
        if(member.getProfile().size() >= 5){
            throw new ProfileException("프로필은 최대 5개까지만 만들 수 있습니다.");
        }
        Profile profile = Profile.builder()
                .nickname(dto.getNickname())
                .level(dto.getLevel())
                .job(dto.getJob())
                .member(member)
                .build();

        profileRepository.save(profile);
        return new ProfileResponseDto(profile);
    }

    /**
     * 프로필 수정
     */
    @Transactional
    public ProfileResponseDto updateProfile(int memberId,
                                            int profileId,
                                            ProfileUpdateRequestDto dto) {

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileException("Profile Not Found"));

        if (profile.getMember().getId() != memberId) {
            throw new ProfileException("권한이 없습니다. (본인 프로필만 수정 가능)");
        }
        if(dto.getNickname().isPresent()) { profile.updateNickname(dto.getNickname().get()); }
        if(dto.getLevel().isPresent()) { profile.updateLevel(dto.getLevel().get()); }
        if(dto.getJob().isPresent()) { profile.updateJob(dto.getJob().get()); }
        return new ProfileResponseDto(profile);
    }

    /**
     * 프로필 삭제
     */
    @Transactional
    public void deleteProfile(int memberId, int profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileException("Profile Not Found"));

        if (profile.getMember().getId() != memberId) {
            throw new ProfileException("권한이 없습니다. (본인 프로필만 삭제 가능)");
        }

        profileRepository.delete(profile);
    }
}