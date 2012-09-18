package bc.com;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;

public class ImageSwitch2 extends Activity implements ViewSwitcher.ViewFactory {
	private Integer[] imageIds = new Integer[4];
	ImageSwitcher s;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.image_switch);

		// 图像切换部分
		 s = (ImageSwitcher) findViewById(R.id.image_Switch);
		s.setFactory(this);

		// gallery部分

		imageIds[0] = R.drawable.gallery_photo_1;
		imageIds[1] = R.drawable.cmcc_tab_bg;
		imageIds[2] = R.drawable.icon;
		imageIds[3] = R.drawable.cmcc_title_bg;
		Gallery g = (Gallery) findViewById(R.id.gallery1);
		g.setAdapter(new ImageAdapter1(this, imageIds));
		g.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				s.setImageResource(imageIds[index]);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}

	// 为图像切换器提供图片
	@Override
	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return imageView;
	}

}
