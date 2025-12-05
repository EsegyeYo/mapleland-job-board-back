package com.ma_recruit.entity.raid;

import com.ma_recruit.entity.BaseEntity;
import com.ma_recruit.entity.member.Job;
import com.ma_recruit.entity.member.Member;
import com.ma_recruit.entity.party.PartyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Entity
@Getter
@RequiredArgsConstructor
public class RaidPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "raid_mob_id", nullable = false)
    private RaidMob raidMob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
