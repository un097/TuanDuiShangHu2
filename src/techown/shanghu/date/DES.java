package techown.shanghu.date;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.https.MD5Util;
import android.content.Context;


public class DES {
	public static DES encrypt = null;
	byte[] keyBuf = null;
	SecureRandom sr =null;
	Key key = null;
	
	
	// INFO�ֶ�����info id
	public byte[] getKeyBytes() {
		byte[] keyBytes = null;

		String nameMD5 = MD5Util.getMD5String(Constant.username);
		String imeiMD5 = MD5Util.getMD5String(Constant.imei);
	
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
	

	


	public String jiaMi(String string) {
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
		String tempStr = byteArr2HexStr(encryptedData);
		return tempStr;
	}

	public String jieMI(String string) {
		String jiemi = null;
		try {
			if (string == null) {
				return null;
			} else {
				jiemi = decrypt(hexStr2ByteArr(string));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jiemi;
	}

	public String decrypt(byte[] encryptedData)
			throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException {
		byte decryptedData[] = null;
		if(isNewDevices()){
			try {
				sr = new SecureRandom();  
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(keyBuf==null)
				keyBuf = getKeyBytes();
			 DESKeySpec desKey = new DESKeySpec(keyBuf);
			 SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
			 SecretKey securekey = keyFactory.generateSecret(desKey); 
			 Cipher cipher = Cipher.getInstance("DES");  
			 // ���ܳ׳�ʼ��Cipher����
			 cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			 // ��ʽִ�н��ܲ���
			 decryptedData = cipher.doFinal(encryptedData);
			
		}else{
			// // DES�㷨Ҫ����һ�������ε������Դ
			sr = new SecureRandom();
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
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			// ��ʽִ�н��ܲ���
			decryptedData = cipher.doFinal(encryptedData);
		}
		return new String(decryptedData);
	}

	public byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];// �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);// ÿ��byte�������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {// �Ѹ���ת��Ϊ����
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {// С��0F������Ҫ��ǰ�油0
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public byte[] encrypt(String str)throws InvalidKeyException, NoSuchAlgorithmException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchPaddingException, InvalidKeySpecException {
		byte[] encryptedData = null;
		if(isNewDevices()){
			try {
				sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(keyBuf==null)
				keyBuf = getKeyBytes();
			  // ����һ��DESKeySpec���� 
			DESKeySpec desKey = new DESKeySpec(keyBuf); 
			 //����һ���ܳ׹�����Ȼ��������DESKeySpecת����  
			 SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
			 SecretKey securekey = keyFactory.generateSecret(desKey); 
			 //Cipher����ʵ����ɼ��ܲ���  
			 Cipher cipher = Cipher.getInstance("DES");
			 // ���ܳ׳�ʼ��Cipher����
			 cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
			 // ���ڣ���ȡ���ݲ�����
			 byte data[] = str.getBytes();
			 // ��ʽִ�м��ܲ���
			 encryptedData = cipher.doFinal(data);
			 
		}else{
			sr = new SecureRandom();
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
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// ���ڣ���ȡ���ݲ�����
			byte data[] = str.getBytes();
			// ��ʽִ�м��ܲ���
			encryptedData = cipher.doFinal(data);
			
		}
		return encryptedData;
	}
	/**
	 * �ж��ǲ�����ͨ�����롢��Ϊ���豸
	 * @return
	 */
	public boolean isNewDevices() {
		boolean isNewDevice =false;
		if(Constant.model.equals("MediaPad 10 Link+")||Constant.model.equals("A106")||Constant.model.equals("TAB A10-80HC")){
			isNewDevice=true;
		}
		return isNewDevice;
	}

}
