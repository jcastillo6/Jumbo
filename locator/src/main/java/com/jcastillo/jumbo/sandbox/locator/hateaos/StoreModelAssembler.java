package com.jcastillo.jumbo.sandbox.locator.hateaos;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jcastillo.jumbo.sandbox.locator.controller.StoreController;
import com.jcastillo.jumbo.sandbox.locator.entity.Store;

@Component
public class StoreModelAssembler extends RepresentationModelAssemblerSupport<Store, StoreModel> {
	private static final Pageable DEFAULT_PAGEABLE = PageRequest.of(0, 10);
	



	public StoreModelAssembler() {
		super(StoreController.class, StoreModel.class);
	}


	@Override
	public StoreModel toModel(Store entity) {
		StoreModel storeModel = new StoreModel();
		storeModel.add(linkTo(methodOn(StoreController.class).getStoreById(entity.getId())).withSelfRel());
		storeModel.setId(entity.getId());
		storeModel.setAddressName(entity.getAddressName());
		storeModel.setCity(entity.getCity());
		storeModel.setPostalCode(entity.getPostalCode());
		storeModel.setStreet(entity.getStreet());
		storeModel.setStreet2(entity.getStreet2());
		storeModel.setStreet3(entity.getStreet3());
		storeModel.setTodayClose(entity.getTodayClose());
		storeModel.setTodayOpen(entity.getTodayOpen());
		storeModel.setLongitude(entity.getLongitude());
		storeModel.setLatitude(entity.getLatitude());

		return storeModel;
	}
	
    @Override
    public CollectionModel<StoreModel> toCollectionModel(Iterable<? extends Store> entries) {
    	CollectionModel<StoreModel> storeModels = super.toCollectionModel(entries);
    	storeModels.add(linkTo(methodOn(StoreController.class).getStores(DEFAULT_PAGEABLE)).withSelfRel());
    	return storeModels;
    	

    }



}
