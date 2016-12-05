package techown.shanghu;
/*
 * 工单维护--上门工单具体信息界面（草稿箱）
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.GPSLocation;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.Encrypt;
import techown.shanghu.photo.GDTPhotoActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class GongDanWeiHu3_3 extends Activity {
	// private EditText edtext1,edtext2,edtext3,edtext4,edtext5;
	private Button button1, button2;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	DES des = new DES();
	String Dnum;
	// GPS
	double longitude = 0;
	double latitude = 0;
	boolean isGetGps = false;
	LocationManager locationManager = null;
	LocationListener llistener;
	String provider;
	Button getGpsBtn;
	TextView text_longitude, text_latitude;
	public List<String> data, price, numb, card;
	
	private GPSLocation mGPS;
	long starttime,stoptime;

	private LinearLayout gss_layoutAll;
	int[] textIds = { R.id.g3et1, R.id.g3et2, R.id.g3et3, R.id.g3et4,
			R.id.g3et5, R.id.g3et6, R.id.g3et7 };
	public static TextView[] textArray;

	int[] linlayIds = { R.id.g3leout1, R.id.g3leout2, R.id.g3leout3,
			R.id.g3leout4, R.id.g3leout5, R.id.g3leout6, R.id.g3leout7, };
	public LinearLayout[] linlay;
	private Dialog mDialog;
	public TextView wenxuan,baifang,tv_other;
	private RadioButton rb_common,rb_fix,rb_practise,rb_close,rb_other;
	private String[] inputControl = new String[11];
	private String batchNo = "";
	private Handler handler  = new Handler(){   //
	       
	    @Override   
	    public void handleMessage(Message msg) {   
	        // TODO Auto-generated method stub    
	        super.handleMessage(msg);   
	        switch (msg.what) {   
	        case 1:
	        	mDialog.show();
		    	mDialog.setContentView(R.layout.loading_process_dialog_anim);
		    	mDialog.setCanceledOnTouchOutside(false);
		    	mDialog.setCancelable(false);
		    	
	        	break;	 
	        case 2:
//	        	DBUtil.upload(YouHuiShangXian_TeHui2.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	
	        	Bundle b = msg.getData();
	        	log.Toast(GongDanWeiHu3_3.this,  b.getString("throwsmessage"));
	        	//Toast.makeText(GongDanWeiHu3_3.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
	        	Intent intent = new Intent();
	        	intent.setClass(GongDanWeiHu3_3.this, ZhongBuHuoDong1.class);
	        	GongDanWeiHu3_3.this.startActivity(intent);
	        	GongDanWeiHu3_3.this.finish();
	        	break;
	        case 3:
//	        	DBUtil.upload(YouHuiShangXian_TeHui2.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(GongDanWeiHu3_3.this,  b.getString("throwsmessage"));
	        	 intent = new Intent();
	        	intent.setClass(GongDanWeiHu3_3.this, ZhongBuHuoDong1.class);
	        	GongDanWeiHu3_3.this.startActivity(intent);
	        	GongDanWeiHu3_3.this.finish();	
	        	break;
	        case 4:
//	        	DBUtil.upload(YouHuiShangXian_TeHui2.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(GongDanWeiHu3_3.this,  b.getString("throwsmessage"));
		        intent = new Intent();
		        intent.setClass(GongDanWeiHu3_3.this, ZhongBuChaXun4.class);
		        GongDanWeiHu3_3.this.startActivity(intent);
		        GongDanWeiHu3_3.this.finish();	
	        	break;	
	        case 6:// 成功获取经纬度
//				mGPS.close();
//				textView6.setText(longitude + "");
//				textView7.setText(latitude + "");
//				mDialog.dismiss();
				
				hideWaitingDialog();
				removeMessages(0);
				mGPS.close();
				Location location = (Location) msg.obj;
				text_longitude.setText(location.getLongitude() + "");
				text_latitude.setText(location.getLatitude() + "");
				stoptime = System.currentTimeMillis();
				break;

			case 7:// 获取gps失败
				text_longitude.setText(0 + "");
				text_latitude.setText(0 + "");
				mDialog.dismiss();
				if(locationManager!=null)
	        		locationManager.removeUpdates(llistener);
				log.Toast(GongDanWeiHu3_3.this,"GPS获取失败");
				stoptime = System.currentTimeMillis();
				break;
			case 8:
				if(stoptime==0)
				{
					mGPS.close();
					hideWaitingDialog();
					log.Toast(GongDanWeiHu3_3.this, "获取GPS超时！");
					stoptime = 0;
				}
				break;
			case 9:// 获取gps失败
				mGPS.close();
				hideWaitingDialog();
				techown.shanghu.https.Log.Instance().WriteLog("获取GPS异常失败");
				break;
	        }
	    }
	};;
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gongdanweihu3);

		// Query();
		// DBUtil.insertContactId3(GongDanWeiHu3.this,"Id",num);
		mDialog = new AlertDialog.Builder(GongDanWeiHu3_3.this).create();//连接网络进程
		price = new ArrayList<String>();
		data = new ArrayList<String>();
		numb = new ArrayList<String>();
		card = new ArrayList<String>();
//		initChView();
		button1 = (Button) findViewById(R.id.button1);
		button1.setVisibility(4);
		button2 = (Button) findViewById(R.id.button2);
		
		tv_other = (TextView) findViewById(R.id.tv_other);
		rb_close = (RadioButton) findViewById(R.id.rb_close);
		rb_common = (RadioButton) findViewById(R.id.rb_common);
		rb_fix = (RadioButton) findViewById(R.id.rb_fix);
		rb_other = (RadioButton) findViewById(R.id.rb_other);
		rb_practise = (RadioButton) findViewById(R.id.rb_practise);
		
		getGpsBtn = (Button) findViewById(R.id.getGpsBtn);
		text_longitude = (TextView) findViewById(R.id.longitude);
		text_latitude = (TextView) findViewById(R.id.latitude);
		wenxuan = (TextView) findViewById(R.id.wenxuan);
		baifang = (TextView) findViewById(R.id.baifang);
		MyLis lis = new MyLis();
		button1.setOnClickListener(lis);
		button2.setOnClickListener(lis);
		getGpsBtn.setOnClickListener(lis);

		
		getshuju();
		queryControl();
		linlay = new LinearLayout[linlayIds.length];
		for (int i = 0; i < linlayIds.length; i++) {
			linlay[i] = (LinearLayout) findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		
		qushuju();

		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(GongDanWeiHu3_3.this, linlay[0],
							textArray[0], R.id.g3et1,"请输入商户接洽人");
					break;
				default:
					break;
				}

				return false;
			}
		});

		linlay[1].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(GongDanWeiHu3_3.this, textArray[1],
							R.id.g3et2,"请输入联系电话");
					break;
				default:
					break;
				}

				return false;
			}
		});

		linlay[2].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(GongDanWeiHu3_3.this, linlay[2],
							textArray[2], R.id.g3et3,"请输入接洽内容");
					break;
				default:
					break;
				}

				return false;
			}
		});

		linlay[3].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					DateDialog.showDatePicker(GongDanWeiHu3_3.this, textArray[3],
							R.id.g3et4);
					break;
				default:
					break;
				}

				return false;
			}
		});

		linlay[4].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(GongDanWeiHu3_3.this, textArray[4],
							R.id.g3et5,"请输入金额");
					break;
				default:
					break;
				}

				return false;
			}
		});

		linlay[5].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(GongDanWeiHu3_3.this, textArray[5],
							R.id.g3et6,"请输入授权号");
					break;
				default:
					break;
				}

				return false;
			}
		});

		linlay[6].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(GongDanWeiHu3_3.this, textArray[6],
							R.id.g3et7,"请输入卡号");
					break;
				default:
					break;
				}

				return false;
			}
		});
		wenxuan.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				case MotionEvent.ACTION_UP:
					log.dialog(GongDanWeiHu3_3.this, wenxuan, "文宣", new String[]{"是","否"});
					break;
					default:break;
				
				}
				return false;
			}
		});
		baifang.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				case MotionEvent.ACTION_UP:
					log.dialog(GongDanWeiHu3_3.this, baifang, "摆放", new String[]{"是","否"});
					break;
					default:break;
				
				}
				return false;
			}
		});


	}

	int i = 0;

	private void queryControl(){
		MyDatabaseHelper myDatabaseHelper = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		Encrypt des = Encrypt.getInstance(GongDanWeiHu3_3.this);
			try {
					Context friendContext = createPackageContext("techown.login", CONTEXT_IGNORE_SECURITY);
					myDatabaseHelper = new MyDatabaseHelper(friendContext, "techown_login.db", Constant.loginDBVer);
					db = myDatabaseHelper.getWritableDatabase();
					String sql = "select * from bsc_task_batchno where batchNo=?";
					cursor = db.rawQuery(sql, new String[]{des.jiaMi(batchNo)});
					while(cursor.moveToNext()){
						inputControl[0] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_1")));
						inputControl[1] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_2")));
						inputControl[2] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_3")));
						inputControl[3] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_4")));
						inputControl[4] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_5")));
						inputControl[5] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_6")));
						inputControl[6] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_7")));
						inputControl[7] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_8")));
						inputControl[8] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_9")));
						inputControl[9] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_10")));
						inputControl[10] = des.jieMI(cursor.getString(cursor.getColumnIndex("flag_11")));
				}
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("查询工单批次号跑批文件异常："+e.getMessage());
				e.printStackTrace();
			}finally{
				if(cursor != null){
					cursor.close();
					cursor = null;
				}
				if(db != null){
					db.close();
					db = null;
				}
			}
		
	}


	public void saveData() {
		String[] clomname = new String[] { "Name", "TelNumber", "Neirong",
				"Xiaofeidate", "Xiaofxs", "Shouquma", "Kahao",  "Jin", "Wei" ,"WenXuan","BaiFang","Wifi","OtherRemark"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[0].getText().toString();
		clomstr[1] = textArray[1].getText().toString();
		clomstr[2] = textArray[2].getText().toString();	
		clomstr[3] = textArray[3].getText().toString();
		clomstr[4] = textArray[4].getText().toString();
		clomstr[5] = textArray[5].getText().toString();
		clomstr[6] = textArray[6].getText().toString();
		
		DecimalFormat d=new DecimalFormat("0.000000");
		if(!text_longitude.getText().toString().equals("")){
			clomstr[7] =d.format(Double.parseDouble( text_longitude.getText().toString()));
			clomstr[8] =d.format(Double.parseDouble( text_latitude.getText().toString()));
		}else{
			clomstr[7] ="";
			clomstr[8] ="";
		}
		if(wenxuan.getText().toString().equals("是")){
			clomstr[9] = "Y";
		}else{
			clomstr[9] = "N";
		}
		if(baifang.getText().toString().equals("是")){
			clomstr[10] = "Y";
		}else{
			clomstr[10] = "N";
		}
		if(rb_common.isChecked()){
			clomstr[11] = "1";
		}else if(rb_fix.isChecked()){
			clomstr[11] = "2";
		}else if(rb_practise.isChecked()){
			clomstr[11] = "3";
		}else if(rb_close.isChecked()){
			clomstr[11] = "4";
		}else if(rb_other.isChecked()){
			clomstr[11] = "5";
		}
		clomstr[12] = tv_other.getText().toString();
//		clomstr[9] = wenxuan.getText().toString();
//		clomstr[10] = baifang.getText().toString();
		DB.upload3(GongDanWeiHu3_3.this, clomname, clomstr, TuanDuiShangHuActivity.tmpId5);
	}


	/**
	 * 从数据库中查询数据
	 */
	 
	 private void qushuju() {
		 
	 MyDatabaseHelper myHelper = new MyDatabaseHelper( GongDanWeiHu3_3.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from gongdanweihu where id==?",new String[] { TuanDuiShangHuActivity.tmpId5 });
		while (cursor.moveToNext()) {
			try {
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Name"))));
				textArray[1].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("TelNumber"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Neirong"))));
//				textArray[3].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("Xiaofeidate"))));
//				textArray[4].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("Kahao"))));
//				textArray[5].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("Xiaofxs"))));
//				textArray[6].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("Shouquma"))));
//				textArray[7].setText(des.jieMI(cursor.getString(cursor      
//						.getColumnIndex("Jin"))));
//				textArray[8].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("Wei"))));
//				
				
				text_longitude.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Jin"))));
				text_latitude.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Wei"))));
				
				textArray[3].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Xiaofeidate"))));
				
				textArray[4].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Xiaofxs"))));
				textArray[5].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Shouquma"))));
				textArray[6].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Kahao"))));
				if(des.jieMI(cursor.getString(cursor.getColumnIndex("BaiFang"))).equals("Y")){
					baifang.setText("是");
				}else{
					baifang.setText("否");
				}
				if(des.jieMI(cursor.getString(cursor.getColumnIndex("WenXuan"))).equals("Y")){
					wenxuan.setText("是");
				}else{
					wenxuan.setText("否");
				}
				
				String wifi = des.jieMI(cursor.getString(cursor.getColumnIndex("Wifi")));
				if("1".equals(wifi)){
					rb_common.setChecked(true);
				}else if("2".equals(wifi)){
					rb_fix.setChecked(true);
				}else if("3".equals(wifi)){
					rb_practise.setChecked(true);
				}else if("4".equals(wifi)){
					rb_close.setChecked(true);
				}else if("5".equals(wifi)){
					rb_other.setChecked(true);
				}
				tv_other.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("OtherRemark"))));
//				wenxuan.setText(des.jieMI(cursor.getString(cursor.getColumnIndex("WenXuan"))));
//				baifang.setText(des.jieMI(cursor.getString(cursor.getColumnIndex("BaiFang"))));
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu3_3类qushuju()"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}if(db != null){
					db.close();
				}
			}
			
		}
	
	 }
	
	
	
	
	

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch (id) {
//			case R.id.button1:
//
//				intent.setClass(GongDanWeiHu3_3.this, GongDanWeiHu1.class);
//				GongDanWeiHu3_3.this.startActivity(intent);
//				GongDanWeiHu3_3.this.finish();
//				break;
			case R.id.button2:
				if(validate()){
				saveData();
				intent.setClass(GongDanWeiHu3_3.this, GDTPhotoActivity.class);
				GongDanWeiHu3_3.this.startActivity(intent);
				GongDanWeiHu3_3.this.finish();
				overridePendingTransition(R.anim.leftin, R.anim.leftout);
				}
				break;
			case R.id.getGpsBtn:
				
				showWaitingDialog();
				start();
				
//				Message msg2 = new Message();   
//		        msg2.what =1; 
//				handler.sendMessage(msg2);//
//				
//				//启动计时器，30秒后未获取到经纬度，提示获取失败
//				Thread thread = new Thread(new GpsOverTime(System.currentTimeMillis()));
//				thread.start();
//				if(openGPSSettings()){
//					getLocation();
//				}
				
				break;
			}
		}
	}
	class GpsOverTime implements Runnable{
    	long startTime;
    	public GpsOverTime(long startTime){
    		this.startTime = startTime;
    	}
    	public void run() {
    		// TODO Auto-generated method stub

    		while(true){
    			
    			long currTime = System.currentTimeMillis();
    			if(currTime - startTime > 300000 && !isGetGps){
    				GongDanWeiHu3_3.this.handler.sendEmptyMessage(7);
    				break;
    			}
	    		try{
	    			
	    			Thread.sleep(1000);
	    		}
	    		catch(Exception e){
	    			techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu3_3类GpsOverTime"+e.getMessage());
	    		}
    		}
    	}
    	
    }
	public String num;

	public void Query() {
		SQLiteDatabase db = null;
		Cursor cur = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query("gongdanweihu", new String[] { "Id" }, null,
					null, null, null, null);

			// int idIndex = cur.getCount();
			// Log.i("Logcat",String.valueOf(idIndex));
			if (cur.getCount() == 0) {
				num = "1";

			} else {
				while (cur.moveToNext()) {
					if (cur.isLast()) {
						num = String
								.valueOf(Long.parseLong(cur.getString(0)) + 1);
					}
				}
			}
		} catch (Exception e) {
			Toast.makeText(this, "数据库错误：" + e.toString(), Toast.LENGTH_SHORT)
					.show();
			techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu3_3类Query()"+e.getMessage());
		}finally{
			if(cur != null){
				cur.close();
			}if(db != null){
				db.close();
			}
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(GongDanWeiHu3_3.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	//打开GPS,获取经纬度
  	private boolean openGPSSettings() {
//  		String gpsProvider = android.location.LocationManager.GPS_PROVIDER;
//  		boolean isGpsEnable = Secure.isLocationProviderEnabled(getContentResolver(), gpsProvider);
//  		if(!isGpsEnable){
//  			Secure.setLocationProviderEnabled(getContentResolver(), gpsProvider, true);
//  		}

  		LocationManager alm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
  		if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
  			return true;
  		}
  		Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
  		startActivityForResult(intent, 0); // 此为设置完成后返回到获取界面
  		return false;

  	}
  	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 0){
			LocationManager alm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
				getLocation();
			}
		}
	}

	private void getLocation() {

		// 获取位置管理服务
		if (locationManager == null) {
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		}

		// 查找到服务信息
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

		provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
//		Location loc = locationManager.getLastKnownLocation(provider);
//		if (loc != null) {
//			longitude = loc.getLongitude();
//			latitude = loc.getLatitude();
//		}

		llistener = new LocationListener() {

			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null) {
					longitude = arg0.getLongitude();
					latitude = arg0.getLatitude();
					

					isGetGps = true;
					GongDanWeiHu3_3.this.handler.sendEmptyMessage(6);
				}
				locationManager.removeUpdates(this);
			}

			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub

			}

			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub

			}

			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub

			}

		};

		locationManager.requestLocationUpdates(provider, 1000, (float) 1000.0,
				llistener);

	}

	protected void hideWaitingDialog() {
		if(mDialog!=null&& mDialog.isShowing()){
			mDialog.dismiss();
		}
	}


	protected void start() {
		System.out.println("定位设置");
		mGPS = new GPSLocation(this, handler);
		mGPS.start();
//		handler.sendEmptyMessageDelayed(8, 5*60*1000);//5分钟内无法精确定位退出定位
		handler.sendEmptyMessageDelayed(8, 300000);
	}


	protected void showWaitingDialog() {
		Message msg2 = new Message();
		msg2.what = 1;
		handler.sendMessage(msg2);
	}
	
	
	protected boolean validate() {
		if(null == inputControl[0]){
			if (textArray[0].getText().toString().equals("")) {
				
				log.Toast(GongDanWeiHu3_3.this, "请输入商户接洽人！");
				return false;
			}
			if (textArray[1].getText().toString().equals("")) {
				
				log.Toast(GongDanWeiHu3_3.this,"请输入联系电话！");
				return false;
			}
			if (textArray[2].getText().toString().equals("")) {
				
				log.Toast(GongDanWeiHu3_3.this, "请输入接洽内容！");
				return false;
			}
			if(text_longitude.getText().toString().equals("0")||text_latitude.getText().toString().equals("0")){
				log.Toast(GongDanWeiHu3_3.this, "GPS采集错误");
				return false;
			}   
			if(text_longitude.getText().toString().equals("")||text_latitude.getText().toString().equals("")){
				log.Toast(GongDanWeiHu3_3.this, "经纬度不能为空");
				return false;
			}
			//添加文宣是否到位，摆放是否正确 addByZhaojingxian
			if(wenxuan.getText().toString().equals("")||wenxuan.getText().toString().length()==0){
				log.Toast(GongDanWeiHu3_3.this, "请选择文宣是否到位");
				return false;
			}
			if(baifang.getText().toString().equals("")||baifang.getText().toString().length()==0){
				log.Toast(GongDanWeiHu3_3.this, "请选择摆放是否正确");
				return false;
			}
			//
			if(Dnum.equals("1")){
				if (textArray[3].getText().toString().equals("")) {
					
					log.Toast(GongDanWeiHu3_3.this,  "请输入消费日期 ！");
					return false;
				}
				if (textArray[4].getText().toString().equals("")) {
					
					log.Toast(GongDanWeiHu3_3.this,"请输入消费金额 ！");
					return false;
				}
				if (textArray[5].getText().toString().equals("")) {
					
							log.Toast(GongDanWeiHu3_3.this, "请输入授权号 ！");
					return false;
				}
				if (textArray[6].getText().toString().equals("")) {
								log.Toast(GongDanWeiHu3_3.this, "请输入卡号 ！");
					return false;
				}
			}
			if((!rb_close.isChecked() && !rb_common.isChecked() && !rb_fix.isChecked() && !rb_other.isChecked() && !rb_practise.isChecked()) && "1".equals(inputControl[8])){
				log.Toast(GongDanWeiHu3_3.this, "请选择WIFI检查 ！");
				return false;
			}
			if("".equals(tv_other.getText().toString()) && "1".equals(inputControl[10])){
				log.Toast(GongDanWeiHu3_3.this, "请输入其他说明 ！");
				return false;
			}
		}else{
			if (textArray[0].getText().toString().equals("") && "1".equals(inputControl[0])) {
				
				log.Toast(GongDanWeiHu3_3.this, "请输入商户接洽人！");
				return false;
			}
			if (textArray[1].getText().toString().equals("") && "1".equals(inputControl[1])) {
				
				log.Toast(GongDanWeiHu3_3.this,"请输入联系电话！");
				return false;
			}
			if (textArray[2].getText().toString().equals("") && "1".equals(inputControl[2])) {
				
				log.Toast(GongDanWeiHu3_3.this, "请输入接洽内容！");
				return false;
			}
			if(text_longitude.getText().toString().equals("0")||text_latitude.getText().toString().equals("0")){
				log.Toast(GongDanWeiHu3_3.this, "GPS采集错误");
				return false;
			}   
			if((text_longitude.getText().toString().equals("")||text_latitude.getText().toString().equals("")) && "1".equals(inputControl[9])){
				log.Toast(GongDanWeiHu3_3.this, "经纬度不能为空");
				return false;
			}
			//添加文宣是否到位，摆放是否正确 addByZhaojingxian
			if((wenxuan.getText().toString().equals("")||wenxuan.getText().toString().length()==0) && "1".equals(inputControl[5])){
				log.Toast(GongDanWeiHu3_3.this, "请选择文宣是否到位");
				return false;
			}
			if((baifang.getText().toString().equals("")||baifang.getText().toString().length()==0) && "1".equals(inputControl[6])){
				log.Toast(GongDanWeiHu3_3.this, "请选择摆放是否正确");
				return false;
			}
			//
			if(Dnum.equals("1") && "1".equals(inputControl[3])){
				if (textArray[3].getText().toString().equals("")) {
					
					log.Toast(GongDanWeiHu3_3.this,  "请输入消费日期 ！");
					return false;
				}
				if (textArray[4].getText().toString().equals("")) {
					
					log.Toast(GongDanWeiHu3_3.this,"请输入消费金额 ！");
					return false;
				}
				if (textArray[5].getText().toString().equals("")) {
					
							log.Toast(GongDanWeiHu3_3.this, "请输入授权号 ！");
					return false;
				}
				if (textArray[6].getText().toString().equals("")) {
								log.Toast(GongDanWeiHu3_3.this, "请输入卡号 ！");
					return false;
				}
			}
			if((!rb_close.isChecked() && !rb_common.isChecked() && !rb_fix.isChecked() && !rb_other.isChecked() && !rb_practise.isChecked()) && "1".equals(inputControl[8])){
				log.Toast(GongDanWeiHu3_3.this, "请选择WIFI检查 ！");
				return false;
			}
			if("".equals(tv_other.getText().toString()) && "1".equals(inputControl[10])){
				log.Toast(GongDanWeiHu3_3.this, "请输入其他说明 ！");
				return false;
			}
		}
		
		
//		if (!posFromSecond(1)) {
//			return false;
//		}
//		if (!posFromSecond(2)) {
//			return false;
//		}
//		if (!posFromSecond(3)) {
//			return false;
//		}
//		if (!posFromSecond(4)) {
//			return false;
//		}
//		save(1);
//		save(2);
//		save(3);
//		save(4);

		return true;
	}
	
	public void save(int k) {
		this.i = k;
		if (i == k) {
			for (int x = 0; x < gss_layoutAll.getChildCount(); x++) {
				LinearLayout viewLayout = (LinearLayout) gss_layoutAll
						.getChildAt(x);

				TextView yedtext5 = (TextView) viewLayout
						.findViewById(R.id.g3et4);
				TextView yedtext6 = (TextView) viewLayout
						.findViewById(R.id.g3et5);
				TextView yedtext7 = (TextView) viewLayout
						.findViewById(R.id.g3et6);
				TextView yedtext8 = (TextView) viewLayout
						.findViewById(R.id.g3et7);

				data.add(yedtext5.getText().toString());
				price.add(yedtext6.getText().toString());
				numb.add(yedtext7.getText().toString());
				card.add(yedtext8.getText().toString());
			}
		}
	}
	
	public void getshuju() {
		SQLiteDatabase db=null;
		Cursor cursor=null;
		try
		{
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					GongDanWeiHu3_3.this, "techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu where id==?",
					new String[] { TuanDuiShangHuActivity.tmpId5 });
			while (cursor.moveToNext()) {
				try {
					// s =
					// cursor.getString(cursor.getColumnIndex("ContractNumber"));
					Dnum =des.jieMI(cursor.getString(cursor
							.getColumnIndex("Dnum"))) ;
					batchNo = des.jieMI(cursor.getString(cursor
							.getColumnIndex("batchNo"))) ;
				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("团队TakePhotoActivity:"+e.getMessage());	
				}
				cursor.close();
				db.close();
			}
	
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队TakePhotoActivity:"+e.getMessage());		
		}
		finally{
			if(cursor!=null)
			{
				cursor.close();	
			}
			if(db!=null)
			{
				db.close();	
			}
		}
	}
}




