package com.plantplaces.plantplaces14ss;

// import android.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

/**
 * A quick search fragment with one field.
 * @author jonesb
 *
 */
public class SearchFragment extends Fragment {

	CoordinateSearch coordinateSearch;
	private AutoCompleteTextView actSearchPlantName;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		super.onCreateView(inflater, container, savedInstanceState);
		
		
		
		View view = inflater.inflate(R.layout.plantsearchfragment, container, false);
		actSearchPlantName = (AutoCompleteTextView) view.findViewById(R.id.actSearchPlantName);
		Button btnFindPlant = (Button) view.findViewById(R.id.btnFindPlant);
		btnFindPlant.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String searchTerm = actSearchPlantName.getText().toString();
				coordinateSearch.searchClicked(searchTerm);
			}
			
		});
		return view;
	}
	
	@Override
	public void onAttach(Activity activity) {
		coordinateSearch = (CoordinateSearch) activity;
		
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	public void onClick(View v) {

	}
	
	interface CoordinateSearch {
		public void searchClicked(String searchTerm);
		
	}
	
}
