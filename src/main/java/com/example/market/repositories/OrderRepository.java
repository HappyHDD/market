package com.example.market.repositories;

import com.example.market.entites.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findOrderById(Long id);
    List<Order> findAll();
    List<Order> findAllByUserId(Long id);
}
