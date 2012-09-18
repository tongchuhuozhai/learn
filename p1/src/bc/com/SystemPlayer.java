//package bc.com;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.media.MediaPlayer.OnBufferingUpdateListener;
//import android.media.MediaPlayer.OnCompletionListener;
//import android.media.MediaPlayer.OnErrorListener;
//import android.media.MediaPlayer.OnInfoListener;
//import android.media.MediaPlayer.OnPreparedListener;
//import android.media.MediaPlayer.OnSeekCompleteListener;
//import android.media.MediaPlayer.OnVideoSizeChangedListener;
//import android.net.Uri;
//import android.os.Handler;
//import android.os.Message;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.widget.ViewFlipper;
//
//public class SystemPlayer implements IPlayer, OnBufferingUpdateListener,
//		OnCompletionListener, OnErrorListener, OnInfoListener,
//		OnSeekCompleteListener, OnVideoSizeChangedListener, OnPreparedListener {
//	private SurfaceView surfaceView;
//	private ViewFlipper pinpu;
//	private MediaPlayer mMediaPlayer = null;
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
//	public SystemPlayer() {
//
//	}
//
//	@Override
//	public void initPlayer(SurfaceView sView, ViewFlipper pView,
//			Handler playerHandler, Context cxt) {
//		logger.e("---------------------HuaweiPlayer-----------------------");
//		surfaceView = sView;
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
//				mMediaPlayer.start();
//				mMediaPlayer.seekTo((int) position);
//			} catch (Exception e) {
//				logger.e(e);
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public boolean start() {
//		if (null != mMediaPlayer) {
//			startChangePicture();
//			try {
//				mMediaPlayer.setScreenOnWhilePlaying(true);
//				mMediaPlayer.start();
//			} catch (Exception e) {
//				logger.e(e);
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public void resume(boolean flag) {
//	}
//
//	@Override
//	public void suspend() {
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
//		hd.sendEmptyMessage(1);
//		if (mediaType == Args.MEDIA_TYPE_MUSIC) {
//			logger.d("--start---music---");
//			pinpu.startFlipping();
//		}
//	}
//
//	private void stopChangePicture() {
//		hd.removeMessages(1);
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
//				mMediaPlayer.pause();
//			} catch (Exception e) {
//				logger.e(e);
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public Map<String, String> changeLivePlayUrl(String liveUrl,
//			long trueStart, long trueEnd, float currentProgress, int BARLENGTH) {
//		// trueStart = trueStart + 2 * 1000;
//		Map<String, String> dataMap = new HashMap<String, String>();
//		Date date = new Date(trueStart
//				+ (long) ((trueEnd - trueStart) * currentProgress / BARLENGTH));
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
//		if (null != mMediaPlayer) {
//			try {
//				mMediaPlayer.setDisplay(holder);
//			} catch (Exception e) {
//				logger.e(e);
//			}
//		}
//	}
//
//	@Override
//	public boolean isCurrentPlaying() {
//		if (null != mMediaPlayer) {
//			return mMediaPlayer.isPlaying();
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public boolean isPlayingOrBuffering() {
//		if (null != mMediaPlayer) {
//			return mMediaPlayer.isPlaying();
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
//		if (null != mMediaPlayer) {
//			try {
//				mMediaPlayer.seekTo((int) whereto);
//				logger.d("-------seek---position-----" + whereto);
//			} catch (Exception e) {
//				logger.e(e);
//			}
//		}
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
//			long position, SurfaceHolder myHolder) {
//		if (uri == null) {
//			return;
//		}
//		logger.e("------openLocalVideo----------position-----" + position);
//		mediaType = videoOrMusic;
//		try {
//			if (mediaType == Args.MEDIA_TYPE_MUSIC) {
//				pinpu.setVisibility(View.VISIBLE);
//			} else if (mediaType == Args.MEDIA_TYPE_VIDEO) {
//				pinpu.setVisibility(View.GONE);
//			}
//		} catch (Exception e) {
//			logger.e(e);
//		}
//		
//		
//		try {
//			mMediaPlayer = new MediaPlayer();
//			mMediaPlayer.setDataSource(context, uri);
//			
//			mMediaPlayer.setDisplay(myHolder);
//			
//			mMediaPlayer.setOnBufferingUpdateListener(this);
//			mMediaPlayer.setOnCompletionListener(this);
//			mMediaPlayer.setOnErrorListener(this);
//			mMediaPlayer.setOnInfoListener(this);
//			mMediaPlayer.setOnPreparedListener(this);
//			mMediaPlayer.setOnSeekCompleteListener(this);
//			mMediaPlayer.setOnVideoSizeChangedListener(this);
//			mMediaPlayer.setScreenOnWhilePlaying(true);
//
//			// mIsInitialized = true;
//			if (type == GlobalSetting.PLAY_TYPE_LIVE) {
//				mMediaPlayer.prepareAsync();
//			} else {
//				mMediaPlayer.prepare();
//			}
//		} catch (Exception e1) {
//			logger.e(e1);
//		}
//	}
//
//	// public void onBufferingBegin(PowerEngine player) {
//	// logger.d("----------------------onBufferingBegin");
//	// logger.i("------width---" + getVideoWeigth() + "-------hight----"
//	// + getVideoHeigth());
//	// handler
//	// .sendEmptyMessage(DispatcherEventEnum.PLAY_EVENT_STATE_ONBUFFERINGBEGIN);
//	// }
//
//	@Override
//	public void onBufferingUpdate(MediaPlayer arg0, int percent) {
//		logger.d("----------------------onBufferingUpdate" + percent);
//		if (percent == 100) {
//			handler
//					.sendEmptyMessage(DispatcherEventEnum.PLAY_EVENT_STATE_ONBUFFERINGCOMPLETE);
//		} else if(percent == 0){
//			handler.sendMessage(handler.obtainMessage(
//					DispatcherEventEnum.PLAY_EVENT_STATE_ONBUFFERINGBEGIN,
//					percent));
//		} else{
//			handler.sendMessage(handler.obtainMessage(
//					DispatcherEventEnum.PLAY_EVENT_STATE_ONBUFFERINGUPDATE,
//					percent));
//		}
//	}
//
//	@Override
//	public void onCompletion(MediaPlayer arg0) {
//		logger.d("----------------------onCompletion");
//		pinpu.stopFlipping();
//		handler
//				.sendEmptyMessage(DispatcherEventEnum.PLAY_EVENT_STATE_ONCOMPLETION);
//	}
//
//	@Override
//	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
//		logger.e("----------------------onError---------" + arg1
//				+ "--------------" + arg2);
//		pinpu.stopFlipping();
//		handler.sendMessage(handler.obtainMessage(
//				DispatcherEventEnum.PLAY_EVENT_STATE_ONERROR, arg1));
//		return false;
//	}
//
//	@Override
//	public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
//		logger.e("----onInfo------" + arg1 + "----" + arg2);
//		return false;
//	}
//
//	@Override
//	public void onSeekComplete(MediaPlayer arg0) {
//		logger.d("---onSeekComplete----");
//	}
//
//	@Override
//	public void onVideoSizeChanged(MediaPlayer arg0, int arg1, int arg2) {
//		logger.d("----onVideoSizeChanged---"+ arg1  + "------" + arg2);
//	}
//
//	@Override
//	public void onPrepared(MediaPlayer arg0) {
//		logger.d("------onPrepared-------");
//		handler
//				.sendEmptyMessage(DispatcherEventEnum.PLAY_EVENT_STATE_ONBUFFERINGCOMPLETE);
//		start();
//	}
//	
//	Handler hd = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			if(msg.what == 1){
//				logger.d("--------1--------");
//				handler.sendMessage(handler.obtainMessage(
//						DispatcherEventEnum.PLAY_EVENT_STATE_PLAY_POSITION, position()));
//				hd.sendEmptyMessageDelayed(1, 1000);
//			}
//		}
//		
//	};
//	
//	private long position() {
//		if (null != mMediaPlayer)
//			return mMediaPlayer.getCurrentPosition();
//		return 0;
//	}
//}