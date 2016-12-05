package techown.shanghu;
/*
 * 合约签订--预审信息界面
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import techown.shanghu.biaoge.MyButton;
import techown.shanghu.date.GetDate;
import techown.shanghu.https.HttpConnection2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class HeYueQianDing_0 extends Activity{
	LinearLayout rightLinearLayout;
	Button button1;
	public static String id;
	public static String  jsont;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	private Dialog mDialog;
	String tableid = "";
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
	        	log.Toast(HeYueQianDing_0.this,  b.getString("throwsmessage"));
	        }
	    }
	};
	
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heyueqianding_0);
        HeYueQianDing_2.com=null;
//        TuanDuiShangHuActivity.Query(HeYueQianDing_0.this);
//        DB.insertContactId(HeYueQianDing_0.this,"Id",TuanDuiShangHuActivity.num);
        
        try{
        mDialog = new AlertDialog.Builder(HeYueQianDing_0.this).create();
        button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setClass(HeYueQianDing_0.this, TuanDuiShangHuActivity.class);
				HeYueQianDing_0.this.startActivity(i);
				HeYueQianDing_0.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);	
			}
		});
        
        rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
        
        try {
			Request();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_0类Request()"+e.getMessage());
		}
        
        ListView mainright_lv;
        //button1=(Button)findViewById(R.id.button);
        rightLinearLayout.removeAllViews();
        rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
        
        LinearLayout view = (LinearLayout) LayoutInflater.from(
        		HeYueQianDing_0.this).inflate(R.layout.faxingxiang_list2,
				null);
        rightLinearLayout.addView(view);
		ListAdapter1 listAdapter = new ListAdapter1(HeYueQianDing_0.this,
				sendtList);
		mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);
		mainright_lv.setAdapter(listAdapter);
       
		
	}catch(Exception e){
		techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_0类："+e.getMessage());
	}
	}	
    
	int checknum;
	class ListAdapter1 extends BaseAdapter {
		 public List<String[]> mItemList; //修饰符不能是private
		public Context mContext;

		 
		 public ListAdapter1(Context con,List<String[]> sendtList)
		 {
			 mItemList=sendtList;
			 mContext=con;
		 }
	        public int getCount() {
	            return  mItemList.size();
	        }	      
	        public Object getItem(int position) {
	            return position;
	        }

	        public long getItemId(int position) {
	            return position;
	        }

	        public View getView(int position, View convertView, ViewGroup parent) {
	        	View view = convertView;
	        	if(view==null){
	        		LayoutInflater inflater = LayoutInflater.from(HeYueQianDing_0.this);
	        		view = inflater.inflate(R.layout.listtable_item3, null);
	        	}
//	            int colorPos =position % colors.length;
	            TextView listtable_tv1 = (TextView) view.findViewById(R.id.listtable_tv1);
	            TextView listtable_tv2 = (TextView) view.findViewById(R.id.listtable_tv2);
	            TextView listtable_tv3 = (TextView) view.findViewById(R.id.listtable_tv3);
	            TextView listtable_tv4 = (TextView) view.findViewById(R.id.listtable_tv4);
	            
	            listtable_tv1.setText(mItemList.get(position)[0]);
	            listtable_tv2.setText(mItemList.get(position)[2]);
	            listtable_tv3.setText(mItemList.get(position)[6]);
	            listtable_tv4.setText(mItemList.get(position)[3]);

	            MyButton listbutton=(MyButton)view.findViewById(R.id.shortcut_delete);
	            MyButton lookbutton=(MyButton)view.findViewById(R.id.shortcut_look);
		        listbutton.itemposition=position;
		        lookbutton.itemposition=position;
		        
		        listbutton.setVisibility(8);
		        lookbutton.setVisibility(8);
		        if(mItemList.get(position)[3].equals("预审成功")){
		        	listbutton.setVisibility(0);
		        }else
		        {
		        	lookbutton.setVisibility(0);
		        }
		        listbutton.setOnClickListener(
						new OnClickListener()
						{

							public void onClick(View arg0) {
								try{
								// TODO Auto-generated method stub
								Message msg = new Message();
								msg.what = 1;
								handler.sendMessage(msg);//	
									
								MyButton myButton=(MyButton)arg0;
								checknum=myButton.itemposition;
								Data1 data=new Data1();
								data.storeauditId=sendtList.get(checknum)[4];
								id=data.storeauditId;
								
								String[] tablestr = sendtList.get(checknum)[5].split("\\|");
								if(tablestr!=null&&tablestr.length>0)
								{
									tableid = tablestr[1];
								}
//								DB.upload(HeYueQianDing_0.this, new String[]{"storeauditId"}, new String[]{data.storeauditId},
//										TuanDuiShangHuActivity.num);
								HeYueQianDing_3.merName = sendtList.get(checknum)[0];
								HeYueQianDing_3.contactTel = sendtList.get(checknum)[1];
								HeYueQianDing_3.address = sendtList.get(checknum)[2];
								HeYueQianDing_3.contacter = sendtList.get(checknum)[7];
								HeYueQianDing_3.misId = sendtList.get(checknum)[8];
								HeYueQianDing_3.terId = sendtList.get(checknum)[9];
								
								new Thread()
								{
									public void run() {
										String[]str=new String[]{""};	
		 	 							str[0]=tableid;
		 	 								
		 	 							String[]namestr=new String[]{"preId"};
		 	 							JSONObject jsonstr=new JSONObject();
		 	 							for(int j=0;j<str.length;j++)
		 	 							{ 
		 	 								try {
		 	 									jsonstr.put(namestr[j], str[j]);
		 	 								} catch (JSONException e) {
		 	 									// TODO Auto-generated catch block
		 	 									e.printStackTrace();
		 	 								}	
		 	 							}
		 	 							String json=jsonstr.toString();
		 	 						 	techown.shanghu.https.Log.Instance().WriteLog("T0001特惠进件查看交易情况"+json);
		 	 						 	YuShenSucc.requeststr = HttpConnection2.RedRequestYuShen(json,"T0001");
		 	 						 	System.out.println("requeststr==="+requeststr);
										
		 	 						 	mDialog.dismiss();
										try {
											if(YuShenSucc.requeststr!=null&&YuShenSucc.requeststr.length()>0)
											{
												JSONObject demoJson1 = new JSONObject(YuShenSucc.requeststr);
												if(demoJson1.getString("rspCode").equals("00"))
				 	 						 	{
				 	 						 		Intent intent = new Intent();
					 	 						 	intent.setClass(HeYueQianDing_0.this, YuShenSucc.class);//跳转
										        	HeYueQianDing_0.this.startActivity(intent);
										        	HeYueQianDing_0.this.finish();//结束本Activity  
										        	overridePendingTransition(R.anim.leftin, R.anim.leftout);
				 	 						 	}
												else if(demoJson1.equals("")){
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson1.getString("rspInfo"));
													msg.setData(b);
											        msg.what =2; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
				 	 						 	else
				 	 						 	{
				 	 						 		Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson1.getString("rspInfo"));
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
												b.putString("throwsmessage","服务器连接超时，请稍后重新查询！");
												msg.setData(b);
										        msg.what =2; 
												handler.sendMessage(msg);//
												mDialog.dismiss();
											}
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									};
								}.start();
								
								}catch(Exception e){
									techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_0类listbutton点击："+e.getMessage());
								}
							}																								
						}									
				);
		        lookbutton.setOnClickListener(
						new OnClickListener()
						{

							public void onClick(View arg0) {
								try{
								// TODO Auto-generated method stub
								Message msg = new Message();
								msg.what = 1;
								handler.sendMessage(msg);//	
									
								MyButton myButton=(MyButton)arg0;
								checknum=myButton.itemposition;
								Data1 data=new Data1();
								data.storeauditId=sendtList.get(checknum)[4];
								id=data.storeauditId;
//								DB.upload(HeYueQianDing_0.this, new String[]{"storeauditId"}, new String[]{data.storeauditId},
//										TuanDuiShangHuActivity.num);
								
								HeYueQianDing_3.merName = sendtList.get(checknum)[0];
								HeYueQianDing_3.contactTel = sendtList.get(checknum)[1];
								HeYueQianDing_3.address = sendtList.get(checknum)[2];
								HeYueQianDing_3.contacter = sendtList.get(checknum)[7];
								HeYueQianDing_3.misId = sendtList.get(checknum)[8];
								HeYueQianDing_3.terId = sendtList.get(checknum)[9];
								
								String[] tablestr = sendtList.get(checknum)[5].split("\\|");
								if(tablestr!=null&&tablestr.length>0)
								{
									tableid = tablestr[1];
								}
								
								new Thread()
								{
									public void run() {
										String[]str=new String[]{""};	
		 	 							str[0]=tableid;
		 	 								
		 	 							String[]namestr=new String[]{"preId"};
		 	 							JSONObject jsonstr=new JSONObject();
		 	 							for(int j=0;j<str.length;j++)
		 	 							{ 
		 	 								try {
		 	 									jsonstr.put(namestr[j], str[j]);
		 	 								} catch (JSONException e) {
		 	 									// TODO Auto-generated catch block
		 	 									e.printStackTrace();
		 	 								}	
		 	 							}
		 	 							String json=jsonstr.toString();
		 	 						 	techown.shanghu.https.Log.Instance().WriteLog("T0001特惠进件查看交易情况"+json);
		 	 						 	YuShenFail.requeststr = HttpConnection2.RedRequestYuShen(json,"T0001");
		 	 						 	System.out.println("requeststr==="+requeststr);
										
		 	 						 	mDialog.dismiss();
		 	 						 	mDialog.dismiss();
										try {
											if(YuShenFail.requeststr!=null&&YuShenFail.requeststr.length()>0)
											{
												JSONObject demoJson1 = new JSONObject(YuShenFail.requeststr);
												if(demoJson1.getString("rspCode").equals("00"))
				 	 						 	{
				 	 						 		Intent intent = new Intent();
				 	 						 		intent.setClass(HeYueQianDing_0.this, YuShenFail.class);//跳转
										        	HeYueQianDing_0.this.startActivity(intent);
										        	HeYueQianDing_0.this.finish();//结束本Activity  
										        	overridePendingTransition(R.anim.leftin, R.anim.leftout);
				 	 						 	}
												else if(demoJson1.equals("")){
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson1.getString("rspInfo"));
													msg.setData(b);
											        msg.what =2; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
				 	 						 	else
				 	 						 	{
				 	 						 		Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson1.getString("rspInfo"));
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
												b.putString("throwsmessage","服务器连接超时，请稍后重新查询！");
												msg.setData(b);
										        msg.what =2; 
												handler.sendMessage(msg);//
												mDialog.dismiss();
											}
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									};
								}.start();
								
								}catch(Exception e){
									techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_0类listbutton点击："+e.getMessage());
								}
							}																								
						}									
				);
		        
		        

	 	        listtable_tv1.setTextColor(Color.BLACK);
	 	        listtable_tv2.setTextColor(Color.BLACK);
	 	        listtable_tv3.setTextColor(Color.BLACK);
	 	        listtable_tv4.setTextColor(Color.BLACK);
	 	    

	            return view;
	        }
			

	    }

	String time=GetDate.getDate1().toString(); 
	
	List<String[]> sendtList = new ArrayList<String[]>();
	protected String Jsonstr;
	public static String requeststr;
	public void Request() throws JSONException {
		
//		List<TCity> tcity=DBUtil.cityQuerycity(HeYueQianDing_0.this,"t_city"," where Name = '"+cityName+"'");
//		String cityid = tcity.get(0).id;
		//Jsonstr = "{\"userId\":\""+TuanDuiShangHuActivity.username+"\",\"chkDate\":\""+time+"\", \"city\":\""+TuanDuiShangHuActivity.cityId+"\",  \"chkStatus\":\"\", \"isDel\":\"\"}";
		
		// jsont=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0007"), Jsonstr);
		//HeYueQianDing_3.jsont=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0007"), Jsonstr);
		
		
		//lxp
		 
		try
		{
			JSONObject demoJson1 = new JSONObject(jsont);
			if(jsont != null){
				
			if(demoJson1.getString("rspCode").equals("00"))
			{
				JSONArray numberList1 =demoJson1.getJSONArray("detail");
				for(int j=0;j<numberList1.length();j++)
	       		{
					JSONObject datajson = (JSONObject)numberList1.opt(j);
					String[] tempstr=new String[10];
					tempstr[0]=datajson.getString("merName");//商户名称
					tempstr[1]=datajson.getString("contactTel");//商户联系方式
					tempstr[2]=datajson.getString("address");//商户地址
					String lie=datajson.getString("chkStatus");//预审状态
					if(lie.equals("0")){
						tempstr[3]="预审中";
					}else if(lie.equals("1")){
						tempstr[3]="预审失败";
					}else if(lie.equals("2")){
						tempstr[3]="预审成功";
					}else{
						tempstr[3]="无";
					}
					//0预审中；1预审失败；2预审成功
					tempstr[4]=datajson.getString("chkId");//预审ID
					tempstr[5]=datajson.getString("isDel");//是否删除
					tempstr[6]=datajson.getString("createDate");//预审时间
					tempstr[7]=datajson.getString("contacter");//联系人
					tempstr[8]=datajson.getString("misId");//商户mis号
					tempstr[9]=datajson.getString("terId");//终端号
					sendtList.add(tempstr);
					mDialog.dismiss();	
	       		}
			}
			else 
			{
				//Toast.makeText(HeYueQianDing_0.this,demoJson1.getString("rspInfo"),Toast.LENGTH_LONG).show();
				log.Toast(HeYueQianDing_0.this, demoJson1.getString("rspInfo"));
			}
		}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队：HeYueQianDing_0出错"+e.getMessage());
		}
	}
	
	
    //add by chengyuan -e
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(HeYueQianDing_0.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
