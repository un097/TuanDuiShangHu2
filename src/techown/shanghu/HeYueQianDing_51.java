package techown.shanghu;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.photo.TakePhotoActivity2;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
/*
 * 拒件--合约签订活动信息录入
 */
public class HeYueQianDing_51  extends Activity{
	public DuanXinDialog dxdialog = new DuanXinDialog();
	EditDialog log=new EditDialog();
	private Button button2,button3;
	 public  RadioButton[] rbArray;
	 private RadioButton rb,rb1;
		DataCheckUtil dc=new DataCheckUtil();
		int [] BdIds={R.id.baijin_radioButton,R.id.puka_radioButton,R.id.jinka_radioButton,
				R.id.taipingyang2_radioButton,R.id.taipingyang_radioButton,R.id.other_radioButton };

	int[] textIds = { R.id.h4edtext1, R.id.h4edtext2, R.id.h4edtext3, R.id.h4edtext4,
			R.id.h4edtext5, R.id.h4edtext6, R.id.h4edtext7, R.id.h4edtext8, R.id.h4edtext9,
			R.id.h4edtext10, R.id.h4edtext11};
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.h5leout1,R.id.h5leout2,R.id.h5leout3,R.id.h5leout4,
			R.id.h5leout5,R.id.h5leout6,R.id.h5leout7,R.id.h5leout8,
			R.id.h5leout9,R.id.h5leout10,R.id.h5leout11};
	public LinearLayout[] linlay;
	DBUtil DB=new DBUtil();
	
	/***************************************************/
	public RadioButton hasyouhuiquan,noyouhuiquan;
	public LinearLayout youhuiquanTxtlayout,beginTimelayout,stopTimelayout;
	public TextView youhuiquanTxt,beginTime,stopTime;
	public RadioGroup hasyouhuiquanRG;
	/*******************************************************/
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heyueqianding_5);
        

//        panel=new Panel(this);
        
        /***************************************************************/  
        youhuiquanTxtlayout = (LinearLayout) findViewById(R.id.h5leout12);
        beginTimelayout = (LinearLayout) findViewById(R.id.h5leout13);
        stopTimelayout = (LinearLayout) findViewById(R.id.h5leout14);
        youhuiquanTxt = (TextView) findViewById(R.id.heyueyouhuiquantxt);
        beginTime = (TextView) findViewById(R.id.youxiaobeginTime);
        stopTime = (TextView) findViewById(R.id.youxiaostopTime);
        hasyouhuiquan = (RadioButton) findViewById(R.id.hasyouhuiquanRB);
        noyouhuiquan = (RadioButton) findViewById(R.id.noyouhuiquanRB);
        hasyouhuiquanRG = (RadioGroup) findViewById(R.id.heyue_radioGroup);
      /**************************************************************/
        
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        rb=(RadioButton)findViewById(R.id.youKa_radioButton);
        rb1=(RadioButton)findViewById(R.id.noKa_radioButton);
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
        
		try{
			
			tiaoxingma();
			
	     setRbCheck1(rbArray[0]);
	     setRbCheck(rbArray[1]);
	     setRbCheck(rbArray[2]);
	     setRbCheck(rbArray[3]);
	     setRbCheck(rbArray[4]);
	     setRbCheck(rbArray[5]);
		
	     readData();
     	 textArray[0].setText(TuanDuiShangHuActivity.username);
		 textArray[2].setText(TuanDuiShangHuActivity.username);
		 textArray[1].setText(TuanDuiShangHuActivity.chinaname);
		 textArray[3].setText(TuanDuiShangHuActivity.chinaname);
        
		 /*******************************************************************/ 
		 	//是否有优惠券
			youhuiquanTxtlayout.setOnTouchListener(new OnTouchListener() {
				
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					log.Dialog(HeYueQianDing_51.this,youhuiquanTxtlayout, youhuiquanTxt,R.id.heyueyouhuiquantxt,"请输入优惠券内容");
					return false;
				}
			});
			//优惠券开始时间
			beginTimelayout.setOnTouchListener(new OnTouchListener() {
				
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					DateDialog.showDatePicker(HeYueQianDing_51.this, beginTime,R.id.youxiaobeginTime);
					return false;
				}
			});
			//优惠券结束时间
			stopTimelayout.setOnTouchListener(new OnTouchListener() {
				
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					DateDialog.showDatePicker(HeYueQianDing_51.this, stopTime,R.id.youxiaostopTime);
					return false;
				}
			});
			hasyouhuiquanRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				public void onCheckedChanged(RadioGroup arg0, int arg1) {
					// TODO Auto-generated method stub
					switch(arg1){
					case R.id.hasyouhuiquanRB:
						youhuiquanTxtlayout.setEnabled(true);
						beginTimelayout.setEnabled(true);
						stopTimelayout.setEnabled(true);
						break;
					case R.id.noyouhuiquanRB:
						youhuiquanTxt.setText("");
						beginTime.setText("");
						stopTime.setText("");
						youhuiquanTxtlayout.setEnabled(false);
						beginTimelayout.setEnabled(false);
						stopTimelayout.setEnabled(false);
						
						break;
						default:break;
					}
				}
			});
			/******************************************************************************************/ 
		 
	     linlay[4].setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_UP:
						DateDialog.showDatePicker(HeYueQianDing_51.this, textArray[4],R.id.h4edtext5);
						break;
					default:
						break;
					}
					return false;
				}
			});
	        linlay[5].setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					// TODO Auto-generated method stub
					
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_UP:
						DateDialog.showDatePicker(HeYueQianDing_51.this, textArray[5],R.id.h4edtext6);
						break;
					default:
						break;
					}
						return true;
				}
			});
	        final String[] province={"是","否"};
	        linlay[6].setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					// TODO Auto-generated method stub
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_UP:
//						log.Dialog(HeYueQianDing_5.this,linlay[6], textArray[6],R.id.h4edtext7);
						log.dialog(HeYueQianDing_51.this,textArray[6],"是否续约",province);
						break;
					default:
						break;
					}
						return true;
				}
			});
	     
	        linlay[7].setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_UP:
						DateDialog.showDatePicker(HeYueQianDing_51.this, textArray[7],R.id.h4edtext8);
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
						dxdialog.Dialog(HeYueQianDing_51.this,linlay[8], textArray[8],R.id.h4edtext9,"请输入短信内容");
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
						log.Dialog(HeYueQianDing_51.this,linlay[9], textArray[9],R.id.h4edtext10,"请输入优惠内容");
						break;
					default:
						break;
					}
					return false;
				}
			});
	        linlay[10].setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_UP:
						log.Dialog(HeYueQianDing_51.this,linlay[10], textArray[10],R.id.h4edtext11,"请输入特别约定");
						break;
					default:
						break;
					}
					return false;
				}
			});
        MyLis lis = new MyLis();
        
        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
        
        init();
    }catch(Exception e){
    	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_51类"+e.getMessage());
    }
    }
    
    DES des = new DES();
    private void readData() {

		MyDatabaseHelper myHelper = new MyDatabaseHelper(
				HeYueQianDing_51.this, "techown.db",Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.num });
		while (cursor.moveToNext()) {
			try {
				
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("MaintainUserId"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ExpandUserId"))));
				textArray[4].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ContractDate"))));
				textArray[5].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("EndTime"))));
				if((des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsExtension"))).equals("Y"))){
					textArray[6].setText("是");
				}
				else if((des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsExtension"))).equals("N"))){
					textArray[6].setText("否");
				}
				if((des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsSentMessage"))).equals("Y"))){
					rb.setChecked(true);
				}
				if((des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsSentMessage"))).equals("N"))){
					rb1.setChecked(true);
				}
				String cardType=des.jieMI(cursor.getString(cursor
						.getColumnIndex("CardType")));
				String [] card2=cardType.split("");
				Log.i("output", String.valueOf(card2.length));
				Log.i("output", card2[1]);
				for(int i=1;i<card2.length;i++){
					
					if(card2[i].equals("1")){
						rbArray[i-1].setChecked(true);
						
				      
					}
					else if(card2[i].equals("0")){
						rbArray[i-1].setChecked(false);
				      
					}
				}
				
				textArray[7].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("BenginTime"))));
				textArray[8].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("MessageContent"))));
				textArray[9].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("PrivilegeContent"))));
				textArray[10].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("SpecialContent"))));
				/***********************************************************/
				if(des.jieMI(cursor.getString(cursor.getColumnIndex("hasyouhuiquan"))).equals("Y")){
					hasyouhuiquan.setChecked(true);
				}else{
					noyouhuiquan.setChecked(true);
					youhuiquanTxtlayout.setEnabled(false);
					beginTimelayout.setEnabled(false);
					stopTimelayout.setEnabled(false);
				}
				youhuiquanTxt.setText(des.jieMI(cursor.getString(cursor.getColumnIndex("youhuiquanneirong"))));
				beginTime.setText(des.jieMI(cursor.getString(cursor.getColumnIndex("youxiaoBeginTime"))));
				stopTime.setText(des.jieMI(cursor.getString(cursor.getColumnIndex("youxiaoStopTime"))));
				/***********************************************************/
			
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing_51:"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}if(db != null){
					db.close();
				}
			}
		}
	}
	class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			try {
				// TODO Auto-generated method stub
				int id = v.getId();
				Intent intent = new Intent();
				

				switch(id){
					case R.id.button2:
					
						
						intent.setClass(HeYueQianDing_51.this, HeYueQianDing_41.class);
						HeYueQianDing_51.this.startActivity(intent);
						HeYueQianDing_51.this.finish();
						overridePendingTransition(R.anim.rightin, R.anim.rightout);	
					break;

					case R.id.button3:
						if(validate()){
						saveData();
						
//					 ZuBao zb = new ZuBao(HeYueQianDing_5.this);
//					 zb.fun();
						
						intent.setClass(HeYueQianDing_51.this, TakePhotoActivity2.class);
						HeYueQianDing_51.this.startActivity(intent);
						HeYueQianDing_51.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_51类按钮点击："+e.getMessage());

			}
		}
    }
    
    
    public void saveData() {
    	try{
		String[] clomname = new String[] { "MaintainUserId", "ExpandUserId",
				"ContractDate", "EndTime",  "IsExtension","BenginTime",
				"IsSentMessage", "CardType", "MessageContent", "PrivilegeContent", "SpecialContent","YesNXieYi",
				"hasyouhuiquan","youhuiquanneirong","youxiaoBeginTime","youxiaoStopTime"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[0].getText().toString();
		clomstr[1] = textArray[2].getText().toString();
		clomstr[2] = textArray[4].getText().toString();
		clomstr[3] = textArray[5].getText().toString();
		
			if(textArray[6].getText().toString().equals("是")){ 
				clomstr[4] = "Y";
			}else if(textArray[6].getText().toString().equals("否")){
				clomstr[4] = "N";
			}
		
		
		
		clomstr[5] = textArray[7].getText().toString();
		if(rb.isChecked()){
			clomstr[6] = "Y";
		}else if(rb1.isChecked()){
			clomstr[6] = "N"; 
		}
		
		StringBuffer sb=new StringBuffer();
		int n=0;
		ischeck2(rbArray[0],n,sb);
		ischeck2(rbArray[1],n,sb);
		ischeck2(rbArray[2],n,sb);
		ischeck2(rbArray[3],n,sb);
		ischeck2(rbArray[4],n,sb);
		ischeck2(rbArray[5],n,sb);
		clomstr[7] = sb.toString();
		Log.i("output", sb.toString());
		clomstr[8] = textArray[8].getText().toString();
		clomstr[9] = textArray[9].getText().toString();
		clomstr[10] = textArray[10].getText().toString();
		if(Dnum.equals("1")){
			clomstr[11] ="N";
		}
		else{
			clomstr[11] ="Y";
		}
		
		/******************************************************/
		if(hasyouhuiquan.isChecked()){
			clomstr[12]="Y";
		}else{
			clomstr[12]="N";
		}
		clomstr[13]=youhuiquanTxt.getText().toString();
		clomstr[14]= beginTime.getText().toString();
		clomstr[15] = stopTime.getText().toString();
	/****************************************************************/	
		
		DB.upload(HeYueQianDing_51.this, clomname, clomstr, TuanDuiShangHuActivity.num);
    	}catch(Exception e){
        	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_51类saveData()"+e.getMessage());

    	}
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
    
	   public void init()
	    {
	    	try{
			textArray[4].setText(HeYueQianDing_31.tempstr[35]);
			textArray[5].setText(HeYueQianDing_31.tempstr[36]);
			textArray[7].setText(HeYueQianDing_31.tempstr[37]);
			textArray[8].setText(HeYueQianDing_31.tempstr[41]);
			textArray[9].setText(HeYueQianDing_31.tempstr[42]);
			textArray[10].setText(HeYueQianDing_31.tempstr[43]);
			
			if(HeYueQianDing_31.tempstr[39].equals("Y")&&HeYueQianDing_31.tempstr[39]!=null){
				rb.setChecked(true);
				
			}
			if(HeYueQianDing_31.tempstr[39].equals("N")&&HeYueQianDing_31.tempstr[39]!=null){
				rb1.setChecked(true);
				
			}
			String card=HeYueQianDing_31.tempstr[40];
			String [] card2=card.split("");
			
			for(int i=1;i<card2.length;i++){
				if(card2[i].equals("1")){
					rbArray[i-1].setChecked(true);
					}
				else if(card2[i].equals("0")){
					rbArray[i-1].setChecked(false);
					}
			}
			if(HeYueQianDing_31.tempstr[38].equals("N")){
				textArray[6].setText("否");
			}else {
				textArray[6].setText("是");
			}
			
			//是否有优惠券
			if(HeYueQianDing_31.tempstr[59].equals("Y"))
			{
				hasyouhuiquan.setChecked(true);
				noyouhuiquan.setChecked(false);
			}else if(HeYueQianDing_31.tempstr[59].equals("N"))
			{
				hasyouhuiquan.setChecked(false);
				noyouhuiquan.setChecked(true);
			}
			//优惠券内容
			youhuiquanTxt.setText(HeYueQianDing_31.tempstr[56]);
			//优惠券开始时间
			beginTime.setText(HeYueQianDing_31.tempstr[57]);
			//优惠券截止时间
			stopTime.setText(HeYueQianDing_31.tempstr[58]);
			
	    	}catch(Exception e){
	        	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_51类init()"+e.getMessage());

	    	}
			
	    }
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(HeYueQianDing_51.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
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
    		boolean check1=true;
 			
 			public void onClick(View arg0) {
 				if(check1){
 					rb.setChecked(false);
 					check1=false;
 				}
 				else{
 					rb.setChecked(true);
 					check1=true;
 				}
 			}
 		});
    }
	protected boolean validate() {
		if (textArray[4].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_51.this, "请输入签约日期！", Toast.LENGTH_LONG)
//					.show();
			log.Toast(HeYueQianDing_51.this,"请输入签约日期！");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_51.this, "请输入优惠开始日期！", Toast.LENGTH_LONG)
//					.show();
			log.Toast(HeYueQianDing_51.this,  "请输入优惠开始日期！");
			return false;
		}
		if (textArray[6].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_51.this, "请确认是否签约", Toast.LENGTH_LONG).show();
			log.Toast(HeYueQianDing_51.this, "请确认是否续约");
			return false;
		}
		if (textArray[7].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_51.this, "请输入优惠截止日期！", Toast.LENGTH_LONG)
//					.show();
			log.Toast(HeYueQianDing_51.this, "请输入优惠截止日期！");
			return false;
		}
		if (textArray[8].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_51.this, "请输入短信内容！", Toast.LENGTH_LONG)
//					.show();
			log.Toast(HeYueQianDing_51.this, "请输入短信内容！");
			return false;
		}
		
		if (textArray[9].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_51.this, "请输入优惠内容！", Toast.LENGTH_LONG)
//					.show();
			log.Toast(HeYueQianDing_51.this, "请输入优惠内容！");
			return false;
		}
//		if (textArray[10].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_5.this, "请输入特别约定！", Toast.LENGTH_LONG)
//					.show();
//			return false;
//		}
		if(!dc.validateTime(textArray[5].getText().toString(),textArray[7].getText().toString())){
//			Toast.makeText(HeYueQianDing_51.this, "优惠开始时间不可大于优惠截止时间！", Toast.LENGTH_LONG)
//			.show();
			log.Toast(HeYueQianDing_51.this, "优惠开始时间不可大于优惠截止时间！");
			return false;
		}
		
		/********************************************************************/
		if(hasyouhuiquan.isChecked()){
			if(beginTime.getText().toString().equals("")){
				log.Toast(HeYueQianDing_51.this, "请输入有效开始时间！");
				return false;
			}
			if(stopTime.getText().toString().equals("")){
				log.Toast(HeYueQianDing_51.this, "请输入有效结束时间！");
				return false;
			}
			if(!beginTime.getText().toString().equals("")&&!stopTime.getText().toString().equals("")){
				if(!dc.validateTime(beginTime.getText().toString(),stopTime.getText().toString())){
					
					log.Toast(HeYueQianDing_51.this, "有效开始时间不可大于有效结束时间！");
					return false;
				}
			}
			if (youhuiquanTxt.getText().toString().equals("")) {
				
				log.Toast(HeYueQianDing_51.this, "请输入优惠券内容！");
				return false;
			}
		}
		/********************************************************************/
		return true;
	}
	String Dnum;
	public void tiaoxingma() {
		SQLiteDatabase db=null;
		Cursor cursor=null;
		try
		{
			MyDatabaseHelper myHelper = new MyDatabaseHelper(
					HeYueQianDing_51.this, "techown.db", Constant.tdshDBVer);
			db = myHelper.getWritableDatabase();
			cursor = db.rawQuery("select * from st_storeinfo where id==?",
					new String[] { TuanDuiShangHuActivity.num });
			while (cursor.moveToNext()) {
				try {
					// s =
					// cursor.getString(cursor.getColumnIndex("ContractNumber"));
					Dnum =des.jieMI(cursor.getString(cursor
							.getColumnIndex("Dnum"))) ;

				} catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("团队TakePhotoActivity:"+e.getMessage());	
				}
				cursor.close();
				db.close();
			}
	
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("团队TakePhotoActivity:"+e.getMessage());		
		}
		finally{
			if(cursor!=null)
			{
				cursor.close();	
			}
			if(db!=null)
			{
				db.close();	
			}
			}
	}
}
