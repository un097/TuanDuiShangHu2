package techown.shanghu;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.photo.RedTakePhotoActivity;
import techown.shanghu.photo.TakePhotoActivity;
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
import android.widget.TextView;
/*
 * ������--���Ϣ¼��
 */
public class RedActivityMessage  extends Activity{
	public DuanXinDialog dxdialog = new DuanXinDialog();
	DataCheckUtil dc=new DataCheckUtil();
	private Button button2,button3;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
    int [] BdIds={R.id.baijin_radioButton,R.id.puka_radioButton,R.id.jinka_radioButton,
    		R.id.taipingyang2_radioButton,R.id.taipingyang_radioButton,R.id.other_radioButton};
    
    int [] AData={R.id.redhuodongyi,R.id.redhuodonger,R.id.redhuodongsan,
    		R.id.redhuodongsi,R.id.redhuodongwu,R.id.redhuodongliu,R.id.redhuodongqi};
    public  RadioButton[] rbArray;
    public  RadioButton[] ActivityData;
    
	int[] textIds = {R.id.redhuodong1, R.id.redhuodong2, R.id.redhuodong3, R.id.redhuodong4,
			 R.id.redhuodong5, R.id.redhuodong6, R.id.redhuodong7,R.id.redhuodong15, R.id.redhuodong16,
			 R.id.redhuodong17, R.id.redhuodong18, R.id.redhuodong20,
			 R.id.redhuodong21, R.id.redhuodong22, R.id.redhuodongduanxin};
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.redlayout1, R.id.redlayout2, R.id.redlayout3, R.id.redlayout4,
			 R.id.redlayout5, R.id.redlayout6, R.id.redlayout7, R.id.redlayout15, R.id.redlayout16,
			 R.id.redlayout17, R.id.redlayout18, R.id.redlayout20,
			 R.id.redlayout21, R.id.redlayout22, R.id.redlayoutduanxin};
	public static LinearLayout[] linlay;
	
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redactivitymessage);
        
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
		rbArray=new   RadioButton[BdIds.length];
		for (int i = 0; i < BdIds.length; i++) {
			rbArray[i] = (RadioButton)findViewById(BdIds[i]);
		}
		
		ActivityData=new   RadioButton[AData.length];
		for (int i = 0; i < AData.length; i++) {
			ActivityData[i] = (RadioButton)findViewById(AData[i]);
		}
		//getLogic();
		   
		
		tiaoxingma();
		try{
		//����
	    setRbCheck1(rbArray[0]);
	    setRbCheck(rbArray[1]);
	    setRbCheck(rbArray[2]);
	    setRbCheck(rbArray[3]);
	    setRbCheck(rbArray[4]);
	    setRbCheck(rbArray[5]);
	    
	    //���
	    setRbCheck1(ActivityData[0]);
	    setRbCheck(ActivityData[1]);
	    setRbCheck(ActivityData[2]);
	    setRbCheck(ActivityData[3]);
	    setRbCheck(ActivityData[4]);
	    setRbCheck(ActivityData[5]);
	    setRbCheck(ActivityData[6]);
	    
	     readData();
		
		
		textArray[7].setText(TuanDuiShangHuActivity.username);
		textArray[9].setText(TuanDuiShangHuActivity.username);
		textArray[8].setText(TuanDuiShangHuActivity.chinaname);
		textArray[10].setText(TuanDuiShangHuActivity.chinaname);
        
		//�������
		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.etDialog(RedActivityMessage.this, textArray[0],R.id.redhuodong1,"������������");
					break;
				default:
					break;
				}
				return false;
			}
		});
		//�������
		linlay[1].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.etDialog(RedActivityMessage.this, textArray[1],R.id.redhuodong2,"�������������");
					break;
				default:
					break;
				}
				return false;
			}
		});
		//ǩԼ����
        linlay[2].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					DateDialog.showDatePicker(RedActivityMessage.this, textArray[2],R.id.redhuodong3);
					break;
				default:
					break;
				}
				return false;
			}
		});
        //��������
        linlay[3].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.etDialog(RedActivityMessage.this, textArray[3],R.id.redhuodong4,"�����뷵������");
					break;
				default:
					break;
				}
				return false;
			}
		});
        //�Żݿ�ʼ����
        linlay[4].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					DateDialog.showDatePicker(RedActivityMessage.this, textArray[4],R.id.redhuodong5);
					break;
				default:
					break;
				}
					return true;
			}
		});
     
        //�Żݽ�ֹ����
        linlay[5].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					DateDialog.showDatePicker(RedActivityMessage.this, textArray[5],R.id.redhuodong6);
					break;
				default:
					break;
				}
				return false;
			}
		});
      //�Ƿ񷢲�
        final String[] province={"��","��"};
        linlay[6].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.red_dialog(RedActivityMessage.this,textArray[6],"��ѡ���Ƿ񷢲�",province);
					break;
				default:
					break;
				}
					return true;
			}
		});
        //��ҵ�
        final String[] activity={"����","����վ"};
        linlay[11].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.istwo=true;
					log.num=9;
					log.red_dialog(RedActivityMessage.this,textArray[11],"",activity);
					break;
				default:
					break;
				}
					return true;
			}
		});
        //�Ƿ��Ͷ���
        final String[] provincedx={"��","��","�ݻ�"};
        linlay[14].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.red_dialog(RedActivityMessage.this,textArray[14],"��ѡ���Ƿ��Ͷ���",provincedx);
					break;
				default:
					break;
				}
					return true;
			}
		});
        MyLis lis = new MyLis();
        
        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
    }catch(Exception e){
    	techown.shanghu.https.Log.Instance().WriteLog("RedActivityMessage��"+e.getMessage());
    }
    
    }
    
    DES des = new DES();
    //����
    private void readData() {

		MyDatabaseHelper myHelper = new MyDatabaseHelper(
				RedActivityMessage.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from red_enterletter where id==?",
				new String[] { TuanDuiShangHuActivity.num });
		while (cursor.moveToNext()) {
			try {
				
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("AcrivityMoneymin"))));
				textArray[1].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("AcrivityMoneymax"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ActivityqianyueDate"))));
				textArray[3].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Acrivityreturn"))));
				textArray[4].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ActivityYouhuiDateStart"))));
				textArray[5].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ActivityYouhuiDateStop"))));
				textArray[6].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("AcrivityIsPublish"))));
				if(textArray[6].getText().toString().equals("Y"))
				{
					textArray[6].setText("��");
				}
				else if(textArray[6].getText().toString().equals("N"))
				{
					textArray[6].setText("��");
				}
				else
				{
					textArray[6].setText("");
				}
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Activityhangye"))));
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Duanxinnrirong"))));
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Activityneirong"))));
				textArray[14].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Isduanxin"))));
				if(textArray[14].getText().toString().equals("Y"))
				{
					textArray[14].setText("��");
				}
				else if(textArray[14].getText().toString().equals("N"))
				{
					textArray[14].setText("��");
				}
				else if(textArray[14].getText().toString().equals("S"))
				{
					textArray[14].setText("�ݻ�");
				}
				else 
				{
					textArray[14].setText("");
				}
				System.out.println("����==="+des.jieMI(cursor.getString(cursor.getColumnIndex("Acrivitykazhong"))));
				String Acrivitykazhong=des.jieMI(cursor.getString(cursor.getColumnIndex("Acrivitykazhong")));
				String [] card2=Acrivitykazhong.split("");
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
				System.out.println("���==="+des.jieMI(cursor.getString(cursor.getColumnIndex("Acrivityhuodongri"))));
				String Acrivityhuodongri=des.jieMI(cursor.getString(cursor.getColumnIndex("Acrivityhuodongri")));
				String [] Data2=Acrivityhuodongri.split("");
				Log.i("output", String.valueOf(Data2.length));
				Log.i("output", Data2[1]);
				for(int i=1;i<Data2.length;i++){
					
					if(Data2[i].equals("1")){
						ActivityData[i-1].setChecked(true);
				      
					}
					else if(Data2[i].equals("0")){
						ActivityData[i-1].setChecked(false);
				      
					}
				}
				
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�RedActivityMessage:"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}if(db != null){
					db.close();
				}
			}
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
	
	class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			try {
				// TODO Auto-generated method stub
				int id = v.getId();
				Intent intent = new Intent();
				switch(id){
					case R.id.button2:
						RedPosterMessage.back = "xia";
						intent.setClass(RedActivityMessage.this, RedPosterMessage.class);
						RedActivityMessage.this.startActivity(intent);
						RedActivityMessage.this.finish();
						overridePendingTransition(R.anim.rightin, R.anim.rightout);
					break;

					case R.id.button3:
						
						if(validate()){
						saveData();
						System.out.println();
						intent.setClass(RedActivityMessage.this, RedTakePhotoActivity.class);
						RedActivityMessage.this.startActivity(intent);
						RedActivityMessage.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("RedActivityMessage�ఴť�����"+e.getMessage());

			}
		}
    }
    
    //����
    public void saveData() {
    	try{
		String[] clomname = new String[] { "Activityneirong", "ActivityqianyueDate",
				"ActivityYouhuiDateStart", "ActivityYouhuiDateStop",  "Acrivitykazhong",
				"Acrivityhuodongri","AcrivityMoneymin","AcrivityMoneymax",
				"Acrivityreturn","AcrivityIsPublish","Isduanxin","Duanxinnrirong","Activityhangye"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[13].getText().toString();//�Ż�����
		clomstr[1] = textArray[2].getText().toString();//ǩԼ����
		clomstr[2] = textArray[4].getText().toString();//�Żݿ�ʼʱ��
		clomstr[3] = textArray[5].getText().toString();//�Żݽ�ֹʱ��
		StringBuffer sb=new StringBuffer();
		int n=0;
		ischeck2(rbArray[0],n,sb);
		ischeck2(rbArray[1],n,sb);
		ischeck2(rbArray[2],n,sb);
		ischeck2(rbArray[3],n,sb);
		ischeck2(rbArray[4],n,sb);
		ischeck2(rbArray[5],n,sb);
		clomstr[4] = sb.toString();//����
		System.out.println("����==="+sb.toString());
		
		StringBuffer sb2=new StringBuffer();
		int n2=0;
		ischeck2(ActivityData[0],n2,sb2);
		ischeck2(ActivityData[1],n2,sb2);
		ischeck2(ActivityData[2],n2,sb2);
		ischeck2(ActivityData[3],n2,sb2);
		ischeck2(ActivityData[4],n2,sb2);
		ischeck2(ActivityData[5],n2,sb2);
		ischeck2(ActivityData[6],n2,sb2);
		clomstr[5] = sb2.toString();//���
		System.out.println("���==="+sb2.toString());
		
		System.out.println(clomstr[5]);
		clomstr[6] = textArray[0].getText().toString();//��������
		clomstr[7] = textArray[1].getText().toString();//��������
		clomstr[8] = textArray[3].getText().toString();//���ر���
		clomstr[9] = textArray[6].getText().toString();//�Ƿ񷢲�
		if(clomstr[9].equals("��"))
		{
			clomstr[9] = "Y";
		}
		else if(clomstr[9].equals("��"))
		{
			clomstr[9] = "N";
		}
		else
		{
			clomstr[9] = "";
		}
		clomstr[10] = textArray[14].getText().toString();//�Ƿ��Ͷ���
		if(clomstr[10].equals("��"))
		{
			clomstr[10] = "Y";
		}
		else if(clomstr[10].equals("��"))
		{
			clomstr[10] = "N";
		}
		else if(clomstr[10].equals("�ݻ�"))
		{
			clomstr[10] = "S";
		}
		else
		{
			clomstr[10] = "";
		}
		clomstr[11] = textArray[12].getText().toString();//��������
		clomstr[12] = textArray[11].getText().toString();//��ҵ�
		
		
		DB.Red_upload(RedActivityMessage.this, clomname, clomstr, TuanDuiShangHuActivity.num);
    	}catch(Exception e){
        	techown.shanghu.https.Log.Instance().WriteLog("RedActivityMessage��saveData()"+e.getMessage());

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

	
	protected boolean validate() {
		if (textArray[4].getText().toString().equals("")) {
			
			log.Toast(RedActivityMessage.this, "�������Żݿ�ʼ���ڣ�");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			
			log.Toast(RedActivityMessage.this, "�������Żݽ�ֹ���ڣ�");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			
			log.Toast(RedActivityMessage.this, "������ǩԼ���ڣ�");
			return false;
		}
		if (textArray[6].getText().toString().equals("")) {
			
			log.Toast(RedActivityMessage.this, "��ѡ���Ƿ񷢲���");
			return false;
		}
		if (textArray[14].getText().toString().equals("")) {
			
			log.Toast(RedActivityMessage.this, "��ѡ���Ƿ��Ͷ��ţ�");
			return false;
		}
		if (textArray[13].getText().toString().equals("")) {
			
			log.Toast(RedActivityMessage.this, "�������Ż����ݣ�");
			return false;
		}
		if (textArray[12].getText().toString().equals("")) {
			
			log.Toast(RedActivityMessage.this, "������������ݣ�");
			return false;
		}
		if(!dc.validateTime(textArray[4].getText().toString(),textArray[5].getText().toString())){
			
			log.Toast(RedActivityMessage.this, "�Żݿ�ʼ���ڲ��ɴ����Żݽ�ֹ���ڣ�");
			return false;
		}
		StringBuffer sb=new StringBuffer();
		int n=0;
		ischeck2(rbArray[0],n,sb);
		ischeck2(rbArray[1],n,sb);
		ischeck2(rbArray[2],n,sb);
		ischeck2(rbArray[3],n,sb);
		ischeck2(rbArray[4],n,sb);
		ischeck2(rbArray[5],n,sb);
		if(sb.toString().equals("000000"))
		{
			log.Toast(RedActivityMessage.this, "��ѡ���֣�");
			return false;
		}
		StringBuffer sb2=new StringBuffer();
		int n2=0;
		ischeck2(ActivityData[0],n2,sb2);
		ischeck2(ActivityData[1],n2,sb2);
		ischeck2(ActivityData[2],n2,sb2);
		ischeck2(ActivityData[3],n2,sb2);
		ischeck2(ActivityData[4],n2,sb2);
		ischeck2(ActivityData[5],n2,sb2);
		ischeck2(ActivityData[6],n2,sb2);
		if(sb2.toString().equals("0000000"))
		{
			log.Toast(RedActivityMessage.this, "��ѡ���գ�");
			return false;
		}
		return true;
	}
	
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(RedActivityMessage.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	
	
	 String Dnum;
		public void tiaoxingma() {
			SQLiteDatabase db=null;
			Cursor cursor=null;
			try
			{
				MyDatabaseHelper myHelper = new MyDatabaseHelper(
						RedActivityMessage.this, "techown.db", Constant.tdshDBVer);
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
						techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�TakePhotoActivity:"+e.getMessage());	
					}
					cursor.close();
					db.close();
				}
		
			}catch(Exception e)
			{
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�TakePhotoActivity:"+e.getMessage());		
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
