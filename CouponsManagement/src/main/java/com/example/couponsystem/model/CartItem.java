package com.example.couponsystem.model;

import lombok.Data;

@Data
public class CartItem {
	private Long productId;
	private Integer quantity;
	private Double price;
}
