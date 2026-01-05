package com.ma_recruit.entity.community;

import com.ma_recruit.entity.BaseEntity;
import com.ma_recruit.entity.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class CommunityPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String image_url;

    @Column(nullable = false)
    private int view_count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_category_id", nullable = false)
    private CommunityCategory communityCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "communityPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comment = new ArrayList<>();

}
