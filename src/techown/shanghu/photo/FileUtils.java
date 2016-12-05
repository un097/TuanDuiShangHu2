package techown.shanghu.photo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Environment;

public class FileUtils {
	private String SDPATH;

	public String getSDPATH() {
		return SDPATH;
	}
	
	public FileUtils() {
		//�õ���ǰ�ⲿ�洢�豸��Ŀ¼
		SDPATH = Environment.getExternalStorageDirectory() + "/";
	}
	/*
	 * ��sd���ϴ����ļ�
	 */
	public File createSDFILE(String fileName) throws IOException {
		File file = new File(SDPATH + fileName);
		file.createNewFile();		
		return file;
	}
	/*
	 * ��sd���ϴ���Ŀ¼
	 */
	public File createSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		dir.mkdirs();
		return dir;
	}
	public boolean isFileExist(String fileName) {
		File file = new File(SDPATH+fileName);
		return file.exists();
	}
	/*
	 * ��һ��Inputstream�������д��sd����
	 */
	public File write2SDFromInput(String path, String fileName, InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			createSDDir(path);
			file = createSDFILE(path+fileName);
			output = new FileOutputStream(file);
			byte buffer [] = new byte[4*1024];
			int tmp;	
			while ((tmp=(input.read(buffer))) != -1) {
				output.write(buffer,0,tmp);
			}	
			output.flush();
		}catch (Exception e) {
			techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�FileUtils:"+e.getMessage());
		}
		finally {
			try {
				output.close();
			}
			catch(Exception e) {
				techown.shanghu.https.Log.Instance().WriteLog("�Ŷ�FileUtils:"+e.getMessage());
			}
		}
		return file;
	}

	public boolean deleteSDFILE(String fileName) {
		boolean isDelete = false;
		if (isFileExist(fileName)){
			File file = new File(SDPATH + fileName);
			isDelete = file.delete();
		}
		return isDelete;
	}

}
