package bc.com;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.format.Time;
import android.util.Log;
public class ServiceBindm extends Service {  
    private static final String TAG = "IOutService";  
   
    @Override  
    public IBinder onBind(Intent intent) {  
        Log.e(TAG, "onBind");  
        IServiceBindm.Stub mBinder = new IServiceBindm.Stub(){        
            @Override       
            public String getSystemTime() throws RemoteException {
                Time t = new Time();            
                t.setToNow();           
                return t.toString()+ "this is the method service provide ";     
             }  
         };
        return mBinder;       
        
    }  
    @Override  
    public void onCreate() {  
        Log.e(TAG, "service onCreate");  
        super.onCreate();  
    }  
      
    @Override  
    public void onStart(Intent intent, int startId) {  
        Log.e(TAG, "service onStart");  
        super.onStart(intent, startId);
    }  
      
    @Override  
    public void onDestroy() {  
        Log.e(TAG, "service onDestroy");  
        super.onDestroy();  
    }  
      
      
    @Override  
    public boolean onUnbind(Intent intent) {  
        Log.e(TAG, "service onUnbind");  
        return super.onUnbind(intent);  
    }  
  
}  