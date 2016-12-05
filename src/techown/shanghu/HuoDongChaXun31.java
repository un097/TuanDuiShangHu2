package techown.shanghu;
/*
 * 活动查询--公司、门店等信息界面
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import techown.shanghu.date.DES;
import techown.shanghu.https.HttpConnection2;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class HuoDongChaXun31 extends Activity{
	Button button1;
	LinearLayout rightLinearLayout;
	ListView mainright_lv,lv;
	TextView textview7,textview8,tv9,tv10,tv11,tv12;
	public  List<String[]> list = new ArrayList<String[]>();
	public static String jsont,jsont1;
	EditDialog log=new EditDialog();
	//public  List<String[]> sendtList1 = new ArrayList<String[]>();
	String zhuti;
    String neirong;
    static String merid;
    String companyname;
    String mername;
    List<TextView> ls=new ArrayList<TextView>();
	List<TextView> ls1=new ArrayList<TextView>();
	Button btback;
	  protected String Jsonstr,Jsonstr1;

	private LinearLayout h3leout9,h3leout10;
	
	
	// 合约签订21公司信息
	
	public static TextView h1et7_21,h1et702_21,h1et703_21,h1et7021_21;//703区,7021详细地址
	public LinearLayout h1leout71_21,h1leout72_21,h1leout73_21,h1leout74_21;//73区，74详细地址
	int[] textIds21 = { R.id.h1et1, R.id.h1et2, R.id.h1et3, R.id.h1et4,
			R.id.h1et5, R.id.h1et6, R.id.h1et7021_21, R.id.h1et8, R.id.h1et9,
			R.id.h1et10, R.id.h1et11, R.id.h1et12, R.id.h1et13};
	public static TextView[] textArray21;
	int[] linlayIds21={R.id.h1leout1,R.id.h1leout2,R.id.h1leout3,R.id.h1leout4,
			R.id.h1leout5,R.id.h1leout6,R.id.h1leout7,R.id.h1leout8,
			R.id.h1leout9,R.id.h1leout10,R.id.h1leout11,R.id.h1leout12,
			R.id.h1leout13};
	public LinearLayout[] linlay21;
	
	
	// 合约签订31門店信息
	public TextView h1et702_31,h1et703_31;
	int[] textIds31 = { R.id.h2edtext1, R.id.h2edtext2, R.id.h2edtext3, R.id.h2edtext4,
			R.id.h2edtext5, R.id.h2edtext6, R.id.h2edtext7, R.id.h2edtext8, R.id.h2edtext9,
			R.id.h2edtext10, R.id.h2edtext11, R.id.h2edtext12, R.id.h2edtext13, R.id.h2edtext14,
			R.id.h2edtext15,R.id.h2edtext16,R.id.h2edtext17,R.id.h2edtext18,R.id.h2edtext19,R.id.h2edtext222,
			R.id.selectzhubei1,R.id.selectzhubei2,R.id.selectzhubei3,R.id.selectzhubei4,};
	public static TextView[] textArray31;

	int[] linlayIds31={R.id.h3leout1,R.id.h3leout2,R.id.h3leout3,R.id.h3leout4,
			R.id.h3leout5,R.id.h3leout6,R.id.h3leout7,R.id.h3leout8,
			R.id.h3leout9,R.id.h3leout10,R.id.h3leout11,
			R.id.h3leout13,R.id.h3leout14,R.id.h3leout15,R.id.h3leout16,R.id.h3leout17,
			R.id.h3leout18,R.id.h3leout19,R.id.selectzhubeiout1,R.id.selectzhubeiout2,
			R.id.selectzhubeiout3,R.id.selectzhubeiout4};
	public LinearLayout[] linlay31;
	
	//合约签订41廣宣信息
	
	int[] textIds41 = { R.id.h3edtext1, R.id.h3edtext2, R.id.h3edtext3, R.id.h3edtext4,
			R.id.h3edtext5, R.id.h3edtext6, R.id.h3edtext7, R.id.h3edtext8, R.id.h3edtext9,
			R.id.h3edtext10, R.id.h3edtext11, R.id.h3edtext12, R.id.h3edtext13, R.id.h3edtext14,
			R.id.h3edtext15};
	public static TextView[] textArray41;
	
	int[] linlayIds41={R.id.h4leout1,R.id.h4leout2,R.id.h4leout3,R.id.h4leout4,
			R.id.h4leout5,R.id.h4leout6,R.id.h4leout7,R.id.h4leout8,
			R.id.h4leout9,R.id.h4leout10,R.id.h4leout11,R.id.h4leout12,
			R.id.h4leout13,R.id.h4leout14,R.id.h4leout15};
	public LinearLayout[] linlay41;
	
	//合约签订51活動信息
	 public  RadioButton[] rbArray;//卡种
	 private RadioButton rb,rb1;//是否发送短信
		DataCheckUtil dc=new DataCheckUtil();
	int [] BdIds={R.id.baijin_radioButton,R.id.puka_radioButton,R.id.jinka_radioButton,
   		R.id.taipingyang_radioButton,R.id.taipingyang2_radioButton,R.id.other_radioButton
   };//卡种

	int[] textIds = { R.id.h4edtext1, R.id.h4edtext2, R.id.h4edtext3, R.id.h4edtext4,
			R.id.h4edtext5, R.id.h4edtext6, R.id.h4edtext7, R.id.h4edtext8,R.id.z2edtext5,
			R.id.z2edtext4,R.id.h4edtext66,R.id.selecth4edtext10,R.id.selectfabuis,
			R.id.selectzhubeia,R.id.selectzhubeib,R.id.selectzhubeic,R.id.selectzhubeid};
	public static TextView[] textArray51;
	//日期限制
	public RadioButton[] ZhirbArray;
	int[] ZhiRi = {R.id.selectzhiyi_radioButton,R.id.selectzhier_radioButton,R.id.selectzhisan_radioButton,
			R.id.selectzhisi_radioButton,R.id.selectzhiwu_radioButton,R.id.selectzhiliu_radioButton,
			R.id.selectzhiri_radioButton};
	//活动日
	public RadioButton[] HuorbArray;
	int[] HuoRi = {R.id.selecthuoyi_radioButton,R.id.selecthuoer_radioButton,R.id.selecthuosan_radioButton,
			R.id.selecthuosi_radioButton,R.id.selecthuowu_radioButton,R.id.selecthuoliu_radioButton,
			R.id.selecthuori_radioButton};
	
	
	
	
	
	
	
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.huodongchaxun22);
        super.onCreate(savedInstanceState);
   	 Intent it=getIntent();
     // String zhuti=it.getStringExtra("zhuti");
    //  String neirong=it.getStringExtra("neirong");
   	 merid=it.getStringExtra("merid");
       
       findId21(); 
       
       findId31(); 
       findId41(); 
       findId51();
       btback=(Button)findViewById(R.id.btback);
       back();
   
       try {
    	   Request();
    	   Request1();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		techown.shanghu.https.Log.Instance().WriteLog("HuoDongChaXun31类"+e.getMessage());
	}
      
  
	findId();
	
       
        
    }
    private void back() {
    	btback.setOnClickListener(new  OnClickListener() {
			
			public void onClick(View arg0) {
				Intent it=new Intent(HuoDongChaXun31.this,HuoDongChaXun21.class);
				startActivity(it);
				HuoDongChaXun31.this.finish();
				overridePendingTransition(R.anim.rightin, R.anim.rightout);	
			}
		});
		
	}
	// 合约签订21 的findID
    public void findId21(){
    	// 条码扫描按钮
    	
    	
    	h1et7_21 = (TextView) findViewById(R.id.h1et7_21);
		h1et702_21 = (TextView) findViewById(R.id.h1et702_21);
		h1et703_21 = (TextView) findViewById(R.id.h1et703_21);
		h1et7021_21 = (TextView) findViewById(R.id.h1et7021_21);
		h1leout71_21 = (LinearLayout)findViewById(R.id.h1leout71_21);//省
		h1leout72_21 = (LinearLayout)findViewById(R.id.h1leout72_21);//市
		h1leout73_21 = (LinearLayout)findViewById(R.id.h1leout73_21);//区
		h1leout74_21 = (LinearLayout)findViewById(R.id.h1leout74_21);//注册地址
    	
    	
		linlay21=new LinearLayout[linlayIds21.length];
		for(int i = 0; i<linlayIds21.length;i++){
			linlay21[i]=(LinearLayout)findViewById(linlayIds21[i]);
		}

		textArray21 = new TextView[textIds21.length];
		for (int i = 0; i < textIds21.length; i++) {
			textArray21[i] = (TextView) findViewById(textIds21[i]);
		}
	
		
    	
    }
    // 合约签订31 的findID
    public void findId31(){
    	
    	linlay31=new LinearLayout[linlayIds31.length];
		for(int i = 0; i<linlayIds31.length;i++){
			linlay31[i]=(LinearLayout)findViewById(linlayIds31[i]);
		}
		
        textArray31 = new TextView[textIds31.length];
		for (int i = 0; i < textIds31.length; i++) {
			textArray31[i] = (TextView) findViewById(textIds31[i]);
		}
		
	
		h1et702_31=(TextView)findViewById(R.id.h1et702_31);
		h1et703_31=(TextView)findViewById(R.id.h1et703_31);


		
		
		
		
		
		
		
		
		
		
    	
    	
    }
    // 合约签订41 的findID
    public void findId41(){
    	
    	 linlay41=new LinearLayout[linlayIds41.length];
 		for(int i = 0; i<linlayIds41.length;i++){
 			linlay41[i]=(LinearLayout)findViewById(linlayIds41[i]);
 		}
 		
         textArray41 = new TextView[textIds41.length];
 		for (int i = 0; i < textIds41.length; i++) {
 			textArray41[i] = (TextView) findViewById(textIds41[i]);
 		}
    	
 		
 		
    }
    // 合约签订51 的findID
    public void findId51(){
    	 rb=(RadioButton)findViewById(R.id.youKa_radioButton);
         rb1=(RadioButton)findViewById(R.id.noKa_radioButton);
        rb.setEnabled(false);
        rb1.setEnabled(false);
 		
         textArray51 = new TextView[textIds.length];
 		for (int i = 0; i < textIds.length; i++) {
 			textArray51[i] = (TextView) findViewById(textIds[i]);
 		}
 		//卡种
 		rbArray=new   RadioButton[BdIds.length];
 		for (int i = 0; i < BdIds.length; i++) {
 			rbArray[i] = (RadioButton)findViewById(BdIds[i]);
 			rbArray[i].setEnabled(false);
 		}
 		//日期限制
 		ZhirbArray=new   RadioButton[ZhiRi.length];
 		for (int i = 0; i < ZhiRi.length; i++) {
 			ZhirbArray[i] = (RadioButton)findViewById(ZhiRi[i]);
 			ZhirbArray[i].setEnabled(false);
 		}//活动日
 		HuorbArray=new   RadioButton[HuoRi.length];
 		for (int i = 0; i < HuoRi.length; i++) {
 			HuorbArray[i] = (RadioButton)findViewById(HuoRi[i]);
 			HuorbArray[i].setEnabled(false);
 		}
 		
    }
    
    public void findId(){
    	 Intent it=getIntent();
        // String zhuti=it.getStringExtra("zhuti");
       //  String neirong=it.getStringExtra("neirong");
    //  merid=it.getStringExtra("merid");
         String companyname=it.getStringExtra("comepanyName");
         String mername=it.getStringExtra("merName");
         
         
        
         textview7=(TextView)findViewById(R.id.textView7);
         textview8=(TextView)findViewById(R.id.textView8);
         tv9=(TextView)findViewById(R.id.textView9);
         tv10=(TextView)findViewById(R.id.textView10);
         tv11=(TextView)findViewById(R.id.textView11);
         tv12=(TextView)findViewById(R.id.textView12);
         textview7.setText(HuoDongChaXun.zhuti);
         textview8.setText(HuoDongChaXun.neirong);
         tv9.setText(mername);
         //tv10.setText(companyname);
         tv10.setText(textArray21[0].getText().toString());
         tv11.setText(mername);
         tv12.setText(HuoDongChaXun.msg1);
    }
  
    
    
    DBUtil db =new DBUtil(); 
    public void Request() throws Exception {

		
		
		try
		{
			JSONObject demoJson1 = new JSONObject(jsont);
			
			if(demoJson1.getString("rspCode").equals("00"))
			{
//				JSONArray numberList1 =demoJson1.getJSONArray("detail");
//				for(int j=0;j<numberList1.length();j++)
//	       		{
					//JSONObject datajson = (JSONObject)numberList1.opt(j);
					String[] tempstr=new String[17];
					tempstr[0]=demoJson1.getString("name");//公司名
					tempstr[1]=demoJson1.getString("legalPerson");//法人代表
					tempstr[2]=demoJson1.getString("contactPerson");//联系人
					tempstr[3]=demoJson1.getString("contactTel");//电话
					tempstr[4]=demoJson1.getString("handPhone");//手机
					tempstr[5]=demoJson1.getString("busiName");//经营门店名称
					tempstr[6]=demoJson1.getString("province");//省市区
					tempstr[7]=demoJson1.getString("city");
					tempstr[8]=demoJson1.getString("area");
					tempstr[9]=demoJson1.getString("address");//注册地址
					tempstr[10]=demoJson1.getString("zip");//邮编
					tempstr[11]=demoJson1.getString("busi");//经营范围
					tempstr[12]=demoJson1.getString("brand");//品牌
					tempstr[13]=demoJson1.getString("brandHotLine");//品牌热线
					tempstr[14]=demoJson1.getString("validDate");//有效期
					tempstr[15]=demoJson1.getString("busiYear");//经营年限
					tempstr[16]=demoJson1.getString("validDate");//条形码
					//sendtList1.add(tempstr);
					textArray21[0].setText(tempstr[0]);
					textArray21[1].setText(tempstr[1]);
					textArray21[2].setText(tempstr[2]);
					textArray21[3].setText(tempstr[3]);
					textArray21[4].setText(tempstr[4]);
					textArray21[5].setText(tempstr[5]);
					
					
					DES des=new DES();
//					h1et7021_21.setText(tempstr[9]);

					String name=db.klQueryCity1(this, "t_province", "where Id = '"
					+des.jiaMi(tempstr[6]) + "'");
					h1et7_21.setText(name);

					String city=db.klQueryCity1(this, "t_city", "where Id = '"
					+des.jiaMi(tempstr[7]) + "'");
					h1et702_21.setText(city);

					String area=db.klQueryCity1(this, "t_district", "where Id = '"
					+ des.jiaMi(tempstr[8]) + "'");
					h1et703_21.setText(area);
					
					textArray21[6].setText(tempstr[9]);
					textArray21[7].setText(tempstr[10]);
					textArray21[8].setText(tempstr[11]);
					textArray21[9].setText(tempstr[12]);
					textArray21[10].setText(tempstr[13]);//品牌热线
					textArray21[11].setText(tempstr[14]);//有效期
					textArray21[12].setText(tempstr[15]);//经营年限
					textArray21[12].setText("123");//经营年限
					
	       		//} 
			}
			else 
			{
				//Toast.makeText(HuoDongChaXun31.this,demoJson1.getString("rspInfo"),Toast.LENGTH_LONG).show();
			log.Toast(HuoDongChaXun31.this,demoJson1.getString("rspInfo"));
			}
			
		}catch(Exception e)
		{
			techown.shanghu.https.Log.Instance().WriteLog("HuoDongChaXun31类Reques（）"+e.getMessage());
		}
	}
public void Request1() throws Exception {

	//3634b0c037792f1901377ca3da9e025c

	try
	{
		JSONObject demoJson1 = new JSONObject(jsont1);
		
		if(demoJson1.getString("rspCode").equals("00"))
		{
//			JSONArray numberList1 =demoJson1.getJSONArray("detail");
//			for(int j=0;j<numberList1.length();j++)
//       		{
				//JSONObject datajson = (JSONObject)numberList1.opt(j);
				String[] tempstr=new String[59];
				tempstr[0]=demoJson1.getString("merId");//门店ID
				tempstr[1]=demoJson1.getString("merName");//门店名
				tempstr[2]=demoJson1.getString("merTel");//营业电话
				tempstr[3]=demoJson1.getString("merAddress");//营业地址
				tempstr[4]=demoJson1.getString("zip");//邮编
				tempstr[5]=demoJson1.getString("industry");//行业
				tempstr[6]=demoJson1.getString("din");//菜系
				tempstr[7]=demoJson1.getString("busiGroup");//商圈
				tempstr[8]=demoJson1.getString("misId");//商户编号
				tempstr[9]=demoJson1.getString("terId");//终端号
				tempstr[10]=demoJson1.getString("longitude");//经度
				tempstr[11]=demoJson1.getString("latitude");//纬度
				tempstr[12]=demoJson1.getString("contacter");//联系人
				tempstr[13]=demoJson1.getString("handPhone");//手机
				tempstr[14]=demoJson1.getString("merLicences");//门店工商执照？
				tempstr[15]=demoJson1.getString("devChn");//拓展渠道
				tempstr[16]=demoJson1.getString("isReceipt");//是否收单
				tempstr[17]=demoJson1.getString("valiTime");//有效期
				tempstr[18]=demoJson1.getString("receiptFee");//收费单率
				tempstr[19]=demoJson1.getString("areaId");//行政区
				tempstr[20]=demoJson1.getString("childType");//子行业
				
				tempstr[21]=demoJson1.getString("parkNo");//停车位
				tempstr[22]=demoJson1.getString("busiTime");//营业时间
				tempstr[23]=demoJson1.getString("chairNo");//椅套
				tempstr[24]=demoJson1.getString("areaNo");//面积
				tempstr[25]=demoJson1.getString("traffic");//交通情况
				tempstr[26]=demoJson1.getString("deskCard");//台卡
				tempstr[27]=demoJson1.getString("rollUpNo");//易拉宝
				tempstr[28]=demoJson1.getString("doorLabel");//门贴
				tempstr[29]=demoJson1.getString("poster");//海报
				tempstr[30]=demoJson1.getString("comContent");//公司介绍
				tempstr[31]=demoJson1.getString("brandContent");//品牌介绍
				tempstr[32]=demoJson1.getString("merContent");//门店介绍
				tempstr[33]=demoJson1.getString("dishes");//招牌菜
				tempstr[34]=demoJson1.getString("perFee");//人均消费
				tempstr[35]=demoJson1.getString("misc");//其他
				
				tempstr[36]=demoJson1.getString("signDate");//签约日期
  				tempstr[37]=demoJson1.getString("startDate");//优惠开始时期
  				tempstr[38]=demoJson1.getString("isContinue");//是否续约
  				tempstr[39]=demoJson1.getString("endDate");//优惠结束时期
  				tempstr[40]=demoJson1.getString("isMessage");//是否短信
  				tempstr[41]=demoJson1.getString("cardType");	//第一位‘1’为白金卡，‘0’为非白金卡
											     				//第二位‘1’为普卡，‘0’为非普卡
											     				//第三位‘1’为金卡，‘0’为非金卡
											     				//第四位‘1’为所有太平洋信用卡，‘0’为非所有太平洋信用卡
											     				//第五位‘1’为所有太平洋卡，‘0’为非所有太平洋卡卡
											     				//第六位‘1’为其他卡种，‘0’为非其他卡种
											     				//例如：选择白金和普卡则为：“110000”，选中所有卡未“111111

  				tempstr[42]=demoJson1.getString("messageContent");//短信内容
  				tempstr[43]=demoJson1.getString("saleContent");//优惠内容
  				tempstr[44]=demoJson1.getString("specList");//特别约定
  				tempstr[45]=demoJson1.getString("actRepeop");//活动维护人工号
				tempstr[46]=demoJson1.getString("actDevUser");//活动拓展人工号
				//十二月份需求
				tempstr[47]=demoJson1.getString("remarks1");//商户注备1
				tempstr[48]=demoJson1.getString("remarks2");//商户注备2
				tempstr[49]=demoJson1.getString("remarks3");//商户注备3
				tempstr[50]=demoJson1.getString("remarks4");//商户注备4
				tempstr[51]=demoJson1.getString("remarksA");//活动注备A
				tempstr[52]=demoJson1.getString("remarksB");//活动注备B
				tempstr[53]=demoJson1.getString("remarksC");//活动注备C
				tempstr[54]=demoJson1.getString("remarksD");//活动注备D
				tempstr[55]=demoJson1.getString("isPublish");//是否发布
				tempstr[56]=demoJson1.getString("amtLimit");//金额限制
				tempstr[57]=demoJson1.getString("dateLimit");//日期限制
				tempstr[58]=demoJson1.getString("activeDate");//活动日
				

			
  				h1et702_31.setText(h1et702_21.getText().toString());
  				h1et703_31.setText(h1et703_21.getText().toString());
				textArray31[0].setText(textArray21[0].getText().toString());
				textArray31[1].setText(tempstr[1]);
				textArray31[2].setText(tempstr[2]);
				textArray31[3].setText(tempstr[3]);
				textArray31[4].setText(tempstr[4]);
				textArray31[5].setText(tempstr[5]);//行业
				textArray31[6].setText(tempstr[6]);//菜系
				textArray31[7].setText(tempstr[7]);//商圈
				String misid=tempstr[8];
				String terid=tempstr[9];
				init1(misid, terid);
				//textArray31[8].setText(tempstr[8]);
				//textArray31[9].setText(tempstr[9]);
				textArray31[10].setText(tempstr[10]);
				textArray31[11].setText(tempstr[11]);
				textArray31[12].setText(tempstr[12]);
				textArray31[13].setText(tempstr[13]);
				textArray31[14].setText(tempstr[14]);
				textArray31[15].setText(tempstr[15]);
				textArray31[16].setText(tempstr[16]);
				textArray31[17].setText(tempstr[17]);
				textArray31[18].setText(tempstr[18]);
				h1et703_31.setText(tempstr[19]);
				textArray31[19].setText(tempstr[20]);
				textArray31[20].setText(tempstr[47]);//注备1
				textArray31[21].setText(tempstr[48]);//注备2
				textArray31[22].setText(tempstr[49]);//注备3
				textArray31[23].setText(tempstr[50]);//注备4
				DES des=new DES();
				String id = db.klQueryCity1(this, "t_category",//行业ID转换
						"where Id = '" + des.jiaMi(textArray31[5].getText().toString())
								+ "'");
				textArray31[5].setText(id);
				String id1 = db.klQueryCity1(this, "t_childtyp",//菜系ID转换
						"where Id = '" + des.jiaMi(textArray31[6].getText().toString())
								+ "'");
				textArray31[6].setText(id1);
				String id2 = db.klQueryCity1(this, "t_trading",//商圈ID转换
						"where Id = '" + des.jiaMi(textArray31[7].getText().toString())
								+ "'");
				textArray31[7].setText(id2);
				String id3 = db.klQueryCity1(this, "t_category",//子行业ID转换
						"where Id = '" + des.jiaMi(textArray31[19].getText().toString())
								+ "'");
				textArray31[19].setText(id3);

				String id4 = db.klQueryCity1(this, "t_district",//地区ID转换
						"where Id = '" + des.jiaMi(h1et703_31.getText().toString())
								+ "'");
				
				String meninfocityid = db.klQueryCity2(this,"t_district", "where Id = '" + des.jiaMi(h1et703_31.getText().toString())
								+ "'");
				String meninfocityname = db.queryCityname(this,meninfocityid); 
				h1et703_31.setText(id4);
				h1et702_31.setText(meninfocityname);
				
				if (textArray31[15].getText().toString().equals("W")) {
					textArray31[15].setText("外包");
				} else if (textArray31[15].getText().toString().equals("D")) {
					textArray31[15].setText("办事处");
				} else if (textArray31[15].getText().toString().equals("B")) {
					textArray31[15].setText("分行");
				} else if (textArray31[15].getText().toString().equals("C")) {
					textArray31[15].setText("卡中心");
				}

				if (textArray31[16].getText().toString().equals("Y")) {
					textArray31[16].setText("是");
				} else if (textArray31[16].getText().toString().equals("N")) {
					textArray31[16].setText("否");
				}
				
				textArray41[0].setText(tempstr[21]);
				textArray41[1].setText(tempstr[22]);
				textArray41[2].setText(tempstr[23]);
				textArray41[3].setText(tempstr[24]);
				textArray41[4].setText(tempstr[25]);
				textArray41[5].setText(tempstr[26]);
				textArray41[6].setText(tempstr[27]);
				textArray41[7].setText(tempstr[28]);
				textArray41[8].setText(tempstr[29]);
				textArray41[9].setText(tempstr[30]);
				textArray41[10].setText(tempstr[31]);
				textArray41[11].setText(tempstr[32]);
				textArray41[12].setText(tempstr[33]);
				textArray41[13].setText(tempstr[34]);
				textArray41[14].setText(tempstr[35]);
				
				//textArray51[0].setText(tempstr[])
				String[] actDevUser = tempstr[46].split("\\*");//拓展
				String[] actRepeop = tempstr[45].split("\\*");//维护
				textArray51[0].setText(actDevUser[0]);//拓展人工号
				textArray51[2].setText(actRepeop[0]);//维护人工号
				textArray51[1].setText(actDevUser[1]);//拓展人姓名
				textArray51[3].setText(actRepeop[1]);//维护人姓名
				
				textArray51[4].setText(tempstr[36]);
				textArray51[5].setText(tempstr[37]);
				if(tempstr[38].equals("N")){
					textArray51[6].setText("否");
				}else {
					textArray51[6].setText("是");
				}
				
				
				textArray51[7].setText(tempstr[39]);
				if(tempstr[40].equals("Y")){
					rb.setChecked(true);
					
				}
				if(tempstr[40].equals("N")){
					rb1.setChecked(true);
					
				}
				//卡种
				String [] card2=tempstr[41].split("");
				for(int i=1;i<card2.length;i++){
					
				
					if(card2[i].equals("1")){
						rbArray[i-1].setChecked(true);
						
				      
					}
					else if(card2[i].equals("0")){
						rbArray[i-1].setChecked(false);
						
				      
					}
				}
				//日期限制
				String [] ZhiRi2=tempstr[57].split("");
				for(int i=1;i<ZhiRi2.length;i++){
					
				
					if(ZhiRi2[i].equals("1")){
						ZhirbArray[i-1].setChecked(true);
						
				      
					}
					else if(ZhiRi2[i].equals("0")){
						ZhirbArray[i-1].setChecked(false);
						
				      
					}
				}
				//活动日
				String [] HuoRi2=tempstr[58].split("");
				for(int i=1;i<HuoRi2.length;i++){
					
				
					if(HuoRi2[i].equals("1")){
						HuorbArray[i-1].setChecked(true);
						
				      
					}
					else if(HuoRi2[i].equals("0")){
						HuorbArray[i-1].setChecked(false);
						
				      
					}
				}
				textArray51[8].setText(tempstr[42]);//短信内容
				textArray51[9].setText(tempstr[43]);//优惠内容
				textArray51[10].setText(tempstr[44]);//特别约定
				textArray51[11].setText(tempstr[56]);//金额限制
				if(tempstr[55].equals("Y"))
				{
					textArray51[12].setText("是");//是否发布
				}
				else if(tempstr[55].equals("N"))
				{
					textArray51[12].setText("否");//是否发布
				}
				else if(tempstr[55].equals("S"))
				{
					textArray51[12].setText("暂缓");//是否发布
				}
				else
				{
					textArray51[12].setText("");//是否发布
				}
				textArray51[13].setText(tempstr[51]);//注备A
				textArray51[14].setText(tempstr[52]);//注备B
				textArray51[15].setText(tempstr[53]);//注备C
				textArray51[16].setText(tempstr[54]);//注备D
				
       		//} 
		}
		else 
		{
			//Toast.makeText(HuoDongChaXun31.this,demoJson1.getString("rspInfo"),Toast.LENGTH_LONG).show();
			log.Toast(HuoDongChaXun31.this,demoJson1.getString("rspInfo"));
		}
	}catch(Exception e)
	{
		techown.shanghu.https.Log.Instance().WriteLog("HuoDongChaXun31类"+e.getMessage());
	}
}
private void init1(String misId,String terId) {
	try{
	h3leout9 = (LinearLayout) findViewById(R.id.h3leout9);
	h3leout10 = (LinearLayout) findViewById(R.id.h3leout10);

	

		
	
					String[] arrg = misId.split("\\|");;//商户mis号
					textArray31[8].setText(arrg[0]);
			    	for (int i = 1; i < arrg.length; i++) {
			    		
			    		View view = LayoutInflater.from(HuoDongChaXun31.this).inflate(
								R.layout.cim_layout_list6, null);
						view.setLayoutParams(new LinearLayout.LayoutParams(
								LayoutParams.FILL_PARENT, 50));
						h3leout9.addView(view);
						TextView tv=(TextView)view.findViewById(R.id.h2edtext9);
						ls.add(tv);
						ls.get(i-1).setText(arrg[i]);
			    	}

					String[] errg = terId.split("\\|");//终端号
			    	textArray31[9].setText(errg[0]);
			    	for (int i = 1; i < errg.length; i++) {
			    		
			    		View view = LayoutInflater.from(HuoDongChaXun31.this).inflate(
								R.layout.cim_layout_list5, null);
						view.setLayoutParams(new LinearLayout.LayoutParams(
								LayoutParams.FILL_PARENT, 50));
						h3leout10.addView(view);
						TextView tv=(TextView)view.findViewById(R.id.h2edtext10);
						ls1.add(tv);
						ls1.get(i-1).setText(errg[i]);
			    	}
	}catch(Exception e){
		techown.shanghu.https.Log.Instance().WriteLog("HuoDongChaXun31类init1()"+e.getMessage());
	}
	
}
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			log.ExitApp(HuoDongChaXun31.this);
			break;

		}
		return super.onKeyDown(keyCode, event);
	}  	  
}	

   

