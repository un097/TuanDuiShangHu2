package techown.shanghu;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class CrashHandler implements UncaughtExceptionHandler{

	private Thread.UncaughtExceptionHandler mDefaultHandler;
	private static CrashHandler INSTANCE;
	private Context mContext;
	
	private CrashHandler(){}
	public static CrashHandler getInstance(){
		if(INSTANCE == null){
			INSTANCE = new CrashHandler();
		}
		return INSTANCE;
	}
	
	public void init(Context ctx){
		mContext = ctx;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}
	
	
	
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		if(!handleException(ex) && mDefaultHandler!=null){
			mDefaultHandler.uncaughtException(thread, ex);
		}
		else{
			try{
				Thread.sleep(2000);
			}catch(Exception e){
			}
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(10);
		}
	}
	
	private boolean handleException(Throwable ex){
		if(ex == null){
			return false;
		}
		
		saveCrashInfoToFile(ex, mContext);
		return true;
	}
	
	
	public void saveCrashInfoToFile(Throwable ex, Context ctx){
		
		try{
			String result = null;
			
			Writer info = new StringWriter();
			PrintWriter printWrite = new PrintWriter(info);
			ex.printStackTrace(printWrite);
			
			Throwable cause = ex.getCause();
			while(cause != null){
				cause.printStackTrace(printWrite);
				cause = cause.getCause();
			}
			
			
			PackageManager pm = ctx.getPackageManager();
			
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
			
			if(pi != null){
				result = "�������! ������: "+pi.applicationInfo.loadLabel(pm).toString()+",  �汾��:��" + pi.versionName+", " + info.toString();
				techown.shanghu.https.Log.Instance().WriteLog(result);
			}
			
			printWrite.close();
		}
		catch(Exception e){
			
		}
		
	}
	
}
