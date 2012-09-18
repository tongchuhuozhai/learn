/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bc.com;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

public class BitmapActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new SampleView(this));

		Log.d("tag1", "AlphaBitmap begin");
	}

	// 按键响应
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		Log.d("tag1", "onKeyDown1");

		if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			Log.d("tag1", "KEYCODE_DPAD_UP");
			return (true);
		} else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {

			finish();
			// 不明: 结束应用程序
			// ActivityManager activityManager = (ActivityManager) this
			// .getSystemService(Context.ACTIVITY_SERVICE);
			// activityManager.killBackgroundProcesses("bc.com.ApplicationEntry");
			// Log.d("application tag", "application exit");

			return (true);
		}
		return super.onKeyDown(keyCode, msg);
	}

	class SampleView extends View {

		public SampleView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.GRAY);

			// 创建一个画笔
			Paint p = new Paint();
			p.setColor(Color.BLUE);
			// 使边缘圆滑
			p.setAntiAlias(true);

			// 在画布上用画笔画图
			canvas.drawCircle(100, 100, 30, p);

			p.setColor(Color.GREEN);
			canvas.drawCircle(100, 200, 30, p);
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent msg) {
			Log.d("tag1", "onKeyDown1");

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

}
