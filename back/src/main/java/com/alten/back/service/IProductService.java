package com.alten.back.service;

import java.util.List;
import java.util.Optional;

import com.alten.back.model.Product;

public interface IProductService {
	    List<Product> findAll();
	    Optional<Product> findById(Long id);
	    Product save(Product product);
	    Product update(Long id, Product updated);
	    void delete(Long id);
}
