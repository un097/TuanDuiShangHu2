package techown.shanghu;
/*
 * 城市活动-活动信息
 */
import java.util.ArrayList;
import java.util.List;

import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetDate;
import techown.shanghu.date.RandomTest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ChengShiHuoDong22 extends Activity {
	DataCheckUtil dc=new DataCheckUtil();
	private Button button2,button3;
	int [] BdIds={R.id.baijin_radioButton,R.id.puka_radioButton,R.id.jinka_radioButton,
    		R.id.taipingyang_radioButton,R.id.taipingyang2_radioButton,R.id.other_radioButton
    };
	public  RadioButton[] rbArray;
	int[] textIds = { R.id.z2edtext1, R.id.z2edtext2, R.id.z2edtext3, R.id.z2edtext4,
			R.id.z2edtext5, R.id.z2edtext6, R.id.z2edtext7, R.id.z2edtext8, R.id.z2edtext9};
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.z2leout1,R.id.z2leout2,R.id.z2leout3,R.id.z2leout4,
			R.id.z2leout5,R.id.z2leout6,R.id.z2leout7,R.id.z2leout8,
			R.id.z2leout9};
	public LinearLayout[] linlay;
	String bb;
	static String[] theme;
	static String[]  Id,acId;
	static Object [] Ids;
	List<String> list;
	static List<String> list22;
	static String number;
	public LinearLayout h5leout5,h5leout6;
	public TextView h4edtext5,h4edtext6;
	public List<String> data, price,numb,card;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	
	

    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chengshihuodong22);
        try{
       price=new ArrayList<String>();
       data=new ArrayList<String>();
      numb=new ArrayList<String>();
      card=new ArrayList<String>();
        list=new ArrayList<String>();
        list22=new ArrayList<String>();
        initChView();
//        panel=new Panel(this);
        //button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button1);
        button3 = (Button) findViewById(R.id.button2);
      //32位随机数
        RandomTest random=new RandomTest();
		String aa = random.randString(11);
		bb=time+TuanDuiShangHuActivity.username+aa;
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		 rbArray=new   RadioButton[BdIds.length];
			for (int i = 0; i < BdIds.length; i++) {
				rbArray[i] = (RadioButton)findViewById(BdIds[i]);
			}
		//getLogic();
			h5leout5=(LinearLayout)findViewById(R.id.h5leout5);
			h4edtext5=(TextView)findViewById(R.id.h4edtext5);
			h5leout6=(LinearLayout)findViewById(R.id.h5leout6);
			h4edtext6=(TextView)findViewById(R.id.h4edtext6);
        MyLis lis = new MyLis();
        //button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
        active();
        setRbCheck1(rbArray[0]);
	     setRbCheck(rbArray[1]);
	     setRbCheck(rbArray[2]);
	     setRbCheck(rbArray[3]);
	     setRbCheck(rbArray[4]);
	     setRbCheck(rbArray[5]);
	     h5leout5.setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_UP:
						DateDialog.showDatePicker(ChengShiHuoDong22.this, h4edtext5,R.id.h4edtext5);
						break;
					default:
						break;
					}
					return false;
				}
			});
			
			h5leout6.setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_UP:
						log.Dialog(ChengShiHuoDong22.this,h5leout6, h4edtext6,R.id.h4edtext6,"请输入特别约定");
						break;
					default:
						break;
					}
					return false;
				}
			});
        linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: 

					//EditDialog.Dialog(ChengShiHuoDong22.this,linlay[0], textArray[0],R.id.z2edtext1);
					log.dialogOB(ChengShiHuoDong22.this,textArray[0],"",Ids);
//					EditDialog.Dialog(ChengShiHuoDong22.this,linlay[0], textArray[0],R.id.z2edtext1);
				

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
					DateDialog.showDatePicker(ChengShiHuoDong22.this,textArray[1],R.id.z2edtext2);
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
					DateDialog.flag=true;
					DateDialog.num=1;
					DateDialog.showDatePicker(ChengShiHuoDong22.this ,textArray[2],R.id.z2edtext3);
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[3].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					
					log.Dialog(ChengShiHuoDong22.this,linlay[3], textArray[3],R.id.z2edtext4,"请输入活动内容");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[4].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(ChengShiHuoDong22.this,linlay[4], textArray[4],R.id.z2edtext5,"请输入短信内容");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[5].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					DateDialog.showDatePicker(ChengShiHuoDong22.this,textArray[5],R.id.z2edtext6);
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[6].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(ChengShiHuoDong22.this,textArray[6],R.id.z2edtext7,"请输入交易金额");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[7].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(ChengShiHuoDong22.this, textArray[7],R.id.z2edtext8,"请输入授权号");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[8].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.etDialog(ChengShiHuoDong22.this,textArray[8],R.id.z2edtext9,"请输入卡号");
					break;
				default:
					break;
				}

				return false;
			}
		});
    
        }catch(Exception e){
        	techown.shanghu.https.Log.Instance().WriteLog("ChengShiHuoDong22类"+e.getMessage());
        }
    }
    private void setRbCheck(final RadioButton rb) {
    	rb.setOnClickListener(new OnClickListener() {
    		boolean check=false;
    		
 			
 			public void onClick(View arg0) {
 				if(check){
 					rb.setChecked(false);
 					check=false;
 				}
 				else{
 					rb.setChecked(true);   
 					check=true;
 				}
 			}
 		});
    }
    private void setRbCheck1(final RadioButton rb) {
    	rb.setOnClickListener(new OnClickListener() {
    		boolean check=true;
    		
 			
 			public void onClick(View arg0) {
 				if(check){
 					rb.setChecked(false);
 					check=false;
 				}
 				else{
 					rb.setChecked(true);   
 					check=true;
 				}
 			}
 		});
    }
    
    
    private void active() {
    	 try {
    			theme=DB.klQuery2(ChengShiHuoDong22.this, "active_theme", "t_cityactive");
    			Id=DB.klQuery2(ChengShiHuoDong22.this, "active_zd", "t_cityactive");
    			acId=DB.klQuery2(ChengShiHuoDong22.this, "active_id", "t_cityactive");
    			for(int i=0;i<Id.length;i++){
    				
    				if(Id[i].contains("CITY")){
    					list.add(theme[i]);
    					list22.add(acId[i]); 
    					
    					
    				}

    			}
    			
    			
    			
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			techown.shanghu.https.Log.Instance().WriteLog("ChengShiHuoDong22类active()"+e.getMessage());
    		}
    	       Ids= list.toArray();
    	       for(int i=0;i<Ids.length;i++){
    	       	//Log.i("output", Ids[i].toString());
    	       }

		
	}


	//POS消息添加
    private LinearLayout ss_layoutAll;
    int i=0;
    private void initChView(){
		ss_layoutAll=(LinearLayout) findViewById(R.id.ss_layoutAll);
		findViewById(R.id.button_add).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
			
				if(i!=4){
				final View view=LayoutInflater.from(ChengShiHuoDong22.this).inflate(
						R.layout.cim_layout_list1, null);
				view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 60));
				
				view.findViewById(R.id.z2edtext6).setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						DateDialog.showDatePicker(ChengShiHuoDong22.this,(TextView)v,
								R.id.z2edtext6);
					}
					
				});
				view.findViewById(R.id.z2edtext7).setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						log.etDialog(ChengShiHuoDong22.this,(TextView)v,
								R.id.z2edtext7,"请输入交易金额");
					}
					
				});
				view.findViewById(R.id.z2edtext8).setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						log.etDialog(ChengShiHuoDong22.this,(TextView)v,
								R.id.z2edtext8,"请输入授权号");
					}
					
				});
				view.findViewById(R.id.z2edtext9).setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						log.etDialog(ChengShiHuoDong22.this,(TextView)v,
								R.id.z2edtext9,"请输入卡号");
					}
					
				});
				ss_layoutAll.addView(view);
				i++;
				view.findViewById(R.id.button91).setOnClickListener(new OnClickListener() {
					
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						ss_layoutAll.removeView(view);
						i--;
						
					}
				});
				}
				else{
					//Toast.makeText(ChengShiHuoDong22.this, "最多加5条", Toast.LENGTH_LONG).show();
					log.Toast(ChengShiHuoDong22.this,  "最多加5条");
				}
			}
		}); 
    }
    
    public void saveData() {
		String[] clomname = new String[] { "storeauditId", "EndTime",
				"BenginTime",  "PrivilegeContent", "MessageContent",
				"ExpenseDate","Price","IdentityCode","CardNumber","ContractDate","CardType","SpecialContent"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = number;
		Log.i("output",clomstr[0]);
		 
	
		clomstr[1] = textArray[1].getText().toString();
		clomstr[2] = textArray[2].getText().toString();
		clomstr[3] = textArray[3].getText().toString();
		clomstr[4] = textArray[4].getText().toString();
		String data1=GetDate.dateChange1(textArray[5].getText().toString());
		String price1=textArray[6].getText().toString();
		String numb1=textArray[7].getText().toString();
		String card1=textArray[8].getText().toString();
		
	
	
		for(int x=0;x<data.size()/4-1;x++){
			data1+="*"+GetDate.dateChange1(data.get(x+1));
		};
		for(int x=0;x<price.size()/4-1;x++){
			price1+="*"+price.get(x+1);
		};
		for(int x=0;x<numb.size()/4-1;x++){
			numb1+="*"+numb.get(x+1);
		};
		for(int x=0;x<card.size()/4-1;x++){
			card1+="*"+card.get(x+1);
		};		
		
		clomstr[5]=data1;
		clomstr[6]=price1;
		clomstr[7] =numb1;
		clomstr[8] =card1;
		Log.i("output", clomstr[5]);
		Log.i("output", clomstr[6]);
		Log.i("output", clomstr[7]);
		Log.i("output", clomstr[8]);
		clomstr[9] = h4edtext5.getText().toString();
		StringBuffer sb=new StringBuffer();
		int n=0;
		ischeck2(rbArray[0],n,sb);
		ischeck2(rbArray[1],n,sb);
		ischeck2(rbArray[2],n,sb);
		ischeck2(rbArray[3],n,sb);
		ischeck2(rbArray[4],n,sb);
		ischeck2(rbArray[5],n,sb);
		clomstr[10] = sb.toString();
		clomstr[11] = h4edtext6.getText().toString();
		
		DB.upload(ChengShiHuoDong22.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
    private void ischeck2(RadioButton rb,int n,StringBuffer sb) {
		 if(rb.isChecked()){
				n=1;
				sb.append(n);
			}
			else{
				n=0;
				sb.append(n);
			}
			
		}

   
    class MyLis implements OnClickListener{

	
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
		
	        
			switch(id){
				case R.id.button1:
					
					if(validate()){
						
						saveData();
//						saveLogic();
						saveData1();
					 
//					if(m!=true){
						
					
						DB.sendinsert(ChengShiHuoDong22.this, new String[] {
			    			MyDatabaseHelper.SEND_BarcodeNo,
							MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
							new String[] { bb, "1", "0" });
					
				
					new AlertDialog.Builder(ChengShiHuoDong22.this).setTitle("提示").setMessage("提交成功")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							Intent intent = new Intent();
							intent.setClass(ChengShiHuoDong22.this, TuanDuiShangHuActivity.class);
							ChengShiHuoDong22.this.startActivity(intent);
							Log.i("output", number);
							ChengShiHuoDong22.this.finish();
						}
					}).show();
				}
					//Request();
				break;
					
			

				case R.id.button2:
					
					intent.setClass(ChengShiHuoDong22.this, ChengShiHuoDong1.class);
					ChengShiHuoDong22.this.startActivity(intent);
					ChengShiHuoDong22.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
			}
		}
    }
    
    String time = GetDate.getDate().toString();
 public void saveData1() {
    	String[] clomname = new String[] {"DCL","LX","TIME","MId"};
    	String[] clomstr = new String[10];
    	clomstr[0] = "3";
    	clomstr[1] = "城市活动";
    	clomstr[2] = time;
    	clomstr[3] = bb;
    	DB.upload(ChengShiHuoDong22.this, clomname, clomstr, TuanDuiShangHuActivity.num);
    }
    public void saveData2() {
    	String[] clomname = new String[] {"DCL","LX","TIME"};
    	String[] clomstr = new String[10];
    	clomstr[0] = "0";
    	clomstr[1] = "城市活动";
    	clomstr[2] = time;
    	
    	DB.upload(ChengShiHuoDong22.this, clomname, clomstr, TuanDuiShangHuActivity.num);
  
    }
    
    
    
    boolean m = true;
	// 验证必填字段的是否为空
	protected boolean validate() {
		if (textArray[0].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong22.this,  "请输入主题！");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong22.this,  "请输入活动开始时间！");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong22.this, "请输入活动结束时间！");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong22.this,  "请输入活动内容！");
			return false;
		}
		if (textArray[4].getText().toString().equals("")) {
		
			log.Toast(ChengShiHuoDong22.this,  "请输入短信内容！");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong22.this,  "请输入交易日期 ！");
			return false;
		}
		if (textArray[6].getText().toString().equals("")) {
		
			log.Toast(ChengShiHuoDong22.this,  "请输入交易金额 ！");
			return false;
		}
		if (textArray[7].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong22.this, "请输入授权号 ！");
			return false;
		}
		if (textArray[8].getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong22.this, "请输入卡号 ！");
			return false;
		}
		if (h4edtext5.getText().toString().equals("")) {
			
			log.Toast(ChengShiHuoDong22.this,  "请输入签约日期 ！");
			return false;
		}
		if (!dc.validateTime(textArray[1].getText().toString(), textArray[2].getText().toString())) {
			
			log.Toast(ChengShiHuoDong22.this,   "活动结束时间不可小于开始时间！");
			return false;
		}
		if(!posFromSecond(1)){
			return false;
		}
		if(!posFromSecond(2)){
			return false;
		}
		if(!posFromSecond(3)){
			return false;
		}  
		if(!posFromSecond(4)){
			return false;
		}
		    save(1);
		    save(2);
		    save(3);
		    save(4);

		return true;
	}
	private boolean posFromSecond(int k) {
	  this.i=k;
	if(i==k)
	{
		for(int x=0;x<ss_layoutAll.getChildCount();x++){
			LinearLayout viewLayout=(LinearLayout) ss_layoutAll.getChildAt(x);
			 
			  TextView   yedtext5=(TextView) viewLayout.findViewById(R.id.z2edtext6);
			  TextView	 yedtext6=(TextView) viewLayout.findViewById(R.id.z2edtext7);
			  TextView	 yedtext7=(TextView) viewLayout.findViewById(R.id.z2edtext8);
			  TextView	 yedtext8=(TextView) viewLayout.findViewById(R.id.z2edtext9);

				if (yedtext5.getText().toString().equals("")) {
				
					log.Toast(ChengShiHuoDong22.this,   "请输入交易日期！");
					return false;
				} 

				if (yedtext6.getText().toString().equals("")) {
					
					log.Toast(ChengShiHuoDong22.this,   "请输入交易金额！");
					return false;
				}
				if (yedtext7.getText().toString().equals("")) {
					
					log.Toast(ChengShiHuoDong22.this,  "请输入授权号！");
					return false;
				} 				if (yedtext8.getText().toString().equals("")) {
					
					log.Toast(ChengShiHuoDong22.this,   "请输入卡号！");
					return false;
				} 

				 
			 
		}
		    
     
	}
	  return true;  
}
	public void save(int k){
		 this.i=k;
			if(i==k)
			{
				for(int x=0;x<ss_layoutAll.getChildCount();x++){
					LinearLayout viewLayout=(LinearLayout) ss_layoutAll.getChildAt(x);
					 
					  TextView   yedtext5=(TextView) viewLayout.findViewById(R.id.z2edtext6);
					  TextView	 yedtext6=(TextView) viewLayout.findViewById(R.id.z2edtext7);
					  TextView	 yedtext7=(TextView) viewLayout.findViewById(R.id.z2edtext8);
					  TextView	 yedtext8=(TextView) viewLayout.findViewById(R.id.z2edtext9);
		
	
				
					  data.add(yedtext5.getText().toString()); 	
					  price.add(yedtext6.getText().toString());  
					 numb.add(yedtext7.getText().toString()); 	 
					card.add(yedtext8.getText().toString()); }
			}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(ChengShiHuoDong22.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
