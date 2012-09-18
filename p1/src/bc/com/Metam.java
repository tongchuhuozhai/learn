package bc.com;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class Metam extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        Log.d("a", "+++++++++ package name is " + this.getPackageName());
        try {
            ApplicationInfo ai = this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            String value = bundle.getString("foo");
            
            Log.d("a", "+++++++++ package name value : " + value);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("a", "+++++++++++ meta error");

        }

    }
}
