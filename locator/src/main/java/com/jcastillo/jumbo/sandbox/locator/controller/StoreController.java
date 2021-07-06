package com.jcastillo.jumbo.sandbox.locator.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
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
import com.jcastillo.jumbo.sandbox.locator.service.impl.DefaultStoreServiceImpl;

/**
 * Store services
 * @author jorge castillo
 *
 */
@RestController
@RequestMapping("/api/v1/")
public class StoreControllerImpl {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(StoreControllerImpl.class);
	private static final Double MIN_LATITUDE=-90.0;
    private static final Double MAX_LATITUDE=90.0;
    private static final Double MIN_LONGITUDE=-180.0;
    private static final Double MAX_LONGITUDE=180.0;
    private static final int DEFAULT_PAGE_NUMBER=0;
    private static final int DEFAULT_PAGE_SIZE=10;
	
	@Autowired
    private StoreService storeCtl;
	@Autowired
	private PagedResourcesAssembler<Store> pagedRsc;
	@Autowired
	private StoreModelAssembler stmAssb;
    
	
    @GetMapping(value="/stores/nearbystores",produces= {MediaType.APPLICATION_JSON_VALUE})
    public List<StoreDistance> getNearbyStores(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude,
                                               @RequestParam(value="number",defaultValue = "5") Integer no  ) throws BadRequestException{
    	LOG.debug("getNearbyStores latitude= "+latitude+" longitude= "+longitude+" Number of records= "+no);
    	if(latitude==null || latitude.compareTo(MIN_LATITUDE)<=0 || latitude.compareTo(MAX_LATITUDE)>=0) {
    		throw new BadRequestException(ErrorCode.INVALID_LATITUDE);
    	}
    	if(longitude==null || longitude.compareTo(MIN_LONGITUDE)<=0 || longitude.compareTo(MAX_LONGITUDE)>=0) {
    		throw new BadRequestException(ErrorCode.INVALID_LONGITUDE);
    	}
    	
    	LOG.info("Valid parameters, executing the request to get closest stores");

        return storeCtl.getClosestStores(latitude,longitude,no);
    }

    @GetMapping(value="stores",produces= {MediaType.APPLICATION_JSON_VALUE})
    public PagedModel<StoreModel> getStores(@PageableDefault(page = DEFAULT_PAGE_NUMBER,size = DEFAULT_PAGE_SIZE) Pageable pageRequest) throws BadRequestException{
    	
    	LOG.info("Calling getStores");
    	LOG.debug("Pageable page = "+pageRequest.getPageNumber()+" size = "+pageRequest.getPageNumber());
    	Page<Store> page = storeCtl.getStores(pageRequest);
   	
        return pagedRsc.toModel(page, stmAssb) ;
    }
    
   // @ResponseBody

    @PostMapping("/stores/createall")
    @ResponseBody
    public List<Store> createAll(@RequestBody List<Store> stores ) throws BadRequestException{
    	LOG.info("Calling createAll");
    	if(stores==null || stores.isEmpty()) {
    		throw new BadRequestException(ErrorCode.INVALID_STORES);
    	}
    	LOG.debug("Stores send "+stores.size());
    	
    	List<Store> storesCreated = storeCtl.createAll(stores);
    	
    	
    	
        return storesCreated;
    }
    
    
    @GetMapping("stores/{id}")
    public Store getStoreById(@PathVariable("id") long id) {
    	LOG.info("Calling getStoreById");
    	if(id<0) {
    		throw new BadRequestException(ErrorCode.INVALID_STORE_ID);
    	}
    	LOG.debug("id = "+id);
    	
    	Optional<Store> opStore= storeCtl.getStoreById(id);
    	
        return opStore.orElseThrow(()->new BadRequestException(ErrorCode.NOT_FOUND_STORE));
    }
    


}
