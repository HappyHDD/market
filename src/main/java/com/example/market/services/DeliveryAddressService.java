package com.example.market.services;

import com.example.market.entites.DeliveryAddress;
import com.example.market.repositories.DeliveryAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {
    private DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository){
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public List<DeliveryAddress> findByUserId(Long id){
        return deliveryAddressRepository.findByUserId(id);
    }

    public DeliveryAddress findFirstByUserId(Long id) {
        return deliveryAddressRepository.findFirstByUserId(id);
    }
}
