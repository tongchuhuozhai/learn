package bc.com;

import android.app.Activity;
import android.os.Bundle;

public class KeyPad1 extends Activity {

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(new KeyPad1View(this));
	}
}
