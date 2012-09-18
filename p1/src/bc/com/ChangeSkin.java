package bc.com;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class ChangeSkin extends Activity {

    static SDCardListener listener;
    static File bgFile;
    public static Button btn;

    public void onCreate(Bundle a) {

        startService(new Intent(ChangeSkin.this.getApplicationContext(), FileModificationService.class));
        super.onCreate(a);
        setContentView(R.layout.change_skina);
        Context friendContext = null;
        // listener = new SDCardListener("/system/custom/com.android.contacts/res/drawable/recent_call_button_normal.PNG");
        try {
            friendContext = this.createPackageContext("bc.com.a", Context.CONTEXT_IGNORE_SECURITY);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        btn = (Button) findViewById(R.id.skina);
        btn.setBackgroundDrawable(friendContext.getResources().getDrawable(R.drawable.back));

        AssetManager asset = getAssets();
//        Log.d("aa", "asset count is " + asset.getGlobalAssetCount());

        try {
            String[] s = asset.list("image");
            for (int i = 0; i < s.length; i++)
                Log.d("aaa", s[i]);
        } catch (IOException e) {
            Log.e("aaa", "error");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        bgFile = new File("/system/custom/com.android.contacts/res/drawable/recent_call_button_normal.PNG");

        Log.d("ddddddddddd", getApplication().getPackageName());
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            // btn.setText(p.applicationInfo.packageName);
            // Log.d("dd", ""+ p.applicationInfo.packageName);

        }

        // PackageManager pManager = this.getPackageManager();
        // List<PackageInfo> appList = Utils.getAllApps(this);
        //        
        // for(int i=0;i<appList.size();i++) {
        // PackageInfo pinfo = appList.get(i);
        // // ShareItemInfo shareItem = new ShareItemInfo();
        // // //set Icon
        // // shareItem.setIcon(pManager.getApplicationIcon(pinfo.applicationInfo));
        // // //set Application Name
        // // shareItem.setLabel(pManager.getApplicationLabel(pinfo.applicationInfo).toString());
        // // //set Package Name
        // // shareItem.setPackageName(pinfo.applicationInfo.packageName);
        // Log.d("dd", ""+ pinfo.applicationInfo.packageName);
        // btn.setText(info.applicationInfo.packageName);

    }

    public static void changeBackground() {
        Drawable drawable = null;
//        Drawable.createFromResourceStream(res, value, is, srcName)
        try {
            drawable = Drawable.createFromPath(bgFile.getCanonicalPath());
            if (null != drawable) {
                btn.setBackgroundDrawable(drawable);
            }
        } catch (IOException e) {
            btn.setText("error");

        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        stopService(new Intent(ChangeSkin.this.getApplicationContext(), FileModificationService.class));
    }

    public static class SDCardListener extends FileObserver {
        public SDCardListener(String path) {
            /*
             * 这种构造方法是默认监听所有事件的,如果使用super(String,int)这种构造方法， 则int参数是要监听的事件类型.
             */
            super(path);
        }

        @Override
        public void onEvent(int event, String path) {
            Log.d("all", "=============path:");
            switch (event) {
            case FileObserver.ALL_EVENTS:
                Log.d("all", "path:" + path);
                break;
            case FileObserver.CREATE:
                Log.d("Create", "path:" + path);
            case FileObserver.MODIFY:
                Log.d("Create", "path: modified" + path);
            case FileObserver.DELETE:
                Log.d("Create", "path: delete" + path);
            case FileObserver.ATTRIB:
                Log.d("Create", "path attribute change" + path);
                break;
            }
        }
    }


    public static Handler handlera = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
            case 1000:
                changeBackground();
                break;
            }
        }

    };


}
