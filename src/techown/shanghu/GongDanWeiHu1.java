package techown.shanghu;
/*
 * 工单维护--工单显示界面
 */
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

public class GongDanWeiHu1 extends Activity implements OnScrollListener{
	public static String jsont;
	ListView mainright_lv, lv;
	LinearLayout rightLinearLayout;
	public static String num,merId;
	private Button button, button1;
	public static String Id;
	public static String name;
	public static String merName;//
	public String taskType,taskModel;
	public static String requeststr1;
	public List<String[]> list22;
	public ListAdapter12 lis;
	public int lastItem = 0;
	public Thread mThread;
	public int maxCount;
	String bb; 
	ListAdapter1 listAdapter;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	TextView yedtext1,yedtext2,yedtext3;
	private String[] redBatchNos = null;
	private String[] marketBatchNos = null;
	private String[] specialBatchNos = null;
	public List<String[]> list = new ArrayList<String[]>();
	public List<String[]> list1 = new ArrayList<String[]>();
	public List<String[]> list2 = new ArrayList<String[]>();
	public List<String[]> list3 = new ArrayList<String[]>();

	LinearLayout view;
	 EditDialog ds=new EditDialog();
	 private Dialog mDialog;
	 Handler handler1 = new Handler(){   //
	       
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
		        	 
		        	 
		        	 ds.Toast(GongDanWeiHu1.this,b.getString("throwsmessage"));
			        break;
		        case 5:
		        	mainright_lv.requestLayout();
		        	listAdapter.notifyDataSetChanged();
		        	break;
		        case 6:
		        	 b = msg.getData();
		        	 mainright_lv.requestLayout();
		        	listAdapter.notifyDataSetChanged();
		        	log.Toast(GongDanWeiHu1.this, b.getString("throwsmessage"));
		        	break;
		        	
		        }
		    }
		};
	
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gongdanweihu1);
		mDialog = new AlertDialog.Builder(GongDanWeiHu1.this).create();//连接网络进程
		rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
		rightLinearLayout.removeAllViews();

		LinearLayout view = (LinearLayout) LayoutInflater.from(
				GongDanWeiHu1.this).inflate(R.layout.faxingxiang_list3,
				null);
		
		rightLinearLayout.addView(view);
		 mainright_lv = (ListView) findViewById(R.id.mainright_lv);
		 listAdapter = new ListAdapter1(GongDanWeiHu1.this,
		list);
		 mainright_lv.setAdapter(listAdapter);

		if(redBatchNos == null){
			getredBatchNo();
		}
		if(marketBatchNos == null){
			getmarketBatchNo();
		}
		if(specialBatchNos == null){
			getspecialBatchNo();
		}
		
		final String[] province = { "手工","上门", "电话", "续约" ,"全部"};
		final String[] province1 = { "特惠","最红","营销"};
//		final String[] 
		button = (Button) findViewById(R.id.button2);
		button1 = (Button) findViewById(R.id.button);
		yedtext1 = (TextView) findViewById(R.id.yedtext1);
		yedtext2 = (TextView) findViewById(R.id.yedtext2);
		yedtext3 = (TextView) findViewById(R.id.yedtext3);
		
		yedtext1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.dialog(GongDanWeiHu1.this, yedtext1, "类型", province);
			}
		});
		yedtext2.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.dialog(GongDanWeiHu1.this, yedtext2, "类型", province1);
			}
		});
		yedtext3.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if("".equals(yedtext2.getText().toString())){
					log.Toast(GongDanWeiHu1.this, "请选择工单属性！");
				}else if("特惠".equals(yedtext2.getText().toString())){
					log.dialog(GongDanWeiHu1.this, yedtext3, "类型", specialBatchNos);
				}else if("最红".equals(yedtext2.getText().toString())){
					log.dialog(GongDanWeiHu1.this, yedtext3, "类型", redBatchNos);
				}else if("营销".equals(yedtext2.getText().toString())){
					log.dialog(GongDanWeiHu1.this, yedtext3, "类型", marketBatchNos);
				}
			}
		});
		act();
	}

	private void act() {
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				list.clear();

					try {
						Request1();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu1类Request1()"+e.getMessage());
						mDialog.dismiss();
					}

			}
		});

		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClass(GongDanWeiHu1.this, TuanDuiShangHuActivity.class);
				GongDanWeiHu1.this.startActivity(i);
				GongDanWeiHu1.this.finish();
//				overridePendingTransition(R.anim.rightin, R.anim.rightout);
			}
		});

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
	

	class ListAdapter1 extends BaseAdapter {
		public List<String[]> mItemList; // 修饰符不能是private
		public Context mContext; 
		public int count=10;
		
		public ListAdapter1(Context con, List<String[]> sendtList1) {
			mItemList = sendtList1;
			mContext = con;
		}

		public int getCount() {
			return mItemList.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}


		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(GongDanWeiHu1.this);
			if(convertView==null){
				convertView = inflater.inflate(R.layout.listtable_item44, null);
			}
			// int colorPos =position % colors.length;
			TextView listtable_tv0 = (TextView) convertView
					.findViewById(R.id.listtable_tv0);
			TextView listtable_tv1 = (TextView) convertView
					.findViewById(R.id.listtable_tv1);
			TextView listtable_tv2 = (TextView) convertView
					.findViewById(R.id.listtable_tv2);
			TextView listtable_tv3 = (TextView) convertView
					.findViewById(R.id.listtable_tv3);
			TextView listtable_tv4 = (TextView) convertView
					.findViewById(R.id.listtable_tv4);
			TextView listtable_tv5 = (TextView) convertView
					.findViewById(R.id.listtable_tv5);
			TextView listtable_tv6 = (TextView) convertView
					.findViewById(R.id.listtable_tv6);
			
			listtable_tv0.setText(mItemList.get(position)[9]);
			listtable_tv1.setText(mItemList.get(position)[1]);
			listtable_tv2.setText(mItemList.get(position)[2]);
			listtable_tv3.setText(mItemList.get(position)[4]);
			listtable_tv4.setText(mItemList.get(position)[5]);
			listtable_tv5.setText(mItemList.get(position)[6]);
			listtable_tv6.setText(mItemList.get(position)[10]);
			if(mItemList.get(position)[1].equals("上门") && mItemList.get(position)[10] != null && !"".equals(mItemList.get(position)[10])){
				listtable_tv1.setText("手工");
			}
		

			listtable_tv0.setTextColor(Color.BLACK);
			listtable_tv1.setTextColor(Color.BLACK);
			listtable_tv2.setTextColor(Color.BLACK);
			listtable_tv3.setTextColor(Color.BLACK);
			listtable_tv4.setTextColor(Color.BLACK);
			listtable_tv5.setTextColor(Color.BLACK);
			listtable_tv6.setTextColor(Color.BLACK);

			MyButton listbutton = (MyButton) convertView
					.findViewById(R.id.shortcut_delete);

			listbutton.itemposition = position;

			listbutton.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					MyButton myButton = (MyButton) arg0;

					Id = list.get(myButton.itemposition)[0];
					name = list.get(myButton.itemposition)[3];

					merName = list.get(myButton.itemposition)[2];
					
					if(mItemList.get(myButton.itemposition)[9].equals("特惠")){
						if (mItemList.get(myButton.itemposition)[1].equals("上门")) {
							Query(GongDanWeiHu1.this);
							DB.insertContactId3(GongDanWeiHu1.this, "Id", num);
							DB.upload3(GongDanWeiHu1.this, new String[]{"batchNo"}, new String[]{mItemList.get(myButton.itemposition)[10]},num);
							String id = list.get(myButton.itemposition)[0] + num;
							DB.upload3(GongDanWeiHu1.this, new String[] {
									"taskId", "ActiveType", "StName" },
									new String[] { Id, "0", merName }, num);
							DB.upload3(GongDanWeiHu1.this,
									new String[] { "taskId2" ,"Dnum"},
									new String[] { id ,"1"}, num);
	
							techown.shanghu.https.Log.Instance().WriteLog("选择特惠上门工单,工单ID："+Id+"商户名称："+merName+",GongDanWeiHu1.num"+num);
							System.out.println("特惠上门工单,工单ID："+Id+"商户名称："+merName);
							Intent i = new Intent();
							i.setClass(GongDanWeiHu1.this, GongDanWeiHu3.class);
							i.putExtra("batchNo", mItemList.get(myButton.itemposition)[10]);
							GongDanWeiHu1.this.startActivity(i);
							GongDanWeiHu1.this.finish();
							overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
	
						if (mItemList.get(myButton.itemposition)[1].equals("电话")) {
							Query(GongDanWeiHu1.this);
							DB.insertContactId3(GongDanWeiHu1.this, "Id", num);
							String id = list.get(myButton.itemposition)[0] + num;
							DB.upload3(GongDanWeiHu1.this, new String[] {
									"taskId", "ActiveType", "StName" },
									new String[] { Id, "1", merName }, num);
							DB.upload3(GongDanWeiHu1.this,
									new String[] { "taskId2" },
									new String[] { id }, num);
							techown.shanghu.https.Log.Instance().WriteLog("选择特惠电话工单,工单ID："+Id+"商户名称："+merName);
							Intent i = new Intent();
							i.setClass(GongDanWeiHu1.this, GongDanWeiHu4.class);
							GongDanWeiHu1.this.startActivity(i);
							GongDanWeiHu1.this.finish();
							overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
	
						if (mItemList.get(myButton.itemposition)[1].equals("续约")) {
								Query(GongDanWeiHu1.this);
								DB.insertContactId3(GongDanWeiHu1.this,"Id",num);
						        String id=name;
						        merId=list.get(myButton.itemposition)[3];
						        DB.upload3(GongDanWeiHu1.this, new String[]{"taskId","ActiveType","StName"}, new String[]{Id,"2",merName},
										num);
						        DB.upload3(GongDanWeiHu1.this, new String[]{"taskId2","Dnum"}, new String[]{name,"1"},
										num);
						        techown.shanghu.https.Log.Instance().WriteLog("选择特惠续约工单,工单ID："+Id+"商户名称："+merName);
								Intent i=new Intent();
								i.setClass(GongDanWeiHu1.this,YouHuiShangXian_TeHui211.class);
								GongDanWeiHu1.this.startActivity(i);
								GongDanWeiHu1.this.finish();
								overridePendingTransition(R.anim.leftin, R.anim.leftout);
//							}
						}
					}else if(mItemList.get(myButton.itemposition)[9].equals("最红"))
					{
						if (mItemList.get(myButton.itemposition)[1].equals("上门")) {
							Query(GongDanWeiHu1.this);
							DB.insertContactId3(GongDanWeiHu1.this, "Id", num);
							DB.upload3(GongDanWeiHu1.this, new String[]{"batchNo"}, new String[]{mItemList.get(myButton.itemposition)[10]},num);
							String id = list.get(myButton.itemposition)[0] + num;
							DB.upload3(GongDanWeiHu1.this, new String[] {
									"taskId", "ActiveType", "StName" ,"Dnum"},
									new String[] { Id, "0", merName ,"3"}, num);
							DB.upload3(GongDanWeiHu1.this,
									new String[] { "taskId2" },
									new String[] { id }, num);
	
							techown.shanghu.https.Log.Instance().WriteLog("选择最红上门工单,工单ID："+Id+"商户名称："+merName);
							System.out.println("最红上门工单,工单ID："+Id+"商户名称："+merName);
							Intent i = new Intent();
							i.setClass(GongDanWeiHu1.this, GongDanWeiHu3.class);
							i.putExtra("batchNo", mItemList.get(myButton.itemposition)[10]);
							GongDanWeiHu1.this.startActivity(i);
							GongDanWeiHu1.this.finish();
							overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
	
						if (mItemList.get(myButton.itemposition)[1].equals("电话")) {
							Query(GongDanWeiHu1.this);
							DB.insertContactId3(GongDanWeiHu1.this, "Id", num);
							String id = list.get(myButton.itemposition)[0] + num;
							DB.upload3(GongDanWeiHu1.this, new String[] {
									"taskId", "ActiveType", "StName" },
									new String[] { Id, "1", merName }, num);
							DB.upload3(GongDanWeiHu1.this,
									new String[] { "taskId2" },
									new String[] { id }, num);
							techown.shanghu.https.Log.Instance().WriteLog("选择电话工单,工单ID："+Id+"商户名称："+merName);
							Intent i = new Intent();
							i.setClass(GongDanWeiHu1.this, GongDanWeiHu4.class);
							GongDanWeiHu1.this.startActivity(i);
							GongDanWeiHu1.this.finish();
							overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
	
						if (mItemList.get(myButton.itemposition)[1].equals("续约")) {
								Query(GongDanWeiHu1.this);
								DB.insertContactId3(GongDanWeiHu1.this,"Id",num);
						        String id=name;
						        merId=list.get(myButton.itemposition)[3];
						        DB.upload3(GongDanWeiHu1.this, new String[]{"taskId","ActiveType","StName","Dnum"}, 
						        		new String[]{Id,"2",merName,"3"},
										num);
						        DB.upload3(GongDanWeiHu1.this, new String[]{"taskId2"}, new String[]{name},
										num);
						        techown.shanghu.https.Log.Instance().WriteLog("选择续约工单,工单ID："+Id+"商户名称："+merName);
								Intent i=new Intent();
								i.setClass(GongDanWeiHu1.this,YouHuiShangXian_TeHui211.class);
								GongDanWeiHu1.this.startActivity(i);
								GongDanWeiHu1.this.finish();
								overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
					}
					else
					{
						if (mItemList.get(myButton.itemposition)[1].equals("上门")) {
							Query(GongDanWeiHu1.this);
							DB.insertContactId3(GongDanWeiHu1.this, "Id", num);
							DB.upload3(GongDanWeiHu1.this, new String[]{"batchNo"}, new String[]{mItemList.get(myButton.itemposition)[10]},num);
							String id = list.get(myButton.itemposition)[0] + num;
							DB.upload3(GongDanWeiHu1.this, new String[] {
									"taskId", "ActiveType", "StName" ,"Dnum"},
									new String[] { Id, "0", merName ,"2"}, num);
							DB.upload3(GongDanWeiHu1.this,
									new String[] { "taskId2" },
									new String[] { id }, num);
	
							techown.shanghu.https.Log.Instance().WriteLog("选择营销上门工单,工单ID："+Id+"商户名称："+merName);
							Intent i = new Intent();
							i.setClass(GongDanWeiHu1.this, GongDanWeiHu3.class);
							i.putExtra("batchNo", mItemList.get(myButton.itemposition)[10]);
							GongDanWeiHu1.this.startActivity(i);
							GongDanWeiHu1.this.finish();
							overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
	
						if (mItemList.get(myButton.itemposition)[1].equals("电话")) {
							Query(GongDanWeiHu1.this);
							DB.insertContactId3(GongDanWeiHu1.this, "Id", num);
							String id = list.get(myButton.itemposition)[0] + num;
							DB.upload3(GongDanWeiHu1.this, new String[] {
									"taskId", "ActiveType", "StName" },
									new String[] { Id, "1", merName }, num);
							DB.upload3(GongDanWeiHu1.this,
									new String[] { "taskId2" },
									new String[] { id }, num);
							techown.shanghu.https.Log.Instance().WriteLog("选择电话工单,工单ID："+Id+"商户名称："+merName);
							Intent i = new Intent();
							i.setClass(GongDanWeiHu1.this, GongDanWeiHu4.class);
							GongDanWeiHu1.this.startActivity(i);
							GongDanWeiHu1.this.finish();
							overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
	
						if (mItemList.get(myButton.itemposition)[1].equals("续约")) {
								Query(GongDanWeiHu1.this);
								DB.insertContactId3(GongDanWeiHu1.this,"Id",num);
						        String id=name;
						        merId=list.get(myButton.itemposition)[3];
						        DB.upload3(GongDanWeiHu1.this, new String[]{"taskId","ActiveType","StName","Dnum"}, 
						        		new String[]{Id,"2",merName,"2"},
										num);
						        DB.upload3(GongDanWeiHu1.this, new String[]{"taskId2"}, new String[]{name},
										num);
						        techown.shanghu.https.Log.Instance().WriteLog("选择续约工单,工单ID："+Id+"商户名称："+merName);
								Intent i=new Intent();
								i.setClass(GongDanWeiHu1.this,YouHuiShangXian_TeHui211.class);
								GongDanWeiHu1.this.startActivity(i);
								GongDanWeiHu1.this.finish();
								overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
					}

				}
			});
			
			return convertView;
		}
	}

	

	protected String Jsonstr;

	// 查询所有
	public void Request() throws JSONException {
		try {
			String rspCode = null;
			JSONObject demoJson1 = new JSONObject(jsont);
			if(demoJson1.opt("rspCode")!=null){
				rspCode = demoJson1.getString("rspCode");
			}
			if (rspCode!=null&&rspCode.equals("00")) {
				JSONArray numberList1 = demoJson1.getJSONArray("detail");
				// Log.i("output",String.valueOf(demoJson1.length()) );
				list.clear();
				for (int j = 0; j < numberList1.length(); j++) {
					JSONObject datajson = (JSONObject) numberList1.opt(j);
					String[] tempstr = new String[10];
					tempstr[0] = datajson.getString("taskId");// 工单ID
					String lei = datajson.getString("taskType");// 工单类型
					if (lei!=null&&lei.equals("0")) {
						tempstr[1] = "上门";
					} else if (lei!=null&&lei.equals("1")) {
						tempstr[1] = "电话";
					} else if (lei!=null&&lei.equals("2")) {
						tempstr[1] = "续约";
					} else {
						tempstr[1] = "无";
					}
					tempstr[2] = datajson.getString("merName");// 商户名称
					tempstr[3] = datajson.getString("merId");// 商户ID
					tempstr[4] = datajson.getString("address");// 商户地址
					tempstr[5] = datajson.getString("beginDate");// 派发日期
					tempstr[6] = datajson.getString("endTime");// 截止日期
					tempstr[7] = datajson.getString("taskState");// 工单状态
					tempstr[8] = datajson.getString("auditReason");// 审核信息
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
				 mDialog.dismiss();
			} else {
				log.Toast(GongDanWeiHu1.this,
						demoJson1.getString("rspInfo"));
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu1类Request()"+e.getMessage());
			 log.Toast(GongDanWeiHu1.this,"暂无数据");
		}
	}

	protected String Jsonstr1;
	Boolean SGBoo = false ;// 是否是手工

	// 查询所有
	public void Request1() throws JSONException {
		
		 Message msg = new Message();   
	     msg.what =1; 
		 handler1.sendMessage(msg);//
		 new Thread(){
			 public void run(){
				 list.clear();
		// userId：业代工号，必填；taskType：工单类型；taskStatus：工单状态
		if(yedtext2.getText().toString().equals("特惠")){
			taskModel = "0";
		}else if(yedtext2.getText().toString().equals("最红")){
			taskModel = "1";
		}else if(yedtext2.getText().toString().equals("营销")){
			taskModel = "2";
		}
		if (yedtext1.getText().toString().equals("上门") || yedtext1.getText().toString().equals("手工")) {
			taskType = "0";
			if (yedtext1.getText().toString().equals("手工")) {
				SGBoo = true ;
			}else{
				SGBoo = false ;
			}
		} else if (yedtext1.getText().toString().equals("电话")) {
			taskType = "1";
		} else if (yedtext1.getText().toString().equals("续约")) {
			taskType = "2";
		}else {
			taskType = "";
		}
		
		String batchNo = "";
		if("全部".equals(yedtext3.getText().toString())){
			batchNo = "";
		}else{
			batchNo = yedtext3.getText().toString();
		}
		Jsonstr = "{\"userId\":\""+ TuanDuiShangHuActivity.username +"\",\"taskModel\":\"" + taskModel + "\",\"taskType\":\"" + taskType + "\",\"batchNo\":\"" + batchNo + "\",\"taskStatus\":\"\"}";	
//		Jsonstr = "{\"userId\":\""+ "A072862" +"\",\"taskModel\":\"" + "0" + "\",\"taskType\":\"" + taskType + "\",\"batchNo\":\"" + "肯德基特惠工单" + "\",\"taskStatus\":\"\"}";
//		Jsonstr = "{\"userId\":\""+ "A049976" +"\",\"taskModel\":\"" + taskModel + "\",\"taskType\":\"" + taskType + "\",\"batchNo\":\"" + batchNo + "\",\"taskStatus\":\"\"}";
//		Jsonstr = "{\"userId\":\""+ "A049976" +"\",\"taskModel\":\"0\",\"taskType\":\"0\",\"batchNo\":\"\",\"taskStatus\":\"\"}";
		String jsont = HttpConnection2.request(Jsonstr, "A0013");
		 mDialog.dismiss();
		try {
			if(jsont!=null)
			{
				String rspCode = null;
				JSONObject demoJson1 = new JSONObject(jsont);
				if(demoJson1.opt("rspCode") != null){
					rspCode = demoJson1.getString("rspCode");
				}
				if (rspCode!=null && rspCode.equals("00")) {
					JSONArray numberList1 = demoJson1.getJSONArray("detail");
					for (int j = 0; j < numberList1.length(); j++) {
						JSONObject datajson = (JSONObject) numberList1.opt(j);
						String[] tempstr = new String[11];
						tempstr[0] = datajson.getString("taskId");// 工单ID
						String lei = datajson.getString("taskType");// 工单类型
						if (lei!=null&&lei.equals("0")) {
							tempstr[1] = "上门";
						} else if (lei!=null&&lei.equals("1")) {
							tempstr[1] = "电话";
						} else if (lei!=null&&lei.equals("2")) {
							tempstr[1] = "续约";
						} else {
							tempstr[1] = "无";
						}
						tempstr[2] = datajson.getString("merName");// 商户名称
						tempstr[3] = datajson.getString("merId");// 商户ID
						tempstr[4] = datajson.getString("address");// 商户地址
						tempstr[5] = datajson.getString("beginDate");// 派发日期
						tempstr[6] = datajson.getString("endTime");// 截止日期
						tempstr[7] = datajson.getString("taskState");// 工单状态
						
//						if(!tempstr[7].equals("SUBMIT"))
//						{
//							tempstr[8] = datajson.getString("auditReason");// 审核信息
//							list.add(tempstr);
//						}
						
						tempstr[8] = datajson.getString("auditReason");// 审核信息
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
						if (SGBoo ) {
							if (tempstr[10] != null && !"".equals(tempstr[10])) {
								list.add(tempstr);
							}
						}else{
							list.add(tempstr);
						}
					}
					listAdapter.mItemList=list;				
					 Message msg2 = new Message();   
				     msg2.what =5; 
					 handler1.sendMessage(msg2);//
				} else {
					listAdapter.mItemList=list; 				
					 Message msg2 = new Message();   
				     msg2.what =6; 
				     Bundle b = new Bundle();
				     b.putString("throwsmessage",demoJson1.getString("rspInfo"));
				     msg2.setData(b);
					 handler1.sendMessage(msg2);//
					
				}
			}
			else
			{
				Message msg2 = new Message();   
			     msg2.what =2; 
			     Bundle b = new Bundle();
			     b.putString("throwsmessage","访问服务器失败");
			     msg2.setData(b);
				 handler1.sendMessage(msg2);//
			}
			
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu1类Request1()"+e.getMessage());
			// Toast.makeText(GongDanWeiHu1.this, "暂无数据", Toast.LENGTH_SHORT).show();
		}
			 }
			}.start();
		// sendtList1.add(name);
	}

	// 查询工单类型

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
				techown.shanghu.https.Log.Instance().WriteLog("工单维护：工单IDnum==="+num);
			} else {
				while (cur.moveToNext()) {
					if (cur.isLast()) {
						System.out.println("cur.getString(cur.getColumnIndex(Id==="+cur.getString(cur.getColumnIndex("Id")));
//						num = "td"+String.valueOf(cur.getCount() + 1);
						String[] numarrg = cur.getString(cur.getColumnIndex("Id")).split("d");
						num = "td"+String.valueOf(Long.parseLong(numarrg[1]) + 1);
						System.out.println("IDnum==="+num);
						techown.shanghu.https.Log.Instance().WriteLog("工单维护：工单IDnum==="+num);
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

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(GongDanWeiHu1.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	class ListAdapter12 extends BaseAdapter {
		public List<String[]> mItemList; // 修饰符不能是private
		public Context mContext;
		public int count = 10;
		
		public ListAdapter12(Context con, List<String[]> sendtList1) {
			mItemList = sendtList1;
			mContext = con;
		}

		public int getCount() {
			return mItemList.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(GongDanWeiHu1.this);
			if (convertView == null) {
				

			convertView = inflater.inflate(R.layout.listtable_item4, null);
			}
			// int colorPos =position % colors.length;
			TextView listtable_tv1 = (TextView) convertView
					.findViewById(R.id.listtable_tv1);
			TextView listtable_tv2 = (TextView) convertView
					.findViewById(R.id.listtable_tv2);
			TextView listtable_tv3 = (TextView) convertView
					.findViewById(R.id.listtable_tv3);
			TextView listtable_tv4 = (TextView) convertView
					.findViewById(R.id.listtable_tv4);
			TextView listtable_tv5 = (TextView) convertView
					.findViewById(R.id.listtable_tv5);
			
			listtable_tv1.setText(mItemList.get(position)[0]);
			listtable_tv2.setText(mItemList.get(position)[1]);
			listtable_tv3.setText(mItemList.get(position)[2]);
			listtable_tv4.setText(mItemList.get(position)[3]);
			listtable_tv5.setText(mItemList.get(position)[4]);
		
			return convertView;


	}

}
	
	private void getActiveType(){
		MyDatabaseHelper myDatabaseHelper = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		Encrypt des = Encrypt.getInstance(GongDanWeiHu1.this);
			try {
				
					Context friendContext = createPackageContext("techown.login", CONTEXT_IGNORE_SECURITY);
					myDatabaseHelper = new MyDatabaseHelper(friendContext, "techown_login.db", Constant.loginDBVer);
					db = myDatabaseHelper.getWritableDatabase();
					String sql = "select * from bsc_task_batchno";
					cursor = db.rawQuery(sql, null);
					while(cursor.moveToNext()){
						
				}
			} catch (NameNotFoundException e) {
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

	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		if(arg1 + arg2 == arg3){  
          
            if(mThread == null || ! mThread.isAlive()){  
                mThread = new Thread(){  
                    @Override  
                    public void run() {  
                        try {  
//                            
//                            if(maxCount==0){  
//                                 Request();
//                        		maxCount=list.size();
//                        		}  
                            }  
                              
                         catch (Exception e) {  
                        	 techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu1类onScroll()"+e.getMessage());
                        }  
                          
                        Message message = new Message();  
                        message.what = 1;  
                        handler.sendMessage(message);  
                    }  
                };  
                mThread.run();  
            }else {  
                  
                mThread.destroy();  
            }  
        }  
		
	}

	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	 private Handler handler = new Handler(){  
	        @Override  
	        public void handleMessage(Message msg) {  
	            switch (msg.what) {  
	            case 1:  
	               
	                if(listAdapter.count < maxCount){  
	                      
	                    if((maxCount - listAdapter.count) >10){  
	                    	listAdapter.count+=10;  
	                    }else {  
	                    	listAdapter.count+= maxCount -listAdapter.count;  
	                    }  
	                      
	                }else {  
	                      
	                    mThread.stop();  
	                }  
	                //重新刷新ListView的adapter里面数据  
	                listAdapter.notifyDataSetChanged();  
	                break;    
	            default:  
	                break;  
	            }  
	        }  
	    };    
}