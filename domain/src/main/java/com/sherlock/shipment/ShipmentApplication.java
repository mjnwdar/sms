package com.sherlock.shipment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/9 20:12
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan()
public class ShipmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShipmentApplication.class, args);
    }
}
