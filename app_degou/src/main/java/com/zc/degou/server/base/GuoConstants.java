package com.zc.degou.server.base;

/**
 * @ClassName: GuoConstants
 * @Description: TODO(甯搁噺锟�
 * @author 钂嬮噾锟�jjhhappy@sina.com
 * @date 2011-10-20 涓嬪崍04:23:41
 * 
 */
public interface GuoConstants {

	/**
	 * 服务器端常量的设�?(手机端向服务器端发�?数据的规�?
	 */

	/* 数据请求�?��的标志的数位个数 */
	public static final int SOCKET_START_FLAG_DIGIT_LENGTH = 2;
	/* 数据请求�?��的标�? */
	public static final String SOCKET_START_FLAG = "ST";
	/* 图片上传�?��的标�? */
	public static final String SOCKET_START_FLAG_PICTURE_UPLOAD = "SP";
	/* 数据请求结束的标�? */
	public static final String SOCKET_END_FLAG = "OV";

	/* 数据包头部表示该数据长度的数位个�? */
	public static final int SOCKET_LENGTH_DIGIT_LENGTH = 4;

	/* 业务处理类型的数位个�? */
	public static final int BUSINESS_TYPE_DIGIT_LENGTH = 5;

	/* 数据库�?择标志位的个�? */
	public static final int BUSINESS_DB_TYPE_DIGIT_LENGTH = 2;

	/* 数据请求的处理类的名称的数位个数 */
	public static final int BUSINESS_CLASSNAME_DIGIT_LENGTH = 8;

	public static final String BUSINESS_DB_TYPE_DEFAULT = "00";
	public static final String BUSINESS_DB_TYPE1_FLAG = "01";
	public static final String BUSINESS_DB_TYPE2_FLAG = "02";
	public static final String BUSINESS_DB_TYPE3_FLAG = "03";

	/**
	 * BUSINESS_TYPE** 系列介绍 前面的�?0”表示是从手机端发�?过来的处理类�?01~03 是第�?��业务
	 * 处理的重要模型，都是通过socket获得相应的处理类名称 从�?做出相应的处�?04 未定�?
	 */

	// /* 第一类业务类型中的最短数据长�?*/
	// public static final int SOCKET_DATALENGTH_MIN = 64;

	/* 主要的业务处理，从数据库中获得数�? */
	public static final String BUSINESS_TYPE01 = "00001";

	/* 主要的业务判断，通过数据库获取判断信�? */
	public static final String BUSINESS_TYPE02 = "02001";

	/* 以后可能通过手机传�?信息，是的服务器端开始一些操�? */
	public static final String BUSINESS_TYPE03 = "03001";

	/* 为以后留出来的第二类业务类型，和上面的三种不是同�?��类型的socket分析模板 */
	public static final String BUSINESS_TYPE04 = "04001";

	/* sokect通讯结束标志 */
	public static final String BUSINESS_TYPEOVER = "over";

	/* 各个参数在数据中的分隔符 */
	public static final String PARA_SEPERATOR = "♣";
	// public static final String PARA_SEPERATOR = "&";
	public static final String PARA_EQUALS = "‖";
	public static final String MAP_SEPERATOR = "╬";

	/* 参数中类名的标题 */
	public static final String PARA_CLASSNAME = "PARA_CLASSNAME";

	/* UDP定时判断工具每两次判断之间的时间长度 */
	public static final int UDP_TIMEINGCHECK_PERIOD_LENGTH = 30 * 60 * 1000;
	/* UDP请求中优惠到期信息的个数 */
	public static final int UDP_PREFERENCE_COUNT_DIGITAL_LENGTH = 3;

	/* UDP异常时间长度 */
	public static final int UDP_TIMEINGCHECK_PERIOD_LENGTH_EXCEPTION = 30 * 60 * 1000;

	// 手机两次优惠劵到期信息请求之间的时间间隔
	public static final int MOBIL_REQUEST_PREFERENCE_LIMIT_PERIOD = 60 * 60 * 24;
	// public static final int MOBIL_REQUEST_PREFERENCE_LIMIT_PERIOD = 60; //
	// 测试时用的时间间�?
	/* 手机向后台请求优惠劵到期信息的标�? */
	public static final String MOBIL_REQUEST_PREFERENCE_FLAG = "PREFERENCE_FLAG";

	/**
	 * 手机端常量的设定 (服务器向手机端端发�?数据的规�?
	 */

	/* 在手机应用程序中保存用户数据的key值 */
	public static final String MOBIL_USER_NAME = "MOBIL_USER_NAME";
	public static final String MOBIL_USER_NAME_DEFAULT = "FLOWERTREEINFO";
	public static final String MOBIL_USER_PASSWORD = "MOBIL_USER_PASSWORD";
	public static final String MOBIL_USER_PASSWORD_DEFAULT = "88888888";
	public static final String MOBIL_USER_ID_DEFAULT = "10000000";
	public static final String MOBIL_USER_INVOTE_CODE = "USER_INVOTE_CODE";
	public static final String MOBIL_LAST_PUSHMESSAGE_ID = "LAST_PUSHMESSAGE_ID";
	public static final String MOBIL_LOCATION_CITYNAME = "MOBIL_LOCATION_CITYNAME";
	public static final String MOBIL_LOCATION_LONGITUDE = "MOBIL_LOCATION_LONGITUDE";
	public static final String MOBIL_LOCATION_LATITUDE = "MOBIL_LOCATION_LATITUDE";
	public static final String MOBIL_SMS_REQUEST_TIME = "MOBIL_SMS_REQUEST_TIME"; // 短信验证码请求时间
	public static final String MOBIL_SMS_INTERVAL = "MOBIL_SMS_INTERVAL"; // 短信验证码请求间隔
	public static final String MOBIL_SMS_TELNUM = "MOBIL_SMS_TELNUM"; // 短信号码
	public static final String MOBIL_USER_VIP = "MOBIL_USER_VIP"; // 用户VIP等级
	// 手机端本身存储的上一次向后台请求优惠劵到期信息的时间
	public static final String MOBIL_REQUEST_PREFERENCE_LIMIT_TIME = "REQUEST_PREFERENCE_LIMIT_TIME";

	/* 向后台请求数据的类型 默认类型 */
	public static final int MOBILE_REQUEST_TYPE_DEFAULT = 0;
	/* 向后台请求数据的类型 图片上传 */
	public static final int MOBILE_REQUEST_TYPE_PICUPLOAD = 1;

	/* 手机上获取数据时，进度条等待的最长时�? */
	public static final int MOBIL_GETDATAVIEW_OUTTIME = 10000;

	/* 手机上的数据请求服务的每次等待处理时间的时长，单位毫 */
	public static final int MOBIL_SERVICE_TCP_WAITTIME = 10;

	/* 数据请求的标志的数位个数 */
	public static final int MOBIL_SOCKET_START_FLAG_DIGIT_LENGTH = 2;
	/* 数据�?��的标�? */
	public static final String MOBIL_SOCKET_START_FLAG = "ST";
	/* 数据结束的标�? */
	public static final String MOBIL_SOCKET_END_FLAG = "OV";

	/* 数据类型的标志的数位个数 */
	public static final int MOBIL_DATA_TYPE_DIGIT_LENGTH = 2;

	/* 数据类型是文字数�? */
	public static final String MOBIL_DATA_TYPE_01 = "01";

	/* 数据类型是错误信�? */
	public static final String MOBIL_DATA_TYPE_02 = "02";

	/* 数据包头部表示该数据长度的数位个�? */
	public static final int MOBIL_SOCKET_LENGTH_DIGIT_LENGTH = 4;

	/* 数据出现错误时，是否显示的标志位 */
	public static final int MOBIL_ERROR_SHOW_FLAG_DIGIT_LENGTH = 1;

	/* 数据错误信息显示标志 */
	public static final String MOBIL_ERROR_SHOW_YES = "Y";

	/* 错误信息不显示标�? */
	public static final String MOBIL_ERROR_SHOW_NO = "N";

	/**
	 * 服务器的相关属�?
	 */

	/* 服务器的地址 */
	// 正式服务器地址
//	public static final String PCSERVER_IP = "www.greenchinanet.cn";
	// 胡柯的服务器地址
	 public static final String PCSERVER_IP = "192.168.1.34";

	/* 在广播中向服务发送请求数据广播的标志 */
	public static final String SERVER_REQUEST = "server_request_flowertreeinfo";

	/* 在广播中包含的请求参数的标签 */
	public static final String SERVER_REQUEST_FLAG = "server_request_flag";

	/* 在广播中向应用发送广播的标志 */
	public static final String SERVER_RESPONSE_DATA_FLAG = "SERVER_RESPONSE_DATA_FLAG";

	public static final String UDP_MESSAGE_TITLE = "PS000";

	/**
	 * 手机端的UDP服务参数
	 */

	public static final String UDP_PCSERVER_IP = "www.greenchinanet.cn"; // 正式服务器推送IP地址
	// public static final String UDP_PCSERVER_IP = "192.168.1.100";
	// //测试服务器推送IP地址

	// public static final int UDP_SERVICE_PORT = 5051; //正式服务器推送端口
	public static final int UDP_SERVICE_PORT = 5052; // 新版本推送服务器端口

	/* 服务器下载数据的端口 */
	public static final int PCSERVER_PORT = 6051; // TCP请求正式端口
	// public static final int PCSERVER_PORT = 5051; //TCP请求测试端口

	public static final String UPD_SERVICE_ADDRESS = "";

	public static final int UDP_PACKAGE_LENGTH_MAX = 1000;
	// /*UDP数据参数的分隔符*/

	/* UDP数据�?��标志的长�? */
	public static final int UDP_FLAG_DIGIT_LENGTH = 2;
	/* UDP数据心跳标志 */
	public static final String UDP_FLAG_HEART = "HT";
	/* UDP数据推�?标志 */
	public static final String UDP_FLAG_PUSH = "PS";
	/* 手机端两次�?知之间的�?��时间间隔 */
	public static final long UDP_PUSH_NOTIFICATION_PERIOD_LENGTH = 1000l * 30;
	/* 手机端用来存储上次�?知时间的标志 */
	public static final String UDP_PUSH_NOTIFICATION_LASTTIME = "UDP_PUSH_NOTIFICATION_LASTTIME";

}
