package bc.com;

import java.util.Map;

import android.content.Context;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ViewFlipper;

public interface IPlayer {

	public void initPlayer(SurfaceView sView, ViewFlipper pView,
			Handler playerHandler, Context cxt);

	public void initPlayer(SurfaceView sView, GLSurfaceView pegView,
			ViewFlipper pView, Handler playerHandler, Context cxt);

	public boolean start(long position);

	public boolean start();

	public boolean pause();

	public void suspend();

	public void resume(boolean flag);

	public void stop();

	public void release();

	public boolean isPlayingOrBuffering();

	public boolean isCurrentPlaying();

	public long duration();

	public long seek(long whereto);

	public int getVideoHeigth();

	public int getVideoWeigth();

	public void updateDisplay(SurfaceHolder holder);

	public void openLocalVideo(final Uri uri, int type, int videoOrMusic,
			long position, SurfaceHolder holder);

	public void openLocalVideo(final Uri uri, int type, int videoOrMusic,
			long position, SurfaceView view, GLSurfaceView glView);

	public Map<String, String> changeLivePlayUrl(String liveUrl,
			long trueStart, long trueEnd, float currentProgress, int BARLENGTH);

}
