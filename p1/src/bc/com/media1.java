package bc.com;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.AbsoluteLayout.LayoutParams;


public class media1 extends BaseActivity {
	private DisplayMetrics dm;
	private ImageView mRewButton;
	private AudioManager audioMgr;
	private int maxStreamVolume;
	private int streamVolume;

	@Override
	public void onCreate(Bundle v){
		super.onCreate(v);
		
		dm = new DisplayMetrics();
		
		
//		mRewButton = (ImageView) findViewById(R.id.pause);
//		mRewButton.setLayoutParams(new AbsoluteLayout.LayoutParams(
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
//				30, 130));
//		
//		audioMgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//		maxStreamVolume = audioMgr
//				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//		streamVolume = audioMgr.getStreamVolume(AudioManager.STREAM_MUSIC);
//		audioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, streamVolume, 0);
//		
		
		
		
	}

}
