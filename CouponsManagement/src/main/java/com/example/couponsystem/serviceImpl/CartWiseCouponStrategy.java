package com.example.couponsystem.serviceImpl;

import org.springframework.stereotype.Component;

import com.example.couponsystem.entity.Coupon;
import com.example.couponsystem.model.Cart;
import com.example.couponsystem.service.CouponService;

@Component
public class CartWiseCouponStrategy implements CouponService {

	@Override
	public double applyDiscount(Cart cart, Coupon coupon) {

		double total = cart.getItems().stream().mapToDouble(iteme -> iteme.getPrice() * iteme.getQuantity()).sum();
		int thershold = (int) coupon.getDetails().get("thershold");
		int discount = (int) coupon.getDetails().get("discount");
		if (total > thershold) {
			return total * discount / 100.0;
		}
		return 0;
	}

}
