package bc.com;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BroadcastReceiver1 extends BroadcastReceiver  {

	Context context;
	Mylogger1 logger;
	String message = null;
	Listennerm d;

	@Override
	public void onReceive(Context context, Intent intent) {
		logger = Mylogger1.getLogger();
		d = ApplicationEntry.getListennerm();
		logger.d("BroadcastReceiver1 onReceive--------------------------------");
		// 获取系统传来的intent中的数据 
		Bundle bundle = intent.getExtras();
		this.context = context;
		receiveMultiMessage(bundle);
	}

	private void receiveMultiMessage(Bundle bundle) {
		// 短信组, 每一个元素为一条短信, 短信对象只能通过短信组对象构造
		SmsMessage[] msgs = null;
		String phone = null;

		if (bundle != null) {
			// 从系统传来的intent中获取协议数据单元
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			// msgs.length为短信的条数
			for (int i = 0; i < msgs.length; i++) {
				// 将协议数据单元构造为短信对象
				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				// 从短信对象中获取地址
				phone = msgs[i].getOriginatingAddress();
				// 从短信对象中获取短信内容
				message = msgs[i].getMessageBody();
			}
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				// 构造页面之间传送的数据p
				Intent1 a = new Intent1();
				a.m = message;
				if (Global.app_status == Global.STARTED) {
					d.sendMessage(d.obtainMessage(MsgIds.MessageID));
				}else{
					Parcelble1 p = new Parcelble1(a);
					Intent i = new Intent(context, MainActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					i.putExtra(MsgIds.intentData, p);
					context.startActivity(i);
				
				}
			}
		}).start();
	}

	private void receiveSingleMessage(Bundle bundle) {

		SmsMessage[] msgs = null;
		String phone;
		String message = null;

		if (bundle != null) {
			Object pdus = (Object) bundle.get("pdus");
			msgs = new SmsMessage[1];

			msgs[0] = SmsMessage.createFromPdu((byte[]) pdus);
			phone = msgs[0].getOriginatingAddress();
			message = msgs[0].getMessageBody();
		}
		Toast.makeText(context, message, 5000);

	}

	public void showNotification(Context context) {
		NotificationManager nm;
		nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		Notification notification = new Notification(R.drawable.play_s, "ticker Text", System.currentTimeMillis());

		nm.notify(0, notification);

	}


}
