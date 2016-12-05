package techown.shanghu;

/*
 * 提报问题查询
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.GongDanWeiHu1.ListAdapter1;
import techown.shanghu.WifiStateChaXun.MyAdapter;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.MyButton;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.Encrypt;
import techown.shanghu.https.HttpConnection2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class WifiStateChaXun extends Activity implements OnScrollListener,
		OnClickListener {

	EditDialog log = new EditDialog();
	EditDialog ds = new EditDialog();
	AlertDialog mDialog;
	private LinearLayout statelayout1, statelayout2, statelayout3,
			statelayout4;
	private TextView merName, starteDate, endDate, checkState;
	private Button btn_return;
	private Button btn_query;
	private ListView listview;
	public List<String[]> list = new ArrayList<String[]>();
	private MyAdapter myAdapter;
	String[] tbType = new String[] {};

	Handler handler = new Handler() {

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
				myAdapter.notifyDataSetChanged();
				myAdapter.notifyDataSetInvalidated();
				break;

			case 3:
				Bundle b = msg.getData();
				b = msg.getData();
				ds.Toast(WifiStateChaXun.this, b.getString("throwsmessage"));
				break;

			}
		}
	};
	private TextView statetexttype;
	private LinearLayout statelayouttype;

	public void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		final String[] stateTag = { "审核中", "待处理", "处理中", "已处理", "结案" };
		setContentView(R.layout.shanghuwifistatechaxun);
		mDialog = new AlertDialog.Builder(WifiStateChaXun.this).create();// 连接网络进程
		/**
		 * 初始化view
		 */
		initview();
		btn_query.setOnClickListener(this);
		btn_return.setOnClickListener(this);

		/**
		 * 商户名称输入框
		 */
		statelayout1.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(WifiStateChaXun.this, statelayout1, merName,
							R.id.statetext1, "请输入商户名");

					break;
				default:
					break;
				}

				return false;
			}
		});
		/**
		 * 输入提报开始日期
		 */
		statelayout2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateDialog.showDatePicker(WifiStateChaXun.this, starteDate,
						R.id.statetext2);
				String sdate = ((String)(starteDate.getText())).replace("-", "");
				
			}

		});
		/**
		 * 输入提报结束日期
		 */
		statelayout3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateDialog.showDatePicker(WifiStateChaXun.this, endDate,
						R.id.statetext3);
			}

		});
		/**
		 * 提报类型输入
		 */
		/*
		 * statetexttype.setOnClickListener(new OnClickListener() {
		 * 
		 * public void onClick(View arg0) { // TODO Auto-generated method stub
		 * log.tbdialog(WifiStateChaXun.this, statetexttype, "提报类型", tbType); }
		 * });
		 */

		/**
		 * 状态输入
		 */
		checkState.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.tbdialog(WifiStateChaXun.this, checkState, "当前状态", stateTag);
			}
		});

		/**
		 * 为listview适配
		 */
		myAdapter = new MyAdapter();
		listview.setAdapter(myAdapter);

	}

	/**
	 * 适配类：baseAdapter
	 * 
	 * @author 程乾坤
	 * 
	 */
	class MyAdapter extends BaseAdapter {

		public int getCount() {
			// TODO Auto-generated method stub
			if (list == null)
				return 0;
			return list.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Holder holder;
			View view = convertView;
			if (view == null) {
				view = LayoutInflater.from(WifiStateChaXun.this).inflate(
						R.layout.wiftstateshanghulist, null);
				holder = new Holder();
				holder.wifistatetext1 = (TextView) view
						.findViewById(R.id.wifistatetext1);
				holder.wifistatetext2 = (TextView) view
						.findViewById(R.id.wifistatetext2);
				holder.wifistatetext3 = (Button) view
						.findViewById(R.id.wifistatetext3);
				holder.wifistatetext4 = (TextView) view
						.findViewById(R.id.wifistatetext4);
				holder.wifistatetext5 = (TextView) view
						.findViewById(R.id.wifistatetext5);
				holder.wifistatetext6 = (TextView) view
						.findViewById(R.id.wifistatetext6);
				holder.wifistatetext7 = (Button) view
						.findViewById(R.id.wifistatetext7);
				holder.wifistatetext8 = (TextView) view
						.findViewById(R.id.wifistatetext8);
				view.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			final String content = list.get(position)[2];
			final String chuli = list.get(position)[6];
			// Log.i("chengqk","问题内容："+content+"-----处理内容："+chuli);
			holder.wifistatetext3.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					new AlertDialog.Builder(WifiStateChaXun.this)
							.setTitle("问题描述")
							.setMessage(content)
							.setPositiveButton("确认",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();

				}
			});
			holder.wifistatetext7.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					new AlertDialog.Builder(WifiStateChaXun.this)
							.setTitle("处理内容")
							.setMessage(chuli)
							.setPositiveButton("确认",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();

				}
			});
			holder.wifistatetext1.setText(list.get(position)[0]);
			holder.wifistatetext2.setText(list.get(position)[1]);

			holder.wifistatetext4.setText(list.get(position)[3]);
			holder.wifistatetext5.setText(list.get(position)[4]);
			holder.wifistatetext6.setText(list.get(position)[5]);

			holder.wifistatetext8.setText(list.get(position)[7]);

			return view;
		}

	}

	/**
	 * 优化listviw
	 * 
	 * @author pccc
	 * 
	 */
	class Holder {
		private TextView wifistatetext1, wifistatetext2, wifistatetext4,
				wifistatetext5, wifistatetext6, wifistatetext8;
		private Button wifistatetext3, wifistatetext7;
	}

	/**
	 * 初始化组件
	 */
	private void initview() {
		statelayout1 = (LinearLayout) findViewById(R.id.statelayout1);
		merName = (TextView) findViewById(R.id.statetext1);
		statelayout2 = (LinearLayout) findViewById(R.id.statelayout2);
		starteDate = (TextView) findViewById(R.id.statetext2);
		statelayout3 = (LinearLayout) findViewById(R.id.statelayout3);

		endDate = (TextView) findViewById(R.id.statetext3);
		statelayout4 = (LinearLayout) findViewById(R.id.statelayout4);

		/*
		 * statetexttype = (TextView)findViewById(R.id.statetexttype);
		 * statelayouttype = (LinearLayout)findViewById(R.id.statelayouttype);
		 */

		checkState = (TextView) findViewById(R.id.statetext4);
		btn_query = (Button) findViewById(R.id.statebutton1);
		btn_return = (Button) findViewById(R.id.statebutton2);
		listview = (ListView) findViewById(R.id.statelist);
	}

	/**
	 * onclick事件处理
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int what = v.getId();
		switch (what) {

		/**
		 * 查询
		 */
		case R.id.statebutton1: {
			list.clear();
			myAdapter.notifyDataSetChanged();
			
			if (validate()) {
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);

				new Thread() {
					public void run() {
						try {
							request();
						} catch (Exception e) {
							techown.shanghu.https.Log.Instance().WriteLog(
									"WifiStateChaXun:Request1()"
											+ e.getMessage());
						}
						if (rspCode != null) {
							if (rspCode.equals("000000")) {
								mDialog.dismiss();
								Message msg = new Message();
								msg.what = 2;
								handler.sendMessage(msg);

							}// else if(rspCode.equals("800")){
							else {
								mDialog.dismiss();
								Message msg = new Message();
								Bundle b = new Bundle();
								b.putString("throwsmessage", rspInfo);
								msg.setData(b);
								msg.what = 3;
								handler.sendMessage(msg);//
							}
						} else {
							mDialog.dismiss();
							Message msg = new Message();
							Bundle b = new Bundle();
							b.putString("throwsmessage", "连接服务器超时，请重新查询！");
							msg.setData(b);
							msg.what = 3;
							handler.sendMessage(msg);//
						}

					}
				}.start();

			}
			// myAdapter.notifyDataSetChanged();
			break;
		}

		/**
		 * 返回
		 */
		case R.id.statebutton2: {
			//log.ExitApp(WifiStateChaXun.this);
			Intent i = new Intent();
			i.setClass(WifiStateChaXun.this, TuanDuiShangHuActivity.class);
			WifiStateChaXun.this.startActivity(i);
			overridePendingTransition(R.anim.rightin,
					R.anim.rightout);
			WifiStateChaXun.this.finish();
			break;
		}

		}

	}

	String rspCode;
	String rspInfo;

	private void request() {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		String json = null;
		try {
			//String mer = "";
			//mer = (String) merName.getText();
			jsonObject.put("merName", merName.getText().toString());
			Log.i("chengqk","merName:------"+merName.getText().toString());
			jsonObject.put("userId", TuanDuiShangHuActivity.username);
			jsonObject.put("questionType", "MTX");
			jsonObject.put("starteDate", starteDate.getText());
			jsonObject.put("endDate", endDate.getText());

			String state = "";
			if (checkState.getText().equals("审核中")) {
				state = "01";
			}
			if (checkState.getText().equals("待处理")) {
				state = "02";
			}
			if (checkState.getText().equals("处理中")) {
				state = "03";
			}
			if (checkState.getText().equals("已处理")) {
				state = "04";
			}
			if (checkState.getText().equals("结案")) {
				state = "05";
			}
			jsonObject.put("checkState", state);

			json = jsonObject.toString();
			Log.i("chengqk", "提交前(WifiStateChaxun )：" + json);
			techown.shanghu.https.Log.Instance().WriteLog("MTX0002的请求的json字符串："+json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog("WifiStateChaXun:Request1()"+e.getMessage());
		}
		String jsonstr = HttpConnection2
				.tbrequest2(json, "MTX0002");
		Log.i("chengqk", jsonstr);
		techown.shanghu.https.Log.Instance().WriteLog("MTX0002的返回的的json字符串："+jsonstr);
		if (jsonstr != null && !jsonstr.equals("")) {
			try {
				JSONObject j = new JSONObject(jsonstr);
				rspCode = j.getString("rspCode");
				Log.i("chengqk", rspCode);
				rspInfo = j.getString("rspInfo");
				if (rspCode.equals("000000")) {
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000000  记录数据成功  rspInfo:"+rspInfo);
					JSONArray array = j.getJSONArray("detail");
					if (array != null && !"".equals(array)) {
						for (int i = 0; i < array.length(); i++) {
							String[] s = new String[8];
							String sta = "";
							JSONObject o = array.getJSONObject(i);
							s[0] = o.getString("merName");
							s[1] = o.getString("createTime");
							s[2] = o.getString("questionDesc");
							String checkResult = o.getString("checkResult");
							if (checkResult.equals("00")) {
								s[3] = "需运营商处理";
							} else if(checkResult.equals("01")){
								s[3] = "误报";
							}else{
								s[3]="待确认";
							}

							s[4] = o.getString("inspectPostil");

							String aFaultType = o.getString("aFaultType");

							if (aFaultType.equals("00")) {
								sta = "平台问题";
							} else if (aFaultType.equals("01")) {
								sta = "线路问题";
							} else if (aFaultType.equals("02")) {
								sta = "终端问题";
							} else if (aFaultType.equals("03")) {
								sta = "施工问题";
							} else if (aFaultType.equals("04")) {
								sta = "用户原因";
							} else if (aFaultType.equals("05")) {
								sta = "不明原因";
							} else if (aFaultType.equals("06")) {
								sta = "网络拥塞";
							} else if (aFaultType.equals("07")) {
								sta = "其他";
							}
							s[5] = sta;

							s[6] = o.getString("dealContent");
							
							if(checkState.getText().toString().equals("结案")){
								s[7] = o.getString("updateTime");
							}else{
								s[7]="";
							}
							list.add(s);
						}
						// }
					}
					// Log.i("chengqk","----------------");
				}else if("000001".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000001  该服务不存在 rspInfo:"+rspInfo);
					rspInfo = "没有查询到相关的商户,请重新查询";
				}else if("000002".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000002  服务器响应超时 rspInfo:"+rspInfo);
					rspInfo = "服务响应超时,请重新查询";
				}else if("000003".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000003 参数错误 rspInfo:"+rspInfo);
					//rspInfo = "没有查询到相关的商户,请重新查询";
				}else if("000004".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000004  没有查询到相关商户  rspInfo"+rspInfo);
					rspInfo = "没有查询到相关的商户，请确认后重新查询";
				}else if("000005".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000005  数据处理错误  rspInfo"+rspInfo);
					rspInfo = "没有查询到相关的商户，请重新查询";
				}
			} catch (JSONException e) {
				//e.printStackTrace();
				techown.shanghu.https.Log.Instance().WriteLog("WifiStateChaXun:Request1()"+e.getMessage());
			}
		}

	}

	/**
	 * 跑批文件，获取提报的类型
	 * 
	 * @return
	 */
	/*
	 * private void getTbTypeBatch(){ MyDatabaseHelper myDatabaseHelper = null;
	 * SQLiteDatabase db = null; Cursor cursor = null; //String[] ques = null ;
	 * DES des = new DES(); try { Context friendContext =
	 * createPackageContext("techown.login", CONTEXT_IGNORE_SECURITY);
	 * myDatabaseHelper = new MyDatabaseHelper(friendContext,
	 * "techown_login.db", Constant.loginDBVer); db =
	 * myDatabaseHelper.getWritableDatabase();
	 * 
	 * String sql = "select batchNo from bsc_task_batchno";
	 * 
	 * cursor = db.rawQuery(sql, null);
	 * 
	 * if(cursor!=null){ tbType = new String[cursor.getCount()]; int i = 0;
	 * while(cursor.moveToNext()){ tbType[i] =
	 * des.jieMI(cursor.getString(cursor.getColumnIndex("batchNo"))); i++; }
	 * 
	 * StringBuilder sb = new StringBuilder(); for(int j = 0; j<tbType.length;
	 * j++){ sb.append(tbType[i]); } Log.i("chengqk","tbType数组："+sb.toString());
	 * 
	 * }
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * techown.shanghu
	 * .https.Log.Instance().WriteLog("商户wifi问题提报获取自查问题目跑批文件异常："+e
	 * .getMessage()); e.printStackTrace(); }finally{ if(cursor != null){
	 * cursor.close(); cursor = null; } if(db != null){ db.close(); db = null; }
	 * } }
	 */
	public boolean validate() {
		try {
			/*
			 * if (statetexttype.getText().toString().equals("")) {
			 * //Toast.makeText(YuShen.this, "请输入商户名！",
			 * Toast.LENGTH_LONG).show(); log.Toast(WifiStateChaXun.this,
			 * "请选择提报类型！"); return false; }
			 
			if (merName.getText().toString().equals("")) {
			
				log.Toast(WifiStateChaXun.this, "请输入商户名！");
				return false;
			}*/
			if (starteDate.getText().toString().equals("")) {
				
				log.Toast(WifiStateChaXun.this, "请输入提报开始时间！");
				return false;
			}
			if (endDate.getText().toString().equals("")) {
			
				log.Toast(WifiStateChaXun.this, "请输入提报结束时间！");
				return false;
			}
			if (checkState.getText().toString().equals("")) {
			
				log.Toast(WifiStateChaXun.this, "请输入当前状态");
				return false;
			}
			String starttime = starteDate.getText().toString().replace("-", "");
			String endtime = endDate.getText().toString().replace("-", "");

			if (Long.parseLong(starttime) > Long.parseLong(endtime)) {
				log.Toast(WifiStateChaXun.this, "提报开始时间不能早于结束时间！");
				return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.Toast(WifiStateChaXun.this, "输入内容错误，请确认后重新提交 ！");
			return false;
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(WifiStateChaXun.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

}