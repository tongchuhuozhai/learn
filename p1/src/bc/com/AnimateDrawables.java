/*
 * Copyright (C) 2008 The Android Open Source Project
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

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class AnimateDrawables extends Activity {
	// 图像运动控制器
	static Animation animation;

	private static Transformation mTransformation = new Transformation();
	static Drawable drawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new SampleView(this));
	}

	private static class SampleView extends View {
		private Drawable mDrawable;

		public SampleView(Context context) {
			super(context);
			setFocusable(true);
			setFocusableInTouchMode(true);

			drawable = context.getResources().getDrawable(R.drawable.icon);
			// 如果draw方法被调用, 则图像的边界必须被设置
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

			// 图像运动轨迹
			animation = new TranslateAnimation(0, 100, 0, 200);
			// 设置图像完成一次运动轨迹所用的时间
			animation.setDuration(3000);
			// 图像重复运动的次数, -1 代表无数次
			animation.setRepeatCount(-1);

			// 不明白
			animation.initialize(100, 100, 10, 10);

			// 启动动画
			animation.startNow();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.WHITE);

			// mDrawable.draw(canvas);
			if (drawable != null) {
				int sc = canvas.save();
				if (animation != null) {
					animation.getTransformation(AnimationUtils
							.currentAnimationTimeMillis(), mTransformation);
					// 不明
					canvas.concat(mTransformation.getMatrix());
					Matrix m;
					m = mTransformation.getMatrix();
					float[] a = new float[9];
					m.getValues(a);
					for (int i = 0; i < 9; i++) {
						Log.i("matrix 1", a.toString());
					}
				}
				drawable.draw(canvas);
				canvas.restoreToCount(sc);
			}
			// ?
			invalidate();
		}
	}
}
