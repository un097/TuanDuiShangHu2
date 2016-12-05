package techown.shanghu.photo;
/*
 * 草稿箱 特惠上线
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import techown.shanghu.DBUtil;
import techown.shanghu.EditDialog;
import techown.shanghu.MyDatabaseHelper;
import techown.shanghu.PadUtiltiy;
import techown.shanghu.R;
import techown.shanghu.TuanDuiShangHuActivity;
import techown.shanghu.YouHuiShangXian_TeHui4_4;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.GetDate;
import techown.shanghu.https.HttpConnection2;
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
import android.util.Base64;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CGXYHTakePhotoActivity extends Activity {
    /** Called when the activity is first created. */
	private Button takephoto_before,takephoto_next,takephoto1,takephoto2,takephoto3,button6;
	private Dialog mDialog;
	TextView textview1,textview2,textview3,textview4;
	LinearLayout etlayout1,etlayout2,etlayout3,etlayout4;
	EditDialog log=new EditDialog();
	DES des = new DES();
	DBUtil DB=new DBUtil();
	AlertDialog alterdlg = null;
	Handler handlerphoto = new Handler(){   
	       
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
	public static String merId;
	int huihui=0;
	String currentPhotoId;
	String currentPicName;
	
	public static String path1;
	int qtnum;
//	String deletenum;
//	boolean isdelete;
	List<String>deletenum=new ArrayList<String>();
	String[]picname;
	Bitmap[]picbim;
	ImageAdapter ia;
	FileUtils fileUtils;
	PadUtiltiy mPadUtiltiy;
	public static	int request = 0;/*1：拍照，6：重拍 */
	static String time;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		time=GetDate.getDate().toString();
		tiaoxingma();
//		createDir(path1);
		
		setContentView(R.layout.youhuishangxian_tehui6);
		
		mPadUtiltiy = new PadUtiltiy(this);
		mDialog = new AlertDialog.Builder(CGXYHTakePhotoActivity.this).create();
		//panel = new Panel(this);
		button6=(Button)findViewById(R.id.chaogaoxiang);
		button6.setOnClickListener(new testBtnListener());
		
		takephoto_before= (Button)findViewById(R.id.button5);
		takephoto_before.setOnClickListener(new testBtnListener());
		
		takephoto_next= (Button)findViewById(R.id.button4);
		takephoto_next.setOnClickListener(new testBtnListener());
		
		takephoto1= (Button)findViewById(R.id.takephoto1);

			takephoto1.setOnClickListener(new testBtnListener());

		
		takephoto2= (Button)findViewById(R.id.takephoto2);

			takephoto2.setOnClickListener(new testBtnListener());
		
		takephoto3= (Button)findViewById(R.id.takephoto3);
		takephoto3.setOnClickListener(new testBtnListener());

		textview1=(TextView)findViewById(R.id.editText1);
		textview2=(TextView)findViewById(R.id.editText2);
		textview3=(TextView)findViewById(R.id.editText3);
//		textview4=(TextView)findViewById(R.id.editText4);
		
		etlayout1=(LinearLayout)findViewById(R.id.etlayout1);
		etlayout2=(LinearLayout)findViewById(R.id.etlayout2);
		etlayout3=(LinearLayout)findViewById(R.id.etlayout3);
//		etlayout4=(LinearLayout)findViewById(R.id.etlayout4);
		
		imageList = (ListView)findViewById(R.id.imageList);
		path1 = "TJB/TuanDui/youhuishang/"+TuanDuiShangHuActivity.tmpId3+"/pic/";
		getBitmap(path1);
		if(picname!=null)
		{
			for(int i=0;i<picname.length;i++)
			{
				if(picname[i].split("_").length==3&&picname[i].split("_")[2].equals("syt.jpg"))
		        {
					takephoto1.setText("收银台卡/跳跳卡拍摄(已拍)");
					takephoto1.setEnabled(false);
		        }
		        else if(picname[i].split("_").length==3&&picname[i].split("_")[2].equals("mt.jpg"))
		        {
		        	takephoto2.setText("海报/宣传贴拍摄(已拍)");
		        	takephoto2.setEnabled(false);
		        }

		        else if(picname[i].split("_").length==4&&picname[i].split("_")[3].equals("qt.jpg"))
		        {   
		        	qtnum++;
		        	
		        	takephoto3.setText("门贴/其他拍照(已拍"+qtnum+")");
		        	if(qtnum==5)
		        	{
		        		takephoto3.setEnabled(false);	
		        	}
		        	
		        }	
			}
		}
		
		textview1.setText(Tkint);
		textview2.setText(Cztint);
		textview3.setText(Mtint);
		
		etlayout1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.etDialog(CGXYHTakePhotoActivity.this, textview1,R.id.editText1,"请输入收银台卡/跳跳卡数量");
			}
		});
	
		etlayout2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.etDialog(CGXYHTakePhotoActivity.this, textview2,R.id.editText2,"请输入海报/宣传贴数量");
			}
		});
		etlayout3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.etDialog(CGXYHTakePhotoActivity.this, textview3,R.id.editText3,"请输入门贴/其他数量");
			}
		});
		
		ia=new ImageAdapter(CGXYHTakePhotoActivity.this,picname,picbim);
		imageList.setAdapter(ia);            	
		imageList.setOnItemClickListener(
				new OnItemClickListener()
				{
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
					CGXYHTPictureDialog editDlg = new CGXYHTPictureDialog(CGXYHTakePhotoActivity.this, ia.mbit[arg2],ia.mItemList[arg2]);
					editDlg.showListDialog();
					}
		
				}
		);
    }
    public void createDir(String filepath) {
		fileUtils = new FileUtils();

		fileUtils.createSDDir(filepath);

	}
    protected String Jsonstr;
	class testBtnListener implements OnClickListener{
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    		int id = v.getId();
    		
    		switch(id){
    		case R.id.button5:
    			Message msg = new Message();   
		        msg.what =1; 
		        handlerphoto.sendMessage(msg);//
				 new Thread(){
					 public void run(){
						 Intent intent = new Intent();
						 String[]str1=new String[]{"","",""};	
						 str1[0]=merId;
						 str1[1]=actId;
						
						 String json1="{" +
			 				"\"merId\":\""+str1[0]+"\"," +
			 				"\"actId\":\""+str1[1]+"\"}";	
						 
						 YouHuiShangXian_TeHui4_4.requeststr1=HttpConnection2.request(json1,"A0010");
				
						 Jsonstr = "{\"merId\":\""+CGXYHTakePhotoActivity.merId+"\",\"imgType\":\"00\"}";
							String jsont=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0018"), Jsonstr);
							try
							{
								JSONObject demoJson1 = new JSONObject(jsont);
								if(demoJson1.getString("rspCode").equals("00"))
								{
									String[] tempstr=new String[3];
									tempstr[0]=demoJson1.getString("imgStream"); 
									byte[]pic=tempstr[0].getBytes();
									byte[]  pic2=Base64.decode(pic,0);
									YouHuiShangXian_TeHui4_4.bm=BitmapFactory.decodeByteArray(pic2, 0, pic2.length);
						       	}
							}catch(Exception e)
							{
								techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui4_4:Request()" + e.getMessage());
							}
						 mDialog.dismiss();	
				saveData();
				saveData2();
				intent.setClass(CGXYHTakePhotoActivity.this, YouHuiShangXian_TeHui4_4.class);
    			CGXYHTakePhotoActivity.this.startActivity(intent);
    			CGXYHTakePhotoActivity.this.finish();
    			overridePendingTransition(R.anim.rightin, R.anim.rightout);
				}}.start();		
    			
    			break;
    		case R.id.button4:
				
    			if(validate()){
//    			if(huihui==2)
//    			{
    				saveData();
        			saveData1();
        			saveData2();
    				String barcodeno=merId1;
    				DB.sendinsert(CGXYHTakePhotoActivity.this, new String[] {
    			    			MyDatabaseHelper.SEND_BarcodeNo,
    							MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
    							new String[] { barcodeno, "1", "0" });
    				
    				Builder b = new AlertDialog.Builder(CGXYHTakePhotoActivity.this);
					b.setTitle("提示");
					b.setMessage("提交成功");// 设置信息
					b.setCancelable(false);

					b.setPositiveButton("确定", new DialogInterface.OnClickListener() {// 设置监听事件
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
									Intent intent = new Intent();
									intent.setClass(
											CGXYHTakePhotoActivity.this,
											TuanDuiShangHuActivity.class);
									CGXYHTakePhotoActivity.this
											.startActivity(intent);
									techown.shanghu.https.OperateLog.Instance().WriteLog("商户团队草稿箱特惠上线提交成功！");
									CGXYHTakePhotoActivity.this.finish();
								}
							});
					if(alterdlg==null||!alterdlg.isShowing()){
						alterdlg = b.create();// 创建对话框
						alterdlg.show();// 显示对话框
						alterdlg.setCanceledOnTouchOutside(false);
						alterdlg.setCancelable(false);// 设置点击Dialog外部任意区域关
					}
    				
//    				new AlertDialog.Builder(CGXYHTakePhotoActivity.this).setTitle("提示").setMessage("提交成功")
//    				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//    				
//    					public void onClick(DialogInterface arg0, int arg1) {
//    						// TODO Auto-generated method stub
//    						Intent intent = new Intent();
//    						intent.setClass(CGXYHTakePhotoActivity.this, TuanDuiShangHuActivity.class);
//    						CGXYHTakePhotoActivity.this.startActivity(intent);
//    						CGXYHTakePhotoActivity.this.finish();
//    					}
//    				}).show();
    				techown.shanghu.https.Log
					.Instance()
					.WriteLog(
							"团队：草稿箱进入发件箱，特惠上线进件 "+TuanDuiShangHuActivity.tmpId3
									);
    			}

    			break;
    		case R.id.chaogaoxiang:
    			saveData();
    			saveData2();
    			log.ExitApp4(CGXYHTakePhotoActivity.this);
//    			CGXYHTakePhotoActivity.this.finish();
    			techown.shanghu.https.Log
				.Instance()
				.WriteLog(
						"团队：特惠上线进入草稿箱 "+TuanDuiShangHuActivity.tmpId3
								);
    			break;
    			
    		case R.id.takephoto1:
    			
	    			currentPicName=merId+"_online"+"_syt.jpg";
//	    			if (TuanDuiShangHuActivity.device == 0) {
//						Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//						i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//						i.putExtra("cameraid",0);
//						i.putExtra("width",640);
//						i.putExtra("height",480);
//						startActivityForResult(i, 1);
//					} else if (TuanDuiShangHuActivity.device == 1) {
//						Intent i = new Intent();
//			    		i.setClass(CGXYHTakePhotoActivity.this, CaptureImage.class);
//			    		i.putExtra("cameraDir",0);
//						i.putExtra("photoWdith",640);
//						i.putExtra("photoHeight",480);
//						startActivityForResult(i, 1);
						mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
						request = 1;
//					}
    			break;
    		case R.id.takephoto2:
    			
	    			currentPicName=merId+"_online"+"_mt.jpg";
//	    			if (TuanDuiShangHuActivity.device == 0) {
//						Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//						i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//						i.putExtra("cameraid",0);
//						i.putExtra("width",640);
//						i.putExtra("height",480);
//						startActivityForResult(i, 2);
//					} else if (TuanDuiShangHuActivity.device == 1) {
//						Intent i = new Intent();
//			    		i.setClass(CGXYHTakePhotoActivity.this, CaptureImage.class);
//			    		i.putExtra("cameraDir",0);
//						i.putExtra("photoWdith",640);
//						i.putExtra("photoHeight",480);
//						startActivityForResult(i, 2);
						mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
						request = 2;
//					}
    			break;

    		case R.id.takephoto3:
    			if(deletenum.size()>0)
    			{
    				currentPicName=merId+"_online"+"_"+deletenum.get(0)+"_qt.jpg";	
    				deletenum.remove(0);
    			}
    			else
    			{
    				currentPicName=merId+"_online"+"_"+qtnum+"_qt.jpg";	
    			}
    		
//    			if (TuanDuiShangHuActivity.device == 0) {
//					Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//					i.putExtra("cameraid",0);
//					i.putExtra("width",640);
//					i.putExtra("height",480);
//					startActivityForResult(i, 3);
//				} else if (TuanDuiShangHuActivity.device == 1) {
//					Intent i = new Intent();
//		    		i.setClass(CGXYHTakePhotoActivity.this, CaptureImage.class);
//		    		i.putExtra("cameraDir",0);
//					i.putExtra("photoWdith",640);
//					i.putExtra("photoHeight",480);
//					startActivityForResult(i, 3);
					mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
					request = 3;
//				}
    			break;
    			default:
    				break;
    		}
    		
    		//得到当前点击button的名字和行号    		  		   	  		
    	}
    }
   
    
    public void saveFile(String filepath, String fileName,int num){
		try
		{
			techown.shanghu.https.Log.Instance().WriteLog("当前图片大小===="+fl.length());
			System.out.println("文件大小==="+fl.length());
			if(realBitmap!=null){
			
			FileUtils fileUtils = new FileUtils();
			
			fileUtils.createSDDir(filepath);
			fileUtils.deleteSDFILE(filepath+fileName);
			File fPicture = fileUtils.createSDFILE(filepath+fileName);
		
			FileOutputStream fout=new FileOutputStream(fPicture);
			BufferedOutputStream bos = new BufferedOutputStream(fout);
			realBitmap.compress
			(
				Bitmap.CompressFormat.JPEG,   //图片格式
				100, 						   //品质0-100
				bos						   //使用的输出流
			);
			bos.flush();
			bos.close();

			switch(num)
			{
			case 1:
				takephoto1.setText("收银台卡/跳跳卡拍摄(已拍)");
    			takephoto1.setEnabled(false);
    			huihui++;
				break;
			case 2:
				takephoto2.setText("海报/宣传贴拍摄(已拍)");
    			takephoto2.setEnabled(false);
    			huihui++;
				break;

			case 3:
				qtnum++;
				takephoto3.setText("门贴/其他拍照(已拍)"+qtnum);
				if(qtnum==5)
				{					
	    			takephoto3.setEnabled(false);
				}
				break;
				default:
					break;
			}
			getBitmap(path1);
			ia.mbit=picbim;
			ia.mItemList=picname;
			Message msg = new Message();   
	        msg.what =2;   
	        handlerphoto.sendMessage(msg); 
			}
		}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity:"+e.getMessage());
		}
		
	}

	public void deletePhoto(String photoid){
		
		 File fipic=new File("/sdcard/"+path1+photoid);	        		        	
         if (fipic.exists())
 		{       	
        	 fipic.delete();
 		}
         if(photoid.split("_").length==3&&photoid.split("_")[2].equals("syt.jpg"))
	        {
				takephoto1.setText("收银台卡/跳跳卡拍摄(必选)");
				takephoto1.setEnabled(true);
				huihui--;
	        }
	        else if(photoid.split("_").length==3&&photoid.split("_")[2].equals("mt.jpg"))
	        {
	        	takephoto2.setText("海报/宣传贴拍摄(必选)");
	        	takephoto2.setEnabled(true);
	        	huihui--;
	        }

	        else if(photoid.split("_").length==4&&photoid.split("_")[3].equals("qt.jpg"))
	        {   
	        	qtnum--;
	        	deletenum.add(photoid.split("_")[2]);
//	        	isdelete=true;
	        	if(qtnum==0)
	        	{
	        		takephoto3.setText("其他附件拍照");
	        	}else
	        	{
	        		takephoto3.setText("门贴/其他拍照(已拍"+qtnum+")");
	        	}
	        	takephoto3.setEnabled(true);
	        	
	        }	
         getBitmap(path1);
			ia.mbit=picbim;
			ia.mItemList=picname;
			Message msg = new Message();   
	        msg.what =2;   
	        handlerphoto.sendMessage(msg); 
		
	}
    

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	if(resultCode==RESULT_OK)
    	{
		if(requestCode == 1){
			takePhoto(1);
			
		}
		else if(requestCode == 2){
			takePhoto(2);
		}
		else if(requestCode == 3)
		{
			takePhoto(3);	
		}
		else if(requestCode==4)
		{
			takePhoto(4);	
		}
		else if(requestCode==5)
		{
			takePhoto(5);	
		}
    	}
    }
    Bitmap realBitmap;
    File fl;
    public void takePhoto(int num){
    	
    	try{
    		if(TuanDuiShangHuActivity.device == 0){
				Bitmap tmpLT = BitmapFactory
						.decodeFile("/mnt/sdcard/tmpPhoto/");
				fl = new File("/mnt/sdcard/tmpPhoto/");
				if (tmpLT != null) {
						bitmap= ResizeBitmap(tmpLT, 300);
				}
				
				realBitmap = BitmapFactory.decodeFile("/mnt/sdcard/tmpPhoto/");
				if (num == 4) {
					// createDir(path);
					saveFile(path1, currentPhotoId, 4);
				} else {
					// createDir(path);
					saveFile(path1, currentPicName, num);
				}
					
				//删除tmpPhoto
				FileUtils fileUtils = new FileUtils();
	    		if(fileUtils.isFileExist("tmpPhoto")){
	    			fileUtils.deleteSDFILE("tmpPhoto");
	    		}
				
			}else if(TuanDuiShangHuActivity.device == 1){
				String tmpPhotoName = "tmpPhoto.jpg";
				Bitmap tmp = BitmapFactory.decodeFile("/mnt/sdcard/"+tmpPhotoName);
				fl = new File("/mnt/sdcard/"+tmpPhotoName);
				if (tmp != null) {
						bitmap= ResizeBitmap(tmp, 300);
				}
				
				
				realBitmap = BitmapFactory.decodeFile("/mnt/sdcard/"+tmpPhotoName);
				if (num == 4) {
					// createDir(path);
					saveFile(path1, currentPhotoId, 4);
				} else {
					// createDir(path);
					saveFile(path1, currentPicName, num);
				}
					
				//删除tmpPhoto
				FileUtils fileUtils = new FileUtils();
	    		if(fileUtils.isFileExist(tmpPhotoName)){
	    			fileUtils.deleteSDFILE(tmpPhotoName);
	    		}
			
			}	
    	}catch(Exception e){
    		techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity:"+e.getMessage());
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
		 public String[] mItemList; //修饰符不能是private
		 public Bitmap[]mbit;
		public Context mContext;

		 
		 public ImageAdapter(Context con,String[]itemList,Bitmap[]bit)
		 {
			 mItemList=itemList;
			 mbit=bit;
			 mContext=con;
		 }
	        public int getCount() {
	        	if(mItemList==null)
	        	{
	        		return 0;
	        	}
	        	else
	        	{
	        		 return  mItemList.length;	
	        	}
	           
	        }	      
	        public Object getItem(int position) {
	            return position;
	        }

	        public long getItemId(int position) {
	            return position;
	        }

	        public View getView(int position, View convertView, ViewGroup parent) {
	            LayoutInflater inflater = LayoutInflater.from(CGXYHTakePhotoActivity.this);
	            View view = inflater.inflate(R.layout.img_list_item, null);
	            	           
	            TextView photoName = (TextView) view.findViewById(R.id.photoName);	
	            if(mItemList[position].split("_").length==3&&mItemList[position].split("_")[2].equals("syt.jpg"))
	            {
	            	 photoName.setText("收银台卡/跳跳卡拍摄");
	            }
	            else if(mItemList[position].split("_").length==3&&mItemList[position].split("_")[2].equals("mt.jpg"))
	            {
	            	photoName.setText("海报/宣传贴拍摄");
	            }

	            else if(mItemList[position].split("_").length==4&&mItemList[position].split("_")[3].equals("qt.jpg"))
	            {            	
	            	photoName.setText("其他拍照"+String.valueOf(Integer.parseInt(mItemList[position].split("_")[2])+1));
	            }
	            ImageView iv=(ImageView)view.findViewById(R.id.photo);
	            iv.setImageBitmap(mbit[position]);		           
	            return view;
	        }

	    }

   
    public void saveData() {
    	
    	String nameone=new String();
    	String nametwo=new String();
    	
    	String namef=new String();
    	StringBuilder sbthree=new StringBuilder();
    	if(picname!=null&&picname.length>0){

    	for(int i=0;i<picname.length;i++)
    	{
    		 if(picname[i].split("_").length==3&&picname[i].split("_")[2].equals("syt.jpg"))
 	        {
    			 nameone=picname[i]; 
 	        }
 	        else if(picname[i].split("_").length==3&&picname[i].split("_")[2].equals("mt.jpg"))
 	        {
 	        	nametwo=picname[i]; 
 	        }

 	        else if(picname[i].split("_").length==4&&picname[i].split("_")[3].equals("qt.jpg"))
 	        {   
 	        	sbthree.append(picname[i]+";");
 	        }	
    	}
    	if(sbthree!=null&&sbthree.length()>0)
    	{
    		namef=sbthree.delete(sbthree.length()-1, sbthree.length()).toString();
    	}
    	
    	DB.upload4(CGXYHTakePhotoActivity.this,new String[]{"AttLicense","AttAccredit","AttOthers"},new String[]{nameone,nametwo,namef}, TuanDuiShangHuActivity.tmpId3);
    
    	
//    	DBUtil.upload4(YouHuiTakePhotoActivity.this, clomname, clomstr, YouHuiShangXian_TeHui.num);
		
    	}

    }
    
    public void saveData1() {
    	
    	
    	String[] clomname = new String[] {"DCL","LX","TIME"};
    	String[] clomstr = new String[10];
    	clomstr[0] = "212";
    	clomstr[1] = "特惠上线";
    	clomstr[2] = time;
    	DB.upload4(CGXYHTakePhotoActivity.this, clomname, clomstr, TuanDuiShangHuActivity.tmpId3);
  
    }
    
    public void getBitmap(String path)
	{
    	try
    	{
    		String test[];
   		 File file=new File("/sdcard/"+path);
   		 test=file.list();
   		 picname=new String[test.length];
   		 picbim=new Bitmap[test.length];
   		BitmapFactory.Options options = new BitmapFactory.Options();
           options.inSampleSize =2;
           for(int i=0;i<test.length;i++)
           {
           	picname[i]=test[i].trim();
           	picbim[i]=BitmapFactory.decodeFile("/sdcard/"+path+test[i].trim(),options);       
           }      
    	}catch(Exception e)
    	{techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity:"+e.getMessage());
    		picname=null;
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
    
    
    
    
    
    
    public  void saveData2(Context con) {
    	
    	
    	String[] clomname = new String[] {"DCL","LX"};
    	String[] clomstr = new String[10];
    	clomstr[0] = "0";
    	clomstr[1] = "特惠上线";
    	
    	
    	DB.upload4(con, clomname, clomstr, TuanDuiShangHuActivity.tmpId3);
  
    }
    
    public void saveData2() {
		String[] clomname = new String[] {"Tkint","Qtint","Mtint"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textview1.getText().toString();
		clomstr[1] = textview2.getText().toString();
		clomstr[2] = textview3.getText().toString();

		
		DB.upload4(CGXYHTakePhotoActivity.this, clomname, clomstr, TuanDuiShangHuActivity.tmpId3);
	}
 protected boolean validate() {
	 
    	
		if(textview1.getText().toString().equals("")){
			log.Toast(CGXYHTakePhotoActivity.this, "收银台卡/跳跳卡数量不可为空");
    		return false;
    	}

    	if(textview1.getText().toString().equals("0")){
    		log.Toast(CGXYHTakePhotoActivity.this, "收银台卡/跳跳卡数量不可为0！");
    		return false;
    	}
    	if(textview2.getText().toString().equals("")){
    		log.Toast(CGXYHTakePhotoActivity.this, "海报/宣传贴数量不可为空");
    		return false;
    	}

    	if(textview2.getText().toString().equals("0")){
    		log.Toast(CGXYHTakePhotoActivity.this, "海报/宣传贴数量不可为0！");
    		return false;
    	}

    	if(textview3.getText().toString().equals("0")){
    		log.Toast(CGXYHTakePhotoActivity.this, "门贴/其他数量不可为0！");
    		return false;
    	}
    	if(!takephoto1.getText().toString().equals("收银台卡/跳跳卡拍摄(已拍)")){
    		log.Toast(CGXYHTakePhotoActivity.this, "请拍摄收银台卡/跳跳卡！");
    		return false;
    	}
    	if(!takephoto2.getText().toString().equals("海报/宣传贴拍摄(已拍)")){
    		log.Toast(CGXYHTakePhotoActivity.this, "请拍摄海报/宣传贴！");
    		return false;
    	}
    	if(!log.isNumber(textview1.getText().toString())){
	 		log.Toast(CGXYHTakePhotoActivity.this, "数量只可为数字且首位不可为0！");
	 		return false;
	 	}
    	if(!log.isNumber(textview2.getText().toString())){
	 		log.Toast(CGXYHTakePhotoActivity.this, "数量只可为数字且首位不可为0！");
	 		return false;
	 	}
    	if(!textview3.getText().toString().equals("")){
		 
	
			 if(!log.isNumber(textview3.getText().toString())){
			 		log.Toast(CGXYHTakePhotoActivity.this, "数量只可为数字且首位不可为0！");
			 		return false;
	 	}}
		return true;
    	
    }
    
 	String AttLicense;
	String AttContract;
	String Mtint;
    String Cztint;
    String Tkint;
	String actId;
	String merId1;
	public  void tiaoxingma(){
		SQLiteDatabase db=null;
		Cursor cursor=null;
		try
		{
			MyDatabaseHelper myHelper = new MyDatabaseHelper(CGXYHTakePhotoActivity.this, "techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from youhushangxian where id==?",new String[] { TuanDuiShangHuActivity.tmpId3 });
			while (cursor.moveToNext()) {
				try {
					
					AttLicense = des.jieMI(cursor.getString(cursor.getColumnIndex("AttLicense")));
					AttContract = des.jieMI(cursor.getString(cursor.getColumnIndex("AttAccredit")));
					Tkint = des.jieMI(cursor.getString(cursor.getColumnIndex("Tkint")));
					Cztint =des.jieMI(cursor.getString(cursor.getColumnIndex("Qtint"))) ;
					Mtint = des.jieMI(cursor.getString(cursor.getColumnIndex("Mtint")));
					merId = des.jieMI(cursor.getString(cursor.getColumnIndex("merId")));
					actId = des.jieMI(cursor.getString(cursor.getColumnIndex("actId")));
					merId1 = des.jieMI(cursor.getString(cursor.getColumnIndex("merId2")));
				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity:"+e.getMessage());
				}
				db.close();
				cursor.close();
				
			}	
		}
		catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队CGXYHTakePhotoActivity:"+e.getMessage());	
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
			log.ExitApp(CGXYHTakePhotoActivity.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}