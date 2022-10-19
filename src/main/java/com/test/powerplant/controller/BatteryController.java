package com.test.powerplant.controller;

import com.test.powerplant.model.Battery;
import com.test.powerplant.model.BatteryRequestDetails;
import com.test.powerplant.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BatteryController {

    @Autowired
    BatteryService batteryService;

    @RequestMapping(value="/batteries", method= RequestMethod.POST)
    public BatteryRequestDetails addBatteryDetails(@RequestBody BatteryRequestDetails batteryRequestDetails) {
        validateBatteryRequest(batteryRequestDetails);
        BatteryRequestDetails batteryRequestDetailsResp = new BatteryRequestDetails();
        for(Battery battery : batteryRequestDetails.getBatteries()) {
            batteryRequestDetailsResp.getBatteries().add( batteryService.saveBatteryDetails(battery));
        }
        return batteryRequestDetailsResp;
    }

    @RequestMapping(value="/batteries/{postcodeStart}/{postcodeEnd}", method= RequestMethod.GET)
    public List<Battery> getBatteryByPostCode(@PathVariable Map<Long, Long> pathVariables) {
        validatePostCodeRange(pathVariables);
        Long postcodeStart = pathVariables.get("postcodeStart");
        Long postcodeEnd = pathVariables.get("postcodeEnd");
        List<Battery> batteries =  batteryService.getBatteryDetails(postcodeStart, postcodeEnd);
        return batteries;
    }

    /**
     * Validate request body for empty/null requests
     * @param batteryRequestDetails
     */
    private void validateBatteryRequest(BatteryRequestDetails batteryRequestDetails) {
        if(batteryRequestDetails == null){
            throw new NullPointerException();
        }
    }

    /**
     * Validate request body for pathevariable.
     * @param pathVariables
     */
    private void validatePostCodeRange(Map<Long, Long> pathVariables) {
        if(pathVariables == null){
            throw new NullPointerException();
        }
    }
}
