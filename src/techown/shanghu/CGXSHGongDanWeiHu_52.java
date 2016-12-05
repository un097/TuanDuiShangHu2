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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 草稿箱 培训信息录入
 */
public class CGXSHGongDanWeiHu_52 extends Activity {

	DES des = new DES();
	EditDialog log = new EditDialog();
	DBUtil DB = new DBUtil();
	GetData getDasta = new GetData();
	private RadioGroup group_train;
	private RadioButton radio_train_online;
	private RadioButton radio_train_visit;
	private LinearLayout linear_train_target;
	private CheckBox cb_target_manager;
	private TextView tv_target_bz;
	private CheckBox cb_target_sericer;
	private CheckBox cb_target_cashier;
	private Button btn_pre;
	private Button btn_next;
	private LinearLayout linear_train_content;
	private CheckBox cb_content_one;
	private CheckBox cb_content_two;
	private CheckBox cb_content_four;
	private CheckBox cb_content_three;
	private TextView tv_content_bz;
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_gongdanweihu5);
		initView();
		MyLis lis = new MyLis();
		btn_pre.setOnClickListener(lis);
		btn_next.setOnClickListener(lis);
		
		try {

			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					CGXSHGongDanWeiHu_52.this, "techown.db", Constant.tdshDBVer);
			SQLiteDatabase db = myHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery(
					"select * from shgongdanweihu where id=?",
					new String[] { TuanDuiShangHuActivity.tmpId8 });
			while (cursor.moveToNext()) {
				try {
					String lable_PropertyCheck = des.jieMI(cursor.getString(cursor
							.getColumnIndex("trainProperty")));
					if("1".equals(lable_PropertyCheck)){
						radio_train_online.setChecked(true);
					}else if("2".equals(lable_PropertyCheck)){
						radio_train_visit.setChecked(true);
					}
					
					String[] tempTargetSelect = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("trainTargetSelect")))).split(";");
					
					for(int i=0; i<tempTargetSelect.length; i++){
						if("1".equals(tempTargetSelect[i])){
							cb_target_manager.setChecked(true);
						}else if("2".equals(tempTargetSelect[i])){
							cb_target_cashier.setChecked(true);
						}else if("3".equals(tempTargetSelect[i])){
							cb_target_sericer.setChecked(true);
						}
					}
					tv_target_bz.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("trainTargetComment"))));
					
					String[] tempContentSelect = (des.jieMI(cursor.getString(cursor
							.getColumnIndex("trainContentSelect")))).split(";");
					
					for(int i=0; i<tempContentSelect.length; i++){
						if("1".equals(tempContentSelect[i])){
							cb_content_one.setChecked(true);
						}else if("2".equals(tempContentSelect[i])){
							cb_content_two.setChecked(true);
						}else if("3".equals(tempContentSelect[i])){
							cb_content_three.setChecked(true);
						}else if("4".equals(tempContentSelect[i])){
							cb_content_four.setChecked(true);
						}
					}
					tv_content_bz.setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("trainContentComment"))));
					
					
				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队CGXSHGongDanWeiHu_52:" + e.getMessage());
				} finally {
					if (cursor != null) {
						cursor.close();
					}
					if (db != null) {
						db.close();
					}
				}

			}
			
			tv_target_bz.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(CGXSHGongDanWeiHu_52.this, linear_train_target,
							tv_target_bz, R.id.tv_gongdan5_target_beizhu,
							"请输入培训对象备注");
				}
			});
			tv_content_bz.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					log.Dialog(CGXSHGongDanWeiHu_52.this, linear_train_content,
							tv_content_bz, R.id.tv_gongdan5_content_beizhu,
							"请输入培训内容备注");
				}
			});
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队CGXSHGongDanWeiHu_52：" + e.getMessage());
		}
	}

	
	private void initView() {
		// TODO Auto-generated method stub
		group_train = (RadioGroup) findViewById(R.id.group_gongdan5_train);
		radio_train_online = (RadioButton) findViewById(R.id.radio_gongdan5_train_online);
		radio_train_visit = (RadioButton) findViewById(R.id.radio_gongdan5_train_visit);
		
		linear_train_target = (LinearLayout) findViewById(R.id.linearlayout_gongdan5_target);
		cb_target_manager = (CheckBox)findViewById(R.id.check_gongdan5_target_manager);
		cb_target_cashier = (CheckBox)findViewById(R.id.check_gongdan5_target_cashier);
		cb_target_sericer = (CheckBox)findViewById(R.id.check_gongdan5_target_sericer);
		tv_target_bz =(TextView)findViewById(R.id.tv_gongdan5_target_beizhu);
		
		linear_train_content = (LinearLayout) findViewById(R.id.linearlayout_gongdan5_content);
		cb_content_one = (CheckBox)findViewById(R.id.checkbox_gongdan5_content_one);
		cb_content_two = (CheckBox)findViewById(R.id.checkbox_gongdan5_content_two);
		cb_content_three = (CheckBox)findViewById(R.id.checkbox_gongdan5_content_three);
		cb_content_four = (CheckBox)findViewById(R.id.checkbox_gongdan5_content_four);
		tv_content_bz =(TextView)findViewById(R.id.tv_gongdan5_content_beizhu);
		
		btn_pre = (Button)findViewById(R.id.btn_gongdan5_pre);
		btn_next = (Button)findViewById(R.id.btn_gongdan5_next);
	}

	
	public void saveData() {
		try {
			
			
			String[] clomname = new String[] {"trainProperty","trainTargetSelect","trainTargetComment",
							"trainContentSelect","trainContentComment"};
					
			String[] clomstr = new String[clomname.length];
			//机具是否运作
			if(radio_train_online.isChecked()){
				clomstr[0] = "1";
			}else{
				clomstr[0] = "2";
			}
			String tempTargentSelect = "";
			if(cb_target_manager.isChecked()){
				tempTargentSelect += "1;";
			}
			if(cb_target_cashier.isChecked()){
				tempTargentSelect += "2;";
			}
			if(cb_target_sericer.isChecked()){
				tempTargentSelect += "3;";
			}
			clomstr[1] = tempTargentSelect;
			clomstr[2] = tv_target_bz.getText().toString();
			
			String tempContentSelect="";
			if(cb_content_one.isChecked()){
				tempContentSelect += "1;";
			}
			if(cb_content_two.isChecked()){
				tempContentSelect += "2;";
			}
			if(cb_content_three.isChecked()){
				tempContentSelect += "3;";
			}
			if(cb_content_four.isChecked()){
				tempContentSelect += "4;";
			}
			clomstr[3] = tempContentSelect;
			
			clomstr[4] = tv_content_bz.getText().toString();

			DB.upload2(CGXSHGongDanWeiHu_52.this, "shgongdanweihu",clomname, clomstr,
					TuanDuiShangHuActivity.tmpId8);
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"CGXSHGongDanWeiHu_52类SaveData（）" + e.getMessage());
		}
	}

	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				int id = v.getId();
				Intent intent = new Intent();
				switch (id) {
				case R.id.btn_gongdan5_pre:
					intent.setClass(CGXSHGongDanWeiHu_52.this,
							CGXSHGongDanWeiHu_42.class);
					CGXSHGongDanWeiHu_52.this.startActivity(intent);
					CGXSHGongDanWeiHu_52.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
					break;

				case R.id.btn_gongdan5_next:
					if (validate()) {
						saveData();
						intent.setClass(CGXSHGongDanWeiHu_52.this,
								CGXSHGongDanWeiHu_62.class);
						CGXSHGongDanWeiHu_52.this.startActivity(intent);
						CGXSHGongDanWeiHu_52.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
				}
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"CGXSHGongDanWeiHu()_52类按钮点击：" + e.getMessage());

			}
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Intent intent = new Intent();
			intent.setClass(CGXSHGongDanWeiHu_52.this,
					CGXSHGongDanWeiHu_42.class);
			CGXSHGongDanWeiHu_52.this.startActivity(intent);
			CGXSHGongDanWeiHu_52.this.finish();
			overridePendingTransition(R.anim.rightin, R.anim.rightout);
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 验证必填字段的是否为空
	protected boolean validate() {

		if (group_train.getCheckedRadioButtonId() == -1) {
			log.Toast(CGXSHGongDanWeiHu_52.this, "请选择培训性质！");
			return false;
		} 
		
		if(!cb_target_cashier.isChecked() && !cb_target_manager.isChecked() && !cb_target_sericer.isChecked()){
			log.Toast(CGXSHGongDanWeiHu_52.this, "请选择培训对象！");
			return false;
		}
		else if(!cb_target_cashier.isChecked() || !cb_target_manager.isChecked() || !cb_target_sericer.isChecked()){
			if("".equals(tv_target_bz.getText().toString())){
				log.Toast(CGXSHGongDanWeiHu_52.this, "培训对象未全选时需填写备注！");
				return false;
			}
			
		}
		
		if(!cb_content_one.isChecked() && !cb_content_two.isChecked() && !cb_content_three.isChecked()&& !cb_content_four.isChecked()){
			log.Toast(CGXSHGongDanWeiHu_52.this, "请选择培训内容！");
			return false;
		}
		
		if("".equals(tv_content_bz.getText().toString())){
			log.Toast(CGXSHGongDanWeiHu_52.this, "请填写培训内容备注！");
			return false;
		}
		
		return true;
	}

}
