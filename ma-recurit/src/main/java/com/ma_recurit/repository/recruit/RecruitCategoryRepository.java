package com.ma_recurit.repository.recruit;

import com.ma_recurit.entity.recruit.RecruitCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RecruitCategoryRepository extends JpaRepository<RecruitCategory, BigInteger> {
}
