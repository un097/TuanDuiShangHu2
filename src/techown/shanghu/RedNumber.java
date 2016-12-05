package techown.shanghu;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.https.HttpConnection2;
import techown.shanghu.photo.RedTakePhotoActivity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//������--�̻����¼��
public class RedNumber extends Activity {
	/** Called when the activity is first created. */

	static String selectno;
	Button addbtn, toppege, bottompege;
	LinearLayout table,shanghulayout;
	TextView shanghuno;
	EditDialog ed = new EditDialog();
	private Dialog mDialog;
	DBUtil DB=new DBUtil();
	DES des = new DES();
	List<TextView> ls=new ArrayList<TextView>();
	Handler handler = new Handler(){   
	       
	    @Override   
	    public void handleMessage(Message msg) {   
	        // TODO Auto-generated method stub    
	        super.handleMessage(msg);   
	        switch (msg.what) {   
	       
	        case 1:
	        	mDialog.show();
		    	mDialog.setContentView(R.layout.loading_process_dialog_anim);
		    	mDialog.setCanceledOnTouchOutside(false);
		    	mDialog.setCancelable(false);
	        	break;	 
	        case 2:
	        	Bundle b = msg.getData();
	        	ed.Toast(RedNumber.this,b.getString("throwsmessage"));
	        	break;
	        }   
	    }   
	};   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rednumber);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
		.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
		.build());
		mDialog = new AlertDialog.Builder(RedNumber.this).create();//�����������
		
		aaa = 0;
		addbtn = (Button) findViewById(R.id.addbtn);
		toppege = (Button) findViewById(R.id.toppage);
		bottompege = (Button) findViewById(R.id.bottompage);
		table = (LinearLayout) findViewById(R.id.table);
		shanghulayout = (LinearLayout)findViewById(R.id.shanghulayout);
		shanghuno = (TextView) findViewById(R.id.shanghuno);
		addtable();//��������¼�
		// �̻����
		shanghuno.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ed.Dialog(RedNumber.this, shanghulayout,
						shanghuno, R.id.shanghuno,"�������̻����");
			}
		});
		MyLis lis = new MyLis();
		toppege.setOnClickListener(lis);
		bottompege.setOnClickListener(lis);
		if(selectno!=null&&selectno.equals("xia"))
		{
			
			try {
				String[] noarrg = RedYuShen.numberno.split(",");
				leng = noarrg.length-1;
				shanghuno.setText(noarrg[0]);
				for (int i = 1; i < noarrg.length; i++) {
			    		
					final View view = LayoutInflater.from(
					RedNumber.this).inflate(R.layout.rednumberlaouty, null);
					view.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.FILL_PARENT, 60));
					final LinearLayout shanghulayout1 = (LinearLayout) view.findViewById(R.id.shanghulayout1);
					view.findViewById(R.id.shanghuno1).setOnClickListener(
							new OnClickListener() {

								public void onClick(View v) {
									ed.Dialog(RedNumber.this, shanghulayout1,
											(TextView)v, R.id.shanghuno1,"�������̻����");
								}

							});
					table.addView(view);
					view.findViewById(R.id.delectbtn).setOnClickListener(
							new OnClickListener() {

								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									table.removeView(view);
									leng--;
									aaa = 0;
								}
							});
					shanghuno1 = (TextView) view.findViewById(R.id.shanghuno1);
					ls.add(shanghuno1);
					ls.get(i-1).setText(noarrg[i]);
			    }
					
			}catch (Exception e) {
					techown.shanghu.https.Log.Instance().WriteLog("�Ŷӣ�ZhongBuHuoDong3�쳣"+e.getMessage());
			}
			selectno = "shang";
		}
	}

	int leng = 0;
	static int aaa = 0;
	static TextView shanghuno1, shanghuno2;

	private void addtable() {

		addbtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// �ж��Ƿ���д
//				if (aaa == 0&&!shanghuno.getText().toString().equals("")) {
					aaa = 1;
					// �ж��Ƿ����10��
					if (leng != 9) {
						final View view = LayoutInflater.from(
								RedNumber.this).inflate(
								R.layout.rednumberlaouty, null);
						view.setLayoutParams(new LinearLayout.LayoutParams(
								LayoutParams.FILL_PARENT, 60));
						
						final LinearLayout shanghulayout1 = (LinearLayout) view.findViewById(R.id.shanghulayout1);
						
						view.findViewById(R.id.shanghuno1).setOnClickListener(
								new OnClickListener() {

									public void onClick(View v) {
										ed.Dialog(RedNumber.this, shanghulayout1,
												(TextView)v, R.id.shanghuno1,"�������̻����");
									}

								});

						table.addView(view);
						leng++;
						shanghuno1 = (TextView) view.findViewById(R.id.shanghuno1);
						// ɾ��һ���̻����
						view.findViewById(R.id.delectbtn).setOnClickListener(
								new OnClickListener() {

								public void onClick(View arg0) {
									// TODO Auto-generated method stub
										table.removeView(view);
										leng--;
										aaa = 0;
								}
							});
					} else {
						ed.Toast(RedNumber.this,
								"�������10���̻���ţ�");
					}
//				} else {
//					ed.Toast(RedNumber.this,
//					"����д�̻���� ��");
//				}
			}
		});
	}
	StringBuffer numno;
	String[] arrgno;
	public void ShanghuNo()
	{	
		arrgno=null;
		numno = new StringBuffer();
		numno.append(shanghuno.getText().toString());
		for (int i = 1; i < table.getChildCount(); i++) {
			System.out.println("table����==="+table.getChildCount());
			LinearLayout noviewLayout=(LinearLayout) table.getChildAt(i);
			shanghuno2=(TextView) noviewLayout.findViewById(R.id.shanghuno1);
			if(shanghuno2.getText().toString()!="")
			{
				if(numno.toString().equals(""))
				{
					numno.append(shanghuno2.getText().toString());
				}
				else
				{
					numno.append(","+shanghuno2.getText().toString());
				}
			}
		}
		System.out.println("numno===="+numno);
		RedCompanyMessage.RedNum = numno.toString();
		RedYuShen.numberno = numno.toString();
		arrgno = numno.toString().split(",");
	}
	

	// ��ť����¼�
	class MyLis implements OnClickListener {

		public void onClick(View v) {
			int id = v.getId();
			Intent intent = new Intent();
			try {
				switch (id) {
				case R.id.bottompage:
					if(validate())
					{
						ShanghuNo();
	 					Message msg = new Message();   
	 			        msg.what =1; 
	 					handler.sendMessage(msg);
	 					 new Thread(){
	 						 public void run(){
	 							 RedYuShen.requeststr = new String[arrgno.length];
	 							for (int i = 0; i < arrgno.length; i++) {
	 								String[]str=new String[]{"",""};	
	 							 	
	 	 							str[0]=TuanDuiShangHuActivity.username;
	 	 							str[1]=arrgno[i];
	 	 								
	 	 							String[]namestr=new String[]{"userId","misId"};
	 	 							JSONObject jsonstr=new JSONObject();
	 	 							for(int j=0;j<str.length;j++)
	 	 							{
	 	 								try {
	 	 									jsonstr.put(namestr[j], str[j]);
	 	 								} catch (JSONException e) {
	 	 									// TODO Auto-generated catch block
	 	 									e.printStackTrace();
	 	 								}	
	 	 							}
	 	 							String json=jsonstr.toString();
	 	 						 	techown.shanghu.https.Log.Instance().WriteLog("A3008���Ԥ���ѯ��������Ϊ"+json);
	 	 						 	System.out.println("�̻���ţ�"+arrgno[i]+"������....");
	 	 						 	String numbersum = HttpConnection2.RedRequest(json,"A3008");	
	 	 						 	if(numbersum!=null)
	 						 		{
	 						 			try
	 									{
	 										JSONObject demoJson1 = new JSONObject(numbersum);
	 										
	 										if(demoJson1.getString("rspCode").equals("00"))
	 										{
	 											RedYuShen.requeststr[i]="��|"+numbersum;	
	 										}									
	 										else 
	 										{
	 											RedYuShen.requeststr[i]="��|"+arrgno[i];
	 										}
	 									}catch(Exception e)
	 									{
	 										
	 										techown.shanghu.https.Log.Instance().WriteLog("A3008���Ԥ���ѯ���󷵻����ݽ����쳣"+e.getMessage().toString());
	 									}	
	 						 		}
	 	 						 	else 
									{
											RedYuShen.requeststr[i]="��|"+arrgno[i];
									}
	 	 						 	
	 							}
	 						 	mDialog.dismiss();	
	 						 	Intent inten = new Intent();
								inten.setClass(RedNumber.this, RedYuShen.class);
							 	RedNumber.this.startActivity(inten);
							 	overridePendingTransition(R.anim.rightin, R.anim.rightout);
							 	RedNumber.this.finish();
	 						 }
	 					 }.start();	
					}
 					 break;
				case R.id.toppage:

					intent.setClass(RedNumber.this, TuanDuiShangHuActivity.class);
					RedNumber.this.startActivity(intent);
					RedNumber.this.finish();
					overridePendingTransition(R.anim.rightin, R.anim.rightout);
					break;

				}
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"HeYueQianDing_3��button3button2�����" + e.getMessage());
			}
		}
	}
	
	// ��֤�����ֶε��Ƿ�Ϊ��
	protected boolean validate() {
		if(shanghuno.getText().toString().equals(""))
		{
			ed.Toast(RedNumber.this, "�������̻���ţ�");
			return false;
		}
		return true;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			ed.ExitApp(RedNumber.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	 
}