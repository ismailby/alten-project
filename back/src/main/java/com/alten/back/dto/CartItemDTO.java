package com.alten.back.dto;

import com.alten.back.model.Product;
import com.alten.back.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data

@Schema(description = "Data Transfer Object representing a CartItem.")
public class CartItemDTO {

	private Long id;
	private User user;
	private Product product;
	private int quantity;
}
