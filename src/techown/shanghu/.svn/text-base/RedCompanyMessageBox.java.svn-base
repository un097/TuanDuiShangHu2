package techown.shanghu;


import org.json.JSONObject;

import techown.shanghu.biaoge.ActionCode;
import techown.shanghu.biaoge.AlgorithmConvert;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetData;
import techown.shanghu.photo.TakePhotoActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * 合约签订公司信息录入
 */
public class RedCompanyMessageBox extends Activity {

	protected static final String DialogUtil = null;
	public static String CompanyLicense1,chengshi;
	DES des = new DES();
	private Button button2, add_button,button_shao;
	public static String isomux,com;
	public static String requeststr2;
	public static String xinhaishijiu;
	String[] province;
	public static String requeststr1;
	public static String[] tempstr1;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	public static int bs;
	GetData getDasta=new GetData();
	PadUtiltiy mPadUtiltiy;
	boolean ischeck=false;
	public static int num=0;//控制不让返回

	int[] textIds = { R.id.h1et1, R.id.h1et2, R.id.h1et3, R.id.redh1et4,
			R.id.h1et5, R.id.h1et6, R.id.h1et7,R.id.h1et8, R.id.h1et9,
			R.id.h1et10, R.id.redh1et11, R.id.h1et12, R.id.redh1et13, R.id.h1et14};
	public static TextView[] textArray;

	int[] linlayIds = { R.id.h1leout1, R.id.h1leout2, R.id.h1leout3,
			R.id.h1leout4, R.id.h1leout5, R.id.h1leout6, R.id.h1leout7,
			R.id.h1leout8, R.id.h1leout9, R.id.h1leout10, R.id.h1leout11,
			R.id.h1leout12, R.id.h1leout13, R.id.h1leout14};
	public LinearLayout[] linlay;
  
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703区,7021详细地址
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73区，74详细地址
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 501){
			if (data != null) {
				textArray[13].setText("Scan canceled!".equals(data.getStringExtra("data")) ? "" : data.getStringExtra("data"));
			}
		}else if(requestCode == 801){
			if (data != null) {
				String barcode = data.getStringExtra("text");
				textArray[13].setText(barcode);
			}
		}
		try{
			
			if(TuanDuiShangHuActivity.device == 0){
				
			
		        if(0 == requestCode && resultCode == RESULT_OK) {
		            if(null == data) {
		            } else {
		                final String text = data.getStringExtra("text");
		                
		                if(textArray[13]!=null)
		                	textArray[13].setText(text);
		            }
		        }
			}
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("团队条形码onActivityResult:"+e.getMessage());
		}
		
    }
	protected void onResume() {
		// TODO Auto-generated method stub
		try{
			super.onResume();
			if(ischeck)
			{
				if(textArray[13]!=null)
					if(mPadUtiltiy.GetBarcode()!=null){
						textArray[13].setText(mPadUtiltiy.GetBarcode());
					}
			}
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：团队条形码:"+e.getMessage());
		}
		
	}
	public static String cityname;
	public String companyId;
	String IsNewCompany;
	public String cgRedFiveCompanyCityId;

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.redcompanymessagebox);
		try{
		mPadUtiltiy = new PadUtiltiy(RedCompanyMessageBox.this);
		province = DB.klQuery(RedCompanyMessageBox.this, "Name", "t_province");
		
		ActionCode ac = new ActionCode();
		String barcode = ac.runVerifyCode(15);
		isomux = AlgorithmConvert.backtonum(barcode);
		
		h1et7 = (TextView) findViewById(R.id.h1et7);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
		h1et7021 = (TextView) findViewById(R.id.redh1et7021);
		
		h1leout71 = (LinearLayout)findViewById(R.id.h1leout71);
		h1leout72 = (LinearLayout)findViewById(R.id.h1leout72);
		h1leout73 = (LinearLayout)findViewById(R.id.h1leout73);
		h1leout74 = (LinearLayout)findViewById(R.id.h1leout74);
		
		button2 = (Button) findViewById(R.id.button2);
		add_button = (Button) findViewById(R.id.add_button);
		button_shao = (Button) findViewById(R.id.shao);

		button_shao.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				try{
//					if(TuanDuiShangHuActivity.device == 0){
//						Intent i = new Intent("cn.iandroid.codescan.ScanAndDecode");
//						i.putExtra("charset", (String) null);
//						startActivityForResult(i, 0);
//					}else if(TuanDuiShangHuActivity.device == 1){
						mPadUtiltiy.startBarCode();
						ischeck=true;
//					}
				}catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("团队条形码:"+e.getMessage());
				}
			}
			
		});
		
		linlay = new LinearLayout[linlayIds.length];
		for (int i = 0; i < linlayIds.length; i++) {
			linlay[i] = (LinearLayout) findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}

		button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(validate())
				{
					saveData();
					Intent intent = new Intent();
					intent.setClass(RedCompanyMessageBox.this, RedShopMessageBox.class);
					RedCompanyMessageBox.this.startActivity(intent);
					RedCompanyMessageBox.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				}
			}
		});
		
		MyDatabaseHelper myHelper = new MyDatabaseHelper(
				RedCompanyMessageBox.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from red_enterletter where id==?",
				new String[] { TuanDuiShangHuActivity.tmpId7 });
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
						.getColumnIndex("ManageName"))));
				h1et7021.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Adress"))));
				textArray[7].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("PostCode"))));
				textArray[8].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ManageScope"))));
				textArray[9].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Brand"))));
				textArray[10].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("BrandTelNumber"))));
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Validity"))));
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("EngageYear"))));
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("TreatyNumber"))));
				h1et703.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyArea"))));
				/*
				 * 记录从表中查询到的公司所在城市Id
				 */
				cgRedFiveCompanyCityId = des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyCity")));

				h1et702.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyCity"))));
				IsNewCompany=des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsNewCompany")));
				if(IsNewCompany.equals("N")){
					h1leout73.setEnabled(false);
					h1leout74.setEnabled(false);
					button_shao.setEnabled(false);
					for(int i = 0; i<linlayIds.length;i++){
						linlay[i].setEnabled(false);
		    		}
					companyId = des.jieMI(cursor.getString(cursor
							.getColumnIndex("CompanyId")));
				}
				
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("团队最红RedCompanyMessage:"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}if(db != null){
					db.close();
				}
			}
			//城市赋值
			String chengshiname = DB.queryCityname(RedCompanyMessageBox.this, h1et702.getText().toString());
			h1et702.setText(chengshiname);
			//省赋值
			String provinceid = DB.queryShengname(RedCompanyMessageBox.this, chengshiname);
			String privincename = DB.queryProvinceName(RedCompanyMessageBox.this, provinceid);
			h1et7.setText(privincename);
			//行政区域赋值
			String name=DB.klQueryCity1(this, "t_district", "where Id = '"
					+ des.jiaMi(h1et703.getText().toString()) + "'");
					h1et703.setText(name);
		}
		
		h1leout73.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				if(h1et7.getText().toString().equals("")){
					h1et703.setEnabled(false);
					
				}
			 else{
				if(h1et702.getText().toString().equals("无")){
					h1et703.setText("无");
					h1et703.setEnabled(false);
		}
				else {
				/*String[] str1 = getDasta.getCity11(RedCompanyMessageBox.this,
						h1et702.getText().toString());
				*/
				String[] str1 = getDasta.getCity12(RedCompanyMessageBox.this,
							cgRedFiveCompanyCityId); 
				if (str1.length > 0) {
					log.red_dialog(RedCompanyMessageBox.this, h1et703, "区",
							str1);
					
				}
				else if(h1et702.getText().toString().equals("无")){
					
				}else {
					h1et703.setText("无");
					h1et703.setEnabled(false);
				}
				
			}
			}
			}
		});
		
		
		h1leout74.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog(RedCompanyMessageBox.this, h1leout74,
						h1et7021, R.id.redh1et7021,"请输入注册地址");
			}
		});
		
		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(RedCompanyMessageBox.this, linlay[0],
							textArray[0], R.id.h1et1,"请输入公司名称");
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
					log.Dialog(RedCompanyMessageBox.this, linlay[1],
							textArray[1], R.id.h1et2,"请输入法人代表");
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
					log.Dialog(RedCompanyMessageBox.this, linlay[2],
							textArray[2], R.id.h1et3,"请输入联系人");
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
					log.etDialog(RedCompanyMessageBox.this,
							textArray[3], R.id.redh1et4,"请输入固定电话");
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
					log.etDialog(RedCompanyMessageBox.this,
							textArray[4], R.id.h1et5,"请输入手机号码");
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
					log.Dialog(RedCompanyMessageBox.this, linlay[5],
							textArray[5], R.id.h1et6,"请输入经营门店名称");
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
					log.etDialog(RedCompanyMessageBox.this, textArray[7],
							R.id.h1et8,"请输入邮编");
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
					log.Dialog(RedCompanyMessageBox.this, linlay[8],
							textArray[8], R.id.h1et9,"请输入经营范围");
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
					log.Dialog(RedCompanyMessageBox.this, linlay[9],
							textArray[9], R.id.h1et10,"请输入品牌");
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
					log.etDialog(RedCompanyMessageBox.this,
							textArray[10], R.id.redh1et11,"请输入品牌热线");
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
					DateDialog.showDatePicker(RedCompanyMessageBox.this,
							textArray[11], R.id.h1et12);
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
					log.etDialog(RedCompanyMessageBox.this,
							textArray[12], R.id.redh1et13,"请输入经营年限");
					break;
				default:
					break;
				}

				return false;
			}
		});


	}catch(Exception e){
		techown.shanghu.https.Log.Instance().WriteLog("最红RedCompanyMessage类："+e.getMessage());
	}
	
	}

	/**
	 * 此方法该类没有调用
	 */
	public void init()
    {
		if(!requeststr1.equals("")){
    	try
		{    
    		JSONObject demoJson1 = new JSONObject(requeststr1);
			if(demoJson1.getString("rspCode").equals("00"))
			{
				tempstr1=new String[19];
				tempstr1[0]=demoJson1.getString("name");//公司名
				tempstr1[1]=demoJson1.getString("companyId");//公司ID
				companyId = demoJson1.getString("companyId");//公司ID
				tempstr1[2]=demoJson1.getString("legalPerson");//法人
				tempstr1[3]=demoJson1.getString("contactPerson");//联系人
				tempstr1[4]=demoJson1.getString("contactTel");//联系电话
				tempstr1[5]=demoJson1.getString("handPhone");//手机	
				tempstr1[6]=demoJson1.getString("busiName");//经营名
				tempstr1[7]=demoJson1.getString("address");//注册地址
				tempstr1[8]=demoJson1.getString("busi");//经营范围
				tempstr1[9]=demoJson1.getString("brand");//品牌
				tempstr1[10]=demoJson1.getString("brandHotLine");//品牌热线
				tempstr1[11]=demoJson1.getString("zip");//邮编
				tempstr1[12]=demoJson1.getString("validDate");//有效期
				tempstr1[13]=demoJson1.getString("busiYear");//经营年限
				tempstr1[14]=demoJson1.getString("province");//省
				tempstr1[15]=demoJson1.getString("city");//市
				tempstr1[16]=demoJson1.getString("area");//区
				
				tempstr1[17]=demoJson1.getString("agrmId");
				tempstr1[18]=demoJson1.getString("agrmType");
				if(tempstr1[18].equals("02")&&tempstr1[17]!=null)
				{
					textArray[13].setText(tempstr1[17]);
					button_shao.setEnabled(false);
					DB.Red_upload(RedCompanyMessageBox.this, new String[]{"Dnum"}, new String[]{"1"},
					TuanDuiShangHuActivity.tmpId7);
				}
				else
				{
					String[] leixing = tempstr1[18].split("\\|");
					String[] tiaoxing=tempstr1[17].split("\\|");
					for (int i = 0; i < leixing.length; i++) {
						if(leixing[i].equals("02"))
						{
							textArray[13].setText(tiaoxing[i]);
							button_shao.setEnabled(false);
							DB.Red_upload(RedCompanyMessageBox.this, new String[]{"Dnum"}, new String[]{"1"},
							TuanDuiShangHuActivity.tmpId7);
						}
					}
				}
				DES des=new DES();
				
				String id = DB.klQueryCity1(this, "t_province", "where Id = '"
						+ des.jiaMi(tempstr1[14]) + "'");
				tempstr1[14] = id;

				String id1 = DB.klQueryCity1(this, "t_city", "where Id = '"
						+ des.jiaMi(tempstr1[15]) + "'");
				tempstr1[15] = id1;
				
				String id2 = DB.klQueryCity1(this, "t_district", "where Id = '"
						+ des.jiaMi(tempstr1[16]) + "'");
				tempstr1[16] = id2;
				
				textArray[0].setText(tempstr1[0]);//公司名
				textArray[1].setText(tempstr1[2]);//法人
				textArray[2].setText(tempstr1[3]);//联系人
				textArray[3].setText(tempstr1[4]);//联系电话
				textArray[4].setText(tempstr1[5]);//手机	
				textArray[5].setText(tempstr1[6]);//经营名
				textArray[7].setText(tempstr1[11]);//邮编
				textArray[8].setText(tempstr1[8]);//经营范围
				textArray[9].setText(tempstr1[9]);//品牌
				textArray[10].setText(tempstr1[10]);//品牌热线
				textArray[11].setText(tempstr1[12]);//有效期
				textArray[12].setText(tempstr1[13]);//经营年限
	    		
	    		h1et7.setText(id);
	    		h1et702.setText(id1);
	    		h1et703.setText(id2);
	    		h1et7021.setText(tempstr1[7]);
	    		h1leout73.setEnabled(false);
				h1leout74.setEnabled(false);
				for(int i=0;i<13;i++){
					linlay[i].setEnabled(false);
				}
			}else {
				DB.Red_upload(RedCompanyMessageBox.this, new String[]{"Dnum"}, new String[]{"2"},
						TuanDuiShangHuActivity.tmpId7);
			}
		
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队最红RedCompanyMessage:init()+"+e.getMessage());
		}	
		  }
    }
	
	
	String a = null;

	public void saveData() {
		try{
		String[] clomname = new String[] { "CompanyName", "LegalPerson",
				"ContactUser", "TelNumber", "MobileNumber", "ManageName",
				"Adress","PostCode","ManageScope", "Brand", "BrandTelNumber", 
				"Validity", "EngageYear", "TreatyNumber","CompanyId" ,"CompanyCity","CompanyArea"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[0].getText().toString();//公司名称
		clomstr[1] = textArray[1].getText().toString();//法人代表
		clomstr[2] = textArray[2].getText().toString();//联系人
		clomstr[3] = textArray[3].getText().toString();//固定电话
		clomstr[4] = textArray[4].getText().toString();//手机号码
		clomstr[5] = textArray[5].getText().toString();//经营门店名称
		clomstr[6] = h1et7021.getText().toString();//注册地址
		clomstr[7] = textArray[7].getText().toString();//P邮编
		clomstr[8] = textArray[8].getText().toString();//E经营范围
		clomstr[9] = textArray[9].getText().toString();//B品牌
		clomstr[10] = textArray[10].getText().toString();//T品牌热线
		clomstr[11] = textArray[11].getText().toString();//有效期
		clomstr[12] = textArray[12].getText().toString();//经营年限
		clomstr[13] = textArray[13].getText().toString();//条形码
		if(IsNewCompany.equals("N"))
		{
			clomstr[14] = companyId;//公司ID
		}
		else if(IsNewCompany.equals("Y"))
		{
			clomstr[14] = "";//公司ID
		}
		
		System.out.println("公司"+clomstr[14]);
		//clomstr[15] = h1et702.getText().toString();//公司所在城市ID
		clomstr[16] = h1et703.getText().toString();//公司所在城市区域
		DES des = new DES();
		
		//存入公司所在城市id
		/*String id = DB.klQueryCity(this, "t_city", "where Name = '"
				+ des.jiaMi(clomstr[15]) + "'");*/
		clomstr[15] = cgRedFiveCompanyCityId;

		String id1 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[16])+ "'"+" and CityId = '"+des.jiaMi(clomstr[15])+"'");
		clomstr[16] = id1;

		DB.Red_upload(RedCompanyMessageBox.this, clomname, clomstr,
				TuanDuiShangHuActivity.tmpId7);
		}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("最红RedCompanyMessage类SaveData（）"+e.getMessage());
		}
	}



	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(RedCompanyMessageBox.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	boolean m = true;

	// 验证必填字段的是否为空
	protected boolean validate() {
		if (textArray[0].getText().toString().equals("")) {
			log.Toast(RedCompanyMessageBox.this, "请输入公司名！");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			log.Toast(RedCompanyMessageBox.this, "请输入法人代表！");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			log.Toast(RedCompanyMessageBox.this, "请输入联系人！");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			log.Toast(RedCompanyMessageBox.this, "请输入固定电话！");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			log.Toast(RedCompanyMessageBox.this, "请输入门店名 ！");
			return false;
		}
		if (textArray[8].getText().toString().equals("")) {
			log.Toast(RedCompanyMessageBox.this, "请输入经营范围 ！");
			return false;
		}
		if (h1et7021.getText().toString().equals("")) {
			log.Toast(RedCompanyMessageBox.this, "请输入注册地址 ！");
			return false;
		}
		if(h1et7.getText().toString().equals("")){
			log.Toast(RedCompanyMessageBox.this, "公司所在省份不能为空！");
			return false;
		}
		if(h1et702.getText().toString().equals("")){
			log.Toast(RedCompanyMessageBox.this, "公司所在城市不能为空！");
			return false;
		}
		if(h1et703.getText().toString().equals("")){
			log.Toast(RedCompanyMessageBox.this, "请输入行政区！");
			return false;
		}
		if(textArray[13].getText().toString().equals(""))
		{
			log.Toast(RedCompanyMessageBox.this, "请扫描协议条形码！");
			return false;
		}
		return true;
	}
	public void saveData4() {
		String[] clomname = new String[] { "IsNewShop",
				"ShopId", "ShopLicense", "BusinessName", "BusinessNunber",
				"BusinessAdress", "ShopCode", "ShopTrade",
				"ShopGreens", "ShopDealer","Longitude","LatiTude","ShopUserName","ShopTelNumber","ShopMobileNumber" ,
				"StChannel","StIsReceiveBill","StValidity","StBillRate","IsNewPoster", "PosterID",
				"StParkSpace", "StBuinessHours", "Stzhuoyi", "Stmianji",
				"Stjiatong", "Sttaika", "Styilabao", "Stmengtie", "Sthaibao",
				"CompanyInfo", "ShopInfo","ZhaoPai","Other","ShopCity","ShopArea"};
		String[] clomstr = new String[clomname.length];
		
		clomstr[0] = "";//是否新建门店
		clomstr[1] = "";//门店ID
		clomstr[2] = "";//门店工商执照
		clomstr[3] = "";//营业名称
		clomstr[4] = "";//营业电话
		clomstr[5] = "";//营业地址
		clomstr[6] = "";//邮编
		clomstr[7] = "";//行业
		clomstr[8] = "";//菜系
		clomstr[9] = "";//商圈
		clomstr[10] = "";//经度
		clomstr[11] = "";//纬度
		clomstr[12] = "";//联系人
		clomstr[13] = "";//电话
		clomstr[14] = "";//手机
		clomstr[15] = "";//拓展渠道
		clomstr[16] = "";//是否收单
		clomstr[17] = "";//有效期
		clomstr[18] = "";//收单费率
		clomstr[19] = "";//是否新建广宣
		clomstr[20] = "";//门店ID
		clomstr[21] = "";//停车位
		clomstr[22] = "";//营业时间
		clomstr[23] = "";//椅套
		clomstr[24] = "";//面积
		clomstr[25] = "";//交通情况
		clomstr[26] = "";//台卡
		clomstr[27] = "";//易拉宝
		clomstr[28] = "";//门贴
		clomstr[29] = "";//海报
		clomstr[30] = "";//公司介绍
		clomstr[31] = "";//门店介绍
		clomstr[32] = "";//招牌菜
		clomstr[33] = "";//其他
		clomstr[34] = "";//门店所属城市
		clomstr[35] = "";//门店所属行政区
		

		DB.Red_upload(RedCompanyMessageBox.this, clomname, clomstr, TuanDuiShangHuActivity.tmpId7);
	}
}
