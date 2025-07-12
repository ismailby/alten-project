package com.alten.back.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.back.exception.ProductNotFoundException;
import com.alten.back.model.Product;
import com.alten.back.repository.ProductRepository;
import com.alten.back.service.IProductService;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));
    }

    public Product save(Product product) {
        product.setCreatedAt(System.currentTimeMillis());
        product.setUpdatedAt(System.currentTimeMillis());
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated) {
        Optional<Product> product = productRepository.findById(id);
        updated.setId(id);
        updated.setCreatedAt(product.get().getCreatedAt());
        updated.setUpdatedAt(System.currentTimeMillis());
        return productRepository.save(updated);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    
}
