package com.resilience4j.springboot_resilience4j_User_Service.repository;

import com.resilience4j.springboot_resilience4j_User_Service.model.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OderDtoRepository extends JpaRepository<OrderDTO, Integer> {

}
