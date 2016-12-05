package techown.shanghu;

import java.io.File;

import techown.shanghu.photo.GongDanTakePhotoActivity;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	public final String CREATE_TABLE = "create table t2(id integer, name text)";
	public static final String TALBE_NAME = "st_storeaudit";
	public static final String ID = "id";

	//发件箱
	public static final String SEND="send";//表名
	public static final String SEND_BarcodeNo="sendbarcodeno";//发件箱序列号0
	public static final String BEGINNUM="beginnum";//起始段数1
	public static final String TOTALNUM="totalnum";//总段数2
	public static boolean delectfile = false;
	
	public MyDatabaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// arg0.execSQL(CREATE_TABLE);
		
		db.execSQL("create table if not exists "+SEND+"(Id varchar(100) primary key, "
				+SEND_BarcodeNo+" varchar(20),"
				+BEGINNUM+" varchar(20),"				
				+TOTALNUM+" varchar(20))");
		
		//DCL发件箱表示位，LX类型表示位，TIME申请时间,meiId商户Id
		db.execSQL("create table if not exists st_storeinfo(Id varchar(100) primary key, " +
				"Dnum varchar(100)," +//1
				"DCL varchar(100)," +//2发件箱表示位
				"LX varchar(100)," +//3类型表示位
				"TIME varchar(100)," +//4申请时间
				"MId varchar(50)," +//5
				"meiId varchar(50)," +//6商户Id
				"ActiveType varchar(100), " +//7活动类型
				"storeauditId varchar(100)," +//8活动ID/预审ID
				"CityId varchar(100)," +//9城市ID
				"AreaId varchar(100), " +//10行政区ID
				"MaintainUserId varchar(100), " +//11维护人员工号
				"ExpandUserId varchar(100), " +//12拓展人员工号
				"IsNewCompany varchar(10), " +//13是否新建公司
				"CompanyId varchar(100)," +//14公司ID
				"CompanyName varchar(100), " +//15公司名称
				"CompanyLicense varchar(100)," +//16公司营业执照
				"LegalPerson varchar(100), " +//17法人
				"ContactUser varchar(100), " +//18联系人
				"TelNumber varchar(100), " +//19联系电话
				"MobileNumber varchar(100), " +//20手机
				"EngageName varchar(100), " +//21经营名
				"Adress varchar(100), " +//22注册地址
				"EngageScope varchar(100), " +//23经营范围
				"Brand varchar(100), " +//24品牌
				"BrandTelNumber varchar(100), " +//25品牌热线
				"PostCode varchar(100), " +//26邮编
				"Validity varchar(100), " +//27有效期
				"EngageYear varchar(100)," +//28经营年限
				"IsNewStore varchar(10), " +//29是否新增门店
				"StId varchar(100), " +//30门店ID
				"StLicense varchar(100),  " +//31门店工商执照
				"StName varchar(100), " +//32门店名称
				"Stel varchar(100), " +//33营业电话
				"StAdress varchar(100), " +//34营业地址
				"StPostCode varchar(100), " +//35邮编
				"StHangye varchar(100), " +//36行业
				"StCaixi varchar(100)," +//37菜系
				"StShangquan varchar(100), " +//38商圈
				"StNumber varchar(100), " +//39商户编号
				"StClientNumber varchar(100), " +//40终端号
				"Stlong varchar(100), " +//41经度
				"Stlat varchar(100), " +//42纬度
				"StContact varchar(100), " +//43联系人
				"StMobileNumber varchar(100), " +//44手机
				"StChannel varchar(100), " +//45拓展渠道
				"StIsReceiveBill varchar(100), " +//46是否收单
				"StValidity varchar(100), " +//47有效期
				"StBillRate varchar(100), " +//48收单费率
				"StParkSpace varchar(100), " +//49停车位
				"StBuinessHours varchar(100), " +//50营业时间
				"Stzhuoyi varchar(100), " +//51桌椅
				"Stmianji varchar(100), " +//52面积
				"Stjiatong varchar(100), " +//53交通
				"Sttaika varchar(100), " +//54太卡
				"Styilabao varchar(100), " +//55易拉宝
				"Stmengtie varchar(100), " +//56门帖
				"Sthaibao varchar(100), " +//57海报
				"CompanyInfo varchar(100), " +//58公司介绍
				"BrandInfo varchar(100), " +//59品牌介绍
				"StoreInfo varchar(100), " +//60门店介绍
				"ZhaoPai varchar(100), " +//61招牌菜
				"AvgExpense varchar(100), " +//62人均消费
				"Other varchar(100), " +//63其他
				"ExpenseDate varchar(100), " +//64消费日期
				"CardNumber varchar(100), " +//65卡号
				"IdentityCode varchar(100), " +//66授权码
				"Price varchar(100), " +//67交易金额
				"ContractDate varchar(20), " +//68签约日期
				"EndTime varchar(20), " +//69优惠开始时间
				"BenginTime varchar(20), " +//70优惠截止时间
				"IsExtension varchar(10), " +//71是否续约
				"IsSentMessage varchar(10), " +//72是否发送短信
				"CardType varchar(50), " +//73卡种
				"MessageContent varchar(100), " +//74短信内容
				"PrivilegeContent varchar(100), " +//75优惠内容
				"SpecialContent varchar(100)," +//76特别约定
				"YesNXieYi varchar(20)," +//77是否新签协议
				"ContractNumber varchar(100)," +//78签约协议号
				"XuanChuan varchar(100)," +//79白名单类型
				"PiceXuanChuan varchar(100)," +//80白名单商户编号
				"Zhihangye varchar(100)," +//81子行业
				"PiceXuanChuan2 varchar(100)," +//82协议电话号
				"PiceXuanChuan3 varchar(100)," +//83签约日期
				"PiceXuanChuan4 varchar(100)," +//84协议有效结束日期
				"Gcityid varchar(100)," +//85公司所属城市ID
				"Gdistrictid varchar(100)," +//86公司所属行政区ID
				"AttLicense varchar(60)," +//87营业执照附件
				"AttContract varchar(60)," +//88盖章协议
				"AttAccredit varchar(60)," +//89授权书
				"AttOthers varchar(60)," +//90其他附件				
				"youhuiquanneirong varchar(400)," +//91优惠内容
				"youxiaoBeginTime varchar(20)," +//92优惠开始时间
				"youxiaoStopTime varchar(20),"+//93优惠结束时间
				"ShopZhubeia varchar(50),"+//94商户注备A
				"ShopZhubeib varchar(50),"+//95商户注备B
				"ShopZhubeic varchar(50),"+//96商户注备C
				"ShopZhubeid varchar(50),"+//97商户注备D
				"ActiveZhubeia varchar(50),"+//98活动注备A
				"ActiveZhubeib varchar(50),"+//99活动注备B
				"ActiveZhubeic varchar(50),"+//100活动注备C
				"ActiveZhubeid varchar(50),"+//101活动注备D
				"ActiveMoney varchar(20),"+//102活动金额限制
				"ActiveDate varchar(20),"+//103活动日期限制
				"ActiveDateTime varchar(20),"+//104活动日
				"HasFaBu varchar(20),"+//105是否发布
				"hasyouhuiquan varchar(10))");//106是否有优惠券
		
		db.execSQL("create table if not exists gongdanweihu(" +
				"Id varchar(100) primary key,"+
				"Dnum varchar(100)," +
				"DCL varchar(100)," +
				"LX varchar(100)," +
				"TIME varchar(100)," +
				"Stname varchar(100)," +
				"taskId2 varchar(100)," +
				"taskId varchar(100)," +
				"ActiveType varchar(100)," +
				"Name varchar(100), " +
				"TelNumber varchar(100), " +
				"Neirong varchar(100), " +
				"Xiaofeidate varchar(100), " +
				"Kahao varchar(100), " +
				"Xiaofxs varchar(50), " +
				"Shouquma varchar(20)," +
				"Jin varchar(20), " +
				"Wei varchar(20)," +
				"WenXuan varchar(10),"+
				"BaiFang varchar(10),"+
				"AttLicense varchar(60)," +
				"AttContract varchar(60)," +
				"AttAccredit varchar(60)," +
				"AttContract1 varchar(60)," +
				"AttContract2 varchar(60)," +
				"AttContract3 varchar(60),"+
				"AttContract4 varchar(60),"+
				"AttContract5 varchar(60),"+
				"AttContract6 varchar(60),"+
				"AttContract7 varchar(60),"+
				"AttContract8 varchar(60),"+
				"AttContract9 varchar(60),"+
				"AttOthers varchar(60),"+
				"Wifi varchar(20),"+
				"OtherRemark varchar(100),"+
				"batchNo varchar(100))");
		
		//工单发送失败重新进件
		db.execSQL("create table if not exists gongdanweihusent(" +
				"Id varchar(100) primary key,"+
				"Dnum varchar(100)," +
				"DCL varchar(100)," +
				"LX varchar(100)," +
				"TIME varchar(100)," +
				"Stname varchar(100)," +
				"taskId2 varchar(100)," +
				"taskId varchar(100)," +
				"ActiveType varchar(100)," +
				"Name varchar(100), " +
				"TelNumber varchar(100), " +
				"Neirong varchar(100), " +
				"Xiaofeidate varchar(100), " +
				"Kahao varchar(100), " +
				"Xiaofxs varchar(50), " +
				"Shouquma varchar(20)," +
				"Jin varchar(20), " +
				"Wei varchar(20)," +
				"WenXuan varchar(10),"+
				"BaiFang varchar(10),"+
				"AttLicense varchar(60)," +
				"AttContract varchar(60)," +
				"AttAccredit varchar(60)," +
				"AttContract1 varchar(60)," +
				"AttContract2 varchar(60)," +
				"AttContract3 varchar(60),"+
				"AttContract4 varchar(60),"+
				"AttContract5 varchar(60),"+
				"AttContract6 varchar(60),"+
				"AttContract7 varchar(60),"+
				"AttContract8 varchar(60),"+
				"AttContract9 varchar(60),"+	
				"AttOthers varchar(60),"+	
				"FileName varchar(200))");
		
		db.execSQL("create table if not exists youhushangxian(" +
				"Id varchar(100) primary key, " +
				"DCL varchar(100)," +
				"LX varchar(100)," +
				"TIME varchar(100)," +
				"Stname varchar(100)," +
				"merId2 varchar(200), " +
				"merId varchar(200)," +
				"actId varchar(100), " +
				"longitude varchar(100), " +
				"latitude varchar(200)," +
				"sale varchar(200)," +
				"AttLicense varchar(60)," +
				"AttContract varchar(60)," +
				"AttAccredit varchar(60)," +
				"AttOthers varchar(60)," +
				"Name varchar(100)," +
				"Zhiwei varchar(100)," +
				"Neirong varchar(100)," +
				"Tkint varchar(100)," +
				"Cztint varchar(100)," +
				"Qtint varchar(100)," +
				"Mtint varchar(100)," +
				"usename varchar(100))");
		
		
		//最红进件表red_enterletter
		db.execSQL("create table if not exists red_enterletter(" +
				"Id varchar(100) primary key," +//1数据ID
				"Dnum varchar(100), " +//2流程记载
				"DCL varchar(100), " +//3发件标志位
				"LX varchar(20), " +//4类型
				"MId varchar(50), " +//5发件ID
				"TIME varchar(100), " +//6时间
				"Activityhangye varchar(50), " +//7行业活动
				"IsNewCompany varchar(1), " +//8是否新增公司
				"CompanyId varchar(50)," +//9公司ID
				"CompanyLicense varchar(50), " +//10公司营业执照
				"CompanyName varchar(60)," +//11公司名
				"LegalPerson varchar(60), " +//12法人
				"ContactUser varchar(60), " +//13联系人
				"TelNumber varchar(20)," +//14联系电话
				"MobileNumber varchar(20), " +//15手机
				"ManageName varchar(60), " +//16经营名
				"Adress varchar(60), " +//17注册地址
				"ManageScope varchar(60), " +//18经营范围
				"Brand varchar(60), " +//19品牌
				"BrandTelNumber varchar(20)," +//20品牌热线
				"PostCode varchar(6)," +//21邮编
				"Validity varchar(20)," +//22有效期
				"EngageYear varchar(10)," +//23经营年限
				"TreatyNumber varchar(50)," +//24条形码协议号
				"CompanyCity varchar(50), " +//25公司所属城市
				"CompanyArea varchar(50), " +//26公司所属行政区
				"IsNewShop varchar(1)," +//27是否新增门店
				"ShopId varchar(50)," +//28门店ID
				"ShopLicense varchar(50)," +//29门店工商执照
				"BusinessName varchar(60)," +//30营业名称
				"BusinessNunber varchar(20)," +//31营业电话
				"BusinessAdress varchar(60)," +//32营业地址
				"ShopCode varchar(6)," +//33门店邮编
				"ShopTrade varchar(50)," +//34门店行业
				"ShopGreens varchar(50)," +//35门店菜系
				"ShopDealer varchar(50)," +//36门店商圈
				"Longitude varchar(20)," +//37经度
				"LatiTude varchar(20)," +//38纬度
				"ShopUserName varchar(60)," +//39门店联系人
				"ShopTelNumber varchar(20)," +//40门店电话
				"ShopMobileNumber varchar(20)," +//41门店手机
				"StChannel varchar(1)," +//42拓展渠道
				"StIsReceiveBill varchar(1)," +//43是否收单
				"StValidity varchar(20)," +//44有效期
				"StBillRate varchar(20)," +//45收单费率
				"ShopCity varchar(50), " +//46门店所属城市
				"ShopArea varchar(50), " +//47门店所属行政区
				"IsNewPoster varchar(1)," +//48是否新建广宣
				"PosterID varchar(50)," +//49商户ID
				"StParkSpace varchar(10), " +//50停车位
				"StBuinessHours varchar(200), " +//51营业时间
				"Stzhuoyi varchar(10), " +//52椅套
				"Stmianji varchar(10), " +//53面积
				"Stjiatong varchar(200), " +//54交通情况
				"Sttaika varchar(5), " +//55台卡
				"Styilabao varchar(5)," +//56易拉宝
				"Stmengtie varchar(5), " +//57门贴
				"Sthaibao varchar(10), " +//58海报
				"CompanyInfo varchar(200), " +//59公司介绍
				"ShopInfo varchar(200), " +//60门店介绍
				"ZhaoPai varchar(200), " +//61招牌菜
				"Other varchar(200), " + //62其他
				"Activityneirong varchar(200), " +//63活动内容
				"Isduanxin varchar(5), " +//64是否发送短信
				"Duanxinnrirong varchar(250), " +//65短信内容
				"ActivityqianyueDate varchar(10), " +//66签约日期
				"ActivityYouhuiDateStart varchar(10), " +//67优惠开始日期
				"ActivityYouhuiDateStop varchar(10), " +//68优惠结束日期
				"Acrivitykazhong varchar(10), " +//69卡种
				"AcrivityIsNewStay varchar(1), " +//70是否驻店
				"AcrivityStayMianji varchar(10), " +//71驻店面积
				"AcrivityStayNum varchar(10), " +//72驻店人数
				"AcrivityStayDateStart varchar(10), " +//73驻店开始时间
				"AcrivityStayDateStop varchar(10), " +//74驻店结束时间
				"AcrivityStayTime varchar(2), " +//75驻店时长
				"Acrivityhuodongri varchar(10), " +//76活动日
				"AcrivityMoneymin varchar(10), " +//77活动金额底限
				"AcrivityMoneymax varchar(10), " +//78活动金额上限
				"Acrivityreturn varchar(5), " +//79返还比例
				"AcrivityIsPublish varchar(1), " +//80是否发布
				"TakePhoneName varchar(200), " +//81照片名字
				"MerNumber varchar(200), "+ //82商户编号
				"mertype varchar(100), " +//83商户类型
				"whitenum varchar(200),"+//84白名单编号
				"RedShopZhubeia varchar(50),"+//85商户注备1
				"RedShopZhubeib varchar(50),"+//86商户注备2
				"RedShopZhubeic varchar(50),"+//87商户注备3
				"RedShopZhubeid varchar(50))");//88商户注备4
		
		
		//商户工单维护数据表
		db.execSQL("create table if not exists shgongdanweihu(" +
				"Id varchar(100) primary key,"+ //0 数据ID
				"Dnum varchar(100)," + //1流程记载
				"DCL varchar(100)," + //2发件标志位
				"LX varchar(100)," + //3类型
				"TIME varchar(100)," + //4时间
				"StName varchar(100)," + //5商户名称
				"taskId2 varchar(100)," + //6字符串工单Id+数据表中tdId
				"userName varchar(50),"+ //7员工号
				"taskId varchar(50)," + //8工单Id
				"startGps varchar(50)," + //9签到Gps
				"startTime varchar(50), " + //10签到时间
				"shName varchar(100), " + //11商户名称
				"shNameCheck varchar(10), " + //12商户名称选择是否
				"shNameContent varchar(100), " + //13商户名称备注
				"shAddress varchar(120), " + //14商户地址
				"shAddressCheck varchar(10), " + //15商户地址选择是否
				"shAddressComment varchar(120)," + //16是地址备注
				"shPhoneNum varchar(50), " + //17商户联系电话
				"shPhoneNumCheck varchar(10)," + //18联系电话选择是否
				"shPhoneNumComment varchar(50),"+ //19联系电话备注
				"posIsWork varchar(10),"+ //20 pos机是否正常运作
				"posIsWorkComment varchar(50)," +//21pos机运作备注
				"sbNumFrom varchar(500)," + //22商编号系统数据
				"sbNumSelect varchar(50)," + //23商编号系统数据选择是否
				"sbNumComment varchar(500)," + //24商编系统数据备注
				"sbNumAdd varchar(210)," + //25商编号新增数据
				"machineIsWork varchar(10),"+ //26机具是否运作
				"isWorkComment varchar(50),"+ //27机具运作情况备注
				"snNumFrom varchar(500),"+ //28机具系统SN号
				"snNumSelect varchar(50),"+ //29机具系统SN号选择是否
				"snNumComment varchar(100),"+ //30机具系统SN号备注
				"snNumAdd varchar(60),"+  //31机具号新增
				"doorPicName varchar(50),"+//32入口区照片名称
				"tablePicName varchar(50),"+//33餐桌区照片名称
				"checkPicName varchar(50),"+//34收银区照片名称
				"trainProperty varchar(10),"+//35培训性质
				"trainTargetSelect varchar(20),"+ //36培训对象选择结果
				"trainTargetComment varchar(100),"+ //37机具运作情况备注
				"trainContentSelect varchar(20),"+ //38培训内容选择结果
				"trainContentComment varchar(400),"+ //39培训内容备注
				"principalName varchar(20),"+ //40商户负责人姓名
				"principalPhoneNum varchar(60),"+  //41商户负责人电话
				"signPicName varchar(20),"+//42负责人签名
				"endGps varchar(50),"+//43签退gps信息
				"endTime varchar(20),"+//44签退时间
				"submitTime varchar(20),"+//45提交时间
				"reservedOne varchar(100),"+//46备用1
				"reservedTwo varchar(100),"+//47备用2
				"reservedThree varchar(100),"+//48备用3
				"reservedFour varchar(100))"); //49备用4
		

		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		System.out.println(arg1 + "" + arg2);
		//数据库添加字段
//		if(arg2>=1){
//			if(IsTableExists(arg0, "表名") && !isColumnExists(arg0, "表名", "添加的字段")){
//				arg0.execSQL("alter table 表名 add column 添加的字段名  varchar(100)");
//			}
//			if(IsTableExists(arg0, "gongdanweihu") && !isColumnExists(arg0, "gongdanweihu", "Dnum")){
//				arg0.execSQL("alter table gongdanweihu add column Dnum varchar(100)");
//			}
//		}
		if(IsTableExists(arg0,"gongdanweihu")){
			arg0.execSQL("drop table gongdanweihu");
		}
		delectfile = true;
		arg0.execSQL("create table if not exists gongdanweihu(" +
				"Id varchar(100) primary key,"+
				"Dnum varchar(100)," +
				"DCL varchar(100)," +
				"LX varchar(100)," +
				"TIME varchar(100)," +
				"Stname varchar(100)," +
				"taskId2 varchar(100)," +
				"taskId varchar(100)," +
				"ActiveType varchar(100)," +
				"Name varchar(100), " +
				"TelNumber varchar(100), " +
				"Neirong varchar(100), " +
				"Xiaofeidate varchar(100), " +
				"Kahao varchar(100), " +
				"Xiaofxs varchar(50), " +
				"Shouquma varchar(20)," +
				"Jin varchar(20), " +
				"Wei varchar(20)," +
				"WenXuan varchar(10),"+
				"BaiFang varchar(10),"+
				"AttLicense varchar(60)," +
				"AttContract varchar(60)," +
				"AttAccredit varchar(60)," +
				"AttContract1 varchar(60)," +
				"AttContract2 varchar(60)," +
				"AttContract3 varchar(60),"+
				"AttContract4 varchar(60),"+
				"AttContract5 varchar(60),"+
				"AttContract6 varchar(60),"+
				"AttContract7 varchar(60),"+
				"AttContract8 varchar(60),"+
				"AttContract9 varchar(60),"+
				"AttOthers varchar(60),"+
				"Wifi varchar(20),"+
				"OtherRemark varchar(100),"+
				"batchNo varchar(100))");
		
		//工单发送失败重新进件
		//arg0.execSQL("drop table gongdanweihusent");
		if(IsTableExists(arg0,"gongdanweihusent")){
			arg0.execSQL("drop table gongdanweihusent");
		}
		arg0.execSQL("create table if not exists gongdanweihusent(" +
				"Id varchar(100) primary key,"+
				"Dnum varchar(100)," +
				"DCL varchar(100)," +
				"LX varchar(100)," +
				"TIME varchar(100)," +
				"Stname varchar(100)," +
				"taskId2 varchar(100)," +
				"taskId varchar(100)," +
				"ActiveType varchar(100)," +
				"Name varchar(100), " +
				"TelNumber varchar(100), " +
				"Neirong varchar(100), " +
				"Xiaofeidate varchar(100), " +
				"Kahao varchar(100), " +
				"Xiaofxs varchar(50), " +
				"Shouquma varchar(20)," +
				"Jin varchar(20), " +
				"Wei varchar(20)," +
				"WenXuan varchar(10),"+
				"BaiFang varchar(10),"+
				"AttLicense varchar(60)," +
				"AttContract varchar(60)," +
				"AttAccredit varchar(60)," +
				"AttContract1 varchar(60)," +
				"AttContract2 varchar(60)," +
				"AttContract3 varchar(60),"+
				"AttContract4 varchar(60),"+
				"AttContract5 varchar(60),"+
				"AttContract6 varchar(60),"+
				"AttContract7 varchar(60),"+
				"AttContract8 varchar(60),"+
				"AttContract9 varchar(60),"+	
				"AttOthers varchar(60),"+	
				"FileName varchar(200))");
		
		//DCL发件箱表示位，LX类型表示位，TIME申请时间,meiId商户Id
		//arg0.execSQL("drop table st_storeinfo");
		if(IsTableExists(arg0,"st_storeinfo")){
			arg0.execSQL("drop table st_storeinfo");
		}
		arg0.execSQL("create table if not exists st_storeinfo(Id varchar(100) primary key, " +
				"Dnum varchar(100)," +//1
				"DCL varchar(100)," +//2发件箱表示位
				"LX varchar(100)," +//3类型表示位
				"TIME varchar(100)," +//4申请时间
				"MId varchar(50)," +//5
				"meiId varchar(50)," +//6商户Id
				"ActiveType varchar(100), " +//7活动类型
				"storeauditId varchar(100)," +//8活动ID/预审ID
				"CityId varchar(100)," +//9城市ID
				"AreaId varchar(100), " +//10行政区ID
				"MaintainUserId varchar(100), " +//11维护人员工号
				"ExpandUserId varchar(100), " +//12拓展人员工号
				"IsNewCompany varchar(10), " +//13是否新建公司
				"CompanyId varchar(100)," +//14公司ID
				"CompanyName varchar(100), " +//15公司名称
				"CompanyLicense varchar(100)," +//16公司营业执照
				"LegalPerson varchar(100), " +//17法人
				"ContactUser varchar(100), " +//18联系人
				"TelNumber varchar(100), " +//19联系电话
				"MobileNumber varchar(100), " +//20手机
				"EngageName varchar(100), " +//21经营名
				"Adress varchar(100), " +//22注册地址
				"EngageScope varchar(100), " +//23经营范围
				"Brand varchar(100), " +//24品牌
				"BrandTelNumber varchar(100), " +//25品牌热线
				"PostCode varchar(100), " +//26邮编
				"Validity varchar(100), " +//27有效期
				"EngageYear varchar(100)," +//28经营年限
				"IsNewStore varchar(10), " +//29是否新增门店
				"StId varchar(100), " +//30门店ID
				"StLicense varchar(100),  " +//31门店工商执照
				"StName varchar(100), " +//32门店名称
				"Stel varchar(100), " +//33营业电话
				"StAdress varchar(100), " +//34营业地址
				"StPostCode varchar(100), " +//35邮编
				"StHangye varchar(100), " +//36行业
				"StCaixi varchar(100)," +//37菜系
				"StShangquan varchar(100), " +//38商圈
				"StNumber varchar(100), " +//39商户编号
				"StClientNumber varchar(100), " +//40终端号
				"Stlong varchar(100), " +//41经度
				"Stlat varchar(100), " +//42纬度
				"StContact varchar(100), " +//43联系人
				"StMobileNumber varchar(100), " +//44手机
				"StChannel varchar(100), " +//45拓展渠道
				"StIsReceiveBill varchar(100), " +//46是否收单
				"StValidity varchar(100), " +//47有效期
				"StBillRate varchar(100), " +//48收单费率
				"StParkSpace varchar(100), " +//49停车位
				"StBuinessHours varchar(100), " +//50营业时间
				"Stzhuoyi varchar(100), " +//51桌椅
				"Stmianji varchar(100), " +//52面积
				"Stjiatong varchar(100), " +//53交通
				"Sttaika varchar(100), " +//54太卡
				"Styilabao varchar(100), " +//55易拉宝
				"Stmengtie varchar(100), " +//56门帖
				"Sthaibao varchar(100), " +//57海报
				"CompanyInfo varchar(100), " +//58公司介绍
				"BrandInfo varchar(100), " +//59品牌介绍
				"StoreInfo varchar(100), " +//60门店介绍
				"ZhaoPai varchar(100), " +//61招牌菜
				"AvgExpense varchar(100), " +//62人均消费
				"Other varchar(100), " +//63其他
				"ExpenseDate varchar(100), " +//64消费日期
				"CardNumber varchar(100), " +//65卡号
				"IdentityCode varchar(100), " +//66授权码
				"Price varchar(100), " +//67交易金额
				"ContractDate varchar(20), " +//68签约日期
				"EndTime varchar(20), " +//69优惠开始时间
				"BenginTime varchar(20), " +//70优惠截止时间
				"IsExtension varchar(10), " +//71是否续约
				"IsSentMessage varchar(10), " +//72是否发送短信
				"CardType varchar(50), " +//73卡种
				"MessageContent varchar(100), " +//74短信内容
				"PrivilegeContent varchar(100), " +//75优惠内容
				"SpecialContent varchar(100)," +//76特别约定
				"YesNXieYi varchar(20)," +//77是否新签协议
				"ContractNumber varchar(100)," +//78签约协议号
				"XuanChuan varchar(100)," +//79白名单类型
				"PiceXuanChuan varchar(100)," +//80白名单商户编号
				"Zhihangye varchar(100)," +//81子行业
				"PiceXuanChuan2 varchar(100)," +//82协议电话号
				"PiceXuanChuan3 varchar(100)," +//83签约日期
				"PiceXuanChuan4 varchar(100)," +//84协议有效结束日期
				"Gcityid varchar(100)," +//85公司所属城市ID
				"Gdistrictid varchar(100)," +//86公司所属行政区ID
				"AttLicense varchar(60)," +//87营业执照附件
				"AttContract varchar(60)," +//88盖章协议
				"AttAccredit varchar(60)," +//89授权书
				"AttOthers varchar(60)," +//90其他附件				
				"youhuiquanneirong varchar(400)," +//91优惠内容
				"youxiaoBeginTime varchar(20)," +//92优惠开始时间
				"youxiaoStopTime varchar(20),"+//93优惠结束时间
				"ShopZhubeia varchar(20),"+//94商户注备A
				"ShopZhubeib varchar(20),"+//95商户注备B
				"ShopZhubeic varchar(20),"+//96商户注备C
				"ShopZhubeid varchar(20),"+//97商户注备D
				"ActiveZhubeia varchar(20),"+//98活动注备A
				"ActiveZhubeib varchar(20),"+//99活动注备B
				"ActiveZhubeic varchar(20),"+//100活动注备C
				"ActiveZhubeid varchar(20),"+//101活动注备D
				"ActiveMoney varchar(20),"+//102活动金额限制
				"ActiveDate varchar(20),"+//103活动日期限制
				"ActiveDateTime varchar(20),"+//104活动日
				"HasFaBu varchar(20),"+//105是否发布
				"hasyouhuiquan varchar(10))");//106是否有优惠券
		
		//修改更新最红进件表red_enterletter
		//arg0.execSQL("drop table red_enterletter");
		if(IsTableExists(arg0,"red_enterletter")){
			arg0.execSQL("drop table red_enterletter");
		}
		arg0.execSQL("create table if not exists red_enterletter(" +
				"Id varchar(100) primary key," +//1数据ID
				"Dnum varchar(100), " +//2流程记载
				"DCL varchar(100), " +//3发件标志位
				"LX varchar(20), " +//4类型
				"MId varchar(50), " +//5发件ID
				"TIME varchar(100), " +//6时间
				"Activityhangye varchar(50), " +//7行业活动
				"IsNewCompany varchar(1), " +//8是否新增公司
				"CompanyId varchar(50)," +//9公司ID
				"CompanyLicense varchar(50), " +//10公司营业执照
				"CompanyName varchar(60)," +//11公司名
				"LegalPerson varchar(60), " +//12法人
				"ContactUser varchar(60), " +//13联系人
				"TelNumber varchar(20)," +//14联系电话
				"MobileNumber varchar(20), " +//15手机
				"ManageName varchar(60), " +//16经营名
				"Adress varchar(60), " +//17注册地址
				"ManageScope varchar(60), " +//18经营范围
				"Brand varchar(60), " +//19品牌
				"BrandTelNumber varchar(20)," +//20品牌热线
				"PostCode varchar(6)," +//21邮编
				"Validity varchar(20)," +//22有效期
				"EngageYear varchar(10)," +//23经营年限
				"TreatyNumber varchar(50)," +//24条形码协议号
				"CompanyCity varchar(50), " +//25公司所属城市
				"CompanyArea varchar(50), " +//26公司所属行政区
				"IsNewShop varchar(1)," +//27是否新增门店
				"ShopId varchar(50)," +//28门店ID
				"ShopLicense varchar(50)," +//29门店工商执照
				"BusinessName varchar(60)," +//30营业名称
				"BusinessNunber varchar(20)," +//31营业电话
				"BusinessAdress varchar(60)," +//32营业地址
				"ShopCode varchar(6)," +//33门店邮编
				"ShopTrade varchar(50)," +//34门店行业
				"ShopGreens varchar(50)," +//35门店菜系
				"ShopDealer varchar(50)," +//36门店商圈
				"Longitude varchar(20)," +//37经度
				"LatiTude varchar(20)," +//38纬度
				"ShopUserName varchar(60)," +//39门店联系人
				"ShopTelNumber varchar(20)," +//40门店电话
				"ShopMobileNumber varchar(20)," +//41门店手机
				"StChannel varchar(1)," +//42拓展渠道
				"StIsReceiveBill varchar(1)," +//43是否收单
				"StValidity varchar(20)," +//44有效期
				"StBillRate varchar(20)," +//45收单费率
				"ShopCity varchar(50), " +//46门店所属城市
				"ShopArea varchar(50), " +//47门店所属行政区
				"IsNewPoster varchar(1)," +//48是否新建广宣
				"PosterID varchar(50)," +//49商户ID
				"StParkSpace varchar(10), " +//50停车位
				"StBuinessHours varchar(200), " +//51营业时间
				"Stzhuoyi varchar(10), " +//52椅套
				"Stmianji varchar(10), " +//53面积
				"Stjiatong varchar(200), " +//54交通情况
				"Sttaika varchar(5), " +//55台卡
				"Styilabao varchar(5)," +//56易拉宝
				"Stmengtie varchar(5), " +//57门贴
				"Sthaibao varchar(10), " +//58海报
				"CompanyInfo varchar(200), " +//59公司介绍
				"ShopInfo varchar(200), " +//60门店介绍
				"ZhaoPai varchar(200), " +//61招牌菜
				"Other varchar(200), " + //62其他
				"Activityneirong varchar(200), " +//63活动内容
				"Isduanxin varchar(5), " +//64是否发送短信
				"Duanxinnrirong varchar(250), " +//65短信内容
				"ActivityqianyueDate varchar(10), " +//66签约日期
				"ActivityYouhuiDateStart varchar(10), " +//67优惠开始日期
				"ActivityYouhuiDateStop varchar(10), " +//68优惠结束日期
				"Acrivitykazhong varchar(10), " +//69卡种
				"AcrivityIsNewStay varchar(1), " +//70是否驻店
				"AcrivityStayMianji varchar(10), " +//71驻店面积
				"AcrivityStayNum varchar(10), " +//72驻店人数
				"AcrivityStayDateStart varchar(10), " +//73驻店开始时间
				"AcrivityStayDateStop varchar(10), " +//74驻店结束时间
				"AcrivityStayTime varchar(2), " +//75驻店时长
				"Acrivityhuodongri varchar(10), " +//76活动日
				"AcrivityMoneymin varchar(10), " +//77活动金额底限
				"AcrivityMoneymax varchar(10), " +//78活动金额上限
				"Acrivityreturn varchar(5), " +//79返还比例
				"AcrivityIsPublish varchar(1), " +//80是否发布
				"TakePhoneName varchar(200), " +//81照片名字
				"MerNumber varchar(200), "+ //82商户编号
				"mertype varchar(100), " +//83商户类型
				"whitenum varchar(200),"+//84白名单编号
				"RedShopZhubeia varchar(50),"+//85商户注备1
				"RedShopZhubeib varchar(50),"+//86商户注备2
				"RedShopZhubeic varchar(50),"+//87商户注备3
				"RedShopZhubeid varchar(50))");//88商户注备4


		
		//修改更新商户工单维护表shgongdanweihu
		//arg0.execSQL("drop table shgongdanweihu");
		if(IsTableExists(arg0,"shgongdanweihu")){
			arg0.execSQL("drop table shgongdanweihu");
		}
		arg0.execSQL("create table if not exists shgongdanweihu(" +
				"Id varchar(100) primary key,"+ //0 数据ID
				"Dnum varchar(100)," + //1流程记载
				"DCL varchar(100)," + //2发件标志位
				"LX varchar(100)," + //3类型
				"TIME varchar(100)," + //4时间
				"StName varchar(100)," + //5商户名称
				"taskId2 varchar(100)," + //6字符串工单Id+数据表中tdId
				"userName varchar(50),"+ //7员工号
				"taskId varchar(50)," + //8工单Id
				"startGps varchar(50)," + //9签到Gps
				"startTime varchar(50), " + //10签到时间
				"shName varchar(100), " + //11商户名称
				"shNameCheck varchar(10), " + //12商户名称选择是否
				"shNameContent varchar(100), " + //13商户名称备注
				"shAddress varchar(120), " + //14商户地址
				"shAddressCheck varchar(10), " + //15商户地址选择是否
				"shAddressComment varchar(120)," + //16是地址备注
				"shPhoneNum varchar(50), " + //17商户联系电话
				"shPhoneNumCheck varchar(10)," + //18联系电话选择是否
				"shPhoneNumComment varchar(50),"+ //19联系电话备注
				"posIsWork varchar(10),"+ //20 pos机是否正常运作
				"posIsWorkComment varchar(50)," +//21pos机运作备注
				"sbNumFrom varchar(500)," + //22商编号系统数据
				"sbNumSelect varchar(50)," + //23商编号系统数据选择是否
				"sbNumComment varchar(500)," + //24商编系统数据备注
				"sbNumAdd varchar(210)," + //25商编号新增数据
				"machineIsWork varchar(10),"+ //26机具是否运作
				"isWorkComment varchar(50),"+ //27机具运作情况备注
				"snNumFrom varchar(500),"+ //28机具系统SN号
				"snNumSelect varchar(50),"+ //29机具系统SN号选择是否
				"snNumComment varchar(100),"+ //30机具系统SN号备注
				"snNumAdd varchar(60),"+  //31机具号新增
				"doorPicName varchar(50),"+//32入口区照片名称
				"tablePicName varchar(50),"+//33餐桌区照片名称
				"checkPicName varchar(50),"+//34收银区照片名称
				"trainProperty varchar(10),"+//35培训性质
				"trainTargetSelect varchar(20),"+ //36培训对象选择结果
				"trainTargetComment varchar(100),"+ //37机具运作情况备注
				"trainContentSelect varchar(20),"+ //38培训内容选择结果
				"trainContentComment varchar(400),"+ //39培训内容备注
				"principalName varchar(20),"+ //40商户负责人姓名
				"principalPhoneNum varchar(60),"+  //41商户负责人电话
				"signPicName varchar(20),"+//42负责人签名
				"endGps varchar(50),"+//43签退gps信息
				"endTime varchar(20),"+//44签退时间
				"submitTime varchar(20),"+//45提交时间
				"reservedOne varchar(100),"+//46备用1
				"reservedTwo varchar(100),"+//47备用2
				"reservedThree varchar(100),"+//48备用3
				"reservedFour varchar(100))"); //49备用4

	}
	
	public boolean isColumnExists(SQLiteDatabase db, String tabName, String col){
		boolean result = true;
		Cursor cursor = null;
		try{
			cursor = db.rawQuery("select * from "+tabName, null);
			if(cursor.getColumnIndex(col) == -1){
				result = false;
			}
		}
		catch(Exception e){
			result = false;
		}
		finally{
			if(cursor!=null)
				cursor.close();
		}
		return result;
	}
	
	public boolean IsTableExists(SQLiteDatabase db, String tabName){
        boolean result = false;
        if(tabName == null){
                return false;
        }
        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"+tabName.trim()+"' ";
            cursor = db.rawQuery(sql, null);
            if(cursor.moveToNext()){
                int count = cursor.getInt(0);
                if(count>0){
                    result = true;
                }
            }
                
        } catch (Exception e) {
                // TODO: handle exception
        }
        finally{
        	if(cursor != null)
        		cursor.close();
        }
        return result;
	}

}