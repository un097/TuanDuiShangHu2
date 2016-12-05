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
import techown.shanghu.https.PostService;
import techown.shanghu.photo.TakePhotoActivity;
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
 * 签到
 */
public class SHGongDanWeiHu_1 extends Activity {

	DES des = new DES();
	EditDialog log = new EditDialog();
	DBUtil DB = new DBUtil();
	GetData getDasta = new GetData();
	private Button btn_back;
	private Button btn_next;
	private Button btn_gps;
	private TextView tv_jing;
	private TextView tv_wei;
	private GPSLocation mGPS;
	private AlertDialog mDialog;
	private long stopTime;
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
				break;
			case 8:
				if (stopTime == 0) {
					mGPS.close();
					hideWaitingDialog();
					Toast.makeText(SHGongDanWeiHu_1.this, "获取GPS超时！", 0).show();
					stopTime = 0;
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
		setContentView(R.layout.new_gongdanweihu1);
		mDialog = new AlertDialog.Builder(SHGongDanWeiHu_1.this).create();// 连接网络进程
		try {

			btn_back = (Button) findViewById(R.id.btn_gongdan1_pre);
			btn_next = (Button) findViewById(R.id.btn_gongdan1_next);
			btn_gps = (Button) findViewById(R.id.btn_gps_new_gongdanweihu1);

			tv_jing = (TextView) findViewById(R.id.tv_gongdan1_jing);
			tv_wei = (TextView) findViewById(R.id.tv_gongdan1_wei);

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
					exitTask(SHGongDanWeiHu_1.this);
					
				}

			});

			MyLis lis = new MyLis();
			btn_next.setOnClickListener(lis);

			/*MyDatabaseHelper myHelper = new MyDatabaseHelper(
					SHGongDanWeiHu_1.this, "techown.db", Constant.tdshDBVer);
			SQLiteDatabase db = myHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery(
					"select * from shgongdanweihu where id==?",
					new String[] { SHGongDanWeiHu.num });
			while (cursor.moveToNext()) {
				try {
					String[] gps = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("startGps")))).split(",");
					tv_jing.setText(gps[0]);
					tv_wei.setText(gps[1]);

				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队SHGongDanWeiHu_1:" + e.getMessage());
				} finally {
					if (cursor != null) {
						cursor.close();
					}
					if (db != null) {
						db.close();
					}
				}

			}*/

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队SHGongDanWeiHu_1：" + e.getMessage());
		}

	}
	protected void start() {
		System.out.println("定位设置");
		mGPS = new GPSLocation(this, handler);
		mGPS.start();
		handler.sendEmptyMessageDelayed(8, 5*60*1000);
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
	public void saveData() {
		try {
			String[] clomname = new String[] { "startGps", "startTime" };
			String[] clomstr = new String[clomname.length];
			clomstr[0] = tv_jing.getText().toString() + ";"
					+ tv_wei.getText().toString();

			
			//stopTime = System.currentTimeMillis();//测试时用，记得删除
			Date data = new Date(stopTime);
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
			clomstr[1] = df.format(data);
			DB.upload2(SHGongDanWeiHu_1.this, "shgongdanweihu", clomname,
					clomstr, SHGongDanWeiHu.num);
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队SHGongDanWeiHu_1类SaveData（）" + e.getMessage());
		}
	}

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				int id = v.getId();
				Intent intent = new Intent();
				switch (id) {
				case R.id.btn_gongdan1_next:
					if (validate()) {
						saveData();
						intent.setClass(SHGongDanWeiHu_1.this,
								SHGongDanWeiHu_2.class);
						SHGongDanWeiHu_1.this.startActivity(intent);
						SHGongDanWeiHu_1.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
				}
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"SHGongDanWeiHu_1类按钮点击：" + e.getMessage());

			}
		}
		// }
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			exitTask(SHGongDanWeiHu_1.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	boolean m = true;

	// 验证必填字段的是否为空
	protected boolean validate() {
		if ((tv_jing.getText().toString().equals(""))
				|| (tv_wei.getText().toString().equals(""))) {
			log.Toast(SHGongDanWeiHu_1.this, "经纬度不能为空，请重新定位！");
			return false;
		}

		return true;
	}

	private void exitTask(final Context con){
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要退出此次工单填写？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						//删除此次工单信息
						/*MyDatabaseHelper myHelper = new MyDatabaseHelper(
								SHGongDanWeiHu_1.this, "techown.db", Constant.tdshDBVer);
						SQLiteDatabase db = myHelper.getWritableDatabase();
						db.delete("shgongdanweihu", "Id = ?", new String[]{SHGongDanWeiHu.num});
						*/
						Intent intent = new Intent();
						intent.setClass(con, SHGongDanWeiHu.class);
						con.startActivity(intent);
						((Activity) con).finish();
						overridePendingTransition(R.anim.rightin, R.anim.rightout);
						
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}
}
