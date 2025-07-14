package com.alten.back.dto;

import lombok.Data;

@Data
public class ProductDTO {
	    private Long id;
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
