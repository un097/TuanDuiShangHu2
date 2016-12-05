package techown.shanghu;
/*
 * 工单维护--电话工单具体信息界面
 */
import java.io.File;

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

public class GongDanWeiHu4 extends Activity{
	private TextView g3et1,g3et2,g3et3;
	private LinearLayout g3leout1,g3leout2,g3leout3;
	Button button1,button2;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	public static String path = "sdcard/"+"TJB/TuanDui/dianhuagongda/pic/";
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gongdanweihu5);
        
        File dir = new File(path);
		dir.mkdirs();
        
        button1=(Button)findViewById(R.id.button1);//返回
        button2=(Button)findViewById(R.id.button2);
        
        g3et1=(TextView)findViewById(R.id.g3et1);
        g3et2=(TextView)findViewById(R.id.g3et2);
        g3et3=(TextView)findViewById(R.id.g3et3);
        
        g3leout1=(LinearLayout)findViewById(R.id.g3leout1);
        g3leout2=(LinearLayout)findViewById(R.id.g3leout2);
        g3leout3=(LinearLayout)findViewById(R.id.g3leout3);
        
        g3leout1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog(GongDanWeiHu4.this,g3leout1, g3et1,R.id.g3et1,"请输入商户接洽人");
			}
		});
        g3leout2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.etDialog(GongDanWeiHu4.this, g3et2,R.id.g3et2,"请输入联系电话");
			}
		});
		g3leout3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog(GongDanWeiHu4.this,g3leout3, g3et3,R.id.g3et3,"请输入接洽内容");
			}
		});
        
        button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				techown.shanghu.https.OperateLog.Instance().WriteLog("电话工单点击返回按钮！");
				Intent i=new Intent();
				i.setClass(GongDanWeiHu4.this, GongDanWeiHu1.class);
				GongDanWeiHu4.this.startActivity(i);
				GongDanWeiHu4.this.finish();
			}
		});
        button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				if(validate()){
				// TODO Auto-generated method stub
				techown.shanghu.https.OperateLog.Instance().WriteLog("商户团队电话工单点击提交按钮" 
						+"，商户接洽人："+g3et1.getText().toString()
						+"联系电话"+g3et1.getText().toString()
						+"商户接洽人"+g3et1.getText().toString());
    			saveData();
    			saveData1();
				String barcodeno=GongDanWeiHu1.Id+GongDanWeiHu1.num;
				DB.sendinsert(GongDanWeiHu4.this, new String[] {
			    			MyDatabaseHelper.SEND_BarcodeNo,
							MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
							new String[] { barcodeno, "1", "0" });
				
				new AlertDialog.Builder(GongDanWeiHu4.this).setTitle("提示").setMessage("提交成功")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

				
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setClass(GongDanWeiHu4.this, TuanDuiShangHuActivity.class);
						GongDanWeiHu4.this.startActivity(intent);
						techown.shanghu.https.OperateLog.Instance().WriteLog("商户团队电话工单提交成功！");
						GongDanWeiHu4.this.finish();
					}
				}).show();
			}
			}
		});

    }
    
    public void saveData() {
    	
    	try{
    	String time=GetDate.getDate().toString();
    	String[] clomname = new String[] {"DCL","LX","TIME"};
    	String[] clomstr = new String[10];
    	clomstr[0] = "3";
    	clomstr[1] = "电话工单";
    	clomstr[2] = time;
    	
    	DB.upload3(GongDanWeiHu4.this, clomname, clomstr, GongDanWeiHu1.num);
    	}catch(Exception e){
    		techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu4类saveData()"+e.getMessage());
    	}
    }
    public void saveData1(){
    	try{
    	String[] clomname = new String[] {"Name","TelNumber","Neirong"};
    	String[] clomstr = new String[10];
    	clomstr[0] = g3et1.getText().toString();
    	clomstr[1] = g3et2.getText().toString();
    	clomstr[2] = g3et3.getText().toString();
    	
    	DB.upload3(GongDanWeiHu4.this, clomname, clomstr, GongDanWeiHu1.num);
    	}catch(Exception e){
    		techown.shanghu.https.Log.Instance().WriteLog("GongDanWeiHu4类saveData1()"+e.getMessage());
    	}
    }
    protected boolean validate() {
		if (g3et1.getText().toString().equals("")) {
			
			log.Toast(GongDanWeiHu4.this, "请输入商户接洽人！");
			
			return false;
		}
		if (g3et2.getText().toString().equals("")) {
			
			log.Toast(GongDanWeiHu4.this, "请输入联系电话！");
			return false;
		}
		if (g3et3.getText().toString().equals("")) {
			
			log.Toast(GongDanWeiHu4.this, "请输入接洽内容！");
			return false;
		}
		

		return true;
	}
}