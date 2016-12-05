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

	//������
	public static final String SEND="send";//����
	public static final String SEND_BarcodeNo="sendbarcodeno";//���������к�0
	public static final String BEGINNUM="beginnum";//��ʼ����1
	public static final String TOTALNUM="totalnum";//�ܶ���2
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
		
		//DCL�������ʾλ��LX���ͱ�ʾλ��TIME����ʱ��,meiId�̻�Id
		db.execSQL("create table if not exists st_storeinfo(Id varchar(100) primary key, " +
				"Dnum varchar(100)," +//1
				"DCL varchar(100)," +//2�������ʾλ
				"LX varchar(100)," +//3���ͱ�ʾλ
				"TIME varchar(100)," +//4����ʱ��
				"MId varchar(50)," +//5
				"meiId varchar(50)," +//6�̻�Id
				"ActiveType varchar(100), " +//7�����
				"storeauditId varchar(100)," +//8�ID/Ԥ��ID
				"CityId varchar(100)," +//9����ID
				"AreaId varchar(100), " +//10������ID
				"MaintainUserId varchar(100), " +//11ά����Ա����
				"ExpandUserId varchar(100), " +//12��չ��Ա����
				"IsNewCompany varchar(10), " +//13�Ƿ��½���˾
				"CompanyId varchar(100)," +//14��˾ID
				"CompanyName varchar(100), " +//15��˾����
				"CompanyLicense varchar(100)," +//16��˾Ӫҵִ��
				"LegalPerson varchar(100), " +//17����
				"ContactUser varchar(100), " +//18��ϵ��
				"TelNumber varchar(100), " +//19��ϵ�绰
				"MobileNumber varchar(100), " +//20�ֻ�
				"EngageName varchar(100), " +//21��Ӫ��
				"Adress varchar(100), " +//22ע���ַ
				"EngageScope varchar(100), " +//23��Ӫ��Χ
				"Brand varchar(100), " +//24Ʒ��
				"BrandTelNumber varchar(100), " +//25Ʒ������
				"PostCode varchar(100), " +//26�ʱ�
				"Validity varchar(100), " +//27��Ч��
				"EngageYear varchar(100)," +//28��Ӫ����
				"IsNewStore varchar(10), " +//29�Ƿ������ŵ�
				"StId varchar(100), " +//30�ŵ�ID
				"StLicense varchar(100),  " +//31�ŵ깤��ִ��
				"StName varchar(100), " +//32�ŵ�����
				"Stel varchar(100), " +//33Ӫҵ�绰
				"StAdress varchar(100), " +//34Ӫҵ��ַ
				"StPostCode varchar(100), " +//35�ʱ�
				"StHangye varchar(100), " +//36��ҵ
				"StCaixi varchar(100)," +//37��ϵ
				"StShangquan varchar(100), " +//38��Ȧ
				"StNumber varchar(100), " +//39�̻����
				"StClientNumber varchar(100), " +//40�ն˺�
				"Stlong varchar(100), " +//41����
				"Stlat varchar(100), " +//42γ��
				"StContact varchar(100), " +//43��ϵ��
				"StMobileNumber varchar(100), " +//44�ֻ�
				"StChannel varchar(100), " +//45��չ����
				"StIsReceiveBill varchar(100), " +//46�Ƿ��յ�
				"StValidity varchar(100), " +//47��Ч��
				"StBillRate varchar(100), " +//48�յ�����
				"StParkSpace varchar(100), " +//49ͣ��λ
				"StBuinessHours varchar(100), " +//50Ӫҵʱ��
				"Stzhuoyi varchar(100), " +//51����
				"Stmianji varchar(100), " +//52���
				"Stjiatong varchar(100), " +//53��ͨ
				"Sttaika varchar(100), " +//54̫��
				"Styilabao varchar(100), " +//55������
				"Stmengtie varchar(100), " +//56����
				"Sthaibao varchar(100), " +//57����
				"CompanyInfo varchar(100), " +//58��˾����
				"BrandInfo varchar(100), " +//59Ʒ�ƽ���
				"StoreInfo varchar(100), " +//60�ŵ����
				"ZhaoPai varchar(100), " +//61���Ʋ�
				"AvgExpense varchar(100), " +//62�˾�����
				"Other varchar(100), " +//63����
				"ExpenseDate varchar(100), " +//64��������
				"CardNumber varchar(100), " +//65����
				"IdentityCode varchar(100), " +//66��Ȩ��
				"Price varchar(100), " +//67���׽��
				"ContractDate varchar(20), " +//68ǩԼ����
				"EndTime varchar(20), " +//69�Żݿ�ʼʱ��
				"BenginTime varchar(20), " +//70�Żݽ�ֹʱ��
				"IsExtension varchar(10), " +//71�Ƿ���Լ
				"IsSentMessage varchar(10), " +//72�Ƿ��Ͷ���
				"CardType varchar(50), " +//73����
				"MessageContent varchar(100), " +//74��������
				"PrivilegeContent varchar(100), " +//75�Ż�����
				"SpecialContent varchar(100)," +//76�ر�Լ��
				"YesNXieYi varchar(20)," +//77�Ƿ���ǩЭ��
				"ContractNumber varchar(100)," +//78ǩԼЭ���
				"XuanChuan varchar(100)," +//79����������
				"PiceXuanChuan varchar(100)," +//80�������̻����
				"Zhihangye varchar(100)," +//81����ҵ
				"PiceXuanChuan2 varchar(100)," +//82Э��绰��
				"PiceXuanChuan3 varchar(100)," +//83ǩԼ����
				"PiceXuanChuan4 varchar(100)," +//84Э����Ч��������
				"Gcityid varchar(100)," +//85��˾��������ID
				"Gdistrictid varchar(100)," +//86��˾����������ID
				"AttLicense varchar(60)," +//87Ӫҵִ�ո���
				"AttContract varchar(60)," +//88����Э��
				"AttAccredit varchar(60)," +//89��Ȩ��
				"AttOthers varchar(60)," +//90��������				
				"youhuiquanneirong varchar(400)," +//91�Ż�����
				"youxiaoBeginTime varchar(20)," +//92�Żݿ�ʼʱ��
				"youxiaoStopTime varchar(20),"+//93�Żݽ���ʱ��
				"ShopZhubeia varchar(50),"+//94�̻�ע��A
				"ShopZhubeib varchar(50),"+//95�̻�ע��B
				"ShopZhubeic varchar(50),"+//96�̻�ע��C
				"ShopZhubeid varchar(50),"+//97�̻�ע��D
				"ActiveZhubeia varchar(50),"+//98�ע��A
				"ActiveZhubeib varchar(50),"+//99�ע��B
				"ActiveZhubeic varchar(50),"+//100�ע��C
				"ActiveZhubeid varchar(50),"+//101�ע��D
				"ActiveMoney varchar(20),"+//102��������
				"ActiveDate varchar(20),"+//103���������
				"ActiveDateTime varchar(20),"+//104���
				"HasFaBu varchar(20),"+//105�Ƿ񷢲�
				"hasyouhuiquan varchar(10))");//106�Ƿ����Ż�ȯ
		
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
		
		//��������ʧ�����½���
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
		
		
		//��������red_enterletter
		db.execSQL("create table if not exists red_enterletter(" +
				"Id varchar(100) primary key," +//1����ID
				"Dnum varchar(100), " +//2���̼���
				"DCL varchar(100), " +//3������־λ
				"LX varchar(20), " +//4����
				"MId varchar(50), " +//5����ID
				"TIME varchar(100), " +//6ʱ��
				"Activityhangye varchar(50), " +//7��ҵ�
				"IsNewCompany varchar(1), " +//8�Ƿ�������˾
				"CompanyId varchar(50)," +//9��˾ID
				"CompanyLicense varchar(50), " +//10��˾Ӫҵִ��
				"CompanyName varchar(60)," +//11��˾��
				"LegalPerson varchar(60), " +//12����
				"ContactUser varchar(60), " +//13��ϵ��
				"TelNumber varchar(20)," +//14��ϵ�绰
				"MobileNumber varchar(20), " +//15�ֻ�
				"ManageName varchar(60), " +//16��Ӫ��
				"Adress varchar(60), " +//17ע���ַ
				"ManageScope varchar(60), " +//18��Ӫ��Χ
				"Brand varchar(60), " +//19Ʒ��
				"BrandTelNumber varchar(20)," +//20Ʒ������
				"PostCode varchar(6)," +//21�ʱ�
				"Validity varchar(20)," +//22��Ч��
				"EngageYear varchar(10)," +//23��Ӫ����
				"TreatyNumber varchar(50)," +//24������Э���
				"CompanyCity varchar(50), " +//25��˾��������
				"CompanyArea varchar(50), " +//26��˾����������
				"IsNewShop varchar(1)," +//27�Ƿ������ŵ�
				"ShopId varchar(50)," +//28�ŵ�ID
				"ShopLicense varchar(50)," +//29�ŵ깤��ִ��
				"BusinessName varchar(60)," +//30Ӫҵ����
				"BusinessNunber varchar(20)," +//31Ӫҵ�绰
				"BusinessAdress varchar(60)," +//32Ӫҵ��ַ
				"ShopCode varchar(6)," +//33�ŵ��ʱ�
				"ShopTrade varchar(50)," +//34�ŵ���ҵ
				"ShopGreens varchar(50)," +//35�ŵ��ϵ
				"ShopDealer varchar(50)," +//36�ŵ���Ȧ
				"Longitude varchar(20)," +//37����
				"LatiTude varchar(20)," +//38γ��
				"ShopUserName varchar(60)," +//39�ŵ���ϵ��
				"ShopTelNumber varchar(20)," +//40�ŵ�绰
				"ShopMobileNumber varchar(20)," +//41�ŵ��ֻ�
				"StChannel varchar(1)," +//42��չ����
				"StIsReceiveBill varchar(1)," +//43�Ƿ��յ�
				"StValidity varchar(20)," +//44��Ч��
				"StBillRate varchar(20)," +//45�յ�����
				"ShopCity varchar(50), " +//46�ŵ���������
				"ShopArea varchar(50), " +//47�ŵ�����������
				"IsNewPoster varchar(1)," +//48�Ƿ��½�����
				"PosterID varchar(50)," +//49�̻�ID
				"StParkSpace varchar(10), " +//50ͣ��λ
				"StBuinessHours varchar(200), " +//51Ӫҵʱ��
				"Stzhuoyi varchar(10), " +//52����
				"Stmianji varchar(10), " +//53���
				"Stjiatong varchar(200), " +//54��ͨ���
				"Sttaika varchar(5), " +//55̨��
				"Styilabao varchar(5)," +//56������
				"Stmengtie varchar(5), " +//57����
				"Sthaibao varchar(10), " +//58����
				"CompanyInfo varchar(200), " +//59��˾����
				"ShopInfo varchar(200), " +//60�ŵ����
				"ZhaoPai varchar(200), " +//61���Ʋ�
				"Other varchar(200), " + //62����
				"Activityneirong varchar(200), " +//63�����
				"Isduanxin varchar(5), " +//64�Ƿ��Ͷ���
				"Duanxinnrirong varchar(250), " +//65��������
				"ActivityqianyueDate varchar(10), " +//66ǩԼ����
				"ActivityYouhuiDateStart varchar(10), " +//67�Żݿ�ʼ����
				"ActivityYouhuiDateStop varchar(10), " +//68�Żݽ�������
				"Acrivitykazhong varchar(10), " +//69����
				"AcrivityIsNewStay varchar(1), " +//70�Ƿ�פ��
				"AcrivityStayMianji varchar(10), " +//71פ�����
				"AcrivityStayNum varchar(10), " +//72פ������
				"AcrivityStayDateStart varchar(10), " +//73פ�꿪ʼʱ��
				"AcrivityStayDateStop varchar(10), " +//74פ�����ʱ��
				"AcrivityStayTime varchar(2), " +//75פ��ʱ��
				"Acrivityhuodongri varchar(10), " +//76���
				"AcrivityMoneymin varchar(10), " +//77�������
				"AcrivityMoneymax varchar(10), " +//78��������
				"Acrivityreturn varchar(5), " +//79��������
				"AcrivityIsPublish varchar(1), " +//80�Ƿ񷢲�
				"TakePhoneName varchar(200), " +//81��Ƭ����
				"MerNumber varchar(200), "+ //82�̻����
				"mertype varchar(100), " +//83�̻�����
				"whitenum varchar(200),"+//84���������
				"RedShopZhubeia varchar(50),"+//85�̻�ע��1
				"RedShopZhubeib varchar(50),"+//86�̻�ע��2
				"RedShopZhubeic varchar(50),"+//87�̻�ע��3
				"RedShopZhubeid varchar(50))");//88�̻�ע��4
		
		
		//�̻�����ά�����ݱ�
		db.execSQL("create table if not exists shgongdanweihu(" +
				"Id varchar(100) primary key,"+ //0 ����ID
				"Dnum varchar(100)," + //1���̼���
				"DCL varchar(100)," + //2������־λ
				"LX varchar(100)," + //3����
				"TIME varchar(100)," + //4ʱ��
				"StName varchar(100)," + //5�̻�����
				"taskId2 varchar(100)," + //6�ַ�������Id+���ݱ���tdId
				"userName varchar(50),"+ //7Ա����
				"taskId varchar(50)," + //8����Id
				"startGps varchar(50)," + //9ǩ��Gps
				"startTime varchar(50), " + //10ǩ��ʱ��
				"shName varchar(100), " + //11�̻�����
				"shNameCheck varchar(10), " + //12�̻�����ѡ���Ƿ�
				"shNameContent varchar(100), " + //13�̻����Ʊ�ע
				"shAddress varchar(120), " + //14�̻���ַ
				"shAddressCheck varchar(10), " + //15�̻���ַѡ���Ƿ�
				"shAddressComment varchar(120)," + //16�ǵ�ַ��ע
				"shPhoneNum varchar(50), " + //17�̻���ϵ�绰
				"shPhoneNumCheck varchar(10)," + //18��ϵ�绰ѡ���Ƿ�
				"shPhoneNumComment varchar(50),"+ //19��ϵ�绰��ע
				"posIsWork varchar(10),"+ //20 pos���Ƿ���������
				"posIsWorkComment varchar(50)," +//21pos��������ע
				"sbNumFrom varchar(500)," + //22�̱��ϵͳ����
				"sbNumSelect varchar(50)," + //23�̱��ϵͳ����ѡ���Ƿ�
				"sbNumComment varchar(500)," + //24�̱�ϵͳ���ݱ�ע
				"sbNumAdd varchar(210)," + //25�̱����������
				"machineIsWork varchar(10),"+ //26�����Ƿ�����
				"isWorkComment varchar(50),"+ //27�������������ע
				"snNumFrom varchar(500),"+ //28����ϵͳSN��
				"snNumSelect varchar(50),"+ //29����ϵͳSN��ѡ���Ƿ�
				"snNumComment varchar(100),"+ //30����ϵͳSN�ű�ע
				"snNumAdd varchar(60),"+  //31���ߺ�����
				"doorPicName varchar(50),"+//32�������Ƭ����
				"tablePicName varchar(50),"+//33��������Ƭ����
				"checkPicName varchar(50),"+//34��������Ƭ����
				"trainProperty varchar(10),"+//35��ѵ����
				"trainTargetSelect varchar(20),"+ //36��ѵ����ѡ����
				"trainTargetComment varchar(100),"+ //37�������������ע
				"trainContentSelect varchar(20),"+ //38��ѵ����ѡ����
				"trainContentComment varchar(400),"+ //39��ѵ���ݱ�ע
				"principalName varchar(20),"+ //40�̻�����������
				"principalPhoneNum varchar(60),"+  //41�̻������˵绰
				"signPicName varchar(20),"+//42������ǩ��
				"endGps varchar(50),"+//43ǩ��gps��Ϣ
				"endTime varchar(20),"+//44ǩ��ʱ��
				"submitTime varchar(20),"+//45�ύʱ��
				"reservedOne varchar(100),"+//46����1
				"reservedTwo varchar(100),"+//47����2
				"reservedThree varchar(100),"+//48����3
				"reservedFour varchar(100))"); //49����4
		

		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		System.out.println(arg1 + "" + arg2);
		//���ݿ�����ֶ�
//		if(arg2>=1){
//			if(IsTableExists(arg0, "����") && !isColumnExists(arg0, "����", "��ӵ��ֶ�")){
//				arg0.execSQL("alter table ���� add column ��ӵ��ֶ���  varchar(100)");
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
		
		//��������ʧ�����½���
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
		
		//DCL�������ʾλ��LX���ͱ�ʾλ��TIME����ʱ��,meiId�̻�Id
		//arg0.execSQL("drop table st_storeinfo");
		if(IsTableExists(arg0,"st_storeinfo")){
			arg0.execSQL("drop table st_storeinfo");
		}
		arg0.execSQL("create table if not exists st_storeinfo(Id varchar(100) primary key, " +
				"Dnum varchar(100)," +//1
				"DCL varchar(100)," +//2�������ʾλ
				"LX varchar(100)," +//3���ͱ�ʾλ
				"TIME varchar(100)," +//4����ʱ��
				"MId varchar(50)," +//5
				"meiId varchar(50)," +//6�̻�Id
				"ActiveType varchar(100), " +//7�����
				"storeauditId varchar(100)," +//8�ID/Ԥ��ID
				"CityId varchar(100)," +//9����ID
				"AreaId varchar(100), " +//10������ID
				"MaintainUserId varchar(100), " +//11ά����Ա����
				"ExpandUserId varchar(100), " +//12��չ��Ա����
				"IsNewCompany varchar(10), " +//13�Ƿ��½���˾
				"CompanyId varchar(100)," +//14��˾ID
				"CompanyName varchar(100), " +//15��˾����
				"CompanyLicense varchar(100)," +//16��˾Ӫҵִ��
				"LegalPerson varchar(100), " +//17����
				"ContactUser varchar(100), " +//18��ϵ��
				"TelNumber varchar(100), " +//19��ϵ�绰
				"MobileNumber varchar(100), " +//20�ֻ�
				"EngageName varchar(100), " +//21��Ӫ��
				"Adress varchar(100), " +//22ע���ַ
				"EngageScope varchar(100), " +//23��Ӫ��Χ
				"Brand varchar(100), " +//24Ʒ��
				"BrandTelNumber varchar(100), " +//25Ʒ������
				"PostCode varchar(100), " +//26�ʱ�
				"Validity varchar(100), " +//27��Ч��
				"EngageYear varchar(100)," +//28��Ӫ����
				"IsNewStore varchar(10), " +//29�Ƿ������ŵ�
				"StId varchar(100), " +//30�ŵ�ID
				"StLicense varchar(100),  " +//31�ŵ깤��ִ��
				"StName varchar(100), " +//32�ŵ�����
				"Stel varchar(100), " +//33Ӫҵ�绰
				"StAdress varchar(100), " +//34Ӫҵ��ַ
				"StPostCode varchar(100), " +//35�ʱ�
				"StHangye varchar(100), " +//36��ҵ
				"StCaixi varchar(100)," +//37��ϵ
				"StShangquan varchar(100), " +//38��Ȧ
				"StNumber varchar(100), " +//39�̻����
				"StClientNumber varchar(100), " +//40�ն˺�
				"Stlong varchar(100), " +//41����
				"Stlat varchar(100), " +//42γ��
				"StContact varchar(100), " +//43��ϵ��
				"StMobileNumber varchar(100), " +//44�ֻ�
				"StChannel varchar(100), " +//45��չ����
				"StIsReceiveBill varchar(100), " +//46�Ƿ��յ�
				"StValidity varchar(100), " +//47��Ч��
				"StBillRate varchar(100), " +//48�յ�����
				"StParkSpace varchar(100), " +//49ͣ��λ
				"StBuinessHours varchar(100), " +//50Ӫҵʱ��
				"Stzhuoyi varchar(100), " +//51����
				"Stmianji varchar(100), " +//52���
				"Stjiatong varchar(100), " +//53��ͨ
				"Sttaika varchar(100), " +//54̫��
				"Styilabao varchar(100), " +//55������
				"Stmengtie varchar(100), " +//56����
				"Sthaibao varchar(100), " +//57����
				"CompanyInfo varchar(100), " +//58��˾����
				"BrandInfo varchar(100), " +//59Ʒ�ƽ���
				"StoreInfo varchar(100), " +//60�ŵ����
				"ZhaoPai varchar(100), " +//61���Ʋ�
				"AvgExpense varchar(100), " +//62�˾�����
				"Other varchar(100), " +//63����
				"ExpenseDate varchar(100), " +//64��������
				"CardNumber varchar(100), " +//65����
				"IdentityCode varchar(100), " +//66��Ȩ��
				"Price varchar(100), " +//67���׽��
				"ContractDate varchar(20), " +//68ǩԼ����
				"EndTime varchar(20), " +//69�Żݿ�ʼʱ��
				"BenginTime varchar(20), " +//70�Żݽ�ֹʱ��
				"IsExtension varchar(10), " +//71�Ƿ���Լ
				"IsSentMessage varchar(10), " +//72�Ƿ��Ͷ���
				"CardType varchar(50), " +//73����
				"MessageContent varchar(100), " +//74��������
				"PrivilegeContent varchar(100), " +//75�Ż�����
				"SpecialContent varchar(100)," +//76�ر�Լ��
				"YesNXieYi varchar(20)," +//77�Ƿ���ǩЭ��
				"ContractNumber varchar(100)," +//78ǩԼЭ���
				"XuanChuan varchar(100)," +//79����������
				"PiceXuanChuan varchar(100)," +//80�������̻����
				"Zhihangye varchar(100)," +//81����ҵ
				"PiceXuanChuan2 varchar(100)," +//82Э��绰��
				"PiceXuanChuan3 varchar(100)," +//83ǩԼ����
				"PiceXuanChuan4 varchar(100)," +//84Э����Ч��������
				"Gcityid varchar(100)," +//85��˾��������ID
				"Gdistrictid varchar(100)," +//86��˾����������ID
				"AttLicense varchar(60)," +//87Ӫҵִ�ո���
				"AttContract varchar(60)," +//88����Э��
				"AttAccredit varchar(60)," +//89��Ȩ��
				"AttOthers varchar(60)," +//90��������				
				"youhuiquanneirong varchar(400)," +//91�Ż�����
				"youxiaoBeginTime varchar(20)," +//92�Żݿ�ʼʱ��
				"youxiaoStopTime varchar(20),"+//93�Żݽ���ʱ��
				"ShopZhubeia varchar(20),"+//94�̻�ע��A
				"ShopZhubeib varchar(20),"+//95�̻�ע��B
				"ShopZhubeic varchar(20),"+//96�̻�ע��C
				"ShopZhubeid varchar(20),"+//97�̻�ע��D
				"ActiveZhubeia varchar(20),"+//98�ע��A
				"ActiveZhubeib varchar(20),"+//99�ע��B
				"ActiveZhubeic varchar(20),"+//100�ע��C
				"ActiveZhubeid varchar(20),"+//101�ע��D
				"ActiveMoney varchar(20),"+//102��������
				"ActiveDate varchar(20),"+//103���������
				"ActiveDateTime varchar(20),"+//104���
				"HasFaBu varchar(20),"+//105�Ƿ񷢲�
				"hasyouhuiquan varchar(10))");//106�Ƿ����Ż�ȯ
		
		//�޸ĸ�����������red_enterletter
		//arg0.execSQL("drop table red_enterletter");
		if(IsTableExists(arg0,"red_enterletter")){
			arg0.execSQL("drop table red_enterletter");
		}
		arg0.execSQL("create table if not exists red_enterletter(" +
				"Id varchar(100) primary key," +//1����ID
				"Dnum varchar(100), " +//2���̼���
				"DCL varchar(100), " +//3������־λ
				"LX varchar(20), " +//4����
				"MId varchar(50), " +//5����ID
				"TIME varchar(100), " +//6ʱ��
				"Activityhangye varchar(50), " +//7��ҵ�
				"IsNewCompany varchar(1), " +//8�Ƿ�������˾
				"CompanyId varchar(50)," +//9��˾ID
				"CompanyLicense varchar(50), " +//10��˾Ӫҵִ��
				"CompanyName varchar(60)," +//11��˾��
				"LegalPerson varchar(60), " +//12����
				"ContactUser varchar(60), " +//13��ϵ��
				"TelNumber varchar(20)," +//14��ϵ�绰
				"MobileNumber varchar(20), " +//15�ֻ�
				"ManageName varchar(60), " +//16��Ӫ��
				"Adress varchar(60), " +//17ע���ַ
				"ManageScope varchar(60), " +//18��Ӫ��Χ
				"Brand varchar(60), " +//19Ʒ��
				"BrandTelNumber varchar(20)," +//20Ʒ������
				"PostCode varchar(6)," +//21�ʱ�
				"Validity varchar(20)," +//22��Ч��
				"EngageYear varchar(10)," +//23��Ӫ����
				"TreatyNumber varchar(50)," +//24������Э���
				"CompanyCity varchar(50), " +//25��˾��������
				"CompanyArea varchar(50), " +//26��˾����������
				"IsNewShop varchar(1)," +//27�Ƿ������ŵ�
				"ShopId varchar(50)," +//28�ŵ�ID
				"ShopLicense varchar(50)," +//29�ŵ깤��ִ��
				"BusinessName varchar(60)," +//30Ӫҵ����
				"BusinessNunber varchar(20)," +//31Ӫҵ�绰
				"BusinessAdress varchar(60)," +//32Ӫҵ��ַ
				"ShopCode varchar(6)," +//33�ŵ��ʱ�
				"ShopTrade varchar(50)," +//34�ŵ���ҵ
				"ShopGreens varchar(50)," +//35�ŵ��ϵ
				"ShopDealer varchar(50)," +//36�ŵ���Ȧ
				"Longitude varchar(20)," +//37����
				"LatiTude varchar(20)," +//38γ��
				"ShopUserName varchar(60)," +//39�ŵ���ϵ��
				"ShopTelNumber varchar(20)," +//40�ŵ�绰
				"ShopMobileNumber varchar(20)," +//41�ŵ��ֻ�
				"StChannel varchar(1)," +//42��չ����
				"StIsReceiveBill varchar(1)," +//43�Ƿ��յ�
				"StValidity varchar(20)," +//44��Ч��
				"StBillRate varchar(20)," +//45�յ�����
				"ShopCity varchar(50), " +//46�ŵ���������
				"ShopArea varchar(50), " +//47�ŵ�����������
				"IsNewPoster varchar(1)," +//48�Ƿ��½�����
				"PosterID varchar(50)," +//49�̻�ID
				"StParkSpace varchar(10), " +//50ͣ��λ
				"StBuinessHours varchar(200), " +//51Ӫҵʱ��
				"Stzhuoyi varchar(10), " +//52����
				"Stmianji varchar(10), " +//53���
				"Stjiatong varchar(200), " +//54��ͨ���
				"Sttaika varchar(5), " +//55̨��
				"Styilabao varchar(5)," +//56������
				"Stmengtie varchar(5), " +//57����
				"Sthaibao varchar(10), " +//58����
				"CompanyInfo varchar(200), " +//59��˾����
				"ShopInfo varchar(200), " +//60�ŵ����
				"ZhaoPai varchar(200), " +//61���Ʋ�
				"Other varchar(200), " + //62����
				"Activityneirong varchar(200), " +//63�����
				"Isduanxin varchar(5), " +//64�Ƿ��Ͷ���
				"Duanxinnrirong varchar(250), " +//65��������
				"ActivityqianyueDate varchar(10), " +//66ǩԼ����
				"ActivityYouhuiDateStart varchar(10), " +//67�Żݿ�ʼ����
				"ActivityYouhuiDateStop varchar(10), " +//68�Żݽ�������
				"Acrivitykazhong varchar(10), " +//69����
				"AcrivityIsNewStay varchar(1), " +//70�Ƿ�פ��
				"AcrivityStayMianji varchar(10), " +//71פ�����
				"AcrivityStayNum varchar(10), " +//72פ������
				"AcrivityStayDateStart varchar(10), " +//73פ�꿪ʼʱ��
				"AcrivityStayDateStop varchar(10), " +//74פ�����ʱ��
				"AcrivityStayTime varchar(2), " +//75פ��ʱ��
				"Acrivityhuodongri varchar(10), " +//76���
				"AcrivityMoneymin varchar(10), " +//77�������
				"AcrivityMoneymax varchar(10), " +//78��������
				"Acrivityreturn varchar(5), " +//79��������
				"AcrivityIsPublish varchar(1), " +//80�Ƿ񷢲�
				"TakePhoneName varchar(200), " +//81��Ƭ����
				"MerNumber varchar(200), "+ //82�̻����
				"mertype varchar(100), " +//83�̻�����
				"whitenum varchar(200),"+//84���������
				"RedShopZhubeia varchar(50),"+//85�̻�ע��1
				"RedShopZhubeib varchar(50),"+//86�̻�ע��2
				"RedShopZhubeic varchar(50),"+//87�̻�ע��3
				"RedShopZhubeid varchar(50))");//88�̻�ע��4


		
		//�޸ĸ����̻�����ά����shgongdanweihu
		//arg0.execSQL("drop table shgongdanweihu");
		if(IsTableExists(arg0,"shgongdanweihu")){
			arg0.execSQL("drop table shgongdanweihu");
		}
		arg0.execSQL("create table if not exists shgongdanweihu(" +
				"Id varchar(100) primary key,"+ //0 ����ID
				"Dnum varchar(100)," + //1���̼���
				"DCL varchar(100)," + //2������־λ
				"LX varchar(100)," + //3����
				"TIME varchar(100)," + //4ʱ��
				"StName varchar(100)," + //5�̻�����
				"taskId2 varchar(100)," + //6�ַ�������Id+���ݱ���tdId
				"userName varchar(50),"+ //7Ա����
				"taskId varchar(50)," + //8����Id
				"startGps varchar(50)," + //9ǩ��Gps
				"startTime varchar(50), " + //10ǩ��ʱ��
				"shName varchar(100), " + //11�̻�����
				"shNameCheck varchar(10), " + //12�̻�����ѡ���Ƿ�
				"shNameContent varchar(100), " + //13�̻����Ʊ�ע
				"shAddress varchar(120), " + //14�̻���ַ
				"shAddressCheck varchar(10), " + //15�̻���ַѡ���Ƿ�
				"shAddressComment varchar(120)," + //16�ǵ�ַ��ע
				"shPhoneNum varchar(50), " + //17�̻���ϵ�绰
				"shPhoneNumCheck varchar(10)," + //18��ϵ�绰ѡ���Ƿ�
				"shPhoneNumComment varchar(50),"+ //19��ϵ�绰��ע
				"posIsWork varchar(10),"+ //20 pos���Ƿ���������
				"posIsWorkComment varchar(50)," +//21pos��������ע
				"sbNumFrom varchar(500)," + //22�̱��ϵͳ����
				"sbNumSelect varchar(50)," + //23�̱��ϵͳ����ѡ���Ƿ�
				"sbNumComment varchar(500)," + //24�̱�ϵͳ���ݱ�ע
				"sbNumAdd varchar(210)," + //25�̱����������
				"machineIsWork varchar(10),"+ //26�����Ƿ�����
				"isWorkComment varchar(50),"+ //27�������������ע
				"snNumFrom varchar(500),"+ //28����ϵͳSN��
				"snNumSelect varchar(50),"+ //29����ϵͳSN��ѡ���Ƿ�
				"snNumComment varchar(100),"+ //30����ϵͳSN�ű�ע
				"snNumAdd varchar(60),"+  //31���ߺ�����
				"doorPicName varchar(50),"+//32�������Ƭ����
				"tablePicName varchar(50),"+//33��������Ƭ����
				"checkPicName varchar(50),"+//34��������Ƭ����
				"trainProperty varchar(10),"+//35��ѵ����
				"trainTargetSelect varchar(20),"+ //36��ѵ����ѡ����
				"trainTargetComment varchar(100),"+ //37�������������ע
				"trainContentSelect varchar(20),"+ //38��ѵ����ѡ����
				"trainContentComment varchar(400),"+ //39��ѵ���ݱ�ע
				"principalName varchar(20),"+ //40�̻�����������
				"principalPhoneNum varchar(60),"+  //41�̻������˵绰
				"signPicName varchar(20),"+//42������ǩ��
				"endGps varchar(50),"+//43ǩ��gps��Ϣ
				"endTime varchar(20),"+//44ǩ��ʱ��
				"submitTime varchar(20),"+//45�ύʱ��
				"reservedOne varchar(100),"+//46����1
				"reservedTwo varchar(100),"+//47����2
				"reservedThree varchar(100),"+//48����3
				"reservedFour varchar(100))"); //49����4

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