package com.plantplaces.plantplaces14ss;

import java.util.ArrayList;

import com.plantplaces.dao.IPlantDAO;
import com.plantplaces.dao.OnlinePlantDAO;
import com.plantplaces.dto.Plant;

import android.support.v4.app.ListFragment;
import android.os.AsyncTask;
// import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class PlantListFragment extends ListFragment {
	
	private String searchTerm;
	
	public String getSearchTerm() {
		return searchTerm;
	}


	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = super.onCreateView(inflater, container, savedInstanceState);
		
		PlantSearchTask pst = new PlantSearchTask();
		pst.execute(searchTerm);
		
		return v;
	}


	class PlantSearchTask extends AsyncTask<String, Integer, ArrayList<Plant>> {
	
		
		/**
		 * The steps in this method will run in a separate (non-UI) thread.
		 */
		@Override
		protected ArrayList<Plant> doInBackground(String... searchTerms) {
			// we're only getting one String, so let's access that one string.
			String searchTerm = searchTerms[0];
			// make a variable that will hold our plant DAO.
			// IPlantDAO plantDAO = new PlantDAOStub();
			IPlantDAO plantDAO = new OnlinePlantDAO();
			
			// fetch the plants from the DAO.
			ArrayList<Plant> plants = plantDAO.fetchPlants(searchTerm);
						
			// return the matching plants.
			return plants;
					
		}

	
		
		/**
		 * This method will be called when doInBackground completes.
		 * The paramter result is populated from the return values of doInBackground.
		 * This method runs on the UI thread, and therefore can update UI components.
		 */
		@Override
		protected void onPostExecute(ArrayList<Plant> allPlants) {
			// adapt the search results returned from doInBackground so that they can be presented on the UI.
			ArrayAdapter<Plant> plantAdapter = new ArrayAdapter<Plant>(getActivity(), android.R.layout.simple_list_item_1, allPlants);
			// show the search resuts in the list.
			setListAdapter(plantAdapter);
			
			// setProgressBarIndeterminateVisibility(false);
		}
		
		
		@Override
		protected void onPreExecute() {
//			setProgressBarIndeterminateVisibility(true);
		}
	}
	
}
