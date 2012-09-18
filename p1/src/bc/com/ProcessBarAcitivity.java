package bc.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class ProcessBarAcitivity extends Activity {
	TextView t;
	Button a;

	ProgressBar p;
	SeekBar seekBar;
	int status = 0;
	Handler h = new Handler();

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		Intent in = new Intent(this, IndexActivity.class);
		in.putExtra("putExtra", "aaaaaaa");
		in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		setContentView(R.layout.intent1);
		t = (TextView) findViewById(R.id.text1);
		Intent i = this.getIntent();
		String s = i.getStringExtra("key1");
		t.setText(s);

		p = (ProgressBar) findViewById(R.id.progress_bar);
		p.setMax(10000);

		// 启动一个线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (status++ < 10000) {
					// 不解
					h.post(new Runnable() {
						@Override
						public void run() {
							p.setProgress(status);

						}
					});
				}
			}
		}).start();

		// 拖动条控制
		seekBar = (SeekBar) findViewById(R.id.seek_bar);
		seekBar.setMax(10000);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (status++ < 10000) {
					// 不解
					h.post(new Runnable() {
						@Override
						public void run() {
							seekBar.setProgress(status);

						}
					});
				}
			}
		}).start();
	}
}
