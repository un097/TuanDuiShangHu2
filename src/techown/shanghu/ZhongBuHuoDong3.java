
package techown.shanghu;

import java.util.ArrayList;
import java.util.List;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.GetData;
import techown.shanghu.date.TArea;
import android.app.Activity;
import android.content.Context;
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
import android.widget.TextView;

/*
 * ��Լǩ���ŵ���Ϣ¼�루�½��ŵ꣩
 */
public class ZhongBuHuoDong3  extends Activity{
	EditDialog log=new EditDialog();
	DBUtil DB=new DBUtil();
	Button button2,button3,button_jwd;
	GetData getDasta=new GetData();
	String[] typCode =new String[2];
	int[] textIds = { R.id.h2edtext1, R.id.h2edtext2, R.id.h2edtext3, R.id.h2edtext4,
			R.id.h2edtext5, R.id.h2edtext6, R.id.h2edtext7, R.id.h2edtext8, R.id.h2edtext9,
			R.id.h2edtext10, R.id.h2edtext11, R.id.h2edtext12, R.id.h2edtext13, R.id.h2edtext14,
			R.id.h2edtext15,R.id.h2edtext16,R.id.h2edtext17,R.id.h2edtext18,R.id.h2edtext19,
			R.id.h2edtext222,R.id.h2edtext20,R.id.h2edtext21,R.id.sparetext1,R.id.sparetext2,
			R.id.sparetext3,R.id.sparetext4};
	public static TextView[] textArray;
	DES des = new DES();
	int[] linlayIds={R.id.h3leout1,R.id.h3leout2,R.id.h3leout3,R.id.h3leout4,
			R.id.h3leout5,R.id.h3leout6,R.id.h3leout7,R.id.h3leout8,
			R.id.h3leout9,R.id.h3leout10,R.id.h3leout11,R.id.h3leout12,
			R.id.h3leout13,R.id.h3leout14,R.id.h3leout15,R.id.h3leout16,R.id.h3leout17,
			R.id.h3leout18,R.id.h3leout19,R.id.h3leout20,R.id.h3leout21,
			R.id.spareout1,R.id.spareout2,R.id.spareout3,R.id.spareout4};
	public static LinearLayout[] linlay;
	String[]t_trading,t_category,t_childtyp,t_category1;
	
	static List<String> s;
	static List<String> s1;
	static List<String> s2;
	static List<String> s3;
	public  static Object[] name;
	List<TArea> tareaList1=null;
	List<TArea> tareaList2=null;
	String[][] typename;
	String[] bb;
	public static TextView h1et7,h1et702,h1et703,h1et7021;//703��,7021��ϸ��ַ
	public LinearLayout h1leout71,h1leout72,h1leout73,h1leout74;//73����74��ϸ��ַ
	String[] province;
	//String ccc;
	public String activeMenInfoCityId;

    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heyueqianding_3);
        ZhongBuHuoDong2.isMessageNew = "Y";
        getdata(ZhongBuHuoDong3.this);
        typename = DB.klQuery21(ZhongBuHuoDong3.this, "typeCode", "typeNum", "t_mertype");
        bb =new String[typename.length];
 		for (int i = 0; i < typename.length; i++) {
 			bb[i] = typename[i][0];
 			System.out.println(bb[i]);
 		}
      
		
//        String cityName = queryCityName(username);
//        final String[] t_trading1 = queryTradingName(cityName); 
        
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
//        button_jwd = (Button) findViewById(R.id.button_jwd);
        
        h1leout72 = (LinearLayout)findViewById(R.id.h1leout72);
		h1leout73 = (LinearLayout)findViewById(R.id.h1leout73);
		h1et702 = (TextView) findViewById(R.id.h1et702);
		h1et703 = (TextView) findViewById(R.id.h1et703);
        
       
        linlay=new LinearLayout[linlayIds.length];
		for(int i = 0; i<linlayIds.length;i++){
			linlay[i]=(LinearLayout)findViewById(linlayIds[i]);
		}
		
        textArray = new TextView[textIds.length];
		for (int i = 0; i < textIds.length; i++) {
			textArray[i] = (TextView) findViewById(textIds[i]);
		}
		textArray[15].setText("���´�");
		
		linlay[18].setEnabled(false);
		linlay[6].setEnabled(false);
		
        MyLis lis = new MyLis();

        button2.setOnClickListener(lis);
        button3.setOnClickListener(lis);
//        button_jwd.setOnClickListener(lis);
        
        
        MyDatabaseHelper myHelper = new MyDatabaseHelper(
				ZhongBuHuoDong3.this, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { TuanDuiShangHuActivity.num });
		while (cursor.moveToNext()) {
			try {
				textArray[0].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CompanyName"))));
				textArray[1].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StName"))));
				textArray[2].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stel"))));
				textArray[3].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StAdress"))));
				textArray[4].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StPostCode"))));
				textArray[5].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StHangye"))));
				textArray[6].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StCaixi"))));
				textArray[7].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StShangquan"))));
				textArray[8].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StNumber"))));
				textArray[9].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StClientNumber"))));
				textArray[10].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stlong"))));
				textArray[11].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Stlat"))));
				textArray[12].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StContact"))));
//				textArray[12].setText(cursor.getString(cursor
//						.getColumnIndex("StMobileNumber")));
				textArray[13].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StMobileNumber"))));
				textArray[15].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StChannel"))));//��չ����
				textArray[16].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StIsReceiveBill"))));//�Ƿ��յ�
				textArray[17].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StValidity"))));
				textArray[18].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StBillRate"))));
//				textArray[18].setText(cursor.getString(cursor
//						.getColumnIndex("IsNewStore")));
				textArray[14].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("StLicense"))));
				textArray[19].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("Zhihangye"))));
				textArray[20].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("XuanChuan"))));
				textArray[21].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("PiceXuanChuan"))));
				textArray[22].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeia"))));
				textArray[23].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeib"))));
				textArray[24].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeic"))));
				textArray[25].setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("ShopZhubeid"))));
//				String id=DB.klQueryCity1(this, "t_category", "where Id = '"+textArray[5].getText().toString()+"'");
//				textArray[5].setText(id);
//				
//				String id1=DB.klQueryCity1(this, "t_childtyp", "where Id = '"+textArray[6].getText().toString()+"'");
//				textArray[6].setText(id1);
//				String id2=DB.klQueryCity1(this, "t_trading", "where Id = '"+textArray[7].getText().toString()+"'");
//				textArray[7].setText(id2);
//
//				String id3=DB.klQueryCity1(this, "t_category", "where Id = '"+textArray[19].getText().toString()+"'");
//				textArray[19].setText(id3);

//				String id3=DB.klQuerytypecode2(this, "t_mertype", "where typeCode = '"+des.jiaMi(textArray[20].getText().toString())+"'");
//				textArray[20].setText(id3);
				
				if(textArray[15].getText().toString().equals("W")){
					textArray[15].setText("���");
				}else if(textArray[15].getText().toString().equals("D")){
					textArray[15].setText("���´�");
				}else if(textArray[15].getText().toString().equals("B")){
					textArray[15].setText("����");
				}else if(textArray[15].getText().toString().equals("C")){
					textArray[15].setText("������");
				}
				
				if(textArray[16].getText().toString().equals("Y")){
					textArray[16].setText("��");
				}else if(textArray[16].getText().toString().equals("N")){
					textArray[16].setText("��");
				}
				
				
				h1et703.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("AreaId"))));
				activeMenInfoCityId = des.jieMI(cursor.getString(cursor
						.getColumnIndex("CityId")));
				//h1et702.setText(activeMenInfoCityId);
				h1et702.setText(des.jieMI(cursor.getString(cursor
						.getColumnIndex("CityId"))));
			}catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�ZhongBuHuoDong3�쳣"+e.getMessage());
			}finally{
				if(cursor != null){
					cursor.close();
				}
				if(db != null){
					db.close();
				}
			}
			//��������
			String chengshiname = DB.queryCityname(ZhongBuHuoDong3.this, h1et702.getText().toString());
			h1et702.setText(chengshiname);
			String id=DB.klQueryCity1(this, "t_category", "where Id = '"+des.jiaMi(textArray[5].getText().toString())+"'");
			textArray[5].setText(id);
			
			String id1=DB.klQueryCity1(this, "t_childtyp", "where Id = '"+des.jiaMi(textArray[6].getText().toString())+"'");
			textArray[6].setText(id1);
			String id2=DB.klQueryCity1(this, "t_trading", "where Id = '"+des.jiaMi(textArray[7].getText().toString())+"'");
			textArray[7].setText(id2);

			String id3=DB.klQueryCity1(this, "t_category", "where Id = '"+des.jiaMi(textArray[19].getText().toString())+"'");
			textArray[19].setText(id3);
			
			String name=DB.klQueryCity1(this, "t_district", "where Id = '"
					+ des.jiaMi(h1et703.getText().toString()) + "'");
					h1et703.setText(name);
			String id4=DB.klQueryCity11(this, "t_mertype", "where typeName = '"
					+ des.jiaMi(textArray[20].getText().toString()) + "'");
			textArray[20].setText(id4);
		}
        
		
//		Context friendContext = null;
//		try {
//			friendContext = ZhongBuHuoDong3.this.createPackageContext("techown.login",
//					Context.CONTEXT_IGNORE_SECURITY);
//			MyDatabaseHelper myHelper1 = new MyDatabaseHelper(friendContext, "techown_login.db",
//						1);		
//			SQLiteDatabase sld = myHelper1.getWritableDatabase();
//			Cursor cur = sld.query("t_city",new String[]{"Name"},"Name like ?"
//					, new String[]{TuanDuiShangHuActivity.city+"%"}, null, null, null);
//			while (cur.moveToNext()) {
//				cur.moveToLast();
//				ccc = cur.getString(0);
//
//			}
//			cur.close();
//			sld.close();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		
		h1leout72.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(TuanDuiShangHuActivity.agencycityname!=null&&TuanDuiShangHuActivity.agencycityname.length!=0)
				{
					log.dialog(ZhongBuHuoDong3.this, h1et702, "��",
							TuanDuiShangHuActivity.agencycityname);
					h1et703.setText("");
					textArray[7].setText("");
				}
			}
		});
		h1leout73.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!h1et702.getText().toString().equals("")&&h1et702.getText().toString()!=null)
				{
					/*String[] str1 = getDasta.getCity11(ZhongBuHuoDong3.this,
							h1et702.getText().toString());*/
					
					/*
					 * ��¼ѡ��ĳ���Id
					 */
					activeMenInfoCityId = (String)TuanDuiShangHuActivity.agencyCityAndIdMap
							.get(h1et702.getText().toString());
					String[] str1 = getDasta.getCity12(ZhongBuHuoDong3.this,
							activeMenInfoCityId);
					if (str1.length > 0) {
						log.dialog(ZhongBuHuoDong3.this, h1et703, "��",
								str1);
						
					} else {
						h1et703.setText("��");
						h1et703.setEnabled(false);
					}
				}
				else
				{
					log.Toast(ZhongBuHuoDong3.this, "��ѡ����У�");
				}
			}
		});
        
//		textArray[0].setText(ZhongBuHuoDong3.textArray[0].getText().toString());        
//        linlay[0].setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				switch (arg1.getAction()) {
//				case MotionEvent.ACTION_UP:
//					log.Dialog(ZhongBuHuoDong3.this,linlay[0], textArray[0],R.id.h2edtext1);
//					break;
//				default:
//					break;
//				}
//
//				return false;
//			}
//		});     
        linlay[1].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this,linlay[1], textArray[1],R.id.h2edtext2,"������Ӫҵ����");
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
					log.etDialog(ZhongBuHuoDong3.this, textArray[2],R.id.h2edtext3,"������Ӫҵ�绰");
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
					log.Dialog(ZhongBuHuoDong3.this,linlay[3], textArray[3],R.id.h2edtext4,"������Ӫҵ��ַ");
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
					log.etDialog(ZhongBuHuoDong3.this,textArray[4],R.id.h2edtext5,"�������ʱ�");
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
					if(name!=null)
					{
//					get();
//					log.istwo=true;
//					log.num=4;
//					log.dialog(ZhongBuHuoDong3.this,textArray[5],"��ҵ",t_category);
					
					log.istwo = true;
					log.num = 4;
					// lxp
					log.dialog21(ZhongBuHuoDong3.this, textArray[5],"����ҵ", name);
					}
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
					log.dialog(ZhongBuHuoDong3.this,textArray[6],"��ϵ",t_childtyp);
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
					if(h1et702.getText().toString().length()>0)
					{
						//final String[] t_trading1 = queryTradingName(h1et702.getText().toString());
					
						/*
						 * ���ݳ���Id ��ѯ�ó����������Ȧ
						 */
						String[] t_trading1 = queryTradingNameByCityId(activeMenInfoCityId);
						if(t_trading1!=null&&t_trading1.length>0)
						{
							log.dialog(ZhongBuHuoDong3.this, textArray[7], "��Ȧ",
									t_trading1);
						}
						else
						{
							log.Toast(ZhongBuHuoDong3.this, "�ó���û����Ȧ��");
						}
					}
					else
					{
						log.Toast(ZhongBuHuoDong3.this, "����ѡ����У�");
					}
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
					log.Dialog(ZhongBuHuoDong3.this,linlay[8], textArray[8],R.id.h2edtext9,"�������̻����");
					
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
					log.Dialog(ZhongBuHuoDong3.this,linlay[9], textArray[9],R.id.h2edtext10,"�������ն˺�");
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
					log.etDialog(ZhongBuHuoDong3.this,textArray[10],R.id.h2edtext11,"�����뾭��");
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
					log.etDialog(ZhongBuHuoDong3.this,textArray[11],R.id.h2edtext12,"������γ��");
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
					log.Dialog(ZhongBuHuoDong3.this,linlay[12], textArray[12],R.id.h2edtext13,"��������ϵ��");
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
					log.etDialog(ZhongBuHuoDong3.this,textArray[13],R.id.h2edtext14,"�������ֻ���");
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
					log.etDialog(ZhongBuHuoDong3.this, textArray[14],R.id.h2edtext15,"�������ŵ깤��ִ��");
					break;
				default:
					break;
				}

				return false;
			}
		});
        final String[] province1={"���´�","���","����","������"};
        linlay[15].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
//					log.Dialog(ZhongBuHuoDong3.this,linlay[15], textArray[15],R.id.h2edtext16);
					log.dialog(ZhongBuHuoDong3.this,textArray[15],"��չ����",province1);
					break;
				default:
					break;
				}

				return false;
			}
		});
        final String[] province={"��","��"};
        linlay[16].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.istwo=true;
					log.num=3;
//					log.Dialog(ZhongBuHuoDong3.this,linlay[16], textArray[16],R.id.h2edtext17);
					log.dialog(ZhongBuHuoDong3.this,textArray[16],"�Ƿ��յ�",province);
					break;
				default:
					break;
				}

				return false;
			}
		});
       
        	
        
	        linlay[17].setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent arg1) {
					switch (arg1.getAction()) {
					case MotionEvent.ACTION_UP:
						DateDialog.showDatePicker(ZhongBuHuoDong3.this, textArray[17],R.id.h2edtext18);
						break;
					default:
						break;
					}
	
					return false;
				}
			});
        
        linlay[18].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.etDialog(ZhongBuHuoDong3.this,textArray[18],R.id.h2edtext19,"�������յ�����");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[19].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.dialog(ZhongBuHuoDong3.this, textArray[20],
							"����", bb);
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[20].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this, linlay[20],textArray[21],
							R.id.h2edtext21,"��������������");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[21].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this, linlay[21],textArray[22],
							R.id.sparetext1,"�������̻���ע1");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[22].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this, linlay[22],textArray[23],
							R.id.sparetext2,"�������̻���ע2");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[23].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this, linlay[23],textArray[24],
							R.id.sparetext3,"�������̻���ע3");
					break;
				default:
					break;
				}

				return false;
			}
		});
        linlay[24].setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_UP:
					log.Dialog(ZhongBuHuoDong3.this, linlay[24],textArray[25],
							R.id.sparetext4,"�������̻���ע4");
					break;
				default:
					break;
				}

				return false;
			}
		});
        new Thread(){
			 public void run(){
				 get();
			 }
		}.start();

    }
    class MyLis implements OnClickListener{

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent intent = new Intent();
			switch(id){
				case R.id.button3:
					
//					saveLogic();
					
					
					if(validate()){
						saveData();
						intent.setClass(ZhongBuHuoDong3.this, HeYueQianDing_44.class);
						ZhongBuHuoDong3.this.startActivity(intent);
						ZhongBuHuoDong3.this.finish();
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
					}
				break;
				case R.id.button2:
			
							intent.setClass(ZhongBuHuoDong3.this, ZhongBuHuoDong1.class);
							ZhongBuHuoDong3.this.startActivity(intent);
							ZhongBuHuoDong3.this.finish();
							overridePendingTransition(R.anim.rightin, R.anim.rightout);
				break;
			}
		}
    }
    
    public String zihangye(String ziname)
	{
		String ZiId = "";
		for (int i = 0; i < EditDialog.list.size(); i++) {
			if(EditDialog.list.get(i).equals(ziname))
			{
				ZiId = EditDialog.listId.get(i);
			}
		}
		return ZiId;
	}
	
	public String zhuhangye(String zhuname)
	{
		String ZhuId = "";
		for (int i = 0; i < s1.size(); i++) {
			if(s1.get(i).equals(zhuname))
			{
				ZhuId = s.get(i);
			}
		}
		return ZhuId;
	}
	
    public void saveData() {
		String[] clomname = new String[] { "StName",
				"Stel", "StAdress", "StPostCode", "StHangye",
				"StCaixi", "StShangquan", "StNumber", "StClientNumber", "Stlong",
				"Stlat", "StContact","StMobileNumber","StChannel","StIsReceiveBill",
				"StValidity","StBillRate" ,"IsNewStore","StLicense","Zhihangye","AreaId",
				"XuanChuan","PiceXuanChuan","CityId","ShopZhubeia","ShopZhubeib","ShopZhubeic","ShopZhubeid"};
		String[] clomstr = new String[clomname.length];
		
		clomstr[0] = textArray[1].getText().toString();
		clomstr[1] = textArray[2].getText().toString();
		clomstr[2] = textArray[3].getText().toString();
		clomstr[3] = textArray[4].getText().toString();
		String Zhuhangye = zhuhangye(textArray[5].getText().toString());
		System.out.println("����ҵ��"+Zhuhangye);
		clomstr[4] = Zhuhangye;
		clomstr[5] = textArray[6].getText().toString();
		clomstr[6] = textArray[7].getText().toString();
		clomstr[7] = textArray[8].getText().toString();
		clomstr[8] = textArray[9].getText().toString();
		clomstr[9] = textArray[10].getText().toString();
		clomstr[10] = textArray[11].getText().toString();
		clomstr[11] = textArray[12].getText().toString();
		
		clomstr[12] = textArray[13].getText().toString();
		clomstr[13] = textArray[15].getText().toString();
		clomstr[14] = textArray[16].getText().toString();
		clomstr[15] = textArray[17].getText().toString();
		clomstr[16] = textArray[18].getText().toString();
		clomstr[17] ="Y";
		clomstr[18] =textArray[14].getText().toString();
		String zihangyeid = zihangye(textArray[19].getText().toString());
		System.out.println("����ҵ��"+zihangyeid);
		clomstr[19] = zihangyeid;//����ҵ
		clomstr[20] = h1et703.getText().toString();
		clomstr[21] = textArray[20].getText().toString();//�̻�����
		clomstr[22] = textArray[21].getText().toString();//���������
		//String cityid = DB.queryCityid(ZhongBuHuoDong3.this, h1et702.getText().toString());
		clomstr[23] = activeMenInfoCityId;//����
		clomstr[24] = textArray[22].getText().toString();//�̻�ע��1
		clomstr[25] = textArray[23].getText().toString();//�̻�ע��2
		clomstr[26] = textArray[24].getText().toString();//�̻�ע��3
		clomstr[27] = textArray[25].getText().toString();//�̻�ע��4
//		String id=DB.klQueryCity(this, "t_category", "where Name = '"+clomstr[4]+"'");
//		clomstr[4]=id;
		
		String id1=DB.klQueryCity(this, "t_childtyp", "where Name = '"+des.jiaMi(clomstr[5])+"'");
		clomstr[5]=id1;
		String id2=DB.klQueryCity(this, "t_trading", "where Name = '"+des.jiaMi(clomstr[6])+"' and CityId = '"+des.jiaMi(clomstr[23])+"'");
		clomstr[6]=id2;
		System.out.println("��Ȧ==="+clomstr[6]);
		String id3=DB.klQuerytypecode(this, "t_mertype", "where typeCode = '"+des.jiaMi(clomstr[21])+"'");
		clomstr[21]=id3;
		
		String id4 = null;
		if(clomstr[13].equals("���")){
			id4="W";
		}else if(clomstr[13].equals("���´�")){
			id4="D";
		}else if(clomstr[13].equals("����")){
			id4="B";
		}else if(clomstr[13].equals("������")){
			id4="C";
		}
		clomstr[13]=id4;
		
		String id5=null;
		if(clomstr[14].equals("��")){
			id5="Y";
		}else if(clomstr[14].equals("��")){
			id5="N";
		}else{
			id5="";
		}
		clomstr[14]=id5;
		String id9 = DB.klQueryCity(this, "t_district", "where Name = '"
				+ des.jiaMi(clomstr[20]) + "'"+" and CityId = '"+des.jiaMi(clomstr[23])+"'");
		clomstr[20] = id9;
		DB.upload(ZhongBuHuoDong3.this, clomname, clomstr, TuanDuiShangHuActivity.num);
	}
    
 // ���浱ǰ����
	public void saveLogic() {
		String[] str = new String[19];
		for (int i = 0; i < str.length; i++) {
			str[i] = textArray[i].getText().toString();
		}
		TuanDuiShangHuActivity.activityStr.put("heyueqianding3", str);
	}

	// �õ���������
	public void getLogic() {
		if (TuanDuiShangHuActivity.activityStr.containsKey("heyueqianding3")) {
			String[] str = TuanDuiShangHuActivity.activityStr
					.get("heyueqianding3");
			for (int i = 0; i < str.length; i++) {
				textArray[i].setText(str[i]);
			}
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(ZhongBuHuoDong3.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}
	
	boolean m = true;

	// ��֤�����ֶε��Ƿ�Ϊ��
	protected boolean validate() {
		if (textArray[1].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "������Ӫҵ���ƣ�");
			return false;
		}
		if (textArray[2].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "������Ӫҵ�绰��");
			return false;
		}
		if (textArray[3].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "������Ӫҵ��ַ��");
			return false;
		}
		if (textArray[4].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "�������ʱ࣡");
			return false;
		}
		if (textArray[5].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "��������ҵ ��");
			return false;
		}
		if (textArray[19].getText().toString().equals("")) {
			log.Toast(ZhongBuHuoDong3.this, "����������ҵ ��");
			return false;
		}
		if (textArray[7].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "��������Ȧ��");
			return false;
		}
		if (h1et702.getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "��ѡ���ŵ����ڳ��� ��");
			return false;
		}
		if(h1et703.getText().toString().equals("")){
			
			log.Toast(ZhongBuHuoDong3.this, "��ѡ����������");
			return false;
		}
		if (textArray[15].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "��������չ���� ��");
			return false;
		}
		if (textArray[16].getText().toString().equals("��")&&textArray[18].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "�������յ����� ��");
			return false;
		}
		if (textArray[20].getText().toString().equals("")||textArray[20].getText().toString()==null) {
			
			log.Toast(ZhongBuHuoDong3.this, "�������̻����ͣ�");
			return false;
		}
		if (textArray[21].getText().toString().equals("")) {
			
			log.Toast(ZhongBuHuoDong3.this, "�������������ţ�");
			return false;
		}
		typCode[1]=textArray[20].getText().toString();
		String id3=DB.klQuerytypecode(ZhongBuHuoDong3.this, "t_mertype", "where typeCode = '"+des.jiaMi(typCode[1])+"'");
		typCode[1]=id3;
		String name = null;
		if(textArray[21].getText().toString().length()>=typCode[1].length()){
			name=textArray[21].getText().toString().substring(0, typCode[1].length());
		}else{
			log.Toast(ZhongBuHuoDong3.this, "������������̻����Ͳ�����");
			return false;

		}
		String tname=typCode[1];
		System.out.println(name+" "+tname);
		if(!name.equals(tname)){
			log.Toast(ZhongBuHuoDong3.this, "������������̻����Ͳ�����");
			return false;
		}
		return true;
	}
	
	 public String queryCityName(String username){
	    	String cityName = null;
	    	SQLiteDatabase db = null;
	    	Cursor cursor = null;
	    	try {
				Context friendContext = createPackageContext("techown.login",
						Context.CONTEXT_IGNORE_SECURITY);
				MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
						Constant.loginDBVer);
				db = myHelper.getWritableDatabase();
				cursor = db.rawQuery("select city from EntityUser where username = ?", new String[]{username});
				if(cursor.getCount() == 1){
					cursor.moveToLast();
					cityName = cursor.getString(0);
				}
				cursor.close();
				db.close();
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�ZhongBuHuoDong3+queryCityName����"+e.getMessage());	
			}finally{
				if(cursor != null){
					cursor.close();
				}
				if(db != null){
					db.close();
				}
			}
	    	
	    	return cityName;
	    }
		
	 	/*
		 * ���ݳ���Id��ѯ�ó��������е���Ȧ
		 */
		public String[] queryTradingNameByCityId(String cityid) {

			String[] tradingName = null;
			SQLiteDatabase db = null;
			Cursor cursor = null;
			//String cityId = null;
			Cursor tmpCur = null;
			try {
				Context friendContext = createPackageContext("techown.login",
						Context.CONTEXT_IGNORE_SECURITY);
				MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
						"techown_login.db", Constant.loginDBVer);
				db = myHelper.getWritableDatabase();
				
				tmpCur = db.rawQuery("select Name from t_trading where CityId = ?",
						new String[] { des.jiaMi(cityid) });
				
				tradingName = new String[tmpCur.getCount()];
				if (tmpCur.getCount() > 0) {
					int idx = 0;
					while (tmpCur.moveToNext()) {
						tradingName[idx++] = des.jieMI(tmpCur.getString(0));
					}
				}

			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"�Ŷӣ�HeYueQianDing_3+queryTradingName����" + e.getMessage());
			} finally {
				if (cursor != null) {
					cursor.close();
				}
				if (tmpCur != null) {
					tmpCur.close();
				}
				if (db != null) {
					db.close();
					;
				}
			}

			return tradingName;
		}
	
	 public String[] queryTradingName(String cityName){
			
			String[] tradingName = null;
			SQLiteDatabase db = null;
	    	Cursor cursor = null;
	    	String cityId = null;
	    	Cursor tmpCur = null;
			try{
				Context friendContext = createPackageContext("techown.login",
						Context.CONTEXT_IGNORE_SECURITY);
				MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
						Constant.loginDBVer);
				db = myHelper.getWritableDatabase();
				
				cursor = db.rawQuery("select Id from t_city where Name = ?", new String[]{des.jiaMi(cityName)});
				if(cursor.getCount() > 0){
					cursor.moveToLast();
					cityId = des.jieMI(cursor.getString(0));
					
//					cursor = db.rawQuery("select Name from t_trading where CityId = ?", new String[]{cityId});
//					tradingName = new String[cursor.getCount()];
//					if(cursor.getCount() > 0){
//						int idx = 0;
//						while(cursor.moveToNext()){
//							tradingName[idx++] = cursor.getString(0);
//						}
//					}
					
				}
				
				tmpCur = db.rawQuery(
						"select Name from t_trading where CityId = ?",
						new String[] { des.jiaMi(cityId) });
//				cursor = db.rawQuery(
//						"select Name from t_trading where CityId = ?",
//						new String[] { cityId });
						tradingName = new String[tmpCur.getCount()];
						if (tmpCur.getCount() > 0) {
							int idx = 0;
							while (tmpCur.moveToNext()) {
								tradingName[idx++] = des.jieMI(tmpCur.getString(0));
							}
						}
			}
			catch(Exception e){
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�ZhongBuHuoDong3+queryTradingName����"+e.getMessage());	
			}finally{
				if(cursor != null){
					cursor.close();
				}if(tmpCur != null){
					tmpCur.close();
				}
				if(db != null){
					db.close();
				}
			}
			
			return tradingName;
		}
	 
	
		
		public  String username ;
		public  String imei;
		public  String indenty;
		public  String chinaname;
		public  String city;
		public  String prov;
		
		public void getdata(Context con)
		{		
			Context friendContext = null;
			String[] str = null;
			Cursor cur = null;
		 	SQLiteDatabase sld =null;
			try {
				friendContext = con.createPackageContext("techown.login",
						Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);		
			str = new String[6];
			sld = myHelper.getWritableDatabase();
			cur = sld.query("EntityUser",new String[]{"username","imei","identity","chinaname","city","prov"},null
					,null, null, null, null);
			while (cur.moveToNext()) {
				for (int i = 0; i <6; i++) {
					if (cur.getString(i) != null) {
						str[i] = cur.getString(i);
					}
				}

			}
			
			
			username=str[0];
			imei=str[1];
			indenty=str[2];
			chinaname=str[3];
			city=str[4].substring(0,2);
			prov=str[5];
			}
			catch(Exception e)
			{
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�ZhongBuHuoDong3+getdata����"+e.getMessage());	
			}
			finally{
				
				if(cur!=null){
					cur.close();
				}
				if(sld!=null){
					sld.close();
				}
				
			}
		}		
	private void get()
	{
		  List<TArea> tareaList=DB.klQuery(ZhongBuHuoDong3.this,"t_category");
	        t_category=new String[tareaList.size()];
	        for(int i=0;i<tareaList.size();i++){
	        	t_category[i]=tareaList.get(i).name;
	        	t_category1 = new String[tareaList.size()];
	        }
	        tareaList1=DB.klQuery(ZhongBuHuoDong3.this,"t_childtyp");
	        t_childtyp=new String[tareaList1.size()];
	        for(int i=0;i<tareaList1.size();i++){
	        	t_childtyp[i]=tareaList1.get(i).name;
	        	
	        }
//	        tareaList2=DB.klQuery(ZhongBuHuoDong3.this,"t_trading");
//	        t_trading=new String[tareaList2.size()];
//	        for(int i=0;i<tareaList2.size();i++){
//	        	t_trading[i]=tareaList2.get(i).name;
//	        	
//	        }
	        

	        s = new ArrayList<String>();
			s1 = new ArrayList<String>();
			s2 = new ArrayList<String>();
			s3 = new ArrayList<String>();

			for (int i = 0; i < tareaList.size(); i++) {

				t_category[i] = tareaList.get(i).id;
				t_category1[i] = tareaList.get(i).name;
				if (t_category[i].length() == 4) {

					
					
					
				
					
					s.add(t_category[i]); // ����Ϊ4��ID
					s1.add(t_category1[i]);// ����Ϊ4��Name

				} else {
					s2.add(t_category[i]);
					s3.add(t_category1[i]);
				}
	        
			}
	        
			name = s1.toArray();
	}
}
