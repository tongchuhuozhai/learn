package bc.com;

import bc.com.ChangeSkin.SDCardListener;
import android.app.Activity;
import android.os.Bundle;
import android.os.FileObserver;
import android.util.Log;

public class Obsevera extends Activity {
    static SDCardListener listener;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        listener = new SDCardListener("/system/custom/com.android.contacts/res/drawable/recent_call_button_normal.PNG");
        listener.startWatching();
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
            new Thread() {
                public void run() {
                    // 批量任务
                    Log.d("Create", "path attribute change close");
                }
            }.start();
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
            case FileObserver.ATTRIB: {
                new Thread() {
                    public void run() {
                        // 批量任务
                        Log.d("Create", "path attribute change close");
                    }
                }.start();
            }
                Log.d("Create", "path attribute change" + path);
                break;

            case android.os.FileObserver.CLOSE_WRITE: {
                new Thread() {
                    public void run() {
                        // 批量任务
                        Log.d("Create", "path attribute change close");
                    }
                }.start();
            }
            }
        }
    }
}
