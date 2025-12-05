package com.ma_recurit.repository.party;

import com.ma_recurit.entity.party.MapleMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface MapleMapRepository extends JpaRepository<MapleMap, BigInteger> {
}
