package com.plantplaces.plantplaces14ss;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.plantplaces.dao.IPlantDAO;
import com.plantplaces.dao.OnlinePlantDAO;
import com.plantplaces.dto.Plant;

public class MapOfPlants extends FragmentActivity implements OnConnectionFailedListener, ConnectionCallbacks, LocationListener {
	
	private LocationClient locationClient;
	private GoogleMap map;
	private LocationRequest request = LocationRequest.create().setInterval(5000).setFastestInterval(16).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	private static final double RANGE = 0.001; 
	private IPlantDAO plantDAO;
		
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		// associate the layout.
		setContentView(R.layout.mapofplants);
		
		setupMapIfRequired();
		
		// initialize the plantDAO.
		plantDAO = new OnlinePlantDAO();
	}

	private void setupMapIfRequired() {
		if (map == null) {
			// get access to the fragment that contains the map.
			MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
			map = mapFragment.getMap();
			
			// enable location on the map.
			if (map != null) {
				map.setMyLocationEnabled(true);
			}
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setupLocationClientIfRequired();
		locationClient.connect();
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationClient.disconnect();
	}

	private void setupLocationClientIfRequired() {
		if (locationClient == null) {
			// instantiate a LocationClient object and store it into our LocationClient variable.
			locationClient = new LocationClient(this, this, this);
		}
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		locationClient.requestLocationUpdates(request, this);
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// Our location has changed, so let's get latitude and longitude values.
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		
		// create an instance of the PlantSearchTask.
		PlantSearchTask pst = new PlantSearchTask();
		
		// start the PST thread.
		pst.execute(latitude, longitude, RANGE);
		
	}
	
	class PlantSearchTask extends AsyncTask<Double, Integer, List<Plant>> {

		/**
		 * onPostExecute runs in the main/UI thread, and thus, 
		 * has access to UI objects.
		 */
		@Override
		protected void onPostExecute(List<Plant> result) {
			for (Plant plant : result) {
				// create a LatLng object which represents a GPS position.
				LatLng position = new LatLng(plant.getLatitude(), plant.getLongitude());
				
				// add a marker to the map.
				map.addMarker(new MarkerOptions().position(position).title(plant.toString()).snippet(plant.getAddress()));
			}
		}
		
		/**
		 * doInBackground runs in a thread separate from the UI thread, 
		 * and thus, can perform network operations.
		 * We must invoke this by calling a method named execute().
		 */
		@Override
		protected List<Plant> doInBackground(Double... params) {
			// Conduct a network call that will return a list of plants that are near the 
			// latitude and longitude values passed in.
			List<Plant> plantsByLocation = new ArrayList<Plant>();
			try {
				plantsByLocation = plantDAO.fetchPlantsByLocation(params[0], params[1], params[2]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return plantsByLocation;
		}
		
	}

}
