package com.plantplaces.plantplaces14ss;

// import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A quick search fragment with one field.
 * @author jonesb
 *
 */
public class SearchFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		super.onCreateView(inflater, container, savedInstanceState);
		
		return inflater.inflate(R.layout.plantsearchfragment, container, false);
	}
}
