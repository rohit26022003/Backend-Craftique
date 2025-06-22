package com.example.craftwebsite.repository;

import com.example.craftwebsite.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
