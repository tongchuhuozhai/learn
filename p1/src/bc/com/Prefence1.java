package bc.com;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Prefence1 extends PreferenceActivity{
    public void onCreate(Bundle b){
        super.onCreate(b);
        addPreferencesFromResource(R.xml.a);
        
    }

}
