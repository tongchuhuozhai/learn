package bc.com;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Contacts.People;
import android.util.Log;

public class UriTest extends Activity {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
//        Uri u = Uri.parse("content://media/internal/audio/media/");
        Uri u = Uri.parse("content://media/external/audio/media/3");
        String[] a = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA
        };
        ContentResolver resolver = getContentResolver();
        Cursor cur = resolver.query(u, a, null, null, null);
//        if( cur.getCount() == 0 )
//            Log.d("UriTest", "uri is  null" );
//        else
//            Log.d("UriTest", "uri is "  + cur.getString(1));
    }

}
