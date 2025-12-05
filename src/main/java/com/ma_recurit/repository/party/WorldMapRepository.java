package com.ma_recurit.repository.party;

import com.ma_recurit.entity.party.WorldMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface WorldMapRepository extends JpaRepository<WorldMap, BigInteger> {
}
