package com.plantplaces.plantplaces14ss;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.plantplaces.dto.Plant;

public class AdvancedSearchActivity extends Activity {

	private static final int IMAGE_GALLERY = 10;
	public static final int PLANT_RESULTS = 1;
	public static final String SEARCH_PLANT_NAME = "SEARCH_PLANT_NAME";
	private AutoCompleteTextView actPlantName;
	private Bitmap selectedImage;
	private ImageView imgPlantSearch;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// associate the layout with this activity.
		setContentView(R.layout.advancedsearch);

		// get access to the auto complete text where the user will enter a search term.
		actPlantName = (AutoCompleteTextView) findViewById(R.id.actPlantName);

		// get access to the image view on this layout.
		imgPlantSearch = (ImageView) findViewById(R.id.imgPlantSearch);
	}


	/**
	 * This method will be called when Search Plants is selected.
	 * @param v
	 */
	public void searchForPlants(View v) {
		// create an explicit intent.
		Intent plantResultsIntent = new Intent(this, PlantResultsActivity.class);

		// get the data that the user entered into the search field.
		String searchPlantName = actPlantName.getText().toString();

		// pass that data to the next activity.
		plantResultsIntent.putExtra(SEARCH_PLANT_NAME, searchPlantName);

		// invoke the explicit intent.
		startActivityForResult(plantResultsIntent, PLANT_RESULTS);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {

			// are we getting data returned from the plantResultsIntent?  If so, this if test will '
			// evaluate to true, because we passed the PLANT_RESULT constant in when we invoked that intent.
			if (requestCode == PLANT_RESULTS) {
				// fetch the selected data using the constant that we've used as a key.
				Plant plant = (Plant) data.getSerializableExtra(PlantResultsActivity.PLANT_RESULT);

				// this toast will be invoked when we receive a result from plantResultsIntent
				// Toast.makeText(this, "Received Result: "  + plant, Toast.LENGTH_LONG).show();

				// put the plant in the intent which we are about to return.
				getIntent().putExtra(PlantResultsActivity.PLANT_RESULT, plant);

				// everything went fine.
				setResult(RESULT_OK, getIntent());

				// finish this intent.
				finish();

			} else if (requestCode == IMAGE_GALLERY) {
				// if we are here, we got a response from the image gallery.

				// find the path of the selected image.
				Uri photoLocation = data.getData();

				// open this as a stream of data/bytes
				try {
					// a stream of data from the file.
					InputStream openInputStream = getContentResolver().openInputStream(photoLocation);
					// take a stream of data, and convert it to a Bitmap.
					selectedImage = BitmapFactory.decodeStream(openInputStream);

					// assign this image to our image view.
					imgPlantSearch.setImageBitmap(selectedImage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					// alert the user that something went wrong.
					Toast.makeText(this, getString(R.string.unable_to_open_image), Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	/**
	 * This method is invoked when the select a picture button is pressed.
	 * @param v
	 */
	public void onSelectAPictureClick(View v) {
		// we want to pick an image from a gallery, so specify the action pick.
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

		// specify where to find the image, using data.

		// give me the path (file system directory) where we store images.
		String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();

		// Convert to URI, because that's what our Gallery is expecting.
		Uri picturesDirectory = Uri.parse(path);

		// set the data and type on this intent, so we tell it where to look for files, and 
		// what file types we want.
		photoPickerIntent.setDataAndType(picturesDirectory, "image/*");

		// start the activity, and tell it we want a result.
		startActivityForResult(photoPickerIntent, IMAGE_GALLERY);
	}

}
