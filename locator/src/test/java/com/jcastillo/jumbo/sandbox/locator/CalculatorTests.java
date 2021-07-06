package com.jcastillo.jumbo.sandbox.locator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jcastillo.jumbo.sandbox.locator.service.CalculatorService;
import com.jcastillo.jumbo.sandbox.locator.service.CalculatorServiceException;

@SpringBootTest
@ActiveProfiles(profiles = "local")
public class CalculatorTests {
	@Autowired
	CalculatorService cal;
	
	@Test
	void distanceCalculator() {
		Double result = 0.0;
		try {
			result= cal.distanceCalculator(0.0, 0.0,0.0 , 0.0);
		} catch (CalculatorServiceException e) {
			e.printStackTrace();
		}
		
		assertTrue(result.compareTo(0.0)==0);
		
		
	}
	
	
	@Test
	void distanceCalculator2() {
		Double result =0.0;
		
		try {
			result= cal.distanceCalculator(100.0, 100.0,100.0 , 100.0);
		} catch (CalculatorServiceException e) {
			
			e.printStackTrace();
		}
		
		assertTrue(result.compareTo(0.0)==0);
		
		
	}
	
	@Test
	void distanceCalculator3() {
		var expectedResult=0.0071757867149520715;
		Double result=0.0;
		
		try {
			result= cal.distanceCalculator(-33.502541567105524, -33.50248788892037,-70.6546920050703, -70.65467054740016);
		} catch (CalculatorServiceException e) {
			
			e.printStackTrace();
		}
		
		assertTrue(result.compareTo(expectedResult)==0);
		
		
	}
	
	@Test()
	void distanceCalculator4() {
		var exceptionMsg = "";
		try {
			cal.distanceCalculator(null, -33.50248788892037,-70.6546920050703, -70.65467054740016);
		}catch (CalculatorServiceException e) {
			exceptionMsg=e.getMessage();

		}
		assertTrue(exceptionMsg.contains("Invalid parameters"));
		
	}
	

	
	


}
