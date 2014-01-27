package com.plantplaces.plantplaces14ss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class GPSAPlantActivity extends Activity {
	
	// declare a variable that will hold a reference to the EditText component on the screen.
	EditText description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps_plants);
		
		description = (EditText) findViewById(R.id.edtDescription);
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
		startActivity(searchIntent);
		
		
		
	}

}



