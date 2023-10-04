package com.bucket.ice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class IceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IceApplication.class, args);
    }

}
