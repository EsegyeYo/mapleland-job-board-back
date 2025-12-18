package com.ma_recruit.dto.raid.response;

import com.ma_recruit.entity.member.Job;
import com.ma_recruit.entity.party.PartyType;
import com.ma_recruit.entity.raid.RaidPost;
import lombok.Getter;

import java.math.BigInteger;
@Getter
public class RaidPostResponseDto {
    private BigInteger id;
    private String description;
    private boolean status;
    private PartyType partyType;
    private Job job;
    private BigInteger mobId;
    private BigInteger memberId;

    public RaidPostResponseDto(RaidPost raidPost) {
        this.id = raidPost.getId();
        this.description = raidPost.getDescription();
        this.status = raidPost.isStatus();
        this.partyType = raidPost.getPartyType();
        this.job = raidPost.getJob();
        this.mobId = raidPost.getRaidMob().getId();
        this.memberId = raidPost.getMember().getId();
    }
}
