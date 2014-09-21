package com.plantplaces.dao;

import java.util.ArrayList;
import java.util.List;

import com.plantplaces.dto.Plant;

public interface IPlantDAO {

	/**
	 * Return a collection of plants that match a given search term.
	 * 
	 * @param searchTerm the term we're searching against.
	 * @return a collection of plants that match the search criteria
	 */
	public abstract ArrayList<Plant> fetchPlants(String searchTerm);

	List<Plant> fetchPlantsByLocation(double latitude, double longitude,
			double range) throws Exception;

}