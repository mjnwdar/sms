package com.sherlock.shipment.application;

import com.alibaba.dubbo.config.annotation.Service;
import com.sherlock.api.ApiShipmentService;
import com.sherlock.model.Parcel;
import com.sherlock.model.Shipment;
import com.sherlock.model.vo.UserVo;
import com.sherlock.shipment.domain.service.ShipmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/5 13:11
 */
@Service(version = "1.0.0")
public class ApiShipmentServiceImpl implements ApiShipmentService {

    @Autowired
    private ShipmentService shipmentService;

    @Override
    public List<Parcel> merge(String addrKey) {
        return shipmentService.merge(addrKey);
    }

    @Override
    public boolean split(Shipment shipment) {
        return shipmentService.split(shipment);
    }

    @Override
    public UserVo login(String userName, String password) {
        return null;
    }
}
