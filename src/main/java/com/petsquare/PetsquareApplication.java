package com.petsquare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.petsquare.mapper")
@SpringBootApplication
public class PetsquareApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetsquareApplication.class, args);
    }

}
