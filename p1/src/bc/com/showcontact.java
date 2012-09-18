package bc.com;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.Contacts.Phones;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class showcontact extends ListActivity {
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Cursor c = getContentResolver().query(Phones.CONTENT_URI, null, null,null, null);
		startManagingCursor(c);
		ListAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, c, new String[] {
						Phones.NAME, Phones.NUMBER }, new int[] {
						android.R.id.text1, android.R.id.text2 });
		Log.e("@@@@@@ Build.VERSION.CODENAME: " , Build.VERSION.CODENAME);
		Log.e("@@@@@@ Build.VERSION.INCREMENTAL : " , Build.VERSION.INCREMENTAL);
		Log.e("@@@@@@ Build.VERSION.RELEASE : " , Build.VERSION.RELEASE);
		Log.e("@@@@@@ Build.VERSION.SDK : " , Build.VERSION.SDK);
		Log.e("@@@@@@ Build.VERSION.SDK : " , " " + Build.VERSION.SDK_INT);
//		Log.e("@@@@@@ : " + )
		setListAdapter(adapter);

		/*
		 * 
		 * 		Header[] hs = hr.getAllHeaders();
		for (Header h : hs) {
			if (h.getName() != null) {
				logger.i("-----name-----" + h.getName() + "------"
						+ h.getValue());
				if ("CONTENT-LENGTH".equals(h.getName().toUpperCase())) {
					index.contentLength = h.getValue();
				} else if ("CONTENT-TYPE".equals(h.getName().toUpperCase())) {
					index.contentType = h.getValue();
				}
			}
		}
		http头中的必要消息: 
		 * 
		 * 
		 * 01-01 08:28:56.312: INFO/[MobileVideo](1497): [ Thread-233: MVHttpTask.java:137 ] - -----name-----name: Content-Type------value: application/xml; charset=UTF-8
01-01 08:28:56.320: INFO/[MobileVideo](1497): [ Thread-233: MVHttpTask.java:137 ] - -----name-----Content-Length------10546
01-01 08:28:56.320: INFO/[MobileVideo](1497): [ Thread-233: MVHttpTask.java:137 ] - -----name-----Date------Fri, 07 Jan 2011 07:59:50 GMT
01-01 08:28:56.320: INFO/[MobileVideo](1497): [ Thread-233: MVHttpTask.java:137 ] - -----name-----Connection------Keep-Alive
01-01 08:28:56.327: INFO/[MobileVideo](1497): [ Thread-233: MVHttpTask.java:137 ] - -----name-----Proxy-Connection------Keep-Alive
01-01 08:28:56.327: INFO/[MobileVideo](1497): [ Thread-233: MVHttpTask.java:137 ] - -----name-----Keep-Alive------timeout=15, max=49
01-01 08:28:56.327: INFO/[MobileVideo](1497): [ Thread-233: MVHttpTask.java:137 ] - -----name-----Via------HTTP/1.1 SHSH-PS-WAP-GW10(infoX-WISG, Huawei Technologies)
01-01 08:28:56.335: INFO/[MobileVideo](1497): [ Thread-233: MVHttpTask.java:137 ] - -----name-----Server------Apache/2.2.13 (Unix) DAV/2 mod_jk/1.2.27



		 */
	}

}
