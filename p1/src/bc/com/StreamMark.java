package bc.com;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class StreamMark extends Activity {


	static Mylogger1 logger;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		logger = Mylogger1.getLogger();
		logger.d("StreamMark oncreate");
		try {
			setContentView(new SampleView(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static class SampleView extends View {
		private Movie mMovie;
		private long mStart = 0;
		private int relTime;
		private Movie mMovieCurrent;

		public SampleView(Context context) throws IOException {
			super(context);
			InputStream is2 = null;
			BufferedInputStream bis = null;

			// 运行前将ad1.gif放到/data/目录下
			String fname = "/data/data/" + ApplicationEntry.getPackageName1() + "/res1/animate.gif";
			logger.d("fname is " + fname);

			File adFile = new File(fname);
			is2 = new FileInputStream(adFile);
			bis = new BufferedInputStream(is2);

			// 参数确定了有效字节的个数
			bis.mark((int) adFile.length());
			mMovieCurrent = Movie.decodeStream(bis);
		}

		// 如下四个成员不能放在onDraw（）中， 否则图片无动画效果
		private float xScale = 1;
		private float yScale = 1;
		private long mMovieStart = 0;
		private Matrix matrix;
	

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			if (null == mMovieCurrent)
				return;

			// 获取从启动时到现在所经历的毫秒数
			long now = SystemClock.uptimeMillis();
			int dur = mMovieCurrent.duration();
			if (dur == 0) {
				dur = 1000;
			}

			if (mMovieStart == 0) { // first time
				mMovieStart = now;
			}

			int relTime = (int) (now - mMovieStart);
			if (relTime >= dur) {
				mMovieStart = now;
				relTime = 0;
			}

			mMovieCurrent.setTime(relTime);

			// 后两个参数为播放画面的起始位置
			mMovieCurrent.draw(canvas, 0, 0);

			// 有动画效果, 就要重画, 即调用此函数
			invalidate();
			logger.d("onDraw ");
		}
	}
}
