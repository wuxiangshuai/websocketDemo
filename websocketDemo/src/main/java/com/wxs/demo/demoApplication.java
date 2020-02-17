package com.wxs.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class demoApplication {

    public static void main(String[] args) {
        SpringApplication.run(demoApplication.class, args);
    }

}
