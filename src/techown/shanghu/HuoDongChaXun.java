package techown.shanghu;
/*
 * 活动查询
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.biaoge.MyButton;
import techown.shanghu.https.HttpConnection2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class HuoDongChaXun extends Activity{
	Button button1;
	LinearLayout rightLinearLayout;
	static List<String[]> list44;
	static String id;
   ListView mainright_lv ;
	String[] theme;
	String[] msgcontent;
	String[] activeId;
	String[] cityId;
	String[] msg_content;
	public static String actId,neirong,zhuti,msg1;
	public static String[]  Id,Id1;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	public  List<String[]> list = new ArrayList<String[]>();
	
	EditDialog ds=new EditDialog();
	 private Dialog mDialog;
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
		        	Bundle b = msg.getData();
		        	 b = msg.getData();
		        	 
		        	 
		        	 ds.Toast(HuoDongChaXun.this,b.getString("throwsmessage"));
//			        Toast.makeText(TuanDuiShangHuActivity.this, b.getString("throwsmessage"),Toast.LENGTH_SHORT).show();
			      //  Intent intent = new Intent();
			        break;
		       
		        }
		    }
		};
	
	
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huodongchaxun1);
        mDialog = new AlertDialog.Builder(HuoDongChaXun.this).create();//连接网络进程
        actId=null;
        
        list44=new ArrayList<String[]>();
        findsonmething3();
//        findsomething2();
        button1=(Button)findViewById(R.id.button);
        rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
        
        LinearLayout view = (LinearLayout) LayoutInflater.from(
				HuoDongChaXun.this).inflate(R.layout.faxingxiang_list6,
				null);
        rightLinearLayout.addView(view);
       
		ListAdapter1 listAdapter = new ListAdapter1(HuoDongChaXun.this,
				list44);
		mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);
	         mainright_lv.setAdapter(listAdapter);
       
		
		
		
        button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
		        
		        	intent.setClass(HuoDongChaXun.this, TuanDuiShangHuActivity.class);//跳转
		        	HuoDongChaXun.this.startActivity(intent);
		        	HuoDongChaXun.this.finish();//结束本Activity  
		        	overridePendingTransition(R.anim.rightin, R.anim.rightout);	
		     }
		});
    }	
    
    
    class ListAdapter1 extends BaseAdapter {
		 public List<String[]> mItemList; //修饰符不能是private
		public Context mContext;

		 
		 public ListAdapter1(Context con,List<String[]> itemList)
		 {
			 mItemList=itemList;
			 mContext=con;
		 }
	        public int getCount() {
	        	
//	            return theme.length;
	            return mItemList.size();
	        }	      
	        public Object getItem(int position) {
	            return position;
	        }

	        public long getItemId(int position) {
	            return position;
	        }

	        public View getView(final int position, View convertView, ViewGroup parent) {
	        	View view = convertView;
				if(view==null){
	        	
	            LayoutInflater inflater = LayoutInflater.from(HuoDongChaXun.this);
	            view = inflater.inflate(R.layout.listtable_item2, null);
				}
//	            int colorPos =position % colors.length;
	            TextView listtable_tv1 = (TextView) view.findViewById(R.id.listtable_tv1);
	            TextView listtable_tv2 = (TextView) view.findViewById(R.id.listtable_tv2);
	            listtable_tv1.setText(mItemList.get(position)[0]);
	            listtable_tv2.setText(mItemList.get(position)[1]);
	            
	            MyButton listbutton=(MyButton)view.findViewById(R.id.shortcut_delete);
		        listbutton.itemposition=position;
		        listbutton.setOnClickListener(
						new OnClickListener() 
						{

							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								
								 Message msg = new Message();   
							     msg.what =1; 
								 handler.sendMessage(msg);//
								 new Thread()
									{
										public void run()
										{
								
								
								Intent intent = new Intent();
								zhuti=mItemList.get(position)[0];//活动主题
								System.out.println("活动主题==="+zhuti);
								neirong=mItemList.get(position)[1];//活动描述
						        actId=mItemList.get(position)[2];//活动ID
						        msg1=mItemList.get(position)[3];//短信内容
						        String Jsonstr;
						        Jsonstr = "{\"misId\":\"\",\"terId\":\"\",\"licences\":\"\",\"companyName\":\"\",\"merName\":\"\",\"address\":\"\",\"merTel\":\"\",\"companyTel\":\"\",\"cityId\":\"\",\"userId\":\"\",\"activeMode\":\""+HuoDongChaXun.actId+"\",\"isOnline\":\"N\",\"pageNo\":\"\"}";
								HuoDongChaXun21.jsont=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0009"), Jsonstr);
								mDialog.dismiss();
								intent.setClass(HuoDongChaXun.this, HuoDongChaXun21.class);//跳转
					        	HuoDongChaXun.this.startActivity(intent);
					        	HuoDongChaXun.this.finish();//结束本Activity  
					        	overridePendingTransition(R.anim.leftin, R.anim.leftout);
							}}.start();
							}																								
						}									
				);
		        
		        
	 	        listtable_tv1.setTextColor(Color.BLACK);
		        listtable_tv2.setTextColor(Color.BLACK);
	         
	           
	            return view;
	        }
			

	    }

    public void findsonmething3()
    {
    	try {
    		list44=DB.select_cityactive(HuoDongChaXun.this, "subject,description,activeId,simMode", "t_cityactive");
			for(int i=0;i<list44.get(0).length;i++){
				Log.i("output", String.valueOf(list44.get(0)[i]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
	    	techown.shanghu.https.Log.Instance().WriteLog("HongDongChaXun类findsomething3（）"+e.getMessage());

		}
    }
    
    
    //调试查询数据库获取数据时间
    public void findsomething2(){
    	
			try {
				theme=DB.klQuery2(HuoDongChaXun.this, "subject", "t_cityactive");
					list44.add(theme);
				for(int i=0;i<list44.get(0).length;i++){
					Log.i("output", String.valueOf(list44.get(0)[i]));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
		    	techown.shanghu.https.Log.Instance().WriteLog("HongDongChaXun类findsomething2（）"+e.getMessage());

			}
				try {
					msgcontent=DB.klQuery2(HuoDongChaXun.this, "description", "t_cityactive");
					
						list44.add(msgcontent);
					for(int i=0;i<list44.get(1).length;i++){
						Log.i("output", String.valueOf(list44.get(1)[i]));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
			    	techown.shanghu.https.Log.Instance().WriteLog("HongDongChaXun类findsomething2（）"+e.getMessage());

				}
		
				
				try {
					activeId=DB.klQuery2(HuoDongChaXun.this, "activeId", "t_cityactive");
					
						list44.add(activeId);
					for(int i=0;i<list44.get(2).length;i++){
						Log.i("output", String.valueOf(list44.get(2)[i]));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
			    	techown.shanghu.https.Log.Instance().WriteLog("HongDongChaXun类findsomething2（）"+e.getMessage());

				}
				try {
					msg_content=DB.klQuery2(HuoDongChaXun.this, "simMode", "t_cityactive");
					
						list44.add(msg_content);
					for(int i=0;i<list44.get(3).length;i++){
						Log.i("output", String.valueOf(list44.get(3)[i]));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
			    	techown.shanghu.https.Log.Instance().WriteLog("HongDongChaXun类findsomething2（）"+e.getMessage());

				}
	
    }
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(HuoDongChaXun.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
	

	
}
