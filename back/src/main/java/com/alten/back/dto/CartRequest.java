package com.alten.back.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data Transfer Object representing a CartRequest.")
public class CartRequest {
	private Long productId;
	private int quantity;

}
