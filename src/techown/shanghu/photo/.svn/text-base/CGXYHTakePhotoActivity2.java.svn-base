package techown.shanghu.photo;
/*
 * 草稿箱 拒件
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import techown.shanghu.DBUtil;
import techown.shanghu.EditDialog;
import techown.shanghu.HeYueQianDing_51_5;
import techown.shanghu.MyDatabaseHelper;
import techown.shanghu.PadUtiltiy;
import techown.shanghu.R;
import techown.shanghu.TuanDuiShangHuActivity;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.GetDate;
import techown.shanghu.date.RandomTest;
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
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CGXYHTakePhotoActivity2 extends Activity {
	/** Called when the activity is first created. */
	private Button takephoto_before, takephoto_next, button7, takephoto1,
			takephoto2, takephoto3, takephoto4;
	private Dialog mDialog;
	public static int count1 = 0;
	EditDialog log=new EditDialog();
	File fPicture;
	DES des = new DES();
	DBUtil DB=new DBUtil();
	AlertDialog alterdlg = null;
	Handler handlerphoto = new Handler() {

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
	Bitmap bitmap;

	ListView imageList;

	int huihui = 0;

	String currentPhotoId;
	String currentPicName;

	public static String path;
	int sqsnum, m;
	String deletenum;
	List<String>n=new ArrayList<String>();
//	boolean isdelete;
	String[] picname;
	Bitmap[] picbim;
	ImageAdapter ia;
	static String bb;
	static String time ;
	FileUtils fileUtils;
	PadUtiltiy mPadUtiltiy;
	public static	int request = 0;/*1：拍照，6：重拍 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.takephoto);
		time = GetDate.getDate().toString();
		path = "TJB/TuanDui/shang/" + TuanDuiShangHuActivity.tmpId2 + "/pic/";
		createDir(path);
//		guolaipaizhao();
		tiaoxingma();
		mPadUtiltiy = new PadUtiltiy(this);
		mDialog = new AlertDialog.Builder(CGXYHTakePhotoActivity2.this).create();
		// panel = new Panel(this);
		takephoto_before = (Button) findViewById(R.id.takephoto_before);
		takephoto_before.setOnClickListener(new testBtnListener());

		takephoto_next = (Button) findViewById(R.id.button6);
		takephoto_next.setOnClickListener(new testBtnListener());

		button7 = (Button) findViewById(R.id.button7);
		button7.setOnClickListener(new testBtnListener());



		takephoto1 = (Button) findViewById(R.id.takephoto1);
		
		if (Dnum!=null&&Dnum.equals("1")) {
			takephoto1.setText("营业执照拍摄(已拍)");
			takephoto1.setEnabled(false);
			huihui = 2;
		} else {
				takephoto1.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						currentPicName = bb + "_zz.jpg";
//						if (TuanDuiShangHuActivity.device == 0) {
//							Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//							i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//							i.putExtra("cameraid",0);
//							i.putExtra("width",640);
//							i.putExtra("height",480);
//							startActivityForResult(i, 1);
//						} else if (TuanDuiShangHuActivity.device == 1) {
//							Intent i = new Intent();
//				    		i.setClass(CGXYHTakePhotoActivity2.this, CaptureImage.class);
//				    		i.putExtra("cameraDir",0);
//							i.putExtra("photoWdith",640);
//							i.putExtra("photoHeight",480);
//							startActivityForResult(i, 1);
							mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
							request = 1;
//						}
						
					}
				});
			
			}
//		}
		
		

		takephoto2 = (Button) findViewById(R.id.takephoto2);

		if (Dnum!=null&&Dnum.equals("1")) {
			takephoto2.setText("盖章协议拍摄(已拍)");
			takephoto2.setEnabled(false);
			huihui = 2;
		}

		else {
				takephoto2.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						currentPicName = bb + "_xy.jpg";
//						if (TuanDuiShangHuActivity.device == 0) {
//							Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//							i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//							i.putExtra("cameraid",0);
//							i.putExtra("width",640);
//							i.putExtra("height",480);
//							startActivityForResult(i, 2);
//						} else if (TuanDuiShangHuActivity.device == 1) {
//							Intent i = new Intent();
//				    		i.setClass(CGXYHTakePhotoActivity2.this, CaptureImage.class);
//				    		i.putExtra("cameraDir",0);
//							i.putExtra("photoWdith",640);
//							i.putExtra("photoHeight",480);
//							startActivityForResult(i, 2);
							mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
							request = 2;
//						}
						
					}
				});
			}
		
		
		// 授权书拍摄

		takephoto3 = (Button) findViewById(R.id.takephoto3);
		takephoto3.setOnClickListener(new testBtnListener());

		// 其他拍摄
		takephoto4 = (Button) findViewById(R.id.takephoto4);
		takephoto4.setOnClickListener(new testBtnListener());

		imageList = (ListView) findViewById(R.id.imageList);

		getBitmap(path);

		RandomTest random = new RandomTest();
		String aa = random.randString(11);
		bb = time + TuanDuiShangHuActivity.username + aa;

		if (picname != null) {
			for (int i = 0; i < picname.length; i++) {
				if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("zz.jpg")) {
					takephoto1.setText("营业执照拍摄(已拍)");
					takephoto1.setEnabled(false);
				} else if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("xy.jpg")) {
					takephoto2.setText("盖章协议拍摄(已拍)");
					takephoto2.setEnabled(false);
				} else if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("sqs.jpg")) {
					takephoto3.setText("授权书拍摄(已拍)");
					takephoto3.setEnabled(false);
				} else if (picname[i].split("_").length == 3
						&& picname[i].split("_")[2].equals("qt.jpg")) {
					m++;

					takephoto4.setText("其他附件拍照(已拍" + m + ")");
					if (m == 5) {
						takephoto4.setEnabled(false);
					}

				}
			}
		}

		ia = new ImageAdapter(CGXYHTakePhotoActivity2.this, picname, picbim);
		imageList.setAdapter(ia);
		imageList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				PictureDialog1 editDlg = new PictureDialog1(
						CGXYHTakePhotoActivity2.this, ia.mbit[arg2],
						ia.mItemList[arg2]);
				editDlg.showListDialog();
			}

		});
	}

	class testBtnListener implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();

			switch (id) {
			case R.id.takephoto_before:// 存入草稿箱
				uploadname();
				log.ExitApp2222(CGXYHTakePhotoActivity2.this);
				techown.shanghu.https.Log
				.Instance()
				.WriteLog(
						"团队：合约签订(拒件)进入草稿箱 "+TuanDuiShangHuActivity.tmpId2
								);
				break;
			case R.id.button6:// 提交
				// if(takephoto1.equals("营业执照拍摄(已拍)") &&
				// takephoto2.equals("盖章协议拍摄(已拍)")){
				if (takephoto1.getText().toString().equals("营业执照拍摄(已拍)") &&
						 takephoto2.getText().toString().equals("盖章协议拍摄(已拍)")) {
					uploadname();
					saveData();

					String barcodeno = bb;

					DB.sendinsert(CGXYHTakePhotoActivity2.this, new String[] {
							MyDatabaseHelper.SEND_BarcodeNo,
							MyDatabaseHelper.BEGINNUM,
							MyDatabaseHelper.TOTALNUM }, new String[] {
							barcodeno, "1", "0" });

					Builder b = new AlertDialog.Builder(CGXYHTakePhotoActivity2.this);
					b.setTitle("提示");
					b.setMessage("提交成功");// 设置信息
					b.setCancelable(false);

					b.setPositiveButton("确定", new DialogInterface.OnClickListener() {// 设置监听事件
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
									Intent intent = new Intent();
									intent.setClass(
											CGXYHTakePhotoActivity2.this,
											TuanDuiShangHuActivity.class);
									CGXYHTakePhotoActivity2.this
											.startActivity(intent);
									techown.shanghu.https.OperateLog.Instance().WriteLog("商户团队草稿箱拒件提交成功！");
									CGXYHTakePhotoActivity2.this.finish();
								}
							});
					if(alterdlg==null||!alterdlg.isShowing()){
						alterdlg = b.create();// 创建对话框
						alterdlg.show();// 显示对话框
						alterdlg.setCanceledOnTouchOutside(false);
						alterdlg.setCancelable(false);// 设置点击Dialog外部任意区域关
					}
					
//					new AlertDialog.Builder(CGXYHTakePhotoActivity2.this)
//							.setTitle("提示")
//							.setMessage("提交成功")
//							.setPositiveButton("确定",
//									new DialogInterface.OnClickListener() {
//
//										public void onClick(
//												DialogInterface arg0, int arg1) {
//											Intent intent = new Intent();
//											intent.setClass(
//													CGXYHTakePhotoActivity2.this,
//													TuanDuiShangHuActivity.class);
//											CGXYHTakePhotoActivity2.this
//													.startActivity(intent);
//											CGXYHTakePhotoActivity2.this.finish();
//										}
//									}).show();
					techown.shanghu.https.Log
					.Instance()
					.WriteLog(
							"团队：草稿箱进入发件箱，合约签订(拒件)进件 "+TuanDuiShangHuActivity.tmpId2
									);

				}	
				else {
//					Toast.makeText(TakePhotoActivity2.this,
//							"请您检查营业执照和盖章协议是否已拍摄！", 3).show();
					log.Toast(CGXYHTakePhotoActivity2.this, "请您检查营业执照和盖章协议是否已拍摄！");
				}
				break;
			case R.id.button7:// 返回
				uploadname();
				Intent intent = new Intent();
				
				intent.setClass(CGXYHTakePhotoActivity2.this,
						HeYueQianDing_51_5.class);
				CGXYHTakePhotoActivity2.this.startActivity(intent);
				CGXYHTakePhotoActivity2.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;

			case R.id.takephoto1:
				currentPicName = bb + "_zz.jpg";
//				if (TuanDuiShangHuActivity.device == 0) {
//					Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//					i.putExtra("cameraid",0);
//					i.putExtra("width",640);
//					i.putExtra("height",480);
//					startActivityForResult(i, 1);
//				} else if (TuanDuiShangHuActivity.device == 1) {
//					Intent i = new Intent();
//		    		i.setClass(CGXYHTakePhotoActivity2.this, CaptureImage.class);
//		    		i.putExtra("cameraDir",0);
//					i.putExtra("photoWdith",640);
//					i.putExtra("photoheight",480);
//					startActivityForResult(i, 1);
					mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
					request = 1;
//				}
				
				break;

			case R.id.takephoto2:
				currentPicName = bb + "_xy.jpg";
//				if (TuanDuiShangHuActivity.device == 0) {
//					Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//					i.putExtra("cameraid",0);
//					i.putExtra("width",640);
//					i.putExtra("height",480);
//					startActivityForResult(i, 2);
//				} else if (TuanDuiShangHuActivity.device == 1) {
//					Intent i = new Intent();
//		    		i.setClass(CGXYHTakePhotoActivity2.this, CaptureImage.class);
//		    		i.putExtra("cameraDir",0);
//					i.putExtra("photoWdith",640);
//					i.putExtra("photoheight",480);
//					startActivityForResult(i, 2);
					mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
					request = 2;
//				}
				
				
				break;

			case R.id.takephoto3:
				currentPicName = bb + "_sqs.jpg";
//				if (TuanDuiShangHuActivity.device == 0) {
//					Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//					i.putExtra("cameraid",0);
//					i.putExtra("width",640);
//					i.putExtra("height",480);
//					startActivityForResult(i, 3);
//				} else if (TuanDuiShangHuActivity.device == 1) {
//					Intent i = new Intent();
//		    		i.setClass(CGXYHTakePhotoActivity2.this, CaptureImage.class);
//		    		i.putExtra("cameraDir",0);
//					i.putExtra("photoWdith",640);
//					i.putExtra("photoHeight",480);
//					startActivityForResult(i, 3);
					mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
					request = 3;
//				}
				break;

			case R.id.takephoto4:
				if (n.size()>0) {
					currentPicName = bb + "_" + n.get(0) + "_qt.jpg";
					n.remove(0);
				} else {
					currentPicName = bb + "_" + m + "_qt.jpg";
				}
//				if (TuanDuiShangHuActivity.device == 0) {
//					Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//					i.putExtra("cameraid",0);
//					i.putExtra("width",640);
//					i.putExtra("height",480);
//					startActivityForResult(i, 5);
//				} else if (TuanDuiShangHuActivity.device == 1) {
//					Intent i = new Intent();
//		    		i.setClass(CGXYHTakePhotoActivity2.this, CaptureImage.class);
//		    		i.putExtra("cameraDir",0);
//					i.putExtra("photoWdith",640);
//					i.putExtra("photoHeight",480);
//					startActivityForResult(i, 5);
					mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
					request = 5;
//				}
				break;
			}

		}
	}

	public void createDir(String filepath) {
		fileUtils = new FileUtils();

		fileUtils.createSDDir(filepath);

	}

	public void saveFile(String filepath, String fileName, int num) {
		try {

			techown.shanghu.https.Log.Instance().WriteLog("当前图片大小===="+fl.length());
			System.out.println("文件大小==="+fl.length());
			if(realBitmap!=null){
			
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
				takephoto1.setText("营业执照拍摄(已拍)");
				takephoto1.setEnabled(false);
//				huihui++;
				break;
			case 2:
				takephoto2.setText("盖章协议拍摄(已拍)");
				takephoto2.setEnabled(false);
				huihui=2;
				break;
			case 3:
				takephoto3.setText("授权书拍摄（已拍）");
				takephoto3.setEnabled(false);
				break;
			case 5:
				m++;
				takephoto4.setText("其他附件拍照（已拍）" + m);
				if (m == 5) {
					takephoto4.setEnabled(false);
				}
				break;
				default :
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
			techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity2:"+e.getMessage());	
		}

	}

	public void deletePhoto(String photoid) {

		File fipic = new File("/sdcard/" + path + photoid);
		if (fipic.exists()) {
			fipic.delete();
		}
		if (photoid.split("_").length == 2
				&& photoid.split("_")[1].equals("zz.jpg")) {
			takephoto1.setText("营业执照拍摄(必选)");
			takephoto1.setEnabled(true);
			huihui--;
		} else if (photoid.split("_").length == 2
				&& photoid.split("_")[1].equals("xy.jpg")) {
			takephoto2.setText("盖章协议拍摄(必选)");
			takephoto2.setEnabled(true);
			huihui--;
		} else if (photoid.split("_").length == 2
				&& photoid.split("_")[1].equals("sqs.jpg")) {
			takephoto3.setText("授权书拍摄(可选)");
			takephoto3.setEnabled(true);

		} else if (photoid.split("_").length == 3
				&& photoid.split("_")[2].equals("qt.jpg")) {
			m--;
			n.add(photoid.split("_")[1]);
//			isdelete = true;
			if(m==0)
        	{
        		takephoto4.setText("其他附件拍照");
        	}else
        	{
        		takephoto4.setText("其他附件拍照(已拍" + m + ")");
        	}
			takephoto4.setEnabled(true);

		}
		getBitmap(path);
		ia.mbit = picbim;
		ia.mItemList = picname;
		Message msg = new Message();
		msg.what = 2;
		handlerphoto.sendMessage(msg);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK)
    	{
		if (requestCode == 1) {
			takePhoto(1);

		} else if (requestCode == 2) {
			takePhoto(2);
		} else if (requestCode == 3) {
			takePhoto(3);
		} else if (requestCode == 4) {
			takePhoto(4);
		} else if (requestCode == 5) {
			takePhoto(5);
		}
    	}
	}
	Bitmap realBitmap;
	File fl;
	public void takePhoto(int num) {

		try {
//			if(TuanDuiShangHuActivity.device == 0){
//				Bitmap tmpLT = BitmapFactory
//						.decodeFile("/mnt/sdcard/tmpPhoto/");
//				fl = new File("/mnt/sdcard/tmpPhoto/");
//				if (tmpLT != null) {
//						bitmap= ResizeBitmap(tmpLT, 300);
//				}
//				
//				realBitmap = BitmapFactory.decodeFile("/mnt/sdcard/tmpPhoto/");
//				if (num == 4) {
//					// createDir(path);
//					saveFile(path, currentPhotoId, 4);
//				} else {
//					// createDir(path);
//					saveFile(path, currentPicName, num);
//				}
//
//				//删除tmpPhoto
//				FileUtils fileUtils = new FileUtils();
//	    		if(fileUtils.isFileExist("tmpPhoto")){
//	    			fileUtils.deleteSDFILE("tmpPhoto");
//	    		}
//				
//			}else if(TuanDuiShangHuActivity.device == 1){
				String tmpPhotoName = "tmpPhoto.jpg";
				Bitmap tmp = BitmapFactory.decodeFile("/mnt/sdcard/"+tmpPhotoName);
				fl = new File("/mnt/sdcard/"+tmpPhotoName);
				if (tmp != null) {
						bitmap= ResizeBitmap(tmp, 300);
				}
				
				
				realBitmap = BitmapFactory.decodeFile("/mnt/sdcard/"+tmpPhotoName);
				if (num == 4) {
					// createDir(path);
					saveFile(path, currentPhotoId, 4);
				} else {
					// createDir(path);
					saveFile(path, currentPicName, num);
				}

					
				//删除tmpPhoto
				FileUtils fileUtils = new FileUtils();
	    		if(fileUtils.isFileExist(tmpPhotoName)){
	    			fileUtils.deleteSDFILE(tmpPhotoName);
	    		}
//			}	
			
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity2:"+e.getMessage());	
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
	        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
	        bitmap.recycle(); 
	        return resizedBitmap;
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
					.from(CGXYHTakePhotoActivity2.this);
			View view = inflater.inflate(R.layout.img_list_item, null);

			TextView photoName = (TextView) view.findViewById(R.id.photoName);
			if (mItemList[position].split("_").length == 2
					&& mItemList[position].split("_")[1].equals("zz.jpg")) {
				photoName.setText("营业执照拍摄");
			} else if (mItemList[position].split("_").length == 2
					&& mItemList[position].split("_")[1].equals("xy.jpg")) {
				photoName.setText("盖章协议拍摄");
			} else if (mItemList[position].split("_").length == 2
					&& mItemList[position].split("_")[1].equals("sqs.jpg")) {
				photoName.setText("授权书拍摄");
			} else if (mItemList[position].split("_").length == 3
					&& mItemList[position].split("_")[2].equals("qt.jpg")) {
				photoName.setText("其他附件拍照"
						+ String.valueOf(Integer.parseInt(mItemList[position]
								.split("_")[1]) + 1));
			}
			ImageView iv = (ImageView) view.findViewById(R.id.photo);
			iv.setImageBitmap(mbit[position]);
			return view;
		}

	}

	public void getBitmap(String path) {
		try {
			String test[];
			File file = new File("/sdcard/" + path);
			test = file.list();
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
			techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity2:"+e.getMessage());	
			picname = null;
		}

	}

	 @Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			picbim=null;
			bitmap=null;
		} 
		 @Override
		   	protected void onResume() {
		   		// TODO Auto-generated method stub
		   		super.onResume();
		   		if(request == 1){
					takePhoto(1);
					
				}
				else if(request == 2){
					takePhoto(2);
				}
				else if(request == 3)
				{
					takePhoto(3);	
				}
				else if(request == 4)
				{
					takePhoto(4);	
				}
				else if(request==5)
				{
					takePhoto(5);	
				}
		   		
		   	}
	
	

	public void saveData() {

		String[] clomname = new String[] { "DCL", "LX", "TIME", "MId" };
		String[] clomstr = new String[10];
		clomstr[0] = "22";
		clomstr[1] = "合约签订(拒件)";
		clomstr[2] = time;
		clomstr[3] = bb;

		DB.upload(CGXYHTakePhotoActivity2.this, clomname, clomstr,
				TuanDuiShangHuActivity.tmpId2);

	}

	public  void saveData1(Context con) {

		String[] clomname = new String[] { "DCL", "LX","MId" };
		String[] clomstr = new String[10];
		clomstr[0] = "0";
		clomstr[1] = "合约签订(拒件)";
		clomstr[2] = bb;
		DB.upload(con, clomname, clomstr, TuanDuiShangHuActivity.tmpId2);

	}

	private void uploadname() {
		String nameone = new String();
		String nametwo = new String();
		String namethree = new String();
		String namef = new String();
		StringBuilder sbthree = new StringBuilder();
		if (picname != null && picname.length > 0) {

			for (int i = 0; i < picname.length; i++) {
				if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("zz.jpg")) {
					nameone = picname[i];
				} else if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("xy.jpg")) {
					nametwo = picname[i];
				} else if (picname[i].split("_").length == 2
						&& picname[i].split("_")[1].equals("sqs.jpg")) {
					namethree = picname[i];
				} else if (picname[i].split("_").length == 3
						&& picname[i].split("_")[2].equals("qt.jpg")) {
					sbthree.append(picname[i] + ";");
				}
			}
			if (sbthree != null && sbthree.length() > 0) {
				namef = sbthree.delete(sbthree.length() - 1, sbthree.length())
						.toString();
			}

			DB.upload(CGXYHTakePhotoActivity2.this, new String[] { "AttLicense",
					"AttContract", "AttAccredit", "AttOthers" }, new String[] {
					nameone, nametwo, namethree, namef },
					TuanDuiShangHuActivity.tmpId2);
		}
	}
	public boolean validate() {
    	

	    
    	if(!takephoto1.getText().toString().equals("营业执照拍摄(已拍)")){
//    		Toast.makeText(TakePhotoActivity2.this, "请拍摄营业执照！", Toast.LENGTH_SHORT)
//			.show();
    		log.Toast(CGXYHTakePhotoActivity2.this, "请拍摄营业执照！");
    		return false;
    	}
    	if(!takephoto2.getText().toString().equals("盖章协议拍摄(已拍)")){
//    		Toast.makeText(TakePhotoActivity2.this, "请拍摄盖章协议！", Toast.LENGTH_SHORT)
//			.show();
    		log.Toast(CGXYHTakePhotoActivity2.this, "请拍摄盖章协议！");
    		return false;
    	}
    	
    	
		return true;
    	
    }

	String Dnum;
	public void tiaoxingma() {
		SQLiteDatabase db=null;
		Cursor cursor=null;
		try
		{
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					CGXYHTakePhotoActivity2.this, "techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from st_storeinfo where id==?",
					new String[] { TuanDuiShangHuActivity.tmpId2 });
			while (cursor.moveToNext()) {
				try {
					// s =
					// cursor.getString(cursor.getColumnIndex("ContractNumber"));
					Dnum =des.jieMI(cursor.getString(cursor
							.getColumnIndex("Dnum"))) ;

				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity2:"+e.getMessage());	
				}
				cursor.close();
				db.close();
			}
	
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity2:"+e.getMessage());		
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
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(CGXYHTakePhotoActivity2.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	
	
	
	
}