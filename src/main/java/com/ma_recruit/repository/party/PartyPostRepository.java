package com.ma_recruit.repository.party;

import com.ma_recruit.entity.party.PartyPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PartyPostRepository extends JpaRepository<PartyPost, BigInteger> {
    Page<PartyPost> findAll(Pageable pageable);
}
