package org.ffmmx.example.android_location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView locationText;
	private Location loc;
	private LocationManager locMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		locationText = (TextView) this.findViewById(R.id.locationText);
		locMgr = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		loc = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 8,
				new LocationListener() {

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {

					}

					@Override
					public void onProviderEnabled(String provider) {

					}

					@Override
					public void onProviderDisabled(String provider) {

					}

					@Override
					public void onLocationChanged(Location location) {
						showInfo(location);
					}
				});
		double latitude = 333;
		double longitude = 111;
		float radius = 1000;
		Intent intent = new Intent("org.ffmmx.example.location");
		PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);
		locMgr.addProximityAlert(latitude, longitude, radius, -1, pi);
	}

	public void showInfo(Location loc) {
		if (loc != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("经度:" + loc.getLatitude());
			sb.append("\n");
			sb.append("纬度:" + loc.getLongitude());

			locationText.setText(sb.toString());
		} else {
			locationText.setText("未获取到坐标信息");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
