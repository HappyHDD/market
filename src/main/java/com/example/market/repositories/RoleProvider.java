package com.example.market.repositories;

import com.example.market.entites.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class RoleProvider {
    private final Sql2o sql2o;

    private static final String SELECT_ROLE = "select * from roles where name = :name";

    private static final String SELECT_ROLE_BY_USER_ID = "select roles.id, roles.name from market.roles " +
            "join users_roles on users_roles.user_id = :user_id and users_roles.role_id = roles.id";

    public RoleProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public Role findOneByName(String roleName) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ROLE, false)
                    .addParameter("name", roleName)
                    .setColumnMappings(Role.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(Role.class);
        }
    }


    public List<Role> findByUserId(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ROLE_BY_USER_ID, false)
                    .addParameter("user_id", id)
                    .setColumnMappings(Role.COLUMN_MAPPINGS)
                    .executeAndFetch(Role.class);
        }
    }
}
