package com.ma_recruit.repository.recruit;

import com.ma_recruit.entity.raid.RaidMob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RecruitCategoryRepository extends JpaRepository<RaidMob, BigInteger> {
}
