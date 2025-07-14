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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.alten.back.constants.Constants;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "Controller for Product")
public class ProductController {

    @Autowired
    private ProductService productService;

   
    @Operation(summary = "Get Product", description = "Get list of Products")
    @GetMapping
    public List<ProductDTO> all() {
        return productService.findAll();
    }

    
    @Operation(summary = "Get Product By ID", description = "Get Product By ID")
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) throws ErrorResponse {
        return productService.findById(id);
    }

    @Operation(summary = "Create Product", description = "Create Product")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO, Authentication auth) throws ErrorResponse {
        return ResponseEntity.ok(productService.create(productDTO,auth));
    }

    @Operation(summary = "Edit Product", description = "Edit Product")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO, Authentication auth) throws ErrorResponse {
    	 return ResponseEntity.ok(productService.update(id, productDTO,auth));
    }

    @Operation(summary = "Delete Product", description = "Delete Product")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) throws ErrorResponse {
    	productService.delete(id,auth);
        return ResponseEntity.noContent().build();
    }
   
}
