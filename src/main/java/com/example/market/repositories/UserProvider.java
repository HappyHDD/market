package com.example.market.repositories;

import com.example.market.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class UserProvider {
    private final Sql2o sql2o;

    private static final String CHECK_USER = "select username from users where username = :user_name";

    private static final String SELECT_USER_QUERY = "select id, username, password, first_name, last_name, email from users" +
            " where username = :user_name";

    private static final String INSERT_USER = "insert into users(username, password, first_name, last_name, email) values(:insert_username, :insert_password," +
            " :insert_first_name, :insert_last_name, :insert_email)";

    private static final String UPDATE_USER = "update users set username = :update_username, password = :update_password, first_name = :update_first_name," +
            " last_name = :update_last_name, email = :update_email where username = :user_name";

    public UserProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public User findByUserName(String userName) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_USER_QUERY, false)
                    .addParameter("user_name", userName)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(User.class);
        }
    }

    public void save(User user) {
        try (Connection connection = sql2o.open()) {
            if(connection.createQuery(CHECK_USER)
                    .addParameter("user_name", user.getUserName()).
                            setColumnMappings(User.COLUMN_MAPPINGS).
                            executeAndFetchFirst(User.class).getId() != null){
                connection.createQuery(UPDATE_USER, false)
                        .addParameter("update_username", user.getUserName())
                        .addParameter("update_password", user.getPassword())
                        .addParameter("update_first_name", user.getFirstName())
                        .addParameter("update_last_name", user.getLastName())
                        .addParameter("update_email", user.getEmail())
                        .setColumnMappings(User.COLUMN_MAPPINGS)
                        .executeUpdate();
            }else {
                connection.createQuery(INSERT_USER, true)
                        .addParameter("insert_username", user.getUserName())
                        .addParameter("insert_password", user.getPassword())
                        .addParameter("insert_first_name", user.getFirstName())
                        .addParameter("insert_last_name", user.getLastName())
                        .addParameter("insert_email", user.getEmail())
                        .setColumnMappings(User.COLUMN_MAPPINGS)
                        .executeUpdate();
            }
        }
    }
}
