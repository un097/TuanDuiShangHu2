
package techown.shanghu;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

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
 * 最红管理--合约签订门店信息录入（新建门店）
 */
public class RedShopMessageBox  extends Activity{
	EditDialog log=new EditDialog();
	public static String requeststr1;
	public static String zhi;
	String isnewshop;
	String merid;
	DBUtil DB=new DBUtil();
	static String chengshiname;
	Button button2,button3,button_jwd;
	GetData getDasta=new GetData();
	String[] typCode =new String[2];
	int[] textIds = { R.id.h2edtext1, R.id.h2edtext2, R.id.redh2edtext3, R.id.redh2edtext4,
			R.id.h2edtext5, R.id.h2edtext6, R.id.h2edtext7, R.id.h2edtext8, 
			R.id.h2edtext11, R.id.h2edtext12, R.id.h2edtext13, R.id.h2edtext14,
			R.id.redh2edtext15,R.id.h2edtext16,R.id.h2edtext17,R.id.h2edtext18,R.id.redh2edtext19,
			R.id.h2edtext222,R.id.h2edtext20,R.id.h2edtext21,R.id.redsparetext1,R.id.redsparetext2,
			R.id.redsparetext3,R.id.redsparetext4};
	public static TextView[] textArray;
	DES des = new DES();
	int[] linlayIds={R.id.h3leout1,R.id.h3leout2,R.id.h3leout3,R.id.h3leout4,
			R.id.h3leout5,R.id.h3leout6,R.id.h3leout7,R.id.h3leout8,
			R.id.h3leout11,R.id.h3leout12,
			R.id.h3leout13,R.id.h3leout14,R.id.h3leout15,R.id.h3leout16,R.id.h3leout17,
			R.id.h3leout18,R.id.h3leout19,R.id.h3leout20,R.id.h3leout21,
			R.id.redspareout1,R.id.redspareout2,R.id.redspareout3,R.id.redspareout4};
	public static LinearLayout[] linlay;
	String[]t_trading,t_category,t_childtyp,t_category1;
	
	static List<String> s;
	static List<String> s1;
	static List<String> s2;
	static List<String> s3;
	public  static Object[] name;
	String[] bb;
	String[][] typename;
	List<TArea> tareaList1=null;
	List<TArea> tareaList2=null;
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703区,7021详细地址
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73区，74详细地址
	String[] province;
	String ccc;
	public String cgRedFiveMenInfoCityId;

    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redshopmessage);

        getdata(RedShopMessageBox.this);
        typename = DB.klQuery21(RedShopMessageBox.this, "typeCode", "typeNum", "t_mertype");
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
        
       
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		textArray[13].setText("办事处");
		
		linlay[16].setEnabled(false);
		linlay[6].setEnabled(false);
		
        MyLis lis = new MyLis();

        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
        
        
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				RedShopMessageBox.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from red_enterletter where id==?",
				new String[] { TuanDuiShangHuActivity.tmpId7 });
		while (cursor.moveToNext()) {
			try {
				
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyName"))));
				textArray[1].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("BusinessName"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("BusinessNunber"))));
				textArray[3].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("BusinessAdress"))));
				textArray[4].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopCode"))));
				textArray[17].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopTrade"))));
				textArray[6].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopGreens"))));
				textArray[7].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopDealer"))));
				textArray[8].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Longitude"))));
				
				textArray[9].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("LatiTude"))));
				textArray[10].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopUserName"))));
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopMobileNumber"))));
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StChannel"))));//拓展渠道
				textArray[14].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StIsReceiveBill"))));//是否收单
				textArray[15].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StValidity"))));
				textArray[16].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StBillRate"))));
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopLicense"))));
				textArray[18].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("mertype"))));//商户类型
				textArray[19].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("whitenum"))));//白名单编号
				
				textArray[20].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("RedShopZhubeia"))));//注备1
				textArray[21].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("RedShopZhubeib"))));//注备2
				textArray[22].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("RedShopZhubeic"))));//注备3
				textArray[23].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("RedShopZhubeid"))));//注备4
				
				if(textArray[13].getText().toString().equals("W")){
					textArray[13].setText("外包");
				}else if(textArray[13].getText().toString().equals("D")){
					textArray[13].setText("办事处");
				}else if(textArray[13].getText().toString().equals("B")){
					textArray[13].setText("分行");
				}else if(textArray[13].getText().toString().equals("C")){
					textArray[13].setText("卡中心");
				}
				
				if(textArray[14].getText().toString().equals("Y")){
					textArray[14].setText("是");
				}else if(textArray[14].getText().toString().equals("N")){
					textArray[14].setText("否");
				}
				
				h1et703.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopArea"))));
				/*
				 * 记录此页面中的城市Id
				 */
				cgRedFiveMenInfoCityId = des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopCity")));
				
				h1et702.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopCity"))));
				//城市名称
				String chengshiname = DB.queryCityname(RedShopMessageBox.this, h1et702.getText().toString());
				h1et702.setText(chengshiname);
				String arearname = DB.klQueryarear2(this, "t_district", "where Id = '"
						+ des.jiaMi(h1et703.getText().toString()) + "'");
				h1et703.setText(arearname);
				
				isnewshop = des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsNewShop")));
				if(isnewshop.equals("N")){
					h1leout72.setEnabled(false);
					h1leout73.setEnabled(false);
					for(int i = 0; i<linlayIds.length;i++){
						linlay[i].setEnabled(false);
		    		}
					merid = des.jieMI(cursor.getString(cursor
							.getColumnIndex("ShopId")));
				}
			
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
			if(!textArray[17].getText().toString().equals(""))
			{
				String zhuid = textArray[17].getText().toString().substring(0, 4);
				String zhuname=DB.klQueryCity1(this, "t_category", "where Id = '"+des.jiaMi(zhuid)+"'");
				textArray[5].setText(zhuname);
				System.out.println("主行业==="+zhuname);
			}
			String id=DB.klQueryCity1(this, "t_category", "where Id = '"+des.jiaMi(textArray[17].getText().toString())+"'");
			textArray[17].setText(id);
			
			String id1=DB.klQueryCity1(this, "t_childtyp", "where Id = '"+des.jiaMi(textArray[6].getText().toString())+"'");
			textArray[6].setText(id1);
			if(id1!=null)
			{
				linlay[6].setEnabled(true);
			}
			else
			{
				linlay[6].setEnabled(false);
			}
			String id2=DB.klQueryCity1(this, "t_trading", "where Id = '"+des.jiaMi(textArray[7].getText().toString())+"'");
			textArray[7].setText(id2);
			
			String id4=DB.klQueryCity11(this, "t_mertype", "where typeName = '"
					+ des.jiaMi(textArray[18].getText().toString()) + "'");
			textArray[18].setText(id4);
		}
        
		h1leout72.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				log.red_dialog(RedShopMessageBox.this, h1et702, "市",
						TuanDuiShangHuActivity.agencycityname);
				h1et703.setText("");
				textArray[7].setText("");
			}
		});
		h1leout73.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!h1et702.getText().toString().equals("")&&h1et702.getText().toString()!=null)
				{
					/*String[] str1 = getDasta.getCity11(RedShopMessageBox.this,
							h1et702.getText().toString());*/
					/*
					 * 记录此页面选择的城市Id
					 */
					cgRedFiveMenInfoCityId = (String)TuanDuiShangHuActivity.agencyCityAndIdMap
							.get(h1et702.getText().toString());
					
					String[] str1 = getDasta.getCity12(RedShopMessageBox.this,
							cgRedFiveMenInfoCityId);
					if (str1.length > 0) {
						log.red_dialog(RedShopMessageBox.this, h1et703, "区",
								str1);
						
					} else {
						h1et703.setText("无");
						h1et703.setEnabled(false);
					}
				}
				else
				{
					log.Toast(RedShopMessageBox.this, "请选择城市！");
				}
			}
		});
        
        linlay[1].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(RedShopMessageBox.this,linlay[1], textArray[1],R.id.h2edtext2,"请输入营业名称");
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
					log.etDialog(RedShopMessageBox.this, textArray[2],R.id.redh2edtext3,"请输入营业电话");
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
					log.Dialog(RedShopMessageBox.this,linlay[3], textArray[3],R.id.redh2edtext4,"请输入营业地址");
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
					log.etDialog(RedShopMessageBox.this,textArray[4],R.id.h2edtext5,"请输入邮编");
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
					log.istwo = true;
					log.num = 7;
					// lxp
					log.dialog21111(RedShopMessageBox.this, textArray[5],"主行业", name);
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
					if(textArray[5].getText().toString().equals("餐饮"))
					{
						log.red_dialog(RedShopMessageBox.this,textArray[6],"菜系",t_childtyp);
					}
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
						 * 根据城市Id 查找商圈
						 */
						String[] t_trading1 = queryTradingNameByCityId(cgRedFiveMenInfoCityId);
						if(t_trading1!=null&&t_trading1.length>0)
						{
							log.dialog(RedShopMessageBox.this, textArray[7], "商圈",
									t_trading1);
						}
						else
						{
							log.Toast(RedShopMessageBox.this, "该城市没有商圈！");
						}
					}
					else
					{
						log.Toast(RedShopMessageBox.this, "请先选择城市！");
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
					log.etDialog(RedShopMessageBox.this, textArray[8],R.id.h2edtext11,"请输入经度");
					
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
					log.etDialog(RedShopMessageBox.this,textArray[9],R.id.h2edtext12,"请输入纬度");
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
					log.Dialog(RedShopMessageBox.this,linlay[10], textArray[10],R.id.h2edtext13,"请输入联系人");
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
					log.etDialog(RedShopMessageBox.this,textArray[11],R.id.h2edtext14,"请输入手机号");
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
					log.Dialog(RedShopMessageBox.this,linlay[12], textArray[12],R.id.redh2edtext15,"请输入门店工商执照");
					break;
				default:
					break;
				}

				return false;
			}
		});
        final String[] province1={"办事处","外包","分行","卡中心"};
        linlay[13].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.red_dialog(RedShopMessageBox.this,textArray[13],"拓展渠道",province1);
					break;
				default:
					break;
				}

				return false;
			}
		});
        final String[] province={"是","否"};
        linlay[14].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.istwo=true;
					log.num=11;
					log.red_dialog(RedShopMessageBox.this,textArray[14],"是否收单",province);
					break;
				default:
					break;
				}

				return false;
			}
		});
       
        	
        
	        linlay[15].setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_UP:
						DateDialog.showDatePicker(RedShopMessageBox.this, textArray[15],R.id.h2edtext18);
						break;
					default:
						break;
					}
	
					return false;
				}
			});
        
        linlay[16].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.etDialog(RedShopMessageBox.this,textArray[16],R.id.redh2edtext19,"请输入收单费率");
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
					log.dialog(RedShopMessageBox.this, textArray[18],
							"类型", bb);
					textArray[19].setText("");
					
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
					log.Dialog(RedShopMessageBox.this, linlay[18],textArray[19],
							R.id.h2edtext21,"请输入白名单编号");
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
					log.Dialog(RedShopMessageBox.this, linlay[19],textArray[20],R.id.redsparetext1,"请输入注备1");
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
					log.Dialog(RedShopMessageBox.this, linlay[20],textArray[21],R.id.redsparetext2,"请输入注备2");
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
					log.Dialog(RedShopMessageBox.this, linlay[21],textArray[22],R.id.redsparetext3,"请输入注备3");
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
					log.Dialog(RedShopMessageBox.this, linlay[22],textArray[23],R.id.redsparetext4,"请输入注备4");
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
    
    List<TextView> ls=new ArrayList<TextView>();
    List<TextView> ls1=new ArrayList<TextView>();
    String preId;
    
    class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch(id){
				case R.id.button3:
					if(validate()){
						saveData();
						intent.setClass(RedShopMessageBox.this, RedPosterMessageBox.class);
						RedShopMessageBox.this.startActivity(intent);
						RedShopMessageBox.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
				break;
				case R.id.button2:
						intent.setClass(RedShopMessageBox.this, RedCompanyMessageBox.class);
						RedShopMessageBox.this.startActivity(intent);
						RedShopMessageBox.this.finish();
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
		String[] clomname = new String[] { 
				"IsNewShop",//20是否新增门店
				"ShopId",//21门店ID
				"ShopLicense",//22门店工商执照
				"BusinessName", //23营业名称
				"BusinessNunber", //24营业电话
				"BusinessAdress",//25营业地址
				"ShopCode", //26门店邮编
				"ShopTrade",//27门店行业
				"ShopGreens", //28门店菜系
				"ShopDealer", //29门店商圈
				"Longitude",//30经度
				"LatiTude", //31纬度
				"ShopUserName",//32门店联系人
				"ShopTelNumber",//33门店电话
				"ShopMobileNumber",//34门店手机
				"StChannel",//35拓展渠道
				"StIsReceiveBill",//36是否收单
				"StValidity" ,//37有效期
				"StBillRate",//38收单费率
				"ShopCity",//74门店所属城市
				"ShopArea",//75门店所属行政区
				"mertype",//76商户类型
				"whitenum",//77白名单
				"RedShopZhubeia",//78注备A
				"RedShopZhubeib",//79注备B
				"RedShopZhubeic",//80注备C
				"RedShopZhubeid"};//81注备D;//77白名单编号
		String[] clomstr = new String[clomname.length];
		
		clomstr[0] =isnewshop;
		if(isnewshop.equals("N"))
		{
			clomstr[1] =merid;
		}
		else if(isnewshop.equals("Y"))
		{
			clomstr[1] ="";
		}
		clomstr[2] =textArray[12].getText().toString();
		clomstr[3] =textArray[1].getText().toString();
		clomstr[4] =textArray[2].getText().toString();
		clomstr[5] =textArray[3].getText().toString();
		clomstr[6] =textArray[4].getText().toString();
		String zihangyeid = DB.categoryid(RedShopMessageBox.this,textArray[17].getText().toString());
		System.out.println("子行业："+zihangyeid);
		clomstr[7] = zihangyeid;//子行业
		clomstr[8] =textArray[6].getText().toString();
		clomstr[9] =textArray[7].getText().toString();
		String id1=DB.klQueryCity(this, "t_childtyp", "where Name = '"+des.jiaMi(clomstr[8])+"'");
		clomstr[8]=id1;
		String id2=DB.klQueryCity(this, "t_trading", "where Name = '"+des.jiaMi(clomstr[9])+"'");
		clomstr[9]=id2;
		clomstr[10] =textArray[8].getText().toString();
		clomstr[11] =textArray[9].getText().toString();
		clomstr[12] =textArray[10].getText().toString();
		clomstr[13] =textArray[2].getText().toString();
		clomstr[14] =textArray[11].getText().toString();
		clomstr[15] =textArray[13].getText().toString();
		String id4 = null;
		if(clomstr[15].equals("外包")){
			id4="W";
		}else if(clomstr[15].equals("办事处")){
			id4="D";
		}else if(clomstr[15].equals("分行")){
			id4="B";
		}else if(clomstr[15].equals("卡中心")){
			id4="C";
		}
		clomstr[15]=id4;
		clomstr[16] = textArray[14].getText().toString();
		String id5=null;
		if(clomstr[16].equals("是")){
			id5="Y";
		}else if(clomstr[16].equals("否")){
			id5="N";
		}else{
			id5="";
		}
		clomstr[16] =id5;
		clomstr[17] = textArray[15].getText().toString();
		clomstr[18] =textArray[16].getText().toString();
		//String cityid = DB.queryCityid(RedShopMessageBox.this, h1et702.getText().toString());
		clomstr[19] =cgRedFiveMenInfoCityId;//记录此页面选择的城市Id
		clomstr[20] = h1et703.getText().toString();
		String id9 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[20]) + "'"+" and CityId = '"+des.jiaMi(clomstr[19])+"'");
		clomstr[20] = id9;
		clomstr[21] = textArray[18].getText().toString();//商户类型
		String id3=DB.klQuerytypecode(this, "t_mertype", "where typeCode = '"+des.jiaMi(clomstr[21])+"'");
		clomstr[21]=id3;
		clomstr[22] = textArray[19].getText().toString();//白名单编号
		
		clomstr[23] = textArray[20].getText().toString();//注备1
		clomstr[24] = textArray[21].getText().toString();//注备2
		clomstr[25] = textArray[22].getText().toString();//注备3
		clomstr[26] = textArray[23].getText().toString();//注备4
		
		DB.Red_upload(RedShopMessageBox.this, clomname, clomstr, TuanDuiShangHuActivity.tmpId7);
	}
    
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(RedShopMessageBox.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
	
	boolean m = true;

	// 验证必填字段的是否为空
	protected boolean validate() {
		if (textArray[1].getText().toString().equals("")) {
			
			log.Toast(RedShopMessageBox.this, "请输入营业名称！");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			
			log.Toast(RedShopMessageBox.this, "请输入营业电话！");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			
			log.Toast(RedShopMessageBox.this, "请输入营业地址！");
			return false;
		}
		if (textArray[4].getText().toString().equals("")) {
			
			log.Toast(RedShopMessageBox.this, "请输入邮编！");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			
			log.Toast(RedShopMessageBox.this, "请输入行业 ！");
			return false;
		}
		if (textArray[7].getText().toString().equals("")) {
			
			log.Toast(RedShopMessageBox.this, "请输入商圈！");
			return false;
		}
//		if (textArray[8].getText().toString().equals("")) {
//			
//			log.Toast(RedShopMessageBox.this, "请输入经度！");
//			return false;
//		}
//		if (textArray[9].getText().toString().equals("")) {
//	
//			log.Toast(RedShopMessageBox.this, "请输入纬度！");
//			return false;
//		}
		if (textArray[10].getText().toString().equals("")) {
	
			log.Toast(RedShopMessageBox.this, "请输入联系人！");
			return false;
		}
		if (h1et702.getText().toString().equals("")) {
			
			log.Toast(RedShopMessageBox.this, "请选择门店所在城市 ！");
			return false;
		}
		if(h1et703.getText().toString().equals("")){
			
			log.Toast(RedShopMessageBox.this, "请选择行政区！");
			return false;
		}
		if(textArray[5].getText().toString().equals("餐饮")&&textArray[6].getText().toString().equals("")){
			
			log.Toast(RedShopMessageBox.this, "请选择菜系！");
			return false;
		}
//		if (textArray[13].getText().toString().equals("")) {
//			
//			log.Toast(RedShopMessage.this, "请输入拓展渠道 ！");
//			return false;
//		}
//		if (textArray[14].getText().toString().equals("是")&&textArray[16].getText().toString().equals("")) {
//			
//			log.Toast(RedShopMessage.this, "请输入收单费率 ！");
//			return false;
//		}
		if (textArray[18].getText().toString().equals("")||textArray[18].getText().toString()==null) {
			log.Toast(RedShopMessageBox.this, "请输入商户类型！");
			return false;
		}
//		if (textArray[12].getText().toString().equals("")) {
//			log.Toast(RedShopMessageBox.this, "请输入门店工商执照！");
//		  	return false;
//		 }
		if (textArray[19].getText().toString().equals("")) {
			log.Toast(RedShopMessageBox.this, "请输入白名单编号！");
			return false;
		}
		typCode[1]=textArray[18].getText().toString();
		String id3=DB.klQuerytypecode(RedShopMessageBox.this, "t_mertype", "where typeCode = '"+des.jiaMi(typCode[1])+"'");
		typCode[1]=id3;
		String name = null;
		if(textArray[19].getText().toString().length()>=typCode[1].length()){
			name=textArray[19].getText().toString().substring(0, typCode[1].length());
		}else{
			log.Toast(RedShopMessageBox.this, "白名单编号与商户类型不符！");
			return false;

		}		
		String tname=typCode[1];
		System.out.println(name+" "+tname);
		if(!name.equals(tname)){
			log.Toast(RedShopMessageBox.this, "白名单编号与商户类型不符！");
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
				}
				
				tmpCur = db.rawQuery(
						"select Name from t_trading where CityId = ?",
						new String[] { des.jiaMi(cityId) });
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
		  List<TArea> tareaList=DB.klQuery(RedShopMessageBox.this,"t_category");
	        t_category=new String[tareaList.size()];
	        for(int i=0;i<tareaList.size();i++){
	        	t_category[i]=tareaList.get(i).name;
	        	t_category1 = new String[tareaList.size()];
	        }
	        tareaList1=DB.klQuery(RedShopMessageBox.this,"t_childtyp");
	        t_childtyp=new String[tareaList1.size()];
	        for(int i=0;i<tareaList1.size();i++){
	        	t_childtyp[i]=tareaList1.get(i).name;
	        	
	        }
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
