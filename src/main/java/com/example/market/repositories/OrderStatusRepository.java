package com.example.market.repositories;

import com.example.market.entites.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
    OrderStatus findByTitle(String title);
}
