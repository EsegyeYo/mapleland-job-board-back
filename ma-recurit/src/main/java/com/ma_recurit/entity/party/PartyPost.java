package com.ma_recurit.entity.party;

import com.ma_recurit.entity.BaseEntity;
import com.ma_recurit.entity.member.Job;
import com.ma_recurit.entity.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class PartyPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
}
