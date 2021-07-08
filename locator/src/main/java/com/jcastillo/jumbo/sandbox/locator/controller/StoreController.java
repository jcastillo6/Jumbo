package com.jcastillo.jumbo.sandbox.locator.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.jcastillo.jumbo.sandbox.locator.entity.Store;
import com.jcastillo.jumbo.sandbox.locator.hateaos.StoreModel;
import com.jcastillo.jumbo.sandbox.locator.hateaos.StoreModelAssembler;
import com.jcastillo.jumbo.sandbox.locator.service.StoreDistance;
import com.jcastillo.jumbo.sandbox.locator.service.StoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Store services
 * 
 * @author jorge castillo
 *
 */
@RestController
@RequestMapping("/api/v1/")
public class StoreController {
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(StoreController.class);
	private static final Double MIN_LATITUDE = -90.0;
	private static final Double MAX_LATITUDE = 90.0;
	private static final Double MIN_LONGITUDE = -180.0;
	private static final Double MAX_LONGITUDE = 180.0;
	private static final int DEFAULT_PAGE_NUMBER = 0;
	private static final int DEFAULT_PAGE_SIZE = 10;

	@Autowired
	private StoreService storeCtl;
	@Autowired
	private PagedResourcesAssembler<Store> pagedRsc;
	@Autowired
	private StoreModelAssembler stmAssb;

	@GetMapping(value = "/stores/nearbystores", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("Get the nearby stores for specific point")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of stores, with the distance between the point and the store"),
			@ApiResponse(responseCode = "404", description = "Not found stores"),
			@ApiResponse(responseCode = "400", description = "Bad request parameter, the service can return a message in the body with the "
					+ "following information: {errorCode: 2001, message: The latitude value is not valid},"
					+ "{errorCode 2002,message: The longitude value is not valid}") })
	public ResponseEntity<List<StoreDistance>> getNearbyStores(@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude, @RequestParam(value = "number", defaultValue = "5") Integer no)
			throws BadRequestException, NotFoundException {

		LOG.debug("getNearbyStores latitude= " + latitude + " longitude= " + longitude + " Number of records= " + no);
		if (latitude == null || latitude.compareTo(MIN_LATITUDE) <= 0 || latitude.compareTo(MAX_LATITUDE) >= 0) {
			throw new BadRequestException(ErrorCode.INVALID_LATITUDE);
		}
		if (longitude == null || longitude.compareTo(MIN_LONGITUDE) <= 0 || longitude.compareTo(MAX_LONGITUDE) >= 0) {
			throw new BadRequestException(ErrorCode.INVALID_LONGITUDE);
		}

		LOG.info("Valid parameters, executing the request to get closest stores");

		List<StoreDistance> strDistances = storeCtl.getClosestStores(latitude, longitude, no);
		if (strDistances == null || strDistances.isEmpty()) {
			throw new NotFoundException(ErrorCode.NOT_FOUND_STORE);
		}

		return new ResponseEntity<>(strDistances, HttpStatus.OK);
	}

	@GetMapping(value = "stores", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("Create a list of store in the app")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List of stores"),
			@ApiResponse(responseCode = "404", description = "Not found stores") })
	public ResponseEntity<PagedModel<StoreModel>> getStores(
			@PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageRequest)
			throws BadRequestException {

		LOG.info("Calling getStores");
		LOG.debug("Pageable page = " + pageRequest.getPageNumber() + " size = " + pageRequest.getPageNumber());
		Page<Store> page = storeCtl.getStores(pageRequest);
		if (page == null || page.getNumberOfElements() == 0) {
			throw new NotFoundException(ErrorCode.NOT_FOUND_STORE);
		}

		return new ResponseEntity<>(pagedRsc.toModel(page, stmAssb), HttpStatus.OK);
	}

	@PostMapping("/stores/createall")
	@ResponseBody
	@ApiOperation("Create a list of store in the app")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "return the list of the stores that was successfully created"),
			@ApiResponse(responseCode = "400", description = "Bad request parameter, the service can return a message in the body with the "
					+ "following information: {errorCode: 2003, message: Invalid stores records},"
					+ "{errorCode 2006,message: An error happen while trying to save the store data}") })
	public ResponseEntity<CollectionModel<StoreModel>> createAll(@RequestBody List<Store> stores)
			throws BadRequestException {
		LOG.info("Calling createAll");
		if (stores == null || stores.isEmpty()) {
			throw new BadRequestException(ErrorCode.INVALID_STORES);
		}
		LOG.debug("Stores send " + stores.size());

		List<Store> storesCreated = storeCtl.createAll(stores);
		if (storesCreated == null || storesCreated.isEmpty()) {
			throw new BadRequestException(ErrorCode.ERROR_SAVING_STORES);
		}

		return new ResponseEntity<>(stmAssb.toCollectionModel(storesCreated), HttpStatus.CREATED);

	}

	@GetMapping("stores/{id}")
	@ApiOperation("Get a specific store")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return the store"),
			@ApiResponse(responseCode = "400", description = "Bad request parameter, the service can return a message in the body with the "
					+ "following information: {errorCode: 2004, message: Invalid store Id}"),
			@ApiResponse(responseCode = "404", description = "Store not found") })
	public ResponseEntity<StoreModel> getStoreById(@PathVariable("id") long id) {
		LOG.info("Calling getStoreById");
		if (id < 0) {
			throw new BadRequestException(ErrorCode.INVALID_STORE_ID);
		}
		LOG.debug("id = " + id);

		Optional<Store> opStore = storeCtl.getStoreById(id);
		Store store = opStore.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_STORE));

		return new ResponseEntity<>(stmAssb.toModel(store), HttpStatus.OK);

	}

}
