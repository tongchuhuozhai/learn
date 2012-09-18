package bc.com;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;

public class CacheIndex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3133744782572292001L;

	public long id;
	public String cacheUrl;
	/**
	 * 数据库中查出来的code
	 */
	public int cacheUrlHashCode;
	public String cacheVersion;
	public long cacheLong = 0;
	public int cacheType = -1;
	public int parseXmlType;
	public byte[] xmlData;

	public static final int CACHE_TYPE_DELETE = 1;// 退出就删除
	public static final int CACHE_TYPE_SAVE = 2;// 退出不删除
	public static final int CACHE_TYPE_ACTION = 3;// 不保存xml，action请求

	public static final String ID = "_id";
	public static final String CACHEURL = "cacheUrl";
	public static final String CACHEVERSION = "cacheVersion";
	public static final String PARSEXMLTYPE = "parseXmlType";
	public static final String XMLDATA = "xmlData";
	public static final String CACHETYPE = "cacheType";
	public static final String CACHELONG = "cacheLong";

	public static final String NAME = "cacheindex";
	public static final String SQLNAME = "CREATE TABLE IF NOT EXISTS cacheindex ( _id  integer primary key autoincrement,"
			+ " cacheUrl text , cacheVersion text, parseXmlType text , cacheType text, xmlData text ,cacheLong long);";

	public static ContentValues cacheIndexToContentValues(CacheIndex index) {
		ContentValues initialValues = new ContentValues();
		if (index.cacheUrl != null) {
			initialValues.put("cacheUrl", index.cacheUrl.hashCode());
			initialValues.put("cacheVersion", index.cacheVersion);
			initialValues.put("xmlData", index.xmlData);
			initialValues.put("parseXmlType", index.parseXmlType);
			initialValues.put("cacheType", index.cacheType);
			initialValues.put("cacheLong", index.cacheLong);
		}
		return initialValues;
	}

	public int flushType = -1;
	public Map<String, Object> dataMap = new HashMap<String, Object>();
	public Map<String, Object> mustRemmenberInfo = new HashMap<String, Object>();

	// /**
	// * 数据库中是否存在，用来确认Http请求返回时更新数据还是直接存储，该状态不存入数据库
	// */
	// public boolean isExsit = false;//
	// /**
	// * 是否需要马上请求数据
	// */
	// public boolean needRequestNow = false;

	/**
	 * 数据库中数据存储的类型
	 */
	public int dataType = DATA_TYPE_NO;
	public static final int DATA_TYPE_NO = 0;// 0 数据库中数据不存在
	public static final int DATA_TYPE_YES_OUT_USE = 1;// 1 数据库中存在但是不过期
	public static final int DATA_TYPE_YES_IN_USE = 2;// 2 数据库中存在但是过期

	/**
	 * mvhttptask 类型，减少线程中的if-else判断
	 */
	public int httpType = HTTP_TYPE_NORMAL;
	public static final int HTTP_TYPE_NORMAL = 0;
	public static final int HTTP_TYPE_SPECIAL = 1;

	/**
	 * 1 正常 2 wml 3 not found 4 socket exception 5
	 */
	public int returnDataType = 1;

	/**
	 * apk 下载速度
	 */
	public String apkDownloadProcess = "0";

	public boolean needParseInHttpComplete = true;

	public boolean needUpdateInHttpComplete = true;
	
	public boolean media_out_need_finish = false;

	public CacheIndex clone() {
		CacheIndex in = new CacheIndex();
		in.cacheType = cacheType;
		in.cacheLong = cacheLong;
		in.cacheUrl = cacheUrl;
		in.cacheVersion = cacheVersion;
		in.parseXmlType = parseXmlType;
		in.dataMap = dataMap;
		in.flushType = flushType;
		in.id = id;
		in.mustRemmenberInfo = mustRemmenberInfo;
		in.returnDataType = returnDataType;
		in.needParseInHttpComplete = needParseInHttpComplete;
		in.media_out_need_finish = media_out_need_finish;
		return in;
	}
}
