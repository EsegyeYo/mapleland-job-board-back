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
@NoArgsConstructor
@Table(name = "world_map")
public class WorldMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String world_url;

    @OneToMany(mappedBy = "worldMap", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MapleMap> mapleMap = new ArrayList<>();

    public WorldMap(String name, String world_url) {
        this.name = name;
        this.world_url = world_url;
    }

}
