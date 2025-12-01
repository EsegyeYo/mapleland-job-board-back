package com.ma_recurit.entity.recruit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class RecruitCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "recruitCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecruitPost> recruitPost = new ArrayList<>();
}
