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

public class aa1 extends Activity {
	private Drawable d;
	private BitmapDrawable pic1;
	private BitmapDrawable pic2;
	private BitmapDrawable p2;
	Map<String, BitmapDrawable> map = new HashMap<String, BitmapDrawable>();
	private OnClickListener listen = new OnClickListener() {

		Boolean flag = true;

		@Override
		public void onClick(View v) {

			if (flag == true) {
				v.setBackgroundDrawable(map.get("pic1"));
				flag = false;
			} else {
				v.setBackgroundDrawable(map.get("pic2"));
				flag = true;
			}
			// 在两个图片之间切换
			// if (map.get("pic1") != null) {
			// map.remove("pic1");
			// map.put("pic2", pic2);
			// v.setBackgroundDrawable(map.get("pic2"));
			// } else if (map.get("pic2") != null) {
			// map.remove("pic2");
			// map.put("pic1", pic1);
			// v.setBackgroundDrawable(map.get("pic1"));
			// } else {
			// map.put("pic1", pic1);
			// v.setBackgroundDrawable(map.get("pic1"));
			// }
		}

	};

	private void setPic() {
		pic1 = getDrawable("/sdcard/a.png");
		pic2 = getDrawable("/sdcard/b.png");

		// f = new File("/sdcard/b.png");
		// try {
		// is = new FileInputStream(f);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// pic2 = new BitmapDrawable(is);
	}

	private BitmapDrawable getDrawable(String path) {
		File f = null;
		InputStream is = null;

		f = new File(path);
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BitmapDrawable drawable = new BitmapDrawable(is);
		return drawable;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setPic();

		map.put("pic1", pic1);
		map.put("pic2", pic2);

		// Bitmap p = BitmapFactory.decodeResource(this.getResources(),
		// R.drawable.icon);
		// Log.d("Bitmap p", "" + p.getWidth() + " " + p.getHeight());
		// Bitmap p1 = Bitmap.createBitmap(p, 0, 0, 35, 26);
		//		

		// d= new BitmapDrawable(p2);

		ImageView v = (ImageView) findViewById(R.id.abc);
		// v.getResources().
		v.setOnClickListener(listen);
	}
}