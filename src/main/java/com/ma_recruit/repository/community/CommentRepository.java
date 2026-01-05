package com.ma_recruit.repository.community;

import com.ma_recruit.entity.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
