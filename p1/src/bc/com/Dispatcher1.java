package bc.com;

import android.os.Handler;
import android.os.Message;

public class Dispatcher1 extends Handler {

	private Listener1 mListener;

	public void handleMessage(Message msg) {

		mListener.handleMessage(msg);

	}
	
	public void setListener(Listener1 listener) {
		mListener = listener;
	}

}
