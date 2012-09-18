package bc.com;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SimUtil extends Activity {
    
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.sim);
        View v = findViewById(R.id.buta);
        v.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Uri simUri = Uri.parse("content://icc/adn");
                ContentValues localContentValues = new ContentValues();
                localContentValues.put("tag", "aaaaaaaaaa");
                localContentValues.put("number", "3333333333333");
                
                
                ContentResolver localObject1 = getContentResolver();
                localObject1.insert(simUri, localContentValues);
                
            }
        });
    }
    
}
