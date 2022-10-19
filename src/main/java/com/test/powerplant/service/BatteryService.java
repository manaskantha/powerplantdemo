package com.test.powerplant.service;

import com.test.powerplant.model.Battery;
import com.test.powerplant.repo.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatteryService {

    @Autowired
    BatteryRepository batteryRepository;


    // Save battery details to DB
    public Battery saveBatteryDetails(Battery battery) {
        validateBatteryInfo(battery);
        return batteryRepository.save(battery);
    }

    // Get battery details from DB
    public List<Battery> getBatteryDetails(Long postcodeStart, Long postcodeEnd) {
        validatePostcodes(postcodeStart, postcodeEnd);
        List<Battery> batteries =  batteryRepository.findAll();
        batteries = batteries.stream().filter(battery -> battery.getPostCode() < postcodeEnd && battery.getPostCode() > postcodeStart).collect(Collectors.toList());
        return batteries;
    }

    //Check if battery object is null, or it's name is null
    private void validateBatteryInfo(Battery battery) {
        if(battery == null || battery.getBatteryName() == null ||
                battery.getBatteryName().isEmpty()){
            throw new NullPointerException();
        }
    }

    //If postcode range is not present then throw NullPointerException
    private void validatePostcodes(Long postcodeStart, Long postcodeEnd) {
        if (postcodeStart == null || postcodeEnd == null) {
            throw new NullPointerException();
        }
    }

}
