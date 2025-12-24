package com.ma_recruit.repository.raid;

import com.ma_recruit.entity.raid.RaidMob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RaidMobRepository extends JpaRepository<RaidMob, BigInteger> {

}
