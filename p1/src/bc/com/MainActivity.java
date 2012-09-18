package bc.com;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.android.internal.telephony.gsm.GsmCallTracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore.Audio;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends BaseActivity {
    private Listennerm listennerm;
    private ArrayList<ActivityEntity> activityEntryList = new ArrayList<ActivityEntity>();
    private Intent intent;
    private String applicationName;
    private ListView listView;
    private Mylogger1 logger;
    static boolean[] listItemState;
    View last;
    private OnItemClickListener itemListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
            Message m = activityEntryList.get(index).msg;
            listennerm.sendMessage(m);
            logger.i("message is " + m.what);
        }
    };
    Parcelble1 p;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        logger = Mylogger1.getLogger();
        applicationName = ApplicationEntry.getPackageName1();
        //
        // final IWindowManager windowManager = IWindowManager.Stub
        // .asInterface(ServiceManager.getService("window"));
        logger.i("onCreate ------------ enter");
        setContentView(R.layout.main_3);

        AssetManager asset = getAssets();
        Log.d("aa", "asset count is " + asset.getGlobalAssetCount());
        
        try {
            String[] s = asset.list("image");
            for(int i=0; i<s.length; i++ )
                Log.d("aaa", s[i]);
        } catch (IOException e) {
            Log.e("aaa", "error");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        p = getIntent().getParcelableExtra(MsgIds.intentData);
        WindowManager.LayoutParams layouParams = getWindow().getAttributes();

        if (p != null) {
            Intent1 i = p.getIntent1();
            layouParams.alpha = 0.0f;
            getWindow().setAttributes(layouParams);
            showDialog(MsgIds.SMS_DialogID);

        } else {
            layouParams.alpha = 1.0f;
            getWindow().setAttributes(layouParams);
            setView();
            listItemState = new boolean[listView.getCount()];
        }

        this.getResources().openRawResourceFd(com.android.internal.R.raw.fallbackring);
//        Uri uri = Settings.System.DEFAULT_RINGTONE_URI;
//        Ringtone r = RingtoneManager.getRingtone(this, uri);
        
        
    }

    MainListAdapter adapter;

    public void setView() {
        listView = (ListView) findViewById(R.id.id1);
        listennerm = ApplicationEntry.getListennerm();

        addActivity();

        adapter = new MainListAdapter(this, activityEntryList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(itemListener);

        Global.app_status = Global.STARTED;
    }

    ActivityEntity e = new ActivityEntity();
    ActivityEntity e1 = new ActivityEntity();
    ActivityEntity e2 = new ActivityEntity();
    ActivityEntity e4 = new ActivityEntity();
    ActivityEntity e5 = new ActivityEntity();
    ActivityEntity e6 = new ActivityEntity();
    ActivityEntity e7 = new ActivityEntity();
    ActivityEntity e8 = new ActivityEntity();
    ActivityEntity e9 = new ActivityEntity();
    ActivityEntity e10 = new ActivityEntity();
    ActivityEntity e11 = new ActivityEntity();
    ActivityEntity e12 = new ActivityEntity();
    ActivityEntity e13 = new ActivityEntity();
    ActivityEntity e14 = new ActivityEntity();
    ActivityEntity e15 = new ActivityEntity();
    ActivityEntity e16 = new ActivityEntity();
    ActivityEntity e17 = new ActivityEntity();
    ActivityEntity e18 = new ActivityEntity();
    ActivityEntity e19 = new ActivityEntity();
    ActivityEntity e20 = new ActivityEntity();
    ActivityEntity e21 = new ActivityEntity();
    ActivityEntity e22 = new ActivityEntity();
    ActivityEntity e23 = new ActivityEntity();
    ActivityEntity e24 = new ActivityEntity();
    ActivityEntity e25 = new ActivityEntity();
    ActivityEntity e26 = new ActivityEntity();
    ActivityEntity e27 = new ActivityEntity();
    ActivityEntity e28 = new ActivityEntity();
    ActivityEntity e29 = new ActivityEntity();
    ActivityEntity e30 = new ActivityEntity();
    ActivityEntity e31 = new ActivityEntity();
    
    private void addActivity() {
        e.data = R.string.index_page;
        e1.data = R.string.phy;
        e2.data = R.string.net1;
        e4.data = R.string.resolver;
        e5.data = R.string.contact;
        e6.data = R.string.gif_animate;
        e7.data = R.string.player;
        e8.data = R.string.g3_spinner;
        e9.data = R.string.bitmap;
        e10.data = R.string.process_title;
        e11.data = R.string.control_process_title;
        e12.data = R.string.list_item_color;
        e13.data = R.string.sliding_tab;
        e14.data = R.string.preference_activity;
        e15.data = R.string.wall_paper;
        e16.data = R.string.wall_paper;
        e17.data = R.string.wall_paper;
        e18.data = R.string.wall_paper;
        e18.data = R.string.wall_paper;
        e19.data = R.string.bind_service;
        e20.data = R.string.wake_lock;
        e21.data = R.string.wake_lock;
        e22.data = R.string.wake_lock;
        e23.data = R.string.change_skin;
        e24.data = R.string.change_skin;
        e25.data = R.string.change_skin;
        e26.data = R.string.change_skin;
        e27.data = R.string.other_app;
        e28.data = R.string.layout_weight;
        e29.data = R.string.thread_test;
        e30.data = R.string.md5;
        e31.data = R.string.meta;
        
        activityEntryList.add(e);

        activityEntryList.add(e1);

        activityEntryList.add(e2);

        activityEntryList.add(e4);

        activityEntryList.add(e5);

        activityEntryList.add(e6);

        activityEntryList.add(e7);
        activityEntryList.add(e8);
        activityEntryList.add(e9);
        activityEntryList.add(e10);
        activityEntryList.add(e11);
        activityEntryList.add(e12);
        activityEntryList.add(e13);
        activityEntryList.add(e14);
        activityEntryList.add(e15);
        activityEntryList.add(e16);
        activityEntryList.add(e17);
        activityEntryList.add(e18);
        activityEntryList.add(e19);
        activityEntryList.add(e20);
        activityEntryList.add(e21);
        activityEntryList.add(e22);
        activityEntryList.add(e23);
        activityEntryList.add(e24);
        activityEntryList.add(e25);
        activityEntryList.add(e26);
        activityEntryList.add(e27);
        activityEntryList.add(e28);
        activityEntryList.add(e29);
        activityEntryList.add(e30);
        activityEntryList.add(e31);
    }

    @Override
    protected void onPause() {
        Log.d("adf", "+++onPause ------ enter");

        super.onPause();
    }

    int count;

    @Override
    protected void onResume() {
        logger.i("onResume ------ enter");
        if (p == null) {
            addListener1();
        }
        count = listView.getCount();
        super.onResume();

    }
    
    

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

    public void addListener1() {
        e.msg = listennerm.obtainMessage(MsgIds.IndexActivityID);
        e1.msg = listennerm.obtainMessage(MsgIds.BookTabID);
        e2.msg = listennerm.obtainMessage(MsgIds.net1ID);
        e4.msg = listennerm.obtainMessage(MsgIds.showcontactID);
        e5.msg = listennerm.obtainMessage(MsgIds.ContactActivityID);
        e6.msg = listennerm.obtainMessage(MsgIds.StreamMarkID);
        e7.msg = listennerm.obtainMessage(MsgIds.Media_Huawei_ActivityID);
        e8.msg = listennerm.obtainMessage(MsgIds.G3ID);
        e9.msg = listennerm.obtainMessage(MsgIds.BitmapActivityID);
        e10.msg = listennerm.obtainMessage(MsgIds.PROCESSID);
        e11.msg = listennerm.obtainMessage(MsgIds.CONTROL_LAYOUT_ID);
        e12.msg = listennerm.obtainMessage(MsgIds.LIST_ITEM_COLOR_ID);
        e13.msg = listennerm.obtainMessage(MsgIds.SLIDINGTAB_ID);
        e14.msg = listennerm.obtainMessage(MsgIds.PREFERENCE_ID);
        e15.msg = listennerm.obtainMessage(MsgIds.WALL_PAPER_ID);
        e16.msg = listennerm.obtainMessage(MsgIds.COLOR_ID);
        e17.msg = listennerm.obtainMessage(MsgIds.SERVICE_ID);
        e18.msg = listennerm.obtainMessage(MsgIds.SIM_ID);
        e19.msg = listennerm.obtainMessage(MsgIds.BIND_ID);
        e20.msg = listennerm.obtainMessage(MsgIds.WAKELOCK_ID);
        e21.msg = listennerm.obtainMessage(MsgIds.Uri_ID);
        e22.msg = listennerm.obtainMessage(MsgIds.BROWSE_ID);
        e23.msg = listennerm.obtainMessage(MsgIds.CHANGE_SKIN_ID);
        e24.msg = listennerm.obtainMessage(MsgIds.TELEPHONE_ID);
        e25.msg = listennerm.obtainMessage(MsgIds.OBSEVER_ID);
        e26.msg = listennerm.obtainMessage(MsgIds.VM_DONW_ID);
        e27.msg = listennerm.obtainMessage(MsgIds.PARTNER_ID);
        e28.msg = listennerm.obtainMessage(MsgIds.WEIGHT_ID);
        e29.msg = listennerm.obtainMessage(MsgIds.THREAD_ID);
        e30.msg = listennerm.obtainMessage(MsgIds.MD5_ID);
        e31.msg = listennerm.obtainMessage(MsgIds.META_ID);
    }

    @Override
    protected void onDestroy() {
        logger.i("onDestroy ------ enter");
        super.onDestroy();

        android.os.Process.sendSignal(android.os.Process.myPid(), android.os.Process.SIGNAL_KILL);
    }

    class ActivityEntity {
        int data;
        Message msg;
    }

    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        // TODO Auto-generated method stub
        final View v = LayoutInflater.from(context).inflate(R.layout.passwd1, null);
        return new AlertDialog.Builder(this).setTitle("passwd").setView(v).setIcon(android.R.drawable.ic_dialog_alert).setMessage("message").setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        EditText et = (EditText) v.findViewById(R.id.pass_wd);
                        Log.d("sfkj", " " + et.getText());
                        if (et.getText().toString().equals("a")) {
                            intent = new Intent(MainActivity.this, BookTab.class);
                            intent.putExtra("title", R.string.huayan);
                            intent.putExtra("tab1", "/data/data/" + applicationName + "/res1/huayan.xml");
                            intent.putExtra("tab2", "/data/data/" + applicationName + "/res1/lengjia.xml");
                            intent.putExtra("tab3", "/data/data/" + applicationName + "/res1/boruojing.xml");
                            intent.putExtra("tab4", "/data/data/" + applicationName + "/res1/jimi.xml");
                            intent.putExtra("tab5", "/data/data/" + applicationName + "/res1/lifa.xml");
                            et.getText().clear();
                            accessNextPage(intent);
                        }

                    }
                }).create();
    }

    @Override
    public void handleMessage1(Message msg) {
        super.handleMessage1(msg); // 此句保证父类handleMessage1调用子类handleMessage1
        switch (msg.what) {
        case MsgIds.IndexActivityID:
            Uri uri = Audio.Media.getContentUri("internal");
            Uri a = Uri.withAppendedPath(uri, "27");
            try {
                getContentResolver().openAssetFileDescriptor(a, "r");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Ringtone r = RingtoneManager.getRingtone(this, a);
            r.play();
            
            intent = new Intent(MainActivity.this, IndexActivity.class);

            accessNextPage(intent);
            break;
        case MsgIds.BookTabID:

            showDialog(100);

            break;
        case MsgIds.net1ID:
            intent = new Intent("aaa");
            ComponentName cn = new ComponentName("bc.com", "bc.com.net1");
            intent.setComponent(cn);
            accessNextPage(intent);
            break;
        case MsgIds.BroadcastReceiver1ID:
            intent = new Intent(MainActivity.this, BroadcastReceiver1.class);
            accessNextPage(intent);
            break;
        case MsgIds.showcontactID:
            intent = new Intent(MainActivity.this, showcontact.class);
            accessNextPage(intent);
            break;
        case MsgIds.ContactActivityID:
            intent = new Intent(MainActivity.this, ContactActivity.class);
            accessNextPage(intent);
            break;
        case MsgIds.StreamMarkID:
            intent = new Intent(MainActivity.this, StreamMark.class);
            accessNextPage(intent);
            break;
        case MsgIds.Media_Huawei_ActivityID:
            // intent = new Intent(MainActivity.this,
            // Media_Huawei_Activity.class);
            // accessNextPage(intent);
            break;
        case MsgIds.G3ID:
            intent = new Intent(MainActivity.this, G3.class);
            accessNextPage(intent);
            break;
        case MsgIds.BitmapActivityID:
            intent = new Intent(MainActivity.this, BitmapActivity.class);
            accessNextPage(intent);
            break;
        case MsgIds.PROCESSID:
            intent = new Intent(MainActivity.this, ProcessBarAcitivity.class);
            accessNextPage(intent);
            break;
        case MsgIds.CONTROL_LAYOUT_ID:
            intent = new Intent(MainActivity.this, RelativeLayout1.class);
            accessNextPage(intent);
            break;
        case MsgIds.SLIDINGTAB_ID:
            intent = new Intent(MainActivity.this, Sliding1.class);
            accessNextPage(intent);
            break;
        case MsgIds.PREFERENCE_ID:
            intent = new Intent(MainActivity.this, Prefence1.class);
            accessNextPage(intent);
            break;
        case MsgIds.WALL_PAPER_ID:
            intent = new Intent(MainActivity.this, WallPaper1.class);
            accessNextPage(intent);
            break;
        case MsgIds.COLOR_ID:
            intent = new Intent(MainActivity.this, Colorsa.class);
            accessNextPage(intent);
            break;
        case MsgIds.SERVICE_ID:
            intent = new Intent(MainActivity.this, ServiceUi.class);
            accessNextPage(intent);
            break;
        case MsgIds.SIM_ID:
            intent = new Intent(MainActivity.this, SimUtil.class);
            accessNextPage(intent);
            break;
        case MsgIds.BIND_ID:
            intent = new Intent(MainActivity.this, LocalClient.class);
            accessNextPage(intent);
            break;
        case MsgIds.WAKELOCK_ID:
            intent = new Intent(MainActivity.this, WakeLoakm.class);
            accessNextPage(intent);
            break;
        case MsgIds.Uri_ID:
            intent = new Intent(MainActivity.this, UriTest.class);
            accessNextPage(intent);
            break;
        case MsgIds.BROWSE_ID:
            intent = new Intent(MainActivity.this, BrowsePic.class);
            accessNextPage(intent);
            break;
        case MsgIds.CHANGE_SKIN_ID:
            intent = new Intent(MainActivity.this, ChangeSkin.class);
            accessNextPage(intent);
            break;
        case MsgIds.TELEPHONE_ID:
            intent = new Intent(MainActivity.this, TelephoneInject.class);
            accessNextPage(intent);
            break;
        case MsgIds.OBSEVER_ID:
            intent = new Intent(MainActivity.this, Obsevera.class);
            accessNextPage(intent);
            break;
        case MsgIds.VM_DONW_ID:
            intent = new Intent(MainActivity.this, ActivityPortrait.class);
            accessNextPage(intent);
            break;
        case MsgIds.PARTNER_ID:
            intent = new Intent("c");
            ComponentName cna = new ComponentName("aa.com", "aa.com.aa");
            intent.setComponent(cna);
            accessNextPage(intent);
            break;
        case MsgIds.WEIGHT_ID:
            intent = new Intent(MainActivity.this, abc.class);
            accessNextPage(intent);
            break;
        case MsgIds.THREAD_ID:
            intent = new Intent(MainActivity.this, ThreadTest.class);
            accessNextPage(intent);
            break;
        case MsgIds.MD5_ID:
            intent = new Intent(MainActivity.this, MD5.class);
            accessNextPage(intent);
            break;
        case MsgIds.META_ID:
            intent = new Intent(MainActivity.this, Metam.class);
            accessNextPage(intent);
            break;

        default:
            break;
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.d("aaa", "+++onStop is callsed ");
    }

    
    private void accessNextPage(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Controller1.getConller1().removeListener(this);
        startActivityForResult(intent, 0);
    }

    private static class MainListAdapter extends BaseAdapter {
        Context c;
        ArrayList<ActivityEntity> listE;

        public MainListAdapter(Context c, ArrayList<ActivityEntity> listE) {
            this.c = c;
            this.listE = listE;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listE.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup arg2) {
            ViewHolder vh = new ViewHolder();
            // 获取视图
            if (convertView == null) {
                convertView = LayoutInflater.from(c).inflate(R.layout.list_a, null);
                vh.tv = (TextView) convertView.findViewById(R.id.list_id_id2);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            // 设置数据
            vh.tv.setText(listE.get(position).data);

            if (listItemState[position]) {
                convertView.setSelected(true);
                convertView.setPressed(true);
                convertView.setBackgroundResource(R.drawable.button_s);
                // convertView.setBackgroundResource(R.drawable.button_s);
            } else {
                convertView.setSelected(false);
                convertView.setPressed(false);
                convertView.setBackgroundResource(R.drawable.cmcc_tab_bg);
                convertView.setBackgroundColor(Color.BLACK);
            }
            return convertView;
        }

        class ViewHolder {
            TextView tv;
        }
    }
}
