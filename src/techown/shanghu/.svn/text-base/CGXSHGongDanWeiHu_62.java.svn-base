package techown.shanghu;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

import org.json.JSONObject;

import techown.shanghu.biaoge.ActionCode;
import techown.shanghu.biaoge.AlgorithmConvert;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetData;
import techown.shanghu.date.GetDate;
import techown.shanghu.date.RandomTest;
import techown.shanghu.photo.FileUtils;
import techown.shanghu.photo.TakePhotoActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 草稿箱：信息确认录入
 */
public class CGXSHGongDanWeiHu_62 extends Activity {

	DES des = new DES();
	EditDialog log = new EditDialog();
	DBUtil DB = new DBUtil();
	GetData getDasta = new GetData();
	String[] picname;
	Bitmap[] picbim;
	private Button btn_pre;
	private Button btn_next;
	private TextView tv_princal_name;
	private LinearLayout linear_princal_name;
	private TextView tv_princal_phone;
	private LinearLayout linear_princal_phone;
	private LinearLayout linear_sign;
	private ImageView iv_sign;
	private String picSignName;// 签名图片名称
	private String signPath;
	private String picPath;
	static String time, bb;
	FileUtils fileUtils;
	public static int request = 1;/* 1：签名 */

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_gongdanweihu6);
		initView();
		fileUtils = new FileUtils();
		time = GetDate.getDate().toString();// 20160616100725
		//RandomTest random = new RandomTest();
		//String aa = random.randString(11);
		//bb = time + TuanDuiShangHuActivity.username + aa;
		bb = time+TuanDuiShangHuActivity.username;
		picSignName = bb+"_sign.png";
		signPath="TJB/TuanDui/gesturesign/" + TuanDuiShangHuActivity.tmpId8;
		picPath ="TJB/TuanDui/shgongdan/" + TuanDuiShangHuActivity.tmpId8 + "/pic/";
		
		createDir(signPath);
		createDir(picPath);
		MyLis lis = new MyLis();
		btn_pre.setOnClickListener(lis);
		btn_next.setOnClickListener(lis);
		iv_sign.setOnClickListener(lis);

		try {

			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					CGXSHGongDanWeiHu_62.this, "techown.db", Constant.tdshDBVer);
			SQLiteDatabase db = myHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery(
					"select * from shgongdanweihu where id=?",
					new String[] { TuanDuiShangHuActivity.tmpId8 });
			while (cursor.moveToNext()) {
				try {

					tv_princal_name.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("principalName"))));
					tv_princal_phone.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("principalPhoneNum"))));
					
					if((des.jieMI(cursor.getString(cursor.getColumnIndex("signPicName"))))!=null){
						picSignName = des.jieMI(cursor.getString(cursor.getColumnIndex("signPicName")));
					}else{
						getBitmap("sdcard/"+picPath);
						for(int i=0; i<picname.length;i++){
							
							if (picname[i].split("_").length == 2
									&& picname[i].split("_")[1].equals("sign.png")) {
								fileUtils.deleteSDFILE(picPath+picname[i]);
							}
						}
					}
				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队CGXSHGongDanWeiHu_62:" + e.getMessage());
				} finally {
					if (cursor != null) {
						cursor.close();
					}
					if (db != null) {
						db.close();
					}
				}

			}
			getBitmap("sdcard/"+picPath);
			if (picbim != null) {
				for (int i = 0; i < picbim.length; i++) {
					if (picname[i].split("_").length == 2
							&& picname[i].split("_")[1].equals("sign.png")) {
						iv_sign.setImageBitmap(picbim[i]);
					}
				}
			}
			
			tv_princal_name.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(CGXSHGongDanWeiHu_62.this, linear_princal_name,
							tv_princal_name, R.id.tv_gongdan6_princal_name,
							"请输入负责人姓名");
				}
			});
			tv_princal_phone.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(CGXSHGongDanWeiHu_62.this, linear_princal_phone,
							tv_princal_phone, R.id.tv_gongdan6_princal_num,
							"请输入负责人联系电话");
				}
			});

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队CGXSHGongDanWeiHu_62：" + e.getMessage());
			picbim=null;
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		tv_princal_name = (TextView) findViewById(R.id.tv_gongdan6_princal_name);
		linear_princal_name = (LinearLayout) findViewById(R.id.linearlayout_gongdan6_name);

		tv_princal_phone = (TextView) findViewById(R.id.tv_gongdan6_princal_num);
		linear_princal_phone = (LinearLayout) findViewById(R.id.linearlayout_gongdan6_phone);

		linear_sign = (LinearLayout) findViewById(R.id.linearlayout_gongdan6_sign);
		iv_sign = (ImageView) findViewById(R.id.iv_gongdan6_sign);

		btn_pre = (Button) findViewById(R.id.btn_gongdan6_pre);
		btn_next = (Button) findViewById(R.id.btn_gongdan6_next);
	}

	public void sign(String name, String signPath) {
		File signFile = new File(signPath);
		if (!signFile.exists()) {
			signFile.mkdirs();
		}
		Intent intent = new Intent(this, SignActivity.class);
		intent.putExtra("name", name);
		intent.putExtra("signPath", signPath);
		startActivityForResult(intent, 2);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK && requestCode == 2) {
			String name = data.getStringExtra("name");
			gestureToPic(name, "sdcard/" + signPath, iv_sign);
			// loadPic(picPath,picSignName);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onResume() {

		super.onResume();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		picbim=null;
	} 
	public void getBitmap(String path) {
		try {
			String test[];
			File file = new File(path);
			//test = file.list();
			test = file.list(new FilenameFilter() {

				public boolean accept(File dir, String filename) {
					// TODO Auto-generated method stub
					if (filename.endsWith(".png")) {
						return true;
					}
					return false;
				}
			});
			picname = new String[test.length];
			picbim = new Bitmap[test.length];
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			for (int i = 0; i < test.length; i++) {
				picname[i] = test[i].trim();
				picbim[i] = BitmapFactory.decodeFile(path + test[i].trim(),
						options);
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队CGXSHGongDanWeiHu_62:" + e.getMessage());
			picname = null;
			picbim = null;
		}

	}

	public void gestureToPic(String name, String signPath, ImageView iv) {
		// 判断PAD是否存在内存卡
		if (!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			return;
		}
		int signColor = this.getResources().getColor(R.color.black);
		int mThumbnailInset = 18;
		GestureLibrary gestureLib = GestureLibraries.fromFile(signPath + "/"
				+ name);
		if (gestureLib.load()) { //
			int size = gestureLib.getGestureEntries().size();
			for (String signName : gestureLib.getGestureEntries()) {
				if (signName.equals(name)) {
					for (Gesture gesture : gestureLib.getGestures(signName)) {
						Bitmap bitmap = gesture.toBitmap(iv.getWidth(), // 设置显示手势图片大小
								iv.getHeight(), mThumbnailInset, signColor);

						// iv.setImageBitmap(bitmap);
						saveToSDcard(bitmap, picPath, picSignName);
					}
				}
			}
		}
	}

	public void createDir(String filepath) {

		fileUtils.createSDDir(filepath);

	}

	private void saveToSDcard(Bitmap bitmap, String picPath, String picName) {
		// TODO Auto-generated method stub
		try {
			if (bitmap != null) {
				fileUtils.deleteSDFILE(picPath + picName);
				File fPicture = fileUtils.createSDFILE(picPath + picName);

				FileOutputStream fout = new FileOutputStream(fPicture);
				BufferedOutputStream bos = new BufferedOutputStream(fout);
				bitmap.compress(Bitmap.CompressFormat.PNG, // 图片格式
						100, // 品质0-100
						bos // 使用的输出流
				);
				bos.flush();
				bos.close();
				iv_sign.setImageBitmap(bitmap);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			picbim=null;
		}
	}

	public void saveData() {
		try {

			String[] clomname = new String[] { "principalName",
					"principalPhoneNum", "signPicName" };

			String[] clomstr = new String[clomname.length];

			clomstr[0] = tv_princal_name.getText().toString();
			clomstr[1] = tv_princal_phone.getText().toString();
			clomstr[2] = picSignName;
			DB.upload2(CGXSHGongDanWeiHu_62.this, "shgongdanweihu", clomname,
					clomstr, TuanDuiShangHuActivity.tmpId8);
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"CGXHeYueQianDing_62类SaveData（）" + e.getMessage());
		}
	}

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				int id = v.getId();
				Intent intent = new Intent();
				switch (id) {
				case R.id.btn_gongdan6_pre:
					intent.setClass(CGXSHGongDanWeiHu_62.this,
							CGXSHGongDanWeiHu_52.class);
					CGXSHGongDanWeiHu_62.this.startActivity(intent);
					CGXSHGongDanWeiHu_62.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
					break;

				case R.id.btn_gongdan6_next:
					if (validate()) {
						saveData();
						intent.setClass(CGXSHGongDanWeiHu_62.this,
								CGXSHGongDanWeiHu_72.class);
						CGXSHGongDanWeiHu_62.this.startActivity(intent);
						CGXSHGongDanWeiHu_62.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
					break;
				case R.id.iv_gongdan6_sign:
					sign("debitsign", "sdcard/" + signPath);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"SHGongDanWeiHu()_2类按钮点击：" + e.getMessage());

			}
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Intent intent = new Intent();
			intent.setClass(CGXSHGongDanWeiHu_62.this, CGXSHGongDanWeiHu_52.class);
			CGXSHGongDanWeiHu_62.this.startActivity(intent);
			CGXSHGongDanWeiHu_62.this.finish();
			overridePendingTransition(R.anim.rightin, R.anim.rightout);
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 验证必填字段的是否为空
	protected boolean validate() {

		if ("".equals(tv_princal_name.getText().toString())) {
			log.Toast(CGXSHGongDanWeiHu_62.this, "请填写负责人姓名 ！");
			return false;

		}
		if ("".equals(tv_princal_phone.getText().toString())) {
			log.Toast(CGXSHGongDanWeiHu_62.this, "请填写负责人联系电话 ！");
			return false;
		}
		
		if(iv_sign.getDrawable()== null){
			log.Toast(CGXSHGongDanWeiHu_62.this, "请负责人签名 ！");
			return false;
		}
		return true;
	}

}
