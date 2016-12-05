package techown.shanghu.https;

import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import techown.shanghu.DBUtil;
import techown.shanghu.MyDatabaseHelper;
import techown.shanghu.biaoge.Constant;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HttpconnectionActivity {
	/** Called when the activity is first created. */


//	public static String uriAPI = "https://182.180.50.105:443/tjbproxy/proxy.do";//UAT
	public static String uriAPI = "https://182.180.98.110/tjbproxy/proxy.do";//生产
//	public static String uriAPI = "http://182.180.50.192:8080/tjbproxy/proxy.do";	
	public static int code;
	public static String error;
	DBUtil dbutil=new DBUtil();
	String username;
	String imei;
	String identity;
	int i=0;
	public  void getdata(Context con)
	{		
		Context friendContext = null;
		String[] str = null;
		SQLiteDatabase sld=null;
		Cursor cur=null;
//		dbutil = new DBUtil();
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
		MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
				Constant.loginDBVer);		
		str = new String[3];
		sld = myHelper.getWritableDatabase();
		cur = sld.query("EntityUser",new String[]{"username","imei","identity"},null
				,null, null, null, null);
		while (cur.moveToNext()) {
			for (int i = 0; i <3; i++) {
				if (cur.getString(i) != null) {
					str[i] = cur.getString(i);
				}
			}

		}
		cur.close();
		sld.close();
		
		username=str[0];
		imei=str[1];
		identity=str[2];
		}
		catch(Exception e)
		{
			if(cur!=null)
			{
				cur.close();
			}
			if(sld!=null)
			{
				sld.close();
			}
			techown.shanghu.https.Log.Instance().WriteLog("发件时读取参数失败");
		}
	}
	
	public void Uploadzip(final Context con, String filepath,
			String uploadfile, int blockindex, String sendbarcodeno) {
		if(username==null||imei==null||identity==null)
		{
			getdata(con);	
		}
		
		RandomAccessFile fTest=null;
//		FileInputStream fss=null;
	  	try
	  	{
	  		
	  		if (blockindex > 0)  //上传分段大于0
          {   
	  			
	  			fTest = new RandomAccessFile(filepath,"rw");  //本地文件写入流     
//	  			fss=new FileInputStream(fTest);

              //GooNuuHelp.Logs.WritemMessage(uploadfile + "大小为:" + fileStream.Length);

              int totalNum = (int)(fTest.length()/50600);    //分段总数
              int yushu = (int)(fTest.length()%50600);       //分段余数
              if (yushu != 0)
              {
                  totalNum++;
              }
              if (blockindex > totalNum)
              {
                  return;
              }
              for(int k=blockindex;k<=totalNum;k++)
              {
            	  
            
              int uplength =50600;
              long startReadIndex = (blockindex - 1) * 50600;  
              if (blockindex == totalNum)
              {
                  uplength = (int)(fTest.length() - startReadIndex);
              }
              byte[] bs = new byte[uplength];   //上传byte数组
              //fileStream.Seek(startReadIndex, SeekOrigin.Begin );
              // fileStream.Read(bs, startReadIndex, uplength);
             
//            boolean ifNeedEncode = false;   //是否需要Encode ^ 122处理
//              if (uploadfile.length()> 4)
//              {
//                  if (uploadfile.substring(uploadfile.length()- 4 , uploadfile.length()).toString().equals(".zip"))
//                  {
//                      ifNeedEncode = false;
//                  }
//              }
              for (long i = startReadIndex, j = startReadIndex + uplength; i < j; i++)
              {

                  //if (!GooNuuHelp.Comm.IsSend)
                  //{
                  //    return;
                  //}
            	  fTest.seek((long)i); 
            	 
            	  bs[(int) (i - startReadIndex)] =fTest.readByte();
//                  if (i >= startReadIndex)
//                  {
//                      if (ifNeedEncode)
//                      {
//                          bs[i - startReadIndex] = (byte)(((byte)bynum) ^ 122);
//                      }
//                      else
//                      {
//                          bs[i - startReadIndex] = (byte)bynum;
//                      }
//                  }
                  if ((i-startReadIndex)%7000==0)
                  {
                      Thread.sleep(1);
                  }
              }
            
            //log 记录日志文件
              URL url = new URL(uriAPI);
  			SSLClient.initializeSSLContext();
  			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();	  			         
  	          conn.setRequestMethod("POST"); 
  	          conn.setConnectTimeout(25 * 1000);
  	          conn.setDoOutput(true); 
  	          conn.setDoInput(true);      
  	          conn.setUseCaches(false);        
	        conn.setRequestProperty("wms-identity",identity);//员工号的MD5值
	        conn.setRequestProperty("wms-message", "upload");
	        conn.setRequestProperty("wms-encrypt", "3des");
			conn.setRequestProperty("wms-compress", "gzip");
	        conn.setRequestProperty("file-name",uploadfile);
	        conn.setRequestProperty("file-block-index", String.valueOf(blockindex));
	        conn.setRequestProperty("file-block-size",String.valueOf(totalNum));
	        System.out.println("qingqiuwancheng"+blockindex);
	        OutputStream os=null;
	        try
	        {
	        	os = conn.getOutputStream();	
	        	bs=Compress.compress(bs);
				bs=Des.EncryptMode(SecretkeyUtil.getKey(username,imei),bs);
		 		os.write(bs);
		 		os.flush();
				os.close();				
	        }catch(Exception e)
	        {
	        	
	        	if(os!=null)
	        	{
	        		os.flush();
					os.close();	
	        	}
	        	return;
	        }
	 		
	 		
			
			System.out.println("shujuduquwancheng");
			if(conn.getResponseCode()!=200){
				System.out.println(conn.getResponseCode());		
				System.out.println(conn.getHeaderField("wms-error"));
				techown.shanghu.https.Log.Instance().WriteLog("商户连接中间件失败"+conn.getResponseCode()+conn.getHeaderField("wms-error"));
			}
			else
			{
				//log 记录日志文件
				techown.shanghu.https.Log.Instance().WriteLog("商户上传进件完成"+blockindex+"段"+uploadfile);
				if(blockindex==totalNum)
				{
					techown.shanghu.https.Log.Instance().WriteLog("商户上传进件完成");
				}
				blockindex++;
				bs=null;
				
				dbutil.sendupload(
						con,
						new String[] { MyDatabaseHelper.BEGINNUM,
								MyDatabaseHelper.TOTALNUM },
						new String[] { String.valueOf(blockindex),
								String.valueOf(totalNum) }, sendbarcodeno);	

			
				//todo sql
				//Uploadzip(con,filepath,uploadfile,blockindex,sendbarcodeno);				
						
				
			}
              }
			
          }
	  	}
	  		catch(Exception e)
	  	{
	  		//log 记录日志文件
	  			
	  			
	  			techown.shanghu.https.Log.Instance().WriteLog("商户出错信息:"+e.getMessage());
	  		
	  	}
	  	finally
	  	{
	  		try {
	  			fTest.close();
//				fss.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				techown.shanghu.https.Log.Instance().WriteLog("商户出错信息:"+e.getMessage());
			}
	  	}
	  }
		
		
		
		
		
//		try {
//  
//			
//			if (blockindex > 0) // 上传分段大于0
//			{
//				File fTest = new File(filepath); // 本地文件写入流
//				FileInputStream fss = new FileInputStream(fTest);
//
//				// GooNuuHelp.Logs.WritemMessage(uploadfile + "大小为:" +
//				// fileStream.Length);
//
//				int totalNum = (int) (fTest.length() / 50600); // 分段总数
//				int yushu = (int) (fTest.length() % 50600); // 分段余数
//				if (yushu != 0) {
//					totalNum++;
//				}
//				if (blockindex > totalNum) {
//					return;
//				}
//				int uplength = 50600;
//				int startReadIndex = (blockindex - 1) * 50600;
//				if (blockindex == totalNum) {
//					uplength = (int) (fTest.length() - startReadIndex);
//				}
//				byte[] bs = new byte[uplength]; // 上传byte数组
//				// fileStream.Seek(startReadIndex, SeekOrigin.Begin );
//				// fileStream.Read(bs, startReadIndex, uplength);
//
//				boolean ifNeedEncode = false; // 是否需要Encode ^ 122处理
//				if (uploadfile.length() > 4) {
//					if (uploadfile
//							.substring(uploadfile.length() - 4,
//									uploadfile.length()).toString()
//							.equals(".zip")) {
//						ifNeedEncode = false;
//					}
//				}
//				for (int i = 0, j = startReadIndex + uplength; i < j; i++) {
//
//					// if (!GooNuuHelp.Comm.IsSend)
//					// {
//					// return;
//					// }
//
//					int bynum = fss.read();
//					if (i >= startReadIndex) {
//						if (ifNeedEncode) {
//							bs[i - startReadIndex] = (byte) (((byte) bynum) ^ 122);
//						} else {
//							bs[i - startReadIndex] = (byte) bynum;
//						}
//					}
//					if ((i - startReadIndex) % 7000 == 0) {
//						Thread.sleep(1);
//					}
//				}
//
//				URL url = new URL(uriAPI);
//				SSLClient.initializeSSLContext();
//				HttpsURLConnection conn = (HttpsURLConnection) url
//						.openConnection();
//				conn.setRequestMethod("POST");
//				
////				i++;
////				if(i==3){
////					conn.setConnectTimeout(1 * 10);
////				}else{
//					conn.setConnectTimeout(25 * 1000);
////				}
//				
//				conn.setDoOutput(true);
//				conn.setDoInput(true);
//				conn.setUseCaches(false);
//				conn.setRequestProperty("wms-identity",
//						identity);// 员工号的MD5值
//				conn.setRequestProperty("wms-message", "upload");
//				conn.setRequestProperty("wms-encrypt", "3des");
//				conn.setRequestProperty("wms-compress", "gzip");
//				conn.setRequestProperty("file-name", uploadfile);
//				conn.setRequestProperty("file-block-index",
//						String.valueOf(blockindex));
//				conn.setRequestProperty("file-block-size",
//						String.valueOf(totalNum));
//				System.out.println("qingqiuwancheng" + blockindex);
//				
////				//测试
////				i++;
////				System.out.println(i);
////				if(i==3){
////					username=null;
////					System.out.println(username+" if i=5");
////				}
////				//end
//				
//				
////				System.out.println(i+" and "+username);
////				if(uploadfile.contains("txt"))
////				{
////					return;
////				}
//				OutputStream os = conn.getOutputStream();
//
//				bs = Compress.compress(bs);
//				// byte[]bt=SecretkeyUtil.getKey(username,"355805030181708");
//				bs = Des.EncryptMode(SecretkeyUtil.getKey(
//						username,
//						imei), bs);
//				os.write(bs);
//				os.close();
//				System.out.println("shujuduquwancheng");
//				if (conn.getResponseCode() != 200) {
//					System.out.println(conn.getResponseCode());
//					System.out.println(conn.getHeaderField("wms-error"));
//					System.out.println(conn.getResponseMessage());
//
//				} else {
//					
//					//log 记录日志文件
//					techown.shanghu.https.Log.Instance().WriteLog("上传进件完成"+blockindex+"段"+uploadfile);
//					if(blockindex==totalNum)
//					{
////						PostService.isTXT=true;
//						techown.shanghu.https.Log.Instance().WriteLog("上传进件完成");
//					}
//					
//					blockindex++;
//						dbutil.sendupload(
//								con,
//								new String[] { MyDatabaseHelper.BEGINNUM,
//										MyDatabaseHelper.TOTALNUM },
//								new String[] { String.valueOf(blockindex),
//										String.valueOf(totalNum) }, sendbarcodeno);	
//
//					// todo sql
//					Uploadzip(con, filepath, uploadfile, blockindex,
//							sendbarcodeno);
//
//					System.out.println(conn.getResponseCode());
//
//				}
//			}
//		} catch (Exception e) {
//			
//			techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件"+blockindex+"段异常"+uploadfile);
//			techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
//			System.out.println("团队：上传进件出错信息:"+e.getMessage());
//			
//			Log.e("login", "连接出现异常");
//			return;
//		}
//	}

}