package com.example.market;

import com.example.market.entites.Category;
import com.example.market.entites.OrderItem;
import com.example.market.entites.User;
import com.example.market.repositories.CategoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;


@SpringBootApplication
public class MarketApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
    }



    private CategoryProvider repository;

    public MarketApplication(@Autowired CategoryProvider repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Category> list = repository.findAll();
        for (Category item: list){
            System.out.println(item.getId());
        }
    }
}
