package com.alten.back.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.back.model.Product;
import com.alten.back.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
    }

    public Product save(Product product) {
        product.setCreatedAt(System.currentTimeMillis());
        product.setUpdatedAt(System.currentTimeMillis());
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product product = findById(id);
        updated.setId(id);
        updated.setCreatedAt(product.getCreatedAt());
        updated.setUpdatedAt(System.currentTimeMillis());
        return productRepository.save(updated);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
