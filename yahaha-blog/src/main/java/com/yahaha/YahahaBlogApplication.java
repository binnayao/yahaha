package com.yahaha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("com.yahaha.mapper")
@EnableScheduling
public class YahahaBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(YahahaBlogApplication.class, args);
    }
}