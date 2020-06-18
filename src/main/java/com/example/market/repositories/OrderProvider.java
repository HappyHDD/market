package com.example.market.repositories;

import com.example.market.entites.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class OrderProvider {
    private final Sql2o sql2o;

    public OrderProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    private static final String SELECT_ORDER_QUERY = "select * from orders where id = :findorder_id";

    private static final String SELECT_ORDER_BY_USER_QUERY = "select * from orders where user_id = :find_id";

    private static final String INSERT_ORDER = "insert into orders(user_id, price, delivery_price, phone_number, status_id, delivery_date) values(:insert_user_id, :insert_price," +
            " :insert_delivery_price, :insert_phone_number, :insert_status_id, :delivery_date)";

    private static final String UPDATE_ORDER = "update orders set user_id = :update_user_id, price = :update_price, delivery_price = :update_delivery_price," +
            " phone_number = :update_phone_number, status_id = :update_status_id, delivery_date = :delivery_date" +
            " where id = :check_id";

    private static final String CHECK_ORDER = "select id from orders where id = :check_id";

    public Order findOrderById(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ORDER_QUERY, false)
                    .addParameter("findorder_id", id)
                    .setColumnMappings(Order.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(Order.class);
        }
    }

    public List<Order> findAllByUserId(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ORDER_BY_USER_QUERY, false)
                    .addParameter("find_id", id)
                    .setColumnMappings(Order.COLUMN_MAPPINGS)
                    .executeAndFetch(Order.class);
        }
    }


    public void save(Order order) {
        try (Connection connection = sql2o.open()) {
            if(connection.createQuery(CHECK_ORDER)
                    .addParameter("check_id", order.getId())
                    .setColumnMappings(Order.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(Order.class).getId() != null){
                connection.createQuery(UPDATE_ORDER, false)
                        .addParameter("check_id", order.getId())
                        .addParameter("update_user_id", order.getUser())
                        .addParameter("update_price", order.getPrice())
                        .addParameter("update_delivery_price", order.getDeliveryPrice())
                        .addParameter("update_phone_number", order.getPhoneNumber())
                        .addParameter("update_status_id", order.getStatus())
                        .addParameter("delivery_date", order.getDeliveryDate())
                        .setColumnMappings(Order.COLUMN_MAPPINGS)
                        .executeUpdate();
            }else {
                connection.createQuery(INSERT_ORDER, false)
                        .addParameter("insert_user_id", order.getUser())
                        .addParameter("insert_price", order.getPrice())
                        .addParameter("insert_delivery_price", order.getDeliveryPrice())
                        .addParameter("insert_phone_number", order.getPhoneNumber())
                        .addParameter("insert_status_id", order.getStatus())
                        .addParameter("delivery_date", order.getDeliveryDate())
                        .setColumnMappings(Order.COLUMN_MAPPINGS)
                        .executeUpdate();
            }
        }

    }
}
