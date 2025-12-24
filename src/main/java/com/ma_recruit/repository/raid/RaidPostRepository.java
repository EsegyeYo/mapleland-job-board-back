package com.ma_recruit.repository.raid;

import com.ma_recruit.entity.party.PartyPost;
import com.ma_recruit.entity.raid.RaidPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RaidPostRepository extends JpaRepository<RaidPost, BigInteger> {
    Page<RaidPost> findAll(Pageable pageable);

}
