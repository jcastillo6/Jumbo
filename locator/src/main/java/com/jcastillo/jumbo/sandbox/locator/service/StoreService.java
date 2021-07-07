package com.jcastillo.jumbo.sandbox.locator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jcastillo.jumbo.sandbox.locator.entity.Store;

/**
 * Store services, this class define the default method to handle store request
 * @author jorge castillo
 *
 */
public interface StoreService {
	/**
	 * Get the closest stores
	 * @param lat latitude
	 * @param lng longitude
	 * @param numberOfStores to return
	 * @return list of store distance object
	 */
	List<StoreDistance> getClosestStores(Double lat, Double lng, int numberOfStores);
	/**
	 * Get Stores
	 * @param pageable
	 * @return page
	 */
	Page<Store> getStores(Pageable pageable);
	
	/**
	 * Get Store by id
	 * @param id
	 * @return Optional store
	 */
	Optional<Store> getStoreById(long id);
	
	/**
	 * Create all stores 
	 * @param stores list
	 * @return the stores created
	 */
	List<Store> createAll(List<Store> stores);

}
