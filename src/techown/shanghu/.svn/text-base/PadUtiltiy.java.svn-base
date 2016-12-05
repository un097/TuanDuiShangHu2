package techown.shanghu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import com.hxsmart.imateinterface.extension.HxBarcode;



import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.hardware.Camera;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


public class PadUtiltiy {
	private String TAG = "PadUtiltiy";
	private Context mContext;
	private ContentResolver mResolver;
	private BluetoothAdapter mBluetoothAdapter = null;
	private WifiManager mWifiManager;
	private Camera mCamera, mCamera2;
	private String computerName;//pad品牌
	private HxBarcode hxBarcode;

	private static String JIAOHANG_CAMERA_DIR = "jiaohang_camera_dir";
	private static String JIAOHANG_CAMERA_HEIGHT = "jiaohang_camera_heigh";
	private static String JIAOHANG_CAMERA_WIDTH = "jiaohang_camera_width";
	private static String JIAOHANG_CAMERA_PATH = "jiaohang_camera_path";
	private static String JIAOHANG_SHUTDOWN_SYSTEM = "jiaohang_shutdown_system";
	
	private static String JIAOHANG_SET_SYSTEM_TIME = "jiaohang_set_system_time";
	private static String JIAOHANG_SET_SYSTEM_TIME_MILLIS = "jiaohang_set_system_time_millis";
	
	
	
	public PadUtiltiy(Context c) {
		super();
		mContext = c;
		mResolver = c.getContentResolver();
		computerName= android.os.Build.BRAND.toLowerCase()+android.os.Build.MODEL.toLowerCase();
		// some init
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			Log.e(TAG, "no bluetooth");
		}
		mWifiManager = (WifiManager) mContext
				.getSystemService(mContext.WIFI_SERVICE);
	}

	private void OpenWifi() {
		if (!mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(true);// 打开wifi
		}
	}

	private void CloseWife() {
		if (mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(false);// 关闭wifi
		}
	}

	// 软键盘屏蔽API
	public int SetSoftKey(int KeyCode, Boolean enabled) {
		// enabled = !enabled;
		if (KeyCode == 0) {
			Settings.System.putInt(mResolver, "disable_keycode_back",
					enabled ? 1 : 0);
			Settings.System.putInt(mResolver, "disable_keycode_home",
					enabled ? 1 : 0);
			Settings.System.putInt(mResolver, "disable_keycode_recent_button",
					enabled ? 1 : 0);
			Settings.System.putInt(mResolver,
					"disable_keycode_notification_button", enabled ? 1 : 0);
		} else if (KeyCode == 1) {
			Settings.System.putInt(mResolver, "disable_keycode_back",
					enabled ? 1 : 0);
		} else if (KeyCode == 2) {
			Settings.System.putInt(mResolver, "disable_keycode_home",
					enabled ? 1 : 0);
		} else if (KeyCode == 3) {
			Settings.System.putInt(mResolver, "disable_keycode_recent_button",
					enabled ? 1 : 0);
		} else if (KeyCode == 4) {
			Settings.System.putInt(mResolver,
					"disable_keycode_notification_button", enabled ? 1 : 0);
		} else {
			return -1;
		}
		return 0;
	}

	// 设置开机密码和待机时长
	public int SetIdleTime(int time, String pw) {
		long ltime = time * 1000;
		Settings.System.putLong(mResolver, Settings.System.SCREEN_OFF_TIMEOUT, ltime);
		
		Settings.System.putInt(mResolver, "jiaohang_change_password", 1);
		Settings.System.putString(mResolver, "jiaohang_password", pw);
		
		startJHRoot();
		return 0;
	}

	/**
	* pass the parameters to the system and start CameraApp
	* @param dir 0:back camera 1:front camera
	* @param w width
	* @param h height
	* @param path path of image that should be saved
	* @return 0:success -1:failed
	*/
	public int takeCamera(int dir, int width, int height, String path) {
		int result=-1;
		   if("lenovotab a10-80hc".equals(computerName))//联想
	   		{
			   PackageManager packageManager = mContext.getPackageManager();    
			   	  Intent intent=null;    
			   	   try {    
			   		Settings.System.putInt(mResolver, "jiaohang_camera_dir", dir);
					Settings.System.putInt(mResolver, "jiaohang_camera_heigh", height);
					Settings.System.putInt(mResolver, "jiaohang_camera_width", width);
					Settings.System.putString(mResolver, "jiaohang_camera_path", path);
			   	    intent =packageManager.getLaunchIntentForPackage("com.jh.camera");    
			   	  } catch (Exception e) {    
			   		  System.out.println("PadUtiltiy.startCaptureApp()方法异常"+e.getMessage());    
			   	  }   
			   	  if(intent!=null){
			   		  mContext.startActivity(intent);
			   		  result=0;
			   	  }else{
			   	       Toast.makeText(mContext, "fail:can't find JHCamera.apk", 0).show();
			   	       result=-1;
			   	  }
			}else if("huaweimediapad 10 link+".equals(computerName))//华为
			{
				PackageManager packageManager = mContext.getPackageManager();    
	            Settings.System.putInt(mResolver, "jiaohang_camera_width", width);                                      
	            Settings.System.putInt(mResolver, "jiaohang_camera_height", height);                                     
	            Settings.System.putInt(mResolver, "jiaohang_camera_dir", dir);                                           
	            Settings.System.putString(mResolver, "jiaohang_camera_path", path);    

	           //设置存储路径
	            Intent intent = null;
	            try {
	                   intent = packageManager.getLaunchIntentForPackage("com.jh.camera");
	            } catch (Exception e) {
	            	System.out.println("PadUtiltiy.startCaptureApp()方法异常"+e.getMessage());    
	            }
	            if (intent != null) {
	            	mContext.startActivity(intent);
	            } else {
	            	Toast.makeText(mContext, "fail:can't find JHCamera.apk", 0).show();
	            }
//				HxCamera camera = new HxCamera();
//				camera.takePhoto(mContext, 100, HxCamera.FILE_URI, 900 , 450 , 601);
			} else {
				Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
				i.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(path)));
				i.putExtra("cameraid", dir);// 0:后置1，前置
				i.putExtra("width", width);
				i.putExtra("height", height);
//				mContext.startActivity(i);
				((Activity)mContext).startActivityForResult(i, 701);
			}
		   return result;
	}

	// 恢复出厂设置
	public int Reset() {
		Settings.System.putInt(mResolver, "jiaohang_reset_system", 1);
		startJHRoot();
		return 0;
	}

	// 开关类
	public int Enable(int bt, int camera, int phone, int sms, int sd, int wifi,
			int sim, int gps, int usb, int password) {

		/* check and do */
		// bt
		if (bt == 1) {
			if (!mBluetoothAdapter.isEnabled()) {
				Intent enableIntent = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				mContext.startActivity(enableIntent);
			}
		} else {
			mBluetoothAdapter.disable();
		}

		try{
			// camera
			if (camera == 1) {
				if (mCamera != null) {
					mCamera.unlock();
					mCamera.release();
					mCamera = null;
				}
				if (mCamera2 != null) {
					mCamera2.unlock();
					mCamera2.release();
					mCamera2 = null;
				}
			} else {
				if(mCamera == null){
					mCamera = Camera.open(1);
					mCamera.lock();
				}
				if(mCamera2 == null){
					mCamera2 = Camera.open();
					mCamera2.lock();
				}
			}
		}
		catch(Exception e){
			Log.e(TAG, "" + e);
			return -1;
		}
		
		// sms
		if (sms == 1) {
			try {
				File file = new File("/mnt/sdcard/", ".jiaohang_conf");
				FileOutputStream fos;
				fos = new FileOutputStream(file);
				fos.write("disable_sms_function".getBytes());
				fos.close();
			} catch (Exception e) {
				Log.e("CYQ", "" + e);
			}
		} else {
			try {
				File file = new File("/mnt/sdcard/", ".jiaohang_conf");
				FileOutputStream fos;
				fos = new FileOutputStream(file);
				fos.write("enable_sms_function".getBytes());
				fos.close();
//				FileReader fr = new FileReader(file);
//				BufferedReader buf = new BufferedReader(fr);
//				String s = buf.readLine();
//				if (s.equals("enable_sms_function"))
//					Log.e("CYQ", "--->");
			} catch (Exception e) {
				Log.e("CYQ", "" + e);
			}
		}
		// sd
		if (sd == 1) {
			Log.d("CYQ", "enable sd");
			Settings.System.putInt(mResolver, "disable_sd_function", 0);
		} else {
			Log.d("CYQ", "disable sd");
			Settings.System.putInt(mResolver, "disable_sd_function", 1);
		}
		// wifi
		if (wifi == 1) {
			OpenWifi();
		} else {
			CloseWife();
		}
		// sim
		if (sim == 1) {
			Settings.System.putInt(mResolver, "disable_sim_function", 0);
		} else {
			Settings.System.putInt(mResolver, "disable_sim_function", 1);
		}
		// gps
		if (gps == 1) {
			Settings.System.putInt(mResolver, "disable_gps_function", 0);

		} else {
			Settings.System.putInt(mResolver, "disable_gps_function", 1);
		}
		// usb
		if (usb == 1) {
			Settings.System.putInt(mResolver, "disable_adb_function", 0);
			EnableUsb();
		} else {
			Settings.System.putInt(mResolver, "disable_adb_function", 1);
			DisableUsb();	
		}
		// password
		if (password == 1) {
			Settings.System.putInt(mResolver, "disable_password_function", 0);
			Log.d("CYQ", "--->disable_password_function 0");
		} else {
			Settings.System.putInt(mResolver, "disable_password_function", 1);
			Log.d("CYQ", "--->disable_password_function 1");
		}
		startJHRoot();
		return 0;
	}

	// WIFI设置
	public void SetWIFI(String SSID, String password) {
		WifiManager mWifiManager;
		WifiConfiguration wc;

		mWifiManager = (WifiManager) mContext.getSystemService(mContext.WIFI_SERVICE);
		wc = new WifiConfiguration();

		/* ---------------------WEP连接方式--------------------- */
		wc.SSID = '\"' + SSID + '\"';// SSID;//"\"shrek\"";
		wc.hiddenSSID = false;
		wc.status = WifiConfiguration.Status.ENABLED;
		// wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		// wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		// wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		// wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
		// wc.wepTxKeyIndex = 0;
		// wc.wepKeys[0] = "01234";
		int length = password.length();
		// String password = mPasswordView.getText().toString();
		// WEP-40, WEP-104, and 256-bit WEP (WEP-232?)
		if ((length == 10 || length == 26 || length == 58)
				&& password.matches("[0-9A-Fa-f]*")) {
			wc.wepKeys[0] = password;
		} else {
			wc.wepKeys[0] = '"' + password + '"';
		}

		int res = mWifiManager.addNetwork(wc);
		Log.e("CYQ", "add network" + res);
		boolean b = mWifiManager.enableNetwork(res, true);
		Log.e("CYQ", "enableNetwork" + b);
		/* ---------------------WEP连接方式END--------------------- */
	}

	//设置VPDN
	public void SetVPDN(String APN, String user, String password, int authtype) {
		Uri APN_TABLE_URI = Uri.parse("content://telephony/carriers");
		Uri PREFERRED_APN_URI = Uri
				.parse("content://telephony/carriers/preferapn");

		queryApnId(mContext, APN, APN_TABLE_URI);

		InsertAPN(APN, user, password, authtype, mContext, APN_TABLE_URI,
				PREFERRED_APN_URI);
	}

	private void InsertAPN(String apn, String user, String password,
			int authtype, Context context, Uri APN_TABLE_URI,
			Uri PREFERRED_APN_URI) {
		int id = -1;
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();

		values.put("name", apn);
		values.put("apn", apn);
		values.put("numeric", "46001");
		values.put("proxy", "");
		values.put("type", "default");
		values.put("mcc", "460");
		values.put("mnc", "03");
		values.put("port", "");
		values.put("mmsproxy", "");
		values.put("mmsport", "");
		values.put("user", user);
		values.put("server", "");
		values.put("password", password);
		values.put("mmsc", "");
		if (authtype == 0)
			values.put("authtype", "0");
		else if (authtype == 1)
			values.put("authtype", "1");
		else if (authtype == 2)
			values.put("authtype", "2");
		else if (authtype == 3)
			values.put("authtype", "3");

		Cursor c = null;
		try {
			Uri newRow = resolver.insert(APN_TABLE_URI, values);
			if (newRow != null) {
				c = resolver.query(newRow, null, null, null, null);
				int idindex = c.getColumnIndex("_id");
				c.moveToFirst();
				id = c.getShort(idindex);
			}
		} catch (SQLException e) {
		}

		if (c != null) {
			c.close();
		}
		SetNowAPN(context, id, PREFERRED_APN_URI);
	}

	private void SetNowAPN(final Context context, final int id,
			Uri PREFERRED_APN_URI) {
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put("apn_id", id);
		try {
			resolver.update(PREFERRED_APN_URI, values, null, null);
		} catch (SQLException e) {
		}

	}

	private void queryApnId(Context context, String apnname, Uri APN_TABLE_URI) {
		String projection[] = { "_id,apn,type,name" };
		Cursor cr = context.getContentResolver().query(APN_TABLE_URI,
				projection, null, null, null);
		while (cr != null && cr.moveToNext()) {
			String id = cr.getString(0);
			String name = cr.getString(3);
			if (name.equals(apnname)) {
				DeleteApn(context, id);
			}
		}
		if (cr != null) {
			cr.close();
		}
	}

	private void DeleteApn(Context context, String id) {
		try {
			context.getContentResolver().delete(
					Uri.parse("content://telephony/carriers"), "_id = " + id,
					null);
		} catch (Exception e) {
			Log.e("CYQ", "DeleteApn exception");
		}
	}

	//获得GPS经纬度
	public String GetGPS(){
		return null;
//		String s;
//		double latitude = 0.0,longitude =0.0;
//		 LocationManager  locationm = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
//		 Criteria criteria = new Criteria();
//		 criteria.setAccuracy(Criteria.ACCURACY_FINE);
//		 criteria.setAltitudeRequired(false);
//		 criteria.setBearingRequired(false);
//		 criteria.setCostAllowed(true);
//		 criteria.setPowerRequirement(Criteria.POWER_LOW);
//		 String provider = locationm.getBestProvider(criteria, true);
//	 
//		 Location location = locationm.getLastKnownLocation(provider);
//		 if(location != null){
//			 latitude = location.getLatitude(); //经度 
//			 longitude = location.getLongitude();//纬度 
//		 }
//
//		 Log.d("CYQ", "--gps:" + "" + latitude + "," + longitude);
//		 return "" + latitude + "," + longitude;
	}
	
	//获取二维码
	public String GetBarcode(){
		String barcode=null;
   		if("lenovotab a10-80hc".equals(computerName))//联想
    	{
   			barcode=Settings.System.getString(mResolver, "jiaohang_get_barcode");
    	}else if("huaweimediapad 10 link+".equals(computerName))//华为
    	{
    		
    	}	
        return barcode;
	}
	
	//enable usb
	private void EnableUsb(){
		try{
			File file = new File("/sys/class/usb_composite/mtp/enable");
	    	FileOutputStream fos = new FileOutputStream(file);
	    	fos.write("1".getBytes());
			fos.close();
		}
		catch (IOException e) {
			Log.e("CYQ", "" + e);
			e.printStackTrace();
		}
	}
	
	//disable usb
	private void DisableUsb(){
		try{
			File file = new File("/sys/class/usb_composite/mtp/enable");
	    	FileOutputStream fos = new FileOutputStream(file);
	    	fos.write("0".getBytes());
			fos.close();
		}
		catch (IOException e) {
			Log.e("CYQ", "" + e);
			e.printStackTrace();
		}
	}
	
	//启动JHRoot apk
	private void startJHRoot(){
		PackageManager packageManager = mContext.getPackageManager();    
		Intent intent=new Intent();    
		  try {    
		  intent =packageManager.getLaunchIntentForPackage("com.superos");    
		} catch (Exception e) {    
		Log.i(TAG, e.toString());    
		}    
		mContext.startActivity(intent);
	}
	
	//启动二维码 apk
	public  void startBarCode(){
		if("lenovotab a10-80hc".equals(computerName))//联想
    	{
			 PackageManager packageManager = mContext.getPackageManager();    
		  	 Intent intent=null;    
		  	  try {    
		  	    intent =packageManager.getLaunchIntentForPackage("com.lenovo.client.android");    
		  	  } catch (Exception e) {    
		  		  System.out.println("PadUtiltiy.startBarCode()方法异常"+e.getMessage());    
		  	  }    
		  	  if(intent!=null)
		  	  {
		  		mContext.startActivity(intent);
		  	  }else{
		  		  Toast.makeText(mContext, "fail:can't find Barcode.apk", 0).show();  
		  	  }
    	}else if("huaweimediapad 10 link+".equals(computerName))//华为
    	{
    		try {
        		if ( hxBarcode == null ){
        			hxBarcode = new HxBarcode();
        		}
        		hxBarcode.scan(mContext, 501);	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	}else {
    		Intent i = new Intent("cn.iandroid.codescan.ScanAndDecode");
			i.putExtra("charset", (String) null);
			((Activity)mContext).startActivityForResult(i, 801);
    	}
	}
	//start Capture
//	private void startCaptureApp() {
//		PackageManager packageManager = mContext.getPackageManager();    
//		Intent intent=new Intent();    
//		  try {    
//		  intent =packageManager.getLaunchIntentForPackage("com.jh.camera");    
//		} catch (Exception e) {    
//		Log.i(TAG, e.toString());
//		}    
//		mContext.startActivity(intent);
//	}

	/**
	 * return the path of the image that capture by camera
	 * @return path of image
	 */
	public String GetImagePath() {
		return Settings.System.getString(mResolver, JIAOHANG_CAMERA_PATH);
	}
	/**
	 * shutdown now
	 */
	public void ShutDownTablet()
	{
		Settings.System.putInt(mResolver, JIAOHANG_SHUTDOWN_SYSTEM, 1);
		startJHRoot();
	}
	
	/**
	 * set system time
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public void SetSystemTime(int year, int month, int day, int hour, int minute, int second){
		long when = 0;
		Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        when = c.getTimeInMillis();
        
		Settings.System.putInt(mResolver, JIAOHANG_SET_SYSTEM_TIME, 1);
		Settings.System.putLong(mResolver, JIAOHANG_SET_SYSTEM_TIME_MILLIS, when);
		Log.d(TAG, "--->when:" + when);
		startJHRoot(); 
	}
	
}

