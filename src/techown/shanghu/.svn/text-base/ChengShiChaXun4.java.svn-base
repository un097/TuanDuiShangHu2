package techown.shanghu;

import java.util.List;

import org.json.JSONObject;

import techown.shanghu.date.DES;
import techown.shanghu.date.TArea;
import techown.shanghu.date.TCity;
import techown.shanghu.https.HttpConnection2;
import techown.shanghu.photo.CaoYouHuiTakePhotoActivity2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/*
 * 城市活动选着大区城市
 */
public class ChengShiChaXun4 extends Activity{
	
	DES des = new DES();
	DBUtil DB=new DBUtil();
	public static String num,CompanyLicense;
	public static int a;
	private Button button1,button2;
	private TextView text1,text2,text3;
	public static String[] tempstr1;
	String[]province;
	private Dialog mDialog;
	public String cityid;
	public static String requeststr1;
	public static String requeststr2;
	public static String requeststr3;
	AlertDialog alterdlg;
	EditDialog log=new EditDialog();
	EditDialog ds=new EditDialog();
	Handler handler = new Handler(){   //
	       
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
	        	DB.upload(ChengShiChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"N"}, TuanDuiShangHuActivity.num);
	        	
	        	Bundle b = msg.getData();
//	        	Toast.makeText(ChengShiChaXun4.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
	        	Intent intent = new Intent();
	        	intent.setClass(ChengShiChaXun4.this, ChengShiHuoDong1.class);
				ChengShiChaXun4.this.startActivity(intent);
				ChengShiChaXun4.this.finish();
	        	break;
	        case 3:
//	        	DB.upload(ChengShiChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	
	        	 b = msg.getData();
//	        	Toast.makeText(ChengShiChaXun4.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
//	        	intent = new Intent();
//	        	intent.setClass(ChengShiChaXun4.this, ChengShiHuoDong1.class);
//				ChengShiChaXun4.this.startActivity(intent);
//				ChengShiChaXun4.this.finish();	
	        	
				
//				ab = new AlertDialog.Builder(ChengShiChaXun4.this);
				Builder ab = new AlertDialog.Builder(ChengShiChaXun4.this);
				ab.setTitle("提示");
				ab.setMessage(b.getString("throwsmessage")+"，是否新建公司");
				ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				ab.setPositiveButton// 为对话框设置按钮
				("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						DB.upload(ChengShiChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
			        	
						Intent intent = new Intent();
			        	intent.setClass(ChengShiChaXun4.this, ChengShiHuoDong1.class);
						ChengShiChaXun4.this.startActivity(intent);
						ChengShiChaXun4.this.finish();	
						
					}
				});			
				if(alterdlg==null||!alterdlg.isShowing()){
					alterdlg = ab.create();// 创建对话框
					alterdlg.show();// 显示对话框
					alterdlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
				}	
				
	        	break;
	        case 4:
//	        	DBUtil.upload(ChengShiChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	
	        	b = msg.getData();
	        	ds.Toast(ChengShiChaXun4.this,b.getString("throwsmessage"));
//		        Toast.makeText(ChengShiChaXun4.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
		        intent = new Intent();
	        	break;	
	        case 5:
	        	DB.upload(ChengShiChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
	        	
	        	 b = msg.getData();
	        	 ds.Toast(ChengShiChaXun4.this,b.getString("throwsmessage"));
		       // Toast.makeText(ChengShiChaXun4.this, b.getString("throwsmessage"),Toast.LENGTH_LONG).show();
		        intent = new Intent();
		        intent.setClass(ChengShiChaXun4.this, ChengShiHuoDong1.class);
				ChengShiChaXun4.this.startActivity(intent);
				ChengShiChaXun4.this.finish();	
	        	break;
	        }   
	    }   
	};   
    public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chenshichaxun4);
        TuanDuiShangHuActivity.Query(ChengShiChaXun4.this);
        DB.insertContactId(ChengShiChaXun4.this,"Id",TuanDuiShangHuActivity.num);
        a=0;
        ChengShiHuoDong1.requeststr1=null;
        requeststr1 = null;
    	requeststr2 = null;
    	requeststr3 = null;
    	CompanyLicense=null;
        mDialog = new AlertDialog.Builder(ChengShiChaXun4.this).create();//连接网络进程
        List<TArea> tareaList=DB.klQuery(ChengShiChaXun4.this,"t_area");
        province=new String[tareaList.size()];
        for(int i=0;i<tareaList.size();i++){
        	province[i]=tareaList.get(i).name;
        }
//        panel=new Panel(this);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        MyLis lis = new MyLis();
        button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
        
        text1=(TextView)findViewById(R.id.hyedit1);
        text1.setText(TuanDuiShangHuActivity.areaName);
        text2=(TextView)findViewById(R.id.hyedit2);
        text2.setText(TuanDuiShangHuActivity.cityname);
        text3=(TextView)findViewById(R.id.hyedit3);
        text2.setEnabled(false);
    
        
//        MyDatabaseHelper myHelper = new MyDatabaseHelper(
//				ChengShiChaXun4.this, "techown.db", 1);
//		SQLiteDatabase db = myHelper.getWritableDatabase();
//		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
//				new String[] { TuanDuiShangHuActivity.num });
//		while (cursor.moveToNext()) {
//			try {
//				text3.setText( des.jieMI(cursor.getString(cursor
//						.getColumnIndex("CompanyLicense"))));
//			}catch (Exception e) {
//				techown.shanghu.https.Log.Instance().WriteLog("团队：ChengShiChaXun4异常"+e.getMessage());
//			}finally{
//				if(cursor!=null){
//					cursor.close();
//				}
//				if(db!=null){
//					db.close();
//				}
//			}
//			
//		}
		 
     
		text3.setText(ChengShiHuoDong1.CompanyLicense1);
        text3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog4(ChengShiChaXun4.this, text3,
						R.id.hyedit3);
			}
		});
    }
    
    //保存
    public void saveData() {
		String[] clomname = new String[] { "ActiveType",
				"CompanyLicense","storeauditId","YesNXieYi","MaintainUserId","ExpandUserId" };
		String[] clomstr = new String[clomname.length];
		clomstr[0] = "MARKETACTIVE";
//		clomstr[1] = text1.getText().toString();
//		clomstr[2] = text2.getText().toString();
		clomstr[1] = text3.getText().toString();
		CompanyLicense=text3.getText().toString();
		clomstr[2] = "";
		clomstr[3] ="N";
		clomstr[4] = TuanDuiShangHuActivity.username;
		clomstr[5] = TuanDuiShangHuActivity.username;
//		String id=DBUtil.klQueryCity(this, "t_area", "where Name = '"+clomstr[1]+"'");
//		clomstr[1]=id;
//		
		List<TCity> tcity=DB.cityQuerycity(ChengShiChaXun4.this,"t_city"," where Name = '"+des.jiaMi(text2.getText().toString())+"'");
		cityid = tcity.get(0).id;
		DB.upload(ChengShiChaXun4.this, new String[]{"CityId"},new String[]{tcity.get(0).id}, TuanDuiShangHuActivity.num);
		
		DB.upload(ChengShiChaXun4.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
    

    class MyLis implements OnClickListener{

	
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch(id){
				case R.id.button1:
					ChengShiHuoDong1.CompanyLicense1=null;
					intent.setClass(ChengShiChaXun4.this, TuanDuiShangHuActivity.class);
					ChengShiChaXun4.this.startActivity(intent);
					ChengShiChaXun4.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
				case R.id.button2://下一页
					if(validate()){
						
			
					saveData();
					//第一次与服务器交互    A0008
					
					 //lxp
					 Message msg = new Message();   
				        msg.what =1; 
						handler.sendMessage(msg);//
						 new Thread(){
					 						
						 public void run(){
							 Intent intent = new Intent();
							 String[]str1=new String[]{"","",""};	
							 str1[0]=text3.getText().toString();
							 str1[1]=cityid;
							 str1[2]=TuanDuiShangHuActivity.username;
							 String json1="{" +
				 				"\"licences\":\""+str1[0]+"\"," +
				 				"\"city\":\""+str1[1]+"\"," +
				 				"\"userId\":\""+str1[2]+"\"}";	
							 ChengShiChaXun4.requeststr1=HttpConnection2.request(json1,"A0008");
							 ChengShiHuoDong1.requeststr1=HttpConnection2.request(json1,"A0008");
							 if(ChengShiChaXun4.requeststr1!=null&&ChengShiChaXun4.requeststr1.length()>0)
								{
									try
									{
										JSONObject demoJson1 = new JSONObject(requeststr1);
										
										if(demoJson1.getString("rspCode").equals("00"))
										{
												//从json中拿值存入tempstr数组
												tempstr1=new String[2];
												tempstr1[0]=text3.getText().toString();
//													demoJson1.getString("busiName");//busiName
												
												//继续进行第二次交互    A0009
												 String[]str2=new String[]{"","","","","","","","","","","S*H*M*W","N",""};
												 str2[9]=TuanDuiShangHuActivity.username;
												 str2[2]=tempstr1[0];
												 str2[8]=cityid;
												 str2[12]="1";
												 
												 String json2="{" +
									 				"\"misId\":\""+str2[0]+"\"," +
									 				"\"terId\":\""+str2[1]+"\"," +
									 				"\"licences\":\""+str2[2]+"\"," +
									 				"\"companyName\":\""+str2[3]+"\"," +
									 				"\"merName\":\""+str2[4]+"\"," +
									 				"\"address\":\""+str2[5]+"\"," +
									 				"\"merTel\":\""+str2[6]+"\"," +
									 				"\"companyTel\":\""+str2[7]+"\"," +
									 				"\"cityId\":\""+str2[8]+"\"," +
									 				"\"userId\":\""+str2[9]+"\"," +
									 				"\"activeMode\":\""+str2[10]+"\"," +
									 				"\"isOnline\":\""+str2[11]+"\"," +
									 				"\"pageNo\":\""+str2[12]+"\"}";			 					    	
												 ChengShiChaXun4.requeststr2=HttpConnection2.request(json2,"A0009");
											 	//ZhongBuHuoDong1.requeststr2=HttpConnection2.request(json2,"A0009");
											 	mDialog.dismiss();	//
											 	if(ChengShiChaXun4.requeststr2!=null)
											 	{
											 		JSONObject demoJson2 = new JSONObject(ChengShiChaXun4.requeststr2);
											 		if(demoJson2.getString("rspCode").equals("00"))
											 		{
														
															a=1;
															saveData();//保存界面的信息到数据库
															DB.upload(ChengShiChaXun4.this, new String[]{"IsNewCompany"}, new String[]{"N"}, TuanDuiShangHuActivity.num);
															intent.setClass(ChengShiChaXun4.this, ChengShiHuoDong1.class);
															ChengShiChaXun4.this.startActivity(intent);
															ChengShiChaXun4.this.finish();
															overridePendingTransition(R.anim.leftin, R.anim.leftout);
														
													}
											 		else if(demoJson1.getString("rspCode").equals("800")){
														Message msg = new Message();   
														Bundle b = new Bundle();
														b.putString("throwsmessage",demoJson1.getString("rspInfo"));
														msg.setData(b);
												        msg.what =4; 
														handler.sendMessage(msg);//
														mDialog.dismiss();
													}
											 		else
													{
											 			Message msg = new Message();   
														Bundle b = new Bundle();
														b.putString("throwsmessage",demoJson2.getString("rspInfo"));
														msg.setData(b);
												        msg.what =2; 
														handler.sendMessage(msg);//
														mDialog.dismiss();
													}
											 	}
										}
										else 
										{
											
											if(demoJson1.equals(""))
											{
												Message msg = new Message();   
												Bundle b = new Bundle();
												b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson1.getString("rspInfo"));
												msg.setData(b);
										        msg.what =4; 
												handler.sendMessage(msg);//
												mDialog.dismiss();
											}
											else
											{
												Message msg = new Message();   
												Bundle b = new Bundle();
												b.putString("throwsmessage",demoJson1.getString("rspInfo"));
												msg.setData(b);
										        msg.what =3; 
												handler.sendMessage(msg);//
												mDialog.dismiss();
											}
										}
									}catch(Exception e)
									{
										techown.shanghu.https.Log.Instance().WriteLog("ChengShiChaXun4类R.id.button2://下一页"+e.getMessage());
									}	
								}
							 else if(ChengShiChaXun4.requeststr1==null)
								{
									Message msg = new Message();   
									Bundle b = new Bundle();
									b.putString("throwsmessage","网络连接失败，请重新点击下一步查询！");
									msg.setData(b);
							        msg.what =4; 
									handler.sendMessage(msg);//
									mDialog.dismiss();
								}
						 }
					 }.start();				
						 
						 
						 

					}
//			
				break;
			}
		}
    }
    
  
   
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(ChengShiChaXun4.this);
			ChengShiHuoDong1.CompanyLicense1=null;
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
    
    protected boolean validate() {
    	if (text1.getText().toString().equals("")) {
//			Toast.makeText(ChengShiChaXun4.this, "大区不能为空！", Toast.LENGTH_LONG)
//					.show();
			log.Toast(ChengShiChaXun4.this, "大区不能为空！");
			return false;
		}
    	if (text2.getText().toString().equals("")) {
//			Toast.makeText(ChengShiChaXun4.this, "城市不能为空！", Toast.LENGTH_LONG)
//					.show();
			log.Toast(ChengShiChaXun4.this,  "城市不能为空！");
			return false;
		}
    	if (text3.getText().toString().equals("")) {
//			Toast.makeText(ChengShiChaXun4.this, "请输入营业执照号！", Toast.LENGTH_LONG)
//					.show();
			log.Toast(ChengShiChaXun4.this,  "请输入营业执照号！");
			return false;
		}
		return true;
    }
}
