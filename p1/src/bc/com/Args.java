package bc.com;

public interface Args {

	public static final String SAVE_MENUXMLID = "menuXmlId";
	public static final String SAVE_ICON_ID = "Icon_id";
	public static final String SAVE_MSG_ID = "msg_id";
	public static final String SAVE_FIXBTN = "fixBtn";
	public static final String SAVE_CANCELBTN = "cancelBtn";
	public static final String SAVE_TYPE = "SAVE_type";
	public static final String SAVE_LONGCLICKTYPE = "longClickType";
	public static final String SAVE_CURRENTVOTESELECT = "currentVoteSelect";
	public static final String SAVE_CURRENTCONTENTPOSITION = "currentContentPosition";
	public static final String SAVE_MULTIDELCONTENTLIST = "multidelcontentlist";
	public static final String SAVE_INDEX_SMS_POSITION = "index_sms_position";
	public static final String SAVE_LIVE_HOSTORY_TO_MULTI = "live_hostory_to_multi";
	public static final String SAVE_DOWNLOAD_DETAIL = "download_detail";
	public static final String SAVE_SMS_CURRENT_POSITION = "sms_current_position";
	public static final String SAVE_SMS_CURRENT_GROUPH = "sms_current_grouph";
	public static final String SAVE_DOWNLOAD_DETAIL_BOOLEAN = "download_detail_boolean";
	public static final String SAVE_LONG_CLICK_BOOLEAN = "long_click_BOOLEAN";
	public static final String SAVE_DOWNLOADCANCEL_BOOLEAN = "isDownloadCancelShowing_boolean";
	public static final String SAVE_DETAIL= "detail_2";
	public static final String SAVE_DETAIL_POSITION= "detail_position";
	

	public static final String SAVE_DOWNLOAD_ITEM = "download_item";

	public static final String SPECIAL_REQUEST = "special_request";

	public static final String PICLENGTH = "login_chg_pic_len";

	public static final String HOTRANK = "hot_rank";

	public static final String FEE = "fee_detail";

	public static final String ACTIVITY_RESULT = "activity_result";
	public static final int ACTIVITY_RESULT_VOTE_REPLAYER = 0;
	public static final int ACTIVITY_RESULT_VOTE_DETAIL = 1;
	public static final int ACTIVITY_RESULT_VOTE_RETURN = 2;

	/**
	 * 当前一级栏目页选择的是那个一级栏目
	 */
	public static final String CURRENTFIRSTPARTID = "currentFirstPartId";

	public static final String PREFER_BG_PATH = "prefer_bg_path";
	public static final String PREFER_BG_SIZE = "prefer_bg_size";
	public static final String PREFER_DOWNLOAD_ROOT_PATH = "download_root_path";
	public static final String PREFER_SMS_ROOT_URL = "sms_root_url";
	public static final String PREFER_LOGIN_USER_ID = "login_user_id";

	/**
	 * 纯音频
	 */
	public static final int MEDIA_TYPE_MUSIC = 0;
	public static final int MEDIA_TYPE_VIDEO = 1;

	/**
	 * 栏目类型
	 */
	public static final int CHANNEL_TYPE_LIVE = 2; // 直播
	public static final int CHANNEL_TYPE_VIDEO = 1; // 点播

	public static final String DEFAULT_VALUE = "Default_Value";

	/**
	 * must传递常量
	 */
	public static String MEDIA_TYPE = "media_type";

	/**
	 * 播放类型，点播，直播，直播回放，本地
	 */
	public static String PLAYER_ARGS = "player_Args";

	/**
	 * 节目列表当前位置
	 */
	public static String CONTENTPOSITION = "contentPosition";
	/**
	 * 节目列表
	 */
	public static String CONTENTLIST = "contentList";
	/**
	 * 频道列表
	 */
	public static String CHANNELLIST = "chooseList";

	public static String CHANNELPOSITION = "channelPosition";

	public static String CONTENT = "content";
	/**
	 * 当前在一级栏目选择的是哪个二级栏目
	 */
	public static String CURRENTCHANNELID = "currentChannelId";

	/**
	 * 一级栏目页到二级栏目页时的二级栏目列表
	 */
	public static String SUPERTOCHANNEL_MULTILIST = "superTochannel_multiList";

	/**
	 * 二级栏目页当前选择的是直播频道中的哪一天
	 */
	public static String CHANNELPAGE_CURRENTLIVETAB = "currentLiveTab";

	/**
	 * 二级栏目当前选择的是点播频道哪个节目
	 */
	public static String CHANNELPAGE_CURRENTCONTENTPOSITION = "currentContentPosition";

	public static String COLLECT_NAME = "collect_name";

	public static String TITLE_NAME = "title_name_2";

	public static final String MULTI_DELETE_LIST = "del_list_object";

	public static String DETAIL_PIC_NAME = "detail_pic_name";

	/**
	 * intent 首页推荐xml到首页
	 */
	public static final String INTENT_RECOM_TO_INDEX = "intent_recom_to_index";

	/**
	 * intent 首页节目包xml到首页
	 */
	public static final String INTENT_PACKAGE_TO_INDEX = "intent_package_to_index";

	/**
	 * 短信内容
	 */
	public static final String INTENT_SMS_TO_INDEX = "intent_sms_to_index";

	public static final String INTENT_TO_NEXT_PAGE = "intent_to_next_page";

	public static final String SEARCH_KEYWORD = "search_keyword";

	/**
	 * xml解析常量
	 */
	public static final String XML_PACKAGELIST = "packageList";
	public static final String XML_SEARCHKEYWORD = "searchkeyword";
	public static final String XML_SEARCHKEYWORDROOT = "searchkeywordroot";
	public static final String XML_NOTICELIST = "noticeList";
	public static final String XML_RANKINGVIDEO = "rankingVideo";
	public static final String XML_RECOMMENDVIDEO = "recommendVideo";
	public static final String XML_LIVECONTENTLIST = "liveContentList";
	public static final String XML_VIDEOCONTENTLIST = "videoContentList";
	public static final String XML_MULTILIST = "multiList";
	public static final String XML_RANKCHANNELLIST = "rankChannelList";
	public static final String XML_VOTE = "vote";
	public static final String XML_VIDEOCONTENT = "videoContent";
	public static final String XML_SEARCHRESULT = "searchResult";
	public static final String XML_SEARCHRECOMMED = "searchRecommed";
	public static final String XML_STATUS = "status";
	public static final String XML_ORDERLIST = "orderList";
	public static final String XML_VIDEOORMUSIC = "videoOrMusic";
	public static final String XML_PLAYORDOWNURL = "playOrDownUrl";
	public static final String XML_DOWNLOADSIZE = "downloadSize";
	public static final String XML_VOTERECOMMEND = "voterecommend";
	public static final String XML_VOTEUSERRECOMMED = "voteuserrecommed";
	public static final String XML_PACKAGEIMAGE = "packageImage";
	public static final String XML_LOGIN_TODAY_login = "login_today";
	public static final String XML_UPDATEFLAG_login = "updateFlag ";
	public static final String XML_UPDATEURL_login = "updateUrl";
	public static final String XML_BASEURL_login = "baseUrl";
	public static final String XML_PACKAGEURL_login = "packageURl";
	public static final String XML_RECOMMENDURL_login = "recommendUrl";
	public static final String XML_MYORDER_login = "myoderUrl";
	public static final String XML_MYCOLLECT_login = "mycollectUrl";
	public static final String XML_RECOMMEDFRIEND_login = "recommendfrientUrl";
	public static final String XML_FAVORITLIST = "favoritList";
	public static final String XML_PRODUCTUNITNAME = "productUnitName";
	public static final String XML_PHONENO = "phonenumber";
	public static final String XML_UUID = "xml_uuid";
	public static final String XML_CONTENT_PLAY_PICURL = "content_play_picurl";
	public static final String XML_CONTENT_PLAY_PICLIST = "content_play_picList";

	/**
	 * status状态集合
	 */
	public static final String STATUS_SUCCESS = "001";
	public static final String STATUS_FAIL = "002";
	/**
	 * 已经收藏
	 */
	public static final String STATUS_HAS_COLLECT = "012";
}
