package com.alten.back.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Produit avec ID " + id + " introuvable");
    }
}