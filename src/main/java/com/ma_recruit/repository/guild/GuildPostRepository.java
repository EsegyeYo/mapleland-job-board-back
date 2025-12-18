package com.ma_recruit.repository.guild;

import com.ma_recruit.entity.guild.GuildPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface GuildPostRepository extends JpaRepository<GuildPost, BigInteger> {
}
