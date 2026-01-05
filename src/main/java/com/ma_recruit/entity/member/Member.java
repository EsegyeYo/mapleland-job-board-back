package com.ma_recruit.entity.member;

import com.ma_recruit.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String discord_id;

    @Column(nullable = false)
    private String discord_name;

    private String discord_photo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean member_ban;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profile> profile = new ArrayList<>();

    @Builder
    public Member(int id, String discord_id, String discord_photo, Role role, boolean user_ban, List<Profile> profile, String discordName) {
        this.id = id;
        this.discord_id = discord_id;
        this.role = role;
        this.discord_photo = discord_photo;
        this.member_ban = user_ban;
        this.discord_name = discordName;
        this.profile = profile;
    }

    public void updateDiscordPhoto(String discordUrl) {
        this.discord_photo = discordUrl;
    }

    public void updateMemberBan(boolean user_ban) {
        this.member_ban = user_ban;
    }

}
