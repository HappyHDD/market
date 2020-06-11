package com.example.market.repositories;

import com.example.market.entites.DeliveryAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress, Long> {
    DeliveryAddress findFirstByUserId(Long id);

    List<DeliveryAddress> findByUserId(Long id);
}
