package com.example.couponsystem.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.couponsystem.entity.Coupon;
import com.example.couponsystem.model.Cart;
import com.example.couponsystem.model.CartItem;
import com.example.couponsystem.service.CouponService;

@Component
public class BxGyCouponStrategy implements CouponService {

	@Override
	public double applyDiscount(Cart cart, Coupon coupon) {
		Long productId = Long.valueOf(coupon.getDetails().get("product_id").toString());
		int buyQuantity = (int) coupon.getDetails().get("buy_quantity");
		int freeQuantity = (int) coupon.getDetails().get("free_quantity");

		List<CartItem> items = cart.getItems().stream().filter(item -> item.getProductId().equals(productId))
				.sorted(Comparator.comparingDouble(CartItem::getPrice).reversed()).collect(Collectors.toList());
		if (items.isEmpty())
			return 0;

		CartItem item = items.get(0);
		int applicableSets = item.getQuantity() / buyQuantity;
		double discount = applicableSets * freeQuantity * item.getPrice();

		return discount;

	}

}
