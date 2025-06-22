package com.example.craftwebsite.service;

import com.example.craftwebsite.model.*;
import com.example.craftwebsite.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepo;

    public CartService(CartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    public CartItem addItem(CartItem item) {
        Optional<CartItem> existingItem = cartRepo.findByUserIdAndProductId(item.getUserId(), item.getProductId());
        if (existingItem.isPresent()) {
            CartItem existingCartItem = existingItem.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
            return cartRepo.save(existingCartItem);
        }
        else {
            return cartRepo.save(item);
        }
    }

    public List<CartItem> getUserCart(Long userId) {
        return cartRepo.findByUserId(userId);
    }

   public void deletebyItemId(Long itemId) {
    cartRepo.deleteById(itemId);
}
    public void deletebyUserId(Long userId) {
        cartRepo.deleteByUserId(userId);
    }
    public CartItem updateQuantity(Long userId, Long productId, boolean increase) {
        Optional<CartItem> cartItemOpt = cartRepo.findByUserIdAndProductId(userId, productId);

        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            cartItem.setQuantity(increase ? cartItem.getQuantity() + 1 : Math.max(1, cartItem.getQuantity() - 1));
            return cartRepo.save(cartItem);
        } else {
            throw new RuntimeException("Cart item not found for userId: " + userId);
        }
    }

}
