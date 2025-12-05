package com.ma_recruit.repository.party;

import com.ma_recruit.entity.party.MapleMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface MapleMapRepository extends JpaRepository<MapleMap, BigInteger> {
}
