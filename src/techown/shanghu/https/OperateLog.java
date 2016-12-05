package techown.shanghu.https;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;


import techown.shanghu.DataCheckUtil;
import techown.shanghu.TuanDuiShangHuActivity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OperateLog extends Service implements Runnable{

	
	private static OperateLog _lg;
	String dateStr1,dateStr;
	//single 
	public static OperateLog Instance(){
		if(_lg == null){
			_lg = new OperateLog();
		}
		return _lg;
	}
	public static String identity;
	public static String filename;
	public static String uriAPI = "https://182.180.50.105:443/tjbproxy/proxy.do";
	Thread t;
	DataCheckUtil dcu = new DataCheckUtil();
	public void run() {
		techown.shanghu.https.Log.Instance().WriteLog("�̻��ŶӲ�����־����");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		if(t==null)
		{
			t=new Thread(this);
			t.start();	
		}
	}
	
	/**������־*/
	public void WriteLog(String msg){
		if(TuanDuiShangHuActivity.username!=null)
		{
			WriteLog1(msg);
			techown.shanghu.https.Log.Instance().WriteLog("Ա����=="+TuanDuiShangHuActivity.username+"����ֱ��д����־");
		}
		else
		{
			WriteLog1(msg);
			techown.shanghu.https.Log.Instance().WriteLog("Ա����=="+TuanDuiShangHuActivity.username+"������ֱ��д����־");
		}
	}
	/**������־*/
	public void WriteLog1(String msg){
		if(msg == null)
			return;
		System.out.println(msg);
		if(LOG_SWITCH){
			File file = createLogFile();
			if(file == null)
				return;
			FileOutputStream fos = null;
			try
			{
				fos = new FileOutputStream(file, true);
				
				SimpleDateFormat sdf = new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\"");
				String dateStr =sdf.format(new Date());
				System.out.println(dateStr);
				fos.write(("\""+TuanDuiShangHuActivity.username+"\"" + "," + dateStr + "," + "\""+msg+"\"").getBytes("gb2312"));
				
				fos.write("\r\n".getBytes("gb2312"));
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally{
				try
				{
					if(fos != null){
						fos.close();
						fos = null;
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				fos = null;
				file = null;
			}
		}
	}
	/**��־����·��*/
	private static final String LOG_SAVE_PATH = "/sdcard/TJB/log/"; //sd���ϵ���־�ļ�Ŀ¼
	/**��־����*/
	private static final boolean LOG_SWITCH = true;		
	
	/**��鵱ǰ���ڵ���־�ļ��Ƿ����*/
	private File createLogFile(){
		//if(!MemorySpaceManager.isSDExist()){  //sd ���Ƿ����
		//	return null;
		//}
		File file = new File(LOG_SAVE_PATH);
		if(!file.exists()){
			file.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateStr = "A_"+TuanDuiShangHuActivity.username+"_optionhis_"+dcu.dateChange(sdf.format(new Date()));
		file = new File(LOG_SAVE_PATH + dateStr + ".txt");
		if(!isLogExist(file)){
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		sdf = null;
		return file;
	}
	
	
	/**
	 * ��鵱����־�ļ��Ƿ����
	 * @param file
	 * @return
	 */
	private boolean isLogExist(File file){
		File tempFile = new File(LOG_SAVE_PATH);
		File[] files = tempFile.listFiles();
		for(int i = 0; i < files.length; i++){
			if(files[0].getName().trim().equalsIgnoreCase(file.getName())){
				return true;
			}
		}
		return false;
	}
	//����ǲ��ǵ������־�ļ�
	public boolean checkrizhifile()
	{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		//�ļ����ж��ļ�
		dateStr1 = "A_"+TuanDuiShangHuActivity.username+"_optionhis_"+dcu.dateChange(sdf1.format(new Date()))+".txt";
		System.out.println("dateStr=="+filename);
		System.out.println("dateStr1=="+dateStr1);
		if(dateStr1.equals(filename))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
