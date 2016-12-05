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
/**
 * 商户名称地址列表
 * @author pccc
 *
 */
public class SHGongDanWeiHu extends Activity {

	private ListView listView;
	private List<String[]> arrlist = new ArrayList<String[]>();;
	private MyAdapter adapter;
	private Button btn_return;
	static String jsonStr;
	public static String num;
	DBUtil DB = new DBUtil();
	private Dialog mDialog;
	EditDialog ds = new EditDialog();
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
				ds.Toast(SHGongDanWeiHu.this, b.getString("throwsmessage"));
				break;
			case 4:
				Bundle bb = msg.getData();
				b = msg.getData();
				listView.setAdapter(adapter);
				ds.Toast(SHGongDanWeiHu.this, b.getString("throwsmessage"));
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
		setContentView(R.layout.shgongdanweihu);

		mDialog = new AlertDialog.Builder(SHGongDanWeiHu.this).create();// 连接网络进程
		getData();

		listView = (ListView) findViewById(R.id.lv_shgdwh);
		adapter = new MyAdapter(SHGongDanWeiHu.this, arrlist);
		listView.setAdapter(adapter);
		btn_return = (Button) findViewById(R.id.button_return);
		btn_return.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(SHGongDanWeiHu.this,
						TuanDuiShangHuActivity.class);
				SHGongDanWeiHu.this.startActivity(intent);
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
				SHGongDanWeiHu.this.finish();

			}
		});

	}

	private void getData() {
		try {
			arrlist.clear();
			JSONObject json = new JSONObject(jsonStr);
			String rspCode = json.optString("rspCode");
			if (rspCode.equals("00")) {

				JSONArray numberList1 = json.getJSONArray("detail");
				for (int j = 0; j < numberList1.length(); j++) {
					JSONObject datajson = (JSONObject) numberList1.opt(j);
					String[] tempstr = new String[11];
					tempstr[0] = datajson.optString("taskId");// 工单ID
					tempstr[1] = datajson.optString("taskType");// 工单类型
					tempstr[2] = datajson.optString("merName");// 商户名称
					tempstr[3] = datajson.optString("merId");// 商户ID
					tempstr[4] = datajson.optString("address");// 商户地址
					tempstr[5] = datajson.optString("beginDate");// 派发日期
					tempstr[6] = datajson.optString("endTime");// 截止日期
					tempstr[7] = datajson.optString("taskState");// 工单状态
					
					arrlist.add(tempstr);

				}
				/*String[] temp1 = new String[]{"1111","","没有商编","merId001","松涛路80号001","20116","5411","3333"};
				String[] temp2 = new String[]{"2222","","没有SN","merId001","松涛路80号002","20116","5411","3333"};
				String[] temp3 = new String[]{"3333","","没有商编和SN","merId001","松涛路80号003","20116","5411","3333"};
				String[] temp4 = new String[]{"4444","","完整的","merId001","松涛路80号004","20116","5411","3333"};
			
				arrlist.add(temp1);
				arrlist.add(temp2);
				arrlist.add(temp3);
				arrlist.add(temp4);*/
			
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog(
					"SHGongDanWeiHu:" + e.getMessage());
		}
	}

	class MyAdapter extends BaseAdapter {

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
				convertView = LayoutInflater.from(SHGongDanWeiHu.this).inflate(
						R.layout.shgongdanweihu_item, null);

				holder.tv_shAddress = (TextView) convertView
						.findViewById(R.id.shgd_shaddress);
				holder.tv_shName = (TextView) convertView
						.findViewById(R.id.shgd_shname);

				holder.btn_select = (Button) convertView
						.findViewById(R.id.shgd_btn_select);

				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();

			}

			holder.tv_shName.setText(((list.get(position))[2]));

			holder.tv_shAddress.setText(list.get(position)[4]);
			
			holder.btn_select.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
					new Thread() {
						public void run() {

							String taskId = list.get(position)[0];// 工单Id
							String Jsonstr = "{\"taskId\":\"" + taskId + "\"}";
							
							SHGongDanWeiHu_2.requeststr1 = HttpConnection2.shgdSerach(Jsonstr, "mmsTaskRequest");
							//测试数据
							/*if("1111".equals(taskId)){
								SHGongDanWeiHu_2.requeststr1 = "{\"rspCode\":\"000000\",\"taskId\":\"00001111000\"," +
										"\"merTel\":\"0212545487\",\"MerAddress\":\"浦东新区八佰伴商圈\"," +
										"\"MerId\":\"22152\",\"MerName\":\"沪上酒楼\"," +
										"\"misInfoList\":\"\","+
										"\"MerSn\":\"254125;554455\"}";
							
							}else if("2222".equals(taskId)){
								SHGongDanWeiHu_2.requeststr1 = "{\"rspCode\":\"000000\",\"taskId\":\"00001111000\"," +
										"\"merTel\":\"0212545487\",\"MerAddress\":\"浦东新区八佰伴商圈\"," +
										"\"MerId\":\"22152\",\"MerName\":\"沪上酒楼\"," +
										"\"misInfoList\":\"25145541,5555;5412541111,5845454;2154551,685442\","+
										"\"MerSn\":\"\"}";
							
							}
							else if("3333".equals(taskId)){
								SHGongDanWeiHu_2.requeststr1 = "{\"rspCode\":\"000000\",\"taskId\":\"00001111000\"," +
										"\"merTel\":\"0212545487\",\"MerAddress\":\"浦东新区八佰伴商圈\"," +
										"\"MerId\":\"22152\",\"MerName\":\"沪上酒楼\"," +
										"\"misInfoList\":\"\","+
										"\"MerSn\":\"\"}";
							
							}else if("4444".equals(taskId)){
								SHGongDanWeiHu_2.requeststr1 = "{\"rspCode\":\"000000\",\"taskId\":\"00001111000\"," +
										"\"merTel\":\"0212545487\",\"MerAddress\":\"浦东新区八佰伴商圈\"," +
										"\"MerId\":\"22152\",\"MerName\":\"沪上酒楼\"," +
										"\"misInfoList\":\"25145541,5555;5412541111,5845454;2154551,685442\","+
										"\"MerSn\":\"254125;554455\"}";
							
							}
							*/
							
							techown.shanghu.https.Log.Instance().WriteLog("商户工单维护查询返回数据：" + SHGongDanWeiHu_2.requeststr1);

							if (SHGongDanWeiHu_2.requeststr1 != null) {

								JSONObject demoJson1;
								try {
									demoJson1 = new JSONObject(SHGongDanWeiHu_2.requeststr1);
									if (demoJson1.optString("rspCode").equals(
											"000000")) {
										if(IsTableExists("shgongdanweihu")){
											Query(SHGongDanWeiHu.this);
											
											DB.insertContactId31(SHGongDanWeiHu.this, "Id", num);
											String id = list.get(position)[0] + num;
											DB.upload31(SHGongDanWeiHu.this, new String[] {
													"taskId","taskId2", "StName" ,"userName"},
													new String[] { list.get(position)[0], id ,list.get(position)[2], TuanDuiShangHuActivity.username}, num);
										
											techown.shanghu.https.Log.Instance().WriteLog("选择商户工单维护,工单ID："+list.get(position)[0]+" 商户名称："+list.get(position)[2]);
											Intent i = new Intent();
											i.setClass(SHGongDanWeiHu.this, SHGongDanWeiHu_1.class);
											SHGongDanWeiHu.this.startActivity(i);
											SHGongDanWeiHu.this.finish();
											overridePendingTransition(R.anim.leftin, R.anim.leftout);
											
											mDialog.dismiss();
										}else{
											Message msg = new Message();
											Bundle b = new Bundle();
											b.putString("throwsmessage", "对应的数据库表名不存在，请进行前端解绑操作");
											msg.setData(b);
											msg.what = 2;
											handler.sendMessage(msg);//
											mDialog.dismiss();
										}
										
									} else if (demoJson1.equals("")) {
										Message msg = new Message();
										Bundle b = new Bundle();
										b.putString(
												"throwsmessage",
												"服务器连接超时，请重新点击！"
														+ demoJson1
																.optString("rspInfo"));
										msg.setData(b);
										msg.what = 2;
										handler.sendMessage(msg);//
										mDialog.dismiss();
									} else {
										Message msg = new Message();
										Bundle b = new Bundle();
										b.putString("throwsmessage", "查询失败,"
												+ demoJson1.optString("rspInfo"));
										msg.setData(b);
										msg.what = 2;
										handler.sendMessage(msg);//
										mDialog.dismiss();
									}
								} catch (Exception e) {
									techown.shanghu.https.Log.Instance()
											.WriteLog(
													"商户工单维护查询出错:"
															+ e.getMessage());
								}
							} else {
								Message msg = new Message();
								Bundle b = new Bundle();
								b.putString("throwsmessage", "连接服务器超时，请重新请求！");
								msg.setData(b);
								msg.what = 2;
								handler.sendMessage(msg);//
								mDialog.dismiss();
							}
						}
					}.start();
				}
			});
			return convertView;
		}
	}

	private class ViewHolder {

		TextView tv_shName;
		TextView tv_shAddress;
		Button btn_select;
	}
	
	//判断是否存在表
	public boolean IsTableExists(String tabName){
        boolean result = false;
        if(tabName == null){
                return false;
        }
        Cursor cursor = null;
       SQLiteDatabase db = null;
        try {
        	 MyDatabaseHelper myHelper = new MyDatabaseHelper(SHGongDanWeiHu.this, "techown.db",
     				Constant.tdshDBVer);
     		db = myHelper.getWritableDatabase();
            String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"+tabName.trim()+"' ";
            cursor = db.rawQuery(sql, null);
            if(cursor.moveToNext()){
                int count = cursor.getInt(0);
                if(count>0){
                    result = true;
                }
            }
                
        } catch (Exception e) {
                // TODO: handle exception
        }
        finally{
        	if(cursor != null)
        		cursor.close();
        }
        return result;
	}

	// 返回数据表里工单id+1
	public void Query(Context con) {
		SQLiteDatabase db = null;
		Cursor cur = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query("shgongdanweihu", new String[] { "Id" }, null,
					null, null, null, null);

			if (cur.getCount() == 0) {
				num = "td1";
				System.out.println("IDnum===" + num);
				techown.shanghu.https.Log.Instance().WriteLog(
						"商户工单维护ID num===" + num);
			} else {
				while (cur.moveToNext()) {
					if (cur.isLast()) {
						String[] numarrg = cur.getString(
								cur.getColumnIndex("Id")).split("d");
						num = "td"
								+ String.valueOf(Long.parseLong(numarrg[1]) + 1);
						techown.shanghu.https.Log.Instance().WriteLog(
								"商户工单维护ID num===" + num);
					}
				}
			}

		} catch (Exception e) {
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:

			Intent intent = new Intent(SHGongDanWeiHu.this,
					TuanDuiShangHuActivity.class);
			SHGongDanWeiHu.this.startActivity(intent);
			overridePendingTransition(R.anim.rightin, R.anim.rightout);
			SHGongDanWeiHu.this.finish();

			break;
		}
		return super.onKeyDown(keyCode, event);

	};

}
