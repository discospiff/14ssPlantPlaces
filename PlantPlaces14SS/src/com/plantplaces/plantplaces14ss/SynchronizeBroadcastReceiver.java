package com.plantplaces.plantplaces14ss;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SynchronizeBroadcastReceiver extends BroadcastReceiver {

	private boolean pluggedIn;
	private boolean wifi;

	@Override
	public void onReceive(Context context, Intent intent) {
		// ask the intent what it represents.
		if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
			pluggedIn = true;
		} else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
			pluggedIn = false;
		}
		
		// connectivity manager tells us if we are on wifi or not.
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		// are we connected?
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		
		// put this into an if test and toggle another boolean to tell us if we are on wifi or not.
		if (activeNetworkInfo != null && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			// assume we're connected on wifi.
			wifi = true;
			
		} else {
			// assume we're connected on the user's data plan.
			wifi = false;
		}
		
		// it's time to make some event based decisions.
		if (pluggedIn && wifi) {
			upload();
		}
		
		
		if (wifi) {
			download();
		}
		
	}

	private void download() {
		// TODO Auto-generated method stub
		
	}

	private void upload() {
		// TODO Auto-generated method stub
		
	}

}
