package com.example.couponsystem.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.couponsystem.entity.CouponType;
import com.example.couponsystem.service.CouponService;
import com.example.couponsystem.serviceImpl.BxGyCouponStrategy;
import com.example.couponsystem.serviceImpl.CartWiseCouponStrategy;
import com.example.couponsystem.serviceImpl.ProductWiseCouponStrategy;

public class CouponStrategyFactory {
	@Autowired
    private CartWiseCouponStrategy cartWiseCouponStrategy;

    @Autowired
    private ProductWiseCouponStrategy productWiseCouponStrategy;

    @Autowired
    private BxGyCouponStrategy bxGyCouponStrategy;

    public CouponService getStrategy(CouponType type) {
        switch (type) {
            case CART_WISE:
                return cartWiseCouponStrategy;
            case PRODUCT_WISE:
                return productWiseCouponStrategy;
            case BXGY:
                return bxGyCouponStrategy;
            default:
                throw new IllegalArgumentException("Invalid coupon type");
        }
    }

}
