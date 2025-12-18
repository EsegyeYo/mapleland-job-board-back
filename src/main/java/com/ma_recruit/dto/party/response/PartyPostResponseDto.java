package com.ma_recruit.dto.party.response;

import com.ma_recruit.entity.member.Job;
import com.ma_recruit.entity.party.PartyPost;
import com.ma_recruit.entity.party.PartyType;
import lombok.Getter;

import java.math.BigInteger;
@Getter
public class PartyPostResponseDto {
    private BigInteger id;
    private String description;
    private boolean status;
    private PartyType partyType;
    private Job job;
    private BigInteger mapId;
    private BigInteger memberId;

    public PartyPostResponseDto(PartyPost partyPost) {
        this.id = partyPost.getId();
        this.description = partyPost.getDescription();
        this.status = partyPost.isStatus();
        this.partyType = partyPost.getPartyType();
        this.job = partyPost.getJob();
        this.mapId = partyPost.getMapleMap().getId();
        this.memberId = partyPost.getMember().getId();
    }
}
