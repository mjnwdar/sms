package com.sherlock.shipment.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sherlock.model.Parcel;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/15 20:02
 */
@SpringBootTest
class ShipmentServiceTest {
    @Autowired
    private ShipmentService shipmentService;

    @Test
    void test_merge() {
        final List<Parcel> merge = shipmentService.merge("");
        assertNull(merge);
    }

    @Test
    void test_split() {
    }
}