
package techown.shanghu;

import java.util.ArrayList;
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
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * 合约签订门店信息录入（已有门店）
 */
public class ZhongBuHuoDong31  extends Activity{
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	public static String zhi;
	//static String chengshiname;
	String chengshiname;
	public static String requeststr1;
	Button button2,button3,button_jwd;
	private LinearLayout h3leout9,h3leout10;
	DES des = new DES();
	int[] textIds = { R.id.h2edtext1, R.id.h2edtext2, R.id.h2edtext3, R.id.h2edtext4,
			R.id.h2edtext5, R.id.h2edtext6, R.id.h2edtext7, R.id.h2edtext8, R.id.h2edtext9,
			R.id.h2edtext10, R.id.h2edtext11, R.id.h2edtext12, R.id.h2edtext13, R.id.h2edtext14,
			R.id.h2edtext15,R.id.h2edtext16,R.id.h2edtext17,R.id.h2edtext18,R.id.h2edtext19,R.id.h2edtext222,
			R.id.h2edtext20,R.id.h2edtext21,R.id.sparetext1,R.id.sparetext2,R.id.sparetext3,R.id.sparetext4};
	public static TextView[] textArray;

	int[] linlayIds={R.id.h3leout1,R.id.h3leout2,R.id.h3leout3,R.id.h3leout4,
			R.id.h3leout5,R.id.h3leout6,R.id.h3leout7,R.id.h3leout8,
			R.id.h3leout9,R.id.h3leout10,R.id.h3leout11,R.id.h3leout12,
			R.id.h3leout13,R.id.h3leout14,R.id.h3leout15,R.id.h3leout16,R.id.h3leout17,
			R.id.h3leout18,R.id.h3leout19,R.id.h3leout20,R.id.h3leout21,
			R.id.spareout1,R.id.spareout2,R.id.spareout3,R.id.spareout4};
	public static LinearLayout[] linlay;
	String[]t_trading,t_category,t_childtyp,t_category1;
	
	List<String> s;
	List<String> s1;
	static List<String> s2;
	static List<String> s3;
	public  static Object[] name;
	List<TArea> tareaList1=null;
	List<TArea> tareaList2=null;
	List<TArea> tareaList=null;
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703区,7021详细地址
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73区，74详细地址
	String[] province;
	String ccc;
	String[] bb;
	String[][] typename;
	String[] typCode =new String[2];
	public String hasMenInfoCityId;//此页面中的城市Id

    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heyueqianding_3);

        typename = DB.klQuery21(ZhongBuHuoDong31.this, "typeCode", "typeNum", "t_mertype");
        bb =new String[typename.length];
 		for (int i = 0; i < typename.length; i++) {
 			bb[i] = typename[i][0];
 			System.out.println(bb[i]);
 		}
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);      
        h1leout72 = (LinearLayout)findViewById(R.id.h1leout72);
		h1leout73 = (LinearLayout)findViewById(R.id.h1leout73);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
		//h1et702.setText(chengshiname);
       
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		textArray[15].setText("办事处");
			getdata();
		
        MyLis lis = new MyLis();

        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
               
		if(zhi.equals("xia")){
			linlay[19].setEnabled(true);
			linlay[20].setEnabled(true);
		}
		linlay[19].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.dialog(ZhongBuHuoDong31.this, textArray[20],
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
					log.Dialog(ZhongBuHuoDong31.this, linlay[20],textArray[21],
							R.id.h2edtext21,"请输入白名单编号");
					break;
				default:
					break;
				}

				return false;
			}
		});
        if(zhi.equals("shang"))
        {
        	init();
        }
    }
  
    List<TextView> ls=new ArrayList<TextView>();
    List<TextView> ls1=new ArrayList<TextView>();
    String preId;
    static String[] tempstr=new String[57];
    public void init()
    {
    	
    	h3leout9 = (LinearLayout) findViewById(R.id.h3leout9);
    	h3leout10 = (LinearLayout) findViewById(R.id.h3leout10);
    	
    	
    	try
		{
    		JSONObject demoJson1 = new JSONObject(requeststr1);
    		
      		if(demoJson1.getString("rspCode").equals("00"))
      		{
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
      				preId=demoJson1.getString("preId");
      				tempstr[50]=demoJson1.getString("merType");
      				tempstr[51]=demoJson1.getString("rollNumber");
      				tempstr[52]=demoJson1.getString("remarks1");
      				tempstr[53]=demoJson1.getString("remarks2");
      				tempstr[54]=demoJson1.getString("remarks3");
      				tempstr[55]=demoJson1.getString("remarks4");
      				ZhongBuHuoDong2.isMessageNew = demoJson1.getString("isMessageNew");
      				
				textArray[1].setText(tempstr[3]);
				textArray[2].setText(tempstr[4]);
				textArray[3].setText(tempstr[5]);
				textArray[4].setText(tempstr[6]);
				textArray[5].setText(tempstr[7]);
				textArray[6].setText(tempstr[8]);
				textArray[7].setText(tempstr[9]);
//				textArray[8].setText(tempstr[10]);
//				textArray[9].setText(tempstr[11]);
				String misId=tempstr[10];
				String terId=tempstr[11];
				
				String[] arrg = misId.split("\\|");;//商户mis号
				textArray[8].setText(arrg[0]);
		    	for (int i = 1; i < arrg.length; i++) {
		    		
		    		View view = LayoutInflater.from(ZhongBuHuoDong31.this).inflate(
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
		    		
		    		View view = LayoutInflater.from(ZhongBuHuoDong31.this).inflate(
							R.layout.cim_layout_list5, null);
					view.setLayoutParams(new LinearLayout.LayoutParams(
							LayoutParams.FILL_PARENT, 50));
					h3leout10.addView(view);
					TextView tv=(TextView)view.findViewById(R.id.h2edtext10);
					ls1.add(tv);
					ls1.get(i-1).setText(errg[i]);
		    	}
				
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
				textArray[22].setText(tempstr[52]);
				textArray[23].setText(tempstr[53]);
				textArray[24].setText(tempstr[54]);
				textArray[25].setText(tempstr[55]);
				h1et703.setText(tempstr[49]);
				String id = DB.klQueryCity1(this, "t_category",//行业ID转换
						"where Id = '" + des.jiaMi(textArray[5].getText().toString())
								+ "'");
				textArray[5].setText(id);
				String id1 = DB.klQueryCity1(this, "t_childtyp",//菜系ID转换
						"where Id = '" + des.jiaMi(textArray[6].getText().toString())
								+ "'");
				textArray[6].setText(id1);
				String id2 = DB.klQueryCity1(this, "t_trading",//商圈ID转换
						"where Id = '" + des.jiaMi(textArray[7].getText().toString())
								+ "'");
				textArray[7].setText(id2);
				String id3 = DB.klQueryCity1(this, "t_category",//商圈ID转换
						"where Id = '" + des.jiaMi(textArray[19].getText().toString())
								+ "'");
				textArray[19].setText(id3);
				String id4 = DB.klQueryCity1(this, "t_district",//区域ID转换
						"where Id = '" + des.jiaMi(tempstr[49])
								+ "'");
				hasMenInfoCityId = DB.klQueryCity2(this, "t_district",//区域ID转换
						"where Id = '" + des.jiaMi(tempstr[49])
								+ "'");
				chengshiname = DB.queryCityname(ZhongBuHuoDong31.this, hasMenInfoCityId);
				h1et702.setText(chengshiname);
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
				

				
				
				
		    	}
//             		}
      	
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("ZhongBuHuoDong31:init()" + e.getMessage());
		}	
    }
	
    
    
    class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch(id){
				case R.id.button3:
					if(validate()){
						saveData();
						intent.setClass(ZhongBuHuoDong31.this, HeYueQianDing_441.class);
						ZhongBuHuoDong31.this.startActivity(intent);
						ZhongBuHuoDong31.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
					
				break;
				case R.id.button2:
			
							intent.setClass(ZhongBuHuoDong31.this, MendianList.class);
							ZhongBuHuoDong31.this.startActivity(intent);
							ZhongBuHuoDong31.this.finish();
							overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
	
			}
		}
    }
    public void saveData() {
		String[] clomname = new String[] { "StName",
				"Stel", "StAdress", "StPostCode", "StHangye",
				"StCaixi", "StShangquan", "StNumber", "StClientNumber", "Stlong",
				"Stlat", "StContact","StMobileNumber","StChannel","StIsReceiveBill","StValidity","StBillRate" ,
				"IsNewStore","StLicense","Zhihangye","AreaId","StId","XuanChuan","PiceXuanChuan","CityId",
				"ShopZhubeia","ShopZhubeib","ShopZhubeic","ShopZhubeid"};
		String[] clomstr = new String[clomname.length];
		
		clomstr[0] = textArray[1].getText().toString();
		clomstr[1] = textArray[2].getText().toString();
		clomstr[2] = textArray[3].getText().toString();
		clomstr[3] = textArray[4].getText().toString();
		clomstr[4] = tempstr[7];
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
		clomstr[17] ="N";
		clomstr[18] = textArray[14].getText().toString();
		clomstr[19] = tempstr[48];
		clomstr[20] = h1et703.getText().toString();
		clomstr[22] = textArray[20].getText().toString();//商户类型
		clomstr[23] = textArray[21].getText().toString();//白名单编号
		//String cityid = DB.queryCityid(ZhongBuHuoDong31.this, h1et702.getText().toString());
		clomstr[24] = hasMenInfoCityId;//城市
		clomstr[25] = textArray[22].getText().toString();//备用A
		clomstr[26] = textArray[23].getText().toString();//备用B
		clomstr[27] = textArray[24].getText().toString();//备用C
		clomstr[28] = textArray[25].getText().toString();//备用D
		
		String id1=DB.klQueryCity(this, "t_childtyp", "where Name = '"+des.jiaMi(clomstr[5])+"'");
		clomstr[5]=id1;
		String id2=DB.klQueryCity(this, "t_trading", "where Name = '"+des.jiaMi(clomstr[6])+"' and CityId = '"+des.jiaMi(clomstr[24])+"'");
		clomstr[6]=id2;
		System.out.println("商圈==="+clomstr[6]);
		String id3=DB.klQuerytypecode(this, "t_mertype", "where typeCode = '"+des.jiaMi(clomstr[22])+"'");
		clomstr[22]=id3;
		clomstr[21] = tempstr[2];
		
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
				+ des.jiaMi(clomstr[20]) + "'"+" and CityId = '"+des.jiaMi(clomstr[24])+"'");
		clomstr[20] = id9;
		
		DB.upload(ZhongBuHuoDong31.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
    

	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(ZhongBuHuoDong31.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	private void getdata()
	{
	        MyDatabaseHelper myHelper = new MyDatabaseHelper(
					ZhongBuHuoDong31.this, "techown.db", Constant.tdshDBVer);
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
				}catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("团队：ZhongBuHuoDong31异常"+e.getMessage());
				}finally{
					if(cursor != null){
						cursor.close();
					}
					if(db != null){
						db.close();
					}
				}
				String id=DB.klQueryCity1(this, "t_category", "where Id = '"+des.jiaMi(textArray[5].getText().toString())+"'");
				textArray[5].setText(id);
				
				String id1=DB.klQueryCity1(this, "t_childtyp", "where Id = '"+des.jiaMi(textArray[6].getText().toString())+"'");
				textArray[6].setText(id1);
				String id2=DB.klQueryCity1(this, "t_trading", "where Id = '"+des.jiaMi(textArray[7].getText().toString())+"'");
				textArray[7].setText(id2);

				String id3=DB.klQueryCity1(this, "t_category", "where Id = '"+des.jiaMi(textArray[19].getText().toString())+"'");
				textArray[19].setText(id3);
				
				hasMenInfoCityId = DB.klQueryCity2(this,"t_district","where Id = '"
						+ des.jiaMi(h1et703.getText().toString()) + "'");
				chengshiname = DB.queryCityname(ZhongBuHuoDong31.this,hasMenInfoCityId);
				h1et702.setText(chengshiname);

				String name=DB.klQueryCity1(this, "t_district", "where Id = '"
						+ des.jiaMi(h1et703.getText().toString()) + "'");
						h1et703.setText(name);
				String id4=DB.klQueryCity11(this, "t_mertype", "where typeName = '"
						+ des.jiaMi(textArray[20].getText().toString()) + "'");
				textArray[20].setText(id4);
			}
	        
			
			Context friendContext = null;
			SQLiteDatabase sld =null;
			Cursor cur = null;
			/*try {
				friendContext = ZhongBuHuoDong31.this.createPackageContext("techown.login",
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
				techown.shanghu.https.Log.Instance().WriteLog("ZhongBuHuoDong31:techown.login()" + e.getMessage());
			}finally{
				if(cur != null){
					cur.close();
				}if(sld != null){
					sld.close();
				}
			}*/
	}
	
	protected boolean validate() {
			if (textArray[20].getText().toString().equals("")) {
				log.Toast(ZhongBuHuoDong31.this, "请输入商户类型！");
				return false;
			}
			if (textArray[19].getText().toString().equals("")) {
				log.Toast(ZhongBuHuoDong31.this, "请输入子行业 ！");
				return false;
			}
			if (textArray[21].getText().toString().equals("")) {
				log.Toast(ZhongBuHuoDong31.this, "请输入白名单编号！");
				return false;
			}
			typCode[1]=textArray[20].getText().toString();
			String id3=DB.klQuerytypecode(ZhongBuHuoDong31.this, "t_mertype", "where typeCode = '"+des.jiaMi(typCode[1])+"'");
			typCode[1]=id3;
			String name = null;
			if(textArray[21].getText().toString().length()>=typCode[1].length()){
				name=textArray[21].getText().toString().substring(0, typCode[1].length());
			}else{
				log.Toast(ZhongBuHuoDong31.this, "白名单编号与商户类型不符！");
				return false;

			}			String tname=typCode[1];
			System.out.println(name+" "+tname);
			if(!name.equals(tname)){
				log.Toast(ZhongBuHuoDong31.this, "白名单编号与商户类型不符！");
				return false;
			}
		return true;
	}
	
	 
	
}
