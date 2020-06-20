package com.sherlock.shipment.domain.service;

import com.sherlock.model.Parcel;
import com.sherlock.model.Shipment;
import java.util.List;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/9 19:59
 */
public interface ShipmentService {

    /**
     * 分拣合并
     * @param addrKey 货运地址
     * @return 分拣的包裹
     */
    List<Parcel> merge(String addrKey);

    /**
     * 将货物拆分
     * @param shipment 货运资源
     * @return 拆分的结果
     */
    boolean split(Shipment shipment);

}
