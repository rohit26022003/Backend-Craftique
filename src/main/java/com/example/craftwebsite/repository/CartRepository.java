package com.example.craftwebsite.repository;

import com.example.craftwebsite.model.CartItem;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
     @Transactional
    void deleteByUserId(Long userId);
   
}
