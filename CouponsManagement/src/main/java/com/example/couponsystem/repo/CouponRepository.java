package com.example.couponsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.couponsystem.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
