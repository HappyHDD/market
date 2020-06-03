package com.example.market.services;

import com.example.market.entites.OrderItem;
import com.example.market.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}

