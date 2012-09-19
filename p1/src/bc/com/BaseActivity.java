package bc.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class BaseActivity extends Activity implements Listener1 {

    Listennerm listenerm;
    Intent1 i;
    Context context;
    private Mylogger1 logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger = Mylogger1.getLogger();
        // 获取消息处理器
        listenerm = ApplicationEntry.getListennerm();
        Parcelble1 p;
        if (savedInstanceState != null) {
            // 横竖屏
            // p = savedInstanceState.getParcelable(MsgIds.intentData);
            // i = p.getIntent1();
        } else {
            p = getIntent().getParcelableExtra(MsgIds.intentData);
            if (p != null) {
                // 短信启动
                i = p.getIntent1();
            }
        }

        context = this;
        // Controller1.getConller1().addListener(this);

    }

    protected void init(int main) {
        // 设置需要在代码中修改的视图属性
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Controller1.getConller1().addListener(this);
    }

    @Override
    public Dialog onCreateDialog(int id) {
        Dialog dialog = super.onCreateDialog(id);
        switch (id) {
        case MsgIds.SMS_DialogID:
            dialog = showMessageDialog();
            break;
        default:
            break;
        }
        return dialog;

    }

    private Dialog showMessageDialog() {
        View mView = LayoutInflater.from(context).inflate(R.layout.sms_dialog,
                null);
        TextView v = (TextView) mView.findViewById(R.id.sms_text);
        v.setText(i.m);
        return new AlertDialog.Builder(this)
                .setIcon(null)
                .setTitle(R.string.sms_title)
                .setView(mView)
                .setPositiveButton(R.string.dialogConfirm,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                    int whichButton) {
                                if (BaseActivity.this instanceof MainActivity) {
                                    WindowManager.LayoutParams layouParams = getWindow()
                                            .getAttributes();
                                    layouParams.alpha = 1.0f;
                                    getWindow().setAttributes(layouParams);

                                    if (Global.app_status != Global.STARTED) {
                                        ((MainActivity) BaseActivity.this)
                                                .setView();
                                        ((MainActivity) BaseActivity.this)
                                                .addListener1();

                                    }

                                }
                            }
                        })
                .setNegativeButton(R.string.app_name,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                    int whichButton) {

                            }
                        })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface arg0) {
                    }
                }).setCancelable(true).create();
    }

    protected void handleMessage1(Message msg) {

    }

    @Override
    public void handleMessage(Message msg) {
        handleMessage1(msg);
        logger.d("handleMessage");
        switch (msg.what) {
        case MsgIds.MessageID:
            showDialog(MsgIds.SMS_DialogID);
            break;
        default:
            break;
        }
    }

    // 按返回按钮, 调用此方法, 若不在此反注册本界面的事件响应, 则所返回的界面的事件响应出错.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Controller1.getConller1().removeListener(this);
    }
}
