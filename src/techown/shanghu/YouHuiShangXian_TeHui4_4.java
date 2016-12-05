package techown.shanghu;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.https.HttpConnection2;
import techown.shanghu.photo.CGXYHTakePhotoActivity;
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
/*
 * （草稿箱）特惠 上线事宜
 */
public class YouHuiShangXian_TeHui4_4 extends Activity{
	Button button3,but2,but4,but6,but8,but9,but10;
	EditDialog log=new EditDialog();
	int i=0,j=0,k=0,l=0,m=0,n=0,o=0,p=0;
	
	int[] textIds = { R.id.y4edtext1, R.id.y4edtext2, R.id.y4edtext3};
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.y4leout1,R.id.y4leout2,R.id.y4leout3};
	public LinearLayout[] linlay;

	public ImageView iv;
	public static Bitmap bm;
	DES des = new DES();
	DBUtil DB=new DBUtil();
	public TextView active_neirong,duanxin,name,ka,time;
	/**
	 *  此返回的页面有功能为实现
	 *  上次这个页面的数据没有存库 
	 *  这边无法从草稿箱中获取
	 *   TuanDuiShangHuActivity.tmpId3
	 */
	
	
	public static String requeststr1;

    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.youhuishangxian_tehui4);
        iv=(ImageView)findViewById(R.id.image1);
        try {
//			Request();
			iv.setImageBitmap(bm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui4_4:Request()" + e.getMessage());
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
        name=(TextView)findViewById(R.id.name);
		ka=(TextView)findViewById(R.id.ka);
		time=(TextView)findViewById(R.id.time);
        
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		
		/**
		 * 返回时取数据
		 */
		qushuju();
		init();
		
		
		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(YouHuiShangXian_TeHui4_4.this,linlay[0], textArray[0],
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
					log.Dialog(YouHuiShangXian_TeHui4_4.this,linlay[1], textArray[1],
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
					log.Dialog(YouHuiShangXian_TeHui4_4.this,linlay[2], textArray[2],
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
					intent.setClass(YouHuiShangXian_TeHui4_4.this, CGXYHTakePhotoActivity.class);
					YouHuiShangXian_TeHui4_4.this.startActivity(intent);
					YouHuiShangXian_TeHui4_4.this.finish();
					overridePendingTransition(R.anim.leftin, R.anim.leftout);
			}
			}
		});

	
		
		but10.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(YouHuiShangXian_TeHui4_4.this, YouHuiShangXian_TeHui2_2.class);
				YouHuiShangXian_TeHui4_4.this.startActivity(intent);
				YouHuiShangXian_TeHui4_4.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
			}
		});
     
    }
    private boolean validate() {
		if(textArray[0].getText().toString().equals("")){
			//Toast.makeText(YouHuiShangXian_TeHui4_4.this, "请输入培训对象", Toast.LENGTH_SHORT).show();
			 log.Toast(YouHuiShangXian_TeHui4_4.this, "请输入培训对象");
			return false;
		}
		if(textArray[1].getText().toString().equals("")){
			//Toast.makeText(YouHuiShangXian_TeHui4_4.this, "请输入职位", Toast.LENGTH_SHORT).show();
			 log.Toast(YouHuiShangXian_TeHui4_4.this, "请输入职位");
			return false;
		}
		if(textArray[2].getText().toString().equals("")){
			//Toast.makeText(YouHuiShangXian_TeHui4_4.this, "请输入培训内容", Toast.LENGTH_SHORT).show();
			 log.Toast(YouHuiShangXian_TeHui4_4.this, "请输入培训内容");
			return false;
		}
		return true;
	}
    
    String[] cardType = new String[6];
    StringBuilder strb = new StringBuilder();
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
					strb.append("太平洋信用卡"+" ");
				}
				if(cardType[4].equals("1")){
					strb.append("太平洋卡"+" ");
				}
				if(cardType[5].equals("1")){
					strb.append("其他卡种"+" ");
				}
				
				time.setText("截止时间："+tempstr1[2]);
	    		name.setText("商户名："+tempstr1[4]);
	    		ka.setText("支持卡种："+strb);
				active_neirong.setText(tempstr1[0]);
	    		duanxin.setText(tempstr1[1]);
	    		
//	    		active_neirong.setText("hah");//公司名称
//	    		duanxin.setText("jj");//法人代表
			}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui4_4:init()" + e.getMessage());
		}	
    }
    
    List<String[]> sendtList = new ArrayList<String[]>();
	protected String Jsonstr;
	public static String requeststr;
//	public void Request() throws Exception {
//		Jsonstr = "{\"merId\":\""+CGXYHTakePhotoActivity.merId+"\",\"imgType\":\"00\"}";
//		String jsont=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0018"), Jsonstr);
//		try
//		{
//			JSONObject demoJson1 = new JSONObject(jsont);
//			if(demoJson1.getString("rspCode").equals("00"))
//			{
//				String[] tempstr=new String[3];
//				tempstr[0]=demoJson1.getString("imgStream"); 
//				byte[]pic=tempstr[0].getBytes();
//				byte[]  pic2=Base64.decode(pic,0);
//				Bitmap bm=BitmapFactory.decodeByteArray(pic2, 0, pic2.length);
//				iv.setImageBitmap(bm);
//	       	}
//		}catch(Exception e)
//		{
//			techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui4_4:Request()" + e.getMessage());
//		}
//	}
    
    
	 public void saveData() {
			String[] clomname = new String[] {"Name","Zhiwei","Neirong"};
			String[] clomstr = new String[clomname.length];
			clomstr[0] = textArray[0].getText().toString();
			clomstr[1] = textArray[1].getText().toString();
			clomstr[2] = textArray[2].getText().toString();

			
			DB.upload4(YouHuiShangXian_TeHui4_4.this, clomname, clomstr, TuanDuiShangHuActivity.tmpId3);
		}
	    

	 
	 /**
	  * 根据 草稿箱给的Id 得到  上一页 存入数据库的数据
	  * 
	  *  存入数据库了 三个数据 ，在此 我只取 三个
	  * 
	  * Name   		Zhiwei			Neirong
	  * 培训对象		职位				内容
	  * 
	  */
	 private void qushuju() {

			MyDatabaseHelper myHelper = new MyDatabaseHelper( YouHuiShangXian_TeHui4_4.this, "techown.db", Constant.tdshDBVer);
			SQLiteDatabase db = null;
			Cursor cursor = null;
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from youhushangxian where id==?", new String[] { TuanDuiShangHuActivity.tmpId3 });
			while (cursor.moveToNext()) {
				try {
					
					textArray[0].setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("Name"))));
					textArray[1].setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("Zhiwei"))));
					textArray[2].setText(des.jieMI(cursor.getString(cursor
							.getColumnIndex("Neirong"))));
				}catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("YouHuiShangXian_TeHui4_4:qushuju()" + e.getMessage());
				}finally{
					if(cursor!=null){
						cursor.close();
					}if(db!=null){
						db.close();
					}
				}
			}
		}
	 
	 
	 
	 
	 

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				log.ExitApp(YouHuiShangXian_TeHui4_4.this);
				break;
			default:
				break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
