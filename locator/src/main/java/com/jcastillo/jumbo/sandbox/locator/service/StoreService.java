package com.jcastillo.jumbo.sandbox.locator.service;

import com.jcastillo.jumbo.sandbox.locator.controller.StoreController;
import com.jcastillo.jumbo.sandbox.locator.controller.StoreDistance;
import com.jcastillo.jumbo.sandbox.locator.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Store services
 * @author jorge castillo
 *
 */
@RestController
@RequestMapping("/api/v1/store")
public class StoreService {
    private static final Double MIN_LATITUDE=-90.0;
    private static final Double MAX_LATITUDE=90.0;
    private static final Double MIN_LONGITUDE=-180.0;
    private static final Double MAX_LONGITUDE=180.0;
    
	
	@Autowired
    private StoreController storeCtl;
    


    @GetMapping("closest")
    public List<StoreDistance> getNearbyStores(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude,
                                               @RequestParam(value="number",defaultValue = "5") Integer no  ) throws BadRequestException{
    	if(latitude==null || latitude.compareTo(MIN_LATITUDE)<=0 || latitude.compareTo(MAX_LATITUDE)>=0) {
    		throw new BadRequestException("Invalid latitude value");
    	}
    	if(longitude==null || longitude.compareTo(MIN_LONGITUDE)<=0 || longitude.compareTo(MAX_LONGITUDE)>=0) {
    		throw new BadRequestException("Invalid longitude value");
    	}

        return storeCtl.getClosestStores(latitude,longitude,no);
    }

    @GetMapping("stores")
    public List<Store> getStores() throws BadRequestException{

        return storeCtl.getStores();
    }


}
