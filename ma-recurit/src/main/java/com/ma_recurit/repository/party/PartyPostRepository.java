package com.ma_recurit.repository.party;

import com.ma_recurit.entity.party.PartyPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PartyPostRepository extends JpaRepository<PartyPost, BigInteger> {
}
