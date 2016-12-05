package techown.shanghu;
/*
 * 商户查询页面--根据商户名称，查询
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.GongDanWeiHu1.ListAdapter1;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.MyButton;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ShangHuWifiWenTi1 extends Activity implements OnScrollListener
				,OnClickListener{
	public static String jsont;
	Intent intent;
	EditDialog log=new EditDialog();
	public List<String[]> arraylist = new ArrayList<String[]>();

	 EditDialog ds=new EditDialog();
	private Dialog mDialog;
	public TextView merName;
	public LinearLayout yleoutwifi;
	private Button btn_return;
	private Button brn_query;
	private ListView listview1;
	private LinearLayout right_linearLayoutwifi;
	private Button btn_query;
	
	private MyAdapter adapter2;
	private ListView shangHuList;

	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
			case 1:
				mDialog.show();
				mDialog.setContentView(R.layout.loading_process_dialog_anim);
				mDialog.setCanceledOnTouchOutside(false);
				mDialog.setCancelable(false);
				break;
				
			case 2:
				adapter2.notifyDataSetChanged();
				adapter2.notifyDataSetInvalidated();
				break;
				
			case 3:
				Bundle b = msg.getData();
	        	 b = msg.getData();
	        	ds.Toast(ShangHuWifiWenTi1.this,b.getString("throwsmessage"));
		        break;
			}
		
		}
		
	};
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		/**
		 * 加载商户查询页面：
		 */
		setContentView(R.layout.shanghuwifichaxun);
		
		mDialog = new AlertDialog.Builder(ShangHuWifiWenTi1.this).create();//连接网络进程
		merName=(TextView)findViewById(R.id.yedtextwifi1);
		/**
		 * listview
		 */
		shangHuList = (ListView)findViewById(R.id.right_linearLayoutwifi);
		//商户名称输入框
		yleoutwifi=(LinearLayout)findViewById(R.id.yleoutwifi1);
		
		//查询按钮
		btn_query = (Button)findViewById(R.id.buttonwifi2);
		//返回按钮
		btn_return = (Button)findViewById(R.id.buttonwifi1);
		
		btn_query.setOnClickListener(this);
		btn_return.setOnClickListener(this);
		
		adapter2 = new MyAdapter();
		shangHuList.setAdapter(adapter2);
		
		/**
		 * listview的item点击事件，点击进入相应的问题提报页面
		 */
		shangHuList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent();
				
				Bundle b = new Bundle();
			//	b.putString("userId",TuanDuiShangHuActivity.username);
				//b.putExtra("merId", arraylist.get(arg2)[0]);
				b.putString("merId", arraylist.get(arg2)[0]);
				
				i.putExtra("data", b);
				
				i.setClass(ShangHuWifiWenTi1.this, WenTiTiBao.class);
				ShangHuWifiWenTi1.this.startActivity(i);
				ShangHuWifiWenTi1.this.finish();
				
			}
		});
		
		
		//商户名称输入框
		yleoutwifi.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ShangHuWifiWenTi1.this, yleoutwifi, merName,
							R.id.yedtextwifi1,"请输入商户名");
					
					break;
				default:
					break;
				}      

				return false;
			}
		});

	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(ShangHuWifiWenTi1.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		int what = v.getId();
		switch(what){
		case R.id.buttonwifi1:{
			Intent i = new Intent();
			i.setClass(ShangHuWifiWenTi1.this, TuanDuiShangHuActivity.class);
			ShangHuWifiWenTi1.this.startActivity(i);
			overridePendingTransition(R.anim.rightin,
					R.anim.rightout);
			ShangHuWifiWenTi1.this.finish();
			break;
			}
		
		case R.id.buttonwifi2:{
			
			arraylist.clear();
			
			adapter2.notifyDataSetChanged();
			
			if(validate()){
				
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
				
				new Thread()
				{
					public void run(){
						try{
							request();
						}catch(Exception e){
							techown.shanghu.https.Log.Instance()
							.WriteLog("ShangHuWifiWenTi1:Request1()" + e.getMessage());
						}
						if(rspCode!=null){
							if(rspCode.equals("000000")){//成功返回数据
								mDialog.dismiss();
								Message msg = new Message();
								msg.what = 2;
								handler.sendMessage(msg);
						
							}else if(rspCode.equals("800")){
							
								mDialog.dismiss();
								Message msg = new Message();   
								Bundle b = new Bundle();
								b.putString("throwsmessage",rspInfo);
								msg.setData(b);
						        msg.what =3; 
								handler.sendMessage(msg);//
								mDialog.dismiss();
							}
							else{
								mDialog.dismiss(); //返回码为000001.000002等
								Message msg = new Message();   
								Bundle b = new Bundle();
								b.putString("throwsmessage",rspInfo);
								msg.setData(b);
						        msg.what =3; 
								handler.sendMessage(msg);//
								mDialog.dismiss();
							}
						}else{
							mDialog.dismiss();
							Message msg = new Message();   
							Bundle b = new Bundle();
							b.putString("throwsmessage","连接服务器超时，请重新查询！");
							msg.setData(b);
					        msg.what =3; 
							handler.sendMessage(msg);//
							mDialog.dismiss();
						}
					}
				}.start();
			}
			
			break;
		}
			
		}
	}
	String rspCode;
	String rspInfo;
	private void request(){
		JSONObject jsonObject = new JSONObject();
		String json = null;
		//Log.i("chengqk","imei:"+TuanDuiShangHuActivity.imei);
		Log.i("chengqk","username:"+TuanDuiShangHuActivity.username);
		try {
			jsonObject.put("userId", TuanDuiShangHuActivity.username);
			jsonObject.put("merName", merName.getText().toString());
			jsonObject.put("questionType", "MTX");
			json = jsonObject.toString();
			Log.i("chengqk",json);
			techown.shanghu.https.Log.Instance().WriteLog("MTX0001的请求的json字符串："+json);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance()
			.WriteLog("ShangHuWifiWenTi1:Request1()" + e.getMessage());
			//e.printStackTrace();
		}
		String jsonstr = HttpConnection2.tbrequest2(json,"MTX0001");
		
		techown.shanghu.https.Log.Instance().WriteLog("MTX0001的返回jsonstr字符串："+jsonstr);
		Log.i("chengqk",jsonstr);
		if(jsonstr!=null &&!"".equals(jsonstr)){
			try {
				JSONObject j = new JSONObject(jsonstr);
				rspCode = j.getString("rspCode");
				Log.i("chengqk",rspCode);
				
				rspInfo = j.getString("rspInfo");
				if(rspCode.equals("000000")){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000000 请求数据成功  rspInfo"+rspInfo);
				
					JSONArray array = j.getJSONArray("detail");
					for(int i = 0; i < array.length(); i++){
						String[] s = new String[3];
						JSONObject o = array.getJSONObject(i);
						s[0] = o.getString("merId");
						s[1] = o.getString("merName");
						s[2] = o.getString("merAddress");
						arraylist.add(s);
					}
				}else if("000001".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("MTX0001 rspCode:000001  该服务不存在  rspInfo:"+rspInfo);
					rspInfo = "没有查询到相关的商户,请重新查询";
				}else if("000002".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("MTX0001 rspCode:000002  服务器响应超时  rspInfo:"+rspInfo);
					rspInfo = "服务响应超时,请重新查询";
				}else if("000003".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("MTX0001 rspCode:000003 参数错误  rspInfo:"+rspInfo);
					//rspInfo = "没有查询到相关的商户,请重新查询";
				}else if("000004".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("MTX0001 rspCode:000004  没有查询到相关商户 rspInfo:"+rspInfo);
					rspInfo = "没有查询到相关的商户，请重新查询";
				}else if("000005".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("MTX0001 rspCode:000005  数据处理错误  rspInfo:"+rspInfo);
					rspInfo = "没有查询到相关的商户，请重新查询";
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance()
				.WriteLog("ShangHuWifiWenTi1:Request1()" + e.getMessage());
				//e.printStackTrace();
			}
		}
		
	}

	
	public class MyAdapter extends BaseAdapter{
		
		public int getCount() {
			// TODO Auto-generated method stub
			return arraylist.size();
		}

		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arraylist.get(arg0);
		}

		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			//View view = arg1;
			if (arg1 == null) {
				LayoutInflater inflater = LayoutInflater
						.from(ShangHuWifiWenTi1.this);
				arg1 = inflater.inflate(R.layout.shanghuwifishanghulistview, null);
			}
			
			TextView listtable_tv1 = (TextView) arg1.findViewById(R.id.shanghuName);
			TextView listtable_tv2 = (TextView)arg1.findViewById(R.id.shanghuAddress);
			
			listtable_tv1.setText(arraylist.get(arg0)[1]);
			listtable_tv2.setText(arraylist.get(arg0)[2]);
			
			listtable_tv1.setTextColor(Color.BLACK);
			listtable_tv2.setTextColor(Color.BLACK);
			
			return arg1;
		}
		
	}
	protected boolean validate() {
		try {
			if ("".equals(merName.getText().toString())) {
				log.Toast(ShangHuWifiWenTi1.this, "请输入商户名！");
				return false;
			}
			return true;  
		} catch (Exception e) {
			log.Toast(ShangHuWifiWenTi1.this, "输入内容错误，请确认后重新提交 ！");
			return false;  
		}
	}

	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
	}
			
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
	
}