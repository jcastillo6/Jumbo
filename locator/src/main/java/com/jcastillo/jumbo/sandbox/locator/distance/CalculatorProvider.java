package com.jcastillo.jumbo.sandbox.locator.distance;


import org.springframework.stereotype.Service;

/**
 * https://andrew.hedges.name/experiments/haversine/
 * dlon = lon2 - lon1
 * dlat = lat2 - lat1
 * a = (sin(dlat/2))^2 + cos(lat1) * cos(lat2) * (sin(dlon/2))^2
 * c = 2 * atan2( sqrt(a), sqrt(1-a) )
 * d = R * c (where R is the radius of the Earth)
 * The values used for the radius of the Earth (3961 miles & 6373 km)
 */
@Service
public class CalculatorProvider implements Calculator {
    private final int RADIUS_OF_THE_EARTH=6373;


    @Override
    public double distanceCalculator(Double latOfPointA, Double latOfPointB, Double longOfPointA, Double longOfPointB) {

        var dlon = longOfPointA - longOfPointB;
        var dlat = latOfPointA - latOfPointB;
        var a = Math.pow(Math.sin(dlat/2),2)+Math.cos(latOfPointA)*Math.cos(latOfPointB)*Math.pow(Math.sin(dlon),2);
        var c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        var d =  RADIUS_OF_THE_EARTH * c;
        return d;
    }
}
