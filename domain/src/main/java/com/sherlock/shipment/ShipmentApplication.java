package com.sherlock.shipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/9 20:12
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.sherlock.shipment.infrastructure.mapper"})
public class ShipmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShipmentApplication.class, args);
    }
}
