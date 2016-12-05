package techown.shanghu;
/*
 * 工单反馈--电话工单具体信息界面
 */
import java.io.File;

import org.json.JSONObject;

import techown.shanghu.date.GetDate;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GongDanWeiHu41 extends Activity{
	private TextView g3et1,g3et2,g3et3;
	private LinearLayout g3leout1,g3leout2,g3leout3;
	Button button1,button2;
	EditDialog log=new EditDialog();
	static String requeststr1;
	DBUtil DB=new DBUtil();
	public TextView textaa;
	
	public static String path = "sdcard/"+"TJB/TuanDui/dianhuagongda/pic/";
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gongdanweihu51);
        
        File dir = new File(path);
		dir.mkdirs();
        
        button1=(Button)findViewById(R.id.button1);//返回
        button2=(Button)findViewById(R.id.button2);
        
        textaa=(TextView)findViewById(R.id.textaa);
        g3et1=(TextView)findViewById(R.id.g3et1);
        g3et2=(TextView)findViewById(R.id.g3et2);
        g3et3=(TextView)findViewById(R.id.g3et3);
        
        g3leout1=(LinearLayout)findViewById(R.id.g3leout1);
        g3leout2=(LinearLayout)findViewById(R.id.g3leout2);
        g3leout3=(LinearLayout)findViewById(R.id.g3leout3);
        
        textaa.setText(GongDanFanKui1.textaa);
        
        g3leout1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog(GongDanWeiHu41.this,g3leout1, g3et1,R.id.g3et1,"请输入商户接洽人");
			}
		});
        g3leout2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.etDialog(GongDanWeiHu41.this, g3et2,R.id.g3et2,"请输入联系电话");
			}
		});
		g3leout3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog(GongDanWeiHu41.this,g3leout3, g3et3,R.id.g3et3,"请输入接洽内容");
			}
		});
        
        button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setClass(GongDanWeiHu41.this, GongDanFanKui1.class);
				GongDanWeiHu41.this.startActivity(i);
				GongDanWeiHu41.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
			}
		});
        button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				if(validate()){
				// TODO Auto-generated method stub
    			saveData();
    			saveData1();
				String barcodeno=GongDanFanKui1.Id+GongDanFanKui1.num;
				DB.sendinsert(GongDanWeiHu41.this, new String[] {
			    			MyDatabaseHelper.SEND_BarcodeNo,
							MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
							new String[] { barcodeno, "1", "0" });
				
				new AlertDialog.Builder(GongDanWeiHu41.this).setTitle("提示").setMessage("提交成功")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

				
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setClass(GongDanWeiHu41.this, TuanDuiShangHuActivity.class);
						GongDanWeiHu41.this.startActivity(intent);
						GongDanWeiHu41.this.finish();
					}
				}).show();
			}
			}
		});

        
        init();
    }
    String[] tempstr=new String[4];
    public void init()
    {
    	try
		{
    		JSONObject demoJson1 = new JSONObject(requeststr1);
    		
      		if(demoJson1.getString("rspCode").equals("00"))
      		{
      			tempstr[0]=demoJson1.getString("contacter");
  				tempstr[1]=demoJson1.getString("contactTel");
  				tempstr[2]=demoJson1.getString("content");
  				
  				g3et1.setText(tempstr[0]);
  				g3et2.setText(tempstr[1]);
  				g3et3.setText(tempstr[2]);
      		}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu41类init()"+e.getMessage());	
		}	
    }
    
    
    String time=GetDate.getDate().toString();
    public void saveData() {
    	try{
    	String[] clomname = new String[] {"DCL","LX","TIME"};
    	String[] clomstr = new String[10];
    	clomstr[0] = "3";
    	clomstr[1] = "电话工单";
    	clomstr[2] = time;
    	
    	DB.upload3(GongDanWeiHu41.this, clomname, clomstr, GongDanFanKui1.num);
    
    	}catch(Exception e ){
    		techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu41类saveData()"+e.getMessage());
    	}
    }
    public void saveData1(){
    	try{
    	String[] clomname = new String[] {"Name","TelNumber","Neirong"};
    	String[] clomstr = new String[10];
    	clomstr[0] = g3et1.getText().toString();
    	clomstr[1] = g3et2.getText().toString();
    	clomstr[2] = g3et3.getText().toString();
    	
    	DB.upload3(GongDanWeiHu41.this, clomname, clomstr, GongDanFanKui1.num);
    	}catch(Exception e){
    		techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu41类saveData1()"+e.getMessage());
    	}
    }
    protected boolean validate() {
		if (g3et1.getText().toString().equals("")) {
			log.Toast(GongDanWeiHu41.this, "请输入商户接洽人！");
			return false;
		}
		if (g3et2.getText().toString().equals("")) {
			
			log.Toast(GongDanWeiHu41.this, "请输入联系电话！");
			return false;
		}
		if (g3et3.getText().toString().equals("")) {
			
			log.Toast(GongDanWeiHu41.this, "请输入接洽内容！");
			return false;
		}
		

		return true;
	}
}