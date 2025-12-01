package com.ma_recurit.entity.community;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class CommunityCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "communityCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityPost> communityPost = new ArrayList<>();
}

