package com.plantplaces.plantplaces14ss;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.plantplaces.dto.Plant;

public class GPSAPlantActivity extends Activity {
	
	private static final int CAMERA_RESULT = 5;
	// declare a variable that will hold a reference to the EditText component on the screen.
	EditText description;
	private TextView txtSelectedPlant;
	private Plant plant;
	private Bitmap plantImage;
	private ImageView imgPlant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps_plants);
		
		description = (EditText) findViewById(R.id.edtDescription);
		
		// the label that contains the description of the selected plant.
		txtSelectedPlant = (TextView) findViewById(R.id.txtSelectedPlant);
		
		// get a reference to the image view that will display a plant photo.
		imgPlant = (ImageView) findViewById(R.id.imgPlant);
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
		} else if (requestCode == CAMERA_RESULT) {
			// we are here because we have received a result from the camera.
			plantImage = (Bitmap) data.getExtras().get("data");
			
			imgPlant.setImageBitmap(plantImage);
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
	
	
	
}



