package com.alten.back.dto;

import com.alten.back.model.Product;
import com.alten.back.model.User;

import lombok.Data;

@Data
public class CartItemDTO {

	private Long id;
	private User user;
	private Product product;
	private int quantity;
}
