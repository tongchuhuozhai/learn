package bc.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

public class Scroll1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 中间1
		BitmapDrawable pic1 = getDrawableFromFile("/data/data/bc.com/lib/d.jpg");

		LinearLayout root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		addBitmapDrawable(layout, pic1, true);
		addBitmapDrawable(layout, pic1, true);
		addBitmapDrawable(layout, pic1, true);

		addChildToRoot(root, layout);
		View scrollView = scrollWrap(root);

		// 标题栏
		RelativeLayout rla = new RelativeLayout(this);
		rla.setBackgroundColor(Color.BLUE);
		TextView textView = new TextView(this);
		textView.setText("标题1");
		RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 30);
		p1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rla.addView(textView, p1);
		rla.setId(2000);

		// 中间2
		LinearLayout rlb = new LinearLayout(this);
		BitmapDrawable pic2 = getDrawableFromFile("/data/data/bc.com/lib/e.jpg");
		ImageView child = new ImageView(this);
		child.setBackgroundDrawable(pic2);
		LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 50);
		rlb.addView(child, p2);

		// 相对布局标签
		LinearLayout wordLinearLayout = new LinearLayout(this);
		wordLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		TextView[] textView1 = new TextView[6];
		LinearLayout.LayoutParams wordLayoutP = new LinearLayout.LayoutParams(
				100, 50);
		Drawable tabButtonBackgroud = getDrawableFromFile("/data/data/bc.com/lib/button.png");
		for (int i = 0; i < 6; i++) {
			textView1[i] = new TextView(this);
			textView1[i].setGravity(Gravity.CENTER);
			textView1[i].setLayoutParams(wordLayoutP);
			textView1[i].setBackgroundDrawable(tabButtonBackgroud);
			textView1[i].setTextSize(18);
		}

		textView1[0].setText("影视");
		textView1[1].setText("娱乐");
		textView1[2].setText("动漫");
		textView1[3].setText("MV");
		textView1[4].setText("原创");
		textView1[5].setText("直播");
		for (int i = 0; i < 6; i++) {
			wordLinearLayout.addView(textView1[i]);
		}

		// Tab布局标签
//		TabHost tabHost = new TabHost(this);
//		FrameLayout fl = tabHost.getTabContentView();
//		Drawable pic3 = getDrawableFromFile("/data/data/bc.com/lib/button.png");
//		tabHost.addTab(tabHost.newTabSpec("tab1")); 
		//.setIndicator("indicator", pic3));
//		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("indicator", pic3));
//		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("indicator", pic3));

		
		// 底部
		LinearLayout bottomLayout = new LinearLayout(this);
		bottomLayout.setOrientation(LinearLayout.HORIZONTAL);
		bottomLayout.setGravity(Gravity.CENTER);
		bottomLayout.setLayoutParams(new LinearLayout.LayoutParams(
				300, 50));
		bottomLayout.setBackgroundColor(Color.GRAY);
		ImageView[] imageView1 = new ImageView[3];
		LinearLayout.LayoutParams bottomLayoutP = new LinearLayout.LayoutParams(
				100, 50);
		Drawable buttonBackgroud1 = getDrawableFromFile("/data/data/bc.com/lib/button.png");
		for (int i = 0; i < 3; i++) {
			imageView1[i] = new ImageView(this);
			imageView1[i].setLayoutParams(bottomLayoutP);
			imageView1[i].setBackgroundDrawable(buttonBackgroud1);
			bottomLayout.addView(imageView1[i]);
		}

		View totalView = setTotalView(wordLinearLayout, rlb, bottomLayout);
	
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(totalView);

		// getWindow().get
		// setFeatureInt(Window.FEATURE_CUSTOM_TITLE, 2000);
		// setFeatureDrawable(Window.FEATURE_CUSTOM_TITLE, rla);
	}

	private void addChildToRoot(LinearLayout root, LinearLayout layout) {
		root.addView(layout, new LinearLayout.LayoutParams(
				300,
				LinearLayout.LayoutParams.WRAP_CONTENT));
	}

	private void addBitmapDrawable(LinearLayout layout, Drawable d,
			boolean scale) {
		Bitmap bitmap;

		View view = new View(this);

		view.setBackgroundDrawable(d);

		view.setLayoutParams(new LinearLayout.LayoutParams(d
				.getIntrinsicWidth(), d.getIntrinsicHeight()));
		layout.addView(view);

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

	private View scrollWrap(View view) {
		ScrollView scroller = new ScrollView(this);

		scroller.addView(view, new ScrollView.LayoutParams(
				300, 300));

		// Gallery g = new Gallery(this);
		// BitmapDrawable pic1 =
		// getDrawableFromFile("/data/data/bc.com/lib/d.jpg");
		// ImageView child = new ImageView(this);
		// child.setBackgroundDrawable(pic1);
		// Gallery.LayoutParams gl = new Gallery.LayoutParams(100,
		// Gallery.LayoutParams.MATCH_PARENT);
		// g.addView(child, gl);
		// g.addView(child, gl);
		// g.addView(child, gl);
		// g.addView(child, gl);
		// g.addView(child, gl);
		// scroller.addView(g, new ScrollView.LayoutParams(
		// ScrollView.LayoutParams.MATCH_PARENT, 200));

		return scroller;
	}

	RelativeLayout.LayoutParams lp;

	private View setTotalView(View view1, View view2, View view3) {
		RelativeLayout li = new RelativeLayout(this);

		lp = new RelativeLayout.LayoutParams(
				300, 50);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		view1.setId(3001);
		li.addView(view1, lp);

		lp = new RelativeLayout.LayoutParams(
				300, 600);
		lp.addRule(RelativeLayout.BELOW, 3001);
		view2.setId(3002);
		li.addView(view2, lp);

		lp = new RelativeLayout.LayoutParams(
			300, 30);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		li.addView(view3, lp);

		// li.addView(view2);
		return li;
	}
}
