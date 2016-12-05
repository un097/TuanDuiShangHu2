package techown.shanghu;
/*
 * ���ݸ��䣩��ԼGPS
 */
import java.text.DecimalFormat;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.GPSLocation;
import techown.shanghu.date.DES;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class YouHuiShangXian_TeHui2_9 extends Activity{
	private Button button1,button3,button2;
	private TextView textView3,textView6,textView7;
	LinearLayout linear1,linear2;
	DES des = new DES();
	DBUtil DB=new DBUtil();
	//GPS
	double longitude = 0;
	double latitude = 0;
	boolean isGetGps = false;
	LocationManager locationManager = null;
	LocationListener llistener;
	String provider;
	Button getGpsBtn;
	String Dnum;
	
	private GPSLocation mGPS;
	long starttime,stoptime;
	EditDialog log=new EditDialog();
	//lxp
	private Dialog mDialog;
	Handler handler = new Handler(){   //
	       
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
	        	//Toast.makeText(YouHuiShangXian_TeHui2_9.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
	        	log.Toast(YouHuiShangXian_TeHui2_9.this, b.getString("throwsmessage"));
	        	Intent intent = new Intent();
	        	intent.setClass(YouHuiShangXian_TeHui2_9.this, ZhongBuHuoDong1.class);
	        	YouHuiShangXian_TeHui2_9.this.startActivity(intent);
	        	YouHuiShangXian_TeHui2_9.this.finish();
	        	break;
	        case 3:
//	        	DBUtil.upload(YouHuiShangXian_TeHui2.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(YouHuiShangXian_TeHui2_9.this, b.getString("throwsmessage"));
	        	// Toast.makeText(YouHuiShangXian_TeHui2_9.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
	        	 intent = new Intent();
	        	intent.setClass(YouHuiShangXian_TeHui2_9.this, ZhongBuHuoDong1.class);
	        	YouHuiShangXian_TeHui2_9.this.startActivity(intent);
	        	YouHuiShangXian_TeHui2_9.this.finish();	
	        	break;
	        case 4:
//	        	DBUtil.upload(YouHuiShangXian_TeHui2.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(YouHuiShangXian_TeHui2_9.this, b.getString("throwsmessage"));
	        	// Toast.makeText(YouHuiShangXian_TeHui2_9.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
		        intent = new Intent();
		        intent.setClass(YouHuiShangXian_TeHui2_9.this, ZhongBuChaXun4.class);
		        YouHuiShangXian_TeHui2_9.this.startActivity(intent);
		        YouHuiShangXian_TeHui2_9.this.finish();	
	        	break;	
	        case 5:
//	        	DBUtil.upload(YouHuiShangXian_TeHui2.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(YouHuiShangXian_TeHui2_9.this, b.getString("throwsmessage"));
	        	// Toast.makeText(YouHuiShangXian_TeHui2_9.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
		        intent = new Intent();
		        intent.setClass(YouHuiShangXian_TeHui2_9.this, ZhongBuHuoDong1.class);
		        YouHuiShangXian_TeHui2_9.this.startActivity(intent);
		        YouHuiShangXian_TeHui2_9.this.finish();	
	        	break;
	        	
	        case 6:// �ɹ���ȡ��γ��
//				mGPS.close();
//				textView6.setText(longitude + "");
//				textView7.setText(latitude + "");
//				mDialog.dismiss();
				
				hideWaitingDialog();
				removeMessages(0);
				mGPS.close();
				Location location = (Location) msg.obj;
				textView6.setText(location.getLongitude() + "");
				textView7.setText(location.getLatitude() + "");
				stoptime = System.currentTimeMillis();
				break;

			case 7:// ��ȡgpsʧ��
				textView6.setText(0 + "");
				textView7.setText(0 + "");
				mDialog.dismiss();
				if(locationManager!=null)
	        		locationManager.removeUpdates(llistener);
				log.Toast(YouHuiShangXian_TeHui2_9.this,"GPS��ȡʧ��");
				stoptime = System.currentTimeMillis();
				break;
			case 8:
				if(stoptime==0)
				{
					mGPS.close();
					hideWaitingDialog();
					log.Toast(YouHuiShangXian_TeHui2_9.this, "��ȡGPS��ʱ��");
					stoptime = 0;
				}
				break;
			case 9:// ��ȡgpsʧ��
				mGPS.close();
				hideWaitingDialog();
				techown.shanghu.https.Log.Instance().WriteLog("��ȡGPS�쳣ʧ��");
				break;
	        }   
	    }   
	};   
	//lxp
	
	
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youhuishangxian_tehui2);
        
        mDialog = new AlertDialog.Builder(YouHuiShangXian_TeHui2_9.this).create();//�����������
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button2.setVisibility(4);
        
        button3 = (Button) findViewById(R.id.button3);
        textView3=(TextView)findViewById(R.id.textView3);
        textView6=(TextView)findViewById(R.id.textView6);
        textView7=(TextView)findViewById(R.id.textView7);
        linear1=(LinearLayout)findViewById(R.id.linear1);
        linear2=(LinearLayout)findViewById(R.id.linear2);
        
        /**
         * 
         * ȡ���ݿ������
         * 
         * 
         */
        qushuju();
        
        MyLis lis = new MyLis();
        button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
        
//        linear1.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				log.etDialog(YouHuiShangXian_TeHui2.this, textView6, R.id.textView6);
//			}
//		});
//        linear2.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				log.etDialog(YouHuiShangXian_TeHui2.this, textView7, R.id.textView7);
//			}
//		});
//        
//        textView3.setText(YouHuiShangXian_TeHui.name);
        
    }
    class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch(id){
//				case R.id.button2:					
//					intent.setClass(YouHuiShangXian_TeHui2_2.this, YouHuiShangXian_TeHui.class);
//					YouHuiShangXian_TeHui2_2.this.startActivity(intent);
//					YouHuiShangXian_TeHui2_2.this.finish();
//				break;
				case R.id.button3:
					if(validate()){
						saveData();
						intent.setClass(YouHuiShangXian_TeHui2_9.this, YouHuiShangXian_TeHui4_9.class);
						YouHuiShangXian_TeHui2_9.this.startActivity(intent);
						YouHuiShangXian_TeHui2_9.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
//					}}.start();		
				break;
				case R.id.button1:
					
					showWaitingDialog();
					start();
					
//					Message msg2 = new Message();   
//			        msg2.what =1; 
//					handler.sendMessage(msg2);//
//					
//					//������ʱ����30���δ��ȡ����γ�ȣ���ʾ��ȡʧ��
//					Thread thread = new Thread(new GpsOverTime(System.currentTimeMillis()));
//					thread.start();
//					
//					if(openGPSSettings()){
//						getLocation();
//					}
					
					break;
			
			
			}
		}
    }
    //GPS
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
    				YouHuiShangXian_TeHui2_9.this.handler.sendEmptyMessage(7);
    				break;
    			}
	    		try{
	    			
	    			Thread.sleep(1000);
	    		}
	    		catch(Exception e){
	    			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui2_9(1000);"+ e.getMessage());
	    		}
    		}
    	}
    	
    }
    
    public void saveData() {
		String[] clomname = new String[] {"Jin","Wei"};
		String[] clomstr = new String[clomname.length];
		DecimalFormat d=new DecimalFormat("0.000000");
		if(!textView6.getText().toString().equals("")){
			clomstr[0] = "";
			clomstr[1] = "";
		}
		clomstr[0] = d.format(Double.parseDouble(textView6.getText().toString()));
		clomstr[1] = d.format(Double.parseDouble(textView7.getText().toString()));
		

		
		DB.upload3(YouHuiShangXian_TeHui2_9.this, clomname, clomstr, TuanDuiShangHuActivity.tmpId6);
	}
    
    
	 /**
	  * ���� �ݸ������Id �õ�  ��һҳ �������ݿ������
	  * 
	  *  �������ݿ��� �������� ���ڴ� ��ֻȡ ����
	  * 
	  
	  * longitude  latitude
	  * ����			γ��
	  */
	 private void qushuju() {

			MyDatabaseHelper myHelper = new MyDatabaseHelper( YouHuiShangXian_TeHui2_9.this, "techown.db", Constant.tdshDBVer);
			SQLiteDatabase db = null;
			Cursor cursor = null;
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu where id==?", new String[] { TuanDuiShangHuActivity.tmpId6 });
			while (cursor.moveToNext()) {
				try {
					
					textView6.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("Jin"))));
					textView7.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("Wei"))));
					textView3.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname"))));
					Dnum =des.jieMI(cursor.getString(cursor
							.getColumnIndex("Dnum"))) ;

				}catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui2_9:qushuju()" + e.getMessage());
				}finally{
					if(cursor!=null){
						cursor.close();
					}if(db!=null){
						db.close();
					}
				}
			}
		}
	
    
    
    
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(YouHuiShangXian_TeHui2_9.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	
    
    //��GPS,��ȡ��γ��
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
  		startActivityForResult(intent, 0); // ��Ϊ������ɺ󷵻ص���ȡ����
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
	
	private void getLocation()
	{

		// ��ȡλ�ù������
		if(locationManager == null){
			locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		}

		// ���ҵ�������Ϣ
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // �߾���
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW); // �͹���
		
		provider = locationManager.getBestProvider(criteria, true); // ��ȡGPS��Ϣ
//		Location loc = locationManager.getLastKnownLocation(provider);
//		if(loc != null){
//			longitude = loc.getLongitude();
//			latitude = loc.getLatitude();
//		}
		
		
		llistener = new LocationListener() {
			
			public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
				if(arg0 != null){
					longitude = arg0.getLongitude();
					latitude = arg0.getLatitude();
					isGetGps = true;
					
					YouHuiShangXian_TeHui2_9.this.handler.sendEmptyMessage(6);
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
		
		locationManager.requestLocationUpdates(provider, 100, (float) 100.0, llistener);

	}
	
	protected void hideWaitingDialog() {
		if(mDialog!=null&& mDialog.isShowing()){
			mDialog.dismiss();
		}
	}


	protected void start() {
		System.out.println("��λ����");
		mGPS = new GPSLocation(this, handler);
		mGPS.start();
//		handler.sendEmptyMessageDelayed(8, 5*60*1000);//5�������޷���ȷ��λ�˳���λ
		handler.sendEmptyMessageDelayed(8, 300000);
	}


	protected void showWaitingDialog() {
		Message msg2 = new Message();
		msg2.what = 1;
		handler.sendMessage(msg2);
	}
	
	protected boolean validate() {
		if(Dnum.equals("1")){
			
			if(textView6.getText().toString().equals("0")||textView7.getText().toString().equals("0"))
			{
				 log.Toast(YouHuiShangXian_TeHui2_9.this, "GPS�ɼ�����");
			}   
			else if(textView6.getText().toString().equals("")||textView7.getText().toString().equals(""))
			{
				 log.Toast(YouHuiShangXian_TeHui2_9.this, "��γ�Ȳ���Ϊ��");
			}
		}
		return true;
	}
}