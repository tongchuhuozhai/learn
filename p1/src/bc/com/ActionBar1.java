package bc.com;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class ActionBar1 extends Activity {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.actionbara, null);
        setContentView(R.layout.action_layout);
//        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayShowTitleEnabled(true);
//        getActionBar().setDisplayShowHomeEnabled(true);
//        getActionBar().setCustomView(v);
        
    }

}
