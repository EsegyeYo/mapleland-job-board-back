package com.ma_recruit.entity.member;

import com.ma_recruit.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "profile")
public class Profile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private int level;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Profile(int id, String nickname, int level, Job job, Member member) {
        this.id = id;
        this.nickname = nickname;
        this.level = level;
        this.job = job;
        this.member = member;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
    public void updateLevel(int level) {
        this.level = level;
    }
    public void updateJob(Job job) {
        this.job = job;
    }
}
