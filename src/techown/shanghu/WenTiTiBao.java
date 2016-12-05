 package techown.shanghu;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.date.DateDialog;
import techown.shanghu.https.HttpConnection2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

/*
 * 问题提报页面
 */
public class WenTiTiBao extends Activity implements OnClickListener{
	EditDialog log=new EditDialog();
	String[] str = new String[7];
	EditDialog ds=new EditDialog();
	AlertDialog mDialog;
	private LinearLayout tbyleout1,tbyleout0,tbyleout2,tbyleout3,tbyleout4,tbyleout5,tbyleout6,tbyleout7,
				tbyleout8,tbyleout9,tbyleout10;
	private TextView tbyedtext1;
	private RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4,radioGroup5,radioGroup6,radioGroup7;
	private RadioButton choice11,choice12,choice21,choice22,choice31,choice32,choice41,choice42,choice51,
						choice52,choice61,choice62,choice71,choice72;
	private TextView tbyedtext2;
	private TextView tbyedtext4;
	private TextView tbyedtext3;
	private Button btn_sub;
	private Button btn_cancle;
	private String merId;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			switch(msg.what){
				case 1:
					Log.i("chengqk","case 1 ------");
					mDialog.show();
					mDialog.setContentView(R.layout.loading_process_dialog_anim);
					mDialog.setCanceledOnTouchOutside(false);
					mDialog.setCancelable(false);
					break;
					
				case 2:
					new AlertDialog.Builder(WenTiTiBao.this)
					.setTitle("提示")
					.setMessage(rspInfo)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
	
								public void onClick(
										DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method
									
									Intent intent = new Intent();
									intent.setClass(
											WenTiTiBao.this,
											ShangHuWifiWenTi1.class);
									WenTiTiBao.this
											.startActivity(intent);
									techown.shanghu.https.Log.Instance().WriteLog("商户团队问题提报提交成功！");
									WenTiTiBao.this.finish();
								}
							}).show();
		        	break;
				
				case 3:
					Bundle b = msg.getData();
		        	 b = msg.getData();
		        	ds.Toast(WenTiTiBao.this,b.getString("throwsmessage"));
			        break;
					
			}
		}
	};

	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shanghuwentitibao);
		mDialog = new AlertDialog.Builder(WenTiTiBao.this).create();//连接网络进程
		
		/**
		 * 接收从上一个antivity发动来的值
		 */
		
		//merid = getIntent().getExtras().getString("mmid");
		Bundle bundle = getIntent().getBundleExtra("data");
		merId = bundle.getString("merId");
		
		
		/**
		 * 初始化组件
		 */
		initview();
		
		btn_sub.setOnClickListener(this);
		btn_cancle.setOnClickListener(this);
		
		/**
		 * 商户名称
		 */
		/*tbyleout0.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(WenTiTiBao.this, tbyleout0, tbyedtext1,
							R.id.tbyedtext1,"请输入商户名");
					
					break;
				default:
					break;
				}      

				return false;
			}
		});*/
		
		/**
		 * 报修400日期时间
		 */
		tbyleout8.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						DateDialog.showDatePicker(WenTiTiBao.this,tbyedtext2, R.id.tbyedtext2);
					}

				});
		
		/**
		 * 报修后运营商处理情况
		 */
		tbyleout9.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(WenTiTiBao.this, tbyleout9, tbyedtext3,
							R.id.tbyedtext3,"报修后运营商处理情况");
					
					break;
				default:
					break;
				}      

				return false;
			}
		});
		
		/**
		 * 问题现象
		 */
		tbyleout10.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(WenTiTiBao.this, tbyleout0, tbyedtext4,
							R.id.tbyedtext4,"问题现象");
					
					break;
				default:
					break;
				}      

				return false;
			}
		});
		
		radioGroup1.setOnCheckedChangeListener(myls);
		radioGroup2.setOnCheckedChangeListener(myls);
		radioGroup3.setOnCheckedChangeListener(myls);
		radioGroup4.setOnCheckedChangeListener(myls);
		radioGroup5.setOnCheckedChangeListener(myls);
		radioGroup6.setOnCheckedChangeListener(myls);
		radioGroup7.setOnCheckedChangeListener(myls);
		
		
	}
	
	
	private void initview() {
		// TODO Auto-generated method stub
		//tbyleout0 = (LinearLayout)findViewById(R.id.tbyleout0);
		tbyleout1 = (LinearLayout)findViewById(R.id.tbyleout1);
		tbyleout2 = (LinearLayout)findViewById(R.id.tbyleout2);
		tbyleout3 = (LinearLayout)findViewById(R.id.tbyleout3);
		tbyleout4 = (LinearLayout)findViewById(R.id.tbyleout4);
		tbyleout5 = (LinearLayout)findViewById(R.id.tbyleout5);
		tbyleout6 = (LinearLayout)findViewById(R.id.tbyleout6);
		tbyleout7 = (LinearLayout)findViewById(R.id.tbyleout7);
		tbyleout8 = (LinearLayout)findViewById(R.id.tbyleout8);
		tbyleout9 = (LinearLayout)findViewById(R.id.tbyleout9);
		tbyleout10 = (LinearLayout)findViewById(R.id.tbyleout10);
		
		radioGroup1 = (RadioGroup) findViewById(R.id.choicegroup1);
		choice11 = (RadioButton)findViewById(R.id.choice11);
		choice12 = (RadioButton)findViewById(R.id.choice12);
		
		radioGroup2 = (RadioGroup) findViewById(R.id.choicegroup2);
		choice21 = (RadioButton)findViewById(R.id.choice21);
		choice12 = (RadioButton)findViewById(R.id.choice22);
		
		radioGroup3 = (RadioGroup) findViewById(R.id.choicegroup3);
		choice31 = (RadioButton)findViewById(R.id.choice31);
		choice32 = (RadioButton)findViewById(R.id.choice32);
		radioGroup4 = (RadioGroup) findViewById(R.id.choicegroup4);
		choice41 = (RadioButton)findViewById(R.id.choice41);
		choice42 = (RadioButton)findViewById(R.id.choice42);
		radioGroup5 = (RadioGroup) findViewById(R.id.choicegroup5);
		choice51 = (RadioButton)findViewById(R.id.choice51);
		choice52 = (RadioButton)findViewById(R.id.choice52);
		
		radioGroup6 = (RadioGroup) findViewById(R.id.choicegroup6);
		choice61 = (RadioButton)findViewById(R.id.choice61);
		choice62 = (RadioButton)findViewById(R.id.choice62);
		radioGroup7 = (RadioGroup) findViewById(R.id.choicegroup7);
		choice71 = (RadioButton)findViewById(R.id.choice71);
		choice72 = (RadioButton)findViewById(R.id.choice72);
		
		//tbyedtext1 = (TextView)findViewById(R.id.tbyedtext1);
		tbyedtext2 = (TextView)findViewById(R.id.tbyedtext2);
		tbyedtext3 = (TextView)findViewById(R.id.tbyedtext3);
		tbyedtext4 = (TextView)findViewById(R.id.tbyedtext4);
		btn_sub = (Button)findViewById(R.id.tibaobutton1);
		btn_cancle = (Button)findViewById(R.id.tibaobutton2);
		
		
	}


	// 验证必填字段的是否为空
	protected boolean validate() {
		try {
			
			if (!(radioGroup1.getCheckedRadioButtonId() >=0)) {
				log.Toast(WenTiTiBao.this, "请选择亲自到店核实情况！");
				Log.i("chengqk",""+radioGroup1.getCheckedRadioButtonId());
				return false;
			}
			if (!(radioGroup2.getCheckedRadioButtonId() >=0)) {
				log.Toast(WenTiTiBao.this, "请选择已确保店员熟知wifi使用方法");
				return false;
			}
			if (!(radioGroup3.getCheckedRadioButtonId() >=0)) {
				log.Toast(WenTiTiBao.this, "请选择已检查wifi设备电源是否正常！");
				return false;
			}
			if (!(radioGroup4.getCheckedRadioButtonId() >=0)) {
				log.Toast(WenTiTiBao.this, "请选择已核实商户有没有私自接过网线！");
				return false;
			}
			if (!(radioGroup5.getCheckedRadioButtonId() >=0)) {
				log.Toast(WenTiTiBao.this, "请选择已核实商户没有将wifi用于自己的收银系统！");
				return false;
			}
			if (!(radioGroup6.getCheckedRadioButtonId() >=0)) {
				log.Toast(WenTiTiBao.this, "请选择已核实商户没有将wifi用于自己的点餐系统！");
				return false; 
			}
			if (!(radioGroup7.getCheckedRadioButtonId() >=0)) {
				log.Toast(WenTiTiBao.this, "请选择已核实出问题wifi确系交行wifi，非商户自己的wifi！");
				return false; 
			}
			if (tbyedtext2.getText().toString().equals("")) {
				log.Toast(WenTiTiBao.this, "请输入报修400日期时间！");
				return false;
			}
			if (tbyedtext3.getText().toString().equals("")) {
				log.Toast(WenTiTiBao.this, "请输入报修后运营商处理情况！");
				return false;
			}
			if (tbyedtext4.getText().toString().equals("")) {
				log.Toast(WenTiTiBao.this, "请输入问题现象！");
				return false;
			}
			
			return true;  
		} catch (Exception e) {
			// TODO: handle exception
			log.Toast(WenTiTiBao.this, "输入内容错误，请确认后重新提交 ！");
			return false;  
		}
		
		
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		int what = v.getId();
			switch(what){
			/**
			 * 提交事件
			 */
			case R.id.tibaobutton1:{
				if(validate()){
					
					
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				
					new Thread(){
						public void run(){
							
							try{	
								request();
							}catch(Exception e){
								techown.shanghu.https.Log.Instance().WriteLog("WenTiTIBao:Request1()" + e.getMessage());
							}
							
							if(rspCode!=null){
								Log.i("chengqk","----提交之后"+rspCode.toString());
								if (rspCode.equals("000000")) {
									
									mDialog.dismiss();
									Message msg1 = new Message();   
									msg1.what =2; 
									handler.sendMessage(msg1);//
								
								}else{
								
										mDialog.dismiss();
										Message msg = new Message();   
										Bundle b = new Bundle();
										b.putString("throwsmessage",rspInfo);
										msg.setData(b);
								        msg.what =3; 
								        Log.i("chengqk","----提交失败 "+rspInfo);
										handler.sendMessage(msg);//
										
								}
							}
							else{
								mDialog.dismiss();
								Message msg = new Message();   
								Bundle b = new Bundle();
								b.putString("throwsmessage","连接服务器超时，请重新查询！");
								msg.setData(b);
							    msg.what =3; 
								handler.sendMessage(msg);//
								
							}	
										
						}
					}.start();
					
				}
				
				break;
				
			}
			/**
			 * 返回按钮	
			 */
			case R.id.tibaobutton2:{
				
				Intent i = new Intent();
				i.setClass(WenTiTiBao.this, ShangHuWifiWenTi1.class);
				WenTiTiBao.this.startActivity(i);
				WenTiTiBao.this.finish();
				overridePendingTransition(R.anim.rightin,
						R.anim.rightout);
				break;
			}
		}
	}

	/**
	 * 是否题，是记为1，否为0
	 */
	OnCheckedChangeListener myls = new OnCheckedChangeListener() {
		
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			
				switch(checkedId){
				case R.id.choice11:
					str[0]= "已亲自到店核实情况:是";
					break;
				case R.id.choice12:
					str[0] ="已亲自到店核实情况:否";
					break;
				case R.id.choice21:
					str[1]= "已确保店员熟知WIFI使用方法:是";
					break;
				case R.id.choice22:
					str[1] ="已确保店员熟知WIFI使用方法:否";
					break;
				case R.id.choice31:
					str[2]= "已检查WIFI设备电源正常:是";
					break;
				case R.id.choice32:
					str[2] ="已检查WIFI设备电源正常:否";
					break;
				case R.id.choice41:
					str[3]= "已核实商户没有私自拆接过网线:是";
					break;
				case R.id.choice42:
					str[3] ="已核实商户没有私自拆接过网线:否";
					break;
				case R.id.choice51:
					str[4]= "已核实商户没有将WIFI用于自己的收银系统:是";
					break;
				case R.id.choice52:
					str[4] ="已核实商户没有将WIFI用于自己的收银系统:否";
					break;
				case R.id.choice61:
					str[5]= "已核实商户没有将WIFI用于自己的点餐系统:是";
					break;
				case R.id.choice62:
					str[5] ="已核实商户没有将WIFI用于自己的点餐系统:否";
					break;
				case R.id.choice71:
					str[6]= "已核实出问题WIFI确系交行WIFI，非商户自己的WIFI:是";
					break;
				case R.id.choice72:
					str[6] ="已核实出问题WIFI确系交行WIFI，非商户自己的WIFI:否";
					break;
				}
		}
	};

	String rspCode;
	String rspInfo;
	
	public void request() {
		// TODO Auto-generated method stub
		String[] namestr = new String[]{"merId","userId","checkItemDesc","warrantyDate","warrantyDeal","questionDesc","questionType"};
		String[] valuestr = new String[7];
		valuestr[0] = merId;
		//valuestr[1] = (String) tbyedtext1.getText();
		valuestr[1] = TuanDuiShangHuActivity.username;
		
		StringBuilder sbuilder = new StringBuilder();
		for(int i = 0; i< str.length; i++){
			
			if(i == str.length -1){
				sbuilder.append(str[i]);
			}
			else{
				sbuilder.append(str[i]+"¤");
			}
			
		}
		
		valuestr[2] = sbuilder.toString();
		
		valuestr[3] = ((String) tbyedtext2.getText()).replace("-", "");
		valuestr[4] = (String) tbyedtext3.getText();
		valuestr[5] = (String) tbyedtext4.getText();
		valuestr[6] = "MTX";
		
		JSONObject jsonObject = new JSONObject() ;
		for(int i = 0; i< namestr.length; i++){
			try{
				jsonObject.put(namestr[i], valuestr[i]);
				Log.i("chengqk","valuestr:"+valuestr[i]);
			}catch(JSONException e){
				e.printStackTrace();
			}
		}
		
		String jsonstr = jsonObject.toString();
		Log.i("chengqk","request 前----------"+jsonstr);
		techown.shanghu.https.Log.Instance().WriteLog("MTX0003的请求的jsonstr字符串："+jsonstr);
		String jsont = HttpConnection2.tbrequest2(jsonstr, "MTX0003");
		Log.i("chengqk","request 后----------"+jsont);
		techown.shanghu.https.Log.Instance().WriteLog("MTX0003的返回的的json字符串："+jsont);
		
		JSONObject json = null;
		if(jsont!=null&&!jsont.equals("")){
			try {
				json = new JSONObject(jsont);
				rspCode = json.optString("rspCode");
				Log.i("chengqk","rspCode------:"+rspCode);
				rspInfo= json.optString("rspInfo");
				//Log.i("chengqk","rspInfo-------:"+rspInfo);
				
				if("000000".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000000 成功记录信息  rspInfo:"+rspInfo);
					rspInfo = "提交成功";
				}else if("000001".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000001  该服务不存在   rspInfo:"+rspInfo);
					rspInfo = "提交失败,请重新提交";
				}else if("000002".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000002  服务器响应超时 rspInfo:"+rspInfo);
					rspInfo = "服务响应超时,请重新提交";
				}else if("000003".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000003 参数错误  rspInfo:"+rspInfo);
					//rspInfo = "提交失败,请重新提交";
				}else if("000004".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000004  没有结果返回  rspInfo:"+rspInfo);
					rspInfo = "提交失败,请重新提交";
				}else if("000005".equals(rspCode)){
					techown.shanghu.https.Log.Instance().WriteLog("rspCode:000005  数据处理错误  rspInfo:"+rspInfo);
					rspInfo = "提交失败,请重新提交";
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance()
				.WriteLog("WenTiTIBao:Request1()" + e.getMessage());
			}
		}
		
	}


	/**
	 * 平板上的返回功能
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(WenTiTiBao.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	
}
