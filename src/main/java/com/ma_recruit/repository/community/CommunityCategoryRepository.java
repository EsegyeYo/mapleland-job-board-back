package com.ma_recruit.repository.community;

import com.ma_recruit.entity.community.CommunityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCategoryRepository extends JpaRepository<CommunityCategory, Integer> {
}
