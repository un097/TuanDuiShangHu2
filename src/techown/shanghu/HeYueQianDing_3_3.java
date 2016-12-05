package techown.shanghu;

import java.util.ArrayList;
import java.util.List;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetData;
import techown.shanghu.date.TArea;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * 合约签订门店信息录入（草稿箱）
 */
public class HeYueQianDing_3_3 extends Activity {
	public static String requeststr3;
	public static String jsont;
	public EditDialog dialog = new EditDialog();
	DBUtil DB=new DBUtil();
	String[] typCode =new String[2];
	Button button2,button3,button_jwd;
	GetData getDasta=new GetData();
	int[] textIds = { R.id.h2edtext1, R.id.h2edtext2, R.id.h2edtext3, R.id.h2edtext4,
			R.id.h2edtext5, R.id.h2edtext6, R.id.h2edtext7, R.id.h2edtext8, R.id.h2edtext9,
			R.id.h2edtext10, R.id.h2edtext11, R.id.h2edtext12, R.id.h2edtext13, R.id.h2edtext14,
			R.id.h2edtext15,R.id.h2edtext16,R.id.h2edtext17,R.id.h2edtext18,R.id.h2edtext19,
			R.id.h2edtext222,R.id.h2edtext20,R.id.h2edtext21,
			R.id.sparetext1, R.id.sparetext2, R.id.sparetext3, R.id.sparetext4};
	public static TextView[] textArray;

	int[] linlayIds = { R.id.h3leout1, R.id.h3leout2, R.id.h3leout3,
			R.id.h3leout4, R.id.h3leout5, R.id.h3leout6, R.id.h3leout7,
			R.id.h3leout8, R.id.h3leout9, R.id.h3leout10, R.id.h3leout11,R.id.h3leout12,
			R.id.h3leout13, R.id.h3leout14, R.id.h3leout15,
			R.id.h3leout16, R.id.h3leout17, R.id.h3leout18, R.id.h3leout19,
			R.id.h3leout20,R.id.h3leout21, R.id.spareout1, R.id.spareout2,
			R.id.spareout3, R.id.spareout4 };

	public static LinearLayout[] linlay;
   
	private LinearLayout h3leout9,h3leout10;
	List<TextView> ls=new ArrayList<TextView>();
	List<TextView> ls1=new ArrayList<TextView>();
	
	String[] t_trading, t_category, t_childtyp, t_category1;

	public static String merName;
	public static String contactTel;
	public static String address;
	public static String contacter;
	public static String misId;
	public static String terId;
	
	public List list;
	static List<String> s;
	static List<String> s1;
	static List<String> s2;
	static List<String> s3;

	DES des = new DES();
	public  static Object[] name;
	//GPS
	double longitude1 = 0;
	double latitude1 = 0;
	boolean isGetGps = false;
	LocationManager locationManager = null;
	LocationListener llistener;
	String provider;
	Button getGpsBtn;
	List<TArea> tareaList = null;
	String[][] typename;
	String[] aa;
	String[] bb;
	public static TextView h1et7,h1et702,h1et703,h1et7021,h2edtext222;//703区,7021详细地址
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73区，74详细地址
//	String[] province;
	String ccc;
	public String caoGaoMenInfoCityId;

    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heyueqianding_3);
		
        try{
		
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);


        typename = DB.klQuery21(HeYueQianDing_3_3.this, "typeCode", "typeNum", "t_mertype");
        bb =new String[typename.length];
 		for (int i = 0; i < typename.length; i++) {
 			bb[i] = typename[i][0];
 			System.out.println(bb[i]);
 		}


		/*String cityName = queryCityName(TuanDuiShangHuActivity.username);
		final String[] t_trading1 = queryTradingName(cityName);*/

		
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);

		linlay = new LinearLayout[linlayIds.length];
		for (int i = 0; i < linlayIds.length; i++) {
			linlay[i] = (LinearLayout) findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		
		
		h1leout72 = (LinearLayout)findViewById(R.id.h1leout72);
		h1leout73 = (LinearLayout)findViewById(R.id.h1leout73);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
		h2edtext222 = (TextView) findViewById(R.id.h2edtext222);
		
		
		Context friendContext = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		/*try {
			friendContext = HeYueQianDing_3_3.this.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper1 = new MyDatabaseHelper(friendContext, "techown_login.db",
						Constant.loginDBVer);		
			sld = myHelper1.getWritableDatabase();
			cur = sld.query("t_city",new String[]{"Name"},"Name like ?"
					, new String[]{des.jiaMi(TuanDuiShangHuActivity.city)+"%"}, null, null, null);
			while (cur.moveToNext()) {
				cur.moveToLast();
				ccc = des.jieMI(cur.getString(0));

			}
		
		}
		catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing3_3:"+e.getMessage());
		}finally{
			if(cur != null){
				cur.close();
			}if(sld != null){
				sld.close();
			}
		}*/
		
		
//		h1et702.setText(ccc);

		//lxp
		linlay[18].setEnabled(false);
		linlay[6].setEnabled(false);
		// lxp
		
		MyLis lis = new MyLis();

		button2.setOnClickListener(lis);
		button3.setOnClickListener(lis);

//        button_jwd.setOnClickListener(lis);
        
        

		
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				HeYueQianDing_3_3.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.tmpId1 });
		while (cursor.moveToNext()) {
			try {
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyName"))));
				textArray[1].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StName"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stel"))));
				textArray[3].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StAdress"))));
				textArray[4].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StPostCode"))));
				textArray[5].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StHangye"))));
				textArray[6].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StCaixi"))));
				textArray[7].setText(des.jieMI(cursor.getString(cursor      
						.getColumnIndex("StShangquan"))));
				textArray[8].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StNumber"))));
				textArray[9].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StClientNumber"))));
				textArray[10].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stlong"))));   
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stlat"))));
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StContact"))));
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StMobileNumber"))));
				textArray[15].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StChannel"))));// 拓展渠道
				textArray[16].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StIsReceiveBill"))));// 是否收单
				textArray[17].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StValidity"))));
				textArray[18].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StBillRate"))));
				textArray[14].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StLicense"))));
				textArray[19].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Zhihangye"))));//子行业
				textArray[20].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("XuanChuan"))));
				textArray[21].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("PiceXuanChuan"))));
				
				textArray[22].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeia"))));//注备1
				textArray[23].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeib"))));//注备2
				textArray[24].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeic"))));//注备3
				textArray[25].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeid"))));//注备4

				if (textArray[15].getText().toString().equals("W")) {
					textArray[15].setText("外包");
				} else if (textArray[15].getText().toString().equals("D")) {
					textArray[15].setText("办事处");
				} else if (textArray[15].getText().toString().equals("B")) {
					textArray[15].setText("分行");
				} else if (textArray[15].getText().toString().equals("C")) {
					textArray[15].setText("卡中心");
				}

				if (textArray[16].getText().toString().equals("Y")) {
					textArray[16].setText("是");
				} else if (textArray[16].getText().toString().equals("N")) {
					textArray[16].setText("否");
				}
				
				h1et703.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("AreaId"))));
				caoGaoMenInfoCityId = des.jieMI(cursor.getString(cursor
						.getColumnIndex("CityId")));
				h1et702.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CityId"))));
				//城市名称
				
//				String name=DB.klQueryCity1(this, "t_district", "where Id = '"
//				+ h1et703.getText().toString() + "'");
//				h1et703.setText(name);
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing3_3:"+e.getMessage());
			}finally{
				if(cursor!=null)
					cursor.close();
				if(db!=null)
					db.close();
			}
			String chengshiname = DB.queryCityname(HeYueQianDing_3_3.this, h1et702.getText().toString());
			h1et702.setText(chengshiname);
			String id = DB.klQueryCity1(this, "t_category",
					"where Id = '" + des.jiaMi(textArray[5].getText().toString())
							+ "'");
			textArray[5].setText(id);

			String id1 = DB.klQueryCity1(this, "t_childtyp",
					"where Id = '" + des.jiaMi(textArray[6].getText().toString())
							+ "'");
			textArray[6].setText(id1);
			String id2 = DB.klQueryCity1(this, "t_trading",
					"where Id = '" + des.jiaMi(textArray[7].getText().toString())
							+ "'");
			textArray[7].setText(id2);
			String id3 = DB.klQueryCity1(this, "t_category",
					"where Id = '" + des.jiaMi(textArray[19].getText().toString())
							+ "'");
			textArray[19].setText(id3);
			
			String name=DB.klQueryCity1(this, "t_district", "where Id = '"
					+ des.jiaMi(h1et703.getText().toString()) + "'");
			h1et703.setText(name);
			
			String id5=DB.klQueryCity11(this, "t_mertype", "where typeName = '"
					+ des.jiaMi(textArray[20].getText().toString()) + "'");
			textArray[20].setText(id5);
		}
        
        
		//init();       
		
		

		h1leout72.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(TuanDuiShangHuActivity.agencycityname.length!=0&&TuanDuiShangHuActivity.agencycityname!=null)
				{
					dialog.dialog(HeYueQianDing_3_3.this, h1et702, "市",
							TuanDuiShangHuActivity.agencycityname);
					textArray[7].setText("");
				}
			}
		});
		
		h1leout73.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!h1et702.getText().toString().equals("")&&h1et702.getText().toString()!=null)
				{
					/*String[] str1 = getDasta.getCity11(HeYueQianDing_3_3.this,
							h1et702.getText().toString());*/
					/*
					 * 记录选择的城市Id
					 */
					caoGaoMenInfoCityId = (String)TuanDuiShangHuActivity.agencyCityAndIdMap
							.get(h1et702.getText().toString());
					String[] str1 = getDasta.getCity12(HeYueQianDing_3_3.this,
							caoGaoMenInfoCityId);
					if (str1.length > 0) {
						dialog.dialog(HeYueQianDing_3_3.this, h1et703, "区",
								str1);
						
					} else {
						h1et703.setText("无");
						h1et703.setEnabled(false);
					}
				}
				else
				{
					dialog.Toast(HeYueQianDing_3_3.this, "请选择城市！");
				}
			}
		});
		
		
		
		


		linlay[4].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.etDialog(HeYueQianDing_3_3.this, textArray[4],
							R.id.h2edtext5,"请输入邮编");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[5].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					if(name!=null)
					{
					// lxp
					dialog.istwo = true;
					dialog.num = 8;
					// lxp
					 dialog.dialog22222(HeYueQianDing_3_3.this, textArray[5],"主行业", name);
					 h2edtext222.setText("");
					 //dialog.dialog(HeYueQianDing_3.this,textArray[5],"行业",t_category7);
					}
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
				case MotionEvent.ACTION_UP:
					dialog.dialog(HeYueQianDing_3_3.this, textArray[6], "菜系",
							t_childtyp);
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
				case MotionEvent.ACTION_UP:
					if(h1et702.getText().toString().length()>0)
					{
						//final String[] t_trading1 = queryTradingName(h1et702.getText().toString());
						String[] t_trading1 = queryTradingNameByCityId(caoGaoMenInfoCityId);
						if(t_trading1!=null&&t_trading1.length>0)
						{
							dialog.dialog(HeYueQianDing_3_3.this, textArray[7], "商圈",
									t_trading1);
						}
						else
						{
							dialog.Toast(HeYueQianDing_3_3.this, "该城市没有商圈！");
						}
					}
					else
					{
						dialog.Toast(HeYueQianDing_3_3.this, "请先选择城市！");
					}
					break;
				default:
					break;
				}

				return false;
			}
		});
//		linlay[8].setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				switch (arg1.getAction()) {
//				case MotionEvent.ACTION_UP:
//					dialog.etDialog(HeYueQianDing_3.this, textArray[8],
//							R.id.h2edtext9);
//					break;
//				default:
//					break;
//				}
//
//				return false;
//			}
//		});
//		linlay[9].setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				switch (arg1.getAction()) {
//				case MotionEvent.ACTION_UP:
//					dialog.etDialog(HeYueQianDing_3.this, textArray[9],
//							R.id.h2edtext10);
//					break;
//				default:
//					break;
//				}
//
//				return false;
//			}
//		});
		linlay[10].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.etDialog(HeYueQianDing_3_3.this, textArray[10],
							R.id.h2edtext11,"请输入经度");
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
				case MotionEvent.ACTION_UP:
//					dialog.etDialog(HeYueQianDing_3.this, textArray[11],
//							R.id.h2edtext12);
					dialog.etDialog(HeYueQianDing_3_3.this, textArray[11],R.id.h2edtext12,"请输入纬度");
					break;
				default:
					break;
				}

				return false;
			}
		});
//		linlay[12].setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				switch (arg1.getAction()) {
//				case MotionEvent.ACTION_UP:
////					dialog.Dialog(HeYueQianDing_3.this, linlay[12],textArray[12], R.id.h2edtext13);
//					dialog.Dialog(HeYueQianDing_3.this,linlay[12],textArray[12],R.id.h2edtext13);
//					break;
//				default:
//					break;
//				}
//
//				return false;
//			}
//		});
		linlay[13].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.etDialog(HeYueQianDing_3_3.this, textArray[13],
							R.id.h2edtext14,"请输入手机号");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[14].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.etDialog(HeYueQianDing_3_3.this, textArray[14],
							R.id.h2edtext15,"请输入门店工商执照");
					break;
				default:
					break;
				}

				return false;
			}
		});
        final String[] province1={"办事处","外包","分行","卡中心"};
        linlay[15].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					// dialog.Dialog(HeYueQianDing_3.this,linlay[15],
					// textArray[15],R.id.h2edtext16);
					dialog.dialog(HeYueQianDing_3_3.this, textArray[15],
							"类型", province1);
					break;
				default:
					break;
				}

				return false;
			}
		});

        final String[] province={"是","否"};
        linlay[16].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					// dialog.Dialog(HeYueQianDing_3.this,linlay[16],
					// textArray[16],R.id.h2edtext17);
					// lxp
					dialog.istwo = true;
					dialog.num = 7;
					// lxp
					dialog.dialog(HeYueQianDing_3_3.this, textArray[16],
							"类型", province);
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[17].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					DateDialog.showDatePicker(HeYueQianDing_3_3.this,
							textArray[17], R.id.h2edtext18);
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[18].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.etDialog(HeYueQianDing_3_3.this, textArray[18],
							R.id.h2edtext19,"请输入收单费率");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[19].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.dialog(HeYueQianDing_3_3.this, textArray[20],
							"类型", bb);
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[20].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.Dialog(HeYueQianDing_3_3.this, linlay[20],textArray[21],
							R.id.h2edtext21,"请输入白名单编号");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[21].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.Dialog(HeYueQianDing_3_3.this, linlay[21],
							textArray[22], R.id.sparetext1, "请输入注备1");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[22].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.Dialog(HeYueQianDing_3_3.this, linlay[22],
							textArray[23], R.id.sparetext2, "请输入注备2");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[23].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.Dialog(HeYueQianDing_3_3.this, linlay[23],
							textArray[24], R.id.sparetext3, "请输入注备3");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[24].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					dialog.Dialog(HeYueQianDing_3_3.this, linlay[24],
							textArray[25], R.id.sparetext4, "请输入注备4");
					break;
				default:
					break;
				}

				return false;
			}
		});
        new Thread(){
			 public void run(){
				 get();
			 }
		}.start();
        
    }catch(Exception e){
    	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_3_3类："+e.getMessage());
    }
    }
    
    public void init()
    {
    	try{
    	h3leout9 = (LinearLayout) findViewById(R.id.h3leout9);
    	h3leout10 = (LinearLayout) findViewById(R.id.h3leout10);

    	textArray[1].setText(merName);//商户名称
		textArray[2].setText(contactTel);//商户联系方式
		textArray[3].setText(address);//商户地址
		textArray[12].setText(contacter);//联系人

    		
   	
						String[] arrg = misId.split("\\|");//商户mis号
						textArray[8].setText(arrg[0]);
				    	for (int i = 1; i < arrg.length; i++) {
				    		
				    		View view = LayoutInflater.from(HeYueQianDing_3_3.this).inflate(
									R.layout.cim_layout_list6, null);
							view.setLayoutParams(new LinearLayout.LayoutParams(
									LayoutParams.FILL_PARENT, 50));
							h3leout9.addView(view);
							TextView tv=(TextView)view.findViewById(R.id.h2edtext9);
							ls.add(tv);
							ls.get(i-1).setText(arrg[i]);
				    	}

						String[] errg = terId.split("\\|");//终端号
				    	textArray[9].setText(errg[0]);
				    	for (int i = 1; i < errg.length; i++) {
				    		
				    		View view = LayoutInflater.from(HeYueQianDing_3_3.this).inflate(
									R.layout.cim_layout_list5, null);
							view.setLayoutParams(new LinearLayout.LayoutParams(
									LayoutParams.FILL_PARENT, 50));
							h3leout10.addView(view);
							TextView tv=(TextView)view.findViewById(R.id.h2edtext10);
							ls1.add(tv);
							ls1.get(i-1).setText(errg[i]);
				    	}
    	}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_3_3类init()"+e.getMessage());

    	}
    }
    
    


	class MyLis implements OnClickListener {

		public void onClick(View v) {
			int id = v.getId();
			Intent intent = new Intent();
			try{
			switch (id) {
			case R.id.button3:
				
				if (validate()) {
					saveData();
					intent.setClass(HeYueQianDing_3_3.this, HeYueQianDing_4_4.class);
					HeYueQianDing_3_3.this.startActivity(intent);
					HeYueQianDing_3_3.this.finish();
					overridePendingTransition(R.anim.leftin, R.anim.leftout);
				}
				break;

				case R.id.button2:
			
				intent.setClass(HeYueQianDing_3_3.this, HeYueQianDing_2_2.class);
				HeYueQianDing_3_3.this.startActivity(intent);
				HeYueQianDing_3_3.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
				
			}
			}catch(Exception e){
				techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_3_3类button3,button2点击："+e.getMessage());
			}
		}
	}

	public String zihangye(String ziname)
	{
		String ZiId = "";
		for (int i = 0; i < EditDialog.list.size(); i++) {
			if(EditDialog.list.get(i).equals(ziname))
			{
				ZiId = EditDialog.listId.get(i);
			}
		}
		return ZiId;
	}
	
	public String zhuhangye(String zhuname)
	{
		String ZhuId = "";
		for (int i = 0; i < s1.size(); i++) {
			if(s1.get(i).equals(zhuname))
			{
				ZhuId = s.get(i);
			}
		}
		return ZhuId;
	}
	
	
	public void saveData() {
		try{
		String[] clomname = new String[] { "StName", "Stel", "StAdress",
				"StPostCode", "StHangye", "StCaixi", "StShangquan", "StNumber",
				"StClientNumber", "Stlong", "Stlat", "StContact",
				"StMobileNumber", "StChannel", "StIsReceiveBill", "StValidity",
				"StBillRate", "StLicense","Zhihangye","AreaId","XuanChuan","PiceXuanChuan","CityId",
				"ShopZhubeia","ShopZhubeib", "ShopZhubeic", "ShopZhubeid"};
		String[] clomstr = new String[clomname.length];

		clomstr[0] = textArray[1].getText().toString();//名称
		clomstr[1] = textArray[2].getText().toString();//电话
		clomstr[2] = textArray[3].getText().toString();//地址
		clomstr[3] = textArray[4].getText().toString();//邮编
		String Zhuhangye = zhuhangye(textArray[5].getText().toString());
		System.out.println("主行业："+Zhuhangye);
		clomstr[4] = Zhuhangye;//行业
		clomstr[5] = textArray[6].getText().toString();//菜系
		clomstr[6] = textArray[7].getText().toString();//商圈
		clomstr[7] = textArray[8].getText().toString();//商户编号
		clomstr[8] = textArray[9].getText().toString();//终端号
		clomstr[9] = textArray[10].getText().toString();//经度
		clomstr[10] = textArray[11].getText().toString();//纬度
		clomstr[11] = textArray[12].getText().toString();//联系人

		clomstr[12] = textArray[13].getText().toString();//手机
		clomstr[13] = textArray[15].getText().toString();//拓展渠道
		clomstr[14] = textArray[16].getText().toString();//收单
		clomstr[15] = textArray[17].getText().toString();//有效期
		clomstr[16] = textArray[18].getText().toString();//收单费率
		
		clomstr[17] = textArray[14].getText().toString();//执照号
		
		String zihangyeid = zihangye(textArray[19].getText().toString());
		System.out.println("子行业："+zihangyeid);
		clomstr[18] = zihangyeid;//子行业
		clomstr[19] = h1et703.getText().toString();
		clomstr[20] = textArray[20].getText().toString();//商户类型
		clomstr[21] = textArray[21].getText().toString();//白名单编号
		//String cityid = DB.queryCityid(HeYueQianDing_3_3.this, h1et702.getText().toString());
		clomstr[22] = caoGaoMenInfoCityId;//城市
		
		clomstr[23] = textArray[22].getText().toString();//注备1
		clomstr[24] = textArray[23].getText().toString();//注备2
		clomstr[25] = textArray[24].getText().toString();//注备3
		clomstr[26] = textArray[25].getText().toString();//注备4
		
		
		DES des=new DES();
		String id1 = DB.klQueryCity(this, "t_childtyp", "where Name = '"
				+ des.jiaMi(clomstr[5]) + "'");
		clomstr[5] = id1;
		String id2=DB.klQueryCity(this, "t_trading", "where Name = '"
				+des.jiaMi(clomstr[6])+"' and CityId = '"+des.jiaMi(clomstr[22])+"'");
		clomstr[6] = id2;
		System.out.println("商圈==="+clomstr[6]);
		String id3=DB.klQuerytypecode(this, "t_mertype", "where typeCode = '"+des.jiaMi(clomstr[20])+"'");
		clomstr[20]=id3;
		
		String id4 = null;
		if (clomstr[13].equals("外包")) {
			id4 = "W";
		} else if (clomstr[13].equals("办事处")) {
			id4 = "D";
		} else if (clomstr[13].equals("分行")) {
			id4 = "B";
		} else if (clomstr[13].equals("卡中心")) {
			id4 = "C";
		}
		clomstr[13] = id4;

		String id5 = null;
		if (clomstr[14].equals("是")) {
			id5 = "Y";
		} else if (clomstr[14].equals("否")) {
			id5 = "N";
		} else {
			id5 = "";
		}
		clomstr[14] = id5;
		String id9 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[19]) + "'"+" and CityId = '"+des.jiaMi(clomstr[22])+"'");
		clomstr[19] = id9;
		
		DB.upload(HeYueQianDing_3_3.this, clomname, clomstr,
				TuanDuiShangHuActivity.tmpId1);
		}catch(Exception e ){
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_3_3类saveData（）"+e.getMessage());
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			dialog.ExitApp(HeYueQianDing_3_3.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	boolean m = true;

	// 验证必填字段的是否为空
	protected boolean validate() {
		if (h1et702.getText().toString().equals("")) {
			dialog.Toast(HeYueQianDing_3_3.this, "请选择门店所在城市 ！");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			dialog.Toast(HeYueQianDing_3_3.this,  "请输入营业名称！");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_3_3.this,  "请输入营业电话！");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_3_3.this, "请输入营业地址！");
			return false;
		}
		if (textArray[4].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_3_3.this,  "请输入邮编！");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_3_3.this,   "请输入行业 ！");
			return false;
		}
		if (textArray[19].getText().toString().equals("")) {

			dialog.Toast(HeYueQianDing_3_3.this,   "请输入子行业 ！");
			return false;
		}
//		if(textArray[5].getText().toString().equals("餐饮")&&textArray[6].getText().toString().equals("")){
//			
//			
//				dialog.Toast(HeYueQianDing_3_3.this,  "请输入菜系 ！");
//			 return false;
//		
//		}
		
		if (textArray[7].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_3_3.this,  "请输入商圈！");
			return false;
		}
//		if (textArray[8].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_3.this, "请输入商户编号 ！", Toast.LENGTH_LONG)
//					.show();
//			return false;
//		}

//		if (textArray[9].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_3.this, "请输入终端号！", Toast.LENGTH_LONG)
//					.show();
//			return false;
//		}
		//
//		 if (textArray[10].getText().toString().equals("")) {
//		 Toast.makeText(HeYueQianDing_3.this, "请输入经度 ！", Toast.LENGTH_LONG)
//		 .show();
//		 return false;
//		 }
//		
//		 if (textArray[11].getText().toString().equals("")) {
//		 Toast.makeText(HeYueQianDing_3.this, "请输入纬度！", Toast.LENGTH_LONG)
//		 .show();
//		 return false;
//		 }
		
//
//		if (textArray[10].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_3.this, "请输入经度 ！", Toast.LENGTH_LONG)
//					.show();
//			return false;
//		}
//
//		if (textArray[11].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_3.this, "请输入纬度！", Toast.LENGTH_LONG)
//					.show();
//			return false;
//		}
//
//		if (textArray[12].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_3_3.this,  "请输入联系人 ！");
//			return false;
//		}
		
//		 if (textArray[13].getText().toString().equals("")) {
//		 Toast.makeText(HeYueQianDing_3.this, "请输入电话 ！", Toast.LENGTH_LONG)
//		 .show();
//		 return false;
//		 }
//		if (textArray[14].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_3.this, "请输入手机 ！", Toast.LENGTH_LONG)
//					.show();
//			return false;
//		}
		if (textArray[15].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_3_3.this,  "请输入拓展渠道 ！");
			return false;
		}
//		if (textArray[16].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_3_3.this,  "请输入是否收单！");
//			return false;
//		}
		// if (textArray[17].getText().toString().equals("")) {
		// Toast.makeText(HeYueQianDing_3.this, "请输入有限期！", Toast.LENGTH_LONG)
		// .show();
		// return false;
		// }
		if (textArray[16].getText().toString().equals("是")&&textArray[18].getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_3_3.this,  "请输入收单费率 ！");
			return false;
		}
		// if (textArray[19].getText().toString().equals("")) {
		// Toast.makeText(HeYueQianDing_3.this, "请输入门店工商执照！", Toast.LENGTH_LONG)
		// .show();
		// return false;
		// }
		if (h1et703.getText().toString().equals("")) {
			
			dialog.Toast(HeYueQianDing_3_3.this,   "请输入区！");
			return false;
		}
		if (textArray[19].getText().toString().equals("")) {
			dialog.Toast(HeYueQianDing_3_3.this, "请输入商户类型！");
			return false;
		}
		if (textArray[20].getText().toString().equals("")) {
			dialog.Toast(HeYueQianDing_3_3.this, "请输入白名单编号！");
			return false;
		}
		typCode[1]=textArray[20].getText().toString();
		String id3=DB.klQuerytypecode(HeYueQianDing_3_3.this, "t_mertype", "where typeCode = '"+des.jiaMi(typCode[1])+"'");
		typCode[1]=id3;
		String name = null;
		if(textArray[21].getText().toString().length()>=typCode[1].length()){
			name=textArray[21].getText().toString().substring(0, typCode[1].length());
		}else{
			dialog.Toast(HeYueQianDing_3_3.this, "白名单编号与商户类型不符！");
			return false;

		}		
		String tname=typCode[1];
		System.out.println(name+" "+tname);
		if(!name.equals(tname)){
			dialog.Toast(HeYueQianDing_3_3.this, "白名单编号与商户类型不符！");
			return false;
		}
		return true;
	}

	public String queryCityName(String username) {
		String cityName = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery(
					"select city from EntityUser where username = ?",
					new String[] { username });
			if (cursor.getCount() == 1) {
				cursor.moveToLast();
				cityName = cursor.getString(0);
			}
			
		} catch (Exception e) { 
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_3_3类queryCityName()"+e.getMessage());
		}finally{
			if(cursor != null){
				cursor.close();
			}if(db != null){
				db.close();
			}
		}

		return cityName;
	}
	/*
	 * 根据城市Id查询该城市下面有的商圈
	 */
	public String[] queryTradingNameByCityId(String cityid) {

		String[] tradingName = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		//String cityId = null;
		Cursor tmpCur = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db = myHelper.getWritableDatabase();
			
			tmpCur = db.rawQuery("select Name from t_trading where CityId = ?",
					new String[] { des.jiaMi(cityid) });
			
			tradingName = new String[tmpCur.getCount()];
			if (tmpCur.getCount() > 0) {
				int idx = 0;
				while (tmpCur.moveToNext()) {
					tradingName[idx++] = des.jieMI(tmpCur.getString(0));
				}
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：HeYueQianDing_3_3+queryTradingNameByCityId出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (tmpCur != null) {
				tmpCur.close();
			}
			if (db != null) {
				db.close();
				;
			}
		}

		return tradingName;
	}


	public String[] queryTradingName(String cityName) {

		String[] tradingName = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		String cityId =null;
		Cursor tmpCur=null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db = myHelper.getWritableDatabase();

			cursor = db.rawQuery("select Id from t_city where Name = ?",
					new String[] { des.jiaMi(cityName )});
			if (cursor.getCount() > 0) {
				cursor.moveToLast();
				cityId = des.jieMI(cursor.getString(0));

//				cursor = db.rawQuery(
//						"select Name from t_trading where CityId = ?",
//						new String[] { cityId });
//				tradingName = new String[cursor.getCount()];
//				if (cursor.getCount() > 0) {
//					int idx = 0;
//					while (cursor.moveToNext()) {
//						tradingName[idx++] = cursor.getString(0);
//					}
//				}
				
			}
			tmpCur = db.rawQuery(
					"select Name from t_trading where CityId = ?",
					new String[] { des.jiaMi(cityId) });
//			cursor = db.rawQuery(
//					"select Name from t_trading where CityId = ?",
//					new String[] { cityId });
					tradingName = new String[tmpCur.getCount()];
					if (tmpCur.getCount() > 0) {
						int idx = 0;
						while (tmpCur.moveToNext()) {
							tradingName[idx++] = des.jieMI(tmpCur.getString(0));
						}
					}
			
		
		}
		catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_3_3类queryTradingName()"+e.getMessage());
		}finally{
			if(cursor != null){
				cursor.close();
				
			}if(tmpCur !=null){
				tmpCur.close();
			}
			if(db != null){
				db.close();
			}
		}

		return tradingName;
	}

	private void get(){
		List<TArea> tareaList1=DB.klQuery(HeYueQianDing_3_3.this,"t_childtyp");
        t_childtyp=new String[tareaList1.size()];
        for(int i=0;i<tareaList1.size();i++){
        	t_childtyp[i]=tareaList1.get(i).name;
        	
        }
		tareaList = DB.klQuery(HeYueQianDing_3_3.this,
				"t_category");
		t_category = new String[tareaList.size()];
		t_category1 = new String[tareaList.size()];

		s = new ArrayList<String>();
		s1 = new ArrayList<String>();
		s2 = new ArrayList<String>();
		s3 = new ArrayList<String>();

		//final String[] ss = t_category;
		for (int i = 0; i < tareaList.size(); i++) {

			t_category[i] = tareaList.get(i).id;
			t_category1[i] = tareaList.get(i).name;
			if (t_category[i].length() == 4) {

				
				
				s.add(t_category[i]); // 长度为4的ID
				s1.add(t_category1[i]);// 长度为4的Name

			} else {
				s2.add(t_category[i]);
				s3.add(t_category1[i]);
			}
		}
		
		name = s1.toArray();
	}
}
