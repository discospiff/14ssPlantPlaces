package com.plantplaces.plantplaces14ss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

public class AdvancedSearchActivity extends Activity {
	
	private AutoCompleteTextView actPlantName;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// associate the layout with this activity.
		setContentView(R.layout.advancedsearch);
		
		// get access to the auto complete text where the user will enter a search term.
		actPlantName = (AutoCompleteTextView) findViewById(R.id.actPlantName);
	}

	
	/**
	 * This method will be called when Search Plants is selected.
	 * @param v
	 */
	public void searchForPlants(View v) {
		// create an explicit intent.
		Intent plantResultsIntent = new Intent(this, PlantResultsActivity.class);
		
		// get the data that the user entered into the search field.
		String searchPlantName = actPlantName.getText().toString();
		
		// pass that data to the next activity.
		plantResultsIntent.putExtra("SEARCH_PLANT_NAME", searchPlantName);
		
		// invoke the explicit intent.
		startActivity(plantResultsIntent);
	}
	
}
