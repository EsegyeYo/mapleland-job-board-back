package com.ma_recurit.repository.community;

import com.ma_recurit.entity.community.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, BigInteger> {
}
