package com.example.market.services;

import com.example.market.entites.OrderStatus;
import com.example.market.repositories.OrderStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {
    private OrderStatusRepository orderStatusRepository;

    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public OrderStatus findByTitle(String title){
        return orderStatusRepository.findByTitle(title);
    }
}