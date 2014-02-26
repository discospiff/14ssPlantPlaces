package com.plantplaces.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
			
			// Pass the data into a JSON object.
			JSONObject jsonObject = new JSONObject(rawPlantData);
			
			// get the data associated with the array named plants.
			JSONArray plantsArray = jsonObject.getJSONArray("plants");
			
			// iterate over the collection of plants from JSON.
			for (int i = 0; i < plantsArray.length(); i++) {
				
				// translating data from JSON to Java.
				
				// create a Plant DTO object that will hold the value from JSON.
				Plant plant = new Plant();
				
				// get the current Plant JSON object.
				JSONObject jsonPlant = plantsArray.getJSONObject(i);
				
				// get the ID from JSON, save into Java.
				int guid = jsonPlant.getInt("id");
				plant.setGuid(guid);
				
				// get the genus from JSON, save into Java.
				String genus = jsonPlant.getString("genus");
				plant.setGenus(genus);
				
				// get the species from JSON, save into Java.
				plant.setSpecies(jsonPlant.getString("species"));
				
				// get the cultivar from JSON, save into Java.
				String cultivar = jsonPlant.getString("cultivar");
				plant.setCultivar(cultivar);
				
				// get the common name from JSON, save into Java.
				String common = jsonPlant.getString("common");
				plant.setCommon(common);
				
				// add our plant object to the collection of plants, named allPlants.
				allPlants.add(plant);
				
			}
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allPlants;
	}

}
