package com.hpe.sparrow.ootb.entity;

import com.hpe.sparrow.ootb.annotation.Entity;

/**
 * Created by gongy on 2017/1/24.
 */
@Entity
public class Location extends BaseEntity {
    private String city;
    private String address;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
