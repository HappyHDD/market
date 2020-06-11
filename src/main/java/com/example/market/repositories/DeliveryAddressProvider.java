package com.example.market.repositories;

import com.example.market.entites.DeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class DeliveryAddressProvider {
    private final Sql2o sql2o;

    private static final String SELECT_USER_BY_ID = "select id, user_id, address from delivery_addresses";

    public DeliveryAddressProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public List<DeliveryAddress> findByUserId(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_USER_BY_ID, false)
                    .addParameter("u_id", id)
                    .setColumnMappings(DeliveryAddress.COLUMN_MAPPINGS)
                    .executeAndFetch(DeliveryAddress.class);
        }
    }


    public DeliveryAddress findFirstByUserId(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_USER_BY_ID, false)
                    .addParameter("u_id", id)
                    .setColumnMappings(DeliveryAddress.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(DeliveryAddress.class);
        }
    }
}
