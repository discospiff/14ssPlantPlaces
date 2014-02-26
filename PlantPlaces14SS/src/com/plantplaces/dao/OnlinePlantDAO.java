package com.plantplaces.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.plantplaces.dto.Plant;

/**
 * Access Plant data in a raw type, and convert it to Plant objects.
 * @author Brandan
 *
 */
public class OnlinePlantDAO implements IPlantDAO {

	/**
	 * Given a search term, return a matching collection of plants.
	 */
	@Override
	public ArrayList<Plant> fetchPlants(String searchTerm) {
		// Declare our return type.
		ArrayList<Plant> allPlants = new ArrayList<Plant>();
		
		// request URI is the JSON service URI that will give us plant results.
		String requestUri = "http://www.plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=" + searchTerm;
		
		// Get a reference to the low level Network DAO.
		NetworkDAO networkDAO = new NetworkDAO();
		
		try {
			// make the request.
			String rawPlantData = networkDAO.request(requestUri);
			
			// TODO parse the raw data into a series of Plant objects.
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allPlants;
	}

}
