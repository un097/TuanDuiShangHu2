package techown.shanghu;
/*
 * 城市活动--门店列表
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.biaoge.MyButton;
import techown.shanghu.https.HttpConnection2;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MendianList1 extends Activity {
	
	public Map<String, String[]>map=new HashMap<String,String[]>();
	public static StringBuilder para=new StringBuilder();
	public int numb=10;
	String bb;
	ListAdapter1 adapter;
	private Dialog mDialog;
	EditDialog log=new EditDialog();
	List<String[]>list= new ArrayList<String[]>();
	Handler handler = new Handler(){   
	       
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
	        	pagenum.setText(pageNo+"/"+pageCnt);
	        	adapter.notifyDataSetChanged();
	        	adapter.notifyDataSetInvalidated();
	        	break;
	        }   
	    }   
	};   
	//lxp
private Button button,tuibtn,shouye_xd,shangye_xd,xiaye_xd,moye_xd,fanhui_button;
ListView mainright_lv;
LinearLayout rightLinearLayout;

int j=0;

String pageCnt;
String pageNo;
TextView pagenum;
public static String mendianName;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	requestWindowFeature(Window.FEATURE_PROGRESS);
	super.onCreate(savedInstanceState);
	setContentView(R.layout.mengdianliebiao);

	ChengShiHuoDong1.requeststr1=null;
	mDialog = new AlertDialog.Builder(MendianList1.this).create();
//	init();		
	pagenum=(TextView)findViewById(R.id.pagenum);
	rightLinearLayout = (LinearLayout) findViewById(R.id.right_linearLayout);
	
	list.clear();
	rightLinearLayout.removeAllViews();
	LinearLayout view = (LinearLayout) LayoutInflater.from(
			MendianList1.this).inflate(R.layout.mendianitemlist,
			null);
	
	rightLinearLayout.addView(view);

	//lxp 将list中的值赋值给messages数组
	init();
	pagenum.setText(pageNo+"/"+pageCnt);
//    if(list.size()>0)
//    {
//    	 String [][] messages = new String[list.size()][6];
//         for(int i=0;i<list.size();i++)
//         {
//         	messages[i][0]=list.get(i)[2];
//         	messages[i][1]=list.get(i)[4]; 
//         	StringBuilder sb=new StringBuilder();
//         	for(int j=0;j<map.get("para").length;j++)
//         	{
//         		String[] paratemp=list.get(i)[6].split("|");
//         		for(int k=0;k<paratemp.length;k++)
//         		{
//         			if(paratemp[k].equals(map.get("para")[j]))
//             		{
//         				sb.append(map.get("parachinese")[j]+"、");  	
//             		}   	
//         		}
//         		
//         	}   
//         	messages[i][2]=sb.delete(sb.length()-1,sb.length()).toString();
//         	messages[i][3]=list.get(i)[0];      	
//         	messages[i][4]=list.get(i)[5];		
//         	messages[i][5]=list.get(i)[7];
//         }			            
         mainright_lv = (ListView) view.findViewById(R.id.mainright_lv);
     	 adapter=new ListAdapter1(MendianList1.this,list);
     	mainright_lv.setAdapter(adapter);  
//    }   
	shouye_xd = (Button)findViewById(R.id.shouye_xd);
    shangye_xd = (Button)findViewById(R.id.shangye_xd);
    xiaye_xd = (Button)findViewById(R.id.xiaye_xd);
    moye_xd = (Button)findViewById(R.id.moye_xd);
    fanhui_button= (Button)findViewById(R.id.fanhui_button);
    MainButtonListener lis = new MainButtonListener();
	
    shouye_xd.setOnClickListener(lis);
    shangye_xd.setOnClickListener(lis);
    xiaye_xd.setOnClickListener(lis);
    moye_xd.setOnClickListener(lis);
    fanhui_button.setOnClickListener(lis);
}


class MainButtonListener implements OnClickListener{
	Intent intent = getIntent();
	public void onClick(View v) {
		int v_id = v.getId();
		switch(v_id){
		case R.id.shouye_xd://首页
			if(Integer.parseInt(pageNo)==1)
			{
				log.Toast(MendianList1.this, "已经是首页！");		
			}
			else
			{
				pageData("1");	
				
			}
			
			break;	
		case R.id.shangye_xd://上一页
			if(Integer.parseInt(pageNo)>1)
			{
				pageData(String.valueOf(Integer.parseInt(pageNo)-1));	
				
			}
			else
			{
				log.Toast(MendianList1.this, "已经是首页！");	
			}
			
			break;	
		case R.id.xiaye_xd://下一页
			if(Integer.parseInt(pageNo)==Integer.parseInt(pageCnt))
			{	
				log.Toast(MendianList1.this, "已经是尾页！");	
			}
			else
			{
				pageData(String.valueOf(Integer.parseInt(pageNo)+1));
				
			}
			break;	
		case R.id.moye_xd://末页
			if(pageCnt.equalsIgnoreCase(pageNo))
			{
				log.Toast(MendianList1.this, "已经是尾页！");		
			}
			else
			{
				pageData(pageCnt);	
				
			}
			
			break;	
		case R.id.fanhui_button:
			intent.setClass(MendianList1.this, ChengShiHuoDong1.class);
			MendianList1.this.startActivity(intent);
			MendianList1.this.finish();
			overridePendingTransition(R.anim.rightin, R.anim.rightout);
			break;
			
		}				
	}
	
}
private void pageData(final String pagerno)
{
	
	Message msg3 = new Message();   
    msg3.what =1; 
	handler.sendMessage(msg3);
	 new Thread(){
		 public void run(){
			
	String[]str=new String[]{"","","","","","","","","","","","N",""};
	 
		
		str[2]=ChengShiChaXun4.tempstr1[0];
		
		
		str[9]=TuanDuiShangHuActivity.username;
	
		str[8]=TuanDuiShangHuActivity.cityId;
		str[12]=pagerno;
 		String json="{" +
		"\"misId\":\""+str[0]+"\"," +
		"\"terId\":\""+str[1]+"\"," +
		"\"licences\":\""+str[2]+"\"," +
		"\"companyName\":\""+str[3]+"\"," +
		"\"merName\":\""+str[4]+"\"," +
		"\"address\":\""+str[5]+"\"," +
		"\"merTel\":\""+str[6]+"\"," +
		"\"companyTel\":\""+str[7]+"\"," +
		"\"cityId\":\""+str[8]+"\"," +
		"\"userId\":\""+str[9]+"\"," +
		"\"activeMode\":\""+str[10]+"\"," +
		"\"isOnline\":\""+str[11]+"\"," +
		"\"pageNo\":\""+str[12]+"\"}";	 					    	
 		String requeststr=HttpConnection2.request(json,"A0009");//
 		mDialog.dismiss();	//
 		if(requeststr!=null)
 		{
 			try
			{
				JSONObject demoJson1 = new JSONObject(requeststr);
				
				if(demoJson1.getString("rspCode").equals("00"))
				{
					list.clear();
					JSONArray numberList1 =demoJson1.getJSONArray("detail");
					pageCnt=demoJson1.getString("pageCnt");
					pageNo=demoJson1.getString("pageNo");
					for(int j=0;j<numberList1.length();j++)
		       		{
						JSONObject datajson = (JSONObject)numberList1.opt(j);
						String[] tempstr=new String[10];
						tempstr[0]=datajson.getString("merName");
						tempstr[1]=datajson.getString("companyName");
						tempstr[2]=datajson.getString("merAddress");
						tempstr[3]=datajson.getString("licences");
						tempstr[4]=datajson.getString("merId");
						tempstr[5]=datajson.getString("actId");					
						tempstr[6]=datajson.getString("activeMode");
						tempstr[7]=datajson.getString("agreementId");
						tempstr[8]=datajson.getString("auditState");
						tempstr[9]=datajson.getString("auditReason");
										
						list.add(tempstr);				
		       		}
//					 String [][] messages = new String[list.size()][5];
//		             for(int i=0;i<list.size();i++)
//		             {
//		             	messages[i][0]=list.get(i)[2];
//		             	messages[i][1]=list.get(i)[4]; 
//		             	StringBuilder sb=new StringBuilder();
//		             	for(int j=0;j<map.get("para").length;j++)
//		             	{
//		             		String[] paratemp=list.get(i)[6].split("|");
//		             		for(int k=0;k<paratemp.length;k++)
//		             		{
//		             			if(paratemp[k].equals(map.get("para")[j]))
//		                 		{
//		             				sb.append(map.get("parachinese")[j]+"、");  	
//		                 		}   	
//		             		}
//		             		
//		             	}  
//		            
//		             	messages[i][2]=sb.delete(sb.length()-1,sb.length()).toString();
//		             	messages[i][3]=list.get(i)[0];      	
//		             	messages[i][4]=list.get(i)[5];	
//		             }
//		             	adapter.mItemList=messages;
					Message msg3 = new Message();   
			        msg3.what =2; 
					handler.sendMessage(msg3);
		            
				}
				else if(demoJson1.getString("rspCode").equals("10"))
				{
					Message msg = new Message();   
					Bundle b = new Bundle();
					b.putString("creat",demoJson1.getString("rspInfo"));
					msg.setData(b);
			        msg.what =3; 
					handler.sendMessage(msg);//
					
				}
				else 
				{
					Message msg = new Message();   
					Bundle b = new Bundle();
					b.putString("throwsmessage",demoJson1.getString("rspInfo"));
					msg.setData(b);
			        msg.what =2; 
					handler.sendMessage(msg);//
					
				}
			}catch(Exception e)
			{
				
				techown.shanghu.https.Log.Instance().WriteLog("团队：MendianList1"+e.getMessage());
			}	
 		}
 		
			}
		}.start();	
}
private void init() {
	// TODO Auto-generated method stub
	list.clear();
	try
	{
		JSONObject demoJson1 = new JSONObject(ChengShiChaXun4.requeststr2);
		
		if(demoJson1.getString("rspCode").equals("00"))
		{
			JSONArray numberList1 =demoJson1.getJSONArray("detail");
			pageCnt=demoJson1.getString("pageCnt");
			pageNo=demoJson1.getString("pageNo");
			for(int j=0;j<numberList1.length();j++)
       		{
				JSONObject datajson = (JSONObject)numberList1.opt(j);
				String[] tempstr=new String[10];
				tempstr[0]=datajson.getString("merName");
				tempstr[1]=datajson.getString("companyName");
				tempstr[2]=datajson.getString("merAddress");
				tempstr[3]=datajson.getString("licences");
				tempstr[4]=datajson.getString("merId");
				tempstr[5]=datajson.getString("actId");					
				tempstr[6]=datajson.getString("activeMode");
				tempstr[7]=datajson.getString("agreementId");
				tempstr[8]=datajson.getString("auditState");
				tempstr[9]=datajson.getString("auditReason");
								
				list.add(tempstr);							
       		}
		}
		else 
		{
			Toast.makeText(MendianList1.this,demoJson1.getString("rspInfo"),Toast.LENGTH_LONG).show();
		}
	}catch(Exception e)
	{
		techown.shanghu.https.Log.Instance().WriteLog("团队：MendianList1"+e.getMessage());
	}
	
}

int checknum;
class ListAdapter1 extends BaseAdapter {
	public List<String[]> mItemList; //修饰符不能是private
	public Context mContext;

	 
	 public ListAdapter1(Context con,List<String[]> itemList)
	 {
		 mItemList=itemList;
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
	            LayoutInflater inflater = LayoutInflater.from(MendianList1.this);
	            view = inflater.inflate(R.layout.shanghuxiadan2list2, null);
			}
            LinearLayout bgsrc = (LinearLayout)view.findViewById(R.id.bgsrc);
            
            TextView listtable_tv1 = (TextView) view.findViewById(R.id.listtable_tv1);
            TextView listtable_tv2 = (TextView) view.findViewById(R.id.listtable_tv2);
            TextView listtable_tv3 = (TextView) view.findViewById(R.id.listtable_tv3);
            listtable_tv1.setText(mItemList.get(position)[0]);
            listtable_tv2.setText(mItemList.get(position)[1]);
            listtable_tv3.setText(mItemList.get(position)[2]);

            listtable_tv1.setTextColor(Color.BLACK);
	        listtable_tv2.setTextColor(Color.BLACK);
	        listtable_tv3.setTextColor(Color.BLACK);
         
	        if(position%2==0)
	        {
	        	bgsrc.setBackgroundDrawable(getResources().getDrawable(R.drawable.a21));
	        }
	     
	        
	        MyButton listbutton=(MyButton)view.findViewById(R.id.shanghuxiadan);
	        listbutton.itemposition=position;
	        
//	        //影藏Button，0显示，4影藏，8删除
//	        listbutton.setVisibility(4);
//	        if(list.get(position)[6].indexOf("W")!=-1){
//	        	listbutton.setVisibility(0);
//	        }
	        
	        listbutton.setOnClickListener(
				new OnClickListener()
				{

					public void onClick(View arg0) {
						MyButton myButton=(MyButton)arg0;
						checknum=myButton.itemposition;
						// TODO Auto-generated method stub
						final String merId=list.get(checknum)[4];
						final String actId=list.get(checknum)[5];
//						String agreementId=list.get(checknum)[7];
						final Intent i=new Intent();
						 Message msg = new Message();   
					        msg.what =1; 
							handler.sendMessage(msg);//
							 new Thread(){
								 public void run(){
									 
									 
									 String[]str1=new String[]{"",""};	
									 str1[0]=merId;
									 str1[1]=actId;
									 
									 String json1="{" +
						 				"\"merId\":\""+str1[0]+"\"," +
						 				"\"actId\":\"\"}";	
									 ChengShiHuoDong31.requeststr1=HttpConnection2.HttpRequest(HttpConnection2.getHttpCon(TuanDuiShangHuActivity.indenty,"A0010"), json1);
									 if(ChengShiHuoDong31.requeststr1!=null&&ChengShiHuoDong31.requeststr1.length()>0){
										 try {
											JSONObject demoJson1 = new JSONObject(ChengShiHuoDong31.requeststr1);
											if(demoJson1.getString("rspCode").equals("00"))
											{
												i.setClass(MendianList1.this, ChengShiHuoDong31.class);
												MendianList1.this.startActivity(i);
												MendianList1.this.finish();
												overridePendingTransition(R.anim.leftin, R.anim.leftout);
												 mDialog.dismiss(); 
											}
											else if(demoJson1.equals("")){
												Message msg = new Message();   
												Bundle b = new Bundle();
												b.putString("throwsmessage","服务器连接超时，请重新点击下一步查询！"+demoJson1.getString("rspInfo"));
												msg.setData(b);
										        msg.what =4; 
												handler.sendMessage(msg);//
												mDialog.dismiss();
											}else
											{
												Message msg = new Message();   
												Bundle b = new Bundle();
												b.putString("throwsmessage",demoJson1.getString("rspInfo"));
												msg.setData(b);
										        msg.what =3; 
												handler.sendMessage(msg);//
												mDialog.dismiss();
											}
										} catch (Exception e) {
											// TODO Auto-generated catch block
											techown.shanghu.https.Log.Instance().WriteLog("团队：MendianList1"+e.getMessage());
										}
									
									 }
									 else 
										{
											Message msg = new Message();   
											Bundle b = new Bundle();
//											b.putString("throwsmessage",demoJson1.getString("rspInfo"));
											msg.setData(b);
									        msg.what =5; 
											handler.sendMessage(msg);//
											mDialog.dismiss();
										}
								 }}.start();	
						
						}																								
					}									
				);
	      
            return view;
        }
}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(MendianList1.this);
			break;
	
		}
		return super.onKeyDown(keyCode, event);
	}

}
