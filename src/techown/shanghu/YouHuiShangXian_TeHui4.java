package techown.shanghu;

/*
 * 优惠上线-- 特惠 上线事宜
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.https.HttpConnection2;
import techown.shanghu.photo.YouHuiTakePhotoActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YouHuiShangXian_TeHui4 extends Activity{
	Button button3,but2,but4,but6,but8,but9,but10;
	EditDialog log=new EditDialog();
	int i=0,j=0,k=0,l=0,m=0,n=0,o=0,p=0;
	
	int[] textIds = { R.id.y4edtext1, R.id.y4edtext2, R.id.y4edtext3};
	public TextView[] textArray;
	DES des = new DES();
	DBUtil DB=new DBUtil();
	
	int[] linlayIds={R.id.y4leout1,R.id.y4leout2,R.id.y4leout3};
	public LinearLayout[] linlay;

	public ImageView iv;

	
	public TextView active_neirong,duanxin,name,ka,time;
	
	
	public static String requeststr1,jsont;

    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youhuishangxian_tehui4);
        iv=(ImageView)findViewById(R.id.image1);
        try {
			Request();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui4:Request()" + e.getMessage());
		}
        
        but2=(Button)findViewById(R.id.but2);
        but4=(Button)findViewById(R.id.but4);
        but6=(Button)findViewById(R.id.but6);
        but8=(Button)findViewById(R.id.but8);
        but9=(Button)findViewById(R.id.but9);
        but10=(Button)findViewById(R.id.but10);
        
        but9.setEnabled(false);

        active_neirong=(TextView)findViewById(R.id.active_neirong);
        duanxin=(TextView)findViewById(R.id.duanxin);
        
        
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		
		name=(TextView)findViewById(R.id.name);
		ka=(TextView)findViewById(R.id.ka);
		time=(TextView)findViewById(R.id.time);
		
		//读取数据
		  MyDatabaseHelper myHelper = new MyDatabaseHelper(
				  YouHuiShangXian_TeHui4.this, "techown.db", Constant.tdshDBVer);
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
					techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui4:读取数据()" + e.getMessage());
				}finally{
					if(cursor!=null){
						cursor.close();
					}if(db!=null){
						db.close();
					}
				}
			}
	        
		
		
		init();
		
		
		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(YouHuiShangXian_TeHui4.this,linlay[0], textArray[0],
							R.id.y4edtext1,"请输入培训对象");
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
					log.Dialog(YouHuiShangXian_TeHui4.this,linlay[1], textArray[1],
							R.id.y4edtext2,"请输入职位");
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
					log.Dialog(YouHuiShangXian_TeHui4.this,linlay[2], textArray[2],
							R.id.y4edtext3,"请输入内容");
					break;
				default:
					break;
				}

				return false;
			}
		});

		but2.setOnClickListener(new OnClickListener() {
					
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						but2.setEnabled(false);
						j=1;
						if(j==1&&l==1&&n==1&&p==1){
							but9.setEnabled(true);
						}
					}
				});
		but4.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				but4.setEnabled(false);
				l=1;
				if(j==1&&l==1&&n==1&&p==1){
					but9.setEnabled(true);
				}
			}
		});
		but6.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				but6.setEnabled(false);
				n=1;
				if(j==1&&l==1&&n==1&&p==1){
					but9.setEnabled(true);
				}
			}
		});
		but8.setOnClickListener(new OnClickListener() {
					
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				but8.setEnabled(false);
				p=1;
				if(j==1&&l==1&&n==1&&p==1){
					but9.setEnabled(true);
				}
			}
		});
		
		but9.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(validate()){
				saveData();
				Intent intent = new Intent();
				intent.setClass(YouHuiShangXian_TeHui4.this, YouHuiTakePhotoActivity.class);
				YouHuiShangXian_TeHui4.this.startActivity(intent);
				YouHuiShangXian_TeHui4.this.finish();
				overridePendingTransition(R.anim.leftin, R.anim.leftout);
			}
			}
		});
		but10.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(YouHuiShangXian_TeHui4.this, YouHuiShangXian_TeHui2.class);
				YouHuiShangXian_TeHui4.this.startActivity(intent);
				YouHuiShangXian_TeHui4.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
				
			}
		});
     
    }
    private boolean validate() {
		if(textArray[0].getText().toString().equals("")){
			 log.Toast(YouHuiShangXian_TeHui4.this,"请输入培训对象");
			return false;
		}
		if(textArray[1].getText().toString().equals("")){
			 log.Toast(YouHuiShangXian_TeHui4.this,"请输入职位");
			return false;
		}
		if(textArray[2].getText().toString().equals("")){
			 log.Toast(YouHuiShangXian_TeHui4.this,"请输入培训内容");
			return false;
		}
		return true;
	}
    
    String[] cardType = new String[6];
    String[] tempstr1=new String[6];
    StringBuilder strb = new StringBuilder();
   
    public void init()
    {
    	try
		{
    		JSONObject demoJson1 = new JSONObject(requeststr1);
    		 strb.append("");
			if(demoJson1.getString("rspCode").equals("00"))
			{
				
				tempstr1[0]=demoJson1.getString("saleContent");
				tempstr1[1]=demoJson1.getString("messageContent");
				tempstr1[2]=demoJson1.getString("endDate");
				tempstr1[3]=demoJson1.getString("cardType");
				tempstr1[4]=demoJson1.getString("merName");
				
				
				for(int i=0;i < tempstr1[3].length();i++){
					cardType[i]=tempstr1[3].substring(i,i+1);
				}
				if(cardType[0].equals("1")){
					strb.append("白金卡"+" ");
				}
				if(cardType[1].equals("1")){
					strb.append("普卡"+" ");
				}
				if(cardType[2].equals("1")){
					strb.append("金卡"+" ");
				}
				if(cardType[3].equals("1")){
					strb.append("所有太平洋信用卡"+" ");
				}
				if(cardType[4].equals("1")){
					strb.append("所有太平洋卡"+" ");
				}
				if(cardType[5].equals("1")){
					strb.append("其他卡种"+" ");
				}
				
				active_neirong.setText(tempstr1[0]);//公司名称
	    		duanxin.setText(tempstr1[1]);//法人代表
	    		time.setText("截止时间："+tempstr1[2]);
	    		name.setText("商户名："+tempstr1[4]);
	    		ka.setText("支持卡种："+strb);

			}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui4:init()" + e.getMessage());
		}	
    }
    
    List<String[]> sendtList = new ArrayList<String[]>();
	public static String requeststr;
	public void Request() throws Exception {

		

		
		
		try
		{
			JSONObject demoJson1 = new JSONObject(jsont);
			
			if(demoJson1.getString("rspCode").equals("00"))
			{
//				JSONArray numberList1 =demoJson1.getJSONArray("detail");
//				for(int j=0;j<numberList1.length();j++)
//	       		{
//					JSONObject datajson = (JSONObject)numberList1.opt(j);
					String[] tempstr=new String[3];
					tempstr[0]=demoJson1.getString("imgStream"); 
//					ByteArrayInputStream bais=new ByteArrayInputStream(null);
					byte[]pic=tempstr[0].getBytes();
					byte[]  pic2=Base64.decode(pic,0);
//					Bitmap bm=BitmapFactory.decodeByteArray(pic, 0, pic.length);
//					iv.setImageBitmap(bm);
				
				
				
					Bitmap bm=BitmapFactory.decodeByteArray(pic2, 0, pic2.length);
					iv.setImageBitmap(bm);
					
//				
	       		}
			
//			else 
//			{
//				Toast.makeText(YouHuiShangXian_TeHui4.this,demoJson1.getString("rspInfo"),Toast.LENGTH_LONG).show();
//			}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui4:Request()" + e.getMessage());
		}
	}
    
    
	 public void saveData() {
			String[] clomname = new String[] {"Name","Zhiwei","Neirong"};
			String[] clomstr = new String[clomname.length];
			clomstr[0] = textArray[0].getText().toString();
			clomstr[1] = textArray[1].getText().toString();
			clomstr[2] = textArray[2].getText().toString();

			
			DB.upload4(YouHuiShangXian_TeHui4.this, clomname, clomstr, YouHuiShangXian_TeHui.num);
		}
	    


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(YouHuiShangXian_TeHui4.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
