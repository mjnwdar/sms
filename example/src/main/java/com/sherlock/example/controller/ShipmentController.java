package com.sherlock.example.controller;

import com.sherlock.example.core.rest.ResultEntity;
import com.sherlock.example.rpc.ShipmentRpcService;
import com.sherlock.model.Parcel;
import com.sherlock.model.Shipment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sherlock
 * @since 2020-06-02
 */
@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    @Autowired
    private ShipmentRpcService shipmentRpcService;

    @GetMapping("")
    public String index() {
        return "welcome to visit my website";
    }

    @PostMapping("/merge/{addrKey}")
    public ResultEntity<List<Parcel>> shipmentMerge(@PathVariable("addrKey") String addrKey) {
        final List<Parcel> parcels = shipmentRpcService.merge(addrKey);
        return ResultEntity.success(parcels);
    }

    @PostMapping("/split")
    public ResultEntity<Boolean> shipmentSplit(@RequestBody Shipment shipment) {
        boolean isDone = shipmentRpcService.split(shipment);
        return ResultEntity.success(isDone);
    }
}
