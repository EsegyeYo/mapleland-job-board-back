package com.ma_recurit.repository.community;

import com.ma_recurit.entity.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CommentRepository extends JpaRepository<Comment, BigInteger> {
}
