package com.resilience4j.springboot_resilience4j_demo.repository;

import com.resilience4j.springboot_resilience4j_demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCategory(String category);
}
