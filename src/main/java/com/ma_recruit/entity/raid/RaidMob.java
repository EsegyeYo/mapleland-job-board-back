package com.ma_recruit.entity.raid;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class RaidMob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image_url;

    // 몹에 대한 포스트들을 불러 올 수도 있기 때문에 추가
    @OneToMany(mappedBy = "raidMob", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RaidPost> raidPost = new ArrayList<>();
}
