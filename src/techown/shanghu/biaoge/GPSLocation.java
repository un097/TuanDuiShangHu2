/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 *
 * FileName锛欸etCity.java
 * Version锛?.0
 * Modify record锛?
 * NO      Date        Name         Content      
 * 1    2011/02/16  JFTT)FengJie   original version 
 */
package techown.shanghu.biaoge;

import android.content.Context;
import android.location.*;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;

public class GPSLocation implements LocationListener {

	private LocationManager locationManager;

	private Double latitude = 0.0;

	private Double longitude = 0.0;
	int getLocationTime = 0;

	private Handler mHandler;
    private long startTime = 0;
	public boolean Is_successed = false;

	public GPSLocation(Context context,Handler handler) {
		mHandler = handler;
		this.locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
	}

	public void start() {
		getLocation();
	}

	public boolean isSuccessed() {
		return Is_successed && (longitude != 0) && (latitude != 0.0);
	}

	private void getLocation() {
		System.out.println("准备");
		Log.e("start getLocation", "get location  start!!!!!!");
		try {
			startTime = System.currentTimeMillis();
			Location location = null;
			System.out.println("开始定位");
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 500, 0, this);
			
			Log.e("GPSLocation", "location=" + location);
		} catch (Exception e) {
			Log.e("getLocation", "catch exception!!!!!!");
			e.printStackTrace();
			locationManager.removeUpdates(this);
		} finally {
			if (longitude == null || latitude == null) {
				Log.e("GPSLocation", "location==null!!!!!");
			}
		}

	}

	public void onLocationChanged(Location location) {
		System.out.println("进入5次定位");
		if(getLocationTime<=4){
//			if(getLocationTime == 0){
//				msg.arg2 = (int) ((System.currentTimeMillis()-startTime)/1000);
//			}
			System.out.println("5次定位完成");
			if(getLocationTime == 4){
				Message msg = new Message();
				msg.obj = location;
//				msg.arg1 = getLocationTime;
				msg.what = 6;
				mHandler.sendMessage(msg);
			}
			getLocationTime++;
		}else{
			locationManager.removeUpdates(this);
			getLocationTime = 0;
			mHandler.sendEmptyMessage(9);
		}
	}

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void close() {
		if (locationManager != null) {
			locationManager.removeUpdates(this);
		}
	}
}