package com.example.market.repositories;

import com.example.market.entites.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class CategoryProvider  {
    private final Sql2o sql2o;

    private static final String SELECT_CATEGORY = "select * from categories";

    public CategoryProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Category> findAll() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_CATEGORY, false)
                    .setColumnMappings(Category.COLUMN_MAPPINGS)
                    .executeAndFetch(Category.class);
        }
    }
}