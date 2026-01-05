package com.ma_recruit.repository.guild;

import com.ma_recruit.entity.guild.GuildPost;
import com.ma_recruit.entity.party.PartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildPostRepository extends JpaRepository<GuildPost, Integer> {
    Page<GuildPost> findAll(Pageable pageable);
}
