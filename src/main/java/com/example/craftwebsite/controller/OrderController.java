package com.example.craftwebsite.controller;

import com.example.craftwebsite.model.*;
import com.example.craftwebsite.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeorder")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping("/{orderId}")
    public Optional<Order> getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }
}
