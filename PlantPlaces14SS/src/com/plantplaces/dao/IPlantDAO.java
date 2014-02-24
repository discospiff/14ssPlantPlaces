package com.plantplaces.dao;

import java.util.ArrayList;

import com.plantplaces.dto.Plant;

public interface IPlantDAO {

	/**
	 * Return a collection of plants that match a given search term.
	 * 
	 * @param searchTerm the term we're searching against.
	 * @return a collection of plants that match the search criteria
	 */
	public abstract ArrayList<Plant> fetchPlants(String searchTerm);

}