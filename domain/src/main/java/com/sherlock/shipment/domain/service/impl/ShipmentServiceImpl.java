package com.sherlock.shipment.domain.service.impl;

import com.sherlock.model.Parcel;
import com.sherlock.model.Shipment;
import com.sherlock.shipment.domain.service.ShipmentService;
import com.sherlock.shipment.domain.core.ShipmentDataBase;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/9 19:59
 */
@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Override
    public List<Parcel> merge(String addrKey) {
        System.out.println("merge");
        return ShipmentDataBase.merge(addrKey);
    }

    @Override
    public boolean split(Shipment shipment) {
        System.out.println("split");
        return ShipmentDataBase.split(shipment);
    }
}
