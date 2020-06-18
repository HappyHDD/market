package com.example.market.repositories;

import com.example.market.entites.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class OrderStatusProvider {
    private final Sql2o sql2o;

    private static final String SELECT_BY_TITLE = "select * from order_statuses where title = :title";

    public OrderStatusProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public OrderStatus findByTitle(String title) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_BY_TITLE, false)
                    .addParameter("title", title)
                    .setColumnMappings(OrderStatus.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(OrderStatus.class);
        }
    }
}
