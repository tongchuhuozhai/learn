package bc.com;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

public class ServiceDemo extends Service {
    
    public void onCreate(){
        super.onCreate();
//        Thread d = new Thread( null, new RunnableImp(), "Service2");
        Thread d = new Thread1("adf");
        d.start();
    }

    class Thread1 extends  Thread{
        
        public Thread1(String fileName) {
        }
        @Override
        public void run(){
            Log.d("Service1", "Runnable++++++++++++++");
        }
    }
    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}
