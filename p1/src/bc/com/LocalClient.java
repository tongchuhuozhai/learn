package bc.com;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LocalClient extends Activity implements OnClickListener {

    private ServiceBindm mMyService;
    private IServiceBindm proxyService;
    private TextView mTextView;
    private Button startServiceButton;
    private Button stopServiceButton;
    private Button bindServiceButton;
    private Button unbindServiceButton;
    private Context mContext;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            proxyService = IServiceBindm.Stub.asInterface(service);
        }
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_a);
        setupViews();
    }

    public void setupViews() {

        mContext = LocalClient.this;
        mTextView = (TextView) findViewById(R.id.text);

        startServiceButton = (Button) findViewById(R.id.startservice);
        stopServiceButton = (Button) findViewById(R.id.stopservice);
        bindServiceButton = (Button) findViewById(R.id.bindservice);
        unbindServiceButton = (Button) findViewById(R.id.unbindservice);

        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);
        bindServiceButton.setOnClickListener(this);
        unbindServiceButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == startServiceButton) {
            Intent i = new Intent();
            i.setClass(LocalClient.this, ServiceBindm.class);
            mContext.startService(i);
        } else if (v == stopServiceButton) {
            Intent i = new Intent();
            i.setClass(LocalClient.this, ServiceBindm.class);
            mContext.stopService(i);
        } else if (v == bindServiceButton) {
            Intent i = new Intent();
            i.setClass(LocalClient.this, ServiceBindm.class);
            mContext.bindService(i, mServiceConnection, BIND_AUTO_CREATE);
            try {
                if( proxyService == null)
                    mTextView.setText("proxyService is null");
                else
                    mTextView.setText("client call service method" + proxyService.getSystemTime());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            mContext.unbindService(mServiceConnection);
        }
    }

    class Stub extends android.os.Binder {

    }

}
