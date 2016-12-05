package techown.shanghu;
/*
 * 优惠上线-- 最红 上线事宜
 */
import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.photo.YouHuiTakePhotoActivity2;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YouHuiShangXian_ZuiHong3  extends Activity{
	private Button button2,button4,button6,button7,button8;
	int i=0,j=0,k=0,l=0,m=0,n=0;
	int[] textIds = { R.id.h3edtext9, R.id.h3edtext10, R.id.h3edtext11};
	public static TextView[] textArray;
	public TextView active_neirong,duanxin;
	int[] linlayIds={R.id.h4leout9,R.id.h4leout10,R.id.h4leout11};
	public LinearLayout[] linlay;
	public static String requeststr1;
	DES des = new DES();
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youhuishangxian_zuihong3);
        
        button2=(Button)findViewById(R.id.butt2);
        button4=(Button)findViewById(R.id.butt4);
        button6=(Button)findViewById(R.id.butt6);
        button7=(Button)findViewById(R.id.butt7);
        button8=(Button)findViewById(R.id.butt8);
        
        active_neirong=(TextView)findViewById(R.id.h3edtext1);
        duanxin=(TextView)findViewById(R.id.h3edtext2);
        
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		
		
		//读取数据
	  MyDatabaseHelper myHelper = new MyDatabaseHelper(
			  YouHuiShangXian_ZuiHong3.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		db = myHelper.getWritableDatabase();
		cursor = db.rawQuery("select * from youhushangxian where id==?",
				new String[] { YouHuiShangXian_TeHui.num });
		while (cursor.moveToNext()) {
			try {
				
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Name"))));
				textArray[1].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Zhiwei"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Neirong"))));
				
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_ZuiHong3:读取数据()" + e.getMessage());
			}finally{
				if(cursor!=null){
					cursor.close();
				}if(db!=null){
					db.close();
				}
			}
		}
	        
		
		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(YouHuiShangXian_ZuiHong3.this,linlay[0], textArray[0],
							R.id.h3edtext9,"请输入培训对象");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[1].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(YouHuiShangXian_ZuiHong3.this,linlay[1], textArray[1],
							R.id.h3edtext10,"请输入职位");
					break;
				default:
					break;
				}

				return false;
			}
		});
		linlay[2].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(YouHuiShangXian_ZuiHong3.this,linlay[2], textArray[2],
							R.id.h3edtext11,"请输入内容");
					break;
				default:
					break;
				}

				return false;
			}
		});
        
        
        
        button7.setEnabled(false);
        
        button2.setOnClickListener(new OnClickListener() {
					
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					button2.setEnabled(false);
					j=1;
					if(j==1&&n==1&&l==1){
						button7.setEnabled(true);
					}
				}
			});
        button4.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				button4.setEnabled(false);
				l=1;
				if(j==1&&n==1&&l==1){
					button7.setEnabled(true);
				}
			}
		});
        button6.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				button6.setEnabled(false);
				n=1;
				if(j==1&&n==1&&l==1){
					button7.setEnabled(true);
				}
			}
		});
        
        
        button7.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(validate()){
				saveData();
				Intent intent = new Intent();
				intent.setClass(YouHuiShangXian_ZuiHong3.this, YouHuiTakePhotoActivity2.class);
				YouHuiShangXian_ZuiHong3.this.startActivity(intent);
				YouHuiShangXian_ZuiHong3.this.finish();
				overridePendingTransition(R.anim.leftin, R.anim.leftout);
				}
			}
		});
    	
        button8.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(YouHuiShangXian_ZuiHong3.this, YouHuiShangXian_TeHui21.class);
				YouHuiShangXian_ZuiHong3.this.startActivity(intent);
				YouHuiShangXian_ZuiHong3.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
			}
		});
        init();
    }
    private boolean validate() {
		if(textArray[0].getText().toString().equals("")){
			//Toast.makeText(YouHuiShangXian_ZuiHong3.this, "请输入培训对象", Toast.LENGTH_SHORT).show();
			 log.Toast(YouHuiShangXian_ZuiHong3.this, "请输入培训对象");
			return false;
		}
		if(textArray[1].getText().toString().equals("")){
			//Toast.makeText(YouHuiShangXian_ZuiHong3.this, "请输入职位", Toast.LENGTH_SHORT).show();
			 log.Toast(YouHuiShangXian_ZuiHong3.this, "请输入职位");
			return false;
		}
		if(textArray[2].getText().toString().equals("")){
			//Toast.makeText(YouHuiShangXian_ZuiHong3.this, "请输入培训内容", Toast.LENGTH_SHORT).show();
			log.Toast(YouHuiShangXian_ZuiHong3.this, "请输入培训内容");
			return false;
		}
		return true;
	}
    
    public void init()
    {
    	try
		{
    		JSONObject demoJson1 = new JSONObject(requeststr1);
			if(demoJson1.getString("rspCode").equals("00"))
			{
				String[] tempstr1=new String[17];
				tempstr1[0]=demoJson1.getString("saleContent");
				tempstr1[1]=demoJson1.getString("messageContent");
				
				active_neirong.setText(tempstr1[0]);//公司名称
	    		duanxin.setText(tempstr1[1]);//法人代表
	    		
			}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui3:init()" + e.getMessage());
		}	
    }
    
    public void saveData() {
		String[] clomname = new String[] {"Name","Zhiwei","Neirong"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[0].getText().toString();
		clomstr[1] = textArray[1].getText().toString();
		clomstr[2] = textArray[2].getText().toString();

		
		DB.upload4(YouHuiShangXian_ZuiHong3.this, clomname, clomstr, YouHuiShangXian_TeHui.num);
	}
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(YouHuiShangXian_ZuiHong3.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}