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

@RestController
@RequestMapping("/api/v1/store")
public class StoreService {
    @Autowired
    private StoreController storeCtl;


    @GetMapping("locate")
    public List<StoreDistance> getNearbyStores(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude,
                                               @RequestParam(value="number",defaultValue = "5") Integer no  ){

        return storeCtl.getClosestStores(latitude,longitude,no);
    }



}
