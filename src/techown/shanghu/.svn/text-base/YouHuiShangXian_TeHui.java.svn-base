package techown.shanghu;
/*
 * 优惠上线--上线列表
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.MyButton;
import techown.shanghu.date.DES;

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
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
/*
 * 优惠上线-特惠
 */
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class YouHuiShangXian_TeHui extends Activity {
	private Button button1, button2;
	ListView mainright_lv, lv;
	LinearLayout rightLinearLayout;
	public static String num;
	public static String Id, actid1, actid2;
	public static String name;
	public TextView yedtext1;
	// public Panel panel;
	public String cityid;
	String S,H,M,W,B,L,R1;
	EditDialog log = new EditDialog();
	String pageCnt;
	String pageNo;
	TextView pagenum;
	ListAdapter1 listAdapter;
	DES des = new DES();
	DBUtil DB=new DBUtil();
	private Dialog mDialog;
	private Button shouye_xd, shangye_xd, xiaye_xd, moye_xd;
	Handler handler = new Handler() { //

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
			case 6:
				pagenum.setText(pageNo + "/" + pageCnt);
				listAdapter.notifyDataSetChanged();
				break;
			case 7:
				Bundle b = msg.getData();
	        	 b = msg.getData();
	        	 pagenum.setText(pageNo + "/" + pageCnt);
	        	listAdapter.notifyDataSetChanged();
	        	listAdapter.notifyDataSetInvalidated();
	        	log.Toast(YouHuiShangXian_TeHui.this, b.getString("throwsmessage"));
	        	break;
			default:
				break;
			}
		}
	};

	public static String jsont;

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youhuishangxian_tehui);
		try {
		S=	mmscat1(YouHuiShangXian_TeHui.this, "S");
		H=	mmscat1(YouHuiShangXian_TeHui.this, "H");
		B=	mmscat1(YouHuiShangXian_TeHui.this, "B"); 
		M=	mmscat1(YouHuiShangXian_TeHui.this, "M");
		L=	mmscat1(YouHuiShangXian_TeHui.this, "L");
		W=	mmscat1(YouHuiShangXian_TeHui.this, "W");
		R1=	mmscat1(YouHuiShangXian_TeHui.this, "R");
			System.out.println(S+R1+B+H+L);
			if(S==null&&H==null){
				log.Toast(YouHuiShangXian_TeHui.this, "未读取到类型数据！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类onCreate（）方法中"+e.getMessage());
		}

		mDialog = new AlertDialog.Builder(YouHuiShangXian_TeHui.this).create();// 连接网络进程

		// button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1 = (Button) findViewById(R.id.button1);
		rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
		// panel=new Panel(this);

		final String[] province = { S, H, "全部" };

		yedtext1 = (TextView) findViewById(R.id.yedtext1);

		MyLis lis = new MyLis();
		// button1.setOnClickListener(lis);
		button2.setOnClickListener(lis);
		button1.setOnClickListener(lis);

		pagenum = (TextView) findViewById(R.id.pagenum);
		shouye_xd = (Button) findViewById(R.id.shouye_xd);
		shangye_xd = (Button) findViewById(R.id.shangye_xd);
		xiaye_xd = (Button) findViewById(R.id.xiaye_xd);
		moye_xd = (Button) findViewById(R.id.moye_xd);
		// fanhui_button = (Button) findViewById(R.id.fanhui_button);
		MainButtonListener lis2 = new MainButtonListener();

		shouye_xd.setOnClickListener(lis2);
		shangye_xd.setOnClickListener(lis2);
		xiaye_xd.setOnClickListener(lis2);
		moye_xd.setOnClickListener(lis2);

		// List<TCity>
		// tcity=DBUtil.cityQuerycity(YouHuiShangXian_TeHui.this,"t_city"," where Name = '"+TuanDuiShangHuActivity.cityname+"'");
		// cityid = tcity.get(0).id;
		yedtext1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// log.Dialog4(YouHuiShangXian_TeHui.this,
				// yedtext1, R.id.yedtext1);
				log.dialog(YouHuiShangXian_TeHui.this, yedtext1, "类型", province);

			}
		});

		list.clear();
		rightLinearLayout.removeAllViews();
		// rightLinearLayout.setBackgroundDrawable(getResources()
		// .getDrawable(R.drawable.findbg));
		LinearLayout view = (LinearLayout) LayoutInflater.from(
				YouHuiShangXian_TeHui.this).inflate(R.layout.faxingxiang_list7,
				null);

		rightLinearLayout.addView(view);

		// try {
		// Request1();
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		init();
		pagenum.setText(pageNo + "/" + pageCnt);
		listAdapter = new ListAdapter1(YouHuiShangXian_TeHui.this, list);
		mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);

		mainright_lv.setAdapter(listAdapter);

	}

	int a;

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch (id) {
			case R.id.button1:
				intent.setClass(YouHuiShangXian_TeHui.this,
						TuanDuiShangHuActivity.class);
				YouHuiShangXian_TeHui.this.startActivity(intent);
				YouHuiShangXian_TeHui.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
			case R.id.button2:
				list.clear();
				rightLinearLayout.removeAllViews();
				// rightLinearLayout.setBackgroundDrawable(getResources()
				// .getDrawable(R.drawable.findbg));
				LinearLayout view = (LinearLayout) LayoutInflater.from(
						YouHuiShangXian_TeHui.this).inflate(
						R.layout.faxingxiang_list7, null);

				rightLinearLayout.addView(view);
				if (yedtext1.getText().equals(S)) {
					a = 1;
					try {
						Requesttehui();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类Requesttehui（）"+e.getMessage());

					}
				} else if (yedtext1.getText().equals(H)) {
					a = 2;
					try {
						Requestzui();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类Requestzui（）"+e.getMessage());
					}
				} else if (yedtext1.getText().equals("全部")) {
					a = 3;
					try {
						Request1();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类Request1（）"+e.getMessage());
					}
				} else if (yedtext1.getText().equals("")) {
					try {
						Request1();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类Request1（）"+e.getMessage());
					}
				}

				pagenum.setText(pageNo + "/" + pageCnt);

				listAdapter = new ListAdapter1(YouHuiShangXian_TeHui.this, list);

				mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);

				mainright_lv.setAdapter(listAdapter);

			}

		}

	}

	class ListAdapter1 extends BaseAdapter {
		public List<String[]> mItemList; // 修饰符不能是private
		public Context mContext;

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
			View view = convertView;
			if(view==null){
				LayoutInflater inflater = LayoutInflater
					.from(YouHuiShangXian_TeHui.this);
				view = inflater.inflate(R.layout.listtable_item7, null);
			}
			// int colorPos =position % colors.length;
			TextView listtable_tv1 = (TextView) view
					.findViewById(R.id.listtable_tv1);
			TextView listtable_tv2 = (TextView) view
					.findViewById(R.id.listtable_tv2);
			TextView listtable_tv3 = (TextView) view
					.findViewById(R.id.listtable_tv3);
			// TextView listtable_tv4 = (TextView)
			// view.findViewById(R.id.listtable_tv4);
			listtable_tv1.setText(mItemList.get(position)[1]);
			listtable_tv2.setText(mItemList.get(position)[4]);
			listtable_tv3.setText(mItemList.get(position)[6]);
			// listtable_tv4.setText(mItemList.get(position)[6]);
			//
			// listtable_tv1.setBackgroundColor(colors[colorPos]);
			// listtable_tv2.setBackgroundColor(colors[colorPos]);
			// listtable_tv3.setBackgroundColor(colors[colorPos]);
			listtable_tv1.setTextColor(Color.BLACK);
			listtable_tv2.setTextColor(Color.BLACK);
			listtable_tv3.setTextColor(Color.BLACK);
			// listtable_tv4.setTextColor(Color.BLACK);

			MyButton listbutton = (MyButton) view
					.findViewById(R.id.shortcut_youhui);
			listbutton.itemposition = position;
			MyButton listbutton2 = (MyButton) view
					.findViewById(R.id.shortcut_zuihong);
			listbutton2.itemposition = position;

			// 影藏Button，0显示，4影藏，8删除
			listbutton.setVisibility(8);
			listbutton2.setVisibility(8);
			if(S!=null&&H!=null){
				if (mItemList.get(position)[6].contains(S)) {
					listbutton.setVisibility(0);
				}
				if (mItemList.get(position)[6].contains(H)) {
					listbutton2.setVisibility(0);
				}
			}
			// 特惠
			listbutton.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					try{
					Query(YouHuiShangXian_TeHui.this);
					DB.insertContactId5(YouHuiShangXian_TeHui.this, "Id",
							num);
					MyButton myButton = (MyButton) arg0;

					Id = list.get(myButton.itemposition)[0];
					name = list.get(myButton.itemposition)[1];
					// actId=list.get(myButton.itemposition)[5];
					// String actId=list.get(myButton.itemposition)[5];
					String sale = list.get(myButton.itemposition)[6];
					String name = list.get(myButton.itemposition)[1];
					actid1 = list.get(myButton.itemposition)[11];
					DB.upload4(YouHuiShangXian_TeHui.this, new String[] {
							"merId", "actId", "Stname" ,"usename"}, new String[] { Id,
							actid1, name ,TuanDuiShangHuActivity.username}, num);

					sale = "S";
					DB.upload4(YouHuiShangXian_TeHui.this,
							new String[] { "sale" }, new String[] { sale }, num);

					String id = list.get(myButton.itemposition)[0] + num;
					DB.upload4(YouHuiShangXian_TeHui.this,
							new String[] { "merId2" }, new String[] { id }, num);
					// HeYueQianDing_21.str=mItemList.get(myButton.itemposition);
					Intent i = new Intent();
					i.setClass(YouHuiShangXian_TeHui.this,
							YouHuiShangXian_TeHui2.class);
					YouHuiShangXian_TeHui.this.startActivity(i);
					YouHuiShangXian_TeHui.this.finish();
					overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}catch(Exception e){
						techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类listbutton.setonclickListener()"+e.getMessage());
					}
				}
			});
			listbutton2.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					try{
					Query(YouHuiShangXian_TeHui.this);
					DB.insertContactId5(YouHuiShangXian_TeHui.this, "Id",
							num);
					MyButton myButton = (MyButton) arg0;
					Id = list.get(myButton.itemposition)[0];
					name = list.get(myButton.itemposition)[1];
					// actId=list.get(myButton.itemposition)[5];
					// String actId=list.get(myButton.itemposition)[5];
					String sale = list.get(myButton.itemposition)[6];
					String name = list.get(myButton.itemposition)[1];
					actid2 = list.get(myButton.itemposition)[10];// 3634ba7836159dc6013615a226ed000e
					// 3634ba7836159dc6013615a226ed000e
					DB.upload4(YouHuiShangXian_TeHui.this, new String[] {
							"merId", "actId", "Stname" ,"usename"}, new String[] { Id,
							actid2, name ,TuanDuiShangHuActivity.username}, num);

					sale = "H";
					DB.upload4(YouHuiShangXian_TeHui.this,
							new String[] { "sale" }, new String[] { sale }, num);

					String id = list.get(myButton.itemposition)[0] + num;
					DB.upload4(YouHuiShangXian_TeHui.this,
							new String[] { "merId2" }, new String[] { id }, num);
					// HeYueQianDing_21.str=mItemList.get(myButton.itemposition);
					Intent i = new Intent();
					i.setClass(YouHuiShangXian_TeHui.this,
							YouHuiShangXian_TeHui21.class);
					YouHuiShangXian_TeHui.this.startActivity(i);
					YouHuiShangXian_TeHui.this.finish();
					overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}catch(Exception e){
						techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHUi类listbutton2.setonclickListener()"+e.getMessage());
					}
				}

			});
//			}else{
//				log.Toast(YouHuiShangXian_TeHui.this, "未读取到类型数据！");
//			}
			return view;
		}
	}

	public List<String[]> list = new ArrayList<String[]>();
	public List<String[]> list1 = new ArrayList<String[]>();
	String[] tempstr = new String[14];
	protected String Jsonstr;

	// 查询
	public void Requesttehui() throws JSONException {
		Message msg3 = new Message();   
	    msg3.what =1; 
		handler.sendMessage(msg3);
		new Thread(){
			 public void run(){
		Jsonstr = "{\"misId\":\"\",\"terId\":\"\",\"licences\":\"\",\"companyName\":\"\",\"merName\":\"\",\"address\":\"\",\"merTel\":\"\",\"companyTel\":\"\",\"cityId\":\""
				+ TuanDuiShangHuActivity.cityId
				+ "\",\"userId\":\"\",\"activeMode\":\"S\",\"isOnline\":\"Y\",\"pageNo\":\"1\"}";
		 jsont = HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(
				TuanDuiShangHuActivity.indenty, "A0009"), Jsonstr);
		 mDialog.dismiss();
		try {
			JSONObject demoJson1 = new JSONObject(jsont);
			if (demoJson1.getString("rspCode").equals("00")) {
				JSONArray numberList1 = demoJson1.getJSONArray("detail");

				for (int j = 0; j < numberList1.length(); j++) {
					JSONObject datajson = (JSONObject) numberList1.opt(j);
					pageCnt = demoJson1.getString("pageCnt");
					pageNo = demoJson1.getString("pageNo");
					String[] tempstr = new String[14];
					tempstr[0] = datajson.getString("merId");
					tempstr[1] = datajson.getString("merName");
					tempstr[2] = datajson.getString("companyName");
					tempstr[3] = datajson.getString("licences");
					tempstr[4] = datajson.getString("merAddress");
					tempstr[5] = datajson.getString("actId");
					String lei = datajson.getString("activeMode");
					String str1[] = lei.split("\\|");
					String activeMode = null;
					
					
		
					for (int i = 0; i < str1.length; i++) {
						
//						 if (str1[i].equals("")) {
//								activeMode = "无";
//								
//							}
						// else{
//						Context	friendContext = createPackageContext("techown.login",
//								Context.CONTEXT_IGNORE_SECURITY);
//						MyDatabaseHelper myHelper = new MyDatabaseHelper(
//								friendContext, "techown_login.db", 1);
//						SQLiteDatabase db = myHelper.getWritableDatabase();
//						Cursor cursor = db.rawQuery("select * from mmsact where para=?  ",
//							new  String[]{	str1[i]});
//						
//						while(cursor.moveToNext()){
//							try {
//								if (activeMode == null) {
//									activeMode = cursor.getString(cursor
//											.getColumnIndex("paraChinese"));
//								}
//								else{
//								activeMode=activeMode+cursor.getString(cursor
//										.getColumnIndex("paraChinese"));
//								}
//							} catch (Exception e) {
//								// TODO: handle exception
//							}
//							cursor.close();
//							db.close();
//						}
						// }
						if (str1[i].equals("H")) {
							if (activeMode == null) {
								activeMode = H;
							} else {
								activeMode = activeMode + H;
							}

						}
						if (str1[i].equals("S")) {
							if (activeMode == null) {
								activeMode = S;
							} else {
								activeMode = activeMode + S;
							}
						}
						if (str1[i].equals("M")) {
							if (activeMode == null) {
								activeMode = M;
							} else {
								activeMode = activeMode + M;
							}
						}
						if (str1[i].equals("W")) {
							if (activeMode == null) {
								activeMode = W;
							} else {
								activeMode = activeMode + W;
							}
						} else if (str1[i].equals("")) {
							activeMode = "无";
						}
						tempstr[6] = activeMode;
					}
					tempstr[7] = datajson.getString("agreementId");
					tempstr[8] = datajson.getString("auditState");
					tempstr[9] = datajson.getString("auditReason");

					String[] arrg1 = lei.split("\\|");
					String[] errg1 = tempstr[5].split("\\|");
					for (int i = 0; i < arrg1.length; i++) {
						if (arrg1[i].equals("H")) {
							actId1 = errg1[i];
						}
					}
					for (int i = 0; i < arrg1.length; i++) {
						if (arrg1[i].equals("S")) {
							actId2 = errg1[i];
						}
					}

					tempstr[10] = actId1;
					tempstr[11] = actId2;

					list.add(tempstr);
				}
				Message msg3 = new Message();   
		        msg3.what =6; 
				handler.sendMessage(msg3);
			}else{
				pageCnt="1";
				 pageNo="1";
				Message msg2 = new Message();   
			     msg2.what =7; 
			     Bundle b = new Bundle();
			     b.putString("throwsmessage",demoJson1.getString("rspInfo"));
			     msg2.setData(b);
				 handler.sendMessage(msg2);
				 
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类Requesttehui（）"+e.getMessage());
		}
			 }
		 }.start();	
	}

	public void Requestzui() throws JSONException {
		Message msg3 = new Message();   
	    msg3.what =1; 
		handler.sendMessage(msg3);
		new Thread(){
			 public void run(){
		Jsonstr = "{\"misId\":\"\",\"terId\":\"\",\"licences\":\"\",\"companyName\":\"\",\"merName\":\"\",\"address\":\"\",\"merTel\":\"\",\"companyTel\":\"\",\"cityId\":\""
				+ TuanDuiShangHuActivity.cityId
				+ "\",\"userId\":\"\",\"activeMode\":\"H\",\"isOnline\":\"Y\",\"pageNo\":\"1\"}";
		jsont = HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(
				TuanDuiShangHuActivity.indenty, "A0009"), Jsonstr);
		mDialog.dismiss(); 
		try {
			JSONObject demoJson1 = new JSONObject(jsont);
			if (demoJson1.getString("rspCode").equals("00")) {
				JSONArray numberList1 = demoJson1.getJSONArray("detail");
				for (int j = 0; j < numberList1.length(); j++) {
					JSONObject datajson = (JSONObject) numberList1.opt(j);
					String[] tempstr = new String[14];
					pageCnt = demoJson1.getString("pageCnt");
					pageNo = demoJson1.getString("pageNo");
					tempstr[0] = datajson.getString("merId");
					tempstr[1] = datajson.getString("merName");
					tempstr[2] = datajson.getString("companyName");
					tempstr[3] = datajson.getString("licences");
					tempstr[4] = datajson.getString("merAddress");
					tempstr[5] = datajson.getString("actId");
					String lei = datajson.getString("activeMode");
					String str1[] = lei.split("\\|");
					String activeMode = null;
					for (int i = 0; i < str1.length; i++) {
						if (str1[i].equals("H")) {
							if (activeMode == null) {
								activeMode = H;
							} else {
								activeMode = activeMode + "、"+H;
							}

						}
						if (str1[i].equals("S")) {
							if (activeMode == null) {
								activeMode = S;
							} else {
								activeMode = activeMode + "、"+S;
							}
						}
						if (str1[i].equals("M")) {
							if (activeMode == null) {
								activeMode = M;
							} else {
								activeMode = activeMode + "、"+M;
							}
						}
						if (str1[i].equals("W")) {
							if (activeMode == null) {
								activeMode = W;
							} else {
								activeMode = activeMode + "、"+W;
							}
						} else if (str1[i].equals("")) {
							activeMode = "无";
						}
						tempstr[6] = activeMode;
					}

					tempstr[7] = datajson.getString("agreementId");
					tempstr[8] = datajson.getString("auditState");
					tempstr[9] = datajson.getString("auditReason");

					String[] arrg1 = lei.split("\\|");
					String[] errg1 = tempstr[5].split("\\|");
					for (int i = 0; i < arrg1.length; i++) {
						if (arrg1[i].equals("H")) {
							actId1 = errg1[i];
						}
					}
					for (int i = 0; i < arrg1.length; i++) {
						if (arrg1[i].equals("S")) {
							actId2 = errg1[i];
						}
					}

					tempstr[10] = actId1;
					tempstr[11] = actId2;

					list.add(tempstr);
				}
				Message msg3 = new Message();   
		        msg3.what =6; 
				handler.sendMessage(msg3);
			}else{
				pageCnt="1";
				 pageNo="1";
				Message msg2 = new Message();   
			     msg2.what =7; 
			     Bundle b = new Bundle();
			     b.putString("throwsmessage",demoJson1.getString("rspInfo"));
			     msg2.setData(b);
				 handler.sendMessage(msg2);
				
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类Requestzui（）"+e.getMessage());
		}
			 }
		}.start();
	}

	public void Request1() throws JSONException {
		// userId：业代工号，必填；taskType：工单类型；taskStatus：工单状态
		Message msg3 = new Message();   
	    msg3.what =1; 
		handler.sendMessage(msg3);
		new Thread(){
			 public void run(){
		Jsonstr = "{\"misId\":\"\",\"terId\":\"\",\"licences\":\"\",\"companyName\":\"\",\"merName\":\"\""
				+ ",\"address\":\"\",\"merTel\":\"\",\"companyTel\":\"\",\"cityId\":\""
				+ TuanDuiShangHuActivity.cityId
				+ "\",\"userId\":\"\",\"activeMode\":\"H*S\",\"isOnline\":\"Y\",\"pageNo\":\"1\"}";

		 jsont = HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(
				TuanDuiShangHuActivity.indenty, "A0009"), Jsonstr);
		 mDialog.dismiss(); 
		try {
			JSONObject demoJson1 = new JSONObject(jsont);
			if (demoJson1.getString("rspCode").equals("00")) {
				JSONArray numberList1 = demoJson1.getJSONArray("detail");
				for (int j = 0; j < numberList1.length(); j++) {
					JSONObject datajson = (JSONObject) numberList1.opt(j);
					String[] tempstr = new String[14];
					pageCnt = demoJson1.getString("pageCnt");
					pageNo = demoJson1.getString("pageNo");
					tempstr[0] = datajson.getString("merId");
					tempstr[1] = datajson.getString("merName");
					tempstr[2] = datajson.getString("companyName");
					tempstr[3] = datajson.getString("licences");
					tempstr[4] = datajson.getString("merAddress");
					tempstr[5] = datajson.getString("actId");
					String lei = datajson.getString("activeMode");
					String str1[] = lei.split("\\|");
					String activeMode = null;
					for (int i = 0; i < str1.length; i++) {
						if (str1[i].equals("H")) {
							if (activeMode == null) {
								activeMode = H;
							} else {
								activeMode = activeMode + "、"+H;
							}

						}
						if (str1[i].equals("S")) {
							if (activeMode == null) {
								activeMode = S;
							} else {
								activeMode = activeMode + "、"+S;
							}
						}
						if (str1[i].equals("M")) {
							if (activeMode == null) {
								activeMode = M;
							} else {
								activeMode = activeMode + "、"+M;
							}
						}
						if (str1[i].equals("W")) {
							if (activeMode == null) {
								activeMode = W;
							} else {
								activeMode = activeMode + "、"+W;
							}
						} else if (str1[i].equals("")) {
							activeMode = "无";
						}
						tempstr[6] = activeMode;
					}
					tempstr[7] = datajson.getString("agreementId");
					tempstr[8] = datajson.getString("auditState");
					tempstr[9] = datajson.getString("auditReason");

					String[] arrg1 = lei.split("\\|");
					String[] errg1 = tempstr[5].split("\\|");
					for (int i = 0; i < arrg1.length; i++) {
						if (arrg1[i].equals("H")) {
							actId1 = errg1[i];
						}
					}
					for (int i = 0; i < arrg1.length; i++) {
						if (arrg1[i].equals("S")) {
							actId2 = errg1[i];
						}
					}

					tempstr[10] = actId1;
					tempstr[11] = actId2;

					list.add(tempstr);
				}
				Message msg3 = new Message();   
		        msg3.what =6; 
				handler.sendMessage(msg3);
			}else{
				pageCnt="1";
				 pageNo="1";
				Message msg2 = new Message();   
			     msg2.what =7; 
			     Bundle b = new Bundle();
			     b.putString("throwsmessage",demoJson1.getString("rspInfo"));
			     msg2.setData(b);
				 handler.sendMessage(msg2);
				 
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类Request1（）"+e.getMessage());
		}
			 }
		}.start();
		// sendtList1.add(name);
	}

	String actId1;
	String actId2;

	public void init() {
		try {
			JSONObject demoJson1 = new JSONObject(jsont);
			if (demoJson1.getString("rspCode").equals("00")) {

				pageCnt = demoJson1.getString("pageCnt");
				pageNo = demoJson1.getString("pageNo");
				JSONArray numberList1 = demoJson1.getJSONArray("detail");
				for (int j = 0; j < numberList1.length(); j++) {
					JSONObject datajson = (JSONObject) numberList1.opt(j);
					String[] tempstr = new String[14];

					tempstr[0] = datajson.getString("merId");
					tempstr[1] = datajson.getString("merName");
					tempstr[2] = datajson.getString("companyName");
					tempstr[3] = datajson.getString("licences");
					tempstr[4] = datajson.getString("merAddress");
					tempstr[5] = datajson.getString("actId");
					String lei = datajson.getString("activeMode");
					String str1[] = lei.split("\\|");
					String activeMode = null;
					for (int i = 0; i < str1.length; i++) {
						if (str1[i].equals("H")) {
							if (activeMode == null) {
								activeMode = H;
							} else {
								activeMode = activeMode + "、"+H;
							}

						}
						if (str1[i].equals("S")) {
							if (activeMode == null) {
								activeMode = S;
							} else {
								activeMode = activeMode + "、"+S;
							}
						}
						if (str1[i].equals("M")) {
							if (activeMode == null) {
								activeMode = M;
							} else {
								activeMode = activeMode + "、"+M;
							}
						}
						if (str1[i].equals("W")) {
							if (activeMode == null) {
								activeMode = W;
							} else {
								activeMode = activeMode + "、"+W;
							}
						} else if (str1[i].equals("")) {
							activeMode = "无";
						}
						tempstr[6] = activeMode;
					}
					tempstr[7] = datajson.getString("agreementId");
					tempstr[8] = datajson.getString("auditState");
					tempstr[9] = datajson.getString("auditReason");

					String[] arrg1 = lei.split("\\|");
					String[] errg1 = tempstr[5].split("\\|");
					for (int i = 0; i < arrg1.length; i++) {
						if (arrg1[i].equals("H")) {
							actId1 = errg1[i];
						}
					}
					for (int i = 0; i < arrg1.length; i++) {
						if (arrg1[i].equals("S")) {
							actId2 = errg1[i];
						}
					}

					tempstr[10] = actId1;
					tempstr[11] = actId2;

					list.add(tempstr);

				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类init（）"+e.getMessage());
		}
	}

	class MainButtonListener implements OnClickListener {
		Intent intent = getIntent();

		public void onClick(View v) {
			int v_id = v.getId();
			switch (v_id) {
			case R.id.shouye_xd:// 首页
				if (Integer.parseInt(pageNo) == 1) {
					log.Toast(YouHuiShangXian_TeHui.this, "已经是首页！");
				} else {
					pageData("1");

				}

				break;
			case R.id.shangye_xd:// 上一页
				if (Integer.parseInt(pageNo) > 1) {
					pageData(String.valueOf(Integer.parseInt(pageNo) - 1));

				} else {
					log.Toast(YouHuiShangXian_TeHui.this, "已经是首页！");
				}

				break;
			case R.id.xiaye_xd:// 下一页
				if (Integer.parseInt(pageNo) == Integer.parseInt(pageCnt)) {
					log.Toast(YouHuiShangXian_TeHui.this, "已经是尾页！");
				} else {
					pageData(String.valueOf(Integer.parseInt(pageNo) + 1));

				}
				break;
			case R.id.moye_xd:// 末页
				if (pageCnt.equalsIgnoreCase(pageNo)) {
					log.Toast(YouHuiShangXian_TeHui.this, "已经是尾页！");
				} else {
					pageData(pageCnt);

				}

				break;

			}
		}

	}

	private void pageData(final String pagerno) {

		Message msg3 = new Message();
		msg3.what = 1;
		handler.sendMessage(msg3);
		new Thread() {
			public void run() {

				String[] str = new String[] { "", "", "", "", "", "", "", "",
						"", "", "", "Y", "" };

				if (a == 1) {
					str[10] = "S";
				} else if (a == 2) {
					str[10] = "H";
				} else {
					str[10] = "S*H";
				}

				// str[9]=TuanDuiShangHuActivity.username;

				str[8] = TuanDuiShangHuActivity.cityId;
				str[12] = pagerno;
				String json = "{" + "\"misId\":\"" + str[0] + "\","
						+ "\"terId\":\"" + str[1] + "\"," + "\"licences\":\""
						+ str[2] + "\"," + "\"companyName\":\"" + str[3]
						+ "\"," + "\"merName\":\"" + str[4] + "\","
						+ "\"address\":\"" + str[5] + "\"," + "\"merTel\":\""
						+ str[6] + "\"," + "\"companyTel\":\"" + str[7] + "\","
						+ "\"cityId\":\"" + str[8] + "\"," + "\"userId\":\""
						+ str[9] + "\"," + "\"activeMode\":\"" + str[10]
						+ "\"," + "\"isOnline\":\"" + str[11] + "\","
						+ "\"pageNo\":\"" + str[12] + "\"}";
				String requeststr = HttpConnection2.request(json, "A0009");//
				mDialog.dismiss(); //
				if (requeststr != null) {
					try {
						JSONObject demoJson1 = new JSONObject(requeststr);

						if (demoJson1.getString("rspCode").equals("00")) {
							list.clear();
							JSONArray numberList1 = demoJson1
									.getJSONArray("detail");
							for (int j = 0; j < numberList1.length(); j++) {
								JSONObject datajson = (JSONObject) numberList1
										.opt(j);
								String[] tempstr = new String[14];
								pageCnt = demoJson1.getString("pageCnt");
								pageNo = demoJson1.getString("pageNo");
								tempstr[0] = datajson.getString("merId");
								tempstr[1] = datajson.getString("merName");
								tempstr[2] = datajson.getString("companyName");
								tempstr[3] = datajson.getString("licences");
								tempstr[4] = datajson.getString("merAddress");
								tempstr[5] = datajson.getString("actId");
								String lei = datajson.getString("activeMode");
								String str1[] = lei.split("\\|");
								String activeMode = null;
								for (int i = 0; i < str1.length; i++) {
									if (str1[i].equals("H")) {
										if (activeMode == null) {
											activeMode = H;
										} else {
											activeMode = activeMode + "、"+H;
										}

									}
									if (str1[i].equals("S")) {
										if (activeMode == null) {
											activeMode = S;
										} else {
											activeMode = activeMode + "、"+S;
										}
									}
									if (str1[i].equals("M")) {
										if (activeMode == null) {
											activeMode = M;
										} else {
											activeMode = activeMode + "、"+M;
										}
									}
									if (str1[i].equals("W")) {
										if (activeMode == null) {
											activeMode = W;
										} else {
											activeMode = activeMode + "、"+W;
										}
									} else if (str1[i].equals("")) {
										activeMode = "无";
									}
									tempstr[6] = activeMode;
								}
								tempstr[7] = datajson.getString("agreementId");
								tempstr[8] = datajson.getString("auditState");
								tempstr[9] = datajson.getString("auditReason");

								String[] arrg1 = lei.split("\\|");
								String[] errg1 = tempstr[5].split("\\|");
								for (int i = 0; i < arrg1.length; i++) {
									if (arrg1[i].equals("H"))// 3634b0c03726d758013726e61fe20001
									{
										actId1 = errg1[i];
									}
								}
								for (int i = 0; i < arrg1.length; i++) {
									if (arrg1[i].equals("S")) {
										actId2 = errg1[i];
									}
								}
								// 3634b0c03726d758013726e61fe20001|3634b0c037174bf3013725c403060012//h\s
								tempstr[10] = actId1;
								tempstr[11] = actId2;

								list.add(tempstr);
							}

							Message msg3 = new Message();
							msg3.what = 6;
							handler.sendMessage(msg3);

						} else if (demoJson1.getString("rspCode").equals("10")) {
							Message msg = new Message();
							Bundle b = new Bundle();
							b.putString("creat", demoJson1.getString("rspInfo"));
							msg.setData(b);
							msg.what = 7;
							handler.sendMessage(msg);//

						} else {
							Message msg = new Message();
							Bundle b = new Bundle();
							b.putString("throwsmessage",
							demoJson1.getString("rspInfo"));
							msg.setData(b);
							msg.what = 7;
							handler.sendMessage(msg);//

						}
					} catch (Exception e) {

						techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类pageData（）"+e.getMessage());
					}
				}

			}
		}.start();
	}
  public  String  mmscat1(Context con,String str) throws NameNotFoundException{
	  String 	re=null;
	  Cursor cursor=null;
	  SQLiteDatabase db = null;
	  try {
		Context	friendContext =con. createPackageContext("techown.login",
				Context.CONTEXT_IGNORE_SECURITY);
		MyDatabaseHelper myHelper = new MyDatabaseHelper(
				friendContext, "techown_login.db", Constant.loginDBVer);
		db = myHelper.getWritableDatabase();
		cursor = db.rawQuery("select * from mmsact where para=?  ",
			new  String[]{des.jiaMi(str)});
		
		while(cursor.moveToNext()){
				
				re	=des.jieMI(cursor.getString(cursor
						.getColumnIndex("paraChinese")));
				
			
		}
	  } catch (Exception e) {
		  techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui类mmscat1()"+e.getMessage());
	  }finally{
		  if(cursor!=null){
			  cursor.close();
		  }
		  if(db!=null){
			  db.close();
		  }
	  }
		return re;
  }
	public  void Query(Context con) {
		SQLiteDatabase db=null;
		Cursor cur=null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query("youhushangxian", new String[] { "Id" },
					null, null, null, null, null);

			if (cur.getCount() == 0) {
				num = "1";

			} else {
				while (cur.moveToNext()) {
					if (cur.isLast()) {
						num = String
								.valueOf(Long.parseLong(cur.getString(0)) + 1);
					}
				}
			}
			
		} catch (Exception e) {
			Toast.makeText(con, "数据库错误：" + e.toString(), Toast.LENGTH_SHORT)
					.show();

		}finally{
			if(cur!=null){
				cur.close();
			}
			if(db!=null){
				db.close();
			}
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(YouHuiShangXian_TeHui.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
}