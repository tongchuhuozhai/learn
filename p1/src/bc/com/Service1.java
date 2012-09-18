package bc.com;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class Service1 extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "servie ", Toast.LENGTH_SHORT).show();
		return null;
	}
	
    @Override
    public void onCreate() {
    	Toast.makeText(this, "servie on create", Toast.LENGTH_SHORT).show();
    	
    }


}
