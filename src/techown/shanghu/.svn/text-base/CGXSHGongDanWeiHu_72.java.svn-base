package techown.shanghu;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import techown.shanghu.biaoge.ActionCode;
import techown.shanghu.biaoge.AlgorithmConvert;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.GPSLocation;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetData;
import techown.shanghu.date.GetDate;
import techown.shanghu.date.RandomTest;
import techown.shanghu.https.PostService;
import techown.shanghu.photo.CaoYouHuiTakePhotoActivity2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 草稿箱 签出
 */
public class CGXSHGongDanWeiHu_72 extends Activity {

	DES des = new DES();
	EditDialog log = new EditDialog();
	DBUtil DB = new DBUtil();
	GetData getDasta = new GetData();
	private Button btn_back;
	private Button btn_gps;
	private Button btn_caogao;
	private Button btn_submit;
	private TextView tv_jing;
	private TextView tv_wei;
	private GPSLocation mGPS;
	private AlertDialog mDialog;
	private long stopTime;
	String gpsTime;
	AlertDialog alterdlg = null;

	Handler handler = new Handler() {

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
			case 6:// 成功获取经纬度

				hideWaitingDialog();
				removeMessages(0);
				mGPS.close();
				Location location = (Location) msg.obj;
				DecimalFormat df1= new DecimalFormat("##.######");
				double jing = location.getLongitude();
				double wei = location.getLatitude();
				tv_jing.setText(df1.format(jing) + "");
				tv_wei.setText(df1.format(wei) + "");
				
				stopTime = System.currentTimeMillis();
				Date data = new Date(stopTime);
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
				gpsTime = df.format(data);
				break;
			case 8:
				if (stopTime == 0) {
					mGPS.close();
					hideWaitingDialog();
					Toast.makeText(CGXSHGongDanWeiHu_72.this, "获取GPS超时！", 0).show();
					stopTime = 0;
					gpsTime ="";
				}
				break;
			case 9:// 获取gps失败
				mGPS.close();
				hideWaitingDialog();
				break;
			default:
				break;
			}
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_gongdanweihu7);
		mDialog = new AlertDialog.Builder(CGXSHGongDanWeiHu_72.this).create();// 连接网络进程
		try {

			btn_back = (Button) findViewById(R.id.btn_gongdanweihu7_return);
			btn_caogao = (Button) findViewById(R.id.btn_gongdanweihu7_caogao);
			btn_gps = (Button) findViewById(R.id.btn_gps_new_gongdanweihu7);
			btn_submit = (Button) findViewById(R.id.btn_gongdanweihu7_submit);
			tv_jing = (TextView) findViewById(R.id.tv_gongdan7_jingdu);
			tv_wei = (TextView) findViewById(R.id.tv_gongdan7_weidu);

			btn_gps.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					tv_jing.setText("");
					tv_wei.setText("");
					showWaitingDialog();
					start();
				}
			});

			btn_back.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					// exitTask(SHGongDanWeiHu_7.this);
					Intent intent = new Intent();
					intent.setClass(CGXSHGongDanWeiHu_72.this,
							CGXSHGongDanWeiHu_62.class);
					CGXSHGongDanWeiHu_72.this.startActivity(intent);
					CGXSHGongDanWeiHu_72.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);

				}

			});

			MyLis lis = new MyLis();
			btn_caogao.setOnClickListener(lis);
			btn_submit.setOnClickListener(lis);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					CGXSHGongDanWeiHu_72.this, "techown.db", Constant.tdshDBVer);
			SQLiteDatabase db = myHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery(
					"select * from shgongdanweihu where id==?",
					new String[] {TuanDuiShangHuActivity.tmpId8});
			while (cursor.moveToNext()) {
				try {
					String[] gps = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("endGps")))).split(";");
					tv_jing.setText(gps[0]);
					tv_wei.setText(gps[1]);
					
					gpsTime = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("endTime"))));

				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队SHGongDanWeiHu_72:" + e.getMessage());
				} finally {
					if (cursor != null) {
						cursor.close();
					}
					if (db != null) {
						db.close();
					}
				}

			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队SHGongDanWeiHu_72：" + e.getMessage());
		}

	}

	protected void start() {
		System.out.println("定位设置");
		mGPS = new GPSLocation(this, handler);
		mGPS.start();
		handler.sendEmptyMessageDelayed(8, 5 * 60 * 1000);
	}

	protected void showWaitingDialog() {
		Message msg2 = new Message();
		msg2.what = 1;
		handler.sendMessage(msg2);
	}

	protected void hideWaitingDialog() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}

	public void uploadData() {
		try {
			String[] clomname = new String[] { "endGps", "endTime",
					"submitTime" };
			String[] clomstr = new String[clomname.length];
			clomstr[0] = tv_jing.getText().toString() + ";"
					+ tv_wei.getText().toString();

			// 测试时用，记得删除
			//clomstr[1] = "20160621143225";
			clomstr[1] = gpsTime;
			clomstr[2] = GetDate.getDate().toString();
			DB.upload2(CGXSHGongDanWeiHu_72.this, "shgongdanweihu", clomname,
					clomstr, TuanDuiShangHuActivity.tmpId8);
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队SHGongDanWeiHu_72类SaveData（）" + e.getMessage());
		}
	}

	public void saveData() {

		String[] clomname = new String[] { "DCL", "LX", "TIME" };
		String[] clomstr = new String[10];
		clomstr[0] = "71";
		clomstr[1] = "商户工单维护";
		clomstr[2] = GetDate.getDate().toString();

		DB.upload2(CGXSHGongDanWeiHu_72.this, "shgongdanweihu", clomname, clomstr,
				TuanDuiShangHuActivity.tmpId8);

	}

	public void saveData1(Context con) {

		String[] clomname = new String[] { "DCL", "LX", "TIME" };
		String[] clomstr = new String[clomname.length];
		clomstr[0] = "0";
		clomstr[1] = "商户工单维护";
		clomstr[2] = GetDate.getDate().toString();

		DB.upload2(con, "shgongdanweihu", clomname, clomstr, TuanDuiShangHuActivity.tmpId8);

	}

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				Intent intent = new Intent();
				switch (v.getId()) {
				case R.id.btn_gongdanweihu7_submit:
					if (validate()) {
						uploadData();
						saveData();
						
						
						String barcodeno = huoQutaskId();

						DB.sendinsert(CGXSHGongDanWeiHu_72.this, new String[] {
								MyDatabaseHelper.SEND_BarcodeNo,
								MyDatabaseHelper.BEGINNUM,
								MyDatabaseHelper.TOTALNUM }, new String[] {
								barcodeno, "1", "0" });

						Builder b = new AlertDialog.Builder(
								CGXSHGongDanWeiHu_72.this);
						b.setTitle("提示");
						b.setMessage("提交成功");// 设置信息
						b.setCancelable(false);

						b.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {// 设置监听事件
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
										Intent intent = new Intent();
										intent.setClass(CGXSHGongDanWeiHu_72.this,
												TuanDuiShangHuActivity.class);
										CGXSHGongDanWeiHu_72.this
												.startActivity(intent);
										CGXSHGongDanWeiHu_72.this.finish();
										techown.shanghu.https.Log
												.Instance()
												.WriteLog(
														"团队：草稿箱商户工单维护进入发件箱，草稿箱商户工单维护进件 "
																+ TuanDuiShangHuActivity.tmpId8);
										overridePendingTransition(
												R.anim.leftin, R.anim.leftout);
									}
								});
						if (alterdlg == null || !alterdlg.isShowing()) {
							alterdlg = b.create();// 创建对话框
							alterdlg.show();// 显示对话框
							alterdlg.setCanceledOnTouchOutside(false);
							alterdlg.setCancelable(false);// 设置点击Dialog外部任意区域关
						}
						
					}
					break;
				case R.id.btn_gongdanweihu7_caogao:

					uploadData();
					EditDialog.ExitApp72(CGXSHGongDanWeiHu_72.this);
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队：草稿箱商户工单维护进入草稿箱 " + TuanDuiShangHuActivity.tmpId8);

					break;
				default:
					break;
				}

			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"SHGongDanWeiHu_7类按钮点击：" + e.getMessage());

			}
		}
	}

	private String huoQutaskId() {
		SQLiteDatabase db = null;
		Cursor cur = null;
		String taskId2 = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					CGXSHGongDanWeiHu_72.this, "techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.rawQuery("select * from shgongdanweihu where id==?",
					new String[] { TuanDuiShangHuActivity.tmpId8 });

			while (cur.moveToNext()) {
				taskId2 = des
						.jieMI(cur.getString(cur.getColumnIndex("taskId2")));
			}
			cur.close();
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队CaoYouHuiTakePhotoActivity2:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (db != null) {
				db.close();
			}
		}

		return taskId2;

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			// exitTask(SHGongDanWeiHu_7.this);
			Intent intent = new Intent();
			intent.setClass(CGXSHGongDanWeiHu_72.this, CGXSHGongDanWeiHu_62.class);
			CGXSHGongDanWeiHu_72.this.startActivity(intent);
			CGXSHGongDanWeiHu_72.this.finish();
			overridePendingTransition(R.anim.rightin, R.anim.rightout);

			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	// 验证必填字段的是否为空
	protected boolean validate() {
		if ((tv_jing.getText().toString().equals(""))
				|| (tv_wei.getText().toString().equals(""))) {
			log.Toast(CGXSHGongDanWeiHu_72.this, "经纬度不能为空，请先定位在提交！");
			return false;
		}

		return true;
	}

}
