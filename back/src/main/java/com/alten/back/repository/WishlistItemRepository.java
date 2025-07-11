package com.alten.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.back.model.User;
import com.alten.back.model.WishlistItem;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByUser(User user);
}
