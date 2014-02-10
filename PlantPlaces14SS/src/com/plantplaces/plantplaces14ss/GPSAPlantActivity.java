package com.plantplaces.plantplaces14ss;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.plantplaces.dto.Plant;

public class GPSAPlantActivity extends Activity implements LocationListener {
	
	private static final int CAMERA_RESULT = 5;
	// declare a variable that will hold a reference to the EditText component on the screen.
	EditText description;
	private TextView txtSelectedPlant;
	private Plant plant;
	private Bitmap plantImage;
	private ImageView imgPlant;
	private LocationManager locationManager;
	private double latitude;
	private double longitude;
	private TextView lblLatitudeValue;
	private TextView lblLongitudeValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps_plants);
		
		description = (EditText) findViewById(R.id.edtDescription);
		
		// the label that contains the description of the selected plant.
		txtSelectedPlant = (TextView) findViewById(R.id.txtSelectedPlant);
		
		// get a reference to the image view that will display a plant photo.
		imgPlant = (ImageView) findViewById(R.id.imgPlant);
		
		// Get the LocationManager as a system service.  Save it into a field.
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		
		lblLatitudeValue = (TextView) findViewById(R.id.lblLatitudeValue);
		lblLongitudeValue = (TextView) findViewById(R.id.lblLongitudeValue);
		
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

		if (resultCode == RESULT_OK) {
			// only do this work if we received a good result.
			if (requestCode == AdvancedSearchActivity.PLANT_RESULTS) {
				// change the label of the text view to be the plant that was passed in.

				// store the plant that the user selected as an attribute.
				plant = (Plant) data.getSerializableExtra(PlantResultsActivity.PLANT_RESULT);

				/// set this plant in the TextView on the UI.
				txtSelectedPlant.setText(plant.toString());
			} else if (requestCode == CAMERA_RESULT) {
				// we are here because we have received a result from the camera.
				plantImage = (Bitmap) data.getExtras().get("data");

				imgPlant.setImageBitmap(plantImage);
			}
		}
	}

	/**
	 * This method will be invoked when the Take Photo button is clicked.
	 * @param v
	 */
	public void onTakePhotoClicked(View v) {
		// use an implict intent to invoke a camera.
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		
		// start this inent, and anticipate a result.
		startActivityForResult(cameraIntent, CAMERA_RESULT);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// subscribe to location service.
		requestLocationUpdates();
	}
	
	private void requestLocationUpdates() {
		// TODO Auto-generated method stub
		if (locationManager != null) {
			// the variable locationManager has an object assigned to it if we have gotten inside this
			// if test.  We want to do a null check like this first, because we want to avoid a 
			// NullPointerException.

			// request location updates.
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, this);
		}
	}

	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// when this activity is no longer visible, we want to unsubscribe from GPS.
		removeLocationUpdates();
	}
	
	/**
	 * Unsubscribe from location services.
	 */
	private void removeLocationUpdates() {
		// TODO Auto-generated method stub
		if (locationManager != null) {
			locationManager.removeUpdates(this);
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	/**
	 * THis method will be invoked when the GPS service informs us that our location has changed.
	 * Anything that we want to do that should be updated when the GPS position of our phone as moved
	 * we must do in this method.
	 */
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		
		// update our user interface.
		lblLatitudeValue.setText("" + latitude);
		lblLongitudeValue.setText("" + longitude);
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}



