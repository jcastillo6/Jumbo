package com.jcastillo.jumbo.sandbox.locator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Store definition
 * @author jorge castillo
 *
 */

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    @Column(name="postalcode")
    private String postalCode;
    private String street;
    private String street2;
    private String street3;
    @Column(unique = true,nullable = false,name="addressname")
    private String addressName;
    private Double longitude;
    private Double latitude;
    @Column(name="complexnumber")
    private Integer complexNumber;
    @Column(name="locationtype")
    private String locationType;
    @Column(name="collectionpoint")
    private Boolean collectionPoint;
    @Column(name="todayopen")
    private String todayOpen;
    @Column(name="todayclose")
    private String todayClose;
    @Column(name="sapstoreid")
    private long sapStoreID;



}
