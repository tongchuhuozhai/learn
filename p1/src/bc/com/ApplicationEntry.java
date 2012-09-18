package bc.com;

import android.app.Application;

public class ApplicationEntry extends Application {

	private static ApplicationEntry instance;
	public static Listennerm d;

	public static ApplicationEntry getInstance() {
		if (instance == null) {
			instance = new ApplicationEntry();
		}
		return instance;
	}

	private static String pName;
	private static Controller1 c;

	public void onCreate() {
		d = new Listennerm();
		c = Controller1.getConller1();
		c.init();
		pName = getPackageName();

	}

	public static Listennerm getListennerm() {
		return d;
	}

	public static Controller1 getController1() {
		return c;
	}

	public static String getPackageName1() {
		return pName;
	}

}
