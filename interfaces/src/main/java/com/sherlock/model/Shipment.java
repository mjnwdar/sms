package com.sherlock.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/5 12:41
 */
@Data
public class Shipment implements Serializable {
    private List<Parcel> parcels;
}
