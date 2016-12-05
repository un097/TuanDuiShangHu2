package techown.shanghu;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.MyButton;
import techown.shanghu.date.DES;
import techown.shanghu.date.Encrypt;
import techown.shanghu.https.HttpConnection2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/*
 *工单反馈 信息界面
 */
public class GongDanFanKui1 extends Activity {
	public static String jsont,jsont1;
	private ListView mainright_lv;
	private LinearLayout rightLinearLayout;
	private TextView numTextView1,numTextView2,numTextView3,numTextView4,yedtext1,yedtext2,yedtext3;
	private Button button,button1;
	public String taskStatus,taskModel;
	public static String num,textaa,Id;
	public static String requeststr1;
	private String[] redBatchNos = null;
	private String[] marketBatchNos = null;
	private String[] specialBatchNos = null;
	ListAdapter1 listAdapter;
	DBUtil DB=new DBUtil();
	
	
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
	        	//Toast.makeText(GongDanFanKui1.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
	        	  log.Toast(GongDanFanKui1.this,b.getString("throwsmessage"));
	        	Intent intent = new Intent();
	        	intent.setClass(GongDanFanKui1.this, WentiGongDan.class);
	        	GongDanFanKui1.this.startActivity(intent);
	        	GongDanFanKui1.this.finish();
	        	break;
	        case 4:
	        	 b = msg.getData();
		        //Toast.makeText(GongDanFanKui1.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
	        	log.Toast(GongDanFanKui1.this,b.getString("throwsmessage"));
//		        intent.setClass(ZhongBuChaXun4.this, ZhongBuChaXun4.class);
//				ZhongBuChaXun4.this.startActivity(intent);
//				ZhongBuChaXun4.this.finish();	
	        	break;	
	        case 5:
	        	if(tempstr!=null)
	    		{
	    			numTextView1.setText(tempstr[0]);
	    		    numTextView2.setText(tempstr[1]);
	    		    numTextView3.setText(tempstr[2]);
	    		    numTextView4.setText(tempstr[3]);
	    		}
	        	mainright_lv.requestLayout();
	        	listAdapter.notifyDataSetChanged();
	        	
//	        	listAdapter.notifyDataSetInvalidated();
	        	break;
	        case 6:
	        	b = msg.getData();
	        	mainright_lv.requestLayout();
	        	listAdapter.notifyDataSetChanged();
//	        	listAdapter.notifyDataSetInvalidated();
	        	log.Toast(GongDanFanKui1.this, b.getString("throwsmessage"));
	        	break;
	        }   
	    }    
	};   
	//lxp
	//lxp

	EditDialog log=new EditDialog();

	String bb; 
	public  List<String[]> list = new ArrayList<String[]>();
//	public  List<String[]> list1 = new ArrayList<String[]>();
//	public  List<String[]> list2 = new ArrayList<String[]>();
//	public  List<String[]> list3 = new ArrayList<String[]>();
//	public  List<String[]> list4 = new ArrayList<String[]>();

    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gongdanfankui1);

        
      //  WentiGongDan.requeststr1=null;
        
        mDialog = new AlertDialog.Builder(GongDanFanKui1.this).create();//连接网络进程
        numTextView1=(TextView) findViewById(R.id.numTextView1);
        numTextView2=(TextView) findViewById(R.id.numTextView2);
        numTextView3=(TextView) findViewById(R.id.numTextView3);
        numTextView4=(TextView) findViewById(R.id.numTextView4);
        yedtext1=(TextView) findViewById(R.id.yedtext1);
        yedtext2=(TextView) findViewById(R.id.yedtext2);
        yedtext3=(TextView) findViewById(R.id.yedtext3);
      
        button = (Button) findViewById(R.id.button2);
        button1 = (Button) findViewById(R.id.button);
        
        if(redBatchNos == null){
			getredBatchNo();
		}
		if(marketBatchNos == null){
			getmarketBatchNo();
		}
		if(specialBatchNos == null){
			getspecialBatchNo();
		}
        
        final String[] province={"派发工单","完成工单","流程中工单","问题工单"};
        yedtext1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				log.dialog(GongDanFanKui1.this,yedtext1,"工单状态",province);
			}
		});
        final String[] province1={"特惠","最红","营销"};
        yedtext2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				log.dialog(GongDanFanKui1.this,yedtext2,"工单状态",province1);
			}
		});
        yedtext3.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if("".equals(yedtext2.getText().toString())){
					log.Toast(GongDanFanKui1.this, "请选择工单属性！");
				}else if("特惠".equals(yedtext2.getText().toString())){
					log.dialog(GongDanFanKui1.this, yedtext3, "类型", specialBatchNos);
				}else if("最红".equals(yedtext2.getText().toString())){
					log.dialog(GongDanFanKui1.this, yedtext3, "类型", redBatchNos);
				}else if("营销".equals(yedtext2.getText().toString())){
					log.dialog(GongDanFanKui1.this, yedtext3, "类型", marketBatchNos);
				}
			}
		});
        
    	rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
    	
    	
//    	try {
//    		Request4();
//    		if(tempstr!=null)
//    		{
//    			numTextView1.setText(tempstr[0]);
//    		    numTextView2.setText(tempstr[1]);
//    		    numTextView3.setText(tempstr[2]);
//    		    numTextView4.setText(tempstr[3]);
//    		}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			techown.shanghu.https.Log.Instance().WriteLog("GongDanFanKui1类Reques()"+e.getMessage());
//		}
		
//		else
//		{
//			Toast.makeText(GongDanFanKui1.this, "查询数据失败，请您重新查询！", 3).show();
//			Intent i=new Intent();
//			i.setClass(GongDanFanKui1.this, TuanDuiShangHuActivity.class);
//			GongDanFanKui1.this.startActivity(i);
//			GongDanFanKui1.this.finish();
//		}
		 
		
    	
    	
    	
    	
    	list.clear();
		rightLinearLayout.removeAllViews();

		LinearLayout view = (LinearLayout) LayoutInflater.from(
				GongDanFanKui1.this).inflate(R.layout.faxingxiang_list8,
				null);
		
		rightLinearLayout.addView(view);
		mainright_lv = (ListView)findViewById(R.id.mainright_lv);
//		try {
//
//
//			Request();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			techown.shanghu.https.Log.Instance().WriteLog("GongDanFanKui1类。。。。。"+e.getMessage());
//		}
		
			
		
		 listAdapter = new ListAdapter1(GongDanFanKui1.this,
				list);
		mainright_lv.setAdapter(listAdapter);

		//mainright_lv.setOnScrollListener(this);

    	
    	
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				
//				}
					try {
						
						Request1();
//						Request41();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						techown.shanghu.https.Log.Instance().WriteLog("GongDanFanKui1类Request1（）"+e.getMessage());
						mDialog.dismiss();
					}

				}
				
		});
		
		button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setClass(GongDanFanKui1.this, TuanDuiShangHuActivity.class);
				GongDanFanKui1.this.startActivity(i);
				GongDanFanKui1.this.finish();
			}
		});

	}  

	class ListAdapter1 extends BaseAdapter {
		public List<String[]> mItemList; //修饰符不能是private
		public Context mContext;
        public int count=10;
		 
		 public ListAdapter1(Context con,List<String[]> sendtList1)
		 {
			 mItemList=sendtList1;
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
	        	
	            LayoutInflater inflater = LayoutInflater.from(GongDanFanKui1.this);
	            
//	    			View view = inflater.inflate(R.layout.listtable_item4, null);
	            if(convertView==null){
	    			convertView = inflater.inflate(R.layout.listtable_item4, null);
	    			}	
//	            convertView = inflater.inflate(R.layout.listtable_item4, null);
//	            int colorPos =position % colors.length;
	            TextView listtable_tv0 = (TextView) convertView.findViewById(R.id.listtable_tv0);
	            TextView listtable_tv1 = (TextView) convertView.findViewById(R.id.listtable_tv1);
	            TextView listtable_tv2 = (TextView) convertView.findViewById(R.id.listtable_tv2);
	            TextView listtable_tv3 = (TextView) convertView.findViewById(R.id.listtable_tv3);
	            TextView listtable_tv4 = (TextView) convertView.findViewById(R.id.listtable_tv4);
	            TextView listtable_tv5 = (TextView) convertView.findViewById(R.id.listtable_tv5);
	            TextView listtable_tv6 = (TextView) convertView.findViewById(R.id.listtable_tv6);
	            TextView listtable_tv7 = (TextView) convertView.findViewById(R.id.listtable_tv7);
	            listtable_tv0.setText(mItemList.get(position)[9]);
	            listtable_tv1.setText(mItemList.get(position)[1]);
	            listtable_tv2.setText(mItemList.get(position)[2]);
	            listtable_tv3.setText(mItemList.get(position)[4]);
	            listtable_tv4.setText(mItemList.get(position)[5]);
	            listtable_tv5.setText(mItemList.get(position)[6]);
	            listtable_tv6.setText(mItemList.get(position)[7]);
	            listtable_tv7.setText(mItemList.get(position)[10]);
	            
	            listtable_tv0.setTextColor(Color.BLACK);
	 	        listtable_tv1.setTextColor(Color.BLACK);
		        listtable_tv2.setTextColor(Color.BLACK);
		        listtable_tv3.setTextColor(Color.BLACK);
		        listtable_tv4.setTextColor(Color.BLACK);
		        listtable_tv5.setTextColor(Color.BLACK);
		        
	         
		     
		        	
		        
		        MyButton listbutton=(MyButton)convertView.findViewById(R.id.shortcut_delete);
		        listbutton.setVisibility(4);
		        if(mItemList.get(position)[7].equals("问题工单")){
		        	listbutton.setVisibility(0);
		        }
		        
		        listbutton.itemposition=position;
		        
		        listbutton.setOnClickListener(
					new OnClickListener()
					{

						public void onClick(final View arg0) {
							// TODO Auto-generated method stub
							
							Query(GongDanFanKui1.this);
							DB.insertContactId3(GongDanFanKui1.this,"Id",GongDanFanKui1.num);
							
							Message msg = new Message();   
					        msg.what =1; 
							handler.sendMessage(msg);//
							 new Thread(){
								
								 
								 
								public void run(){
									 
									
									
									
									final MyButton myButton=(MyButton)arg0;
									Id = list.get(myButton.itemposition)[0];
									String taskType=list.get(myButton.itemposition)[1];
									String taskModel=list.get(myButton.itemposition)[9];
									textaa = list.get(myButton.itemposition)[8];
									final String name = list.get(myButton.itemposition)[2];
									 String[]str1=new String[]{""};	
									 str1[0]=Id;
									
									 String json1="{" +
						 				"\"taskId\":\""+str1[0]+"\"}";	
									 WentiGongDan.requeststr1=HttpConnection2.request(json1,"A0017");
									 WentiGongDan1.requeststr1=HttpConnection2.request(json1,"A0017");
									 GongDanWeiHu41.requeststr1=HttpConnection2.request(json1,"A0017");
									 mDialog.dismiss();
									 
									 if(taskModel.equals("特惠")){
										 DB.upload3(GongDanFanKui1.this, new String[]{"Dnum"}, new String[]{"1"},
									        		GongDanFanKui1.num);
										 DB.upload3(GongDanFanKui1.this, new String[]{"batchNo"}, new String[]{list.get(myButton.itemposition)[10]},
									        		GongDanFanKui1.num);
									 }else{
										 DB.upload3(GongDanFanKui1.this, new String[]{"Dnum"}, new String[]{"2"},
									        		GongDanFanKui1.num);
									 }
									 
									 if(taskType.equals("电话")){
										 
										 try{
											
										 if(GongDanWeiHu41.requeststr1!=null && GongDanWeiHu41.requeststr1.length()>0){
											 
//										 	Query(GongDanFanKui1.this);
//										 	DB.insertContactId3(GongDanFanKui1.this,"Id",GongDanFanKui1.num);
									        String id=list.get(myButton.itemposition)[0]+GongDanFanKui1.num;
									        DB.upload3(GongDanFanKui1.this, new String[]{"taskId","ActiveType"}, new String[]{Id,"1"},
									        		GongDanFanKui1.num);
									        DB.upload3(GongDanFanKui1.this, new String[]{"taskId2","Stname"}, new String[]{id,name},
									        		GongDanFanKui1.num);
									        
											Intent i=new Intent();
											i.setClass(GongDanFanKui1.this,GongDanWeiHu41.class);
											GongDanFanKui1.this.startActivity(i);
											GongDanFanKui1.this.finish();
											overridePendingTransition(R.anim.leftin, R.anim.leftout);
										 }
										 else {
											 JSONObject demoJson2 = new JSONObject(WentiGongDan.requeststr1);
											 if(demoJson2.getString("rspCode").equals(""))
												{
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson2.getString("rspInfo"));
													msg.setData(b);
											        msg.what =4; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
												else if(demoJson2.getString("rspCode").equals("800")){
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson2.getString("rspInfo"));
													msg.setData(b);
											        msg.what =4; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
												else
												{
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson2.getString("rspInfo"));
													msg.setData(b);
											        msg.what =2; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
										 		}
											}
										 catch(Exception e)
											{
									        	techown.shanghu.https.Log.Instance().WriteLog("GongDanFanKui1类if(taskType.equals(电话))"+e.getMessage());

											}	
										 
									 }
									 else if(taskType.equals("续约")){
										 
										 try
											{JSONObject demoJson2 = null;
										 if(WentiGongDan1.requeststr1!=null && WentiGongDan1.requeststr1.length()>0){
											
											 
//										 	Query(GongDanFanKui1.this);
//										 	DB.insertContactId3(GongDanFanKui1.this,"Id",GongDanFanKui1.num);
									        String id=list.get(myButton.itemposition)[0]+GongDanFanKui1.num;
									        DB.upload3(GongDanFanKui1.this, new String[]{"taskId","ActiveType"}, new String[]{Id,"0"},
									        		GongDanFanKui1.num);
									        DB.upload3(GongDanFanKui1.this, new String[]{"taskId2","Stname"}, new String[]{id,name},
									        		GongDanFanKui1.num);
									        
											Intent i=new Intent();
											i.setClass(GongDanFanKui1.this,WentiGongDan1.class);
											GongDanFanKui1.this.startActivity(i);
											GongDanFanKui1.this.finish();
											overridePendingTransition(R.anim.leftin, R.anim.leftout);
										 }
										 else {
											 demoJson2 = new JSONObject(WentiGongDan1.requeststr1);
											 if(demoJson2.getString("rspCode").equals(""))
												{
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson2.getString("rspInfo"));
													msg.setData(b);
											        msg.what =4; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
												else if(demoJson2.getString("rspCode").equals("800")){
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson2.getString("rspInfo"));
													msg.setData(b);
											        msg.what =4; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
												else
												{
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson2.getString("rspInfo"));
													msg.setData(b);
											        msg.what =2; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
										 	}
											}
										 catch(Exception e)
											{
												
											}	
									 }
								else{
										 
										 try
											{JSONObject demoJson2 = null;
										 if(WentiGongDan.requeststr1!=null && WentiGongDan.requeststr1.length()>0){
											
											 
//										 	Query(GongDanFanKui1.this);
//										 	DB.insertContactId3(GongDanFanKui1.this,"Id",GongDanFanKui1.num);
									        String id=list.get(myButton.itemposition)[0]+GongDanFanKui1.num;
									        DB.upload3(GongDanFanKui1.this, new String[]{"taskId","ActiveType"}, new String[]{Id,"0"},
									        		GongDanFanKui1.num);
									        DB.upload3(GongDanFanKui1.this, new String[]{"taskId2","Stname"}, new String[]{id,name},
									        		GongDanFanKui1.num);
									        
											Intent i=new Intent();
											i.setClass(GongDanFanKui1.this,WentiGongDan.class);
											i.putExtra("batchNo", list.get(myButton.itemposition)[10]);
											GongDanFanKui1.this.startActivity(i);
											GongDanFanKui1.this.finish();
											overridePendingTransition(R.anim.leftin, R.anim.leftout);
										 }
										 else {
											 demoJson2 = new JSONObject(WentiGongDan.requeststr1);
											 if(demoJson2.getString("rspCode").equals(""))
												{
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson2.getString("rspInfo"));
													msg.setData(b);
											        msg.what =4; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
												else if(demoJson2.getString("rspCode").equals("800")){
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson2.getString("rspInfo"));
													msg.setData(b);
											        msg.what =4; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
												else
												{
													Message msg = new Message();   
													Bundle b = new Bundle();
													b.putString("throwsmessage",demoJson2.getString("rspInfo"));
													msg.setData(b);
											        msg.what =2; 
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
										 	}
											}
										 catch(Exception e)
											{
												
											}	
									 }
								}
							 }.start();	
						
								
					
						}																								
					}									
				);
		        
	            return convertView;
	        }
			

	    }
	
	
	
	public  void Query(Context con) {
		SQLiteDatabase db = null;
		Cursor cur = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query("gongdanweihu", new String[] { "Id" }, null,
					null, null, null, null);

			// int idIndex = cur.getCount();
			// Log.i("Logcat",String.valueOf(idIndex));
			System.out.println("cur.getCount()"+cur.getCount());
			if (cur.getCount() == 0) {
				num = "td1";
				System.out.println("IDnum==="+num);
				techown.shanghu.https.Log.Instance().WriteLog("工单反馈：工单IDnum==="+num);
			} else {
				while (cur.moveToNext()) {
					if (cur.isLast()) {
						System.out.println("cur.getString(cur.getColumnIndex(Id==="+cur.getString(cur.getColumnIndex("Id")));
//						num = "td"+String.valueOf(cur.getCount() + 1);
						String[] numarrg = cur.getString(cur.getColumnIndex("Id")).split("d");
						num = "td"+String.valueOf(Long.parseLong(numarrg[1]) + 1);
						System.out.println("IDnum==="+num);
						techown.shanghu.https.Log.Instance().WriteLog("工单反馈：工单IDnum==="+num);
					}
				}
			}
			
		} catch (Exception e) {
		}finally{
			if(cur != null){
				cur.close();
			}if(db != null){
				db.close();
			}
		}
	}
	
	private void getredBatchNo(){
		MyDatabaseHelper myDatabaseHelper = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		DES des = new DES();
			try {
				Context friendContext = createPackageContext("techown.login", CONTEXT_IGNORE_SECURITY);
				myDatabaseHelper = new MyDatabaseHelper(friendContext, "techown_login.db", Constant.loginDBVer);
				db = myDatabaseHelper.getWritableDatabase();
				String sql = "select batchNo from bsc_task_batchno where trackType=?";
				cursor = db.rawQuery(sql, new String[]{des.jiaMi("REDFRIDAY")});
				redBatchNos = new String[cursor.getCount() + 1];
				redBatchNos[0] = "全部";
				int i = 1;
				while(cursor.moveToNext()){
					redBatchNos[i] = des.jieMI(cursor.getString(cursor.getColumnIndex("batchNo")));
					i++;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("查询工单批次号跑批文件异常："+e.getMessage());
				e.printStackTrace();
			}finally{
				if(cursor != null){
					cursor.close();
					cursor = null;
				}
				if(db != null){
					db.close();
					db = null;
				}
			}
		
	}
	
	private void getmarketBatchNo(){
		MyDatabaseHelper myDatabaseHelper = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		DES des = new DES();
			try {
				Context friendContext = createPackageContext("techown.login", CONTEXT_IGNORE_SECURITY);
				myDatabaseHelper = new MyDatabaseHelper(friendContext, "techown_login.db", Constant.loginDBVer);
				db = myDatabaseHelper.getWritableDatabase();
				String sql = "select batchNo from bsc_task_batchno where trackType=?";
				cursor = db.rawQuery(sql, new String[]{des.jiaMi("MARKETACTIVE")});
				marketBatchNos = new String[cursor.getCount() + 1];
				marketBatchNos[0] = "全部";
				int i = 1;
				while(cursor.moveToNext()){
					marketBatchNos[i] = des.jieMI(cursor.getString(cursor.getColumnIndex("batchNo")));
					i++;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("查询工单批次号跑批文件异常："+e.getMessage());
				e.printStackTrace();
			}finally{
				if(cursor != null){
					cursor.close();
					cursor = null;
				}
				if(db != null){
					db.close();
					db = null;
				}
			}
	}
	private void getspecialBatchNo(){
		MyDatabaseHelper myDatabaseHelper = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		DES des = new DES();
			try {
				Context friendContext = createPackageContext("techown.login", CONTEXT_IGNORE_SECURITY);
				myDatabaseHelper = new MyDatabaseHelper(friendContext, "techown_login.db", Constant.loginDBVer);
				db = myDatabaseHelper.getWritableDatabase();
				String sql = "select batchNo from bsc_task_batchno where trackType=?";
				cursor = db.rawQuery(sql, new String[]{des.jiaMi("SPECIAL")});
				specialBatchNos = new String[cursor.getCount() + 1];
				specialBatchNos[0] = "全部";
				int i = 1;
				while(cursor.moveToNext()){
					specialBatchNos[i] = des.jieMI(cursor.getString(cursor.getColumnIndex("batchNo")));
					i++;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("查询工单批次号跑批文件异常："+e.getMessage());
				e.printStackTrace();
			}finally{
				if(cursor != null){
					cursor.close();
					cursor = null;
				}
				if(db != null){
					db.close();
					db = null;
				}
			}
		
	}
	
	
	protected String Jsonstr;
	//查询所有
	public void Request(){
//		 Message msg = new Message();   
//	     msg.what =1; 
//		 handler.sendMessage(msg);//
//		 new Thread()
//			{
//				public void run()
//				{
		//userId：业代工号，必填；taskType：工单类型；taskStatus：工单状态
		try
        {
        	 JSONObject demoJson1 = new JSONObject(jsont);
     		if(demoJson1.getString("rspCode").equals("00"))
     		{
     			JSONArray numberList1 =demoJson1.getJSONArray("detail");
     			for(int j=0;j<numberList1.length();j++)
            		{
     				JSONObject datajson = (JSONObject)numberList1.opt(j);
     				String[] tempstr=new String[15];
     				tempstr[0]=datajson.getString("taskId");//工单ID
     				String lei=datajson.getString("taskType");//工单类型
     				if(lei.equals("0")){
     					tempstr[1]="上门";
     				}else if(lei.equals("1")){
     					tempstr[1]="电话";
     				}
     				else if(lei.equals("2")){
     					tempstr[1]="续约";
     				}else{
     					tempstr[1]="无";
     				}
     				tempstr[2]=datajson.getString("merName");//商户名称
     				tempstr[3]=datajson.getString("merId");//商户ID
     				tempstr[4]=datajson.getString("address");//商户地址
     				tempstr[5]=datajson.getString("beginDate");//派发日期
     				tempstr[6]=datajson.getString("endTime");//截止日期
     				String lei1=datajson.getString("taskState");//工单状态
     				if(lei1.equals("SEND")){
     					tempstr[7]="派发工单";
     				}else if(lei1.equals("FINISH")){
     					tempstr[7]="完成工单";
     				}
//     				else if(lei1.equals("REPEAL")){
//     					tempstr[7]="撤销";
//     				}
     				else if(lei1.equals("SUBMIT")){
     					tempstr[7]="流程中工单";
     				}
     				else if(lei1.equals("BACK")){
     					tempstr[7]="问题工单";
     				}
     				else{
     					tempstr[7]="";
     				}
     				tempstr[8]=datajson.getString("auditReason");//审核信息
					String lei2 = datajson.getString("taskModel");//
					if (lei2!=null&&lei2.equals("SPECIAL")) {
						tempstr[9] = "特惠";
					} else if (lei2!=null&&lei2.equals("REDFRIDAY")) {
						tempstr[9] = "最红";
					} else if (lei2!=null&&lei2.equals("MARKETACTIVE")) {
						tempstr[9] = "营销";
					} else {
						tempstr[9] = "无";
					}
     				list.add(tempstr);
            		}
//     			 mDialog.dismiss();
//     			listAdapter = new ListAdapter1(GongDanFanKui1.this,
//						list);
//				mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);
//				
//				mainright_lv.setAdapter(listAdapter);
     		}	
     		else
     		{
     			log.Toast(GongDanFanKui1.this, demoJson1.getString("rspInfo"));
     		}
        }catch(Exception e)
        {
        	techown.shanghu.https.Log.Instance().WriteLog("GongDanFanKui1类Request()"+e.getMessage());
        }
	}
//			}.start();	
//	}
	
	//查询工单状态
	protected String Jsonstr1;
	
	public void Request1() throws JSONException {
		 Message msg = new Message();   
	     msg.what =1; 
		 handler.sendMessage(msg);//
		 new Thread(){
			 public void run(){
				 list.clear();
		//userId：业代工号，必填；taskType：工单类型；taskStatus：工单状态
		if(yedtext2.getText().toString().equals("特惠")){
			taskModel= "0";
		}
		else if(yedtext2.getText().toString().equals("最红")){
			taskModel= "1";
		}else if(yedtext2.getText().toString().equals("营销")){
			taskModel= "2";
		}else{
			taskModel="";
		}
		
		
		
		if(yedtext1.getText().toString().equals("派发工单"))
		 {
			 taskStatus = "SEND";
		 }
		 else if(yedtext1.getText().toString().equals("完成工单"))
		 {
			 taskStatus = "FINISH";
		 }
		 else if(yedtext1.getText().toString().equals("流程中工单"))
		 {
			 taskStatus = "SUBMIT";
		 }
		 else if(yedtext1.getText().toString().equals("问题工单"))
		 {
			 taskStatus = "BACK";
		 }else{
			 taskStatus ="";
		 }
		
		if("全部".equals(yedtext3.getText().toString())){
			
		}
		
		String batchNo = "";
		if("全部".equals(yedtext3.getText().toString())){
			batchNo = "";
		}else{
			batchNo = yedtext3.getText().toString();
		}
		Jsonstr = "{\"userId\":\""+ TuanDuiShangHuActivity.username +"\",\"taskModel\":\""+taskModel+"\",\"taskType\":\"\",\"batchNo\":\""+batchNo+"\",\"taskStatus\":\""+taskStatus+"\"}";
//		Jsonstr = "{\"userId\":\""+ "A072862" +"\",\"taskModel\":\""+taskModel+"\",\"taskType\":\"\",\"batchNo\":\""+ "121212121111111" +"\",\"taskStatus\":\""+taskStatus+"\"}";
//		Jsonstr = "{\"userId\":\""+ "A049976" +"\",\"taskModel\":\""+taskModel+"\",\"taskType\":\"\",\"batchNo\":\""+batchNo+"\",\"taskStatus\":\""+taskStatus+"\"}";
		String jsont=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0013"), Jsonstr);
		 
		try
        {
			if(jsont!=null)
			{
				 JSONObject demoJson1 = new JSONObject(jsont);
				 if(demoJson1.getString("rspCode").equals("00"))
		     		{
		     			JSONArray numberList1 =demoJson1.getJSONArray("detail");
		     			for(int j=0;j<numberList1.length();j++)
		            		{
		     				JSONObject datajson = (JSONObject)numberList1.opt(j);
		     				String[] tempstr=new String[14];
		     				tempstr[0]=datajson.getString("taskId");//工单ID
		     				String lei=datajson.getString("taskType");//工单类型
		     				if(lei.equals("0")){
		     					tempstr[1]="上门";
		     				}else if(lei.equals("1")){
		     					tempstr[1]="电话";
		     				}
		     				else if(lei.equals("2")){
		     					tempstr[1]="续约";
		     				}else{
		     					tempstr[1]="无";
		     				}
		     				tempstr[2]=datajson.getString("merName");//商户名称
		     				tempstr[3]=datajson.getString("merId");//商户ID
		     				tempstr[4]=datajson.getString("address");//商户地址
		     				tempstr[5]=datajson.getString("beginDate");//派发日期
		     				tempstr[6]=datajson.getString("endTime");//截止日期
		     				String lei1=datajson.getString("taskState");//工单状态
		     				if(lei1.equals("SEND")){
		     					tempstr[7]="派发工单";
		     				}else if(lei1.equals("FINISH")){
		     					tempstr[7]="完成工单";
		     				}
		     				else if(lei1.equals("SUBMIT")){
		     					tempstr[7]="流程中工单";
		     				}
		     				else if(lei1.equals("BACK")){
		     					tempstr[7]="问题工单";
		     				}
		     				else{
		     					tempstr[7]="";
		     				}

		     				tempstr[8]=datajson.getString("auditReason");//审核信息
		     				String lei2 = datajson.getString("taskModel");//
							if (lei2!=null&&lei2.equals("SPECIAL")) {
								tempstr[9] = "特惠";
							} else if (lei2!=null&&lei2.equals("REDFRIDAY")) {
								tempstr[9] = "最红";
							} else if (lei2!=null&&lei2.equals("MARKETACTIVE")) {
								tempstr[9] = "营销";
							} else {
								tempstr[9] = "无";
							}
							tempstr[10] = datajson.getString("batchNo");
		     				list.add(tempstr);

		            		}
//		     			ListAdapter1 listAdapter = new ListAdapter1(GongDanFanKui1.this,
//								list);
//						mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);
//						
//						mainright_lv.setAdapter(listAdapter);
		     			
		     			
		     			 String Jsonstr1 = "{\"userId\":\""+TuanDuiShangHuActivity.username+"\",\"taskModel\":\""+taskModel+"\"}";
							String jsont1=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0015"), Jsonstr1);
							
							try
					        {
					        	 JSONObject demoJson2 = new JSONObject(jsont1);
					     		if(demoJson2.getString("rspCode").equals("00"))
					     		{
								     			
					     			demoJson2.optString("taskFinish");
					     			demoJson2.optString("taskWorking");
					     			demoJson2.optString("taskWrong");
					     			
					     			tempstr=new String[4];
					     			tempstr[0]=demoJson2.optString("taskCount");
					     			tempstr[1]=demoJson2.optString("taskFinish");
					     			tempstr[2]=demoJson2.optString("taskWorking");
					     			tempstr[3]=demoJson2.optString("taskWrong");
					     		}
					        }catch(Exception e)
					        {
					        	techown.shanghu.https.Log.Instance().WriteLog("GongDanFanKui1类Request4()"+e.getMessage());

					        }
					        mDialog.dismiss(); 
		     			
					         			
		 				listAdapter.mItemList=list;				
		 				 Message msg2 = new Message();   
					     msg2.what =5; 
						 handler.sendMessage(msg2);//
						 
						 
						
						
		     		}	
		     		else
		     		{
		     			 mDialog.dismiss(); 
		     			listAdapter.mItemList=list; 				
		 				 Message msg2 = new Message();   
					     msg2.what =6; 
					     Bundle b = new Bundle();
							b.putString("throwsmessage",demoJson1.getString("rspInfo"));
							msg2.setData(b);
						 handler.sendMessage(msg2);//
		     			
		     		}
			}
			else
			{
				 mDialog.dismiss(); 
				 Message msg2 = new Message();   
			     msg2.what =4; 
			     Bundle b = new Bundle();
					b.putString("throwsmessage","访问服务器失败");
					msg2.setData(b);
			     handler.sendMessage(msg2);//
			}
        	
        	 
     		
        }catch(Exception e)
        {
        	techown.shanghu.https.Log.Instance().WriteLog("GongDanFanKui1类Request1()"+e.getMessage());
        }
			 }
			}.start();	
	}
	
	String[] tempstr;
	//查询工单反馈
	public void Request4() throws JSONException {
		//userId：业代工号，必填；
		
		try
        {
        	 JSONObject demoJson1 = new JSONObject(jsont1);
     		if(demoJson1.getString("rspCode").equals("00"))
     		{
			     			
     			demoJson1.optString("taskFinish");
     			demoJson1.optString("taskWorking");
     			demoJson1.optString("taskWrong");
     			
     			tempstr=new String[4];
     			tempstr[0]=demoJson1.optString("taskCount");
     			tempstr[1]=demoJson1.optString("taskFinish");
     			tempstr[2]=demoJson1.optString("taskWorking");
     			tempstr[3]=demoJson1.optString("taskWrong");
     		}
        }catch(Exception e)
        {
        	techown.shanghu.https.Log.Instance().WriteLog("GongDanFanKui1类Request4()"+e.getMessage());

        }
	}
	
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(GongDanFanKui1.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
//	protected void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		list.clear();
//		listAdapter.mItemList.clear();
//		listAdapter.notifyDataSetChanged();
//	}  	 
	
}
