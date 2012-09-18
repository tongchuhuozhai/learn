package bc.com;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class KeyPad1View extends View {
	public KeyPad1View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {

		if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			Log.d("tag1", "KEYCODE_DPAD_UP");

			// Intent i = new Intent(KeyPad1.this, IndexActity.class);
			// startActivityForResult(i, 0);

			return (true);
		}

		Log.d("tag1", "onKeyDown");
		return super.onKeyDown(keyCode, msg);
	}
}
