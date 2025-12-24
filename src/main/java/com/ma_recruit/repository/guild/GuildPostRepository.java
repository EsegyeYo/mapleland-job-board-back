package com.ma_recruit.repository.guild;

import com.ma_recruit.entity.guild.GuildPost;
import com.ma_recruit.entity.party.PartyPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface GuildPostRepository extends JpaRepository<GuildPost, BigInteger> {
    Page<GuildPost> findAll(Pageable pageable);
}
