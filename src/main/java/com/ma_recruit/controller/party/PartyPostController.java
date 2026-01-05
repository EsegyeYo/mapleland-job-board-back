package com.ma_recruit.controller.party;

import com.ma_recruit.dto.member.request.ProfileUpdateRequestDto;
import com.ma_recruit.dto.member.response.ProfileResponseDto;
import com.ma_recruit.dto.party.request.PartyPostCreateRequestDto;
import com.ma_recruit.dto.party.request.PartyPostUpdateRequestDto;
import com.ma_recruit.dto.party.response.PartyPostResponseDto;
import com.ma_recruit.entity.member.CustomOAuth2User;
import com.ma_recruit.entity.party.PartyType;
import com.ma_recruit.service.party.PartyPostService;
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
@RequestMapping("/api/partys")
public class PartyPostController {
    private final PartyPostService partyPostService;

    @PostMapping
    public ResponseEntity<PartyPostResponseDto> createPartyPost(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @RequestBody @Valid PartyPostCreateRequestDto requestDto) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        int memberId = oauthUser.getMemberId();
        PartyPostResponseDto responseDto = partyPostService.createPartyPost(memberId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{partyPostId}")
    public ResponseEntity<PartyPostResponseDto> updatePartyPost(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @PathVariable int partyPostId,
            @RequestBody @Valid PartyPostUpdateRequestDto requestDto) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        int memberId = oauthUser.getMemberId();

        PartyPostResponseDto responseDto =
                partyPostService.updatePartyPost(memberId, partyPostId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{partyPostId}")
    public ResponseEntity<Void> deletePartyPost(
            @AuthenticationPrincipal CustomOAuth2User oauthUser,
            @PathVariable int partyPostId) {

        if (oauthUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        int memberId = oauthUser.getMemberId();

        partyPostService.deletePartyPost(memberId, partyPostId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{partyPostId}")
    public PartyPostResponseDto getPartyPost(
           @PathVariable int partyPostId) {
        return partyPostService.getPartyPost(partyPostId);
    }

    @GetMapping("/party-posts")
    public Page<PartyPostResponseDto> getPartyPosts(
            @RequestParam(required = false) PartyType partyType,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {

        return partyPostService.getPartyPostsByPartyType(partyType, pageable);
    }
}
