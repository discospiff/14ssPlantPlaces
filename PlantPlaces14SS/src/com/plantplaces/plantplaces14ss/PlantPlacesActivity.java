package com.plantplaces.plantplaces14ss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class PlantPlacesActivity extends Activity {

	public PlantPlacesActivity() {
		super();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater mi = new MenuInflater(this);
		mi.inflate(R.menu.search_plants, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		
		if (item.getItemId() == R.id.mnuCombinedView) {
			Intent combinedView = new Intent(this, MasterDetailsActivity.class);
			startActivity(combinedView);
			
		}
		
		return true;
	}

	public void openMasterDetail(View v) {
		Intent combinedView = new Intent(this, MasterDetailsActivity.class);
		startActivity(combinedView);		
	}
	
}