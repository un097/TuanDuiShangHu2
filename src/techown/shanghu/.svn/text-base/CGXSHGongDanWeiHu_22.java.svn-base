package techown.shanghu;

import org.json.JSONObject;

import techown.shanghu.biaoge.ActionCode;
import techown.shanghu.biaoge.AlgorithmConvert;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetData;
import techown.shanghu.photo.TakePhotoActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 商户信息录入
 */
public class CGXSHGongDanWeiHu_22 extends Activity {

	protected static final String DialogUtil = null;
	DES des = new DES();
	EditDialog log = new EditDialog();
	DBUtil DB = new DBUtil();
	GetData getDasta = new GetData();
	private int sum = 0;
	private TextView tv_shName, tv_shNameBZ, tv_shAddress, tv_shAddressBZ,
			tv_shPhoneNumBZ, tv_shPhoneNum, tv_shPosWorkBZ, tv_sbNumBZ,
			tv_sbNum, tv_sbAdd;
	private LinearLayout linear_shAddress, linear_shName, linear_shPhoneNum,
			linear_poswork, linear_sbNum, linear_sbAdd, linear_parent_sbNum,
			linear_parent_sbAdd;
	private RadioGroup group_shName;
	private RadioButton radio_shName_yes;
	private RadioButton radio_shName_no;

	private RadioGroup group_shAddress;
	private RadioButton radio_shAddress_yes;
	private RadioButton radio_shAddress_no;

	private RadioGroup group_shPhoneNum;
	private RadioButton radio_shPhoneNum_yes;
	private RadioButton radio_shPhoneNum_no;

	private RadioGroup group_pos_wrok;
	private RadioButton radio_pos_work_yes;
	private RadioButton radio_pos_work_no;

	private RadioGroup group_sbNum;
	private RadioButton radio_sbNum_yes;
	private RadioButton radio_sbNum_no;

	private Button btn_sbAdd;
	private Button btn_pre;
	private Button btn_next;
	public static String[] tempstr1;

	public static  boolean flagfirst =true;
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_gongdanweihu2);
		initView();
		MyLis lis = new MyLis();
		//btn_pre.setOnClickListener(lis);
		btn_pre.setVisibility(View.INVISIBLE);
		btn_next.setOnClickListener(lis);
		
		try {

			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					CGXSHGongDanWeiHu_22.this, "techown.db", Constant.tdshDBVer);
			SQLiteDatabase db = myHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery(
					"select * from shgongdanweihu where id==?",
					new String[] { TuanDuiShangHuActivity.tmpId8 });
			while (cursor.moveToNext()) {
				try {

					tv_shName.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("shName"))));
					String lable_NameCheck = des.jieMI(cursor.getString(cursor
							.getColumnIndex("shNameCheck")));
					if("1".equals(lable_NameCheck)){
						radio_shName_yes.setChecked(true);
					}else if("2".equals(lable_NameCheck)){
						radio_shName_no.setChecked(true);
					}
					tv_shNameBZ.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("shNameContent"))));
					
					tv_shAddress.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("shAddress"))));
					String lable_AddressCheck = des.jieMI(cursor.getString(cursor
							.getColumnIndex("shAddressCheck")));
					if("1".equals(lable_AddressCheck)){
						radio_shAddress_yes.setChecked(true);
					}else if("2".equals(lable_AddressCheck)){
						radio_shAddress_no.setChecked(true);
					}
					tv_shAddressBZ.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("shAddressComment"))));
					
					tv_shPhoneNum.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("shPhoneNum"))));
					String lable_PhoneNumCheck = des.jieMI(cursor.getString(cursor
							.getColumnIndex("shPhoneNumCheck")));
					if("1".equals(lable_PhoneNumCheck)){
						radio_shPhoneNum_yes.setChecked(true);
					}else if("2".equals(lable_PhoneNumCheck)){
						radio_shPhoneNum_no.setChecked(true);
					}
					tv_shPhoneNumBZ.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("shPhoneNumComment"))));
					
					String lable_posWorkCheck = des.jieMI(cursor.getString(cursor
							.getColumnIndex("posIsWork")));
					if("1".equals(lable_posWorkCheck)){
						radio_pos_work_yes.setChecked(true);
					}else if("2".equals(lable_posWorkCheck)){
						radio_pos_work_no.setChecked(true);
					}
					tv_shPosWorkBZ.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("posIsWorkComment"))));
					
					String[] sbNumInfo = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("sbNumFrom")))).split(";");
					String[] sbCheckInfo = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("sbNumSelect")))).split(";");
					String[] sbBZInfo = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("sbNumComment")))).split(";",-1);
					for(int i=0; i<sbNumInfo.length;i++){
						if(i==0){
							tv_sbNum.setText(sbNumInfo[i]);
							tv_sbNumBZ.setText(sbBZInfo[i]);
							
							if("1".equals(sbCheckInfo[i])){
								radio_sbNum_yes.setChecked(true);
							}else if("2".equals(sbCheckInfo[i])){
								radio_sbNum_no.setChecked(true);
							}
						}else{
							View view = LayoutInflater.from(
									CGXSHGongDanWeiHu_22.this).inflate(
									R.layout.gongdan2_sbnum_item, null);
							linear_parent_sbNum.addView(view);
							
							
							final LinearLayout linearlayoutItem = (LinearLayout) view
									.findViewById(R.id.linearlayout_gongdan2_item);
							
							RadioButton sbCheckYes = (RadioButton)view.findViewById(R.id.radio_yes_gongdan2_sbNum_item);
							RadioButton sbCheckNo = (RadioButton)view.findViewById(R.id.radio_no_gongdan2_sbNum_item);
							final TextView tv_sbNumbz = (TextView) view
									.findViewById(R.id.tv_gongdan2_sbNum_beizhu_item);
							tv_sbNumbz.setOnClickListener(new OnClickListener() {
								
								public void onClick(View v) {
									// TODO Auto-generated method stub
									log.Dialog(
											CGXSHGongDanWeiHu_22.this,
											linearlayoutItem,
											tv_sbNumbz,
											R.id.tv_gongdan2_sbNum_beizhu_item,
											"请输入商编终端号备注");
								}
							});
							
							TextView tv_sbNum = (TextView) view
									.findViewById(R.id.tv_gongdan2_sbNum_item);
							
							if("1".equals(sbCheckInfo[i])){
								sbCheckYes.setChecked(true);
							}else if("2".equals(sbCheckInfo[i])){
								sbCheckNo.setChecked(true);
							}
							
							tv_sbNum.setText(sbNumInfo[i]);
							tv_sbNumbz.setText(sbBZInfo[i]);
							
						}
					}
					
					String[] sbAddInfo = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("sbNumAdd")))).split(";");
					for (int i = 0; i < sbAddInfo.length; i++) {
						if(i ==0 ){
							tv_sbAdd.setText(sbAddInfo[i]);
						}else{
							final View view = LayoutInflater.from(
									CGXSHGongDanWeiHu_22.this).inflate(
									R.layout.add_sbnum_item, null);
							
							linear_parent_sbAdd.addView(view);
							
							TextView tv_sbadd = (TextView) view
									.findViewById(R.id.tv_gongdan2_sbadd_item);
							tv_sbadd.setText(sbAddInfo[i]);
						}
					}
					
				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队CGXSHGongDanWeiHu_22:" + e.getMessage());
				} finally {
					if (cursor != null) {
						cursor.close();
					}
					if (db != null) {
						db.close();
					}
				}

			}
			tv_shPosWorkBZ.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(CGXSHGongDanWeiHu_22.this, linear_poswork,
							tv_shPosWorkBZ, R.id.tv_gongdan2_pos_work_beizhu,
							"请输入pos机是否正常工作备注");
				}
			});
			tv_shNameBZ.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(CGXSHGongDanWeiHu_22.this, linear_shName,
							tv_shNameBZ, R.id.tv_gongdan2_shName_beizhu,
							"请输入商户名称备注");
				}
			});
			tv_shPhoneNumBZ.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(CGXSHGongDanWeiHu_22.this, linear_shPhoneNum,
							tv_shPhoneNumBZ,
							R.id.tv_gongdan2_shPhoneNum_beizhu, "请输入商户联系电话备注");
				}
			});
			tv_sbNumBZ.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(CGXSHGongDanWeiHu_22.this, linear_sbNum, tv_sbNumBZ,
							R.id.tv_gongdan2_sbNum_beizhu, "请输入商编终端号备注");
				}
			});
			tv_sbAdd.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(CGXSHGongDanWeiHu_22.this, linear_sbAdd, tv_sbAdd,
							R.id.tv_gongdan2_sbadd, "请输入新增商编终端号");
				}
			});
			tv_shAddressBZ.setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_DOWN:
						log.Dialog(CGXSHGongDanWeiHu_22.this, linear_shAddress,
								tv_shAddressBZ,
								R.id.tv_gongdan2_shAddress_beizhu, "请输入商户地址备注");
						break;
					default:
						break;
					}

					return false;
				}
			});
			// 商编增加按钮
			btn_sbAdd.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (sum != 4) {
						final View view = LayoutInflater.from(
								CGXSHGongDanWeiHu_22.this).inflate(
								R.layout.add_sbnum_item, null);

						linear_parent_sbAdd.addView(view);
						sum++;
						final TextView tv_item_sbadd = (TextView) view
								.findViewById(R.id.tv_gongdan2_sbadd_item);
						tv_item_sbadd.setOnClickListener(new OnClickListener() {

							public void onClick(View v) {
								// TODO Auto-generated method stub
								log.Dialog(CGXSHGongDanWeiHu_22.this, linear_sbAdd,
										tv_item_sbadd, R.id.tv_gongdan2_sbadd_item,
										"请输入新增商编终端号");
							}
						});
						Button btn_delete = (Button) view
								.findViewById(R.id.btn_gongdan2_sbadd_item);
						btn_delete.setOnClickListener(new OnClickListener() {

							public void onClick(View v) {
								// TODO Auto-generated method stub
								linear_parent_sbAdd.removeView(view);
								sum--;
							}
						});
					} else {
						Toast.makeText(CGXSHGongDanWeiHu_22.this, "只能添加5个商编", 0)
								.show();
					}
				}
			});
			
			if("".equals(tv_sbNum.getText().toString())){
				group_sbNum.setClickable(false);
				radio_sbNum_no.setClickable(false);
				radio_sbNum_yes.setClickable(false);
				tv_sbNumBZ.setClickable(false);
			}
			
			
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队CGXSHGongDanWeiHu_22：" + e.getMessage());
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		tv_shName = (TextView) findViewById(R.id.tv_gongdan2_shName);
		tv_shNameBZ = (TextView) findViewById(R.id.tv_gongdan2_shName_beizhu);
		group_shName = (RadioGroup) findViewById(R.id.group_gongdan2_shName);
		radio_shName_yes = (RadioButton) findViewById(R.id.radio_yes_gongdan2_shName);
		radio_shName_no = (RadioButton) findViewById(R.id.radio_no_gongdan2_shName);
		linear_shName = (LinearLayout) findViewById(R.id.linearlayout_gongdan2_shName);

		tv_shAddress = (TextView) findViewById(R.id.tv_gongdan2_shAddress);
		tv_shAddressBZ = (TextView) findViewById(R.id.tv_gongdan2_shAddress_beizhu);
		group_shAddress = (RadioGroup) findViewById(R.id.group_gongdan2_shAddress);
		radio_shAddress_yes = (RadioButton) findViewById(R.id.radio_yes_gongdan2_shAddress);
		radio_shAddress_no = (RadioButton) findViewById(R.id.radio_no_gongdan2_shAddress);
		linear_shAddress = (LinearLayout) findViewById(R.id.linearlayout_gongdan2_shAddress);

		tv_shPhoneNum = (TextView) findViewById(R.id.tv_gongdan2_shPhoneNum);
		tv_shPhoneNumBZ = (TextView) findViewById(R.id.tv_gongdan2_shPhoneNum_beizhu);
		group_shPhoneNum = (RadioGroup) findViewById(R.id.group_gongdan2_shPhoneNum);
		radio_shPhoneNum_yes = (RadioButton) findViewById(R.id.radio_yes_gongdan2_shPhoneNum);
		radio_shPhoneNum_no = (RadioButton) findViewById(R.id.radio_no_gongdan2_shPhoneNum);
		linear_shPhoneNum = (LinearLayout) findViewById(R.id.linearlayout_gongdan2_shPhoneNum);

		tv_shPosWorkBZ = (TextView) findViewById(R.id.tv_gongdan2_pos_work_beizhu);
		group_pos_wrok = (RadioGroup) findViewById(R.id.group_gongdan2_pos_work);
		radio_pos_work_yes = (RadioButton) findViewById(R.id.radio_yes_gongdan2_pos_work);
		radio_pos_work_no = (RadioButton) findViewById(R.id.radio_no_gongdan2_pos_work);
		linear_poswork = (LinearLayout) findViewById(R.id.linearlayout_gongdan2_poswork);

		tv_sbNum = (TextView) findViewById(R.id.tv_gongdan2_sbNum);
		tv_sbNumBZ = (TextView) findViewById(R.id.tv_gongdan2_sbNum_beizhu);
		group_sbNum = (RadioGroup) findViewById(R.id.group_gongdan2_sbNum);
		radio_sbNum_yes = (RadioButton) findViewById(R.id.radio_yes_gongdan2_sbNum);
		radio_sbNum_no = (RadioButton) findViewById(R.id.radio_no_gongdan2_sbNum);
		linear_sbNum = (LinearLayout) findViewById(R.id.linearlayout_gongdan2_sbNum);

		tv_sbAdd = (TextView) findViewById(R.id.tv_gongdan2_sbadd);
		btn_sbAdd = (Button) findViewById(R.id.btn_gongdan2_sbadd);
		linear_sbAdd = (LinearLayout) findViewById(R.id.linearlayout_gongdan2_sbAdd);

		linear_parent_sbNum = (LinearLayout) findViewById(R.id.linearlayout_parent_sbNum);
		linear_parent_sbAdd = (LinearLayout) findViewById(R.id.linearlayout_parent_sbAdd);
		btn_pre = (Button) findViewById(R.id.btn_gongdan2_pre);
		btn_next = (Button) findViewById(R.id.btn_gongdan2_next);

	}

	/*public void init() {
		if (!requeststr1.equals("")) {

			try {
				JSONObject demoJson1 = new JSONObject(requeststr1);
				if (demoJson1.getString("rspCode").equals("000000")) {
					tempstr1 = new String[7];
					tempstr1[0] = demoJson1.getString("taskId");// 工单Id
					tempstr1[1] = demoJson1.getString("MerId");// 商户Id
					tempstr1[2] = demoJson1.getString("MerName");// 商户名称
					tempstr1[3] = demoJson1.getString("MerAddress");// 商户地址
					tempstr1[4] = demoJson1.getString("merTel");// 商户电话
					tempstr1[5] = demoJson1.getString("misInfoList");// 商编终端号集合
					tempstr1[6] = demoJson1.getString("MerSn");// SN集合
					tv_shName.setText(tempstr1[2]);
					tv_shAddress.setText(tempstr1[3]);
					tv_shPhoneNum.setText(tempstr1[4]);

					String[] misInfo = tempstr1[5].split(","); // 商编终端多个
					for (int i = 0; i < misInfo.length; i++) {
						if (i == 0) {
							tv_sbNum.setText(misInfo[i]);
						} else {
							View view = LayoutInflater.from(
									CGXSHGongDanWeiHu_22.this).inflate(
									R.layout.gongdan2_sbnum_item, null);

							linear_parent_sbNum.addView(view);
							final LinearLayout linearlayoutItem = (LinearLayout) view
									.findViewById(R.id.linearlayout_gongdan2_item);
							TextView tv_sbNum = (TextView) view
									.findViewById(R.id.tv_gongdan2_sbNum_item);
							tv_sbNum.setText(misInfo[i]);
							final TextView tv_sbNumBZ = (TextView) view
									.findViewById(R.id.tv_gongdan2_sbNum_beizhu_item);
							tv_sbNumBZ
									.setOnClickListener(new OnClickListener() {

										public void onClick(View v) {
											// TODO Auto-generated method stub
											log.Dialog(
													CGXSHGongDanWeiHu_22.this,
													linearlayoutItem,
													tv_sbNumBZ,
													R.id.tv_gongdan2_sbNum_beizhu_item,
													"请输入商编终端号备注");
										}
									});
						}

					}
				}
				
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"团队HeYueQianDing_2:init()+" + e.getMessage());
			}
		}
	}
*/
	private void exitTask(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要退出此次工单填写？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
						overridePendingTransition(R.anim.rightin,
								R.anim.rightout);

					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}

	public void saveData() {
		try {
			
					
			String[] clomname = new String[] {"shName","shNameCheck","shNameContent",
							"shAddress","shAddressCheck","shAddressComment",
							"shPhoneNum","shPhoneNumCheck","shPhoneNumComment",
							"posIsWork","posIsWorkComment","sbNumFrom",
							"sbNumSelect","sbNumComment","sbNumAdd"};
					
			String[] clomstr = new String[clomname.length];
			//商户名称
			clomstr[0] = tv_shName.getText().toString();
			//clomstr[0] = tempstr1[2];
			if(radio_shName_yes.isChecked()){
				clomstr[1] = "1";
			}else{
				clomstr[1] = "2";
			}
			clomstr[2] = tv_shNameBZ.getText().toString();
			
			//商户地址
			clomstr[3] = tv_shAddress.getText().toString();
			//clomstr[3] = tempstr1[3];
			if(radio_shAddress_yes.isChecked()){
				clomstr[4] = "1";
			}else{
				clomstr[4] = "2";
			}
			clomstr[5] = tv_shAddressBZ.getText().toString();
			
			//联系电话
			//clomstr[6] = tempstr1[4];
			clomstr[6] = tv_shPhoneNum.getText().toString();
			if(radio_shPhoneNum_yes.isChecked()){
				clomstr[7] = "1";
			}else{
				clomstr[7] = "2";
			}
			clomstr[8] = tv_shPhoneNumBZ.getText().toString();

			//pos是否正常工作
			if(radio_pos_work_yes.isChecked()){
				clomstr[9] = "1";
			}else{
				clomstr[9] = "2";
			}
			clomstr[10] = tv_shPosWorkBZ.getText().toString();
			
			//获取商编终端对应的数据
			String tempSbNum="",tempSbCheck="",tempSbBZ="";
			for (int i = 0; i < linear_parent_sbNum.getChildCount(); i++) {
				if (i == 0) {
					tempSbNum = tv_sbNum.getText().toString()+";";
					tempSbBZ = tv_sbNumBZ.getText().toString();
					if (group_sbNum.getCheckedRadioButtonId() == R.id.radio_no_gongdan2_sbNum) {
						tempSbCheck ="2;";
					}else if(group_sbNum.getCheckedRadioButtonId() == R.id.radio_yes_gongdan2_sbNum) {
						tempSbCheck ="1;";
					}else{
						tempSbCheck ="3;";
					}
				} else {
					LinearLayout linear = (LinearLayout) linear_parent_sbNum
							.getChildAt(i);
					RadioGroup radioGroup = (RadioGroup) linear
							.findViewById(R.id.group_gongdan2_sbNum_item);
					TextView tv_sbNumbz = (TextView) linear
							.findViewById(R.id.tv_gongdan2_sbNum_beizhu_item);
					TextView tv_sbNum = (TextView) linear
							.findViewById(R.id.tv_gongdan2_sbNum_item);
					
					tempSbNum += tv_sbNum.getText().toString()+";";
					tempSbBZ +=";"+tv_sbNumbz.getText().toString();
					if (radioGroup.getCheckedRadioButtonId() == R.id.radio_no_gongdan2_sbNum_item) {
						tempSbCheck += "2;";
					}else{
						tempSbCheck += "1;";
					}
				}
			}
			
			clomstr[11] = tempSbNum;
			clomstr[12] = tempSbCheck;
			clomstr[13] = tempSbBZ;

			
			//新增商编
			String tempsbNum = "";
			for (int i = 0; i < linear_parent_sbAdd.getChildCount(); i++) {
				if(i ==0 ){
					tempsbNum = tv_sbAdd.getText().toString();
				}else{
					LinearLayout linear = (LinearLayout) linear_parent_sbAdd
							.getChildAt(i);
					TextView tv_sbadd = (TextView) linear
							.findViewById(R.id.tv_gongdan2_sbadd_item);
					
					tempsbNum +=";"+tv_sbadd.getText().toString();
				}
			}
			clomstr[14] =tempsbNum;
			
			
			DB.upload2(CGXSHGongDanWeiHu_22.this, "shgongdanweihu",clomname, clomstr,
					TuanDuiShangHuActivity.tmpId8);
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"CGXSHGongDanWeiHu_22类SaveData（）" + e.getMessage());
		}
	}

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				int id = v.getId();
				Intent intent = new Intent();
				switch (id) {
				case R.id.btn_gongdan2_pre:
					exitTask(CGXSHGongDanWeiHu_22.this);
					break;

				case R.id.btn_gongdan2_next:
					if (validate()) {
						saveData();
						intent.setClass(CGXSHGongDanWeiHu_22.this,
								CGXSHGongDanWeiHu_32.class);
						CGXSHGongDanWeiHu_22.this.startActivity(intent);
						CGXSHGongDanWeiHu_22.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
				}
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"CGXSHGongDanWeiHu()_22类按钮点击：" + e.getMessage());

			}
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			// log.ExitApp(SHGongDanWeiHu_2.this);
			exitTask(CGXSHGongDanWeiHu_22.this);
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 验证必填字段的是否为空
	protected boolean validate() {

		if (group_shName.getCheckedRadioButtonId() == -1) {
			log.Toast(CGXSHGongDanWeiHu_22.this, "请选择商户名称是否正确 ！");
			return false;
		} else if (group_shName.getCheckedRadioButtonId() == R.id.radio_no_gongdan2_shName) {
			if ("".equals(tv_shNameBZ.getText().toString())) {
				log.Toast(CGXSHGongDanWeiHu_22.this, "商户名称是否正确您选择了否，请填写备注 ！");
				return false;
			}
		}

		if (group_shAddress.getCheckedRadioButtonId() == -1) {
			log.Toast(CGXSHGongDanWeiHu_22.this, "请选择商户地址是否正确 ！");
			return false;
		} else if (group_shAddress.getCheckedRadioButtonId() == R.id.radio_no_gongdan2_shAddress) {
			if ("".equals(tv_shAddressBZ.getText().toString())) {
				log.Toast(CGXSHGongDanWeiHu_22.this, "商户地址是否正确您选择了否，请填写备注 ！");
				return false;
			}
		}

		if (group_shPhoneNum.getCheckedRadioButtonId() == -1) {
			log.Toast(CGXSHGongDanWeiHu_22.this, "请选择商户联系电话是否正确  ！");
			return false;
		} else if (group_shPhoneNum.getCheckedRadioButtonId() == R.id.radio_no_gongdan2_shPhoneNum) {
			if ("".equals(tv_shPhoneNumBZ.getText().toString())) {
				log.Toast(CGXSHGongDanWeiHu_22.this, "联系电话是否正确您选择了否，请填写备注 ！");
				return false;
			}
		}

		if (group_pos_wrok.getCheckedRadioButtonId() == -1) {
			log.Toast(CGXSHGongDanWeiHu_22.this, "请选择pos机是否正常运作 ！");
			return false;
		} else if (group_pos_wrok.getCheckedRadioButtonId() == R.id.radio_no_gongdan2_pos_work) {
			if ("".equals(tv_shPosWorkBZ.getText().toString())) {
				log.Toast(CGXSHGongDanWeiHu_22.this, "POS是否正常运作您选择了否，请填写备注 ！");
				return false;
			}
		}
		if(!"".equals(tv_sbNum.getText().toString())){
			for (int i = 0; i < linear_parent_sbNum.getChildCount(); i++) {
				if (i == 0) {
					if (group_sbNum.getCheckedRadioButtonId() == -1) {
						log.Toast(CGXSHGongDanWeiHu_22.this, "请选择商编终端号是否正确  ！");
						return false;
					} else if (group_sbNum.getCheckedRadioButtonId() == R.id.radio_no_gongdan2_sbNum) {
						if ("".equals(tv_sbNumBZ.getText().toString())) {
							log.Toast(CGXSHGongDanWeiHu_22.this,
									"商编终端号是否正确您选择了否，请填写备注 ！");
							return false;
						}
					}
				} else {
					LinearLayout linear = (LinearLayout) linear_parent_sbNum
							.getChildAt(i);
					RadioGroup radioGroup = (RadioGroup) linear
							.findViewById(R.id.group_gongdan2_sbNum_item);
					TextView tv_sbNumbz = (TextView) linear
							.findViewById(R.id.tv_gongdan2_sbNum_beizhu_item);
					if (radioGroup.getCheckedRadioButtonId() == -1) {
						log.Toast(CGXSHGongDanWeiHu_22.this, "请选择商编终端号是否正确  ！");
						return false;
					} else if (radioGroup.getCheckedRadioButtonId() == R.id.radio_no_gongdan2_sbNum_item) {
						if ("".equals(tv_sbNumbz.getText().toString())) {
							log.Toast(CGXSHGongDanWeiHu_22.this,
									"商编终端号是否正确您选择了否，请填写备注 ！");
							return false;
						}
					}
				}
			}
		}
		
		if (linear_parent_sbAdd.getChildCount() > 1) {
			for (int i = 0; i < linear_parent_sbAdd.getChildCount(); i++) {
				if(i == 0){
					if ("".equals(tv_sbAdd.getText().toString())) {
						log.Toast(CGXSHGongDanWeiHu_22.this, "请填写完整新增商编终端号 ！");
						return false;
					}
				}else{
					LinearLayout linear = (LinearLayout) linear_parent_sbAdd
							.getChildAt(i);
					TextView tv_sbadd = (TextView) linear
							.findViewById(R.id.tv_gongdan2_sbadd_item);
					if ("".equals(tv_sbadd.getText().toString())) {
						log.Toast(CGXSHGongDanWeiHu_22.this, "请填写完整新增商编终端号 ！");
						return false;
					}
				}
				
			}
		}

		return true;
	}

}
