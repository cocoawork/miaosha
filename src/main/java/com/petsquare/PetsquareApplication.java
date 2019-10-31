package com.petsquare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.petsquare.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class PetsquareApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetsquareApplication.class, args);
    }

}
