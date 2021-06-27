package com.nbu.logistics.dto;

import com.nbu.logistics.entity.User;

public class ShipmentDto extends BaseDto {
    private String target;
    private String address;
    private double weight;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}