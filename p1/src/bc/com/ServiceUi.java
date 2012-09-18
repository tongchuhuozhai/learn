package bc.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ServiceUi extends Activity{
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.service_ui);
        View v = findViewById(R.id.button1);
        v.setOnClickListener( new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                Log.d("aa", "aads");
                startService(new Intent(ServiceUi.this,  
                        ServiceDemo.class));  
//                bindService(service, conn, flags)
            }
        });
        View v1 = findViewById(R.id.button2);
        v1.setOnClickListener( new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                Log.d("aa", "aads");
                stopService(new Intent(ServiceUi.this,  
                        ServiceDemo.class));  
            }
        });
        
        
        
    }

}
