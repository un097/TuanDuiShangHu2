package techown.shanghu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.TArea;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * 拒件--合约签订门店信息录入
 */
public class HeYueQianDing_31  extends Activity{
	public String refuseMenInfoCityId;//此页面对应的城市Id

	public TextView h1et702,h1et703;
	private Button button2,button3;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	DES des = new DES();
	String ccc;
	int[] textIds = { R.id.h2edtext1, R.id.h2edtext2, R.id.h2edtext3, R.id.h2edtext4,
			R.id.h2edtext5, R.id.h2edtext6, R.id.h2edtext7, R.id.h2edtext8, R.id.h2edtext9,
			R.id.h2edtext10, R.id.h2edtext11, R.id.h2edtext12, R.id.h2edtext13, R.id.h2edtext14,
			R.id.h2edtext15,R.id.h2edtext16,R.id.h2edtext17,R.id.h2edtext18,R.id.h2edtext19,
			R.id.h2edtext222,R.id.h2edtext20,R.id.h2edtext21,
			R.id.sparetext1, R.id.sparetext2, R.id.sparetext3, R.id.sparetext4};
	public static TextView[] textArray;

	int[] linlayIds={R.id.h3leout1,R.id.h3leout2,R.id.h3leout3,R.id.h3leout4,
			R.id.h3leout5,R.id.h3leout6,R.id.h3leout7,R.id.h3leout8,
			R.id.h3leout9,R.id.h3leout10,R.id.h3leout11,R.id.h3leout12,
			R.id.h3leout13,R.id.h3leout14,R.id.h3leout15,R.id.h3leout16,R.id.h3leout17,
			R.id.h3leout18,R.id.h3leout19,R.id.h3leout20,R.id.h3leout21,
			R.id.spareout1, R.id.spareout2,R.id.spareout3, R.id.spareout4 };
	public LinearLayout[] linlay;
	public static String requeststr1;
	List<TextView> ls;
	List<TextView> ls1;
	String[][] typename;
	String[] bb;
	private LinearLayout h3leout9,h3leout10;
	String[] typCode =new String[2];
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heyueqianding_3);
        try{
        
       ls=new ArrayList<TextView>();
       ls1=new ArrayList<TextView>();
//        panel=new Panel(this);
        //createDatabase();
//       typename = DB.klQuery(HeYueQianDing_31.this, "typeCode", "t_mertype");
       typename = DB.klQuery21(HeYueQianDing_31.this, "typeCode", "typeNum", "t_mertype");
       bb =new String[typename.length];
		for (int i = 0; i < typename.length; i++) {
			bb[i] = typename[i][0];
			System.out.println(bb[i]);
		}
       
       
       
        button2 = (Button) findViewById(R.id.button2);    
        button3 = (Button) findViewById(R.id.button3);
       
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		linlay[19].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.dialog(HeYueQianDing_31.this, textArray[20],
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
					log.Dialog(HeYueQianDing_31.this, linlay[20],textArray[21],
							R.id.h2edtext21,"请输入白名单编号");
					break;
				default:
					break;
				}

				return false;
			}
		});
		
		h1et702=(TextView)findViewById(R.id.h1et702);
		h1et703=(TextView)findViewById(R.id.h1et703);
        MyLis lis = new MyLis();

        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
       
        DES des = new DES();Cursor cursor=null;SQLiteDatabase db=null;
        try{
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				HeYueQianDing_31.this, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.num });
		while (cursor.moveToNext()) {
			
				
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyName"))));
				textArray[20].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("XuanChuan"))));
				textArray[21].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("PiceXuanChuan"))));
//				h1et702.setText(cursor.getString(cursor
//						.getColumnIndex("Gcityid")));
//				String id1 = DBUtil.klQueryCity1(this, "t_city", "where Id = '"
//						+ h1et702 + "'");
//				h1et702.setText(id1);
			
		}
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_31类："+e.getMessage());

		}finally{
			if(cursor!=null)
				cursor.close();
			if(db!=null)
				db.close();
		}
		String id4=DB.klQueryCity11(this, "t_mertype", "where typeName = '"
				+ des.jiaMi(textArray[20].getText().toString()) + "'");
		textArray[20].setText(id4);
		SQLiteDatabase sld = null;
		Cursor cur = null;
		Context friendContext = null;
		/*try {
			friendContext = HeYueQianDing_31.this.createPackageContext("techown.login",
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
			techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing_31:"+e.getMessage());
		}finally{
			if(cur != null){
				cur.close();
			}if(sld != null){
				sld.close();
			}
		}
		*/
		refuseMenInfoCityId = TuanDuiShangHuActivity.cityId;
		ccc = TuanDuiShangHuActivity.cityname;
		
		h1et702.setText(ccc);
		init();
       
    }catch(Exception e){
    	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_31类："+e.getMessage());

    }
    
    }
    class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			try {
				// TODO Auto-generated method stub
				int id = v.getId();
				Intent intent = new Intent();
				switch(id){
					case R.id.button3:
						
						if(validate())
						{
							saveData();

							intent.setClass(HeYueQianDing_31.this, HeYueQianDing_41.class);
							HeYueQianDing_31.this.startActivity(intent);
							HeYueQianDing_31.this.finish();
							overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
						
					break;
					case R.id.button2:
				
						
					
						
						
								intent.setClass(HeYueQianDing_31.this, HeYueQianDing_21.class);
								HeYueQianDing_31.this.startActivity(intent);
								HeYueQianDing_31.this.finish();
								overridePendingTransition(R.anim.rightin, R.anim.rightout);	

					break;
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_31类按钮点击："+e.getMessage());

			}
		}
    }
    public void saveData() {
    	try{
		String[] clomname = new String[] { "StName", "Stel", "StAdress",
				"StPostCode", "StHangye", "StCaixi", "StShangquan", "StNumber",
				"StClientNumber", "Stlong", "Stlat", "StContact",
				"StMobileNumber", "StChannel", "StIsReceiveBill", "StValidity",
				"StBillRate", "IsNewStore", "StLicense","Zhihangye","storeauditId",
				"AreaId","XuanChuan","PiceXuanChuan","CityId", "ShopZhubeia",
				"ShopZhubeib", "ShopZhubeic", "ShopZhubeid"};
		String[] clomstr = new String[clomname.length];

		clomstr[0] = textArray[1].getText().toString();//名称
		clomstr[1] = textArray[2].getText().toString();//电话
		clomstr[2] = textArray[3].getText().toString();//地址
		clomstr[3] = textArray[4].getText().toString();//邮编
		clomstr[4] = tempstr[7];//行业
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
		clomstr[17] = "N";
		clomstr[18] = textArray[14].getText().toString();//执照号
		clomstr[19] = childType;
		clomstr[20] = preId;
		clomstr[21] = h1et703.getText().toString();
		clomstr[22] = textArray[20].getText().toString();//商户类型
		clomstr[23] = textArray[21].getText().toString();//白名单编号
		//String cityid = DB.queryCityid(HeYueQianDing_31.this, h1et702.getText().toString());
		clomstr[24] = refuseMenInfoCityId;//城市
		
		clomstr[25] = textArray[22].getText().toString();//注备1
		clomstr[26] = textArray[23].getText().toString();//注备2
		clomstr[27] = textArray[24].getText().toString();//注备3
		clomstr[28] = textArray[25].getText().toString();//注备4
		
		System.out.println("preId"+clomstr[20]+"+"+preId);
		DES des=new DES();
		String id1 = DB.klQueryCity(this, "t_childtyp", "where Name = '"
				+ des.jiaMi(clomstr[5]) + "'");
		clomstr[5] = id1;
		String id2=DB.klQueryCity(this, "t_trading", "where Name = '"
				+des.jiaMi(clomstr[6])+"' and CityId = '"+des.jiaMi(clomstr[24])+"'");
		clomstr[6] = id2;
		System.out.println("商圈==="+clomstr[6]);
		String id3=DB.klQuerytypecode(this, "t_mertype", "where typeCode = '"+des.jiaMi(clomstr[22])+"'");
		clomstr[22]=id3;
		
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
				+ des.jiaMi(clomstr[21]) + "'"+" and CityId = '"+des.jiaMi(clomstr[24])+"'");
		clomstr[21] = id9;
		
		DB.upload(HeYueQianDing_31.this, clomname, clomstr,
				TuanDuiShangHuActivity.num);
		for(int i=0;i<20;i++){
			System.out.println("zhiduan"+clomname[i]);
		}
    	}catch(Exception e){
    		techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_31类saveData()"+e.getMessage());
    	}
		
	}

    public String preId;
    String childType;
    static String[] tempstr=new String[60];
    public void init()
    {
    	try
		{
    		JSONObject demoJson1 = new JSONObject(requeststr1);
    		
      		if(demoJson1.getString("rspCode").equals("00"))
      		{
//      			JSONArray numberList1 =demoJson1.getJSONArray("detail");
//      			for(int j=0;j<numberList1.length();j++)
//             		{
//      				JSONObject datajson = (JSONObject)numberList1.opt(j);
      				tempstr[0]=demoJson1.getString("merLicences");
      				tempstr[1]=demoJson1.getString("busiName");
      				tempstr[2]=demoJson1.getString("merId");
      				tempstr[3]=demoJson1.getString("merName");
      				tempstr[4]=demoJson1.getString("merTel");
      				tempstr[5]=demoJson1.getString("merAddress");
      				tempstr[6]=demoJson1.getString("zip");
      				tempstr[7]=demoJson1.getString("industry");
      				tempstr[8]=demoJson1.getString("din");
      				tempstr[9]=demoJson1.getString("busiGroup");
      				tempstr[10]=demoJson1.getString("misId");
      				tempstr[11]=demoJson1.getString("terId");
      				tempstr[12]=demoJson1.getString("longitude");
      				tempstr[13]=demoJson1.getString("latitude");
      				tempstr[14]=demoJson1.getString("contacter");
      				tempstr[15]=demoJson1.getString("handPhone");
      				tempstr[16]=demoJson1.getString("devChn");
      				tempstr[17]=demoJson1.getString("isReceipt");
      				tempstr[18]=demoJson1.getString("valiTime");
      				tempstr[19]=demoJson1.getString("receiptFee");
      				
      				tempstr[20]=demoJson1.getString("parkNo");
      				tempstr[21]=demoJson1.getString("busiTime");
      				tempstr[22]=demoJson1.getString("chairNo");
      				tempstr[23]=demoJson1.getString("areaNo");
      				tempstr[24]=demoJson1.getString("traffic");
      				tempstr[25]=demoJson1.getString("deskCard");
      				tempstr[26]=demoJson1.getString("rollUpNo");
      				tempstr[27]=demoJson1.getString("doorLabel");
      				tempstr[28]=demoJson1.getString("poster");
      				tempstr[29]=demoJson1.getString("comContent");
      				tempstr[30]=demoJson1.getString("brandContent");
      				tempstr[31]=demoJson1.getString("merContent");
      				tempstr[32]=demoJson1.getString("dishes");
      				tempstr[33]=demoJson1.getString("perFee");
      				tempstr[34]=demoJson1.getString("misc");
      				tempstr[35]=demoJson1.getString("signDate");
      				tempstr[36]=demoJson1.getString("startDate");
      				tempstr[37]=demoJson1.getString("endDate");
      				tempstr[38]=demoJson1.getString("isContinue");
      				tempstr[39]=demoJson1.getString("isMessage");
      				tempstr[40]=demoJson1.getString("cardType");	//第一位‘1’为白金卡，‘0’为非白金卡
 											     				//第二位‘1’为普卡，‘0’为非普卡
 											     				//第三位‘1’为金卡，‘0’为非金卡
 											     				//第四位‘1’为所有太平洋信用卡，‘0’为非所有太平洋信用卡
 											     				//第五位‘1’为所有太平洋卡，‘0’为非所有太平洋卡卡
 											     				//第六位‘1’为其他卡种，‘0’为非其他卡种
 											     				//例如：选择白金和普卡则为：“110000”，选中所有卡未“111111

      				tempstr[41]=demoJson1.getString("messageContent");
      				tempstr[42]=demoJson1.getString("saleContent");
      				tempstr[43]=demoJson1.getString("specList");
      				tempstr[44]=demoJson1.getString("photoL");
      				tempstr[45]=demoJson1.getString("photoS");
      				tempstr[46]=demoJson1.getString("photoA");
      				tempstr[47]=demoJson1.getString("photoQ");
      				tempstr[48]=demoJson1.getString("childType");
      				tempstr[49]=demoJson1.getString("areaId");
      				childType=demoJson1.getString("childType");
      				preId=demoJson1.getString("preId");
      				tempstr[50]=demoJson1.getString("merType");
      				tempstr[51]=demoJson1.getString("rollNumber");
      				if(tempstr[50].length()==0){
      					linlay[19].setEnabled(true);
      				}
      				if(tempstr[51].length()==0){
      					linlay[20].setEnabled(true);
      				}
      				tempstr[52]=demoJson1.getString("remarks1");//注备1
      				tempstr[53]=demoJson1.getString("remarks2");//注备2
      				tempstr[54]=demoJson1.getString("remarks3");//注备3
      				tempstr[55]=demoJson1.getString("remarks4");//注备4
      				tempstr[56]=demoJson1.getString("couponContent");//优惠券内容
      				tempstr[57]=demoJson1.getString("couponStartDate");//优惠券开始日期
      				tempstr[58]=demoJson1.getString("couponEndDate");//优惠券截止日期
      				tempstr[59]=demoJson1.getString("isCoupon");//是否有优惠券
      				
      				
				textArray[1].setText(tempstr[3]);
				textArray[2].setText(tempstr[4]);
				textArray[3].setText(tempstr[5]);
				textArray[4].setText(tempstr[6]);
				textArray[5].setText(tempstr[7]);
				textArray[6].setText(tempstr[8]);
				textArray[7].setText(tempstr[9]);
//				textArray[8].setText(tempstr[10]);
//				textArray[9].setText(tempstr[11]);
				String misid=tempstr[10];
				String terid=tempstr[11];
				textArray[10].setText(tempstr[12]);
				textArray[11].setText(tempstr[13]);
				textArray[12].setText(tempstr[14]);
				textArray[13].setText(tempstr[15]);
				textArray[14].setText(tempstr[0]);
				textArray[15].setText(tempstr[16]);
				textArray[16].setText(tempstr[17]);
				textArray[17].setText(tempstr[18]);
				textArray[18].setText(tempstr[19]);
				textArray[19].setText(tempstr[48]);
				textArray[20].setText(tempstr[50]);
				textArray[21].setText(tempstr[51]);
				
				textArray[22].setText(tempstr[52]);//注备1
				textArray[23].setText(tempstr[53]);//注备2
				textArray[24].setText(tempstr[54]);//注备3
				textArray[25].setText(tempstr[55]);//注备4
				
				h1et703.setText(tempstr[49]);
				System.out.println(tempstr[49]);
				DES des=new DES();
				//城市名称
				refuseMenInfoCityId=DB.klQueryCity2(this, "t_district", "where Id = '"
						+ des.jiaMi(h1et703.getText().toString()) + "'");
				String chengshiname = DB.queryCityname(HeYueQianDing_31.this, refuseMenInfoCityId);
				h1et702.setText(chengshiname);
				String id = DB.klQueryCity1(this, "t_category",//行业ID转换
						"where Id = '" + des.jiaMi(textArray[5].getText().toString())
								+ "'");
				textArray[5].setText(id);
				String id1 = DB.klQueryCity1(this, "t_childtyp",//菜系ID转换
						"where Id = '" + des.jiaMi(textArray[6].getText().toString())
								+ "'");
				textArray[6].setText(id1);
				String id2 = DB.klQueryCity1(this, "t_trading",//
						"where Id = '" + des.jiaMi(textArray[7].getText().toString())
								+ "'");
				textArray[7].setText(id2);
				String id3 = DB.klQueryCity1(this, "t_category",//
						"where Id = '" + des.jiaMi(textArray[19].getText().toString())
								+ "'");
				textArray[19].setText(id3);

				String id4 = DB.klQueryCity1(this, "t_district",//
						"where Id = '" + des.jiaMi(h1et703.getText().toString())
								+ "'");
				h1et703.setText(id4);
				
				String id5=DB.klQueryCity11(this, "t_mertype", "where typeName = '"
						+ des.jiaMi(textArray[20].getText().toString()) + "'");
				textArray[20].setText(id5);
				
				
				
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
				

				init1(misid,terid);
				
				
		    	}
//             		}
      	
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing_31:"+e.getMessage());
		}	
    }
	
	
	
	

	private void init1(String misId,String terId) {
		try{
		h3leout9 = (LinearLayout) findViewById(R.id.h3leout9);
    	h3leout10 = (LinearLayout) findViewById(R.id.h3leout10);

    	

    		
   	
						String[] arrg = misId.split("\\|");;//商户mis号
						textArray[8].setText(arrg[0]);
				    	for (int i = 1; i < arrg.length; i++) {
				    		
				    		View view = LayoutInflater.from(HeYueQianDing_31.this).inflate(
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
				    		
				    		View view = LayoutInflater.from(HeYueQianDing_31.this).inflate(
									R.layout.cim_layout_list5, null);
							view.setLayoutParams(new LinearLayout.LayoutParams(
									LayoutParams.FILL_PARENT, 50));
							h3leout10.addView(view);
							TextView tv=(TextView)view.findViewById(R.id.h2edtext10);
							ls1.add(tv);
							ls1.get(i-1).setText(errg[i]);
				    	}
		}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_31类init1（）"+e.getMessage());
		}
		
	}

	public  List<String[]> list = new ArrayList<String[]>();
	public  List<String[]> list1 = new ArrayList<String[]>();
	protected String Jsonstr;
	protected boolean validate() {
			if (h1et702.getText().toString().equals("")) {
				log.Toast(HeYueQianDing_31.this, "请选择门店所在城市 ！");
				return false;
			}
			if (textArray[20].getText().toString().equals("")) {
				log.Toast(HeYueQianDing_31.this, "请输入商户类型！");
				return false;
			}
			if (textArray[19].getText().toString().equals("")) {

				log.Toast(HeYueQianDing_31.this,   "请输入子行业 ！");
				return false;
			}
			if (textArray[21].getText().toString().equals("")) {
				log.Toast(HeYueQianDing_31.this, "请输入白名单编号！");
				return false;
			}
			typCode[1]=textArray[20].getText().toString();
			String id3=DB.klQuerytypecode(HeYueQianDing_31.this, "t_mertype", "where typeCode = '"+des.jiaMi(typCode[1])+"'");
			typCode[1]=id3;
			String name = null;
			if(textArray[21].getText().toString().length()>=typCode[1].length()){
				name=textArray[21].getText().toString().substring(0, typCode[1].length());
			}else{
				log.Toast(HeYueQianDing_31.this, "白名单编号与商户类型不符！");
				return false;

			}			String tname=typCode[1];
			System.out.println(name+" "+tname);
			if(!name.equals(tname)){
				log.Toast(HeYueQianDing_31.this, "白名单编号与商户类型不符！");
				return false;
			}
		return true;
	}
	

	

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(HeYueQianDing_31.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
