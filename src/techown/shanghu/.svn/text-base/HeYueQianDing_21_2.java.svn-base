package techown.shanghu;

import org.json.JSONObject;

import techown.shanghu.biaoge.ActionCode;
import techown.shanghu.biaoge.AlgorithmConvert;
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
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * （草稿箱）拒件--合约签订公司信息录入
 */
public class HeYueQianDing_21_2 extends Activity {

	public String cgRefuseCompanyCityId;//本页面的城市id

	protected static final String DialogUtil = null;

	private Button button2, button3,button_shao;
	public static String isomux;
	public static String requeststr2;
	String[] province;
	public static String requeststr1;
	public static String[] tempstr1;
	GetData getDasta=new GetData();
	public EditDialog dialog = new EditDialog();
	DBUtil DB=new DBUtil();
	// private EditText edtext1,edtext2,edtext3,edtext4,edtext5,edtext6,edtext7
	// ,edtext8,edtext9,edtext10,edtext11,edtext12,edtext13;
	// String name1, name2, name3, name4, name5, name6, name7, name8, name9,
	// name10, name11, name12, name13;

	TextView h1et1,h1et2,h1et3,h1et4,h1et5,h1et6,h1et8,h1et9,h1et10,h1et11,h1et12,h1et13,h1et14;
	
	
	int[] textIds = { R.id.h1et1, R.id.h1et2, R.id.h1et3, R.id.h1et4,
			R.id.h1et5, R.id.h1et6, R.id.h1et7,R.id.h1et8, R.id.h1et9,
			R.id.h1et10, R.id.h1et11, R.id.h1et12, R.id.h1et13, R.id.h1et14 };
	public static TextView[] textArray;

	int[] linlayIds = { R.id.h1leout1, R.id.h1leout2, R.id.h1leout3,
			R.id.h1leout4, R.id.h1leout5, R.id.h1leout6, R.id.h1leout7,
			R.id.h1leout8, R.id.h1leout9, R.id.h1leout10, R.id.h1leout11,
			R.id.h1leout12, R.id.h1leout13, R.id.h1leout14, };
	public LinearLayout[] linlay;
  
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703区,7021详细地址
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73区，74详细地址
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try{
			
			if(TuanDuiShangHuActivity.device == 0){
				
			
		        if(0 == requestCode && resultCode == RESULT_OK) {
		            if(null == data) {
		              //  showDecodeResult(null, null);
		            } else {
		                final String text = data.getStringExtra("text");
		                final String format = data.getStringExtra("format");
		                
		                if(textArray[13]!=null)
		                	textArray[13].setText(text);
		            //    showDecodeResult(text, format);
		            }
		        }
			}
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("团队条形码onActivityResult:"+e.getMessage());
		}
		
    }
	
	public static String cityname;
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heyueqianding_2);

		// 调用侧边栏
		// panel = new Panel(this);
		try{
		province = DB.klQuery(HeYueQianDing_21_2.this, "Name", "t_province");
		
		ActionCode ac = new ActionCode();
		String barcode = ac.runVerifyCode(15);
		isomux = AlgorithmConvert.backtonum(barcode);
		
		h1et1 = (TextView) findViewById(R.id.h1et1);
		h1et2 = (TextView) findViewById(R.id.h1et2);
		h1et3 = (TextView) findViewById(R.id.h1et3);
		h1et4 = (TextView) findViewById(R.id.h1et4);
		h1et5 = (TextView) findViewById(R.id.h1et5);
		h1et6 = (TextView) findViewById(R.id.h1et6);
		h1et7 = (TextView) findViewById(R.id.h1et7);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
		h1et7021 = (TextView) findViewById(R.id.h1et7021);
		
		h1et8 = (TextView) findViewById(R.id.h1et8);
		h1et9 = (TextView) findViewById(R.id.h1et9);
		h1et10 = (TextView) findViewById(R.id.h1et10);
		h1et11 = (TextView) findViewById(R.id.h1et11);
		h1et12 = (TextView) findViewById(R.id.h1et12);
		h1et13 = (TextView) findViewById(R.id.h1et13);
		
		
		
		h1leout71 = (LinearLayout)findViewById(R.id.h1leout71);
		h1leout72 = (LinearLayout)findViewById(R.id.h1leout72);
		h1leout73 = (LinearLayout)findViewById(R.id.h1leout73);
		h1leout74 = (LinearLayout)findViewById(R.id.h1leout74);
		
		// button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button2.setVisibility(4);
		button3 = (Button) findViewById(R.id.button3);
		button_shao = (Button) findViewById(R.id.shao);
		

//		button_shao.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				 Intent it = new Intent("cn.iandroid.codescan.ScanAndDecode");
//			        it.putExtra("charset", (String)null);
//			        startActivityForResult(it, 0);
//			}
//		});
		
		button_shao.setEnabled(false);
		
		// linerlout=(LinearLayout)findViewById(R.id.h1leout);
		linlay = new LinearLayout[linlayIds.length];
		for (int i = 0; i < linlayIds.length; i++) {
			linlay[i] = (LinearLayout) findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}

				// 设置监听
		MyLis lis = new MyLis();
		// button1.setOnClickListener(lis);
//		button2.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				intent.setClass(HeYueQianDing_2_2.this, HeYueQianDing_1_1.class);
//				HeYueQianDing_2_2.this.startActivity(intent);
//				HeYueQianDing_2_2.this.finish();
//			}
//		});
		
		
		button3.setOnClickListener(lis);

		DES des = new DES();SQLiteDatabase db=null;Cursor cursor=null;
		try{
		MyDatabaseHelper myHelper = new MyDatabaseHelper(
				HeYueQianDing_21_2.this, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.tmpId2 });
		while (cursor.moveToNext()) {
			
				
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
				h1et7021.setText(des.jieMI(cursor.getString(cursor
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
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ContractNumber"))));
				h1et702.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Gcityid"))));
				/**
				 * 记录此页面城市Id
				 */
				cgRefuseCompanyCityId=des.jieMI(cursor.getString(cursor
						.getColumnIndex("Gcityid")));
				
				h1et703.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Gdistrictid"))));
			
//				String name=DB.klQueryCity1(this, "t_district", "where Id = '"
//				+ h1et703.getText().toString() + "'");
//				h1et703.setText(name);
				
				String Dnum=des.jieMI(cursor.getString(cursor
						.getColumnIndex("Dnum")));
				if(Dnum.equals("1")){
					h1et703.setEnabled(false);
				}
					
					for(int i = 0; i<linlayIds.length;i++){
						linlay[i].setEnabled(false);
		    		}
									
		}
		
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing21_2:"+e.getMessage());
		}finally{
			if(cursor != null){
				cursor.close();
			}if(db != null){
				db.close();
			}
		}
		//城市名称
		String cityname=DB.klQueryCity1(this, "t_city", "where Id = '"
				+ des.jiaMi(h1et702.getText().toString()) + "'");
		h1et702.setText(cityname);
		//省份名称
		String shengid = DB.queryShengname(HeYueQianDing_21_2.this, h1et702.getText().toString());
		String shengname = DB.queryProvinceName(HeYueQianDing_21_2.this, shengid);
		h1et7.setText(shengname);
		//区域名称
		String name=DB.klQueryCity1(this, "t_district", "where Id = '"
				+ des.jiaMi(h1et703.getText().toString()) + "'");
				h1et703.setText(name);
		
		if(HeYueQianDing_1.a==1){
			h1leout73.setEnabled(false);
			h1leout74.setEnabled(false);
			for(int i=0;i<13;i++){
				linlay[i].setEnabled(false);
			}
		}
		
		else if(HeYueQianDing_1.a==2){
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
				/*String[] str1 = getDasta.getCity11(HeYueQianDing_21_2.this,
						h1et702.getText().toString());
				*/
				String[] str1 = getDasta.getCity12(HeYueQianDing_21_2.this,
							cgRefuseCompanyCityId); 
				if (str1.length > 0) {
					dialog.dialog(HeYueQianDing_21_2.this, h1et703, "区",
							str1);
					
				}
				else if(h1et702.getText().toString().equals("无")){
					
				}else {
					h1et703.setText("无");
					h1et703.setEnabled(false);
				}
				
//				dialog.Dialog(HeYueQianDing_2.this, h1leout73,
//						h1et7021, R.id.h1et1);
			}
			}
			}
		});
		
		
		h1leout74.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.Dialog(HeYueQianDing_21_2.this, h1leout74,
						h1et7021, R.id.h1et7021,"请输入注册地址");
			}
		});
		
		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					dialog.Dialog(HeYueQianDing_21_2.this, linlay[0],
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
					dialog.Dialog(HeYueQianDing_21_2.this, linlay[1],
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
					dialog.Dialog(HeYueQianDing_21_2.this, linlay[2],
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
					dialog.etDialog(HeYueQianDing_21_2.this,
							textArray[3], R.id.h1et4,"请输入固定电话");
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
					dialog.etDialog(HeYueQianDing_21_2.this,
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
					dialog.Dialog(HeYueQianDing_21_2.this, linlay[5],
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
////					dialog.Dialog(HeYueQianDing_2.this, linlay[6],
////							textArray[6], R.id.h1et7);
//					h1et702.setEnabled(true);
//					h1et702.setText("");
//					dialog.istwo = true;
//					dialog.num = 0;
//					dialog.dialog(HeYueQianDing_2.this, h1et7, "省",
//							province);
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
					dialog.etDialog(HeYueQianDing_21_2.this, textArray[7],
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
					dialog.Dialog(HeYueQianDing_21_2.this, linlay[8],
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
					dialog.Dialog(HeYueQianDing_21_2.this, linlay[9],
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
					dialog.etDialog(HeYueQianDing_21_2.this,
							textArray[10], R.id.h1et11,"请输入品牌热线");
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
					DateDialog.showDatePicker(HeYueQianDing_21_2.this,
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
					dialog.etDialog(HeYueQianDing_21_2.this,
							textArray[12], R.id.h1et13,"请输入经营年限");
					break;
				default:
					break;
				}

				return false;
			}
		});
//		linlay[13].setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				switch (arg1.getAction()) {
//				case MotionEvent.ACTION_DOWN:
//					dialog.Dialog(HeYueQianDing_21_2.this, linlay[13],
//							textArray[13], R.id.h1et14);
//					break;
//				default:
//					break;
//				}
//
//				return false;
//			}
//		});
		}
		//init();
	}catch(Exception e){
		techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_21_2类："+e.getMessage());
	}
	}

	
	
	
	
	String a = null;

	public void saveData() {
		try{
		String[] clomname = new String[] { "CompanyName", "LegalPerson",
				"ContactUser", "TelNumber", "MobileNumber", "EngageName",
				"Adress","PostCode","EngageScope", "Brand", "BrandTelNumber", 
				"Validity", "EngageYear", "ContractNumber","Gcityid","Gdistrictid"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[0].getText().toString();
		clomstr[1] = textArray[1].getText().toString();
		clomstr[2] = textArray[2].getText().toString();
		clomstr[3] = textArray[3].getText().toString();
		clomstr[4] = textArray[4].getText().toString();
		clomstr[5] = textArray[5].getText().toString();
		clomstr[6] = h1et7021.getText().toString();
		clomstr[7] = textArray[7].getText().toString();//P
		clomstr[8] = textArray[8].getText().toString();//E
		clomstr[9] = textArray[9].getText().toString();//B
		clomstr[10] = textArray[10].getText().toString();//T
		clomstr[11] = textArray[11].getText().toString();
		clomstr[12] = textArray[12].getText().toString();
		clomstr[13] = textArray[13].getText().toString();
		
		System.out.println(textArray[13].getText().toString());
		//clomstr[14] = h1et702.getText().toString();
		clomstr[15] = h1et703.getText().toString();
		DES des=new DES();
		/*String id = DB.klQueryCity(this, "t_city", "where Name = '"
				+ des.jiaMi(clomstr[14]) + "'");*/
		clomstr[14] = cgRefuseCompanyCityId;

		String id1 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[15]) + "'"+" and CityId = '"+des.jiaMi(clomstr[14])+"'");
		System.out.println("id1==="+id1);
		clomstr[15] = id1;

		DB.upload(HeYueQianDing_21_2.this, clomname, clomstr,
				TuanDuiShangHuActivity.tmpId2);
		}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_21_2类：saveData()"+e.getMessage());
		}

	}

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch (id) {
			case R.id.button3:
				try{
				saveData();
				intent.setClass(HeYueQianDing_21_2.this, HeYueQianDing_31_3.class);
				HeYueQianDing_21_2.this.startActivity(intent);
				HeYueQianDing_21_2.this.finish();
				overridePendingTransition(R.anim.leftin, R.anim.leftout);
				}catch(Exception e){
					techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing类：button3点击"+e.getMessage());

				}
				break;
				}
			}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			dialog.ExitApp(HeYueQianDing_21_2.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	boolean m = true;

	// 验证必填字段的是否为空
	protected boolean validate() {
		if (textArray[0].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_21_2.this, "请输入公司名！");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_21_2.this, "请输入法人代表！");
			return false;
		}
//		if (textArray[2].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_21_2.this, "请输入联系人！");
//			return false;
//		}
//		if (textArray[3].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_21_2.this,  "请输入固定电话！");
//			return false;
//		}
		if (textArray[5].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_21_2.this, "请输入门店名 ！");
			return false;
		}
		if (h1et7021.getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_21_2.this, "请输入注册地址 ！");
			return false;
		}
//		if (textArray[7].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_21_2.this,  "请输入邮编！");
//			return false;
//		}
//		if (textArray[8].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_21_2.this, "请输入经营范围 ！");
//			return false;
//		}

//		if (textArray[9].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_21_2.this, "请输入品牌！");
//			return false;
//		}

		if (textArray[13].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_21_2.this, "请输入条形码 ！");
			return false;
		}

		return true;
	}

}
