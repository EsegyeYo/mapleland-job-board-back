package com.ma_recruit.repository.party;

import com.ma_recruit.entity.party.PartyPost;
import com.ma_recruit.entity.party.PartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyPostRepository extends JpaRepository<PartyPost, Integer> {
    Page<PartyPost> findAll(Pageable pageable);
    Page<PartyPost> findByPartyType(PartyType partyType, Pageable pageable);
}
