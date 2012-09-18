package bc.com;

import android.util.Log;

public class Mylogger1 {

	static Mylogger1 logger;

	private String getTraceInfo() {
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();

		if (sts == null) {
			return null;
		}

		for (StackTraceElement st : sts) {
			if (st.isNativeMethod()) {
				continue;
			}

			if (st.getClassName().equals(Thread.class.getName())) {
				continue;
			}

			if (st.getClassName().equals(this.getClass().getName())) {
				continue;
			}

			return "[ " + Thread.currentThread().getName() + ": "
					+ st.getFileName() + ":" + st.getLineNumber() + " ]";
		}

		return null;
	}
	
	public Mylogger1() {

	}

	public static Mylogger1 getLogger() {
		if (logger == null) {
			logger = new Mylogger1();
		}
		return logger;
	}
	
	public void i(String s){
		
		Log.i("test", getTraceInfo() + s);
	}

	public void d(String s) {
		Log.d("test",getTraceInfo() + s);
		
	}

}
