package com.example.market.repositories;

import com.example.market.entites.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class OrderItemProvider  {
    private final Sql2o sql2o;

    private static final String INSERT_ORDERITEM = "insert into orders_item(product_id, order_id, quantity, item_price, total_price) values(:insert_product_id, :insert_order_id," +
            " :insert_quantity, :insert_item_price, :insert_total_price)";

    private static final String UPDATE_ORDERITEM = "update orders_item set product_id = :update_product_id, order_id = :update_order_id, quantity = :update_quantity," +
            " item_price = :update_item_price, total_price = :update_total_price where id = :oi_id";

    private static final String CHECK_ORDERITEM = "select id from orders_item where id = :check_id";

    public OrderItemProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public void save(OrderItem orderItem) {
        try (Connection connection = sql2o.open()) {
            if(connection.createQuery(CHECK_ORDERITEM)
                    .addParameter("check_id", orderItem.getId())
                    .setColumnMappings(OrderItem.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(OrderItem.class).getId() != null){
                connection.createQuery(UPDATE_ORDERITEM, false)
                        .addParameter("check_id", orderItem.getId())
                        .addParameter("update_product_id", orderItem.getProduct())
                        .addParameter("update_order_id", orderItem.getOrder())
                        .addParameter("update_quantity", orderItem.getQuantity())
                        .addParameter("update_item_price", orderItem.getItemPrice())
                        .addParameter("update_total_price", orderItem.getTotalPrice())
                        .setColumnMappings(OrderItem.COLUMN_MAPPINGS)
                        .executeUpdate();
            }else {
                connection.createQuery(INSERT_ORDERITEM, true)
                        .addParameter("insert_product_id", orderItem.getProduct())
                        .addParameter("insert_order_id", orderItem.getOrder())
                        .addParameter("insert_quantity", orderItem.getQuantity())
                        .addParameter("insert_item_price", orderItem.getItemPrice())
                        .addParameter("insert_total_price", orderItem.getTotalPrice())
                        .setColumnMappings(OrderItem.COLUMN_MAPPINGS)
                        .executeUpdate();
            }
        }

    }
}
