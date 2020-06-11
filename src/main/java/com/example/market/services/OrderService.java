package com.example.market.services;


import com.example.market.entites.Order;
import com.example.market.entites.User;
import com.example.market.repositories.OrderRepository;
import com.example.market.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderStatusService orderStatusService;
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderStatusService orderStatusService, DeliveryAddressService deliveryAddressService) {
        this.orderRepository = orderRepository;
        this.orderStatusService = orderStatusService;
        this.deliveryAddressService = deliveryAddressService;
    }


    public Order findById(Long id) {
        return orderRepository.findOrderById(id);
    }

    public List<Order> findByUserId(Long id){
        List<Order> listOrder = orderRepository.findAllByUserId(id);
        return listOrder;
    }

    public List<Order> findall(){
        List<Order> listOrder = orderRepository.findAll();
        return listOrder;
    }

    public Order createOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getTotalCost());
        order.setStatus(orderStatusService.findByTitle("Сформирован"));
        order.setDeliveryAddress(deliveryAddressService.findFirstByUserId(user.getId()));
        order.setPhoneNumber("+");
        order.setDeliveryDate(LocalDateTime.now());
        order.setDeliveryPrice(0.0);
        orderRepository.save(order);
        return order;
    }

}

