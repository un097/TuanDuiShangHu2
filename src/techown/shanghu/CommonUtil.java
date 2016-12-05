package techown.shanghu;

import java.util.List;

public class CommonUtil {

	/**将list转换成字符串数组
	 * @param list :List集合
	 * @return String[] : 字符串数组
	 */
	public String[] listToStringArray(List list){
		int length = list.size();
		String[] str = new String[length] ;
		if(length>=1){
			for(int i=0; i<length; i++){
				str[i] = (String)list.get(i);
			}
		}
		return str;
	}
}
