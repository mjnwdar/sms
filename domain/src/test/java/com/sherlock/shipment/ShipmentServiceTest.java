package com.sherlock.shipment;

import com.alibaba.fastjson.JSON;
import com.sherlock.model.Contact;
import com.sherlock.model.Parcel;
import com.sherlock.model.Shipment;
import com.sherlock.shipment.domain.service.ShipmentService;
import com.sherlock.shipment.domain.core.ShipmentDataBase;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/5 21:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipmentServiceTest {

    @Autowired
    private ShipmentService shipmentService;

    @Test
    public void test_split_and_merge_parcel_when_cargo_ship_enter_the_port() {
        Shipment shipment = new Shipment();
        List<Parcel> parcels = new ArrayList<>(100);
        Contact from = new Contact();
        from.setName("sam");
        from.setPhoneNo("003277388");
        from.setZip("029387");
        from.setCountry("US");
        from.setProvince("New York");
        from.setCity("New York");
        from.setStreet("Same street");
        from.setAddress("No.499, Road 1");
        Contact to = new Contact();
        to.setName("Ben");
        to.setPhoneNo("13929893983");
        to.setZip("200038");
        to.setCountry("ZH");
        to.setProvince("Shang hai");
        to.setCity("Shang hai");
        to.setStreet("HongMei street");
        to.setAddress("No.4932, Road X");
        for (int i = 0; i < 5; i++) {
            Parcel parcel = new Parcel();
            parcel.setFrom(from);
            parcel.setTo(to);
            parcel.setType(i);
            parcel.setHeight(i * 100);
            parcel.setWidth(i * 200);
            parcel.setWeight(i * 5L);
            parcel.setArrivalTime(LocalDateTime.now());
            parcel.setDeliveryTime(LocalDateTime.of(2020, 7, 1, 0, 0, 0));
            parcel.setDescription("this is parcel no." + i);
            parcels.add(parcel);
        }
        shipment.setParcels(parcels);
        boolean isDone = shipmentService.split(shipment);
        Assert.assertFalse(isDone);
        Assert.assertEquals(ShipmentDataBase.getDbSize(), 5);
        final List<Parcel> merge = shipmentService.merge(to.toString());
        System.out.println(JSON.toJSONString(merge));
        Assert.assertEquals(merge.size(), 5);
        Assert.assertEquals(ShipmentDataBase.getDbSize(), 0);
    }
}