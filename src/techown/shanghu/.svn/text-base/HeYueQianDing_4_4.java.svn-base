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
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
/*
 * 合约签订广宣信息录入（草稿箱）
 */
public class HeYueQianDing_4_4  extends Activity{
	public EditDialog dialog = new EditDialog();
	private Button button2,button3;
	String [] sp={"自有","公有","无"};
	String [] sp2={"小于100","100-300","300-1000","1000以上"};
	
	int[] textIds = {R.id.h3edtext1,  R.id.h3edtext2, R.id.h3edtext3, R.id.h3edtext4,
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
		
//		getLogic();
        
        
        MyLis lis = new MyLis();

        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
        
        DES des = new DES();
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				HeYueQianDing_4_4.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.tmpId1 });
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
				techown.shanghu.https.Log.Instance().WriteLog("团队HeYueQianDing4_4:"+e.getMessage());
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
				case MotionEvent.ACTION_DOWN:
					dialog.dialog(HeYueQianDing_4_4.this, textArray[0], "停车场", sp)	;			
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
					dialog.Dialog(HeYueQianDing_4_4.this,linlay[1],textArray[1],R.id.h3edtext2,"请输入营业时间");
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
					dialog.etDialog(HeYueQianDing_4_4.this, textArray[2],R.id.h3edtext3,"请输入椅套数量");
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
					dialog.dialog(HeYueQianDing_4_4.this, textArray[3],"面积",sp2);
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
					dialog.Dialog(HeYueQianDing_4_4.this, linlay[4], textArray[4],R.id.h3edtext5,"请输入交通情况");
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
					dialog.etDialog(HeYueQianDing_4_4.this,textArray[5],R.id.h3edtext6,"请输入台卡数量");
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
					dialog.etDialog(HeYueQianDing_4_4.this, textArray[6],R.id.h3edtext7,"请输入易拉宝数量");
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
					dialog.etDialog(HeYueQianDing_4_4.this,textArray[7],R.id.h3edtext8,"请输入门贴数量");
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
					dialog.etDialog(HeYueQianDing_4_4.this,textArray[8],R.id.h3edtext9,"请输入海报数量");
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
				case MotionEvent.ACTION_DOWN:
					dialog.Dialog(HeYueQianDing_4_4.this,linlay[9], textArray[9],R.id.h3edtext10,"请输入公司介绍");
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
				case MotionEvent.ACTION_DOWN:
					dialog.Dialog(HeYueQianDing_4_4.this,linlay[10], textArray[10],R.id.h3edtext11,"请输入品牌介绍");
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
				case MotionEvent.ACTION_DOWN:
					dialog.Dialog(HeYueQianDing_4_4.this,linlay[11], textArray[11],R.id.h3edtext12,"请输入门店介绍");
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
				case MotionEvent.ACTION_DOWN:
					dialog.Dialog(HeYueQianDing_4_4.this,linlay[12], textArray[12],R.id.h3edtext13,"请输入招牌菜");
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
				case MotionEvent.ACTION_DOWN:
					dialog.etDialog(HeYueQianDing_4_4.this, textArray[13],R.id.h3edtext14,"请输入人均消费");
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
				case MotionEvent.ACTION_DOWN:
					dialog.Dialog(HeYueQianDing_4_4.this,linlay[14], textArray[14],R.id.h3edtext15,"请输入其他");
					break;
				default:
					break;
				}

				return false;
			}
		});
    }catch(Exception e){
    	techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_4_4类："+e.getMessage());

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

		
		DB.upload(HeYueQianDing_4_4.this, clomname, clomstr, TuanDuiShangHuActivity.tmpId1);
    	}catch(Exception  e){
    		techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing类saveData()"+e.getMessage());
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
						intent.setClass(HeYueQianDing_4_4.this, HeYueQianDing_5_5.class);
						HeYueQianDing_4_4.this.startActivity(intent);
						HeYueQianDing_4_4.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
					break;
					
					case R.id.button2:
						
						intent.setClass(HeYueQianDing_4_4.this, HeYueQianDing_3_3.class);
						HeYueQianDing_4_4.this.startActivity(intent);
						HeYueQianDing_4_4.this.finish();
						overridePendingTransition(R.anim.rightin, R.anim.rightout);
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("HeYueQianDing_4_4类按钮点击："+e.getMessage());

			}
		}
    }
 // 验证必填字段的是否为空
	protected boolean validate() {
//		if (textArray[0].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_4_4.this, "请输入停车场！");
//			return false;
//		}
//		if (textArray[1].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_4_4.this,  "请输入营业时间！");
//			return false;
//		}
//		if (textArray[2].getText().toString().equals("")) {
//			Toast.makeText(HeYueQianDing_44.this, "请输入椅套！", Toast.LENGTH_LONG)
//					.show();
//			return false;
//		}
//		if (textArray[3].getText().toString().equals("")) {
//			
//			dialog.Toast(HeYueQianDing_4_4.this, "请输入面积！");
//			return false;
//		}
//		if (textArray[4].getText().toString().equals("")) {
////			Toast.makeText(HeYueQianDing_4_4.this, "请输入交通情况！", Toast.LENGTH_LONG)
////					.show();
//			dialog.Toast(HeYueQianDing_4_4.this, "请输入交通情况！");
//			return false;
//		}
		if (textArray[11].getText().toString().equals("")) {
			dialog.Toast(HeYueQianDing_4_4.this, "请输入门店介绍！");
			return false;
		}

//		if (textArray[12].getText().toString().equals("")) {
//			dialog.Toast(HeYueQianDing_4_4.this, "请输入招牌菜！");
//			return false;
//		}
//		if (textArray[13].getText().toString().equals("")) {
//			dialog.Toast(HeYueQianDing_4_4.this, "请输入人均消费！");
//			return false;
//		}



		return true;
	}

    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			dialog.ExitApp(HeYueQianDing_4_4.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}
