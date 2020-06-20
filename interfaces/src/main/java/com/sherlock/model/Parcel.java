package com.sherlock.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/4 12:50
 */
@Data
public class Parcel implements Serializable {
    private Contact from;
    private Contact to;
    private int type;
    private int width;
    private int height;
    private long weight;
    private String description;
    private LocalDateTime deliveryTime;
    private LocalDateTime arrivalTime;
}
