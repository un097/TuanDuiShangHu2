package techown.shanghu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.GetDate;
import techown.shanghu.https.PostService;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class ZuBao1 {
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
	 
	 
	public ZuBao1(Context context){
		this.context = context;
	}
	
	public void fun(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from youhushangxian where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 6; i < count; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}

				else {
					fqbStr.append(des.jieMI(cursor.getString(i)) + "|");
				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"online.txt"); 
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
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/youhuishang/"+PostService.id6+"/pic/",
//					 "/sdcard/"+path+"online.zip",
//					 "TJB/TuanDui/youhuishang/"+PostService.id6+"/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + PostService.id6);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + path ));
			}	
			 
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/youhuishang/" + path,"/sdcard/"+path
								+ "online.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "online.zip","/sdcard/1");	 
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
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	
	public void fun12(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from youhushangxian where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 6; i < count; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}

				else {
					fqbStr.append(des.jieMI(cursor.getString(i)) + "|");
				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"online.txt"); 
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
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/youhuishang/"+PostService.id7+"/pic/",
//					 "/sdcard/"+path+"online.zip",
//					 "TJB/TuanDui/youhuishang/"+PostService.id7+"/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + PostService.id7);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/youhuishang/" + path,"/sdcard/"+path
								+ "online.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "online.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 //lxp
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	
	public void fun9(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from youhushangxian where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 6; i < count; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}

				else {
					fqbStr.append(des.jieMI(cursor.getString(i)) + "|");
				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"online.txt"); 
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
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/youhuishang/"+PostService.id8+"/pic/",
//					 "/sdcard/"+path+"online.zip",
//					 "TJB/TuanDui/youhuishang/"+PostService.id8+"/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + PostService.id8);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/youhuishang/" + path,"/sdcard/"+path
								+ "online.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "online.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 //lxp
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	public void fun91(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from youhushangxian where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 6; i < count; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}

				else {
					fqbStr.append(des.jieMI(cursor.getString(i)) + "|");
				}
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"online.txt"); 
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
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/youhuishang/"+PostService.id9+"/pic/",
//					 "/sdcard/"+path+"online.zip",
//					 "TJB/TuanDui/youhuishang/"+PostService.id9+"/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + PostService.id9);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/youhuishang/" + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/youhuishang/" + path,"/sdcard/"+path
								+ "online.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
//				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "online.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 
			 //lxp
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	
	public void fun5(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from gongdanweihu where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 7; i < count; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}

				else {
					if(i==13||i==11||i==12||i==14||i==10)
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
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"task.txt"); 
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
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/gongdan/"+PostService.id10+"/pic/",
//					 "/sdcard/"+path+"task.zip",
//					 "TJB/TuanDui/gongdan/"+PostService.id10+"/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/gongdan/" + PostService.id10);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/gongdan/" + path ));
//					
				}
			 
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/gongdan/"  + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/gongdan/" + path,"/sdcard/"+path
								+ "task.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "task.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 //lxp
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	
	public void fun51(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from gongdanweihu where id==?",
				new String[] { id });
		
		
		
		while(cursor.moveToNext()){
			StringBuffer fqbStr = new StringBuffer();
			cursor.moveToLast();
			int count = cursor.getColumnCount();
			for (int i = 7; i < count; i++) {
				if (cursor.getString(i) == null) {
					fqbStr.append("|");
				}

				else {
					if(i==13||i==11||i==12||i==14||i==10)
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
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"task.txt"); 
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
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/gongdan/"+PostService.id11+"/pic/",
//					 "/sdcard/"+path+"task.zip",
//					 "TJB/TuanDui/gongdan/"+PostService.id11+"/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/gongdan/" + PostService.id11);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/gongdan/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/gongdan/"  + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/gongdan/" + path,"/sdcard/"+path
								+ "task.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "task.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 //lxp
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
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
		Cursor cursor = db.rawQuery("select * from gongdanweihu where id==?",
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
					if(i==13||i==11||i==12||i==14||i==10)
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
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"task.txt"); 
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
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/gongdan/" + PostService.id12);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/gongdan/" + path ));
				}
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/gongdan/"  + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/gongdan/" + path,"/sdcard/"+path
								+ "task.zip");	
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
	
	
	
	public void funsent(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from gongdanweihusent where id==?",
				new String[] { des.jiaMi(id) });
		
		
		
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
					if(i==13||i==11||i==12||i==14||i==10)
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
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"task.txt"); 
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
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/gongdan/"+PostService.id12+"/pic/",
//					 "/sdcard/"+path+"task.zip",
//					 "TJB/TuanDui/gongdan/"+PostService.id12+"/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/gongdan/" + PostService.id120);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/gongdan/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/gongdan/"  + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/gongdan/" + path,"/sdcard/"+path
								+ "task.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "task.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 //lxp
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	
	
	public void fun15(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from gongdanweihu where id==?",
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
					if(i==13||i==11||i==12||i==14||i==10)
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
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"task.txt"); 
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
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/gongdan/"+PostService.id13+"/pic/",
//					 "/sdcard/"+path+"task.zip",
//					 "TJB/TuanDui/gongdan/"+PostService.id13+"/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/gongdan/" + PostService.id13);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/gongdan/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/gongdan/"  + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/gongdan/" + path,"/sdcard/"+path
								+ "task.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "task.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 
			 //lxp
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	public void fun2(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from gongdanweihu where id==?",
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
					if(i==13||i==11||i==12||i==14||i==10)
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
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"task.txt"); 
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
//			 ZIPUtil.zip(GongDanWeiHu4.path,
//					 "/sdcard/"+path+"task.zip",
//					 GongDanWeiHu4.path);
			 
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File(GongDanWeiHu4.path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder(GongDanWeiHu4.path,"/sdcard/"+path
								+ "task.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "task.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	
	//商户工单维护压缩文件
	public void fun71(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from shgongdanweihu where id==?",
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
					if(i==13||i==11||i==12||i==14||i==10)
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
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"newtask.txt"); 
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
//			 ZIPUtil.zip("/sdcard/"+"TJB/TuanDui/gongdan/"+PostService.id13+"/pic/",
//					 "/sdcard/"+path+"task.zip",
//					 "TJB/TuanDui/gongdan/"+PostService.id13+"/pic/");
			 
			 //lxp
			 File fit2 = new File("/sdcard/"+"TJB/TuanDui/shgongdan/" + PostService.id71);
			 if (fit2.exists()) {
					fit2.renameTo(new File("/sdcard/"+"TJB/TuanDui/shgongdan/" + path ));
//					
				}
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File("/sdcard/"+"TJB/TuanDui/shgongdan/"  + path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder("/sdcard/"+"TJB/TuanDui/shgongdan/" + path,"/sdcard/"+path
								+ "newtask.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "task.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 
			 //lxp
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	
	
	public void fun3(String id,String path){
		myHelper =new MyDatabaseHelper(context, "techown.db", Constant.tdshDBVer);
		db = myHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from gongdanweihu where id==?",
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
					if(i==13||i==11||i==12||i==14||i==10)
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
			}
			fqbStr = fqbStr.delete(fqbStr.length() - 1, fqbStr.length());

			try
	        {
			 File fTxt=new File("/sdcard/"+path+"task.txt"); 
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
//			 ZIPUtil.zip(WentiGongDan.path,
//					 "/sdcard/"+path+"task.zip",
//					 WentiGongDan.path);
			 
//			 File fit4 = new File("/sdcard/1");
//			 while(!fit4.exists()){
				 File fit3= new File(WentiGongDan.path);
				 if(fit3.exists())
					{
					 ZipUtilNew.zipFolder(WentiGongDan.path,"/sdcard/"+path
								+ "task.zip");	
						System.out.println("压缩完成");
						techown.shanghu.https.Log.Instance().WriteLog("ZuBao: File fTxt()" + "压缩完成");
					}
				 
				 //lxp
				 
//				 ZipUtilNew.unZipFolder("/sdcard/"+path
//								+ "task.zip","/sdcard/1");	 
//			 }
			 
			 
//			 if(fit4.exists()){
//				 deleteDir(fit4);
//			 }
			 
			 
			 
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.zip",path1+"active.zip",1);
//	         
//			HttpconnectionActivity.Uploadzip("/sdcard/"+path1+"active.txt",path1+"active.txt",1);
		         

		}
		db.close();
		cursor.close();
	}
	
	
	public void deleteDir(File dir) { 
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
