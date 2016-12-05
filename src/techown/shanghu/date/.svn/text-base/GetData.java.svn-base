package techown.shanghu.date;


import techown.shanghu.DBUtil;


import android.content.Context;

public class GetData {
	public static String[] getka=new String[3];
	
	public String[]getCity11(Context con,String province)
	{
		DBUtil DB=new DBUtil();
		String strcity[]=null;
		String[] str=DB.cityQuery(con,"Id", "Name", province, "t_city");
		if(str!=null&&str.length>0)
		{
			strcity=DB.cityQuery(con,"Name", "CityId", str[0], "t_district");	
		}
		
		return strcity;		
	}
	/**
	 * 根据城市Id获取城市下面所含的行政区
	 */
	public String[]getCity12(Context con,String cityId)
	{
		DBUtil DB=new DBUtil();
		String strcity[]=null;
		//String[] str=DB.cityQuery(con,"Id", "Name", province, "t_city");
		//if(str!=null&&str.length>0)
		//{
			strcity=DB.cityQuery(con,"Name", "CityId", cityId, "t_district");	
		//}
		
		return strcity;		
	}
	
	public static String[]getCity(Context con,String province)
	{
		DBUtil DB=new DBUtil();
		String strcity[]=DB.cityQuery1(con,"Id", "Name", province, "t_area");
		String str="";
		if(strcity!=null&&strcity.length!=0){
			 str=strcity[0];
		}
		
		
		return strcity;		
	}
	
	public  String[]getCity1(Context con,String province)
	{
		DBUtil DB=new DBUtil();
		String str=DB.cityQuery(con,"Id", "Name", province, "t_province")[0];
		String strcity[]=DB.cityQuery(con,"Name", "ProvinceName", str, "t_city");
		return strcity;		
	}
	
	public  String[]getProfession(Context con,String industry)
	{
		DBUtil DB=new DBUtil();
		String str[]=DB.cityQuery(con,"industryId", "industry", industry, "industry");
		String profession[]=DB.cityQuery(con,"profession", "industryId", str[0], "profession");
		return profession;				
	}
	
	public  String[]getPosition(Context con,String profession)
	{
		DBUtil DB=new DBUtil();
		String str[]=DB.cityQuery(con,"professionId", "profession", profession, "profession");
		
		String position[]=DB.cityQuery(con,"position","professionId", str[0], "position");
		return position;
	}
	
//	public static String getDytj(Context con)
//	{
//		String str[]=DBUtil.lydmQuery(con, new String[]{"servicecode"}, "business_code",Constant.ka, "typeproduct");
//		String dytj=str[0];
//		return dytj;
//		
//	}
}
