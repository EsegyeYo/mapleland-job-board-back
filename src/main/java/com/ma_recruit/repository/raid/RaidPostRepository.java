package com.ma_recruit.repository.raid;

import com.ma_recruit.entity.raid.RaidPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RaidPostRepository extends JpaRepository<RaidPost, BigInteger> {

}
