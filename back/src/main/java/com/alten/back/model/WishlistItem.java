package com.alten.back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class WishlistItem {
    @Id @GeneratedValue
    private Long id;
    
    @ManyToOne 
    private User user;
    
    @ManyToOne 
    private Product product;
}