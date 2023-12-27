package com.yahaha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.yahaha.mapper")
public class YahahaBlogApplication {


    public static void main(String[] args) {
        SpringApplication.run(YahahaBlogApplication.class, args);
    }
}


class A {

}

class B extends A {

}

