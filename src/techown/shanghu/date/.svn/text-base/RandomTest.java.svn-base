package techown.shanghu.date;
import java.util.Random;


public class RandomTest {

	Random r = new Random();
	  String ssource = "0123456789";
	  char[] src = ssource.toCharArray();
	    //产生随机字符串
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

	    //调用该方法，产生随机字符串,
	    //参数i: 为字符串的长度
	   public String runVerifyCode(int i)
	    {
	            String VerifyCode = randString(i);
	            return VerifyCode;
	    }
}
