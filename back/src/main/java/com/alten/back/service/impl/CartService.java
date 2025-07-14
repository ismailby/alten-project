package com.alten.back.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alten.back.repository.CartItemRepository;
import com.alten.back.service.ICartService;
import com.alten.back.service.IProductService;

public class CartService implements ICartService {
	
	 @Autowired
	 private CartItemRepository cartRepository;
	 
	 @Autowired 
	 private IProductService productService;

}
