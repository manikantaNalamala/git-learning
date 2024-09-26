package com.example.couponsystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.couponsystem.entity.Coupon;
import com.example.couponsystem.model.Cart;
import com.example.couponsystem.service.CouponApplicationService;

@RestController
@RequestMapping("/coupon") 	 
public class CouponController {
	@Autowired
	private CouponApplicationService couponService;

	@Autowired
	private CouponApplicationService couponApplicationService;
	
	@GetMapping("/get")
	public String getSample() {
		return "its working";
	}
	
	@PostMapping("/insert-coupon")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.createCoupon(coupon));
    }
	@GetMapping("/{id}")
    public ResponseEntity<Coupon> getCoupon(@PathVariable Long id) {
        Optional<Coupon> coupon = couponService.getCoupon(id);
        return coupon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/apply-coupon/{id}")
    public ResponseEntity<Double> applyCoupon(@RequestBody Cart cart, @PathVariable Long id) {
        double discount = couponApplicationService.applyCoupon(cart, id);
        return ResponseEntity.ok(discount);
    }

}
