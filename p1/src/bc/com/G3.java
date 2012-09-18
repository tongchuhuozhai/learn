package bc.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class G3 extends BaseActivity  {

	public Instrumentation mInst = null;
	private ListView listView;
	private Gallery mChannelGallery = null;
	// 下拉列表
	private Spinner spinner;

	private Listennerm dispatcher;

	private Intent intent;

	private SQLiteDatabase dataBase;
	private MyHelper dataBaseHelper;
	private Cursor cursor;

	private OnItemClickListener itemListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> convert, View arg1, int index, long arg3) {
			ListView s = (ListView) convert;
			if (index == 0) {
				dispatcher.sendMessage(dispatcher.obtainMessage(MsgIds.A));
			} else if (index == 1) {
				dispatcher.sendMessage(dispatcher.obtainMessage(MsgIds.B));
			} else if (index == 2) {
				dispatcher.sendMessage(dispatcher.obtainMessage(MsgIds.C));
			} else {
				dispatcher.sendMessage(dispatcher.obtainMessage(MsgIds.D));
			}
		}
	};

	private OnItemClickListener galleryItemListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
			setList(index);
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
	
	}

	// private Listener1 messageListener = new Listener1() {
	// @Override
	// public void handleMessage(Message msg) {
	// switch (msg.what) {
	// case 2001:
	// intent = new Intent(G3.this, StreamMark.class);
	// startActivityForResult(intent, 0);
	// break;
	// case 2002:
	// intent = new Intent(G3.this, Media_Huawei_Activity.class);
	// startActivityForResult(intent, 0);
	// break;
	// case 2003:
	// String path = Environment.getExternalStorageDirectory()
	// .getPath();
	// if (android.os.Environment.getExternalStorageState().equals(
	// android.os.Environment.MEDIA_MOUNTED))
	// try {
	// if (checkLastSpacePrivate(path) > (5 * 1024 * 1024)) {
	// showMsg("有5M空间");
	// } else {
	// showMsg("有存储卡");
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// else
	// showMsg("无存储卡");
	// break;
	// case 2004:
	// showDialog(1);
	// break;
	// default:
	// break;
	// }
	// }
	// };

	private static long checkLastSpacePrivate(String path) throws Exception {
		String dir = path;
		File base = new File(dir);
		StatFs stat = new StatFs(base.getPath());
		return stat.getBlockSize() * ((long) stat.getAvailableBlocks() - 4);
	}

	public void showMsg(String msg) {
		Toast.makeText(this, msg, 2000).show();
	}

	@Override
	public void onCreate(Bundle v) {
		super.onCreate(v);
		super.init(R.layout.g3);
		// TouchUtils.dras
		// final WindowManager windowManager = WindowManager.
		// Stub
		// .asInterface(ServiceManager.getService("window"));
		// mInst = getInstrumentation();
		// TouchUtils.clickView(test, v)

		setupView();

		// 获取消息处理器
		dispatcher = ApplicationEntry.getListennerm();

		setDB();

		spinner = (Spinner) findViewById(R.id.spiner1);

		// 所要显示的列的名字
		String[] collumNames = new String[] { "country" };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, collumNames,
				new int[] { android.R.id.text1 });
		spinner.setAdapter(adapter);

		// startInstrumentation(new ComponentName(G3.this,
		// ContactsFilterInstrumentation.class), null, null);
		// Instrumentation.ActivityMonitor.this.
		// windowManager.i
	}

	private void setDB() {
		// 创建数据库
		dataBaseHelper = new MyHelper(this, "db_name1", null, 1);
		dataBase = dataBaseHelper.getWritableDatabase();

		// 插入一行数据
		ContentValues values = new ContentValues();
		values.put("country", "abd");
		dataBase.insert("table1", "_id", values);

		values.clear();
		values.put("country", "abd1");
		dataBase.insert("table1", "_id", values);

		// 查询
		cursor = dataBase.query("table1", null, null, null, null, null, "_id DESC");
		int collomIndex = cursor.getColumnIndexOrThrow("country");
		Log.d("tag1", "@@@@@@@@@db tag is: " + dataBase.getPath());
		for (cursor.moveToFirst(); (!cursor.isAfterLast()); cursor.moveToNext()) {
			Log.d("data is", cursor.getString(collomIndex));
		}
	}

	void insert() {

	}

	@Override
	public void onDestroy() {
		dataBase.close();
		super.onDestroy();
	}

	private void setupView() {
		setGallery();
		setList(0);
	}

	private void setGallery() {
		List<String> adapterData = new ArrayList<String>();
		adapterData.add("abcd1");
		adapterData.add("abcd2");
		adapterData.add("abcd3");
		adapterData.add("abcd4");
		adapterData.add("abcd5");
		adapterData.add("abcd6");
		mChannelGallery = (Gallery) findViewById(R.id.gallery);
		mChannelGallery.setAdapter(new GalleryAdapter1(this, adapterData));
		mChannelGallery.setOnItemClickListener(galleryItemListener);
		mChannelGallery.setSelection(2);
		// mChannelGallery.ha
	}

	private void setList(int index) {
		List<String> adapterData = new ArrayList<String>();
		if (index == 0) {
			File f = new File("/data/data/bc.com/res1/vajra.xml");
			InputStream is = null;
			try {
				is = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			XMLParserAPN x = new XMLParserAPN(is);
			for (int i = 0; i < 3; i++) {
				String s = x.getDataByTag1("chapter", i).get("name");
				adapterData.add(s);
			}

		} else if (index == 1) {
			adapterData.add("世主妙严之一");
			adapterData.add("世主妙严之二");
			adapterData.add("世主妙严之三");
			adapterData.add("世主妙严之四");
			adapterData.add("毗卢遮那品");
			adapterData.add("如来名号品");
			adapterData.add("四圣谛品");
			adapterData.add("光明觉品");
			adapterData.add("菩萨问明品");
			adapterData.add("净行品");
			adapterData.add("贤首品");
			adapterData.add("升须弥山顶品");
			adapterData.add("须弥顶上偈赞品");
			adapterData.add("十住品");
			adapterData.add("梵行品");
			adapterData.add("初发心功德品");
		} else {
			for (int i = 0; i < 5; i++) {
				adapterData.add("bbb");
			}
		}

		ListAdapter1 adapter = new ListAdapter1(this, adapterData);
		listView = (ListView) findViewById(R.id.list2);
		listView.setOnItemClickListener(itemListener);
		listView.setAdapter(adapter);

	}

	@Override
	public void handleMessage1(Message msg) {
		super.handleMessage1(msg);
		switch (msg.what) {
		case MsgIds.A:
			intent = new Intent(G3.this, StreamMark.class);
			startActivityForResult(intent, 0);
			break;
		case MsgIds.B:
//			intent = new Intent(G3.this, Media_Huawei_Activity.class);
//			startActivityForResult(intent, 0);
			break;
		case MsgIds.C:
			String path = Environment.getExternalStorageDirectory().getPath();
			if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
				try {
					if (checkLastSpacePrivate(path) > (5 * 1024 * 1024)) {
						showMsg("有5M空间");
					} else {
						showMsg("有存储卡");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			else
				showMsg("无存储卡");
			break;
		case MsgIds.D:
			showDialog(1);
			break;
		default:
			break;
		}
	}

}