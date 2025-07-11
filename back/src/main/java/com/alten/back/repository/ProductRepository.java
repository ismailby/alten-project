package com.alten.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.back.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}