package techown.shanghu.photo;
/*
 * ��� ����
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import techown.shanghu.DBUtil;
import techown.shanghu.EditDialog;
import techown.shanghu.MyDatabaseHelper;
import techown.shanghu.PadUtiltiy;
import techown.shanghu.R;
import techown.shanghu.TuanDuiShangHuActivity;
import techown.shanghu.YouHuiShangXian_TeHui;
import techown.shanghu.YouHuiShangXian_ZuiHong3;
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

public class YouHuiTakePhotoActivity2 extends Activity {
    /** Called when the activity is first created. */
	private Button takephoto_before,takephoto_next,takephoto1,button6,takephoto3;
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
			
			int huihui=0;
			String currentPhotoId;
			String currentPicName;
			
			public static String path1 ;
			int qtnum;

			List<String>deletenum=new ArrayList<String>();
			String[]picname;
			Bitmap[]picbim;
			ImageAdapter ia;
			FileUtils fileUtils;
			PadUtiltiy mPadUtiltiy;
			static String time;
			public static	int request = 0;/*1�����գ�6������ */
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		    	super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				requestWindowFeature(Window.FEATURE_PROGRESS);
				setContentView(R.layout.youhuishangxian_zuihong4);
				
				time=GetDate.getDate().toString();
				path1 = "TJB/TuanDui/youhuishang/"+YouHuiShangXian_TeHui.num+"/pic/";
				createDir(path1);
				tiaoxingma();
				mPadUtiltiy = new PadUtiltiy(this);
				mDialog = new AlertDialog.Builder(YouHuiTakePhotoActivity2.this).create();
				//panel = new Panel(this);
				takephoto_before= (Button)findViewById(R.id.button5);
				takephoto_before.setOnClickListener(new testBtnListener());
				
				takephoto_next= (Button)findViewById(R.id.button4);
				takephoto_next.setOnClickListener(new testBtnListener());
				
				takephoto1= (Button)findViewById(R.id.takephoto1);

					takephoto1.setOnClickListener(new testBtnListener());
//				}
				button6= (Button)findViewById(R.id.button6);
				button6.setOnClickListener(new testBtnListener());
				
				takephoto3= (Button)findViewById(R.id.takephoto3);
				takephoto3.setOnClickListener(new testBtnListener());
						

				textview1=(TextView)findViewById(R.id.editText1);
//				textview2=(TextView)findViewById(R.id.editText2);
				textview3=(TextView)findViewById(R.id.editText3);
//				textview4=(TextView)findViewById(R.id.editText4);
				
				etlayout1=(LinearLayout)findViewById(R.id.etlayout1);
//				etlayout2=(LinearLayout)findViewById(R.id.etlayout2);
				etlayout3=(LinearLayout)findViewById(R.id.etlayout3);
//				etlayout4=(LinearLayout)findViewById(R.id.etlayout4);
				
				imageList = (ListView)findViewById(R.id.imageList);
				getBitmap(path1);
				if(picname!=null)
				{
					for(int i=0;i<picname.length;i++)
					{
						if(picname[i].split("_").length==3&&picname[i].split("_")[2].equals("czt.jpg"))
				        {
							takephoto1.setText("չ������(����)");
							takephoto1.setEnabled(false);
				        }

				        else if(picname[i].split("_").length==4&&picname[i].split("_")[3].equals("qt.jpg"))
				        {   
				        	qtnum++;
				        	
				        	takephoto3.setText("��������(����"+qtnum+")");
				        	if(qtnum==5)
				        	{
				        		takephoto3.setEnabled(false);	
				        	}
				        	
				        }	
					}
				}
				textview1.setText(Tkint);
				textview3.setText(Cztint);
				etlayout1.setOnClickListener(new OnClickListener() {
					
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						log.etDialog(YouHuiTakePhotoActivity2.this, textview1,R.id.editText1,"������չ������");
					}
				});
			

				etlayout3.setOnClickListener(new OnClickListener() {
					
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						log.etDialog(YouHuiTakePhotoActivity2.this, textview3,R.id.editText3,"������������������");
					}
				});

				
				ia=new ImageAdapter(YouHuiTakePhotoActivity2.this,picname,picbim);
				imageList.setAdapter(ia);            	
				imageList.setOnItemClickListener(
						new OnItemClickListener()
						{
							public void onItemClick(AdapterView<?> arg0, View arg1,
									int arg2, long arg3) {
								// TODO Auto-generated method stub
							PictureDialog3 editDlg = new PictureDialog3(YouHuiTakePhotoActivity2.this, ia.mbit[arg2],ia.mItemList[arg2]);
							editDlg.showListDialog();
							}
				
						}
				);
		    }
		    public void createDir(String filepath) {
				fileUtils = new FileUtils();

				fileUtils.createSDDir(filepath);

			}
			class testBtnListener implements OnClickListener{
		    	public void onClick(View v) {
		    		// TODO Auto-generated method stub
		    		int id = v.getId();
		    		Intent intent = new Intent();
		    		switch(id){
		    		case R.id.button5:
		    			saveData();
		    		
		    			saveData2();
		    			intent.setClass(YouHuiTakePhotoActivity2.this, YouHuiShangXian_ZuiHong3.class);
		    			YouHuiTakePhotoActivity2.this.startActivity(intent);
		    			YouHuiTakePhotoActivity2.this.finish();
		    			overridePendingTransition(R.anim.rightin, R.anim.rightout);
		    			break;
		    		case R.id.button4:
//						saveData3();
		    			if(validate()){
//		    			if(huihui==1)
//		    			{
		    				saveData();
			    			saveData1();
			    			saveData2();
							String barcodeno=YouHuiShangXian_TeHui.Id+YouHuiShangXian_TeHui.num;
							DB.sendinsert(YouHuiTakePhotoActivity2.this, new String[] {
						    			MyDatabaseHelper.SEND_BarcodeNo,
										MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
										new String[] { barcodeno, "1", "0" });
							
							
							Builder b = new AlertDialog.Builder(YouHuiTakePhotoActivity2.this);
							b.setTitle("��ʾ");
							b.setMessage("�ύ�ɹ�");// ������Ϣ
							b.setCancelable(false);

							b.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {// ���ü����¼�
										public void onClick(DialogInterface dialog, int which) {
											dialog.dismiss();
											Intent intent = new Intent();
											intent.setClass(
													YouHuiTakePhotoActivity2.this,
													TuanDuiShangHuActivity.class);
											YouHuiTakePhotoActivity2.this
													.startActivity(intent);
											techown.shanghu.https.OperateLog.Instance().WriteLog("�̻��Ŷ���������ύ�ɹ���");
											YouHuiTakePhotoActivity2.this.finish();
										}
									});
							if(alterdlg==null||!alterdlg.isShowing()){
								alterdlg = b.create();// �����Ի���
								alterdlg.show();// ��ʾ�Ի���
								alterdlg.setCanceledOnTouchOutside(false);
								alterdlg.setCancelable(false);// ���õ��Dialog�ⲿ���������
							}
							
//							new AlertDialog.Builder(YouHuiTakePhotoActivity2.this).setTitle("��ʾ").setMessage("�ύ�ɹ�")
//							.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
//
//							
//								public void onClick(DialogInterface arg0, int arg1) {
//									// TODO Auto-generated method stub
//									Intent intent = new Intent();
//									intent.setClass(YouHuiTakePhotoActivity2.this, TuanDuiShangHuActivity.class);
//									YouHuiTakePhotoActivity2.this.startActivity(intent);
//									YouHuiTakePhotoActivity2.this.finish();
//								}
//							}).show();
							
		    			}

		    			break;
		    		case R.id.button6:
		    			saveData();
		    			saveData2();
		    			log.ExitAppYou55(YouHuiTakePhotoActivity2.this);
		    			techown.shanghu.https.Log
						.Instance()
						.WriteLog(
								"�Ŷӣ�������߽���ݸ��� "+YouHuiShangXian_TeHui.num
										);
		    			break;
		    		case R.id.takephoto1:
		    			currentPicName=YouHuiShangXian_TeHui.Id+"_online"+"_czt.jpg";
//		    			if (TuanDuiShangHuActivity.device == 0) {
//							Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//							i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//							i.putExtra("cameraid",0);
//							i.putExtra("width",640);
//							i.putExtra("height",480);
//							startActivityForResult(i, 1);
//						} else if (TuanDuiShangHuActivity.device == 1) {
//							Intent i = new Intent();
//				    		i.setClass(YouHuiTakePhotoActivity2.this, CaptureImage.class);
//				    		i.putExtra("cameraDir",0);
//							i.putExtra("photoWdith",640);
//							i.putExtra("photoHeight",480);
//							startActivityForResult(i, 1);
							mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
							request = 1;
//						}
		    			
		    			
		    			break;

		    		case R.id.takephoto3:
		    			if(deletenum.size()>0)
		    			{
		    				currentPicName=YouHuiShangXian_TeHui.Id+"_online"+"_"+deletenum.get(0)+"_qt.jpg";	
		    				deletenum.remove(0);
		    			}
		    			else
		    			{
		    				currentPicName=YouHuiShangXian_TeHui.Id+"_online"+"_"+qtnum+"_qt.jpg";	
		    			}
		    		
//		    			if (TuanDuiShangHuActivity.device == 0) {
//							Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//							i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//							i.putExtra("cameraid",0);
//							i.putExtra("width",640);
//							i.putExtra("height",480);
//							startActivityForResult(i, 1);
//						} else if (TuanDuiShangHuActivity.device == 1) {
//							Intent i = new Intent();
//				    		i.setClass(YouHuiTakePhotoActivity2.this, CaptureImage.class);
//				    		i.putExtra("cameraDir",0);
//							i.putExtra("photoWdith",640);
//							i.putExtra("photoHeight",480);
//							startActivityForResult(i, 3);
							mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
							request = 3;
//						}
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

					switch(num)
					{
					case 1:
						takephoto1.setText("չ������(����)");
		    			takephoto1.setEnabled(false);
		    			huihui++;
						
						break;

					case 3:
						qtnum++;
						takephoto3.setText("��������(����)"+qtnum);
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
					techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�YouHuiTakePhotoActivity2:"+e.getMessage());		
				}
				
			}

			public void deletePhoto(String photoid){
				
				 File fipic=new File("/sdcard/"+path1+photoid);	        		        	
		         if (fipic.exists())
		 		{       	
		        	 fipic.delete();
		 		}
		         if(photoid.split("_").length==3&&photoid.split("_")[2].equals("czt.jpg"))
			        {
						takephoto1.setText("չ������(��ѡ)");
						takephoto1.setEnabled(true);
						huihui--;
			        }

			        else if(photoid.split("_").length==4&&photoid.split("_")[3].equals("qt.jpg"))
			        {   
			        	qtnum--;
			        	deletenum.add(photoid.split("_")[2]);
//			        	isdelete=true;
			        	if(qtnum==0)
			        	{
			        		takephoto3.setText("������������");
			        	}else
			        	{
			        		takephoto3.setText("��������(����"+qtnum+")");
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
		    public void takePhoto(int num){
		    	
		    	try{
//		    		if(TuanDuiShangHuActivity.device == 0){
//						Bitmap tmpLT = BitmapFactory
//								.decodeFile("/mnt/sdcard/tmpPhoto/");
//						if (tmpLT != null) {
//							
//							bitmap= ResizeBitmap(tmpLT, 300);
//							
//						}
//						
//						realBitmap = BitmapFactory.decodeFile("/mnt/sdcard/tmpPhoto/");
//						if (num == 4) {
//							// createDir(path);
//							saveFile(path1, currentPhotoId, 4);
//						} else {
//							// createDir(path);
//							saveFile(path1, currentPicName, num);
//						}
//							
//						//ɾ��tmpPhoto
//						FileUtils fileUtils = new FileUtils();
//			    		if(fileUtils.isFileExist("tmpPhoto")){
//			    			fileUtils.deleteSDFILE("tmpPhoto");
//			    		}
//						
//					}else if(TuanDuiShangHuActivity.device == 1){
						String tmpPhotoName = "tmpPhoto.jpg";
						Bitmap tmp = BitmapFactory.decodeFile("/mnt/sdcard/"+tmpPhotoName);
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
							
						//ɾ��tmpPhoto
						FileUtils fileUtils = new FileUtils();
			    		if(fileUtils.isFileExist(tmpPhotoName)){
			    			fileUtils.deleteSDFILE(tmpPhotoName);
			    		}
					
//					}	
		    	}catch(Exception e){
		    		techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�YouHuiTakePhotoActivity2:"+e.getMessage());	
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
			            LayoutInflater inflater = LayoutInflater.from(YouHuiTakePhotoActivity2.this);
			            View view = inflater.inflate(R.layout.img_list_item, null);
			            	           
			            TextView photoName = (TextView) view.findViewById(R.id.photoName);	
			            if(mItemList[position].split("_").length==3&&mItemList[position].split("_")[2].equals("czt.jpg"))
			            {
			            	 photoName.setText("չ������");
			            }

			            else if(mItemList[position].split("_").length==4&&mItemList[position].split("_")[3].equals("qt.jpg"))
			            {            	
			            	photoName.setText("��������"+String.valueOf(Integer.parseInt(mItemList[position].split("_")[2])+1));
			            }
			            ImageView iv=(ImageView)view.findViewById(R.id.photo);
			            iv.setImageBitmap(mbit[position]);		           
			            return view;
			        }

			    }

		   
		    public void saveData() {
		    	
		    	String nameone=new String();
		    	
		    	String namef=new String();
		    	StringBuilder sbthree=new StringBuilder();
		    	if(picname!=null&&picname.length>0){

		    	for(int i=0;i<picname.length;i++)
		    	{
		    		 if(picname[i].split("_").length==3&&picname[i].split("_")[2].equals("czt.jpg"))
		 	        {
		    			 nameone=picname[i]; 
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
		    	
		    	DB.upload4(YouHuiTakePhotoActivity2.this,new String[]{"AttContract","AttOthers"},new String[]{nameone,namef}, YouHuiShangXian_TeHui.num);
		    
		    	
//		    	DBUtil.upload4(YouHuiTakePhotoActivity.this, clomname, clomstr, YouHuiShangXian_TeHui.num);
				
		    	}

		    }
		    
		    public void saveData2() {
				String[] clomname = new String[] {"Cztint","Mtint"};
				String[] clomstr = new String[clomname.length];
				clomstr[0] = textview1.getText().toString();
				clomstr[1] = textview3.getText().toString();
				
				DB.upload4(YouHuiTakePhotoActivity2.this, clomname, clomstr, YouHuiShangXian_TeHui.num);
			}
		    public void saveData1() {
		    	
		    	String[] clomname = new String[] {"DCL","LX","TIME"};
		    	String[] clomstr = new String[10];
		    	clomstr[0] = "3";
		    	clomstr[1] = "�������";
		    	clomstr[2] = time;
		    	
		    	DB.upload4(YouHuiTakePhotoActivity2.this, clomname, clomstr, YouHuiShangXian_TeHui.num);
		  
		    }
		    
		     
		    public  void saveData2(Context con) {
		    	
		    	String[] clomname = new String[] {"DCL","LX","TIME"};
		    	String[] clomstr = new String[10];
		    	clomstr[0] = "0";
		    	clomstr[1] = "�������";
		    	clomstr[2] = time;
		    	
//		    	System.out.println("����ݸ����ʱ��+ �������  +="+time);
		    	DB.upload4(con, clomname, clomstr, YouHuiShangXian_TeHui.num);
		  
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
		    	{
		    		picname=null;
		    		techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�YouHuiTakePhotoActivity2:"+e.getMessage());	
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
		    
		    
		    String AttLicense;
		    String AttContract;
		    String Mtint;
		    String Cztint;
		    String Tkint;
		 	public  void tiaoxingma(){
		 		SQLiteDatabase db=null;
		 		Cursor cursor=null;
				try
				{
					MyDatabaseHelper myHelper = new MyDatabaseHelper(YouHuiTakePhotoActivity2.this, "techown.db", Constant.tdshDBVer);
					db = myHelper.getWritableDatabase();
					cursor = db.rawQuery("select * from youhushangxian where id==?",new String[] { YouHuiShangXian_TeHui.num});
					while (cursor.moveToNext()) {
						try {
							
							AttLicense = des.jieMI(cursor.getString(cursor.getColumnIndex("AttContract")));
//							AttContract = cursor.getString(cursor.getColumnIndex("AttContract"));
							Tkint = des.jieMI(cursor.getString(cursor.getColumnIndex("Cztint")));
							Cztint = des.jieMI(cursor.getString(cursor.getColumnIndex("Mtint")));
//							Mtint = cursor.getString(cursor.getColumnIndex("Mtint"));
//							
						} catch (Exception e) {
							techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�YouHuiTakePhotoActivity2:"+e.getMessage());	
						}
						cursor.close();
						db.close();
					}	
				}catch(Exception e)
				{
					techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�YouHuiTakePhotoActivity2:"+e.getMessage());		
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
		    
		    protected boolean validate() {
		    	

		    	if(textview1.getText().toString().equals("")){

		    		log.Toast(YouHuiTakePhotoActivity2.this, "չ����������Ϊ��");
		    		return false;
		    	}
		    	if(!log.allowMaxLenthOfString(textview1.getText().toString(), 10)){

		    		log.Toast(YouHuiTakePhotoActivity2.this, "չ����������С��10λ��");
		    		return false;
		    	}

		    	if(!log.allowMaxLenthOfString(textview3.getText().toString(), 10)){

		    		log.Toast(YouHuiTakePhotoActivity2.this, "����������������С��10λ��");
		    		return false;
		    	}

		    	if(!takephoto1.getText().toString().equals("չ������(����)")){

		    		log.Toast(YouHuiTakePhotoActivity2.this, "������չ���գ�");
		    		return false;
		    	}
		    	if(!log.isNumber(textview1.getText().toString())){

			 		log.Toast(YouHuiTakePhotoActivity2.this, "����ֻ��Ϊ��������λ����Ϊ0��");
			 		return false;
			 	}
		    
		    	if(!textview3.getText().toString().equals("")){
				 
			
					 if(!log.isNumber(textview3.getText().toString())){

					 		log.Toast(YouHuiTakePhotoActivity2.this, "����ֻ��Ϊ��������λ����Ϊ0��");
					 		return false;
			 	}}
		    	
		    	
				return true;
		    	
		    }
		    
		    
			public boolean onKeyDown(int keyCode, KeyEvent event) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_BACK:
					log.ExitApp(YouHuiTakePhotoActivity2.this);
					break;

				}
				return super.onKeyDown(keyCode, event);
			}
}