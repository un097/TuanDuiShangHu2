package techown.shanghu.https;

import java.io.File;
import java.util.ArrayList;
import techown.shanghu.DBUtil;
import techown.shanghu.MyDatabaseHelper;
import techown.shanghu.ZuBao;
import techown.shanghu.ZuBao1;
import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.photo.GongDanTakePhotoActivity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

public class PostService extends Service implements Runnable {
	public boolean isrun = true;
	Thread t;
	public ArrayList<String> fjnum = new ArrayList<String>(100);
	MyDatabaseHelper myHelper;
	String textname;
	String pathdelete;
	HttpconnectionActivity http = new HttpconnectionActivity();
	ZuBao zuBao = new ZuBao(PostService.this);
	ZuBao1 zuBao1 = new ZuBao1(PostService.this);
	public static String id,id1,id2,id3,id4,id5,id6,id7,id8,id9,id10,id11,id12,id13,id14,id15,id100,id120,id71;
	DES des = new DES();
	DBUtil DB=new DBUtil();
	
	public static boolean isTXT;
//	public static String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700010"+"_"+time+".";
	
	
	SQLiteDatabase db = null;
	Cursor cursor = null;
	public static String isomux;

	public void run() {
		while (isrun) {
			
			Context friendContext3 = null;
			try {
				friendContext3 = PostService.this.createPackageContext("techown.login",
						Context.CONTEXT_IGNORE_SECURITY);
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				techown.shanghu.https.Log.Instance().WriteLog("团队：PostService中登录数据库未找到");
			}
			if(friendContext3==null||!isDatabaseExists(friendContext3,"techown_login.db"))
			{
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					
				}
				continue;
			}
			else
			{
				
				if(Constant.username==null||Constant.imei==null)
				{
					Context friendContext = null;
					String[] str = null;
					SQLiteDatabase sld=null;
					Cursor cur= null;
					try {
						friendContext = PostService.this.createPackageContext("techown.login",
								Context.CONTEXT_IGNORE_SECURITY);
						if(friendContext!=null&&isDatabaseExists(friendContext3,"techown_login.db"))
						{	
							MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
										Constant.loginDBVer);		
							str = new String[3];
							sld = myHelper.getWritableDatabase();
							 cur = sld.query("EntityUser",new String[]{"username","imei","identity"},null
									,null, null, null, null);
							 if(cur.getCount() > 0){
							while (cur.moveToNext()) {
								for (int i = 0; i <3; i++) {
									if (cur.getString(i) != null) {
										str[i] = cur.getString(i);
									}
								}

					}
					
					
						Constant.username=str[0];
						Constant.imei=str[1];
						
							 }
					}else{
									try {
										Thread.sleep(2000);
									} catch (Exception e) {
										 
									}
									continue;
								}
				}
					catch(Exception e)
					{
						if(cur!=null)
							cur.close();
						if(sld!=null)
							sld.close();
						techown.shanghu.https.Log.Instance().WriteLog("团队：发送件时读取员工号异常");
						try {
							Thread.sleep(5000);
						} catch (Exception e2) {
							 
						}
					}
					finally{
						if(cur!=null)
							cur.close();
						if(sld!=null)
							sld.close();
					}
				}
				
				//删除登陆数据库中t_sentupload表中状态为1的文件
//				DB.Deletetsentuplaod(PostService.this, new String[]{"1"});
				
				//获得登录向中间件发送的文件
				String[] loginstr = DB.klQueryloginstr(PostService.this);
				if(loginstr!=null&&loginstr.length!=0)
				{
					//失败的工单文件名
					String[] arrgstr = DB.klQuery(PostService.this, "flowerNum", "t_sent");
					
					//进件失败的工单重新发送
					if(arrgstr!=null&&arrgstr.length!=0)
					{
						for (int i = 0; i < arrgstr.length; i++) {
							System.out.println("i=="+i+",arrgstr.length=="+arrgstr.length);
							PostServiceTow(arrgstr[i]);//重新发送失败的工单进件
							DB.DeleteQuerytsent(PostService.this,arrgstr[i],"t_sent");
						}
					}
					//成功的工单文件名
					String[] errgstr = DB.FileNameQuerytwo(PostService.this,arrgstr);
					if(errgstr!=null&&errgstr.length!=0)
					{
						for (int i = 0; i < errgstr.length; i++) 
						{
							//删除成功工单的文件夹和图片
							DeleteFile(errgstr[i]);
						}
					}
				}
				
			//正常进件和草稿箱
			cgxQuery("DCL", des.jiaMi("2"),"st_storeinfo");
			
			// &&NetUtil.checkNet(PostService.this)
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"MId","time"},"Id",fjnum.get(i),"st_storeinfo");				      
//						 String[] send=DBUtil.sendQuery(PostService.this,new String[]{MyDatabaseHelper.BEGINNUM},MyDatabaseHelper.SEND_BarcodeNo,barcodeno[0]);
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from st_storeinfo where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("MId")));
						}
						//发件ID
						id=fjnum.get(i);
						cursor.close();
						db.close();
					
						
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao.fun(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700006"+"_"+ZuBao.time;
						try{
							http.Uploadzip(PostService.this, "/sdcard/" +path
									+ "active.zip",path + "active.zip",
									Integer.parseInt(send[0]), isomux);
						}catch (Exception e) {
							// TODO: handle exception
							
//							return;
						}
						
						String[] sendnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
							
							http.Uploadzip(PostService.this, "/sdcard/" + path
									+ "active.txt", path + "active.txt",
									1, isomux);
							String[] sendtxtnum = DB.sendQuery(PostService.this,
									new String[] { MyDatabaseHelper.BEGINNUM,
											MyDatabaseHelper.TOTALNUM },
									MyDatabaseHelper.SEND_BarcodeNo, isomux);
							if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
								
								DB.upload(PostService.this,
										new String[] { "DCL" },
										new String[] { "1" }, fjnum.get(i));
								
								//广播
								Intent lrcIntent = new Intent();
								lrcIntent.setAction("techown.card.portWolong.FJ");
								sendBroadcast(lrcIntent); 
								
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.zip");	
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.txt");
								
								File fit=new File("/sdcard/"+"TJB/TuanDui/shang/"+id);
								File fit2 = new File("/sdcard/"+"TJB/TuanDui/shang/" +path);
		       					  if (fit.exists())
		       			    		{
		       						  deleteDir(fit);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+id+"/pic/"+"原文件夹");					     	
		       			    		}
		       					  if (fit2.exists())
		       					  {
		       						deleteDir(fit2);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
		       					  }	 	 
		       					  
		       					//删除数据库 
	        					  if(isDatabaseExists(PostService.this,"techown.db"))
		      						{
		        						  SQLiteDatabase db=null;
		        						  try
		        						  {
		        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
		  											"techown.db", 1);
		  									 db = myHelper.getWritableDatabase();
		  									db.delete("st_storeinfo",
		  											"DCL" + "=?",
		  											new String[] {des.jiaMi("1") });
		  									db.delete(MyDatabaseHelper.SEND,
		  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
		  											new String[] {barcodeno[0]});
		  									db.close();  
		        						  }
		        						  catch(Exception e)
		        						  {
		        							  if(db!=null)
		        							  {
		        								  db.close();   
		        							  }
		        						  }
		      						}
	        					//删除数据库 
		       					  
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队正常进件和草稿箱进件：上传进件异");
						techown.shanghu.https.Log.Instance().WriteLog("团队正常进件和草稿箱进件：上传进件出错信息:"+e.getMessage());
						e.printStackTrace();
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
					
				}
			}
			
			//
			cgxQuery("DCL", des.jiaMi("12"),"st_storeinfo");
			// &&NetUtil.checkNet(PostService.this)
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"MId","time"},"Id",fjnum.get(i),"st_storeinfo");				      
//						 String[] send=DBUtil.sendQuery(PostService.this,new String[]{MyDatabaseHelper.BEGINNUM},MyDatabaseHelper.SEND_BarcodeNo,barcodeno[0]);
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from st_storeinfo where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("MId")));
						}
						//发件ID
						id1=fjnum.get(i);
						cursor.close();
						db.close();
					
						
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao.fun11(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700006"+"_"+ZuBao.time;
						
						try{
							http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "active.zip",path + "active.zip",
								Integer.parseInt(send[0]), isomux);
						}catch (Exception e) {
							// TODO: handle exception
//							return;
						}
						
						String[] sendnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
							
						
							http.Uploadzip(PostService.this, "/sdcard/" + path
									+ "active.txt", path + "active.txt",
									1, isomux);
							String[] sendtxtnum = DB.sendQuery(PostService.this,
									new String[] { MyDatabaseHelper.BEGINNUM,
											MyDatabaseHelper.TOTALNUM },
									MyDatabaseHelper.SEND_BarcodeNo, isomux);
							if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
								
								DB.upload(PostService.this,
										new String[] { "DCL" },
										new String[] { "1" }, fjnum.get(i));
								
								//广播
								Intent lrcIntent = new Intent();
								lrcIntent.setAction("techown.card.portWolong.FJ");
								sendBroadcast(lrcIntent); 
								
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.zip");	
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.txt");
								
								File fit=new File("/sdcard/"+"TJB/TuanDui/shang/"+id1);	 
								File fit2=new File("/sdcard/"+"TJB/TuanDui/shang/"+path);
		       					  if (fit.exists())
		       			    		{
		       						  deleteDir(fit);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+id1+"/pic/"+"原文件夹");					     	
		       			    		}
		       					if (fit2.exists())
		       					  {
		       						deleteDir(fit2);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
		       					  }	 
		       					//删除数据库 
	        					  if(isDatabaseExists(PostService.this,"techown.db"))
		      						{
		        						  SQLiteDatabase db=null;
		        						  try
		        						  {
		        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
		  											"techown.db", 1);
		  									 db = myHelper.getWritableDatabase();
		  									db.delete("st_storeinfo",
		  											"DCL" + "=?",
		  											new String[] {des.jiaMi("1") });
		  									db.delete(MyDatabaseHelper.SEND,
		  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
		  											new String[] {barcodeno[0]});
		  									db.close();  
		        						  }
		        						  catch(Exception e)
		        						  {
		        							  if(db!=null)
		        							  {
		        								  db.close();   
		        							  }
		        						  }
		      						}
	        					//删除数据库 
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件异");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
						e.printStackTrace();
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
					
				}
			}
			
			
			//商户工单维护-----------------------------------------
			cgxQuery("DCL", des.jiaMi("71"),"shgongdanweihu");
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from shgongdanweihu where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("taskId2")));
						}
						//发件ID
						id71=fjnum.get(i);
						cursor.close();
						db.close();
						
						
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"taskId","TIME"},"Id",fjnum.get(i),"shgongdanweihu");
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						
						zuBao1.fun71(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
					try{	
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "newtask.zip", path + "newtask.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
					
						http.Uploadzip(PostService.this, "/sdcard/" + path
								+ "newtask.txt", path + "newtask.txt",
								1, isomux);
						String[] sendtxtnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
							DB.upload31(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							//广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent); 
							
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"newtask.zip");	
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"newtask.txt");
							
							
							File fit=new File("/sdcard/"+"TJB/TuanDui/shgongdan/"+id71);	 
							File fit2=new File("/sdcard/"+"TJB/TuanDui/shgongdan/"+path);
							File fit3=new File("/sdcard/"+"TJB/TuanDui/gesturesign/"+id71);
	       					 
							if (fit.exists())
	       			    		{
	       						  deleteDir(fit);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shgongdan/"+id71+"/pic/"+"原文件夹");					     	
	       			    		}
							
	       					if (fit2.exists())
	       					  {
	       						deleteDir(fit2);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shgongdan/"+path+"原文件夹");					     	
	       					  }	 
	       					
	       					if (fit3.exists())
       			    		{
       						  deleteDir(fit3);
       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/gesturesign/"+id71+"/"+"原文件夹");					     	
       			    		}
							//删除数据库 
	  					  if(isDatabaseExists(PostService.this,"techown.db"))
	    						{
	      						  SQLiteDatabase db=null;
	      						  try
	      						  {
	      							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
												"techown.db", Constant.tdshDBVer);
										 db = myHelper.getWritableDatabase();
										db.delete("shgongdanweihu",
												"DCL" + "=?",
												new String[] {des.jiaMi("1") });
										db.delete(MyDatabaseHelper.SEND,
												MyDatabaseHelper.SEND_BarcodeNo + "=?",
												new String[] {isomux});
										db.close();  
	      						  }
	      						  catch(Exception e)
	      						  {
	      							  if(db!=null)
	      							  {
	      								  db.close();   
	      							  }
	      						  }
	    						}
	  					  //删除数据库 
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}
			
			
			
			
			
			//拒件和草稿箱拒件
			cgxQuery("DCL", des.jiaMi("22"),"st_storeinfo");
			// &&NetUtil.checkNet(PostService.this)
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					isTXT=false;
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"MId","time"},"Id",fjnum.get(i),"st_storeinfo");				      
//						 String[] send=DBUtil.sendQuery(PostService.this,new String[]{MyDatabaseHelper.BEGINNUM},MyDatabaseHelper.SEND_BarcodeNo,barcodeno[0]);
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from st_storeinfo where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("MId")));
						}
						//发件ID
						id2=fjnum.get(i);
						cursor.close();
						db.close();
					
						
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao.funj(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700006"+"_"+ZuBao.time;
						try{
							http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "active.zip",path + "active.zip",
								Integer.parseInt(send[0]), isomux);
						}catch (Exception e) {
							// TODO: handle exception
							techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件异"+e.getMessage());
//							return;
						}
						
						String[] sendnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) 
						{	
							http.Uploadzip(PostService.this, "/sdcard/" + path
									+ "active.txt", path + "active.txt",
									1, isomux);	
						
						
							String[] sendtxtnum = DB.sendQuery(PostService.this,
									new String[] { MyDatabaseHelper.BEGINNUM,
											MyDatabaseHelper.TOTALNUM },
									MyDatabaseHelper.SEND_BarcodeNo, isomux);
							if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
								DB.upload(PostService.this,
										new String[] { "DCL" },
										new String[] { "1" }, fjnum.get(i));
								//广播
								Intent lrcIntent = new Intent();
								lrcIntent.setAction("techown.card.portWolong.FJ");
								sendBroadcast(lrcIntent); 
								
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.zip");	
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.txt");
								
								File fit=new File("/sdcard/"+"TJB/TuanDui/shang/"+id2);	
								File fit2=new File("/sdcard/"+"TJB/TuanDui/shang/"+path);
		       					  if (fit.exists())
		       			    		{
		       						  deleteDir(fit);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+id2+"/pic/"+"原文件夹");					     	
		       			    		}
		       					if (fit2.exists())
		       					  {
		       						deleteDir(fit2);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
		       					  }	 
		       					//删除数据库 
	        					  if(isDatabaseExists(PostService.this,"techown.db"))
		      						{
		        						  SQLiteDatabase db=null;
		        						  try
		        						  {
		        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
		  											"techown.db", 1);
		  									 db = myHelper.getWritableDatabase();
		  									db.delete("st_storeinfo",
		  											"DCL" + "=?",
		  											new String[] {des.jiaMi("1") });
		  									db.delete(MyDatabaseHelper.SEND,
		  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
		  											new String[] {barcodeno[0]});
		  									db.close();  
		        						  }
		        						  catch(Exception e)
		        						  {
		        							  if(db!=null)
		        							  {
		        								  db.close();   
		        							  }
		        						  }
		      						}
	        					//删除数据库 
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件异");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
						e.printStackTrace();
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
					
				}
			}
			
			cgxQuery("DCL", des.jiaMi("221"),"st_storeinfo");
			// &&NetUtil.checkNet(PostService.this)
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"MId","time"},"Id",fjnum.get(i),"st_storeinfo");				      
//						 String[] send=DBUtil.sendQuery(PostService.this,new String[]{MyDatabaseHelper.BEGINNUM},MyDatabaseHelper.SEND_BarcodeNo,barcodeno[0]);
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from st_storeinfo where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("MId")));
						}
						//发件ID
						id3=fjnum.get(i);
						cursor.close();
						db.close();
					
						
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao.funj1(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700006"+"_"+ZuBao.time;
						try{
							http.Uploadzip(PostService.this, "/sdcard/" +path
									+ "active.zip",path + "active.zip",
									Integer.parseInt(send[0]), isomux);
						}catch (Exception e) {
							// TODO: handle exception
//							return;
						}

						String[] sendnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
						
							http.Uploadzip(PostService.this, "/sdcard/" + path
									+ "active.txt", path + "active.txt",
									1, isomux);
							String[] sendtxtnum = DB.sendQuery(PostService.this,
									new String[] { MyDatabaseHelper.BEGINNUM,
											MyDatabaseHelper.TOTALNUM },
									MyDatabaseHelper.SEND_BarcodeNo, isomux);
							if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
								DB.upload(PostService.this,
										new String[] { "DCL" },
										new String[] { "1" }, fjnum.get(i));
								//广播
								Intent lrcIntent = new Intent();
								lrcIntent.setAction("techown.card.portWolong.FJ");
								sendBroadcast(lrcIntent); 
								
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.zip");	
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.txt");
								
								File fit=new File("/sdcard/"+"TJB/TuanDui/shang/"+id3);	 
								File fit2=new File("/sdcard/"+"TJB/TuanDui/shang/"+path);
		       					  if (fit.exists())
		       			    		{
		       						  deleteDir(fit);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+id3+"/pic/"+"原文件夹");					     	
		       			    		}
		       					if (fit2.exists())
		       					  {
		       						deleteDir(fit2);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
		       					  }	 
		       					//删除数据库 
	        					  if(isDatabaseExists(PostService.this,"techown.db"))
		      						{
		        						  SQLiteDatabase db=null;
		        						  try
		        						  {
		        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
		  											"techown.db", 1);
		  									 db = myHelper.getWritableDatabase();
		  									db.delete("st_storeinfo",
		  											"DCL" + "=?",
		  											new String[] {des.jiaMi("1") });
		  									db.delete(MyDatabaseHelper.SEND,
		  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
		  											new String[] {barcodeno[0]});
		  									db.close();  
		        						  }
		        						  catch(Exception e)
		        						  {
		        							  if(db!=null)
		        							  {
		        								  db.close();   
		        							  }
		        						  }
		      						}
	        					//删除数据库 
							}
						} 
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件异");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
						e.printStackTrace();
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
					
				}
			}
			cgxQuery("DCL", des.jiaMi("4"),"st_storeinfo");
			// &&NetUtil.checkNet(PostService.this)
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"meiId","time"},"Id",fjnum.get(i),"st_storeinfo");				      
//						 String[] send=DBUtil.sendQuery(PostService.this,new String[]{MyDatabaseHelper.BEGINNUM},MyDatabaseHelper.SEND_BarcodeNo,barcodeno[0]);
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from st_storeinfo where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("meiId")));
						}
						//发件ID
						id4=fjnum.get(i);
						cursor.close();
						db.close();
					
						
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao.fun2(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700006"+"_"+ZuBao.time;
						try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "active.zip",path + "active.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
							http.Uploadzip(PostService.this, "/sdcard/" + path
									+ "active.txt", path + "active.txt",
									1, isomux);
							String[] sendtxtnum = DB.sendQuery(PostService.this,
									new String[] { MyDatabaseHelper.BEGINNUM,
											MyDatabaseHelper.TOTALNUM },
									MyDatabaseHelper.SEND_BarcodeNo, isomux);
							if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
								DB.upload(PostService.this,
										new String[] { "DCL" },
										new String[] { "1" }, fjnum.get(i));
								//广播
								Intent lrcIntent = new Intent();
								lrcIntent.setAction("techown.card.portWolong.FJ");
								sendBroadcast(lrcIntent); 
								
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.zip");	
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.txt");
								
								File fit=new File("/sdcard/"+"TJB/TuanDui/shang/"+id4);	  
								File fit2=new File("/sdcard/"+"TJB/TuanDui/shang/"+path);
		       					  if (fit.exists())
		       			    		{
		       						  deleteDir(fit);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+id4+"/pic/"+"原文件夹");					     	
		       			    		}
		       					if (fit2.exists())
		       					  {
		       						deleteDir(fit2);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
		       					  }	 
		       					//删除数据库 
	        					  if(isDatabaseExists(PostService.this,"techown.db"))
		      						{
		        						  SQLiteDatabase db=null;
		        						  try
		        						  {
		        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
		  											"techown.db", 1);
		  									 db = myHelper.getWritableDatabase();
		  									db.delete("st_storeinfo",
		  											"DCL" + "=?",
		  											new String[] {des.jiaMi("1") });
		  									db.delete(MyDatabaseHelper.SEND,
		  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
		  											new String[] {barcodeno[0]});
		  									db.close();  
		        						  }
		        						  catch(Exception e)
		        						  {
		        							  if(db!=null)
		        							  {
		        								  db.close();   
		        							  }
		        						  }
		      						}
	        					//删除数据库 
							}
						} 
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}
			
			//营销活动录入
			cgxQuery("DCL", des.jiaMi("3"),"st_storeinfo");
			// &&NetUtil.checkNet(PostService.this)
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"MId","time"},"Id",fjnum.get(i),"st_storeinfo");				      
//						 String[] send=DBUtil.sendQuery(PostService.this,new String[]{MyDatabaseHelper.BEGINNUM},MyDatabaseHelper.SEND_BarcodeNo,barcodeno[0]);
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from st_storeinfo where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("MId")));
						}
						//发件ID
						id5=fjnum.get(i);
						cursor.close();
						db.close();
					
						
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao.fun1(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700006"+"_"+ZuBao.time;
						try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "active.zip",path + "active.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
						
							http.Uploadzip(PostService.this, "/sdcard/" + path
									+ "active.txt", path + "active.txt",
									1, isomux);
							String[] sendtxtnum = DB.sendQuery(PostService.this,
									new String[] { MyDatabaseHelper.BEGINNUM,
											MyDatabaseHelper.TOTALNUM },
									MyDatabaseHelper.SEND_BarcodeNo, isomux);
							if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
								DB.upload(PostService.this,
										new String[] { "DCL" },
										new String[] { "1" }, fjnum.get(i));
								//广播
								Intent lrcIntent = new Intent();
								lrcIntent.setAction("techown.card.portWolong.FJ");
								sendBroadcast(lrcIntent); 
								
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.zip");	
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"active.txt");
								
								//删除数据库 
	      					  if(isDatabaseExists(PostService.this,"techown.db"))
		      						{
		        						  SQLiteDatabase db=null;
		        						  try
		        						  {
		        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
		  											"techown.db", 1);
		  									 db = myHelper.getWritableDatabase();
		  									db.delete("st_storeinfo",
		  											"DCL" + "=?",
		  											new String[] {des.jiaMi("1") });
		  									db.delete(MyDatabaseHelper.SEND,
		  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
		  											new String[] {barcodeno[0]});
		  									db.close();  
		        						  }
		        						  catch(Exception e)
		        						  {
		        							  if(db!=null)
		        							  {
		        								  db.close();   
		        							  }
		        						  }
		      						}
	      					//删除数据库 	
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}
			
			
			//特惠上线
 			cgxQuery1("DCL",des.jiaMi("2"));
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from youhushangxian where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("merId2")));
						}
						//发件ID
						id6=fjnum.get(i);
						cursor.close();
						db.close();
						
						
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"merId","time"},"Id",fjnum.get(i),"youhushangxian");
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao1.fun(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
						try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "online.zip", path + "online.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
//					http.Uploadzip(PostService.this, "/sdcard/" + path
//							+ "online.txt", path + "online.txt",
//							1, isomux);
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
					
							http.Uploadzip(PostService.this, "/sdcard/" + path
									+ "online.txt", path + "online.txt",
									1, isomux);
							String[] sendtxtnum = DB.sendQuery(PostService.this,
									new String[] { MyDatabaseHelper.BEGINNUM,
											MyDatabaseHelper.TOTALNUM },
									MyDatabaseHelper.SEND_BarcodeNo, isomux);
							if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
								DB.upload4(PostService.this,
										new String[] { "DCL" },
										new String[] { "1" }, fjnum.get(i));
								//广播
								Intent lrcIntent = new Intent();
								lrcIntent.setAction("techown.card.portWolong.FJ");
								sendBroadcast(lrcIntent); 
								
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"online.zip");	
								delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"online.txt");
								
								File fit=new File("/sdcard/"+"TJB/TuanDui/youhuishang/"+id6);
								File fit2=new File("/sdcard/"+"TJB/TuanDui/youhuishang/"+path);
		       					  if (fit.exists())
		       			    		{
		       						  deleteDir(fit);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/youhuishang/"+id6+"/pic/"+"原文件夹");					     	
		       			    		}
		       					if (fit2.exists())
		       					  {
		       						deleteDir(fit2);
		       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
		       					  }	 
		       					//删除数据库 
	        					  if(isDatabaseExists(PostService.this,"techown.db"))
		      						{
		        						  SQLiteDatabase db=null;
		        						  try
		        						  {
		        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
		  											"techown.db", 1);
		  									 db = myHelper.getWritableDatabase();
		  									db.delete("youhushangxian",
		  											"DCL" + "=?",
		  											new String[] {des.jiaMi("1") });
		  									db.delete(MyDatabaseHelper.SEND,
		  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
		  											new String[] {barcodeno[0]});
		  									db.close();  
		        						  }
		        						  catch(Exception e)
		        						  {
		        							  if(db!=null)
		        							  {
		        								  db.close();   
		        							  }
		        						  }
		      						}
	        					//删除数据库 
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}
			
			Log.d("debug main Service", ".............");
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
			
			//草稿箱特惠上线
			cgxQuery1("DCL",des.jiaMi("212"));
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from youhushangxian where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("merId2")));
						}
						//发件ID
						id7=fjnum.get(i);
						cursor.close();
						db.close();
						
						
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"merId","time"},"Id",fjnum.get(i),"youhushangxian");
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao1.fun12(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
					try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "online.zip", path + "online.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
					
						http.Uploadzip(PostService.this, "/sdcard/" + path
								+ "online.txt", path + "online.txt",
								1, isomux);
						String[] sendtxtnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
							DB.upload4(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							//广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent); 
							
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"online.zip");	
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"online.txt");
							
							File fit=new File("/sdcard/"+"TJB/TuanDui/youhuishang/"+id7);
							File fit2=new File("/sdcard/"+"TJB/TuanDui/youhuishang/"+path);
	       					  if (fit.exists())
	       			    		{
	       						  deleteDir(fit);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/youhuishang/"+id7+"/pic/"+"原文件夹");					     	
	       			    		}
	       					if (fit2.exists())
	       					  {
	       						deleteDir(fit2);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
	       					  }	 
	       					//删除数据库 
        					  if(isDatabaseExists(PostService.this,"techown.db"))
	      						{
	        						  SQLiteDatabase db=null;
	        						  try
	        						  {
	        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
	  											"techown.db", 1);
	  									 db = myHelper.getWritableDatabase();
	  									db.delete("youhushangxian",
	  											"DCL" + "=?",
	  											new String[] {des.jiaMi("1") });
	  									db.delete(MyDatabaseHelper.SEND,
	  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
	  											new String[] {barcodeno[0]});
	  									db.close();  
	        						  }
	        						  catch(Exception e)
	        						  {
	        							  if(db!=null)
	        							  {
	        								  db.close();   
	        							  }
	        						  }
	      						}
        					//删除数据库 
							}
						} 
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}
			
			Log.d("debug main Service", ".............");
			try {
//				Thread.sleep(5000);
			} catch (Exception e) {
			}
			
			
			//最红上线
			cgxQuery1("DCL",des.jiaMi("3"));
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from youhushangxian where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("merId2")));
						}
						//发件ID
						id8=fjnum.get(i);
						cursor.close();
						db.close();
						
						
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"merId","time"},"Id",fjnum.get(i),"youhushangxian");
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao1.fun9(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
					try{	
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "online.zip", path + "online.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
					
						http.Uploadzip(PostService.this, "/sdcard/" + path
								+ "online.txt", path + "online.txt",
								1, isomux);
						String[] sendtxtnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
							DB.upload4(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							//广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent); 
							
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"online.zip");	
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"online.txt");
							
							File fit=new File("/sdcard/"+"TJB/TuanDui/youhuishang/"+id8);	
							File fit2=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+path);
	       					  if (fit.exists())
	       			    		{
	       						  deleteDir(fit);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/youhuishang/"+id8+"/pic/"+"原文件夹");					     	
	       			    		}
	       					if (fit2.exists())
	       					  {
	       						deleteDir(fit2);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
	       					  }	 
	       					//删除数据库 
        					  if(isDatabaseExists(PostService.this,"techown.db"))
	      						{
	        						  SQLiteDatabase db=null;
	        						  try
	        						  {
	        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
	  											"techown.db", 1);
	  									 db = myHelper.getWritableDatabase();
	  									db.delete("youhushangxian",
	  											"DCL" + "=?",
	  											new String[] {des.jiaMi("1") });
	  									db.delete(MyDatabaseHelper.SEND,
	  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
	  											new String[] {barcodeno[0]});
	  									db.close();  
	        						  }
	        						  catch(Exception e)
	        						  {
	        							  if(db!=null)
	        							  {
	        								  db.close();   
	        							  }
	        						  }
	      						}
        					//删除数据库 
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}
			
			Log.d("debug main Service", ".............");
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
			
			
			//草稿箱最红上线
			cgxQuery1("DCL",des.jiaMi("313"));
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from youhushangxian where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("merId2")));
						}
						//发件ID
						id9=fjnum.get(i);
						cursor.close();
						db.close();
						
						
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"merId","time"},"Id",fjnum.get(i),"youhushangxian");
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao1.fun91(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
					try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "online.zip", path + "online.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
					
						http.Uploadzip(PostService.this, "/sdcard/" + path
								+ "online.txt", path + "online.txt",
								1, isomux);
						String[] sendtxtnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
							DB.upload4(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							//广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent); 
							
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"online.zip");	
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"online.txt");
							
							File fit=new File("/sdcard/"+"TJB/TuanDui/youhuishang/"+id9);	
							File fit2=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+path);
	       					  if (fit.exists())
	       			    		{
	       						  deleteDir(fit);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/youhuishang/"+id9+"/pic/"+"原文件夹");					     	
	       			    		}
	       					if (fit2.exists())
	       					  {
	       						deleteDir(fit2);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
	       					  }	 
	       					//删除数据库 
        					  if(isDatabaseExists(PostService.this,"techown.db"))
	      						{
	        						  SQLiteDatabase db=null;
	        						  try
	        						  {
	        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
	  											"techown.db", 1);
	  									 db = myHelper.getWritableDatabase();
	  									db.delete("youhushangxian",
	  											"DCL" + "=?",
	  											new String[] {des.jiaMi("1") });
	  									db.delete(MyDatabaseHelper.SEND,
	  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
	  											new String[] {barcodeno[0]});
	  									db.close();  
	        						  }
	        						  catch(Exception e)
	        						  {
	        							  if(db!=null)
	        							  {
	        								  db.close();   
	        							  }
	        						  }
	      						}
        					//删除数据库 
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}
			System.out.println(Constant.username);
			System.out.println(Constant.imei);
			Log.d("debug main Service", ".............");
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
			
			//续约
			cgxQuery("DCL",des.jiaMi("21"),"gongdanweihu");
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from gongdanweihu where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("taskId2")));
						}
						//发件ID
						id10=fjnum.get(i);
						cursor.close();
						db.close();
						
						
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"taskId","time"},"Id",fjnum.get(i),"gongdanweihu");
						System.out.println("续约工单：barcodeno[0]=="+barcodeno[0]+",barcodeno[1]=="+barcodeno[1]);
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao1.fun5(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
					try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "task.zip", path + "task.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
					
						http.Uploadzip(PostService.this, "/sdcard/" + path
								+ "task.txt", path + "task.txt",
								1, isomux);
						String[] sendtxtnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
							DB.upload3(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							//广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent); 
							
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.zip");	
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.txt");
							
							File fit=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+id10);	 
//							File fit2=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+path);
	       					if (fit.exists())
	       			    	{
	       						deleteDir(fit);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/gongdan/"+id10+"/pic/"+"原文件夹");					     	
	       			    	}
//	       					if (fit2.exists())
//	       					  {
//	       						deleteDir(fit2);
//	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
//	       					  }	 
	       					  
	       					//压缩包的文件名称
								String filepath = "t_" + Constant.username + "_"
								+ barcodeno[0] + "_" + barcodeno[1] + ".task";
								// 查询在gongdanweihu表中该工单的数据保存到QueryInsert数组
								String[] QueryInsert = DB.cardQuery(
										PostService.this, new String[] {"Dnum",
												"DCL", "LX", "TIME", "Stname",
												"taskId2", "taskId", "ActiveType",
												"Name", "TelNumber", "Neirong",
												"Xiaofeidate", "Kahao", "Xiaofxs",
												"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
												"AttLicense", "AttContract","AttAccredit", "AttContract1",
												"AttContract2","AttContract3","AttContract4","AttContract5",
												"AttContract6","AttContract7","AttContract8","AttContract9",
												"AttOthers"},
										"Id", fjnum.get(i), "gongdanweihu");
								
								//存入该笔工单的工单的文件名
								DB.senduploadinsert(PostService.this, "FileName",
										filepath, "gongdanweihusent");
								
								//将该笔发送的工单信息存入到备份数据库中
								DB.uploadinsert(
										PostService.this,
										new String[] {"Dnum",
												"DCL", "LX", "TIME", "Stname",
												"taskId2", "taskId", "ActiveType",
												"Name", "TelNumber", "Neirong",
												"Xiaofeidate", "Kahao", "Xiaofxs",
												"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
												"AttLicense", "AttContract","AttAccredit", "AttContract1",
												"AttContract2","AttContract3","AttContract4","AttContract5",
												"AttContract6","AttContract7","AttContract8","AttContract9",
												"AttOthers"},
										QueryInsert,filepath);
								DB.uploadinsert(
										PostService.this,new String[] {"Id"},
										new String[]{fjnum.get(i)},filepath);
								
								
								// 存入登陆表中t sentupload表中标志位 1 成功 0 失败
								String[] strname = new String[]{"t_sent","flag"};
								String[] strvalue = new String[]{filepath,"0"};
								DB.senduploadinsertfilename(PostService.this, strname,
										strvalue,"t_sentUpload");
	       					  
	       					//删除数据库 
        					  if(isDatabaseExists(PostService.this,"techown.db"))
	      						{
	        						  SQLiteDatabase db=null;
	        						  try
	        						  {
	        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
	  											"techown.db", 1);
	  									 db = myHelper.getWritableDatabase();
	  									db.delete("gongdanweihu",
	  											"DCL" + "=?",
	  											new String[] {des.jiaMi("1") });
	  									db.delete(MyDatabaseHelper.SEND,
	  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
	  											new String[] {barcodeno[0]});
	  									db.close();  
	        						  }
	        						  catch(Exception e)
	        						  {
	        							  if(db!=null)
	        							  {
	        								  db.close();   
	        							  }
	        						  }
	      						}
        					//删除数据库 
							}
						} 
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}
			
			Log.d("debug main Service", ".............");
			try {
//				Thread.sleep(5000);
			} catch (Exception e) {
			}
			
			//草稿箱续约工单
			cgxQuery("DCL",des.jiaMi("213"),"gongdanweihu");
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from gongdanweihu where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("taskId2")));
						}
						//发件ID
						id11=fjnum.get(i);
						cursor.close();
						db.close();
						
						
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"taskId","time"},"Id",fjnum.get(i),"gongdanweihu");
						System.out.println("草稿箱续约工单：barcodeno[0]=="+barcodeno[0]+",barcodeno[1]=="+barcodeno[1]);
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao1.fun51(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
					try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "task.zip", path + "task.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
					
						http.Uploadzip(PostService.this, "/sdcard/" + path
								+ "task.txt", path + "task.txt",
								1, isomux);
						String[] sendtxtnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
							DB.upload3(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							//广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent); 
							
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.zip");	
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.txt");
							
							File fit=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+id11);	 
//							File fit2=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+path);
	       					  if (fit.exists())
	       			    		{
	       						  deleteDir(fit);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/gongdan/"+id11+"/pic/"+"原文件夹");					     	
	       			    		}	 
//	       					if (fit2.exists())
//	       					  {
//	       						deleteDir(fit2);
//	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
//	       					  }	 
	       					  
	       					  //压缩包的文件名称
								String filepath = "t_" + Constant.username + "_"
								+ barcodeno[0] + "_" + barcodeno[1] + ".task";
								// 查询在gongdanweihu表中该工单的数据保存到QueryInsert数组
								String[] QueryInsert = DB.cardQuery(
										PostService.this, new String[] {"Dnum",
												"DCL", "LX", "TIME", "Stname",
												"taskId2", "taskId", "ActiveType",
												"Name", "TelNumber", "Neirong",
												"Xiaofeidate", "Kahao", "Xiaofxs",
												"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
												"AttLicense", "AttContract","AttAccredit", "AttContract1",
												"AttContract2","AttContract3","AttContract4","AttContract5",
												"AttContract6","AttContract7","AttContract8","AttContract9",
												"AttOthers"},
										"Id", fjnum.get(i), "gongdanweihu");
								
								//存入该笔工单的工单的文件名
								DB.senduploadinsert(PostService.this, "FileName",
										filepath, "gongdanweihusent");
								
								//将该笔发送的工单信息存入到备份数据库中
								DB.uploadinsert(
										PostService.this,
										new String[] {"Dnum",
												"DCL", "LX", "TIME", "Stname",
												"taskId2", "taskId", "ActiveType",
												"Name", "TelNumber", "Neirong",
												"Xiaofeidate", "Kahao", "Xiaofxs",
												"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
												"AttLicense", "AttContract","AttAccredit", "AttContract1",
												"AttContract2","AttContract3","AttContract4","AttContract5",
												"AttContract6","AttContract7","AttContract8","AttContract9",
												"AttOthers"},
										QueryInsert,filepath);
								DB.uploadinsert(
										PostService.this,new String[] {"Id"},
										new String[]{fjnum.get(i)},filepath);
								
								
								// 存入登陆表中t sentupload表中标志位 1 成功 0 失败
								String[] strname = new String[]{"t_sent","flag"};
								String[] strvalue = new String[]{filepath,"0"};
								DB.senduploadinsertfilename(PostService.this, strname,
										strvalue,"t_sentUpload");
	       					  
	       					//删除数据库 
        					  if(isDatabaseExists(PostService.this,"techown.db"))
	      						{
	        						  SQLiteDatabase db=null;
	        						  try
	        						  {
	        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
	  											"techown.db", 1);
	  									 db = myHelper.getWritableDatabase();
	  									db.delete("gongdanweihu",
	  											"DCL" + "=?",
	  											new String[] {des.jiaMi("1") });
	  									db.delete(MyDatabaseHelper.SEND,
	  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
	  											new String[] {barcodeno[0]});
	  									db.close();  
	        						  }
	        						  catch(Exception e)
	        						  {
	        							  if(db!=null)
	        							  {
	        								  db.close();   
	        							  }
	        						  }
	      						}
        					//删除数据库 
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			}
			
			Log.d("debug main Service", ".............");
			try {
//				Thread.sleep(5000);
			} catch (Exception e) {
			}
			
			//上门工单
 			cgxQuery("DCL",des.jiaMi("2"),"gongdanweihu");
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from gongdanweihu where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("taskId2")));
						}
						//发件ID
						id12=fjnum.get(i);
						cursor.close();
						db.close();
						
						
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"taskId","time"},"Id",fjnum.get(i),"gongdanweihu");
						System.out.println("上门工单：barcodeno[0]=="+barcodeno[0]+",barcodeno[1]=="+barcodeno[1]);
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao1.fun1(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
					try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "task.zip", path + "task.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
					
						http.Uploadzip(PostService.this, "/sdcard/" + path
								+ "task.txt", path + "task.txt",
								1, isomux);
						String[] sendtxtnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
							DB.upload3(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							//广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent); 
							
							
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.zip");	
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.txt");
							
							File fit=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+id12);	 
//							File fit2=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+path);
	       					  if (fit.exists())
	       			    		{
	       						  deleteDir(fit);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/gongdan/"+id12+"/pic/"+"原文件夹");					     	
	       			    		}	
//	       					if (fit2.exists())
//	       					  {
//	       						deleteDir(fit2);
//	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
//	       					  }	 
	       					  
	       					//压缩包的文件名称
								String filepath = "t_" + Constant.username + "_"
								+ barcodeno[0] + "_" + barcodeno[1] + ".task";
								// 查询在gongdanweihu表中该工单的数据保存到QueryInsert数组
								String[] QueryInsert = DB.cardQuery(
										PostService.this, new String[] {"Dnum",
												"DCL", "LX", "TIME", "Stname",
												"taskId2", "taskId", "ActiveType",
												"Name", "TelNumber", "Neirong",
												"Xiaofeidate", "Kahao", "Xiaofxs",
												"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
												"AttLicense", "AttContract","AttAccredit", "AttContract1",
												"AttContract2","AttContract3","AttContract4","AttContract5",
												"AttContract6","AttContract7","AttContract8","AttContract9",
												"AttOthers"},
										"Id", fjnum.get(i), "gongdanweihu");
								
								//存入该笔工单的工单的文件名
								DB.senduploadinsert(PostService.this, "FileName",
										filepath, "gongdanweihusent");
								
								//将该笔发送的工单信息存入到备份数据库中
								DB.uploadinsert(
										PostService.this,
										new String[] {"Dnum",
												"DCL", "LX", "TIME", "Stname",
												"taskId2", "taskId", "ActiveType",
												"Name", "TelNumber", "Neirong",
												"Xiaofeidate", "Kahao", "Xiaofxs",
												"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
												"AttLicense", "AttContract","AttAccredit", "AttContract1",
												"AttContract2","AttContract3","AttContract4","AttContract5",
												"AttContract6","AttContract7","AttContract8","AttContract9",
												"AttOthers"},
										QueryInsert,filepath);
								DB.uploadinsert(
										PostService.this,new String[] {"Id"},
										new String[]{fjnum.get(i)},filepath);
								
								
								// 存入登陆表中t sentupload表中标志位 1 成功 0 失败
								String[] strname = new String[]{"t_sent","flag"};
								String[] strvalue = new String[]{filepath,"0"};
								DB.senduploadinsertfilename(PostService.this, strname,
										strvalue,"t_sentUpload");
	       					  
	       					  
	       					//删除数据库 
        					  if(isDatabaseExists(PostService.this,"techown.db"))
	      						{
	        						  SQLiteDatabase db=null;
	        						  try
	        						  {
	        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
	  											"techown.db", 1);
	  									 db = myHelper.getWritableDatabase();
	  									db.delete("gongdanweihu",
	  											"DCL" + "=?",
	  											new String[] {des.jiaMi("1") });
	  									db.delete(MyDatabaseHelper.SEND,
	  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
	  											new String[] {barcodeno[0]});
	  									db.close();  
	        						  }
	        						  catch(Exception e)
	        						  {
	        							  if(db!=null)
	        							  {
	        								  db.close();   
	        							  }
	        						  }
	      						}
        					//删除数据库 
							}
						}
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			
			
			Log.d("debug main Service", ".............");
			try {
//				Thread.sleep(5000);
			} catch (Exception e) {
			}
			
			

		}
			
			
			//草稿箱上门工单
			cgxQuery("DCL",des.jiaMi("234"),"gongdanweihu");
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					try {
						// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
						
						myHelper = new MyDatabaseHelper(PostService.this,
								"techown.db", Constant.tdshDBVer);
						db = myHelper.getWritableDatabase();
						cursor = db.rawQuery(
								"select * from gongdanweihu where id == ?",
								new String[] { fjnum.get(i) });

						while (cursor.moveToNext()) {

							isomux = des.jieMI(cursor.getString(cursor
									.getColumnIndex("taskId2")));
						}
						//发件ID
						id13=fjnum.get(i);
						cursor.close();
						db.close();
						
						
						String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"taskId","time"},"Id",fjnum.get(i),"gongdanweihu");
						System.out.println("草稿箱上门工单：barcodeno[0]=="+barcodeno[0]+",barcodeno[1]=="+barcodeno[1]);
						String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
						String[] send = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						zuBao1.fun15(fjnum.get(i),path);
						
//						String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
					try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "task.zip", path + "task.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
//						return;
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
					
						http.Uploadzip(PostService.this, "/sdcard/" + path
								+ "task.txt", path + "task.txt",
								1, isomux);
						String[] sendtxtnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
							DB.upload3(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							//广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent); 
							
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.zip");	
							delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.txt");
							
							File fit=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+id13);	
//							File fit2=new File("/sdcard/"+"TJB/TuanDui/gongdan/"+path);	
	       					  if (fit.exists())
	       			    		{
	       						  deleteDir(fit);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/gongdan/"+id13+"/pic/"+"原文件夹");					     	
	       			    		}
//	       					if (fit2.exists())
//	       					  {
//	       						deleteDir(fit2);
//	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");					     	
//	       					  }	 
	       					  
	       					//压缩包的文件名称
								String filepath = "t_" + Constant.username + "_"
								+ barcodeno[0] + "_" + barcodeno[1] + ".task";
								// 查询在gongdanweihu表中该工单的数据保存到QueryInsert数组
								String[] QueryInsert = DB.cardQuery(
										PostService.this, new String[] {"Dnum",
												"DCL", "LX", "TIME", "Stname",
												"taskId2", "taskId", "ActiveType",
												"Name", "TelNumber", "Neirong",
												"Xiaofeidate", "Kahao", "Xiaofxs",
												"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
												"AttLicense", "AttContract","AttAccredit", "AttContract1",
												"AttContract2","AttContract3","AttContract4","AttContract5",
												"AttContract6","AttContract7","AttContract8","AttContract9",
												"AttOthers"},
										"Id", fjnum.get(i), "gongdanweihu");
								
								//存入该笔工单的工单的文件名
								DB.senduploadinsert(PostService.this, "FileName",
										filepath, "gongdanweihusent");
								
								//将该笔发送的工单信息存入到备份数据库中
								DB.uploadinsert(
										PostService.this,
										new String[] {"Dnum",
												"DCL", "LX", "TIME", "Stname",
												"taskId2", "taskId", "ActiveType",
												"Name", "TelNumber", "Neirong",
												"Xiaofeidate", "Kahao", "Xiaofxs",
												"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
												"AttLicense", "AttContract","AttAccredit", "AttContract1",
												"AttContract2","AttContract3","AttContract4","AttContract5",
												"AttContract6","AttContract7","AttContract8","AttContract9",
												"AttOthers"},
										QueryInsert,filepath);
								DB.uploadinsert(
										PostService.this,new String[] {"Id"},
										new String[]{fjnum.get(i)},filepath);
								
								
								// 存入登陆表中t sentupload表中标志位 1 成功 0 失败
								String[] strname = new String[]{"t_sent","flag"};
								String[] strvalue = new String[]{filepath,"0"};
								DB.senduploadinsertfilename(PostService.this, strname,
										strvalue,"t_sentUpload");
	       					  
	       					//删除数据库 
        					  if(isDatabaseExists(PostService.this,"techown.db"))
	      						{
	        						  SQLiteDatabase db=null;
	        						  try
	        						  {
	        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
	  											"techown.db", 1);
	  									 db = myHelper.getWritableDatabase();
	  									db.delete("gongdanweihu",
	  											"DCL" + "=?",
	  											new String[] {des.jiaMi("1") });
	  									db.delete(MyDatabaseHelper.SEND,
	  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
	  											new String[] {barcodeno[0]});
	  									db.close();  
	        						  }
	        						  catch(Exception e)
	        						  {
	        							  if(db!=null)
	        							  {
	        								  db.close();   
	        							  }
	        						  }
	      						}
        					//删除数据库 
							}
						} 
					} catch (Exception e) {
						cursor.close();
						db.close();
						Log.d("debug main Service", "发送失败！" + e.getMessage());
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
						techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					}
//					fjnum.remove(0);
//					finally{
//						if(cursor!=null)
//							cursor.close();
//						if(db!=null)
//							db.close();
//					}
				}

				try {
//					Thread.sleep(3000);
				} catch (Exception e) {
				}
			
			
			Log.d("debug main Service", ".............");
			try {
//				Thread.sleep(5000);
			} catch (Exception e) {
			}
			
			

		}
			
		//电话工单	
		cgxQuery("DCL",des.jiaMi("3"),"gongdanweihu");
		if (fjnum.size() > 0) {
			for (int i = 0; i < fjnum.size(); i++) {
				try {
					// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
					
					myHelper = new MyDatabaseHelper(PostService.this,
							"techown.db", Constant.tdshDBVer);
					db = myHelper.getWritableDatabase();
					cursor = db.rawQuery(
							"select * from gongdanweihu where id == ?",
							new String[] { fjnum.get(i) });

					while (cursor.moveToNext()) {

						isomux = des.jieMI(cursor.getString(cursor
								.getColumnIndex("taskId2")));
					}
					//发件ID
					id14=fjnum.get(i);
					cursor.close();
					db.close();
					
					
					String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"taskId","time"},"Id",fjnum.get(i),"gongdanweihu");
					System.out.println("电话工单：barcodeno[0]=="+barcodeno[0]+",barcodeno[1]=="+barcodeno[1]);
					String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
					String[] send = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					zuBao1.fun2(fjnum.get(i),path);
					
//					String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
				try{
					http.Uploadzip(PostService.this, "/sdcard/" +path
							+ "task.zip", path + "task.zip",
							Integer.parseInt(send[0]), isomux);
				}catch (Exception e) {
					// TODO: handle exception
//					return;
				}
				
				String[] sendnum = DB.sendQuery(PostService.this,
						new String[] { MyDatabaseHelper.BEGINNUM,
								MyDatabaseHelper.TOTALNUM },
						MyDatabaseHelper.SEND_BarcodeNo, isomux);
				if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
				
					http.Uploadzip(PostService.this, "/sdcard/" + path
							+ "task.txt", path + "task.txt",
							1, isomux);
					String[] sendtxtnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
						DB.upload3(PostService.this,
								new String[] { "DCL" },
								new String[] { "1" }, fjnum.get(i));
						//广播
						Intent lrcIntent = new Intent();
						lrcIntent.setAction("techown.card.portWolong.FJ");
						sendBroadcast(lrcIntent); 
						
						delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.zip");	
						delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.txt");
						
						//压缩包的文件名称
						String filepath = "t_" + Constant.username + "_"
						+ barcodeno[0] + "_" + barcodeno[1] + ".task";
						// 查询在gongdanweihu表中该工单的数据保存到QueryInsert数组
						String[] QueryInsert = DB.cardQuery(
								PostService.this, new String[] {"Dnum",
										"DCL", "LX", "TIME", "Stname",
										"taskId2", "taskId", "ActiveType",
										"Name", "TelNumber", "Neirong",
										"Xiaofeidate", "Kahao", "Xiaofxs",
										"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
										"AttLicense", "AttContract","AttAccredit", "AttContract1",
										"AttContract2","AttContract3","AttContract4","AttContract5",
										"AttContract6","AttContract7","AttContract8","AttContract9",
										"AttOthers"},
								"Id", fjnum.get(i), "gongdanweihu");
						
						//存入该笔工单的工单的文件名
						DB.senduploadinsert(PostService.this, "FileName",
								filepath, "gongdanweihusent");
						
						//将该笔发送的工单信息存入到备份数据库中
						DB.uploadinsert(
								PostService.this,
								new String[] {"Dnum",
										"DCL", "LX", "TIME", "Stname",
										"taskId2", "taskId", "ActiveType",
										"Name", "TelNumber", "Neirong",
										"Xiaofeidate", "Kahao", "Xiaofxs",
										"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
										"AttLicense", "AttContract","AttAccredit", "AttContract1",
										"AttContract2","AttContract3","AttContract4","AttContract5",
										"AttContract6","AttContract7","AttContract8","AttContract9",
										"AttOthers"},
								QueryInsert,filepath);
						DB.uploadinsert(
								PostService.this,new String[] {"Id"},
								new String[]{fjnum.get(i)},filepath);
						
						
						// 存入登陆表中t sentupload表中标志位 1 成功 0 失败
						String[] strname = new String[]{"t_sent","flag"};
						String[] strvalue = new String[]{filepath,"0"};
						DB.senduploadinsertfilename(PostService.this, strname,
								strvalue,"t_sentUpload");
						
						//删除数据库 
  					  if(isDatabaseExists(PostService.this,"techown.db"))
    						{
      						  SQLiteDatabase db=null;
      						  try
      						  {
      							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
											"techown.db", 1);
									 db = myHelper.getWritableDatabase();
									db.delete("gongdanweihu",
											"DCL" + "=?",
											new String[] {des.jiaMi("1") });
									db.delete(MyDatabaseHelper.SEND,
											MyDatabaseHelper.SEND_BarcodeNo + "=?",
											new String[] {barcodeno[0]});
									db.close();  
      						  }
      						  catch(Exception e)
      						  {
      							  if(db!=null)
      							  {
      								  db.close();   
      							  }
      						  }
    						}
  					//删除数据库 
						}
					}
				} catch (Exception e) {
					cursor.close();
					db.close();
					Log.d("debug main Service", "发送失败！" + e.getMessage());
					techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
					techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
				}
//				fjnum.remove(0);
//				finally{
//					if(cursor!=null)
//						cursor.close();
//					if(db!=null)
//						db.close();
//				}
			}

			try {
//				Thread.sleep(3000);
			} catch (Exception e) {
			}
		}
		
		Log.d("debug main Service", ".............");
		try {
//			Thread.sleep(5000);
		} catch (Exception e) {
		}
		
	
		cgxQuery("DCL",des.jiaMi("31"),"gongdanweihu");
		if (fjnum.size() > 0) {
			for (int i = 0; i < fjnum.size(); i++) {
				try {
					// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
					
					myHelper = new MyDatabaseHelper(PostService.this,
							"techown.db", Constant.tdshDBVer);
					db = myHelper.getWritableDatabase();
					cursor = db.rawQuery(
							"select * from gongdanweihu where id == ?",
							new String[] { fjnum.get(i) });

					while (cursor.moveToNext()) {

						isomux = des.jieMI(cursor.getString(cursor
								.getColumnIndex("taskId2")));
					}
					//发件ID
					id15=fjnum.get(i);
					cursor.close();
					db.close();
					
					
					String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"taskId","time"},"Id",fjnum.get(i),"gongdanweihu");
					String path="t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+".";
					String[] send = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					zuBao1.fun3(fjnum.get(i),path);
					
//					String path1 = "t_"+"A024146"+"_"+"3634b0c036f853480136f884f4700004"+"_"+ZuBao.time;
				try{	
					http.Uploadzip(PostService.this, "/sdcard/" +path
							+ "task.zip", path + "task.zip",
							Integer.parseInt(send[0]), isomux);
				}catch (Exception e) {
					// TODO: handle exception
//					return;
				}
				
				String[] sendnum = DB.sendQuery(PostService.this,
						new String[] { MyDatabaseHelper.BEGINNUM,
								MyDatabaseHelper.TOTALNUM },
						MyDatabaseHelper.SEND_BarcodeNo, isomux);
				if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
				
					http.Uploadzip(PostService.this, "/sdcard/" + path
							+ "task.txt", path + "task.txt",
							1, isomux);
					String[] sendtxtnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
						DB.upload3(PostService.this,
								new String[] { "DCL" },
								new String[] { "1" }, fjnum.get(i));
						//广播
						Intent lrcIntent = new Intent();
						lrcIntent.setAction("techown.card.portWolong.FJ");
						sendBroadcast(lrcIntent); 
						
						delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.zip");	
						delete("/sdcard/"+"t_"+Constant.username+"_"+barcodeno[0]+"_"+barcodeno[1]+"."+"task.txt");
						
						//压缩包的文件名称
						String filepath = "t_" + Constant.username + "_"
						+ barcodeno[0] + "_" + barcodeno[1] + ".task";
						// 查询在gongdanweihu表中该工单的数据保存到QueryInsert数组
						String[] QueryInsert = DB.cardQuery(
								PostService.this, new String[] {"Dnum",
										"DCL", "LX", "TIME", "Stname",
										"taskId2", "taskId", "ActiveType",
										"Name", "TelNumber", "Neirong",
										"Xiaofeidate", "Kahao", "Xiaofxs",
										"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
										"AttLicense", "AttContract","AttAccredit", "AttContract1",
										"AttContract2","AttContract3","AttContract4","AttContract5",
										"AttContract6","AttContract7","AttContract8","AttContract9",
										"AttOthers"},
								"Id", fjnum.get(i), "gongdanweihu");
						
						//存入该笔工单的工单的文件名
						DB.senduploadinsert(PostService.this, "FileName",
								filepath, "gongdanweihusent");
						
						//将该笔发送的工单信息存入到备份数据库中
						DB.uploadinsert(
								PostService.this,
								new String[] {"Dnum",
										"DCL", "LX", "TIME", "Stname",
										"taskId2", "taskId", "ActiveType",
										"Name", "TelNumber", "Neirong",
										"Xiaofeidate", "Kahao", "Xiaofxs",
										"Shouquma", "Jin", "Wei","WenXuan","BaiFang",
										"AttLicense", "AttContract","AttAccredit", "AttContract1",
										"AttContract2","AttContract3","AttContract4","AttContract5",
										"AttContract6","AttContract7","AttContract8","AttContract9",
										"AttOthers"},
								QueryInsert,filepath);
						DB.uploadinsert(
								PostService.this,new String[] {"Id"},
								new String[]{fjnum.get(i)},filepath);
						
						
						// 存入登陆表中t sentupload表中标志位 1 成功 0 失败
						String[] strname = new String[]{"t_sent","flag"};
						String[] strvalue = new String[]{filepath,"0"};
						DB.senduploadinsertfilename(PostService.this, strname,
								strvalue,"t_sentUpload");
						
						//删除数据库 
  					  if(isDatabaseExists(PostService.this,"techown.db"))
    						{
      						  SQLiteDatabase db=null;
      						  try
      						  {
      							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
											"techown.db", 1);
									 db = myHelper.getWritableDatabase();
									db.delete("gongdanweihu",
											"DCL" + "=?",
											new String[] {des.jiaMi("1") });
									db.delete(MyDatabaseHelper.SEND,
											MyDatabaseHelper.SEND_BarcodeNo + "=?",
											new String[] {barcodeno[0]});
									db.close();  
      						  }
      						  catch(Exception e)
      						  {
      							  if(db!=null)
      							  {
      								  db.close();   
      							  }
      						  }
    						}
  					  //删除数据库 
						}
					}
				} catch (Exception e) {
					cursor.close();
					db.close();
					Log.d("debug main Service", "发送失败！" + e.getMessage());
					techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
					techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
				}
//				fjnum.remove(0);
//				finally{
//					if(cursor!=null)
//						cursor.close();
//					if(db!=null)
//						db.close();
//				}
			}

			try {
				Thread.sleep(3000);
			} catch (Exception e) {
			}
		}
		
		//最红  星期五正常进件和草稿箱
		cgxQuery("DCL", des.jiaMi("2"),"red_enterletter");
		
		if (fjnum.size() > 0) {
			for (int i = 0; i < fjnum.size(); i++) {
				try {
					// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来
					String[] barcodeno=DB.cardQuery(PostService.this,new String[]{"MId","time"},"Id",fjnum.get(i),"red_enterletter");				      
					myHelper = new MyDatabaseHelper(PostService.this,
							"techown.db", Constant.tdshDBVer);
					db = myHelper.getWritableDatabase();
					cursor = db.rawQuery(
							"select * from red_enterletter where id == ?",
							new String[] { fjnum.get(i) });

					while (cursor.moveToNext()) {

						isomux = des.jieMI(cursor.getString(cursor
								.getColumnIndex("MId")));
					}
					//发件ID
					id=fjnum.get(i);
					cursor.close();
					db.close();
				
					
					String path="t_"+Constant.username+"_RedFridayActive_"+barcodeno[0]+".";
					
					String[] send = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					zuBao.redfun(fjnum.get(i),path);
					
					try{
						http.Uploadzip(PostService.this, "/sdcard/" +path
								+ "redactive.zip",path + "redactive.zip",
								Integer.parseInt(send[0]), isomux);
					}catch (Exception e) {
						// TODO: handle exception
						
					}
					
					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")&&Integer.parseInt(sendnum[0])>Integer.parseInt(sendnum[1])) {
						
						http.Uploadzip(PostService.this, "/sdcard/" + path
								+ "redactive.txt", path + "redactive.txt",
								1, isomux);
						String[] sendtxtnum = DB.sendQuery(PostService.this,
								new String[] { MyDatabaseHelper.BEGINNUM,
										MyDatabaseHelper.TOTALNUM },
								MyDatabaseHelper.SEND_BarcodeNo, isomux);
						if (sendtxtnum[1].equals("1")&&Integer.parseInt(sendtxtnum[0])>Integer.parseInt(sendtxtnum[1])) {
							
							DB.Red_upload(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							
							//广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.portWolong.FJ");
							sendBroadcast(lrcIntent); 
							
							delete("/sdcard/"+"t_"+Constant.username+"_RedFridayActive_"+barcodeno[0]+"."+"redactive.zip");	
							delete("/sdcard/"+"t_"+Constant.username+"_RedFridayActive_"+barcodeno[0]+"."+"redactive.txt");
							
							File fit=new File("/sdcard/"+"TJB/TuanDui/redwu/"+id);
							File fit2 = new File("/sdcard/"+"TJB/TuanDui/redwu/" +path);
	       					  if (fit.exists())
	       			    		{
	       						  deleteDir(fit);
	       						techown.shanghu.https.Log.Instance().WriteLog("已经删除"+"/sdcard/"+"TJB/TuanDui/redwu/"+id+"/pic/"+"原文件夹");					     	
	       			    		}
	       					  if (fit2.exists())
	       					  {
	       						deleteDir(fit2);
	       					  }	 	 
	       					  
	       					//删除数据库 
        					  if(isDatabaseExists(PostService.this,"techown.db"))
	      						{
	        						  SQLiteDatabase db=null;
	        						  try
	        						  {
	        							  MyDatabaseHelper myHelper = new MyDatabaseHelper(PostService.this,
	  											"techown.db", Constant.tdshDBVer);
	  									 db = myHelper.getWritableDatabase();
	  									db.delete("red_enterletter",
	  											"DCL" + "=?",
	  											new String[] {des.jiaMi("1") });
	  									db.delete(MyDatabaseHelper.SEND,
	  											MyDatabaseHelper.SEND_BarcodeNo + "=?",
	  											new String[] {barcodeno[0]});
	  									db.close();  
	        						  }
	        						  catch(Exception e)
	        						  {
	        							  if(db!=null)
	        							  {
	        								  db.close();   
	        							  }
	        						  }
	      						}
        					//删除数据库 
	       					  
						}
					}
				} catch (Exception e) {
					cursor.close();
					db.close();
					Log.d("debug main Service", "发送失败！" + e.getMessage());
					techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件异");
					techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件出错信息:"+e.getMessage());
					e.printStackTrace();
				}
			}

			try {
			} catch (Exception e) {
				
			}
		}
		
			Log.d("debug main Service", ".............");
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
			}
		}

	}
	

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		myHelper = new MyDatabaseHelper(this, "techown.db", Constant.tdshDBVer);
		isrun = true;
		
		if(t==null)
		{
			t=new Thread(this);
			t.start();	
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isrun = false;
	}

	public void cgxQuery(String ziduan, String str,String table) {
		 fjnum.clear();
		 SQLiteDatabase db = null;
		 Cursor cur = null;
		try {

			db = myHelper.getWritableDatabase();
			cur = db.query(table,
					new String[] { "Id" }, ziduan + "=?",
					new String[] { str }, null, null, null);
			int numIndex = cur.getColumnIndex("Id");
			if (cur.getCount() != 0) {
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					fjnum.add(cur.getString(numIndex));
				}
			}
			
		} catch (Exception e) {
//			Toast.makeText(this, "数据库错误：" + e.toString(), Toast.LENGTH_SHORT)
//					.show();
			Log.d("debug main Service", "cgxQuery出错！" + e.getMessage());
			techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
		}finally{
			if(cur != null){
				cur.close();
			}if(db != null){
				db.close();
			}
		}

	}
	public void cgxQuery1(String ziduan, String str) {
		 fjnum.clear();
		 SQLiteDatabase db = null;
		 Cursor cur = null;
		try {

			db = myHelper.getWritableDatabase();
			cur = db.query("youhushangxian",
					new String[] { "Id" }, ziduan + "=?",
					new String[] { str }, null, null, null);
			int numIndex = cur.getColumnIndex("Id");
			if (cur.getCount() != 0) {
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					fjnum.add(cur.getString(numIndex));
				}
			}
			
		} catch (Exception e) {
//			Toast.makeText(this, "数据库错误：" + e.toString(), Toast.LENGTH_SHORT)
//					.show();
			Log.d("debug main Service", "cgxQuery出错！" + e.getMessage());
			techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
		}finally{
			if(cur != null){
				cur.close();
			}if(db != null){
				db.close();
			}
		}

	}
	public void cgxQuery111(String ziduan, String str,String table) {
		 fjnum.clear();
		 SQLiteDatabase db = null;
		 Cursor cur = null;
		try {

			db = myHelper.getWritableDatabase();
			cur = db.query(table,
					new String[] { "Id" }, ziduan + "=?",
					new String[] { str }, null, null, null);
			int numIndex = cur.getColumnIndex("Id");
			if (cur.getCount() != 0) {
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					fjnum.add(des.jieMI(cur.getString(numIndex)));
				}
			}
			
		} catch (Exception e) {
//			Toast.makeText(this, "数据库错误：" + e.toString(), Toast.LENGTH_SHORT)
//					.show();
			Log.d("debug main Service", "cgxQuery出错！" + e.getMessage());
			techown.shanghu.https.Log.Instance().WriteLog("团队：上传进件段异常");
		}finally{
			if(cur != null){
				cur.close();
			}if(db != null){
				db.close();
			}
		}

	}
	private void delete(String filepath)
	{
		  File fizip=new File(filepath);	        		        	
        if (fizip.exists())
		{
       	 fizip.delete();
       	 techown.shanghu.https.Log.Instance().WriteLog("删除目录"+filepath+"下文件成功");		
		}
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
	} 
	
	//删除成功工单的问题件夹以及图片
	public void DeleteFile(String Dcl)
	{
		try {
			cgxQuery111("FileName", des.jiaMi(Dcl), "gongdanweihusent");
			if (fjnum.size() > 0) {
				for (int i = 0; i < fjnum.size(); i++) {
					
					id100 = fjnum.get(i);
					String[] barcodeno = DB.cardQuerytask(PostService.this,
							new String[] { "taskId", "time" }, "Id",
							fjnum.get(i), "gongdanweihusent");
					String path = "t_" + Constant.username + "_"
					+ barcodeno[0] + "_" + barcodeno[1] + ".";
					File fit = new File("/sdcard/"
							+ "TJB/TuanDui/gongdan/" + id100);
					File fit2 = new File("/sdcard/"
							+ "TJB/TuanDui/gongdan/" + path);
					if (fit.exists()) {
						deleteDir(fit);
						techown.shanghu.https.Log.Instance().WriteLog(
								"已经删除/sdcard/TJB/TuanDui/gongdan/"+id100+"/pic/文件夹");
						System.out.println("进件重传删除"+"/sdcard/TJB/TuanDui/gongdan/" + id100);
					}
					if (fit2.exists()) {
						deleteDir(fit2);
						techown.shanghu.https.Log.Instance().WriteLog(
								"已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"原文件夹");
						System.out.println("已经删除"+"/sdcard/"+"TJB/TuanDui/shang/"+path+"文件夹");
						
						//删除登录    t_sentUpload  表中前一次登录发送的旧数据
						DB.Deletetsentuplaodsent(PostService.this, new String[]{Dcl});
						
						//删除团队数据库中成功的工单数据
						DB.DeleteQuery(PostService.this, Dcl, "gongdanweihusent", "FileName");
						
						techown.shanghu.https.Log.Instance().WriteLog("已经删除数据"+Dcl);
					}
					else
					{
						//删除登录    t_sentUpload  表中前一次登录发送的旧数据
						DB.Deletetsentuplaodsent(PostService.this, new String[]{Dcl});
						
						//删除团队数据库中成功的工单数据
						DB.DeleteQuery(PostService.this, Dcl, "gongdanweihusent", "FileName");
						
						techown.shanghu.https.Log.Instance().WriteLog("已经删除数据"+Dcl);
					}
				}
			}
			else
			{
				//删除登录    t_sentUpload  表中前一次登录发送的旧数据
				DB.Deletetsentuplaodsent(PostService.this, new String[]{Dcl});
			}
		} catch (Exception e) {
			// TODO: handle exception
			techown.shanghu.https.Log.Instance().WriteLog(
					"已经删除原文件夹异常"+e.getMessage());
		}
		
	}
	
	
	//工单进件失败再次进件
	public void PostServiceTow(String Dcl)
	{
		//上门工单进件失败再次进件
		cgxQuery111("FileName", des.jiaMi(Dcl), "gongdanweihusent");
		if (fjnum.size() > 0) {
			System.out.println("进入失败工单重传");
			for (int i = 0; i < fjnum.size(); i++) {
				try {
					// 生成Txt文件并且发送图片压缩文件和文本 流水号等待扫描替换，员工号和日期从登录模块传过来

					System.out.println("i==="+i+",fjnum.size()==="+fjnum.size());
					myHelper = new MyDatabaseHelper(PostService.this,
							"techown.db", Constant.tdshDBVer);
					db = myHelper.getWritableDatabase();
					cursor = db.rawQuery(
							"select * from gongdanweihusent where id == ?",
							new String[] { des.jiaMi(fjnum.get(i)) });

					while (cursor.moveToNext()) {

						isomux = des.jieMI(cursor.getString(cursor
								.getColumnIndex("taskId2")));
					}
					
					// 发件ID
					id120 = fjnum.get(i);
					cursor.close();
					db.close();

					String[] barcodeno = DB.cardQuery(PostService.this,
							new String[] { "taskId", "time" }, "Id",
							des.jiaMi(fjnum.get(i)), "gongdanweihusent");
					System.out.println("失败进件barcodeno[0]===="+barcodeno[0]+"，barcodeno[1]"+barcodeno[1]);
					//文件名称
					String path = "t_" + Constant.username + "_"
							+ barcodeno[0] + "_" + barcodeno[1] + ".";
					pathdelete = path;
					
					DB.DeletesendQuery(PostService.this, MyDatabaseHelper.SEND_BarcodeNo, isomux);
					
					DB.sendinsert(PostService.this, new String[] {
			    			MyDatabaseHelper.SEND_BarcodeNo,
							MyDatabaseHelper.BEGINNUM, MyDatabaseHelper.TOTALNUM },
							new String[] { isomux, "1", "0" });
					
					String[] send = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					zuBao1.funsent(fjnum.get(i), path);
					
					try {
						http.Uploadzip(PostService.this, "/sdcard/"
								+ path + "task.zip", path + "task.zip",
								Integer.parseInt(send[0]), isomux);
						System.out.println("发送失败的工单：/sdcard/"+ path + "task.zip");
					} catch (Exception e) {
						// TODO: handle exception
						// return;
					}

					String[] sendnum = DB.sendQuery(PostService.this,
							new String[] { MyDatabaseHelper.BEGINNUM,
									MyDatabaseHelper.TOTALNUM },
							MyDatabaseHelper.SEND_BarcodeNo, isomux);
					if (!sendnum[1].equals("0")
							&& Integer.parseInt(sendnum[0]) > Integer
									.parseInt(sendnum[1])) {

						http.Uploadzip(PostService.this, "/sdcard/"
								+ path + "task.txt", path + "task.txt",
								1, isomux);
						String[] sendtxtnum = DB
								.sendQuery(
										PostService.this,
										new String[] {
												MyDatabaseHelper.BEGINNUM,
												MyDatabaseHelper.TOTALNUM },
										MyDatabaseHelper.SEND_BarcodeNo,
										isomux);
						if (sendtxtnum[1].equals("1")
								&& Integer.parseInt(sendtxtnum[0]) > Integer
										.parseInt(sendtxtnum[1])) {
							DB.uploadsent(PostService.this,
									new String[] { "DCL" },
									new String[] { "1" }, fjnum.get(i));
							System.out.println("失败工单重新发送成功");
							// 广播
							Intent lrcIntent = new Intent();
							lrcIntent.setAction("techown.card.pordWolong.FJ");
							sendBroadcast(lrcIntent);

							
							//压缩包的文件名称
							String filepath = "t_" + Constant.username + "_"
							+ barcodeno[0] + "_" + barcodeno[1] + ".task";
							
							//删除登录    t_sentUpload  表中前一次登录发送的旧数据
							DB.Deletetsentuplaodsent(PostService.this, new String[]{Dcl});
							
							// 存入登陆表中t sentupload表中标志位 1 成功 0 失败
							String[] strname = new String[]{"t_sent","flag"};
							String[] strvalue = new String[]{filepath,"0"};
							DB.senduploadinsertfilename(PostService.this, strname,
									strvalue,"t_sentUpload");

							
							delete("/sdcard/" + "t_"
									+ Constant.username + "_"
									+ barcodeno[0] + "_" + barcodeno[1]
									+ "." + "task.zip");
							System.out.println("删除发送的失败工单：/sdcard/" + "t_"+ Constant.username + "_"
									+ barcodeno[0] + "_" + barcodeno[1]+ "." + "task.zip");
							
							delete("/sdcard/" + "t_"
									+ Constant.username + "_"
									+ barcodeno[0] + "_" + barcodeno[1]
									+ "." + "task.txt");
							System.out.println("删除发送的失败工单：/sdcard/" + "t_"+ Constant.username + "_"
									+ barcodeno[0] + "_" + barcodeno[1]+ "." + "task.txt");
						}
					}
				} catch (Exception e) {
					cursor.close();
					db.close();
					Log.d("debug main Service",
							"发送失败！" + e.getMessage());
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队：上传失败工单进件段异常");
					techown.shanghu.https.Log.Instance().WriteLog(
							"团队：上传失败工单进件出错信息:" + e.getMessage());
				}
				break;
			}
		}
	}
	
	
	
	 public  boolean isDatabaseExists(Context context, String dbName){
			
			File dbFile = context.getDatabasePath(dbName);
			if(dbFile.exists()){
				return true;
			}
			return false;
		}
	 
	 
	 
	 
	 	

	 

}
