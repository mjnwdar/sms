package com.sherlock.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/4 12:50
 */
@Data
public class Contact implements Serializable {
    private String name;
    private String phoneNo;
    private String country;
    private String province;
    private String city;
    private String street;
    private String zip;
    private String address;

    @Override
    public String toString(){
        return new StringBuffer(name).append("-").append(phoneNo).append("-").append(zip).append("-")
            .append(country).append("-").append(province).append("-").append(city).append("-").append(street).toString();
    }
}
