package com.ma_recruit.service.party;

import com.ma_recruit.dto.party.request.PartyPostCreateRequestDto;
import com.ma_recruit.dto.party.request.PartyPostUpdateRequestDto;
import com.ma_recruit.dto.party.response.PartyPostResponseDto;
import com.ma_recruit.entity.member.Member;
import com.ma_recruit.entity.party.MapleMap;
import com.ma_recruit.entity.party.PartyPost;
import com.ma_recruit.repository.member.MemberRepository;
import com.ma_recruit.repository.party.MapleMapRepository;
import com.ma_recruit.repository.party.PartyPostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class PartyPostService {
    MemberRepository memberRepository;
    MapleMapRepository mapleMapRepository;
    PartyPostRepository partyPostRepository;

    /**
     * 파티 게시글 생성
     */

    @Transactional
    public PartyPostResponseDto createPartyPost(BigInteger memberId, PartyPostCreateRequestDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member Not Found"));
        MapleMap mapleMap = mapleMapRepository.findById(dto.getMapId())
                .orElseThrow(() -> new IllegalArgumentException("Map Not Found"));

        PartyPost partyPost = PartyPost.builder()
                        .description(dto.getDescription())
                        .status(true)
                        .partyType(dto.getPartyType())
                        .job(dto.getJob())
                        .mapleMap(mapleMap)
                        .member(member)
                        .member_max_count(dto.getMember_max_count())
                        .member_count(dto.getMember_count())
                        .build();


        partyPostRepository.save(partyPost);
        return new PartyPostResponseDto(partyPost);
    }

        /**
         * 파티포스트 수정
         */
        @Transactional
        public PartyPostResponseDto updatePartyPost(BigInteger memberId,
                                                 BigInteger partyPostId,
                                                 PartyPostUpdateRequestDto dto) {

            PartyPost partyPost = partyPostRepository.findById(partyPostId)
                    .orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
            MapleMap mapleMap = mapleMapRepository.findById(dto.getMapId().get())
                    .orElseThrow(() -> new IllegalArgumentException("Map Not Found"));
            if (!partyPost.getMember().getId().equals(memberId)) {
                throw new IllegalStateException("권한이 없습니다. (본인 게시글만 수정 가능)");
            }
            if(dto.getDescription().isPresent()){ partyPost.updateDescription(dto.getDescription().get()); }
            if(dto.getJob().isPresent()){ partyPost.updateJob(dto.getJob().get()); }
            if(dto.getMapId().isPresent()) { partyPost.updateMapleMap(mapleMap); }
            if(dto.getMember_count().isPresent()) { partyPost.updateMember_count(dto.getMember_count().get());}
            if(dto.getMember_max_count().isPresent()) { partyPost.updateMember_max_count(dto.getMember_max_count().get());}

            return new PartyPostResponseDto(partyPost);
        }

        /**
         * 파티포스트 삭제
         */
        @Transactional
        public void deletePartyPost(BigInteger memberId,
                                    BigInteger partyPostId) {

            PartyPost partyPost = partyPostRepository.findById(partyPostId)
                    .orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
            if (!partyPost.getMember().getId().equals(memberId)) {
                throw new IllegalStateException("권한이 없습니다. (본인 게시글만 삭제 가능)");
            }

            partyPostRepository.delete(partyPost);
        }
}
