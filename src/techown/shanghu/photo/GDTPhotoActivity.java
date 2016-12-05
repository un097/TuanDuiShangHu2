package techown.shanghu.photo;
/*
 * �ݸ��� ���Ź���
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import techown.shanghu.DBUtil;
import techown.shanghu.EditDialog;
import techown.shanghu.GongDanWeiHu1;
import techown.shanghu.GongDanWeiHu3_3;
import techown.shanghu.MyDatabaseHelper;
import techown.shanghu.PadUtiltiy;
import techown.shanghu.R;
import techown.shanghu.SpinnerAdapter;
import techown.shanghu.TuanDuiShangHuActivity;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.GetDate;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
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

public class GDTPhotoActivity extends Activity {
    /** Called when the activity is first created. */
	private Button button7,takephoto_before,takephoto_next,takephoto1,takephoto2,takephoto4,takephoto5;
	private Dialog mDialog;
	EditDialog log=new EditDialog();
	DES des = new DES();
	DBUtil DB=new DBUtil();
	AlertDialog alterdlg = null;
	
	String[]picTitle = {"�ŵ���Ƭ","����","����","������","̨��","����̨��","Xչ��","������","��������/����","����POS","POS����POSСƱ","�һ�����","�̻���Ա��Ӱ"};
	public int y =0;//�ж����һ����������û��0 ��û�ģ�1������
	int addCnt=0;//���ͼƬ������
	int firstpic=0;//��һ��ͼƬ��û�� 0 ��û�ģ�1�����ģ�2�����Ĳ����ǴӲݸ������һ���淵����һ����
	String str= ",";//�洢���ĵ����ƣ��жϲ���������
	public TextView choosePic,choosePic_1;
	public Button addPic;
	Map<String, String> picMap,titleMap;
	int back =0 ;//��ͨ����ʱ ���ȡ����ťδ����ʱ�Ŀ���
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
				System.out.println("currentphotoID->"+currentPhotoId);
	        	break;
	        	default:
	        		break;
	        }   
	    }   
	};   
	Bitmap bitmap;

	ListView imageList;
	
	int huihui=0;
	String currentPhotoId;
	String currentPicName;
	
	public static String path ;
	int qtnum;
	String deletenum;
	boolean isdelete;
	String[]picname;
	Bitmap[]picbim;
	ImageAdapter ia;
	PadUtiltiy mPadUtiltiy;
	static String time;
	public static	int request = 0;/*1�����գ�6������ */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.gongdanweihu4);
		time=GetDate.getDate().toString();
		tiaoxingma();
		mPadUtiltiy = new PadUtiltiy(this);
		mDialog = new AlertDialog.Builder(GDTPhotoActivity.this).create();
		//panel = new Panel(this);
		takephoto_before= (Button)findViewById(R.id.button5);
		takephoto_before.setOnClickListener(new testBtnListener());
		
		takephoto_next= (Button)findViewById(R.id.button6);
		takephoto_next.setOnClickListener(new testBtnListener());
		
		button7= (Button)findViewById(R.id.button7);
		button7.setOnClickListener(new testBtnListener());
		
		choosePic = (TextView) findViewById(R.id.choosePicTxt);
		addPic = (Button) findViewById(R.id.addPicture);
		picMap = new HashMap<String, String>();
		picMap.put( "�ŵ���Ƭ","md");
		picMap.put( "����","hb");
		picMap.put( "����","mt");
		picMap.put( "������","ttk");
		picMap.put( "̨��","tk");
		picMap.put( "����̨��","syt");
		picMap.put( "Xչ��","x");
		picMap.put( "������","xct");
		picMap.put( "��������/����","rp");
		picMap.put( "����POS","jfpos");
		picMap.put( "POS����POSСƱ","posxp");
		picMap.put( "�һ�����","dhcj");
		picMap.put( "�̻���Ա��Ӱ","ryhy");
		titleMap = new HashMap<String, String>();
		titleMap.put("md", "�ŵ���Ƭ");
		titleMap.put("hb", "����");
		titleMap.put("mt", "����");
		titleMap.put("ttk", "������");
		titleMap.put("tk", "̨��");
		titleMap.put("syt", "����̨��");
		titleMap.put("x", "Xչ��");
		titleMap.put("xct", "������");
		titleMap.put("rp", "��������/����");
		titleMap.put("jfpos", "����POS");
		titleMap.put("posxp", "POS����POSСƱ");
		titleMap.put("dhcj", "�һ�����");
		titleMap.put("ryhy", "�̻���Ա��Ӱ");
		
		getData();
		
		choosePic.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				case MotionEvent.ACTION_UP:
//					File hb=new File(Environment.getExternalStorageDirectory() + "/"+path+GongDanWeiHu1.Id+"_task_"+picMap.get(choosePic.getText().toString()));
					if(choosePic.getText().toString()==null||choosePic.getText().toString().length()==0){
						dialog(GDTPhotoActivity.this, choosePic, "ѡ��ͼƬ", picTitle);
					}
					break;
				default:break;
				}
				return false;
			}
		});
		final LinearLayout choosePicLayout = (LinearLayout) findViewById(R.id.addChoosePicLayout);

		//���������������
		addPic.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(addCnt>=13){
					addPic.setClickable(false);
					log.Toast(GDTPhotoActivity.this, "����������������13��");
					return false;
				}
				switch(event.getAction()){
				case MotionEvent.ACTION_UP:
					y=0;					
					addPic.setClickable(false);
					
					final View view = LayoutInflater.from(GDTPhotoActivity.this).inflate(
							R.layout.add_wenxuan_pic, null);
					view.findViewById(R.id.choosePicTxt_add).setOnTouchListener(new OnTouchListener() {
						
						public boolean onTouch(View v, MotionEvent event) {
							// TODO Auto-generated method stub
							switch (event.getAction()){
							case MotionEvent.ACTION_UP:
								
								choosePic_1=(TextView)view.findViewById(R.id.choosePicTxt_add);
								
//								File hb=new File(Environment.getExternalStorageDirectory() + "/"+path+GongDanWeiHu1.Id+"_task_"+picMap.get(choosePic_1.getText().toString()));
								//�����ͼƬ˵�������㣬���Ӧ�Ĳ����������ټ������㣬�������
								
								if(choosePic_1.getText().toString()==null||choosePic_1.getText().toString().length()==0){
								
									dialog(GDTPhotoActivity.this, (TextView)view.findViewById(R.id.choosePicTxt_add), "ѡ��ͼƬ", picTitle);
								}
								break;
							
							}
							return false;
						}
					});
					choosePicLayout.addView(view);
					//ɾ����Ӧ����������
					view.findViewById(R.id.deltetPicture).setOnTouchListener(new OnTouchListener() {
						
						public boolean onTouch(View v, MotionEvent event) {
							// TODO Auto-generated method stub
							switch (event.getAction()){
							case MotionEvent.ACTION_UP:
								
								DeletePhoto(view,choosePicLayout);
								
								break;
							
							}
							return false;
							
						}
					});
					
	    			
					break;
				default:break;
				}
				return false;
			}
		});
		
		takephoto1= (Button)findViewById(R.id.takephoto1);

			takephoto1.setOnClickListener(new testBtnListener());
//		}
		
		
		takephoto2= (Button)findViewById(R.id.takephoto2);

			takephoto2.setOnClickListener(new testBtnListener());

				
		takephoto4= (Button)findViewById(R.id.takephoto4);
		takephoto4.setOnClickListener(new testBtnListener());
		
		takephoto5= (Button)findViewById(R.id.takephoto5);
		takephoto5.setOnClickListener(new testBtnListener());
		
		imageList = (ListView)findViewById(R.id.imageList);
		path = "TJB/TuanDui/gongdan/"+TuanDuiShangHuActivity.tmpId5+"/pic/";
		getBitmap(path);
		if(picname!=null)
		{
			for(int i=0;i<picname.length;i++)
			{
				if(picname[i].split("_").length==3&&picname[i].split("_")[2].equals("tk.jpg"))
		        {
					takephoto1.setText("����̨��/����������(����)");
					takephoto1.setEnabled(false);
		        }
		        else if(picname[i].split("_").length==3&&picname[i].split("_")[2].equals("yt.jpg"))
		        {
		        	takephoto2.setText("����/����������(����)");
		        	takephoto2.setEnabled(false);
		        }

		        else if(picname[i].split("_").length==3&&picname[i].split("_")[2].equals("zj.jpg"))
		        {
		        	takephoto4.setText("������/Xչ������(����)");
		        	takephoto4.setEnabled(false);
		        }
		        else if(picname[i].split("_").length==4&&picname[i].split("_")[3].equals("mt.jpg"))
		        {
		        	takephoto5.setText("����/��������(����)");
		        	takephoto5.setEnabled(false);
		        }
		        
		        
			}
		}
		
		
		ia=new ImageAdapter(GDTPhotoActivity.this,picname,picbim);
		imageList.setAdapter(ia);            	
		imageList.setOnItemClickListener(
				new OnItemClickListener()
				{
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
					GDTPictureDialog editDlg = new GDTPictureDialog(GDTPhotoActivity.this, ia.mbit[arg2],ia.mItemList[arg2]);
					editDlg.showPicDlg();
					}
		
				}
		);
    }
    class testBtnListener implements OnClickListener{
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    		int id = v.getId();
    		Intent intent = new Intent();
    		switch(id){
    		case R.id.button7:
    			uploadname();
    			intent.setClass(GDTPhotoActivity.this, GongDanWeiHu3_3.class);
    			GDTPhotoActivity.this.startActivity(intent);
    			GDTPhotoActivity.this.finish();
    			overridePendingTransition(R.anim.rightin, R.anim.rightout);
    			break;
    		case R.id.button5:
    			uploadname();
				log.ExitApp6(GDTPhotoActivity.this);
				techown.shanghu.https.Log.Instance().WriteLog("���Ź���idΪ"+actId1+"numΪ"+TuanDuiShangHuActivity.tmpId5+"�ļ��ٴν���ݸ���");
    			break;
    		case R.id.button6:
    			
    			if(choosePic.getText().toString()!=null&&choosePic.getText().toString().length()>0){
    				uploadname();
        			saveData();
    				String barcodeno=actId;
    				DB.sendinsert(GDTPhotoActivity.this, new String[] {
    			    			MyDatabaseHelper.SEND_BarcodeNo,
    							MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
    							new String[] { barcodeno, "1", "0" });
    				
    				
    				Builder b = new AlertDialog.Builder(GDTPhotoActivity.this);
					b.setTitle("��ʾ");
					b.setMessage("�ύ�ɹ�");// ������Ϣ
					b.setCancelable(false);

					b.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {// ���ü����¼�
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
									Intent intent = new Intent();
									intent.setClass(
											GDTPhotoActivity.this,
											TuanDuiShangHuActivity.class);
									GDTPhotoActivity.this
											.startActivity(intent);
									techown.shanghu.https.Log.Instance().WriteLog("�ݸ������Ź���idΪ"+actId1+"numΪ"+TuanDuiShangHuActivity.tmpId5+"�ύ����");

									techown.shanghu.https.OperateLog.Instance().WriteLog("�̻��ŶӲݸ������Ź����ύ�ɹ���");
									GDTPhotoActivity.this.finish();
								}
							});
					if(alterdlg==null||!alterdlg.isShowing()){
						alterdlg = b.create();// �����Ի���
						alterdlg.show();// ��ʾ�Ի���
						alterdlg.setCanceledOnTouchOutside(false);
						alterdlg.setCancelable(false);// ���õ��Dialog�ⲿ���������
					}
    				techown.shanghu.https.Log
					.Instance()
					.WriteLog(
							"�Ŷӣ��ݸ�����뷢���䣬���Ź������� "+TuanDuiShangHuActivity.tmpId5
									);
    			}else{
    				log.Toast(GDTPhotoActivity.this, "ͼƬδ���㲻���ύ��������ͼƬ��");
    			}
    			break;
				
    			
    		case R.id.takephoto1:
    			currentPicName=actId1+"_task_tk.jpg";
//    			if (TuanDuiShangHuActivity.device == 0) {
//					Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//					i.putExtra("cameraid",0);
//					i.putExtra("width",640);
//					i.putExtra("height",480);
//					startActivityForResult(i, 1);
//				} else if (TuanDuiShangHuActivity.device == 1) {
					mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
					request = 1;
//				}
    			break;
    		case R.id.takephoto2:
    			currentPicName=actId1+"_task_yt.jpg";
//    			if (TuanDuiShangHuActivity.device == 0) {
//					Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//					i.putExtra("cameraid",0);
//					i.putExtra("width",640);
//					i.putExtra("height",480);
//					startActivityForResult(i, 2);
//				} else if (TuanDuiShangHuActivity.device == 1) {
					mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
					request = 2;
//				}
    			
    			break;

    		case R.id.takephoto4:
    			currentPicName=actId1+"_task_zj.jpg";	
//    			if (TuanDuiShangHuActivity.device == 0) {
//					Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//					i.putExtra("cameraid",0);
//					i.putExtra("width",640);
//					i.putExtra("height",480);
//					startActivityForResult(i, 6);
//				} else if (TuanDuiShangHuActivity.device == 1) {
					mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
					request = 6;
//				}
    			break;
    		case R.id.takephoto5:
    			currentPicName=actId1+"_task_0_mt.jpg";	
//    			if (TuanDuiShangHuActivity.device == 0) {
//					Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//					i.putExtra("cameraid",0);
//					i.putExtra("width",640);
//					i.putExtra("height",480);
//					startActivityForResult(i, 5);
//				} else if (TuanDuiShangHuActivity.device == 1) {
					mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
					request = 5;
//				}
    			break;
    		default:
        		break;
    		}
    		
    		//�õ���ǰ���button�����ֺ��к�    		  		   	  		
    	}
    }
   
    
    public void saveFile(String filepath, String fileName,int num){
		try
		{
			System.out.println("��ǰͼƬ��С===="+fl.length());
			techown.shanghu.https.Log.Instance().WriteLog("��ǰͼƬ��С===="+fl.length());
			if(realBitmap!=null){
			
			FileUtils fileUtils = new FileUtils();
			
			fileUtils.createSDDir(filepath);
			fileUtils.deleteSDFILE(filepath+fileName);
			File fPicture = fileUtils.createSDFILE(filepath+fileName);
		
			FileOutputStream fout=new FileOutputStream(fPicture);
			BufferedOutputStream bos = new BufferedOutputStream(fout);
			realBitmap.compress
			(
				Bitmap.CompressFormat.JPEG,   //ͼƬ��ʽ
				100, 						   //Ʒ��0-100
				bos						   //ʹ�õ������
			);
			bos.flush();
			bos.close();
			
			getBitmap(path);
			ia.mbit=picbim;
			ia.mItemList=picname;
			Message msg = new Message();   
	        msg.what =2;   
	        handlerphoto.sendMessage(msg); 
			}
		}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�GDTPhotoActivity����"+e.getMessage());	
		}
		
	}

	public void deletePhoto(String photoid){
		
		 File fipic=new File("/sdcard/"+path+photoid);	        		        	
         if (fipic.exists())
 		{       	
        	 fipic.delete();
 		}
         if(photoid.split("_").length==3&&photoid.split("_")[2].equals("tk.jpg"))
	        {
				takephoto1.setText("����̨��/����������(����)");
				takephoto1.setEnabled(true);
				huihui--;
	        }
	        else if(photoid.split("_").length==3&&photoid.split("_")[2].equals("yt.jpg"))
	        {
	        	takephoto2.setText("����/����������(����)");
	        	takephoto2.setEnabled(true);
	        	huihui--;
	        }

	        else if(photoid.split("_").length==3&&photoid.split("_")[2].equals("zj.jpg"))
	        {   
	        	takephoto4.setText("������/Xչ������(����)");
	        	takephoto4.setEnabled(true);
	        }	
	        else if(photoid.split("_").length==4&&photoid.split("_")[3].equals("mt.jpg"))
	        {   
	        	takephoto5.setText("����/��������(����)");
	        	takephoto5.setEnabled(true);
	        }	
         getBitmap(path);
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
    		if(requestCode == 0){
    			takePhoto(1);
    		}
			if(requestCode == 1){
				takePhoto(1);
			}
			else if(requestCode == 2){
				takePhoto(2);
			}
	
			else if(requestCode==4)
			{
				takePhoto(4);	
			}
			else if(requestCode==5)
			{
				takePhoto(5);	
			}
			else if(requestCode==6)
			{
				takePhoto(6);	
			}
    	}	
    }
    Bitmap realBitmap;
    File fl;
    public void takePhoto(int num){
    	
    	try{
//    		if(TuanDuiShangHuActivity.device == 0){
//				Bitmap tmpLT = BitmapFactory
//						.decodeFile("/mnt/sdcard/tmpPhoto/");
//				fl = new File("/mnt/sdcard/tmpPhoto/");
//				if (tmpLT != null) {
//						bitmap= ResizeBitmap(tmpLT, 300);
//						//���ͼƬ������Ӱ�ť���ò��Ҵ洢����ͼƬ���ƽ�ֹ��������
//						back=1;
//						y=1;
//						firstpic=1;
//						
//						if(num==1){
//							techown.shanghu.https.Log.Instance().WriteLog("�ݸ������Ź�������ͼƬ����Ϊ"+currentPicName);
//							str+=currentPicName+",";
//							System.out.println("str-->"+str);
//							addCnt++;
//							addPic.setClickable(true);
//						}					
//				}else{
//					
//				}
//				
//				realBitmap = BitmapFactory.decodeFile("/mnt/sdcard/tmpPhoto/");
//				if (num == 4) {
//					// createDir(path);
//					techown.shanghu.https.Log.Instance().WriteLog("�ݸ������Ź�������ͼƬ������������Ϊ"+currentPhotoId);
//					saveFile(path, currentPhotoId, 4);
//				} else {
//					// createDir(path);
//					saveFile(path, currentPicName, num);
//				}
//					
//				//ɾ��tmpPhoto
//				FileUtils fileUtils = new FileUtils();
//	    		if(fileUtils.isFileExist("tmpPhoto")){
//	    			fileUtils.deleteSDFILE("tmpPhoto");
//	    		}
//				
//			}else if(TuanDuiShangHuActivity.device == 1){
				String tmpPhotoName = "tmpPhoto.jpg";
				Bitmap tmp = BitmapFactory.decodeFile("/mnt/sdcard/"+tmpPhotoName);
//				fl = new File("/mnt/sdcard/aaa/bbb/ccc/"+tmpPhotoName);
				fl = new File("/mnt/sdcard/"+tmpPhotoName);
				if (tmp != null) {
						bitmap= ResizeBitmap(tmp, 300);
						y=1;
						firstpic=1;
						
						if(num==1){
							techown.shanghu.https.Log.Instance().WriteLog("�ݸ������Ź�������ͼƬ����Ϊ"+currentPicName);
							str+=currentPicName+",";
							System.out.println("str-->"+str);
							addCnt++;
							addPic.setClickable(true);
						}
				}else{	
					if(firstpic==0){
						choosePic.setText("");
					}else if(firstpic==1){
						if(num!=4){
							choosePic_1.setText("");
						}
					}else if(firstpic==2){
						//��һ�δӲݸ�����ߴ���һ���淵�ؽ���ҳ�� �����½�������������Ϊ1���Ա��´�û�����㷵�����һ����������Ϊ��
						firstpic=1;
					}
//				}
				
				
				realBitmap = BitmapFactory.decodeFile("/mnt/sdcard/"+tmpPhotoName);
				if (num == 4) {
					// createDir(path);
					techown.shanghu.https.Log.Instance().WriteLog("�ݸ������Ź�������ͼƬ������������Ϊ"+currentPhotoId);
					saveFile(path, currentPhotoId, 4);
				} else {
					// createDir(path);
					saveFile(path, currentPicName, num);
				}
					
				//ɾ��tmpPhoto
				FileUtils fileUtils = new FileUtils();
	    		if(fileUtils.isFileExist(tmpPhotoName)){
	    			fileUtils.deleteSDFILE(tmpPhotoName);
	    		}
			
			}	
					
    	}catch(Exception e){
    		techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�GDTPhotoActivity����"+e.getMessage());	
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
		 public String[] mItemList; //���η�������private
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
	            LayoutInflater inflater = LayoutInflater.from(GDTPhotoActivity.this);
	            View view = inflater.inflate(R.layout.img_list_item, null);
	            	           
	            TextView photoName = (TextView) view.findViewById(R.id.photoName);	
	            if(mItemList[position].split("_").length==4){
	            	photoName.setText(titleMap.get(mItemList[position].split("_")[2]));
	            }
	            ImageView iv=(ImageView)view.findViewById(R.id.photo);
	            iv.setImageBitmap(mbit[position]);		           
	            return view;
	        }

	    }

    public void getBitmap(String path)
	{
    	try
    	{
    		String test[];
   		 	File file=new File("/sdcard/"+path);
   		 	test=file.list();
   		 	if(test!=null){
	   		 	picname=new String[test.length];
	   		 	picbim=new Bitmap[test.length];
	   		 	BitmapFactory.Options options = new BitmapFactory.Options();
	   		 	options.inSampleSize =2;
	   		 	for(int i=0;i<test.length;i++){
	   		 		picname[i]=test[i].trim();
	   		 		picbim[i]=BitmapFactory.decodeFile("/sdcard/"+path+test[i].trim(),options);    
	   		 		techown.shanghu.https.Log.Instance().WriteLog("�ݸ������Ź������ս���getBitmap������"+path+"�ļ���ͼƬpicname"+picname[i]);
	           }
   		 	}else{
   		 		picname=null;
   		 	techown.shanghu.https.Log.Instance().WriteLog("�ݸ������Ź������ս���getBitmap������"+path+"�ļ���ͼƬΪ��");
   		 	}
    	}catch(Exception e)
    	{
    		techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�GDTPhotoActivity����"+e.getMessage());
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
	   		if(TuanDuiShangHuActivity.device==1){
		   		if(request == 0){
		   			takePhoto(1);
		   		}
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
				else if(request==6)
				{
					takePhoto(6);	
				}
	   		}else{
	   			if(back==0){
	   			//���ͼƬû������ֻ��������淵�أ�����������ѡ��Ϊ��
					if(firstpic==0){
						choosePic.setText("");
					}else if(firstpic==1){
						if(request!=4){
							choosePic_1.setText("");
						}
					}else if(firstpic==2){
						//��һ�δӲݸ�����ߴ���һ���淵�ؽ���ҳ�� �����½�������������Ϊ1���Ա��´�û�����㷵�����һ����������Ϊ��
						firstpic=1;
					}
	   			}
	   		}
	   	}
    
    
    
    public void saveData() {
    	
    	String[] clomname = new String[] {"DCL","LX","TIME"};
    	String[] clomstr = new String[4];
    	clomstr[0] = "234";
    	clomstr[1] = "���Ź���";
    	clomstr[2] = time;
    	DB.upload3(GDTPhotoActivity.this, clomname, clomstr, TuanDuiShangHuActivity.tmpId5);
  
    }
    
    public  void saveData1(Context con) {
    	
    	
    	String[] clomname = new String[] {"DCL","LX"};
    	String[] clomstr = new String[4];
    	clomstr[0] = "0";
    	clomstr[1] = "���Ź���";
    	
    	
    	DB.upload3(con, clomname, clomstr, TuanDuiShangHuActivity.tmpId5);
  
    }
    
    
    private void uploadname()
    {

    	
    	String string[] = new String [13];
    	String str1 []= new String[]{"md","hb","mt","ttk","_tk","syt","_x","xct","rp","jfpos","posxp","dhcj","ryhy"};
    	String filedName[] = new String[]{"AttLicense","AttContract","AttAccredit","AttContract1","AttContract2","AttContract3",
    			"AttContract4","AttContract5","AttContract6","AttContract7","AttContract8","AttContract9","AttOthers"};
    	
    	if(picname!=null&&picname.length>0){
    		
    	

    		savePicName(string, str1);
    		
        	DB.upload3(GDTPhotoActivity.this, filedName, string,TuanDuiShangHuActivity.tmpId5);
        	System.out.println("GongDanweihu1.num"+GongDanWeiHu1.num+"TuanDuiShanghuActivity.tmpId5"+TuanDuiShangHuActivity.tmpId5);
//    		DB.upload3(GDTPhotoActivity.this,new String[]{"AttContract","AttContract1","AttContract2","AttLicense"},new String[]{nameone,nametwo,namef,sbthree}, TuanDuiShangHuActivity.tmpId5);
    	}
    }
    public void savePicName(String[]string,String[]str1){
    	
    	for(int i=0;i<picname.length;i++){
    		string[i]=picname[i];
    		techown.shanghu.https.Log.Instance().WriteLog("�ݸ������Ź���idΪ"+actId1+"����ͼƬ����"+string[i]);
    	}
    	
    	for(int i=0;i<string.length;i++){
    		
    		System.out.println("uploadpic->"+string[i]);
    	}
    	
    }
 
    String AttLicense;
	String AttContract;
	String actId;
	String actId1;
	public  void tiaoxingma(){
		
		MyDatabaseHelper myHelper = new MyDatabaseHelper(GDTPhotoActivity.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from gongdanweihu where id==?",new String[] { TuanDuiShangHuActivity.tmpId5 });
		while (cursor.moveToNext()) {
			try {
				
				AttLicense = des.jieMI(cursor.getString(cursor.getColumnIndex("AttContract")));
				AttContract = des.jieMI(cursor.getString(cursor.getColumnIndex("AttContract1")));
				actId = des.jieMI(cursor.getString(cursor.getColumnIndex("taskId2")));
				actId1 = des.jieMI(cursor.getString(cursor.getColumnIndex("taskId")));
				
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�GDTPhotoActivity��tiaoxingma��������"+e.getMessage());
			}finally{
				if(cursor !=null){
					cursor.close();
				}if(db != null){
					db.close();
				}
			}
			
		}
		techown.shanghu.https.Log.Instance().WriteLog("���Ź����ݸ������ս���tiaoxingma()����IDΪ"+actId1+"numΪ"+TuanDuiShangHuActivity.tmpId5);
		
	}
	 //ͼƬ���� addByZhaojingxian 
    public Dialog dlg;
	public void dialog(final Context con, final TextView text1, String string,
			String[] array) {
		
		
		LayoutInflater factory = LayoutInflater.from(con);
		// �õ��Զ���Ի���
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);

		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);

		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				addPic.setClickable(false);
				text1.setClickable(true);
				dlg.dismiss();
				y=0;
			}
		});
		lv.setAdapter(new SpinnerAdapter(con, array));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// ��дѡ������¼��Ĵ�����
				LinearLayout ll = (LinearLayout) arg1;// ��ȡ��ǰѡ��ѡ���Ӧ��LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);//
				text1.setText(tvn.getText().toString());										
				dlg.dismiss();
				currentPicName = actId1+"_task_"+picMap.get(tvn.getText().toString())+"_01.jpg";
				
				String str1 = currentPicName.split("_")[2];
				//�жϸ�����ͼƬ�Ƿ�������������������ͼƬ����+1��
				if(str1!=null&&str1.equals("tk")){
					str1 = "_tk";
				}
				if(str.contains(str1)){
					int x=1;//����ͼƬ������
					int y=0;
					String strname[]=str.split(",");
					for(int i =0;i<strname.length;i++){
						if(strname[i].contains(str1)){
							x++;
						}
						if(strname[i].contains("xct")){
							y++;
						}
						if(strname[i].contains("posxp")){
							y++;
						}
					}
					if(str1.equals("x")){
						x=x-y;
					}
					//�޸�Ҫ�����ͼƬ������
					if(x<10){
						String str2 = currentPicName.split("_")[3].charAt(0)+String.valueOf(x);
						currentPicName=actId1+"_task_"+picMap.get(tvn.getText().toString())+"_"+str2+".jpg";
					}else{
						String str2 = String.valueOf(x);
						currentPicName=actId1+"_task_"+picMap.get(tvn.getText().toString())+"_"+str2+".jpg";
					}
				}
				System.out.println("currentPicName:-->"+currentPicName);
				//���ѡ�������û����������Խ�������
//				if(!str.contains(currentPicName)){
					
//					if (TuanDuiShangHuActivity.device == 0) {
//						back=0;
//						Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//						i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//						i.putExtra("cameraid",0);
//						i.putExtra("width",640);
//						i.putExtra("height",480);
//						startActivityForResult(i, 1);
//						GDTPhotoActivity.request =1;
//					} else if (TuanDuiShangHuActivity.device == 1) {
						path = "TJB/TuanDui/gongdan/"+TuanDuiShangHuActivity.tmpId5+"/pic/";
						mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
						request = 0;
//					}
				

			}
		});
		// �����Ի���

		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// ���õ��Dialog�ⲿ���������
		dlg.setCancelable(false);

		dlg.show();
	}
	public void getData(){
		//��ѯ�����ͼƬ�������ͼƬ����ͼƬ�洢���Ʒ���picname
		path = "TJB/TuanDui/gongdan/"+TuanDuiShangHuActivity.tmpId5+"/pic/";
		try{
			String test[];
			File file=new File("/sdcard/"+path);
   		 	test=file.list();
   		 	if(test==null){
   		 		picname=null;
   				techown.shanghu.https.Log.Instance().WriteLog("���Ź����ݸ������ս���getData()����IDΪ"+actId1+"numΪ"+TuanDuiShangHuActivity.tmpId5+"pic�ļ���ͼƬΪ��");
   		 	}else{
	   		 	picname=new String[test.length];
	   		 	picbim=new Bitmap[test.length];
	   		 	addCnt=test.length;
   		 	
	   		 	BitmapFactory.Options options = new BitmapFactory.Options();
	   		 	options.inSampleSize =2;
	   		 	for(int i=0;i<test.length;i++){
	   		 		picname[i]=test[i].trim();
	   		 		picbim[i]=BitmapFactory.decodeFile("/sdcard/"+path+test[i].trim(),options);    
	   		 		System.out.println("picname:"+picname[i]);
	   				techown.shanghu.https.Log.Instance().WriteLog("���Ź����ݸ������ս���getData()����IDΪ"+actId1+"numΪ"+TuanDuiShangHuActivity.tmpId5+"pic�ļ���picname"+picname[i]);

	   		 	}
   		 	}
    	}catch(Exception e){
    		techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�GongDanTakePhotoActivity��getData()"+e.getMessage());	
    		picname=null;
    	}
	
		//����ͼƬ�����������������
		
		final LinearLayout choosePicLayout = (LinearLayout) findViewById(R.id.addChoosePicLayout);
		if(picname==null){
			addPic.setClickable(false);
		}else{
			firstpic=2;//��һ����Ƭ���ģ����������½��������
			y=1;
			addPic.setClickable(true);
			choosePic = (TextView) findViewById(R.id.choosePicTxt);
			if(picname==null){
				choosePic.setText("");
				str=",";
			}else{
				choosePic.setText(titleMap.get(picname[picname.length-1].split("_")[2]));
				str+=picname[picname.length-1]+",";
			}			
			
			for( int i=0;i<picname.length-1;i++){
				final int x=i;
				final View view = LayoutInflater.from(GDTPhotoActivity.this).inflate(
						R.layout.add_wenxuan_pic, null);
				
				choosePic_1=(TextView)view.findViewById(R.id.choosePicTxt_add);
				String str1 = titleMap.get(picname[x].split("_")[2]);
				choosePic_1.setText(str1);
				str +=picname[x]+","; 

				choosePicLayout.addView(view);
				//ɾ����Ӧ����������
				view.findViewById(R.id.deltetPicture).setOnTouchListener(new OnTouchListener() {
					
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						switch (event.getAction()){
						case MotionEvent.ACTION_UP:

							DeletePhoto(view, choosePicLayout);
						    
							break;
						
						}
						return false;
						
					}
				});
			}
		}
		
	}
	public void DeletePhoto(View view,LinearLayout choosePicLayout){
		
		//���һ��������״̬ ����Ӱ�ť������ӣ����һ��δ���� ��ɾ������������������Ӱ�ť���ɵ�������һ��δ������ɾ�����һ������Ӱ�ť�ɵ��
		boolean haspic=false;
		int piccnt=0;
		String test[];
   		File file=new File("/sdcard/"+path);
   		test=file.list();
   		if(test!=null){
	   		picname=new String[test.length];
	   		picbim=new Bitmap[test.length];
	   		BitmapFactory.Options options = new BitmapFactory.Options();
	           options.inSampleSize =2;						      
	        for(int i=0;i<test.length;i++){
	           	picname[i]=test[i].trim();
	           	picbim[i]=BitmapFactory.decodeFile("/sdcard/"+path+test[i].trim(),options);    
	           	System.out.println("picname:"+picname[i]);
	        	String picMapName[];
	           	if(((TextView)view.findViewById(R.id.choosePicTxt_add)).getText().toString().length()!=0){
	           		picMapName=picname[i].split("_");
	           		if(picMapName[2]!=null&&picMapName[2].equals(picMap.get(((TextView)view.findViewById(R.id.choosePicTxt_add)).getText().toString()))){
//	           		if(picname[i].contains(picMap.get(((TextView)view.findViewById(R.id.choosePicTxt_add)).getText().toString()))){
			        	haspic=true;//��ʾɾ�������ж�Ӧ��������ͼƬ
			        	piccnt++;
			        }

		        }else{
		        	haspic=false;
		        }
	        }
   		}
   		
//		File hb=new File(Environment.getExternalStorageDirectory() + "/"+path+GongDanWeiHu1.Id+"_task_"+picMap.get(((TextView)view.findViewById(R.id.choosePicTxt_add)).getText().toString()));
		if(y==1){
			if(GDTPhotoActivity.request==4){
				if(!haspic){
					addPic.setClickable(true);
				}
				
			}else{
				addPic.setClickable(true);
			}
		}else if(y==0&&haspic){
			addPic.setClickable(false);
		}else if(y==0&&!haspic){
			addPic.setClickable(true);
			y=1;
		}

		choosePicLayout.removeView(view);
		if(piccnt>0){
			addCnt--;//����ͼƬ������1��
			
			String strname;
			if(piccnt<10){
				strname = "0"+String.valueOf(piccnt);
			}else{
				strname = String.valueOf(piccnt);
			}
			
			System.out.println("removePic-->"+actId1+"_task_"+picMap.get(((TextView)view.findViewById(R.id.choosePicTxt_add)).getText().toString())+"_"+strname+".jpg");
			//�洢��������Ҫɾ�����´ο��Լ���ѡ������
			str=str.replace(actId1+"_task_"+picMap.get(((TextView)view.findViewById(R.id.choosePicTxt_add)).getText().toString())+"_"+strname+".jpg"+",","");
			System.out.println("newstr-->"+str);
			
			//ɾ����Ӧ����Ƭ
			FileUtils fileUtils = new FileUtils();
			fileUtils.deleteSDFILE(path+actId1+"_task_"+picMap.get(((TextView)view.findViewById(R.id.choosePicTxt_add)).getText().toString())+"_"+strname+".jpg");
    		System.out.println("path-->"+path+"..."+path+actId1+"_task_"+picMap.get(((TextView)view.findViewById(R.id.choosePicTxt_add)).getText().toString())+"_"+strname+".jpg");
    		techown.shanghu.https.Log.Instance().WriteLog("�ݸ������Ź������ս���DeletePhoto����ɾ��ͼƬ"+path+"�ļ��µ�"+actId1+"_task_"+picMap.get(((TextView)view.findViewById(R.id.choosePicTxt_add)).getText().toString())+"_"+strname+".jpg");

		}
		//����ˢ��ͼƬ�б�
	    getBitmap(path);
		ia.mbit=picbim;
		ia.mItemList=picname;
		Message msg = new Message();   
        msg.what =2;   
        handlerphoto.sendMessage(msg); 
	}
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(GDTPhotoActivity.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}