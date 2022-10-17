package com.neuedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 施子安
 * @create
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AdminApp2 {
    public static void main(String[] args) {
        SpringApplication.run(AdminApp2.class);
    }
}
