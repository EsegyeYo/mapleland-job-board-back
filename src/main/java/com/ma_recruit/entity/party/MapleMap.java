package com.ma_recruit.entity.party;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "maple_map")
public class MapleMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String map_url;

    @ManyToOne(fetch = FetchType.LAZY)
    private WorldMap worldMap;

    public MapleMap(String name, String map_url, WorldMap worldMap) {
        this.name = name;
        this.map_url = map_url;
        this.worldMap = worldMap;
    }
}
