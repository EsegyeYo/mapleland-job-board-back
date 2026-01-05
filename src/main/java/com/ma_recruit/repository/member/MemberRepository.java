package com.ma_recruit.repository.member;

import com.ma_recruit.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("select m from Member m where m.discord_id = :discordId")
    Optional<Member> findByDiscordId(@Param("discordId") String discordId);
}
