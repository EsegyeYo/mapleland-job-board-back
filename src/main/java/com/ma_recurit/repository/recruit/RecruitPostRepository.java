package com.ma_recurit.repository.recruit;

import com.ma_recurit.entity.recruit.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RecruitPostRepository extends JpaRepository<RecruitPost, BigInteger> {
}
