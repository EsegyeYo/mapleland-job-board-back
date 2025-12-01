package com.ma_recurit.repository.member;

import com.ma_recurit.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface MemberRepository extends JpaRepository<Member, BigInteger> {
}
