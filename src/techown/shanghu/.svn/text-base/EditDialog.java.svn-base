package techown.shanghu;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import techown.shanghu.date.DES;
import techown.shanghu.date.DateDialog;
import techown.shanghu.date.TCity;
import techown.shanghu.photo.CGXYHTakePhotoActivity;
import techown.shanghu.photo.CGXYHTakePhotoActivity2;
import techown.shanghu.photo.COfCOfYHTakePhotoActivity;
import techown.shanghu.photo.CaoYouHuiTakePhotoActivity2;
import techown.shanghu.photo.CopyOfYouHuiTakePhotoActivity;
import techown.shanghu.photo.GDTPhotoActivity;
import techown.shanghu.photo.GongDanTakePhotoActivity;
import techown.shanghu.photo.RedTakePhotoActivity;
import techown.shanghu.photo.RedTakePhotoActivityBox;
import techown.shanghu.photo.TakePhotoActivity;
import techown.shanghu.photo.TakePhotoActivity2;
import techown.shanghu.photo.TakePhotoActivity_1_5;
import techown.shanghu.photo.YouHuiTakePhotoActivity;
import techown.shanghu.photo.YouHuiTakePhotoActivity2;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EditDialog {
	static Object[] name;
	static int l = 0;
	static EditText et;
	static StringBuilder sb = new StringBuilder();
	static Window window;
	static Button ok_button, cancle_button;
	static RelativeLayout relativeLayout;
	public static boolean istwo, a;
	public static int num = 0, n = 0;
	public static int num1 = 0;
	DES des = new DES();
	DataCheckUtil dc = new DataCheckUtil();
	public TextView string_ed,duanxinno,duanxinnum;
	public static Dialog dialog, dlg, etdlg;
	DBUtil DB = new DBUtil();
	static List<String> list = new ArrayList<String>();
	static List<String> listId = new ArrayList<String>();
	AlertDialog dlg1 = null;
	LinearLayout okno;
	
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
		final View DialogView = factory.inflate(R.layout.ra_layout, null);
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
		ok_button = (Button) DialogView.findViewById(R.id.ok_button);
		ok_button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				switch (ID) {
				case R.id.redsparetext1:
				case R.id.redsparetext2:
				case R.id.redsparetext3:
				case R.id.redsparetext4:
				case R.id.sparetext1:
				case R.id.sparetext2:
				case R.id.sparetext3:
				case R.id.sparetext4:
					if (!allowMaxLenthOfString(et.getText().toString(), 100)) 
					{
						Toast(con, "输入的字符过长");
					}
					else
					{
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.zhubeia:
				case R.id.zhubeib:
				case R.id.zhubeic:
				case R.id.zhubeid:
					if (!allowMaxLenthOfString(et.getText().toString(), 100)) 
					{
						Toast(con, "输入的字符过长");
					}
					else
					{
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.redh2edtext15:// 营业号
					if ((et.getText().toString()).length() == 0) 
					{
						Toast(con, "门店工商执照号不能为空");
					} 
					else 
					{
						if (!allowMaxLenthOfString(et.getText().toString(), 50)) 
						{
							Toast(con, "输入的字符过长");
						}
						else
						{
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}
						break;
					}
				//最红签约商户编号输入
				case R.id.shanghuno:
					if (et.getText().toString().length()==15) 
					{
						if(isAddress2(et.getText().toString()))
						{
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}
						else
						{
							Toast(con, "商户编号只能输入英文和数字");
						}
					} else 
					{
						Toast(con, "商户编号为15位数");
					}
					break;
				case R.id.shanghuno1:
					if (et.getText().toString().length()==15) 
					{
						if(isAddress2(et.getText().toString()))
						{
							textArray.setText(et.getText().toString());
							dialog.dismiss();
							RedNumber.aaa=0;
						}
						else
						{
							Toast(con, "商户编号只能输入英文和数字");
						}
					} else 
					{
						Toast(con, "商户编号为15位数");
					}
					break;
				
				case R.id.h1et7021:
			

					if (!allowMaxLenthOfString(et.getText().toString(), 100)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						Log.i("textaray",et.getText().toString());
						Log.i("text",textArray.getText().toString());
						dialog.dismiss();
					}

					break;
					
					
				//商户工单维护
				case R.id.tv_gongdan6_princal_name:
					if (!allowMaxLenthOfString(et.getText().toString(), 20)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;	
				case R.id.tv_gongdan5_content_beizhu:
					if (!allowMaxLenthOfString(et.getText().toString(), 400)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.tv_gongdan2_shName_beizhu:
				case R.id.tv_gongdan5_target_beizhu:
					if (!allowMaxLenthOfString(et.getText().toString(), 100)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.tv_gongdan2_shAddress_beizhu:
					if (!allowMaxLenthOfString(et.getText().toString(), 120)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.tv_gongdan2_shPhoneNum_beizhu:
				case R.id.tv_gongdan2_sbNum_beizhu:
				case R.id.tv_gongdan2_sbadd:
				case R.id.tv_gongdan2_sbNum_beizhu_item:
				case R.id.tv_gongdan2_pos_work_beizhu:
				case R.id.tv_gongdan2_sbadd_item:
				case R.id.tv_gongdan3_machine_work_beizhu:
				case R.id.tv_gongdan3_snNum_beizhu:
				case R.id.tv_gongdan3_snNum_beizhu_item:
				case R.id.tv_gongdan6_princal_num:
					if (!allowMaxLenthOfString(et.getText().toString(), 50)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.tv_gongdan3_sbNum_add:
					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
					
					
				case R.id.redh1et7021://最红公司信息注册地址
				case R.id.redh2edtext4:
					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.tbyedtext3:
					if (!allowMaxLenthOfString(et.getText().toString(), 40)) {
						Toast(con, "字符过长，最多可以输入20个汉字");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.tbyedtext4:
					if (!allowMaxLenthOfString(et.getText().toString(), 50)) {
						Toast(con, "字符过长，最多可以输入25个汉字");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.yedtextwifi1:
				case R.id.statetext1:
				
					if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.yedtext1:
					if (!allowMaxLenthOfString(et.getText().toString(), 100)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.yedtext2:
					// if (dc.isChinese(et.getText().toString())) {

					if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else if (!allowMaxLenthOfString(et.getText().toString(),
							200)) {
						Toast(con, "输入的字符过长");

					}

					else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.yedtext3:
				case R.id.h2edtext13:
				case R.id.z1edtext2:
					if (isdouhao(et.getText().toString())) {

						if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
							Toast(con, "输入的字符过长");

						} else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}

					} else {
						Toast(con, "内容含有异常字符，请正确输入！");
						// et.setText("");

					}
					break;
				case R.id.yedtext4:
					if (et.getText().toString().length() > 64) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");

					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.yedtext5:
					if (et.getText().toString().length() > 17) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h3edtext2:// 营业时间

					if (allowMaxLenthOfString(et.getText().toString(), 200)) {

						if (!isdouhao(et.getText().toString())) {
							Toast(con, "内容含有异常字符，请正确输入！");
						} else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}

					} else {
						Toast(con, "输入的字符过长");
						// et.setText("");
					}
					break;
				//
				case R.id.yedtext7:
					if (et.getText().toString().length() > 7) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.h1et1:
				case R.id.c1et1:
					// case R.id.z1edtext1:

					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					}

					else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.h1et2:
				case R.id.c1et2:

					if (isdouhao(et.getText().toString())) {

						if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
							Toast(con, "输入的字符过长");

						} else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}

					} else {
						Toast(con, "内容含有异常字符，请正确输入！");

					}
					break;
				case R.id.h1et3:
				case R.id.c1et3:

					if (isdouhao(et.getText().toString())) {

						if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
							Toast(con, "输入的字符过长");

						} else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}

					} else {
						Toast(con, "内容含有异常字符，请正确输入！");

					}
					break;
				case R.id.h1et4:
				case R.id.c1et4:
				case R.id.z1edtext4:
				case R.id.h3edtext9:
				case R.id.y4edtext1:
					if (!allowMaxLenthOfString(et.getText().toString(), 40)) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h1et6:
				case R.id.c1et6:
					// case R.id.z1edtext6:

					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();

					}
					break;
				case R.id.h1et7:

					if (!allowMaxLenthOfString(et.getText().toString(), 100)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.c1et7:
				case R.id.z1edtext7:
					if (!allowMaxLenthOfString(et.getText().toString(), 200)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.h1et8:
					if (et.getText().toString().length() != 6) {
						Toast(con, "请填写6位邮编");
						// et.setText("");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.h1et9:

					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.z1edtext9:

					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					}

					else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h1et10:

					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.z1edtext10:

					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					}

					else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h1et11:
				case R.id.c1et11:
				case R.id.z1edtext11:
					if (et.getText().toString().length() > 20) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.h1et13:
					if (et.getText().toString().length() > 3) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h1et14:
					if (et.getText().toString().length() > 20) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
						et.setText("");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.h2edtext1:

					if (!allowMaxLenthOfString(et.getText().toString(), 30)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.h2edtext2:

					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h3edtext7:
					if (!isAddress(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else if (!allowMaxLenthOfString(et.getText().toString(),
							30)) {
						Toast(con, "输入的字符过长");

					}

					else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();

					}
					break;

				case R.id.h2edtext3:
					if (et.getText().toString().length() > 20) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h2edtext4:

					if (!isdouhao(et.getText().toString())) {
						Toast(con, "输入的字符错误");
					} else if (!allowMaxLenthOfString(et.getText().toString(),
							200)) {
						Toast(con, "输入的字符过长");

					}

					else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;

				case R.id.h2edtext6:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h2edtext7:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h2edtext8:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.h2edtext16:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.tv_other:
					if (et.getText().toString().length() > 50) {
						Toast(con, "输入长度过长");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h2edtext17:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.h2edtext19:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h3edtext1:
					if (dc.isData(et.getText().toString())) {
						textArray.setText(et.getText().toString());
						dialog.dismiss();

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else {
						Toast(con, "输入有误");
					}
					break;

				case R.id.h3edtext3:
					if (dc.isData(et.getText().toString())) {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					} else if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						Toast(con, "输入有误");
					}
					break;
				case R.id.h3edtext4:
					if (dc.isData(et.getText().toString())) {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					} else if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						Toast(con, "输入有误");
					}
					break;

				case R.id.h3edtext5:// 交通情况

					if (allowMaxLenthOfString(et.getText().toString(), 200)) {

						if (!isdouhao(et.getText().toString())) {
							Toast(con, "内容含有异常字符，请正确输入！");
						} else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}

					}

					else {
						Toast(con, "输入的字符过长！");

					}
					break;
				case R.id.h3edtext6:
					if (dc.isData(et.getText().toString())) {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					} else if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
						// et.setText("");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						Toast(con, "输入有误");
					}
					break;

				case R.id.h3edtext8:
					if (dc.isData(et.getText().toString())) {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					} else if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						Toast(con, "输入有误");
					}
					break;

				case R.id.h3edtext10:

					if (!allowMaxLenthOfString(et.getText().toString(), 400)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();

					}
					break;
				case R.id.h3edtext11:
					if (!allowMaxLenthOfString(et.getText().toString(), 400)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
					
				case R.id.y4edtext3:
					if (!allowMaxLenthOfString(et.getText().toString(), 200)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h3edtext12:

					if (!allowMaxLenthOfString(et.getText().toString(), 120)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();

					}
					break;
				case R.id.h3edtext13:

					if (!allowMaxLenthOfString(et.getText().toString(), 40)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();

					}
					break;
				case R.id.h3edtext14:
					if (et.getText().toString().length() == 0) {
						Toast(con, "信息不可为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						if (!allowMaxLenthOfString(et.getText().toString(), 10)) {
							Toast(con, "输入的字符过长");

						} else if (!dc.isData(et.getText().toString())) {
							Toast(con, "请输入数字信息");
						}

						else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}
					}
					break;
				case R.id.h3edtext15:

					if (!allowMaxLenthOfString(et.getText().toString(), 200)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();

					}
					break;
					
				case R.id.redh3edtext10:
				case R.id.redh3edtext12:
				case R.id.redh3edtext13:
				case R.id.redh3edtext15:

					if (!allowMaxLenthOfString(et.getText().toString(), 200)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();

					}
					break;
					
				case R.id.h4edtext1:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h4edtext2:
					if ((et.getText().toString().length()) == 0) {
						Toast(con, "姓名不能为空");
					} else {
						if (dc.isChinese(et.getText().toString())
								|| dc.isEnglish(et.getText().toString())) {

							if (!allowMaxLenthOfString(et.getText().toString(),
									20)) {
								Toast(con, "输入的字符过长");

							} else {
								textArray.setText(et.getText().toString());
								dialog.dismiss();
							}

						} else {
							Toast(con, "输入的字符错误");

						}
					}
					break;
				case R.id.h4edtext3:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h4edtext4:
					if ((et.getText().toString().length()) == 0) {
						Toast(con, "姓名不能为空");
					} else {
						if (dc.isChinese(et.getText().toString())
								|| dc.isEnglish(et.getText().toString())) {

							if (!allowMaxLenthOfString(et.getText().toString(),
									20)) {
								Toast(con, "输入的字符过长");

							} else {
								textArray.setText(et.getText().toString());
								dialog.dismiss();
							}

						} else {
							Toast(con, "输入的字符错误");

						}
					}
					break;
				case R.id.h4edtext7:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
					
				case R.id.h4edtext9:
					if (et.getText().toString().length() <= 500
							&& et.getText().toString().length() != 0) {
						if (!isdouhao(et.getText().toString())) {
							Toast(con, "内容含有异常字符，请正确输入！");
						} else {
							String[] strb = new String[et.getText().toString()
									.length()];
							int aa = et.getText().toString().indexOf("$");
							char bbb = et
									.getText()
									.toString()
									.charAt(et.getText().toString().length() - 1);
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
								if(dc.CharString(et.getText().toString()))
								{
									okno.setVisibility(0);
									System.out.println(dc.CharString(et.getText().toString()));
									double allnum = (double)(et.getText().toString().length()+29)/(double)60;
									System.out.println("短信输入内容字节数："+et.getText().toString().length());
									duanxinnum.setText(et.getText().toString().length());
									System.out.println("短信内容总字节数："+(et.getText().toString().length()+29));
									System.out.println(allnum);
									System.out.println("可分："+(int)Math.ceil(allnum)+"页");
									duanxinno.setText((int)Math.ceil(allnum));
								}
								else
								{
									okno.setVisibility(0);
									System.out.println(dc.CharString(et.getText().toString()));
									int num = dc.NumString(et.getText().toString());
									double allnum = (double)(num+58)/(double)120;
									System.out.println("短信输入内容字节数："+num);
									duanxinnum.setText(num);
									System.out.println("短信内容总字节数："+(num+58));
									System.out.println(allnum);
									System.out.println("可分："+(int)Math.ceil(allnum)+"页");
									duanxinno.setText((int)Math.ceil(allnum));
								}
								dialog.dismiss();
							} else {

								Toast(con, "短信格式错误！如：商户名称$短信内容......！");
							}
						}

					}
					else {
						Toast(con, "输入的字符过长！");
					}

					break;
				case R.id.h4edtext10:

					if (!allowMaxLenthOfString(et.getText().toString(), 200)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.heyueyouhuiquantxt:
					if (allowMaxLenthOfString(et.getText().toString(),400) && et.getText().toString().length() != 0) {
						if (!isdouhao(et.getText().toString())) {
							Toast(con, "内容含有异常字符，请正确输入！");
						} else {
//							String[] strb = new String[et.getText().toString()
//									.length()];
//							int aa = et.getText().toString().indexOf("$");
//							char bbb = et.getText().toString().charAt(et.getText().toString().length() - 1);
//							String aaaa = "";
//							for (int i = 0; i < strb.length; i++) {
//								strb[i] = et.getText().toString()
//										.substring(i, i + 1);
//								if (aa > 0 && !strb[0].equals("$")
//										&& bbb != '$') {
//									aaaa = "aaaaaa";
//								}
//		
//							}
//							if (aaaa.equals("aaaaaa")) {
								textArray.setText(et.getText().toString());
								dialog.dismiss();
//							} else {
//		
//								Toast(con, "短信格式错误！如：商户名称$短信内容......！");
//							}
							}
			
					}
					else {
						Toast(con, "不得为空！");
					}				
					break;
				case R.id.h4edtext11:
				case R.id.h4edtext6:
					if (!allowMaxLenthOfString(et.getText().toString(), 200)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;

				case R.id.z1edtext1:

					if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.z1edtext3:
					if (isdouhao(et.getText().toString())) {

						if (!allowMaxLenthOfString(et.getText().toString(), 60)) {
							Toast(con, "输入的字符过长");

						} else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}

					} else {
						Toast(con, "输入的字符错误");
						// et.setText("");

					}
					break;
				case R.id.z1edtext5:
					if (dc.isPhoneNum(et.getText().toString())) {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					} else {
						Toast(con, "手机号码输入有误");
						// et.setText("");
					}
					break;
				case R.id.z1edtext6:
					if (!isdouhao(et.getText().toString())) {
						Toast(con, "输入的字符错误");
					} else if (!allowMaxLenthOfString(et.getText().toString(),
							60)) {
						Toast(con, "输入的字符过长");

					}

					else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();

					}
					break;
				case R.id.z1edtext8:
					if (et.getText().toString().length() != 6) {
						Toast(con, "请填写6位邮编");
						// et.setText("");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}

					break;
				case R.id.z1edtext13:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.z2edtext1:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.z2edtext2:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.z2edtext3:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.z2edtext4:
					if (!allowMaxLenthOfString(et.getText().toString(), 200)) {
						Toast(con, "输入的字符过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.z2edtext5:
					if (!allowMaxLenthOfString(et.getText().toString(), 200)) {
						Toast(con, "输入的字符过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.z2edtext6:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.z2edtext7:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.z2edtext8:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.z2edtext9:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.c1et9:
					if (!allowMaxLenthOfString(et.getText().toString(), 20)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.c1et10:
					if (!allowMaxLenthOfString(et.getText().toString(), 20)) {
						Toast(con, "输入的字符过长");

					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.c2et1:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.c2et2:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.c2et3:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.c2et4:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
						et.setText("");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.c2et5:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.c2et6:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.g3et1:
					if (allowMaxLenthOfString(et.getText().toString(), 40)) {
						if (!isdouhao(et.getText().toString())) {
							Toast(con, "内容含有异常字符，请正确输入！");
						} else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}

					} else {
						Toast(con, "输入的字符过长！");
					}
					break;
				case R.id.g3et2:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.g3et3:
					if (!allowMaxLenthOfString(et.getText().toString(), 200)) {
						Toast(con, "输入的字符过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.g3et4:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.g3et5:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.g3et6:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.y4edtext2:
					if (et.getText().toString().length() > 20) {
						Toast(con, "输入长度过长");
					} else if (!isdouhao(et.getText().toString())) {
						Toast(con, "内容含有异常字符，请正确输入！");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;
				case R.id.h2edtext9:
					if (et.getText().toString().length()==15) 
					{
						if(isAddress2(et.getText().toString()))
						{
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}
						else
						{
							Toast(con, "商户编号只能输入英文和数字");
						}
						
					} else {
						Toast(con, "商户编号为15位数");
					}
					break;
				case R.id.h2edtext10:
					if (et.getText().toString().length()==8) 
					{
						if(isAddress2(et.getText().toString()))
						{
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}
						else
						{
							Toast(con, "终端号只能输入英文和数字");
						}
						
					} else {
						Toast(con, "终端号为8位数");
					}
					break;
					
				case R.id.h2edtext21:
					if(et.getText().toString().contains(" ")){
						Toast(con, "不能输入空格");
					}else{
						if(et.getText().toString().length() > 32){
							Toast(con, "输入长度过长");
						}else{
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}
					}
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

	public void Dialog1(final Context con, final TextView textArray,
			final int ID, String string) {
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
		final View DialogView = factory.inflate(R.layout.ra_layout, null);
		dialog = new Dialog(con, R.style.FullHeightDialog);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.TOP);

		et = (EditText) DialogView.findViewById(R.id.dialog_editText);
		et.setText("");
		et.setSelection(textArray.getText().toString().length());
		string_ed = (TextView) DialogView.findViewById(R.id.string_textView);
		string_ed.setText(string);
		sb = sb.delete(0, sb.length());
		ok_button = (Button) DialogView.findViewById(R.id.ok_button);
		ok_button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				switch (ID) {
				case R.id.h1et1:
					if (et.getText().toString().length() > 20) {
						Toast(con, "输入长度过长");
						et.setText("");
					} else if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
						et.setText("");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.hyedit1:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
						et.setText("");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.hyedit2:
					if (et.getText().toString().length() == 0) {
						Toast(con, "不能为空");
						et.setText("");
					} else {
						textArray.setText(et.getText().toString());
						dialog.dismiss();
					}
					break;

				case R.id.hyedit3:
					if ((et.getText().toString()).length() == 0) {
						Toast(con, "营业注册号不能为空");

					} else {
						if (!allowMaxLenthOfString(et.getText()
								.toString(), 60)) {
							Toast(con, "输入的字符过长");

						}

						else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}
					}
					break;

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
		dialog.setCancelable(false);
		dialog.show();
	}

	static Button etdialog_bt01, etdialog_bt02, etdialog_bt03, etdialog_bt04,
			etdialog_bt05, etdialog_bt06, etdialog_pointbt;
	static GridView etdialog_gv01;
	static EditText et01;
	static StringBuilder sb2 = new StringBuilder();
	static String[] textstr = new String[] { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9" };

	// 数字键盘
	public void etDialog(final Context con, final TextView ettext,
			final int etid, String string) {

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
		final View DialogView = factory.inflate(R.layout.etdialog, null);
		etdlg = new Dialog(con, R.style.FullHeightDialog);
		Window dialogWindow = etdlg.getWindow();
		// WindowManager.LayoutParams lp =dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.BOTTOM);
		etdialog_gv01 = (GridView) DialogView.findViewById(R.id.etdialog_gv01);
		string_ed = (TextView) DialogView.findViewById(R.id.string_textViewnum);
		string_ed.setText(string);
		et = (EditText) DialogView.findViewById(R.id.etdialog_et);
		et01 = (EditText) DialogView.findViewById(R.id.etdialog_tv01);

		et.setInputType(InputType.TYPE_NULL);
		et01.setInputType(InputType.TYPE_NULL);

		sb = sb.delete(0, sb.length());
		sb2 = sb2.delete(0, sb2.length());

		et.setText(ettext.getText().toString());
		sb2.append(ettext.getText().toString());

		etdialog_gv01.setAdapter(new GridImageAdapter(con, textstr));
		etdialog_gv01.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				FrameLayout ll = (FrameLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView bt = (TextView) ll.getChildAt(0);

				if (et01.hasFocus()) {
					sb.append(bt.getText().toString());
					et01.setText(sb);

				} else {

					sb2.append(bt.getText().toString());
					et.setText(sb2);

				}
			}
		});

		etdialog_bt01 = (Button) DialogView.findViewById(R.id.etdialog_bt01);
		etdialog_bt02 = (Button) DialogView.findViewById(R.id.etdialog_bt02);
		etdialog_bt03 = (Button) DialogView.findViewById(R.id.etdialog_bt03);
		etdialog_bt04 = (Button) DialogView.findViewById(R.id.etdialog_bt04);
		etdialog_bt05 = (Button) DialogView.findViewById(R.id.etdialog_bt05);
		etdialog_bt06 = (Button) DialogView.findViewById(R.id.etdialog_bt06);
		etdialog_pointbt = (Button) DialogView
				.findViewById(R.id.etdialog_pointbt);

		etdialog_bt02.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					sb.append(etdialog_bt02.getText().toString());
					et01.setText(sb);
				} else {

					sb2.append(etdialog_bt02.getText().toString());
					et.setText(sb2);
				}
			}
		});

		etdialog_bt03.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					sb.append(etdialog_bt03.getText().toString());
					et01.setText(sb);
				} else {

					sb2.append(etdialog_bt03.getText().toString());
					et.setText(sb2);
				}
			}
		});
		etdialog_pointbt.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					sb.append(etdialog_pointbt.getText().toString());
					et01.setText(sb);
				} else {

					sb2.append(etdialog_pointbt.getText().toString());
					et.setText(sb2);
				}
			}
		});

		etdialog_bt04.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					sb.append(etdialog_bt04.getText().toString());
					et01.setText(sb);
				} else {

					sb2.append(etdialog_bt04.getText().toString());
					et.setText(sb2);
				}
			}
		});
		etdialog_bt05.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					if (sb.length() > 0) {
						sb = sb.delete(sb.length() - 1, sb.length());
						et01.setText(sb);
					}

				} else {
					if (sb2.length() > 0) {
						sb2 = sb2.delete(sb2.length() - 1, sb2.length());
						et.setText(sb2);
					}

				}
			}
		});
		etdialog_bt06.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// if(etid==R.id.cardcheck_etgone)
				// {
				// CardCheck.cardcheck_cb02.setChecked(false);
				// }
				etdlg.dismiss();
			}
		});
		etdialog_bt01.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				switch (etid) {

				// //add by syc and cy 5.19
				//最红活动录入
				case R.id.h4edtext10://营销活动录入界面金额限制
					if(et.getText().toString().length()>8)
					{
						Toast(con, "输入的内容长度不能超过8位");
					}
					else if(dc.isAllDigit(et.getText().toString()))
					{
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					}
					else
					{
						String[] arrgmoney = et.getText().toString().split("\\.");
						String[] errgmoney = arrgmoney[0].split("0");
						if(arrgmoney.length==2)
						{
							if(errgmoney!=null&&!et.getText().toString().trim().substring(0, 1).equals("0"))
							{
									if(arrgmoney[1].length()<=2)
									{
										ettext.setText(et.getText().toString());
										etdlg.dismiss();
									}
									else
									{
										Toast(con, "小数点后面只能保留两位小数");
									}
							}
							else
							{
								Toast(con, "输入的金额错误,请重新输入");
							}
						}
						else
						{
							Toast(con, "输入的内容中只能包含一个小数点");
						}
					}
					break;
				case R.id.redhuodong1://活动金额底线
				case R.id.redhuodong2://返还金额上限
					if(et.getText().toString().length()>10)
					{
						Toast(con, "输入的内容长度不能超过10位");
					}
					else if(dc.isAllDigit(et.getText().toString()))
					{
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					}
					else
					{
						String[] arrgmoney = et.getText().toString().split("\\.");
						String[] errgmoney = arrgmoney[0].split("0");
						if(arrgmoney.length==2)
						{
							if(errgmoney!=null&&!et.getText().toString().trim().substring(0, 1).equals("0"))
							{
									if(arrgmoney[1].length()<=2)
									{
										ettext.setText(et.getText().toString());
										etdlg.dismiss();
									}
									else
									{
										Toast(con, "小数点后面只能保留两位小数");
									}
							}
							else
							{
								Toast(con, "输入的金额错误,请重新输入");
							}
						}
						else
						{
							Toast(con, "输入的内容中只能包含一个小数点");
						}
					}
					break;
				case R.id.redhuodong4://最红活动录入--返还比例	
					if(et.getText().toString().length()>5)
					{
						Toast(con, "输入的内容长度不能超过5位");
					}
					else if(dc.isAllDigit(et.getText().toString()))
					{
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					}
					else
					{
						String[] arrgmoney = et.getText().toString().split("\\.");
						String[] errgmoney = arrgmoney[0].split("0");
						if(arrgmoney.length==2)
						{
							if(errgmoney!=null&&!et.getText().toString().trim().substring(0, 1).equals("0"))
							{
									if(arrgmoney[1].length()<=2)
									{
										ettext.setText(et.getText().toString());
										etdlg.dismiss();
									}
									else
									{
										Toast(con, "小数点后面只能保留两位小数");
									}
							}
							else
							{
								Toast(con, "输入的返还比例错误,请重新输入");
							}
						}
						else
						{
							Toast(con, "输入的内容中只能包含一个小数点");
						}
					}
					break;
					
				case R.id.yedtext6:
				case R.id.yedtext61:
				case R.id.z2edtext7:
				case R.id.c2et7:
				case R.id.g3et5:
					String inputStr6 = et.getText().toString();
					if (inputStr6.equals("")) {
						Toast(con, "请正确输入金额！");
					} else {
						DecimalFormat d=new DecimalFormat("0.00");
						if (inputStr6.contains(".")
								&& dc.isJustDigitStar(inputStr6)) {
							if (inputStr6.replace(".", "").length()>0&&Double.parseDouble(inputStr6.replace(".", "")) == 0) {
								Toast(con, "输入的交易金额要大于0！");
							} else {
								String digtal = inputStr6.trim();
								try {
									String Dia[] = digtal.split("\\.", 2);

									if (Dia[0].length() == 0) {
										Toast(con, "输入内容错误，请重新输入！");
									} else {
										if (Dia[1].length() == 0) {
											
											
												ettext.setText(String.valueOf(d.format(Double.parseDouble(Dia[0])))
														);
												etdlg.dismiss();
									
										} else {
											if (Dia.length != 0
													&& !Dia.equals(null)) {
												String dig1 = Dia[0];
												String dig2 = Dia[1];

												if (Dia[1].contains(".")) {
													Toast(con,
															"输入的数字只能存在一个小数点！");
												} 
												else
												{
													if (dig1.length() > 16) {
														Toast(con,
																"输入的数字整数不能超过16位！");
													} else if (dig2.length() > 2) {
														Toast(con,
																"输入的数字小数点之后只能有2位！");
													} else if (dig2.length() < 3) {
														if (dig1.equals("")) {
															if (inputStr6.length() == 2) {
																inputStr6 += "0";
															}
															String sss = String.valueOf(Double
																	.valueOf(inputStr6));
															// ettext.setText("0" +
															// sss);
															String d1 = String.valueOf(Double
																	.valueOf(inputStr6));
															String dd = d1.trim();
															String ddd[] = dd
																	.split("\\.");
															String ddd1 = ddd[0];
															String ddd2 = ddd[1];
															if (ddd2.length() == 1) {
																ettext.setText(String.valueOf(d.format(Double.parseDouble(sss)))
																		);
																etdlg.dismiss();
															} else {
																ettext.setText(String.valueOf(d.format(Double.parseDouble(sss))));
																etdlg.dismiss();
															}

														} else if (dig2.length() == 2) {

															String sss = String.valueOf(Double
																	.valueOf(inputStr6));
															String ssss = sss
																	.trim();
															String sssss[] = ssss
																	.split("\\.");
															String sssss1 = sssss[0];
															String sssss2 = sssss[1];
															if (sssss2.length() == 1) {
																ettext.setText(String.valueOf(d.format(Double.parseDouble(sss)))
																		);
																etdlg.dismiss();
															} else {
																ettext.setText(String.valueOf(d.format(Double.parseDouble(sss))));
																etdlg.dismiss();
															}

														} else if (dig2.length() == 1) {
															String sss = String.valueOf(Double
																	.valueOf(inputStr6));
															ettext.setText(String.valueOf(d.format(Double.parseDouble(sss)))
																	);
															etdlg.dismiss();
														}
														
													}	
												}
												
										
											}
										}
									}

								} catch (Exception e) {
									Toast(con, "输入格式有误！");

								}
							}
						} else if (dc.isAllDigit(inputStr6)) {
							if (Double.parseDouble(inputStr6) == 0) {
								Toast(con, "输入的交易金额要大于0！");
							} else {
								if (et.getText().toString().trim().length() > 16) {
									Toast(con, "输入的数字整数不能超过16位！");
								} else if (dc.isAllDigit(inputStr6)) {
									 ettext.setText(d.format(Double.parseDouble(et.getText().toString()))
												);
									etdlg.dismiss();
								}
							}
						} else {
							Toast(con, "输入的只能包含数字和小数点！");
						}
					}

					break;

				case R.id.yedtext7:
				case R.id.yedtext71:
				case R.id.g3et6:
				case R.id.z2edtext8:
				case R.id.c2et8:
					// case R.id.cimedtext3:
					if (et.getText().toString().trim().length() != 6) {
						Toast(con, "授权号应为6位数");

					} else if (et.getText().toString().trim().contains("*")
							|| et.getText().toString().trim().contains("x")
							|| et.getText().toString().trim().contains(".")) {
						Toast(con, "授权号应为6位整数");
					} else {
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					}
					break;

				// add by syc 5.19
				case R.id.yedtext81:
				case R.id.yedtext8:
				case R.id.z2edtext9:
				case R.id.g3et7:
				case R.id.c2et9:
					if (et.getText().toString().equals("")) {
						Toast(con, "卡号不能为空！");
					} else {
						if (dc.isAllDigit(et.getText().toString())) {
							if ((et.getText().toString().trim().substring(0, 1))
									.equals("0")) {
								Toast(con, "输入的卡号首位不能为0！");
							} else if (et.getText().toString().trim().length() != 16) {
								Toast(con, "卡号应为16位数！");
							} else {
								ettext.setText(et.getText().toString());
								etdlg.dismiss();
							}
						} else {
							Toast(con, "输入的卡号必须是数字！");
						}
					}
					break;
				case R.id.hyedit3:
					if ((et.getText().toString()).length() == 0) {
						Toast(con, "营业注册号不能为空");
					} else {
						if (!allowMaxLenthOfString(et.getText()
								.toString(), 60)) {
							Toast(con, "输入的字符过长");
						}
						else {
							ettext.setText(et.getText().toString());
							dialog.dismiss();
						}
					}
					break;
				case R.id.h1et8:
				case R.id.z1edtext8:
				case R.id.c1et8:
				case R.id.h2edtext5:
					if(et.getText().toString().length()!=0)
					{
						if (et.getText().toString().length() != 6) {
							Toast(con, "请填写6位邮编");
						} else if (et.getText().toString().equals("000000")) {
							Toast(con, "邮编填写错误");
						} else if (et.getText().toString().substring(3, 6)
								.equals("000")) {
							Toast(con, "请填写该地址详细邮编！");

							ettext.setText(et.getText().toString());
							etdlg.dismiss();
						} else if (dc.isAllDigit(et.getText().toString())) {
							ettext.setText(et.getText().toString());
							etdlg.dismiss();
						} else {
							Toast(con, "输入的必须是数字！");
						}
					}
					else
					{
						etdlg.dismiss();
						ettext.setText("");
					}
					break;
				case R.id.h2edtext11:
					if (et.getText().toString().length() != 0) {
						if (et.getText().toString().length() > 0
								&& !et.getText().toString().contains("."))

						{
							Toast(con, "经度长度必须包含一个小数点");
						} else {
							String tempstr = et.getText().toString();
							String str[] = tempstr.split("\\.", 2);
							if (str[0].length() == 0 || str[1].length() == 0) {
								Toast(con, "输入内容错误，请重新输入！");
							} else {
								if (dc.isData(str[0]) && dc.isData(str[1])) {
									if (str[1] != null && str[1].contains(".")) {
										Toast(con, "经度只能包含一个小数点");
									} else {
										if (str[1].length() > 6) {
											Toast(con, "经度小数点后面不能超过6位");
										} 
										else if(str[0].length() > 3)
										{
											Toast(con, "经度小数点前面不能超过3位，请重新输入！");
										}
										else {
											if (et.getText().toString()
													.length() <= 10) {
												if (dc.isJustDigitStar(et
														.getText().toString())) {
													ettext.setText(et.getText()
															.toString());
													etdlg.dismiss();
												}
											} else {
												Toast(con, "输入的经度长度不能超过10位！");
											}
										}
									}
								} else {
									Toast(con, "经度只能填写数字和小数点！");
								}

							}

						}
					} else {
						et.setText("");
						etdlg.dismiss();
					}
					break;

				case R.id.h2edtext19:
					if(et.getText().toString().length()>20)
					{
						Toast(con, "收单费率长度不能超过20位！");
					}
					String str = et.getText().toString().trim();
					if (str.contains("*") || str.contains("x")) {
						Toast(con, "输入的格式错误");
					} else if (str.equals("")) {
						ettext.setText("");
						etdlg.dismiss();
					} else {
						if (str.contains(".") && dc.isJustDigitStar(str)) {
							String[] po = str.split("\\.", 2);

							if(po.length!=2){
								Toast(con, "输入格式错误，收单费率应为小于1且大于0的小数");
							}else{
								System.out.println(po[0] + "--");
								if(po[0].equals("")){
									Toast(con, "输入格式错误，收单费率应为小于1且大于0的小数");
								}else if (po[1].contains(".")) {
									Toast(con, "输入的数字只能存在一个小数点");
								}else if(po[1].equals("")){
									Toast(con, "输入格式错误，收单费率应为小于1且大于0的小数");
								}else if(!po[0].equals("0")){
									Toast(con, "收单费率应为小于1且大于0的小数");
								}
								else if(Integer.parseInt(po[1]) == 0){
									Toast(con, "输入格式错误，收单费率应为小于1且大于0的小数");
								}
								else {
									
									ettext.setText(0 + "." + po[1]);
									etdlg.dismiss();
	
								}
							}
						
						}else {
							Double ii = Double.valueOf(str);
							if (ii >= 1 || ii <= 0) {
								Toast(con, "收单费率应为小于1且大于0的小数");
							} else if (str.length() > 6) {
								Toast(con, "输入字符过长");
							}
						}
					}

					break;
					
				case R.id.redh2edtext19:
					if(et.getText().toString().length()>20)
					{
						Toast(con, "收单费率长度不能超过20位！");
					}
					String str11 = et.getText().toString().trim();
					if (str11.contains("*") || str11.contains("x")) {
						Toast(con, "输入的格式错误");
					} else if (str11.equals("")) {
						ettext.setText("");
						etdlg.dismiss();
					} else {
						if (str11.contains(".") && dc.isJustDigitStar(str11)) {
							String[] po = str11.split("\\.", 2);
							if(po.length!=2){
								Toast(con, "输入格式错误，收单费率应为小于1且大于0的小数");
							}else{
								System.out.println(po[0] + "--");
								if(po[0].equals("")){
									Toast(con, "输入格式错误，收单费率应为小于1且大于0的小数");
								}else if (po[1].contains(".")) {
									Toast(con, "输入的数字只能存在一个小数点");
								}else if(po[1].equals("")){
									Toast(con, "输入格式错误，收单费率应为小于1且大于0的小数");
								}else if(!po[0].equals("0")){
									Toast(con, "收单费率应为小于1且大于0的小数");
								}
								else if(Integer.parseInt(po[1]) == 0){
									Toast(con, "输入格式错误，收单费率应为小于1且大于0的小数");
								}
								else {
									
									ettext.setText(0 + "." + po[1]);
									etdlg.dismiss();
	
								}
							}
						} else {
							Double ii = Double.valueOf(str11);
							if (ii >= 1 || ii <= 0) {
								Toast(con, "收单费率应为小于1且大于0的小数");
							} else if (str11.length() > 6) {
								Toast(con, "输入的字符过长");
							}
						}
					}

					break;

				case R.id.h2edtext12:
					if (et.getText().toString().length() != 0) {
						if (et.getText().toString().length() > 0
								&& !et.getText().toString().contains("."))

						{
							Toast(con, "纬度长度必须包含一个小数点");
						} else {
							String tempstr = et.getText().toString();
							String str1[] = tempstr.split("\\.", 2);
							if (str1[0].length() == 0 || str1[1].length() == 0) {
								Toast(con, "输入内容错误，请重新输入！");
							} else {
								if (dc.isData(str1[0]) && dc.isData(str1[1])) {
									if (str1[1] != null
											&& str1[1].contains(".")) {
										Toast(con, "纬度只能包含一个小数点");
									} else {
										if (str1[1].length() > 6) {
											Toast(con, "纬度小数点后面不能超过6位");
										} 
										else if(str1[0].length() > 3)
										{
											Toast(con, "纬度小数点前面不能超过3位，请重新输入！");
										}
										else {
											if (et.getText().toString()
													.length() <= 10) {
												if (dc.isJustDigitStar(et
														.getText().toString())) {
													ettext.setText(et.getText()
															.toString());
													etdlg.dismiss();
												}
											} else {
												Toast(con, "输入的纬度长度不能超过10位！");
											}
										}
									}
								} else {
									Toast(con, "纬度只能填写数字和小数点！");
								}

							}

						}
					} else {
						et.setText("");
						etdlg.dismiss();
					}
					break;

				// add by syc
				// case R.id.h2edtext14:
				case R.id.z1edtext4:
				case R.id.c1et4:
				case R.id.h1et4:
				case R.id.h1et11:
				case R.id.c1et11:
				case R.id.h2edtext3:
				case R.id.yedtext4:
				case R.id.z1edtext11:
				case R.id.g3et2:

					String inputStr1 = et.getText().toString();

					if (dc.isAllDigit1(et.getText().toString())) {

						if (inputStr1.contains("*")) {

							String digtal = inputStr1.trim();
							String Dia[] = digtal.split("\\*", 2);
							if (Dia[1].contains("*")) {
								Toast(con, "输入的数字只能存在一个*号！");

							} 
							else if (et.getText().toString().trim().length() > 30) {
								Toast(con, "电话不超过30位数！");
							}else {
								ettext.setText(et.getText().toString());
								etdlg.dismiss();
							}
						} else {
							if (et.getText().toString().trim()
									.equals("00000000")) {
								Toast(con, "输入的电话不能全为0！");
							} else if (dc.iszieo(et.getText().toString())) {
								Toast(con, "电话不能全部为0！");
							} else if (et.getText().toString().trim().length() > 30) {
								Toast(con, "电话不超过30位数！");
							} else {
								ettext.setText(et.getText().toString());
								etdlg.dismiss();
							}
						}
					} else {
						Toast(con, "输入的电话必须是数字！");
					}

					break;
				case R.id.redh1et11://最红公司信息品牌热线
				case R.id.redh1et4:
				case R.id.redh2edtext3:
					String inputStr11 = et.getText().toString();

					if (dc.isAllDigit1(et.getText().toString())) {

						if (inputStr11.contains("*")) {

							String digtal = inputStr11.trim();
							String Dia[] = digtal.split("\\*", 2);
							if (Dia[1].contains("*")) {
								Toast(con, "输入的数字只能存在一个*号！");

							} 
							else if (et.getText().toString().trim().length() > 20) {
								Toast(con, "电话不超过20位数！");
							}else {
								ettext.setText(et.getText().toString());
								etdlg.dismiss();
							}
						} else {
							if (et.getText().toString().trim()
									.equals("00000000")) {
								Toast(con, "输入的电话不能全为0！");
							} else if (dc.iszieo(et.getText().toString())) {
								Toast(con, "电话不能全部为0！");
							} else if (et.getText().toString().trim().length() > 20) {
								Toast(con, "电话不超过20位数！");
							} else {
								ettext.setText(et.getText().toString());
								etdlg.dismiss();
							}
						}
					} else {
						Toast(con, "输入的电话必须是数字！");
					}
					break;
				case R.id.h2edtext14:
				case R.id.c1et5:
				case R.id.z1edtext5:
					if (dc.isPhoneNum(et.getText().toString())
							|| et.getText().equals("")) {
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					} else {
						Toast(con, "输入手机首位必须为1且长度为11位");
					}
					break;
				case R.id.h1et5:
					if (dc.isPhoneNum(et.getText().toString())
							|| et.getText().equals("")) {
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					} else {
						Toast(con, "输入手机首位必须为1且长度为11位");
					}
					break;
				case R.id.h1et13:
				case R.id.z1edtext13:
				case R.id.c1et13:
					if (et.getText().toString().length() > 3) {
						Toast(con, "输入长度过长");
					} else {
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					}
					break;
				case R.id.redh1et13://最红公司信息录入经营年限
					if (et.getText().toString().length() > 10) {
						Toast(con, "输入长度过长");
					} else {
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					}
					break;
				case R.id.h2edtext15:
					if (et.getText().toString().length() > 30) {
						Toast(con, "输入长度过长");
					} else {
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					}
					break;
				case R.id.h3edtext1:
					// case R.id.h3edtext2:

				case R.id.h3edtext4:
				case R.id.h3edtext5:
					// case R.id.h3edtext6:
					// case R.id.h3edtext7:
					// case R.id.h3edtext8:
					// case R.id.h3edtext9:

				case R.id.editText1:
				case R.id.editText2:
				case R.id.editText3:
					// case R.id.editText4:
				case R.id.textView7:
				case R.id.textView6:

					if (et.getText().toString().trim().length() > 10) {
						Toast(con, "数量长度不可大于10位");
					} else if (et.getText().toString().trim().contains(".")
							|| et.getText().toString().trim().contains("*")
							|| et.getText().toString().trim().contains("x")) {
						Toast(con, "数量应为整数");

					} else {
						ettext.setText(et.getText().toString());
						etdlg.dismiss();
					}
					break;

				// case R.id.h3edtext2:
				case R.id.h3edtext3:
				case R.id.h3edtext6:
				case R.id.h3edtext7:
				case R.id.h3edtext8:
				case R.id.h3edtext9:
					if (et.getText().toString().trim().equals("")) {
						ettext.setText("");
						etdlg.dismiss();
					} else {
						if (et.getText().toString().trim().length() > 10) {
							Toast(con, "数量长度不可大于10位");
						} else if (et.getText().toString().trim().contains(".")
								|| et.getText().toString().trim().contains("*")
								|| et.getText().toString().trim().contains("x")) {
							Toast(con, "数量应为整数");

						} else {
							DecimalFormat d=new DecimalFormat("0.00");
						
//							ettext.setText(et.getText().toString());
							ettext.setText(d.format(Double.parseDouble(et.getText().toString())).split("\\.")[0]);
							etdlg.dismiss();
						}
					}
					break;
				case R.id.redh3edtext3:
				case R.id.redh3edtext6:
				case R.id.redh3edtext7:
				case R.id.redh3edtext8:
				case R.id.redh3edtext9:
					if (et.getText().toString().trim().equals("")) {
						ettext.setText("");
						etdlg.dismiss();
					} else {
						if (et.getText().toString().trim().length() > 5) {
							Toast(con, "数量长度不可大于5位");
						} else if (et.getText().toString().trim().contains(".")
								|| et.getText().toString().trim().contains("*")
								|| et.getText().toString().trim().contains("x")) {
							Toast(con, "数量应为整数");

						} else {
							DecimalFormat d=new DecimalFormat("0.00");
							ettext.setText(d.format(Double.parseDouble(et.getText().toString())).split("\\.")[0]);
							etdlg.dismiss();
						}
					}
					break;
				case R.id.h3edtext14:
					try
					{
						int a = 0;
						for (int idx = 0; idx < et.getText().toString().trim()
								.length(); idx++) {
							if (et.getText().toString().trim().charAt(idx) == '.') {
								a++;
							}

						}
						if (a > 1) {
							Toast(con, "输入格式错误");
						} else {
							// case R.id.editText4:
							if (et.getText().toString().trim().equals("")) {
								ettext.setText("");
								etdlg.dismiss();
							} else {
								if (et.getText().toString().trim().length() > 10) {
									Toast(con, "数量长度不可大于10位");
								} else if (et.getText().toString().trim()
										.contains("*")
										|| et.getText().toString().trim()
												.contains("x")) {
									Toast(con, "输入格式错误");

								}

								else {
									if(et.getText().toString().trim()
												.contains("."))
									{
										String digtal =et.getText().toString().trim();									
										String Dia[] = digtal.split("\\.", 2);
										if(Dia[0].length()==0)
										{
											Toast(con, "输入格式错误");	
										}
										else
										{
											if(Dia[1].length()==0)
											{
												ettext.setText(String.valueOf(new BigDecimal(Dia[0])).split("\\.", 2)[0]);	
												etdlg.dismiss();
											}
											else
											{
												ettext.setText(String.valueOf(new BigDecimal(Dia[0])).split("\\.", 2)[0]+"."+Dia[1]);	
												etdlg.dismiss();	
												
											}
											
										}
																												
									}
									else
									{
										
										ettext.setText(String.valueOf(new BigDecimal(et.getText().toString())).split("\\.", 2)[0]);	
										etdlg.dismiss();
									}
									
									
								}
							
								// case R.id.cimedtext4:
							}
						}	
					}
					catch(Exception e)
					{
						
					}
				
					break;
				}
			}
		});

		etdlg.setContentView(DialogView);
		// etdlg.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关
		etdlg.setCancelable(false);

		etdlg.show();
	}
	
	
	
	// 数字键盘
	public void etDialog2(final Context con, final TextView ettext,
			final int etid, String string) {

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
		final View DialogView = factory.inflate(R.layout.etdialog2, null);
		etdlg = new Dialog(con, R.style.FullHeightDialog);
		Window dialogWindow = etdlg.getWindow();
		// WindowManager.LayoutParams lp =dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.BOTTOM);
		etdialog_gv01 = (GridView) DialogView.findViewById(R.id.etdialog_gv01);
		string_ed = (TextView) DialogView.findViewById(R.id.string_textViewnum);
		string_ed.setText(string);
		et = (EditText) DialogView.findViewById(R.id.etdialog_et);
		et01 = (EditText) DialogView.findViewById(R.id.etdialog_tv01);

		et.setInputType(InputType.TYPE_NULL);
		et01.setInputType(InputType.TYPE_NULL);

		sb = sb.delete(0, sb.length());
		sb2 = sb2.delete(0, sb2.length());

		et.setText(ettext.getText().toString());
		sb2.append(ettext.getText().toString());

		etdialog_gv01.setAdapter(new GridImageAdapter(con, textstr));
		etdialog_gv01.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				FrameLayout ll = (FrameLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView bt = (TextView) ll.getChildAt(0);

				if (et01.hasFocus()) {
					sb.append(bt.getText().toString());
					et01.setText(sb);

				} else {

					sb2.append(bt.getText().toString());
					et.setText(sb2);

				}
			}
		});

		etdialog_bt01 = (Button) DialogView.findViewById(R.id.etdialog_bt01);
		etdialog_bt02 = (Button) DialogView.findViewById(R.id.etdialog_bt02);
		etdialog_bt03 = (Button) DialogView.findViewById(R.id.etdialog_bt03);
		etdialog_bt04 = (Button) DialogView.findViewById(R.id.etdialog_bt04);
		etdialog_bt05 = (Button) DialogView.findViewById(R.id.etdialog_bt05);
		etdialog_bt06 = (Button) DialogView.findViewById(R.id.etdialog_bt06);
		etdialog_pointbt = (Button) DialogView
				.findViewById(R.id.etdialog_pointbt);

		etdialog_bt02.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					sb.append(etdialog_bt02.getText().toString());
					et01.setText(sb);
				} else {

					sb2.append(etdialog_bt02.getText().toString());
					et.setText(sb2);
				}
			}
		});

		etdialog_bt03.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					sb.append(etdialog_bt03.getText().toString());
					et01.setText(sb);
				} else {

					sb2.append(etdialog_bt03.getText().toString());
					et.setText(sb2);
				}
			}
		});
		etdialog_pointbt.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					sb.append(etdialog_pointbt.getText().toString());
					et01.setText(sb);
				} else {

					sb2.append(etdialog_pointbt.getText().toString());
					et.setText(sb2);
				}
			}
		});

		etdialog_bt04.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					sb.append(etdialog_bt04.getText().toString());
					et01.setText(sb);
				} else {

					sb2.append(etdialog_bt04.getText().toString());
					et.setText(sb2);
				}
			}
		});
		etdialog_bt05.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (et01.hasFocus()) {
					if (sb.length() > 0) {
						sb = sb.delete(sb.length() - 1, sb.length());
						et01.setText(sb);
					}

				} else {
					if (sb2.length() > 0) {
						sb2 = sb2.delete(sb2.length() - 1, sb2.length());
						et.setText(sb2);
					}

				}
			}
		});
		etdialog_bt06.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// if(etid==R.id.cardcheck_etgone)
				// {
				// CardCheck.cardcheck_cb02.setChecked(false);
				// }
				etdlg.dismiss();
			}
		});
		etdialog_bt01.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				switch (etid) {
				
				}
			}
		});

		etdlg.setContentView(DialogView);
		// etdlg.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关
		etdlg.setCancelable(false);

		etdlg.show();
	}
	
	

	public void Dialog3(final Context con, final LinearLayout linearLayout,
			final TextView textArray, final int ID) {

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
		final View DialogView = factory.inflate(R.layout.ra_layout, null);
		dialog = new Dialog(con, R.style.FullHeightDialog);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.TOP);

		et = (EditText) DialogView.findViewById(R.id.dialog_editText);
		et.setText(textArray.getText().toString());
		et.setSelection(textArray.getText().toString().length());
		sb = sb.delete(0, sb.length());
		ok_button = (Button) DialogView.findViewById(R.id.ok_button);
		ok_button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (a) {
					switch (n) {
					case 9:
						new EditDialog().Dialog(con, null,
								HeYueQianDing_2.h1et7021, R.id.h1et7021, "");

						break;
					}

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

	/*
	 * 问题提报
	 */
	public void tbdialog(final Context con, final TextView text1, String string,
			String[] array) {
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
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);

		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);

		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		lv.setAdapter(new SpinnerAdapter(con, array));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);//
				text1.setText(tvn.getText().toString());
				dlg.dismiss();
			}
			});		
		// 创建对话框

		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
		
	}
	
	public void dialog(final Context con, final TextView text1, String string,
			String[] array) {
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
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);

		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);

		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		lv.setAdapter(new SpinnerAdapter(con, array));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);//
				text1.setText(tvn.getText().toString());
				if(l==1)
				{
					ZhongBuChaXun4.text2.setText("");
					l=0;
				}
				if(l==2)
				{
					HeYueQianDing_1.text2.setText("");
					l=0;
				}
				if(l==3)
				{
					RedBigArea.text2.setText("");
					l=0;
				}
				dlg.dismiss();
				// lxp
				if (istwo) {
					switch (num) {

					case 1:// 是否收单
						if (arg2 == 0) {
							HeYueQianDing_3.linlay[18].setEnabled(true);
							// ZhongBuHuoDong3.linlay[18].setEnabled(true);
							// ChengShiHuoDong3.linlay[18].setEnabled(true);
						} else {
							HeYueQianDing_3.textArray[18].setText("");
							HeYueQianDing_3.linlay[18].setEnabled(false);

						}

						break;
					case 2:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							HeYueQianDing_3.linlay[6].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[6].setText("");
							HeYueQianDing_3.linlay[6].setEnabled(false);

						}
						break;
					case 3:// 是否收单
						if (arg2 == 0) {
							ZhongBuHuoDong3.linlay[18].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[18].setText("");
							ZhongBuHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 4:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ZhongBuHuoDong3.linlay[6].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[6].setText("");
							ZhongBuHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					case 5:// 是否收单
						if (arg2 == 0) {
							ChengShiHuoDong3.linlay[18].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[18].setText("");
							ChengShiHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 6:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ChengShiHuoDong3.linlay[6].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[6].setText("");
							ChengShiHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					case 7:// 是否收单
						if (arg2 == 0) {
							HeYueQianDing_3_3.linlay[18].setEnabled(true);

						} else {
							HeYueQianDing_3_3.textArray[18].setText("");
							HeYueQianDing_3_3.linlay[18].setEnabled(false);

						}
						break;
					case 8:// 是否收单
						if (arg2 == 0) {
							RedShopMessage.linlay[16].setEnabled(true);
						} else {
							RedShopMessage.textArray[16].setText("");
							RedShopMessage.linlay[16].setEnabled(false);
						}
						break;
					case 9:// 是否发送短信
						if(!ZhongBuHuoDong2.isMessageNew.equals("")&&ZhongBuHuoDong2.isMessageNew.length()>0)
						{
							if(ZhongBuHuoDong2.isMessageNew.equals("N"))
							{
								text1.setText("否");
							}
							else
							{
								text1.setText("是");
							}
						}
						else
						{
							text1.setText("是");
						}
						break;
						
						
					}
					istwo = false;
				}
				// lxp

			}
		});
		// 创建对话框

		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);

		dlg.show();
	}
	
	
	public void red_dialog(final Context con, final TextView text1, String string,
			String[] array) {
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
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);

		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);

		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		lv.setAdapter(new SpinnerAdapter(con, array));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);//
				text1.setText(tvn.getText().toString());
				if(l==3)
				{
					RedBigArea.text2.setText("");
					l=0;
				}
				dlg.dismiss();
				// lxp
				if (istwo) {
					switch (num) {
					case 4:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ZhongBuHuoDong3.linlay[6].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[6].setText("");
							ZhongBuHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					case 8:// 是否收单
						if (tvn.getText().toString().equals("是")) {
							RedShopMessage.textArray[16].setText("");
							RedShopMessage.linlay[16].setEnabled(true);
						} else {
							RedShopMessage.textArray[16].setText("");
							RedShopMessage.linlay[16].setEnabled(false);
						}
						break;
					case 9:// 行业活动
						String redid="";
						if(tvn.getText().toString().equals("超市"))
						{
							redid = "CS";
						}
						else if(tvn.getText().toString().equals("加油站"))
						{
							redid = "JYZ";
						}
						String[] succ =DB.redFridayInfoQuery(con,"redFridayInfo",
								" where businessName = '"+des.jiaMi(redid)+"'");
						RedActivityMessage.textArray[12].setText(succ[1]);
						RedActivityMessage.textArray[13].setText(succ[0]);
						break;
					case 10:// 是否收单
						String redid1="";
						if(tvn.getText().toString().equals("超市"))
						{
							redid1 = "CS";
						}
						else if(tvn.getText().toString().equals("加油站"))
						{
							redid1 = "JYZ";
						}
						String[] succ1 =DB.redFridayInfoQuery(con,"redFridayInfo",
								" where businessName = '"+des.jiaMi(redid1)+"'");
						RedActivityMessageBox.textArray[12].setText(succ1[1]);
						RedActivityMessageBox.textArray[13].setText(succ1[0]);
						break;
					case 11:// 是否收单
						if (tvn.getText().toString().equals("是")) {
							RedShopMessageBox.textArray[16].setText("");
							RedShopMessageBox.linlay[16].setEnabled(true);
						} else {
							RedShopMessageBox.textArray[16].setText("");
							RedShopMessageBox.linlay[16].setEnabled(false);
						}
						break;
					}
					istwo = false;
				}
				// lxp

			}
		});
		// 创建对话框

		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);

		dlg.show();
	}
	

	public void ExitApp(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要退出？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}

	
	public void ExitApp2(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						TakePhotoActivity sa = new TakePhotoActivity();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}
	
	public void RedExitApp2(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						RedTakePhotoActivity sa = new RedTakePhotoActivity();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}
	
	
	public void RedExitAppBox(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						RedTakePhotoActivityBox sa = new RedTakePhotoActivityBox();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}

	

	public void ExitApp2222(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						CGXYHTakePhotoActivity2 sa = new CGXYHTakePhotoActivity2();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}
	public void ExitAppTake(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						TakePhotoActivity2 sa = new TakePhotoActivity2();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}
	public static void ExitApp22(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						TakePhotoActivity_1_5 sa = new TakePhotoActivity_1_5();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}

	public static void ExitApp71(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						SHGongDanWeiHu_7 sa = new SHGongDanWeiHu_7();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}
	public static void ExitApp72(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						CGXSHGongDanWeiHu_72 sa = new CGXSHGongDanWeiHu_72();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对话框
	}

	
	public void ExitApp4(final Context con) {

		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						CGXYHTakePhotoActivity sa = new CGXYHTakePhotoActivity();
						sa.saveData2(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对
	}
	public void ExitApp44(final Context con) {

		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						YouHuiTakePhotoActivity sa = new YouHuiTakePhotoActivity();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对
	}
	public void ExitApp14(final Context con) {

		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						COfCOfYHTakePhotoActivity sa = new COfCOfYHTakePhotoActivity();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对
	}

	public void ExitApp5(final Context con) {

		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						CaoYouHuiTakePhotoActivity2 sa = new CaoYouHuiTakePhotoActivity2();
						sa.saveData2(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对
	}
	public void ExitAppYou55(final Context con) {

		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						YouHuiTakePhotoActivity2 sa = new YouHuiTakePhotoActivity2();
						sa.saveData2(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对
	}
	
	public void ExitApp6(final Context con) {

		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						GDTPhotoActivity sa = new GDTPhotoActivity();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对
	}


	public void ExitApp66(final Context con) {

		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						GongDanTakePhotoActivity sa = new GongDanTakePhotoActivity();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对
	}


	public void ExitApp7(final Context con) {
		AlertDialog dlg = null;
		Builder b = new AlertDialog.Builder(con);
		b.setTitle("提示");
		b.setMessage("是否要存入草稿箱？");// 设置信息
		b.setCancelable(false);
		b.setPositiveButton("是", new DialogInterface.OnClickListener() {// 设置监听事件
					public void onClick(DialogInterface dialog, int which) {

						CopyOfYouHuiTakePhotoActivity sa = new CopyOfYouHuiTakePhotoActivity();
						sa.saveData1(con);
						Intent intent = new Intent();
						intent.setClass(con, TuanDuiShangHuActivity.class);
						con.startActivity(intent);
						((Activity) con).finish();
					}
				});

		b.setNegativeButton("否",// 设置取消按钮
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		dlg = b.create();// 创建对话框
		dlg.show();// 显示对
	}

	public void Dialog4(final Context con, final TextView textArray,

	final int ID) {

		LayoutInflater factory = LayoutInflater.from(con);
		// 得到自定义对话框
		final View DialogView = factory.inflate(R.layout.ra_layout, null);
		final Dialog dialog = new Dialog(con, R.style.FullHeightDialog);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.TOP);

		et = (EditText) DialogView.findViewById(R.id.dialog_editText);
		et.setText(textArray.getText().toString());
		et.setSelection(textArray.getText().toString().length());
		sb = sb.delete(0, sb.length());
		ok_button = (Button) DialogView.findViewById(R.id.ok_button);
		ok_button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				switch (ID) {
				case R.id.hyedit3:// 营业号
					if ((et.getText().toString()).length() == 0) {
						Toast(con, "营业注册号不能为空");

					} else {
						if (!allowMaxLenthOfString(et.getText()
								.toString(), 20)) {
							Toast(con, "输入的字符不能超过20个");

						}else if(!isAddress3(et.getText().toString())){
							Toast(con, "请输入纯数字和大写字母");
						}
						else {
							textArray.setText(et.getText().toString());
							dialog.dismiss();
						}
						break;
					}
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

	public boolean allowMaxLenthOfString(String s, int charNum) {// 字符串长度校验

		int num = 0;
		for (int i = 0; i < s.length(); i++) {
			String tmp = s.substring(i, i + 1);
			if (tmp.getBytes().length == 3) {
				num += 2;
			} else if (tmp.getBytes().length == 1) {
				num += 1;
			}

		}
		if (num <= charNum) {
			return true;
		}
		return false;
	}

	public boolean isAddress(String address) {// 中英文数字校验
		int i = 0, j = 0, k = 0;
		int count = address.length();
		Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
		Matcher m = pattern.matcher(address);
		while (m.find()) {
			i++;
		}
		for (int idx = 0; idx < count; idx++) {
			char c = address.charAt(idx);
			int tmp = (int) c;
			if ((tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z')) {
				j++;
			}

			if (Character.isDigit(address.charAt(idx))) {
				k++;
			}

		}
		if ((i + j + k) == count) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean isAddress2(String address) {// 英文数字校验
		int j = 0,k = 0;
		int count = address.length();
		for (int idx = 0; idx < count; idx++) {
			char c = address.charAt(idx);
			int tmp = (int) c;
			if ((tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z')) {
				j++;
			}
			if (Character.isDigit(address.charAt(idx))) {
				k++;
			}
		}
		if ((j + k) == count) {
			return true;
		} else {
			return false;
		}

	}
	/*
	 * 校验是否全为数字和大写字母
	 */
	public boolean isAddress3(String address) {// 英文数字校验
		int j = 0,k = 0;
		int count = address.length();
		for (int idx = 0; idx < count; idx++) {
			char c = address.charAt(idx);
			int tmp = (int) c;
			if (tmp >= 'A' && tmp <= 'Z') {
				j++;
			}
			if (Character.isDigit(address.charAt(idx))) {
				k++;
			}
		}
		if ((j + k) == count) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isNumber(String num) {// 判断是否为数字
		int k = 0;
		if (num != null) {
			for (int i = 0; i < num.length(); i++) {
				if (Character.isDigit(num.charAt(i))) {
					k++;

				}

			}
			if (k == num.length() && !num.substring(0, 1).equals("0")) {
				return true;
			}
		}

		return false;
	}

	public void dialog21(final Context con, final TextView text1,
			String string, Object[] array) {
		// ,final List<String> l1, final List<String> l2
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
		
		list.clear();
		listId.clear();
		LayoutInflater factory = LayoutInflater.from(con);

		// 得到自定义对话框
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);
		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		lv.setAdapter(new SpinnerAdapter2(con, array));

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
//				List<String> list = new ArrayList<String>();
				// List<String> list1 =new ArrayList<String>();
				// list1.clear();

				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);
				final String zhus = tvn.getText().toString();
				// System.out.println("你点击的主行业名****"+zhus);
				text1.setText(zhus);
//				String zhuid = DB.hyquery(con, zhus);
				final String zhuid = zhuhangye(zhus,ZhongBuHuoDong3.s1,ZhongBuHuoDong3.s);
				System.out.println("你点击的主行业的id" + zhuid);

				for (int i = 0; i < ZhongBuHuoDong3.s2.size(); i++) {

					if (zhuid.equals(ZhongBuHuoDong3.s2.get(i).substring(0, 4))) {

						list.add(ZhongBuHuoDong3.s3.get(i).toString());
						listId.add(ZhongBuHuoDong3.s2.get(i).toString());
						num1 = 1;

					}

				}

				dlg.dismiss();

				name = list.toArray();

				if (list.size() == 0) {
					text1.setText(zhus);

					ZhongBuHuoDong3.textArray[19].setText("");

				} else {

					new EditDialog().dialog3(con,
							ZhongBuHuoDong3.textArray[19], "副行业", name);

				}

				if (istwo) {
					switch (num) {
					case 1:// 是否收单
						if (arg2 == 0) {
							HeYueQianDing_3.linlay[18].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[18].setText("");
							HeYueQianDing_3.linlay[18].setEnabled(false);

						}

						break;
					case 2:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							HeYueQianDing_3.linlay[6].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[6].setText("");
							HeYueQianDing_3.linlay[6].setEnabled(false);

						}
						break;
					case 3:// 是否收单
						if (arg2 == 0) {
							ZhongBuHuoDong3.linlay[18].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[18].setText("");
							ZhongBuHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 4:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ZhongBuHuoDong3.linlay[6].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[6].setText("");
							ZhongBuHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					case 5:// 是否收单
						if (arg2 == 0) {
							ChengShiHuoDong3.linlay[18].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[18].setText("");
							ChengShiHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 6:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ChengShiHuoDong3.linlay[6].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[6].setText("");
							ChengShiHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					}
					istwo = false;
				}
				// lxp

			}
		});
		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
	}
	public void dialog21111(final Context con, final TextView text1,
			String string, Object[] array) {
		// ,final List<String> l1, final List<String> l2
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
		
		list.clear();
		listId.clear();
		LayoutInflater factory = LayoutInflater.from(con);

		// 得到自定义对话框
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);
		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		lv.setAdapter(new SpinnerAdapter2(con, array));

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
//				List<String> list = new ArrayList<String>();
				// List<String> list1 =new ArrayList<String>();
				// list1.clear();

				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);
				final String zhus = tvn.getText().toString();
				// System.out.println("你点击的主行业名****"+zhus);
				text1.setText(zhus);
//				String zhuid = DB.hyquery(con, zhus);
				String zhuid = null;
				if(num==4)
				{
					zhuid = zhuhangye(zhus,RedShopMessage.s1,RedShopMessage.s);
					for (int i = 0; i < RedShopMessage.s2.size(); i++) 
					{
						if (zhuid.equals(RedShopMessage.s2.get(i).substring(0, 4))) 
						{
							list.add(RedShopMessage.s3.get(i).toString());
							listId.add(RedShopMessage.s2.get(i).toString());
							num1 = 1;
						}
					}
					dlg.dismiss();
					name = list.toArray();
					if (list.size() == 0) {
						text1.setText(zhus);
						RedShopMessage.textArray[17].setText("");

					} else {
						RedShopMessage.textArray[17].setText("");
						new EditDialog().dialog3(con,
								RedShopMessage.textArray[17], "副行业", name);
					}
				}
				else if(num==7)
				{
					zhuid = zhuhangye(zhus,RedShopMessageBox.s1,RedShopMessageBox.s);
					for (int i = 0; i < RedShopMessageBox.s2.size(); i++) 
					{
						if (zhuid.equals(RedShopMessageBox.s2.get(i).substring(0, 4))) 
						{
							list.add(RedShopMessageBox.s3.get(i).toString());
							listId.add(RedShopMessageBox.s2.get(i).toString());
							num1 = 1;
						}
					}
					dlg.dismiss();
					name = list.toArray();
					if (list.size() == 0) {
						text1.setText(zhus);
						RedShopMessageBox.textArray[17].setText("");

					} else {
						RedShopMessageBox.textArray[17].setText("");
						new EditDialog().dialog3(con,
								RedShopMessageBox.textArray[17], "副行业", name);
					}
				}
				System.out.println("你点击的主行业的id" + zhuid);

				if (istwo) {
					switch (num) {
					case 3:// 是否收单
						if (arg2 == 0) {
							RedShopMessage.linlay[16].setEnabled(true);
						} else {
							RedShopMessage.textArray[16].setText("");
							RedShopMessage.linlay[16].setEnabled(false);
						}

						break;
					case 4:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							RedShopMessage.linlay[6].setEnabled(true);
						} else {
							RedShopMessage.textArray[6].setText("");
							RedShopMessage.linlay[6].setEnabled(false);
						}
						break;
					case 7:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							RedShopMessageBox.linlay[6].setEnabled(true);
						} else {
							RedShopMessageBox.textArray[6].setText("");
							RedShopMessageBox.linlay[6].setEnabled(false);
						}
						break;
					}
					istwo = false;
				}
				// lxp

			}
		});
		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
	}
	public void dialog22(final Context con, final TextView text1,
			String string, Object[] array) {
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
		// ,final List<String> l1, final List<String> l2
		list.clear();
		listId.clear();
		LayoutInflater factory = LayoutInflater.from(con);

		// 得到自定义对话框
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);
		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		lv.setAdapter(new SpinnerAdapter2(con, array));

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
//				List<String> list = new ArrayList<String>();
				// List<String> list1 =new ArrayList<String>();
				// list1.clear();

				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);
				final String zhus = tvn.getText().toString();
				// System.out.println("你点击的主行业名****"+zhus);
				text1.setText(zhus);
//				String zhuid = DB.hyquery(con, zhus);
				final String zhuid = zhuhangye(zhus,ChengShiHuoDong3.s1,ChengShiHuoDong3.s);
				System.out.println("你点击的主行业的id" + zhuid);

				for (int i = 0; i < ChengShiHuoDong3.s2.size(); i++) {

					if (zhuid
							.equals(ChengShiHuoDong3.s2.get(i).substring(0, 4))) {

						list.add(ChengShiHuoDong3.s3.get(i).toString());
						listId.add(ChengShiHuoDong3.s2.get(i).toString());
						num1 = 1;

					}

				}

				dlg.dismiss();

				name = list.toArray();

				if (list.size() == 0) {
					text1.setText(zhus);

					ChengShiHuoDong3.textArray[19].setText("");

				} else {

					new EditDialog().dialog3(con,
							ChengShiHuoDong3.textArray[19], "副行业", name);

				}

				if (istwo) {
					switch (num) {
					case 1:// 是否收单
						if (arg2 == 0) {
							HeYueQianDing_3.linlay[18].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[18].setText("");
							HeYueQianDing_3.linlay[18].setEnabled(false);

						}

						break;
					case 2:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							HeYueQianDing_3.linlay[6].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[6].setText("");
							HeYueQianDing_3.linlay[6].setEnabled(false);

						}
						break;
					case 3:// 是否收单
						if (arg2 == 0) {
							ZhongBuHuoDong3.linlay[18].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[18].setText("");
							ZhongBuHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 4:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ZhongBuHuoDong3.linlay[6].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[6].setText("");
							ZhongBuHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					case 5:// 是否收单
						if (arg2 == 0) {
							ChengShiHuoDong3.linlay[18].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[18].setText("");
							ChengShiHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 6:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ChengShiHuoDong3.linlay[6].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[6].setText("");
							ChengShiHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					}
					istwo = false;
				}
				// lxp

			}
		});
		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
	}
	
	
	
	public void dialog2(final Context con, final TextView text1, String string,
			Object[] array) {
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
		// ,final List<String> l1, final List<String> l2
		list.clear();
		listId.clear();
		LayoutInflater factory = LayoutInflater.from(con);

		// 得到自定义对话框
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);
		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		lv.setAdapter(new SpinnerAdapter2(con, array));

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				
				// List<String> list1 =new ArrayList<String>();
				// list1.clear();

				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);
				final String zhus = tvn.getText().toString();
				// System.out.println("你点击的主行业名****"+zhus);
				text1.setText(zhus);
//				final String zhuid = DB.hyquery(con, zhus);
				final String zhuid = zhuhangye(zhus,HeYueQianDing_3.s1,HeYueQianDing_3.s);
				// System.out.println("你点击的主行业的id" + zhuid);

				for (int i = 0; i < HeYueQianDing_3.s2.size(); i++) {
					// System.out.println(HeYueQianDing_3.s2.get(i));
					// System.out.println(HeYueQianDing_3.s2.get(i).substring(0,
					// 4));
					if (zhuid.equals(HeYueQianDing_3.s2.get(i).substring(0, 4))) {

						list.add(HeYueQianDing_3.s3.get(i).toString());
						listId.add(HeYueQianDing_3.s2.get(i).toString());
						num1 = 1;

					}

				}

				dlg.dismiss();

				name = list.toArray();

				if (list.size() == 0) {
					text1.setText(zhus);
					HeYueQianDing_3.textArray[19].setText("");

				} else {
					dialog3(con, HeYueQianDing_3.textArray[19], "副行业", name);

				}

				if (istwo) {
					switch (num) {
					case 1:// 是否收单
						if (arg2 == 0) {
							HeYueQianDing_3.linlay[18].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[18].setText("");
							HeYueQianDing_3.linlay[18].setEnabled(false);

						}

						break;
					case 2:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							HeYueQianDing_3.linlay[6].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[6].setText("");
							HeYueQianDing_3.linlay[6].setEnabled(false);

						}
						break;
					case 3:// 是否收单
						if (arg2 == 0) {
							ZhongBuHuoDong3.linlay[18].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[18].setText("");
							ZhongBuHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 4:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ZhongBuHuoDong3.linlay[6].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[6].setText("");
							ZhongBuHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					case 5:// 是否收单
						if (arg2 == 0) {
							ChengShiHuoDong3.linlay[18].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[18].setText("");
							ChengShiHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 6:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ChengShiHuoDong3.linlay[6].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[6].setText("");
							ChengShiHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					case 8:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							HeYueQianDing_3.linlay[6].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[6].setText("");
							HeYueQianDing_3.linlay[6].setEnabled(false);

						}
						break;
					}
					istwo = false;
				}
				// lxp

			}
		});
		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
	}

	public void dialog22222(final Context con, final TextView text1,
			String string, Object[] array) {
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
		// ,final List<String> l1, final List<String> l2
		list.clear();
		listId.clear();
		LayoutInflater factory = LayoutInflater.from(con);

		// 得到自定义对话框
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);
		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		lv.setAdapter(new SpinnerAdapter2(con, array));

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				
				// List<String> list1 =new ArrayList<String>();
				// list1.clear();

				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);
				final String zhus = tvn.getText().toString();
				// System.out.println("你点击的主行业名****"+zhus);
				text1.setText(zhus);
//				final String zhuid = DB.hyquery(con, zhus);
				final String zhuid = zhuhangye(zhus,HeYueQianDing_3_3.s1,HeYueQianDing_3_3.s);
				System.out.println("你点击的主行业的id" + zhuid);

				for (int i = 0; i < HeYueQianDing_3_3.s2.size(); i++) {

					if (zhuid.equals(HeYueQianDing_3_3.s2.get(i)
							.substring(0, 4))) {

						list.add(HeYueQianDing_3_3.s3.get(i).toString());
						listId.add(HeYueQianDing_3_3.s2.get(i).toString());
						num1 = 1;

					}

				}

				dlg.dismiss();

				name = list.toArray();

				if (list.size() == 0) {
					text1.setText(zhus);
					HeYueQianDing_3_3.textArray[19].setText("");

				} else {
					new EditDialog().dialog3(con,
							HeYueQianDing_3_3.textArray[19], "副行业", name);

				}

				if (istwo) {
					switch (num) {

					case 8:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							HeYueQianDing_3_3.linlay[6].setEnabled(true);

						} else {
							HeYueQianDing_3_3.textArray[6].setText("");
							HeYueQianDing_3_3.linlay[6].setEnabled(false);

						}
						break;
					}
					istwo = false;
				}
				// lxp

			}
		});
		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
	}

	
	public String zhuhangye(String zhuname,List<String> s1,List<String> s)
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
	
	public void dialog3(Context con, final TextView text1, String string,
			Object[] array) {
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
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);
		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		lv.setAdapter(new SpinnerAdapter2(con, array));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法

				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);//
				text1.setText(tvn.getText().toString());
				dlg.dismiss();

			}
		});

		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
	}

	public void dialogOB(final Context con, final TextView text1,
			String string, Object[] array) {
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
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		et.setText(string);
		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		lv.setAdapter(new SpinnerAdapter2(con, array));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);//
				text1.setText(tvn.getText().toString());
				ChengShiHuoDong22.number = ChengShiHuoDong22.list22.get(arg2)
						.toString();
				// Log.i("output", ChengShiHuoDong22.number);
				// list.add(ChengShiHuoDong22.list22.get(arg2));
				dlg.dismiss();
				// lxp

			}

		});
		// 创建对话框

		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
	}

	public void dialogOB2(final Context con, final TextView text1,
			String string, Object[] array) {
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
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);
		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		et.setText(string);
		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		lv.setAdapter(new SpinnerAdapter2(con, array));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);//
				text1.setText(tvn.getText().toString());
//				ZhongBuHuoDong2.number = ZhongBuHuoDong2.list22.get(arg2)
//						.toString();
//				Log.i("output", ZhongBuHuoDong2.number);
				// list.add(ChengShiHuoDong22.list22.get(arg2));
				
				
				System.out.println("arg2======="+arg2);
				
				dlg.dismiss();
				// lxp
				if (istwo) {
					switch (num) {
					case 1:// 是否收单
						if (arg2 == 0) {
							HeYueQianDing_3.linlay[18].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[18].setText("");
							HeYueQianDing_3.linlay[18].setEnabled(false);

						}

						break;
					case 2:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							HeYueQianDing_3.linlay[6].setEnabled(true);

						} else {
							HeYueQianDing_3.textArray[6].setText("");
							HeYueQianDing_3.linlay[6].setEnabled(false);

						}
						break;
					case 3:// 是否收单
						if (arg2 == 0) {
							ZhongBuHuoDong3.linlay[18].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[18].setText("");
							ZhongBuHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 4:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ZhongBuHuoDong3.linlay[6].setEnabled(true);
						} else {
							ZhongBuHuoDong3.textArray[6].setText("");
							ZhongBuHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					case 5:// 是否收单
						if (arg2 == 0) {
							ChengShiHuoDong3.linlay[18].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[18].setText("");
							ChengShiHuoDong3.linlay[18].setEnabled(false);
						}

						break;
					case 6:// 行业
						if (tvn.getText().toString().equals("餐饮")) {
							ChengShiHuoDong3.linlay[6].setEnabled(true);
						} else {
							ChengShiHuoDong3.textArray[6].setText("");
							ChengShiHuoDong3.linlay[6].setEnabled(false);
						}
						break;
					}
					istwo = false;
				}
			}
		});
		// 创建对话框

		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
	}
	
	
	public void ActiveDialog(final Context con, final TextView text1,
			String string, final String[][] array) {
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
		final View DialogView = factory.inflate(R.layout.spdialog, null);
		dlg = new Dialog(con, R.style.FullHeightDialog);
		TextView et = (TextView) DialogView.findViewById(R.id.spdialog_title);
		Button btback = (Button) DialogView.findViewById(R.id.btback);
		btback.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		et.setText(string);
		ListView lv = (ListView) DialogView.findViewById(R.id.spdialog_lv);
		lv.setAdapter(new SpinnerAdapter2(con, array[0]));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {// 重写选项被单击事件的处理方法
				LinearLayout ll = (LinearLayout) arg1;// 获取当前选中选项对应的LinearLayout
				TextView tvn = (TextView) ll.getChildAt(0);//
				text1.setText(tvn.getText().toString());
				
				System.out.println("arg2======="+arg2);
				System.out.println(array[0][arg2]);
				System.out.println(array[1][arg2]);
				
				ZhongBuHuoDong2.strActiveId = array[1][arg2];
				
				//查询所有字段数据，条件：活动主题
				String[] ActiveStr = DB.ZBActive(con, "where subject = '"+des.jiaMi(array[0][arg2])+"'");
				
				//取得全部是否统一控制字段
				if(ActiveStr[5].equals("Y")&&!ActiveStr[4].equals(""))//签约日期是否统一控制
				{
					ZhongBuHuoDong2.h4edtext5.setText(ActiveStr[4].substring(0, 4)+"-"+ActiveStr[4].substring(4, 6)+"-"+ActiveStr[4].substring(6, 8));
					ZhongBuHuoDong2.h5leout5.setEnabled(false);
				}
				else
				{
					if(!ActiveStr[4].equals("")&&!ActiveStr[4].equals("19700101"))
					{
						ZhongBuHuoDong2.h4edtext5.setText(ActiveStr[4].substring(0, 4)+"-"+ActiveStr[4].substring(4, 6)+"-"+ActiveStr[4].substring(6, 8));
						ZhongBuHuoDong2.h5leout5.setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.h4edtext5.setText("");
						ZhongBuHuoDong2.h5leout5.setEnabled(true);
					}
				}
				if(ActiveStr[7].equals("Y")&&!ActiveStr[6].equals(""))//活动优惠开始日期是否统一控制
				{
					ZhongBuHuoDong2.textArray[1].setText(ActiveStr[6].substring(0, 4)+"-"+ActiveStr[6].substring(4, 6)+"-"+ActiveStr[6].substring(6, 8));
					ZhongBuHuoDong2.linlay[1].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[6].equals("")&&!ActiveStr[6].equals("19700101"))
					{
						ZhongBuHuoDong2.textArray[1].setText(ActiveStr[6].substring(0, 4)+"-"+ActiveStr[6].substring(4, 6)+"-"+ActiveStr[6].substring(6, 8));
						ZhongBuHuoDong2.linlay[1].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[1].setText("");
						ZhongBuHuoDong2.linlay[1].setEnabled(true);
					}
				}
				if(ActiveStr[9].equals("Y")&&!ActiveStr[8].equals(""))//优惠结束日期是否统一控制
				{
					ZhongBuHuoDong2.textArray[2].setText(ActiveStr[8].substring(0, 4)+"-"+ActiveStr[8].substring(4, 6)+"-"+ActiveStr[8].substring(6, 8));
					ZhongBuHuoDong2.linlay[2].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[8].equals("")&&!ActiveStr[8].equals("19700101"))
					{
						ZhongBuHuoDong2.textArray[2].setText(ActiveStr[8].substring(0, 4)+"-"+ActiveStr[8].substring(4, 6)+"-"+ActiveStr[8].substring(6, 8));
						ZhongBuHuoDong2.linlay[2].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[2].setText("");
						ZhongBuHuoDong2.linlay[2].setEnabled(true);
					}
				}
				if(ActiveStr[11].equals("Y"))//卡种是否统一控制
				{
					//卡种
					String [] card2=ActiveStr[10].split("");
					for(int i=1;i<card2.length;i++){
						if(card2[i].equals("1")){
							ZhongBuHuoDong2.rbArray[i-1].setChecked(true);
						}
						if(card2[i].equals("0")){
							ZhongBuHuoDong2.rbArray[i-1].setChecked(false);
							ZhongBuHuoDong2.rbArray[i-1].setEnabled(false);
						}
					}
				}
				else
				{
					//卡种
					String [] card2=ActiveStr[10].split("");
					for(int i=1;i<card2.length;i++){
						if(card2[i].equals("1")){
							ZhongBuHuoDong2.rbArray[i-1].setChecked(true);
							ZhongBuHuoDong2.rbArray[i-1].setEnabled(true);
						}
						if(card2[i].equals("0")){
							ZhongBuHuoDong2.rbArray[i-1].setChecked(false);
							ZhongBuHuoDong2.rbArray[i-1].setEnabled(true);
						}
					}
				}
				if(ActiveStr[13].equals("Y"))//发送短信是否统一控制
				{
					if(ActiveStr[12].equals("Y"))
					{
						ZhongBuHuoDong2.textArray[10].setText("是");
					}
					else
					{
						ZhongBuHuoDong2.textArray[10].setText("否");
					}
					ZhongBuHuoDong2.linlay[10].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[12].equals(""))
					{
						if(ActiveStr[12].equals("Y"))
						{
							ZhongBuHuoDong2.textArray[10].setText("是");
						}
						else
						{
							ZhongBuHuoDong2.textArray[10].setText("否");
						}
						ZhongBuHuoDong2.linlay[10].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[10].setText("");
						ZhongBuHuoDong2.linlay[10].setEnabled(true);
					}
				}
				if(ActiveStr[15].equals("Y"))//短信内容是否统一控制
				{
					ZhongBuHuoDong2.textArray[4].setText(ActiveStr[14]);
					ZhongBuHuoDong2.linlay[4].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[14].equals(""))
					{
						ZhongBuHuoDong2.textArray[4].setText(ActiveStr[14]);
						ZhongBuHuoDong2.linlay[4].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[4].setText("");
						ZhongBuHuoDong2.linlay[4].setEnabled(true);
					}
				}
				if(ActiveStr[17].equals("Y"))//优惠内容是否统一控制
				{
					ZhongBuHuoDong2.textArray[3].setText(ActiveStr[16]);
					ZhongBuHuoDong2.linlay[3].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[16].equals(""))
					{
						ZhongBuHuoDong2.textArray[3].setText(ActiveStr[16]);
						ZhongBuHuoDong2.linlay[3].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[3].setText("");
						ZhongBuHuoDong2.linlay[3].setEnabled(true);
					}
				}
				if(ActiveStr[19].equals("Y"))//金额限制是否统一控制
				{
					ZhongBuHuoDong2.textArray[9].setText(ActiveStr[18]);
					ZhongBuHuoDong2.linlay[9].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[18].equals(""))
					{
						ZhongBuHuoDong2.textArray[9].setText(ActiveStr[18]);
						ZhongBuHuoDong2.linlay[9].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[9].setText("");
						ZhongBuHuoDong2.linlay[9].setEnabled(true);
					}
				}
				if(ActiveStr[21].equals("Y"))//日期限制是否统一控制
				{
					//日期限制
					String [] card2=ActiveStr[20].split("");
					for(int i=1;i<card2.length;i++){
						if(card2[i].equals("1")){
							ZhongBuHuoDong2.rbZhiRi[i-1].setChecked(true);
							ZhongBuHuoDong2.rbZhiRi[i-1].setEnabled(false);
						}
						else if(card2[i].equals("0")){
							ZhongBuHuoDong2.rbZhiRi[i-1].setChecked(false);
							ZhongBuHuoDong2.rbZhiRi[i-1].setEnabled(false);
						}
					}
				}
				else
				{
					//日期限制
					if(!ActiveStr[20].equals(""))
					{
						String [] card2=ActiveStr[20].split("");
						for(int i=1;i<card2.length;i++){
							if(card2[i].equals("1")){
								ZhongBuHuoDong2.rbZhiRi[i-1].setChecked(true);
								ZhongBuHuoDong2.rbZhiRi[i-1].setEnabled(true);
							}
							else if(card2[i].equals("0")){
								ZhongBuHuoDong2.rbZhiRi[i-1].setChecked(false);
								ZhongBuHuoDong2.rbZhiRi[i-1].setEnabled(true);
							}
						}
					}
					else
					{
						for(int i=1;i<7;i++){
							ZhongBuHuoDong2.rbZhiRi[i-1].setChecked(false);
							ZhongBuHuoDong2.rbZhiRi[i-1].setEnabled(true);
						}
					}
				}
				if(ActiveStr[23].equals("Y"))//活动日是否统一控制
				{
					//活动日限制
					String [] card2=ActiveStr[22].split("");
					for(int i=1;i<card2.length;i++){
						if(card2[i].equals("1")){
							ZhongBuHuoDong2.rbHuoRi[i-1].setChecked(true);
							ZhongBuHuoDong2.rbHuoRi[i-1].setEnabled(false);
						}
						else if(card2[i].equals("0")){
							ZhongBuHuoDong2.rbHuoRi[i-1].setChecked(false);
							ZhongBuHuoDong2.rbHuoRi[i-1].setEnabled(false);
						}
					}
				}
				else
				{
					//活动日限制
					if(!ActiveStr[22].equals(""))
					{
						String [] card2=ActiveStr[22].split("");
						for(int i=1;i<card2.length;i++){
							if(card2[i].equals("1")){
								ZhongBuHuoDong2.rbHuoRi[i-1].setChecked(true);
								ZhongBuHuoDong2.rbHuoRi[i-1].setEnabled(true);
							}
							else if(card2[i].equals("0")){
								ZhongBuHuoDong2.rbHuoRi[i-1].setChecked(false);
								ZhongBuHuoDong2.rbHuoRi[i-1].setEnabled(true);
							}
						}
					}
					else
					{
						for(int i=1;i<7;i++){
							ZhongBuHuoDong2.rbHuoRi[i-1].setChecked(false);
							ZhongBuHuoDong2.rbHuoRi[i-1].setEnabled(true);
						}
					}
				}
				if(ActiveStr[25].equals("Y"))//特别约定是否统一控制
				{
					ZhongBuHuoDong2.h4edtext6.setText(ActiveStr[24]);
					ZhongBuHuoDong2.h5leout6.setEnabled(false);
				}
				else
				{
					if(!ActiveStr[24].equals(""))
					{
						ZhongBuHuoDong2.h4edtext6.setText(ActiveStr[24]);
						ZhongBuHuoDong2.h5leout6.setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.h4edtext6.setText("");
						ZhongBuHuoDong2.h5leout6.setEnabled(true);
					}
				}
				if(ActiveStr[27].equals("Y"))//发布是否统一控制
				{
					if(ActiveStr[26].equals("Y"))
					{
						ZhongBuHuoDong2.textArray[11].setText("是");
						ZhongBuHuoDong2.linlay[11].setEnabled(false);
					}
					else if(ActiveStr[26].equals("N"))
					{
						ZhongBuHuoDong2.textArray[11].setText("否");
						ZhongBuHuoDong2.linlay[11].setEnabled(false);
					}
					else if(ActiveStr[26].equals("S"))
					{
						ZhongBuHuoDong2.textArray[11].setText("暂缓");
						ZhongBuHuoDong2.linlay[11].setEnabled(false);
					}
				}
				else
				{
					if(!ActiveStr[26].equals(""))
					{
						if(ActiveStr[26].equals("Y"))
						{
							ZhongBuHuoDong2.textArray[11].setText("是");
						}
						else if(ActiveStr[26].equals("N"))
						{
							ZhongBuHuoDong2.textArray[11].setText("否");
						}
						else if(ActiveStr[26].equals("S"))
						{
							ZhongBuHuoDong2.textArray[11].setText("暂缓");
						}
						ZhongBuHuoDong2.linlay[11].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[11].setText("");
						ZhongBuHuoDong2.linlay[11].setEnabled(true);
					}
				}
				if(ActiveStr[29].equals("Y"))//备用A是否统一控制
				{
					ZhongBuHuoDong2.textArray[12].setText(ActiveStr[28]);
					ZhongBuHuoDong2.linlay[12].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[28].equals(""))
					{
						ZhongBuHuoDong2.textArray[12].setText(ActiveStr[28]);
						ZhongBuHuoDong2.linlay[12].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[12].setText("");
						ZhongBuHuoDong2.linlay[12].setEnabled(true);
					}
				}
				if(ActiveStr[31].equals("Y"))//备用B是否统一控制
				{
					ZhongBuHuoDong2.textArray[13].setText(ActiveStr[30]);
					ZhongBuHuoDong2.linlay[13].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[30].equals(""))
					{
						ZhongBuHuoDong2.textArray[13].setText(ActiveStr[30]);
						ZhongBuHuoDong2.linlay[13].setEnabled(false);
					}
					else
					{
						ZhongBuHuoDong2.textArray[13].setText("");
						ZhongBuHuoDong2.linlay[13].setEnabled(true);
					}
				}
				if(ActiveStr[33].equals("Y"))//备用C是否统一控制
				{
					ZhongBuHuoDong2.textArray[14].setText(ActiveStr[32]);
					ZhongBuHuoDong2.linlay[14].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[32].equals(""))
					{
						ZhongBuHuoDong2.textArray[14].setText(ActiveStr[32]);
						ZhongBuHuoDong2.linlay[14].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[14].setText("");
						ZhongBuHuoDong2.linlay[14].setEnabled(true);
					}
				}
				if(ActiveStr[35].equals("Y"))//备用D是否统一控制
				{
					ZhongBuHuoDong2.textArray[15].setText(ActiveStr[34]);
					ZhongBuHuoDong2.linlay[15].setEnabled(false);
				}
				else
				{
					if(!ActiveStr[34].equals(""))
					{
						ZhongBuHuoDong2.textArray[15].setText(ActiveStr[34]);
						ZhongBuHuoDong2.linlay[15].setEnabled(true);
					}
					else
					{
						ZhongBuHuoDong2.textArray[15].setText("");
						ZhongBuHuoDong2.linlay[15].setEnabled(true);
					}
				}
					
				
				dlg.dismiss();
				// lxp
			}
		});
		// 创建对话框

		dlg.setContentView(DialogView);
		// dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.setCancelable(false);
		dlg.show();
	}
	

	public boolean isdouhao(String name) {
		int aa = name.indexOf("|");
//		int bb = name.indexOf("<");
//		int cc = name.indexOf(">");
//		int dd = name.indexOf("/");
//		int ee = name.indexOf("\\");
//		int ff = name.indexOf("?");
//		int gg = name.indexOf("％");
//		int ggg = name.indexOf("%");
//		int hh = name.indexOf("”");
//		int ii = name.indexOf("“");
//		int jj = name.indexOf("\"");
//		int kk = name.indexOf("*");
//		int kkk = name.indexOf("﹡");
//		int ll = name.indexOf("＆");
//		int lll = name.indexOf("&");
//		int mm = name.indexOf("^");
//		int nn = name.indexOf("~");
//		int oo = name.indexOf("`");
//		int pp = name.indexOf("'");
//		int qq = name.indexOf("’");
//		int rr = name.indexOf("‘");
//		int ss = name.indexOf("‰");
//		int rr1 = name.indexOf("＇");
//		int rr2 = name.indexOf("ˊ");
//		int rr3 = name.indexOf("´");
//		int rr4 = name.indexOf("ˋ");
//		int rr5 = name.indexOf("ˋ‘");
//		int rr6 = name.indexOf("ˋ’");
		String[] arrg = name.split(" ");
		if (aa >= 0) {
			return false;
		} 
//		else if (bb >= 0) {
//			return false;
//		}else if (rr1 >= 0) {
//			return false;
//		}else if (rr2 >= 0) {
//			return false;
//		}else if (rr3 >= 0) {
//			return false;
//		}else if (rr4 >= 0) {
//			return false;
//		}else if (rr5 >= 0) {
//			return false;
//		}else if (rr6 >= 0) {
//			return false;
//		} else if (cc >= 0) {
//			return false;
//		} else if (dd >= 0) {
//			return false;
//		} else if (ee >= 0) {
//			return false;
//		} else if (ee >= 0) {
//			return false;
//		}else if (ff >= 0) {
//			return false;
//		}else if (gg >= 0) {
//			return false;
//		}else if (ggg >= 0) {
//			return false;
//		}else if (hh >= 0) {
//			return false;
//		}else if (ii >= 0) {
//			return false;
//		}else if (jj >= 0) {
//			return false;
//		}else if (kk >= 0) {
//			return false;
//		}else if (kkk >= 0) {
//			return false;
//		}else if (ll >= 0) {
//			return false;
//		}else if (lll >= 0) {
//			return false;
//		}else if (mm >= 0) {
//			return false;
//		}else if (nn >= 0) {
//			return false;
//		}else if (oo >= 0) {
//			return false;
//		}else if (pp >= 0) {
//			return false;
//		}else if (qq >= 0) {
//			return false;
//		}else if (rr >= 0) {
//			return false;
//		}else if (ss >= 0) {
//			return false;
//		}
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
}
