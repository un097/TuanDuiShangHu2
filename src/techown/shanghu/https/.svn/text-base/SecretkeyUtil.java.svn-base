package techown.shanghu.https;

public class SecretkeyUtil {
	/*
	 * ªÒµ√√‹‘ø
	 * 
	 * */
	public static byte[] getKey(String user,String imei){
		
		byte[] temp = new byte[24];
		try {
			byte[] datemd5 = MD5Util.getMd5(user);
			
			byte[] imeimd5 = MD5Util.getMd5(imei);
		       for (int i = 0; i < datemd5.length; i++) {
		           temp[i] = datemd5[i];
		       }
		       for (int j = 16; j < 24; j++) {
		           temp[j] = imeimd5[j - 16];
		       }
		       return temp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
}
