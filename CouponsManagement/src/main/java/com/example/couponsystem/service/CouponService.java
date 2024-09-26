package com.example.couponsystem.service;

import com.example.couponsystem.entity.Coupon;
import com.example.couponsystem.model.Cart;


public interface CouponService {
	
	double applyDiscount(Cart cart, Coupon coupon);

	


}
