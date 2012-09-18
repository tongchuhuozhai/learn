package bc.com;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

// 不解
public class MyProvider extends ContentProvider {

	MyHelper dbHelper;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId;
		rowId = db.insert("table1", "_id", values);

		// 不解
		Uri uri1 = ContentUris.withAppendedId(CountryCode.CONTENT_URI, rowId);
		getContext().getContentResolver().notifyChange(uri1, null);

		return uri1;
	}

	@Override
	public boolean onCreate() {
		dbHelper = new MyHelper(getContext(), "db_name1", null, 1);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
