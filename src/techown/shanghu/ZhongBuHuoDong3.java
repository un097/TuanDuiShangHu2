
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
import android.widget.TextView;

/*
 * 合约签订门店信息录入（新建门店）
 */
public class ZhongBuHuoDong3  extends Activity{
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	Button button2,button3,button_jwd;
	GetData getDasta=new GetData();
	String[] typCode =new String[2];
	int[] textIds = { R.id.h2edtext1, R.id.h2edtext2, R.id.h2edtext3, R.id.h2edtext4,
			R.id.h2edtext5, R.id.h2edtext6, R.id.h2edtext7, R.id.h2edtext8, R.id.h2edtext9,
			R.id.h2edtext10, R.id.h2edtext11, R.id.h2edtext12, R.id.h2edtext13, R.id.h2edtext14,
			R.id.h2edtext15,R.id.h2edtext16,R.id.h2edtext17,R.id.h2edtext18,R.id.h2edtext19,
			R.id.h2edtext222,R.id.h2edtext20,R.id.h2edtext21,R.id.sparetext1,R.id.sparetext2,
			R.id.sparetext3,R.id.sparetext4};
	public static TextView[] textArray;
	DES des = new DES();
	int[] linlayIds={R.id.h3leout1,R.id.h3leout2,R.id.h3leout3,R.id.h3leout4,
			R.id.h3leout5,R.id.h3leout6,R.id.h3leout7,R.id.h3leout8,
			R.id.h3leout9,R.id.h3leout10,R.id.h3leout11,R.id.h3leout12,
			R.id.h3leout13,R.id.h3leout14,R.id.h3leout15,R.id.h3leout16,R.id.h3leout17,
			R.id.h3leout18,R.id.h3leout19,R.id.h3leout20,R.id.h3leout21,
			R.id.spareout1,R.id.spareout2,R.id.spareout3,R.id.spareout4};
	public static LinearLayout[] linlay;
	String[]t_trading,t_category,t_childtyp,t_category1;
	
	static List<String> s;
	static List<String> s1;
	static List<String> s2;
	static List<String> s3;
	public  static Object[] name;
	List<TArea> tareaList1=null;
	List<TArea> tareaList2=null;
	String[][] typename;
	String[] bb;
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703区,7021详细地址
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73区，74详细地址
	String[] province;
	//String ccc;
	public String activeMenInfoCityId;

    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heyueqianding_3);
        ZhongBuHuoDong2.isMessageNew = "Y";
        getdata(ZhongBuHuoDong3.this);
        typename = DB.klQuery21(ZhongBuHuoDong3.this, "typeCode", "typeNum", "t_mertype");
        bb =new String[typename.length];
 		for (int i = 0; i < typename.length; i++) {
 			bb[i] = typename[i][0];
 			System.out.println(bb[i]);
 		}
      
		
//        String cityName = queryCityName(username);
//        final String[] t_trading1 = queryTradingName(cityName); 
        
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
//        button_jwd = (Button) findViewById(R.id.button_jwd);
        
        h1leout72 = (LinearLayout)findViewById(R.id.h1leout72);
		h1leout73 = (LinearLayout)findViewById(R.id.h1leout73);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
        
       
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		textArray[15].setText("办事处");
		
		linlay[18].setEnabled(false);
		linlay[6].setEnabled(false);
		
        MyLis lis = new MyLis();

        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
//        button_jwd.setOnClickListener(lis);
        
        
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				ZhongBuHuoDong3.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.num });
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
//				textArray[12].setText(cursor.getString(cursor
//						.getColumnIndex("StMobileNumber")));
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StMobileNumber"))));
				textArray[15].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StChannel"))));//拓展渠道
				textArray[16].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StIsReceiveBill"))));//是否收单
				textArray[17].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StValidity"))));
				textArray[18].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StBillRate"))));
//				textArray[18].setText(cursor.getString(cursor
//						.getColumnIndex("IsNewStore")));
				textArray[14].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StLicense"))));
				textArray[19].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Zhihangye"))));
				textArray[20].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("XuanChuan"))));
				textArray[21].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("PiceXuanChuan"))));
				textArray[22].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeia"))));
				textArray[23].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeib"))));
				textArray[24].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeic"))));
				textArray[25].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeid"))));
//				String id=DB.klQueryCity1(this, "t_category", "where Id = '"+textArray[5].getText().toString()+"'");
//				textArray[5].setText(id);
//				
//				String id1=DB.klQueryCity1(this, "t_childtyp", "where Id = '"+textArray[6].getText().toString()+"'");
//				textArray[6].setText(id1);
//				String id2=DB.klQueryCity1(this, "t_trading", "where Id = '"+textArray[7].getText().toString()+"'");
//				textArray[7].setText(id2);
//
//				String id3=DB.klQueryCity1(this, "t_category", "where Id = '"+textArray[19].getText().toString()+"'");
//				textArray[19].setText(id3);

//				String id3=DB.klQuerytypecode2(this, "t_mertype", "where typeCode = '"+des.jiaMi(textArray[20].getText().toString())+"'");
//				textArray[20].setText(id3);
				
				if(textArray[15].getText().toString().equals("W")){
					textArray[15].setText("外包");
				}else if(textArray[15].getText().toString().equals("D")){
					textArray[15].setText("办事处");
				}else if(textArray[15].getText().toString().equals("B")){
					textArray[15].setText("分行");
				}else if(textArray[15].getText().toString().equals("C")){
					textArray[15].setText("卡中心");
				}
				
				if(textArray[16].getText().toString().equals("Y")){
					textArray[16].setText("是");
				}else if(textArray[16].getText().toString().equals("N")){
					textArray[16].setText("否");
				}
				
				
				h1et703.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("AreaId"))));
				activeMenInfoCityId = des.jieMI(cursor.getString(cursor
						.getColumnIndex("CityId")));
				//h1et702.setText(activeMenInfoCityId);
				h1et702.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CityId"))));
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("团队：ZhongBuHuoDong3异常"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}
				if(db != null){
					db.close();
				}
			}
			//城市名称
			String chengshiname = DB.queryCityname(ZhongBuHuoDong3.this, h1et702.getText().toString());
			h1et702.setText(chengshiname);
			String id=DB.klQueryCity1(this, "t_category", "where Id = '"+des.jiaMi(textArray[5].getText().toString())+"'");
			textArray[5].setText(id);
			
			String id1=DB.klQueryCity1(this, "t_childtyp", "where Id = '"+des.jiaMi(textArray[6].getText().toString())+"'");
			textArray[6].setText(id1);
			String id2=DB.klQueryCity1(this, "t_trading", "where Id = '"+des.jiaMi(textArray[7].getText().toString())+"'");
			textArray[7].setText(id2);

			String id3=DB.klQueryCity1(this, "t_category", "where Id = '"+des.jiaMi(textArray[19].getText().toString())+"'");
			textArray[19].setText(id3);
			
			String name=DB.klQueryCity1(this, "t_district", "where Id = '"
					+ des.jiaMi(h1et703.getText().toString()) + "'");
					h1et703.setText(name);
			String id4=DB.klQueryCity11(this, "t_mertype", "where typeName = '"
					+ des.jiaMi(textArray[20].getText().toString()) + "'");
			textArray[20].setText(id4);
		}
        
		
//		Context friendContext = null;
//		try {
//			friendContext = ZhongBuHuoDong3.this.createPackageContext("techown.login",
//					Context.CONTEXT_IGNORE_SECURITY);
//			MyDatabaseHelper myHelper1 = new MyDatabaseHelper(friendContext, "techown_login.db",
//						1);		
//			SQLiteDatabase sld = myHelper1.getWritableDatabase();
//			Cursor cur = sld.query("t_city",new String[]{"Name"},"Name like ?"
//					, new String[]{TuanDuiShangHuActivity.city+"%"}, null, null, null);
//			while (cur.moveToNext()) {
//				cur.moveToLast();
//				ccc = cur.getString(0);
//
//			}
//			cur.close();
//			sld.close();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		
		h1leout72.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(TuanDuiShangHuActivity.agencycityname!=null&&TuanDuiShangHuActivity.agencycityname.length!=0)
				{
					log.dialog(ZhongBuHuoDong3.this, h1et702, "市",
							TuanDuiShangHuActivity.agencycityname);
					h1et703.setText("");
					textArray[7].setText("");
				}
			}
		});
		h1leout73.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!h1et702.getText().toString().equals("")&&h1et702.getText().toString()!=null)
				{
					/*String[] str1 = getDasta.getCity11(ZhongBuHuoDong3.this,
							h1et702.getText().toString());*/
					
					/*
					 * 记录选择的城市Id
					 */
					activeMenInfoCityId = (String)TuanDuiShangHuActivity.agencyCityAndIdMap
							.get(h1et702.getText().toString());
					String[] str1 = getDasta.getCity12(ZhongBuHuoDong3.this,
							activeMenInfoCityId);
					if (str1.length > 0) {
						log.dialog(ZhongBuHuoDong3.this, h1et703, "区",
								str1);
						
					} else {
						h1et703.setText("无");
						h1et703.setEnabled(false);
					}
				}
				else
				{
					log.Toast(ZhongBuHuoDong3.this, "请选择城市！");
				}
			}
		});
        
//		textArray[0].setText(ZhongBuHuoDong3.textArray[0].getText().toString());        
//        linlay[0].setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				switch (arg1.getAction()) {
//				case MotionEvent.ACTION_UP:
//					log.Dialog(ZhongBuHuoDong3.this,linlay[0], textArray[0],R.id.h2edtext1);
//					break;
//				default:
//					break;
//				}
//
//				return false;
//			}
//		});     
        linlay[1].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this,linlay[1], textArray[1],R.id.h2edtext2,"请输入营业名称");
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
				case MotionEvent.ACTION_UP:
					log.etDialog(ZhongBuHuoDong3.this, textArray[2],R.id.h2edtext3,"请输入营业电话");
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
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this,linlay[3], textArray[3],R.id.h2edtext4,"请输入营业地址");
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
				case MotionEvent.ACTION_UP:
					log.etDialog(ZhongBuHuoDong3.this,textArray[4],R.id.h2edtext5,"请输入邮编");
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
				case MotionEvent.ACTION_UP:
					if(name!=null)
					{
//					get();
//					log.istwo=true;
//					log.num=4;
//					log.dialog(ZhongBuHuoDong3.this,textArray[5],"行业",t_category);
					
					log.istwo = true;
					log.num = 4;
					// lxp
					log.dialog21(ZhongBuHuoDong3.this, textArray[5],"主行业", name);
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
					log.dialog(ZhongBuHuoDong3.this,textArray[6],"菜系",t_childtyp);
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
					
						/*
						 * 根据城市Id 查询该城市下面的商圈
						 */
						String[] t_trading1 = queryTradingNameByCityId(activeMenInfoCityId);
						if(t_trading1!=null&&t_trading1.length>0)
						{
							log.dialog(ZhongBuHuoDong3.this, textArray[7], "商圈",
									t_trading1);
						}
						else
						{
							log.Toast(ZhongBuHuoDong3.this, "该城市没有商圈！");
						}
					}
					else
					{
						log.Toast(ZhongBuHuoDong3.this, "请先选择城市！");
					}
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
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this,linlay[8], textArray[8],R.id.h2edtext9,"请输入商户编号");
					
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
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this,linlay[9], textArray[9],R.id.h2edtext10,"请输入终端号");
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
				case MotionEvent.ACTION_UP:
					log.etDialog(ZhongBuHuoDong3.this,textArray[10],R.id.h2edtext11,"请输入经度");
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
					log.etDialog(ZhongBuHuoDong3.this,textArray[11],R.id.h2edtext12,"请输入纬度");
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
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this,linlay[12], textArray[12],R.id.h2edtext13,"请输入联系人");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[13].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.etDialog(ZhongBuHuoDong3.this,textArray[13],R.id.h2edtext14,"请输入手机号");
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
					log.etDialog(ZhongBuHuoDong3.this, textArray[14],R.id.h2edtext15,"请输入门店工商执照");
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
//					log.Dialog(ZhongBuHuoDong3.this,linlay[15], textArray[15],R.id.h2edtext16);
					log.dialog(ZhongBuHuoDong3.this,textArray[15],"拓展渠道",province1);
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
					log.istwo=true;
					log.num=3;
//					log.Dialog(ZhongBuHuoDong3.this,linlay[16], textArray[16],R.id.h2edtext17);
					log.dialog(ZhongBuHuoDong3.this,textArray[16],"是否收单",province);
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
						DateDialog.showDatePicker(ZhongBuHuoDong3.this, textArray[17],R.id.h2edtext18);
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
					log.etDialog(ZhongBuHuoDong3.this,textArray[18],R.id.h2edtext19,"请输入收单费率");
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
					log.dialog(ZhongBuHuoDong3.this, textArray[20],
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
					log.Dialog(ZhongBuHuoDong3.this, linlay[20],textArray[21],
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
					log.Dialog(ZhongBuHuoDong3.this, linlay[21],textArray[22],
							R.id.sparetext1,"请输入商户备注1");
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
					log.Dialog(ZhongBuHuoDong3.this, linlay[22],textArray[23],
							R.id.sparetext2,"请输入商户备注2");
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
					log.Dialog(ZhongBuHuoDong3.this, linlay[23],textArray[24],
							R.id.sparetext3,"请输入商户备注3");
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
					log.Dialog(ZhongBuHuoDong3.this, linlay[24],textArray[25],
							R.id.sparetext4,"请输入商户备注4");
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

    }
    class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch(id){
				case R.id.button3:
					
//					saveLogic();
					
					
					if(validate()){
						saveData();
						intent.setClass(ZhongBuHuoDong3.this, HeYueQianDing_44.class);
						ZhongBuHuoDong3.this.startActivity(intent);
						ZhongBuHuoDong3.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
				break;
				case R.id.button2:
			
							intent.setClass(ZhongBuHuoDong3.this, ZhongBuHuoDong1.class);
							ZhongBuHuoDong3.this.startActivity(intent);
							ZhongBuHuoDong3.this.finish();
							overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
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
		String[] clomname = new String[] { "StName",
				"Stel", "StAdress", "StPostCode", "StHangye",
				"StCaixi", "StShangquan", "StNumber", "StClientNumber", "Stlong",
				"Stlat", "StContact","StMobileNumber","StChannel","StIsReceiveBill",
				"StValidity","StBillRate" ,"IsNewStore","StLicense","Zhihangye","AreaId",
				"XuanChuan","PiceXuanChuan","CityId","ShopZhubeia","ShopZhubeib","ShopZhubeic","ShopZhubeid"};
		String[] clomstr = new String[clomname.length];
		
		clomstr[0] = textArray[1].getText().toString();
		clomstr[1] = textArray[2].getText().toString();
		clomstr[2] = textArray[3].getText().toString();
		clomstr[3] = textArray[4].getText().toString();
		String Zhuhangye = zhuhangye(textArray[5].getText().toString());
		System.out.println("主行业："+Zhuhangye);
		clomstr[4] = Zhuhangye;
		clomstr[5] = textArray[6].getText().toString();
		clomstr[6] = textArray[7].getText().toString();
		clomstr[7] = textArray[8].getText().toString();
		clomstr[8] = textArray[9].getText().toString();
		clomstr[9] = textArray[10].getText().toString();
		clomstr[10] = textArray[11].getText().toString();
		clomstr[11] = textArray[12].getText().toString();
		
		clomstr[12] = textArray[13].getText().toString();
		clomstr[13] = textArray[15].getText().toString();
		clomstr[14] = textArray[16].getText().toString();
		clomstr[15] = textArray[17].getText().toString();
		clomstr[16] = textArray[18].getText().toString();
		clomstr[17] ="Y";
		clomstr[18] =textArray[14].getText().toString();
		String zihangyeid = zihangye(textArray[19].getText().toString());
		System.out.println("子行业："+zihangyeid);
		clomstr[19] = zihangyeid;//子行业
		clomstr[20] = h1et703.getText().toString();
		clomstr[21] = textArray[20].getText().toString();//商户类型
		clomstr[22] = textArray[21].getText().toString();//白名单编号
		//String cityid = DB.queryCityid(ZhongBuHuoDong3.this, h1et702.getText().toString());
		clomstr[23] = activeMenInfoCityId;//城市
		clomstr[24] = textArray[22].getText().toString();//商户注备1
		clomstr[25] = textArray[23].getText().toString();//商户注备2
		clomstr[26] = textArray[24].getText().toString();//商户注备3
		clomstr[27] = textArray[25].getText().toString();//商户注备4
//		String id=DB.klQueryCity(this, "t_category", "where Name = '"+clomstr[4]+"'");
//		clomstr[4]=id;
		
		String id1=DB.klQueryCity(this, "t_childtyp", "where Name = '"+des.jiaMi(clomstr[5])+"'");
		clomstr[5]=id1;
		String id2=DB.klQueryCity(this, "t_trading", "where Name = '"+des.jiaMi(clomstr[6])+"' and CityId = '"+des.jiaMi(clomstr[23])+"'");
		clomstr[6]=id2;
		System.out.println("商圈==="+clomstr[6]);
		String id3=DB.klQuerytypecode(this, "t_mertype", "where typeCode = '"+des.jiaMi(clomstr[21])+"'");
		clomstr[21]=id3;
		
		String id4 = null;
		if(clomstr[13].equals("外包")){
			id4="W";
		}else if(clomstr[13].equals("办事处")){
			id4="D";
		}else if(clomstr[13].equals("分行")){
			id4="B";
		}else if(clomstr[13].equals("卡中心")){
			id4="C";
		}
		clomstr[13]=id4;
		
		String id5=null;
		if(clomstr[14].equals("是")){
			id5="Y";
		}else if(clomstr[14].equals("否")){
			id5="N";
		}else{
			id5="";
		}
		clomstr[14]=id5;
		String id9 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[20]) + "'"+" and CityId = '"+des.jiaMi(clomstr[23])+"'");
		clomstr[20] = id9;
		DB.upload(ZhongBuHuoDong3.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
    
 // 保存当前数据
	public void saveLogic() {
		String[] str = new String[19];
		for (int i = 0; i < str.length; i++) {
			str[i] = textArray[i].getText().toString();
		}
		TuanDuiShangHuActivity.activityStr.put("heyueqianding3", str);
	}

	// 得到界面数据
	public void getLogic() {
		if (TuanDuiShangHuActivity.activityStr.containsKey("heyueqianding3")) {
			String[] str = TuanDuiShangHuActivity.activityStr
					.get("heyueqianding3");
			for (int i = 0; i < str.length; i++) {
				textArray[i].setText(str[i]);
			}
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(ZhongBuHuoDong3.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
	
	boolean m = true;

	// 验证必填字段的是否为空
	protected boolean validate() {
		if (textArray[1].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入营业名称！");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入营业电话！");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入营业地址！");
			return false;
		}
		if (textArray[4].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入邮编！");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入行业 ！");
			return false;
		}
		if (textArray[19].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong3.this, "请输入子行业 ！");
			return false;
		}
		if (textArray[7].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入商圈！");
			return false;
		}
		if (h1et702.getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请选择门店所在城市 ！");
			return false;
		}
		if(h1et703.getText().toString().equals("")){
			
			log.Toast(ZhongBuHuoDong3.this, "请选择行政区！");
			return false;
		}
		if (textArray[15].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入拓展渠道 ！");
			return false;
		}
		if (textArray[16].getText().toString().equals("是")&&textArray[18].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入收单费率 ！");
			return false;
		}
		if (textArray[20].getText().toString().equals("")||textArray[20].getText().toString()==null) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入商户类型！");
			return false;
		}
		if (textArray[21].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "请输入白名单编号！");
			return false;
		}
		typCode[1]=textArray[20].getText().toString();
		String id3=DB.klQuerytypecode(ZhongBuHuoDong3.this, "t_mertype", "where typeCode = '"+des.jiaMi(typCode[1])+"'");
		typCode[1]=id3;
		String name = null;
		if(textArray[21].getText().toString().length()>=typCode[1].length()){
			name=textArray[21].getText().toString().substring(0, typCode[1].length());
		}else{
			log.Toast(ZhongBuHuoDong3.this, "白名单编号与商户类型不符！");
			return false;

		}
		String tname=typCode[1];
		System.out.println(name+" "+tname);
		if(!name.equals(tname)){
			log.Toast(ZhongBuHuoDong3.this, "白名单编号与商户类型不符！");
			return false;
		}
		return true;
	}
	
	 public String queryCityName(String username){
	    	String cityName = null;
	    	SQLiteDatabase db = null;
	    	Cursor cursor = null;
	    	try {
				Context friendContext = createPackageContext("techown.login",
						Context.CONTEXT_IGNORE_SECURITY);
				MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
						Constant.loginDBVer);
				db = myHelper.getWritableDatabase();
				cursor = db.rawQuery("select city from EntityUser where username = ?", new String[]{username});
				if(cursor.getCount() == 1){
					cursor.moveToLast();
					cityName = cursor.getString(0);
				}
				cursor.close();
				db.close();
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("团队：ZhongBuHuoDong3+queryCityName出错"+e.getMessage());	
			}finally{
				if(cursor != null){
					cursor.close();
				}
				if(db != null){
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
						"团队：HeYueQianDing_3+queryTradingName出错" + e.getMessage());
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
	
	 public String[] queryTradingName(String cityName){
			
			String[] tradingName = null;
			SQLiteDatabase db = null;
	    	Cursor cursor = null;
	    	String cityId = null;
	    	Cursor tmpCur = null;
			try{
				Context friendContext = createPackageContext("techown.login",
						Context.CONTEXT_IGNORE_SECURITY);
				MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
						Constant.loginDBVer);
				db = myHelper.getWritableDatabase();
				
				cursor = db.rawQuery("select Id from t_city where Name = ?", new String[]{des.jiaMi(cityName)});
				if(cursor.getCount() > 0){
					cursor.moveToLast();
					cityId = des.jieMI(cursor.getString(0));
					
//					cursor = db.rawQuery("select Name from t_trading where CityId = ?", new String[]{cityId});
//					tradingName = new String[cursor.getCount()];
//					if(cursor.getCount() > 0){
//						int idx = 0;
//						while(cursor.moveToNext()){
//							tradingName[idx++] = cursor.getString(0);
//						}
//					}
					
				}
				
				tmpCur = db.rawQuery(
						"select Name from t_trading where CityId = ?",
						new String[] { des.jiaMi(cityId) });
//				cursor = db.rawQuery(
//						"select Name from t_trading where CityId = ?",
//						new String[] { cityId });
						tradingName = new String[tmpCur.getCount()];
						if (tmpCur.getCount() > 0) {
							int idx = 0;
							while (tmpCur.moveToNext()) {
								tradingName[idx++] = des.jieMI(tmpCur.getString(0));
							}
						}
			}
			catch(Exception e){
				techown.shanghu.https.Log.Instance().WriteLog("团队：ZhongBuHuoDong3+queryTradingName出错"+e.getMessage());	
			}finally{
				if(cursor != null){
					cursor.close();
				}if(tmpCur != null){
					tmpCur.close();
				}
				if(db != null){
					db.close();
				}
			}
			
			return tradingName;
		}
	 
	
		
		public  String username ;
		public  String imei;
		public  String indenty;
		public  String chinaname;
		public  String city;
		public  String prov;
		
		public void getdata(Context con)
		{		
			Context friendContext = null;
			String[] str = null;
			Cursor cur = null;
		 	SQLiteDatabase sld =null;
			try {
				friendContext = con.createPackageContext("techown.login",
						Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);		
			str = new String[6];
			sld = myHelper.getWritableDatabase();
			cur = sld.query("EntityUser",new String[]{"username","imei","identity","chinaname","city","prov"},null
					,null, null, null, null);
			while (cur.moveToNext()) {
				for (int i = 0; i <6; i++) {
					if (cur.getString(i) != null) {
						str[i] = cur.getString(i);
					}
				}

			}
			
			
			username=str[0];
			imei=str[1];
			indenty=str[2];
			chinaname=str[3];
			city=str[4].substring(0,2);
			prov=str[5];
			}
			catch(Exception e)
			{
				techown.shanghu.https.Log.Instance().WriteLog("团队：ZhongBuHuoDong3+getdata出错"+e.getMessage());	
			}
			finally{
				
				if(cur!=null){
					cur.close();
				}
				if(sld!=null){
					sld.close();
				}
				
			}
		}		
	private void get()
	{
		  List<TArea> tareaList=DB.klQuery(ZhongBuHuoDong3.this,"t_category");
	        t_category=new String[tareaList.size()];
	        for(int i=0;i<tareaList.size();i++){
	        	t_category[i]=tareaList.get(i).name;
	        	t_category1 = new String[tareaList.size()];
	        }
	        tareaList1=DB.klQuery(ZhongBuHuoDong3.this,"t_childtyp");
	        t_childtyp=new String[tareaList1.size()];
	        for(int i=0;i<tareaList1.size();i++){
	        	t_childtyp[i]=tareaList1.get(i).name;
	        	
	        }
//	        tareaList2=DB.klQuery(ZhongBuHuoDong3.this,"t_trading");
//	        t_trading=new String[tareaList2.size()];
//	        for(int i=0;i<tareaList2.size();i++){
//	        	t_trading[i]=tareaList2.get(i).name;
//	        	
//	        }
	        

	        s = new ArrayList<String>();
			s1 = new ArrayList<String>();
			s2 = new ArrayList<String>();
			s3 = new ArrayList<String>();

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
