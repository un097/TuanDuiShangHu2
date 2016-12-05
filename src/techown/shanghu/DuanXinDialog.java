package techown.shanghu;

import java.util.ArrayList;
import java.util.List;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DuanXinDialog {
	static Object[] name;
	static EditText et;
//	EditDialog ed = new EditDialog();
	static StringBuilder sb = new StringBuilder();
	static Window window;
	static Button ok_button, cancle_button;
	static RelativeLayout relativeLayout;
	public static boolean istwo, a;
	public static int num = 0, n = 0;
	public static int num1 = 0;
	DataCheckUtil dc = new DataCheckUtil();
	public TextView string_ed,duanxinno,duanxinnum;
	public static Dialog dialog, dlg, etdlg;
	DBUtil DB = new DBUtil();
	static List<String> list = new ArrayList<String>();
	static List<String> listId = new ArrayList<String>();
	AlertDialog dlg1 = null;
	LinearLayout okno;
	DES des = new DES();
	
	
	public void Dialog(final Context con, final LinearLayout linearLayout,
			final TextView textArray, final int ID, String string) {
		if (dialog != null && dialog.isShowing()) {
			return;
		}
		if (dlg != null && dlg.isShowing()) {
			return;
		}
		if (etdlg != null && etdlg.isShowing()) {
			return;
		}
		if (DateDialog.dialog != null && DateDialog.dialog.isShowing()) {
			return;
		}

		LayoutInflater factory = LayoutInflater.from(con);
		// 得到自定义对话框
		final View DialogView = factory.inflate(R.layout.duanxinra_layout, null);
		dialog = new Dialog(con, R.style.FullHeightDialog);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.TOP);

		okno = (LinearLayout) DialogView.findViewById(R.id.okno);
		duanxinnum = (TextView) DialogView.findViewById(R.id.duanxinnum);
		duanxinno = (TextView) DialogView.findViewById(R.id.duanxinno);
		
		et = (EditText) DialogView.findViewById(R.id.dialog_editText);
		et.setText(textArray.getText().toString());
		string_ed = (TextView) DialogView.findViewById(R.id.string_textView);
		string_ed.setText(string);
		et.setSelection(textArray.getText().toString().length());
		sb = sb.delete(0, sb.length());

		et.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				//输入时调用方法
//				if(dc.CharString(et.getText().toString()))
//				{
//					double allnum=getMsgLength(et.getText().toString());
					
				
			
				
					int num1=0;
					okno.setVisibility(0);
					System.out.println(dc.CharString(et.getText().toString()));
//					int num=getMessageLength(et.getText().toString());
					int num=et.getText().toString().length();
					int aa = et.getText().toString().indexOf("$");
					if(aa>0){
						num1=num-1;
					}else{
						num1=num;
					}
					if(TuanDuiShangHuActivity.templateLength!=0)
					{
						double allnum = (double)(num1+TuanDuiShangHuActivity.templateLength)/(double)TuanDuiShangHuActivity.msgLength;
						System.out.println(allnum);
						duanxinnum.setText(String.valueOf(num1));
						duanxinno.setText(String.valueOf((int)Math.ceil(allnum)));
					}
					else
					{
						double allnum = (double)(num1+29)/(double)60;
						System.out.println(allnum);
						duanxinnum.setText(String.valueOf(num1));
						duanxinno.setText(String.valueOf((int)Math.ceil(allnum)));
					}
					
//				}
//				else
//				{
//					okno.setVisibility(0);
//					System.out.println(dc.CharString(et.getText().toString()));
//					int num = dc.NumString(et.getText().toString());
//					String snum = String.valueOf(num);
//					double allnum = (double)(num+58)/(double)120;
//					System.out.println("短信输入内容字节数："+num);
//					duanxinnum.setText(snum);
//					System.out.println("短信内容总字节数："+(num+58));
//					System.out.println(allnum);
//					System.out.println("可分："+(int)Math.ceil(allnum)+"页");
//					duanxinno.setText(String.valueOf((int)Math.ceil(allnum)));
//				}
				
			}
			
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ok_button = (Button) DialogView.findViewById(R.id.ok_button);
		ok_button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				switch (ID) {
				case R.id.h4edtext9:
					if (et.getText().toString().length() <= 181
							&& et.getText().toString().length() != 0) {
						if (!isdouhao(et.getText().toString())) {
							Toast(con, "内容含有异常字符，请正确输入！");
						} else {
							String[] strb = new String[et.getText().toString()
									.length()];
							int aa = et.getText().toString().indexOf("$");
							char bbb = et.getText().toString().charAt(et.getText().toString().length() - 1);
							String aaaa = "";
							for (int i = 0; i < strb.length; i++) {
								strb[i] = et.getText().toString()
										.substring(i, i + 1);
								if (aa > 0 && !strb[0].equals("$")
										&& bbb != '$') {
									aaaa = "aaaaaa";
								}
							}
							if (aaaa.equals("aaaaaa")) {
								textArray.setText(et.getText().toString());
								dialog.dismiss();
							} else {

								Toast(con, "短信格式错误！如：商户名称$短信内容......！");
							}
						}

					}
					else {
						Toast(con, "不得为空！");
					}				

					break;
//				case R.id.heyueyouhuiquantxt:
//					if (ed.allowMaxLenthOfString(et.getText().toString(),400) && et.getText().toString().length() != 0) {
//						if (!isdouhao(et.getText().toString())) {
//							Toast(con, "内容含有异常字符，请正确输入！");
//						} else {
////							String[] strb = new String[et.getText().toString()
////									.length()];
////							int aa = et.getText().toString().indexOf("$");
////							char bbb = et.getText().toString().charAt(et.getText().toString().length() - 1);
////							String aaaa = "";
////							for (int i = 0; i < strb.length; i++) {
////								strb[i] = et.getText().toString()
////										.substring(i, i + 1);
////								if (aa > 0 && !strb[0].equals("$")
////										&& bbb != '$') {
////									aaaa = "aaaaaa";
////								}
////		
////							}
////							if (aaaa.equals("aaaaaa")) {
//								textArray.setText(et.getText().toString());
//								dialog.dismiss();
////							} else {
////		
////								Toast(con, "短信格式错误！如：商户名称$短信内容......！");
////							}
//				}
//
//			}
//			else {
//				Toast(con, "不得为空！");
//			}				
//					break;
				default:
					break;

				}
			}
		});

		cancle_button = (Button) DialogView.findViewById(R.id.cancle_button);
		cancle_button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});

		dialog.setContentView(DialogView);
		// etdlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dialog.setCancelable(false);
		dialog.show();
	}
	public boolean isdouhao(String name) {
		int aa = name.indexOf("|");
		String[] arrg = name.split(" ");
		if (aa >= 0) {
			return false;
		} 
		else if(arrg.length==0||arrg==null)
		{
			return false;
		}else {
			return true;
		}
	}
	public void Toast(final Context con, String message) {
		
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage(message);// 设置信息
		b.setCancelable(false);

		b.setPositiveButton("确定", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		if(dlg1==null||!dlg1.isShowing()){
			dlg1 = b.create();// 创建对话框
			dlg1.show();// 显示对话框
			dlg1.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		}
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
	}
//	public  int getMsgLength(String str){
//		int count = 0;
//		int count1=0;
//		StringBuffer error = new StringBuffer();
//		char[] aa = str.toCharArray();
//		for(int i=0;i<aa.length;i++){
//			char c = aa[i];
//			if(Pattern.matches("^[\\w\\s]|[\u4E00-\u9FA5]|[\\x00-\\xFF]$", String.valueOf(c)))
//				count= count+1;
//			else if(Pattern.matches("[^\\x00-\\x7F]", String.valueOf(c)))
//				count = count+2;
//			else 
//				error.append(String.valueOf(c)+",");
//		}
//		System.out.println(count);
////		if(isNull(enterChar))
////			logger.info(error+" 未在定义的正则表达式范围内");
//		
//		  
//		int aacc = str.indexOf("$");
//		if(aacc > 0){
//			count1=count-1;
//		}else
//			count1=count;
//		
//		return count1;
//	}
	 private static final String FULL_MARKS = "｀～！＠＃＄％＾＆＊（）＿－＋＝｛［｝］｜＼：；＂＇＜，＞．？／";
		public static int getMessageLength(String message) {
			
			int length = message.length();
			int lengthResult = length;
			
			for(int index = 0 ; index < length ; index ++) {
						
				String single = message.substring(index,index + 1);

				if(FULL_MARKS.indexOf(single) > -1) {
					
					lengthResult ++;
				} 
			}
			
			return lengthResult;
		}
		
	

}
