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

// Need the following import to get access to the app resources, since this
// class is in a sub-package.
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.widget.Toast;

public class AlarmService_Service1 extends Service {
	NotificationManager notificationManager;

	@Override
	public void onCreate() {
		Toast.makeText(this, "服务进程启动", Toast.LENGTH_SHORT).show();

		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// 在状态栏中显示通告标题与文本
		showNotification();

		// Start up the thread running the service. Note that we create a
		// separate thread because the service normally runs in the process's
		// main thread, which we don't want to block.

		// 译: 为什么要启动一个运行服务的线程?
		// 答: 由于服务通常会因为等待特定事件而进入阻塞状态, 而应用进程中的主线程是不应该被阻塞的, 所以不应该让服务运行在应用进程的主线程中,
		// 于是就创建一个 专门运行服务的独立线程.
		Thread thread = new Thread(null, mTask, "AlarmService_Service1");
		thread.start();
	}

	@Override
	public void onDestroy() {
		// 通知管理器以其标题栏字符的ID作为其ID, 通知管理器以notify方法作为起始, 以cancel方法为结束.
		// 所以两个方法中的字符串ID必须相同
		notificationManager.cancel(R.string.notify);
		Toast.makeText(this, "服务进程终止", Toast.LENGTH_SHORT).show();
		AlarmService_Service1.this.stopSelf();
	}

	/**
	 * The function that runs in our worker thread
	 */
	Runnable mTask = new Runnable() {
		public void run() {
			// Normally we would do some work here... for our sample, we will
			// just sleep for 30 seconds.
			long endTime = System.currentTimeMillis() + 5000;
			while (System.currentTimeMillis() < endTime) {
				synchronized (mBinder) {
					try {
						mBinder.wait(endTime - System.currentTimeMillis());
					} catch (Exception e) {
					}
				}
			}

			// Done with our work... stop the service!
			AlarmService_Service1.this.stopSelf();
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	/**
	 * Show a notification while this service is running.
	 */
	private void showNotification() {

		// Set the icon, scrolling text and timestamp
		// 设置顶部通知的图标与滚动文本
		Notification notification = new Notification(R.drawable.play_s,
				"ticker Text", System.currentTimeMillis());

		// The PendingIntent to launch our activity if the user selects this
		// notification
		// 译: 如果用户拉下顶部通知, 则启动如下活动
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, IndexActivity.class), 0);
		// 设置通知面板
		notification.setLatestEventInfo(this, "content title", "content text",
				contentIntent);

		// Send the notification.
		// We use a layout id because it is a unique number. We use it later to
		// cancel.
		// 译: 由通知管理器来启动一个通知, 并为该通知标上一个字符串id. 之后, 可通过此id来引用该通知, 对此通知进行结束等操作
		notificationManager.notify(R.string.notify, notification);
	}

	/**
	 * This is the object that receives interactions from clients. See
	 * RemoteService for a more complete example.
	 */
	private final IBinder mBinder = new Binder() {
		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			return super.onTransact(code, data, reply, flags);
		}
	};
}
