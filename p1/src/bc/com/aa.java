package bc.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class aa extends Activity {
	private BitmapDrawable pic1;
	private BitmapDrawable pic2;
	private Boolean isPic1 = true;
	private Map<String, BitmapDrawable> map = new HashMap<String, BitmapDrawable>();


	private OnClickListener listen = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (isPic1 == false) {
				v.setBackgroundDrawable(map.get("pic1"));
				isPic1 = true;
			} else {
				v.setBackgroundDrawable(map.get("pic2"));
				isPic1 = false;
			}
		}
	};

	private void getPic() {
		pic1 = getDrawableFromFile("/data/a.png");
		map.put("pic1", pic1);

		pic2 = getDrawableFromFile("/data/b.png");
		map.put("pic2", pic2);
	}

	private BitmapDrawable getDrawableFromFile(String path) {
		File picFile = new File(path);
		InputStream is = null;
		BitmapDrawable drawable;

		try {
			is = new FileInputStream(picFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		drawable = new BitmapDrawable(is);
		return drawable;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa);
		getPic();

		ImageView v = (ImageView) findViewById(R.id.abc);
		v.setBackgroundDrawable(map.get("pic1"));
		v.setOnClickListener(listen);
	}
}