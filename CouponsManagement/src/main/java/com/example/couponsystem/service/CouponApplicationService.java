package com.example.couponsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.couponsystem.entity.Coupon;
import com.example.couponsystem.model.Cart;
import com.example.couponsystem.repo.CouponRepository;
import com.example.couponsystem.util.CouponStrategyFactory;

@Service
public class CouponApplicationService {
	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CouponStrategyFactory strategyFactory;

	public double applyCoupon(Cart cart, Long couponId) {
		Optional<Coupon> couponOpt = couponRepository.findById(couponId);
		if (couponOpt.isPresent()) {
			Coupon coupon = couponOpt.get();
			return strategyFactory.getStrategy(coupon.getType()).applyDiscount(cart, coupon);
		}
		throw new IllegalArgumentException("Coupon not found");
	}

	public Coupon createCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}

	public Optional<Coupon> getCoupon(Long id) {
		return couponRepository.findById(id);
	}

	public void deleteCoupon(Long id) {
		couponRepository.deleteById(id);
	}
}
