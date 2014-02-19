package com.plantplaces.plantplaces14ss;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.plantplaces.dto.Plant;

public class PlantResultsActivity extends ListActivity {
	public static final String PLANT_RESULT = "PLANT_RESULT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		String searchTerm = getIntent().getStringExtra(AdvancedSearchActivity.SEARCH_PLANT_NAME);

		// create an instance of our PlantSearchTask that will run in a separate thread.
		PlantSearchTask pst = new PlantSearchTask();
		// execute the PlantSearchTask thread.
		// this will start a new thread and invoke doInBackground in that new thread.
		// it will pass the search term into that new thread.
		pst.execute(searchTerm);
		

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Plant plant = (Plant) getListAdapter().getItem(position);
		getIntent().putExtra(PLANT_RESULT, plant);
		setResult(RESULT_OK, getIntent());
		finish();
	}
	
	class PlantSearchTask extends AsyncTask<String, Integer, ArrayList<Plant>> {
	
		
		/**
		 * The steps in this method will run in a separate (non-UI) thread.
		 */
		@Override
		protected ArrayList<Plant> doInBackground(String... searchTerms) {
			// we're only getting one String, so let's access that one string.
			String searchTerm = searchTerms[0];
			
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
		
		/**
		 * This method will be called when doInBackground completes.
		 * The paramter result is populated from the return values of doInBackground.
		 * This method runs on the UI thread, and therefore can update UI components.
		 */
		@Override
		protected void onPostExecute(ArrayList<Plant> allPlants) {
			// adapt the search results returned from doInBackground so that they can be presented on the UI.
			ArrayAdapter<Plant> plantAdapter = new ArrayAdapter<Plant>(PlantResultsActivity.this, android.R.layout.simple_list_item_1, allPlants);
			// show the search resuts in the list.
			setListAdapter(plantAdapter);
		}
		
	}
	
	
}
