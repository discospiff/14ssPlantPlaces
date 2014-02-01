package com.plantplaces.plantplaces14ss;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.plantplaces.dto.Plant;

public class AdvancedSearchActivity extends Activity {
	
	public static final int PLANT_RESULTS = 1;
	public static final String SEARCH_PLANT_NAME = "SEARCH_PLANT_NAME";
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
		plantResultsIntent.putExtra(SEARCH_PLANT_NAME, searchPlantName);
		
		// invoke the explicit intent.
		startActivityForResult(plantResultsIntent, PLANT_RESULTS);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		// are we getting data returned from the plantResultsIntent?  If so, this if test will '
		// evaluate to true, because we passed the PLANT_RESULT constant in when we invoked that intent.
		if (requestCode == PLANT_RESULTS) {
			// fetch the selected data using the constant that we've used as a key.
			Plant plant = (Plant) data.getSerializableExtra(PlantResultsActivity.PLANT_RESULT);
			
			// this toast will be invoked when we receive a result from plantResultsIntent
			// Toast.makeText(this, "Received Result: "  + plant, Toast.LENGTH_LONG).show();

			// put the plant in the intent which we are about to return.
			getIntent().putExtra(PlantResultsActivity.PLANT_RESULT, plant);
			
			// everything went fine.
			setResult(RESULT_OK, getIntent());
			
			// finish this intent.
			finish();
			
		}
	}
	
}
