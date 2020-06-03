package com.sherlock.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sherlock
 * @since 2020-06-02
 */
@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @GetMapping("")
    public String index() {
        return "hello world";
    }
}
