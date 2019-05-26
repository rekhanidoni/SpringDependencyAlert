package com.dependencyalert;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.data.mongo.*;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(exclude =  {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
        System.out.println("yes in main");
    }
}
