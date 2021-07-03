package com.jcastillo.jumbo.sandbox.locator.controller;

import com.jcastillo.jumbo.sandbox.locator.distance.Calculator;
import com.jcastillo.jumbo.sandbox.locator.entity.Store;
import com.jcastillo.jumbo.sandbox.locator.model.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;


@Component
public class StoreController {

    private static final int PAGE_SIZE = 10;
    @Autowired
    private StoreRepository storeRep;
    @Autowired
    private Calculator calculator;


    public List<StoreDistance> getClosestStores(Double lat, Double lng, int numberOfStores){

        Pageable pageable = PageRequest.of(0,PAGE_SIZE);
        Page<Store> page = storeRep.findAll(pageable);
        var heap = new PriorityQueue<StoreDistance>((o1, o2) -> o1.getDistance().compareTo(o2.getDistance()));

        while(!page.isEmpty()){
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

        return heap.stream().limit(numberOfStores).collect(Collectors.toList());

    }





}
