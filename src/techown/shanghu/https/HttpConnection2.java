package techown.shanghu.https;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;


import techown.shanghu.TuanDuiShangHuActivity;
import android.util.Log;



public class HttpConnection2 {
//	static String uriAPI;
//		public static String uriAPI = "http://182.180.50.192:8080/tjbproxy/proxy.do";
		static String error;
		public static String username;
		static String imei;
		static String indenty;
		
		
		public static HttpURLConnection getHttpCon(String identity,String model) {
		
//		uriAPI = "http://182.180.50.192:8080/tjbproxy/proxy.do";
//		uriAPI = "https://182.180.50.105:443/tjbproxy/proxy.do";
		URL url; 
		try {
			url = new URL(HttpconnectionActivity.uriAPI);
			SSLClient.initializeSSLContext();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(25 * 1000);
			conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			conn.setRequestProperty("wms-message", "trader");
			conn.setRequestProperty("wms-identity",identity);// 员工号的MD5值
			conn.setRequestProperty("wms-encrypt", "3des");
			conn.setRequestProperty("wms-compress", "gzip");
			conn.setRequestProperty("wms-model", model);

			
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//conn.setUseCaches(false);

			return conn;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "连接不到服务器";
			System.out.println("**************get con wrong");
			return null;
		}
	}
	
		public static HttpURLConnection getHttpCon() {
//			uriAPI = "http://182.180.50.192:8080/tjbproxy/proxy.do";
			URL url;
			try {
				url = new URL(HttpconnectionActivity.uriAPI);
				SSLClient.initializeSSLContext();
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setConnectTimeout(25 * 1000);
				conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
				conn.setRequestProperty("wms-message", "cim");
				conn.setRequestProperty("wms-identity",indenty);// 员工号的MD5值
				conn.setRequestProperty("wms-encrypt", "3des");
				conn.setRequestProperty("wms-compress", "gzip");
				conn.setRequestProperty("wms-model", "TJB001");
				
				conn.setDoOutput(true);
				conn.setDoInput(true);
				//conn.setUseCaches(false);

				return conn;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = "连接不到服务器";
				System.out.println("**************get con wrong");
				return null;
			}
		}
	
	public static String HttpRequest(HttpURLConnection con, String Jsonstr) {
		// OutputStream os;

		try {
			
			OutputStream os=con.getOutputStream();  
			byte[] jsonbt = Jsonstr.getBytes("UTF-8");
			jsonbt=Compress.compress(jsonbt);
			jsonbt=Des.EncryptMode(SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei),jsonbt);
			os.write(jsonbt);
			os.flush();
			 os.close();
			
			int code = con.getResponseCode();
			if (code != 200) {
				String error11 = con.getHeaderField("wms-error");
				error = new String(error11.getBytes("ISO-8859-1"), "UTF-8");
				System.out.println("++++++++++++++++++++request wrong" + code+error);
				return null;
			} else {
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$request sucess");
				InputStream in = con.getInputStream();
				
				if ("3des".equals(con.getHeaderField("wms-encrypt"))) {
					in = DESUtil.decrypt(in,
							SecretkeyUtil.getKey(TuanDuiShangHuActivity.username, TuanDuiShangHuActivity.imei));// 员工号和手机imei号
				}
				
				if ("gzip".equals(con.getHeaderField("wms-compress"))) {
					in = new GZIPInputStream(in);

				}
				BufferedReader br=new BufferedReader(new InputStreamReader(in));
				String str;
				String str1="";
				while((str=br.readLine())!=null){
//					str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
					str1+=str;
				}
				return str1;
			}
		}catch (SocketTimeoutException e) {
			
			error = "连接超時";
		return "Timeout";
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("login", "连接出现异常");
			error = "连接出现异常";
//			try {
//				int code = con.getResponseCode();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			return null;
		}
	}
	public static String request(String json,String model)
	  {
		String jsontext=null;
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
	  	try
	  	{	  			  	             
	  		URL url = new URL(HttpconnectionActivity.uriAPI);
			SSLClient.initializeSSLContext();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();	  			         
	          conn.setRequestMethod("POST"); 
	          conn.setConnectTimeout(25 * 1000);
	          conn.setDoOutput(true); 
	          conn.setDoInput(true);      
	          conn.setUseCaches(false);        
	        conn.setRequestProperty("wms-identity",TuanDuiShangHuActivity.indenty);//员工号的MD5值
	        conn.setRequestProperty("wms-message", "trader");	 
	        conn.setRequestProperty("wms-model", model);	
	        conn.setRequestProperty("wms-encrypt", "3des");
			conn.setRequestProperty("wms-compress", "gzip");	        	        
	 		os = conn.getOutputStream();		 		
	 		byte[]bs=json.getBytes("UTF-8");
			bs=Compress.compress(bs);
			bs=Des.EncryptMode(SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei),bs);
	 		os.write(bs);
			os.close();
			System.out.println("shujuduquwancheng");
			if(conn.getResponseCode()!=200){
				System.out.println(conn.getResponseCode());		
				System.out.println(conn.getHeaderField("wms-error"));
				System.out.println(conn.getResponseMessage());				
			}
			else
			{
				System.out.println("request sucess");
				InputStream in = conn.getInputStream();
				
				if ("3des".equals(conn.getHeaderField("wms-encrypt"))) {
					in = DESUtil.decrypt(in,
							SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei));// 员工号和手机imei号
				}
				if ("gzip".equals(conn.getHeaderField("wms-compress"))) {
					in = new GZIPInputStream(in);

				}
				bos = new ByteArrayOutputStream();								
				int i = 0;
				while ((i = in.read()) != -1) {
					bos.write(i);
				}
				byte[] re = bos.toByteArray();
				jsontext= new String(re, "UTF-8").trim();				
				bos.close();
				in.close();		
			}
	  	}
	  	catch(SocketTimeoutException e)
	  	{
	  		jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络超时\"}";
	  		return jsontext;
	  	}
	  	catch(Exception e)
	  	{
  			jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络连接异常\"}";
	  		return jsontext;

	  	}
	  	finally{
	  		try{
		  		if(os!=null)os.close();
		  		if(bos!=null)bos.close();
	  		}
	  		catch(Exception e){}
	  	}
		return jsontext;
	  }
	
	public static String tbrequest2(String json,String model)
	  {
		String jsontext=null;
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
	  	try
	  	{	  
	  		
	  		URL url = new URL(HttpconnectionActivity.uriAPI);
			SSLClient.initializeSSLContext();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();	  			         
	          conn.setRequestMethod("POST"); 
	          conn.setConnectTimeout(25 * 1000);
	          conn.setDoOutput(true); 
	          conn.setDoInput(true);      
	          conn.setUseCaches(false);        
	        conn.setRequestProperty("wms-identity",TuanDuiShangHuActivity.indenty);//员工号的MD5值
	        conn.setRequestProperty("wms-message", "mtxSh");	 
	        conn.setRequestProperty("wms-model", model);	
	        conn.setRequestProperty("wms-encrypt", "3des");
			conn.setRequestProperty("wms-compress", "gzip");
			
	 		os = conn.getOutputStream();		 		
	 		byte[]bs=json.getBytes("UTF-8");
			bs=Compress.compress(bs);
			bs=Des.EncryptMode(SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei),bs);
	 		os.write(bs);
			os.close();
			System.out.println("shujuduquwancheng");
			if(conn.getResponseCode()!=200){
				System.out.println(conn.getResponseCode());	
				byte[] s = conn.getHeaderField("wms-error").getBytes("ISO-8859-1");
				//System.out.println(new String(s));
				System.out.println(conn.getResponseMessage());	
				if("MTX0003".equals(model)){
					jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"提交失败，请重新提交\"}";

				}else{
					
					jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"查询失败,请重新查询\"}";
				}
				techown.shanghu.https.Log.Instance().WriteLog("服务器responseCode:"+conn.getResponseCode()+" 返回信息:"+new String(s));
			}
			else
			{
				System.out.println("request sucess");
				InputStream in = conn.getInputStream();
				byte[] buffer = new byte[1024];
				if ("3des".equals(conn.getHeaderField("wms-encrypt"))) {
					in = DESUtil.decrypt(in,
							SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei));// 员工号和手机imei号
				}
				if ("gzip".equals(conn.getHeaderField("wms-compress"))) {
					in = new GZIPInputStream(in);

				}
				bos = new ByteArrayOutputStream();								
				int i = 0;
				while ((i = in.read()) != -1) {
					bos.write(i);
				}
				byte[] re = bos.toByteArray();
				jsontext= new String(re, "UTF-8").trim();	
				Log.i("chengqk","HttpConnection:"+jsontext.toString());
				bos.close();
				in.close();		
			}
	  	}
	  	catch(SocketTimeoutException e)
	  	{
	  		jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络超时\"}";
	  		techown.shanghu.https.Log.Instance().WriteLog(model+" 的操作：网络超时");
	  		return jsontext;
	  	
	  	}
	  	catch(Exception e)
	  	{
			jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络连接异常\"}";
			techown.shanghu.https.Log.Instance().WriteLog(model+" 的操作：网络连接异常");
	  		return jsontext;

	  	}
	  	finally{
	  		try{
		  		if(os!=null)os.close();
		  		if(bos!=null)bos.close();
	  		}
	  		catch(Exception e){}
	  	}
		return jsontext;
	  }
	public static String ycgdRequest1(String json,String model)
	  {
		String jsontext=null;
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
	  	try
	  	{	  
	  		
	  		URL url = new URL(HttpconnectionActivity.uriAPI);
			SSLClient.initializeSSLContext();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();	  			         
	          conn.setRequestMethod("POST"); 
	          conn.setConnectTimeout(25 * 1000);
	          conn.setDoOutput(true); 
	          conn.setDoInput(true);      
	          conn.setUseCaches(false);        
	        conn.setRequestProperty("wms-identity",TuanDuiShangHuActivity.indenty);//员工号的MD5值
	        conn.setRequestProperty("wms-message", "mmsSendSJFKTaskToPDA");	 
	        conn.setRequestProperty("wms-model", model);	
	        conn.setRequestProperty("wms-encrypt", "3des");
			conn.setRequestProperty("wms-compress", "gzip");
			
	 		os = conn.getOutputStream();		 		
	 		byte[]bs=json.getBytes("UTF-8");
			bs=Compress.compress(bs);
			bs=Des.EncryptMode(SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei),bs);
	 		os.write(bs);
			os.close();
			System.out.println("shujuduquwancheng");
			if(conn.getResponseCode()!=200){
				System.out.println(conn.getResponseCode());	
				byte[] s = conn.getHeaderField("wms-error").getBytes("ISO-8859-1");
				//jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"查询失败,请重新查询\"}";
				techown.shanghu.https.Log.Instance().WriteLog("服务器responseCode:"+conn.getResponseCode()+" 返回信息:"+new String(s));
			}
			else
			{
				System.out.println("request sucess");
				InputStream in = conn.getInputStream();
				byte[] buffer = new byte[1024];
				if ("3des".equals(conn.getHeaderField("wms-encrypt"))) {
					in = DESUtil.decrypt(in,
							SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei));// 员工号和手机imei号
				}
				if ("gzip".equals(conn.getHeaderField("wms-compress"))) {
					in = new GZIPInputStream(in);

				}
				bos = new ByteArrayOutputStream();								
				int i = 0;
				while ((i = in.read()) != -1) {
					bos.write(i);
				}
				byte[] re = bos.toByteArray();
				jsontext= new String(re, "UTF-8").trim();	
				Log.i("chengqk","HttpConnection:"+jsontext.toString());
				bos.close();
				in.close();		
			}
	  	}
	  	catch(SocketTimeoutException e)
	  	{
	  		jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络超时\"}";
	  		techown.shanghu.https.Log.Instance().WriteLog(model+" 的操作：网络超时");
	  		return jsontext;
	  	
	  	}
	  	catch(Exception e)
	  	{
			jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络连接异常\"}";
			techown.shanghu.https.Log.Instance().WriteLog(model+" 的操作：网络连接异常");
	  		return jsontext;

	  	}
	  	finally{
	  		try{
		  		if(os!=null)os.close();
		  		if(bos!=null)bos.close();
	  		}
	  		catch(Exception e){}
	  	}
		return jsontext;
	  }
	public static String ycgdRequest2(String json,String model)
	  {
		String jsontext=null;
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
	  	try
	  	{	  
	  		
	  		URL url = new URL(HttpconnectionActivity.uriAPI);
			SSLClient.initializeSSLContext();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();	  			         
	          conn.setRequestMethod("POST"); 
	          conn.setConnectTimeout(25 * 1000);
	          conn.setDoOutput(true); 
	          conn.setDoInput(true);      
	          conn.setUseCaches(false);        
	        conn.setRequestProperty("wms-identity",TuanDuiShangHuActivity.indenty);//员工号的MD5值
	        conn.setRequestProperty("wms-message", "mmsReceiveFinSJFKFromPDA");	 
	        conn.setRequestProperty("wms-model", model);	
	        conn.setRequestProperty("wms-encrypt", "3des");
			conn.setRequestProperty("wms-compress", "gzip");
			
	 		os = conn.getOutputStream();		 		
	 		byte[]bs=json.getBytes("UTF-8");
			bs=Compress.compress(bs);
			bs=Des.EncryptMode(SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei),bs);
	 		os.write(bs);
			os.close();
			System.out.println("shujuduquwancheng");
			if(conn.getResponseCode()!=200){
				System.out.println(conn.getResponseCode());	
				byte[] s = conn.getHeaderField("wms-error").getBytes("ISO-8859-1");
				//jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"查询失败,请重新查询\"}";
				techown.shanghu.https.Log.Instance().WriteLog("服务器responseCode:"+conn.getResponseCode()+" 返回信息:"+new String(s));
			}
			else
			{
				System.out.println("request sucess");
				InputStream in = conn.getInputStream();
				byte[] buffer = new byte[1024];
				if ("3des".equals(conn.getHeaderField("wms-encrypt"))) {
					in = DESUtil.decrypt(in,
							SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei));// 员工号和手机imei号
				}
				if ("gzip".equals(conn.getHeaderField("wms-compress"))) {
					in = new GZIPInputStream(in);

				}
				bos = new ByteArrayOutputStream();								
				int i = 0;
				while ((i = in.read()) != -1) {
					bos.write(i);
				}
				byte[] re = bos.toByteArray();
				jsontext= new String(re, "UTF-8").trim();	
				Log.i("chengqk","HttpConnection:"+jsontext.toString());
				bos.close();
				in.close();		
			}
	  	}
	  	catch(SocketTimeoutException e)
	  	{
	  		jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络超时\"}";
	  		techown.shanghu.https.Log.Instance().WriteLog(model+" 的操作：网络超时");
	  		return jsontext;
	  	
	  	}
	  	catch(Exception e)
	  	{
			jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络连接异常\"}";
			techown.shanghu.https.Log.Instance().WriteLog(model+" 的操作：网络连接异常");
	  		return jsontext;

	  	}
	  	finally{
	  		try{
		  		if(os!=null)os.close();
		  		if(bos!=null)bos.close();
	  		}
	  		catch(Exception e){}
	  	}
		return jsontext;
	  }
	
	public static String shgdSerach(String json,String model)
	  {
		String jsontext=null;
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
	  	try
	  	{	  
	  		
	  		URL url = new URL(HttpconnectionActivity.uriAPI);
			SSLClient.initializeSSLContext();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();	  			         
	          conn.setRequestMethod("POST"); 
	          conn.setConnectTimeout(25 * 1000);
	          conn.setDoOutput(true); 
	          conn.setDoInput(true);      
	          conn.setUseCaches(false);        
	        conn.setRequestProperty("wms-identity",TuanDuiShangHuActivity.indenty);//员工号的MD5值
	        conn.setRequestProperty("wms-message", "MMSTaskWebServiceForPDA");	 
	        conn.setRequestProperty("wms-model", model);	
	        conn.setRequestProperty("wms-encrypt", "3des");
			conn.setRequestProperty("wms-compress", "gzip");
			
	 		os = conn.getOutputStream();		 		
	 		byte[]bs=json.getBytes("UTF-8");
			bs=Compress.compress(bs);
			bs=Des.EncryptMode(SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei),bs);
	 		os.write(bs);
			os.close();
			System.out.println("shujuduquwancheng");
			if(conn.getResponseCode()!=200){
				System.out.println(conn.getResponseCode());	
				byte[] s = conn.getHeaderField("wms-error").getBytes("ISO-8859-1");
				//jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"查询失败,请重新查询\"}";
				techown.shanghu.https.Log.Instance().WriteLog("服务器responseCode:"+conn.getResponseCode()+" 返回信息:"+new String(s));
			}
			else
			{
				System.out.println("request sucess");
				InputStream in = conn.getInputStream();
				byte[] buffer = new byte[1024];
				if ("3des".equals(conn.getHeaderField("wms-encrypt"))) {
					in = DESUtil.decrypt(in,
							SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei));// 员工号和手机imei号
				}
				if ("gzip".equals(conn.getHeaderField("wms-compress"))) {
					in = new GZIPInputStream(in);

				}
				bos = new ByteArrayOutputStream();								
				int i = 0;
				while ((i = in.read()) != -1) {
					bos.write(i);
				}
				byte[] re = bos.toByteArray();
				jsontext= new String(re, "UTF-8").trim();	
				Log.i("chengqk","HttpConnection:"+jsontext.toString());
				bos.close();
				in.close();		
			}
	  	}
	  	catch(SocketTimeoutException e)
	  	{
	  		jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络超时\"}";
	  		techown.shanghu.https.Log.Instance().WriteLog(model+" 的操作：网络超时");
	  		return jsontext;
	  	
	  	}
	  	catch(Exception e)
	  	{
			jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络连接异常\"}";
			techown.shanghu.https.Log.Instance().WriteLog(model+" 的操作：网络连接异常");
	  		return jsontext;

	  	}
	  	finally{
	  		try{
		  		if(os!=null)os.close();
		  		if(bos!=null)bos.close();
	  		}
	  		catch(Exception e){}
	  	}
		return jsontext;
	  }

	
	public static String RedRequest(String json,String model)
	  {
		String jsontext=null;
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
	  	try
	  	{	  			  	             
	  		URL url = new URL(HttpconnectionActivity.uriAPI);
			SSLClient.initializeSSLContext();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();	  			         
	          conn.setRequestMethod("POST"); 
	          conn.setConnectTimeout(25 * 1000);
	          conn.setDoOutput(true); 
	          conn.setDoInput(true);      
	          conn.setUseCaches(false);        
	        conn.setRequestProperty("wms-identity",TuanDuiShangHuActivity.indenty);//员工号的MD5值
	        conn.setRequestProperty("wms-message", "dy");	 
	        conn.setRequestProperty("wms-model", model);	
	        conn.setRequestProperty("wms-encrypt", "3des");
			conn.setRequestProperty("wms-compress", "gzip");	        	        
	 		os = conn.getOutputStream();		 		
	 		byte[]bs=json.getBytes("UTF-8");
			bs=Compress.compress(bs);
			bs=Des.EncryptMode(SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei),bs);
	 		os.write(bs);
			os.close();
			System.out.println("shujuduquwancheng");
			if(conn.getResponseCode()!=200){
				System.out.println(conn.getResponseCode());		
				System.out.println(conn.getHeaderField("wms-error"));
				System.out.println(conn.getResponseMessage());				
			}
			else
			{
				System.out.println("request sucess");
				InputStream in = conn.getInputStream();
				
				if ("3des".equals(conn.getHeaderField("wms-encrypt"))) {
					in = DESUtil.decrypt(in,
							SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei));// 员工号和手机imei号
				}
				if ("gzip".equals(conn.getHeaderField("wms-compress"))) {
					in = new GZIPInputStream(in);

				}
				bos = new ByteArrayOutputStream();								
				int i = 0;
				while ((i = in.read()) != -1) {
					bos.write(i);
				}
				byte[] re = bos.toByteArray();
				jsontext= new String(re, "UTF-8").trim();				
				bos.close();
				in.close();		
			}
	  	}
	  	catch(SocketTimeoutException e)
	  	{
	  		jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络超时\"}";
	  		return jsontext;
	  	}
	  	catch(Exception e)
	  	{
			jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络连接异常\"}";
	  		return jsontext;

	  	}
	  	finally{
	  		try{
		  		if(os!=null)os.close();
		  		if(bos!=null)bos.close();
	  		}
	  		catch(Exception e){}
	  	}
		return jsontext;
	  }
	
	
	public static String RedRequestYuShen(String json,String model)
	  {
		String jsontext=null;
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
	  	try
	  	{	  			  	             
	  		URL url = new URL(HttpconnectionActivity.uriAPI);
			SSLClient.initializeSSLContext();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();	  			         
	          conn.setRequestMethod("POST"); 
	          conn.setConnectTimeout(25 * 1000);
	          conn.setDoOutput(true); 
	          conn.setDoInput(true);      
	          conn.setUseCaches(false);        
	        conn.setRequestProperty("wms-identity",TuanDuiShangHuActivity.indenty);//员工号的MD5值
	        conn.setRequestProperty("wms-message", "special");	 
	        conn.setRequestProperty("wms-model", model);	
	        conn.setRequestProperty("wms-encrypt", "3des");
			conn.setRequestProperty("wms-compress", "gzip");	        	        
	 		os = conn.getOutputStream();		 		
	 		byte[]bs=json.getBytes("UTF-8");
			bs=Compress.compress(bs);
			bs=Des.EncryptMode(SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei),bs);
	 		os.write(bs);
			os.close();
			System.out.println("shujuduquwancheng");
			if(conn.getResponseCode()!=200){
				System.out.println(conn.getResponseCode());		
				System.out.println(conn.getHeaderField("wms-error"));
				System.out.println(conn.getResponseMessage());				
			}
			else
			{
				System.out.println("request sucess");
				Log.i("chengqk","HttpConnection    ");
				InputStream in = conn.getInputStream();
				
				if ("3des".equals(conn.getHeaderField("wms-encrypt"))) {
					in = DESUtil.decrypt(in,
							SecretkeyUtil.getKey(TuanDuiShangHuActivity.username,TuanDuiShangHuActivity.imei));// 员工号和手机imei号
				}
				if ("gzip".equals(conn.getHeaderField("wms-compress"))) {
					in = new GZIPInputStream(in);

				}
				bos = new ByteArrayOutputStream();								
				int i = 0;
				while ((i = in.read()) != -1) {
					bos.write(i);
				}
				byte[] re = bos.toByteArray();
				jsontext= new String(re, "UTF-8").trim();				
				bos.close();
				in.close();		
			}
	  	}
	  	catch(SocketTimeoutException e)
	  	{
	  		jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络超时\"}";
	  		return jsontext;
	  	}
	  	catch(Exception e)
	  	{
			jsontext="{\"rspCode\":\"800\",\"rspInfo\":\"网络连接异常\"}";
	  		return jsontext;

	  	}
	  	finally{
	  		try{
		  		if(os!=null)os.close();
		  		if(bos!=null)bos.close();
	  		}
	  		catch(Exception e){}
	  	}
		return jsontext;
	  }
	
}
