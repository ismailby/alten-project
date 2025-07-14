package com.alten.back.dto;

import lombok.Data;

@Data
public class CartRequest {
	private Long productId;
	private int quantity;

}
