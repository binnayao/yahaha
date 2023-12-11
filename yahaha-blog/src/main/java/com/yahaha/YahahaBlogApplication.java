package com.yahaha;

import com.yahaha.domain.entity.Article;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;

@SpringBootApplication
@MapperScan("com.yahaha.mapper")
public class YahahaBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(YahahaBlogApplication.class, args);
    }
}
