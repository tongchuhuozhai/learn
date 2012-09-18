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

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

public class Arcs extends Activity {
	private Paint[] mPaints;
	private Paint mFramePaint;
	private boolean[] mUseCenters;
	private RectF[] mOvals;
	private RectF mBigOval;
	private static float mStart;
	private static float mSweep;
	private static int mBigIndex;

	private static final float SWEEP_INC = 2;
	private static final float START_INC = 15;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new SampleView(this));
	}

	private static class SampleView extends View {

		private RectF mBigOval;

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

			p.setStrokeWidth(4);
			p.setStyle(Paint.Style.STROKE);
			mBigOval = new RectF(40, 10, 280, 250);
			// 在画布上用画笔画图,
			canvas.drawArc(mBigOval, mStart, mSweep, true, p);

			mSweep += SWEEP_INC;
			if (mSweep > 360) {
				mSweep -= 360;
				mStart += START_INC;
				if (mStart >= 360) {
					mStart -= 360;
				}
				// mBigIndex = (mBigIndex + 1) % mOvals.length;
			}
			// invalidate 使图像每隔1秒钟重画一次
			invalidate();

		}
	}
}
