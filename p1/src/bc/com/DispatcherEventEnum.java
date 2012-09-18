package bc.com;

import java.lang.reflect.Field;

import android.os.Message;

public class DispatcherEventEnum {

	// ==================================================
	// System Event Group
	// ==================================================
	public static final int SYSTEM_EVENT_BEGIN = 0;

	public static final int SYSTEM_EVENT_OPEN_DATACONNECTION_SUCCESS = SYSTEM_EVENT_BEGIN + 1;
	public static final int SYSTEM_EVENT_OPEN_DATACONNECTION_FAIL = SYSTEM_EVENT_OPEN_DATACONNECTION_SUCCESS + 1;
	public static final int SYSTEM_EVENT_PHONE_STATUS_RING = SYSTEM_EVENT_OPEN_DATACONNECTION_FAIL + 1;
	public static final int SYSTEM_EVENT_PHONE_STATUS_OFFHOOK = SYSTEM_EVENT_PHONE_STATUS_RING + 1;
	public static final int SYSTEM_EVENT_PHONE_STATUS_IDLE = SYSTEM_EVENT_PHONE_STATUS_OFFHOOK + 1;
	public static final int SYSTEM_EVENT_MEDIA_EJECT = SYSTEM_EVENT_PHONE_STATUS_IDLE + 1;
	public static final int SYSTEM_EVENT_MEDIA_MOUNTED = SYSTEM_EVENT_MEDIA_EJECT + 1;
	public static final int SYSTEM_EVENT_PHONE_POWER_DOWN = SYSTEM_EVENT_MEDIA_MOUNTED + 1;
	public static final int SYSTEM_EVENT_SOUND_OFF = SYSTEM_EVENT_PHONE_POWER_DOWN + 1;
	public static final int SYSTEM_EVENT_SOUND_ON = SYSTEM_EVENT_SOUND_OFF + 1;
	public static final int SYSTEM_EVENT_ALERM_ON = SYSTEM_EVENT_SOUND_ON + 1;
	public static final int SYSTEM_EVENT_SCREEN_OFF = SYSTEM_EVENT_ALERM_ON + 1;
	public static final int SYSTEM_EVENT_SCREEN_ON = SYSTEM_EVENT_SCREEN_OFF + 1;
	public static final int SYSTEM_EVENT_ALERM_COMEIN = SYSTEM_EVENT_SCREEN_ON + 1;
	public static final int SYSTEM_EVENT_AIR_MODE_COME = SYSTEM_EVENT_ALERM_COMEIN + 1;

	public static final int SYSTEM_EVENT_END = 1000;

	// ==================================================
	// Player Event Group
	// ==================================================
	public static final int PLAYER_EVENT_BEGIN = 1001;

	/**
	 * 准备好
	 */
	public static final int PLAY_EVENT_STATE_ONPREPARED = PLAYER_EVENT_BEGIN + 1;
	/**
	 * seek完成
	 */
	public static final int PLAY_EVENT_STATE_ONSEEKCOMPLETE = PLAY_EVENT_STATE_ONPREPARED + 1;
	/**
	 * 开始缓冲
	 */
	public static final int PLAY_EVENT_STATE_ONBUFFERINGBEGIN = PLAY_EVENT_STATE_ONSEEKCOMPLETE + 1;
	/**
	 * 缓冲完成
	 */
	public static final int PLAY_EVENT_STATE_ONBUFFERINGCOMPLETE = PLAY_EVENT_STATE_ONBUFFERINGBEGIN + 1;
	/**
	 * 缓冲进度比
	 */
	public static final int PLAY_EVENT_STATE_ONBUFFERINGUPDATE = PLAY_EVENT_STATE_ONBUFFERINGCOMPLETE + 1;
	/**
	 * 播放完成
	 */
	public static final int PLAY_EVENT_STATE_ONCOMPLETION = PLAY_EVENT_STATE_ONBUFFERINGUPDATE + 1;
	/**
	 * 播放错误
	 */
	public static final int PLAY_EVENT_STATE_ONERROR = PLAY_EVENT_STATE_ONCOMPLETION + 1;
	/**
	 * size改变
	 */
	public static final int PLAY_EVENT_STATE_ONVIDEOSIZECHANGED = PLAY_EVENT_STATE_ONERROR + 1;

	/**
	 * 播放器进度
	 */
	public static final int PLAY_EVENT_STATE_PLAY_POSITION = PLAY_EVENT_STATE_ONVIDEOSIZECHANGED + 1;

	public static final int PLAYER_EVENT_END = 2000;

	// ==================================================
	// Download Event Group
	// ==================================================
	public static final int DL_EVENT_BEGIN = 2001;

	/**
	 * The event indicates that download list changed. Include item added,
	 * removed, updated.
	 */
	public static final int DL_EVENT_DL_LIST_CHANGED = DL_EVENT_BEGIN + 1;

	/**
	 * The event indicates that download task start.
	 * 
	 * msg.obj is the download task.
	 */
	public static final int DL_EVENT_DL_TASK_START = DL_EVENT_DL_LIST_CHANGED + 1;

	/**
	 * The event indicates that download task canceled.
	 * 
	 * msg.obj is the download task.
	 */
	public static final int DL_EVENT_DL_TASK_CANCELED = DL_EVENT_DL_TASK_START + 1;

	/**
	 * The event indicates that download progress.
	 * 
	 * msg.arg1 is the download size. msg.arg2 is the total size. msg.obj is the
	 * download task.
	 */
	public static final int DL_EVENT_DL_TASK_PROGRESS = DL_EVENT_DL_TASK_CANCELED + 1;

	/**
	 * The event indicates that download task failed.
	 * 
	 * msg.obj is the download task.
	 */
	public static final int DL_EVENT_DL_TASK_FAILED = DL_EVENT_DL_TASK_PROGRESS + 1;

	/**
	 * The event indicates that download task completed.
	 * 
	 * msg.obj is the download task.
	 */
	public static final int DL_EVENT_DL_TASK_COMPLETE = DL_EVENT_DL_TASK_FAILED + 1;

	/**
	 * The event indicates that download task delete.
	 * 
	 * msg.obj is the download task.
	 */
	public static final int DL_EVENT_DL_TASK_DEL = DL_EVENT_DL_TASK_COMPLETE + 1;

	public static final int DL_EVENT_DL_TASK_EXISTS = DL_EVENT_DL_TASK_DEL + 1;

	public static final int DL_EVENT_DL_TASK_NOSDCARD = DL_EVENT_DL_TASK_EXISTS + 1;

	public static final int DL_EVENT_DL_TASK_NOSPACE = DL_EVENT_DL_TASK_NOSDCARD + 1;
	/**
	 * The event indicates that download the DRM rights successfully.
	 * 
	 * msg.obj is the MMHttpTask that download the rights.
	 */
	public static final int DL_EVENT_GET_DRM_RIGHTS_SUCCESS = DL_EVENT_DL_TASK_NOSPACE + 1;

	/**
	 * The event indicates that download the DRM rights fail.
	 * 
	 * msg.obj is the MMHttpTask that download the rights.
	 */
	public static final int DL_EVENT_GET_DRM_RIGHTS_FAIL = DL_EVENT_GET_DRM_RIGHTS_SUCCESS + 1;

	public static final int DL_EVENT_END = 3000;

	// ==================================================
	// HTTP Event Group
	// ==================================================
	public static final int HTTP_EVENT_BEGIN = 3001;

	public static final int HTTP_EVENT_TASK_START = HTTP_EVENT_BEGIN + 1;

	public static final int HTTP_EVENT_TASK_COMPLETE = HTTP_EVENT_TASK_START + 1;

	public static final int HTTP_EVENT_TASK_FAIL = HTTP_EVENT_TASK_COMPLETE + 1;

	public static final int HTTP_EVENT_TASK_CANCELED = HTTP_EVENT_TASK_FAIL + 1;

	public static final int HTTP_EVENT_TASK_PROCESS = HTTP_EVENT_TASK_CANCELED + 1;

	public static final int HTTP_EVENT_END = 4000;

	// 上传消息定义
	public static final int UPLOAD_EVENT_BEGIN = 4001;
	public static final int UPLOAD_EVENT_DL_TASK_START = UPLOAD_EVENT_BEGIN + 1;
	public static final int UPLOAD_EVENT_DL_TASK_CANCELED = UPLOAD_EVENT_DL_TASK_START + 1;
	public static final int UPLOAD_EVENT_DL_TASK_PROGRESS = UPLOAD_EVENT_DL_TASK_CANCELED + 1;
	public static final int UPLOAD_EVENT_DL_TASK_FAILED = UPLOAD_EVENT_DL_TASK_PROGRESS + 1;
	public static final int UPLOAD_EVENT_DL_TASK_COMPLETE = UPLOAD_EVENT_DL_TASK_FAILED + 1;
	public static final int UPLOAD_EVENT_DL_TASK_DEL = UPLOAD_EVENT_DL_TASK_COMPLETE + 1;
	public static final int UPLOAD_EVENT_DL_TASK_EXISTS = UPLOAD_EVENT_DL_TASK_DEL + 1;
	public static final int UPLOAD_EVENT_DL_TASK_NOSDCARD = UPLOAD_EVENT_DL_TASK_EXISTS + 1;
	public static final int UPLOAD_EVENT_DL_TASK_NOSPACE = UPLOAD_EVENT_DL_TASK_NOSDCARD + 1;
	public static final int UPLOAD_EVENT_END = 5000;

	public static String getString(int msgCode) {
		Field[] fs = DispatcherEventEnum.class.getDeclaredFields();
		for (Field f : fs) {
			try {
				if (f.getType() == Integer.TYPE && f.getInt(null) == msgCode) {
					return f.getName();
				}
			} catch (IllegalAccessException e) {

			}
		}

		return "Unknown Event Type";
	}

	public static boolean isSystemEvent(Message msg) {
		return (msg.what > SYSTEM_EVENT_BEGIN && msg.what < SYSTEM_EVENT_END) ? true
				: false;
	}

	public static boolean isPlayerEvent(Message msg) {
		return (msg.what > PLAYER_EVENT_BEGIN && msg.what < PLAYER_EVENT_END) ? true
				: false;
	}

	public static boolean isDLEvent(Message msg) {
		return (msg.what > DL_EVENT_BEGIN && msg.what < DL_EVENT_END) ? true
				: false;
	}

	public static boolean isHttpEvent(Message msg) {
		return (msg.what > HTTP_EVENT_BEGIN && msg.what < HTTP_EVENT_END) ? true
				: false;
	}

	public static boolean isUploadEvent(Message msg) {
		return (msg.what > UPLOAD_EVENT_BEGIN && msg.what < UPLOAD_EVENT_END) ? true
				: false;
	}
}
