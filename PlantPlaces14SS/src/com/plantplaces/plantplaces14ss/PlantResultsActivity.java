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
		
		// create a new object from class Plant.
		Plant redbud = new Plant();
		redbud.setCommon("Redbud");
		redbud.setGenus("Cercis");
		redbud.setSpecies("canadensis");
		
		// create a paw paw.
		Plant pawpaw = new Plant();
		pawpaw.setCommon("PawPaw");
		pawpaw.setGenus("Asimina");
		pawpaw.setSpecies("triloba");
		
		// now we have two palnts that we want to display on our results.
		
		// create a collection to hold the plants.
		ArrayList<Plant> allPlants = new ArrayList<Plant>();
		
		// add the redbud and the paw paw to this collection.
		allPlants.add(redbud);
		allPlants.add(pawpaw);
		
		// show this collection in our list.
		ArrayAdapter<Plant> plantAdapter = new ArrayAdapter<Plant>(this, android.R.layout.simple_list_item_1, allPlants);
		
		setListAdapter(plantAdapter);
		
		
		
		
		
		
	}

}
