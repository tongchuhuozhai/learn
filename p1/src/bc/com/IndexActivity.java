package bc.com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class IndexActivity extends Activity {
    protected static Context context;
    private EditText searchText;
    private Button searchButton;

    // 输入法
    private InputMethodManager inputMethodManager;

    // 保存引用
    private SharedPreferences sharedPreference;
    private String referenceName = "sp1";
    private String referenceSharedKey1 = "key1";

    // 短信
    private SmsManager smsManager;
    private PendingIntent pengdingSMS;

    // gallery组织图片
    private Gallery gallery;
    private Integer[][] imageIds = new Integer[2][4];

    // 在指定时刻启动后台进程
    private PendingIntent mAlarmSender;

    // 通知管理器
    private NotificationManager notificationManager;

    public static Context getContext() {
        return context;
    }

    private OnClickListener wordClickListener0 = new OnClickListener() {
        @Override
        public void onClick(View view) {
            // 字体放大
            setWordAnimation((TextView) view);

            // 通过Intent来完成Activity之间传送信息
            Intent i = new Intent(IndexActivity.this, ProcessBarAcitivity.class);
            i.putExtra("key1", "key1 value");
            startActivityForResult(i, 0);
        }
    };

    private OnClickListener wordClickListener1 = new OnClickListener() {
        @Override
        public void onClick(View view) {
            // 获取作为后台活动的通知管理器

            // 获取通知对象
            Notification notification = new Notification(R.drawable.icon,
                    "顶部通知条", System.currentTimeMillis());
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                    null, 0);
            // 设置通知消息正文.
            notification.setLatestEventInfo(context, "通知标题", "通知正文",
                    contentIntent);
            notificationManager.notify(0, notification);
        }
    };
    private OnClickListener wordClickListener2 = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(IndexActivity.this, BookTab.class);
            intent.putExtra("title", "华严宗");
            intent.putExtra("tab1", "/data/huayan.xml");
            intent.putExtra("tab2", "/data/lengjia.xml");
            intent.putExtra("tab3", "/data/jiemi.xml");
            startActivityForResult(intent, 0);

        }
    };

    private OnClickListener wordClickListener3 = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(IndexActivity.this, BookTab.class);
            intent.putExtra("title", "禅宗");
            intent.putExtra("tab1", "/data/vajra.xml");
            intent.putExtra("tab2", "/data/tanjing.xml");
            intent.putExtra("tab3", "/data/xinjing.xml");
            startActivityForResult(intent, 0);
        }
    };

    private OnClickListener wordClickListener4 = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(IndexActivity.this, ImageSwitch2.class);

            startActivityForResult(intent, 0);
        }
    };

    // 为何为final类型, 不解
    private void setWordAnimation(final TextView textView) {
        // 放大设置
        Animation animation = AnimationUtils.loadAnimation(context,
                R.anim.popup_out);
        // 颜色设置
        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                textView.setTextColor(Color.rgb(0, 255, 0));
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                textView.setTextColor(Color.rgb(0, 255, 255));
            }
        });
        textView.startAnimation(animation);
        textView.setTextColor(Color.WHITE);
    }

    // 启动输入法
    private OnFocusChangeListener wordLayoutListener = new OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (v.getId() == R.id.search_result_text) {
                if (!hasFocus) {
                    inputMethodManager = (InputMethodManager) ApplicationEntry
                            .getInstance().getSystemService(
                                    ApplicationEntry.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(
                            searchText.getWindowToken(), 0);
                }
            }
        }
    };

    private OnClickListener searchButtonListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // Intent intent = new Intent(IndexActivity.this,
            // BookTab.class);
            // intent.putExtra("title", "禅宗");
            // intent.putExtra("tab1", "/data/vajra.xml");
            // intent.putExtra("tab2", "/data/tanjing.xml");
            // intent.putExtra("tab3", "/data/xinjing.xml");
            // startActivityForResult(intent, 0);
            if (searchText.getText().toString().length() == 0) {
                searchText.setError("输入不能为空");
            } else {
                Intent i = new Intent(IndexActivity.this, G3.class);
                startActivityForResult(i, 0);
            }
        }
    };

    private OnItemClickListener galleryItemListener = new OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position,
                long id) {

            Intent intent = new Intent(IndexActivity.this, BookTab.class);
            intent.putExtra("title", "华严宗");
            intent.putExtra("tab1", "/data/huayan.xml");
            intent.putExtra("tab2", "/data/lengjia.xml");
            intent.putExtra("tab3", "/data/jiemi.xml");
            startActivityForResult(intent, 0);
            // long firstTime = SystemClock.elapsedRealtime();
            // if (position == 0) {
            // // 发送短信,
            // smsManager.sendTextMessage("5554", null, "abd", pengdingSMS,
            // null);
            // } else if (position == 1) {
            // Intent intent = new Intent(context, BitmapActivity.class);
            // startActivityForResult(intent, 0);
            //
            // } else if (position == 2) {
            // AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            // // 设置闹钟响起时, 启动的后台服务进程
            // am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            // firstTime, 2 * 1000, mAlarmSender);
            //
            // } else if (position == 3) {
            // AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            // am.cancel(mAlarmSender);
            // }
        }
    };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.index);

        Log.e("receiver@@@@@@@@@@@@@@@@@@@@@@", "packageName is "
                + getIntent().getComponent().getPackageName());
        context = this;
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Intent i = new Intent();
        smsManager = SmsManager.getDefault();
        pengdingSMS = PendingIntent.getActivity(this, 0, new Intent(this,
                IndexActivity.class), 0);

        // 获取后台进程AlarmService_Service的句柄
        mAlarmSender = PendingIntent.getService(IndexActivity.this, 0,
                new Intent(IndexActivity.this, AlarmService_Service1.class), 0);

        setupView();
        if (getIntent().getParcelableExtra("putExtra") != null) {

            Intent i = new Intent(IndexActivity.this, ProcessBarAcitivity.class);
            i.putExtra("key1", "key1 value");
            startActivityForResult(i, 0);
        }

        f();

    }

    void f() {
        ContentValues v = new ContentValues();
        v.put("apn_id", 1);
        // Uri u = Uri.parse("content://com.android.contacts/d");
        Uri u = Uri.parse("content://telephony/carriers/preferapnd");
        // getContentResolver().insert(u,v);

    }

    private void setupView() {
        // 搜索输入框
        searchText = (EditText) findViewById(R.id.search_result_text);
        searchText.setText(R.string.app_name);
        searchText.setFocusableInTouchMode(true);
        searchText.setOnFocusChangeListener(wordLayoutListener);

        // 读取上次保存的数据
        sharedPreference = getSharedPreferences(referenceName, 0);
        sharedPreference.getString(referenceSharedKey1, null);
        sharedPreference = getSharedPreferences(referenceName, 0);
        String saveString = sharedPreference.getString(referenceSharedKey1,
                null);
        if (saveString != null) {
            searchText.setText(saveString);
        }

        // 搜索按钮
        searchButton = (Button) findViewById(R.id.search_result_button);
        searchButton.setOnClickListener(searchButtonListener);

        // 关键字列表
        LinearLayout wordLayout = (LinearLayout) findViewById(R.id.wordLayout);
        TextView[] wordText = new TextView[5];
        for (int i = 0; i < 5; i++) {
            wordText[i] = new TextView(this);
            // 有点击效果的图片为xml文件形式
            wordText[i].setTextColor(this.getResources().getColor(
                    R.drawable.index_text_selector));
        }
        wordText[0].setText("无生忍");
        wordText[0].setOnClickListener(wordClickListener0);

        wordText[1].setText("无法忍");
        wordText[1].setOnClickListener(wordClickListener1);

        wordText[2].setText("布施");
        wordText[2].setOnClickListener(wordClickListener2);

        wordText[3].setText("持戒");
        wordText[3].setOnClickListener(wordClickListener3);

        wordText[4].setText("观想");
        wordText[4].setOnClickListener(wordClickListener4);

        List<String> b = new ArrayList<String>();

        Set<String> a = new HashSet<String>();
        a.add("abd");
        a.add("abd");
        // Log.e("hashset @@@@@@@@@@@@" + a.)

        for (int i = 0; i < 5; i++) {
            wordLayout.addView(wordText[i]);
        }

        // 底部gallery
        gallery = (Gallery) findViewById(R.id.gallery);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                imageIds[i][j] = R.drawable.gallery_photo_1;
            }
        }
        gallery.setAdapter(new ImageAdapter(this, imageIds));
        gallery.setOnItemClickListener(galleryItemListener);
        gallery.setSelection(2);
    }

    @Override
    public void onDestroy() {
        // 退出时保存数据
        SharedPreferences.Editor e = sharedPreference.edit();
        e.putString(referenceSharedKey1, String.valueOf(searchText.getText()));
        e.commit();
        super.onDestroy();
    }

    public int forTest() {
        return 1;
    }
}
