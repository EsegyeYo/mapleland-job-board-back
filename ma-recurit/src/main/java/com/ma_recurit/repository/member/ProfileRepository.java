package com.ma_recurit.repository.member;

import com.ma_recurit.entity.member.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProfileRepository extends JpaRepository<Profile, BigInteger> {
}
