package com.jcastillo.jumbo.sandbox.locator.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

/**
 * Store definition
 * @author jorge castillo
 *
 */
@Data
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private LocalTime todayOpen;
    private long sapStoreID;
    private LocalTime todayClose;


}
