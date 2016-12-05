package techown.shanghu;

/*
 * ���� 14:18:04
 06 ��ͨ��  
 01 �������  
 02 �ͷ����  
 03 ͨ��  
 05 ����  
 04 �ܼ�  
 07 �������  
 08 �������  
 10 ������  
 11 ������� 
 ���� 14:18:10
 12 ���߾ܾ�  
 13 Ԥ��ʱ  
 14 ������˳�ʱ  
 15 ������˳�ʱ  
 16 ������˳�ʱ  
 17 �ͷ���˳�ʱ  
 18 ������˳�ʱ
 */

/*
 * �̻���ѯ 
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.biaoge.MyButton;
import techown.shanghu.date.DES;
import techown.shanghu.date.TCity;
import techown.shanghu.https.HttpConnection2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShangHuChaXun extends Activity {

	ListAdapter1 listAdapter;
	DBUtil DB = new DBUtil();
	public static String jsont;
	private Button button, button1;
	ListView mainright_lv, lv;
	LinearLayout rightLinearLayout;
	TextView yedtext1;
	public static String Id, actId;
	int j = 0;
	public static String cityid;
	EditDialog log = new EditDialog();
	public static int a;
	DES des = new DES();
	// lxp
	private Dialog mDialog;
	private Button shouye_xd, shangye_xd, xiaye_xd, moye_xd, fanhui_button;
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
				Bundle b = msg.getData();
				log.Toast(ShangHuChaXun.this, b.getString("throwsmessage"));
				Intent intent = new Intent();
				intent.setClass(ShangHuChaXun.this, HeYueQianDing_21.class);
				ShangHuChaXun.this.startActivity(intent);
				ShangHuChaXun.this.finish();
				break;
			case 3:
				b = msg.getData();
				log.Toast(ShangHuChaXun.this, b.getString("throwsmessage"));
				intent = new Intent();
				intent.setClass(ShangHuChaXun.this, HeYueQianDing_21.class);
				ShangHuChaXun.this.startActivity(intent);
				ShangHuChaXun.this.finish();
				break;
			case 4:
				b = msg.getData();
				log.Toast(ShangHuChaXun.this, b.getString("throwsmessage"));
				intent = new Intent();
				break;
			case 5:
				b = msg.getData();
				log.Toast(ShangHuChaXun.this, b.getString("throwsmessage"));
				intent = new Intent();
				intent.setClass(ShangHuChaXun.this, HeYueQianDing_21.class);
				ShangHuChaXun.this.startActivity(intent);
				ShangHuChaXun.this.finish();
				break;

			case 6:
				pagenum.setText(pageNo + "/" + pageCnt);
				listAdapter.notifyDataSetChanged();
				listAdapter.notifyDataSetInvalidated();
				break;

			case 7:
				b = msg.getData();
				pagenum.setText(pageNo + "/" + pageCnt);
				listAdapter.notifyDataSetChanged();
				listAdapter.notifyDataSetInvalidated();
				log.Toast(ShangHuChaXun.this, b.getString("throwsmessage"));
				break;
			default:
				break;
			}
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shanghuchaxun);
		mDialog = new AlertDialog.Builder(ShangHuChaXun.this).create();

		a = 0;

		/*List<TCity> tcity = DB.cityQuerycity(ShangHuChaXun.this, "t_city",
				" where Name = '" + des.jiaMi(TuanDuiShangHuActivity.cityname)
						+ "'");
		cityid = tcity.get(0).id;*/

		cityid = TuanDuiShangHuActivity.cityId;
		button = (Button) findViewById(R.id.button2);
		button1 = (Button) findViewById(R.id.button);
		yedtext1 = (TextView) findViewById(R.id.yedtext1);

		pagenum = (TextView) findViewById(R.id.pagenum);
		shouye_xd = (Button) findViewById(R.id.shouye_xd);
		shangye_xd = (Button) findViewById(R.id.shangye_xd);
		xiaye_xd = (Button) findViewById(R.id.xiaye_xd);
		moye_xd = (Button) findViewById(R.id.moye_xd);
		MainButtonListener lis = new MainButtonListener();

		shouye_xd.setOnClickListener(lis);
		shangye_xd.setOnClickListener(lis);
		xiaye_xd.setOnClickListener(lis);
		moye_xd.setOnClickListener(lis);

		rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);

		yedtext1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog(ShangHuChaXun.this, rightLinearLayout, yedtext1,
						R.id.yedtext1, "�������̻���");
			}
		});

		list.clear();
		rightLinearLayout.removeAllViews();
		LinearLayout view = (LinearLayout) LayoutInflater.from(
				ShangHuChaXun.this).inflate(R.layout.faxingxiang_list1, null);

		rightLinearLayout.addView(view);
		try {
			Request();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog(
					"ShangHuChaXun��request()" + e.getMessage());
		}
		pagenum.setText(pageNo + "/" + pageCnt);
		listAdapter = new ListAdapter1(ShangHuChaXun.this, list);
		mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);

		mainright_lv.setAdapter(listAdapter);

		button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				list.clear();
				rightLinearLayout.removeAllViews();

				LinearLayout view = (LinearLayout) LayoutInflater.from(
						ShangHuChaXun.this).inflate(R.layout.faxingxiang_list1,
						null);

				rightLinearLayout.addView(view);

				try {
					Request1(yedtext1.getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pagenum.setText(pageNo + "/" + pageCnt);
				listAdapter = new ListAdapter1(ShangHuChaXun.this, list);
				mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);

				mainright_lv.setAdapter(listAdapter);
			}
		});
		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClass(ShangHuChaXun.this, TuanDuiShangHuActivity.class);
				ShangHuChaXun.this.startActivity(i);
				ShangHuChaXun.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
			}
		});

	}

	class ListAdapter1 extends BaseAdapter {
		public List<String[]> mItemList; // ���η�������private
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

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater
						.from(ShangHuChaXun.this);
				view = inflater.inflate(R.layout.listtable_item1, null);
			}
			TextView listtable_tv1 = (TextView) view
					.findViewById(R.id.listtable_tv1);
			TextView listtable_tv2 = (TextView) view
					.findViewById(R.id.listtable_tv2);
			TextView listtable_tv3 = (TextView) view
					.findViewById(R.id.listtable_tv3);
			TextView listtable_tv5 = (TextView) view
					.findViewById(R.id.listtable_tv5);
			TextView listtable_tv7 = (TextView) view
					.findViewById(R.id.listtable_tv7);
			listtable_tv1.setText(mItemList.get(position)[1]);
			listtable_tv2.setText(mItemList.get(position)[4]);
			listtable_tv3.setText(mItemList.get(position)[2]);
			listtable_tv5.setText(mItemList.get(position)[6]);
			listtable_tv7.setText(mItemList.get(position)[8]);
			listtable_tv1.setTextColor(Color.BLACK);
			listtable_tv2.setTextColor(Color.BLACK);
			listtable_tv3.setTextColor(Color.BLACK);
			listtable_tv5.setTextColor(Color.BLACK);
			listtable_tv7.setTextColor(Color.BLACK);

			MyButton listbutton = (MyButton) view
					.findViewById(R.id.shortcut_delete);
			MyButton localCall = (MyButton) view.findViewById(R.id.localCall);
			MyButton nonlocalCall = (MyButton) view
					.findViewById(R.id.nonlocalCall);
			listbutton.itemposition = position;
			localCall.itemposition = position;// ���ز���
			nonlocalCall.itemposition = position;// ��ز���

			// Ӱ��Button��0��ʾ��4Ӱ�أ�8ɾ��
			listbutton.setVisibility(4);
			localCall.setVisibility(4);
			nonlocalCall.setVisibility(4);
			if (mItemList.get(position)[6].equals("�ػ�")
					&& mItemList.get(position)[8].equals("�ܼ�")) {
				listbutton.setVisibility(0);
			}
			listbutton.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					TuanDuiShangHuActivity.Query(ShangHuChaXun.this);
					DB.insertContactId(ShangHuChaXun.this, "Id",
							TuanDuiShangHuActivity.num);
					MyButton myButton = (MyButton) arg0;
					final String licences = list.get(myButton.itemposition)[3];
					final String companyCity = list.get(myButton.itemposition)[10];
					Id = list.get(myButton.itemposition)[0];
					actId = list.get(myButton.itemposition)[5];
					DB.upload(ShangHuChaXun.this, new String[] { "meiId",
							"StId", "ActiveType", "IsNewCompany" },
							new String[] { Id, Id, "SPECIAL", "N" },
							TuanDuiShangHuActivity.num);

					// lxp
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);//
					new Thread() {
						public void run() {

							String[] str1 = new String[] { "", "", "" };
							str1[0] = licences;
							str1[1] = companyCity;
							str1[2] = TuanDuiShangHuActivity.username;
							String json1 = "{" + "\"licences\":\"" + str1[0]
									+ "\"," + "\"city\":\"" + str1[1] + "\","
									+ "\"userId\":\"" + str1[2] + "\"}";
							HeYueQianDing_21.requeststr1 = HttpConnection2
									.HttpRequest(HttpConnection2.getHttpCon(
											TuanDuiShangHuActivity.indenty,
											"A0008"), json1);

							if (HeYueQianDing_21.requeststr1 != null
									&& HeYueQianDing_21.requeststr1.length() > 0) {

								try {
									JSONObject demoJson1 = new JSONObject(
											HeYueQianDing_21.requeststr1);
									if (demoJson1.getString("rspCode").equals(
											"00")) {
										a = 1;
										Intent i = new Intent();
										i.setClass(ShangHuChaXun.this,
												HeYueQianDing_21.class);
										ShangHuChaXun.this.startActivity(i);
										ShangHuChaXun.this.finish();
										overridePendingTransition(
												R.anim.leftin, R.anim.leftout);
										mDialog.dismiss();
									} else if (demoJson1.equals("")) {
										Message msg = new Message();
										Bundle b = new Bundle();
										b.putString(
												"throwsmessage",
												"���������ӳ�ʱ�������µ����һ����ѯ��"
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
										msg.what = 4;
										handler.sendMessage(msg);//
										mDialog.dismiss();
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									techown.shanghu.https.Log.Instance()
											.WriteLog(
													"ShangHuChaXun��"
															+ e.getMessage());
								}
							} else {
								Message msg = new Message();
								Bundle b = new Bundle();
								b.putString("throwsmessage",
										"��������ʧ�ܣ������µ����һ����ѯ��");
								msg.setData(b);
								msg.what = 4;
								handler.sendMessage(msg);//
								mDialog.dismiss();
							}
						}
					}.start();
				}
			});

			localCall.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
					new Thread() {
						public void run() {
							Looper.prepare();
							try {
								String json = "{" + "\"merId\":\""
										+ mItemList.get(position)[0] + "\","
										+ "\"actId\":\""
										+ mItemList.get(position)[5] + "\"}";
								String requestStr = HttpConnection2.HttpRequest(
										HttpConnection2.getHttpCon(
												TuanDuiShangHuActivity.indenty,
												"A0010"), json);
								mDialog.dismiss();
								if (null != requestStr && requestStr.length() > 0) {
									JSONObject obj = new JSONObject(requestStr);
									mobilPhone = obj.getString("handPhone");// ��ȡ�ƶ��绰����

									if (null != mobilPhone) {
										dialDialog(ShangHuChaXun.this,
												"�Ƿ񲦴򱾵ص绰��", mobilPhone);
									}
								} else {
									Message msg = new Message();
									Bundle b = new Bundle();
									b.putString("throwsmessage", "��������ʧ�ܣ������ԣ�");
									msg.setData(b);
									msg.what = 4;
									handler.sendMessage(msg);
								}
							} catch (JSONException e) {
								techown.shanghu.https.Log.Instance().WriteLog(
										"ShangHuChaXun�ࣺ����A0010�ӿ�ʧ��..."
												+ e.getMessage());
							}
							Looper.loop();
						}
					}.start();
				}
			});

			nonlocalCall.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
					new Thread() {
						public void run() {
							Looper.prepare();
							try {
								String jsonStr = "{" + "\"merId\":\""
										+ mItemList.get(position)[0] + "\","
										+ "\"actId\":\""
										+ mItemList.get(position)[5] + "\"}";
								String requestData = HttpConnection2.HttpRequest(
										HttpConnection2.getHttpCon(
												TuanDuiShangHuActivity.indenty,
												"A0010"), jsonStr);
								mDialog.dismiss();
								if (null != requestData && requestData.length() > 0) {
									JSONObject obj = new JSONObject(requestData);
									mobilPhone = obj.getString("handPhone");// ��ȡ�ƶ��绰����

									if (null != mobilPhone) {
										dialDialog(ShangHuChaXun.this,
												"�Ƿ񲦴���ص绰��", "0" + mobilPhone);
									}
								} else {
									Message msg = new Message();
									Bundle b = new Bundle();
									b.putString("throwsmessage", "��������ʧ�ܣ������ԣ�");
									msg.setData(b);
									msg.what = 4;
									handler.sendMessage(msg);
								}
							} catch (JSONException e) {
								techown.shanghu.https.Log.Instance().WriteLog(
										"ShangHuChaXun�ࣺ����A0010�ӿ�ʧ��..."
												+ e.getMessage());
							}
							Looper.loop();
						}
					}.start();
				}
			});

			return view;
		}
	}

	public List<String[]> list = new ArrayList<String[]>();
	protected String Jsonstr, mobilPhone;

	String[] tempstr = new String[14];

	public void Request() throws JSONException {

		try {
			JSONObject demoJson1 = new JSONObject(jsont);

			if (demoJson1.getString("rspCode").equals("00")) {
				JSONArray numberList1 = demoJson1.getJSONArray("detail");
				for (int j = 0; j < numberList1.length(); j++) {
					JSONObject datajson = (JSONObject) numberList1.opt(j);
					String[] tempstr = new String[11];
					pageCnt = demoJson1.getString("pageCnt");
					pageNo = demoJson1.getString("pageNo");
					tempstr[0] = datajson.getString("merId");
					tempstr[1] = datajson.getString("merName");
					tempstr[2] = datajson.getString("companyName");
					tempstr[3] = datajson.getString("licences");
					tempstr[4] = datajson.getString("merAddress");
					tempstr[5] = datajson.getString("actId");
					// tempstr[6]=datajson.getString("activeMode");
					String lei1 = datajson.getString("activeMode");
					if (lei1.equals("H")) {
						tempstr[6] = "���";
					} else if (lei1.equals("S")) {
						tempstr[6] = "�ػ�";
					} else if (lei1.equals("M")) {
						tempstr[6] = "Ӫ��";
					} else if (lei1.equals("W")) {
						tempstr[6] = "������";
					}

					else {
						tempstr[6] = "��";
					}
					tempstr[7] = datajson.getString("agreementId");
					String lei = datajson.getString("auditState");
					if (lei.equals("06")) {
						tempstr[8] = "��ͨ��";
					} else if (lei.equals("01")) {
						tempstr[8] = "�������";
					} else if (lei.equals("02")) {
						tempstr[8] = "�ͷ����";
					} else if (lei.equals("03")) {
						tempstr[8] = "ͨ��";
					} else if (lei.equals("05")) {
						tempstr[8] = "����";
					} else if (lei.equals("04")) {
						tempstr[8] = "�ܼ�";
					} else if (lei.equals("07")) {
						tempstr[8] = "�������";
					} else if (lei.equals("08")) {
						tempstr[8] = "�������";
					} else if (lei.equals("10")) {
						tempstr[8] = "������";
					} else if (lei.equals("11")) {
						tempstr[8] = "�������";
					} else if (lei.equals("12")) {
						tempstr[8] = "���߾ܾ�";
					} else if (lei.equals("13")) {
						tempstr[8] = "Ԥ��ʱ";
					} else if (lei.equals("14")) {
						tempstr[8] = "������˳�ʱ";
					} else if (lei.equals("15")) {
						tempstr[8] = "������˳�ʱ";
					} else if (lei.equals("16")) {
						tempstr[8] = "������˳�ʱ";
					} else if (lei.equals("17")) {
						tempstr[8] = "�ͷ���˳�ʱ";
					} else if (lei.equals("18")) {
						tempstr[8] = "������˳�ʱ";
					} else {
						tempstr[8] = "��";
					}
					tempstr[9] = datajson.getString("auditReason");
					tempstr[10] = datajson.getString("companyCity");
					list.add(tempstr);

				}
			} else {
				log.Toast(ShangHuChaXun.this, demoJson1.getString("rspInfo"));
				pageCnt = "1";
				pageNo = "1";
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"ShangHuChaXun�ࡣ������" + e.getMessage());
		}

	}

	public void Request1(final String name) throws Exception {

		Message msg3 = new Message();
		msg3.what = 1;
		handler.sendMessage(msg3);
		new Thread() {
			public void run() {

				Jsonstr = "{\"misId\":\"\",\"terId\":\"\",\"licences\":\"\",\"companyName\":\"\",\"merName\":\""
						+ name
						+ "\",\"address\":\"\",\"merTel\":\"\",\"companyTel\":\"\",\"cityId\":\""
						+ cityid
						+ "\",\"userId\":\""
						+ TuanDuiShangHuActivity.username
						+ "\",\"activeMode\":\"MER\",\"isOnline\":\"N\",\"pageNo\":\"1\"}";

				String jsont = HttpConnection2.HttpRequest(HttpConnection2
						.getHttpCon(TuanDuiShangHuActivity.indenty, "A0009"),
						Jsonstr);
				mDialog.dismiss();
				try {
					JSONObject demoJson1 = new JSONObject(jsont);
					if (jsont != null) {

						if (demoJson1.getString("rspCode").equals("00")) {
							JSONArray numberList1 = demoJson1
									.getJSONArray("detail");
							for (int j = 0; j < numberList1.length(); j++) {
								JSONObject datajson = (JSONObject) numberList1
										.opt(j);
								String[] tempstr = new String[11];
								pageCnt = demoJson1.getString("pageCnt");
								pageNo = demoJson1.getString("pageNo");
								tempstr[0] = datajson.getString("merId");
								tempstr[1] = datajson.getString("merName");
								tempstr[2] = datajson.getString("companyName");
								tempstr[3] = datajson.getString("licences");
								tempstr[4] = datajson.getString("merAddress");
								tempstr[5] = datajson.getString("actId");
								String lei1 = datajson.getString("activeMode");
								if (lei1.equals("H")) {
									tempstr[6] = "���";
								} else if (lei1.equals("S")) {
									tempstr[6] = "�ػ�";
								} else if (lei1.equals("M")) {
									tempstr[6] = "Ӫ��";
								} else if (lei1.equals("W")) {
									tempstr[6] = "������";
								}

								else {
									tempstr[6] = "��";
								}
								tempstr[7] = datajson.getString("agreementId");
								String lei = datajson.getString("auditState");
								if (lei.equals("06")) {
									tempstr[8] = "��ͨ��";
								} else if (lei.equals("01")) {
									tempstr[8] = "�������";
								} else if (lei.equals("02")) {
									tempstr[8] = "�ͷ����";
								} else if (lei.equals("03")) {
									tempstr[8] = "ͨ��";
								} else if (lei.equals("05")) {
									tempstr[8] = "����";
								} else if (lei.equals("04")) {
									tempstr[8] = "�ܼ�";
								} else if (lei.equals("07")) {
									tempstr[8] = "�������";
								} else if (lei.equals("08")) {
									tempstr[8] = "�������";
								} else if (lei.equals("10")) {
									tempstr[8] = "������";
								} else if (lei.equals("11")) {
									tempstr[8] = "�������";
								} else if (lei.equals("12")) {
									tempstr[8] = "���߾ܾ�";
								} else if (lei.equals("13")) {
									tempstr[8] = "Ԥ��ʱ";
								} else if (lei.equals("14")) {
									tempstr[8] = "������˳�ʱ";
								} else if (lei.equals("15")) {
									tempstr[8] = "������˳�ʱ";
								} else if (lei.equals("16")) {
									tempstr[8] = "������˳�ʱ";
								} else if (lei.equals("17")) {
									tempstr[8] = "�ͷ���˳�ʱ";
								} else if (lei.equals("18")) {
									tempstr[8] = "������˳�ʱ";
								} else {
									tempstr[8] = "��";
								}
								tempstr[9] = datajson.getString("auditReason");
								tempstr[10] = datajson.getString("companyCity");
								list.add(tempstr);
							}
							Message msg3 = new Message();
							msg3.what = 6;
							handler.sendMessage(msg3);
						} else {
							// log.Toast(ShangHuChaXun.this,demoJson1.getString("rspInfo"));
							pageCnt = "1";
							pageNo = "1";
							Message msg2 = new Message();
							msg2.what = 7;
							Bundle b = new Bundle();
							b.putString("throwsmessage",
									demoJson1.getString("rspInfo"));
							msg2.setData(b);
							handler.sendMessage(msg2);

						}
					}
				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"ShangHuChaXun��" + e.getMessage());
				}
			}
		}.start();
	}

	String pageCnt;
	String pageNo;
	TextView pagenum;

	class MainButtonListener implements OnClickListener {
		Intent intent = getIntent();

		public void onClick(View v) {
			int v_id = v.getId();
			switch (v_id) {
			case R.id.shouye_xd:// ��ҳ
				if (Integer.parseInt(pageNo) == 1) {
					log.Toast(ShangHuChaXun.this, "�Ѿ�����ҳ��");
				} else {
					pageData("1");

				}

				break;
			case R.id.shangye_xd:// ��һҳ
				if (Integer.parseInt(pageNo) > 1) {
					pageData(String.valueOf(Integer.parseInt(pageNo) - 1));

				} else {
					log.Toast(ShangHuChaXun.this, "�Ѿ�����ҳ��");
				}

				break;
			case R.id.xiaye_xd:// ��һҳ
				if (Integer.parseInt(pageNo) == Integer.parseInt(pageCnt)) {
					log.Toast(ShangHuChaXun.this, "�Ѿ���βҳ��");
				} else {
					pageData(String.valueOf(Integer.parseInt(pageNo) + 1));

				}
				break;
			case R.id.moye_xd:// ĩҳ
				if (pageCnt.equalsIgnoreCase(pageNo)) {
					log.Toast(ShangHuChaXun.this, "�Ѿ���βҳ��");
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
						"", "", "MER", "N", "" };

				// str[3]=ZhongBuChaXun4.tempstr1[0];

				str[4] = yedtext1.getText().toString();
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
								String[] tempstr = new String[11];

								tempstr[0] = datajson.getString("merId");
								tempstr[1] = datajson.getString("merName");
								tempstr[2] = datajson.getString("companyName");
								tempstr[3] = datajson.getString("licences");
								tempstr[4] = datajson.getString("merAddress");
								tempstr[5] = datajson.getString("actId");
								// tempstr[6]=datajson.getString("activeMode");
								String lei1 = datajson.getString("activeMode");
								if (lei1.equals("H")) {
									tempstr[6] = "���";
								} else if (lei1.equals("S")) {
									tempstr[6] = "�ػ�";
								} else if (lei1.equals("M")) {
									tempstr[6] = "Ӫ��";
								} else if (lei1.equals("W")) {
									tempstr[6] = "������";
								}

								else {
									tempstr[6] = "��";
								}
								tempstr[7] = datajson.getString("agreementId");
								String lei = datajson.getString("auditState");
								if (lei.equals("06")) {
									tempstr[8] = "��ͨ��";
								} else if (lei.equals("01")) {
									tempstr[8] = "�������";
								} else if (lei.equals("02")) {
									tempstr[8] = "�ͷ����";
								} else if (lei.equals("03")) {
									tempstr[8] = "ͨ��";
								} else if (lei.equals("05")) {
									tempstr[8] = "����";
								} else if (lei.equals("04")) {
									tempstr[8] = "�ܼ�";
								} else if (lei.equals("07")) {
									tempstr[8] = "�������";
								} else if (lei.equals("08")) {
									tempstr[8] = "�������";
								} else if (lei.equals("10")) {
									tempstr[8] = "������";
								} else if (lei.equals("11")) {
									tempstr[8] = "�������";
								} else if (lei.equals("12")) {
									tempstr[8] = "���߾ܾ�";
								} else if (lei.equals("13")) {
									tempstr[8] = "Ԥ��ʱ";
								} else if (lei.equals("14")) {
									tempstr[8] = "������˳�ʱ";
								} else if (lei.equals("15")) {
									tempstr[8] = "������˳�ʱ";
								} else if (lei.equals("16")) {
									tempstr[8] = "������˳�ʱ";
								} else if (lei.equals("17")) {
									tempstr[8] = "�ͷ���˳�ʱ";
								} else if (lei.equals("18")) {
									tempstr[8] = "������˳�ʱ";
								} else {
									tempstr[8] = "��";
								}
								tempstr[9] = datajson.getString("auditReason");
								tempstr[10] = datajson.getString("companyCity");
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

						techown.shanghu.https.Log.Instance().WriteLog(
								"ShanghuChaXun�ࡢ������" + e.getMessage());
					}
				}

			}
		}.start();
	}

	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			int result = bundle.getInt("code");
			if (1 == result) {
				Toast.makeText(ShangHuChaXun.this, "������ĵ绰Ϊ��" + mobilPhone, 1000).show();
			} else {
				Toast.makeText(ShangHuChaXun.this, "������ĵ绰δ��ͨ!", 1000).show();
			}
		}
	};

	private void dialDialog(final Context con, String message,
			final String phoneNum) {
		AlertDialog.Builder ab = new AlertDialog.Builder(con);
		ab.setTitle("��ʾ");
		ab.setMessage(message);
		ab.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent mIntent = new Intent("android.provider.action.SIPDAILOUT");
				Bundle bundle = new Bundle();
				bundle.putString("package", "techown.shanghu");
				bundle.putString("class", "techown.shanghu.ShangHuChaXun");
				bundle.putString("number", phoneNum);
				mIntent.putExtras(bundle);
				sendOrderedBroadcast(mIntent, null);

				IntentFilter intentFilter = new IntentFilter();
				intentFilter.addAction("android.provider.action.SIPDAILOUTRESULT");
				registerReceiver(receiver, intentFilter);
				dialog.dismiss();
			}
		});
		ab.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		ab.create().show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.provider.action.SIPDAILOUTRESULT");
		registerReceiver(receiver, intentFilter);
		unregisterReceiver(receiver);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(ShangHuChaXun.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

}
