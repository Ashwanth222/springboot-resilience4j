package com.resilience4j.springboot_resilience4j_demo.controller;

import com.resilience4j.springboot_resilience4j_demo.model.Order;
import com.resilience4j.springboot_resilience4j_demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(
            @RequestBody Order order
    ) {
      return orderRepository.save(order);
    }

    @GetMapping
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
    @GetMapping("/{category}")
    public List<Order> getOrdersByCategory(@PathVariable String category){
        return orderRepository.findByCategory(category);
    }

}
