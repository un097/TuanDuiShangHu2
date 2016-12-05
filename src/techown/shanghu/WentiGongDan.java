package techown.shanghu;
/*
 * ��������--���Ź���
 */
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.GPSLocation;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetDate;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WentiGongDan extends Activity{
private Button button1,button2;
public TextView textaa;

	public static String requeststr1;
	EditDialog log=new EditDialog();
	public List<String> data, price,numb,card;
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
	TextView text_longitude,text_latitude;
	LinearLayout g3leout4,g3leout5,g3leout6,g3leout7;
	LinearLayout gss_layoutAll;
	int[] textIds = { R.id.g3et1, R.id.g3et2, R.id.g3et3, R.id.g3et4,
			R.id.g3et5, R.id.g3et6, R.id.g3et7};
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.g3leout1,R.id.g3leout2,R.id.g3leout3,R.id.g3leout4,
			R.id.g3leout5,R.id.g3leout6,R.id.g3leout7,};
	public LinearLayout[] linlay;
	
	public static String path = "sdcard/"+"TJB/TuanDui/wentigongda/pic/";
	private GPSLocation mGPS;
	private Dialog mDialog;
	long starttime,stoptime;
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
	        	log.Toast(WentiGongDan.this, b.getString("throwsmessage"));
	        	//Toast.makeText(WentiGongDan.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
	        	Intent intent = new Intent();
	        	intent.setClass(WentiGongDan.this, GongDanFanKui1.class);
	        	WentiGongDan.this.startActivity(intent);
	        	WentiGongDan.this.finish();
	        	break;
	        case 3:
//	        	DBUtil.upload(YouHuiShangXian_TeHui2.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(WentiGongDan.this, b.getString("throwsmessage"));
	        	 intent = new Intent();
	        	intent.setClass(WentiGongDan.this, GongDanFanKui1.class);
	        	WentiGongDan.this.startActivity(intent);
	        	WentiGongDan.this.finish();	
	        	break;
	        case 4:
//	        	DBUtil.upload(YouHuiShangXian_TeHui2.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(WentiGongDan.this, b.getString("throwsmessage"));
		        intent = new Intent();
		        intent.setClass(WentiGongDan.this, GongDanFanKui1.class);
		        WentiGongDan.this.startActivity(intent);
		        WentiGongDan.this.finish();	
	        	break;	
	        case 5:
//	        	DBUtil.upload(YouHuiShangXian_TeHui2.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(WentiGongDan.this, b.getString("throwsmessage"));
		        intent = new Intent();
		        intent.setClass(WentiGongDan.this, GongDanFanKui1.class);
		        WentiGongDan.this.startActivity(intent);
		        WentiGongDan.this.finish();	
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
				text_longitude.setText(location.getLongitude() + "");
				text_latitude.setText(location.getLatitude() + "");
				stoptime = System.currentTimeMillis();
				break;

			case 7:// ��ȡgpsʧ��
				text_longitude.setText(0 + "");
				text_latitude.setText(0 + "");
				mDialog.dismiss();
				if(locationManager!=null)
	        		locationManager.removeUpdates(llistener);
				log.Toast(WentiGongDan.this,"GPS��ȡʧ��");
				stoptime = System.currentTimeMillis();
				break;
			case 8:
				if(stoptime==0)
				{
					mGPS.close();
					hideWaitingDialog();
					log.Toast(WentiGongDan.this, "��ȡGPS��ʱ��");
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
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wentigongdan);
        price=new ArrayList<String>();
        data=new ArrayList<String>();
       numb=new ArrayList<String>();
       card=new ArrayList<String>();
//        Query();
//        DBUtil.insertContactId3(WentiGongDan.this,"Id",num);
        File dir = new File(path);
		dir.mkdirs();
        mDialog = new AlertDialog.Builder(WentiGongDan.this).create();
        getshuju();
//        initChView();
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        textaa=(TextView)findViewById(R.id.textaa);
        getGpsBtn = (Button)findViewById(R.id.getGpsBtn);
        text_longitude = (TextView)findViewById(R.id.longitude);
        text_latitude = (TextView)findViewById(R.id.latitude);
        
        MyLis lis = new MyLis();
        button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
        getGpsBtn.setOnClickListener(lis);
        
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		
		textaa.setText(GongDanFanKui1.textaa);
		
		
		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(WentiGongDan.this,linlay[0], textArray[0],
							R.id.g3et1,"�������̻���Ǣ��");
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
				case MotionEvent.ACTION_UP:
					log.etDialog(WentiGongDan.this,textArray[1],
							R.id.g3et2,"��������ϵ�绰");
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
				case MotionEvent.ACTION_UP:
					log.Dialog(WentiGongDan.this,linlay[2], textArray[2],
							R.id.g3et3,"�������Ǣ����");
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
				case MotionEvent.ACTION_UP:
					DateDialog.showDatePicker(WentiGongDan.this,textArray[3],
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
				case MotionEvent.ACTION_UP:
					log.etDialog(WentiGongDan.this, textArray[4],
							R.id.g3et5,"���������ѽ��");
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
				case MotionEvent.ACTION_UP:
					log.etDialog(WentiGongDan.this, textArray[5],
							R.id.g3et6,"��������Ȩ��");
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
				case MotionEvent.ACTION_UP:
					log.etDialog(WentiGongDan.this,textArray[6],
							R.id.g3et7,"�����뿨��");
					break;
				default:
					break;
				}

				return false;
			}
		});
		init();
		System.out.println(ss_layoutAll.getChildCount());
    }
    

    
    public void saveData() {
		String[] clomname = new String[] { "Name", "TelNumber",
				"Neirong",  "Xiaofeidate","Xiaofxs","Shouquma" ,
				"Kahao",  "Jin", "Wei"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[0].getText().toString();
		clomstr[1] = textArray[1].getText().toString();
		clomstr[2] = textArray[2].getText().toString();
		if(!textArray[3].getText().toString().equals("")){
			clomstr[3]=GetDate.dateChange1(textArray[3].getText().toString());
		}else{
			clomstr[3]="";
		}
		clomstr[4]=textArray[4].getText().toString();
		clomstr[5]=textArray[5].getText().toString();
		clomstr[6]=textArray[6].getText().toString();
		DecimalFormat d=new DecimalFormat("0.000000");
		if(!text_longitude.getText().toString().equals("")){
			clomstr[7] = d.format(Double.parseDouble(text_longitude.getText().toString()));
			clomstr[8] = d.format(Double.parseDouble(text_latitude.getText().toString()));
		}else{
			clomstr[7] = "";
			clomstr[8] = "";
		}
		for (int i = 0; i < clomstr.length; i++) {
			System.out.println("�������Ź�����"+clomstr[i]);
		}
		DB.upload3(WentiGongDan.this, clomname, clomstr, GongDanFanKui1.num);
	}
    
    String time=GetDate.getDate().toString();
    public void saveData1() {
    	
    	String[] clomname = new String[] {"DCL","LX","TIME"};
    	String[] clomstr = new String[10];
    	clomstr[0] = "31";
    	clomstr[1] = "���⹤��";
    	clomstr[2] = time;
    	
    	DB.upload3(WentiGongDan.this, clomname, clomstr, GongDanFanKui1.num);
    }
	
	
    class MyLis implements OnClickListener{

    	
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch(id){
				case R.id.button1:
					
					intent.setClass(WentiGongDan.this, GongDanFanKui1.class);
					WentiGongDan.this.startActivity(intent);
					WentiGongDan.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
				case R.id.button2:
					
					if(validate()){
						
				
					
					saveData1();
					saveData();
					String barcodeno=GongDanFanKui1.Id+GongDanFanKui1.num;
					DB.sendinsert(WentiGongDan.this, new String[] {
    			    			MyDatabaseHelper.SEND_BarcodeNo,
    							MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
    							new String[] { barcodeno, "1", "0" });
    				
    				new AlertDialog.Builder(WentiGongDan.this).setTitle("��ʾ").setMessage("�ύ�ɹ�")
    				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

    				
    					public void onClick(DialogInterface arg0, int arg1) {
    						// TODO Auto-generated method stub
    						Intent intent = new Intent();
    						intent.setClass(WentiGongDan.this, TuanDuiShangHuActivity.class);
    						WentiGongDan.this.startActivity(intent);
    						WentiGongDan.this.finish();
    					}
    				}).show();
    				
					}
				break;
				case R.id.getGpsBtn:
					/*Message msg2 = new Message();   
			        msg2.what =1; 
					handler.sendMessage(msg2);//
					
					//������ʱ����30���δ��ȡ����γ�ȣ���ʾ��ȡʧ��
					Thread thread = new Thread(new GpsOverTime(System.currentTimeMillis()));
					thread.start();
					
					if(openGPSSettings()){
						getLocation();
					}*/
					showWaitingDialog();
					start();
					break;
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
			cur = db.query("gongdanweihu",
					new String[] {"Id"}, null, null, null,
					null, null);

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
			Toast.makeText(this, "���ݿ����" + e.toString(), Toast.LENGTH_SHORT)
					.show();
			// Log.i("Logcat",e.toString());
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
			log.ExitApp(WentiGongDan.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
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
    				WentiGongDan.this.handler.sendEmptyMessage(7);
    				break;
    			}
	    		try{
	    			
	    			Thread.sleep(1000);
	    		}
	    		catch(Exception e){
	    			techown.shanghu.https.Log.Instance().WriteLog("WentiGongDan��GpsOverTime"+e.getMessage());
	    		}
    		}
    	}
    	
    }
    List<TextView> ls=new ArrayList<TextView>();
    List<TextView> ls1=new ArrayList<TextView>();
    List<TextView> ls2=new ArrayList<TextView>();
    List<TextView> ls3=new ArrayList<TextView>();
    String[] tempstr=new String[10];
    LinearLayout gl;
    LinearLayout ss_layoutAll;
    public void init()
    {
    	
    	 ss_layoutAll=(LinearLayout) findViewById(R.id.gss_layoutAll);
    	
    	try
		{
    		JSONObject demoJson1 = new JSONObject(requeststr1);
    		
      		if(demoJson1.getString("rspCode").equals("00"))
      		{
      			tempstr[0]=demoJson1.getString("contacter");
      			tempstr[1]=demoJson1.getString("contactTel");
      			tempstr[2]=demoJson1.getString("content");
      			tempstr[3]=demoJson1.getString("longitude");
      			tempstr[4]=demoJson1.getString("latitude");
      			tempstr[5]=demoJson1.getString("txnDate");//��������
      			tempstr[6]=demoJson1.getString("cardId");//���׿���
      			tempstr[7]=demoJson1.getString("auditCode");//��Ȩ����
      			tempstr[8]=demoJson1.getString("txnAt");//���׽��
      				
      			textArray[0].setText(tempstr[0]);
      			textArray[1].setText(tempstr[1]);
      			textArray[2].setText(tempstr[2]);
      			text_longitude.setText(tempstr[3]);
      			text_latitude.setText(tempstr[4]);
      				
      			if(tempstr[5]!=null){
	      				
	      			String[] arrg = tempstr[5].split("\\|");
	      			String[] arrg1 = tempstr[6].split("\\|");
	      			String[] arrg2 = tempstr[7].split("\\|");  
	      			String[] arrg3 = tempstr[8].split("\\|");
					textArray[3].setText(arrg[0]);
					textArray[6].setText(arrg1[0]);
					textArray[5].setText(arrg2[0]);
					textArray[4].setText(arrg3[0]);
				    for (int i = 1; i < arrg.length; i++) {
				    		
				    	final View view=LayoutInflater.from(WentiGongDan.this).inflate(
								R.layout.cim_layout_list1, null);
						view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 60));
							
						TextView tv1=(TextView)view.findViewById(R.id.z2edtext6);
						TextView tv2=(TextView)view.findViewById(R.id.z2edtext7);
						TextView tv3=(TextView)view.findViewById(R.id.z2edtext8);
						TextView tv4=(TextView)view.findViewById(R.id.z2edtext9);
						tv1.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
									
								DateDialog.showDatePicker(WentiGongDan.this,(TextView)v,
											R.id.z2edtext6);
							}
								
						});
						tv2.setOnClickListener(new OnClickListener() {

							public void onClick(View v) {
									
								log.etDialog(WentiGongDan.this,(TextView)v,
										R.id.z2edtext7,"���������ѽ��");
							}
								
						});
						tv3.setOnClickListener(new OnClickListener() {

							public void onClick(View v) {
									
								log.etDialog(WentiGongDan.this,(TextView)v,
										R.id.z2edtext8,"��������Ȩ��");
							}
								
						});
						tv4.setOnClickListener(new OnClickListener() {

							public void onClick(View v) {
									
								log.etDialog(WentiGongDan.this,(TextView)v,
										R.id.z2edtext9,"�����뿨��");
							}
								
						});
						ls.add(tv1);
						ls1.add(tv2);
						ls2.add(tv3);
						ls3.add(tv4);
						ls.get(i-1).setText(arrg[i]);
						ls1.get(i-1).setText(arrg1[i]);
						ls2.get(i-1).setText(arrg2[i]);
						ls3.get(i-1).setText(arrg3[i]);
						ss_layoutAll.addView(view);
				    }
      			}
      		}
  		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("WentiGongDan�ࡣ������"+e.getMessage());
		}	
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
		try{
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
					
					WentiGongDan.this.handler.sendEmptyMessage(6);
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
	
	}catch(Exception e){
	techown.shanghu.https.Log.Instance().WriteLog("WentiGongDan��getLocation()"+e.getMessage());
	}
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
		if (textArray[0].getText().toString().equals("")) {
			log.Toast(WentiGongDan.this, "�������̻���Ǣ�� ��");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			log.Toast(WentiGongDan.this,"��������ϵ�绰 ��");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			log.Toast(WentiGongDan.this, "�������Ǣ���� ��");
			return false;
		}
		if(Dnum.equals("1")){
			if (textArray[3].getText().toString().equals("")) {
				log.Toast(WentiGongDan.this, "�����뽻������ ��");
				return false;
			}
			if (textArray[4].getText().toString().equals("")) {
				log.Toast(WentiGongDan.this,"�����뽻�׽�� ��");
				return false;
			}
			if (textArray[5].getText().toString().equals("")) {
				log.Toast(WentiGongDan.this, "��������Ȩ�� ��");
				return false;
			}
			if (textArray[6].getText().toString().equals("")) {
				log.Toast(WentiGongDan.this,"�����뿨�� ��");
				return false;
			}
		}
		if(text_longitude.getText().toString().equals("0")||text_latitude.getText().toString().equals("0")){
			log.Toast(WentiGongDan.this, "GPS�ɼ�����");
			return false;
		}   
		if(text_longitude.getText().toString().equals("")||text_latitude.getText().toString().equals("")){
			log.Toast(WentiGongDan.this,"��γ�Ȳ���Ϊ��");
			return false;
		}
		save();
		return true;
	}
	
		public void save(){
			 try{
					for(int x=1;x<ss_layoutAll.getChildCount();x++){
						LinearLayout viewLayout=(LinearLayout) ss_layoutAll.getChildAt(x);
						 
						  TextView   yedtext5=(TextView) viewLayout.findViewById(R.id.z2edtext6);
						  TextView	 yedtext6=(TextView) viewLayout.findViewById(R.id.z2edtext7);
						  TextView	 yedtext7=(TextView) viewLayout.findViewById(R.id.z2edtext8);
						  TextView	 yedtext8=(TextView) viewLayout.findViewById(R.id.z2edtext9);
			
		
					
						  data.add(yedtext5.getText().toString()); 	
						  price.add(yedtext6.getText().toString());  
						 numb.add(yedtext7.getText().toString()); 	 
						card.add(yedtext8.getText().toString()); 
						}
			 }catch(Exception e){
					techown.shanghu.https.Log.Instance().WriteLog("WentiGongDan��save����������"+e.getMessage());
				}
		}
		String Dnum;
		public void getshuju() {
			SQLiteDatabase db=null;
			Cursor cursor=null;
			try
			{
				MyDatabaseHelper myHelper = new MyDatabaseHelper(
						WentiGongDan.this, "techown.db", Constant.tdshDBVer);
				db = myHelper.getWritableDatabase();
				cursor = db.rawQuery("select * from gongdanweihu where id==?",
						new String[] { GongDanFanKui1.num });
				while (cursor.moveToNext()) {
					try {
						// s =
						// cursor.getString(cursor.getColumnIndex("ContractNumber"));
						Dnum =des.jieMI(cursor.getString(cursor
								.getColumnIndex("Dnum"))) ;

					} catch (Exception e) {
						techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�TakePhotoActivity:"+e.getMessage());	
					}
					cursor.close();
					db.close();
				}
		
			}catch(Exception e)
			{
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�TakePhotoActivity:"+e.getMessage());		
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
