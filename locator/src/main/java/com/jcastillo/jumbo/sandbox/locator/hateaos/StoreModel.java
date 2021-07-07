package com.jcastillo.jumbo.sandbox.locator.hateaos;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonRootName(value = "store")
@Relation(collectionRelation = "stores")
@JsonInclude(Include.NON_NULL)
public class StoreModel extends RepresentationModel<StoreModel>{
    private long id;
    private String city;
    private String postalCode;
    private String street;
    private String street2;
    private String street3;
    private String addressName;
    private Double longitude;
    private Double latitude;
    private Integer complexNumber;
    private String locationType;
    private Boolean collectionPoint;
    private String todayOpen;
    private String todayClose;
    private long sapStoreID;
	
	

}
