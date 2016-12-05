package techown.shanghu;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
/*
 * 城市活动--合约签订广宣信息录入（已有门店）
 */
public class HeYueQianDing_451  extends Activity{
	
	private Button button2,button3;
	EditDialog log=new EditDialog();
	String [] sp={"自有","公有","无"};
	String [] sp2={"小于100","100-300","300-1000","1000以上"};
	
	int[] textIds = { R.id.h3edtext1, R.id.h3edtext2, R.id.h3edtext3, R.id.h3edtext4,
			R.id.h3edtext5, R.id.h3edtext6, R.id.h3edtext7, R.id.h3edtext8, R.id.h3edtext9,
			R.id.h3edtext10, R.id.h3edtext11, R.id.h3edtext12, R.id.h3edtext13, R.id.h3edtext14,
			R.id.h3edtext15};
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.h4leout1,R.id.h4leout2,R.id.h4leout3,R.id.h4leout4,
			R.id.h4leout5,R.id.h4leout6,R.id.h4leout7,R.id.h4leout8,
			R.id.h4leout9,R.id.h4leout10,R.id.h4leout11,R.id.h4leout12,
			R.id.h4leout13,R.id.h4leout14,R.id.h4leout15};
	public LinearLayout[] linlay;
	DBUtil DB=new DBUtil();
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heyueqianding_4);
//        panel=new Panel(this);

        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
       
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		
//		getLogic();
        
        try{
        MyLis lis = new MyLis();

        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
        
        DES des = new DES();
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				HeYueQianDing_451.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.num });
		while (cursor.moveToNext()) {
			try {
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StParkSpace"))));
				textArray[1].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StBuinessHours"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stzhuoyi"))));
				textArray[3].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stmianji"))));
				textArray[4].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stjiatong"))));
				textArray[5].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Sttaika"))));
				textArray[6].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Styilabao"))));
				textArray[7].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stmengtie"))));
				textArray[8].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Sthaibao"))));
				textArray[9].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyInfo"))));
				textArray[10].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("BrandInfo"))));
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StoreInfo"))));
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ZhaoPai"))));
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("AvgExpense"))));
				textArray[14].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Other"))));
				
				
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing_451:"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}if(db != null){
					db.close();
				}
			}
		}   
        
		init();
        }catch(Exception e){
    	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_451类"+e.getMessage());
    	}
        
    }
    
    public void saveData() {
    	try{
		String[] clomname = new String[] { "StParkSpace", "StBuinessHours",
				"Stzhuoyi", "Stmianji", "Stjiatong", "Sttaika",
				"Styilabao", "Stmengtie", "Sthaibao", "CompanyInfo", "BrandInfo",
				"StoreInfo", "ZhaoPai","AvgExpense","Other"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[0].getText().toString();
		clomstr[1] = textArray[1].getText().toString();
		clomstr[2] = textArray[2].getText().toString();
		clomstr[3] = textArray[3].getText().toString();
		clomstr[4] = textArray[4].getText().toString();
		clomstr[5] = textArray[5].getText().toString();
		clomstr[6] = textArray[6].getText().toString();
		clomstr[7] = textArray[7].getText().toString();
		clomstr[8] = textArray[8].getText().toString();
		clomstr[9] = textArray[9].getText().toString();
		clomstr[10] = textArray[10].getText().toString();
		clomstr[11] = textArray[11].getText().toString();
		clomstr[12] = textArray[12].getText().toString();
		clomstr[13] = textArray[13].getText().toString();
		clomstr[14] = textArray[14].getText().toString();

		
		DB.upload(HeYueQianDing_451.this, clomname, clomstr, TuanDuiShangHuActivity.num);
    	}catch(Exception e){
        	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_451类saveData()"+e.getMessage());
    	}
	}
    
    
    public void init()
    {
    	try{
    	textArray[0].setText(ChengShiHuoDong31.tempstr[20]);
		textArray[1].setText(ChengShiHuoDong31.tempstr[21]);
		textArray[2].setText(ChengShiHuoDong31.tempstr[22]);
		textArray[3].setText(ChengShiHuoDong31.tempstr[23]);
		textArray[4].setText(ChengShiHuoDong31.tempstr[24]);
		textArray[5].setText(ChengShiHuoDong31.tempstr[25]);
		textArray[6].setText(ChengShiHuoDong31.tempstr[26]);
		textArray[7].setText(ChengShiHuoDong31.tempstr[27]);
		textArray[8].setText(ChengShiHuoDong31.tempstr[28]);
		textArray[9].setText(ChengShiHuoDong31.tempstr[29]);
		textArray[10].setText(ChengShiHuoDong31.tempstr[30]);
		textArray[11].setText(ChengShiHuoDong31.tempstr[31]);
		textArray[12].setText(ChengShiHuoDong31.tempstr[32]);
		textArray[13].setText(ChengShiHuoDong31.tempstr[33]);
		textArray[14].setText(ChengShiHuoDong31.tempstr[34]);
		
    	}catch(Exception e){
        	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_451类init()"+e.getMessage());

    	}
	
		
		
    }
    
    class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			try {
				// TODO Auto-generated method stub
				int id = v.getId();
				Intent intent = new Intent();
				switch(id){
					case R.id.button3:
						if(validate()){
//					saveLogic();
						saveData();
						intent.setClass(HeYueQianDing_451.this, ChengShiHuoDong22.class);
						HeYueQianDing_451.this.startActivity(intent);
						HeYueQianDing_451.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
					break;
					
					case R.id.button2:
						
						intent.setClass(HeYueQianDing_451.this, ChengShiHuoDong3.class);
						HeYueQianDing_451.this.startActivity(intent);
						HeYueQianDing_451.this.finish();
						overridePendingTransition(R.anim.rightin, R.anim.rightout);
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_451类按钮点击："+e.getMessage());

			}
		}
    }
 // 验证必填字段的是否为空
	protected boolean validate() {
		if (textArray[0].getText().toString().equals("")) {
			
			log.Toast(HeYueQianDing_451.this,"请输入停车场！");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			
			log.Toast(HeYueQianDing_451.this,"请输入营业时间！");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			
			log.Toast(HeYueQianDing_451.this,"请输入面积！");
			return false;
		}
		if (textArray[4].getText().toString().equals("")) {
			
			log.Toast(HeYueQianDing_451.this,"请输入交通情况！");
			return false;
		}


		if (textArray[11].getText().toString().equals("")) {
			
			log.Toast(HeYueQianDing_451.this,"请输入门店介绍！");
			return false;
		}

		if (textArray[12].getText().toString().equals("")) {
			
			log.Toast(HeYueQianDing_451.this,"请输入招牌菜！");
			return false;
		}
		if (textArray[13].getText().toString().equals("")) {
			log.Toast(HeYueQianDing_451.this, "请输入人均消费！");
			return false;
		}
		return true;
	}

    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(HeYueQianDing_451.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
