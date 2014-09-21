package com.plantplaces.dao;

import java.util.ArrayList;
import java.util.List;

import com.plantplaces.dto.Plant;

/**
 * A stub class that returns predictable data for Plants
 * @author Brandan
 *
 */
public class PlantDAOStub implements IPlantDAO {

	
	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IPlantDAO#fetchPlants(java.lang.String)
	 */
	@Override
	public ArrayList<Plant> fetchPlants(String searchTerm) {
		ArrayList<Plant> allPlants = new ArrayList<Plant>();

		if (searchTerm.equalsIgnoreCase("redbud")) {
			// Create a new object from class plant.
			Plant redbud = new Plant();
			redbud.setCommon("Redbud");
			redbud.setGenus("Cercis");
			redbud.setSpecies("canadensis");
			allPlants.add(redbud);	
		}

		if (searchTerm.contains("pawpaw")) {
			Plant pawpaw = new Plant();
			pawpaw.setCommon("PawPaw");
			pawpaw.setGenus("Asimina");
			pawpaw.setSpecies("triloba");
			allPlants.add(pawpaw);
		}
		
		if (allPlants.size() == 0) {
			Plant empty = new Plant();
			empty.setCommon("No plants match your results.  Please try again.");
			empty.setGenus("");
			empty.setSpecies("");
			allPlants.add(empty);
		}
		
		return allPlants;
	}

	@Override
	public List<Plant> fetchPlantsByLocation(double latitude, double longitude,
			double range) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
