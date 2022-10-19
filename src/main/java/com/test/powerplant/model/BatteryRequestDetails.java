package com.test.powerplant.model;

import java.util.ArrayList;
import java.util.List;

public class BatteryRequestDetails {

    public List<Battery> batteries = new ArrayList<>();

    public List<Battery> getBatteries() {
        return batteries;
    }

    public void setBatteries(List<Battery> batteries) {
        this.batteries = batteries;
    }
}
