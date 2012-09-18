package bc.com;

import android.app.Activity;
import android.os.FileObserver;
import android.util.Log;

public class MyFileObserver extends FileObserver {
    public String absolutePath;

    ChangeSkin m;
    public MyFileObserver(String path) {
        super(path, FileObserver.ALL_EVENTS);
        absolutePath = path;
    }

    @Override
    public void onEvent(int event, String path) {
        if (path == null) {
            return;
        }
        Log.d("Create", "onEvent" );
        switch (event) {
        case FileObserver.CREATE:
            Log.d("Create", "path: create " + path);
        case FileObserver.MODIFY:
            Log.d("Create", "path: modified " + path);
        case FileObserver.DELETE:
            Log.d("Create", "path: delete " + path);
        case FileObserver.ATTRIB:
            Log.d("Create", "path attribute " + path);
            ChangeSkin.handlera.sendEmptyMessage(1000);
            break;
        default:
            break;

        }

    }
}