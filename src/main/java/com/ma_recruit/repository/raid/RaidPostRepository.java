package com.ma_recruit.repository.raid;

import com.ma_recruit.entity.party.PartyType;
import com.ma_recruit.entity.raid.RaidPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaidPostRepository extends JpaRepository<RaidPost, Integer> {
    Page<RaidPost> findAll(Pageable pageable);
    Page<RaidPost> findByPartyType(PartyType partyType, Pageable pageable);
}
