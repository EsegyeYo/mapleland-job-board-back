package com.ma_recruit.repository.community;

import com.ma_recruit.entity.community.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, BigInteger> {
}
