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
 * SN信息录入
 */
public class SHGongDanWeiHu_3 extends Activity {

	DES des = new DES();
	EditDialog log = new EditDialog();
	DBUtil DB = new DBUtil();
	GetData getDasta = new GetData();
	
	private Button btn_pre;
	private Button btn_next;
	private TextView tv_machineWorkBZ;
	private RadioGroup group_machine_wrok;
	private RadioButton radio_machine_work_yes;
	private LinearLayout linear_machinework;
	private RadioButton radio_machine_work_no;
	private RadioGroup group_snNum;
	private RadioButton radio_snNum_yes;
	private LinearLayout linear_snNum;
	private RadioButton radio_snNum_no;
	private TextView tv_snNumBZ;
	private LinearLayout linear_parent_snNum;
	private TextView tv_snNumAdd;
	private LinearLayout linear_snNumAdd;
	private TextView tv_snNum;
	

	public static  boolean flagfirst =true;
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_gongdanweihu3);
		initView();
		MyLis lis = new MyLis();
		btn_pre.setOnClickListener(lis);
		btn_next.setOnClickListener(lis);
		
		try {

			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					SHGongDanWeiHu_3.this, "techown.db", Constant.tdshDBVer);
			SQLiteDatabase db = myHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery(
					"select * from shgongdanweihu where id=?",
					new String[] { SHGongDanWeiHu.num });
			while (cursor.moveToNext()) {
				try {
					String lable_machineWorkCheck = des.jieMI(cursor.getString(cursor
							.getColumnIndex("machineIsWork")));
					if("1".equals(lable_machineWorkCheck)){
						radio_machine_work_yes.setChecked(true);
					}else if("2".equals(lable_machineWorkCheck)){
						radio_machine_work_no.setChecked(true);
					}
					tv_machineWorkBZ.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("isWorkComment"))));
					
					String[] snNumInfo = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("snNumFrom")))).split(";");
					String[] snCheckInfo = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("snNumSelect")))).split(";");
					String[] snBZInfo = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("snNumComment")))).split(";",-1);
					for(int i=0; i<snNumInfo.length;i++){
						if(i==0){
							tv_snNum.setText(snNumInfo[i]);
							tv_snNumBZ.setText(snBZInfo[i]);
							
							if("1".equals(snCheckInfo[i])){
								radio_snNum_yes.setChecked(true);
							}else if("2".equals(snCheckInfo[i])){
								radio_snNum_no.setChecked(true);
							}
						}else{
							View view = LayoutInflater.from(
									SHGongDanWeiHu_3.this).inflate(
									R.layout.gongdan3_snnum_item, null);

							linear_parent_snNum.addView(view);
							final LinearLayout linearlayoutItem = (LinearLayout) view
									.findViewById(R.id.linearlayout_gongdan3_item);
							RadioButton sbCheckYes = (RadioButton)view.findViewById(R.id.radio_yes_gongdan3_snNum_item);
							RadioButton sbCheckNo = (RadioButton)view.findViewById(R.id.radio_no_gongdan3_snNum_item);
							final TextView tv_sbNumbz = (TextView) view
									.findViewById(R.id.tv_gongdan3_snNum_beizhu_item);
							tv_sbNumbz.setOnClickListener(new OnClickListener() {
								
								public void onClick(View v) {
									// TODO Auto-generated method stub
									log.Dialog(
											SHGongDanWeiHu_3.this,
											linearlayoutItem,
											tv_sbNumbz,
											R.id.tv_gongdan3_snNum_beizhu_item,
											"请输入机具SN号备注");
								}
							});
							TextView tv_sbNum = (TextView) view
									.findViewById(R.id.tv_gongdan3_snNum_item);
							
							if("1".equals(snCheckInfo[i])){
								sbCheckYes.setChecked(true);
							}else if("2".equals(snCheckInfo[i])){
								sbCheckNo.setChecked(true);
							}
							
							tv_sbNum.setText(snNumInfo[i]);
							tv_sbNumbz.setText(snBZInfo[i]);
							
						}
					}
					
					//机具sn号
					tv_snNumAdd.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("snNumAdd"))));
					
					
				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队SHGongDanWeiHu_2:" + e.getMessage());
				} finally {
					if (cursor != null) {
						cursor.close();
					}
					if (db != null) {
						db.close();
					}
				}

			}
			
			tv_machineWorkBZ.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(SHGongDanWeiHu_3.this, linear_machinework,
							tv_machineWorkBZ,
							R.id.tv_gongdan3_machine_work_beizhu, "请输入机具是否正常运作备注");
				}
			});
			
			tv_snNumBZ.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(SHGongDanWeiHu_3.this, linear_snNum, tv_snNumBZ,
							R.id.tv_gongdan3_snNum_beizhu, "请输入机具SN号备注");
				}
			});
			tv_snNumAdd.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(SHGongDanWeiHu_3.this, linear_snNumAdd, tv_snNumAdd,
							R.id.tv_gongdan3_sbNum_add, "请输入新增机具SN号");
				}
			});
			if(flagfirst){
				init();
			}
			
			if("".equals((tv_snNum.getText().toString()).trim())){
				group_machine_wrok.setClickable(false);
				group_snNum.setClickable(false);
				radio_machine_work_yes.setClickable(false);
				radio_machine_work_no.setClickable(false);
				radio_snNum_no.setClickable(false);
				radio_snNum_yes.setClickable(false);
				tv_machineWorkBZ.setClickable(false);
				tv_snNumBZ.setClickable(false);
			}
			
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队SHGongDanWeiHu_2：" + e.getMessage());
		}
	}

	
	private void initView() {
		// TODO Auto-generated method stub
		tv_machineWorkBZ = (TextView) findViewById(R.id.tv_gongdan3_machine_work_beizhu);
		group_machine_wrok = (RadioGroup) findViewById(R.id.group_gongdan3_machine_work);
		radio_machine_work_yes = (RadioButton) findViewById(R.id.radio_yes_gongdan3_machine_work);
		radio_machine_work_no = (RadioButton) findViewById(R.id.radio_no_gongdan3_machine_work);
		linear_machinework = (LinearLayout) findViewById(R.id.linearlayout_gongdan3_machinework);

		tv_snNum = (TextView) findViewById(R.id.tv_gongdan3_snNum);
		tv_snNumBZ = (TextView) findViewById(R.id.tv_gongdan3_snNum_beizhu);
		group_snNum = (RadioGroup) findViewById(R.id.group_gongdan3_snNum);
		radio_snNum_yes = (RadioButton) findViewById(R.id.radio_yes_gongdan3_snNum);
		radio_snNum_no = (RadioButton) findViewById(R.id.radio_no_gongdan3_snNum);
		linear_snNum = (LinearLayout) findViewById(R.id.linearlayout_gongdan3_SNNum);
		linear_parent_snNum = (LinearLayout) findViewById(R.id.linearlayout_gongdan3_parent_SNInfo);
		
		tv_snNumAdd = (TextView) findViewById(R.id.tv_gongdan3_sbNum_add);
		linear_snNumAdd = (LinearLayout) findViewById(R.id.linearlayout_gongdan3_SNAdd);
		
		btn_pre = (Button)findViewById(R.id.btn_gongdan3_pre);
		btn_next = (Button)findViewById(R.id.btn_gongdan3_next);
	}


	public void init() {
		if (!SHGongDanWeiHu_2.tempstr1.equals("")) {
			String[] snInfo = (SHGongDanWeiHu_2.tempstr1)[6].split(";");
			for (int i = 0; i < snInfo.length; i++) {
				if (i == 0) {
					tv_snNum.setText(snInfo[i]);
				} else {
					View view = LayoutInflater.from(
							SHGongDanWeiHu_3.this).inflate(
							R.layout.gongdan3_snnum_item, null);

					linear_parent_snNum.addView(view);
					final LinearLayout linearlayoutItem = (LinearLayout) view
							.findViewById(R.id.linearlayout_gongdan3_item);
					TextView tv_snNum = (TextView) view
							.findViewById(R.id.tv_gongdan3_snNum_item);
					tv_snNum.setText(snInfo[i]);
					final TextView tv_sbNumBZ = (TextView) view
							.findViewById(R.id.tv_gongdan3_snNum_beizhu_item);
					tv_sbNumBZ
							.setOnClickListener(new OnClickListener() {

								public void onClick(View v) {
									// TODO Auto-generated method stub
									log.Dialog(
											SHGongDanWeiHu_3.this,
											linearlayoutItem,
											tv_sbNumBZ,
											R.id.tv_gongdan3_snNum_beizhu_item,
											"请输入机具SN号备注");
								}
							});
				}

			}
		}
	}

	
	public void saveData() {
		try {
			
			
			String[] clomname = new String[] {"machineIsWork","isWorkComment","snNumFrom",
							"snNumSelect","snNumComment","snNumAdd"};
					
			String[] clomstr = new String[clomname.length];
			//机具是否运作
			if(radio_machine_work_yes.isChecked()){
				clomstr[0] = "1";
			}else if(radio_machine_work_no.isChecked()){
				clomstr[0] = "2";
			}else{
				clomstr[0] = "3";
			}
			clomstr[1] = tv_machineWorkBZ.getText().toString();
			
			//机具SN号
			
			//获取商编终端对应的数据
			String tempSnNum="",tempSnCheck="",tempSnBZ="";
			for (int i = 0; i < linear_parent_snNum.getChildCount(); i++) {
				if (i == 0) {
					tempSnNum = tv_snNum.getText().toString()+";";
					tempSnBZ = tv_snNumBZ.getText().toString();
					if (group_snNum.getCheckedRadioButtonId() == R.id.radio_no_gongdan3_snNum) {
						tempSnCheck ="2;";
					}else if(group_snNum.getCheckedRadioButtonId() == R.id.radio_yes_gongdan3_snNum){
						tempSnCheck ="1;";
					}else{
						tempSnCheck ="3;";
					}
				} else {
					LinearLayout linear = (LinearLayout) linear_parent_snNum
							.getChildAt(i);
					RadioGroup radioGroup = (RadioGroup) linear
							.findViewById(R.id.group_gongdan3_snNum_item);
					TextView tv_snNumbz = (TextView) linear
							.findViewById(R.id.tv_gongdan3_snNum_beizhu_item);
					TextView tv_snNum = (TextView) linear
							.findViewById(R.id.tv_gongdan3_snNum_item);
					
					tempSnNum += tv_snNum.getText().toString()+";";
					tempSnBZ +=";"+tv_snNumbz.getText().toString();
					if (radioGroup.getCheckedRadioButtonId() == R.id.radio_no_gongdan3_snNum_item) {
						tempSnCheck += "2;";
					}else{
						tempSnCheck += "1;";
					}
				}
			}
			
			clomstr[2] = tempSnNum;
			clomstr[3] = tempSnCheck;
			clomstr[4] = tempSnBZ;

			clomstr[5] = tv_snNumAdd.getText().toString();

			DB.upload2(SHGongDanWeiHu_3.this, "shgongdanweihu",clomname, clomstr,
					SHGongDanWeiHu.num);
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"HeYueQianDing_3类SaveData（）" + e.getMessage());
		}
	}

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				int id = v.getId();
				Intent intent = new Intent();
				switch (id) {
				case R.id.btn_gongdan3_pre:
					SHGongDanWeiHu_2.flagfirst = false;
					intent.setClass(SHGongDanWeiHu_3.this,
							SHGongDanWeiHu_2.class);
					SHGongDanWeiHu_3.this.startActivity(intent);
					SHGongDanWeiHu_3.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
					break;

				case R.id.btn_gongdan3_next:
					if (validate()) {
						saveData();
						flagfirst = true;
						intent.setClass(SHGongDanWeiHu_3.this,
								SHGongDanWeiHu_4.class);
						SHGongDanWeiHu_3.this.startActivity(intent);
						SHGongDanWeiHu_3.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
				}
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"SHGongDanWeiHu()_2类按钮点击：" + e.getMessage());

			}
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Intent intent = new Intent();
			SHGongDanWeiHu_2.flagfirst = false;
			intent.setClass(SHGongDanWeiHu_3.this,
					SHGongDanWeiHu_2.class);
			SHGongDanWeiHu_3.this.startActivity(intent);
			SHGongDanWeiHu_3.this.finish();
			overridePendingTransition(R.anim.rightin, R.anim.rightout);
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 验证必填字段的是否为空
	protected boolean validate() {

		if(!"".equals(tv_snNum.getText().toString())){
			if (group_machine_wrok.getCheckedRadioButtonId() == -1) {
				log.Toast(SHGongDanWeiHu_3.this, "请选择机具是否正常运作 ！");
				return false;
			} else if (group_machine_wrok.getCheckedRadioButtonId() == R.id.radio_no_gongdan3_machine_work) {
				if ("".equals(tv_machineWorkBZ.getText().toString())) {
					log.Toast(SHGongDanWeiHu_3.this, "机具是否正常运作您选择了否，请填写备注 ！");
					return false;
				}
			}
		}
		
		if(!"".equals(tv_snNum.getText().toString())){
		
			for (int i = 0; i < linear_parent_snNum.getChildCount(); i++) {
				if (i == 0) {
					if (group_snNum.getCheckedRadioButtonId() == -1) {
						log.Toast(SHGongDanWeiHu_3.this, "请选择机具SN号是否正确  ！");
						return false;
					} else if (group_snNum.getCheckedRadioButtonId() == R.id.radio_no_gongdan3_snNum) {
						if ("".equals(tv_snNumBZ.getText().toString())) {
							log.Toast(SHGongDanWeiHu_3.this,
									"机具SN号是否正确您选择了否，请填写备注 ！");
							return false;
						}
					}
				} else {
					LinearLayout linear = (LinearLayout) linear_parent_snNum
							.getChildAt(i);
					RadioGroup radioGroup = (RadioGroup) linear
							.findViewById(R.id.group_gongdan3_snNum_item);
					TextView tv_sbNumbz = (TextView) linear
							.findViewById(R.id.tv_gongdan3_snNum_beizhu_item);
					if (radioGroup.getCheckedRadioButtonId() == -1) {
						log.Toast(SHGongDanWeiHu_3.this, "请选择机具SN号是否正确  ！");
						return false;
					} else if (radioGroup.getCheckedRadioButtonId() == R.id.radio_no_gongdan3_snNum_item) {
						if ("".equals(tv_sbNumbz.getText().toString())) {
							log.Toast(SHGongDanWeiHu_3.this,
									"机具SN号是否正确您选择了否，请填写备注 ！");
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}

}
