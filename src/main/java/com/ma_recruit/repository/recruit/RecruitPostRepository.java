package com.ma_recruit.repository.recruit;

import com.ma_recruit.entity.raid.RaidPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RecruitPostRepository extends JpaRepository<RaidPost, BigInteger> {
}
