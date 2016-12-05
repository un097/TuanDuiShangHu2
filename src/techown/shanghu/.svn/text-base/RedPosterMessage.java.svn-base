package techown.shanghu;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * 总部活动--合约签订广宣信息录入（新建门店）
 */
public class RedPosterMessage  extends Activity{
	
	private Button button2,button3;
	public static String back;
	String merid;
	String IsNewPoster;
	String [] sp={"自有","公有","无"};
	String [] sp2={"小于100","100-300","300-1000","1000以上"};
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	int[] textIds = { R.id.h3edtext1, R.id.h3edtext2, R.id.redh3edtext3, R.id.h3edtext4,
			R.id.h3edtext5, R.id.redh3edtext6, R.id.redh3edtext7, R.id.redh3edtext8, R.id.redh3edtext9,
			R.id.redh3edtext10, R.id.redh3edtext12, R.id.redh3edtext13,
			R.id.redh3edtext15};
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.h4leout1,R.id.h4leout2,R.id.h4leout3,R.id.h4leout4,
			R.id.h4leout5,R.id.h4leout6,R.id.h4leout7,R.id.h4leout8,
			R.id.h4leout9,R.id.h4leout10,R.id.h4leout12,
			R.id.h4leout13,R.id.h4leout15};
	public LinearLayout[] linlay;
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redpostermessage);
        try{
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
		
        MyLis lis = new MyLis();

        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
        
        DES des = new DES();
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				RedPosterMessage.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from red_enterletter where id==?",
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
						.getColumnIndex("ShopInfo"))));
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ZhaoPai"))));
				
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Other"))));
				
				IsNewPoster = des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsNewPoster")));
				if(IsNewPoster.equals("N"))
				{
					for(int i = 0; i<linlayIds.length;i++){
						linlay[i].setEnabled(false);
		    		}
				}
				
				
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing_44:"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}if(db != null){
					db.close();
				}
			}
		}
        
		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.red_dialog(RedPosterMessage.this, textArray[0], "停车场", sp)	;			
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
					log.Dialog(RedPosterMessage.this,linlay[1],textArray[1],R.id.h3edtext2,"请输入营业时间");
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
					log.etDialog(RedPosterMessage.this, textArray[2],R.id.redh3edtext3,"请输入椅套数量");
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
					log.red_dialog(RedPosterMessage.this, textArray[3],"面积",sp2);
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
					log.Dialog(RedPosterMessage.this, linlay[4], textArray[4],R.id.h3edtext5,"请输入交通情况");
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
					log.etDialog(RedPosterMessage.this,textArray[5],R.id.redh3edtext6,"请输入台卡数量");
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
					log.etDialog(RedPosterMessage.this, textArray[6],R.id.redh3edtext7,"请输入易拉宝数量");
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
					log.etDialog(RedPosterMessage.this,textArray[7],R.id.redh3edtext8,"请输入门贴数量");
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
					log.etDialog(RedPosterMessage.this,textArray[8],R.id.redh3edtext9,"请输入海报数量");
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
					log.Dialog(RedPosterMessage.this,linlay[9], textArray[9],R.id.redh3edtext10,"请输入公司介绍");
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
					log.Dialog(RedPosterMessage.this,linlay[10], textArray[10],R.id.redh3edtext12,"请输入门店介绍");
					break;
				default:
					break;
				}
				return false;
			}
		});
        linlay[11].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(RedPosterMessage.this,linlay[11], textArray[11],R.id.redh3edtext13,"请输入招牌菜");
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
					log.Dialog(RedPosterMessage.this,linlay[12], textArray[12],R.id.redh3edtext15,"请输入其他");
					break;
				default:
					break;
				}
				return false;
			}
		});
        if(back!="xia"&&!Constant.isShopCreate)
        {
        	init();
        	back="stop";
        	for(int i = 0; i<linlayIds.length;i++){
				linlay[i].setEnabled(false);
    		}
        }
    }catch(Exception e){
    	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_44类："+e.getMessage());

    }
    }
    
    public void init()
    {
    	try{
    	textArray[0].setText(RedShopMessage.tempstr[20]);
		textArray[1].setText(RedShopMessage.tempstr[21]);
		textArray[2].setText(RedShopMessage.tempstr[22]);
		textArray[3].setText(RedShopMessage.tempstr[23]);
		textArray[4].setText(RedShopMessage.tempstr[24]);
		textArray[5].setText(RedShopMessage.tempstr[25]);
		textArray[6].setText(RedShopMessage.tempstr[26]);
		textArray[7].setText(RedShopMessage.tempstr[27]);
		textArray[8].setText(RedShopMessage.tempstr[28]);
		textArray[9].setText(RedShopMessage.tempstr[29]);
		textArray[10].setText(RedShopMessage.tempstr[31]);
		textArray[11].setText(RedShopMessage.tempstr[32]);
		textArray[12].setText(RedShopMessage.tempstr[34]);
		
    	}catch(Exception e){
    		techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_441类init（）"+e.getMessage());
    	}
    }
    
    
    public void saveData() {
    	try{
		String[] clomname = new String[] { "StParkSpace", "StBuinessHours",
				"Stzhuoyi", "Stmianji", "Stjiatong", "Sttaika",
				"Styilabao", "Stmengtie", "Sthaibao", "CompanyInfo", "ShopInfo",
				"ZhaoPai", "Other", "PosterID" ,"IsNewPoster"};
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
		if(Constant.isShopCreate)
		{
			clomstr[13] = "";
			clomstr[14] = "Y";
			System.out.println("是否新建广宣==="+clomstr[14]);
			System.out.println("广宣ID===="+clomstr[13]);
		}
		else if(!Constant.isShopCreate)
		{
			if(RedShopMessage.tempstr!=null)
			{
				if(RedShopMessage.tempstr[31] != null)
				{
					clomstr[13] = RedShopMessage.merid;
					clomstr[14] = "N";
					System.out.println("是否新建广宣==="+clomstr[14]);
					System.out.println("广宣ID===="+clomstr[13]);
				}
				else
				{
					clomstr[13] = "";
					clomstr[14] = "Y";
					System.out.println("是否新建广宣==="+clomstr[14]);
					System.out.println("广宣ID===="+clomstr[13]);
				}
			}
			else
			{
				clomstr[13] = "";
				clomstr[14] = "Y";
				System.out.println("是否新建广宣==="+clomstr[14]);
				System.out.println("广宣ID===="+clomstr[13]);
			}
		}
		
		DB.Red_upload(RedPosterMessage.this, clomname, clomstr, TuanDuiShangHuActivity.num);
    	}catch(Exception e){
    		techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_44类saveData（）"+e.getMessage());
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
						saveData();
						intent.setClass(RedPosterMessage.this, RedActivityMessage.class);
						RedPosterMessage.this.startActivity(intent);
						RedPosterMessage.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
					break;
					
					case R.id.button2:
						RedShopMessage.zhi = "poster";
						intent.setClass(RedPosterMessage.this, RedShopMessage.class);
						RedPosterMessage.this.startActivity(intent);
						RedPosterMessage.this.finish();
						overridePendingTransition(R.anim.rightin, R.anim.rightout);
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_44类按钮点击："+e.getMessage());

			}
		}
    }
 // 验证必填字段的是否为空
	protected boolean validate() {

		if (textArray[10].getText().toString().equals("")) {
			log.Toast(RedPosterMessage.this,"请输入门店介绍！");
			return false;
		}

		return true;
	}

    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(RedPosterMessage.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
