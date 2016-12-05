package techown.shanghu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.TArea;
import techown.shanghu.date.TCity;
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
import android.widget.TextView;
/*
 * ������--��������
 */
public class RedBigArea extends Activity{
	static String[] tempstr1;
	String[] citystr;
	CommonUtil commonUtil = new CommonUtil();
	Map<String,String> map = new HashMap<String, String>();
	public static String redAreaCityId; //��ҳ��ѡ��ĳ��ж�Ӧ�ĳ���Id

	public static String num,CompanyLicense;
	public static int a;
	private Button button1,button2;
	private TextView text1,text3;
	static TextView text2;
	EditDialog log=new EditDialog();
	EditDialog ds=new EditDialog();
	DBUtil DB=new DBUtil();
	String[]province;
	public String cityid;
	static int i = 0;
	public static String requeststr1,daqu,chengshi;
	public static String requeststr2;
	public static String requeststr3;
	AlertDialog alterdlg;
	DES des = new DES();
	//lxp
	private Dialog mDialog;
	Constant ct = new Constant();
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
	        	Bundle b = msg.getData();
	        	Intent intent = new Intent();
	        	intent.setClass(RedBigArea.this, RedCompanyMessage.class);
				RedBigArea.this.startActivity(intent);
				RedBigArea.this.finish();
	        	break;
	        case 3:
	        	 b = msg.getData();
	        	Builder ab = new AlertDialog.Builder(RedBigArea.this);
				ab.setTitle("��ʾ");
				ab.setMessage(b.getString("throwsmessage")+"���Ƿ��½���˾");
				ab.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				ab.setPositiveButton// Ϊ�Ի������ð�ť
				("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						DB.Red_upload(RedBigArea.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
						Intent intent = new Intent();
						intent.setClass(RedBigArea.this, RedCompanyMessage.class);
						RedBigArea.this.startActivity(intent);
						RedBigArea.this.finish();	
						
					}
				});			
				if(alterdlg==null||!alterdlg.isShowing()){
					alterdlg = ab.create();// �����Ի���
					alterdlg.show();// ��ʾ�Ի���
					alterdlg.setCanceledOnTouchOutside(true);// ���õ��Dialog�ⲿ���������
				}	
				
				
	        	break;
	        case 4:
	        	b = msg.getData();
	        	ds.Toast(RedBigArea.this,b.getString("throwsmessage"));
		        intent = new Intent();
	        	break;	
	        case 5:
	        	DB.Red_upload(RedBigArea.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(RedBigArea.this, b.getString("throwsmessage"));
		        intent = new Intent();
		        intent.setClass(RedBigArea.this, RedCompanyMessage.class);
				RedBigArea.this.startActivity(intent);
				RedBigArea.this.finish();	
	        	break;
	        case 6:
	        	 b = msg.getData();
	        	Builder ab1 = new AlertDialog.Builder(RedBigArea.this);
	        	ab1.setTitle("��ʾ");
	        	ab1.setMessage(b.getString("throwsmessage"));
	        	ab1.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
	        	ab1.setPositiveButton// Ϊ�Ի������ð�ť
				("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						DB.Red_upload(RedBigArea.this, new String[]{"IsNewCompany"}, new String[]{"N"}, TuanDuiShangHuActivity.num);
						Intent intent = new Intent();
						intent.setClass(RedBigArea.this, RedCompanyMessage.class);
						RedBigArea.this.startActivity(intent);
						RedBigArea.this.finish();	
						
					}
				});			
				if(alterdlg==null||!alterdlg.isShowing()){
					alterdlg = ab1.create();// �����Ի���
					alterdlg.show();// ��ʾ�Ի���
					alterdlg.setCanceledOnTouchOutside(true);// ���õ��Dialog�ⲿ���������
				}	
				
				
	        	break;
	        }   
	    }   
	};   
	//lxp
	//lxp
    public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zongbuchaxun3);
        
        CompanyLicense=null;
        
        TuanDuiShangHuActivity.Red_Query(RedBigArea.this);
        DB.red_insertContactId(RedBigArea.this,"Id",TuanDuiShangHuActivity.num);
        
        mDialog = new AlertDialog.Builder(RedBigArea.this).create();//�����������
        List<TArea> tareaList=DB.klQuery(RedBigArea.this,"t_area");
        province=new String[tareaList.size()];
        for(int i=0;i<tareaList.size();i++){
        	province[i]=tareaList.get(i).name;
        	
        }
        a=0;
        RedCompanyMessage.requeststr1=null;
        requeststr1 = null;
    	requeststr2 = null;
    	requeststr3 = null;
        
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        MyLis lis = new MyLis();
        button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
        
        text1=(TextView)findViewById(R.id.hyedit1);
        text2=(TextView)findViewById(R.id.hyedit2);//������
        text3=(TextView)findViewById(R.id.hyedit3);//Ӫҵִ��ע���
        if(i==1)
        {
        	text1.setText(daqu);
            text2.setText(chengshi);
            text3.setText(RedCompanyMessage.CompanyLicense1);
            i=0;
        }
        else
        {
        	text1.setText(TuanDuiShangHuActivity.areaName2);
            text2.setText(TuanDuiShangHuActivity.cityname);
            text3.setText("");
            /*
             * ��������Ĭ������µĳ���Id
             */
            redAreaCityId = TuanDuiShangHuActivity.cityId;

        }
        
        text3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog4(RedBigArea.this, text3,
						R.id.hyedit3);
			}
		});
        text2.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					if(text1.getText().toString()!=null&&!text1.getText().toString().equals(""))
					{
						String agencyareaId = queryAreaIdName(text1.getText().toString());
						//citystr = cityQuery(agencyareaId);
						/*
						 * �޸ĳ���Id
						 */
						map = cityNameAndIdQuery(agencyareaId);
						List<String> cityList = new ArrayList<String>(map.keySet());
						citystr = commonUtil.listToStringArray(cityList);
						
						log.red_dialog(RedBigArea.this, text2, "����",
								citystr);
					}
					else
					{
						log.Toast(RedBigArea.this, "��ѡ�����");
					}
					break;
				default:
					break;
				}

				return false;
			}
		});
        text1.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.red_dialog(RedBigArea.this, text1, "����",TuanDuiShangHuActivity.areastr);
					log.l = 3;
					break;
				default:
					break;
				}

				return false;
			}
		});
    }
           
    
    public void saveData() {
		String[] clomname = new String[] {"CompanyLicense"};
		String[] clomstr = new String[clomname.length];

		clomstr[0] = text3.getText().toString();
		CompanyLicense=text3.getText().toString();
		DB.Red_upload(RedBigArea.this, clomname, clomstr, TuanDuiShangHuActivity.num);
		
		/*List<TCity> tcity=DB.cityQuerycity(RedBigArea.this,"t_city"," where Name = '"+des.jiaMi(text2.getText().toString())+"'");
		cityid = tcity.get(0).id;*/
		if(map!=null&& map.size()>=1&&text2.getText().toString()!=null
				&&!"".equals(text2.getText().toString())){
			redAreaCityId  = map.get(text2.getText().toString());
			
		}
	}
   
    class MyLis implements OnClickListener{

	
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			
			switch(id){
				case R.id.button1:
					RedCompanyMessage.CompanyLicense1=null;
					RedNumber.selectno = "xia";
					Intent intent2 = new Intent();
					intent2.setClass(RedBigArea.this, RedNumber.class);
					RedBigArea.this.startActivity(intent2);
					RedBigArea.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
				case R.id.button2://��һҳ
					if(validate()){
						
					daqu = text1.getText().toString();
					chengshi = text2.getText().toString();
					RedCompanyMessage.chengshi = text2.getText().toString();
					RedCompanyMessage.CompanyLicense1 = text3.getText().toString();
					saveData();
					//��һ�������������    A0008
					
					 //lxp
					 Message msg = new Message();   
				        msg.what =1; 
						handler.sendMessage(msg);//
						 new Thread(){
							 public void run(){
								 Intent intent = new Intent();
								 String[]str1=new String[]{"","",""};	
								 str1[0]=text3.getText().toString();
								 str1[1]=redAreaCityId;
								 str1[2]=TuanDuiShangHuActivity.username;
								 String json1="{" +
					 				"\"licences\":\""+str1[0]+"\"," +
					 				"\"city\":\""+str1[1]+"\"," +
					 				"\"userId\":\""+str1[2]+"\"}";	
								 RedBigArea.requeststr1=HttpConnection2.request(json1,"A0008");
								 RedCompanyMessage.requeststr1=HttpConnection2.request(json1,"A0008");
								 if(RedBigArea.requeststr1!=null&&RedBigArea.requeststr1.length()>0)
									{
										try
										{
											JSONObject demoJson1 = new JSONObject(requeststr1);
											
											if(demoJson1.getString("rspCode").equals("00"))
											{
													//��json����ֵ����tempstr����
													tempstr1=new String[1];
													tempstr1[0]=text3.getText().toString();
													
													//�������еڶ��ν���    A0009
													 String[]str2=new String[]{"","","","","","","","","","","","",""};
													 str2[9]=TuanDuiShangHuActivity.username;
//													 str2[9]="A049976";
													 str2[2]=tempstr1[0];
													 str2[8]=redAreaCityId;
													 str2[12]="1";
													 
													 String json2="{" +
										 				"\"misId\":\""+str2[0]+"\"," +
										 				"\"terId\":\""+str2[1]+"\"," +
										 				"\"licences\":\""+str2[2]+"\"," +
										 				"\"companyName\":\""+str2[3]+"\"," +
										 				"\"merName\":\""+str2[4]+"\"," +
										 				"\"address\":\""+str2[5]+"\"," +
										 				"\"merTel\":\""+str2[6]+"\"," +
										 				"\"companyTel\":\""+str2[7]+"\"," +
										 				"\"cityId\":\""+str2[8]+"\"," +
										 				"\"userId\":\""+str2[9]+"\"," +
										 				"\"activeMode\":\""+str2[10]+"\"," +
										 				"\"isOnline\":\""+str2[11]+"\"," +
										 				"\"pageNo\":\""+str2[12]+"\"}";			 					    	
												 	RedBigArea.requeststr2=HttpConnection2.request(json2,"A0009");
												 	mDialog.dismiss();	//
												 	if(RedBigArea.requeststr2!=null)
												 	{
												 		JSONObject demoJson2 = new JSONObject(RedBigArea.requeststr2);
												 		if(demoJson2.getString("rspCode").equals("00"))
												 		{
																saveData();//����������Ϣ�����ݿ�
																a=1;
																Constant.istop = true;
																DB.Red_upload(RedBigArea.this, new String[]{"IsNewCompany"}, new String[]{"N"}, TuanDuiShangHuActivity.num);
																intent.setClass(RedBigArea.this, RedCompanyMessage.class);
																RedBigArea.this.startActivity(intent);
																RedBigArea.this.finish();
																overridePendingTransition(R.anim.leftin, R.anim.leftout);
															
														}
												 		else
														{
												 			saveData();
												 			a=1;
												 			Message msg = new Message();   
															Bundle b = new Bundle();
															b.putString("throwsmessage",demoJson2.getString("rspInfo"));
															msg.setData(b);
													        msg.what =6; 
															handler.sendMessage(msg);//
															mDialog.dismiss();
														}
												 	}
											}
											else 
											{
												
												if(demoJson1.equals(""))
												{
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage","���������ӳ�ʱ�������µ����һ����ѯ��"+demoJson1.getString("rspInfo"));
													msg.setData(b);
											        msg.what =4; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
												else if(demoJson1.getString("rspCode").equals("800")){
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson1.getString("rspInfo"));
													msg.setData(b);
											        msg.what =4; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
												else
												{
													saveData();
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson1.getString("rspInfo"));
													msg.setData(b);
											        msg.what =3; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
											}
										}catch(Exception e)
										{
											techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�ZhongBuChaXun4�쳣"+e.getMessage());
										}	
									}
								 else if(RedBigArea.requeststr1==null)
									{
										Message msg = new Message();   
										Bundle b = new Bundle();
										b.putString("throwsmessage","��������ʧ�ܣ������µ����һ����ѯ��");
										msg.setData(b);
								        msg.what =4; 
										handler.sendMessage(msg);//
										mDialog.dismiss();
									}
							 }
						 }.start();				
					}
				break;
			}
		}
    }
    
  //��������--������ID
	public String queryAreaIdName(String AreaName) {

		String AreaID = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select Id from t_area where Name = ?",
					new String[] { des.jiaMi(AreaName) });
			if (cursor.getCount() == 1) {
				cursor.moveToLast();
				AreaID = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"�Ŷӣ�queryAreaName����" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return AreaID;
	}
	//����ID--���������� ����Id
	public  Map cityNameAndIdQuery(String agencyareaId) {
		Map map = new HashMap();
		Cursor cur=null;
		SQLiteDatabase sld=null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			int i = 0;
			sld = myHelper.getWritableDatabase();
			
			cur = sld.rawQuery("select distinct Id,Name from t_city where AreaId=?", new String[]{des.jiaMi(agencyareaId)});
//			cur = sld.query("t_city", new String[] { "Name" }, "AreaId"
//					+ "=?", new String[] { des.jiaMi(agencyareaId) }, null, null, null);
			//citystr = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while(cur.moveToNext()){
					String id = des.jieMI(cur.getString(cur.getColumnIndex("Id")));
					String name = des.jieMI(cur.getString(cur.getColumnIndex("Name")));
					map.put(name, id);
					//citystr[i] = name;
					//Log.i("chengqk",""+citystr.length);
					//i++;
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ���ѯ��cityQuery:"+e.getMessage());
		}finally{
			if(cur!=null){
				cur.close();
			}
			if(sld!=null){
				sld.close();
			}
		}
		return map;
	}


	//����ID--����������
	public  String[] cityQuery(String agencyareaId) {
		String[] str = null;
		Cursor cur=null;
		SQLiteDatabase sld=null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);
			int i = 0;
			sld = myHelper.getWritableDatabase();

			cur = sld.rawQuery("select distinct Name from t_city where AreaId=?", new String[]{des.jiaMi(agencyareaId)});
			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while(cur.moveToNext()){
					str[i] = des.jieMI(cur.getString(cur.getColumnIndex("Name")));
					i++;
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ���ѯ��cityQuery:"+e.getMessage());
		}finally{
			if(cur!=null){
				cur.close();
			}
			if(sld!=null){
				sld.close();
			}
		}
		return str;
	}
	
	
 
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(RedBigArea.this);
			RedCompanyMessage.CompanyLicense1=null;
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
    protected boolean validate() {
    	if (text1.getText().toString().equals("")) {
			log.Toast(RedBigArea.this, "��������Ϊ�գ�");
			return false;
		}
    	if (text2.getText().toString().equals("")) {
			log.Toast(RedBigArea.this,"���в���Ϊ�գ�");
			return false;
		}
    	if (text3.getText().toString().equals("")) {
			log.Toast(RedBigArea.this, "������Ӫҵִ�պţ�");
			return false;
		}
		return true;
    }
}
