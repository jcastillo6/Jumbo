package com.jcastillo.jumbo.sandbox.locator.controller;

import com.jcastillo.jumbo.sandbox.locator.distance.Calculator;
import com.jcastillo.jumbo.sandbox.locator.entity.Store;
import com.jcastillo.jumbo.sandbox.locator.model.StoreRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * This class provides all the business logic and other functions related with the stores
 * @author jorge castillo
 *
 */
@Component
public class StoreController {
	 
    private static final int PAGE_SIZE = 10;
    @Autowired
    private StoreRepository storeRep;
    @Autowired
    private Calculator calculator;
    private final Logger LOG = org.slf4j.LoggerFactory.getLogger(StoreController.class);


    /**
     * Return the closest stores to a given point
     * @param lat latitude
     * @param lng longitude
     * @param numberOfStores qty results
     * @return StoreDistance List
     */
    public List<StoreDistance> getClosestStores(Double lat, Double lng, int numberOfStores){

    	LOG.debug("getClosestStores lat="+lat+" longitude= "+lng+" numberOfStores= "+numberOfStores);
        Pageable pageable = PageRequest.of(0,PAGE_SIZE);
        Page<Store> page = storeRep.findAll(pageable);
        var heap = new PriorityQueue<StoreDistance>((o1, o2) -> o1.getDistance().compareTo(o2.getDistance()));

        while(!page.isEmpty()){
        	LOG.debug("new page ");
            pageable = pageable.next();
            List<StoreDistance> distances=page.stream().map(x->{
                var storeDistance = new StoreDistance();
                storeDistance.setDistance(calculator.distanceCalculator(lat,x.getLatitude(),lng,x.getLongitude()));
                storeDistance.setStore(x);
                return storeDistance;
            }).collect(Collectors.toList());
            heap.addAll(distances);

            page = storeRep.findAll(pageable);


        }
        
        LOG.debug("Records found "+heap.size());

        return heap.stream().limit(numberOfStores).collect(Collectors.toList());

    }

    /**
     * Get all the stores
     * @return stores list
     */
    public List<Store> getStores(){
    	
        Pageable pageable = PageRequest.of(0,PAGE_SIZE);
        Page<Store> page = storeRep.findAll(pageable);
        var stores = new ArrayList<Store>();

        while(!page.isEmpty()){
            pageable = pageable.next();
            stores.addAll(page.toList());    

            page = storeRep.findAll(pageable);


        }

        LOG.debug("total stores "+stores.size());
        return stores;
    	
    	
    }




}
