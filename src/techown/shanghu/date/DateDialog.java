package techown.shanghu.date;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import techown.shanghu.DataCheckUtil;
import techown.shanghu.EditDialog;
import techown.shanghu.R;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DateDialog  {
	public static  Dialog dialog;
	private static int START_YEAR = 1900, END_YEAR = 2100;
	static TextView etTemp;
	static int year,month,day;
	public static int num=0;
	public static boolean flag;
	static DataCheckUtil dc=new DataCheckUtil();
	static EditDialog log=new EditDialog();
	public static void showDatePicker(final Context con,final TextView textView,final int ID) {
		if(dialog!=null&&dialog.isShowing())
		{
			return;
		}if(EditDialog.dlg!=null&&EditDialog.dlg.isShowing()){
			return;
		}
		if(EditDialog.etdlg!=null&&EditDialog.etdlg.isShowing()){
			return;
		}if(EditDialog.dialog!=null&&EditDialog.dialog.isShowing()){
			return;
		}
		
		etTemp=textView;
		if(etTemp.getText().toString().length()>0)
		{
			String[] time  = etTemp.getText().toString().split("-");
			 year = Integer.parseInt(time[0]);
			month = Integer.parseInt(time[1])-1;
			day = Integer.parseInt(time[2]);	
		}
		else
		{
			Calendar calendar = Calendar.getInstance();
			 year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DATE);	
		}
		
		// ��Ӵ�С���·ݲ�����ת��Ϊlist,����֮����ж�
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);
		dialog = new Dialog(con,R.style.FullHeightDialog);
//		dialog.setTitle("��ѡ������");
		// �ҵ�dialog�Ĳ����ļ�
		LayoutInflater inflater =LayoutInflater.from(con);
		View view = inflater.inflate(R.layout.time_layout, null);

		// ��
		final WheelView wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// ����"��"����ʾ����
		wv_year.setCyclic(true);// ��ѭ������
		wv_year.setLabel("��");// �������
		wv_year.setCurrentItem(year - START_YEAR);// ��ʼ��ʱ��ʾ������

		// ��
		final WheelView wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("��");
		wv_month.setCurrentItem(month);

		// ��
		final WheelView wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// ����
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel("��");
		wv_day.setCurrentItem(day - 1);

		// ���"��"����
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
				if (list_big.contains(String
						.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0)
					{
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						wv_day.setCurrentItem(0);	
					}
						
					else
					{
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						wv_day.setCurrentItem(0);
					}
						
				}
			}
		};
		// ���"��"����
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
					wv_day.setCurrentItem(0);	
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					wv_day.setCurrentItem(0);	
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
							.getCurrentItem() + START_YEAR) % 100 != 0)
							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
					{
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						wv_day.setCurrentItem(0);		
					}
						
					else
					{
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						wv_day.setCurrentItem(0);		
					}
						
				}
			}
		};

		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// ������Ļ�ܶ���ָ��ѡ��������Ĵ�

		wv_day.TEXT_SIZE = (int)con.getResources().getDimension(R.dimen.btnTextSizebody);
//		wv_hours.TEXT_SIZE = textSize;
//		wv_mins.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE =(int)con.getResources().getDimension(R.dimen.btnTextSizebody);
		wv_year.TEXT_SIZE = (int)con.getResources().getDimension(R.dimen.btnTextSizebody);

		Button btn_sure = (Button) view.findViewById(R.id.btn_datetime_sure);
		Button btn_cancel = (Button) view
				.findViewById(R.id.btn_datetime_cancel);
		// ȷ��
		btn_sure.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// ����Ǹ���,����ʾΪ"02"����ʽ
//				String parten = "00";
//				DecimalFormat decimal = new DecimalFormat(parten);
				String day1 = String.valueOf(wv_day.getCurrentItem()+1);
				String year1 = String.valueOf(wv_year.getCurrentItem()+START_YEAR);
				String month1 = String.valueOf(wv_month.getCurrentItem()+1);
				if(month1.length()<2)
				{
					month1=0+month1;
				}
				if(day1.length()<2)
				{
					day1=0+day1;
				}
				
				// �������ڵ���ʾ
				etTemp.setText(year1 + "-"
				 + month1 + "-"
				 + day1
				);
				
				switch(ID)
				{
				case R.id.h1leout12:
				case R.id.h1et12:
			
				case R.id.z1edtext12:
				
				case R.id.c1et12:
					if(dc.validate(etTemp.getText().toString()))
					{
						dialog.dismiss();
					}
					else
					{
						log.Toast(con,"�����ʱ�䲻�����ڵ�ǰ���ڣ�");
						etTemp.setText("");
					}
					break;
				case R.id.h2edtext18:
					
					if(dc.validate(etTemp.getText().toString()))
					{
						dialog.dismiss();
					}
					else
					{
						log.Toast(con,"�����ʱ�䲻�����ڵ�ǰ���ڣ�");
						etTemp.setText("");
					}
					break;
					
				case R.id.statetext2:
					if(dc.validate2(etTemp.getText().toString()))
					{
						if(dc.validate3(etTemp.getText().toString())){
							
							dialog.dismiss();
						}else{
							log.Toast(con,"�ᱨ��ʼ���ڱ�����60��֮�ڣ�");
							etTemp.setText("");
						}
					}
					else
					{
						log.Toast(con,"�����ʱ�䲻�����ڵ�ǰ���ڣ�");
						etTemp.setText("");
					}
					break;
					
				case R.id.yedtext5:
				case R.id.g3et4:
				case R.id.statetext3:
				case R.id.tbyedtext2:
					if(dc.validate2(etTemp.getText().toString()))
					{
						dialog.dismiss();
					}
					else
					{
						log.Toast(con,"�����ʱ�䲻�����ڵ�ǰ���ڣ�");
						etTemp.setText("");
					}
					break;
				case R.id.yedtext51:
					if(dc.validate2(etTemp.getText().toString()))
					{
						dialog.dismiss();
					}
					else
					{
						log.Toast(con,"�����ʱ�䲻�����ڵ�ǰ���ڣ�");
						etTemp.setText("");
					}
					break;
				case R.id.redhuodong3://ǩԼ����
				case R.id.h4edtext5://ǩԼ����
					if(etTemp.getText().toString() != null)
					{
						dialog.dismiss();
					}
					break;
					
				case R.id.redhuodong5:
				case R.id.h4edtext6:
					if(etTemp.getText().toString() != null)
					{
						dialog.dismiss();
					}
					break;
					
				case R.id.redhuodong6:
				case R.id.h4edtext8:
					if(etTemp.getText().toString() != null)
					{
						dialog.dismiss();
					}
					break;
			
				
				case R.id.z2edtext2:
					if(etTemp.getText().toString() != null)
					{
						dialog.dismiss();
					}
					break;
				case R.id.z2edtext6:
					if(dc.validate2(etTemp.getText().toString()))
					{
						dialog.dismiss();
					}
					else
					{
						log.Toast(con,"�������ڲ������ڵ�ǰ����");
						etTemp.setText("");
					}
					break;
					
			
				case R.id.z2edtext3://@
				
			
					if(etTemp.getText().toString() != null)
					{
						dialog.dismiss();
					}
					break;
			
					
				
					
					
				
					
				
				case R.id.c2et2:
					if(etTemp.getText().toString() != null)
					{
						dialog.dismiss();
					}
					break;
				case R.id.c2et3://@
					if(etTemp.getText().toString() != null)
					{
						dialog.dismiss();
					}
					break;
					
					
				case R.id.c2et6://@
					
					if(dc.validate2(etTemp.getText().toString()))
					{
						dialog.dismiss();
					}
					else
					{
						Toast.makeText(con,"�������ڲ������ڵ�ǰ���ڣ�",Toast.LENGTH_LONG).show();
						etTemp.setText("");
					}
					break;
				case R.id.youxiaobeginTime:
					if(etTemp.getText().toString() != null)
					{
						dialog.dismiss();
					}
					break;
				case R.id.youxiaostopTime:
					if(dc.validate(etTemp.getText().toString()))
					{
						dialog.dismiss();
					}
					else
					{
						log.Toast(con,"�����ʱ�䲻�����ڵ�ǰ���ڣ�");
						etTemp.setText("");
					}
					break;
				default:
					break;
				}

			}
		});
		// ȡ��
		btn_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				
				dialog.dismiss();
			}
		});
		// ����dialog�Ĳ���,����ʾ
		dialog.setContentView(view);
		dialog.setCancelable(false);
		dialog.show();
		
	}

}