 package techown.shanghu.https;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log
{
	private static Log _lg;
	//single 
	public static Log Instance(){
		if(_lg == null){
			_lg = new Log();
		}
		return _lg;
	}
	
	//��־��������� ,Ĭ�ϱ������10�����־
	public int logSaveNum=10;
	
	/**������־*/
	public void WriteLog(String msg){
		if(LOG_SWITCH){
			File file = checkLogFileIsExist();
			if(file == null)
				return;
			FileOutputStream fos = null;
			try
			{
				fos = new FileOutputStream(file, true);
				fos.write((new Date().toLocaleString() + "	" + msg).getBytes("gbk"));
				fos.write("\r\n".getBytes("gbk"));
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
	/**
	 * ��ӡ�쳣��ջ��Ϣ
	 * @param e
	 * @return
	 */
	public void WriteStackTrace(Throwable e){
        if(e != null){
            //StringWriter sw = new StringWriter();
            //PrintWriter pw = new PrintWriter(sw);
            //e.printStackTrace(pw);
            //return sw.toString();           
            WriteLog("���ڱ���"+e.getStackTrace().toString());
        }
        //return "";
    }
	
	
	
	
	private Log()
	{
		CheckLogDele();
		
	}
	
	private void CheckLogDele()
	{
		//todo ɨ��sd���������־�ļ���ɾ������logSaveNum����־
		
	}
	
	/**��־����·��*/
	private static final String LOG_SAVE_PATH = "/sdcard/Log/"; //sd���ϵ���־�ļ�Ŀ¼
	/**��־����*/
	private static final boolean LOG_SWITCH = true;		
	
	/**�����־�ļ��Ƿ����*/
	private File checkLogFileIsExist(){
		//if(!MemorySpaceManager.isSDExist()){  //sd ���Ƿ����
		//	return null;
		//}
		File file = new File(LOG_SAVE_PATH);
		if(!file.exists()){
			file.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date());
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
		if(files != null){
			for(int i = 0; i < files.length; i++){
				if(files[0].getName().trim().equalsIgnoreCase(file.getName())){
					return true;
				}
			}
		}
		return false;
	}

}