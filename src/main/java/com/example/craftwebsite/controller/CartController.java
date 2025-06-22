package com.example.craftwebsite.controller;

import com.example.craftwebsite.model.*;
import com.example.craftwebsite.service.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //add cart item
    @PostMapping("/additem")
    public CartItem addItem(@RequestBody CartItem item) {
        return cartService.addItem(item);
    }
    // get cart by user id
    @GetMapping("/{userId}")
    public List<CartItem> getUserCart(@PathVariable Long userId) {
        return cartService.getUserCart(userId);
    }
    // delete cart by   item id
    @DeleteMapping("delete/item/{itemId}")
     public void deleteItemByItemid(@PathVariable Long itemId) {
    cartService.deletebyItemId(itemId);
}
    // delete all item of specific users
    @DeleteMapping("delete/{userId}")
    public void deleteItemByUserId(@PathVariable Long userId) {
        cartService.deletebyUserId(userId);
    }
    // increase or decrease quantity
     @PutMapping("/update/{userId}/{productId}")
    public ResponseEntity<CartItem> updateCartQuantity(@PathVariable Long userId,
                                                       @PathVariable Long productId,
                                                       @RequestParam boolean increase) {
        CartItem updatedItem = cartService.updateQuantity(userId, productId, increase);
        return ResponseEntity.ok(updatedItem);
    }

}
