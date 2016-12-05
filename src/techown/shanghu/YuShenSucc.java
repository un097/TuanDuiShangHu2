package techown.shanghu;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class YuShenSucc extends Activity {
    /** Called when the activity is first created. */
	
	 ListView list;
	 List<String[]> sendtList = new ArrayList<String[]>();
	 EditDialog log=new EditDialog();
	 public static String requeststr;
	 Button btnback,btnnext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.yushensucc);
        
        try {
			Request();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        list = (ListView)findViewById(R.id.listmessage);
        
      
        ListAdapter1 listAdapter = new ListAdapter1(YuShenSucc.this,
				sendtList);
        list.setAdapter(listAdapter);
        
        btnback = (Button) findViewById(R.id.butback);
        btnnext = (Button) findViewById(R.id.butnext);
        
        btnnext.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(YuShenSucc.this, HeYueQianDing_1.class);//跳转
				YuShenSucc.this.startActivity(intent);
				YuShenSucc.this.finish();//结束本Activity  
	        	overridePendingTransition(R.anim.leftin, R.anim.leftout);
			}
		});
        
        btnback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(YuShenSucc.this, HeYueQianDing_0.class);//跳转
				YuShenSucc.this.startActivity(intent);
				YuShenSucc.this.finish();//结束本Activity  
	        	overridePendingTransition(R.anim.rightin, R.anim.rightout);
			}
		});
    }
    
    class ListAdapter1 extends BaseAdapter {
   	 public List<String[]> mItemList; //修饰符不能是private
   	public Context mContext;

   	 
   	 public ListAdapter1(Context con,List<String[]> sendtList)
   	 {
   		 mItemList=sendtList;
   		 mContext=con;
   	 }
          public int getCount() {
        	  if(mItemList!=null){
        		  return mItemList.size();
        	  }
              return  0;
          }	      
          public Object getItem(int position) {
              return position;
          }

          public long getItemId(int position) {
              return position;
          }

          public View getView(int position, View convertView, ViewGroup parent) {
          	View view = null;
          	if(view==null){
          		LayoutInflater inflater = LayoutInflater.from(YuShenSucc.this);
          		view = inflater.inflate(R.layout.listtable_item3yushen, null);
          	}
              TextView listtable_tv1 = (TextView) view.findViewById(R.id.listtable_tv1);
              TextView listtable_tv2 = (TextView) view.findViewById(R.id.listtable_tv2);
              TextView listtable_tv3 = (TextView) view.findViewById(R.id.listtable_tv3);
              TextView listtable_tv4 = (TextView) view.findViewById(R.id.listtable_tv4);
              TextView listtable_tv11 = (TextView) view.findViewById(R.id.listtable_tv11);
              TextView listtable_tv22 = (TextView) view.findViewById(R.id.listtable_tv22);
              TextView listtable_tv33 = (TextView) view.findViewById(R.id.listtable_tv33);
              TextView listtable_tv44 = (TextView) view.findViewById(R.id.listtable_tv44);
              TextView listtable_tv5 = (TextView) view.findViewById(R.id.listtable_tv5);
              
              listtable_tv1.setText(mItemList.get(position)[5]);
              listtable_tv2.setText(mItemList.get(position)[6]);
              listtable_tv3.setText(mItemList.get(position)[7]);
              listtable_tv4.setText(mItemList.get(position)[8]);
              listtable_tv11.setText(mItemList.get(position)[2]);
              listtable_tv22.setText(mItemList.get(position)[1]);
              listtable_tv33.setText(mItemList.get(position)[4]);
              listtable_tv44.setText(mItemList.get(position)[0]);
              
              if(mItemList.get(position)[7].equals("")&&
            	mItemList.get(position)[8].equals("")&&
            	mItemList.get(position)[3].equals(""))
              {
            	  listtable_tv5.setText("未查询到相关的交易信息");
            	  listtable_tv5.setTextColor(Color.RED);
              }
              else
              {
            	  listtable_tv5.setText(mItemList.get(position)[3]); 
            	  listtable_tv5.setTextColor(Color.BLACK);
              }
   	        listtable_tv1.setTextColor(Color.BLACK);
   	        listtable_tv2.setTextColor(Color.BLACK);
   	        listtable_tv3.setTextColor(Color.BLACK);
   	        listtable_tv4.setTextColor(Color.BLACK);
   	        listtable_tv11.setTextColor(Color.BLACK);
	        listtable_tv22.setTextColor(Color.BLACK);
	        listtable_tv33.setTextColor(Color.BLACK);
	        listtable_tv44.setTextColor(Color.BLACK);
              return view;
          }
      }
    
    
    public void Request() throws JSONException {
		//lxp
		try
		{
			JSONObject demoJson1 = new JSONObject(requeststr);
			if(requeststr != null){
				
			if(demoJson1.getString("rspCode").equals("00"))
			{
				
				String[] tempstr=null;
				String[] cardNostr=demoJson1.getString("cardNo").split("\\|");//卡号
				String[] transAmtstr=demoJson1.getString("transAmt").split("\\|");//交易金额
				String[] transDatestr=demoJson1.getString("transDate").split("\\|");//交易日期
				String[] transDescstr=demoJson1.getString("transDesc").split("\\|");//交易描述
				String[] authNostr=demoJson1.getString("authNo").split("\\|");//授权号
				String[] misIdstr=demoJson1.getString("misId").split("\\|");//商户编号
				String[] terIdstr=demoJson1.getString("terId").split("\\|");//终端号
				String[] ptgCntstr=demoJson1.getString("ptgCnt").split("\\|");//近60天交易笔数
				String[] ptgAmtstr=demoJson1.getString("ptgAmt").split("\\|");//近60天交易笔均金额
					
				if(cardNostr!=null&&cardNostr.length>0)
				{
					for (int i = 0; i < cardNostr.length; i++) {
						tempstr = new String[9];
						tempstr[0]=cardNostr[i];//卡号
						if(transAmtstr!=null&&transAmtstr.length>0)
						{
							tempstr[1]=transAmtstr[i];//交易金额
						}
						else
						{
							tempstr[1]="";//交易金额
						}
						
						if(transDatestr!=null&&transDatestr.length>0)
						{
							tempstr[2]=transDatestr[i];//交易日期
						}
						else
						{
							tempstr[2]="";//交易金额
						}
						
						if(transDescstr!=null&&transDescstr.length>0)
						{
							tempstr[3]=transDescstr[i];//交易描述
						}
						else
						{
							tempstr[3]="";//交易金额
						}
						
						if(authNostr!=null&&authNostr.length>0)
						{
							tempstr[4]=authNostr[i];//授权号
						}
						else
						{
							tempstr[4]="";//交易金额
						}
						
						if(misIdstr!=null&&misIdstr.length>0)
						{
							tempstr[5]=misIdstr[i];//商户编号
						}
						else
						{
							tempstr[5]="";//交易金额
						}
						
						if(terIdstr!=null&&terIdstr.length>0)
						{
							tempstr[6]=terIdstr[i];//终端号
						}
						else
						{
							tempstr[6]="";//交易金额
						}
						if(ptgCntstr!=null&&ptgCntstr.length>0)
						{
							tempstr[7]=ptgCntstr[i];//近60天交易笔数
						}
						else
						{
							tempstr[7]="";//近60天交易笔数
						}
						
						if(ptgAmtstr!=null&&ptgAmtstr.length>0)
						{
							if(!ptgAmtstr[i].equals(""))
							{
								Double ptgAmt = Double.valueOf(ptgAmtstr[i])/100;
								DecimalFormat d=new DecimalFormat("0.00");
						    	String db1 = d.format(ptgAmt);
								tempstr[8]=db1.toString();//近60天交易笔均金额
							}
							else
							{
								tempstr[8]=ptgAmtstr[i];
							}
							
						}
						else
						{
							tempstr[8]="";//近60天交易笔均金额
						}
						sendtList.add(tempstr);
					}
				}
			}
			else 
			{
				//Toast.makeText(HeYueQianDing_0.this,demoJson1.getString("rspInfo"),Toast.LENGTH_LONG).show();
				log.Toast(YuShenSucc.this, demoJson1.getString("rspInfo"));
			}
		}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队：yushensucc出错"+e.getMessage());
		}
	}
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(YuShenSucc.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	
}
