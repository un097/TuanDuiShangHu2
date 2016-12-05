package techown.shanghu;

import org.json.JSONObject;

import techown.shanghu.biaoge.ActionCode;
import techown.shanghu.biaoge.AlgorithmConvert;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.https.HttpConnection2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * 拒件--合约签订公司信息录入
 */
public class HeYueQianDing_21 extends Activity {

	
	protected static final String DialogUtil = null;
	public static String[] str;
		private Button button2, button3,button_shao;
	public static String  isomux;

	public String refuseCompanyCityId;

	String[] province;
	public static String requeststr1;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	public static String[] tempstr1;
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703区,7021详细地址
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73区，74详细地址

	int[] textIds = { R.id.h1et1, R.id.h1et2, R.id.h1et3, R.id.h1et4,
			R.id.h1et5, R.id.h1et6, R.id.h1et7, R.id.h1et8, R.id.h1et9,
			R.id.h1et10, R.id.h1et11, R.id.h1et12, R.id.h1et13 ,R.id.h1et14};
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.h1leout1,R.id.h1leout2,R.id.h1leout3,R.id.h1leout4,
			R.id.h1leout5,R.id.h1leout6,R.id.h1leout7,R.id.h1leout8,
			R.id.h1leout9,R.id.h1leout10,R.id.h1leout11,R.id.h1leout12,
			R.id.h1leout13,R.id.h1leout14,};
	public LinearLayout[] linlay;
	
	boolean ischeck=false;
	PadUtiltiy mPadUtiltiy;
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
	        	log.Toast(HeYueQianDing_21.this, b.getString("throwsmessage"));
	        	Intent intent = new Intent();
	        	intent.setClass(HeYueQianDing_21.this, HeYueQianDing_31.class);
	        	HeYueQianDing_21.this.startActivity(intent);
	        	HeYueQianDing_21.this.finish();
	        	break;
	        case 3:
	        	 b = msg.getData();
	        	 log.Toast(HeYueQianDing_21.this, b.getString("throwsmessage"));
	        	/* intent = new Intent();
	        	intent.setClass(HeYueQianDing_21.this, HeYueQianDing_31.class);
	        	HeYueQianDing_21.this.startActivity(intent);
	        	HeYueQianDing_21.this.finish();	*/
	        	break;
	        case 4:
	        	 b = msg.getData();
	        	 log.Toast(HeYueQianDing_21.this, b.getString("throwsmessage"));
		        intent = new Intent();
	        	break;	
	        case 5:
	        	 b = msg.getData();
	        	 log.Toast(HeYueQianDing_21.this, b.getString("throwsmessage"));
		        intent = new Intent();
		        intent.setClass(HeYueQianDing_21.this, HeYueQianDing_31.class);
		        HeYueQianDing_21.this.startActivity(intent);
		        HeYueQianDing_21.this.finish();	
	        	break;
	        }   
	    }   
	};   
	
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
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heyueqianding_2);
		mDialog = new AlertDialog.Builder(HeYueQianDing_21.this).create();
		DB.upload(HeYueQianDing_21.this, new String[]{"Dnum"}, new String[]{"2"},
				TuanDuiShangHuActivity.num);
		// 调用侧边栏
//		panel = new Panel(this);
		try{
		mPadUtiltiy = new PadUtiltiy(HeYueQianDing_21.this);
		
		ActionCode ac=new ActionCode();
		String barcode=ac.runVerifyCode(15);
		isomux = AlgorithmConvert.backtonum(barcode);
		
		
		
		//button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		
		h1et7 = (TextView) findViewById(R.id.h1et7);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
		h1et7021 = (TextView) findViewById(R.id.h1et7021);
		h1leout71 = (LinearLayout)findViewById(R.id.h1leout71);
		h1leout72 = (LinearLayout)findViewById(R.id.h1leout72);
		h1leout73 = (LinearLayout)findViewById(R.id.h1leout73);
		h1leout74 = (LinearLayout)findViewById(R.id.h1leout74);
		
//		linerlout=(LinearLayout)findViewById(R.id.h1leout);
		linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
	

		// 设置监听
		MyLis lis = new MyLis();
		//button1.setOnClickListener(lis);
		button2.setOnClickListener(lis);
		button3.setOnClickListener(lis);

		button_shao = (Button) findViewById(R.id.shao);
		

		button_shao.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				if(TuanDuiShangHuActivity.device == 0){
//					Intent i = new Intent("cn.iandroid.codescan.ScanAndDecode");
//					i.putExtra("charset", (String) null);
//					startActivityForResult(i, 0);
//				}else if(TuanDuiShangHuActivity.device == 1){
					mPadUtiltiy.startBarCode();
					ischeck=true;
//				}
			}
		});
		
		
		
		init();
		
		DES des = new DES();SQLiteDatabase db=null;Cursor cursor=null;
		try{
		MyDatabaseHelper myHelper = new MyDatabaseHelper(
				HeYueQianDing_21.this, "techown.db",Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.num });
		while (cursor.moveToNext()) {
				String tiaoxinma=des.jieMI(cursor.getString(cursor
						.getColumnIndex("ContractNumber")));
			if(tiaoxinma != null&&tiaoxinma.length()>0){
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ContractNumber"))));
			}
			
			
//				String name=DB.klQueryCity1(this, "t_district", "where Id = '"
//				+ h1et703.getText().toString() + "'");
//				h1et703.setText(name);
									
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
	}catch(Exception e){
		techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_21类："+e.getMessage());
	}
	}
	String a=null;
	public void saveData() {
		try{
		String[] clomname = new String[] { "CompanyName", "LegalPerson",
				"ContactUser", "TelNumber", "MobileNumber", "EngageName",
				"Adress","PostCode","EngageScope", "Brand", "BrandTelNumber", 
				"Validity", "EngageYear", "ContractNumber","CompanyId" ,"Gcityid","Gdistrictid"};
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
		clomstr[14] = companyId;
//		System.out.println(textArray[13].getText().toString());
		clomstr[15] = h1et702.getText().toString();
		clomstr[16] = h1et703.getText().toString();
		DES des=new DES();
		/*String id = DB.klQueryCity(this, "t_city", "where Name = '"
				+ des.jiaMi(clomstr[15]) + "'");*/
		clomstr[15] = refuseCompanyCityId;;

		String id1 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[16]) + "'"+" and CityId = '"+des.jiaMi(clomstr[15])+"'");
		System.out.println("id1==="+id1);
		clomstr[16] = id1;

//		if(!tempstr1[17].equals("")){
//			clomstr[17] ="N";
//		}else{
//			clomstr[17] ="Y";
//		}
		
		

		
		// isomux=textArray[13].getText().toString();
		DB.upload(HeYueQianDing_21.this, clomname, clomstr,
				TuanDuiShangHuActivity.num);
		
//		DB.upload(HeYueQianDing_21.this, new String[]{"CityId"},new String[]{TuanDuiShangHuActivity.cityId}, TuanDuiShangHuActivity.num);
		
		}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_21类saveData（）"+e.getMessage());
		}
	}
	

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			try{
			// TODO Auto-generated method stub
			int id = v.getId();
			final Intent intent = new Intent();
			switch (id) {
			case R.id.button3:
				if(!textArray[13].getText().equals("")){
					
			
				saveData();
				 Message msg = new Message();   
			        msg.what =1; 
					handler.sendMessage(msg);//
					 new Thread(){
						 public void run(){
							 
							 
							 String[]str1=new String[]{"",""};	
							 str1[0]=ShangHuChaXun.Id;
							 str1[1]=ShangHuChaXun.actId;
							 
							 String json1="{" +
				 				"\"merId\":\""+str1[0]+"\"," +
				 				"\"actId\":\""+str1[1]+"\"}";	
							 HeYueQianDing_31.requeststr1=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0010"), json1);
							 if(HeYueQianDing_31.requeststr1!=null&&HeYueQianDing_31.requeststr1.length()>0){
								 try {
									JSONObject demoJson1 = new JSONObject(HeYueQianDing_31.requeststr1);
									if(demoJson1.getString("rspCode").equals("00"))
									{
										intent.setClass(HeYueQianDing_21.this, HeYueQianDing_31.class);
										HeYueQianDing_21.this.startActivity(intent);
										HeYueQianDing_21.this.finish();
										overridePendingTransition(R.anim.leftin, R.anim.leftout);
										 mDialog.dismiss(); 
									}
									else if(demoJson1.equals("")){
										Message msg = new Message();   
										Bundle b = new Bundle();
										b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson1.getString("rspInfo"));
										msg.setData(b);
								        msg.what =4; 
										handler.sendMessage(msg);//
										mDialog.dismiss();
									}else
									{
										Message msg = new Message();   
										Bundle b = new Bundle();
										b.putString("throwsmessage",demoJson1.getString("rspInfo"));
										msg.setData(b);
								        msg.what =4; 
										handler.sendMessage(msg);//
										mDialog.dismiss();
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_21类button3点击：：："+e.getMessage());
								}
							
							 }
							 else 
								{
								 try{
									
									Message msg = new Message();   
									Bundle b = new Bundle();
									b.putString("throwsmessage","请求失败，确认网络连接正常后重新点击下一步！");
									msg.setData(b);
							        msg.what =4; 
									handler.sendMessage(msg);//
									mDialog.dismiss();
								 }catch(Exception e){
									 techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_21类button3点击"+e.getMessage());
								 }
								}
						 }}.start();	
				
				}else {
					log.Toast(HeYueQianDing_21.this, "请输入条形码 ！");
				}
				
				
				break;
			case R.id.button2:

				intent.setClass(HeYueQianDing_21.this, ShangHuChaXun.class);
				HeYueQianDing_21.this.startActivity(intent);
				HeYueQianDing_21.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);	
				break;

			}
		}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_21类按钮点击："+e.getMessage());
			}
		}
	}
	public  String companyId;
	 public void init()
	    {
	    	try
			{
	    		JSONObject demoJson1 = new JSONObject(requeststr1);
				if(demoJson1.getString("rspCode").equals("00"))
				{
					tempstr1=new String[19];
					tempstr1[0]=demoJson1.getString("name");
					tempstr1[1]=demoJson1.getString("companyId");
					companyId = demoJson1.getString("companyId");//公司ID
					tempstr1[2]=demoJson1.getString("legalPerson");
					tempstr1[3]=demoJson1.getString("contactPerson");
					tempstr1[4]=demoJson1.getString("contactTel");//contactTel	联系电话
					tempstr1[5]=demoJson1.getString("handPhone");//	handPhone	手机			
					tempstr1[6]=demoJson1.getString("busiName");
					tempstr1[7]=demoJson1.getString("address");
					tempstr1[8]=demoJson1.getString("busi");
					tempstr1[9]=demoJson1.getString("brand");
					tempstr1[10]=demoJson1.getString("brandHotLine");
					tempstr1[11]=demoJson1.getString("zip");
					tempstr1[12]=demoJson1.getString("validDate");
					tempstr1[13]=demoJson1.getString("busiYear");
					tempstr1[14]=demoJson1.getString("province");//省
					//tempstr1[15]=demoJson1.getString("city");//市
					refuseCompanyCityId = demoJson1.getString("city");//市
					tempstr1[15] = refuseCompanyCityId;
					tempstr1[16]=demoJson1.getString("area");//区textArray[13]
					
					tempstr1[17]=demoJson1.getString("agrmId");
					tempstr1[18]=demoJson1.getString("agrmType");
					if(tempstr1[18].equals("S")&&tempstr1[17]!=null)
					{
						textArray[13].setText(tempstr1[17]);
						button_shao.setEnabled(false);
						DB.upload(HeYueQianDing_21.this, new String[]{"Dnum"}, new String[]{"1"},
						TuanDuiShangHuActivity.num);
					}
					else
					{
						String[] leixing = tempstr1[18].split("\\|");
						String[] tiaoxing=tempstr1[17].split("\\|");
						for (int i = 0; i < leixing.length; i++) {
							if(leixing[i].equals("S"))
							{
								textArray[13].setText(tiaoxing[i]);
								button_shao.setEnabled(false);
								DB.upload(HeYueQianDing_21.this, new String[]{"Dnum"}, new String[]{"1"},
								TuanDuiShangHuActivity.num);
							}
						}
					}
//					if(!tempstr1[17].equals("")){
//						 String []	tiaoxing=tempstr1[17].split("\\|");
//				    		textArray[13].setText(tiaoxing[0]);
//				    		button_shao.setEnabled(false);
//				    		DB.upload(HeYueQianDing_21.this, new String[]{"Dnum"}, new String[]{"1"},
//									TuanDuiShangHuActivity.num);
//					}else{
//						DB.upload(HeYueQianDing_21.this, new String[]{"Dnum"}, new String[]{"2"},
//								TuanDuiShangHuActivity.num);
//					}
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
					
					textArray[0].setText(tempstr1[0]);
					textArray[1].setText(tempstr1[2]);
					textArray[2].setText(tempstr1[3]);
					textArray[3].setText(tempstr1[4]);
					textArray[4].setText(tempstr1[5]);
					textArray[5].setText(tempstr1[6]);
					textArray[7].setText(tempstr1[11]);
					textArray[8].setText(tempstr1[8]);
					textArray[9].setText(tempstr1[9]);
					textArray[10].setText(tempstr1[10]);
					textArray[11].setText(tempstr1[12]);
					textArray[12].setText(tempstr1[13]);
//					String []tiaoxing=tempstr1[17].split("\\|");
//		    		textArray[13].setText(tiaoxing[0]);
					
					h1et7.setText(tempstr1[14]);
					h1et702.setText(tempstr1[15]);
					h1et703.setText(tempstr1[16]);
					h1et7021.setText(tempstr1[7]);
				}
			}catch(Exception e)
			{
				techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing_21类init():"+e.getMessage());
			}	
	    }

	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(HeYueQianDing_21.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
