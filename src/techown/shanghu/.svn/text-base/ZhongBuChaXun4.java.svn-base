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
 * 总部选着大区城市
 */
public class ZhongBuChaXun4 extends Activity{
	static String[] tempstr1;
	String[] citystr;
	Map<String,String> map = new HashMap<String, String>();
	CommonUtil commonUtil = new CommonUtil();

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
	public static String active_Area_CityId;//本页面选择的城市Id

	static int i = 0;
	public static String requeststr1,
	daqu,
	chengshi;
	public static String requeststr2;
	public static String requeststr3;
	AlertDialog alterdlg;
	DES des = new DES();
	//lxp
	private Dialog mDialog;
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
	        	//log.Toast(ZhongBuChaXun4.this, b.getString("throwsmessage"));
//	        	Toast.makeText(ZhongBuChaXun4.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
	        	Intent intent = new Intent();
	        	intent.setClass(ZhongBuChaXun4.this, ZhongBuHuoDong1.class);
				ZhongBuChaXun4.this.startActivity(intent);
				ZhongBuChaXun4.this.finish();
	        	break;
	        case 3:
//	        	DB.upload(ZhongBuChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
//	        	 Toast.makeText(ZhongBuChaXun4.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
//	        	 intent = new Intent();
//	        	intent.setClass(ZhongBuChaXun4.this, ZhongBuHuoDong1.class);
//				ZhongBuChaXun4.this.startActivity(intent);
//				ZhongBuChaXun4.this.finish();	
				
				
	        	Builder ab = new AlertDialog.Builder(ZhongBuChaXun4.this);
				ab.setTitle("提示");
				ab.setMessage(b.getString("throwsmessage")+"，是否新建公司");
				ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				ab.setPositiveButton// 为对话框设置按钮
				("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						DB.upload(ZhongBuChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
						Intent intent = new Intent();
						intent.setClass(ZhongBuChaXun4.this, ZhongBuHuoDong1.class);
						ZhongBuChaXun4.this.startActivity(intent);
						ZhongBuChaXun4.this.finish();	
						
					}
				});			
				if(alterdlg==null||!alterdlg.isShowing()){
					alterdlg = ab.create();// 创建对话框
					alterdlg.show();// 显示对话框
					alterdlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
				}	
				
				
	        	break;
	        case 4:
//	        	DBUtil.upload(ZhongBuChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	b = msg.getData();
	        	ds.Toast(ZhongBuChaXun4.this,b.getString("throwsmessage"));
//		        Toast.makeText(ZhongBuChaXun4.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
		        intent = new Intent();
	        	break;	
	        case 5:
	        	DB.upload(ZhongBuChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	 b = msg.getData();
	        	 log.Toast(ZhongBuChaXun4.this, b.getString("throwsmessage"));
		        intent = new Intent();
		        intent.setClass(ZhongBuChaXun4.this, ZhongBuHuoDong1.class);
				ZhongBuChaXun4.this.startActivity(intent);
				ZhongBuChaXun4.this.finish();	
	        	break;
	        case 6:
	        	 b = msg.getData();
	        	Builder ab6 = new AlertDialog.Builder(ZhongBuChaXun4.this);
	        	ab6.setTitle("提示");
	        	ab6.setMessage(b.getString("throwsmessage"));
	        	ab6.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
	        	ab6.setPositiveButton// 为对话框设置按钮
				("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						DB.upload(ZhongBuChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"N"}, TuanDuiShangHuActivity.num);
						Intent intent = new Intent();
						intent.setClass(ZhongBuChaXun4.this, ZhongBuHuoDong1.class);
						ZhongBuChaXun4.this.startActivity(intent);
						ZhongBuChaXun4.this.finish();	
					}
				});			
				if(alterdlg==null||!alterdlg.isShowing()){
					alterdlg = ab6.create();// 创建对话框
					alterdlg.show();// 显示对话框
					alterdlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
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
//        String id9 = DB.SelectWhere(this, "t_cityactive", "where active_theme = '"+des.jiaMi("竺娅测试03")+"'","active_id");
//        System.out.println("竺娅测试03==="+id9);
        CompanyLicense=null;
        
        TuanDuiShangHuActivity.Query(ZhongBuChaXun4.this);
        DB.insertContactId(ZhongBuChaXun4.this,"Id",TuanDuiShangHuActivity.num);
        
        mDialog = new AlertDialog.Builder(ZhongBuChaXun4.this).create();//连接网络进程
        List<TArea> tareaList=DB.klQuery(ZhongBuChaXun4.this,"t_area");
        province=new String[tareaList.size()];
        for(int i=0;i<tareaList.size();i++){
        	province[i]=tareaList.get(i).name;
        	
        }
        a=0;
        ZhongBuHuoDong1.requeststr1=null;
        requeststr1 = null;
    	requeststr2 = null;
    	requeststr3 = null;
        
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        MyLis lis = new MyLis();
        button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
        
        text1=(TextView)findViewById(R.id.hyedit1);
        text2=(TextView)findViewById(R.id.hyedit2);//城市名
        if(i==1)
        {
        	text1.setText(daqu);
            text2.setText(chengshi);
            i=0;
        }
        else
        {
        	text1.setText(TuanDuiShangHuActivity.areaName2);
            text2.setText(TuanDuiShangHuActivity.cityname);
            /*
             * 大区城市默认情况下的城市Id
             */
            active_Area_CityId = TuanDuiShangHuActivity.cityId;

        }
        text3=(TextView)findViewById(R.id.hyedit3);//营业执照注册号
        
        text3.setText(ZhongBuHuoDong1.CompanyLicense1);
        text3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog4(ZhongBuChaXun4.this, text3,
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
						map = cityQueryAndCityId(agencyareaId);
						List<String> cityList = new ArrayList<String>(map.keySet());
						citystr = commonUtil.listToStringArray(cityList);
						log.dialog(ZhongBuChaXun4.this, text2, "城市",
								citystr);
					}
					else
					{
						log.Toast(ZhongBuChaXun4.this, "请选择大区");
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
					log.dialog(ZhongBuChaXun4.this, text1, "大区",TuanDuiShangHuActivity.areastr);
					log.l = 1;
					break;
				default:
					break;
				}

				return false;
			}
		});
    }
           
    
    public void saveData() {
		String[] clomname = new String[] { "ActiveType",
				"CompanyLicense","storeauditId","YesNXieYi","MaintainUserId","ExpandUserId" };
		String[] clomstr = new String[clomname.length];
		clomstr[0] = "MARKETACTIVE";
//		clomstr[1] = text1.getText().toString();
//		clomstr[2] = text2.getText().toString();
		clomstr[1] = text3.getText().toString();
		CompanyLicense=text3.getText().toString();
		clomstr[2] = "";
		clomstr[3] ="N";
		clomstr[4] = TuanDuiShangHuActivity.username;
		clomstr[5] = TuanDuiShangHuActivity.username;
//		String id=DBUtil.klQueryCity(this, "t_area", "where Name = '"+clomstr[1]+"'");
		//clomstr[1]=id;
		
		/*List<TCity> tcity=DB.cityQuerycity(ZhongBuChaXun4.this,"t_city"," where Name = '"+des.jiaMi(text2.getText().toString())+"'");
		cityid = tcity.get(0).id;*/
//		DB.upload(ZhongBuChaXun4.this, new String[]{"CityId"},new String[]{tcity.get(0).id}, TuanDuiShangHuActivity.num);
		if(map!=null && map.size()>=1&&text2.getText().toString()!=null
				&&!"".equals(text2.getText().toString()))
		{	
			active_Area_CityId = map.get(text2.getText().toString());
		}
		DB.upload(ZhongBuChaXun4.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
   
    class MyLis implements OnClickListener{

	
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			
			switch(id){
				case R.id.button1:
					ZhongBuHuoDong1.CompanyLicense1=null;
					Intent intent2 = new Intent();
					intent2.setClass(ZhongBuChaXun4.this, TuanDuiShangHuActivity.class);
					ZhongBuChaXun4.this.startActivity(intent2);
					ZhongBuChaXun4.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
				case R.id.button2://下一页
					if(validate()){
						
					daqu = text1.getText().toString();
					chengshi = text2.getText().toString();
					//ZhongBuHuoDong1.chengshi = text2.getText().toString();
					saveData();
					//第一次与服务器交互    A0008
					
					 //lxp
					 Message msg = new Message();   
				        msg.what =1; 
						handler.sendMessage(msg);//
						 new Thread(){
							 public void run(){
								 Intent intent = new Intent();
								 String[]str1=new String[]{"","",""};	
								 str1[0]=text3.getText().toString();
								 str1[1]=active_Area_CityId;
								 str1[2]=TuanDuiShangHuActivity.username;
								 String json1="{" +
					 				"\"licences\":\""+str1[0]+"\"," +
					 				"\"city\":\""+str1[1]+"\"," +
					 				"\"userId\":\""+str1[2]+"\"}";	
								 ZhongBuChaXun4.requeststr1=HttpConnection2.request(json1,"A0008");
								 ZhongBuHuoDong1.requeststr1=HttpConnection2.request(json1,"A0008");
								 if(ZhongBuChaXun4.requeststr1!=null&&ZhongBuChaXun4.requeststr1.length()>0)
									{
										try
										{
											JSONObject demoJson1 = new JSONObject(requeststr1);
											
											if(demoJson1.getString("rspCode").equals("00"))
											{
													//从json中拿值存入tempstr数组
													tempstr1=new String[1];
													tempstr1[0]=text3.getText().toString();
//														demoJson1.getString("busiName");//busiName
													
													//继续进行第二次交互    A0009
													 String[]str2=new String[]{"","","","","","","","","","","","",""};
													 str2[9]=TuanDuiShangHuActivity.username;
//													 str2[9]="A049976";
													 str2[2]=tempstr1[0];
													 str2[8]=active_Area_CityId;
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
												 	ZhongBuChaXun4.requeststr2=HttpConnection2.request(json2,"A0009");
												 	//ZhongBuHuoDong1.requeststr2=HttpConnection2.request(json2,"A0009");
												 	mDialog.dismiss();	//
												 	if(ZhongBuChaXun4.requeststr2!=null)
												 	{
												 		JSONObject demoJson2 = new JSONObject(ZhongBuChaXun4.requeststr2);
												 		if(demoJson2.getString("rspCode").equals("00"))
												 		{
																saveData();//保存界面的信息到数据库
																a=1;
																DB.upload(ZhongBuChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"N"}, TuanDuiShangHuActivity.num);
																intent.setClass(ZhongBuChaXun4.this, ZhongBuHuoDong1.class);
																ZhongBuChaXun4.this.startActivity(intent);
																ZhongBuChaXun4.this.finish();
																overridePendingTransition(R.anim.leftin, R.anim.leftout);
															
														}
												 		else
														{
												 			saveData();
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
													b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson1.getString("rspInfo"));
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
											techown.shanghu.https.Log.Instance().WriteLog("团队：ZhongBuChaXun4异常"+e.getMessage());
										}	
									}
								 else if(ZhongBuChaXun4.requeststr1==null)
									{
										Message msg = new Message();   
										Bundle b = new Bundle();
										b.putString("throwsmessage","网络连接失败，请重新点击下一步查询！");
										msg.setData(b);
								        msg.what =4; 
										handler.sendMessage(msg);//
										mDialog.dismiss();
									}
							 }
						 }.start();				
						//lxp		
				 		
					

					}
				break;
			}
		}
    }
    
  //大区名称--〉大区ID
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
					"团队：queryAreaName出错" + e.getMessage());
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
	//大区ID--〉城市名称/城市Id
	public  Map cityQueryAndCityId(String agencyareaId) {
		//String[] str = null;
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
			//str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while(cur.moveToNext()){
					String name  = des.jieMI(cur.getString(cur.getColumnIndex("Name")));
					String id = des.jieMI(cur.getString(cur.getColumnIndex("Id")));
					map.put(name, id);
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：查询市cityQuery:"+e.getMessage());
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
	

	//大区ID--〉城市名称
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
			techown.shanghu.https.Log.Instance().WriteLog("团队：查询市cityQuery:"+e.getMessage());
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
			log.ExitApp(ZhongBuChaXun4.this);
			ZhongBuHuoDong1.CompanyLicense1=null;
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
    protected boolean validate() {
    	if (text1.getText().toString().equals("")) {
			log.Toast(ZhongBuChaXun4.this, "大区不能为空！");
			return false;
		}
    	if (text2.getText().toString().equals("")) {
			log.Toast(ZhongBuChaXun4.this,"城市不能为空！");
			return false;
		}
    	if (text3.getText().toString().equals("")) {
			log.Toast(ZhongBuChaXun4.this, "请输入营业执照号！");
			return false;
		}
		return true;
    }
}
