package com.plantplaces.plantplaces14ss;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.plantplaces.dto.Plant;

public class PlantResultsActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// retrieve any data that was passed in, and was associated with the name "SEARCH_PLANT_NAME"
		String searchTerm = getIntent().getStringExtra("SEARCH_PLANT_NAME");


		// create a collection to hold the plants.
		ArrayList<Plant> allPlants = new ArrayList<Plant>();

		if (searchTerm.equalsIgnoreCase("redbud")) {
			// create a new object from class Plant.
			Plant redbud = new Plant();
			redbud.setCommon("Redbud");
			redbud.setGenus("Cercis");
			redbud.setSpecies("canadensis");

			// add the redbud and the paw paw to this collection.
			allPlants.add(redbud);
		}

		if (searchTerm.contains("pawpaw")) {
			// create a paw paw.
			Plant pawpaw = new Plant();
			pawpaw.setCommon("PawPaw");
			pawpaw.setGenus("Asimina");
			pawpaw.setSpecies("triloba");
			allPlants.add(pawpaw);
		}
		
		// do we have an empty list?
		if (allPlants.size() == 0) {
			Plant empty = new Plant();
			empty.setCommon("No plants match your result.  Please try again");
			allPlants.add(empty);
		}
		
		// show this collection in our list.
		ArrayAdapter<Plant> plantAdapter = new ArrayAdapter<Plant>(this, android.R.layout.simple_list_item_1, allPlants);

		setListAdapter(plantAdapter);






	}

}
