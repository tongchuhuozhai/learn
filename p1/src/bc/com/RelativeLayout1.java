package bc.com;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RelativeLayout1 extends Activity {

	private RelativeLayout.LayoutParams layoutParams;
	private int topHeight = 30;
	private int bottomHeight = 80;
	private int middleHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView topView = new TextView(this);
		topView.setBackgroundColor(Color.RED);
		topView.setText("A");

		TextView bottomView = new TextView(this);
		bottomView.setBackgroundColor(Color.BLUE);
		bottomView.setText("B");

		TextView middleView = new TextView(this);
		middleView.setBackgroundColor(Color.YELLOW);
		middleView.setText("C");

		View totalView = setTotalView(topView, bottomView, middleView);
		setContentView(totalView);
	}

	private View setTotalView(View topView, View bottomView, View middleView) {
		RelativeLayout relativeLayout = new RelativeLayout(this);

		// 顶部
		layoutParams = new RelativeLayout.LayoutParams(
				300, topHeight);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		topView.setId(3001);
		topView.setLayoutParams(layoutParams);
		relativeLayout.addView(topView);

		// 底部
		layoutParams = new RelativeLayout.LayoutParams(
				300, bottomHeight);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bottomView.setLayoutParams(layoutParams);
		((TextView)bottomView).setGravity(Gravity.CENTER);
		relativeLayout.addView(bottomView);

		// 中部
//		final Display display = this.getWindow().getWindowManager()
//				.getDefaultDisplay();
//		middleHeight = display.getHeight() - topHeight - bottomHeight;
		layoutParams = new RelativeLayout.LayoutParams(
				300, -2);
		layoutParams.addRule(RelativeLayout.BELOW, 3001);
		middleView.setLayoutParams(layoutParams);
		relativeLayout.addView(middleView);

		return relativeLayout;
	}
}
