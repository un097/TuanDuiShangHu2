package techown.shanghu.date;
import java.util.Random;


public class RandomTest {

	Random r = new Random();
	  String ssource = "0123456789";
	  char[] src = ssource.toCharArray();
	    //��������ַ���
	    public String randString (int length)
	    {
	            char[] buf = new char[length];
	            int rnd;
	            for(int i=0;i<length;i++)
	            {
	                    rnd = Math.abs(r.nextInt()) % src.length;

	                    buf[i] = src[rnd];
	            }
	            return new String(buf);
	    }

	    //���ø÷�������������ַ���,
	    //����i: Ϊ�ַ����ĳ���
	   public String runVerifyCode(int i)
	    {
	            String VerifyCode = randString(i);
	            return VerifyCode;
	    }
}
