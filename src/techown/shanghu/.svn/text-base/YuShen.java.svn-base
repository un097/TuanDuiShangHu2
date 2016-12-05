 package techown.shanghu;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetDate;
import techown.shanghu.https.HttpConnection2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
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
 * 商户预审
 */
public class YuShen extends Activity {
	Button button2, button1, button_add;
	//TextView yedtext5,yedtext6,yedtext7,yedtext8;
	private LinearLayout ss_layoutAll;
	TextView  yedtext5, yedtext6, yedtext7,yedtext8; 
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	
	int[] textIds = { R.id.yedtext1, R.id.yedtext2, R.id.yedtext3,
			R.id.yedtext4, R.id.yedtext5, R.id.yedtext6, R.id.yedtext7,
			R.id.yedtext8 };
	public static TextView[] textArray;

	int[] linlayIds = { R.id.yleout1, R.id.yleout2, R.id.yleout3, R.id.yleout4,
			R.id.yleout5, R.id.yleout6, R.id.yleout7, R.id.yleout8 };
	public LinearLayout[] linlay;
	public List<String> list2;

	
	String[] province;

	//lxp
	JSONObject jsonstr=new JSONObject();
	 JSONArray ja = new JSONArray();
	 
	//lxp
	 
	 EditDialog ds=new EditDialog();
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
		        	 b = msg.getData();
		        	 
		        	 
		        	 ds.Toast(YuShen.this,b.getString("throwsmessage"));
			        break;
		        case 3:
		        	new AlertDialog.Builder(YuShen.this)
					.setTitle("提示")
					.setMessage(rspInfo)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method
									
									Intent intent = new Intent();
									intent.setClass(
											YuShen.this,
											TuanDuiShangHuActivity.class);
									YuShen.this
											.startActivity(intent);
									techown.shanghu.https.OperateLog.Instance().WriteLog("商户团队预审提交成功！");
									YuShen.this.finish();
								}
							}).show();
		        	break;
		        }
		    }
		};
	 
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yushen);
		list2=new ArrayList<String>();
		initChView();

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());
		mDialog = new AlertDialog.Builder(YuShen.this).create();//连接网络进程

		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);

		province = DB.klQuery(YuShen.this, "Name", "t_province");
		
		linlay = new LinearLayout[linlayIds.length];
		for (int i = 0; i < linlayIds.length; i++) {
			linlay[i] = (LinearLayout) findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}

		MyLis lis = new MyLis();
		button1.setOnClickListener(lis);
		button2.setOnClickListener(lis);

		

		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(YuShen.this, linlay[0], textArray[0],
							R.id.yedtext1,"请输入商户名");
					
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
				case MotionEvent.ACTION_UP:
					log.Dialog(YuShen.this, linlay[1], textArray[1],
							R.id.yedtext2,"请输入地址");
					
	                
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
					log.Dialog(YuShen.this, linlay[2], textArray[2],
							R.id.yedtext3,"请输入联系人");
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
					log.etDialog(YuShen.this, textArray[3],
							R.id.yedtext4,"请输入电话");

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
					DateDialog.showDatePicker(YuShen.this, textArray[4],
							R.id.yedtext5);
					aaa=1;
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
					log.etDialog(YuShen.this, textArray[5],
							R.id.yedtext6,"请输入交易金额");
					bbb=1;
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
					log.etDialog(YuShen.this, textArray[6],
							R.id.yedtext7,"请输入授权号");
					ccc=1;
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
					log.etDialog(YuShen.this, textArray[7],
							R.id.yedtext8,"请输入卡号");
					ddd=1;
					break;
				default:
					break;
				}

				return false;
			}
		});

	}

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
			case R.id.button2:
				
				
				log.ExitApp(YuShen.this);
				
				break;
			case R.id.button1:
		
				if (validate()) {

					
//					
						if (aaa==0) {
							log.Toast(YuShen.this, "请输入交易日期！");
							//Toast.makeText(YuShen.this, "请输入交易日期！", Toast.LENGTH_LONG).show();
						}else if (bbb==0) {
							log.Toast(YuShen.this,"请输入交易金额 ！");
							//Toast.makeText(YuShen.this, "请输入交易金额 ！", Toast.LENGTH_LONG).show();
						}else if (ccc==0) {
							log.Toast(YuShen.this, "请输入授权号 ！");
							//Toast.makeText(YuShen.this, "请输入授权号 ！", Toast.LENGTH_LONG).show();
						}else if (ddd==0) {
							log.Toast(YuShen.this, "请输入卡号 ！");
							//Toast.makeText(YuShen.this, "请输入卡号 ！", Toast.LENGTH_LONG).show();
						}
						else {
							
							 Message msg = new Message();   
						     msg.what =1; 
							 handler.sendMessage(msg);//
							 new Thread()
								{
									public void run()
									{
							
							try {
								Request1();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								techown.shanghu.https.Log.Instance().WriteLog("YuShen:Request1()" + e.getMessage());
							}
						if(rspCode!=null)
						{
							if (rspCode.equals("00")) {
								mDialog.dismiss();
								 Message msg = new Message();   
							     msg.what =3; 
								 handler.sendMessage(msg);//
								
							}

							else{
								
								Message msg = new Message();   
								Bundle b = new Bundle();
								b.putString("throwsmessage",rspInfo);
								msg.setData(b);
						        msg.what =2; 
								handler.sendMessage(msg);//
								mDialog.dismiss();
							}
						}
						else
						{
							Message msg = new Message();   
							Bundle b = new Bundle();
							b.putString("throwsmessage","连接服务器超时，请重新查询！");
							msg.setData(b);
					        msg.what =2; 
							handler.sendMessage(msg);//
							mDialog.dismiss();
						}
					}	
					}.start();
						}
					
				} 
			
				break;
			}
		}
	}

	public  int aaa=0;
	public  int bbb=0;
	public  int ccc=0;
	public  int ddd=0;
	TextView yedtext51;
	// POS消息添加
	
	TextView yedtext61,yedtext71,yedtext81;
	int leng = 0;
	private void initChView() {
		ss_layoutAll = (LinearLayout) findViewById(R.id.ss_layoutAll);
		findViewById(R.id.button_add).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(ss_layoutAll.getChildCount()<5)
				{
				if(aaa==1&&bbb==1&&ccc==1&&ddd==1)
				{
					aaa=0;
					bbb=0;
					ccc=0;
					ddd=0;
					Log.i("QQQQQQQQQQQQQq", String.valueOf(leng));
					if (leng != 4) {
						final View view = LayoutInflater.from(YuShen.this).inflate(
								R.layout.cim_layout_list3, null);
						view.setLayoutParams(new LinearLayout.LayoutParams(
								LayoutParams.FILL_PARENT, 60));

						view.findViewById(R.id.yedtext51).setOnClickListener(
								new OnClickListener() {

									public void onClick(View v) {
										
										
										DateDialog.showDatePicker(YuShen.this,(TextView) v, R.id.yedtext51);
										aaa=1;
										
									}

								});
								
								
								
						view.findViewById(R.id.yedtext61).setOnClickListener(
								new OnClickListener() {

									public void onClick(View v) {

										log.etDialog(YuShen.this,(TextView) v, R.id.yedtext61,"请输入交易金额");
										bbb=1;
										
									}

								});
						
						
						view.findViewById(R.id.yedtext71).setOnClickListener(
								new OnClickListener() {

									public void onClick(View v) {

										log.etDialog(YuShen.this,(TextView) v, R.id.yedtext71,"请输入授权号");
										ccc=1;
									}

								});
						
						view.findViewById(R.id.yedtext81).setOnClickListener(
								new OnClickListener() {

									public void onClick(View v) {

										log.etDialog(YuShen.this,(TextView) v, R.id.yedtext81,"请输入卡号");
										ddd=1;
									}

								});
						
							ss_layoutAll.addView(view);
							
						leng++;
						
						view.findViewById(R.id.button91).setOnClickListener(new OnClickListener() {
							
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								ss_layoutAll.removeView(view);
								leng--;
								aaa=1;
								bbb=1;
								ccc=1;
								ddd=1;
							}
						});
						
						
					} else {
//						Toast.makeText(YuShen.this, "最多加5条", Toast.LENGTH_LONG)
//								.show();
						log.Toast(YuShen.this, "最多加5条");
					}	
				}
				else
				{
					log.Toast(YuShen.this, "请填写完整再添加下一条POS信息！");
				}
			}else {
				log.Toast(YuShen.this, "最多加5条");
			}	
	
			}
			
		});
		
	}
	
	
	// 与服务器交互
	List<String> sendtList = new ArrayList<String>();
	public static String uriAPI = "http://182.180.90.192:8080/tjbproxy/proxy.do";
	public static int code;
	StringBuffer Jsonstr1 = null;
	String rspCode;
	String rspInfo;
	String rspCode1 = "0";

	int ee=0;
	int ff=0;
	int gg=0;
	int hh=0;
	
	public void Request1() throws Exception {
		Jsonstr1 = null;
		Jsonstr1 = new StringBuffer();
		String name=textArray[0].getText().toString();
		String address=textArray[1].getText().toString();
		String contacter=textArray[2].getText().toString();
		String contactTel=textArray[3].getText().toString();
		
//		List<TCity> tcity=DBUtil.cityQuerycity(YuShen.this,"t_city"," where Name = '"+TuanDuiShangHuActivity.cityId+"'");
//		String cityid = tcity.get(0).id;
		
		Jsonstr1.append("{\"userId\":\""+TuanDuiShangHuActivity.username+"\", \"cityId\":\""+TuanDuiShangHuActivity.cityId+"\", \"activeMode\":\"S\", \"merName\":\""+name+"\", \"address\":\""+address+"\", \"contacter\":\""+contacter+"\", \"contactTel\":\""+contactTel+"\", \"detail\":["); 
		
		yedtext5=(TextView)findViewById(R.id.yedtext5);
		yedtext6=(TextView)findViewById(R.id.yedtext6);
		yedtext7=(TextView)findViewById(R.id.yedtext7);
		yedtext8=(TextView)findViewById(R.id.yedtext8);
		String[][] arrg = new String[5][4];
		arrg[0][0]=GetDate.dateChange1(yedtext5.getText().toString());
		arrg[0][1]=(yedtext6.getText().toString());
		arrg[0][2]=yedtext7.getText().toString();
		arrg[0][3]=yedtext8.getText().toString();
		
		Jsonstr1.append("{\"txnDate\":\""+arrg[0][0]+"\",\"cardId\":\""+arrg[0][3]+"\",\"auditCode\":\""+arrg[0][2]+"\",\"txnAt\":\""+arrg[0][1]+"\"}");
		//lxp
		JSONObject json1=new JSONObject();
		try
		{
			
			json1.put("txnDate", arrg[0][0]);
			json1.put("cardId", arrg[0][3]);
			json1.put("auditCode", arrg[0][2]);
			json1.put("txnAt", arrg[0][1]);
		}catch(Exception e)
		{
			
		} 
		ja.put(json1);	
		//lxp
		for(int i=1;i<ss_layoutAll.getChildCount();i++){
			JSONObject json=new JSONObject();
			LinearLayout viewLayout=(LinearLayout) ss_layoutAll.getChildAt(i);
			yedtext51=(TextView) viewLayout.findViewById(R.id.yedtext51);
			yedtext61=(TextView) viewLayout.findViewById(R.id.yedtext61);
			yedtext71=(TextView) viewLayout.findViewById(R.id.yedtext71);
			yedtext81=(TextView) viewLayout.findViewById(R.id.yedtext81);

				if(yedtext51.getText().toString()!="")
				{
					arrg[i][0] = GetDate.dateChange1(yedtext51.getText().toString());
//					aaa=1;
					if(arrg[i][0].equals(""))
					{
						log.Toast(YuShen.this,"请输入交易日期！");
						//Toast.makeText(YuShen.this, "请输入交易日期！", Toast.LENGTH_LONG).show();
					}
				}else {
					
					ee=1;
				}
				if(yedtext61.getText().toString()!="")
				{
					
					arrg[i][1] = (yedtext61.getText().toString());
//					bbb=1;
					if(arrg[i][1].equals(""))
					{
						//Toast.makeText(YuShen.this, "请输入交易金额 ！", Toast.LENGTH_LONG).show();
						log.Toast(YuShen.this, "请输入交易金额 ！");
					}
				}else {
					
					ff=1;
				}
				if(yedtext71.getText().toString()!="")
				{
					arrg[i][2] = yedtext71.getText().toString();
//					ccc=1;
					if(arrg[i][2].equals(""))
					{
						//Toast.makeText(YuShen.this, "请输入授权号 ！", Toast.LENGTH_LONG).show();
						log.Toast(YuShen.this, "请输入授权号 ！");
					}
				}else {
					gg=1;
				}
				if(yedtext81.getText().toString()!="")
				{
					arrg[i][3] = yedtext81.getText().toString();
//					ddd=1;
					if(arrg[i][3].equals(""))
					{
						//Toast.makeText(YuShen.this, "请输入卡号 ！", Toast.LENGTH_LONG).show();
						log.Toast(YuShen.this, "请输入卡号 ！");
					}
				}else {
					
					hh=1;
				}
				if(ee==0&&ff==0&&gg==0&&hh==0){
//					Jsonstr1.append(",");
//					Jsonstr1.append("{\"txnDate\":\""+arrg[i][0]+"\",\"cardId\":\""+arrg[i][3]+"\",\"auditCode\":\""+arrg[i][2]+"\",\"txnAt\":\""+arrg[i][1]+"\"}");
					
					//lxp
					try
					{
						
						json.put("txnDate", arrg[i][0]);
						json.put("cardId", arrg[i][3]);
						json.put("auditCode", arrg[i][2]);
						json.put("txnAt", arrg[i][1]);
					}catch(Exception e)
					{
						
					} 
					ja.put(json);	
					System.out.println(i);
					//lxp
				}
				
			}
//		Jsonstr1.append("]}");
		//lxp
		 String[]namestr=new String[]{"userId","cityId","activeMode","merName",
					"address","contacter","contactTel"};
		 String[]str=new String[]{"","","","","","",""};
		 str[0]=TuanDuiShangHuActivity.username;
		 str[1]=TuanDuiShangHuActivity.cityId;
		 str[2]="S";
		 str[3]=name;
		 str[4]=address;
		 str[5]=contacter;
		 str[6]=contactTel;
		 
		 for(int i=0;i<str.length;i++)
			{
				try {
					jsonstr.put(namestr[i], str[i]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		
//		 for(int i=0;i<4;i++)
//		 {
//			
//				
//		 }								
			jsonstr.put("detail", ja);
			
			String Jsonstr = jsonstr.toString();
		//lxp

	//	String Jsonstr = Jsonstr1.toString();
		System.out.println("********"+Jsonstr);	
		String jsont = HttpConnection2.request(Jsonstr, "A0006");
		
		if(jsont!=null&&!jsont.equals("")){
			JSONObject json = new JSONObject(jsont);
			rspCode = json.optString("rspCode");
			rspInfo= json.optString("rspInfo");
		}
		else
		{
			rspCode1="1";
		}
	}

	

	// 验证必填字段的是否为空
	protected boolean validate() {
		try {
			if (textArray[0].getText().toString().equals("")) {
				//Toast.makeText(YuShen.this, "请输入商户名！", Toast.LENGTH_LONG).show();
				log.Toast(YuShen.this, "请输入商户名！");
				return false;
			}
			if (textArray[1].getText().toString().equals("")) {
				//Toast.makeText(YuShen.this, "请输入地址！", Toast.LENGTH_LONG).show();
				log.Toast(YuShen.this, "请输入地址！");
				return false;
			}
			if (textArray[2].getText().toString().equals("")) {
				//Toast.makeText(YuShen.this, "请输入联系人！", Toast.LENGTH_LONG).show();
				log.Toast(YuShen.this, "请输入联系人！");
				return false;
			}
			if (textArray[3].getText().toString().equals("")) {
				//Toast.makeText(YuShen.this, "请输入电话！", Toast.LENGTH_LONG).show();
				log.Toast(YuShen.this,  "请输入电话！");
				return false;
			}
			if (textArray[4].getText().toString().equals("")) {
				
				//Toast.makeText(YuShen.this, "请输入交易日期！", Toast.LENGTH_LONG).show();
				log.Toast(YuShen.this,  "请输入交易日期！");
				return false;
			}
			if (textArray[5].getText().toString().equals("")) {
				
				//Toast.makeText(YuShen.this, "请输入交易金额 ！", Toast.LENGTH_LONG).show();
				log.Toast(YuShen.this, "请输入交易金额 ！");
				return false;
			}
			if (textArray[6].getText().toString().equals("")) {
				
				//Toast.makeText(YuShen.this, "请输入授权号 ！", Toast.LENGTH_LONG).show();
				log.Toast(YuShen.this, "请输入授权号 ！");
				return false;
			}
			if (textArray[7].getText().toString().equals("")) {
				
				//Toast.makeText(YuShen.this, "请输入卡号 ！", Toast.LENGTH_LONG).show();
				log.Toast(YuShen.this, "请输入卡号 ！");
				return false;
			}
			
			return true;  
		} catch (Exception e) {
			// TODO: handle exception
			log.Toast(YuShen.this, "输入内容错误，请确认后重新提交 ！");
			return false;  
		}
		
		
	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(YuShen.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}
