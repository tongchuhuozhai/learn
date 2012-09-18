package bc.com;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.internal.telephony.ITelephony;

/**
 * 演示如何设置呼叫转移，拦截电话（拦截后提示为空号）的例子
 * 
 * @author Tony from ToEach.
 * @email wan1976@21cn.com
 */
public class TelephoneInject extends Activity {
    private static final String TAG = TelephoneInject.class.getSimpleName();

    private final static int OP_REGISTER = 100;
    private final static int OP_CANCEL = 200;

    private final static String BLOCKED_NUMBER = "00000000000";// 要拦截的号码
    // 占线时转移，这里13800000000是空号，所以会提示所拨的号码为空号
    private final String ENABLE_SERVICE = "tel:**67*13800000000%23";
    // 占线时转移
    private final String DISABLE_SERVICE = "tel:%23%2367%23";
    private IncomingCallReceiver mReceiver;
    private ITelephony iTelephony;
    private AudioManager mAudioManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telephone_inject);

        findViewById(R.id.btnEnable).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // 设置呼叫转移
                Message message = mHandler.obtainMessage();
                message.what = OP_REGISTER;
                mHandler.dispatchMessage(message);
            }
        });

        findViewById(R.id.btnDisable).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // 取消呼叫转移
                Message message = mHandler.obtainMessage();
                message.what = OP_CANCEL;
                mHandler.dispatchMessage(message);
            }
        });

        mReceiver = new IncomingCallReceiver();
        IntentFilter filter = new IntentFilter("android.intent.action.PHONE_STATE");
        registerReceiver(mReceiver, filter);// 注册BroadcastReceiver

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // 利用反射获取隐藏的endcall方法
        TelephonyManager telephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Method getITelephonyMethod = TelephonyManager.class.getDeclaredMethod("getITelephony", (Class[]) null);
            getITelephonyMethod.setAccessible(true);
            iTelephony = (ITelephony) getITelephonyMethod.invoke(telephonyMgr, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message response) {
            int what = response.what;
            switch (what) {
            case OP_REGISTER: {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(ENABLE_SERVICE));
                startActivity(i);
                break;
            }
            case OP_CANCEL: {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(DISABLE_SERVICE));
                startActivity(i);
                break;
            }
            }
        }
    };

    private class IncomingCallReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            Log.i(TAG, "State: " + state);

            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.d(TAG, "Incomng Number: " + number);

            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {// 电话正在响铃
                if (number.equals(BLOCKED_NUMBER)) {// 拦截指定的电话号码
                    // 先静音处理
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    Log.d(TAG, "Turn ringtone silent");

                    try {
                        // 挂断电话
                        iTelephony.endCall();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    // 再恢复正常铃声
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
            }
        }
    }
 
}
