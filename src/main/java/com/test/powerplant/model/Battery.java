package com.test.powerplant.model;

import javax.persistence.*;

@Entity
@Table(name = "Battery")
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="battery_id")
    private Long id;

    @Column(name="battery_name")
    private String batteryName;

    @Column(name="post_code")
    private Long postCode;

    @Column(name="watt_capacity")
    private String wattCapacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatteryName() {
        return batteryName;
    }

    public void setBatteryName(String batteryName) {
        this.batteryName = batteryName;
    }

    public Long getPostCode() {
        return postCode;
    }

    public void setPostCode(Long postCode) {
        this.postCode = postCode;
    }

    public String getWattCapacity() {
        return wattCapacity;
    }

    public void setWattCapacity(String wattCapacity) {
        this.wattCapacity = wattCapacity;
    }

    public Battery(String batteryName, Long postCode, String wattCapacity){
        this.batteryName = batteryName;
        this.postCode = postCode;
        this.wattCapacity = wattCapacity;
    }
}
