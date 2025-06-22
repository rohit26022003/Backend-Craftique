package com.example.craftwebsite.service;

import com.example.craftwebsite.model.*;
import com.example.craftwebsite.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order placeOrder(Order order) {
        return orderRepo.save(order);
    }

    public Optional<Order> getOrder(Long orderId) {
        return orderRepo.findById(orderId);
    }
}
