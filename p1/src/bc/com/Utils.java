package bc.com;

import java.util.Date;
import java.util.Formatter;

public class Utils {
	public static String stringForTime(long timeMs) {
		StringBuilder mFormatBuilder = new StringBuilder();
		Formatter mFormatter = new Formatter();
		long totalSeconds = timeMs / 1000;

		long seconds = totalSeconds % 60;
		long minutes = totalSeconds / 60 % 60;
		long hours = totalSeconds / 3600;

		mFormatBuilder.setLength(0);
		if (hours > 0)
			return mFormatter.format("%d:%02d:%02d", new Object[] { Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds) }).toString();

		return mFormatter.format("%02d:%02d", new Object[] { Long.valueOf(minutes), Long.valueOf(seconds) }).toString();
	}
	
	public static long getCurrentLiveTime() {
		long ss = System.currentTimeMillis() + GlobalSetting.TIME_DIFFERENCE;
		Date date = new Date(ss);
		return date.getTime();
	}
}
