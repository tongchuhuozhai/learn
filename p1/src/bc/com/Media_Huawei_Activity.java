//package bc.com;
//
//import android.app.Activity;
//import android.content.res.Configuration;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.DisplayMetrics;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.WindowManager;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.ViewFlipper;
//import android.widget.LinearLayout.LayoutParams;
//
//public class Media_Huawei_Activity extends Activity implements SurfaceHolder.Callback {
//
//	private ViewFlipper pinpu;
//	private SurfaceView mPlayView;
//	private HuaweiPlayer myplayer;
//	long currentPlayPosition;
//	private static final int BARLENGTH = 10000;
//	private ProgressBar mProgress;
//	long mDuration = 0;
//	Handler playerHandler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			switch (msg.what) {
//			case MsgIds.PLAY_TIME_ID:
//				mDuration = myplayer.duration();
//				totalTime.setText(Utils.stringForTime(mDuration));
//				
//				currentPlayPosition = Utils.getCurrentLiveTime();
//				
//				float test = (float) currentPlayPosition / mDuration;
//				long pos = (long) (BARLENGTH * test);
////				mProgress.setProgress((int) pos);
//				mProgress.setProgress((int) 382);
//				
////				mProgress.setSecondaryProgress((int) pos);
//			}
//		}
//	};
//
//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		// TODO Auto-generated method stub
//		super.onConfigurationChanged(newConfig);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//			// 横屏
//			RelativeLayout.LayoutParams rl = (android.widget.RelativeLayout.LayoutParams) mPlayView.getLayoutParams();
//			rl.width = LayoutParams.FILL_PARENT;
//			if (dm.widthPixels == 320) {
//				rl.height = 262;
//			} else if (dm.widthPixels == 480) {
//				rl.height = 400;
//			} else if (dm.widthPixels == 240) {
//				logger.d("rl.height");
//				rl.height = 192;
//			} else if (dm.widthPixels == 360) {
//				rl.height = 336;
//			}
//			mPlayView.setLayoutParams(rl);
//		} else {
//			// 竖屏
//			RelativeLayout.LayoutParams rl = (android.widget.RelativeLayout.LayoutParams) mPlayView.getLayoutParams();
//			rl.width = LayoutParams.FILL_PARENT;
//			rl.height = LayoutParams.FILL_PARENT;
//			mPlayView.setLayoutParams(rl);
//			logger.d("media onConfigurationChanged horizontal");
//
//		}
//	}
//
//	Mylogger1 logger;
//	private DisplayMetrics dm;
//
//	TextView currentTime;
//	TextView totalTime;
//
//	public void onCreate(Bundle b) {
//		super.onCreate(b);
//		logger = Mylogger1.getLogger();
//		logger.d("oncreate----------+++++++++++++");
//		setContentView(R.layout.media1);
//
//		setupView();
//
//		// 只用于音乐
//		pinpu = (ViewFlipper) findViewById(R.id.pinpu);
//
//		// 视频画面
//		mPlayView = (SurfaceView) findViewById(R.id.playsurface);
//
//		// 设置画面响应, 不调用addCallback, 就不会在页面加载完成后, 自动调用surfaceCreated, 不显示视频画画
//		mPlayView.getHolder().addCallback(this);
//
//		dm = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//		myplayer = new HuaweiPlayer();
//		myplayer.initPlayer(mPlayView, null, null, playerHandler, this);
//
//		totalTime.setText(Utils.stringForTime(myplayer.duration()));
//
//	}
//
//	private void setupView() {
//		currentTime = (TextView) findViewById(R.id.current_time);
//		totalTime = (TextView) findViewById(R.id.total_time_a);
//
//		mProgress = (ProgressBar) findViewById(R.id.progress_bar);
//
//	}
//
//	@Override
//	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
//		logger.d("@@@@@@@@@@@@@@@@surfaceChanged------------");
//	}
//
//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		logger.d("--surfaceCreated------------");
//		myplayer.openLocalVideo(Uri.parse("/data/data/bc.com/res1/ab.3gp"), 0, 1, -1, mPlayView, null);
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//
//	}
//
//	private void startChangePicture() {
//		logger.d("--start---music---");
//		pinpu.startFlipping();
//	}
//
//	@Override
//	protected void onResume() {
//
//		super.onResume();
//	}
//
//	private void stop() {
//		if (myplayer != null) {
//			myplayer.release();
//		}
//	}
//
//	@Override
//	public void finish() {
//		// 返回键, 关闭播放器
//		stop();
//		super.finish();
//	}
//}
