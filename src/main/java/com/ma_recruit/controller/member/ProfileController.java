package com.ma_recruit.controller.member;

import com.ma_recruit.dto.member.request.ProfileCreateRequestDto;
import com.ma_recruit.dto.member.request.ProfileUpdateRequestDto;
import com.ma_recruit.dto.member.response.ProfileResponseDto;
import com.ma_recruit.entity.member.CustomOAuth2User;
import com.ma_recruit.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileResponseDto> createProfile(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @RequestBody @Valid ProfileCreateRequestDto requestDto) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BigInteger memberId = oauthUser.getMemberId();

        ProfileResponseDto responseDto = profileService.createProfile(memberId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDto> updateProfile(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @PathVariable BigInteger profileId,
            @RequestBody @Valid ProfileUpdateRequestDto requestDto) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BigInteger memberId = oauthUser.getMemberId();

        ProfileResponseDto responseDto =
                profileService.updateProfile(memberId, profileId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @PathVariable BigInteger profileId) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BigInteger memberId = oauthUser.getMemberId();

        profileService.deleteProfile(memberId, profileId);

        return ResponseEntity.noContent().build();
    }
}
