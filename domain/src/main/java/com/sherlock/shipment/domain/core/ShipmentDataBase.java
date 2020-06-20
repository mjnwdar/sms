package com.sherlock.shipment.domain.core;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.sherlock.model.Parcel;
import com.sherlock.model.Shipment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/9 20:04
 */
public final class ShipmentDataBase {
    private ShipmentDataBase(){}

    private static final ConcurrentHashMap<String, List<Parcel>> DATABASE = new ConcurrentHashMap<>();

    public static boolean split(Shipment shipment) {
        if (CollectionUtils.isNotEmpty(DATABASE.keySet())) {
            splitShipment(shipment);
        } else {
            firstSplitShipment(shipment);
        }
        return true;
    }

    private static void splitShipment(Shipment shipment) {
        final Map<String, List<Parcel>> groupMap = groupByShipment(shipment);
        groupMap.forEach((key, value) -> {
            if (DATABASE.containsKey(key) && CollectionUtils.isNotEmpty(value)) {
                value.addAll(DATABASE.get(key));
            }
            DATABASE.put(key, value);
        });
    }

    private static void firstSplitShipment(Shipment shipment) {
        final Map<String, List<Parcel>> groupMap = groupByShipment(shipment);
        DATABASE.putAll(groupMap);
    }

    private static Map<String, List<Parcel>> groupByShipment(Shipment shipment) {
        final List<Parcel> parcels = shipment.getParcels();
        if (CollectionUtils.isNotEmpty(parcels)) {
            return parcels.stream().collect(Collectors.groupingBy(item -> item.getTo().toString()));
        }
        return new HashMap<>(0);
    }


    public static List<Parcel> merge(String addrKey) {
        return DATABASE.remove(addrKey);
    }

    public static int getDbSize() {
        return DATABASE.size();
    }
}
