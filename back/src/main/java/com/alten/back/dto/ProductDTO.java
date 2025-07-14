package com.alten.back.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data Transfer Object representing a Product.")
public class ProductDTO {
	    private Long id;
	    @Schema(description = "Code of product.",example = "AA654B")
	    private String code;
	    private String name;
	    private String description;
	    private String image;
	    private String category;
	    private double price;
	    private int quantity;
	    private String internalReference;
	    private Long shellId;
	    private String inventoryStatus;
	    private int rating;
	    private long createdAt;
	    private long updatedAt;
}
