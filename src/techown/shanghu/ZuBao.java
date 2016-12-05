package techown.shanghu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.tools.ant.taskdefs.Delete;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.GetDate;
import techown.shanghu.https.PostService;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ZuBao {
	static SQLiteDatabase db;

	MyDatabaseHelper myHelper;
	Context context;
	String path = "pic/";
	String path2="/sdcard/"+"pic2/";
	DES des = new DES();
	
	 public static String time = GetDate.getDate().toString();
	 String num=PostService.isomux;
//	 public static String path1 = "t_"+"A024146"+"_"+HeYueQianDing_0.id+"_"+time+".";
	 String isomux;
	 SQLiteDatabase db1 = null;	 
	
	public ZuBao(Context context){
		this.context = context;
	}
	public void fun(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 7; i < count-1; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}
				else
				{
					if(i==27||i==28||i==47||i==62||i==63||i==64||i==65||i==66||i==67||i==68||i==69||i==70||i==73)
					{
						fqbStr.append(GetDate.dateChange(des.jieMI(cursor.getString(i)))+"|");
					}
//					else if(i==19||i==20||i==32||i==33||i==195)
//					{
//						sb.append(GetDate.addressChange(cur.getString(i))+"|");
//					}
					else
					{
						fqbStr.append(des.jieMI(cursor.getString(i))+"|");
					}
					
					
				}
//				else {
//					fqbStr.append(cursor.getString(i) + "|");
//				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"active.txt"); 
				 if(fTxt.exists()){
					 fTxt.delete();
				 }
	             if (!fTxt.exists())
	     		{
	             	fTxt.createNewFile();
	     		}
	             OutputStream out = new FileOutputStream(fTxt);
//	             byte [] utf8Header={(byte)0xef,(byte)0xbb,(byte) 0xbf};
//	             out.write(utf8Header);
	             out.write(fqbStr.toString().getBytes("GB2312"));
	             out.close();
	     		
	        }
	        catch(Exception e)
	        {
	        	techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + e.getMessage());
	        }
	        
	        
	      try {
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/shang/" + PostService.id + "/pic/",
//					 "/sdcard/"+path+"active.zip",
//					 "TJB/TuanDui/shang/" + PostService.id + "/pic/");
//			
			 
			 
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/shang/" + PostService.id);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/shang/" + path ));
//					
				}
			 
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/shang/" + path);
				 if(fit3.exists())
					{
						ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/shang/" + path,"/sdcard/"+path
								+ "active.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "active.zip","/sdcard/1");	 
//			 }
			 
			 
			 
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
				 techown.shanghu.https.Log.Instance().WriteLog("ZuBao: ZIPUtil.zip()" + e.getMessage());
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	public void fun11(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 7; i < count-1; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}
				else
				{
					if(i==27||i==28||i==47||i==62||i==63||i==64||i==65||i==66||i==67||i==68||i==69||i==70||i==73)
					{
						fqbStr.append(GetDate.dateChange(des.jieMI(cursor.getString(i)))+"|");
					}
//					else if(i==19||i==20||i==32||i==33||i==195)
//					{
//						sb.append(GetDate.addressChange(cur.getString(i))+"|");
//					}
					else
					{
						fqbStr.append(des.jieMI(cursor.getString(i))+"|");
					}
					
					
				}
//				else {
//					fqbStr.append(cursor.getString(i) + "|");
//				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"active.txt"); 
				 if(fTxt.exists()){
					 fTxt.delete();
				 }
	             if (!fTxt.exists())
	     		{
	             	fTxt.createNewFile();
	     		}
	             OutputStream out = new FileOutputStream(fTxt);
//	             byte [] utf8Header={(byte)0xef,(byte)0xbb,(byte) 0xbf};
//	             out.write(utf8Header);
	             out.write(fqbStr.toString().getBytes("GB2312"));
	             out.close();
	     		
	        }
	        catch(Exception e)
	        {
	        	techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + e.getMessage());
	        }
	        
	        
	      try {
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/shang/" + PostService.id1 + "/pic/",
//					 "/sdcard/"+path+"active.zip",
//					 "TJB/TuanDui/shang/" + PostService.id1 + "/pic/");
			 
			 
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/shang/" + PostService.id1);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/shang/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/shang/" + path);
				 if(fit3.exists())
					{
						ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/shang/" + path,"/sdcard/"+path
								+ "active.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "active.zip","/sdcard/1");	 
//			 }
//			 
//			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 //lxp
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
				 techown.shanghu.https.Log.Instance().WriteLog("ZuBao: ZIPUtil.zip()" + e.getMessage());
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	public void funj(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db",Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 7; i < count-1; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}
				else
				{
					if(i==27||i==28||i==47||i==62||i==63||i==64||i==65||i==66||i==67||i==68||i==69||i==70||i==73)
					{
						fqbStr.append(GetDate.dateChange(des.jieMI(cursor.getString(i)))+"|");
					}
//					else if(i==19||i==20||i==32||i==33||i==195)
//					{
//						sb.append(GetDate.addressChange(cur.getString(i))+"|");
//					}
					else
					{
						fqbStr.append(des.jieMI(cursor.getString(i))+"|");
					}
					
					
				}
//				else {
//					fqbStr.append(cursor.getString(i) + "|");
//				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"active.txt"); 
				 if(fTxt.exists()){
					 fTxt.delete();
				 }
	             if (!fTxt.exists())
	     		{
	             	fTxt.createNewFile();
	     		}
	             OutputStream out = new FileOutputStream(fTxt);
//	             byte [] utf8Header={(byte)0xef,(byte)0xbb,(byte) 0xbf};
//	             out.write(utf8Header);
	             out.write(fqbStr.toString().getBytes("GB2312"));
	             out.close();
	     		
	        }
	        catch(Exception e)
	        {
	        	techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt3()" + e.getMessage());
	        }
	        
	        
	      try {
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/shang/" + PostService.id2 + "/pic/",
//					 "/sdcard/"+path+"active.zip",
//					 "TJB/TuanDui/shang/" + PostService.id2 + "/pic/");
//			 
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/shang/" + PostService.id2);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/shang/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/shang/" + path);
				 if(fit3.exists())
					{
						ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/shang/" + path,"/sdcard/"+path
								+ "active.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "active.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 //lxp
			 
			 
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
				 techown.shanghu.https.Log.Instance().WriteLog("ZuBao: ZIPUtil.zip3()" + e.getMessage());
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	
	public void funj1(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 7; i < count-1; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}
				else
				{
					if(i==27||i==28||i==47||i==62||i==63||i==64||i==65||i==66||i==67||i==68||i==69||i==70||i==73)
					{
						fqbStr.append(GetDate.dateChange(des.jieMI(cursor.getString(i)))+"|");
					}
					else
					{
						fqbStr.append(des.jieMI(cursor.getString(i))+"|");
					}
					
					
				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"active.txt"); 
				 if(fTxt.exists()){
					 fTxt.delete();
				 }
	             if (!fTxt.exists())
	     		{
	             	fTxt.createNewFile();
	     		}
	             OutputStream out = new FileOutputStream(fTxt);
//	             byte [] utf8Header={(byte)0xef,(byte)0xbb,(byte) 0xbf};
//	             out.write(utf8Header);
	             out.write(fqbStr.toString().getBytes("GB2312"));
	             out.close();
	     		
	        }
	        catch(Exception e)
	        {
	        	techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt4()" + e.getMessage());
	        }
	        
	        
	      try {
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/shang/" + PostService.id3 + "/pic/",
//					 "/sdcard/"+path+"active.zip",
//					 "TJB/TuanDui/shang/" + PostService.id3 + "/pic/");
			 
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/shang/" + PostService.id3);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/shang/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/shang/" + path);
				 if(fit3.exists())
					{
						ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/shang/" + path,"/sdcard/"+path
								+ "active.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "active.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 
			 //lxp
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
				 techown.shanghu.https.Log.Instance().WriteLog("ZuBao: ZIPUtil.zip4()" + e.getMessage());
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	public void fun1(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 7; i < count-1; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}
				else
				{
					if(i==27||i==28||i==47||i==62||i==63||i==64||i==65||i==66||i==67||i==68||i==69||i==70||i==73)
					{
						fqbStr.append(GetDate.dateChange(des.jieMI(cursor.getString(i)))+"|");
					}
					else
					{
						fqbStr.append(des.jieMI(cursor.getString(i))+"|");
					}
					
					
				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"active.txt"); 
				 if(fTxt.exists()){
					 fTxt.delete();
				 }
	             if (!fTxt.exists())
	     		{
	             	fTxt.createNewFile();
	     		}
	             OutputStream out = new FileOutputStream(fTxt);
	             out.write(fqbStr.toString().getBytes("GB2312"));
	             out.close();
	     		
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
	        
	      try {
				 File fit3= new File(TuanDuiShangHuActivity.path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder(TuanDuiShangHuActivity.path,"/sdcard/"+path
								+ "active.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
		}
		db.close();
		cursor.close();
	}
	
	public void fun2(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from st_storeinfo where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 7; i < count-1; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}
				else
				{
					if(i==27||i==28||i==47||i==62||i==63||i==64||i==65||i==66||i==67||i==68||i==69||i==70||i==73)
					{
						fqbStr.append(GetDate.dateChange(des.jieMI(cursor.getString(i)))+"|");
					}
//					else if(i==19||i==20||i==32||i==33||i==195)
//					{
//						sb.append(GetDate.addressChange(cur.getString(i))+"|");
//					}
					else
					{
						fqbStr.append(des.jieMI(cursor.getString(i))+"|");
					}
					
					
				}
//				else {
//					fqbStr.append(cursor.getString(i) + "|");
//				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"active.txt"); 
				 if(fTxt.exists()){
					 fTxt.delete();
				 }
	             if (!fTxt.exists())
	     		{
	             	fTxt.createNewFile();
	     		}
	             OutputStream out = new FileOutputStream(fTxt);
//	             byte [] utf8Header={(byte)0xef,(byte)0xbb,(byte) 0xbf};
//	             out.write(utf8Header);
	             out.write(fqbStr.toString().getBytes("GB2312"));
	             out.close();
	     		
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
	        
	      try {
//			 ZIPUtil.zip("sdcard/"+"TJB/TuanDui/shang/" + PostService.id4 + "/pic/",
//					 "/sdcard/"+path+"active.zip",
//					 "TJB/TuanDui/shang/" + PostService.id4 + "/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/shang/" + PostService.id4);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/shang/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/shang/" + path);
				 if(fit3.exists())
					{
						ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/shang/" + path,"/sdcard/"+path
								+ "active.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "active.zip","/sdcard/1");	 
//			 }
//			 
//			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 
			 //lxp
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
		}
		db.close();
		cursor.close();
	}
	
	public void redfun(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from red_enterletter where id==?",
				new String[] { id });
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 7; i < count; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}
				else
				{
					fqbStr.append(des.jieMI(cursor.getString(i))+"|");
				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());
			try
	        {
			 File fTxt=new File("/sdcard/"+path+"redactive.txt"); 
				 if(fTxt.exists()){
					 fTxt.delete();
				 }
	             if (!fTxt.exists())
	     		{
	             	fTxt.createNewFile();
	     		}
	             OutputStream out = new FileOutputStream(fTxt);
	             out.write(fqbStr.toString().getBytes("GB2312"));
	             out.close();
	        }
	        catch(Exception e)
	        {
	        	techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + e.getMessage());
	        }
	        
	        
	      try {
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/redwu/" + PostService.id);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/redwu/" + path ));
				}
			 
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/redwu/" + path);
				 if(fit3.exists())
					{
						ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/redwu/" + path,"/sdcard/"+path
								+ "redactive.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
				 techown.shanghu.https.Log.Instance().WriteLog("ZuBao: ZIPUtil.zip()" + e.getMessage());
			 }
		}
		db.close();
		cursor.close();
	}
	
	
	public   void deleteDir(File dir) { 
	    if (dir == null || !dir.exists() || !dir.isDirectory()) 
	        return; // 检查参数 
	    for (File file : dir.listFiles()) { 
	        if (file.isFile()) 
	            file.delete(); // 删除所有文件 
	        else if (file.isDirectory()) 
	            deleteDir(file); // 递规的方式删除文件夹 
	    } 
	    dir.delete();// 删除目录本身 
	    System.out.println("删除sdcard/1");
	    techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcardsdcard/1"+"原文件夹");					     	
   		
	} 
}
