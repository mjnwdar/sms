package com.sherlock.example.rpc;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sherlock.api.ApiShipmentService;
import com.sherlock.model.Parcel;
import com.sherlock.model.Shipment;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/9 20:39
 */
@Component
public class ShipmentRpcService {

    @Reference(version = "1.0.0")
    private ApiShipmentService apiShipmentService;


    public List<Parcel> merge(String addrKey) {
        return apiShipmentService.merge(addrKey);
    }

    public boolean split(Shipment shipment) {
        return apiShipmentService.split(shipment);
    }
}
