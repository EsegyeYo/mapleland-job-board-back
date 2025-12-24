package com.ma_recruit.controller.raid;

import com.ma_recruit.dto.guild.response.GuildPostResponseDto;
import com.ma_recruit.dto.raid.request.RaidPostCreateRequestDto;
import com.ma_recruit.dto.raid.request.RaidPostUpdateRequestDto;
import com.ma_recruit.dto.raid.response.RaidPostResponseDto;
import com.ma_recruit.entity.member.CustomOAuth2User;
import com.ma_recruit.service.raid.RaidPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/raids")
public class RaidPostController {
    private final RaidPostService raidPostService;

    @PostMapping
    public ResponseEntity<RaidPostResponseDto> createRaidPost(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @RequestBody @Valid RaidPostCreateRequestDto requestDto) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BigInteger memberId = oauthUser.getMemberId();
        RaidPostResponseDto responseDto = raidPostService.createPartyPost(memberId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{raidPostId}")
    public ResponseEntity<RaidPostResponseDto> updateRaidPost(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @PathVariable BigInteger raidPostId,
            @RequestBody @Valid RaidPostUpdateRequestDto requestDto) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BigInteger memberId = oauthUser.getMemberId();

        RaidPostResponseDto responseDto =
                raidPostService.updateRaidPost(memberId, raidPostId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{raidPostId}")
    public ResponseEntity<Void> deleteRaidPost(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @PathVariable BigInteger raidPostId) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BigInteger memberId = oauthUser.getMemberId();

        raidPostService.deleteRaidPost(memberId, raidPostId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/raid-posts")
    public Page<RaidPostResponseDto> getRaidPosts(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return raidPostService.getRaidPosts(pageable);
    }

}
