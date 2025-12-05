package com.ma_recruit.entity.party;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorldMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String world_url;

    @OneToMany(mappedBy = "worldMap", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MapleMap> mapleMap = new ArrayList<>();
}
