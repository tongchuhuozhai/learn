package bc.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

public class ActivityPortrait extends Activity{
    
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
//        setContentView(R.layout.portait);
//        Intent i = new Intent(this, );
//        Intent intent = new Intent(ActivityPortrait.this, ActivityHorizontal.class);
//        startActivity(intent);
        
//        findViewById(R.id.but_a).setBackgroundColor(Color.BLUE);
        constructDialog(0); 
        finish();
        
        
        
    }
    
    private ProgressDialog constructDialog(int retryCount) {
        // figure out the message to display. 
        // create a system dialog that will persist outside this activity.
        ProgressDialog pd = new ProgressDialog(ActivityPortrait.this);
        pd.setTitle(getText(R.string.alarm1));
        pd.setMessage("df");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
//        pd.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
        pd.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        // show the dialog
        pd.show();
        
//        ProgressDialog progressDialog = ProgressDialog.show(ActivityPortrait.this, "Loading...", "Please wait...", true, false);  
        
        return pd;
    }

}
