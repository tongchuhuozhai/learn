package bc.com;

import android.os.Message;

public interface SystemEventListener {
	/**
	 * 处理系统事件
	 * 
	 * @param msg
	 */
	public void handleSystemEvent(Message msg);
}
