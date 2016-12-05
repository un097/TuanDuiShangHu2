package techown.shanghu;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.GetDate;
import techown.shanghu.date.RandomTest;
import techown.shanghu.photo.CopyOfSHGDPictureDialog;
import techown.shanghu.photo.FileUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/*
 * 草稿箱 拍照页面
 */
public class CGXSHGongDanWeiHu_42 extends Activity {

	private Dialog mDialog;
	DES des = new DES();
	DBUtil DB = new DBUtil();
	AlertDialog alterdlg = null;
	EditDialog log = new EditDialog();
	String[] picname;
	static String bb;
	static String time;
	String currentPicName;
	Bitmap[] picbim;
	File fl;
	Bitmap bitmap;
	Bitmap realBitmap;
	String currentPhotoId;

	public static String path;
	FileUtils fileUtils;
	PadUtiltiy mPadUtiltiy;

	public static int request = 0;/* 1：拍照，4：重拍 */

	public Handler handlerphoto = new Handler() {

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
				ia.notifyDataSetChanged();
				break;
			case 3:
				Bundle b = msg.getData();
				String photoId = b.getString("photoid");
				deletePhoto(photoId);
				break;
			case 4:
				b = msg.getData();
				currentPhotoId = b.getString("photoid");
				break;
			default:
				break;
			}
		}
	};
	private Button btn_pic_entry;
	private Button btn_pic_table;
	private Button btn_pic_check;
	private Button btn_next;
	private Button btn_pre;
	private ListView imagList;
	private ImageAdapter ia;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.new_gongdanweihu4);
		time = GetDate.getDate().toString();// 20160616100725
		path = "TJB/TuanDui/shgongdan/" + TuanDuiShangHuActivity.tmpId8
				+ "/pic/";
		createDir(path);
		initView();
		guolaipaizhao();
		mPadUtiltiy = new PadUtiltiy(this);
		mDialog = new AlertDialog.Builder(CGXSHGongDanWeiHu_42.this).create();

		testBtnListener lis = new testBtnListener();
		btn_pic_entry.setOnClickListener(lis);
		btn_pic_table.setOnClickListener(lis);
		btn_pic_check.setOnClickListener(lis);
		btn_next.setOnClickListener(lis);
		btn_pre.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(CGXSHGongDanWeiHu_42.this,
						CGXSHGongDanWeiHu_32.class);
				CGXSHGongDanWeiHu_42.this.startActivity(intent);
				CGXSHGongDanWeiHu_42.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
			}
		});

		getBitmap(path);

		RandomTest random = new RandomTest();
		String aa = random.randString(11);
		bb = time + TuanDuiShangHuActivity.username + aa;
		if (picname != null) {
			for (int i = 0; i < picname.length; i++) {
				if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("entry.jpg")) {
					btn_pic_entry.setText("入口区拍摄(已拍)");
					btn_pic_entry.setEnabled(false);
				} else if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("table.jpg")) {
					btn_pic_table.setText("餐桌区拍摄(已拍)");
					btn_pic_table.setEnabled(false);
				} else if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("check.jpg")) {
					btn_pic_check.setText("收银区拍摄(已拍)");
					btn_pic_check.setEnabled(false);
				}
			}
		}
		ia = new ImageAdapter(CGXSHGongDanWeiHu_42.this, picname, picbim);
		imagList.setAdapter(ia);
		imagList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				CopyOfSHGDPictureDialog editDlg = new CopyOfSHGDPictureDialog(
						CGXSHGongDanWeiHu_42.this, ia.mbit[arg2],
						ia.mItemList[arg2]);
				editDlg.showListDialog();
			}

		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_pre = (Button) findViewById(R.id.btn_gongdan4_pre);
		btn_next = (Button) findViewById(R.id.btn_gongdan4_next);
		btn_pic_entry = (Button) findViewById(R.id.btn_gongdan4_takephoto1);
		btn_pic_table = (Button) findViewById(R.id.btn_gongdan4_takephoto2);
		btn_pic_check = (Button) findViewById(R.id.btn_gongdan4_takephoto3);
		imagList = (ListView) findViewById(R.id.lv_gongdan4_imageList);
	}

	class testBtnListener implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {

			case R.id.btn_gongdan4_next:// next
				if (validate()) {
					uploadname();
					Intent intent = new Intent();
					intent.setClass(CGXSHGongDanWeiHu_42.this,
							CGXSHGongDanWeiHu_52.class);
					CGXSHGongDanWeiHu_42.this.startActivity(intent);
					techown.shanghu.https.OperateLog.Instance().WriteLog(
							"进入SHGongDanWeiHu_42");
					CGXSHGongDanWeiHu_42.this.finish();

				}

				break;

			case R.id.btn_gongdan4_takephoto1:

				currentPicName = bb + "_entry.jpg";
				// Intent a = new Intent("android.media.action.IMAGE_CAPTURE");
				// startActivityForResult(a, 1);
				// if (TuanDuiShangHuActivity.device == 0) {
				// Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
				// i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new
				// File("/mnt/sdcard/tmpPhoto")));
				// i.putExtra("cameraid",0);
				// i.putExtra("width",640);
				// i.putExtra("height",480);
				// startActivityForResult(i, 1);
				// } else if (TuanDuiShangHuActivity.device == 1) {
				// Intent i = new Intent();
				// i.setClass(TakePhotoActivity.this, CaptureImage.class);
				// i.putExtra("cameraDir",1);
				// i.putExtra("photoWdith",640);
				// i.putExtra("photoHeight",480);
				// startActivityForResult(i, 1);
				mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
				request = 1;
				// }

				break;

			case R.id.btn_gongdan4_takephoto2:
				currentPicName = bb + "_table.jpg";
				// Intent b = new Intent("android.media.action.IMAGE_CAPTURE");
				// startActivityForResult(b, 2);
				// if (TuanDuiShangHuActivity.device == 0) {
				// Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
				// i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new
				// File("/mnt/sdcard/tmpPhoto")));
				// i.putExtra("cameraid",0);
				// i.putExtra("width",640);
				// i.putExtra("height",480);
				// startActivityForResult(i, 2);
				// } else if (TuanDuiShangHuActivity.device == 1) {
				// Intent i = new Intent();
				// i.setClass(TakePhotoActivity.this, CaptureImage.class);
				// i.putExtra("cameraDir",0);
				// i.putExtra("photoWdith",640);
				// i.putExtra("photoHeight",480);
				// startActivityForResult(i, 2);
				mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
				request = 2;
				// }

				break;

			case R.id.btn_gongdan4_takephoto3:
				currentPicName = bb + "_check.jpg";
				// Intent c = new Intent("android.media.action.IMAGE_CAPTURE");
				// startActivityForResult(c, 3);
				// if (TuanDuiShangHuActivity.device == 0) {
				// Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
				// i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new
				// File("/mnt/sdcard/tmpPhoto")));
				// i.putExtra("cameraid",0);
				// i.putExtra("width",640);
				// i.putExtra("height",480);
				// startActivityForResult(i, 3);
				// } else if (TuanDuiShangHuActivity.device == 1) {
				// Intent i = new Intent();
				// i.setClass(TakePhotoActivity.this, CaptureImage.class);
				// i.putExtra("cameraDir",0);
				// i.putExtra("photoWdith",640);
				// i.putExtra("photoHeight",480);
				// startActivityForResult(i, 3);
				mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
				request = 3;
				// }
				break;

			default:
				break;
			}

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == 1) {
				takePhoto(1);
			} else if (requestCode == 2) {
				takePhoto(2);
			} else if (requestCode == 3) {
				takePhoto(3);
			} else if (requestCode == 4) {
				takePhoto(4);
			} else if (requestCode == 6) {
				takePhoto(6);
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		picbim = null;
		bitmap = null;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (request == 1) {
			takePhoto(1);

		} else if (request == 2) {
			takePhoto(2);
		} else if (request == 3) {
			takePhoto(3);
		} else if (request == 4) {
			takePhoto(4);
		} else if (request == 5) {
			takePhoto(5);
		}

	}

	public void takePhoto(int num) {

		try {
			String tmpPhotoName = "tmpPhoto.jpg";
			Bitmap tmp = BitmapFactory
					.decodeFile("/mnt/sdcard/" + tmpPhotoName);
			fl = new File("/mnt/sdcard/" + tmpPhotoName);
			if (tmp != null) {
				bitmap = ResizeBitmap(tmp, 300);
			}

			realBitmap = BitmapFactory
					.decodeFile("/mnt/sdcard/" + tmpPhotoName);
			if (num == 4) {
				// createDir(path);
				saveFile(path, currentPhotoId, 4);
			} else {
				// createDir(path);
				saveFile(path, currentPicName, num);
			}

			// 删除tmpPhoto
			FileUtils fileUtils = new FileUtils();
			if (fileUtils.isFileExist(tmpPhotoName)) {
				fileUtils.deleteSDFILE(tmpPhotoName);
			}
			// }
			// Bundle extras = data.getExtras();
			// bitmap = (Bitmap) extras.get("data");

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队CGXSHGongDanWeih_42:" + e.getMessage());
		}

	}

	public void saveFile(String filepath, String fileName, int num) {
		try {

			techown.shanghu.https.Log.Instance().WriteLog(
					"当前图片大小====" + fl.length());
			System.out.println("文件大小===" + fl.length());
			if (realBitmap != null) {

				fileUtils.deleteSDFILE(filepath + fileName);
				File fPicture = fileUtils.createSDFILE(filepath + fileName);

				FileOutputStream fout = new FileOutputStream(fPicture);
				BufferedOutputStream bos = new BufferedOutputStream(fout);
				realBitmap.compress(Bitmap.CompressFormat.JPEG, // 图片格式
						100, // 品质0-100
						bos // 使用的输出流
						);
				bos.flush();
				bos.close();
				switch (num) {
				case 1:
					btn_pic_entry.setText("入口区拍摄(已拍)");
					btn_pic_entry.setEnabled(false);

					break;
				case 2:
					btn_pic_table.setText("餐桌区拍摄(已拍)");
					btn_pic_table.setEnabled(false);
					break;
				case 3:
					btn_pic_check.setText("收银区拍摄(已拍)");
					btn_pic_check.setEnabled(false);
					break;

				default:
					break;
				}
				getBitmap(path);
				ia.mbit = picbim;
				ia.mItemList = picname;
				Message msg = new Message();
				msg.what = 2;
				handlerphoto.sendMessage(msg);
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队CGXSHGongDanWeiHu_42:" + e.getMessage());
		}

	}

	public void deletePhoto(String photoid) {

		File fipic = new File("/sdcard/" + path + photoid);
		if (fipic.exists()) {
			fipic.delete();
		}
		if (photoid.split("_").length == 2
				&& photoid.split("_")[1].equals("entry.jpg")) {
			btn_pic_entry.setText("入口区拍摄(必选)");
			btn_pic_entry.setEnabled(true);
		} else if (photoid.split("_").length == 2
				&& photoid.split("_")[1].equals("table.jpg")) {
			btn_pic_table.setText("餐桌区拍摄(必选)");
			btn_pic_table.setEnabled(true);
		} else if (photoid.split("_").length == 2
				&& photoid.split("_")[1].equals("check.jpg")) {
			btn_pic_check.setText("收银区拍摄(必选)");
			btn_pic_check.setEnabled(true);

		}
		getBitmap(path);
		ia.mbit = picbim;
		ia.mItemList = picname;
		Message msg = new Message();
		msg.what = 2;
		handlerphoto.sendMessage(msg);

	}

	class ImageAdapter extends BaseAdapter {
		public String[] mItemList; // 修饰符不能是private
		public Bitmap[] mbit;
		public Context mContext;

		public ImageAdapter(Context con, String[] itemList, Bitmap[] bit) {
			mItemList = itemList;
			mbit = bit;
			mContext = con;
		}

		public int getCount() {
			if (mItemList == null) {
				return 0;
			} else {
				return mItemList.length;
			}

		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater
					.from(CGXSHGongDanWeiHu_42.this);
			View view = inflater.inflate(R.layout.img_list_item, null);

			TextView photoName = (TextView) view.findViewById(R.id.photoName);
			if (mItemList[position].split("_").length == 2
					&& mItemList[position].split("_")[1].equals("entry.jpg")) {
				photoName.setText("入口区拍摄");
			} else if (mItemList[position].split("_").length == 2
					&& mItemList[position].split("_")[1].equals("table.jpg")) {
				photoName.setText("餐桌区拍摄");
			} else if (mItemList[position].split("_").length == 2
					&& mItemList[position].split("_")[1].equals("check.jpg")) {
				photoName.setText("收银区拍摄");
			}
			
			ImageView iv = (ImageView) view.findViewById(R.id.photo);
			iv.setImageBitmap(mbit[position]);

			return view;
		}

	}

	public void createDir(String filepath) {
		fileUtils = new FileUtils();

		fileUtils.createSDDir(filepath);

	}

	String AttEntry;
	String AttTable;
	String AttCheck;

	private void guolaipaizhao() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					CGXSHGongDanWeiHu_42.this, "techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from shgongdanweihu where id=?",
					new String[] { TuanDuiShangHuActivity.tmpId8 });
			while (cursor.moveToNext()) {
				try {

					AttEntry = des.jieMI(cursor.getString(cursor
							.getColumnIndex("doorPicName")));
					AttTable = des.jieMI(cursor.getString(cursor
							.getColumnIndex("tablePicName")));
					AttCheck = des.jieMI(cursor.getString(cursor
							.getColumnIndex("checkPicName")));

				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队SHGongDanWeiHu_42:" + e.getMessage());
				}
				cursor.close();
				db.close();
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队CGXSHGongDanWeiHu_42:" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}

	private void uploadname() {
		String nameone = new String();
		String nametwo = new String();
		String namethree = new String();
		if (picname != null && picname.length > 0) {

			for (int i = 0; i < picname.length; i++) {
				if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("entry.jpg")) {
					nameone = picname[i];
				} else if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("table.jpg")) {
					nametwo = picname[i];
				} else if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("check.jpg")) {
					namethree = picname[i];
				}
			}

			DB.upload2(CGXSHGongDanWeiHu_42.this, "shgongdanweihu",
					new String[] { "doorPicName", "tablePicName",
							"checkPicName" }, new String[] { nameone, nametwo,
							namethree }, TuanDuiShangHuActivity.tmpId8);
		}
	}

	public void getBitmap(String path) {
		try {
			String test[];
			File file = new File("/sdcard/" + path);
			// test = file.list();
			test = file.list(new FilenameFilter() {

				public boolean accept(File dir, String filename) {
					// TODO Auto-generated method stub
					if (filename.endsWith(".jpg")) {
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
				picbim[i] = BitmapFactory.decodeFile("/sdcard/" + path
						+ test[i].trim(), options);

			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队CGXSHGongDanWeiHu_42:" + e.getMessage());
			picname = null;
			picbim = null;
		}

	}

	private Bitmap ResizeBitmap(Bitmap bitmap, int newWidth) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float temp = ((float) height) / ((float) width);
		int newHeight = (int) ((newWidth) * temp);
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// matrix.postRotate(45);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		bitmap.recycle();
		return resizedBitmap;
	}

	public boolean validate() {

		if (!btn_pic_entry.getText().toString().equals("入口区拍摄(已拍)")) {
			log.Toast(CGXSHGongDanWeiHu_42.this, "请拍摄入口区照片！");
			return false;
		}
		if (!btn_pic_table.getText().toString().equals("餐桌区拍摄(已拍)")) {
			log.Toast(CGXSHGongDanWeiHu_42.this, "请拍摄餐桌区照片！");
			return false;
		}
		if (!btn_pic_check.getText().toString().equals("收银区拍摄(已拍)")) {
			log.Toast(CGXSHGongDanWeiHu_42.this, "请拍摄收银区照片！");
			return false;
		}

		return true;

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			//log.ExitApp(CGXSHGongDanWeiHu_42.this);
			exitTask(CGXSHGongDanWeiHu_42.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
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
						intent.setClass(con, TuanDuiShangHuActivity.class);
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
