//package bc.com;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import android.content.Context;
//import android.net.Uri;
//import android.opengl.GLSurfaceView;
//import android.os.Handler;
//import android.util.Log;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.widget.ViewFlipper;
//
//import com.huawei.PELib.OnBufferingUpdateListener;
//import com.huawei.PELib.OnCompletionListener;
//import com.huawei.PELib.OnErrorListener;
//import com.huawei.PELib.OnPlayTimeListener;
//import com.huawei.PELib.PowerEngine;
//
//public class HuaweiPlayer implements IPlayer, OnBufferingUpdateListener,
//		OnCompletionListener, OnErrorListener, OnPlayTimeListener {
//	private SurfaceView surfaceView;
//	private GLSurfaceView mGLSurfaceView;
//	private ViewFlipper pinpu;
//	private PowerEngine mMediaPlayer = null;
//	// private boolean mIsInitialized = false;
//	private int mediaType = Args.MEDIA_TYPE_VIDEO;
//	private int mVideoWidth;
//	private int mVideoHeigth;
//	private Handler handler;
//
//	private Context context = null;
//
//	private static final MyLogger logger = MyLogger.getLogger();
//
//	public HuaweiPlayer() {
//	}
//
//	@Override
//	public void initPlayer(SurfaceView sView, GLSurfaceView pegView,
//			ViewFlipper pView, Handler playerHandler, Context cxt) {
//		logger.d("---------------------HuaweiPlayer-----------------------");
//		surfaceView = sView;
//		mGLSurfaceView = null;
//		surfaceView.setVisibility(View.VISIBLE);
//		pinpu = pView;
//		handler = playerHandler;
//		context = cxt;
//	}
//
//	@Override
//	public boolean start(long position) {
//		if (null != mMediaPlayer) {
//			startChangePicture();
//			try {
//				mMediaPlayer.setScreenOnWhilePlaying(true);
//				return mMediaPlayer.start((int) position);
//			} catch (Exception e) {
//				logger.e(e);
//				return false;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public boolean start() {
//		logger.d("enter start() in HuaweiPlayer");
//		if (null != mMediaPlayer) {
//			startChangePicture();
//			try {
//				mMediaPlayer.setScreenOnWhilePlaying(true);
//				return mMediaPlayer.start(-1);
//			} catch (Exception e) {
//				logger.e(e);
//				return false;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public void resume(boolean flag) {
//		if (null != mMediaPlayer) {
//			if (flag) {
//				startChangePicture();
//			}
//			try {
//				mMediaPlayer.resume();
//			} catch (Exception e) {
//				logger.e(e);
//			}
//		}
//	}
//
//	@Override
//	public void suspend() {
//		if (null != mMediaPlayer) {
//			stopChangePicture();
//			try {
//				mMediaPlayer.suspend();
//			} catch (Exception e) {
//				logger.e(e);
//			}
//		}
//	}
//
//	@Override
//	public void stop() {
//		if (null != mMediaPlayer) {
//			stopChangePicture();
//			try {
//				mMediaPlayer.stop();
//				logger.d("----stop---");
//			} catch (Exception e) {
//				logger.e(e);
//			}
//		}
//	}
//
//	@Override
//	public void release() {
//		if (null != mMediaPlayer) {
//			stopChangePicture();
//			try {
//				logger.d("---release---");
//				mMediaPlayer.release();
//			} catch (Exception e) {
//				logger.e(e);
//			} finally {
//				mMediaPlayer = null;
//			}
//		}
//	}
//
//	private void startChangePicture() {
//		if (mediaType == Args.MEDIA_TYPE_MUSIC) {
//			logger.d("--start---music---");
//			pinpu.startFlipping();
//		}
//	}
//
//	private void stopChangePicture() {
//		if (mediaType == Args.MEDIA_TYPE_MUSIC) {
//			pinpu.stopFlipping();
//		}
//	}
//
//	@Override
//	public boolean pause() {
//		if (null != mMediaPlayer) {
//			stopChangePicture();
//			try {
//				return mMediaPlayer.pause();
//			} catch (Exception e) {
//				logger.e(e);
//				return false;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public Map<String, String> changeLivePlayUrl(String liveUrl,
//			long trueStart, long trueEnd, float currentProgress, int BARLENGTH) {
//		trueStart = trueStart + 3 * 60 * 1000;
//		Map<String, String> dataMap = new HashMap<String, String>();
//		long temp = trueStart
//				+ (long) ((trueEnd - trueStart) * currentProgress / BARLENGTH);
//		long current = new Date().getTime() + GlobalSetting.TIME_DIFFERENCE;
//		if (current - temp < 3 * 60 * 1000) {
//			temp = current - 3 * 60 * 1000;
//		}
//		Date date = new Date(temp);
//		long position = (date.getTime() - trueStart) / 1000;
//		SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化当前系统日期
//		String start = dateFm.format(date);
//		String end = dateFm.format(trueEnd);
//		String playLiveSeekUrl = liveUrl + "&playbackbegin=" + start
//				+ "&playbackend=" + end;
//		dataMap.put(Args.CONTENTPOSITION, position + "");
//		dataMap.put(Args.XML_PLAYORDOWNURL, playLiveSeekUrl);
//		return dataMap;
//	}
//
//	public void updateDisplay(SurfaceHolder holder) {
//		// if (null != mMediaPlayer) {
//		// try {
//		// mMediaPlayer.setDisplay(holder);
//		// } catch (Exception e) {
//		// logger.e(e);
//		// }
//		// }
//	}
//
//	@Override
//	public boolean isCurrentPlaying() {
//		if (null != mMediaPlayer) {
//			if (mMediaPlayer.getStatus() == PowerEngine.Status_Started) {
//				return true;
//			} else {
//				return false;
//			}
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public boolean isPlayingOrBuffering() {
//		if (null != mMediaPlayer) {
//			if (mMediaPlayer.getStatus() == PowerEngine.Status_Buffering
//					|| mMediaPlayer.getStatus() == PowerEngine.Status_Started) {
//				return true;
//			} else {
//				return false;
//			}
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public long duration() {
//		if (null != mMediaPlayer)
//			return mMediaPlayer.getDuration();
//		return 0;
//	}
//
//	@Override
//	public long seek(long whereto) {
//		if (null != mMediaPlayer)
//			try {
//				if (mMediaPlayer.isSeekable()) {
//					mMediaPlayer.seekTo((int) whereto);
//					logger.d("-------seek---position-----" + whereto);
//				} else {
//					logger.d("---can not----seek---position-----");
//				}
//
//			} catch (Exception e) {
//				logger.e(e);
//			}
//		return whereto;
//	}
//
//	@Override
//	public int getVideoHeigth() {
//		return mVideoHeigth;
//	}
//
//	@Override
//	public int getVideoWeigth() {
//		return mVideoWidth;
//	}
//
//	@Override
//	public void openLocalVideo(final Uri uri, int type, int videoOrMusic,
//			long position, SurfaceView view, GLSurfaceView glView) {
//		if (uri == null) {
//			return;
//		}
//
//		logger.d("------openLocalVideo----------position-----" + position);
//		byte a = 0;
//		mediaType = videoOrMusic;
//		int bufferLocal = 2000;
//		int bufferOnline = 3000;
//		// String packagePath = "/data/data/"
//		// + context.getApplicationContext().getPackageName();
//		String packagePath = "/data/data/bc.com";
//		try {
//			if (mediaType == Args.MEDIA_TYPE_MUSIC) {
//				surfaceView.setVisibility(View.INVISIBLE);
//				pinpu.setVisibility(View.VISIBLE);
//
//				if (type == GlobalSetting.PLAY_TYPE_LOCAL) {
//					mMediaPlayer = PowerEngine.create(uri.toString(),
//							packagePath, a, bufferLocal, "net", view, null);
//				} else {
//					mMediaPlayer = PowerEngine.create(uri.toString(),
//							packagePath, a, bufferOnline,
//							GlobalSetting.LOCAL_PARAM_SOCKET_NAME, view, null);
//				}
//			} else if (mediaType == Args.MEDIA_TYPE_VIDEO) {
//				surfaceView.setVisibility(View.VISIBLE);
//
//				if (pinpu != null) {
//					pinpu.setVisibility(View.GONE);
//				}
//				if (type == GlobalSetting.PLAY_TYPE_LOCAL) {
//					mMediaPlayer = PowerEngine.create("file://"
//							+ uri.toString(), packagePath, a, bufferLocal,
//							"net", view, null);
//					logger.e("@@@@@@@@@@" + uri.toString());
//				} else {
//					mMediaPlayer = PowerEngine.create(uri.toString(),
//							packagePath, a, bufferOnline,
//							GlobalSetting.LOCAL_PARAM_SOCKET_NAME, view, null);
//				}
//			}
//		} catch (Exception e) {
//			logger.e(e);
//		}
//		// v_rendertype与v_rendertype参数设不对, 则出现黑屏
//		mMediaPlayer.setRenderParam(GlobalSetting.v_rendertype,
//				GlobalSetting.v_rendertype, "RGBX");//
//		logger.d("---play--args---" + GlobalSetting.v_rendertype + "---"
//				+ GlobalSetting.v_format);
//		mMediaPlayer.setOnBufferingUpdateListener(this);
//		mMediaPlayer.setOnCompletionListener(this);
//		mMediaPlayer.setOnErrorListener(this);
//		mMediaPlayer.setOnPlayTimeListener(this);
//		mMediaPlayer.setScreenOnWhilePlaying(true);
//
//		// mIsInitialized = true;
//
//		// updateDisplay(myHolder);
//
//		if (type == GlobalSetting.PLAY_TYPE_LIVE) {
//			start();
//		} else {
//			start(position);
//		}
//	}
//
//	public void onBufferingBegin(PowerEngine player) {
//		logger.d("----------------------onBufferingBegin");
//		logger.i("------width---" + getVideoWeigth() + "-------hight----"
//				+ getVideoHeigth());
//		handler
//				.sendEmptyMessage(DispatcherEventEnum.PLAY_EVENT_STATE_ONBUFFERINGBEGIN);
//	}
//
//	public void onBufferingComplete(PowerEngine player) {
//		logger.i("------width---" + getVideoWeigth() + "-------hight----"
//				+ getVideoHeigth());
//		logger.d("----------------------onBufferingComplete");
//		mMediaPlayer.setScreenOnWhilePlaying(true);
//		handler
//				.sendEmptyMessage(DispatcherEventEnum.PLAY_EVENT_STATE_ONBUFFERINGCOMPLETE);
//	}
//
//	public void onBufferingUpdate(PowerEngine player, int percent) {
//		logger.d("----------------------onBufferingUpdate" + percent);
//		handler.sendMessage(handler
//				.obtainMessage(
//						DispatcherEventEnum.PLAY_EVENT_STATE_ONBUFFERINGUPDATE,
//						percent));
//	}
//
//	public void onCompletion(PowerEngine player) {
//		logger.d("----------------------onCompletion");
//		pinpu.stopFlipping();
//		handler
//				.sendEmptyMessage(DispatcherEventEnum.PLAY_EVENT_STATE_ONCOMPLETION);
//	}
//
//	public boolean onError(PowerEngine player, int arg1, int arg2) {
//		logger.d("----------------------onError---------" + arg1
//				+ "--------------" + arg2);
//		pinpu.stopFlipping();
//
//		int textId = 0;
//
//		switch (arg1) {
//		case PowerEngine.PE_ERROR_OPEN_FAILED:
//			textId = R.string.hw_openfailed;
//			break;
//		case PowerEngine.PE_ERROR_TRANSMISSION_ERROR:
//			textId = R.string.hw_net_error;
//			break;
//		case PowerEngine.PE_ERROR_TRANSMISSION_TIMEOUT:
//			textId = R.string.hw_net_timeout;
//			break;
//		case PowerEngine.PE_ERROR_SEEK_FAILED:
//			textId = R.string.hw_seek_failed;
//			break;
//		case PowerEngine.PE_ERROR_UNSUPPORT_FORMAT:
//			textId = R.string.hw_not_support;
//			break;
//		default:
//			textId = R.string.media_error_unkown;
//			break;
//		}
//
//		handler.sendMessage(handler.obtainMessage(
//				DispatcherEventEnum.PLAY_EVENT_STATE_ONERROR, textId));
//		return false;
//	}
//
//	@Override
//	public void onPlayTime(PowerEngine arg0, int arg1) {
//		logger.d("the time is");
//		handler.sendMessage(handler.obtainMessage(
//				MsgIds.PLAY_TIME_ID, arg1));
//	}
//
//	@Override
//	public void openLocalVideo(Uri uri, int type, int videoOrMusic,
//			long position, SurfaceHolder holder) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void initPlayer(SurfaceView sView, ViewFlipper pView,
//			Handler playerHandler, Context cxt) {
//		// TODO Auto-generated method stub
//		logger.d("---------------------HuaweiPlayer-----------------------");
//		surfaceView = sView;
//		mGLSurfaceView = null;
//		surfaceView.setVisibility(View.VISIBLE);
//		pinpu = pView;
//		handler = playerHandler;
//		context = cxt;
//
//	}
//
//}