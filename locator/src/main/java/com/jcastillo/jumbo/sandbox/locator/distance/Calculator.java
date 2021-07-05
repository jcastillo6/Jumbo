package com.jcastillo.jumbo.sandbox.locator.distance;

/**
 * Functional interface to work with distances
 * @author jorge castillo
 *
 */
public interface Calculator {

	/**
	 * Return the distance in KM
	 * @param latOfPointA latitude point A
	 * @param latOfPointB latitude point B
	 * @param longOfPointA longitude point A
	 * @param longOfPointB longitude point b
	 * @return distance in km
	 */
    public double distanceCalculator(Double latOfPointA, Double latOfPointB, Double longOfPointA, Double longOfPointB);

}
