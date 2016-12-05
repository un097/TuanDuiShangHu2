package techown.shanghu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetData;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * �¼��-----��˾��Ϣ¼��
 */
public class ZhongBuHuoDong1 extends Activity{
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	//static String chengshi;
	String[] province;
	public  String activeCompanyCityId;//��ҳ��ĳ���Id

	
	//private Button button1;
	Button button2,button3,add_button,name_button;
	String namemerid;
	private TextView z1edtext1,z1edtext2,z1edtext3,z1edtext4,z1edtext5,z1edtext6,
					 z1edtext8,z1edtext9,z1edtext10,z1edtext11,z1edtext12,
					 z1edtext13;
	public static TextView z1edtext702,z1edtext7;
	GetData getDasta=new GetData();
	int[] textIds = { R.id.z1edtext1, R.id.z1edtext2, R.id.z1edtext3, R.id.z1edtext4,
			R.id.z1edtext5, R.id.z1edtext6, R.id.z1edtext7, R.id.z1edtext8, R.id.z1edtext9,
			R.id.z1edtext10,R.id.z1edtext11,R.id.z1edtext12,R.id.z1edtext13}; 
	public static TextView[] textArray;
	
	int[] linlayIds={R.id.z1leout1,R.id.z1leout2,R.id.z1leout3,R.id.z1leout4,
			R.id.z1leout5,R.id.z1leout6,R.id.z1leout7,R.id.z1leout8,
			R.id.z1leout9,R.id.z1leout10,R.id.z1leout11,R.id.z1leout12,
			R.id.z1leout13};
	public LinearLayout[] linlay;
	String[][] tempstr2;
	ListView mylist;   
	List<HashMap<String,String>>data = new ArrayList<HashMap<String,String>>();
	DES des = new DES();
	public static String requeststr1;
	public static String requeststr2;
	public static String merName;
	public static String merId;
	public static String summerId;
	public static String CompanyLicense1;
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703��,7021��ϸ��ַ
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73����74��ϸ��ַ
	public static int bs;
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhongbuhuodong1); 
//        panel=new Panel(this);
        //button1 = (Button) findViewById(R.id.button1);
//        province = DBUtil.klQuery(ZhongBuHuoDong1.this, "province", "province");
     
        CompanyLicense1=null;
        
        File dir = new File(TuanDuiShangHuActivity.path);
		dir.mkdirs();
        
        button2 = (Button) findViewById(R.id.button2);
//        button3 = (Button) findViewById(R.id.button3);
        add_button = (Button) findViewById(R.id.add_button);
        name_button= (Button) findViewById(R.id.name_button);
       
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
//		getLogic();
		
		
		z1edtext1 = (TextView) findViewById(R.id.z1edtext1);
		z1edtext2 = (TextView) findViewById(R.id.z1edtext2);
		z1edtext3 = (TextView) findViewById(R.id.z1edtext3);
		z1edtext4 = (TextView) findViewById(R.id.z1edtext4);
		z1edtext5 = (TextView) findViewById(R.id.z1edtext5);
		z1edtext6 = (TextView) findViewById(R.id.z1edtext6);
		z1edtext7 = (TextView) findViewById(R.id.z1edtext7);
		z1edtext8 = (TextView) findViewById(R.id.z1edtext8);
		z1edtext9 = (TextView) findViewById(R.id.z1edtext9);
		z1edtext10 = (TextView) findViewById(R.id.z1edtext10);
		z1edtext11 = (TextView) findViewById(R.id.z1edtext11);
		z1edtext12 = (TextView) findViewById(R.id.z1edtext12);
		z1edtext13 = (TextView) findViewById(R.id.z1edtext13);
		//z1edtext702= (TextView) findViewById(R.id.z1edtext702);
		




		

		h1et7=(TextView) findViewById(R.id.h1et7);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
		h1et7021 = (TextView) findViewById(R.id.h1et7021);
		
		h1leout71 = (LinearLayout)findViewById(R.id.h1leout71);
		h1leout72 = (LinearLayout)findViewById(R.id.h1leout72);
		h1leout73 = (LinearLayout)findViewById(R.id.h1leout73);
		h1leout74 = (LinearLayout)findViewById(R.id.h1leout74);

		MyLis lis = new MyLis();

        //button1.setOnClickListener(lis);
        button2.setOnClickListener(lis);
//        button3.setOnClickListener(lis);
        add_button.setOnClickListener(lis);
        name_button.setOnClickListener(lis);
        
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				ZhongBuHuoDong1.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
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
						.getColumnIndex("EngageName"))));
				textArray[6].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Adress"))));
				textArray[7].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("PostCode"))));
				textArray[8].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("EngageScope"))));
				textArray[9].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Brand"))));
				textArray[10].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("BrandTelNumber"))));
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Validity"))));
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("EngageYear"))));
				h1et702.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Gcityid"))));
				/*
				 * ��¼�����ݱ��еĲ�ѯ���ĳ���Id
				 */
				activeCompanyCityId = des.jieMI(cursor.getString(cursor
						.getColumnIndex("Gcityid")));
				

				h1et703.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Gdistrictid"))));
				companyId=des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyId")));
				
				String IsNewCompany=des.jieMI(cursor.getString(cursor
						.getColumnIndex("IsNewCompany")));
				if(IsNewCompany.equals("N")){
					h1leout73.setEnabled(false);
					for(int i = 0; i<linlayIds.length;i++){
						linlay[i].setEnabled(false);
		    		}
				}
				
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�ZhongBuHuoDong1�쳣"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}
				if(db != null){
					db.close();
				}
			}
			//��������
			String cityname=DB.klQueryCity1(this, "t_city", "where Id = '"
					+ des.jiaMi(h1et702.getText().toString()) + "'");
			h1et702.setText(cityname);
			//ʡ������
			String shengid = DB.queryShengname(ZhongBuHuoDong1.this, h1et702.getText().toString());
			String shengname = DB.queryProvinceName(ZhongBuHuoDong1.this, shengid);
			h1et7.setText(shengname);
			//��������
			String name=DB.klQueryCity1(this, "t_district", "where Id = '"
					+ des.jiaMi(h1et703.getText().toString()) + "'");
					h1et703.setText(name);
		}

        
		init();
		
		

		String provinceid = DB.queryShengname(ZhongBuHuoDong1.this, ZhongBuChaXun4.chengshi);
		String ProvinceName = DB.queryProvinceName(ZhongBuHuoDong1.this, provinceid);
		h1et7.setText(ProvinceName);
		h1et702.setText(ZhongBuChaXun4.chengshi);
		/*
		 * ��¼�Ӵ��������ҳ������ĳ���Id
		 */
		activeCompanyCityId = ZhongBuChaXun4.active_Area_CityId;
		
		h1leout73.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				 if(h1et7.getText().toString().equals("")){
						h1et703.setEnabled(false);
						;
					}
				 else{
				if(h1et702.getText().toString().equals("��")){
					h1et703.setText("��");
					h1et703.setEnabled(false);
		}
				else {
				/*String[] str1 = getDasta.getCity11(ZhongBuHuoDong1.this,
						h1et702.getText().toString());*/
				
				String[] str1 = getDasta.getCity12(ZhongBuHuoDong1.this,
						activeCompanyCityId);
					
				if (str1.length > 0) {
					log.dialog(ZhongBuHuoDong1.this, h1et703, "��",
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
		
		
		
        linlay[0].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					log.Dialog(ZhongBuHuoDong1.this, linlay[0],
							textArray[0], R.id.z1edtext1,"�����빫˾��");
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
					log.Dialog(ZhongBuHuoDong1.this, linlay[1],
							textArray[1], R.id.z1edtext2,"�����뷨�˴���");
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
					log.Dialog(ZhongBuHuoDong1.this, linlay[2],
							textArray[2], R.id.z1edtext3,"��������ϵ��");
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
					log.etDialog(ZhongBuHuoDong1.this,
							textArray[3], R.id.z1edtext4,"������̶��绰");
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
					log.etDialog(ZhongBuHuoDong1.this,
							textArray[4], R.id.z1edtext5,"�������ֻ�����");
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
					log.Dialog(ZhongBuHuoDong1.this, linlay[5],
							textArray[5], R.id.z1edtext6,"�����뾭Ӫ�ŵ�����");
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
					log.Dialog(ZhongBuHuoDong1.this, linlay[6],
							textArray[6], R.id.z1edtext7,"������ע���ַ");
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
					log.etDialog(ZhongBuHuoDong1.this, textArray[7],
							R.id.z1edtext8,"�������ʱ�");
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
					log.Dialog(ZhongBuHuoDong1.this, linlay[8],
							textArray[8], R.id.z1edtext9,"�����뾭Ӫ��Χ");
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
					log.Dialog(ZhongBuHuoDong1.this, linlay[9],
							textArray[9], R.id.z1edtext10,"������Ʒ��");
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
					log.etDialog(ZhongBuHuoDong1.this,
							textArray[10], R.id.z1edtext11,"������Ʒ������");
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
					DateDialog.showDatePicker(ZhongBuHuoDong1.this,
							textArray[11], R.id.z1edtext12);
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
					log.etDialog(ZhongBuHuoDong1.this,
							textArray[12], R.id.z1edtext13,"�����뾭Ӫ����");
					break;
				default:
					break;
				}

				return false;
			}
		});

    }
    public String companyId;
    String[] tempstr1=new String[17];
    public void init()
    {
    	try
		{
    		JSONObject demoJson1 = new JSONObject(requeststr1);
			if(demoJson1.getString("rspCode").equals("00"))
			{
				tempstr1[0]=demoJson1.getString("name");
				tempstr1[1]=demoJson1.getString("companyId");
				companyId=demoJson1.getString("companyId");
				tempstr1[2]=demoJson1.getString("legalPerson");
				tempstr1[3]=demoJson1.getString("contactPerson");
				tempstr1[4]=demoJson1.getString("contactTel");//contactTel	��ϵ�绰
				tempstr1[5]=demoJson1.getString("handPhone");//	handPhone	�ֻ�			
				tempstr1[6]=demoJson1.getString("busiName");
				tempstr1[7]=demoJson1.getString("address");
				tempstr1[8]=demoJson1.getString("busi");
				tempstr1[9]=demoJson1.getString("brand");
				tempstr1[10]=demoJson1.getString("brandHotLine");
				tempstr1[11]=demoJson1.getString("zip");
				tempstr1[12]=demoJson1.getString("validDate");
				tempstr1[13]=demoJson1.getString("busiYear");
				tempstr1[14]=demoJson1.getString("province");//ʡ
				tempstr1[15]=demoJson1.getString("city");//��
				/**
				 * ��¼��ҳ��ĳ���Id
				 */
				activeCompanyCityId = tempstr1[15];

				tempstr1[16]=demoJson1.getString("area");//��
				
				String id = DB.klQueryCity1(this, "t_province", "where Id = '"
						+ des.jiaMi(tempstr1[14]) + "'");
				tempstr1[14] = id;

				String id1 = DB.klQueryCity1(this, "t_city", "where Id = '"
						+ des.jiaMi(tempstr1[15]) + "'");
				tempstr1[15] = id1;
				
				String id2 = DB.klQueryCity1(this, "t_district", "where Id = '"
						+ des.jiaMi(tempstr1[16]) + "'");
				tempstr1[16] = id2;
				
				z1edtext1.setText(tempstr1[0]);//��˾����
	    		z1edtext2.setText(tempstr1[2]);//���˴���
	    		z1edtext3.setText(tempstr1[3]);//��ϵ��
	    		z1edtext4.setText(tempstr1[4]);//�̶��绰
	    		z1edtext5.setText(tempstr1[5]);//�ֻ�����
	    		z1edtext6.setText(tempstr1[6]);//��Ӫ�ŵ�����
	    		z1edtext7.setText(tempstr1[7]);//ע���ַ
	    		z1edtext8.setText(tempstr1[11]);//�ʱ�
	    		z1edtext9.setText(tempstr1[8]);//��Ӫ��Χ
	    		z1edtext10.setText(tempstr1[9]);//Ʒ��
	    		z1edtext11.setText(tempstr1[10]);//Ʒ������
	    		z1edtext12.setText(tempstr1[12]);//��Ч��
	    		z1edtext13.setText(tempstr1[13]);//��Ӫ����
	    		h1et7.setText(id);
	    		h1et702.setText(id1);
	    		h1et703.setText(id2);
	    		for(int i = 0; i<linlayIds.length;i++){
					linlay[i].setEnabled(false);
	    		}
	    		h1leout73.setEnabled(false);
	    		
			}
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�ZhongBuHuoDong1�쳣init"+e.getMessage());
		}	
    }
    
    
    
    
       
    public void saveData() {
		String[] clomname = new String[] { "CompanyName", "LegalPerson",
				"ContactUser", "TelNumber", "MobileNumber", "EngageName",
				"Adress","PostCode", "EngageScope", "Brand", "BrandTelNumber", 
				"Validity", "EngageYear" ,"meiId","Gcityid","Gdistrictid","CompanyId"};
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
	
		clomstr[13] = "";
		clomstr[14] = h1et702.getText().toString();
		
		clomstr[15] = h1et703.getText().toString();
		if(ZhongBuChaXun4.a==1){
			clomstr[16] = companyId;
		}else{
			clomstr[16] = "";
		}
		/*String id = DB.klQueryCity(this, "t_city", "where Name = '"
				+ des.jiaMi(clomstr[14]) + "'");*/
		clomstr[14] = activeCompanyCityId;;

		String id1 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[15]) + "'"+" and CityId = '"+des.jiaMi(clomstr[14])+"'");
		System.out.println("id1==="+id1);
		clomstr[15] = id1;
		
	
		
		DB.upload(ZhongBuHuoDong1.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}


	
    class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();

			switch(id){

				case R.id.button2:
					ZhongBuChaXun4.i = 1;
					CompanyLicense1=ZhongBuChaXun4.CompanyLicense;
					intent.setClass(ZhongBuHuoDong1.this, ZhongBuChaXun4.class);
					ZhongBuHuoDong1.this.startActivity(intent);
					ZhongBuHuoDong1.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;

				case R.id.add_button:
					if (validate()) {
						
						//�ж��Ƿ��������
						if(bs==1){
							saveData4();
							bs=0;
						}
						
						saveData(); 
						intent.setClass(ZhongBuHuoDong1.this, ZhongBuHuoDong3.class);
						ZhongBuHuoDong1.this.startActivity(intent);
						ZhongBuHuoDong1.this.finish();
						DB.upload(ZhongBuHuoDong1.this, new String[]{"IsNewStore"}, new String[]{"Y"}, TuanDuiShangHuActivity.num);
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
					break;
					
				case R.id.name_button:
					if(ZhongBuChaXun4.a==1){
						bs=1;
						
						saveData(); 
						intent.setClass(ZhongBuHuoDong1.this, MendianList.class);
						ZhongBuHuoDong1.this.startActivity(intent);
						ZhongBuHuoDong1.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}else{
						log.Toast(ZhongBuHuoDong1.this, "û���ŵ꣬���½��ŵ꣡");
					}
					
					break;
				}  
			
		}
    }
    
    
 // ��֤�����ֶε��Ƿ�Ϊ��
	protected boolean validate() {
		if (textArray[0].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong1.this, "�����빫˾����");
			return false;
		}
		if (textArray[1].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong1.this, "�����뷨�˴���");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong1.this, "�������ŵ��� ��");
			return false;
		}
		if (textArray[6].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong1.this, "������ע���ַ ��");
			return false;
		}
		if(h1et703.getText().toString().equals("")){
			log.Toast(ZhongBuHuoDong1.this, "��������������");
			return false;
		}
		return true;
	}

	public void saveData4() {
		String[] clomname = new String[] { "StName",
				"Stel", "StAdress", "StPostCode", "StHangye",
				"StCaixi", "StShangquan", "StNumber", "StClientNumber", "Stlong",
				"Stlat", "StContact","StMobileNumber","StChannel","StIsReceiveBill","StValidity","StBillRate" ,
				"IsNewStore","StLicense","Zhihangye","AreaId","StId","StParkSpace", "StBuinessHours",
				"Stzhuoyi", "Stmianji", "Stjiatong", "Sttaika",
				"Styilabao", "Stmengtie", "Sthaibao", "CompanyInfo", "BrandInfo",
				"StoreInfo", "ZhaoPai","AvgExpense","Other"};
		String[] clomstr = new String[clomname.length];
		
		clomstr[0] = "";
		clomstr[1] = "";
		clomstr[2] = "";
		clomstr[3] = "";
		clomstr[4] = "";
		clomstr[5] = "";
		clomstr[6] = "";
		clomstr[7] = "";
		clomstr[8] = "";
		clomstr[9] = "";
		clomstr[10] = "";
		clomstr[11] = "";
		clomstr[12] = "";
		clomstr[13] = "";
		clomstr[14] = "";
		clomstr[15] = "";
		clomstr[16] = "";
		clomstr[17] = "";
		clomstr[18] = "";
		clomstr[19] = "";
		clomstr[20] = "";
		clomstr[21] = "";
		clomstr[22] = "";
		clomstr[23] = "";
		clomstr[24] = "";
		clomstr[25] = "";
		clomstr[26] = "";
		clomstr[27] = "";
		clomstr[28] = "";
		clomstr[29] = "";
		clomstr[30] = "";
		clomstr[31] = "";
		clomstr[32] = "";
		clomstr[33] = "";
		clomstr[34] = "";
		clomstr[35] = "";
		

		DB.upload(ZhongBuHuoDong1.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(ZhongBuHuoDong1.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}