package techown.shanghu;
/*
 * 活动商户清单
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import techown.shanghu.biaoge.MyButton;
import techown.shanghu.https.HttpConnection2;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HuoDongChaXun21 extends Activity{
	Button button1;
	LinearLayout rightLinearLayout;
	ListView mainright_lv,lv;
	TextView textview4,textview5;
	public static String licences;
	public static String companyCity;
	public  List<String[]> list = new ArrayList<String[]>();
	public  List<String[]> sendtList1 = new ArrayList<String[]>();
	static String  actid1;
	public static String jsont;
	EditDialog log=new EditDialog();
	
	
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
		        	 
		        	 
		        	 ds.Toast(HuoDongChaXun21.this,b.getString("throwsmessage"));
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
        setContentView(R.layout.huodongchaxun21);
        mDialog = new AlertDialog.Builder(HuoDongChaXun21.this).create();//连接网络进程
        Intent it=getIntent();
     //   String zhuti=it.getStringExtra("zhuti");
      //  String neirong=it.getStringExtra("neirong");
//        actId=it.getStringExtra("actId");
        button1=(Button)findViewById(R.id.button1);
        try {
			Request();
		} catch (Exception e) {
			// TODO Auto-generated catch block
	    	techown.shanghu.https.Log.Instance().WriteLog("HongDongChaXun21类Request()"+e.getMessage());

		}
        
        textview4=(TextView)findViewById(R.id.textView4);
        textview5=(TextView)findViewById(R.id.textView5);
        textview4.setText(HuoDongChaXun.zhuti);
        textview5.setText(HuoDongChaXun.neirong);
        rightLinearLayout=(LinearLayout)findViewById(R.id.ss_layout);
        
        list.clear();
		rightLinearLayout.removeAllViews();
//		rightLinearLayout.setBackgroundDrawable(getResources()
//				.getDrawable(R.drawable.findbg));
		LinearLayout view = (LinearLayout) LayoutInflater.from(
				HuoDongChaXun21.this).inflate(R.layout.faxingxiang_list5,
				null);
		
		rightLinearLayout.addView(view);
		//sendQuery();
		
		
			
		
		ListAdapter1 listAdapter = new ListAdapter1(HuoDongChaXun21.this,
				sendtList1);
		mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);
		
		mainright_lv.setAdapter(listAdapter);

		button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setClass(HuoDongChaXun21.this,HuoDongChaXun.class);
				HuoDongChaXun21.this.startActivity(i);
				HuoDongChaXun21.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);	
			}
		});
	}

    class ListAdapter1 extends BaseAdapter {
		public List<String[]> mItemList; //修饰符不能是private
		public Context mContext;

		 
		 public ListAdapter1(Context con,List<String[]> sendtList1)
		 {
			 mItemList=sendtList1;
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

	        public View getView(final int position, View convertView, ViewGroup parent) {
	        	View view = convertView;
				if(view==null){
		            LayoutInflater inflater = LayoutInflater.from(HuoDongChaXun21.this);
		            view = inflater.inflate(R.layout.listtable_item6, null);
				}
//	            int colorPos =position % colors.length;
	            TextView listtable_tv1 = (TextView) view.findViewById(R.id.listtable_tv1);
	            listtable_tv1.setText(mItemList.get(position)[1]);
//	          
//	            listtable_tv1.setBackgroundColor(colors[colorPos]);	           
//	 	        listtable_tv2.setBackgroundColor(colors[colorPos]);	           
//	 	        listtable_tv3.setBackgroundColor(colors[colorPos]);
	 	        listtable_tv1.setTextColor(Color.BLACK);
	         
		    
		        
		        MyButton listbutton=(MyButton)view.findViewById(R.id.shortcut_delete);
		        listbutton.itemposition=position;
		        
		        //影藏Button，0显示，4影藏，8删除
		       // listbutton.setVisibility(4);
		        //if(mItemList.get(position)[6].equals("aad2")){
		        	//listbutton.setVisibility(0);
		       // }
		        
		        listbutton.setOnClickListener(
					new OnClickListener()
					{

						public void onClick(View arg0) {
							MyButton myButton=(MyButton)arg0;
							 Message msg = new Message();   
						     msg.what =1; 
							 handler.sendMessage(msg);//
							 new Thread()
								{
									public void run()
									{
							
							
							try{
							// TODO Auto-generated method stub
							
//							HeYueQianDing_21.str=mItemList.get(myButton.itemposition);
							Intent i=new Intent();
						//i.putExtra("neirong", textview4.getText().toString());
					     // i.putExtra("zhuti", textview5.getText().toString());
					      i.putExtra("merid", mItemList.get(position)[0]);
					      i.putExtra("companyName", mItemList.get(position)[2]);
					      i.putExtra("merName", mItemList.get(position)[1]);
					      	actid1= mItemList.get(position)[6];//
					      	licences = mItemList.get(position)[3];
					      	companyCity = mItemList.get(position)[10];
					      	String merid=mItemList.get(position)[0];
					      	
					      	
					      	String Jsonstr = "{\"licences\":\""+HuoDongChaXun21.licences+"\",\"city\":\""+HuoDongChaXun21.companyCity+"\",\"userId\":\""+TuanDuiShangHuActivity.username+"\"}";
					      	HuoDongChaXun31.jsont=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0008"), Jsonstr);
						
							String Jsonstr1 = "{\"merId\":\""+merid+"\",\"actId\":\""+HuoDongChaXun21.actid1+"\"}";
							HuoDongChaXun31.jsont1=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0010"), Jsonstr1);

							mDialog.dismiss();
							i.setClass(HuoDongChaXun21.this,HuoDongChaXun31.class);
							HuoDongChaXun21.this.startActivity(i);
							HuoDongChaXun21.this.finish();
							overridePendingTransition(R.anim.leftin, R.anim.leftout);
							}catch(Exception e){
								techown.shanghu.https.Log.Instance().WriteLog("HuoDongChaXun21类listbutton"+e.getMessage());
							}
							}	}.start();
						}																									
						}									
					);
		     
		      
	            return view;
	        }
			

	    }
    
    String actId1;
	
	protected String Jsonstr;
	
	public void Request() throws Exception {

		
		
	
		try
		{
			JSONObject demoJson1 = new JSONObject(jsont);
			
			if(demoJson1.getString("rspCode").equals("00"))
			{
				JSONArray numberList1 =demoJson1.getJSONArray("detail");
				for(int j=0;j<numberList1.length();j++)
	       		{
					JSONObject datajson = (JSONObject)numberList1.opt(j);
					String[] tempstr=new String[11];
					tempstr[0]=datajson.getString("merId");//3634ba6f34e498110134e5085bbe0011
					tempstr[1]=datajson.getString("merName");
					tempstr[2]=datajson.getString("companyName");
					tempstr[3]=datajson.getString("licences");
					tempstr[4]=datajson.getString("merAddress");
					tempstr[5] = datajson.getString("actId");
					String lei=datajson.getString("activeMode");
					String[] arrg1 = lei.split("\\|");
					String[] errg1 = tempstr[5].split("\\|");
					for (int i = 0; i < arrg1.length; i++) {
						if (arrg1[i].equals("M")) {
							actId1 = errg1[i];
						}
					}
					tempstr[6]= actId1;
					
//					tempstr[6]=datajson.getString("activeMode");
					tempstr[7]=datajson.getString("agreementId");
					tempstr[8]=datajson.getString("auditState");
					tempstr[9]=datajson.getString("auditReason");
					tempstr[10]=datajson.getString("companyCity");
					sendtList1.add(tempstr);
//					licences=tempstr[3];
//					companyCity=datajson.getString("companyCity");
	       		} 
			}
			else 
			{
				log.Toast(HuoDongChaXun21.this,demoJson1.getString("rspInfo"));
			}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("HuoDongChaXun21类request()"+e.getMessage());
		}
	}
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(HuoDongChaXun21.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}	

   

