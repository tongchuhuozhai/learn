package bc.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class dd extends BaseActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.init(R.layout.main);

		setupView();
		View v = findViewById(R.id.loginprogress);
		OnClickListener cl = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(dd.this, IndexActivity.class);
				startActivityForResult(i, 0);
			}
		};
		v.setOnClickListener(cl);

	}

	private void setupView() {
		findViewById(R.id.loginbg).setBackgroundResource(
				R.drawable.login_background1);

	}

}