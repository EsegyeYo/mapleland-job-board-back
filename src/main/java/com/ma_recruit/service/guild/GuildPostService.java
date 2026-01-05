package com.ma_recruit.service.guild;

import com.ma_recruit.dto.guild.request.GuildPostCreateRequestDto;
import com.ma_recruit.dto.guild.request.GuildPostUpdateRequestDto;
import com.ma_recruit.dto.guild.response.GuildPostResponseDto;
import com.ma_recruit.dto.party.response.PartyPostResponseDto;
import com.ma_recruit.entity.guild.GuildPost;
import com.ma_recruit.entity.member.Member;
import com.ma_recruit.repository.guild.GuildPostRepository;
import com.ma_recruit.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class GuildPostService {
    private final GuildPostRepository guildPostRepository;
    private final MemberRepository memberRepository;

    /**
     * 길드 게시글 생성
     */
    @Transactional
    public GuildPostResponseDto createGuildPost(int memberId, GuildPostCreateRequestDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member Not Found"));

        GuildPost guildPost = GuildPost.builder()
                .description(dto.getDescription())
                .status(true)
                .job(dto.getJob())
                .member(member)
                .guild_name(dto.getGuild_name())
                .condition(dto.getCondition())
                .build();


        guildPostRepository.save(guildPost);
        return new GuildPostResponseDto(guildPost);
    }

    /**
     * 길드 포스트 수정
     */
    @Transactional
    public GuildPostResponseDto updateGuildPost(int memberId,
                                                int guildPostId,
                                              GuildPostUpdateRequestDto dto) {

        GuildPost guildPost = guildPostRepository.findById(guildPostId)
                .orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
        if (guildPost.getMember().getId() != memberId) {
            throw new IllegalStateException("권한이 없습니다. (본인 게시글만 수정 가능)");
        }
        if(dto.getDescription().isPresent()){ guildPost.updateDescription(dto.getDescription().get()); }
        if(dto.getJob().isPresent()){ guildPost.updateJob(dto.getJob().get()); }
        if(dto.getGuild_name().isPresent()) { guildPost.updateGuildName(dto.getGuild_name().get());}
        if(dto.getCondition().isPresent()) { guildPost.updateCondition(dto.getCondition().get());}

        return new GuildPostResponseDto(guildPost);
    }

    /**
     * 길드 포스트 삭제
     */
    @Transactional
    public void deleteGuildPost(int memberId,
                                int guildPostId) {

        GuildPost guildPost = guildPostRepository.findById(guildPostId)
                .orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
        if (guildPost.getMember().getId() != memberId) {
            throw new IllegalStateException("권한이 없습니다. (본인 게시글만 삭제 가능)");
        }

        guildPostRepository.delete(guildPost);
    }

    /**
     * 길드포스트 페이징네이션 불러오기
     */
    @Transactional(readOnly = true)
    public Page<GuildPostResponseDto> getGuildPosts(Pageable pageable) {

        Page<GuildPost> page = guildPostRepository.findAll(pageable);

        return page.map(GuildPostResponseDto::new);
    }

    /**
     * 길드포스트 단 건 불러오기
     */
    @Transactional(readOnly = true)
    public GuildPostResponseDto getGuildPost(int guildPostId) {
        return guildPostRepository.findById(guildPostId)
                .map(GuildPostResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("글을 찾을 수 없습니다."));
    }
}
