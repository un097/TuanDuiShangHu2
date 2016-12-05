package techown.shanghu;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.GetDate;
import techown.shanghu.https.HttpConnection2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenUnusualGongDan extends Activity {

	private ListView listView;
	private List<String[]> arrlist = new ArrayList<String[]>();;
	private MyAdapter adapter;
	private Button btn_return;
	static String jsonStr;
	public static String num;
	DBUtil DB=new DBUtil();
	private Dialog mDialog;
	EditDialog ds = new EditDialog();
	private String taskJson ;
	private String rspCode;
	private String rspInfo;
	
	
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
			case 2:
				Bundle b = msg.getData();
				b = msg.getData();
				ds.Toast(MenUnusualGongDan.this,
						b.getString("throwsmessage"));
				break;
			case 4:
				Bundle bb = msg.getData();
				b = msg.getData();
				listView.setAdapter(adapter);
				ds.Toast(MenUnusualGongDan.this,
						b.getString("throwsmessage"));
				break;
			case 3:
				mDialog.dismiss();
				break;
			default:
        		break;
			}
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.unusualgongdan);

		mDialog = new AlertDialog.Builder(MenUnusualGongDan.this)
				.create();// 连接网络进程
		getDate();

		listView = (ListView) findViewById(R.id.lv_ununualgd);
		adapter = new MyAdapter(MenUnusualGongDan.this, arrlist);
		listView.setAdapter(adapter);
		btn_return = (Button) findViewById(R.id.button_return);
		btn_return.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MenUnusualGongDan.this,
						TuanDuiShangHuActivity.class);
				MenUnusualGongDan.this.startActivity(intent);
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
				MenUnusualGongDan.this.finish();

			}
		});

	}

	private void getDate() {
		try {
			JSONObject j = new JSONObject(jsonStr);
			String rspCode = j.optString("rspCode");
			if (rspCode.equals("000000")) {

				JSONArray array = j.optJSONArray("detail");
				for (int i = 0; i < array.length(); i++) {
					String[] s = new String[10];
					JSONObject o = array.optJSONObject(i);
					s[0] = o.optString("startDate");// 开始日期
					s[1] = o.optString("endDate");// 结束日期
					s[2] = o.optString("requiredEndDate");// 要求完成日期
					s[3] = o.optString("merId");// 商户Id
					s[4] = o.optString("merName");// 商户名
					s[5] = o.optString("merCity");// 商户城市
					s[6] = o.optString("merAddress");// 商户地址
					s[7] = o.optString("repeopName");// 维护人姓名
					s[8] = o.optString("taskId");// 工单Id
					s[9] = o.optString("taskCount");// 工单生成次数
					arrlist.add(s);
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog(
					"MenUnusualGongdan:" + e.getMessage());
		}
	}

	class MyAdapter extends BaseAdapter {

		private int temp = -1;

		Activity activity;
		List<String[]> list = new ArrayList<String[]>();;

		public MyAdapter(Activity context, List list) {
			this.activity = context;
			this.list = list;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			if (list != null & list.size() > 0) {
				return list.size();
			}
			return 0;
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();
				convertView = LayoutInflater.from(MenUnusualGongDan.this)
						.inflate(R.layout.ununualgongdan_item, null);

				holder.radioButton = (RadioButton) convertView
						.findViewById(R.id.appgd_mycheckbox);
				holder.tv_shAddress = (TextView) convertView
						.findViewById(R.id.appgd_shaddress);
				holder.tv_shName = (TextView) convertView
						.findViewById(R.id.appgd_shname);
				holder.tv_startTime = (TextView) convertView
						.findViewById(R.id.appgd_starttime);
				holder.tv_endTime = (TextView) convertView
						.findViewById(R.id.appgd_endtime);
				holder.tv_needTime = (TextView) convertView
						.findViewById(R.id.appgd_needtime);
				holder.btn_del = (Button) convertView
						.findViewById(R.id.appgd_btn_select);
				holder.radioButton.setChecked(false);

				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();

			}

			holder.tv_shName.setText(((list.get(position))[4]));

			holder.tv_shAddress.setText(list.get(position)[6]);

			holder.tv_startTime.setText(list.get(position)[0]);

			holder.tv_endTime.setText(list.get(position)[1]);

			holder.tv_needTime.setText(list.get(position)[2]);

			final Button btn = (Button) convertView
					.findViewById(R.id.appgd_btn_select);
			holder.radioButton.setId(position);
			holder.radioButton
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							// TODO Auto-generated method stub
							if (isChecked) {
								/*
								 * 把上次选择的取消
								 */
								if (temp != -1) {
									RadioButton tempBtn = (RadioButton) activity
											.findViewById(temp);
									if (tempBtn != null) {
										tempBtn.setChecked(false);
									}
								}
								temp = buttonView.getId();

								btn.setVisibility(View.VISIBLE);
								btn.setClickable(true);
							} else {
								btn.setVisibility(View.INVISIBLE);
								btn.setClickable(false);
							}
						}
					});
			if (temp == position) {
				holder.radioButton.setChecked(true);

			} else {
				holder.radioButton.setChecked(false);

			}

			holder.btn_del.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub

					AlertDialog.Builder ab = new AlertDialog.Builder(
							MenUnusualGongDan.this);
					ab.setTitle("提示");
					ab.setMessage("是否确认提交");
					ab.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
					ab.setPositiveButton// 为对话框设置按钮
					("确定", new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {

							//提交操作
							final String taskId = list.get(position)[8];//工单Id
							
							/*
							 * 文件方式提交工单
							 * 
							 * Query(MenUnusualGongDan.this);
							DB.insertContactId31(MenUnusualGongDan.this, "Id", num);
							String id = Id + num;
							
							String time=GetDate.getDate().toString();
							String[] columnName = {"taskId","StName","TaskId2","DCL","LX","TIME","MaintainUserId","SubmitTime"}; 
							String[] columnStr = {Id,list.get(position)[4],id,"5","门店进件异常工单",time,list.get(position)[7],time};
							DB.upload31(MenUnusualGongDan.this, columnName,columnStr, num);
							
							DB.sendinsert(MenUnusualGongDan.this, new String[] {
						    			MyDatabaseHelper.SEND_BarcodeNo,
										MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
										new String[] { id, "1", "0" });
							*/
							
							
							
							
							Message msg = new Message();
							msg.what = 1;
							handler.sendMessage(msg);
							new Thread() {
								public void run() {
									try{
										requestTaskId(taskId);
									}catch(Exception e){
										techown.shanghu.https.Log.Instance().WriteLog("YuShen:Request1()" + e.getMessage());
									}
									if(rspCode!=null&&!rspCode.equals(""))
									{
										if (rspCode.equals("000000")) {
											//删除 后刷新
											list.remove(position);
											adapter = new MyAdapter(MenUnusualGongDan.this,
													list);
											mDialog.dismiss();
											Message msg = new Message();   
											Bundle b = new Bundle();
											b.putString("throwsmessage","提交成功");
											msg.setData(b);
									        msg.what =4; 
											handler.sendMessage(msg);//
										}else{
											Message msg = new Message();   
											Bundle b = new Bundle();
											b.putString("throwsmessage","提交失败,"+rspInfo);
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
										b.putString("throwsmessage","连接服务器超时，请重新提交！");
										msg.setData(b);
								        msg.what =2; 
										handler.sendMessage(msg);//
										mDialog.dismiss();
									}
								}
							}.start();
							
							
						}
					});
					ab.create().show();

				}
			});
			return convertView;
		}

	}
	private void requestTaskId(String taskId) {
		// TODO Auto-generated method stub
		String Jsonstr = "{\"taskId\":\""+taskId+"\"}";

		taskJson = HttpConnection2.ycgdRequest2(Jsonstr, "sjfkTaskRecvRequest");
		techown.shanghu.https.Log.Instance().WriteLog(taskJson);
		if(taskJson!=null&&!taskJson.equals("")){
			JSONObject json;
			try {
				json = new JSONObject(taskJson);
				rspCode = json.optString("rspCode");
				rspInfo= json.optString("rspInfo");
				/*if("000001".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000001  该服务不存在 rspInfo:"+rspInfo);
					rspInfo = "该服务不存在";
				}else if("000002".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000002  服务器响应超时 rspInfo:"+rspInfo);
					rspInfo = "服务响应超时";
				}else if("000003".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000003 参数错误 rspInfo:"+rspInfo);
					rspInfo = "参数错误";
				}else if("000004".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000004  没有结果返回  rspInfo"+rspInfo);
					rspInfo = "没有结果返回";
				}else if("000005".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000005  数据处理错误  rspInfo"+rspInfo);
					rspInfo = "数据处理错误";
				}else if("000010".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000005  数据处理错误  rspInfo"+rspInfo);
					rspInfo = "工单异常";
				}*/
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private class ViewHolder {

		TextView tv_shName;
		TextView tv_shAddress;
		TextView tv_startTime;
		TextView tv_endTime;
		TextView tv_needTime;
		Button btn_del;
		RadioButton radioButton;
	}
	/*public  void Query(Context con) {
		SQLiteDatabase db = null;
		Cursor cur = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query("carderrorgongdan", new String[] { "Id" }, null,
					null, null, null, null);

			if (cur.getCount() == 0) {
				num = "td1";
				System.out.println("IDnum==="+num);
				techown.shanghu.https.Log.Instance().WriteLog("门店进件异常工单IDnum==="+num);
			} else {
				while (cur.moveToNext()) {
					if (cur.isLast()) {
						String[] numarrg = cur.getString(cur.getColumnIndex("Id")).split("d");
						num = "td"+String.valueOf(Long.parseLong(numarrg[1]) + 1);
						techown.shanghu.https.Log.Instance().WriteLog("门店进件异常工单IDnum==="+num);
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
	}*/

	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:

			Intent intent = new Intent(MenUnusualGongDan.this,
					TuanDuiShangHuActivity.class);
			MenUnusualGongDan.this.startActivity(intent);
			overridePendingTransition(R.anim.rightin, R.anim.rightout);
			MenUnusualGongDan.this.finish();

			break;
		}
		return super.onKeyDown(keyCode, event);

	};

}
