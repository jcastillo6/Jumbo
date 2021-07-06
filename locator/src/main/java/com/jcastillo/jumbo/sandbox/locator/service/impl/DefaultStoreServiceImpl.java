package com.jcastillo.jumbo.sandbox.locator.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jcastillo.jumbo.sandbox.locator.entity.Store;
import com.jcastillo.jumbo.sandbox.locator.model.StoreRepository;
import com.jcastillo.jumbo.sandbox.locator.service.CalculatorService;
import com.jcastillo.jumbo.sandbox.locator.service.CalculatorServiceException;
import com.jcastillo.jumbo.sandbox.locator.service.StoreDistance;
import com.jcastillo.jumbo.sandbox.locator.service.StoreService;

/**
 * This class provides all the business logic and other functions related with
 * the stores
 * 
 * @author jorge castillo
 *
 */
@Service
public class DefaultStoreServiceImpl implements StoreService {

	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(DefaultStoreServiceImpl.class);

	private static final int PAGE_SIZE = 10;
	@Autowired
	private StoreRepository storeRep;
	@Autowired
	private CalculatorService calculator;

	/**
	 * Return the closest stores to a given point
	 * 
	 * @param lat            latitude
	 * @param lng            longitude
	 * @param numberOfStores qty results
	 * @return StoreDistance List
	 */
	public List<StoreDistance> getClosestStores(Double lat, Double lng, int numberOfStores) {
		if (lat == null)
			throw new IllegalArgumentException("Latitude is mandatoy");
		if (lng == null)
			throw new IllegalArgumentException("Longitud is mandatory");
		if (numberOfStores <= 0) {
			throw new IllegalArgumentException(
					"The number of store is a mandatory parameter and it must be greater that 0");
		}

		LOG.debug("getClosestStores lat=" + lat + " longitude= " + lng + " numberOfStores= " + numberOfStores);
		Pageable pageable = PageRequest.of(0, PAGE_SIZE);
		Page<Store> page = storeRep.findAll(pageable);
		// heap in reverse order

		List<StoreDistance> closerStores = new ArrayList<StoreDistance>();

		while (!page.isEmpty()) {
			LOG.debug("new page ");
			pageable = pageable.next();
			// calculate the distances for every store in the map

			List<StoreDistance> distances = page.stream().map(x -> {
				var storeDistance = new StoreDistance();
				try {
					storeDistance
							.setDistance(calculator.distanceCalculator(lat, x.getLatitude(), lng, x.getLongitude()));
				} catch (CalculatorServiceException e) {
					LOG.warn("Invalid distance calculation for the store " + x.getAddressName());
					// Setting a high value, this will avoid taken this store into account for the
					// result
					storeDistance.setDistance(Double.MAX_VALUE);

				}
				storeDistance.setStore(x);
				return storeDistance;
			}).collect(Collectors.toList());

			closerStores.addAll(distances);
			// Sort to put the closer stores first
			Collections.sort(closerStores, (o1, o2) -> o1.getDistance().compareTo(o2.getDistance()));
			// Resize the list to avoid memory problems
			if (!closerStores.isEmpty()) {
				LOG.info("Resizing the results from " + closerStores.size() + " to numberOfStores");
				closerStores = closerStores.subList(0,
						(closerStores.size() >= numberOfStores ? numberOfStores : closerStores.size()));
			} else {
				LOG.debug("Nothing to add, there is no more close stores");

			}

			page = storeRep.findAll(pageable);

		}

		LOG.info("Records found " + closerStores.size());

		return closerStores;

	}

	/**
	 * Get stores
	 * 
	 * @param numberOfStores
	 * @return List of stores
	 */
	public Page<Store> getStores(Pageable pageable) {
		if (pageable == null) {
			throw new IllegalArgumentException("Invalid value of pageable, it is mandatory");
		}

		Page<Store> page = storeRep.findAll(pageable);
		LOG.info("Pages found " + page.getTotalPages());

		return page;

	}

	/**
	 * Persist a list of stores
	 * @param stores list of stores to save
	 * @return the stores saved
	 */
	public List<Store> createAll(List<Store> stores) {
		if (stores == null) {
			throw new IllegalArgumentException("Stores is mandatory");

		}
		LOG.debug("Stores to save ="+stores.size());

		List<Store> result= new ArrayList<>();
		Iterable<Store> itStores= new ArrayList<>();
		try {
			itStores=storeRep.saveAll(stores);
		} catch (IllegalArgumentException e) {
			LOG.warn("Error while creating the stores", e);
		}
		
		itStores.forEach(result::add);
		LOG.debug("Stores saved ="+result.size());

		return result;
	}

	public Optional<Store> getStoreById(long id) {
		if(id<0) {
			throw new IllegalArgumentException("Invalid id");
		}
		LOG.debug("Id "+id );
		
		return storeRep.findById(id);
	}

}
