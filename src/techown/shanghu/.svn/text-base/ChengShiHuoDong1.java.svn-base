package techown.shanghu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetData;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 城市活动-公示信息录入
 */
public class ChengShiHuoDong1 extends Activity{
	public static int bs;
	DES des = new DES();
	DBUtil DB=new DBUtil();
	String[] province;
	String namemerid;
	GetData getDasta=new GetData();
	//private Button button1;
	Button button2,button3,add_button,md_button,name_button;
	private TextView c1et1,c1et2,c1et3,c1et4,c1et5,c1et6,
				c1et8,c1et9,c1et10,c1et11,c1et12,
					c1et13;
	
	public static TextView c1et7 ,c1et702;
	
	int[] textIds = { R.id.c1et1, R.id.c1et2, R.id.c1et3, R.id.c1et4,
			R.id.c1et5, R.id.c1et6, R.id.c1et7, R.id.c1et8, R.id.c1et9,
			R.id.c1et10, R.id.c1et11, R.id.c1et12, R.id.c1et13 };
	public static TextView[] textArray;
 
	int[] linlayIds={R.id.c1leout1,R.id.c1leout2,R.id.c1leout3,R.id.c1leout4,
			R.id.c1leout5,R.id.c1leout6,R.id.c1leout7,R.id.c1leout8,
			R.id.c1leout9,R.id.c1leout10,R.id.c1leout11,R.id.c1leout12,
			R.id.c1leout13};
	public static String requeststr1;
	public static String merName,CompanyLicense1;
	String[][] tempstr2;
	public static String summerId;
	ListView mylist; 
	List<HashMap<String,String>>data = new ArrayList<HashMap<String,String>>();
	public LinearLayout[] linlay;
//	public static String path = "sdcard/"+"zhongbu/pic/";
	
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703区,7021详细地址
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73区，74详细地址
	EditDialog log=new EditDialog();
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chengshihuodong1);

        
        button2 = (Button) findViewById(R.id.button2);

        add_button = (Button) findViewById(R.id.add_button);
        name_button= (Button) findViewById(R.id.name_button); 
       
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
//		getLogic();
               
		
		c1et1 = (TextView) findViewById(R.id.c1et1);
		c1et2 = (TextView) findViewById(R.id.c1et2);
		c1et3 = (TextView) findViewById(R.id.c1et3);
		c1et4 = (TextView) findViewById(R.id.c1et4);
		c1et5 = (TextView) findViewById(R.id.c1et5);
		c1et6 = (TextView) findViewById(R.id.c1et6);
		c1et7 = (TextView) findViewById(R.id.c1et7);
		c1et8 = (TextView) findViewById(R.id.c1et8);
		c1et9 = (TextView) findViewById(R.id.c1et9);
		c1et10 = (TextView) findViewById(R.id.c1et10);
		c1et11 = (TextView) findViewById(R.id.c1et11);
		c1et12 = (TextView) findViewById(R.id.c1et12);
		c1et13 = (TextView) findViewById(R.id.c1et13);
		//c1et702= (TextView) findViewById(R.id.c1et702);
		
		h1leout71=(LinearLayout)findViewById(R.id.h1leout71);
		h1leout72=(LinearLayout)findViewById(R.id.h1leout72);
		h1leout73=(LinearLayout)findViewById(R.id.h1leout73);
		h1et7 = (TextView) findViewById(R.id.h1et7);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
        MyLis lis = new MyLis();
        //button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
//        button3.setOnClickListener(lis);
        add_button.setOnClickListener(lis);
        name_button.setOnClickListener(lis);
//        md_button.setOnClickListener(lis);
      
        
        //读取数据库
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				ChengShiHuoDong1.this, "techown.db",Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.num });
		while (cursor.moveToNext()) {
			try {
				
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyName"))));
				textArray[1].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("LegalPerson"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ContactUser"))));
				textArray[3].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("TelNumber"))));
				textArray[4].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("MobileNumber"))));
				textArray[5].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("EngageName"))));
				textArray[6].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Adress"))));
				textArray[7].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("PostCode"))));
				textArray[8].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("EngageScope"))));
				textArray[9].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Brand"))));
				textArray[10].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("BrandTelNumber"))));
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Validity"))));
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("EngageYear"))));
//				textArray[13].setText(cursor.getString(cursor
//						.getColumnIndex("ContractNumber")));
				
				h1et703.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Gdistrictid"))));
			
				
			
				String IsNewCompany=des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsNewCompany")));
				if(IsNewCompany.equals("N")){
					h1leout73.setEnabled(false);
					for(int i = 0; i<linlayIds.length;i++){
						linlay[i].setEnabled(false);
		    		}
				}
				
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("团队：ChengShiHuoDong1异常"+e.getMessage());
			}
			finally{
				if(cursor!=null){
					cursor.close();
				}
				if(db!=null){
					db.close();
				}
			}
			String name=DB.klQueryCity1(this, "t_district", "where Id = '"
					+ des.jiaMi(h1et703.getText().toString()) + "'");
					h1et703.setText(name);
		}

        
		
		
		
		mylist=(ListView)findViewById(R.id.mainright_lv);   

		h1et7.setText(TuanDuiShangHuActivity.ProvinceName);
		h1et702.setText(TuanDuiShangHuActivity.city);
		h1leout73.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
			
				 if(h1et7.getText().toString().equals("")){
						h1et703.setEnabled(false);
						;
					}
				 else{
				if(h1et702.getText().toString().equals("无")){
					h1et703.setText("无");
					h1et703.setEnabled(false);
		}
				else {
				String[] str1 = getDasta.getCity11(ChengShiHuoDong1.this,
						h1et702.getText().toString());
				
				 
				if (str1.length > 0) {
					log.dialog(ChengShiHuoDong1.this, h1et703, "区",
							str1);
					
				}
				else if(h1et702.getText().toString().equals("无")){
					
				}else {
					h1et703.setText("无");
					h1et703.setEnabled(false);
				}
				
//				EditDialog.Dialog(HeYueQianDing_2.this, h1leout73,
//						h1et7021, R.id.h1et1);
			}
			}
			}
		});
		
		
        linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					new EditDialog().Dialog(ChengShiHuoDong1.this, linlay[0],
							textArray[0], R.id.c1et1,"请输入公司名");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[1].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					new EditDialog().Dialog(ChengShiHuoDong1.this, linlay[1],
							textArray[1], R.id.c1et2,"请输入法人代表");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[2].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					new EditDialog().Dialog(ChengShiHuoDong1.this, linlay[2],
							textArray[2], R.id.c1et3,"请输入联系人");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[3].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					new EditDialog().etDialog(ChengShiHuoDong1.this,
							textArray[3], R.id.c1et4,"请输入固定电话");
					break;
				default:
					break;
				}

				return false;
			}

		});
		linlay[4].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					new EditDialog().etDialog(ChengShiHuoDong1.this,
							textArray[4], R.id.c1et5,"请输入手机号码");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[5].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					new EditDialog().Dialog(ChengShiHuoDong1.this, linlay[5],
							textArray[5], R.id.c1et6,"请输入经营门店名称");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[6].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(ChengShiHuoDong1.this, linlay[6],
							textArray[6], R.id.c1et7,"请输入注册地址");

					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[7].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(ChengShiHuoDong1.this, textArray[7],
							R.id.c1et8,"请输入邮编");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[8].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(ChengShiHuoDong1.this, linlay[8],
							textArray[8], R.id.c1et9,"请输入经营范围");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[9].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(ChengShiHuoDong1.this, linlay[9],
							textArray[9], R.id.c1et10,"请输入品牌");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[10].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(ChengShiHuoDong1.this,
							textArray[10], R.id.c1et11,"请输入品牌热线");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[11].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					DateDialog.showDatePicker(ChengShiHuoDong1.this,
							textArray[11], R.id.c1et12);
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[12].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(ChengShiHuoDong1.this,
							textArray[12], R.id.c1et13,"请输入经营年限");
					break;
				default:
					break;
				}

				return false;
			}
		});
		init();
    }
       
    String[] tempstr1=new String[17];
    public void init()
    {
    	try
		{
    		JSONObject demoJson1 = new JSONObject(requeststr1);
			if(demoJson1.getString("rspCode").equals("00"))
			{
				tempstr1[0]=demoJson1.getString("name");
				tempstr1[1]=demoJson1.getString("companyId");
				tempstr1[2]=demoJson1.getString("legalPerson");
				tempstr1[3]=demoJson1.getString("contactPerson");
				tempstr1[4]=demoJson1.getString("contactTel");
				tempstr1[5]=demoJson1.getString("handPhone");					
				tempstr1[6]=demoJson1.getString("busiName");
				tempstr1[7]=demoJson1.getString("address");
				tempstr1[8]=demoJson1.getString("busi");
				tempstr1[9]=demoJson1.getString("brand");
				tempstr1[10]=demoJson1.getString("brandHotLine");
				tempstr1[11]=demoJson1.getString("zip");
				tempstr1[12]=demoJson1.getString("validDate");
				tempstr1[13]=demoJson1.getString("busiYear");
				tempstr1[14]=demoJson1.getString("province");//省
				tempstr1[15]=demoJson1.getString("city");//市
				tempstr1[16]=demoJson1.getString("area");//区
				
				String id = DB.klQueryCity1(this, "t_province", "where Id = '"
						+ des.jiaMi(tempstr1[14]) + "'");
				tempstr1[14] = id;

				String id1 = DB.klQueryCity1(this, "t_city", "where Id = '"
						+ des.jiaMi(tempstr1[15]) + "'");
				tempstr1[15] = id1;
				
				String id2 = DB.klQueryCity1(this, "t_district", "where Id = '"
						+ des.jiaMi(tempstr1[16]) + "'");
				tempstr1[16] = id2;
				
				c1et1.setText(tempstr1[0]);//公司名称
	    		c1et2.setText(tempstr1[2]);//法人代表
	    		c1et3.setText(tempstr1[3]);//联系人
	    		c1et4.setText(tempstr1[4]);//固定电话
	    		c1et5.setText(tempstr1[5]);//手机号码
	    		c1et6.setText(tempstr1[6]);//经营门店名称
	    		c1et7.setText(tempstr1[7]);//注册地址
	    		c1et8.setText(tempstr1[11]);//邮编
	    		c1et9.setText(tempstr1[8]);//经营范围
	    		c1et10.setText(tempstr1[9]);//品牌
	    		c1et11.setText(tempstr1[10]);//品牌热线
	    		c1et12.setText(tempstr1[12]);//有效期
	    		c1et13.setText(tempstr1[13]);//门店名称
	    		h1et7.setText(id);
	    		h1et702.setText(id1);
	    		h1et703.setText(id2);
	    		for(int i = 0; i<linlayIds.length;i++){
	    			linlay[i].setEnabled(false);
	    		}
	    		h1leout73.setEnabled(false);
	    		DB.upload(ChengShiHuoDong1.this, new String[]{"CompanyId"}, new String[]{tempstr1[1]}, TuanDuiShangHuActivity.num);
	    		
//	    		JSONObject demoJson2 = new JSONObject(ChengShiChaXun4.requeststr2);
//	    		tempstr2=new String[demoJson2.length()][2];
////	    		summerId = demoJson2.getString("total");
//		 		if(demoJson2.getString("rspCode").equals("00"))
//		 		{
//		 			
//		 			JSONArray numberList1 =demoJson2.getJSONArray("detail");
//		 			data.clear();
//					for (int i = 0; i < numberList1.length(); i++) {
//						JSONObject datajson = (JSONObject)numberList1.opt(i);
//						tempstr2[i][0]=datajson.getString("merId");
//						namemerid = tempstr2[i][0];
//						tempstr2[i][1]=datajson.getString("merName");
//						
//							HashMap<String, String>map = new HashMap<String, String>();
//							map.put("line", tempstr2[i][1]);
//							
//							data.add(map);
//					}
//						
//		 		}

			}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("ChengShiHuoDong1类init()"+e.getMessage());
		}	
    }
    
    
    //保存
    public void saveData() {
		String[] clomname = new String[] { "CompanyName", "LegalPerson",
				"ContactUser", "TelNumber", "MobileNumber", "EngageName",
				"Adress","PostCode", "EngageScope", "Brand", "BrandTelNumber", 
				"Validity", "EngageYear" ,"meiId","Gcityid","Gdistrictid","CompanyId"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[0].getText().toString();
		clomstr[1] = textArray[1].getText().toString();
		clomstr[2] = textArray[2].getText().toString();
		clomstr[3] = textArray[3].getText().toString();
		clomstr[4] = textArray[4].getText().toString();
		clomstr[5] = textArray[5].getText().toString();
		clomstr[6] = textArray[6].getText().toString();
		clomstr[7] = textArray[7].getText().toString();
		clomstr[8] = textArray[8].getText().toString();
		clomstr[9] = textArray[9].getText().toString();
		clomstr[10] = textArray[10].getText().toString();
		clomstr[11] = textArray[11].getText().toString();
		clomstr[12] = textArray[12].getText().toString();
	
		clomstr[13] = "";
		clomstr[14] = h1et702.getText().toString();
		clomstr[15] = h1et703.getText().toString();
		
		String id = DB.klQueryCity(this, "t_city", "where Name = '"
				+des.jiaMi(clomstr[14]) + "'");
		clomstr[14] = id;

		String id1 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[15]) + "'");
		clomstr[15] = id1;
		
		System.out.println(clomstr[15]);
		if(ChengShiChaXun4.a==1){
			clomstr[16] = tempstr1[1];
		}else{
			clomstr[16] = "";
		}
	
		
		DB.upload(ChengShiHuoDong1.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}

	
	
    class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();

			switch(id){

				case R.id.button2:
					CompanyLicense1=ChengShiChaXun4.CompanyLicense;
					intent.setClass(ChengShiHuoDong1.this, ChengShiChaXun4.class);
					ChengShiHuoDong1.this.startActivity(intent);
					ChengShiHuoDong1.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;

				case R.id.add_button:
					if(validate()){
						
						//判断是否清空数据
						if(bs==1){
							saveData4();
							bs=0;
						}	
					
					saveData();
					intent.setClass(ChengShiHuoDong1.this, ChengShiHuoDong3.class);
					ChengShiHuoDong1.this.startActivity(intent);
					ChengShiHuoDong1.this.finish();
					DB.upload(ChengShiHuoDong1.this, new String[]{"IsNewStore"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
					overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
					break;
				case R.id.name_button:
					if(ChengShiChaXun4.a==1){
						bs=1;
						saveData();
						intent.setClass(ChengShiHuoDong1.this, MendianList1.class);
						ChengShiHuoDong1.this.startActivity(intent);
						ChengShiHuoDong1.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}else{
						log.Toast(ChengShiHuoDong1.this, "没有门店，请新建门店！");
					}
					
					
					break;
			}
		}
    }
    
 // 验证必填字段的是否为空
	protected boolean validate() {
		if (textArray[0].getText().toString().equals("")) {
//			Toast.makeText(ChengShiHuoDong1.this, "请输入公司名！", Toast.LENGTH_LONG)
//					.show();
			log.Toast(ChengShiHuoDong1.this, "请输入公司名！");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong1.this,  "请输入法人代表！");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong1.this,  "请输入联系人！");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong1.this, "请输入固定电话！");
			return false;
		}
//		if (textArray[4].getText().toString().equals("")) {
//			Toast.makeText(ChengShiHuoDong1.this, "请输入手机号码！", Toast.LENGTH_LONG)
//					.show();
//			return false;
//		}
		if (textArray[5].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong1.this, "请输入门店名 ！");
			return false;
		}
		if (textArray[6].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong1.this, "请输入注册地址 ！");
			return false;
		}
		if (textArray[7].getText().toString().equals("")) {
		
			log.Toast(ChengShiHuoDong1.this, "请输入邮编！");
			return false;
		}
		if (textArray[8].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong1.this, "请输入经营范围 ！");
			return false;
		}

		if (textArray[9].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong1.this, "请输入品牌！");
			return false;
		}
		if(h1et703.getText().toString().equals("")){
			
			log.Toast(ChengShiHuoDong1.this, "请输入行政区！");
			return false;
		}


		return true;
	}
	
	
	
	public void saveData4() {
		String[] clomname = new String[] { "StName",
				"Stel", "StAdress", "StPostCode", "StHangye",
				"StCaixi", "StShangquan", "StNumber", "StClientNumber", "Stlong",
				"Stlat", "StContact","StMobileNumber","StChannel","StIsReceiveBill","StValidity","StBillRate" ,
				"IsNewStore","StLicense","Zhihangye","AreaId","StId","StParkSpace", "StBuinessHours",
				"Stzhuoyi", "Stmianji", "Stjiatong", "Sttaika",
				"Styilabao", "Stmengtie", "Sthaibao", "CompanyInfo", "BrandInfo",
				"StoreInfo", "ZhaoPai","AvgExpense","Other"};
		String[] clomstr = new String[clomname.length];
		
		clomstr[0] = "";
		clomstr[1] = "";
		clomstr[2] = "";
		clomstr[3] = "";
		clomstr[4] = "";
		clomstr[5] = "";
		clomstr[6] = "";
		clomstr[7] = "";
		clomstr[8] = "";
		clomstr[9] = "";
		clomstr[10] = "";
		clomstr[11] = "";
		clomstr[12] = "";
		clomstr[13] = "";
		clomstr[14] = "";
		clomstr[15] = "";
		clomstr[16] = "";
		clomstr[17] = "";
		clomstr[18] = "";
		clomstr[19] = "";
		clomstr[20] = "";
		clomstr[21] = "";
		clomstr[22] = "";
		clomstr[23] = "";
		clomstr[24] = "";
		clomstr[25] = "";
		clomstr[26] = "";
		clomstr[27] = "";
		clomstr[28] = "";
		clomstr[29] = "";
		clomstr[30] = "";
		clomstr[31] = "";
		clomstr[32] = "";
		clomstr[33] = "";
		clomstr[34] = "";
		clomstr[35] = "";
		

		DB.upload(ChengShiHuoDong1.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(ChengShiHuoDong1.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}