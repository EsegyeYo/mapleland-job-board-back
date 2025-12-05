package com.ma_recurit.repository.community;

import com.ma_recurit.entity.community.CommunityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CommunityCategoryRepository extends JpaRepository<CommunityCategory, BigInteger> {
}
