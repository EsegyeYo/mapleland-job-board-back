package com.ma_recruit.repository.party;

import com.ma_recruit.entity.party.WorldMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface WorldMapRepository extends JpaRepository<WorldMap, BigInteger> {
}
