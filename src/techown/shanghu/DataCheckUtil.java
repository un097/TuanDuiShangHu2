package techown.shanghu;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCheckUtil {
	

	 public   boolean isAddress1(String address) {//��Ӣ������У��
			int i = 0, j = 0, k = 0;
			int count = address.length();
			Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
			Matcher m = pattern.matcher(address);
			while (m.find()) {
				i++;
			}
			for (int idx = 0; idx < count; idx++) {
				char c = address.charAt(idx);
				int tmp = (int)c;
				if((tmp>='a' && tmp<='z') || (tmp>='A'&&tmp<='Z')){
					j++;
				}
			}
			for (int idx = 0; idx < count; idx++) {
				char c = address.charAt(idx);
				if(c==' '){
					k++;
				}
			}
			if ((i + j + k) == count) {
				return true;
			} else {
				return false;
			}

		}
	
	//ʱ���ʽ��ȥ-
		public String dateChange(String date)
		{
			StringBuilder sb=new StringBuilder();
			String[]dateStr=date.split("-");
			for(int i=0;i<dateStr.length;i++)
			{
				sb.append(dateStr[i]);
			}
			return sb.toString();		
		}
//	�ж��Ƿ�������
			public  boolean isAllDigit(String str){
				int len=0;
				for(int idx=0;idx<str.length();idx++){
					if(Character.isDigit(str.charAt(idx))){
						len++;
					}
				}
				if(len==str.length()){
					return true;
				}
				
				return false;
			}
			
			
			
//			�ж��Ƿ������ּӡ�*��
			public  boolean isAllDigit1(String str){
				int len=0;
				for(int idx=0;idx<str.length();idx++){
					if(Character.isDigit(str.charAt(idx))||str.charAt(idx)=='*'){
						len++;
					}
				}
				if(len==str.length()){
					return true;
				}
				
				return false;
			}
			
//�жϵ绰�����Ƿ�ȫ��Ϊ0
			public  boolean iszieo(String str)
			{
				String[] aa = str.split("0");
				if(aa.length==0)
				{
					return true;
				}
				return false;
			}

	//�ж��Ƿ������ֺ�"*"����ɵ�
		public  boolean isJustDigitStar(String str){
			
			int len = 0;
			for(int idx=0;idx<str.length();idx++){
				if (Character.isDigit(str.charAt(idx)) || str.charAt(idx) == '.'){
					len++;
				}
			}
			
			if(len == str.length()){
				return true;
			}
			
			return false;
		}
	
	
	
	
	
	
	


	

	// �ֻ�������֤
	public  boolean isPhoneNum(String phonenum) {
		Pattern pattern = Pattern
				.compile("^(1[0-9])\\d{9}$"); 
		Matcher m = pattern.matcher(phonenum);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	

	
		//�ж��ַ�����
		 public  boolean allowMaxLenthOfString(String s, int charNum) {
				int num = 0;
				for(int i=0; i<s.length(); i++){
					String tmp = s.substring(i, i+1);
					if(tmp.getBytes().length == 3){
						num += 2;
					}
					else if(tmp.getBytes().length == 1){
						num += 1;
					}
					
				}
				if(num <= charNum){
					return true;
				}
				return false;
			}
		//�ж��Ƿ��������
		 public  boolean CharString(String s) {
				int num = 0;
				for(int i=0; i<s.length(); i++){
					String tmp = s.substring(i, i+1);
					if(tmp.getBytes().length == 3){
						num=2;
						break;
					}
					else if(tmp.getBytes().length == 1){
						num = 1;
					}
					
				}
				if(num==2)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		//�ж��ַ�����
		 public  int NumString(String s) {
				int num = 0;
				for(int i=0; i<s.length(); i++){
					String tmp = s.substring(i, i+1);
					if(tmp.getBytes().length == 3){
						num += 2;
					}
					else if(tmp.getBytes().length == 1){
						num += 1;
					}
					
				}
				return num;
			}

	// Ӣ��������֤
	public  boolean isEnglish(String english) {
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		Matcher m = pattern.matcher(english);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	// ������֤
	public boolean isData(String number) {
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher m = pattern.matcher(number);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public  boolean validate(String beginTime) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String strBegin[] = beginTime.split("-");

		int beginYear = Integer.parseInt(strBegin[0]);
		int beginMonth = Integer.parseInt(strBegin[1]);
		
		int beginDay = Integer.parseInt(strBegin[2].trim());
		// ����Ŀ�ʼ���ڲ������ڵ�ǰʱ��!
		if ((beginYear * 372 + beginMonth * 31 + beginDay) >= (year * 372
				+ month * 31 + day)) {
			return true;
		} else {
			return false;
		}
	}
	
	public  boolean validate3(String beginTime) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String strBegin[] = beginTime.split("-");

		int beginYear = Integer.parseInt(strBegin[0]);
		int beginMonth = Integer.parseInt(strBegin[1]);
		
		int beginDay = Integer.parseInt(strBegin[2].trim());
		// ����Ŀ�ʼ���ڲ������ڵ�ǰʱ��60��!
		if ((beginYear * 372 + beginMonth * 31 + beginDay+ 60) > (year * 372
				+ month * 31 + day)) {
			return true;
		} else {
			return false;
		}
	}
	public  boolean validate2(String beginTime) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String strBegin[] = beginTime.split("-");

		int beginYear = Integer.parseInt(strBegin[0]);
		int beginMonth = Integer.parseInt(strBegin[1]);
		
		int beginDay = Integer.parseInt(strBegin[2].trim());
		// ����Ŀ�ʼ���ڲ������ڵ�ǰʱ��!
		if ((beginYear * 372 + beginMonth * 31 + beginDay) > (year * 372
				+ month * 31 + day)) {
			return false;
		} else {
			return true;
		}
	}
	
	
	// ������֤
	public boolean isChinese(String name) {
		int j = 0;
		int i = name.length();
		Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
		Matcher m = pattern.matcher(name);
		while (m.find()) {
			j++;
		}
		if (i == j) {
			return true;
		} else {
			return false;
		}
	}

	
	public  boolean validateTime(String beginTime,String  endTime) {
		
		String strBegin[] = beginTime.split("-");
		String strEnd[] = endTime.split("-");

		int beginYear = Integer.parseInt(strBegin[0].trim());
		int beginMonth = Integer.parseInt(strBegin[1].trim());
		int beginDay = Integer.parseInt(strBegin[2].trim());
		
		int endYear = Integer.parseInt(strEnd[0].trim());
		int endMonth = Integer.parseInt(strEnd[1].trim());
		int endDay = Integer.parseInt(strEnd[2].trim());
		// ����Ŀ�ʼ���ڲ������ڵ�ǰʱ��!
		if ((beginYear * 372 + beginMonth * 31 + beginDay) <= (endYear * 372
				+ endMonth * 31 + endDay)) {
			return true;
		} else {
			return false;
		}
	}


}

