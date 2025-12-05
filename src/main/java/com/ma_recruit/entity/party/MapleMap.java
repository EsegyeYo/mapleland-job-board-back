package com.ma_recruit.entity.party;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Getter
@Entity
@RequiredArgsConstructor
public class MapleMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String map_url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "world_map_id", nullable = false)
    private WorldMap worldMap;
}
