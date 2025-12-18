package com.ma_recruit.entity.guild;

import com.ma_recruit.entity.BaseEntity;
import com.ma_recruit.entity.member.Job;
import com.ma_recruit.entity.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;

@Entity
@Getter
@RequiredArgsConstructor
public class GuildPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    // 설명 최소 3글자?
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean status;

    @Enumerated(EnumType.STRING)
    private Job job;

    @Column(nullable = false)
    private String guild_name;

    private ArrayList<String> condition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public GuildPost(BigInteger id, String description, boolean status, Job job, String guild_name, ArrayList<String> condition, Member member) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.job = job;
        this.guild_name = guild_name;
        this.condition = condition;
        this.member = member;
    }

    public void updateDescription(String description) {
        this.description = description;
    }
    public void updateStatus(boolean status) {
        this.status = status;
    }
    public void updateJob(Job job) {
        this.job = job;
    }
    public void updateCondition(ArrayList<String> condition) {
        this.condition = condition;
    }
    public void updateGuildName(String guild_name) {
        this.guild_name = guild_name;
    }
    public void updateMember(Member member) {
        this.member = member;
    }
}
