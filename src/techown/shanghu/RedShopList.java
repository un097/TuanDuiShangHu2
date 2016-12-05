package techown.shanghu;
/*
 * 总部活动--门店列表
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.biaoge.MyButton;
import techown.shanghu.https.HttpConnection2;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RedShopList extends Activity {

	public Map<String, String[]> map = new HashMap<String, String[]>();
	public static StringBuilder para = new StringBuilder();
	public int numb = 10;
	String bb;
	ListAdapter1 adapter;
	private Dialog mDialog;
	EditDialog log=new EditDialog();
	List<String[]> list = new ArrayList<String[]>();
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
				pagenum.setText(pageNo+"/"+pageCnt);
				adapter.notifyDataSetChanged();
				adapter.notifyDataSetInvalidated();
				break;
			}
		}
	};
	// lxp
	private Button  shouye_xd, shangye_xd, xiaye_xd, moye_xd,
			fanhui_button;
	ListView mainright_lv;
	LinearLayout rightLinearLayout;

	int j = 0;

	String pageCnt;
	String pageNo;
	TextView pagenum;
	public static String mendianName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mengdianliebiao);

		ZhongBuHuoDong1.requeststr1 = null;
		mDialog = new AlertDialog.Builder(RedShopList.this).create();
		// init();
		pagenum = (TextView) findViewById(R.id.pagenum);
		rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);

		list.clear();
		rightLinearLayout.removeAllViews();
		LinearLayout view = (LinearLayout) LayoutInflater
				.from(RedShopList.this).inflate(R.layout.mendianitemlist, null);

		rightLinearLayout.addView(view);

		// lxp 将list中的值赋值给messages数组
		init();
		pagenum.setText(pageNo + "/" + pageCnt);
		mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);
		adapter = new ListAdapter1(RedShopList.this, list);
		mainright_lv.setAdapter(adapter);
		// }
		shouye_xd = (Button) findViewById(R.id.shouye_xd);
		shangye_xd = (Button) findViewById(R.id.shangye_xd);
		xiaye_xd = (Button) findViewById(R.id.xiaye_xd);
		moye_xd = (Button) findViewById(R.id.moye_xd);
		fanhui_button = (Button) findViewById(R.id.fanhui_button);
		MainButtonListener lis = new MainButtonListener();

		shouye_xd.setOnClickListener(lis);
		shangye_xd.setOnClickListener(lis);
		xiaye_xd.setOnClickListener(lis);
		moye_xd.setOnClickListener(lis);
		fanhui_button.setOnClickListener(lis);
	}

	class MainButtonListener implements OnClickListener {
		Intent intent = getIntent();

		public void onClick(View v) {
			int v_id = v.getId();
			switch (v_id) {
			case R.id.shouye_xd:// 首页
				if (Integer.parseInt(pageNo) == 1) {
					log.Toast(RedShopList.this, "已经是首页！");
				} else {
					pageData("1");

				}

				break;
			case R.id.shangye_xd:// 上一页
				if (Integer.parseInt(pageNo) > 1) {
					pageData(String.valueOf(Integer.parseInt(pageNo) - 1));

				} else {
					log.Toast(RedShopList.this, "已经是首页！");
				}

				break;
			case R.id.xiaye_xd:// 下一页
				if (Integer.parseInt(pageNo) == Integer.parseInt(pageCnt)) {
					log.Toast(RedShopList.this, "已经是尾页！");
				} else {
					pageData(String.valueOf(Integer.parseInt(pageNo) + 1));

				}
				break;
			case R.id.moye_xd:// 末页
				if (pageCnt.equalsIgnoreCase(pageNo)) {
					log.Toast(RedShopList.this, "已经是尾页！");
				} else {
					pageData(pageCnt);

				}

				break;
			case R.id.fanhui_button:
				intent.setClass(RedShopList.this, RedCompanyMessage.class);
				RedShopList.this.startActivity(intent);
				RedShopList.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
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
						"", "", "", "N", "" };

				str[2] = RedBigArea.tempstr1[0];

				str[9] = TuanDuiShangHuActivity.username;

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
							pageCnt = demoJson1.getString("pageCnt");
							pageNo = demoJson1.getString("pageNo");
							for (int j = 0; j < numberList1.length(); j++) {
								JSONObject datajson = (JSONObject) numberList1
										.opt(j);
								String[] tempstr = new String[10];
								tempstr[0] = datajson.getString("merName");
								tempstr[1] = datajson.getString("companyName");
								tempstr[2] = datajson.getString("merAddress");
								tempstr[3] = datajson.getString("licences");
								tempstr[4] = datajson.getString("merId");
								tempstr[5] = datajson.getString("actId");
								tempstr[6] = datajson.getString("activeMode");
								tempstr[7] = datajson.getString("agreementId");
								tempstr[8] = datajson.getString("auditState");
								tempstr[9] = datajson.getString("auditReason");

								list.add(tempstr);
							}
							Message msg3 = new Message();
							msg3.what = 2;
							handler.sendMessage(msg3);

						} else if (demoJson1.getString("rspCode").equals("10")) {
							Message msg = new Message();
							Bundle b = new Bundle();
							b.putString("creat", demoJson1.getString("rspInfo"));
							msg.setData(b);
							msg.what = 3;
							handler.sendMessage(msg);//

						} else {
							Message msg = new Message();
							Bundle b = new Bundle();
							b.putString("throwsmessage",
									demoJson1.getString("rspInfo"));
							msg.setData(b);
							msg.what = 2;
							handler.sendMessage(msg);//

						}
					} catch (Exception e) {

						techown.shanghu.https.Log.Instance().WriteLog("团队：MendianList"+e.getMessage());
					}
				}

			}
		}.start();
	}

	private void init() {
		// TODO Auto-generated method stub
		list.clear();
		try {
			JSONObject demoJson1 = new JSONObject(RedBigArea.requeststr2);

			if (demoJson1.getString("rspCode").equals("00")) {
				JSONArray numberList1 = demoJson1.getJSONArray("detail");
				pageCnt = demoJson1.getString("pageCnt");
				pageNo = demoJson1.getString("pageNo");
				for (int j = 0; j < numberList1.length(); j++) {
					JSONObject datajson = (JSONObject) numberList1.opt(j);
					String[] tempstr = new String[10];
					tempstr[0] = datajson.getString("merName");
					tempstr[1] = datajson.getString("companyName");
					tempstr[2] = datajson.getString("merAddress");
					tempstr[3] = datajson.getString("licences");
					tempstr[4] = datajson.getString("merId");
					tempstr[5] = datajson.getString("actId");
					tempstr[6] = datajson.getString("activeMode");
					tempstr[7] = datajson.getString("agreementId");
					tempstr[8] = datajson.getString("auditState");
					tempstr[9] = datajson.getString("auditReason");

					list.add(tempstr);
				}
			} else {
				Toast.makeText(RedShopList.this,
						demoJson1.getString("rspInfo"), Toast.LENGTH_LONG)
						.show();
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：MendianList"+e.getMessage());
		}

	}

	int checknum;

	class ListAdapter1 extends BaseAdapter {
		public List<String[]> mItemList; // 修饰符不能是private
		public Context mContext;

		public ListAdapter1(Context con, List<String[]> itemList) {
			mItemList = itemList;
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
			LayoutInflater inflater = LayoutInflater.from(RedShopList.this);
			view = inflater.inflate(R.layout.redshanghuxiadan2list2, null);
			}
			LinearLayout bgsrc = (LinearLayout) view.findViewById(R.id.bgsrc);

			TextView listtable_tv1 = (TextView) view
					.findViewById(R.id.listtable_tv1);
			TextView listtable_tv2 = (TextView) view
					.findViewById(R.id.listtable_tv2);
			TextView listtable_tv3 = (TextView) view
					.findViewById(R.id.listtable_tv3);
			listtable_tv1.setText(mItemList.get(position)[0]);
			listtable_tv2.setText(mItemList.get(position)[1]);
			listtable_tv3.setText(mItemList.get(position)[2]);

			listtable_tv1.setTextColor(Color.BLACK);
			listtable_tv2.setTextColor(Color.BLACK);
			listtable_tv3.setTextColor(Color.BLACK);

			if (position % 2 == 0) {
				bgsrc.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.a21));
			}else{
				bgsrc.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.mainetbg));
			}
			
			MyButton listbutton = (MyButton) view
					.findViewById(R.id.shanghuxiadan);
			listbutton.itemposition = position;

			// //影藏Button，0显示，4影藏，8删除

			listbutton.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					MyButton myButton = (MyButton) arg0;
					checknum = myButton.itemposition;
					// TODO Auto-generated method stub
					
					String[] activeModestr = list.get(checknum)[6].split("\\|");
					String activeid="";
					
					for (int i = 0; i < activeModestr.length; i++) {
						if(activeModestr[i].equals("H"))
						{
							activeid = activeModestr[i];
						}
					}
					if(activeid!=null&&!activeid.equals("H"))
					{
						final String merId = list.get(checknum)[4];
						final String actId = list.get(checknum)[5];
						final Intent i = new Intent();
						Message msg = new Message();
						msg.what = 1;
						handler.sendMessage(msg);//
						new Thread() {
							String[] str1 = new String[] { "", "" };
							public void run() {

								
								str1[0] = merId;
								str1[1] = actId;

								String json1 = "{" + "\"merId\":\"" + str1[0]
										+ "\"," + "\"actId\":\"\"}";
								RedShopMessage.requeststr1 = HttpConnection2
										.HttpRequest(HttpConnection2.getHttpCon(
												TuanDuiShangHuActivity.indenty,
												"A0010"), json1);
								if (RedShopMessage.requeststr1 != null
										&& RedShopMessage.requeststr1.length() > 0) {
									try {
										JSONObject demoJson1 = new JSONObject(
												RedShopMessage.requeststr1);
										if (demoJson1.getString("rspCode").equals(
												"00")) {
											mDialog.dismiss();
											i.setClass(RedShopList.this,
													RedShopMessage.class);
											RedShopList.this.startActivity(i);
											overridePendingTransition(R.anim.leftin, R.anim.leftout);
											RedShopMessage.zhi="shang";
											Constant.isShopCreate = false;
											RedShopMessage.merid = merId;
											RedShopList.this.finish();
											
											
										} else if (demoJson1.equals("")) {
											Message msg = new Message();
											Bundle b = new Bundle();
											b.putString(
													"throwsmessage",
													"服务器连接超时，请重新点击下一步查询！"
															+ demoJson1
																	.getString("rspInfo"));
											msg.setData(b);
											msg.what = 4;
											handler.sendMessage(msg);//
											mDialog.dismiss();
										} else {
											Message msg = new Message();
											Bundle b = new Bundle();
											b.putString("throwsmessage",
													demoJson1.getString("rspInfo"));
											msg.setData(b);
											msg.what = 3;
											handler.sendMessage(msg);//
											mDialog.dismiss();
										}
									} catch (Exception e) {
										// TODO Auto-generated catch block
										techown.shanghu.https.Log.Instance().WriteLog("团队：MendianList"+e.getMessage());
									}

								} else {
									Message msg = new Message();
									Bundle b = new Bundle();
									msg.setData(b);
									msg.what = 5;
									handler.sendMessage(msg);//
									mDialog.dismiss();
								}
							}
						}.start();
					}
					else
					{
						log.Toast(RedShopList.this, "您选择的该商户门店已拓展，请重新选择！");
					}
				}
			});

			return view;
		}
	}
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				log.ExitApp(RedShopList.this);
				break;

			}
			return super.onKeyDown(keyCode, event);
		}  	  

}
