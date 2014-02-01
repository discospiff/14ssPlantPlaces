package com.plantplaces.plantplaces14ss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.plantplaces.dto.Plant;

public class GPSAPlantActivity extends Activity {
	
	// declare a variable that will hold a reference to the EditText component on the screen.
	EditText description;
	private TextView txtSelectedPlant;
	private Plant plant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps_plants);
		
		description = (EditText) findViewById(R.id.edtDescription);
		
		// the label that contains the description of the selected plant.
		txtSelectedPlant = (TextView) findViewById(R.id.txtSelectedPlant);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_plants, menu);
		return true;
	}

	public void searchClicked(View v) {
		// create an explicit intent.
		Intent searchIntent = new Intent(this, AdvancedSearchActivity.class);
		
		// start the activity
		startActivityForResult(searchIntent, AdvancedSearchActivity.PLANT_RESULTS);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == AdvancedSearchActivity.PLANT_RESULTS) {
			// change the label of the text view to be the plant that was passed in.
			
			// store the plant that the user selected as an attribute.
			plant = (Plant) data.getSerializableExtra(PlantResultsActivity.PLANT_RESULT);
		
			/// set this plant in the TextView on the UI.
			txtSelectedPlant.setText(plant.toString());
		}
	}

}



