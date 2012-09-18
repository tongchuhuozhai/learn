package bc.com;

import java.util.List;

import android.net.Uri;

public class GlobalSetting {
	public static final int TEMOBI_3G = 0;
	public static final int TEMOBI_2G = 1;
	public static final int HUAWEI = 2;
	public static final int SYSTEM = 3;
	public static int PLAYER_PROVIDER = HUAWEI;

	public static final int WAP = 0;
	public static final int NET = 1;
	public static int CONNECTION_THROUGH = WAP;

	public static final String HUA_WEI_PLAYER_ARGS = "&codeFormat=H264&isRtspPrefix=0&is3gpSuffix=0";
	public static final String RONG_CH_PLAYER_ARGS = "&codeFormat=TIVC";

	public static String TAG_COMPLETE_VIDEO = ".3gp";
	public static String TAG_COMPLETE_MUSIC = ".mp4";
	public static final String TAG_SUFFIX = ".part";

	/**
	 *clientType:0 或不传 默认为旗舰融创高清 ; clientType:1 旗舰融创普通; clientType:2 旗舰H264版
	 */
	public static String IF_SIMPLE_VERSION = "&clientType=0";

	/**
	 * 控制播放器参数
	 */
	public static String PLAYER_ARGS_END = RONG_CH_PLAYER_ARGS;

	public static boolean ifDisplayOutside = false;

	/**
	 * 初始化播放器
	 * 
	 * @param playerType
	 *            ——
	 * @param apnType
	 */
	public static void init(int playerType, int apnType, boolean javaDisplay) {
		PLAYER_PROVIDER = playerType;
		CONNECTION_THROUGH = apnType;
		ifDisplayOutside = javaDisplay;
		if (PLAYER_PROVIDER == TEMOBI_3G) {
			PLAYER_ARGS_END = RONG_CH_PLAYER_ARGS;
			TAG_COMPLETE_VIDEO = ".cmtv";
			TAG_COMPLETE_MUSIC = ".mp4";
			IF_SIMPLE_VERSION = "&clientType=0";
		} else if (PLAYER_PROVIDER == TEMOBI_2G) {
			PLAYER_ARGS_END = RONG_CH_PLAYER_ARGS;
			TAG_COMPLETE_VIDEO = ".cmtv";
			TAG_COMPLETE_MUSIC = ".mp4";
			IF_SIMPLE_VERSION = "&clientType=1";
		} else if (PLAYER_PROVIDER == HUAWEI) {
			PLAYER_ARGS_END = HUA_WEI_PLAYER_ARGS;
			TAG_COMPLETE_VIDEO = ".3gp";
			TAG_COMPLETE_MUSIC = ".m4a";
			IF_SIMPLE_VERSION = "&clientType=2";
		} else if (PLAYER_PROVIDER == SYSTEM) {
			PLAYER_ARGS_END = HUA_WEI_PLAYER_ARGS;
			TAG_COMPLETE_VIDEO = ".3gp";
			TAG_COMPLETE_MUSIC = ".m4a";
			IF_SIMPLE_VERSION = "&clientType=2";
		}
	}

	public static final int PLAY_TYPE_LOCAL = 0;
	public static final int PLAY_TYPE_VIDEO = 1;
	public static final int PLAY_TYPE_LIVE = 2;
	public static final int PLAY_TYPE_LIVE_BACK = 3;

	public static final int CLIENT_STATUS_MESSAGE_PAGE = 0;
	public static final int CLIENT_STATUS_LOGIN_PAGE = 1;
	public static final int CLIENT_STATUS_INDEX_PAGE = 2;
	/**
	 * 客户端正在登陆
	 */
	public static int CLIENT_CURRENT_STATUS = CLIENT_STATUS_MESSAGE_PAGE;

	/**
	 * 8分钟更新当次请求数据
	 */
	public static final long updateCacheTimes = 1000 * 60 * 1;

	public static String LOCAL_PARAM_SOCKET_NAME = "";

	/**
	 * 下面四个常量用来控制首页是否需要完全刷新
	 */
	public static int LOADING_RECOMMED_SIZE = -1;
	public static int LOADING_PACKAGE_SIZE = -1;
	public static int INDEX_RECOMMED_SIZE = -1;
	public static int INDEX_PACKAGE_SIZE = -1;

	/**
	 * 上传用户类型
	 */
	public static final String USER_STATUS_NOT_WHITE_USER = "01";
	public static final String USER_STATUS_IS_WHITE_USER_NOT_REG = "02";
	public static final String USER_STATUS_IS_WHITE_USER_AND_REG = "03";
	public static final String USER_STATUS_UNKNOWN = "==";
	public static String USER_VALID_STATUS = USER_STATUS_UNKNOWN;

	public static final String STORED_INSIDE_SDCARD = "/external_sd";
	public static final String APK_DOWNLOAD_ITEM = "/.mobilevideo/update_upk/";
	public static final String DOWNLOAD_MIDDLE_PATH = "/.mobilevideo/download/";

	/**
	 * UGC上传协议
	 */
	public static final String UGC_SERVER_URL = "http://c2.cmvideo.cn/ugcapp/uploadFile/";
	public static final String UGC_REQUEST_UPLOAD_URL = UGC_SERVER_URL
			+ "UGC_GetUploadUrl.html";
	public static final String UGC_REQUEST_CURRENT_UPLOAD_POSITION = "UGC_GetLastUploadPos.html?";
	public static final String UGC_UPLOAD_URL = "UGC_FileUpload.html?";
	public static final String UGC_CHECK_USER_URL = UGC_SERVER_URL
			+ "UGC_CheckUser.html?T_TYPE=003";
	public static final String UGC_USER_REGISTER = UGC_SERVER_URL
			+ "UGC_RegUser.html";

	public static final String ENCODING_UTF8 = "UTF-8";
	public static String apType = "";
	public static int apnId = -1;
	public static final String APTYPE_WAP = "wap";
	public static final String APTYPE_INTERNET = "internet";
	public static String CMCC_WAP_PROXY_HOST = "10.0.0.172";
	public static int CMCC_WAP_PROXY_PORT = 80;
	public static String CMCC_WAP_PROXY_PORT_STR = CMCC_WAP_PROXY_PORT + "";
	public static final int TIME_OUT_TIME = 20 * 1000;
	public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
	public static final String LAST_MODIFIED = "last-modified";

	// public static String userAgent = ProductProperties
	// .get(ProductProperties.USER_AGENT_KEY);

	public static String userAgent = "SAMSUNG_GT-I7680_TD/1.0 OPhone";
	// public static String userAgent = "";
	// ProductProperties.get(ProductProperties.USER_AGENT_KEY);

	/**
	 * 播放器参数
	 */
//	public static String v_rendertype = "POSTBUFFER";
//	public static String v_format = "PIXEL_FORMAT_RGB_565";
	public static String v_rendertype = "CANVAS";
	public static String v_format = "";

	/**
	 * 解请求返回的状态，收藏，预约，推荐等等
	 */
	public static final int XML_RESULT = 0;
	/**
	 * 解首页节目包
	 */
	public static final int XML_PACKAGE = XML_RESULT + 1;
	/**
	 * 解首页推荐
	 */
	public static final int XML_RECOMMEND = XML_PACKAGE + 1;
	/**
	 * 解一级栏目页
	 */
	public static final int XML_MULTIMENU = XML_RECOMMEND + 1;
	/**
	 * 接二级栏目页
	 */
	public static final int XML_CHANNEL = XML_MULTIMENU + 1;
	/**
	 * 解直播列表
	 */
	public static final int XML_CHANNEL_LIVE = XML_CHANNEL + 1;
	/**
	 * 解点播列表
	 */
	public static final int XML_CHANNEL_VIDEO = XML_CHANNEL_LIVE + 1;
	/**
	 * 解点播内容
	 */
	public static final int XML_CONTENT_VIDEO = XML_CHANNEL_VIDEO + 1;
	/**
	 * 解播放,下载，需要订购返回
	 */
	public static final int XML_ORDER_INFO = XML_CONTENT_VIDEO + 1;
	/**
	 * 解搜索返回结果
	 */
	public static final int XML_SEARCH = XML_ORDER_INFO + 1;
	/**
	 * 解投票结果
	 */
	public static final int XML_VOTE_RESULT = XML_SEARCH + 1;
	/**
	 * 解收藏的直播
	 */
	public static final int XML_FAVORITE = XML_VOTE_RESULT + 1;

	/**
	 * 解登录鉴权
	 */
	public static final int XML_LOGIN = XML_FAVORITE + 1;
	/**
	 * 请求收藏列表
	 */
	public static final int XML_COLLOCT_LIST = XML_LOGIN + 1;
	/**
	 * 解排行榜栏目列表
	 */
	public static final int XML_HOTRANKLIST_CHANNEL = XML_COLLOCT_LIST + 1;
	/**
	 * 解大包月订购详情
	 */
	public static final int XML_BIG_ORDERDETAIL = XML_HOTRANKLIST_CHANNEL + 1;
	/**
	 * 订购列表
	 */
	public static final int XML_ORDER_LIST = XML_BIG_ORDERDETAIL + 1;

	public static long SERVER_TODAY = 0;
	public static String DATE_TODAY = null;
	public static String DATE_TOMORROW = null;
	public static String DATE_YESTODAY = null;
	public static String DATE_YESTODAY_BEFOR = null;
	public static long TIME_DIFFERENCE = 0L;

	/**
	 * 播放器页面默认的纯音频切换图片
	 */
	public static String PLAYER_PIC_LIST_DEFAULT_ROOT = null;

	public static List<String> PLAYER_PIC_DEFAULT_LIST = null;

	public static int SCREEN_TYPE;

	public static final int SCREEN_480x320 = 1;
	public static final int SCREEN_800x480 = 2;
	public static final int SCREEN_640x360 = 3;
	public static final int SCREEN_854x480 = 4;
	public static final int SCREEN_400X240 = 5;
	public static final int SCREEN_1024X600 = 6;
	public static final int SCREEN_320X240 = 7;

	public static float SCREEN_DENCITY = 160;

	// 下载文件目录
	public static String FILE_DOWNLOAD_PATH = "";
	// upk升级文件目录
	public static String UPK_DOWNLOAD_PATH = "";
	// 首页登录图片，首页节目包图片
	public static String CACHE_PIC_MAIN = "";
	// 内容图片
	public static String CACHE_PIC_CONTENT = "";

	public static final Uri CONTACT_URI = Uri
			.parse("content://contacts/combined");
	public static final Uri CONTACT_PHONE_URI = Uri
			.parse("content://contacts/people"); // People.CONTENT_URI;
	public static final Uri CONTACT_SIM_URI = Uri.parse("content://icc/adn"); // sim

	public static final int DATA_CONNECTION_STATE_NO_SIM = 1;// 没有sim卡
	public static final int DATA_CONNECTION_STATE_RETRY_FAILED = 2;// 重试失败

	public static Boolean IS_EXITING = false;
}
