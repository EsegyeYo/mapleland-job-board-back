package com.ma_recurit.service;

import com.ma_recurit.dto.member.request.ProfileCreateRequestDto;
import com.ma_recurit.dto.member.request.ProfileUpdateRequestDto;
import com.ma_recurit.dto.member.response.ProfileResponseDto;
import com.ma_recurit.entity.member.Member;
import com.ma_recurit.entity.member.Profile;
import com.ma_recurit.repository.member.MemberRepository;
import com.ma_recurit.repository.member.ProfileRepository;
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
    public ProfileResponseDto createProfile(BigInteger memberId, ProfileCreateRequestDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member Not Found"));

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
    public ProfileResponseDto updateProfile(BigInteger memberId,
                                            BigInteger profileId,
                                            ProfileUpdateRequestDto dto) {

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile Not Found"));

        if (!profile.getMember().getId().equals(memberId)) {
            throw new IllegalStateException("권한이 없습니다. (본인 프로필만 수정 가능)");
        }

        profile.updateProfile(dto.getNickname(), dto.getLevel(), dto.getJob());

        return new ProfileResponseDto(profile);
    }

    /**
     * 프로필 삭제
     */
    @Transactional
    public void deleteProfile(BigInteger memberId, BigInteger profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile Not Found"));

        if (!profile.getMember().getId().equals(memberId)) {
            throw new IllegalStateException("권한이 없습니다. (본인 프로필만 삭제 가능)");
        }

        profileRepository.delete(profile);
    }
}