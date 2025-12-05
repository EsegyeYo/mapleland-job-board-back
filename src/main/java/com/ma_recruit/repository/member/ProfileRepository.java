package com.ma_recruit.repository.member;

import com.ma_recruit.entity.member.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProfileRepository extends JpaRepository<Profile, BigInteger> {
}
