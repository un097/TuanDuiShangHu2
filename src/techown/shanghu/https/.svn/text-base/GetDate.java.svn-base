package techown.shanghu.https;

import java.util.Calendar;
/*
 * 得到当前日期
 */
public class GetDate {
	public static StringBuilder getDate()
	{
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int minute=c.get(Calendar.MINUTE);
        int second=c.get(Calendar.SECOND);
        StringBuilder strDate=new StringBuilder();
        strDate.append(String.valueOf(year));
        if(month<10)
        {
        	strDate.append("-0"+String.valueOf(month));
        }
        else
        {
        	strDate.append("-"+String.valueOf(month));
        }
        if(day<10)
        {
        	strDate.append("-0"+String.valueOf(day));
        }
        else
        {
        	strDate.append("-"+String.valueOf(day));
        }
        if(hour<10)
        {
        	strDate.append("-0"+String.valueOf(hour));
        }
        else
        {
        	strDate.append("-"+String.valueOf(hour));
        }
        if(minute<10)
        {
        	strDate.append("-0"+String.valueOf(minute));
        }
        else
        {
        	strDate.append("-"+String.valueOf(minute));
        }
        if(second<10)
        {
        	strDate.append("-0"+String.valueOf(second));
        }
        else
        {
        	strDate.append("-"+String.valueOf(second));
        }
	return strDate;		
	}
	public static StringBuilder getYMD()
	{
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        StringBuilder strDate=new StringBuilder();
        strDate.append(String.valueOf(year));
        if(month<10)
        {
        	strDate.append("0"+String.valueOf(month));
        }
        else
        {
        	strDate.append(""+String.valueOf(month));
        }
        if(day<10)
        {
        	strDate.append("0"+String.valueOf(day));
        }
        else
        {
        	strDate.append(""+String.valueOf(day));
        }
        return strDate;		
	}
	public static StringBuilder getYM()
	{
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        StringBuilder strDate=new StringBuilder();
        strDate.append(String.valueOf(year));
        if(month<10)
        {
        	strDate.append("-0"+String.valueOf(month));
        }
        else
        {
        	strDate.append("-"+String.valueOf(month));
        }
        return strDate;			
	}
	
	public static String getMonths(String date,String nowdate)//晚-早
	{
		String[]str1=date.trim().split("-");
		String[]str2=nowdate.trim().split("-");
		int s=(Integer.parseInt(str1[0])-Integer.parseInt(str2[0]))*12+Integer.parseInt(str1[1])-Integer.parseInt(str2[1]);
		String months=String.valueOf(s);
		return months;	
	}
	
	public static String dateChange(String date)
	{
		StringBuilder sb=new StringBuilder();
		String[]dateStr=date.split("-");
		for(int i=0;i<dateStr.length;i++)
		{
			sb.append(dateStr[i]);
		}
		return sb.toString();		
	}
	//"-"
	public static String addressChange(String addresss)
	{
		char spl = (char)0x1c;
		StringBuilder sb=new StringBuilder();
		String[]addressStr=addresss.split(String.valueOf(spl));
		for(int i=0;i<addressStr.length;i++)
		{
			sb.append(addressStr[i]);
		}
		return sb.toString();		
	}
}
