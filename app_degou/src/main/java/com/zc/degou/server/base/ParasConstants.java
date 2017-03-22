package com.zc.degou.server.base;

/**
 * @ClassName: ParasConstants
 * @Description: TODO(各个页面请求的参�?
 * @author 蒋金�?jjhhappy@sina.com
 * @date Jan 1, 2012 2:26:52 PM
 * 
 */
public interface ParasConstants {

	/*
	 * 第一个界面参�?首页频道选择界面
	 */

	// 请求参数
	// 城市ID
	public static final String REQ1_CITYID = "CITY_ID";
	// 默认城市的ID
	public static final String REQ1_CITYID_DEFAULT = "1";
	public static final String REQ1_CITYNAME_DEFAULT = "苏州";
	
	// 系统本地存储的商户个�?
	public static final String REQ1_MERCHANT_COUNT = "MERCHANT_COUNT";
	// 本地商户ID的前�?
	public static final String REQ1_MERCHANT_ID_ ="REQ1_MERCHANT_ID_";
//	// 本地商户ID的上次的登录时间
//	public static final String REQ1_MERCHANT_LAST_TIME = "MERCHANT_LAST_TIME_";
	
	// 返回参数
	// 频道ID
	public static final String RET1_CHANNEL_ID = "CHANNEL_ID";
	// 频道中文名称
	public static final String RET1_CHANNEL_NAME_CH = "CHANNEL_NAME_CH";
	// 频道英文名称
	public static final String RET1_CHANNEL_NAME_EN = "CHANNEL_NAME_EN";
	// 频道图片地址
	public static final String RET1_CHANNEL_PIC_LOCATION = "CHANNEL_PIC_LOCATION";
	// 城市经度
	public static final String RET1_CITY_LONGITUDE = "CITY_LONGITUDE";
	// 城市纬度
	public static final String RET1_CITY_LATITUDE = "CITY_LATITUDE";
	// 城市名称
	public static final String RET1_CITY_NAME = "CITY_NAME";
	// 到期优惠信息的个�?
	public static final String RET1_COUNT_PREFERENCE = "COUNT_PREFERENCE";
	// 关注的商户有新的优惠信息
	public static final String RET1_COUNT_ATTENTION_NUMBER = "COUNT_ATTENTION_NUMBER";
	// 关注的商户ID
	public static final String RET1_ATTENTION_MERCHANT_ID_ = "ATTENTION_MERCHANT_ID_";
	public static final String RET1_ATTENTION_PREFENCE_ID_ = "ATTENTION_PREFENCE_ID_";
	
	/*
	 * 第二个界面参�?频道列表界面
	 */

	// 请求参数
	// 频道ID
	public static final String REQ2_CHANNEL_ID = "CHANNEL_ID";
	// 商户显示页码
	public static final String REQ2_MERCHANT_PAGE_INDEX = "MERCHANT_PAGE_INDEX";
	// 商户单页显示容量
	public static final String REQ2_MERCHANT_PAGE_CAPABILITY = "MERCHANT_PAGE_CAPABILITY";

	// 返回参数
	// 主打商户的ID
	public static final String RET2_MAIN_MERCHANT_ID = "MAIN_MERCHANT_ID";
	// 主打商户的视频图片地�?
	public static final String RET2_MAIN_PIC_LOCATION = "MAIN_PIC_LOCATION";
	// 主打商户的视频地�?
	public static final String RET2_MAIN_VIDEO_LOCATION = "MAIN_VIDEO_LOCATION";
	// 主打商户的视频密�?
	public static final String RET2_MAIN_VIDEO_PASSWORD = "MAIN_VIDEO_PASSWORD";
	// 主打商户的视频对应的特惠ID
	public static final String RET2_MAIN_VIDEO_PREFERNCEID = "MAIN_VIDEO_PREFERNCEID";
	// 主打商户的简�?
	public static final String RET2_MAIN_MERCHANT_INTRODUCTION = "MAIN_MERCHANT_INTRODUCTION";
	// 商户ID
	public static final String RET2_MERCHANT_ID = "MERCHANT_ID";
	// 商户中文名称
	public static final String RET2_MERCHANT_NAME_CH = "MERCHANT_NAME_CH";
	// 商户英文名称
	public static final String RET2_MERCHANT_NAME_EN = "MERCHANT_NAME_EN";
	// 商户的简�?
	public static final String RET2_MERCHANT_INTRODUCTION = "MERCHANT_INTRODUCTION";
	// 商户图片地址
	public static final String RET2_MERCHANT_PIC_LOCATION = "MERCHANT_PIC_LOCATION";

	/*
	 * 第三个界面参�?商户详细界面
	 */

	// 请求参数
	// 商户ID
	public static final String REQ3_MERCHANT_ID = "MERCHANT_ID";

	// 返回参数
	// 商户的名�?
	public static final String RET3_MERCHANT_NAME_CH = "MERCHANT_NAME_CH";
	public static final String RET3_MERCHANT_NAME_EN = "MERCHANT_NAME_EN";
	// 商户�?��
	public static final String RET3_MERCHANT_INTRODUCTION = "MERCHANT_INTRODUCTION";
	// 商户主要视频图片
	public static final String RET3_MERCHANT_MAIN_PIC_LOCATION = "MERCHANT_MAIN_PIC_LOCATION";
	// 商户主要视频地址
	public static final String RET3_MERCHANT_MAIN_VIDEO_LOCATION = "MAIN_VIDEO_LOCATION";
	// 商户主要视频密码
	public static final String RET3_MERCHANT_MAIN_VIDEO_PASSWORD = "MERCHANT_MAIN_VIDEO_PASSWORD";
	// 主打商户的视频对应的特惠ID
	public static final String RET3_MERCHANT_MAIN_VIDEO_PREFERNCEID = "MERCHANT_MAIN_VIDEO_PREFERNCEID";
	// 商户预约电话
	public static final String RET3_MERCHANT_TEL_BOOKING = "MERCHANT_TEL_BOOKING";
	// 商户位置经度
	public static final String RET3_MERCHANT_LONGITUDE = "MERCHANT_LONGITUDE";
	// 商户位置纬度
	public static final String RET3_MERCHANT_LATITUDE = "MERCHANT_LATITUDE";
	// 商户展示图片地址
	public static final String RET3_MERCHANT_PIC_LOCATIONS = "MERCHANT_PIC_LOCATIONS_";
	// Pic Note
	public static final String RET3_MERCHANT_PIC_NOTES = "MERCHANT_PIC_NOTES_";
	// 商户的打折信�?
	public static final String RET3_MERCHANT_ONSELL = "MERCHANT_ONSELL";
	// 商户评论的图片地�?
	public static final String RET3_APPRAICE_MARK = "APPRAICE_MARK";
	// 商户评论的作�?
	public static final String RET3_APPRAICE_WRITER = "APPRAICE_WRITER";
	// 商户评论的内�?
	public static final String RET3_APPRATCE_REMARK = "APPRATCE_REMARK";
	public static final String RET3_APPRAICE_PICLOCATION = "APPRAICE_PICLOCATION";
	
	// 商户优惠个数
	public static final String RET3_PREFERENCE_NUMBER = "PREFERENCE_NUMBER";
	// 商户优惠ID
	public static final String RET3_PREFERENCE_ID_ = "PREFERENCE_ID_";
	// 商户平均评分
	public static final String RET3_AVG_MARK = "RET3_AVG_MARK";
	

	
	public static final String RET3_VOTE_DEVICE_CODE="DEVICE_CODE";
	
	public static final String RET3_ATTENTION_DEVICE_CODE="DEVICE_CODE";
	 
	public static final String RET3_VOTE_RESULT = "RESULT";
	
	public static final String RET3_ATTENTION_RESULT = "RESULT";
	
	public static final String RET3_VOTE_NOTE = "NOTE";
	
	public static final String RET3_ATTENTION_NOTE = "NOTE";
	
	public static final String  RET3_VOTE_COUNT="VOTE";
	
	public static final String RET3_ATTENTION_FAVOURATE_STATUS="FAVOURATE_STATUS";
	
	//public static final String RET3_ATTENTION="false";
	/*
	 * 第四个界面参�?频道选择界面
	 */

	// 请求参数 �?

	// 返回参数
	// 城市的ID
	public static final String RET4_CITY_ID = "CITY_ID";
	// 城市的名�?
	public static final String RET4_CITY_NAME_CH = "CITY_NAME_CH";
	public static final String RET4_CITY_NAME_EN = "CITY_NAME_EN";
	// 城市的经纬度
	public static final String RET4_CITY_LONGITUDE = "CITY_LONGITUDE";
	public static final String RET4_CITY_LATITUDE = "CITY_LANTITUDE";

	/*
	 * 第五个界面参�?精确查询界面
	 */

	// 请求参数
	// 商户名称
	public static final String REQ5_MERCHANT_NAME_CH = "MERCHANT_NAME_CH";
	// 查询的频道ID
	public static final String REQ5_CHANNEL_ID = "CHANNEL_ID";

	// 返回参数
	// 商户的ID
	public static final String RET5_MERCHANT_ID = "MERCHANT_ID";
	// 商户中文�?
	public static final String RET5_MERCHANT_NAME_CH = "MERCHANT_NAME_CH";
	// 商户英文�?
	public static final String RET5_MERCHANT_NAME_EN = "MERCHANT_NAME_EN";
	// 商户图片地址
	public static final String RET5_MERCHANT_PIC_LOCATION = "MERCHANT_PIC_LOCATION";
	// 商户�?��
	public static final String RET5_MERCHANT_INTRODUCTION = "MERCHANT_INTRODUCTION";
	// 商户打折
	public static final String RET5_MERCHANT_ONSELL_LEVEL = "MERCHANT_ONSELL_LEVEL";
	// 商户经度
	public static final String RET5_MERCHANT_LONGITUDE = "MERCHANT_LONGITUDE";
	// 商户纬度
	public static final String RET5_MERCHANT_LATITUDE = "MERCHANT_LATITUDE";
	// 商户注册时间
	public static final String RET5_MERCHANT_REGIST_TIME = "MERCHANT_REGIST_TIME";

	/*
	 * 第六个界面参�?附近界面
	 */

	// 请求参数
	// 经度
	public static final String REQ6_LONGITUDE = "LONGITUDE";
	// 纬度
	public static final String REQ6_LATITUDE = "LATITUDE";
	// 经度允许的差�?
	public static final String REQ6_LONGITUDE_DISTANCE = "LONGITUDE_DISTANCE";
	// 纬度允许的差�?
	public static final String REQ6_LATITUDE_DISTANCE = "LATITUDE_DISTANCE";

	// 返回参数
	// 商户的ID
	public static final String RET6_MERCHANT_ID = "MERCHANT_ID";
	// 商户中文�?
	public static final String RET6_MERCHANT_NAME_CH = "MERCHANT_NAME_CH";
	// 商户英文�?
	public static final String RET6_MERCHANT_NAME_EN = "MERCHANT_NAME_EN";
	// 商户图片地址
	public static final String RET6_MERCHANT_PIC_LOCATION = "MERCHANT_PIC_LOCATION";
	// 商户�?��
	public static final String RET6_MERCHANT_INTRODUCTION = "MERCHANT_INTRODUCTION";
	// 商户经度
	public static final String RET6_MERCHANT_LONGITUDE = "MERCHANT_LONGITUDE";
	// 商户纬度
	public static final String RET6_MERCHANT_LATITUDE = "MERCHANT_LATITUDE";

	/*
	 * 第七个界面参�?积分界面
	 */

	// 请求参数
	// 经度
	public static final String REQ7_PHONEUSER_NAME = "PHONEUSER_NAME";

	// 返回参数
	// 手机用户的上传积�?
	public static final String RET7_AMASS_UPLOAD = "AMASS_UPLOAD";
	// 手机用户的签到积�?
	public static final String RET7_AMASS_SIGNED = "AMASS_SIGNED";
	// 手机用户的评论积�?
	public static final String RET7_AMASS_APPRAISE = "AMASS_APPRAISE";
	// 手机用户的分享积�?
	public static final String RET7_AMASS_SHARE = "AMASS_SHARE";

	/*
	 * 第八个界面参�?附近界面
	 */

	// 请求参数
	// 二维�?
	public static final String REQ8_BINARY_ID = "BINARY_ID";

	// 返回参数
	// 商户ID
	public static final String RET8_MERCHANT_ID = "MERCHANT_ID";
	public static final String REQ01004_CODE = "CODE";
	public static final String RET01004_TYPE = "TYPE";
	public static final String RET01004_MAIN_VIDEO_URL = "MAIN_VIDEO_URL";
	public static final String RET01004_EXPIRED_TIME = "EXPIRED_TIME";
	public static final String RET01004_PIC_URL = "PIC_URL";
	public static final String RET01004_MERCHANT_NAME_CH = "MERCHANT_NAME_CH";
	public static final String RET01004_VIDEO_URL = "VIDEO_URL";
	public static final String RET01004_MAIN_PIC_URL = "MAIN_PIC_URL";
	

	/*
	 * 第九�?参数 查询 分类显示信息
	 */
	// 请求参数
	// 频道ID
	public static final String REQ9_CHANNEL_ID = "CHANNEL_ID";

	// 返回参数
	// 分类第一�?类型
	public static final String RET9_TYPE_ID_ = "TYPE_ID_";
	public static final String RET9_TYPE_NAME_ = "TYPE_NAME_";
	// 分类第一级的个数
	public static final String RET9_TYPE_NUMBER = "TYPE_NUMBER";
	// 分类项目
	public static final String RET9_TERM_ID_ = "TERM_ID_";
	public static final String RET9_TERM_NAME_ = "TERM_NAME_";
	// 分类项目的个�?
	public static final String RET9_TERM_NUMBER = "TERM_NUMBER";

	/*
	 * 第十�?分类查询界面
	 */
	// 请求参数
	// 频道ID
	public static final String REQ10_CHANNEL_ID = "CHANNEL_ID";
	// 分类的类型ID
	public static final String REQ10_TYPE_ID_ = "TYPE_ID_";
	// 分类的�?个数
	public static final String REQ10_TYPE_NUMBER = "TYPE_NUMBER";

	// 返回的数�?虽然名称不一�?但是内容和第五个界面的参数是�?���?���?
	// 商户的ID
	public static final String RET10_MERCHANT_ID = "MERCHANT_ID";
	// 商户中文�?
	public static final String RET10_MERCHANT_NAME_CH = "MERCHANT_NAME_CH";
	// 商户英文�?
	public static final String RET10_MERCHANT_NAME_EN = "MERCHANT_NAME_EN";
	// 商户图片地址
	public static final String RET10_MERCHANT_PIC_LOCATION = "MERCHANT_PIC_LOCATION";
	// 商户�?��
	public static final String RET10_MERCHANT_INTRODUCTION = "MERCHANT_INTRODUCTION";
	// 商户打折
	public static final String RET10_MERCHANT_ONSELL_LEVEL = "MERCHANT_ONSELL_LEVEL";
	// 商户经度
	public static final String RET10_MERCHANT_LONGITUDE = "MERCHANT_LONGITUDE";
	// 商户纬度
	public static final String RET10_MERCHANT_LATITUDE = "MERCHANT_LATITUDE";
	// 商户注册时间
	public static final String RET10_MERCHANT_REGIST_TIME = "MERCHANT_REGIST_TIME";

	/*
	 * 第十�?�� 评论列表界面
	 */
	// 请求参数
	// 商户ID
	public static final String REQ11_MERCHANT_ID = "MERCHANT_ID";
	// �?��请求的最大评论数
	public static final String REQ11_PAGE_CAPABILITY = "PAGE_CAPABILITY";
	// 请求的第几页
	public static final String REQ11_PAGE_INDEX = "PAGE_INDEX";

	// 返回的数�?
	// 评论ID
	public static final String RET11_APPRAISE_ID = "APPRAISE_ID";
	// 评论者的名称
	public static final String RET11_WRITER_NAME = "WRITER_NAME";
	// 评论时间
	public static final String RET11_TIME = "TIME";
	// 评分
	public static final String RET11_MARK = "MARK";
	// 评语
	public static final String RET11_REMARK = "REMARK";
	// 评论图片地址
	public static final String RET11_PIC_LOCATION = "PIC_LOCATION";
	// 评论视频地址
	public static final String RET11_VIDEO_LOCATION = "VIDEO_LOCATION";
	// 评论的类型：0：对商户的评�?    4：对评论的回�?
	public static final String RET11_TYPE = "TYPE";

	/*
	 * 第十二个 评论提交界面
	 */
	// 请求参数
	// 评分
	public static final String REQ12_MARK = "MARK";
	// 评论内容
	public static final String REQ12_REMARK = "REMARK";
	// 评论图片ID
	public static final String REQ12_PICTURE_ID = "PICTURE_ID";
	// 评论视频ID
	public static final String REQ12_VIDEO_ID = "VIDEO_ID";
	// 评论人名�?
	public static final String REQ12_NAME = "NAME";
	
	public static final String REQ12_MERCHANT_ID = "MERCHANT_ID";

	// 返回的数�?
	// 是不是已经插入成功标�?
	public static final String RET12_OK_FLAG = "OK_FLAG";
	
	/*
	 * 第十三个  用户注册提交
	 */
	
	// 请求参数
	// 用户�?
	public static final String REQ13_USER_NAME = "USER_NAME";
	// 用户密码
	public static final String REQ13_USER_PASSWORD = "USER_PASSWORD";
	// 用户推荐的推荐码
	public static final String REQ13_RECOMMEND_CODE = "RECOMMEND_CODE";
	// 用户注册的手机号
	public static final String REQ13_PHONENUMBER = "PHONENUMBER";
	
	// 返回参数
	// 成功标签
	public static final String RET13_REGIST_FLAG = "REGIST_FLAG";
	// 显示信息
	public static final String RET13_SHOW_MESSAGE ="SHOW_MESSAGE";
	// 该手机用户的�?���?
	public static final String RET13_INVOTE_CODE = "INVOTE_CODE";
	
	/*
	 * 第十四个  用户登录
	 */
	
	// 请求参数
	// 用户�?
	public static final String REQ14_USER_NAME = "USER_NAME";
	// 用户密码
	public static final String REQ14_USER_PASSWORD = "USER_PASSWORD";
	
	// 返回参数
	// 成功标签
	public static final String RET14_LOGGING_FLAG = "LOGGING_FLAG";
	// 显示信息
	public static final String RET14_SHOW_MESSAGE ="SHOW_MESSAGE";
	// 该手机用户的�?���?
	public static final String RET14_INVOTE_CODE = "INVOTE_CODE";
	/*
	 * 第十五个  推�?信息查询
	 */
	
	// 请求参数 �?
	
	// 返回参数
	// 推�?信息ID
	public static final String RET15_MESSAGE_ID = "MESSAGE_ID";
	// 推�?信息标题
	public static final String RET15_TITLE ="TITLE";
	// 推�?信息内容
	public static final String RET15_CONTENT = "CONTENT";
	// 推�?信息对应商户ID
	public static final String RET15_MERCHANT_ID = "MERCHANT_ID";
	
	/*
	 * 第十六个 优惠信息查询
	 */
	
	// 请求参数 
	public static final String REQ16_PAGE_INDEX = "PAGE_INDEX";
	public static final String REQ16_PAGE_CAPABILITY = "PAGE_CAPABILITY";
	
	// 返回参数
	// 优惠ID
	public static final String RET16_PREFERENCE_ID = "PREFERENCE_ID";
	// 优惠信息标题
	public static final String RET16_PREFERENCE_TITLE = "PREFERENCE_TITLE";
	// 优惠信息内容
	public static final String RET16_PREFERENCE_CONTENT = "PREFERENCE_CONTENT";
	// 优惠信息图片地址
	public static final String RET16_PREFERENCE_PIC_LOCATION ="PREFERENCE_PIC_LOCATION";
	// 优惠商户ID
	public static final String RET16_MERCHANT_ID = "MERCHANT_ID";
	
	/*
	 * 第十七个 优惠信息查询
	 */
	
	// 请求参数 
	public static final String REQ17_PAGE_INDEX = "PAGE_INDEX";
	public static final String REQ17_PAGE_CAPABILITY = "PAGE_CAPABILITY";
	// 商户ID
	public static final String REQ17_MERCHANT_ID = "MERCHANT_ID";
	
	// 返回参数
	// 优惠ID
	// 优惠ID
	public static final String RET17_PREFERENCE_ID = "PREFERENCE_ID";
	// 优惠信息标题
	public static final String RET17_PREFERENCE_TITLE = "PREFERENCE_TITLE";
	// 优惠信息内容
	public static final String RET17_PREFERENCE_CONTENT = "PREFERENCE_CONTENT";
	// 优惠信息图片地址
	public static final String RET17_PREFERENCE_PIC_LOCATION ="PREFERENCE_PIC_LOCATION";
	// 优惠�?��时间
	public static final String RET17_PREFERENCE_STARTTIME = "PREFERENCE_STARTTIME";
	// 优惠结束时间
	public static final String RET17_PREFERENCE_ENDTIME = "PREFERENCE_ENDTIME";
	// 优惠信息种类
	public static final String RET17_PREFERENCE_TYPE = "PREFERENCE_TYPE";
	// 商户的二维码
	public static final String RET17_MERCHANT_BINARYCODE = "MERCHANT_BINARYCODE";
	
	
	/**
	 * 第十八个 优惠信息下载
	 */
	// 请求参数
	// 优惠ID
	public static final String REQ18_PREFERENCE_ID = "PREFERENCE_ID";
	// 用户名称
	public static final String REQ18_USERNAME = "USERNAME";
	
	// 返回参数
	// 插入数据库成功标�?
	public static final String RET18_INSERT_OKFLAG = "INSERT_OKFLAG";
	// 优惠ID
	public static final String RET18_PREFERENCE_ID = "PREFERENCE_ID";
	
	/**
	 * 第十九个 优惠劵的使用
	 */
	
	// 请求参数
	// 优惠ID 
	public static final String REQ19_PREFERENCE_ID = "PREFERENCE_ID";
	// 用户名称
	public static final String REQ19_USERNAME = "USERNAME";
	
	// 返回数据
	// 修改数据库成功标�?
	public static final String RET19_UPDATE_OKFLAG = "UPDATE_OKFLAG";
	// 优惠ID
	public static final String RET19_PREFERENCE_ID = "PREFERENCE_ID";
	// 优惠信息标题
	public static final String RET19_PREFERENCE_TITLE = "PREFERENCE_TITLE";
	// 优惠信息内容
	public static final String RET19_PREFERENCE_CONTENT = "PREFERENCE_CONTENT";
	// 优惠信息图片地址
	public static final String RET19_PREFERENCE_PIC_LOCATION = "PREFERENCE_PIC_LOCATION";
	// 优惠�?��时间
	public static final String RET19_PREFERENCE_STARTTIME = "PREFERENCE_STARTTIME";
	// 优惠结束时间
	public static final String RET19_PREFERENCE_ENDTIME = "PREFERENCE_ENDTIME";
	// 优惠信息种类
	public static final String RET19_PREFERENCE_TYPE = "PREFERENCE_TYPE";
	// 商户的二维码
	public static final String RET19_MERCHANT_BINARYCODE = "MERCHANT_BINARYCODE";
	// 商户ID
	public static final String RET19_MERCHANT_ID = "MERCHANT_ID";
	// 优惠劵使用标�?
	public static final String RET19_USE_FLAG = "USE_FLAG";
	
	/**
	 * 第二十个 钱包中手机用户自己的优惠劵信息的获得
	 */
	// 请求参数
	// 用户名称
	public static final String REQ20_USERNAME = "USERNAME";
	
	// 返回数据
	// 优惠ID
	public static final String RET20_PREFERENCE_ID = "PREFERENCE_ID";
	// 优惠信息标题
	public static final String RET20_PREFERENCE_TITLE = "PREFERENCE_TITLE";
	// 优惠信息内容
	public static final String RET20_PREFERENCE_CONTENT = "PREFERENCE_CONTENT";
	// 优惠信息图片地址
	public static final String RET20_PREFERENCE_PIC_LOCATION = "PREFERENCE_PIC_LOCATION";
	// 优惠�?��时间
	public static final String RET20_PREFERENCE_STARTTIME = "PREFERENCE_STARTTIME";
	// 优惠结束时间
	public static final String RET20_PREFERENCE_ENDTIME = "PREFERENCE_ENDTIME";
	// 优惠信息种类
	public static final String RET20_PREFERENCE_TYPE = "PREFERENCE_TYPE";
	// 商户的二维码
	public static final String RET20_MERCHANT_BINARYCODE = "MERCHANT_BINARYCODE";
	// 商户ID
	public static final String RET20_MERCHANT_ID = "MERCHANT_ID";
	// 优惠劵使用标�?
	public static final String RET20_USE_FLAG = "USE_FLAG";
	
	/**
	 * 第二十一�? 用户浏览某个优惠信息后的操作
	 */
	// 请求参数
	// 优惠ID
	public static final String REQ21_MERCHANT_ID = "MERCHANT_ID";
	
	// 返回参数
	// 视频地址
	public static final String RET21_VIDEO_LOCATION = "VIDEO_LOCATION";
	// 视频名称
	public static final String RET21_VIDEO_NAME = "VIDEO_NAME";
	// 视频图片地址
	public static final String RET21_VIDEO_PIC_LOCATION = "VIDEO_PIC_LOCATION";
	// 视频密码
	public static final String RET21_VIDEO_PASSWORD = "VIDEO_PASSWORD";
	// 视频特惠ID
	public static final String RET21_VIDEO_PREFERENCEID = "VIDEO_PREFERENCEID";
	
	/**
	 * 第二十二�? 通过特惠Id抢拍优惠
	 */
	// 请求参数
	// 优惠ID
	public static final String REQ22_PREFERENCE_ID = "PREFERENCE_ID";
	
	// 返回参数
	// 抢拍结果FLAG
	public static final String RET22_OK_FLAG = "OK_FLAG";
	// 优惠信息标题
	public static final String RET22_PREFERENCE_TITLE = "PREFERENCE_TITLE";
	// 优惠信息内容
	public static final String RET22_PREFERENCE_CONTENT = "PREFERENCE_CONTENT";
	// 优惠信息图片地址
	public static final String RET22_PREFERENCE_PIC_LOCATION = "PREFERENCE_PIC_LOCATION";
	// 优惠�?��时间
	public static final String RET22_PREFERENCE_STARTTIME = "PREFERENCE_STARTTIME";
	// 优惠结束时间
	public static final String RET22_PREFERENCE_ENDTIME = "PREFERENCE_ENDTIME";
	// 优惠信息种类
	public static final String RET22_PREFERENCE_TYPE = "PREFERENCE_TYPE";
	// 商户的二维码
	public static final String RET22_MERCHANT_ID = "MERCHANT_ID";
	
	
	// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<积分传�?参数>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static final String AMASS_PASS_PIC_COUNT = "AMASS_PASS_PIC_COUNT";
	public static final String AMASS_PASS_WEIBO_COUNT = "AMASS_PASS_WEIBO_COUNT";
	public static final String AMASS_VIDEO_URL = "AMASS_VIDEO_URL";
}


