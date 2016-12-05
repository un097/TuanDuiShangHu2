package techown.shanghu;


import org.json.JSONObject;

import techown.shanghu.biaoge.ActionCode;
import techown.shanghu.biaoge.AlgorithmConvert;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetData;
import techown.shanghu.photo.TakePhotoActivity;
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
import android.widget.TextView;

/*
 * ��Լǩ����˾��Ϣ¼��
 */
public class RedCompanyMessage extends Activity {

	public String redCompanyCityId;//��ҳ���Ӧ�ĳ���Id������˾���ڵĳ���

	protected static final String DialogUtil = null;
	public static String CompanyLicense1,chengshi;
	DES des = new DES();
	private Button button2, add_button,button_shao,name_button;
	public static String isomux,com;
	public static String requeststr2;
	public static String xinhaishijiu;
	public static String RedNum;
	String[] province;
	public static String requeststr1;
	public static String[] tempstr1;
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	public static int bs;
	GetData getDasta=new GetData();
	PadUtiltiy mPadUtiltiy;
	boolean ischeck=false;
	public static int num=0;//���Ʋ��÷���

	int[] textIds = { R.id.h1et1, R.id.h1et2, R.id.h1et3, R.id.redh1et4,
			R.id.h1et5, R.id.h1et6, R.id.h1et7,R.id.h1et8, R.id.h1et9,
			R.id.h1et10, R.id.redh1et11, R.id.h1et12, R.id.redh1et13, R.id.h1et14};
	public static TextView[] textArray;

	int[] linlayIds = { R.id.h1leout1, R.id.h1leout2, R.id.h1leout3,
			R.id.h1leout4, R.id.h1leout5, R.id.h1leout6, R.id.h1leout7,
			R.id.h1leout8, R.id.h1leout9, R.id.h1leout10, R.id.h1leout11,
			R.id.h1leout12, R.id.h1leout13, R.id.h1leout14};
	public LinearLayout[] linlay;
  
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703��,7021��ϸ��ַ
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73����74��ϸ��ַ
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 501){
			if (data != null) {
				textArray[13].setText("Scan canceled!".equals(data.getStringExtra("data")) ? "" : data.getStringExtra("data"));
			}
		}else if(requestCode == 801){
			if (data != null) {
				String barcode = data.getStringExtra("text");
				textArray[13].setText(barcode);
			}
		}
		try{
			
			if(TuanDuiShangHuActivity.device == 0){
				
			
		        if(0 == requestCode && resultCode == RESULT_OK) {
		            if(null == data) {
		            } else {
		                final String text = data.getStringExtra("text");
		                
		                if(textArray[13]!=null)
		                	textArray[13].setText(text);
		            }
		        }
			}
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�������onActivityResult:"+e.getMessage());
		}
		
    }
	protected void onResume() {
		// TODO Auto-generated method stub
		try{
			super.onResume();
			if(ischeck)
			{
				if(textArray[13]!=null)
					if(mPadUtiltiy.GetBarcode()!=null){
						textArray[13].setText(mPadUtiltiy.GetBarcode());
					}
			}
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ��Ŷ�������:"+e.getMessage());
		}
		
	}
	public static String cityname;
	public String companyId;
	
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.redcompanymessage);
		DB.Red_upload(RedCompanyMessage.this, new String[]{"Dnum"}, new String[]{"2"},
				TuanDuiShangHuActivity.num);
		try{
		mPadUtiltiy = new PadUtiltiy(RedCompanyMessage.this);
		province = DB.klQuery(RedCompanyMessage.this, "Name", "t_province");
		
		ActionCode ac = new ActionCode();
		String barcode = ac.runVerifyCode(15);
		isomux = AlgorithmConvert.backtonum(barcode);
		
		h1et7 = (TextView) findViewById(R.id.h1et7);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
		h1et7021 = (TextView) findViewById(R.id.redh1et7021);
		
		h1leout71 = (LinearLayout)findViewById(R.id.h1leout71);
		h1leout72 = (LinearLayout)findViewById(R.id.h1leout72);
		h1leout73 = (LinearLayout)findViewById(R.id.h1leout73);
		h1leout74 = (LinearLayout)findViewById(R.id.h1leout74);
		
		button2 = (Button) findViewById(R.id.button2);
		add_button = (Button) findViewById(R.id.add_button);
		button_shao = (Button) findViewById(R.id.shao);
		name_button = (Button) findViewById(R.id.name_button);

		button_shao.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				try{
//					if(TuanDuiShangHuActivity.device == 0){
//						Intent i = new Intent("cn.iandroid.codescan.ScanAndDecode");
//						i.putExtra("charset", (String) null);
//						startActivityForResult(i, 0);
//					}else if(TuanDuiShangHuActivity.device == 1){
						mPadUtiltiy.startBarCode();
						ischeck=true;
//					}
				}catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�������:"+e.getMessage());
				}
			}
			
		});
		
		linlay = new LinearLayout[linlayIds.length];
		for (int i = 0; i < linlayIds.length; i++) {
			linlay[i] = (LinearLayout) findViewById(linlayIds[i]);
		}

		textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}

		if(num==1){
			button2.setEnabled(false);
		}
				// ���ü���
		MyLis lis = new MyLis();
		button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RedBigArea.i = 1;
				Intent intent = new Intent();
				intent.setClass(RedCompanyMessage.this, RedBigArea.class);
				RedCompanyMessage.this.startActivity(intent);
				RedCompanyMessage.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);
			}
		});
		add_button.setOnClickListener(lis);
		name_button.setOnClickListener(lis);
		
		MyDatabaseHelper myHelper = new MyDatabaseHelper(
				RedCompanyMessage.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from red_enterletter where id==?",
				new String[] { TuanDuiShangHuActivity.num });
		while (cursor.moveToNext()) {
			try {
				
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyName"))));
				textArray[1].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("LegalPerson"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ContactUser"))));
				textArray[3].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("TelNumber"))));
				textArray[4].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("MobileNumber"))));
				textArray[5].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ManageName"))));
				h1et7021.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Adress"))));
				textArray[7].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("PostCode"))));
				textArray[8].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ManageScope"))));
				textArray[9].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Brand"))));
				textArray[10].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("BrandTelNumber"))));
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Validity"))));
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("EngageYear"))));
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("TreatyNumber"))));
				h1et703.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyArea"))));
				h1et702.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyCity"))));
				redCompanyCityId = des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyCity")));

				String IsNewCompany=des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsNewCompany")));
				if(IsNewCompany.equals("N")){
					h1leout73.setEnabled(false);
					h1leout74.setEnabled(false);
					for(int i = 0; i<linlayIds.length;i++){
						linlay[i].setEnabled(false);
		    		}
				}
				
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷ����RedCompanyMessage:"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}if(db != null){
					db.close();
				}
			}
			//���и�ֵ
			String chengshiname = DB.queryCityname(RedCompanyMessage.this, h1et702.getText().toString());
			h1et702.setText(chengshiname);
			//ʡ��ֵ
			String provinceid = DB.queryShengname(RedCompanyMessage.this, h1et702.getText().toString());
			String privincename = DB.queryProvinceName(RedCompanyMessage.this, provinceid);
			h1et7.setText(privincename);
			//��������ֵ
			String name=DB.klQueryCity1(this, "t_district", "where Id = '"
					+ des.jiaMi(h1et703.getText().toString()) + "'");
					h1et703.setText(name);
		}
		
		
		String shengid = DB.queryShengname(RedCompanyMessage.this, RedBigArea.chengshi);
		String shengname = DB.queryProvinceName(RedCompanyMessage.this, shengid);
		h1et7.setText(shengname);
		h1et702.setText(RedBigArea.chengshi);
		/*
		 * ��¼�Ӵ��������ҳ������ĳ���Id
		 */
		redCompanyCityId = RedBigArea.redAreaCityId;
		


		h1leout73.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				if(h1et7.getText().toString().equals("")){
					h1et703.setEnabled(false);
					
				}
			 else{
				if(h1et702.getText().toString().equals("��")){
					h1et703.setText("��");
					h1et703.setEnabled(false);
		}
				else {
				/*String[] str1 = getDasta.getCity11(RedCompanyMessage.this,
						h1et702.getText().toString());
				*/
				/**
				 * ���ݳ���Id��ȡ���������������
				 */
				String[] str1 = getDasta.getCity12(RedCompanyMessage.this,
							redCompanyCityId);	 
				if (str1.length > 0) {
					log.red_dialog(RedCompanyMessage.this, h1et703, "��",
							str1);
					
				}
				else if(h1et702.getText().toString().equals("��")){
					
				}else {
					h1et703.setText("��");
					h1et703.setEnabled(false);
				}
				
			}
			}
			}
		});
		
		
		h1leout74.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.Dialog(RedCompanyMessage.this, h1leout74,
						h1et7021, R.id.redh1et7021,"������ע���ַ");
			}
		});
		
		linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(RedCompanyMessage.this, linlay[0],
							textArray[0], R.id.h1et1,"�����빫˾����");
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
					log.Dialog(RedCompanyMessage.this, linlay[1],
							textArray[1], R.id.h1et2,"�����뷨�˴���");
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
					log.Dialog(RedCompanyMessage.this, linlay[2],
							textArray[2], R.id.h1et3,"��������ϵ��");
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
					log.etDialog(RedCompanyMessage.this,
							textArray[3], R.id.redh1et4,"������̶��绰");
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
					log.etDialog(RedCompanyMessage.this,
							textArray[4], R.id.h1et5,"�������ֻ�����");
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
					log.Dialog(RedCompanyMessage.this, linlay[5],
							textArray[5], R.id.h1et6,"�����뾭Ӫ�ŵ�����");
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
					log.etDialog(RedCompanyMessage.this, textArray[7],
							R.id.h1et8,"�������ʱ�");
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
					log.Dialog(RedCompanyMessage.this, linlay[8],
							textArray[8], R.id.h1et9,"�����뾭Ӫ��Χ");
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
					log.Dialog(RedCompanyMessage.this, linlay[9],
							textArray[9], R.id.h1et10,"������Ʒ��");
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
					log.etDialog(RedCompanyMessage.this,
							textArray[10], R.id.redh1et11,"������Ʒ������");
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
					DateDialog.showDatePicker(RedCompanyMessage.this,
							textArray[11], R.id.h1et12);
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
					log.etDialog(RedCompanyMessage.this,
							textArray[12], R.id.redh1et13,"�����뾭Ӫ����");
					break;
				default:
					break;
				}

				return false;
			}
		});


		init();
	}catch(Exception e){
		techown.shanghu.https.Log.Instance().WriteLog("���RedCompanyMessage�ࣺ"+e.getMessage());
	}
	
	}

	
	public void init()
    {
		if(!requeststr1.equals("")){
    	try
		{    
    		JSONObject demoJson1 = new JSONObject(requeststr1);
			if(demoJson1.getString("rspCode").equals("00"))
			{
				tempstr1=new String[19];
				tempstr1[0]=demoJson1.getString("name");//��˾��
				tempstr1[1]=demoJson1.getString("companyId");//��˾ID
				companyId = demoJson1.getString("companyId");//��˾ID
				tempstr1[2]=demoJson1.getString("legalPerson");//����
				tempstr1[3]=demoJson1.getString("contactPerson");//��ϵ��
				tempstr1[4]=demoJson1.getString("contactTel");//��ϵ�绰
				tempstr1[5]=demoJson1.getString("handPhone");//�ֻ�	
				tempstr1[6]=demoJson1.getString("busiName");//��Ӫ��
				tempstr1[7]=demoJson1.getString("address");//ע���ַ
				tempstr1[8]=demoJson1.getString("busi");//��Ӫ��Χ
				tempstr1[9]=demoJson1.getString("brand");//Ʒ��
				tempstr1[10]=demoJson1.getString("brandHotLine");//Ʒ������
				tempstr1[11]=demoJson1.getString("zip");//�ʱ�
				tempstr1[12]=demoJson1.getString("validDate");//��Ч��
				tempstr1[13]=demoJson1.getString("busiYear");//��Ӫ����
				tempstr1[14]=demoJson1.getString("province");//ʡ
				tempstr1[15]=demoJson1.getString("city");//��
				redCompanyCityId = tempstr1[15];
				tempstr1[16]=demoJson1.getString("area");//��
				
				tempstr1[17]=demoJson1.getString("agrmId");
				tempstr1[18]=demoJson1.getString("agrmType");
				if(tempstr1[18].equals("02")&&tempstr1[17]!=null)
				{
					textArray[13].setText(tempstr1[17]);
					button_shao.setEnabled(false);
					DB.Red_upload(RedCompanyMessage.this, new String[]{"Dnum"}, new String[]{"1"},
					TuanDuiShangHuActivity.num);
				}
				else
				{
					String[] leixing = tempstr1[18].split("\\|");
					String[] tiaoxing=tempstr1[17].split("\\|");
					for (int i = 0; i < leixing.length; i++) {
						if(leixing[i].equals("02"))
						{
							textArray[13].setText(tiaoxing[i]);
							button_shao.setEnabled(false);
							DB.Red_upload(RedCompanyMessage.this, new String[]{"Dnum"}, new String[]{"1"},
							TuanDuiShangHuActivity.num);
						}
					}
				}
				DES des=new DES();
				
				String id = DB.klQueryCity1(this, "t_province", "where Id = '"
						+ des.jiaMi(tempstr1[14]) + "'");
				tempstr1[14] = id;

				String id1 = DB.klQueryCity1(this, "t_city", "where Id = '"
						+ des.jiaMi(tempstr1[15]) + "'");
				tempstr1[15] = id1;
				
				String id2 = DB.klQueryCity1(this, "t_district", "where Id = '"
						+ des.jiaMi(tempstr1[16]) + "'");
				tempstr1[16] = id2;
				
				textArray[0].setText(tempstr1[0]);//��˾��
				textArray[1].setText(tempstr1[2]);//����
				textArray[2].setText(tempstr1[3]);//��ϵ��
				textArray[3].setText(tempstr1[4]);//��ϵ�绰
				textArray[4].setText(tempstr1[5]);//�ֻ�	
				textArray[5].setText(tempstr1[6]);//��Ӫ��
				textArray[7].setText(tempstr1[11]);//�ʱ�
				textArray[8].setText(tempstr1[8]);//��Ӫ��Χ
				textArray[9].setText(tempstr1[9]);//Ʒ��
				textArray[10].setText(tempstr1[10]);//Ʒ������
				textArray[11].setText(tempstr1[12]);//��Ч��
				textArray[12].setText(tempstr1[13]);//��Ӫ����
	    		
	    		h1et7.setText(id);
	    		h1et702.setText(id1);
	    		h1et703.setText(id2);
	    		h1et7021.setText(tempstr1[7]);
	    		h1leout73.setEnabled(false);
				h1leout74.setEnabled(false);
				for(int i=0;i<13;i++){
					linlay[i].setEnabled(false);
				}
			}else {
				DB.Red_upload(RedCompanyMessage.this, new String[]{"Dnum"}, new String[]{"2"},
						TuanDuiShangHuActivity.num);
			}
		
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("�Ŷ����RedCompanyMessage:init()+"+e.getMessage());
		}	
		  }
    }
	
	
	String a = null;

	public void saveData() {
		try{
		String[] clomname = new String[] { "CompanyName", "LegalPerson",
				"ContactUser", "TelNumber", "MobileNumber", "ManageName",
				"Adress","PostCode","ManageScope", "Brand", "BrandTelNumber", 
				"Validity", "EngageYear", "TreatyNumber","CompanyId" ,"CompanyCity","CompanyArea","MerNumber"};
		String[] clomstr = new String[clomname.length];
		clomstr[0] = textArray[0].getText().toString();//��˾����
		clomstr[1] = textArray[1].getText().toString();//���˴���
		clomstr[2] = textArray[2].getText().toString();//��ϵ��
		clomstr[3] = textArray[3].getText().toString();//�̶��绰
		clomstr[4] = textArray[4].getText().toString();//�ֻ�����
		clomstr[5] = textArray[5].getText().toString();//��Ӫ�ŵ�����
		clomstr[6] = h1et7021.getText().toString();//ע���ַ
		clomstr[7] = textArray[7].getText().toString();//P�ʱ�
		clomstr[8] = textArray[8].getText().toString();//E��Ӫ��Χ
		clomstr[9] = textArray[9].getText().toString();//BƷ��
		clomstr[10] = textArray[10].getText().toString();//TƷ������
		clomstr[11] = textArray[11].getText().toString();//��Ч��
		clomstr[12] = textArray[12].getText().toString();//��Ӫ����
		clomstr[13] = textArray[13].getText().toString();//������
		if(RedBigArea.a==1){
			clomstr[14] = companyId;//��˾ID
		}else{
			clomstr[14] = "";
		}
		
		System.out.println("��˾"+companyId);
		//clomstr[15] = h1et702.getText().toString();//��˾���ڳ���ID
		clomstr[16] = h1et703.getText().toString();//��˾���ڳ�������
		DES des = new DES();
		
		//���빫˾���ڳ���id
		/*String id = DB.klQueryCity(this, "t_city", "where Name = '"
				+ des.jiaMi(clomstr[15]) + "'");*/
		clomstr[15] = redCompanyCityId;

		String id1 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[16])+ "'"+" and CityId = '"+des.jiaMi(clomstr[15])+"'");
		System.out.println("id1==="+id1);
		clomstr[16] = id1;
		clomstr[17] = RedNum;

		DB.Red_upload(RedCompanyMessage.this, clomname, clomstr,
				TuanDuiShangHuActivity.num);
		}catch(Exception e){
			techown.shanghu.https.Log.Instance().WriteLog("���RedCompanyMessage��SaveData����"+e.getMessage());
		}
	}



	class MyLis implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			try{
			int id = v.getId();
			Intent intent = new Intent();
			switch (id) 
			{
				case R.id.add_button:
					saveData();
					if (validate()) 
					{
						if(bs==1){
							saveData4();
							bs=0;
						}
						saveData();
						RedShopMessage.zhi = "addshop";//�Ƿ�����ŵ�
						Constant.isShopCreate = true;//�Ƿ��½�
						intent.setClass(RedCompanyMessage.this, RedShopMessage.class);
						RedCompanyMessage.this.startActivity(intent);
						RedCompanyMessage.this.finish();
						DB.Red_upload(RedCompanyMessage.this, new String[]{"IsNewShop"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
					break;
				case R.id.name_button:
					if(RedBigArea.a==1)
					{
						if (validate()) 
						{
							bs=1;
							saveData(); 
							intent.setClass(RedCompanyMessage.this, RedShopList.class);
							RedCompanyMessage.this.startActivity(intent);
							RedCompanyMessage.this.finish();
							overridePendingTransition(R.anim.leftin, R.anim.leftout);
						}
					}
					else
					{
						log.Toast(RedCompanyMessage.this, "û���ŵ꣬���½��ŵ꣡");
					}
					break;
			}
			}catch(Exception e){
				techown.shanghu.https.Log.Instance().WriteLog("���RedCompanyMessage�ఴť�����"+e.getMessage());

			}
			}
//		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(RedCompanyMessage.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

	boolean m = true;

	// ��֤�����ֶε��Ƿ�Ϊ��
	protected boolean validate() {
		if (textArray[0].getText().toString().equals("")) {
			log.Toast(RedCompanyMessage.this, "�����빫˾����");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			log.Toast(RedCompanyMessage.this, "�����뷨�˴���");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			log.Toast(RedCompanyMessage.this, "��������ϵ�ˣ�");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			log.Toast(RedCompanyMessage.this, "������̶��绰��");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			log.Toast(RedCompanyMessage.this, "�������ŵ��� ��");
			return false;
		}
		if (textArray[8].getText().toString().equals("")) {
			log.Toast(RedCompanyMessage.this, "�����뾭Ӫ��Χ ��");
			return false;
		}
		if (h1et7021.getText().toString().equals("")) {
			log.Toast(RedCompanyMessage.this, "������ע���ַ ��");
			return false;
		}
		if(h1et7.getText().toString().equals("")){
			log.Toast(RedCompanyMessage.this, "��˾����ʡ�ݲ���Ϊ�գ�");
			return false;
		}
		if(h1et702.getText().toString().equals("")){
			log.Toast(RedCompanyMessage.this, "��˾���ڳ��в���Ϊ�գ�");
			return false;
		}
		if(h1et703.getText().toString().equals("")){
			log.Toast(RedCompanyMessage.this, "��������������");
			return false;
		}
		if(textArray[13].getText().toString().equals(""))
		{
			log.Toast(RedCompanyMessage.this, "��ɨ��Э�������룡");
			return false;
		}
		return true;
	}
	public void saveData4() {
		String[] clomname = new String[] { "IsNewShop",
				"ShopId", "ShopLicense", "BusinessName", "BusinessNunber",
				"BusinessAdress", "ShopCode", "ShopTrade",
				"ShopGreens", "ShopDealer","Longitude","LatiTude","ShopUserName","ShopTelNumber","ShopMobileNumber" ,
				"StChannel","StIsReceiveBill","StValidity","StBillRate","IsNewPoster", "PosterID",
				"StParkSpace", "StBuinessHours", "Stzhuoyi", "Stmianji",
				"Stjiatong", "Sttaika", "Styilabao", "Stmengtie", "Sthaibao",
				"CompanyInfo", "ShopInfo","ZhaoPai","Other","ShopCity","ShopArea","mertype","whitenum"};
		String[] clomstr = new String[clomname.length];
		
		clomstr[0] = "";//�Ƿ��½��ŵ�
		clomstr[1] = "";//�ŵ�ID
		clomstr[2] = "";//�ŵ깤��ִ��
		clomstr[3] = "";//Ӫҵ����
		clomstr[4] = "";//Ӫҵ�绰
		clomstr[5] = "";//Ӫҵ��ַ
		clomstr[6] = "";//�ʱ�
		clomstr[7] = "";//��ҵ
		clomstr[8] = "";//��ϵ
		clomstr[9] = "";//��Ȧ
		clomstr[10] = "";//����
		clomstr[11] = "";//γ��
		clomstr[12] = "";//��ϵ��
		clomstr[13] = "";//�绰
		clomstr[14] = "";//�ֻ�
		clomstr[15] = "";//��չ����
		clomstr[16] = "";//�Ƿ��յ�
		clomstr[17] = "";//��Ч��
		clomstr[18] = "";//�յ�����
		clomstr[19] = "";//�Ƿ��½�����
		clomstr[20] = "";//�ŵ�ID
		clomstr[21] = "";//ͣ��λ
		clomstr[22] = "";//Ӫҵʱ��
		clomstr[23] = "";//����
		clomstr[24] = "";//���
		clomstr[25] = "";//��ͨ���
		clomstr[26] = "";//̨��
		clomstr[27] = "";//������
		clomstr[28] = "";//����
		clomstr[29] = "";//����
		clomstr[30] = "";//��˾����
		clomstr[31] = "";//�ŵ����
		clomstr[32] = "";//���Ʋ�
		clomstr[33] = "";//����
		clomstr[34] = "";//�ŵ���������
		clomstr[35] = "";//�ŵ�����������
		clomstr[36] = "";//�̻�����
		clomstr[37] = "";//���������
		

		DB.Red_upload(RedCompanyMessage.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
}
