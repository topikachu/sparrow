package com.hpe.sparrow.ootb.entity;


import com.hpe.sparrow.ootb.annotation.Entity;

/**
 * Created by gongy on 2017/1/24.
 */
@Entity
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private Location address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }


}
