package com.ma_recruit.service.raid;

import com.ma_recruit.dto.guild.response.GuildPostResponseDto;
import com.ma_recruit.dto.raid.request.RaidPostCreateRequestDto;
import com.ma_recruit.dto.raid.request.RaidPostUpdateRequestDto;
import com.ma_recruit.dto.raid.response.RaidPostResponseDto;
import com.ma_recruit.entity.member.Member;
import com.ma_recruit.entity.party.PartyType;
import com.ma_recruit.entity.raid.RaidMob;
import com.ma_recruit.entity.raid.RaidPost;
import com.ma_recruit.repository.member.MemberRepository;
import com.ma_recruit.repository.raid.RaidMobRepository;
import com.ma_recruit.repository.raid.RaidPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RaidPostService {
    private final MemberRepository memberRepository;
    private final RaidMobRepository raidMobRepository;
    private final RaidPostRepository raidPostRepository;

    /**
     * 레이드 게시글 생성
     */
    @Transactional
    public RaidPostResponseDto createPartyPost(int memberId, RaidPostCreateRequestDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member Not Found"));
        RaidMob raidMob = raidMobRepository.findById(dto.getMobId())
                .orElseThrow(() -> new IllegalArgumentException("Mob Not Found"));

        RaidPost raidPost = RaidPost.builder()
                .description(dto.getDescription())
                .status(true)
                .partyType(dto.getPartyType())
                .job(dto.getJob())
                .raidMob(raidMob)
                .member(member)
                .member_max_count(dto.getMember_max_count())
                .member_count(dto.getMember_count())
                .build();


        raidPostRepository.save(raidPost);
        return new RaidPostResponseDto(raidPost);
    }

    /**
     * 레이드 포스트 수정
     */
    @Transactional
    public RaidPostResponseDto updateRaidPost(int memberId,
                                              int raidPostId,
                                                RaidPostUpdateRequestDto dto) {

        RaidPost raidPost = raidPostRepository.findById(raidPostId)
                .orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
        RaidMob raidMob = raidMobRepository.findById(dto.getMobId().get())
                .orElseThrow(() -> new IllegalArgumentException("Mob Not Found"));
        if (raidPost.getMember().getId() != memberId) {
            throw new IllegalStateException("권한이 없습니다. (본인 게시글만 수정 가능)");
        }
        if(dto.getDescription().isPresent()){ raidPost.updateDescription(dto.getDescription().get()); }
        if(dto.getJob().isPresent()){ raidPost  .updateJob(dto.getJob().get()); }
        if(dto.getMobId().isPresent()) { raidPost.updateRaidMob(raidMob); }
        if(dto.getMember_count().isPresent()) { raidPost.updateMember_count(dto.getMember_count().get());}
        if(dto.getMember_max_count().isPresent()) { raidPost.updateMember_max_count(dto.getMember_max_count().get());}

        return new RaidPostResponseDto(raidPost);
    }

    /**
     * 레이드 포스트 삭제
     */
    @Transactional
    public void deleteRaidPost(int memberId,
                               int raidPostId) {

        RaidPost raidPost = raidPostRepository.findById(raidPostId)
                .orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
        if (raidPost.getMember().getId() != memberId) {
            throw new IllegalStateException("권한이 없습니다. (본인 게시글만 삭제 가능)");
        }

        raidPostRepository.delete(raidPost);
    }

    /**
     * 레이드포스트 페이징네이션 불러오기
     */
    @Transactional(readOnly = true)
    public Page<RaidPostResponseDto> getRaidPostsByPartyType(PartyType partyType, Pageable pageable) {

        Page<RaidPost> page = (partyType == null)
                ? raidPostRepository.findAll(pageable)
                : raidPostRepository.findByPartyType(partyType, pageable);

        return page.map(RaidPostResponseDto::new);
    }

    /**
     * 레이드 포스트 단 건 불러오기
     */
    @Transactional(readOnly = true)
    public RaidPostResponseDto getRaidPost(int raidPostId) {
        return raidPostRepository.findById(raidPostId)
                .map(RaidPostResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
    }
}
