package techown.shanghu;

import java.util.List;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import android.widget.Toast;
/*
 * 合约签订选着大区城市
 */
public class HeYueQianDing_1 extends Activity{
	public static int a=0;
	CommonUtil commonUtil = new CommonUtil();
	Map<String,String> map = new HashMap<String, String>();

	public static String num;
	DES des = new DES();
	private Button button1,button2;
	private TextView text1;
	static TextView text2;
	public static TextView text3;
	static int i = 0;
	String[] citystr;
	String[] province;
	public static String requeststr1,daqu,chengshi;
	String areaName;
	String cityName;
	private Dialog mDialog;
	//public String cityid;
	public static String areaCityId; //此页面选择的城市对应的城市Id

	EditDialog log=new EditDialog();
	AlertDialog alterdlg;
	DBUtil DB=new DBUtil();
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
//	        	log.Toast(HeYueQianDing_1.this,  b.getString("throwsmessage"));
//	        	Toast.makeText(HeYueQianDing_1.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
	        	
//	        	intent.setClass(HeYueQianDing_1.this, HeYueQianDing_2.class);
//				HeYueQianDing_1.this.startActivity(intent);
//				HeYueQianDing_1.this.finish();
	        	Builder ab = new AlertDialog.Builder(HeYueQianDing_1.this);
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
						DB.upload(HeYueQianDing_1.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
						Intent intent = new Intent();
			        	intent.setClass(HeYueQianDing_1.this, HeYueQianDing_2.class);
						HeYueQianDing_1.this.startActivity(intent);
						HeYueQianDing_1.this.finish();
						
					}
				});			
				if(alterdlg==null||!alterdlg.isShowing()){
					alterdlg = ab.create();// 创建对话框
					alterdlg.show();// 显示对话框
					alterdlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
				}	
	        	break;
	        case 3:
	        	
	        	 b = msg.getData();
	        	 log.Toast(HeYueQianDing_1.this,  b.getString("throwsmessage"));
		       // Toast.makeText(HeYueQianDing_1.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
		        
	        }   
	    }   
	};   
    public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heyueqianding_1);
        try{
        TuanDuiShangHuActivity.Query(HeYueQianDing_1.this);
        DB.insertContactId(HeYueQianDing_1.this,"Id",TuanDuiShangHuActivity.num);
        DB.upload(HeYueQianDing_1.this, new String[]{"storeauditId"}, new String[]{HeYueQianDing_0.id},
				TuanDuiShangHuActivity.num);
        
        
        a=0;
        requeststr1=null;
        HeYueQianDing_2.requeststr1=null;
//        mDialog = new AlertDialog.Builder(HeYueQianDing_1.this).create();//连接网络进程
        mDialog = new AlertDialog.Builder(HeYueQianDing_1.this).create();//连接网络进程
        
        List<TArea> tareaList=DB.klQuery(HeYueQianDing_1.this,"t_area");
        province=new String[tareaList.size()];
        for(int i=0;i<tareaList.size();i++){
        	province[i]=tareaList.get(i).name;
        }
        

        
//        panel=new Panel(this);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        MyLis lis = new MyLis();
        button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
        
        
        
        
        
        text1=(TextView)findViewById(R.id.hyedit1);
        text2=(TextView)findViewById(R.id.hyedit2);
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
            areaCityId = TuanDuiShangHuActivity.cityId;

        }
        
        text3=(TextView)findViewById(R.id.hyedit3);

        DES des = new DES();
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				HeYueQianDing_1.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.num });
		while (cursor.moveToNext()) {
			try {
				text3.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyLicense"))));
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing_1:"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}if(db != null){
					db.close();
				}
			}
			
		}        
		text3.setText(HeYueQianDing_2.com);
		text3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog4(HeYueQianDing_1.this, text3,
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
							 * 修改城市Id
							 */
							//citystr = cityQuery(agencyareaId);
							map = cityNameAndIdQuery(agencyareaId);
							List<String> cityList = new ArrayList<String>(map.keySet());
							citystr = commonUtil.listToStringArray(cityList);
							
							log.dialog(HeYueQianDing_1.this, text2, "城市",
									citystr);
						}
						else
						{
							log.Toast(HeYueQianDing_1.this, "请选择大区");
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
						log.dialog(HeYueQianDing_1.this, text1, "大区",TuanDuiShangHuActivity.areastr);
						log.l = 2;
						break;
					default:
						break;
					}

					return false;
				}
			});
    }catch(Exception e){
    	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_1类："+e.getMessage());
    }
    
    }
    static String companylicense;
    public void saveData() {
		String[] clomname = new String[] { "ActiveType", 
				"CompanyLicense"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = "SPECIAL";
//		clomstr[1] = text1.getText().toString();
//		clomstr[2] = text2.getText().toString();
		clomstr[1] = text3.getText().toString();
		companylicense = text3.getText().toString();
		/*
		 * 修改城市Id
		 */
		/*DES des = new DES();
		List<TCity> tcity=DB.cityQuerycity(HeYueQianDing_1.this,"t_city"," where Name = '"+des.jiaMi(text2.getText().toString())+"'");
		cityid = tcity.get(0).id;*/
//		DB.upload(HeYueQianDing_1.this, new String[]{"CityId"},new String[]{tcity.get(0).id}, TuanDuiShangHuActivity.num);
		
	
		if(map!=null&& map.size()>=1&&text2.getText().toString()!=null
				&&!"".equals(text2.getText().toString())){
			areaCityId  = map.get(text2.getText().toString());
			
		}
		DB.upload(HeYueQianDing_1.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
    

    class MyLis implements OnClickListener{

	
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try{
			int id = v.getId();
			Intent intent = new Intent();
			switch(id){
				case R.id.button1:
					HeYueQianDing_2.com=null;
//					DB.upload(HeYueQianDing_1.this, new String[]{"CompanyLicense"},new String[]{""}, TuanDuiShangHuActivity.num);
					intent.setClass(HeYueQianDing_1.this, YuShenSucc.class);
					HeYueQianDing_1.this.startActivity(intent);
					HeYueQianDing_1.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);	

				break;
				case R.id.button2:
				if(validate()){
						
					daqu = text1.getText().toString();
					chengshi = text2.getText().toString();
					saveData();
//					saveData1();
					 Message msg = new Message();   
				     msg.what =1; 
					 handler.sendMessage(msg);//
				new Thread()
				{
					public void run()
					{
					 Intent intent = new Intent();
					 String[]str1=new String[]{"","",""};	
					 str1[0]=text3.getText().toString();
					 str1[1]=areaCityId;
					 str1[2]=TuanDuiShangHuActivity.username;
					// HeYueQianDing_2.cityname = areaCityId;
					 String json="{" +
		 				"\"licences\":\""+str1[0]+"\"," +
		 				"\"city\":\""+str1[1]+"\"," +
		 				"\"userId\":\""+str1[2]+"\"}";			 			
					 	HeYueQianDing_1.requeststr1=HttpConnection2.request(json,"A0008");
					 	HeYueQianDing_2.requeststr1=HttpConnection2.request(json,"A0008");
					 	if(HeYueQianDing_1.requeststr1!=null&&HeYueQianDing_1.requeststr1.length()>0){
					 		
					 
							try {
								JSONObject demoJson1 = new JSONObject(HeYueQianDing_1.requeststr1);
								if(demoJson1.getString("rspCode").equals("00"))
								{
									a=1;
									DB.upload(HeYueQianDing_1.this, new String[]{"IsNewCompany"}, new String[]{"N"}, TuanDuiShangHuActivity.num);
									intent.setClass(HeYueQianDing_1.this, HeYueQianDing_2.class);
									HeYueQianDing_1.this.startActivity(intent);
									HeYueQianDing_1.this.finish();
									overridePendingTransition(R.anim.leftin, R.anim.leftout);
									mDialog.dismiss();
								}
								else if(demoJson1.equals("")){
									Message msg = new Message();   
									Bundle b = new Bundle();
									b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson1.getString("rspInfo"));
									msg.setData(b);
							        msg.what =3; 
									handler.sendMessage(msg);//
									mDialog.dismiss();
								}
								else
								{
									a=2;
									Message msg = new Message();   
									Bundle b = new Bundle();
									b.putString("throwsmessage",demoJson1.getString("rspInfo"));
									msg.setData(b);
							        msg.what =2; 
									handler.sendMessage(msg);//
									mDialog.dismiss();
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_1类......()"+e.getMessage());
							}
					 	}else 
						{
							Message msg = new Message();   
							Bundle b = new Bundle();
							b.putString("throwsmessage","网络连接失败，请重新点击下一步查询！");
							msg.setData(b);
					        msg.what =3; 
							handler.sendMessage(msg);//
							mDialog.dismiss();
						}
					}
					}.start();
				}
					
				break;
			}
		}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_1类按钮点击："+e.getMessage());

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
	//大区ID--〉城市名称 城市Id
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
//			cur = sld.query("t_city", new String[] { "Name" }, "AreaId"
//					+ "=?", new String[] { des.jiaMi(agencyareaId) }, null, null, null);
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
			log.ExitApp(HeYueQianDing_1.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
    
 
    
    protected boolean validate() {
    	if (text1.getText().toString().equals("")) {
			
			log.Toast(HeYueQianDing_1.this, "大区不能为空！");
			return false;
		}
    	if (text2.getText().toString().equals("")) {
			
			log.Toast(HeYueQianDing_1.this, "城市不能为空！");
			return false;
		}
    	if (text3.getText().toString().equals("")) {
		
			log.Toast(HeYueQianDing_1.this, "请输入营业执照号！");
			return false;
		}
		return true;
    }
    
}
