package com.plantplaces.plantplaces14ss;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlantDetailsFragment extends Fragment {

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	//	super.onCreateView(inflater, container, savedInstanceState);
		
		return inflater.inflate(R.layout.plantdetailfragment, container, false);
	}
}
