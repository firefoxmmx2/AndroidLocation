package org.ffmmx.example.android_location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class ProximityAlert extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		boolean isEnter = (Boolean) bundle
				.get(LocationManager.KEY_PROXIMITY_ENTERING);
		String result = "";
		if (isEnter) {
			result = "你已经进入设定范围了";
		} else
			result = "你已经离开设定范围了";

		Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
	}

}
