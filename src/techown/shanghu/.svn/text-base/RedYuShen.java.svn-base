package techown.shanghu;
/*
 * 合约签订--预审信息界面
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import techown.shanghu.biaoge.MyButton;
import techown.shanghu.date.GetDate;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;

public class RedYuShen extends Activity{
	LinearLayout rightLinearLayout;
	public static String[] requeststr;
	Button button2,button3;
	public static String id;
	static String numberno;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
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
	        }
	    }
	};
	
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redyushen);
        
        try{
        mDialog = new AlertDialog.Builder(RedYuShen.this).create();
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        
        button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RedNumber.selectno = "xia";
				Intent i=new Intent();
				i.setClass(RedYuShen.this, RedNumber.class);
				RedYuShen.this.startActivity(i);
				RedYuShen.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);	
			}
		});
        button3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setClass(RedYuShen.this, RedBigArea.class);
				RedYuShen.this.startActivity(i);
				RedYuShen.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);	
			}
		});
        
        rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
        
        try {
			Request();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_0类Request()"+e.getMessage());
		}
        
        ListView mainright_lv;
        rightLinearLayout.removeAllViews();
        rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
        
        LinearLayout view = (LinearLayout) LayoutInflater.from(
        		RedYuShen.this).inflate(R.layout.redyushen_list2,
				null);
        rightLinearLayout.addView(view);
		ListAdapter1 listAdapter = new ListAdapter1(RedYuShen.this,
				sendtList);
		mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);
		mainright_lv.setAdapter(listAdapter);
       
		
	}catch(Exception e){
		techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_0类："+e.getMessage());
	}
	}	
    
	int checknum;
	class ListAdapter1 extends BaseAdapter {
		 public List<String[]> mItemList; //修饰符不能是private
		public Context mContext;

		 
		 public ListAdapter1(Context con,List<String[]> sendtList)
		 {
			 mItemList=sendtList;
			 mContext=con;
		 }
	        public int getCount() {
	            return  mItemList.size();
	        }	      
	        public Object getItem(int position) {
	            return position;
	        }

	        public long getItemId(int position) {
	            return position;
	        }

	        public View getView(int position, View convertView, ViewGroup parent) {
	        	View view = convertView;
	        	if(view==null){
	        		LayoutInflater inflater = LayoutInflater.from(RedYuShen.this);
	        		view = inflater.inflate(R.layout.redyushen_list1, null);
	        	}
	            TextView listtable_tv1 = (TextView) view.findViewById(R.id.listtable_tv1);
	            TextView listtable_tv2 = (TextView) view.findViewById(R.id.listtable_tv2);
	            TextView listtable_tv3 = (TextView) view.findViewById(R.id.listtable_tv3);
	            
	            listtable_tv1.setText(mItemList.get(position)[0]);
	            listtable_tv2.setText(mItemList.get(position)[1]);
	            listtable_tv3.setText(mItemList.get(position)[2]);


	 	        listtable_tv1.setTextColor(Color.BLACK);
	 	        listtable_tv2.setTextColor(Color.BLACK);
	 	        listtable_tv3.setTextColor(Color.BLACK);
	 	    

	            return view;
	        }
			

	    }

	String time=GetDate.getDate1().toString(); 
	
	List<String[]> sendtList = new ArrayList<String[]>();
	String[] errgnumno;
	public void Request() throws JSONException {
		
		try
		{
			for (int i = 0; i < requeststr.length; i++) {
				if(requeststr[i] != null){
					errgnumno = requeststr[i].split("\\|");
					if(errgnumno[0].equals("成"))
					{
						JSONObject demoJson1 = new JSONObject(errgnumno[1]);
						if(demoJson1.getString("rspCode").equals("00"))
						{
							String[] tempstr=new String[3];
							tempstr[0]=demoJson1.getString("misId");//商户编号
							tempstr[1]=demoJson1.getString("txnCnt");//交易总笔数
							tempstr[2]=demoJson1.getString("txnAmt");//交易总金额
							sendtList.add(tempstr);
						}
						else 
						{
							log.Toast(RedYuShen.this, demoJson1.getString("rspInfo"));
						}
					}
					else if(errgnumno[0].equals("败"))
					{
						String[] tempstr=new String[3];
						tempstr[0]=errgnumno[1];//商户编号
						tempstr[1] = "0";//交易总笔数
						tempstr[2] = "0";//交易总金额
						sendtList.add(tempstr);
					}
				}
				
			}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队：HeYueQianDing_0出错"+e.getMessage());
		}
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(RedYuShen.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
