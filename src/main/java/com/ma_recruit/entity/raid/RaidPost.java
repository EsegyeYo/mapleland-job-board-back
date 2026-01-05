package com.ma_recruit.entity.raid;

import com.ma_recruit.entity.BaseEntity;
import com.ma_recruit.entity.member.Job;
import com.ma_recruit.entity.member.Member;
import com.ma_recruit.entity.party.PartyType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "raid_post")
public class RaidPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 설명 최소 3글자?
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PartyType partyType;

    @Enumerated(EnumType.STRING)
    private Job job;

    private int member_max_count;

    private int member_count;

    @ManyToOne(fetch = FetchType.LAZY)
    private RaidMob raidMob;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public RaidPost(int id, String description, boolean status, PartyType partyType, Job job, int member_max_count, int member_count, RaidMob raidMob, Member member) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.partyType = partyType;
        this.job = job;
        this.member_max_count = member_max_count;
        this.member_count = member_count;
        this.raidMob = raidMob;
        this.member = member;
    }

    public void updateDescription(String description) {
        this.description = description;
    }
    public void updateStatus(boolean status) {
        this.status = status;
    }
    public void updatePartyType(PartyType partyType) {
        this.partyType = partyType;
    }
    public void updateJob(Job job) {
        this.job = job;
    }
    public void updateRaidMob(RaidMob raidMob) {
        this.raidMob = raidMob;
    }
    public void updateMember(Member member) {
        this.member = member;
    }
    public void updateMember_max_count(int member_max_count) {
        this.member_max_count = member_max_count;
    }
    public void updateMember_count(int member_count) {
        this.member_count = member_count;
    }

}
