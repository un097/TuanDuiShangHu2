package techown.shanghu;

import java.util.ArrayList;
import java.util.List;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetDate;
import techown.shanghu.date.RandomTest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

/*
 * 总部-活动信息
 */
public class ZhongBuHuoDong2 extends Activity{
	EditDialog log=new EditDialog();
	static String isMessageNew;
	DBUtil DB=new DBUtil();
	private Button button2,button3;
	String time = GetDate.getDate().toString();
	static String strActiveId;
	String bb;
	DataCheckUtil dc=new DataCheckUtil();
	DES des = new DES();
	//卡种
	static int [] BdIds={R.id.baijin_radioButton,R.id.puka_radioButton,R.id.jinka_radioButton,
			R.id.taipingyang2_radioButton,R.id.taipingyang_radioButton,R.id.other_radioButton };
	public static RadioButton[] rbArray;
	//日期限制
	int [] ZhiRi={R.id.zhiyi_radioButton,R.id.zhier_radioButton,R.id.zhisan_radioButton,
			R.id.zhisi_radioButton,R.id.zhiwu_radioButton,R.id.zhiliu_radioButton,R.id.zhiri_radioButton };
	public static RadioButton[] rbZhiRi;
	//活动日
	static int [] 	HuoRi={R.id.huoyi_radioButton,R.id.huoer_radioButton,R.id.huosan_radioButton,
			R.id.huosi_radioButton,R.id.huowu_radioButton,R.id.huoliu_radioButton,R.id.huori_radioButton };
	public static RadioButton[] rbHuoRi;
	
	int[] textIds = { R.id.z2edtext1, R.id.z2edtext2, R.id.z2edtext3, R.id.z2edtext4,
			R.id.z2edtext5, R.id.z2edtext6, R.id.z2edtext7, R.id.z2edtext8, R.id.z2edtext9,
			R.id.h4edtext10,R.id.messageis,R.id.fabuis,R.id.zhubeia,R.id.zhubeib,R.id.zhubeic,R.id.zhubeid};
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.z2leout1,R.id.z2leout2,R.id.z2leout3,R.id.z2leout4,
			R.id.z2leout5,R.id.z2leout6,R.id.z2leout7,R.id.z2leout8,
			R.id.z2leout9,R.id.h5leout10,R.id.messageisout,R.id.fabuisout,R.id.zhubeiaout,
			R.id.zhubeibout,R.id.zhubeicout,R.id.zhubeidout,};
	public static LinearLayout[] linlay;
	public static LinearLayout h5leout5,h5leout6;
	public static TextView h4edtext5,h4edtext6;
	public List<String> data, price,numb,card;
	
	static List<String[]> list44;
	static String id;
   ListView mainright_lv ;
	String[][] theme;
//	String[] msgcontent;
//	String[] activeId;
//	String[] cityId;
	String[]  Id,acId;
	Object [] Ids;
	List<String> list;
	static List <String>list22;
	static String number;
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        super.onCreate(savedInstanceState);
        System.out.println("3");
        setContentView(R.layout.zhongbuhuodong2);
        System.out.println("4");
        list=new ArrayList<String>();
        list22=new ArrayList<String>();
//        active();
        price=new ArrayList<String>();
        data=new ArrayList<String>();
	    numb=new ArrayList<String>();
	    card=new ArrayList<String>();
        initChView();
//        panel=new Panel(this);
        //button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button1);
        button3 = (Button) findViewById(R.id.button2);
        
       
        //卡种
    	rbArray=new   RadioButton[BdIds.length];
		for (int i = 0; i < BdIds.length; i++) {
			rbArray[i] = (RadioButton)findViewById(BdIds[i]);
		}
		//日期限制
		rbZhiRi=new   RadioButton[ZhiRi.length];
		for (int i = 0; i < ZhiRi.length; i++) {
			rbZhiRi[i] = (RadioButton)findViewById(ZhiRi[i]);
		}
		//活动日
		rbHuoRi=new   RadioButton[HuoRi.length];
		for (int i = 0; i < HuoRi.length; i++) {
			rbHuoRi[i] = (RadioButton)findViewById(HuoRi[i]);
		}
		
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		//getLogic();
		
		h5leout5=(LinearLayout)findViewById(R.id.h5leout5);
		h4edtext5=(TextView)findViewById(R.id.h4edtext5);
		h5leout6=(LinearLayout)findViewById(R.id.h5leout6);
		h4edtext6=(TextView)findViewById(R.id.h4edtext6);
		
		//卡种
		 setRbCheck1(rbArray[0]);
	     setRbCheck(rbArray[1]);
	     setRbCheck(rbArray[2]);
	     setRbCheck(rbArray[3]);
	     setRbCheck(rbArray[4]);
	     setRbCheck(rbArray[5]);
	     
	     //日期限制
	     setRbCheck1(rbZhiRi[0]);
	     setRbCheck(rbZhiRi[1]);
	     setRbCheck(rbZhiRi[2]);
	     setRbCheck(rbZhiRi[3]);
	     setRbCheck(rbZhiRi[4]);
	     setRbCheck(rbZhiRi[5]);
	     setRbCheck(rbZhiRi[6]);
	     
	     //活动日
	     setRbCheck1(rbHuoRi[0]);
	     setRbCheck(rbHuoRi[1]);
	     setRbCheck(rbHuoRi[2]);
	     setRbCheck(rbHuoRi[3]);
	     setRbCheck(rbHuoRi[4]);
	     setRbCheck(rbHuoRi[5]);
	     setRbCheck(rbHuoRi[6]);
	     System.out.println("5");
        MyLis lis = new MyLis();
        //button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
        //32位随机数
		RandomTest random=new RandomTest();
		String aa = random.randString(11);
		bb=time+TuanDuiShangHuActivity.username+aa;
        
//        MyDatabaseHelper myHelper = new MyDatabaseHelper(
//				ZhongBuHuoDong2.this, "techown.db", Constant.tdshDBVer);
//		SQLiteDatabase db = myHelper.getWritableDatabase();
//		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
//				new String[] { TuanDuiShangHuActivity.num });
//		while (cursor.moveToNext()) {
//			try {
//				
//				textArray[0].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("storeauditId"))));
//				textArray[1].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("EndTime"))));
//				textArray[2].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("BenginTime"))));
//				textArray[3].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("PrivilegeContent"))));
//				textArray[4].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("MessageContent"))));
//				textArray[5].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("ExpenseDate"))));
//				textArray[6].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("Price"))));
//				textArray[7].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("IdentityCode"))));
//				textArray[8].setText(des.jieMI(cursor.getString(cursor
//						.getColumnIndex("CardNumber"))));
////				textArray[9].setText(cursor.getString(cursor
////						.getColumnIndex("BrandTelNumber")));
////				textArray[10].setText(cursor.getString(cursor
////						.getColumnIndex("PostCode")));
////				textArray[11].setText(cursor.getString(cursor
////						.getColumnIndex("Validity")));
////				textArray[12].setText(cursor.getString(cursor
////						.getColumnIndex("EngageYear")));
////				textArray[13].setText(cursor.getString(cursor
////						.getColumnIndex("ContractNumber")));
//			
//			}catch (Exception e) {
//				techown.shanghu.https.Log.Instance().WriteLog("团队：ZhongBuHuoDong2异常"+e.getMessage());
//			}finally{
//				if(cursor != null){
//					cursor.close();
//				}
//				if(db != null){
//					db.close();
//				}
//			}
//		}
		System.out.println("6");
		h5leout5.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					DateDialog.showDatePicker(ZhongBuHuoDong2.this, h4edtext5,R.id.h4edtext5);
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
					log.Dialog(ZhongBuHuoDong2.this,h5leout6, h4edtext6,R.id.h4edtext6,"请输入特别约定");
					break;
				default:
					break;
				}
				return false;
			}
		});
		try {
			theme=DB.ActiveSelect(ZhongBuHuoDong2.this, "t_cityactive");
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
        linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
//					log.Dialog(ZhongBuHuoDong2.this,linlay[0], textArray[0],R.id.z2edtext1);
					
					log.ActiveDialog(ZhongBuHuoDong2.this,textArray[0],"",theme);
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
				case MotionEvent.ACTION_UP:
					DateDialog.showDatePicker(ZhongBuHuoDong2.this,textArray[1],R.id.z2edtext2);
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
				case MotionEvent.ACTION_UP:
					DateDialog.flag=true;
					DateDialog.num=2;
					DateDialog.showDatePicker(ZhongBuHuoDong2.this ,textArray[2],R.id.z2edtext3);
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
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong2.this,linlay[3], textArray[3],R.id.z2edtext4,"请输入活动内容");
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
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong2.this,linlay[4], textArray[4],R.id.z2edtext5,"请输入短信内容");
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
				case MotionEvent.ACTION_UP:
					DateDialog.showDatePicker(ZhongBuHuoDong2.this,textArray[5],R.id.z2edtext6);
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
				case MotionEvent.ACTION_UP:
					log.etDialog(ZhongBuHuoDong2.this,textArray[6],R.id.z2edtext7,"请输入交易金额");
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
				case MotionEvent.ACTION_UP:
					log.etDialog(ZhongBuHuoDong2.this, textArray[7],R.id.z2edtext8,"请输入授权号");
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
				case MotionEvent.ACTION_UP:
					log.etDialog(ZhongBuHuoDong2.this,textArray[8],R.id.z2edtext9,"请输入卡号");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[9].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.etDialog(ZhongBuHuoDong2.this,textArray[9],R.id.h4edtext10,"请输入金额限制");
					break;
				default:
					break;
				}

				return false;
			}
		});
        
        final String[] messageis={"是","否"};
        linlay[10].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.istwo = true;
					log.num = 9;
					log.dialog(ZhongBuHuoDong2.this, textArray[10], "是否发送短信",messageis);
					break;
				default:
					break;
				}

				return false;
			}
		});
        final String[] fabuis={"是","否","暂缓"};
        linlay[11].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.dialog(ZhongBuHuoDong2.this, textArray[11], "是否发布",fabuis);
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[12].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong2.this,linlay[12], textArray[12],R.id.zhubeia,"请输入注备A");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[13].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong2.this,linlay[13], textArray[13],R.id.zhubeib,"请输入注备B");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[14].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong2.this,linlay[14], textArray[14],R.id.zhubeic,"请输入注备C");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[15].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong2.this,linlay[15], textArray[15],R.id.zhubeid,"请输入注备D");
					break;
				default:
					break;
				}

				return false;
			}
		});
        System.out.println("7");
    }
       
    
//    private void active() {
//    	try {
//		 	theme=DB.klQuery2(ZhongBuHuoDong2.this, "active_theme", "t_cityactive");
////			Id=DB.klQuery2(ZhongBuHuoDong2.this, "active_zd", "t_cityactive");
//			acId=DB.klQuery2(ZhongBuHuoDong2.this, "active_id", "t_cityactive");
//			System.out.println("总部活动的Id 为========="+acId);
//			System.out.println("acId===="+acId.length);
//			for(int i=0;i<theme.length;i++){
//				Log.i("output", acId[i]);
//					list.add(theme[i]);
////					list22.add(acId[i]);
//			}  
//			
//			} catch (Exception e1) {  
//			// TODO Auto-generated catch block
//				techown.shanghu.https.Log.Instance().WriteLog("团队：ZhongBuHuoDong2active异常"+e1.getMessage());
//		}
//        Ids= list.toArray();
//        for(int i=0;i<Ids.length;i++){
//        	Log.i("output", Ids[i].toString());
//        }
//		
//	}

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
	//POS消息添加
    private LinearLayout ss_layoutAll;
    int i=0;
    private void initChView(){
		ss_layoutAll=(LinearLayout) findViewById(R.id.ss_layoutAll);
		findViewById(R.id.button_add).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				if(i!=4){
				final View view=LayoutInflater.from(ZhongBuHuoDong2.this).inflate(
						R.layout.cim_layout_list1, null);
				view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 60));
				
				view.findViewById(R.id.z2edtext6).setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						DateDialog.showDatePicker(ZhongBuHuoDong2.this,(TextView)v,
								R.id.z2edtext6);
					}
					
				});
				view.findViewById(R.id.z2edtext7).setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						log.etDialog(ZhongBuHuoDong2.this,(TextView)v,
								R.id.z2edtext7,"请输入交易金额");
					}
					
				});
				view.findViewById(R.id.z2edtext8).setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						log.etDialog(ZhongBuHuoDong2.this,(TextView)v,
								R.id.z2edtext8,"请输入授权号");
					}
					
				});
				view.findViewById(R.id.z2edtext9).setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						log.etDialog(ZhongBuHuoDong2.this,(TextView)v,
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
					log.Toast(ZhongBuHuoDong2.this, "最多加5条");
				}
			}
		});
    }
    
    public void saveData() {
		String[] clomname = new String[] {"storeauditId", "EndTime","BenginTime","PrivilegeContent",
				"MessageContent","ExpenseDate","Price","IdentityCode","CardNumber","ContractDate","CardType",
				"SpecialContent","IsSentMessage","ActiveZhubeia","ActiveZhubeib","ActiveZhubeic","ActiveZhubeid",
				"ActiveMoney","ActiveDate","ActiveDateTime","HasFaBu"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = strActiveId;
		Log.i("output",clomstr[0]);
		clomstr[1] = textArray[1].getText().toString();
		clomstr[2] = textArray[2].getText().toString();
		clomstr[3] = textArray[3].getText().toString();
		clomstr[4] = textArray[4].getText().toString();
		String data1=GetDate.dateChange1(textArray[5].getText().toString());
		String price1=textArray[6].getText().toString();
		String numb1=textArray[7].getText().toString();
		String card1=textArray[8].getText().toString();
		clomstr[9] = h4edtext5.getText().toString();
		
	
	
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
		
		//卡种
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
		//是否发送短信
		if(textArray[10].getText().toString().equals("是"))
		{
			clomstr[12] = "Y";
		}else if(textArray[10].getText().toString().equals("否"))
		{
			clomstr[12] = "N";
		}
		clomstr[13] = textArray[12].getText().toString();//活动注备A
		clomstr[14] = textArray[13].getText().toString();//活动注备B
		clomstr[15] = textArray[14].getText().toString();//活动注备C
		clomstr[16] = textArray[15].getText().toString();//活动注备D
		clomstr[17] = textArray[9].getText().toString();//活动金额限制
		//日期限制
		StringBuffer sb1=new StringBuffer();
		int DateZhi=0;
		ischeck2(rbZhiRi[0],DateZhi,sb1);
		ischeck2(rbZhiRi[1],DateZhi,sb1);
		ischeck2(rbZhiRi[2],DateZhi,sb1);
		ischeck2(rbZhiRi[3],DateZhi,sb1);
		ischeck2(rbZhiRi[4],DateZhi,sb1);
		ischeck2(rbZhiRi[5],DateZhi,sb1);
		ischeck2(rbZhiRi[6],DateZhi,sb1);
		clomstr[18] = sb1.toString();
		System.out.println("clomstr[18]==="+clomstr[18]);
		//活动日
		StringBuffer sb2=new StringBuffer();
		int ActiveDate=0;
		ischeck2(rbHuoRi[0],ActiveDate,sb2);
		ischeck2(rbHuoRi[1],ActiveDate,sb2);
		ischeck2(rbHuoRi[2],ActiveDate,sb2);
		ischeck2(rbHuoRi[3],ActiveDate,sb2);
		ischeck2(rbHuoRi[4],ActiveDate,sb2);
		ischeck2(rbHuoRi[5],ActiveDate,sb2);
		ischeck2(rbHuoRi[6],ActiveDate,sb2);
		clomstr[19] = sb2.toString();
		System.out.println("clomstr[19]==="+clomstr[19]);
		//是否发布
		if(textArray[11].getText().toString().equals("是"))
		{
			clomstr[20] = "Y";
		}else if(textArray[11].getText().toString().equals("否"))
		{
			clomstr[20] = "N";
		}else if(textArray[11].getText().toString().equals("暂缓"))
		{
			clomstr[20] = "S";
		}
		DB.upload(ZhongBuHuoDong2.this, clomname, clomstr, TuanDuiShangHuActivity.num);
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
					
//					saveLogic();
					
					if(validate()){
						saveData1();
						saveData();
//					if(m!=true){
						
					String barcodeno=bb;
					DB.sendinsert(ZhongBuHuoDong2.this, new String[] {
			    			MyDatabaseHelper.SEND_BarcodeNo,
							MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
							new String[] { barcodeno, "1", "0" });
					
				
					new AlertDialog.Builder(ZhongBuHuoDong2.this).setTitle("提示").setMessage("提交成功")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							Intent intent = new Intent();
							intent.setClass(ZhongBuHuoDong2.this, TuanDuiShangHuActivity.class);
							ZhongBuHuoDong2.this.startActivity(intent);
							techown.shanghu.https.OperateLog.Instance().WriteLog("商户团队活动录入提交成功！");
							ZhongBuHuoDong2.this.finish();
							
						}
					}).show();
				}
					//Request();
				break;
					
			

				case R.id.button2:
					
//					intent.setClass(ZhongBuHuoDong2.this, ZhongBuHuoDong1.class);
					intent.setClass(ZhongBuHuoDong2.this, HeYueQianDing_44.class);
					ZhongBuHuoDong2.this.startActivity(intent);
					ZhongBuHuoDong2.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
			}
		}
    }
    
    
 public void saveData1() {
    	String[] clomname = new String[] {"DCL","LX","TIME","MId"};
    	String[] clomstr = new String[10];
    	clomstr[0] = "3";
    	clomstr[1] = "活动录入";
    	clomstr[2] = time;
    	clomstr[3] = bb;
    	DB.upload(ZhongBuHuoDong2.this, clomname, clomstr, TuanDuiShangHuActivity.num);
    }
   
    
    
    
    boolean m = true;
	// 验证必填字段的是否为空
	protected boolean validate() {
		if (textArray[0].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入主题！");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入活动开始时间！");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入活动结束时间！");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入活动内容！");
			return false;
		}
		if (textArray[4].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入短信内容！");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入交易日期 ！");
			return false;
		}
		if (textArray[6].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入交易金额 ！");
			return false;
		}
		if (textArray[7].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入授权号 ！");
			return false;
		}
		if (textArray[8].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入卡号 ！");
			return false;
		}
		if (h4edtext5.getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong2.this, "请输入签约日期 ！");
			return false;
		}
		if(!dc.validateTime(textArray[1].getText().toString(),textArray[2].getText().toString())){
			log.Toast(ZhongBuHuoDong2.this, "活动结束时间不可小于开始时间！");
			return false;
		}
		if(textArray[10].getText().toString().equals(""))
		{
			log.Toast(ZhongBuHuoDong2.this, "请选择是否发送短信！");
			return false;
		}
		if(textArray[11].getText().toString().equals(""))
		{
			log.Toast(ZhongBuHuoDong2.this, "请选择是否发布！");
			return false;
		}
		if(textArray[9].getText().toString().equals(""))
		{
			log.Toast(ZhongBuHuoDong2.this, "请输入活动金额限制！");
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
						log.Toast(ZhongBuHuoDong2.this, "请输入交易日期！");
						return false;
					} 

					if (yedtext6.getText().toString().equals("")) {
						log.Toast(ZhongBuHuoDong2.this,"请输入交易金额！");
						return false;
					}
					if (yedtext7.getText().toString().equals("")) {
						log.Toast(ZhongBuHuoDong2.this, "请输入授权号！");
						return false;
					} 				
					if (yedtext8.getText().toString().equals("")) {
						log.Toast(ZhongBuHuoDong2.this, "请输入卡号！");
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
			log.ExitApp(ZhongBuHuoDong2.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}   
