package com.ma_recruit.entity.party;

import com.ma_recruit.entity.BaseEntity;
import com.ma_recruit.entity.member.Job;
import com.ma_recruit.entity.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Entity
@Getter
@RequiredArgsConstructor
public class PartyPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    // 설명 최소 3글자?
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PartyType partyType;

    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maple_map_id", nullable = false)
    private MapleMap mapleMap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private int member_max_count;

    private int member_count;

    @Builder
    public PartyPost(BigInteger id, String description, boolean status, PartyType partyType, Job job, MapleMap mapleMap, Member member,  int member_max_count, int member_count) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.partyType = partyType;
        this.job = job;
        this.mapleMap = mapleMap;
        this.member = member;
        this.member_max_count = member_max_count;
        this.member_count = member_count;
    }

    public void updatePartyPost(String description, PartyType partyType, Job job, MapleMap mapleMap, Member member, int member_max_count, int member_count) {
        this.description = description;
        this.partyType = partyType;
        this.job = job;
        this.mapleMap = mapleMap;
        this.member = member;
        this.member_max_count = member_max_count;
        this.member_count = member_count;
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
    public void updateMapleMap(MapleMap mapleMap) {
        this.mapleMap = mapleMap;
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
