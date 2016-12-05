package techown.shanghu;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import techown.shanghu.biaoge.Constant;
import techown.shanghu.date.DES;
import techown.shanghu.date.TArea;
import techown.shanghu.date.TCity;
import techown.shanghu.date.T_MerType;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
	public static MyDatabaseHelper myHelper;
	static DES des = new DES();
	String[][] list2 = null;
	
	
	//总部活动查询
	public String[] ZBActive(Context con, String SelectWhere)
	{
		String[] Succ;
		Context friendContext = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		Succ = new String[37];
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			sld = myHelper.getWritableDatabase();

			String sql = "select * from t_cityactive "+ SelectWhere;
			cur = sld.rawQuery(sql, null);
			System.out.println("cur.getCount()===="+cur.getCount());
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					Succ[0] = des.jieMI(cur.getString(cur//活动主题
							.getColumnIndex("subject")));
					Succ[1] = des.jieMI(cur.getString(cur//活动描述
							.getColumnIndex("description")));
					Succ[2] = des.jieMI(cur.getString(cur//活动开始时间
							.getColumnIndex("beginDate")));
					Succ[3] = des.jieMI(cur.getString(cur//活动结束时间
							.getColumnIndex("endDate")));
					Succ[4] = des.jieMI(cur.getString(cur//签约日期
							.getColumnIndex("signDate")));
					Succ[5] = des.jieMI(cur.getString(cur//签约日期是否统一控制
							.getColumnIndex("isSignDate")));
					Succ[6] = des.jieMI(cur.getString(cur//活动优惠开始日期
							.getColumnIndex("priBeginDate")));
					Succ[7] = des.jieMI(cur.getString(cur//活动优惠开始日期是否统一控制
							.getColumnIndex("isPriBeginDate")));
					Succ[8] = des.jieMI(cur.getString(cur//活动优惠结束日期
							.getColumnIndex("priEndDate")));
					Succ[9] = des.jieMI(cur.getString(cur//优惠结束日期是否统一控制
							.getColumnIndex("isPriEndDate")));
					Succ[10] = des.jieMI(cur.getString(cur//卡种
							.getColumnIndex("card")));
					Succ[11] = des.jieMI(cur.getString(cur//卡种是否统一控制
							.getColumnIndex("isCard")));
					Succ[12] = des.jieMI(cur.getString(cur//是否发送短信
							.getColumnIndex("isSms")));
					Succ[13] = des.jieMI(cur.getString(cur//发送短信是否统一控制
							.getColumnIndex("isSmsUnite")));
					Succ[14] = des.jieMI(cur.getString(cur//短信内容
							.getColumnIndex("smsContent")));
					Succ[15] = des.jieMI(cur.getString(cur//短信内容是否统一控制
							.getColumnIndex("isSmsContent")));
					Succ[16] = des.jieMI(cur.getString(cur//优惠内容
							.getColumnIndex("priContent")));
					Succ[17] = des.jieMI(cur.getString(cur//优惠内容是否统一控制
							.getColumnIndex("isPriContent")));
					Succ[18] = des.jieMI(cur.getString(cur//金额限制
							.getColumnIndex("money")));
					Succ[19] = des.jieMI(cur.getString(cur//金额限制是否统一控制
							.getColumnIndex("isMoney")));
					Succ[20] = des.jieMI(cur.getString(cur//日期限制
							.getColumnIndex("dateLimit")));
					Succ[21] = des.jieMI(cur.getString(cur//日期限制是否统一控制
							.getColumnIndex("isDateLimit")));
					Succ[22] = des.jieMI(cur.getString(cur//活动日
							.getColumnIndex("activityDay")));
					Succ[23] = des.jieMI(cur.getString(cur//活动日是否统一控制
							.getColumnIndex("isActivityDay")));
					Succ[24] = des.jieMI(cur.getString(cur//特别约定
							.getColumnIndex("spercialAgree")));
					Succ[25] = des.jieMI(cur.getString(cur//特别约定是否统一控制
							.getColumnIndex("isSpercialAgree")));
					Succ[26] = des.jieMI(cur.getString(cur//是否发布
							.getColumnIndex("isPublish")));
					Succ[27] = des.jieMI(cur.getString(cur//发布是否统一控制
							.getColumnIndex("isPublishLimit")));
					Succ[28] = des.jieMI(cur.getString(cur//备用A
							.getColumnIndex("remartA")));
					Succ[29] = des.jieMI(cur.getString(cur//备用A是否统一控制
							.getColumnIndex("isRemartA")));
					Succ[30] = des.jieMI(cur.getString(cur//备用B
							.getColumnIndex("remartB")));
					Succ[31] = des.jieMI(cur.getString(cur//备用B是否统一控制
							.getColumnIndex("isRemartB")));
					Succ[32] = des.jieMI(cur.getString(cur//备用C
							.getColumnIndex("remartC")));
					Succ[33] = des.jieMI(cur.getString(cur//备用C是否统一控制
							.getColumnIndex("isRemartC")));
					Succ[34] = des.jieMI(cur.getString(cur//备用D
							.getColumnIndex("remartD")));
					Succ[35] = des.jieMI(cur.getString(cur//备用D是否统一控制
							.getColumnIndex("isRemartD")));
					Succ[36] = des.jieMI(cur.getString(cur//创建日期
							.getColumnIndex("createTimeDate")));
				}

			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：ZBActive:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return Succ;
	}
	
	
	//最红
	public String[] redFridayInfo(Context con) {
		String[] str = null;
		Cursor cur = null;
		SQLiteDatabase sld = null;
		try {
			Context friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			int i = 0;
			sld = myHelper.getWritableDatabase();

			cur = sld.query("redFridayInfo", new String[] { "businessName" }, null, null,
					null, null, null);
			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des
							.jieMI(cur.getString(cur.getColumnIndex("businessName")));
					i++;
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询行业活动:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return str;
	}
	public String[] redFridayInfoQuery(Context con, String tablename,
			String where) {
		String[] succ;
		Context friendContext = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		succ = new String[2];
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			sld = myHelper.getWritableDatabase();

			String sql = "select * from " + tablename + where;
			cur = sld.rawQuery(sql, null);
			System.out.println("cur.getCount()===="+cur.getCount());
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					succ[0] = des.jieMI(cur.getString(cur
							.getColumnIndex("activeContent")));
					succ[1] = des.jieMI(cur.getString(cur
							.getColumnIndex("smsContent")));
				}

			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：cityQuerycity:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return succ;
	}
	
	// 查询一行多个字段
	public  String[] cardQuery(final Context con, String[] clunname,
			String where, String wherestr,String table) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		String[] str = new String[clunname.length];
		SQLiteDatabase sld=null;
		Cursor cur=null;
		try{
		sld = myHelper.getWritableDatabase();
		cur = sld.query(table, clunname, where
				+ "=?", new String[] { wherestr }, null, null, null);
		while (cur.moveToNext()) {
			for (int i = 0; i < clunname.length; i++) {
				if (cur.getString(i) != null) {
					str[i] = des.jieMI(cur.getString(i));
				}
			}

		}
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：查询省cardQuery:"+e.getMessage());
		}finally{
			if(cur!=null){
				cur.close();
			}
			if(sld!=null){
				sld.close();
			}
		}
			
		
		return str;
	}
	
	
	// 查询一行多个字段
	public  String[] cardQuerytask(final Context con, String[] clunname,
			String where, String wherestr,String table) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		String[] str = new String[clunname.length];
		SQLiteDatabase sld=null;
		Cursor cur=null;
		try{
		sld = myHelper.getWritableDatabase();
		cur = sld.query(table, clunname, where
				+ "=?", new String[] { des.jiaMi(wherestr) }, null, null, null);
		while (cur.moveToNext()) {
			for (int i = 0; i < clunname.length; i++) {
				if (cur.getString(i) != null) {
					str[i] = des.jieMI(cur.getString(i));
				}
			}

		}
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：查询省cardQuery:"+e.getMessage());
		}finally{
			if(cur!=null){
				cur.close();
			}
			if(sld!=null){
				sld.close();
			}
		}
			
		
		return str;
	}
	
	
	//查询成功文件的文件名称
		public String[] FileNameQuerytwo(Context con, String[] clunname) {
			Context friendContext = null;
			SQLiteDatabase db = null;
			Cursor cursor = null;
			String[] str = null;
			try {
				friendContext = con.createPackageContext("techown.login",
								Context.CONTEXT_IGNORE_SECURITY);
				if (isDatabaseExists(friendContext,"techown_login.db")) 
				{
					StringBuffer sb = new StringBuffer();
					if(clunname!=null&&clunname.length!=0)
					{
						if(clunname.length==1)
						{
							
							sb.append("select t_sent from t_sentUpload where t_sent != ? and flag = '"+des.jiaMi("1")+"'");
						}
						else
						{
							sb.append("select t_sent from t_sentUpload where t_sent != ? and flag = '"+des.jiaMi("1")+"'");
							for (int j = 1; j < clunname.length; j++) {
								sb.append(" and t_sent != ?");
							}
						}
						Set<String> set = new HashSet<String>();
						myHelper = new MyDatabaseHelper(friendContext,"techown_login.db", Constant.loginDBVer);
						db = myHelper.getWritableDatabase();
						String[] arrgname = new String[clunname.length];
						for (int i = 0; i < clunname.length; i++) {
							arrgname[i] = des.jiaMi(clunname[i]);
						}
						cursor = db.rawQuery(sb.toString(),arrgname);
						if (cursor.getCount() > 0) {
							while (cursor.moveToNext()) {
								set.add(des.jieMI(cursor.getString(cursor
										.getColumnIndex("t_sent"))));
							}
						}
						str = new String[set.size()];
						str = set.toArray(str);
						System.out.println("查询成功工单文件名称成功");
					}
					else
					{
						sb.append("select t_sent from t_sentUpload where flag = '"+des.jiaMi("1")+"'");
						Set<String> set = new HashSet<String>();
						myHelper = new MyDatabaseHelper(friendContext,"techown_login.db", Constant.loginDBVer);
						db = myHelper.getWritableDatabase();
						
						cursor = db.rawQuery(sb.toString(),null);
						if (cursor.getCount() > 0) {
							while (cursor.moveToNext()) {
								set.add(des.jieMI(cursor.getString(cursor
										.getColumnIndex("t_sent"))));
							}
						}
						str = new String[set.size()];
						str = set.toArray(str);
						System.out.println("查询成功工单文件名称成功");
					}
				}
				System.out.println("删除数据库t_sentUpload数据");
			} catch (Exception e) {
				Constant.loginDBVer = getLoginDBVer("/sdcard/TJB/ver/login.txt");
				techown.shanghu.https.Log.Instance().WriteLog(
						"删除数据库t_sentUpload数据异常" + e.getMessage());
			}
			finally{
				if (db != null) {
					db.close();
				}
			}
			return str;
		}
	
	
	//查询成功文件的文件名称
	public String[] FileNameQuery(Context con, String[] clunname) {
		String[] str = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		DES des = new DES();
		StringBuffer sb = new StringBuffer();
		try {
			if(clunname!=null&&clunname.length!=0)
			{
				if(clunname.length==1)
				{
					
					sb.append("select FileName from gongdanweihusent where FileName != ?");
				}
				else
				{
					sb.append("select FileName from gongdanweihusent where FileName != ?");
					for (int j = 1; j < clunname.length; j++) {
						sb.append(" and FileName != ?");
					}
				}
				Set<String> set = new HashSet<String>();
				MyDatabaseHelper myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
				db = myHelper.getWritableDatabase();
				String[] arrgname = new String[clunname.length];
				for (int i = 0; i < clunname.length; i++) {
					arrgname[i] = des.jiaMi(clunname[i]);
				}
				cursor = db.rawQuery(sb.toString(),arrgname);
				if (cursor.getCount() > 0) {
					while (cursor.moveToNext()) {
						set.add(des.jieMI(cursor.getString(cursor
								.getColumnIndex("FileName"))));
					}
				}
				str = new String[set.size()];
				str = set.toArray(str);
				System.out.println("查询成功工单文件名称成功");
			}
			else
			{
				sb.append("select FileName from gongdanweihusent");
				Set<String> set = new HashSet<String>();
				MyDatabaseHelper myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
				db = myHelper.getWritableDatabase();
				
				cursor = db.rawQuery(sb.toString(),null);
				if (cursor.getCount() > 0) {
					while (cursor.moveToNext()) {
						set.add(des.jieMI(cursor.getString(cursor
								.getColumnIndex("FileName"))));
					}
				}
				str = new String[set.size()];
				str = set.toArray(str);
				System.out.println("查询成功工单文件名称成功");
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("查询成功工单文件名称"+e.getMessage());
		}
		finally{
			if(cursor!=null){
				cursor.close();
			}
			if(db!=null){
				db.close();
			}
		}
		return str;
	}
	//删除登陆数据库中t_sentupload表中状态为1的文件
	public void Deletetsentuplaod(final Context con,String[] clomstr) {
		Context friendContext = null;
		SQLiteDatabase sld3 = null;
		try {
			friendContext = con.createPackageContext("techown.login",
							Context.CONTEXT_IGNORE_SECURITY);
			if (isDatabaseExists(friendContext,"techown_login.db")) 
			{
				myHelper = new MyDatabaseHelper(friendContext,"techown_login.db", Constant.loginDBVer);
				sld3 = myHelper.getWritableDatabase();
				int cont = sld3.delete("t_sentUpload", "flag" + "=?",new String[] { des.jiaMi(clomstr[0]) });
				System.out.println("cont===="+cont);
			}
			System.out.println("删除数据库t_sentUpload数据");
		} catch (Exception e) {
			Constant.loginDBVer = getLoginDBVer("/sdcard/TJB/ver/login.txt");
			techown.shanghu.https.Log.Instance().WriteLog(
					"删除数据库t_sentUpload数据异常" + e.getMessage());
		}
		finally{
			if (sld3 != null) {
				sld3.close();
			}
		}
	}
	
	//删除登陆数据库中t_sentupload表中状态为1的文件
	public void Deletetsentuplaodsent(final Context con,String[] clomstr) {
		Context friendContext = null;
		SQLiteDatabase sld3 = null;
		try {
			friendContext = con.createPackageContext("techown.login",
							Context.CONTEXT_IGNORE_SECURITY);
			if (isDatabaseExists(friendContext,"techown_login.db")) 
			{
				myHelper = new MyDatabaseHelper(friendContext,"techown_login.db", Constant.loginDBVer);
				sld3 = myHelper.getWritableDatabase();
				int cont = sld3.delete("t_sentUpload", "t_sent" + "=?",new String[] { des.jiaMi(clomstr[0]) });
				System.out.println("t_sentUpload：cont===="+cont);
				techown.shanghu.https.Log.Instance().WriteLog("t_sentUpload：cont===="+cont);
			}
			System.out.println("删除数据库t_sentUpload数据");
		} catch (Exception e) {
			Constant.loginDBVer = getLoginDBVer("/sdcard/TJB/ver/login.txt");
			techown.shanghu.https.Log.Instance().WriteLog(
					"删除数据库t_sentUpload数据异常" + e.getMessage());
		}
		finally{
			if (sld3 != null) {
				sld3.close();
			}
		}
	}
	
	
	
	// 删除不符合条件的数据
	public void DeleteQuerytsent(final Context con, String clomname,
			String tablename) {
		Context friendContext = null;
		SQLiteDatabase db = null;
		DES des = new DES();
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			if (isDatabaseExists(friendContext, "techown_login.db")) {
				myHelper = new MyDatabaseHelper(friendContext,
						"techown_login.db", Constant.loginDBVer);
				db = myHelper.getWritableDatabase(); // 获得数据库对象
				db.delete(tablename, "flowerNum = ?", new String[]{des.jiaMi(clomname)});
				db.close();
			}
		} catch (Exception e) {
			if (db != null) {
				db.close();
			}
			techown.shanghu.https.Log.Instance().WriteLog(
					"DBUTIL.senduploadinsert异常:" + e.getMessage());
		}
	}
	
	// 删除不符合条件的数据
	public void DeleteQuery(final Context con, String clunstr,
			String table, String wherename) {
		MyDatabaseHelper myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase();
		try {
			
			String wherestr=wherename+" = ?";
				
			int cont = db.delete(table, wherestr, new String[]{des.jiaMi(clunstr)});
			System.out.println("gongdanweihusent：cont===="+cont);
		} catch (Exception e) {
			// TODO: handle exception
			db.close();
		}
		finally{
			if(db != null)
			{
				db.close();
			}
		}
		
	}

	// 查询dcl标志位
	public String QueryNum(final Context con, String[] clunname, String where,
			String wherestr, String table) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		String str = "";
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {
			sld = myHelper.getWritableDatabase();
			cur = sld.query(table, clunname, where + "=?",
					new String[] { des.jiaMi(wherestr) }, null, null, null);
			while (cur.moveToNext()) {
				for (int i = 0; i < clunname.length; i++) {
					if (cur.getString(i) != null) {
						str = des.jieMI(cur.getString(i));
					}
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省cardQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return str;
	}

	public void senduploadinsertfilename(final Context con, String[] clomname,
			String[] clomstr, String tablename) {
		Context friendContext = null;
		SQLiteDatabase db = null;
		DES des = new DES();
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			if (isDatabaseExists(friendContext, "techown_login.db")) {
				myHelper = new MyDatabaseHelper(friendContext,
						"techown_login.db", Constant.loginDBVer);
				db = myHelper.getWritableDatabase(); // 获得数据库对象
				ContentValues values = new ContentValues();
				for (int i = 0; i < clomname.length; i++) {
					values.put(clomname[i], des.jiaMi(clomstr[i]));
				}

				long count = db.insert(tablename, null, values); // 插入数据
				db.close();
				if (count == -1) {
					techown.shanghu.https.Log.Instance().WriteLog(
							"DBUTIL.senduploadinsert 插入失败:");

				}
			}
		} catch (Exception e) {
			if (db != null) {
				db.close();
			}

			techown.shanghu.https.Log.Instance().WriteLog(
					"DBUTIL.senduploadinsert异常:" + e.getMessage());

		}
	}

	// 更新一行多个字段
	public void uploadinsert(final Context con, String[] clomname,
			String[] clomstr, String FileName) {

		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();

		for (int i = 0; i < clomname.length; i++) {
			values.put(clomname[i], des.jiaMi(clomstr[i]));
		}

		long count = db.update("gongdanweihusent", values, "FileName" + "=?",
				new String[] { des.jiaMi(FileName) }); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：查询省upload:");
		}

	}

	// 插入一个字段
	public void senduploadinsert(final Context con, String clomname,
			String clomstr, String tablename) {
		SQLiteDatabase db = null;
		DES des = new DES();
		try {
			myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase(); // 获得数据库对象
			ContentValues values = new ContentValues();
			values.put(clomname, des.jiaMi(clomstr));
			long count = db.insert(tablename, null, values); // 插入数据
			db.close();
			if (count == -1) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"DBUTIL.senduploadinsert 插入失败:");

			}
		} catch (Exception e) {
			if (db != null) {
				db.close();
			}
			techown.shanghu.https.Log.Instance().WriteLog(
					"DBUTIL.senduploadinsert异常:" + e.getMessage());
		}
	}

	// 查询省
	public String[] klQuery(final Context con, String clunname, String tablename) {

		Context friendContext = null;
		String[] str = null;
		Cursor cur = null;
		SQLiteDatabase sld = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			Set<String> set = new HashSet<String>();
			sld = myHelper.getWritableDatabase();
			cur = sld.query(tablename, new String[] { clunname }, null, null,
					null, null, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					set.add(des.jieMI(cur.getString(cur
							.getColumnIndex(clunname))));
				}
			}
			str = new String[set.size()];
			str = set.toArray(str);

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省klQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return str;
	}
	
	//查询登录向中间件发送的文件
	public String[] klQueryloginstr(final Context con) {

		Context friendContext = null;
		String[] str = null;
		Cursor cur = null;
		SQLiteDatabase sld = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			Set<String> set = new HashSet<String>();
			sld = myHelper.getWritableDatabase();
			cur = sld.rawQuery("select t_sent from t_sentUpload where flag = ?", new String[]{des.jiaMi("1")});
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					set.add(des.jieMI(cur.getString(cur
							.getColumnIndex("t_sent"))));
				}
			}
			str = new String[set.size()];
			str = set.toArray(str);

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省klQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return str;
	}
	

	// 查询省
	public String[][] klQuery21(final Context con, String clunname,
			String clunname2, String tablename) {

		List<T_MerType> typelist = new ArrayList<T_MerType>();
		Context friendContext = null;
		Cursor cur = null;
		SQLiteDatabase sld = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			sld = myHelper.getWritableDatabase();
			cur = sld.query(tablename, new String[] { "*" }, null, null, null,
					null, null);
			// if (cur.getCount() != 0) {
			// while(cur.moveToNext()){
			// for (int i = 0; i < cur.getCount(); i++) {
			// list2[i][0] =
			// des.jieMI(cur.getString(cur.getColumnIndex(clunname)));
			// list2[i][1] =
			// des.jieMI(cur.getString(cur.getColumnIndex(clunname2)));
			// }
			// }
			// }

			if (cur.getCount() != 0) {
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					// set.add(cur.getString(cur.getColumnIndex(clunname)));
					T_MerType type = new T_MerType();
					type.typeCode = des.jieMI(cur.getString(cur
							.getColumnIndex("typeCode")));
					type.typeNum = des.jieMI(cur.getString(cur
							.getColumnIndex("typeNum")));
					typelist.add(type);
				}
			}
			list2 = new String[typelist.size()][2];
			for (int i = 0; i < typelist.size(); i++) {
				list2[i][0] = typelist.get(i).typeCode;
				list2[i][1] = typelist.get(i).typeNum;
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省klQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return list2;
	}

	// 查询多行的一个字段
	public String[] klQuery2(Context con, String clunname, String tablename)
			throws NameNotFoundException {
		con = con.createPackageContext("techown.login",
				Context.CONTEXT_IGNORE_SECURITY);
		myHelper = new MyDatabaseHelper(con, "techown_login.db",
				Constant.loginDBVer);
		// myHelper = new MyDatabaseHelper(con,"techown_login.db",1);
		String[] str = null;
		// Set<String> set=new HashSet<String>();
		List<String> set = new ArrayList<String>();
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {

			sld = myHelper.getWritableDatabase();
			cur = sld.query(tablename, new String[] { clunname }, null, null,
					null, null, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					set.add(des.jieMI(cur.getString(cur
							.getColumnIndex(clunname))));
				}
			}
			str = new String[set.size()];
			str = set.toArray(str);
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省klQuery2:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return str;
	}
	
	// 查询城市活动表中的subject,description,activeId,simMode   4个字段
		public List<String[]> select_cityactive(Context con, String clunname, String tablename)
				throws NameNotFoundException {
			con = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(con, "techown_login.db",
					Constant.loginDBVer);
			// myHelper = new MyDatabaseHelper(con,"techown_login.db",1);
			// Set<String> set=new HashSet<String>();
			List<String[]> list44 = new ArrayList<String[]>();
			String[] set = null;
			SQLiteDatabase sld = null;
			Cursor cur = null;
			try {

				sld = myHelper.getWritableDatabase();
				cur = sld.query(tablename, new String[] { clunname }, null, null,
						null, null, null);
				String[] active = clunname.split(",");
				if (cur.getCount() != 0) {
					while (cur.moveToNext()) {
						set = new String[4];
						set[0] = des.jieMI(cur.getString(cur.getColumnIndex(active[0])));
						set[1] = des.jieMI(cur.getString(cur.getColumnIndex(active[1])));
						set[2] = des.jieMI(cur.getString(cur.getColumnIndex(active[2])));
						set[3] = des.jieMI(cur.getString(cur.getColumnIndex(active[3])));
						list44.add(set);
					}
				}
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"团队：查询省klQuery2:" + e.getMessage());
			} finally {
				if (cur != null) {
					cur.close();
				}
				if (sld != null) {
					sld.close();
				}
			}

			return list44;
		}
	
	
	// 查询多行的一个字段
		public String[][] ActiveSelect(Context con, String tablename)
				throws NameNotFoundException {
			con = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(con, "techown_login.db",
					Constant.loginDBVer);
			// myHelper = new MyDatabaseHelper(con,"techown_login.db",1);
			String[] StrActiveName = null;
			String[] StrActiveId = null;
			String[][] StrActive= null;
			// Set<String> set=new HashSet<String>();
			List<String> SetActiveName = new ArrayList<String>();
			List<String> SetActiveId = new ArrayList<String>();
			SQLiteDatabase sld = null;
			Cursor cur = null;
			try {

				sld = myHelper.getWritableDatabase();
				cur = sld.query(tablename, new String[] { "subject","activeId" }, null, null,
						null, null, null);
				if (cur.getCount() != 0) {
					while (cur.moveToNext()) {
						SetActiveName.add(des.jieMI(cur.getString(cur
								.getColumnIndex("subject"))));
						SetActiveId.add(des.jieMI(cur.getString(cur
								.getColumnIndex("activeId"))));
					}
				}
				//活动名称
				StrActiveName = new String[SetActiveName.size()];
				StrActiveName = SetActiveName.toArray(StrActiveName);
				//活动ID
				StrActiveId = new String[SetActiveId.size()];
				StrActiveId = SetActiveId.toArray(StrActiveId);
				
				StrActive= new String[2][StrActiveName.length];
				StrActive[0]= StrActiveName;
				StrActive[1]= StrActiveId;
			} catch (Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog(
						"团队：查询省klQuery2:" + e.getMessage());
			} finally {
				if (cur != null) {
					cur.close();
				}
				if (sld != null) {
					sld.close();
				}
			}

			return StrActive;
		}
	

	// 查询发件箱一行多个字段
	public String[] sendQuery(final Context con, String[] clunname,
			String where, String wherestr) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		String[] str = new String[clunname.length];
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {

			sld = myHelper.getWritableDatabase();
			cur = sld.query(MyDatabaseHelper.SEND, clunname, where + "=?",
					new String[] { wherestr }, null, null, null);
			while (cur.moveToNext()) {
				for (int i = 0; i < clunname.length; i++) {
					if (cur.getString(i) != null) {
						str[i] = cur.getString(i);
					}
				}

			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省sendQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return str;
	}
	
	// 删除发件箱一行多个字段
	public void DeletesendQuery(final Context con,
			String where, String wherestr) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {
			sld = myHelper.getWritableDatabase();
			sld.delete(MyDatabaseHelper.SEND, where + "=?",new String[] { wherestr });
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"删除发件箱一行多个字段" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

	}
	

	// 更新发件箱一行多个字段
	public void sendupload(final Context con, String[] clomname,
			String[] clomstr, String id) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		for (int i = 0; i < clomname.length; i++) {
			values.put(clomname[i], clomstr[i]);
		}

		long count = db.update(MyDatabaseHelper.SEND, values,
				MyDatabaseHelper.SEND_BarcodeNo + "=?", new String[] { id }); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：查询省sendupload:");
		}

	}

	// 发件箱表
	// 插入一行（ID)
	public void sendinsert(final Context con, String[] clomname,
			String[] clomstr) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		for (int i = 0; i < clomname.length; i++) {
			values.put(clomname[i], clomstr[i]);
		}

		long count = db.insert(MyDatabaseHelper.SEND, null, values); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：查询省sendinsert:");
		}
	}

	// 查询多行多个字段
	// public String[] kppQuery(Context con,String[] clunname,String
	// where,String wherestr,String tablename) throws NameNotFoundException
	// {con = con.createPackageContext("techown.login",
	// Context.CONTEXT_IGNORE_SECURITY);
	// myHelper = new MyDatabaseHelper(con, "techown_login.db",
	// Constant.loginDBVer);
	// // myHelper = new MyDatabaseHelper(con,"techown.db",1);
	// String[]str = null;
	// int i=0;
	// SQLiteDatabase sld=null;
	// Cursor cur=null;
	// try{
	//
	// sld = myHelper.getWritableDatabase();
	//
	// cur=sld.query(tablename,clunname,where+"=?",new
	// String[]{des.jiaMi(wherestr)},null,null,null);
	// str=new String[cur.getCount()];
	// if(cur.getCount()!=0)
	// {
	// while(cur.moveToNext()){
	// str[i]=des.jieMI(cur.getString(cur.getColumnIndex(clunname[0])))+":"+des.jieMI(cur.getString(cur.getColumnIndex(clunname[1])));
	// i++;
	// }
	// }
	//
	// }catch (Exception e) {
	// techown.shanghu.https.Log.Instance().WriteLog("团队：查询省kppQuery:"+e.getMessage());
	// }finally{
	// if(cur!=null){
	// cur.close();
	// }
	// if(sld!=null){
	// sld.close();
	// }
	// }
	//
	// return str;
	// }
	// 查询整个表
	public Map<String, String> QueryTable(final Context con, String[] clunname,
			String tablename) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		Map<String, String> map = new HashMap<String, String>();
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {

			sld = myHelper.getWritableDatabase();
			cur = sld.query(tablename, clunname, null, null, null, null, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					map.put(cur.getString(cur.getColumnIndex(clunname[0])),
							cur.getString(cur.getColumnIndex(clunname[1])));
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省QueryTable:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return map;
	}

	// 查询市
	public String[] cityQuery(final Context con, String clunname, String where,
			String wherestr, String tablename) {
		Context friendContext = null;
		String[] str = null;
		Cursor cur = null;
		SQLiteDatabase sld = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			int i = 0;
			sld = myHelper.getWritableDatabase();

			cur = sld.query(tablename, new String[] { clunname }, where + "=?",
					new String[] { des.jiaMi(wherestr) }, null, null, null);
			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询市cityQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return str;
	}

	public String query(final Context con, String[] str) {
		String sno = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try

		{

			myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);
			sld = myHelper.getWritableDatabase();
			String sql = "select business_code from typeproduct where card_type_code=? and issuing_org=? and gold_regular=?";
			cur = sld.rawQuery(sql, str);

			while (cur.moveToNext()) {
				sno = cur.getString(0);

			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：数据库错误:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return sno;
	}

	// 查询多行多个字段
	public String[] lydmQuery(final Context con, String[] clunname,
			String where, String wherestr, String tablename) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		String[] str = null;
		int i = 0;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {

			sld = myHelper.getWritableDatabase();

			cur = sld.query(tablename, clunname, where + "=?",
					new String[] { wherestr }, null, null, null);
			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = cur.getString(cur.getColumnIndex(clunname[0]));
					i++;
				}
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省lydmQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return str;
	}

	// 插入一行（ID)
	public void insertContactId(final Context con, String ziduan, String str) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		values.put(ziduan, str);
		long count = db.insert("st_storeinfo", ziduan, values); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省insertContactId:");
		}
	}
	
	// 插入一行（ID)
	public void red_insertContactId(final Context con, String ziduan, String str) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		values.put(ziduan, str);
		long count = db.insert("red_enterletter", ziduan, values); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省insertContactId:");
		}
	}

	public void insertContactId1(final Context con, String ziduan, String str) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		values.put(ziduan, str);
		long count = db.insert("hq_activity", ziduan, values); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省insertContactId1:");
		}
		// else{
		// Toast.makeText(con, str, Toast.LENGTH_LONG).show();
		// }
	}

	public void insertContactId2(final Context con, String ziduan, String str) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		values.put(ziduan, str);
		long count = db.insert("city_activity", ziduan, values); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省insertContactId2:");
		}
	}

	public void insertContactId31(final Context con, String ziduan, String str) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		values.put(ziduan, str);
		long count = db.insert("shgongdanweihu", ziduan, values); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询商户工单维护工单insertContactId31:");
		}
		// else{
		// Toast.makeText(con, str, Toast.LENGTH_LONG).show();
		// }
	}
	
	public void insertContactId3(final Context con, String ziduan, String str) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		values.put(ziduan, str);
		long count = db.insert("gongdanweihu", ziduan, values); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省insertContactId3:");
		}
		// else{
		// Toast.makeText(con, str, Toast.LENGTH_LONG).show();
		// }
	}

	public void insertContactId4(final Context con, String ziduan, String str) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		values.put(ziduan, str);
		long count = db.insert(MyDatabaseHelper.SEND, ziduan, values); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省insertContactId4:");
		}
		// else{
		// Toast.makeText(con, str, Toast.LENGTH_LONG).show();
		// }
	}

	public void insertContactId5(final Context con, String ziduan, String str) {
		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();
		values.put(ziduan, str);
		long count = db.insert("youhushangxian", ziduan, values); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省insertContactId5:");
		}
		// else{
		// Toast.makeText(con, str, Toast.LENGTH_LONG).show();
		// }
	}

	
	/**
	 * @param tableName 指定更新表名
	 * @param clomname 更新的字段列表
	 * @param clomstr 字段列表对应的值
	 * @param id 表的唯一Id
	 */
	public void upload2(final Context con, String tableName, String[] clomname, String[] clomstr,
				String id) {

			myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
			SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
			ContentValues values = new ContentValues();

			for (int i = 0; i < clomname.length; i++) {
				values.put(clomname[i], des.jiaMi(clomstr[i]));
			}
			long count = db.update(tableName, values, "Id" + "=?",
					new String[] { id }); // 插入数据
			db.close();
			if (count == -1) {
				techown.shanghu.https.Log.Instance().WriteLog("团队：查询省upload:");
			}
	}
	
	// 更新一行多个字段
	public void upload(final Context con, String[] clomname, String[] clomstr,
			String id) {

		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();

		for (int i = 0; i < clomname.length; i++) {
			values.put(clomname[i], des.jiaMi(clomstr[i]));
		}

		long count = db.update("st_storeinfo", values, "Id" + "=?",
				new String[] { id }); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：查询省upload:");
		}

	}
	
	// 更新一行多个字段
	public void Red_upload(final Context con, String[] clomname, String[] clomstr,
			String id) {

		myHelper = new MyDatabaseHelper(con, "techown.db", Constant.tdshDBVer);
		SQLiteDatabase db = myHelper.getWritableDatabase(); // 获得数据库对象
		ContentValues values = new ContentValues();

		for (int i = 0; i < clomname.length; i++) {
			values.put(clomname[i], des.jiaMi(clomstr[i]));
		}

		long count = db.update("red_enterletter", values, "Id" + "=?",
				new String[] { id }); // 插入数据
		db.close();
		if (count == -1) {
			techown.shanghu.https.Log.Instance().WriteLog("团队：查询省upload:");
		}

	}

	public void upload3(final Context con, String[] clomname, String[] clomstr,
			String id) {
		SQLiteDatabase db = null;
		try {
			myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);

			db = myHelper.getWritableDatabase(); // 获得数据库对象
			ContentValues values = new ContentValues();
			des = new DES();
			for (int i = 0; i < clomname.length; i++) {
				values.put(clomname[i], des.jiaMi(clomstr[i]));
			}
			long count = db.update("gongdanweihu", values, "Id" + "=?",
					new String[] { id }); // 插入数据
			db.close();
			if (count == -1) {
				techown.shanghu.https.Log.Instance().WriteLog("团队：工单添加存值gongdanweihu:");
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：upload3:" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void upload31(final Context con, String[] clomname, String[] clomstr,
			String id) {
		SQLiteDatabase db = null;
		try {
			myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);

			db = myHelper.getWritableDatabase(); // 获得数据库对象
			ContentValues values = new ContentValues();
			des = new DES();
			for (int i = 0; i < clomname.length; i++) {
				values.put(clomname[i], des.jiaMi(clomstr[i]));
			}
			long count = db.update("shgongdanweihu", values, "Id" + "=?",
					new String[] { id }); // 插入数据
			db.close();
			if (count == -1) {
				techown.shanghu.https.Log.Instance().WriteLog("团队：工单添加存值shgongdanweihu:");
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：upload31:" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void uploadsent(final Context con, String[] clomname, String[] clomstr,
			String id) {
		SQLiteDatabase db = null;
		try {
			myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);

			db = myHelper.getWritableDatabase(); // 获得数据库对象
			ContentValues values = new ContentValues();
			des = new DES();
			for (int i = 0; i < clomname.length; i++) {
				values.put(clomname[i], des.jiaMi(clomstr[i]));
			}
			long count = db.update("gongdanweihu", values, "Id" + "=?",
					new String[] { id }); // 插入数据
			db.close();
			if (count == -1) {
				techown.shanghu.https.Log.Instance().WriteLog("团队：工单添加存值gongdanweihu:");
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：upload3:" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

	}
	

	public void upload4(final Context con, String[] clomname, String[] clomstr,
			String id) {
		SQLiteDatabase db = null;
		try {
			myHelper = new MyDatabaseHelper(con, "techown.db",
					Constant.tdshDBVer);
			db = myHelper.getWritableDatabase(); // 获得数据库对象
			ContentValues values = new ContentValues();
			des = new DES();
			for (int i = 0; i < clomname.length; i++) {
				values.put(clomname[i], des.jiaMi(clomstr[i]));
			}

			long count = db.update("youhushangxian", values, "Id" + "=?",
					new String[] { id }); // 插入数据

			if (count == -1) {
				techown.shanghu.https.Log.Instance().WriteLog("团队：查询省upload4:");
			}
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：upload4:" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

	}

	// // 查询省
	// public String[] klQuery1(final DuanXinDialog duanXinDialog, String
	// clunname,
	// String tablename) {
	//
	// Context friendContext = null;
	// String[] str = null;
	// SQLiteDatabase sld=null;
	// Cursor cur=null;
	// try {
	// friendContext = duanXinDialog.createPackageContext("techown.login",
	// Context.CONTEXT_IGNORE_SECURITY);
	// myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
	// Constant.loginDBVer);
	//
	// Set<String> set = new HashSet<String>();
	// sld = myHelper.getWritableDatabase();
	// cur = sld.query(tablename, new String[] { clunname }, null,
	// null, null, null, null);
	// if (cur.getCount() != 0) {
	// while(cur.moveToNext()){
	// set.add(des.jieMI(cur.getString(cur.getColumnIndex(clunname))));
	// }
	// }
	// str = new String[set.size()];
	// str = set.toArray(str);
	//
	//
	// } catch (Exception e) {
	// techown.shanghu.https.Log.Instance().WriteLog("团队：查询省klQuery1:"+e.getMessage());
	// }finally{
	// if(cur!=null){
	// cur.close();
	// }
	// if(sld!=null){
	// sld.close();
	// }
	// }
	// return str;
	// }
	//
	// 查询省
	public List<TArea> klQuery(final Context con, String tablename) {
		List<TArea> tarea = new ArrayList<TArea>();
		Context friendContext = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;

		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			// Set<String> set = new HashSet<String>();
			sld = myHelper.getWritableDatabase();
			cur = sld.query(tablename, new String[] { "*" }, null, null, null,
					null, null);
			//

			if (cur.getCount() != 0) {
				for (cur.moveToFirst(); !(cur.isAfterLast()); cur.moveToNext()) {
					// set.add(cur.getString(cur.getColumnIndex(clunname)));
					TArea tArea = new TArea();
					tArea.id = des
							.jieMI(cur.getString(cur.getColumnIndex("Id")));
					tArea.name = des.jieMI(cur.getString(cur
							.getColumnIndex("Name")));
					tarea.add(tArea);
				}
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询省klQuery:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return tarea;
	}

	// 查询市
	public String[] cityQuery1(final Context con, String clunname,
			String where, String wherestr, String tablename) {
		Context friendContext = null;
		String[] str = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			int i = 0;
			sld = myHelper.getWritableDatabase();

			cur = sld.query(tablename, new String[] { clunname }, where + "=?",
					new String[] { des.jiaMi(wherestr) }, null, null, null);
			str = new String[cur.getCount()];
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					str[i] = des.jieMI(cur.getString(cur
							.getColumnIndex(clunname)));
					i++;
				}

			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：查询市cityQuery1:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return str;
	}

	public List<TCity> cityQuerycity(final Context con, String tablename,
			String where) {
		List<TCity> tC = new ArrayList<TCity>();
		Context friendContext = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);

			sld = myHelper.getWritableDatabase();

			String sql = "select * from " + tablename + " " + where;
			cur = sld.rawQuery(sql, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					TCity tcity = new TCity();

					tcity.id = des
							.jieMI(cur.getString(cur.getColumnIndex("Id")));
					tcity.name = des.jieMI(cur.getString(cur
							.getColumnIndex("Name")));
					tC.add(tcity);
				}

			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：cityQuerycity:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}
		return tC;
	}

	public String klQueryCity(Context con, String tablename, String where) {
		Context friendContext = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity:" + e.getMessage());
		}
		myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
				Constant.loginDBVer);
		SQLiteDatabase sld = null;
		String id = null;
		Cursor cur = null;
		try {

			sld = myHelper.getWritableDatabase();

			String sql = "select *  from " + tablename + " " + where;
			System.out.println(sql);
			cur = sld.rawQuery(sql, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					id = des.jieMI(cur.getString(cur
							.getColumnIndex("Id")));
					if (cur != null) {
						cur.close();
					}
					if (sld != null) {
						sld.close();
					}
				}
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return id;
	}
	
	public String SelectWhere(Context con, String tablename, String where,String selectid) {
		Context friendContext = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：SelectWhere:" + e.getMessage());
		}
		myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
				Constant.loginDBVer);
		SQLiteDatabase sld = null;
		String id = null;
		Cursor cur = null;
		try {

			sld = myHelper.getWritableDatabase();

			String sql = "select "+selectid+"  from " + tablename + " " + where;
			System.out.println(sql);
			cur = sld.rawQuery(sql, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					id = des.jieMI(cur.getString(cur
							.getColumnIndex(selectid)));
					if (cur != null) {
						cur.close();
					}
					if (sld != null) {
						sld.close();
					}
				}
			}
			System.out.println("SelectWhere:id==="+id);
		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return id;
	}
	

	public String klQuerytypecode(Context con, String tablename, String where) {
		Context friendContext = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity:" + e.getMessage());
		}
		myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
				Constant.loginDBVer);
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {

			sld = myHelper.getWritableDatabase();

			String sql = "select *  from " + tablename + " " + where;
			System.out.println(sql);
			cur = sld.rawQuery(sql, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					String id = des.jieMI(cur.getString(cur
							.getColumnIndex("typeName")));
					if (cur != null) {
						cur.close();
					}
					if (sld != null) {
						sld.close();
					}
					return id;
				}
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return "";
	}

	public String klQuerytypecode2(Context con, String tablename, String where) {
		Context friendContext = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity:" + e.getMessage());
		}
		myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
				Constant.loginDBVer);
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {

			sld = myHelper.getWritableDatabase();

			String sql = "select *  from " + tablename + " " + where;
			System.out.println(sql);
			cur = sld.rawQuery(sql, null);
			if (cur.getCount() != 0) {
				while (cur.moveToNext()) {
					String name = des.jieMI(cur.getString(cur
							.getColumnIndex("typeName")));
					if (cur != null) {
						cur.close();
					}
					if (sld != null) {
						sld.close();
					}
					return name;
				}
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return "";
	}

	public String klQueryCity1(Context con, String tablename, String where) {
		Context friendContext = null;
		String id = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);
			sld = myHelper.getWritableDatabase();

			String sql = "select *  from " + tablename + " " + where;
			// System.out.println(sql);
			cur = sld.rawQuery(sql, null);

			while (cur.moveToNext()) {
				id = des.jieMI(cur.getString(cur.getColumnIndex("Name")));
			}

		} catch (NameNotFoundException e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity1:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return id;
	}

	public String klQueryCity2(Context con, String tablename, String where) {
		Context friendContext = null;
		String id = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);
			sld = myHelper.getWritableDatabase();

			String sql = "select *  from " + tablename + " " + where;
			// System.out.println(sql);
			cur = sld.rawQuery(sql, null);

			while (cur.moveToNext()) {
				id = des.jieMI(cur.getString(cur.getColumnIndex("CityId")));
			}

		} catch (NameNotFoundException e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity1:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return id;
	}
	
	public String klQueryarear2(Context con, String tablename, String where) {
		Context friendContext = null;
		String id = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);
			sld = myHelper.getWritableDatabase();

			String sql = "select *  from " + tablename + " " + where;
			// System.out.println(sql);
			cur = sld.rawQuery(sql, null);

			while (cur.moveToNext()) {
				id = des.jieMI(cur.getString(cur.getColumnIndex("Name")));
			}

		} catch (NameNotFoundException e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity1:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return id;
	}
	
	
	public String klQueryCity11(Context con, String tablename, String where) {
		Context friendContext = null;
		String id = null;
		SQLiteDatabase sld = null;
		Cursor cur = null;
		try {
			friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			myHelper = new MyDatabaseHelper(friendContext, "techown_login.db",
					Constant.loginDBVer);
			sld = myHelper.getWritableDatabase();

			String sql = "select *  from " + tablename + " " + where;
			// System.out.println(sql);
			cur = sld.rawQuery(sql, null);

			while (cur.moveToNext()) {
				id = des.jieMI(cur.getString(cur.getColumnIndex("typeCode")));
			}

		} catch (NameNotFoundException e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：klQueryCity1:" + e.getMessage());
		} finally {
			if (cur != null) {
				cur.close();
			}
			if (sld != null) {
				sld.close();
			}
		}

		return id;
	}

	public boolean isDatabaseExists(Context context, String dbName) {

		File dbFile = context.getDatabasePath(dbName);
		if (dbFile.exists()) {
			return true;
		}
		return false;
	}
	
	//获取数据库版本号
	public int getLoginDBVer(String path){

    	File f=null;
//		  f= new File("/sdcard/TJB/ver/login.txt");//这是对应文件名
    	StringBuilder text = new StringBuilder();
    	 f= new File(path);//这是对应文件名
    	 if(f.exists())
    	 {
    		 InputStream in = null;
 			  try {
 			   in = new BufferedInputStream(new FileInputStream(f));
 			  } catch (FileNotFoundException e3) {
 			   // TODO Auto-generated catch block
 				 techown.shanghu.https.Log.Instance().WriteLog("数据库版本号读取异常");
 			  }
 			  BufferedReader br = null;
 			  try {
 			   br = new BufferedReader(new InputStreamReader(in, "gb2312"));
 			  } catch (UnsupportedEncodingException e1) {
 			   // TODO Auto-generated catch block
 				 techown.shanghu.https.Log.Instance().WriteLog("数据库版本号读取异常");
 			  }
 			  String tmp;
 			
 			  try {
 			   while((tmp=br.readLine())!=null){
 			    //在这对tmp操作
 				 text.append(tmp);
 		      
 			  }
 			   br.close();
 			   in.close();
 			  } catch (IOException e) {
 			   // TODO Auto-generated catch block
 				 techown.shanghu.https.Log.Instance().WriteLog("数据库版本号读取异常");
 			 
 			  } 
    	 }
    	 else
    	 {
    		 text.append("1"); 
    	 }
		return Integer.parseInt(text.toString());

    }
	//城市ID---〉城市名称
	public String queryCityname(Context con,String username) {
		String cityid = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select Name from t_city where Id = ?",
					new String[] { des.jiaMi(username) });
			if (cursor.getCount() != 0) {
				cursor.moveToLast();
				cityid = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryCityname出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}
		return cityid;
	}
	//城市名称--〉省ID
	public String queryShengname(Context con,String username) {
		String AreaId = "";
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery(
					"select ProvinceName from t_city where Name = ?",
					new String[] { des.jiaMi(username) });
			if (cursor.getCount() != 0) {
				cursor.moveToLast();
				AreaId = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryShengname出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return AreaId;
	}
	
	//行业名称---〉行业ID
	public String categoryid(Context con,String username) {
		String categoryid = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select Id from t_category where Name = ?",
					new String[] { des.jiaMi(username) });
			if (cursor.getCount() > 0) {
				cursor.moveToLast();
				categoryid = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryCityname出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}
		return categoryid;
	}
	
	
	//城市名称---〉城市ID
	public String queryCityid(Context con,String username) {
		String cityid = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select Id from t_city where Name = ?",
					new String[] { des.jiaMi(username) });
			if (cursor.getCount() > 0) {
				cursor.moveToLast();
				cityid = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryCityname出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}
		return cityid;
	}
	
	//省ID--〉省名称
	public String queryProvinceName(Context con,String AreaId) {

		String AreaName = null;
		Cursor cursor = null;
		SQLiteDatabase db1 = null;
		try {
			Context friendContext = con.createPackageContext("techown.login",
					Context.CONTEXT_IGNORE_SECURITY);
			MyDatabaseHelper myHelper = new MyDatabaseHelper(friendContext,
					"techown_login.db", Constant.loginDBVer);
			db1 = myHelper.getWritableDatabase();
			cursor = db1.rawQuery("select Name from t_province where Id = ?",
					new String[] { des.jiaMi(AreaId) });
			if (cursor.getCount() == 1) {
				cursor.moveToLast();
				AreaName = des.jieMI(cursor.getString(0));
			}

		} catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog(
					"团队：queryProvinceName出错" + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db1 != null) {
				db1.close();
			}
		}

		return AreaName;
	}
	
	
}
