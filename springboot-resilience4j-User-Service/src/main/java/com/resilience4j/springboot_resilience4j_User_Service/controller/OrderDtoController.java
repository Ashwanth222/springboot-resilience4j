package com.resilience4j.springboot_resilience4j_User_Service.controller;

import com.resilience4j.springboot_resilience4j_User_Service.model.OrderDTO;
import com.resilience4j.springboot_resilience4j_User_Service.repository.OderDtoRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/ordersDto")
public class OrderDtoController {

    @Autowired
    private RestTemplate restTemplate;
    private static final String BASE_URL
            = "http://localhost:8080/";

    private static final String USER_SERVICE = "userService";

    @Autowired
    OderDtoRepository orderDtoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderDtoRepository.save(orderDTO);
    }

    int count=1;
    @GetMapping
    //@CircuitBreaker(name = USER_SERVICE, fallbackMethod = "getAllAvailableProducts")
    @Retry(name = USER_SERVICE)
    //@RateLimiter(name = USER_SERVICE)
    public List<OrderDTO> getOrderDtos() {
        String url = BASE_URL + "orders";
        System.out.println("Retry method called " + count++ + " times at " + new Date());
        return restTemplate.getForObject(
                url,
                ArrayList.class
        );
    }


        public List<OrderDTO> getAllAvailableProducts(Exception e){
            return orderDtoRepository.findAll();
        }



}