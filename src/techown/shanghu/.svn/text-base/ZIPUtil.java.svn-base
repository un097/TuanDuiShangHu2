package techown.shanghu;

import java.io.BufferedOutputStream;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;
import java.util.Enumeration;
  
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;  
  
public class ZIPUtil {  
      
    /** 
     *  
     * @param inputFileName 输入一个文件夹 
     * @param zipFileName   输出一个压缩文件夹，打包后文件名字 
     * @throws Exception 
     */  
    public static void  zip(String inputFileName, String zipFileName,String base) throws Exception {  
        System.out.println(zipFileName);  
        zip(zipFileName, new File(inputFileName),base);  
    }  
  
    private static void  zip(String zipFileName, File inputFile,String base) throws Exception {  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
                zipFileName));  
        zip(out, inputFile,base);  
        System.out.println("zip done");  
        out.close();  
    }  
  
    private static void zip(ZipOutputStream out, File f, String base) throws Exception {  
        if (f.isDirectory()) {  //判断是否为目录   
            File[] fl = f.listFiles();  
            out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));  
            base = base.length() == 0 ? "" : base + "/";  
            for (int i = 0; i < fl.length; i++) {  
                zip(out, fl[i], base + fl[i].getName());  
            }  
        } else {                //压缩目录中的所有文件   
            out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));  
            FileInputStream in = new FileInputStream(f);  
            int b;  
            System.out.println(base);  
            while ((b = in.read()) != -1) {  
                out.write(b);  
            }  
            in.close();  
        }  
    }
    
    /** 
     *  解压
     * @param inputFileName 输入一个压缩文件夹 
     * @param outputFileName   输出一个文件夹 
     * @throws Exception 
     */ 
    
    public static void unzip(String zipFile, String outputFileName) throws Exception {   
    	  ZipFile zip = new ZipFile(zipFile);   
    	  Enumeration<ZipEntry> en = zip.getEntries();   
    	  ZipEntry entry = null;   
    	  byte[] buffer = new byte[1024];   
    	  int length = -1;   
    	  InputStream input = null;   
    	  BufferedOutputStream bos = null;   
    	  File file = null;   
    	  
    	  while (en.hasMoreElements()) {   
    	   entry = (ZipEntry) en.nextElement();   
    	   if (entry.isDirectory()) {   
    	    file = new File(outputFileName, entry.getName());   
    	    if (!file.exists()) {   
    	     file.mkdir();   
    	    }   
    	    continue;   
    	   }   
    	  
    	   input = zip.getInputStream(entry);   
    	   file = new File(outputFileName, entry.getName());   
    	   if (!file.getParentFile().exists()) {   
    	    file.getParentFile().mkdirs();   
    	   }   
    	   bos = new BufferedOutputStream(new FileOutputStream(file));   
    	  
    	   while (true) {   
    	    length = input.read(buffer);   
    	    if (length == -1)   
    	     break;   
    	    bos.write(buffer, 0, length);   
    	   }   
    	   bos.close();   
    	   input.close();   
    	  }   
    	  zip.close();   
    	 } 
}