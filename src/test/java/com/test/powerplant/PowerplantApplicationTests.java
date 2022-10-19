package com.test.powerplant;

import com.test.powerplant.controller.BatteryController;
import com.test.powerplant.model.Battery;
import com.test.powerplant.model.BatteryRequestDetails;
import com.test.powerplant.service.BatteryService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PowerplantApplicationTests {

	@Test
	void contextLoads() {
	}

	@InjectMocks
	BatteryService batteryService;

	@Mock
	Battery battery;

	@BeforeAll
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void add_NewBattery_WithNullInput(){
		BatteryService batteryService = new BatteryService();
		List<Battery> batteries = new ArrayList<>();
		batteries.add(null);
		BatteryRequestDetails batteryRequestDetails = new BatteryRequestDetails();
		batteryRequestDetails.setBatteries(batteries);
		Assertions.assertThrows(NullPointerException.class, () ->batteryService.saveBatteryDetails(batteryRequestDetails.getBatteries().get(0)));
	}

	@Test
	void add_NewBatteries_ValidInput(){
		BatteryService batteryService = new BatteryService();
		List<Battery> batteries = new ArrayList<>();
		batteries.add(new Battery("Powersonic", 6204L, "100 Ah"));
		batteries.add(new Battery("Amptron", 6243L, "110 Ah"));
		batteries.add(new Battery("Senec", 6543L, "120 W"));
		batteries.add(new Battery("CEIL", 6788L, "110 W"));
		batteries.add(new Battery("SIOMAR", 6005L, "120 W"));

		BatteryRequestDetails batteryRequestDetails = new BatteryRequestDetails();
		batteryRequestDetails.setBatteries(batteries);
		Assertions.assertNotNull(batteryService.saveBatteryDetails(batteryRequestDetails.getBatteries().get(0)));
	}

	@Test
	void check_NullPostCode_Range(){
		BatteryService batteryService = new BatteryService();
		Assertions.assertThrows(NullPointerException.class, () ->batteryService.getBatteryDetails(null, null));
	}

	@Test
	void get_BatteryDetails_ByPostCode_Range(){
		BatteryService batteryService = new BatteryService();
		Assertions.assertNotNull(batteryService.getBatteryDetails(6123L, 6336L));
	}

}
