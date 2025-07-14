package com.alten.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.back.dto.ProductDTO;
import com.alten.back.exception.ErrorResponse;
import com.alten.back.model.Product;
import com.alten.back.service.impl.ProductService;
import com.alten.back.constants.Constants;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> all() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) throws ErrorResponse {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO, Authentication auth) throws ErrorResponse {
        return ResponseEntity.ok(productService.create(productDTO,auth));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO, Authentication auth) throws ErrorResponse {
    	 return ResponseEntity.ok(productService.update(id, productDTO,auth));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) throws ErrorResponse {
    	productService.delete(id,auth);
        return ResponseEntity.noContent().build();
    }
   
}
