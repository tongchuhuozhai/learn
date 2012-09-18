package bc.com;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WakeLoakm extends Activity implements OnClickListener {

    PowerManager pm;
    WakeLock partialLock;
    WakeLock fullLock;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.wake_lock_m);
        Button realsePartialLock = (Button) findViewById(R.id.releae_partial_lock);
        Button realseFullLock = (Button) findViewById(R.id.releae_full_lock);
        realsePartialLock.setOnClickListener(this);
        realseFullLock.setOnClickListener(this);
        
        
        pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        partialLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "taga");
        fullLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP, "tagb");
        fullLock.acquire();
        partialLock.acquire();
//        fullLock.release();
//        partialLock.release();

    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
        case R.id.releae_partial_lock:
            if (partialLock.isHeld())
                partialLock.release();
            break;

        case R.id.releae_full_lock:
            if (fullLock.isHeld()){
                Log.d("a", "+++++++++++++++++ lock ");
                fullLock.release();
            }
            break;
        default:
            break;
        }

    }

}
