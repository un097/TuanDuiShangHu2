package techown.shanghu;

/*
 * ��Լ����  GPS
 */
import java.text.DecimalFormat;

import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.GPSLocation;
import techown.shanghu.date.DES;
import techown.shanghu.https.HttpConnection2;
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

public class YouHuiShangXian_TeHui211 extends Activity {
	private Button button1, button3, button2;
	private TextView textView3, textView6, textView7;
	LinearLayout linear1 = null;
	LinearLayout linear2 = null;
	EditDialog log = new EditDialog();
	DBUtil DB = new DBUtil();
	// GPS
	double longitude = 0;
	double latitude = 0;
	boolean isGetGps = false;
	LocationManager locationManager = null;
	LocationListener llistener;
	String provider;
	Button getGpsBtn;
	private GPSLocation mGPS;
	long starttime,stoptime;
	DES des = new DES();
	// lxp
	private Dialog mDialog;
	Handler handler = new Handler() { //

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

			 case 6:// �ɹ���ȡ��γ��
//					mGPS.close();
//					textView6.setText(longitude + "");
//					textView7.setText(latitude + "");
//					mDialog.dismiss();
					
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
					log.Toast(YouHuiShangXian_TeHui211.this,"GPS��ȡʧ��");
					stoptime = System.currentTimeMillis();
					break;
				case 8:
					if(stoptime==0)
					{
						mGPS.close();
						hideWaitingDialog();
						log.Toast(YouHuiShangXian_TeHui211.this, "��ȡGPS��ʱ��");
						stoptime = 0;
					}
					break;
				case 9:// ��ȡgpsʧ��
					mGPS.close();
					hideWaitingDialog();
					techown.shanghu.https.Log.Instance().WriteLog("��ȡGPS�쳣ʧ��");
					break;
			default:
				break;
			}
		}
	};

	// lxp

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youhuishangxian_tehui2);

		mDialog = new AlertDialog.Builder(YouHuiShangXian_TeHui211.this)
				.create();// �����������
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView6 = (TextView) findViewById(R.id.textView6);
		textView7 = (TextView) findViewById(R.id.textView7);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);

		MyLis lis = new MyLis();
		button1.setOnClickListener(lis);
		button2.setOnClickListener(lis);
		button3.setOnClickListener(lis);
		getshuju();

		// ��ȡ����
		MyDatabaseHelper myHelper = new MyDatabaseHelper(
				YouHuiShangXian_TeHui211.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		db = myHelper.getWritableDatabase();
		cursor = db.rawQuery("select * from gongdanweihu where id==?",
				new String[] { GongDanWeiHu1.num });
		while (cursor.moveToNext()) {
			try {

				textView6.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Jin"))));
				textView7.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Wei"))));

			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"YouHuiShangXian_TeHui211:��ȡ����" + e.getMessage());
			} finally {
				if (cursor != null) {
					cursor.close();
				}
				if (db != null) {
					db.close();
				}
			}
		}

		textView3.setText(GongDanWeiHu1.merName);

	}

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch (id) {
			case R.id.button2:
				techown.shanghu.https.Log.Instance().WriteLog(
						"�ػ���Լ����������ذ�ť�ص�����ѡ�����");
				intent.setClass(YouHuiShangXian_TeHui211.this,
						GongDanWeiHu1.class);
				YouHuiShangXian_TeHui211.this.startActivity(intent);
				YouHuiShangXian_TeHui211.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
			case R.id.button3:
				techown.shanghu.https.Log.Instance().WriteLog(
						"��Լ���������һ��" + "���̻�ID��" + GongDanWeiHu1.name + "�ID"
								+ activeId);
				// textView6.setText("11");
				// textView6.setText("12");
				if (validate()) {
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);//
					new Thread() {
						public void run() {
							Intent intent = new Intent();

							try {
								Request();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								techown.shanghu.https.Log.Instance().WriteLog(
										"YouHuiShangXian_TeHui211:Request()"
												+ e.getMessage());
							}

							String[] str1 = new String[] { "", "" };
							str1[0] = GongDanWeiHu1.name;
							str1[1] = activeId;

							String json1 = "{" + "\"merId\":\"" + str1[0]
									+ "\"," + "\"actId\":\"" + str1[1] + "\"}";

							YouHuiShangXian_ZuiHong31.requeststr1 = HttpConnection2
									.request(json1, "A0010");

							String Jsonstr = "{\"merId\":\""
									+ GongDanWeiHu1.merId
									+ "\",\"imgType\":\"00\"}";

							YouHuiShangXian_ZuiHong31.jsont = HttpConnection2
									.HttpRequest(HttpConnection2.getHttpCon(
											TuanDuiShangHuActivity.indenty,
											"A0018"), Jsonstr);
							//

							mDialog.dismiss();
							saveData();
							intent.setClass(YouHuiShangXian_TeHui211.this,
									YouHuiShangXian_ZuiHong31.class);
							YouHuiShangXian_TeHui211.this.startActivity(intent);
							YouHuiShangXian_TeHui211.this.finish();
							overridePendingTransition(R.anim.leftin,
									R.anim.leftout);
						}
					}.start();

				}

				break;
			case R.id.button1:
				techown.shanghu.https.Log.Instance().WriteLog("����ɼ���γ�Ȱ�ť�ɼ���γ��");
				showWaitingDialog();
				start();
//				Message msg2 = new Message();
//				msg2.what = 1;
//				handler.sendMessage(msg2);//
//
//				// ������ʱ����30���δ��ȡ����γ�ȣ���ʾ��ȡʧ��
//				Thread thread = new Thread(new GpsOverTime(
//						System.currentTimeMillis()));
//				thread.start();
//
//				if (openGPSSettings()) {
//					getLocation();
//				}
				break;

			}
		}
	}

	String activeId;

	// ��ѯ����
	public void Request() throws Exception {
		// userId��ҵ�����ţ����taskType���������ͣ�taskStatus������״̬
		String Jsonstr = "{\"taskId\":\"" + GongDanWeiHu1.Id + "\"}";
		String jsont = HttpConnection2.request(Jsonstr, "A0017");
		try {
			JSONObject demoJson1 = new JSONObject(jsont);
			if (demoJson1.getString("rspCode").equals("00")) {

				String[] tempstr = new String[1];
				tempstr[0] = demoJson1.getString("activeId");// ����ID

				activeId = tempstr[0];
			}

			else {
				Toast.makeText(YouHuiShangXian_TeHui211.this,
						demoJson1.getString("rspInfo"), 3).show();
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"YouHuiShangXian_TeHui211:Request()" + e.getMessage());
		}

	}

	public void saveData() {
		String[] clomname = new String[] { "Jin", "Wei" };
		String[] clomstr = new String[clomname.length];
		DecimalFormat d = new DecimalFormat("0.000000");
		if (!textView6.getText().toString().equals("")) {
			clomstr[0] = d.format(Double.parseDouble(textView6.getText()
					.toString()));
			clomstr[1] = d.format(Double.parseDouble(textView7.getText()
					.toString()));

		} else {
			clomstr[0] = "";
			clomstr[1] = "";
		}
		// clomstr[0] = d.format(Double.parseDouble("123.12345678"));
		// clomstr[1] = d.format(Double.parseDouble("12.123232131"));

		DB.upload3(YouHuiShangXian_TeHui211.this, clomname, clomstr,
				GongDanWeiHu1.num);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(YouHuiShangXian_TeHui211.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	// GPS
	class GpsOverTime implements Runnable {
		long startTime;

		public GpsOverTime(long startTime) {
			this.startTime = startTime;
		}

		public void run() {
			// TODO Auto-generated method stub

			while (true) {

				long currTime = System.currentTimeMillis();
				if (currTime - startTime > 300000 && !isGetGps) {
					YouHuiShangXian_TeHui211.this.handler.sendEmptyMessage(7);
					break;
				}
				try {

					Thread.sleep(1000);
				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"YouHuiShangXian_TeHui211:1000()" + e.getMessage());
				}
			}
		}

	}

	// ��GPS,��ȡ��γ��
	private boolean openGPSSettings() {
		// String gpsProvider = android.location.LocationManager.GPS_PROVIDER;
		// boolean isGpsEnable =
		// Secure.isLocationProviderEnabled(getContentResolver(), gpsProvider);
		// if(!isGpsEnable){
		// Secure.setLocationProviderEnabled(getContentResolver(), gpsProvider,
		// true);
		// }

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
		if (requestCode == 0) {
			LocationManager alm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
				getLocation();
			}
		}
	}

	private void getLocation() {

		// ��ȡλ�ù������
		if (locationManager == null) {
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		}

		// ���ҵ�������Ϣ
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // �߾���
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW); // �͹���

		provider = locationManager.getBestProvider(criteria, true); // ��ȡGPS��Ϣ
		// Location loc = locationManager.getLastKnownLocation(provider);
		// if(loc != null){
		// longitude = loc.getLongitude();
		// latitude = loc.getLatitude();
		// }

		llistener = new LocationListener() {

			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null) {
					longitude = arg0.getLongitude();
					latitude = arg0.getLatitude();
					isGetGps = true;

					YouHuiShangXian_TeHui211.this.handler.sendEmptyMessage(6);
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

		locationManager.requestLocationUpdates(provider, 100, (float) 100.0,
				llistener);

	}

	String Dnum;

	public void getshuju() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					YouHuiShangXian_TeHui211.this, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu where id==?",
					new String[] { GongDanWeiHu1.num });
			while (cursor.moveToNext()) {
				try {
					// s =
					// cursor.getString(cursor.getColumnIndex("ContractNumber"));
					Dnum = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Dnum")));

				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"�Ŷ�TakePhotoActivity:" + e.getMessage());
				}
				cursor.close();
				db.close();
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"�Ŷ�TakePhotoActivity:" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	protected boolean validate() {
			if (textView6.getText().toString().equals("0")|| textView7.getText().toString().equals("0")) 
			{
				log.Toast(YouHuiShangXian_TeHui211.this, "GPS�ɼ�����");
				return false;
			}
			else if (textView6.getText().toString().equals("")|| textView7.getText().toString().equals("")) 
			{
				log.Toast(YouHuiShangXian_TeHui211.this, "��γ�Ȳ���Ϊ��");
				return false;
			}
			return true;
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
	
	
}