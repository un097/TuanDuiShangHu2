package techown.shanghu;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.GetDate;
import techown.shanghu.https.HttpConnection2;
import techown.shanghu.https.PostService;
import techown.shanghu.photo.CGXYHTakePhotoActivity;
import techown.shanghu.photo.CGXYHTakePhotoActivity2;
import techown.shanghu.photo.COfCOfYHTakePhotoActivity;
import techown.shanghu.photo.CaoYouHuiTakePhotoActivity2;
import techown.shanghu.photo.FileUtils;
import techown.shanghu.photo.GDTPhotoActivity;
import techown.shanghu.photo.RedTakePhotoActivity;
import techown.shanghu.photo.RedTakePhotoActivityBox;
import techown.shanghu.photo.TakePhotoActivity_1_5;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TuanDuiShangHuActivity extends Activity {

	public static Map<String, String[]> activityStr = new HashMap<String, String[]>();

	public static String path = "sdcard/" + "TJB/TuanDui/zhongbu/pic/";
	CommonUtil commonUtil = new CommonUtil();
	static Map agencyCityAndIdMap;

	View nextLayout;
	DES des = new DES();
	ListView mainright_lv;
	ListAdapter listAdapter;
	DataCheckUtil dc = new DataCheckUtil();
	DBUtil DB = new DBUtil();
	LinearLayout nextLayoutChild, rightLinearLayout;
	EditDialog log = new EditDialog();
	String[][] contactsName = new String[4][]; // 声明用于存放姓名数组
	String[][] contactsPhone = new String[4][]; // 声明用于存放电话的数组
	String[][] contactsTime = new String[4][]; // 声明用于存放申请时间的数组
	String[][] contactId = new String[4][];// 声明用于存放ID的数组

	public List<String[]> liststr = new ArrayList<String[]>();
	private int i;
	boolean j;
	static String ProvinceName;
	static String cityId;
	static String[] areastr = null;
	static String agencyId;
	static String agencyareaId;
	static String[] agencycityname;
	static String shengid;
	static String cityname;
	static String areaName;
	static String areaName2;
	public static int templateLength, msgLength;
	public static String num;
	public static String num1;
	public static String time;
	public static String tmpId1, tmpId2, tmpId3, tmpId4, tmpId5, tmpId6, tmpId7, tmpId8;
	boolean isdoubleclick;
	public String name1, time1;
	public static int device = 0;// 0联通。1联想。;
	private Dialog mDialog;

	EditDialog ds = new EditDialog();
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
				ds.Toast(TuanDuiShangHuActivity.this,
						b.getString("throwsmessage"));
				break;
			case 3:
				mDialog.dismiss();
				break;
			case 4:
				log.Toast(TuanDuiShangHuActivity.this, "垃圾文件清理完毕，请关机重启！");
			default:
        		break;
			}
		}
	};

	/*********************************** 广播注册与解除注册 赵景贤添加 ***************************/
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		registerReceiver(broadcastReceiver, getIntentFilter());
		registerReceiver(broadcastReceiver1, getIntentFilter());
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(broadcastReceiver);
		unregisterReceiver(broadcastReceiver1);
	}

	/*************************************************************************************/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
				.penaltyLog().build());
		saveDBVer("13");// 生产1 UAT 9    生产13 UAT 13 20160630修改 
		Constant.loginDBVer = getLoginDBVer("/sdcard/TJB/ver/login.txt");
		Constant.tdshDBVer = getLoginDBVer("/sdcard/TJB/ver/tuanduishanghu.txt");
		getmodel();
		Context friendContext3 = null;
		try {
			friendContext3 = TuanDuiShangHuActivity.this.createPackageContext(
					"techown.login", Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog("团队：没有登录数据库");
		}
		if (friendContext3 != null
				&& isDatabaseExists(friendContext3, "techown_login.db")) {
			setContentView(R.layout.main);
			isdoubleclick = true;
			getdata(TuanDuiShangHuActivity.this);
			Constant.username = username;
			System.out.println("username==="+username);
			Constant.imei = imei;
			System.out.println("imei==="+imei);
			quaryCityId();
			// 开启startService
			Intent it = new Intent("techown.shanghu.https.PostService");
			TuanDuiShangHuActivity.this.startService(it);
			if (getWindowManager().getDefaultDisplay().getWidth() > 1024) {
				device = 1;
			}

			mDialog = new AlertDialog.Builder(TuanDuiShangHuActivity.this)
					.create();// 连接网络进程

			HeYueQianDing_2.num = 0;
			try {
				String[] a = DB.klQuery(this, "templateLength", "t_msgconfig");
				String[] b = DB.klQuery(this, "msgLength", "t_msgconfig");
				templateLength = Integer.parseInt(a[0]);
				msgLength = Integer.parseInt(b[0]);
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"短信数据库查询异常！" + e.getMessage().toString());
			}

			LinearLayout layoutAll = (LinearLayout) findViewById(R.id.layoutAll);
			rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
			List<Map<String, List<String>>> strList = new ArrayList<Map<String, List<String>>>();// 下拉菜单的内容
			List<Map<String, List<OnClickListener>>> cameraListenerList = new ArrayList<Map<String, List<OnClickListener>>>();
			List<Back> backListenerList = new ArrayList<Back>();
			for (int i = 0; i < 7; i++) {
				Map<String, List<String>> strMap = new HashMap<String, List<String>>();// 下拉菜单的标题
				final List<String> title = new ArrayList<String>();
				if (i == 0) {
					title.add("特惠拓展");
					strMap.put("title", title);
					backListenerList.add(null);

					List<String> contextList = new ArrayList<String>();// 下拉菜单字菜单的标题
					contextList.add("商户预审");
					contextList.add("合约签订");
					contextList.add("优惠上线");
					contextList.add("商户查询");

					strMap.put("context", contextList);
					strList.add(strMap);

					// 商户预审
					Map<String, List<OnClickListener>> testOnClickMap = new HashMap<String, List<OnClickListener>>();// 调用摄像头监听
																														// 与下拉菜单字菜单的标题对应
					List<OnClickListener> cameraList = new ArrayList<OnClickListener>();

					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							// TODO Auto-generated method stub

							if (isdoubleclick) {
								isdoubleclick = false;
								Intent i = new Intent();
								i.setClass(TuanDuiShangHuActivity.this,
										YuShen.class);
								TuanDuiShangHuActivity.this.startActivity(i);
								TuanDuiShangHuActivity.this.finish();
								// overridePendingTransition(R.anim.rightin,
								// R.anim.rightout);
								techown.shanghu.https.OperateLog.Instance()
										.WriteLog("商户团队商户预审！");
								overridePendingTransition(R.anim.leftin,
										R.anim.leftout);
								j = true;
							}
						}
					});
					// 合约签订

					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {

								Message msg = new Message();
								msg.what = 1;
								handler.sendMessage(msg);//
								new Thread() {
									public void run() {
										String time = GetDate.getDate1()
												.toString();

										String Jsonstr = "{\"userId\":\""
												+ username
												+ "\",\"chkDate\":\""
												+ time
												+ "\", \"city\":\""
												+ cityId
												+ "\",  \"chkStatus\":\"\", \"isDel\":\"\"}";
										HeYueQianDing_0.jsont = HttpConnection2.HttpRequest(
												HttpConnection2
														.getHttpCon(
																TuanDuiShangHuActivity.indenty,
																"A0007"),
												Jsonstr);
										HeYueQianDing_3.jsont = HttpConnection2.HttpRequest(
												HttpConnection2
														.getHttpCon(
																TuanDuiShangHuActivity.indenty,
																"A0007"),
												Jsonstr);
										if (HeYueQianDing_0.jsont != null
												&& HeYueQianDing_3.jsont
														.length() > 0) {
											JSONObject demoJson1;
											try {
												demoJson1 = new JSONObject(
														HeYueQianDing_0.jsont);
												if (demoJson1.getString(
														"rspCode").equals("00")) {
													Query(TuanDuiShangHuActivity.this);
													// DB.insertContactId(TuanDuiShangHuActivity.this,"Id",num);
													// DBUtil.insertContactId4(TuanDuiShangHuActivity.this,MyDatabaseHelper.SEND_BarcodeNo,num);
													isdoubleclick = false;
													Intent i = new Intent();
													i.setClass(
															TuanDuiShangHuActivity.this,
															HeYueQianDing_0.class);

													TuanDuiShangHuActivity.this
															.startActivity(i);
													TuanDuiShangHuActivity.this
															.finish();
													overridePendingTransition(
															R.anim.leftin,
															R.anim.leftout);
													techown.shanghu.https.OperateLog
															.Instance()
															.WriteLog(
																	"商户团队合约签订！");
													mDialog.dismiss();
												} else if (demoJson1.equals("")) {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage",
															"服务器连接超时，请重新点击！"
																	+ demoJson1
																			.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												} else {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage",
															demoJson1
																	.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch
												// block
												techown.shanghu.https.Log
														.Instance()
														.WriteLog(
																"TuanDuiShangHuActivity合约签订"
																		+ e.getMessage());
											}

										} else {
											Message msg = new Message();
											Bundle b = new Bundle();
											b.putString("throwsmessage",
													"网络连接失败，请重新点击！");
											msg.setData(b);
											msg.what = 2;
											handler.sendMessage(msg);//
											mDialog.dismiss();
										}

									}
								}.start();

							}
						}

					});
					// 优惠上线
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								Message msg = new Message();
								msg.what = 1;
								handler.sendMessage(msg);//
								new Thread() {
									public void run() {
										String Jsonstr = "{\"misId\":\"\",\"terId\":\"\",\"licences\":\"\",\"companyName\":\"\",\"merName\":\"\",\"address\":\"\",\"merTel\":\"\",\"companyTel\":\"\",\"cityId\":\""
												+ cityId
												+ "\",\"userId\":\"\",\"activeMode\":\"S*H\",\"isOnline\":\"Y\",\"pageNo\":\"1\"}";
										YouHuiShangXian_TeHui.jsont = HttpConnection2.HttpRequest(
												HttpConnection2
														.getHttpCon(
																TuanDuiShangHuActivity.indenty,
																"A0009"),
												Jsonstr);
										if (YouHuiShangXian_TeHui.jsont != null) {
											JSONObject demoJson1;
											try {
												demoJson1 = new JSONObject(
														YouHuiShangXian_TeHui.jsont);
												if (demoJson1.getString(
														"rspCode").equals("00")) {
													isdoubleclick = false;
													Intent i = new Intent();
													i.setClass(
															TuanDuiShangHuActivity.this,
															YouHuiShangXian_TeHui.class);
													TuanDuiShangHuActivity.this
															.startActivity(i);
													TuanDuiShangHuActivity.this
															.finish();
													overridePendingTransition(
															R.anim.leftin,
															R.anim.leftout);
													techown.shanghu.https.OperateLog
															.Instance()
															.WriteLog(
																	"商户团队优惠上线！");
													mDialog.dismiss();
												} else if (demoJson1.equals("")) {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage",
															"服务器连接超时，请重新点击！"
																	+ demoJson1
																			.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												} else {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage",
															demoJson1
																	.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch
												// block
												techown.shanghu.https.Log
														.Instance()
														.WriteLog(
																"TuanDuiShangHuActivity优惠上线"
																		+ e.getMessage());
											}

										} else {
											Message msg = new Message();
											Bundle b = new Bundle();
											b.putString("throwsmessage",
													"网络连接失败，请重新点击！");
											msg.setData(b);
											msg.what = 2;
											handler.sendMessage(msg);//
											// Toast.makeText(TuanDuiShangHuActivity.this,"网络连接失败，请重新点击下一步查询！",Toast.LENGTH_SHORT).show();
											mDialog.dismiss();
										}

									}
								}.start();
							}
						}
					});
					// 商户查询064150
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								Message msg = new Message();
								msg.what = 1;
								handler.sendMessage(msg);
								new Thread() {
									public void run() {
										// List<TCity>
										// tcity=DBUtil.cityQuerycity(TuanDuiShangHuActivity.this,"t_city"," where Name = '"+cityname+"'");
										// ShangHuChaXun.cityid =
										// tcity.get(0).id;
										String Jsonstr = "{\"misId\":\"\",\"terId\":\"\",\"licences\":\"\","
												+ "\"companyName\":\"\",\"merName\":\"\",\"address\":\"\","
												+ "\"merTel\":\"\",\"companyTel\":\"\",\"cityId\":\""
												+ cityId
												+ "\",\"userId\":\""
												+ username
												+ "\",\"activeMode\":\"MER\",\"isOnline\":\"\",\"pageNo\":\"1\"}";
										ShangHuChaXun.jsont = HttpConnection2.HttpRequest(
												HttpConnection2
														.getHttpCon(
																TuanDuiShangHuActivity.indenty,
																"A0009"),
												Jsonstr);
										if (ShangHuChaXun.jsont != null) {
											JSONObject demoJson1;
											try {
												demoJson1 = new JSONObject(
														ShangHuChaXun.jsont);
												if (demoJson1.getString(
														"rspCode").equals("00")) {
													isdoubleclick = false;
													Intent i = new Intent();
													i.setClass(
															TuanDuiShangHuActivity.this,
															ShangHuChaXun.class);
													TuanDuiShangHuActivity.this
															.startActivity(i);
													TuanDuiShangHuActivity.this
															.finish();
													overridePendingTransition(
															R.anim.leftin,
															R.anim.leftout);
													techown.shanghu.https.OperateLog
															.Instance()
															.WriteLog(
																	"商户团队商户查询！");
													mDialog.dismiss();
												} else if (demoJson1.equals("")) {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage",
															"服务器连接超时，请重新点击！"
																	+ demoJson1
																			.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												} else {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage",
															demoJson1
																	.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch
												// block
												techown.shanghu.https.Log
														.Instance()
														.WriteLog(
																"TuanDuiShangHuActivity商户查询"
																		+ e.getMessage());
											}

										} else {
											Message msg = new Message();
											Bundle b = new Bundle();
											b.putString("throwsmessage",
													"网络连接失败，请重新点击！");
											msg.setData(b);
											msg.what = 2;
											handler.sendMessage(msg);//
											// Toast.makeText(TuanDuiShangHuActivity.this,"网络连接失败，请重新点击下一步查询！",Toast.LENGTH_SHORT).show();
											mDialog.dismiss();
										}

									};
								}.start();

							}
						}
					});

					testOnClickMap.put("cameraListener", cameraList);
					cameraListenerList.add(testOnClickMap);
				}
				if (i == 1) {
					title.add("活动管理");
					strMap.put("title", title);
					backListenerList.add(null);

					List<String> contextList = new ArrayList<String>();// 下拉菜单字菜单的标题
					contextList.add("活动录入");
					contextList.add("活动查询");

					strMap.put("context", contextList);
					strList.add(strMap);

					Map<String, List<OnClickListener>> testOnClickMap = new HashMap<String, List<OnClickListener>>();// 调用摄像头监听
																														// 与下拉菜单字菜单的标题对应
					List<OnClickListener> cameraList = new ArrayList<OnClickListener>();
					// 总部活动录入
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								isdoubleclick = false;
								// TODO Auto-generated method stub
								// Query(TuanDuiShangHuActivity.this);
								// DBUtil.insertContactId(TuanDuiShangHuActivity.this,"Id",num);
								ZhongBuHuoDong1.CompanyLicense1 = null;
								Intent i = new Intent();
								i.setClass(TuanDuiShangHuActivity.this,
										ZhongBuChaXun4.class);
								
//								i.setClass(TuanDuiShangHuActivity.this,
//										ZhongBuHuoDong2.class);
								
								TuanDuiShangHuActivity.this.startActivity(i);
								TuanDuiShangHuActivity.this.finish();
								overridePendingTransition(R.anim.leftin,
										R.anim.leftout);
								techown.shanghu.https.OperateLog.Instance()
										.WriteLog("商户团队活动录入！");

							}
						}
					});

					// 活动查询
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {

								isdoubleclick = false;
								Intent i = new Intent();
								i.setClass(TuanDuiShangHuActivity.this,
										HuoDongChaXun.class);
								
//								i.setClass(TuanDuiShangHuActivity.this,
//										ZhongBuHuoDong2.class);
								
								TuanDuiShangHuActivity.this.startActivity(i);
								TuanDuiShangHuActivity.this.finish();
								overridePendingTransition(R.anim.leftin,
										R.anim.leftout);
								techown.shanghu.https.OperateLog.Instance()
										.WriteLog("商户团队活动查询！");

							}
						}
					});

					testOnClickMap.put("cameraListener", cameraList);
					cameraListenerList.add(testOnClickMap);
				}
				if (i == 2) {
					title.add("最红管理");
					strMap.put("title", title);
					backListenerList.add(null);

					List<String> contextList = new ArrayList<String>();// 下拉菜单字菜单的标题
					contextList.add("合约签订");

					strMap.put("context", contextList);
					strList.add(strMap);

					Map<String, List<OnClickListener>> testOnClickMap = new HashMap<String, List<OnClickListener>>();// 调用摄像头监听
																														// 与下拉菜单字菜单的标题对应
					List<OnClickListener> cameraList = new ArrayList<OnClickListener>();
					// 最红签约
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								isdoubleclick = false;
								ZhongBuHuoDong1.CompanyLicense1 = null;
								Intent i = new Intent();
								i.setClass(TuanDuiShangHuActivity.this,
										RedNumber.class);
								TuanDuiShangHuActivity.this.startActivity(i);
								TuanDuiShangHuActivity.this.finish();
								overridePendingTransition(R.anim.leftin,
										R.anim.leftout);
								techown.shanghu.https.OperateLog.Instance()
										.WriteLog("最红签约进入商户编号录入界面！");
							}
						}
					});
					testOnClickMap.put("cameraListener", cameraList);
					cameraListenerList.add(testOnClickMap);
				}

				if (i == 3) {
					title.add("工单管理");
					strMap.put("title", title);

					backListenerList.add(null);
					List<String> contextList = new ArrayList<String>();// 下拉菜单字菜单的标题
					contextList.add("工单维护");
					contextList.add("工单反馈");
					contextList.add("商户工单维护");
					contextList.add("门店进件异常工单");

					strMap.put("context", contextList);
					strList.add(strMap);
					Map<String, List<OnClickListener>> testOnClickMap = new HashMap<String, List<OnClickListener>>();// 调用摄像头监听
																														// 与下拉菜单字菜单的标题对应
					List<OnClickListener> cameraList = new ArrayList<OnClickListener>();
					// 工单维护
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								isdoubleclick = false;

								// // TODO Auto-generated method stub
								Intent i = new Intent();
								i.setClass(TuanDuiShangHuActivity.this,
										GongDanWeiHu1.class);
								TuanDuiShangHuActivity.this.startActivity(i);
								TuanDuiShangHuActivity.this.finish();
								overridePendingTransition(R.anim.leftin,
										R.anim.leftout);
								techown.shanghu.https.OperateLog.Instance()
										.WriteLog("商户团队工单维护！");
							}
						}
					});
					// 工单反馈
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								isdoubleclick = false;
								Intent i = new Intent();
								i.setClass(TuanDuiShangHuActivity.this,
										GongDanFanKui1.class);
								TuanDuiShangHuActivity.this.startActivity(i);
								TuanDuiShangHuActivity.this.finish();
								overridePendingTransition(R.anim.leftin,
										R.anim.leftout);
								techown.shanghu.https.OperateLog.Instance()
										.WriteLog("商户团队工单反馈！");
							}
						}
					});
					
					// 商户工单维护
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								Message msg = new Message();
								msg.what = 1;
								handler.sendMessage(msg);
								new Thread() {
									public void run() {
										//String Jsonstr = "{\"userId\":\""+ TuanDuiShangHuActivity.username +"\",\"taskModel\":\"SJFK\",\"taskState\":\"\"}";
										String Jsonstr = "{\"userId\":\""+ TuanDuiShangHuActivity.username +"\",\"taskModel\":\"3\",\"taskType\":\"\",\"batchNo\":\"\",\"taskStatus\":\"\"}";	
										SHGongDanWeiHu.jsonStr =  HttpConnection2.request(Jsonstr, "A0013");
										//SHGongDanWeiHu.jsonStr="{\"rspCode\":\"00\",\"detail\":[]}";
										techown.shanghu.https.Log.Instance().WriteLog("商户工单维护返回json:"+SHGongDanWeiHu.jsonStr);
										if (SHGongDanWeiHu.jsonStr != null) {
											JSONObject demoJson1;
											try {
												demoJson1 = new JSONObject(
														SHGongDanWeiHu.jsonStr);
												if (demoJson1.getString(
														"rspCode").equals("00")) {
													isdoubleclick = false;
													Intent i = new Intent();
													i.setClass(TuanDuiShangHuActivity.this,
															SHGongDanWeiHu.class);
													TuanDuiShangHuActivity.this.startActivity(i);
													TuanDuiShangHuActivity.this.finish();
													overridePendingTransition(R.anim.leftin,
															R.anim.leftout);
													techown.shanghu.https.OperateLog.Instance()
															.WriteLog("商户团队商户工单维护工单！");
													
													mDialog.dismiss();
												}else if (demoJson1.equals("")) {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage",
															"服务器连接超时，请重新点击！"
																	+ demoJson1
																			.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}else {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage","查询失败,"+
															demoJson1
																	.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch
												// block
												techown.shanghu.https.Log
														.Instance()
														.WriteLog(
																"TuanDuiShangHuActivity商户工单维护："
																		+ e.getMessage());
											}

										} else {
											Message msg = new Message();
											Bundle b = new Bundle();
											b.putString("throwsmessage",
													"网络连接失败，请重新点击查询！");
											msg.setData(b);
											msg.what = 2;
											handler.sendMessage(msg);//
											mDialog.dismiss();
										}

									};
								}.start();
							}
						}
					});
					// 门店进件异常工单
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								Message msg = new Message();
								msg.what = 1;
								handler.sendMessage(msg);
								new Thread() {
									public void run() {
										String Jsonstr = "{\"userId\":\""+ TuanDuiShangHuActivity.username +"\",\"taskModel\":\"SJFK\",\"taskState\":\"\"}";
										MenUnusualGongDan.jsonStr = HttpConnection2.ycgdRequest1(Jsonstr,"sjfkTaskRequest");
										
										techown.shanghu.https.Log.Instance().WriteLog("门店进件异常工单返回json:"+MenUnusualGongDan.jsonStr);
										if (MenUnusualGongDan.jsonStr != null) {
											JSONObject demoJson1;
											try {
												demoJson1 = new JSONObject(
														MenUnusualGongDan.jsonStr);
												if (demoJson1.getString(
														"rspCode").equals("000000")) {
													isdoubleclick = false;
													Intent i = new Intent();
													i.setClass(TuanDuiShangHuActivity.this,
															MenUnusualGongDan.class);
													TuanDuiShangHuActivity.this.startActivity(i);
													TuanDuiShangHuActivity.this.finish();
													overridePendingTransition(R.anim.leftin,
															R.anim.leftout);
													techown.shanghu.https.OperateLog.Instance()
															.WriteLog("商户团队门店进件异常工单！");
													
													mDialog.dismiss();
												}else if (demoJson1.getString(
														"rspCode").equals("000010")) {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage",
															"没有查询到工单信息");
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												} else if (demoJson1.equals("")) {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage",
															"服务器连接超时，请重新点击！"
																	+ demoJson1
																			.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}else {
													Message msg = new Message();
													Bundle b = new Bundle();
													b.putString(
															"throwsmessage","查询失败,"+
															demoJson1
																	.getString("rspInfo"));
													msg.setData(b);
													msg.what = 2;
													handler.sendMessage(msg);//
													mDialog.dismiss();
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch
												// block
												techown.shanghu.https.Log
														.Instance()
														.WriteLog(
																"TuanDuiShangHuActivity门店进件异常工单："
																		+ e.getMessage());
											}

										} else {
											Message msg = new Message();
											Bundle b = new Bundle();
											b.putString("throwsmessage",
													"网络连接失败，请重新点击查询！");
											msg.setData(b);
											msg.what = 2;
											handler.sendMessage(msg);//
											mDialog.dismiss();
										}

									};
								}.start();
							}
						}
					});
					testOnClickMap.put("cameraListener", cameraList);
					cameraListenerList.add(testOnClickMap);
				}

				if (i == 4) {
					title.add("草稿箱"
							+ "("
							+ String.valueOf(numQueryint("DCL",
											des.jiaMi("0"), "st_storeinfo")
									+ numQueryint("DCL",
											des.jiaMi("0"), "youhushangxian")
									+ numQueryint("DCL",
											des.jiaMi("0"), "gongdanweihu")
									+ numQueryint("DCL",
											des.jiaMi("0"), "shgongdanweihu")
									+ numQueryint("DCL",
											des.jiaMi("0"), "red_enterletter"))
							+ ")");
					strMap.put("title", title);

					List<String> contextList = new ArrayList<String>();// 下拉菜单字菜单的标题
					strMap.put("context", contextList);

					strList.add(strMap);
					backListenerList.add(new Back() {

						public void back() {
							// TODO Auto-generated method stub

							caodata.clear();
							rightLinearLayout.removeAllViews();
							rightLinearLayout
									.setBackgroundDrawable(getResources()
											.getDrawable(R.drawable.findbg));
							LinearLayout view = (LinearLayout) LayoutInflater
									.from(TuanDuiShangHuActivity.this).inflate(
											R.layout.caogaoxiang_list, null);
							rightLinearLayout.addView(view);

							sendQuery1();
							sendQuery01();
							sendQuery001();
							sendQuery0001();
							sendQuery00001();
							ListAdapter listAdapter = new ListAdapter(
									TuanDuiShangHuActivity.this, caodata);

							mainright_lv = (ListView) view
									.findViewById(R.id.mainright_lv);
							mainright_lv.setAdapter(listAdapter);
							mainright_lv
									.setOnItemClickListener(new OnItemClickListener() {

										public void onItemClick(
												AdapterView<?> arg0, View arg1,
												int arg2, long arg3) {
											if (isdoubleclick) {
												isdoubleclick = false;

												// TODO Auto-generated method
												// stub

												try {

													MyDatabaseHelper myHelper = null;
													SQLiteDatabase db = null;
													try {

														myHelper = new MyDatabaseHelper(
																TuanDuiShangHuActivity.this,
																"techown.db",
																Constant.tdshDBVer);
														db = myHelper
																.getWritableDatabase();
														techown.shanghu.https.OperateLog
																.Instance()
																.WriteLog(
																		"商户团队草稿箱！");
													} catch (Exception e) {
														// TODO: handle
														// exception
														techown.shanghu.https.Log
																.Instance()
																.WriteLog(
																		"团队：草稿箱出错MyDatabaseHelper"
																				+ e.getMessage());
													} finally {
														if (db != null)
															db.close();
														if (myHelper != null)
															myHelper.close();
													}

													Data1 tmpData = caodata
															.get(arg2);

													if (tmpData.lx
															.equals("合约签订")) {

														tmpId1 = tmpData.Id;
														Intent intent = new Intent();
														intent.setClass(
																TuanDuiShangHuActivity.this,
																TakePhotoActivity_1_5.class);
														startActivity(intent);
														TuanDuiShangHuActivity.this
																.finish();
													}
													if (tmpData.lx
															.equals("合约签订(拒件)")) {
														tmpId2 = tmpData.Id;
														Intent intent = new Intent();
														intent.setClass(
																TuanDuiShangHuActivity.this,
																CGXYHTakePhotoActivity2.class);
														startActivity(intent);
														TuanDuiShangHuActivity.this
																.finish();
													}
													if (tmpData.lx
															.equals("特惠上线")) {
														tmpId3 = tmpData.Id;
														Intent intent = new Intent();
														intent.setClass(
																TuanDuiShangHuActivity.this,
																CGXYHTakePhotoActivity.class);
														startActivity(intent);
														TuanDuiShangHuActivity.this
																.finish();
													}
													if (tmpData.lx
															.equals("最红上线")) {
														tmpId4 = tmpData.Id;
														Intent intent = new Intent();
														intent.setClass(
																TuanDuiShangHuActivity.this,
																CaoYouHuiTakePhotoActivity2.class);
														startActivity(intent);
														TuanDuiShangHuActivity.this
																.finish();
													}
													if (tmpData.lx
															.equals("上门工单")) {
														tmpId5 = tmpData.Id;
														techown.shanghu.https.Log.Instance().WriteLog("草稿箱上门工单num为"+tmpId5+"的件进入拍照界面");
														Intent intent = new Intent();
														intent.setClass(
																TuanDuiShangHuActivity.this,
																GDTPhotoActivity.class);
														startActivity(intent);
														TuanDuiShangHuActivity.this
																.finish();
													}
													if (tmpData.lx
															.equals("续约工单")) {
														tmpId6 = tmpData.Id;
														Intent intent = new Intent();
														intent.setClass(
																TuanDuiShangHuActivity.this,
																COfCOfYHTakePhotoActivity.class);
														startActivity(intent);
														TuanDuiShangHuActivity.this
																.finish();
													}
													if (tmpData.lx
															.equals("最红星期五")) {
														tmpId7 = tmpData.Id;
														Intent intent = new Intent();
														intent.setClass(
																TuanDuiShangHuActivity.this,
																RedTakePhotoActivityBox.class);
														startActivity(intent);
														TuanDuiShangHuActivity.this
																.finish();
													}
													if (tmpData.lx
															.equals("商户工单维护")) {
														tmpId8 = tmpData.Id;
														Intent intent = new Intent();
														intent.setClass(
																TuanDuiShangHuActivity.this,
																CGXSHGongDanWeiHu_72.class);
														startActivity(intent);
														TuanDuiShangHuActivity.this
																.finish();
													}
												} catch (Exception e) {
													// TODO: handle exception
													techown.shanghu.https.Log
															.Instance()
															.WriteLog(
																	"团队：草稿箱出错"
																			+ e.getMessage());
												}
											}
										}
									});
						}

					});

					Map<String, List<OnClickListener>> testOnClickMap = new HashMap<String, List<OnClickListener>>();// 调用摄像头监听
																														// 与下拉菜单字菜单的标题对应
					List<OnClickListener> cameraList = new ArrayList<OnClickListener>();
					testOnClickMap.put("cameraListener", cameraList);
					cameraListenerList.add(testOnClickMap);
				}
				if (i == 5) {

					title.add("发件箱"
							+ "("
							+ String.valueOf(numQueryint("DCL",
									des.jiaMi("2"), "st_storeinfo")
									+ numQueryint("DCL",
											des.jiaMi("12"), "st_storeinfo")
									+ numQueryint("DCL",
											des.jiaMi("22"), "st_storeinfo")
									+ numQueryint("DCL",
											des.jiaMi("221"), "st_storeinfo")
									+ numQueryint("DCL",
											des.jiaMi("4"), "st_storeinfo")
									+ numQueryint("DCL",
											des.jiaMi("3"), "st_storeinfo")
									+ numQueryint("DCL",
											des.jiaMi("2"), "youhushangxian")
									+ numQueryint("DCL",
											des.jiaMi("212"), "youhushangxian")
									+ numQueryint("DCL",
											des.jiaMi("3"), "youhushangxian")
									+ numQueryint("DCL",
											des.jiaMi("313"), "youhushangxian")
									+ numQueryint("DCL",
											des.jiaMi("21"), "gongdanweihu")
									+ numQueryint("DCL",
											des.jiaMi("213"), "gongdanweihu")
									+ numQueryint("DCL",
											des.jiaMi("2"), "gongdanweihu")
									+ numQueryint("DCL",
											des.jiaMi("2"), "red_enterletter")
									+ numQueryint("DCL",
											des.jiaMi("234"), "gongdanweihu")
									+ numQueryint("DCL",
											des.jiaMi("3"), "gongdanweihu")
									+ numQueryint("DCL",
											des.jiaMi("71"), "shgongdanweihu")
									+ numQueryint("DCL",
											des.jiaMi("31"), "gongdanweihu"))
							+ ")");
					strMap.put("title", title);

					List<String> contextList = new ArrayList<String>();// 下拉菜单字菜单的标题
					strMap.put("context", contextList);

					strList.add(strMap);
					backListenerList.add(new Back() {

						public void back() {

							try {

								todayList1.clear();
								// TODO Auto-generated method stub
								rightLinearLayout.removeAllViews();
								rightLinearLayout
										.setBackgroundDrawable(getResources()
												.getDrawable(R.drawable.findbg));
								LinearLayout view = (LinearLayout) LayoutInflater
										.from(TuanDuiShangHuActivity.this)
										.inflate(R.layout.faxingxiang_list,
												null);
								rightLinearLayout.addView(view);
								sendQuery();
								redsendQuery();
								sendQueryyu();
								sendQueryyu1();
								sendQueryjujian();
								sendQuery3();
								sendQueryyu3();
								sendQueryyu31();
								sendQuery31();
								sendQuery4();
								sendQueryyu41();
								sendQueryyu411();
								sendQuery41();
								sendQuery411();
								sendQuery4111();
								sendQuery21();
								sendQuery11();
								sendQuery71();
								ListAdapter listAdapter = new ListAdapter(
										TuanDuiShangHuActivity.this, todayList1);
								mainright_lv = (ListView) view
										.findViewById(R.id.mainright_lv);
								mainright_lv.setAdapter(listAdapter);
							} catch (Exception e) {
								// TODO: handle exception
								techown.shanghu.https.Log.Instance().WriteLog(
										"团队：发件箱出错" + e.getMessage());
							}
						}

					});

					Map<String, List<OnClickListener>> testOnClickMap = new HashMap<String, List<OnClickListener>>();// 调用摄像头监听
																														// 与下拉菜单字菜单的标题对应
					List<OnClickListener> cameraList = new ArrayList<OnClickListener>();
					testOnClickMap.put("cameraListener", cameraList);
					cameraListenerList.add(testOnClickMap);
				}
				/**
				 * 添加问题提报菜单
				 */
				if (i == 6) {
					title.add("问题提报");
					strMap.put("title", title);

					backListenerList.add(null);
					List<String> contextList = new ArrayList<String>();// 下拉菜单字菜单的标题
					contextList.add("设备问题提报");
					contextList.add("提报问题查询");

					strMap.put("context", contextList);
					strList.add(strMap);
					Map<String, List<OnClickListener>> testOnClickMap = new HashMap<String, List<OnClickListener>>();// 调用摄像头监听
																														// 与下拉菜单字菜单的标题对应
					List<OnClickListener> cameraList = new ArrayList<OnClickListener>();
					// 商户问题提报
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								isdoubleclick = false;

								// // TODO Auto-generated method stub
								Intent i = new Intent();
								i.setClass(TuanDuiShangHuActivity.this,
										ShangHuWifiWenTi1.class);
								TuanDuiShangHuActivity.this.startActivity(i);
								TuanDuiShangHuActivity.this.finish();
								overridePendingTransition(R.anim.leftin,
										R.anim.leftout);
								techown.shanghu.https.OperateLog.Instance()
										.WriteLog("商户团队商户设备问题提报！");
							}
						}
					});
					// 提报问题查询
					cameraList.add(new OnClickListener() {

						public void onClick(View arg0) {
							if (isdoubleclick) {
								isdoubleclick = false;
								Intent i = new Intent();
								i.setClass(TuanDuiShangHuActivity.this,
										WifiStateChaXun.class);
								TuanDuiShangHuActivity.this.startActivity(i);
								TuanDuiShangHuActivity.this.finish();
								overridePendingTransition(R.anim.leftin,
										R.anim.leftout);
								techown.shanghu.https.OperateLog.Instance()
										.WriteLog("商户团队商户提报问题查询！");
							}
						}
					});

					testOnClickMap.put("cameraListener", cameraList);
					cameraListenerList.add(testOnClickMap);
				}

			}
			setLayout(layoutAll, strList, cameraListenerList, backListenerList);
			
			System.out.println("MyDatabaseHelper.delectfile==="+MyDatabaseHelper.delectfile);
			techown.shanghu.https.Log.Instance().WriteLog(
					"MyDatabaseHelper.delectfile==="+MyDatabaseHelper.delectfile);
			if(MyDatabaseHelper.delectfile)
			{
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
				new Thread()
				{
					public void run() {
						deleteAllFiles("gongdan");
						deleteAllFiles("youhuishang");
						deleteAllFiles("wentigongda");
						deleteAllFiles("redwu");
						deleteAllFiles("shang");
						deleteAllFiles("shgongdan");
						deleteAllFiles("gesturesign");
						MyDatabaseHelper.delectfile = false;
						System.out.println("清空完毕！！！");
						techown.shanghu.https.Log.Instance().WriteLog("清空完毕！！！");
						mDialog.dismiss();
					};
				}.start();
			}
		} else {
			AlertDialog.Builder ab = new AlertDialog.Builder(
					TuanDuiShangHuActivity.this);
			ab.setTitle("提示");
			ab.setMessage("您还没有登录，请先登录");

			ab.setPositiveButton// 为对话框设置按钮
			("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					TuanDuiShangHuActivity.this.finish();
					techown.shanghu.https.Log.Instance().WriteLog("团队：业代退出程序");
				}
			});
			ab.create().show();
		}

		new Thread() {
			public void run() {
				get();// 查找草稿箱的件事
			}
		}.start();
		
	}

	IntentFilter intentFilter;

	public IntentFilter getIntentFilter() {
		if (intentFilter == null) {
			intentFilter = new IntentFilter();
			intentFilter.addAction("techown.card.portWolong.FJ");
		}
		return intentFilter;
	}

	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			// 接受从PlayerService发送过来的广播数据。
			// loadLayout();
			// TODO

			LinearLayout layoutAll = (LinearLayout) findViewById(R.id.layoutAll);
			LinearLayout linearLayout1 = ((LinearLayout) layoutAll
					.getChildAt(5));

			String num = "发件箱"
					+ "("
					+ String.valueOf(numQueryint("DCL",des.jiaMi("2"), 
									"st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("12"),
									"st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("22"),
									"st_storeinfo")
							+ numQueryint("DCL",
									des.jiaMi("221"), "st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("4"),
									"st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("3"),
									"st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("2"),
									"youhushangxian")
							+ numQueryint("DCL",
									des.jiaMi("212"), "youhushangxian")
							+ numQueryint("DCL", des.jiaMi("3"),
									"youhushangxian")
							+ numQueryint("DCL",
									des.jiaMi("313"), "youhushangxian")
							+ numQueryint("DCL", des.jiaMi("21"),
									"gongdanweihu")
							+ numQueryint("DCL",
									des.jiaMi("213"), "gongdanweihu")
							+ numQueryint("DCL", des.jiaMi("2"),
									"gongdanweihu")
							+ numQueryint("DCL",
									des.jiaMi("234"), "gongdanweihu")
							+ numQueryint("DCL", des.jiaMi("3"),
									"gongdanweihu")
							+ numQueryint("DCL", des.jiaMi("2"),
									"red_enterletter")
							+ numQueryint("DCL", des.jiaMi("71"),
									"shgongdanweihu")
							+ numQueryint("DCL", des.jiaMi("31"),
									"gongdanweihu")) + ")";
			LinearLayout linearLayout2 = (LinearLayout) linearLayout1
					.getChildAt(0);
			TextView t = (TextView) linearLayout2.getChildAt(0);
			t.setText(num);

		}
	};

	BroadcastReceiver broadcastReceiver1 = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			// 接受从PlayerService发送过来的广播数据。
			// loadLayout();
			// TODO

			LinearLayout layoutAll = (LinearLayout) findViewById(R.id.layoutAll);
			LinearLayout linearLayout1 = ((LinearLayout) layoutAll
					.getChildAt(4));

			String num = "草稿箱"
					+ "("
					+ String.valueOf(numQueryint("DCL",
							des.jiaMi("0"), "st_storeinfo")
							+ numQueryint("DCL", 
									des.jiaMi("0"),"youhushangxian")
							+ numQueryint("DCL",
									des.jiaMi("0"), "red_enterletter")
							+ numQueryint("DCL",
									des.jiaMi("0"), "shgongdanweihu")
							+ numQueryint("DCL", 
									des.jiaMi("0"),"gongdanweihu")) + ")";
			LinearLayout linearLayout2 = (LinearLayout) linearLayout1
					.getChildAt(0);
			TextView t = (TextView) linearLayout2.getChildAt(0);
			t.setText(num);

		}
	};

	public OnClickListener getLayoutOnClick(final Back back,
			final LinearLayout layout, final List<String> strList,
			final List<OnClickListener> cameraListener) {
		return new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (back != null) {
					back.back();
				}
				if (nextLayoutChild != null) {
					nextLayoutChild.setVisibility(View.GONE);
					nextLayoutChild.removeAllViews();
				}
				nextLayoutChild = layout;
				if (nextLayout != v) {

					i = 0;

					ListLayout listLayout = new ListLayout(
							TuanDuiShangHuActivity.this, strList);

					layout.addView(listLayout.getLayout(cameraListener));

					layout.setVisibility(View.VISIBLE);
				} else {
					i++;
					if (i == 2) {
						i = 0;

						ListLayout listLayout = new ListLayout(
								TuanDuiShangHuActivity.this, strList);

						layout.addView(listLayout.getLayout(cameraListener));

						layout.setVisibility(View.VISIBLE);
					}
				}

				nextLayout = v;

			}
		};
	}

	class ListAdapter extends BaseAdapter {
		public List<Data1> mItemList; // 修饰符不能是private
		public Context mContext;

		public ListAdapter(Context con, List<Data1> itemList) {
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
			if (view == null) {
				LayoutInflater inflater = LayoutInflater
						.from(TuanDuiShangHuActivity.this);
				view = inflater.inflate(R.layout.listtable_item, null);
			}
			// int colorPos =position % colors.length;
			TextView listtable_tv1 = (TextView) view
					.findViewById(R.id.listtable_tv1);
			TextView listtable_tv2 = (TextView) view
					.findViewById(R.id.listtable_tv2);
			TextView listtable_tv3 = (TextView) view
					.findViewById(R.id.listtable_tv3);
			listtable_tv1.setText(mItemList.get(position).lx);
			listtable_tv2.setText(mItemList.get(position).name);
			listtable_tv3.setText(mItemList.get(position).time);
			listtable_tv1.setTextColor(Color.BLACK);
			listtable_tv2.setTextColor(Color.BLACK);
			listtable_tv3.setTextColor(Color.BLACK);

			return view;
		}

	}

	// 查询每个箱的件个数
	public String numQuery(String ziduan, String str) {
		String numstr = null;
		SQLiteDatabase db = null;
		Cursor cur = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					TuanDuiShangHuActivity.this, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query("st_storeinfo", null, ziduan + "=?",
					new String[] { str }, null, null, null);
			numstr = "(" + cur.getCount() + ")";

			cur.close();
			db.close();
		}

		catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：numQuery出错" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (db != null) {
				db.close();
			}
		}
		return numstr;

	}

	// 查询各个箱的数据
	public void dclQuery(String clomname, String clomstr, int numtemp) {

		Cursor cur = null;
		SQLiteDatabase db = null;
		int i = 0;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					TuanDuiShangHuActivity.this, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query("st_storeinfo", new String[] { "LX", "StEngageName",
					"TIME", "Id" }, clomname + "=?", new String[] { clomstr },
					null, null, null);

			int nameIndex = cur.getColumnIndex("LX");
			int phoneIndex = cur.getColumnIndex("StEngageName");
			int timeIndex = cur.getColumnIndex("TIME");
			int idIndex = cur.getColumnIndex("Id");

			contactsName[numtemp] = new String[cur.getCount()];
			contactsPhone[numtemp] = new String[cur.getCount()];
			contactsTime[numtemp] = new String[cur.getCount()];
			contactId[numtemp] = new String[cur.getCount()];

			if (cur.getCount() != 0) {
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {

					contactsName[1][i] = des.jieMI(cur.getString(nameIndex));
					contactsPhone[1][i] = des.jieMI(cur.getString(phoneIndex));
					contactsTime[1][i] = des.jieMI(cur.getString(timeIndex));
					contactId[1][i] = des.jieMI(cur.getString(idIndex));
					i++;
				}
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：dclQuery出错" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}

	public static void Query(Context con) {

		SQLiteDatabase db = null;
		Cursor cur = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query("st_storeinfo", new String[] { "Id" }, null, null,
					null, null, null);

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
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：Query出错" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}
	
	
	public static void Red_Query(Context con) {

		SQLiteDatabase db = null;
		Cursor cur = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query("red_enterletter", new String[] { "Id" }, null, null,
					null, null, null);

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
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：Query出错" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void setLayout(LinearLayout layoutAll,
			List<Map<String, List<String>>> strList,
			List<Map<String, List<OnClickListener>>> cameraListenerList,
			List<Back> backListenerList) {
		for (int i = 0; i < strList.size(); i++) {
			View convertView = LayoutInflater.from(this).inflate(
					R.layout.layout_list_all, null);
			Map<String, List<String>> strMap = strList.get(i);
			Map<String, List<OnClickListener>> cameraListenerMap = cameraListenerList
					.get(i);

			List<String> title = strMap.get("title");
			List<String> contextList = strMap.get("context");
			TextView testView = (TextView) convertView
					.findViewById(R.id.testView);
			testView.setText(title.get(0));
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

			params.setMargins(0, 10, 0, 0);
			convertView.setLayoutParams(params);
			List<OnClickListener> cameraListeners = cameraListenerMap
					.get("cameraListener");

			Back back = backListenerList.get(i);

			convertView.setOnClickListener(getLayoutOnClick(back,
					(LinearLayout) convertView.findViewById(R.id.layoutList),
					contextList, cameraListeners));
			layoutAll.addView(convertView);
		}
	}

	MyDatabaseHelper myHelper;
	String[] nameArrayToday = null;
	String[] timeArrayToday = null;
	String[] isomuxArrayToday = null;

	String[][] Id = null;
	List<String[]> draftList = new ArrayList<String[]>();
	List<String[]> sendtList = new ArrayList<String[]>();
	List<String[]> todayList = new ArrayList<String[]>();

	List<Data1> todayList1 = new ArrayList<Data1>();
	List<Data1> caodata = new ArrayList<Data1>();

	public void sendQueryyu1() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from st_storeinfo", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("221")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("StName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQueryyu1出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}

	public void sendQueryyu() {

		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from st_storeinfo", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("12")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("StName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQueryyu出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}
	public void redsendQuery() {

		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from red_enterletter", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("2")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("CompanyName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}
	
	public void sendQuery() {

		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from st_storeinfo", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("2")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("StName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}

	public void sendQueryjujian() {

		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from st_storeinfo", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("22")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("StName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQueryjujian出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}

	public void sendQuery1() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from st_storeinfo", null);

			// contactId[0]=new String[count];

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("0")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("StName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String id = (cursor.getString(cursor.getColumnIndex("Id")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;
					data.Id = id;
					caodata.add(data);

					String todayTime = GetDate.getDate().toString();
					if (time != null && todayTime != null) {
						int timeMonth = Integer.parseInt(time.substring(4, 6)
								.toString().replace("0", ""));
						int timeDay = Integer.parseInt(time.substring(6, 8));

						int todayMonth = Integer.parseInt(todayTime
								.substring(4, 6).toString().replace("0", ""));
						int todayDay = Integer.parseInt(todayTime.substring(6,
								8));
						int daynum = (todayMonth - timeMonth) * 30 + todayDay
								- timeDay;
						if (daynum >= 30) {
							caodata.remove(data);
							// 删除数据库
							if (isDatabaseExists(TuanDuiShangHuActivity.this,
									"techown.db")) {
								db = null;
								try {
									MyDatabaseHelper myHelper = new MyDatabaseHelper(
											TuanDuiShangHuActivity.this,
											"techown.db", Constant.tdshDBVer);
									db = myHelper.getWritableDatabase();
									db.delete("st_storeinfo", "Id" + "=?",
											new String[] { data.Id });
									db.close();
								} catch (Exception e) {
									if (db != null) {
										db.close();
									}
								}
							}
							// 删除数据库
							techown.shanghu.https.Log.Instance().WriteLog(
									"团队：草稿箱，超过30天的删除的件——" + data.name
											+ data.time);
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent);
						}
					}

					// if(!dc.validate12(data.time)){
					// caodata.remove(data);
					// }
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery1出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}

	public void sendQuery01() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu", null);

			// contactId[0]=new String[count];

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("0")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String id = (cursor.getString(cursor.getColumnIndex("Id")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;
					data.Id = id;
					caodata.add(data);
					String todayTime = GetDate.getDate().toString();
					if (data.time != null && todayTime != null) {
						int timeMonth = Integer.parseInt(time.substring(4, 6)
								.toString().replace("0", ""));
						int timeDay = Integer.parseInt(time.substring(6, 8));

						int todayMonth = Integer.parseInt(todayTime
								.substring(4, 6).toString().replace("0", ""));
						int todayDay = Integer.parseInt(todayTime.substring(6,
								8));
						int daynum = (todayMonth - timeMonth) * 30 + todayDay
								- timeDay;
						if (daynum >= 30) {
							caodata.remove(data);
							// 删除数据库
							if (isDatabaseExists(TuanDuiShangHuActivity.this,
									"techown.db")) {
								db = null;
								try {
									MyDatabaseHelper myHelper = new MyDatabaseHelper(
											TuanDuiShangHuActivity.this,
											"techown.db", Constant.tdshDBVer);
									db = myHelper.getWritableDatabase();
									db.delete("gongdanweihu", "Id" + "=?",
											new String[] { data.Id });
									db.close();
								} catch (Exception e) {
									if (db != null) {
										db.close();
									}
								}
							}
							// 删除数据库
							techown.shanghu.https.Log.Instance().WriteLog(
									"团队：草稿箱，超过30天的删除的件——" + data.name
											+ data.time);
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery01出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}

	public void sendQuery001() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from youhushangxian", null);

			// contactId[0]=new String[count];

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("0")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String id = (cursor.getString(cursor.getColumnIndex("Id")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;
					data.Id = id;
					caodata.add(data);
					String todayTime = GetDate.getDate().toString();
					if (time != null && todayTime != null) {
						int timeMonth = Integer.parseInt(time.substring(4, 6)
								.toString().replace("0", ""));
						int timeDay = Integer.parseInt(time.substring(6, 8));

						int todayMonth = Integer.parseInt(todayTime
								.substring(4, 6).toString().replace("0", ""));
						int todayDay = Integer.parseInt(todayTime.substring(6,
								8));
						int daynum = (todayMonth - timeMonth) * 30 + todayDay
								- timeDay;
						if (daynum >= 30) {
							caodata.remove(data);
							// 删除数据库
							if (isDatabaseExists(TuanDuiShangHuActivity.this,
									"techown.db")) {
								db = null;
								try {
									MyDatabaseHelper myHelper = new MyDatabaseHelper(
											TuanDuiShangHuActivity.this,
											"techown.db", Constant.tdshDBVer);
									db = myHelper.getWritableDatabase();
									db.delete("youhushangxian", "Id" + "=?",
											new String[] { data.Id });
									db.close();
								} catch (Exception e) {
									if (db != null) {
										db.close();
									}
								}
							}
							// 删除数据库
							techown.shanghu.https.Log.Instance().WriteLog(
									"团队：草稿箱，超过30天的删除的件——" + data.name
											+ data.time);
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery001出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}
	public void sendQuery0001() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from red_enterletter", null);

			// contactId[0]=new String[count];

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("0")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("CompanyName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String id = (cursor.getString(cursor.getColumnIndex("Id")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;
					data.Id = id;
					caodata.add(data);

					String todayTime = GetDate.getDate().toString();
					if (time != null && todayTime != null) {
						int timeMonth = Integer.parseInt(time.substring(4, 6)
								.toString().replace("0", ""));
						int timeDay = Integer.parseInt(time.substring(6, 8));

						int todayMonth = Integer.parseInt(todayTime
								.substring(4, 6).toString().replace("0", ""));
						int todayDay = Integer.parseInt(todayTime.substring(6,
								8));
						int daynum = (todayMonth - timeMonth) * 30 + todayDay
								- timeDay;
						if (daynum >= 30) {
							caodata.remove(data);
							// 删除数据库
							if (isDatabaseExists(TuanDuiShangHuActivity.this,
									"techown.db")) {
								db = null;
								try {
									MyDatabaseHelper myHelper = new MyDatabaseHelper(
											TuanDuiShangHuActivity.this,
											"techown.db", Constant.tdshDBVer);
									db = myHelper.getWritableDatabase();
									db.delete("red_enterletter", "Id" + "=?",
											new String[] { data.Id });
									db.close();
								} catch (Exception e) {
									if (db != null) {
										db.close();
									}
								}
							}
							// 删除数据库
							techown.shanghu.https.Log.Instance().WriteLog(
									"团队：草稿箱，超过30天的删除的件——" + data.name
											+ data.time);
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent);
						}
					}

					// if(!dc.validate12(data.time)){
					// caodata.remove(data);
					// }
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery1出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}
	public void sendQuery00001() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from shgongdanweihu", null);

			// contactId[0]=new String[count];

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("0")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("StName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String id = (cursor.getString(cursor.getColumnIndex("Id")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;
					data.Id = id;
					caodata.add(data);

					String todayTime = GetDate.getDate().toString();
					if (time != null && todayTime != null) {
						int timeMonth = Integer.parseInt(time.substring(4, 6)
								.toString().replace("0", ""));
						int timeDay = Integer.parseInt(time.substring(6, 8));

						int todayMonth = Integer.parseInt(todayTime
								.substring(4, 6).toString().replace("0", ""));
						int todayDay = Integer.parseInt(todayTime.substring(6,
								8));
						int daynum = (todayMonth - timeMonth) * 30 + todayDay
								- timeDay;
						if (daynum >= 30) {
							caodata.remove(data);
							// 删除数据库
							if (isDatabaseExists(TuanDuiShangHuActivity.this,
									"techown.db")) {
								db = null;
								try {
									MyDatabaseHelper myHelper = new MyDatabaseHelper(
											TuanDuiShangHuActivity.this,
											"techown.db", Constant.tdshDBVer);
									db = myHelper.getWritableDatabase();
									db.delete("shgongdanweihu", "Id" + "=?",
											new String[] { data.Id });
									db.close();
								} catch (Exception e) {
									if (db != null) {
										db.close();
									}
								}
							}
							// 删除数据库
							techown.shanghu.https.Log.Instance().WriteLog(
									"团队：草稿箱，超过30天的删除的件——" + data.name
											+ data.time);
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent);
						}
					}

					// if(!dc.validate12(data.time)){
					// caodata.remove(data);
					// }
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery00001出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}
	public void sendQuery21() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from st_storeinfo", null);

			// contactId[0]=new String[count];

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("3")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("StName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery21出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}
	

	public void sendQuery71() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from shgongdanweihu", null);

			// contactId[0]=new String[count];

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("71")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("StName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery71出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}

	public void sendQuery11() {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from st_storeinfo", null);

			// contactId[0]=new String[count];

			while (cursor.moveToNext()) {
				String flag = cursor.getString(cursor.getColumnIndex("DCL"));
				if (flag != null && flag.equals("4")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("StName")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					caodata.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery11出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

	}

	public void sendQuery3()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from youhushangxian", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("2")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery3出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void sendQueryyu3()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from youhushangxian", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("212")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQueryyu3出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void sendQueryyu31()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from youhushangxian", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("313")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQueryyu31出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void sendQuery31()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from youhushangxian", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("3")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery31出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void sendQuery4()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("2")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery4出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void sendQuery4111()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("31")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery4111出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void sendQuery411()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("21")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery411出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void sendQuery41()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("3")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQuery41出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void sendQueryyu41()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("234")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQueryyu41出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void sendQueryyu411()

	{
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			myHelper = new MyDatabaseHelper(TuanDuiShangHuActivity.this,
					"techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from gongdanweihu", null);

			while (cursor.moveToNext()) {
				String flag = des.jieMI(cursor.getString(cursor
						.getColumnIndex("DCL")));
				if (flag != null && flag.equals("213")) {

					String name = des.jieMI(cursor.getString(cursor
							.getColumnIndex("LX")));
					String StName = des.jieMI(cursor.getString(cursor
							.getColumnIndex("Stname")));
					String time = des.jieMI(cursor.getString(cursor
							.getColumnIndex("TIME")));
					String time1[] = time.split("");

					String newtime = time1[1] + time1[2] + time1[3] + time1[4]
							+ "-" + time1[5] + time1[6] + "-" + time1[7]
							+ time1[8] + " " + time1[9] + time1[10] + ":"
							+ time1[11] + time1[12];
					Data1 data = new Data1();
					data.lx = name;
					data.name = StName;
					data.time = newtime;

					todayList1.add(data);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：sendQueryyu411出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
	}

	public void quaryCityId() {

		// 机构查询
		agencyId = queryAgencyID(TuanDuiShangHuActivity.username);// 员工号--〉机构ID
		techown.shanghu.https.Log.Instance().WriteLog("机构agencyId==="+agencyId);
		System.out.println("agencyId==="+agencyId);
		agencyCityAndIdMap = cityQueryNameAndId(agencyId);//机构大ID--〉城市名称和城市Id
		//agencycityname = cityQueryName(agencyId);
		List<String> cityNameList = new ArrayList<String>(agencyCityAndIdMap.keySet());
		agencycityname =  commonUtil.listToStringArray(cityNameList);//机构大ID--〉城市名称
		
		agencyareaId = queryAreaID(agencyId);// 机构ID--〉机构大区ID
		areaName2 = queryAreaName(agencyareaId);// 机构大区ID--〉大区名称
		areastr = areaQuery();// 所有大区名称

		cityId = queryCity(TuanDuiShangHuActivity.username);// 员工号---〉城市ID
		cityname = queryCityname(cityId);// 城市ID---〉城市名称
		String ProvinceId = queryShengname(cityname);// 城市名称--〉省ID
		ProvinceName = queryProvinceName(ProvinceId);// 省ID--〉省名称
		String areaId = queryAreaId(cityname);// 城市名称--〉大区ID
		areaName = queryAreaName(areaId);// 大区ID--〉大区名称
	}

	
	// 机构大ID--〉城市名称和城市Id
		public Map cityQueryNameAndId(String agencyId) {
			Map map = new HashMap();
			Cursor cur = null;
			SQLiteDatabase sld = null;
			try {
				Context friendContext = createPackageContext("techown.login",
						Context.CONTEXT_IGNORE_SECURITY);
				myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
						Constant.loginDBVer);

				int i = 0;
				sld = myHelper.getWritableDatabase();
				cur = sld.query("t_city", new String[] { "Id","Name" }, "AgencyId"
						+ "=?", new String[] { des.jiaMi(agencyId) }, null, null,
						null);
				//str = new String[cur.getCount()];
				//int aa = cur.getCount();
			//	System.out.println("aa==========" + aa);
				if (cur.getCount() != 0) {
					while (cur.moveToNext()) {
						String name = des
								.jieMI(cur.getString(cur.getColumnIndex("Name")));
						String id = des
								.jieMI(cur.getString(cur.getColumnIndex("Id")));
						map.put(name, id);
					}
				}
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"团队：查询市cityQuery:" + e.getMessage());
			} finally {
				if (cur != null) {
					cur.close();
				}
				if (sld != null) {
					sld.close();
				}
			}
			return map;
		}

	// 机构大ID--〉城市名称
	public String[] cityQueryName(String agencyId) {
		String[] str = null;
		Cursor cur = null;
		SQLiteDatabase sld = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			int i = 0;
			sld = myHelper.getWritableDatabase();
			cur = sld.query("t_city", new String[] { "Name" }, "AgencyId"
					+ "=?", new String[] { des.jiaMi(agencyId) }, null, null,
					null);
			str = new String[cur.getCount()];
			int aa = cur.getCount();
			System.out.println("aa==========" + aa);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des
							.jieMI(cur.getString(cur.getColumnIndex("Name")));
					i++;
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询市cityQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return str;
	}

	public String[] areaQuery() {
		String[] str = null;
		Cursor cur = null;
		SQLiteDatabase sld = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			int i = 0;
			sld = myHelper.getWritableDatabase();

			cur = sld.query("t_area", new String[] { "Name" }, null, null,
					null, null, null);
			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des
							.jieMI(cur.getString(cur.getColumnIndex("Name")));
					i++;
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询市cityQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return str;
	}

	// 机构大区ID--〉机构大区城市名称
	public String[] cityQuery(String agencyareaId) {
		String[] str = null;
		Cursor cur = null;
		SQLiteDatabase sld = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			int i = 0;
			sld = myHelper.getWritableDatabase();

			cur = sld.query("t_city", new String[] { "Name" }, "AreaId" + "=?",
					new String[] { des.jiaMi(agencyareaId) }, null, null, null);
			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des
							.jieMI(cur.getString(cur.getColumnIndex("Name")));
					i++;
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询市cityQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return str;
	}

	// 机构ID--〉城市名称
	public String queryCityName(String agencyId) {
		String AgencyareaId = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select areaId from agency where id = ?",
					new String[] { des.jiaMi(agencyId) });
			if (cursor.getCount() == 1) {
				cursor.moveToLast();
				AgencyareaId = des.jieMI(cursor.getString(0));
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryCity出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return AgencyareaId;
	}

	// 机构ID--〉大区ID
	public String queryAreaID(String agencyId) {
		String AgencyareaId = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select areaId from agency where id = ?",
					new String[] { des.jiaMi(agencyId) });
			if (cursor.getCount() == 1) {
				cursor.moveToLast();
				AgencyareaId = des.jieMI(cursor.getString(0));
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryCity出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return AgencyareaId;
	}

	// 员工号--〉机构ID
	public String queryAgencyID(String username) {
		String AgencyId = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery(
					"select AgencyId from t_user where UserId = ?",
					new String[] { des.jiaMi(username) });
			System.out.println("aaaaaaaaaaaa===" + des.jiaMi(username));
			if (cursor.getCount() == 1) {
				cursor.moveToLast();
				AgencyId = des.jieMI(cursor.getString(0));
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryCity出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return AgencyId;
	}

	// 员工号---〉城市ID
	public String queryCity(String username) {
		String cityid = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select CityId from t_user where UserId = ?",
					new String[] { des.jiaMi(username) });
			if (cursor.getCount() == 1) {
				cursor.moveToLast();
				cityid = des.jieMI(cursor.getString(0));
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryCity出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return cityid;
	}

	// 城市名称--〉省ID
	public String queryShengname(String username) {
		String cityid = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery(
					"select ProvinceName from t_city where Name = ?",
					new String[] { des.jiaMi(username) });
			if (cursor.getCount() != 0) {
				cursor.moveToLast();
				cityid = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryShengname出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return cityid;
	}

	// 城市ID---〉城市名称
	public String queryCityname(String username) {
		String cityid = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select Name from t_city where Id = ?",
					new String[] { des.jiaMi(username) });
			if (cursor.getCount() != 0) {
				cursor.moveToLast();
				cityid = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryCityname出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}
		return cityid;
	}

	// 城市名称--〉大区ID
	public String queryAreaId(String cityname) {
		String AreaId = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select AreaId from t_city where Name = ?",
					new String[] { des.jiaMi(cityname) });
			if (cursor.getCount() != 0) {
				cursor.moveToLast();
				AreaId = des.jieMI(cursor.getString(0));
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryAreaId出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}

		}

		return AreaId;
	}

	// 省ID--〉省名称
	public String queryProvinceName(String AreaId) {

		String AreaName = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select Name from t_province where Id = ?",
					new String[] { des.jiaMi(AreaId) });
			if (cursor.getCount() == 1) {
				cursor.moveToLast();
				AreaName = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryProvinceName出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return AreaName;
	}

	// 大区名称--〉大区ID
	public String queryAreaIdName(String AreaName) {

		String AreaID = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select Id from t_area where Name = ?",
					new String[] { des.jiaMi(AreaName) });
			if (cursor.getCount() == 1) {
				cursor.moveToLast();
				AreaID = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryAreaName出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return AreaID;
	}

	// 大区ID--〉大区名称
	public String queryAreaName(String AreaId) {

		String AreaName = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select Name from t_area where Id = ?",
					new String[] { des.jiaMi(AreaId) });
			if (cursor.getCount() != 0) {
				cursor.moveToLast();
				AreaName = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryAreaName出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return AreaName;
	}

	public static String username;
	public static String imei;
	public static String indenty;
	public static String chinaname;
	public static String city;
	public static String prov;

	public void getdata(Context con) {
		Context friendContext = null;
		String[] str = null;
		Cursor cur = null;
		SQLiteDatabase sld = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			str = new String[6];
			sld = myHelper.getWritableDatabase();
			cur = sld.query("EntityUser", new String[] { "username", "imei",
					"identity", "chinaname", "city", "prov" }, null, null,
					null, null, null);
			while (cur.moveToNext()) {
				for (int i = 0; i < 6; i++) {
					if (cur.getString(i) != null) {
						str[i] = cur.getString(i);
					}
				}

			}

			username = str[0];
			imei = str[1];
			indenty = str[2];
			chinaname = str[3];
			city = str[4].substring(0, 2);
			prov = str[5];
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：getdata出错" + e.getMessage());
		} finally {

			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}

		}
	}

	public void deleteAllFiles(String FileName){
		String sdcardPath = "/mnt/sdcard/TJB/TuanDui/";
		File fl = new File(sdcardPath+FileName);
		System.out.println("清空文件大小"+FileName+"==="+fl.length());
		techown.shanghu.https.Log.Instance().WriteLog("清空文件大小"+FileName+"==="+fl.length());
		delFolder(sdcardPath+FileName);
	}
	
	//删除文件夹下所有文件
	public void delFolder(String folderPath){
		try{
			//删除完里面所有内容
			delAllFile(folderPath);
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			//删除空文件夹
			myFilePath.delete();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean delAllFile(String path){
		boolean flag = false;
		File file = new File(path);
		if(!file.exists()){
			return flag;
		}
		if(!file.isDirectory()){
			return flag;
		}
		String[] tmpList = file.list();
		File tmp = null;
		
		for(int i=0; i<tmpList.length; i++){
			if(path.endsWith(File.separator)){
				tmp = new File(path+tmpList[i]);
			}
			else{
				tmp = new File(path+File.separator+tmpList[i]);
			}
			
			if(tmp.isFile()){
				tmp.delete();
			}
			if(tmp.isDirectory()){
				delAllFile(path+"/"+tmpList[i]);
				delFolder(path+"/"+tmpList[i]);
				flag = true;
			}
		}
		return flag;
	}
	
	// 查询每个箱的件个数
	public int numQueryint(String ziduan, String str, String tablename) {
		int numstr=0;
		SQLiteDatabase db = null;
		Cursor cur = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					TuanDuiShangHuActivity.this, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query(tablename, null, ziduan + "=?",
					new String[] { str }, null, null, null);
			numstr = cur.getCount();

			cur.close();
			db.close();
		}

		catch (Exception e) {
			if (cur != null) {
				cur.close();
			}
			if (db != null) {
				db.close();
			}
			// Toast.makeText(this, "数据库错误："+e.toString(),
			// Toast.LENGTH_SHORT).show();;
		}
		return numstr;

	}
	
	
	// 查询每个箱的件个数
	public String numQuery(String ziduan, String str, String tablename) {
		String numstr = null;
		SQLiteDatabase db = null;
		Cursor cur = null;
		try {
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					TuanDuiShangHuActivity.this, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cur = db.query(tablename, null, ziduan + "=?",
					new String[] { str }, null, null, null);
			numstr = String.valueOf(cur.getCount());

			cur.close();
			db.close();
		}

		catch (Exception e) {
			if (cur != null) {
				cur.close();
			}
			if (db != null) {
				db.close();
			}
			// Toast.makeText(this, "数据库错误："+e.toString(),
			// Toast.LENGTH_SHORT).show();;
		}
		return numstr;

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder ab = new AlertDialog.Builder(
					TuanDuiShangHuActivity.this);
			ab.setTitle("提示");
			ab.setMessage("是否退出程序");
			ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			ab.setPositiveButton// 为对话框设置按钮
			("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					String aa = String.valueOf(numQueryint("DCL",
							des.jiaMi("2"), "st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("12"),
									"st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("22"),
									"st_storeinfo")
							+ numQueryint("DCL",
									des.jiaMi("221"), "st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("4"),
									"st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("3"),
									"st_storeinfo")
							+ numQueryint("DCL", des.jiaMi("2"),
									"youhushangxian")
							+ numQueryint("DCL",
									des.jiaMi("212"), "youhushangxian")
							+ numQueryint("DCL", des.jiaMi("3"),
									"youhushangxian")
							+ numQueryint("DCL",
									des.jiaMi("313"), "youhushangxian")
							+ numQueryint("DCL", des.jiaMi("21"),
									"gongdanweihu")
							+ numQueryint("DCL",
									des.jiaMi("213"), "gongdanweihu")
							+ numQueryint("DCL", des.jiaMi("2"),
									"gongdanweihu")
							+ numQueryint("DCL",
									des.jiaMi("234"), "gongdanweihu")
							+ numQueryint("DCL", des.jiaMi("3"),
									"gongdanweihu")
							+ numQueryint("DCL", des.jiaMi("31"),
									"gongdanweihu"));
					System.out.println(aa);
					if (!aa.equals("0")) {
						TuanDuiShangHuActivity.this.finish();
						techown.shanghu.https.Log.Instance().WriteLog(
								"业代退出团队商户");
						System.out.println("aaaaaaaaaaaaa");
					} else {
						Intent it = new Intent(
								"techown.shanghu.https.PostService");
						TuanDuiShangHuActivity.this.stopService(it);
						TuanDuiShangHuActivity.this.finish();
						// android.os.Process.killProcess(android.os.Process.myPid());
						// System.exit(0);
						techown.shanghu.https.Log.Instance().WriteLog(
								"业代退出团队商户");
						System.out.println("bbbbbbbbbbbbbbb");
					}
				}
			});
			ab.create().show();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	public boolean isDatabaseExists(Context context, String dbName) {

		File dbFile = context.getDatabasePath(dbName);
		if (dbFile.exists()) {
			return true;
		}
		return false;
	}

	// lxp
	public void saveDBVer(String ver) {
		FileOutputStream fos = null;
		String fileName = "tuanduishanghu.txt";

		try {
			FileUtils fu = new FileUtils();
			fu.createSDDir("TJB/ver/");
			if (fu.isFileExist("TJB/ver/" + fileName)) {
				fu.deleteSDFILE("TJB/ver/" + fileName);
			}
			File f = fu.createSDFILE("TJB/ver/" + fileName);
			fos = new FileOutputStream(f);
			byte[] buf = ver.getBytes();
			fos.write(buf);
		} catch (Exception e) {

			techown.shanghu.https.Log.Instance().WriteLog("数据库版本号存储异常");
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
			}
		}
	}

	public int getLoginDBVer(String path) {

		File f = null;
		// f= new File("/sdcard/TJB/ver/login.txt");//这是对应文件名
		StringBuilder text = new StringBuilder();
		f = new File(path);// 这是对应文件名
		if (f.exists()) {
			InputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(f));
			} catch (FileNotFoundException e3) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("数据库版本号读取异常");
			}
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(in, "gb2312"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("数据库版本号读取异常");
			}
			String tmp;

			try {
				while ((tmp = br.readLine()) != null) {
					// 在这对tmp操作
					text.append(tmp);

				}
				br.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("数据库版本号读取异常");

			}
		} else {
			text.append("1");
		}
		return Integer.parseInt(text.toString());

	}
	
	private void getmodel() {
		// TODO Auto-generated method stub
		Build b = new Build();
		Constant.model = b.MODEL;
	}

	// lxp

	private void get() {
		sendQuery1();
		sendQuery01();
		sendQuery001();
	}
}