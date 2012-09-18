package bc.com;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import android.os.Message;

public class Controller1 implements Listener1 {

	static Controller1 c;
	Listennerm listennerm;

	public Controller1() {

	}

	public static Controller1 getConller1() {
		if (c == null) {
			c = new Controller1();
		}
		return c;
	}

	public void init() {
		listennerm = ApplicationEntry.getInstance().getListennerm();
		listennerm.setListener(this);
		logger = Mylogger1.getLogger();
	}

	HashMap h = new HashMap<String, Listener1>();
	private Mylogger1 logger;

	@Override
	public void handleMessage(Message msg) {
		logger.d(" Controller1 handleMessage");
		for (Iterator<Entry<String, Listener1>> i = h.entrySet().iterator(); i.hasNext();) {
			i.next().getValue().handleMessage(msg);
		}
	}

	public void addListener(Listener1 l) {
		logger.d(" Controller1 addListener ");
		if (h.get(l.getClass().getName()) == null) {
			h.put(l.getClass().getName(), l);
		}
	}

	public void removeListener(Listener1 listener) {
		logger.d(" Controller1 removeListener ");
		if (h.get(listener.getClass().getName()) != null) {
			h.remove(listener.getClass().getName());
		}
	}

}
