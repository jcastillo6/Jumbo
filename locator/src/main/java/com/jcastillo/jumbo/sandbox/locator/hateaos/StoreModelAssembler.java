package com.jcastillo.jumbo.sandbox.locator.hateaos;


import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import com.jcastillo.jumbo.sandbox.locator.controller.StoreControllerImpl;
import com.jcastillo.jumbo.sandbox.locator.entity.Store;

@Component
public class StoreModelAssembler implements RepresentationModelAssembler<Store, StoreModel> {



	@Override
	public StoreModel toModel(Store entity) {
		StoreModel storeModel = new StoreModel();
		storeModel.add(linkTo(methodOn(StoreControllerImpl.class).getStoreById(entity.getId())).withSelfRel());
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



}
