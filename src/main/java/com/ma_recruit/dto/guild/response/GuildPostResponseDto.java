package com.ma_recruit.dto.guild.response;

import com.ma_recruit.entity.guild.GuildPost;
import com.ma_recruit.entity.member.Job;
import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;

@Getter
public class GuildPostResponseDto {
    private BigInteger id;
    private String description;
    private boolean status;
    private Job job;
    private ArrayList<String> condition;
    private BigInteger memberId;
    private String guild_name;

    public GuildPostResponseDto(GuildPost guildPost) {
        this.id = guildPost.getId();
        this.description = guildPost.getDescription();
        this.status = guildPost.isStatus();
        this.job = guildPost.getJob();
        this.guild_name = guildPost.getGuild_name();
        this.condition = guildPost.getCondition(); // TODO: toString으로 변환해서 날려야할듯?
        this.memberId = guildPost.getMember().getId();
    }
}
