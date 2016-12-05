package techown.shanghu.date;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import techown.shanghu.MyDatabaseHelper;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.https.MD5Util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class Encrypt {
	
	
	public static Encrypt encrypt = null;
	public static Context context;
	String jiemi = null;
	String tempStr = null;
	byte[] keyBuf = null;
	
	private Encrypt(){}
	
	public static Encrypt getInstance(Context con){
		if(encrypt==null){
			encrypt = new Encrypt();
		}
		context = con;
		return encrypt;
	}
	
	// INFO字段名，info id
	public byte[] getKeyBytes() {
		byte[] keyBytes = null;
		String username = getUserName();
		String imei = getImei();
		
		String nameMD5 = MD5Util.getMD5String(username);
		String imeiMD5 = MD5Util.getMD5String(imei);
		
		byte[] nameByte = nameMD5.getBytes();
		byte[] imeiByte = imeiMD5.getBytes();
		keyBytes = new byte[24];
		for (int i = 0; i < 16; i++) {
			keyBytes[i] = nameByte[i];
		}
		for (int i = 0; i < 8; i++) {
			keyBytes[16 + i] = imeiByte[i];
		}
		
		return keyBytes;
	}
	
	public String getUserName(){
		String userName = null;
		Cursor cur = null;
		SQLiteDatabase db = null;
		try{
			
			Context friendContext = context.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);
			
			db = myHelper.getWritableDatabase(); // 获得数据库对象
			cur = db.rawQuery("select * from EntityUser", null);
			if(cur.getCount() > 0){
				cur.moveToLast();
				userName = cur.getString(cur.getColumnIndex("username"));
			}
		}
		catch(Exception e){
//			Log.getInstance().writeLog(e.getMessage());
		}
		finally{
			if(cur!=null)cur.close();
			if(db!=null)db.close();
		}
		return userName;
	}
	
	public String getImei(){
		String imei = null;
		Cursor cur = null;
		SQLiteDatabase db = null;
		
		try{
			Context friendContext = context.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);
			
			db = myHelper.getWritableDatabase(); // 获得数据库对象
			
			cur = db.rawQuery("select * from EntityUser", null);
			if(cur.getCount() > 0){
				cur.moveToLast();
				imei = cur.getString(cur.getColumnIndex("imei"));
			}
		}
		catch(Exception e){
//			Log.getInstance().writeLog(e.getMessage());
		}
		finally{
			if(cur!=null)cur.close();
			if(db!=null)db.close();
		}
		return imei;
	}

	public String jiaMi(String string) {
		
		if(Constant.isDecrypt)
		{
			byte[] encryptedData = null;
			try {
				if (string == null) {
					return null;
				} else {
					encryptedData = encrypt(string);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			tempStr = byteArr2HexStr(encryptedData);
		}
		else
		{
			tempStr = string;
		}
		return tempStr;
	}

	public String jieMI(String string) {
		if(Constant.isDecrypt)
		{
			try {
				if (string == null) {
					return null;
				} else {
					jiemi = decrypt(hexStr2ByteArr(string));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			jiemi = string;
		}
		return jiemi;
	}

	public String decrypt(byte[] encryptedData)
			throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException {
		// // DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// // 从原始密匙数据创建一个DESKeySpec对象
		// DESKeySpec dks = new DESKeySpec(rawKeyData);
		// // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
		// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// SecretKey key = keyFactory.generateSecret(dks);
		// // Cipher对象实际完成解密操作
		Key key = null;
		try {
			if(keyBuf==null)
				keyBuf = getKeyBytes();
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(keyBuf));
			key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		// 正式执行解密操作
		byte decryptedData[] = cipher.doFinal(encryptedData);
		return new String(decryptedData);
	}

	public byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {// 把负数转换为正数
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {// 小于0F的数需要在前面补0
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public byte[] encrypt(String str)
			throws InvalidKeyException, NoSuchAlgorithmException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchPaddingException, InvalidKeySpecException {
		// // DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// // 从原始密匙数据创建一个DESKeySpec对象
		// DESKeySpec dks = new DESKeySpec(rawKeyData);
		// // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// SecretKey key = keyFactory.generateSecret(dks);
		// // Cipher对象实际完成加密操作
		Key key = null;
		try {
			if(keyBuf==null)
				keyBuf = getKeyBytes();
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(keyBuf));
			key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		// 现在，获取数据并加密
		byte data[] = str.getBytes();
		// 正式执行加密操作
		byte[] encryptedData = cipher.doFinal(data);

		return encryptedData;
	}
}
