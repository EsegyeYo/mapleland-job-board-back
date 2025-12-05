package com.ma_recruit.dto.member.response;

import com.ma_recruit.entity.member.Job;
import com.ma_recruit.entity.member.Profile;
import lombok.Getter;

import java.math.BigInteger;

@Getter
public class ProfileResponseDto {

    private BigInteger id;
    private String nickname;
    private int level;
    private Job job;
    private BigInteger memberId;

    public ProfileResponseDto(Profile profile) {
        this.id = profile.getId();
        this.nickname = profile.getNickname();
        this.level = profile.getLevel();
        this.job = profile.getJob();
        this.memberId = profile.getMember().getId();
    }
}