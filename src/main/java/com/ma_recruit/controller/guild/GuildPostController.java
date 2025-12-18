package com.ma_recruit.controller.guild;

import com.ma_recruit.dto.guild.request.GuildPostCreateRequestDto;
import com.ma_recruit.dto.guild.request.GuildPostUpdateRequestDto;
import com.ma_recruit.dto.guild.response.GuildPostResponseDto;
import com.ma_recruit.entity.member.CustomOAuth2User;
import com.ma_recruit.service.guild.GuildPostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

public class GuildPostController {
    GuildPostService guildPostService;

    @PostMapping
    public ResponseEntity<GuildPostResponseDto> createGuildPost(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @RequestBody @Valid GuildPostCreateRequestDto requestDto) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BigInteger memberId = oauthUser.getMemberId();
        GuildPostResponseDto responseDto = guildPostService.createGuildPost(memberId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{guildPostId}")
    public ResponseEntity<GuildPostResponseDto> updateGuildPost(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @PathVariable BigInteger guildPostId,
            @RequestBody @Valid GuildPostUpdateRequestDto requestDto) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BigInteger memberId = oauthUser.getMemberId();

        GuildPostResponseDto responseDto =
                guildPostService.updateGuildPost(memberId, guildPostId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{guildPostId}")
    public ResponseEntity<Void> deleteGuildPost(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @PathVariable BigInteger guildPostId) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BigInteger memberId = oauthUser.getMemberId();

        guildPostService.deleteGuildPost(memberId, guildPostId);

        return ResponseEntity.noContent().build();
    }
}
