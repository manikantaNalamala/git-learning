package com.example.couponsystem.serviceImpl;

import org.springframework.stereotype.Component;

import com.example.couponsystem.entity.Coupon;
import com.example.couponsystem.model.Cart;
import com.example.couponsystem.service.CouponService;

@Component
public class ProductWiseCouponStrategy implements CouponService {

	@Override
	public double applyDiscount(Cart cart, Coupon coupon) {
		Long productId = Long.valueOf(coupon.getDetails().get("product_id").toString());
		int discount = (int) coupon.getDetails().get("discount");

		return cart.getItems().stream().filter(item -> item.getProductId().equals(productId))
				.mapToDouble(item -> item.getPrice() * item.getQuantity() * discount / 100.0).sum();
	}

}
