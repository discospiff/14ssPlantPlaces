package com.plantplaces.plantplaces14ss;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MasterDetailsActivity extends FragmentActivity implements SearchFragment.CoordinateSearch {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		setContentView(R.layout.plantmasterdetail);

	}

	
	public void swapFragment(View v) {
		SearchFragment searchFragment = new SearchFragment();
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.remove(getSupportFragmentManager().findFragmentById(R.id.findBox)).commit();
		transaction.replace(R.id.findBox, searchFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}


	@Override
	public void searchClicked(String searchTerm) {
		// TODO Auto-generated method stub
		PlantListFragment plf = new PlantListFragment();
		plf.setSearchTerm(searchTerm);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.findBox, plf);
		transaction.addToBackStack(null);
		transaction.commit();
		
		
	}
}
