package bc.com;

import java.io.File;
import java.util.ArrayList;
import java.util.zip.Inflater;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContentProviderOperation.Builder;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.ContactsContract.CommonDataKinds.GroupMembership;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.provider.ContactsContract.CommonDataKinds.Nickname;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.provider.ContactsContract.CommonDataKinds.Organization;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.CommonDataKinds.Website;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class BackupContacts extends Activity {

    private ProgressDialog mProgressDialogForReadVCard;

    @Override
    public void onCreate(Bundle b) {

        super.onCreate(b);
        setContentView(R.layout.backup_contacts);
        ImageView v = (ImageView) findViewById(R.id.contact_icon);
        v.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Log.d("a", "++++ onclick . ");
//                contactsBackup();
                showDialog(1);
            }

        });

        ImageView v1 = (ImageView) findViewById(R.id.restore_contact_icon);
        v1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Log.d("a", "++++ onclick . ");
                restoreContacts();
            }
        });
        
       t = new Thread1();


//       LayoutInflater l = (LayoutInflater ) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//       View v = l.inflate(R.layout., root)
    }
    Thread1 t ;
    protected Dialog onCreateDialog(int id) {
        switch (id) {

        case 1: 
            if (mProgressDialogForReadVCard == null) {
                String title = "backup ";
                String message = "message ";
                mProgressDialogForReadVCard = new ProgressDialog(this);
                mProgressDialogForReadVCard.setTitle(title);
                mProgressDialogForReadVCard.setMessage(message);
               
//                mProgressDialogForReadVCard.setView(view);
                mProgressDialogForReadVCard.setIndeterminate(false);// false代表根据程序进度确切的显示进度
                Uri localUri = Uri.parse("content://sms");
                Cursor localCursor = getContentResolver().query(localUri, null, null,
                        null, null);
             
                int m = localCursor.getCount();
                
                Uri ri = ContactsContract.Data.CONTENT_URI;
                Cursor cursor = getContentResolver().query(ri, null, null, null, null);
                int n= cursor.getCount();
                
                
                mProgressDialogForReadVCard.setMax(m+n);
                mProgressDialogForReadVCard.setProgress(0);
                mProgressDialogForReadVCard.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                
                t.start();
            }

            
            break;
           
        
        }
        return mProgressDialogForReadVCard;
    }

    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1000:
                    mProgressDialogForReadVCard.setMessage("backup sms . " );
                    break;
                    
            }
        }
    };
    
    class Thread1 extends Thread {
        public void run(){
            contactsBackup();
            
            backupSms();
        }
    }
    private void restoreContacts() {
        long t = System.currentTimeMillis();

        int i = 1;
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        File backupPath = Environment.getExternalStorageDirectory();
        String fileName = mExportingFileName;
        File dbFile = new File(backupPath, fileName);
        if (!dbFile.exists()) {
            return;
        }

        SQLiteDatabase localSQLiteDatabase = null;
        try {
            localSQLiteDatabase = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, 1);
        } catch (Exception e) {
            e.printStackTrace();
            localSQLiteDatabase = null;
        }
        if (localSQLiteDatabase == null) {
            return;
        }

        Cursor cursor = localSQLiteDatabase.query(true, "data", new String[] {
                "contact_id", RawContacts.ACCOUNT_TYPE, RawContacts.ACCOUNT_NAME
        }, null, null, null, null, null, null);
        if (cursor == null || cursor.getCount() <= 0) {
            try {
                if (cursor != null)
                    cursor.close();
                localSQLiteDatabase.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        while (cursor.moveToNext()) {
            int contact_id = cursor.getInt(cursor.getColumnIndex("contact_id"));

            Cursor localCursor = localSQLiteDatabase.query("data", null, "contact_id=?", new String[] {
                String.valueOf(contact_id)
            }, null, null, null);
            try {
                ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

                ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(
                        ContactsContract.RawContacts.ACCOUNT_NAME, null).withValue(RawContacts.AGGREGATION_MODE, RawContacts.AGGREGATION_MODE_SUSPENDED).build());

                while (localCursor.moveToNext()) {
                    String mType = localCursor.getString(localCursor.getColumnIndex(ContactsContract.RawContacts.Data.MIMETYPE)).trim();
                    Builder builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0).withValue(
                            ContactsContract.RawContacts.Data.MIMETYPE, mType);

                    if (mType.equals(StructuredName.CONTENT_ITEM_TYPE)) {
                        if (localCursor.getString(localCursor.getColumnIndex(StructuredName.DISPLAY_NAME)) != null)
                            builder.withValue(StructuredName.DISPLAY_NAME, localCursor.getString(localCursor.getColumnIndex(StructuredName.DISPLAY_NAME)));
                        // Given name
                        String givenName = localCursor.getString(localCursor.getColumnIndex(StructuredName.GIVEN_NAME));
                        if (givenName != null)
                            builder.withValue(StructuredName.GIVEN_NAME, givenName);
                        String familyName = localCursor.getString(localCursor.getColumnIndex(StructuredName.FAMILY_NAME));
                        if (familyName != null)
                            builder.withValue(StructuredName.FAMILY_NAME, familyName);
                        String prefix = localCursor.getString(localCursor.getColumnIndex(StructuredName.PREFIX));
                        if (prefix != null)
                            builder.withValue(StructuredName.PREFIX, prefix);
                        String middle_name = localCursor.getString(localCursor.getColumnIndex(StructuredName.MIDDLE_NAME));
                        if (middle_name != null)
                            builder.withValue(StructuredName.MIDDLE_NAME, middle_name);
                        String suffix = localCursor.getString(localCursor.getColumnIndex(StructuredName.SUFFIX));
                        if (suffix != null)
                            builder.withValue(StructuredName.SUFFIX, suffix);
                        String ph_given_name = localCursor.getString(localCursor.getColumnIndex(StructuredName.PHONETIC_GIVEN_NAME));
                        if (ph_given_name != null)
                            builder.withValue(StructuredName.PHONETIC_GIVEN_NAME, ph_given_name);
                        String ph_middle_name = localCursor.getString(localCursor.getColumnIndex(StructuredName.PHONETIC_MIDDLE_NAME));
                        if (ph_middle_name != null)
                            builder.withValue(StructuredName.PHONETIC_MIDDLE_NAME, ph_middle_name);
                        String ph_family_name = localCursor.getString(localCursor.getColumnIndex(StructuredName.PHONETIC_FAMILY_NAME));
                        if (ph_family_name != null)
                            builder.withValue(StructuredName.PHONETIC_FAMILY_NAME, ph_family_name);

                    } else if (mType.equals(Phone.CONTENT_ITEM_TYPE)) {
                        int type = localCursor.getInt(localCursor.getColumnIndex(Phone.TYPE));
                        builder.withValue(Phone.TYPE, type);
                        String number = localCursor.getString(localCursor.getColumnIndex(Phone.NUMBER));
                        if (number != null)
                            builder.withValue(Phone.NUMBER, number);
                        String label = localCursor.getString(localCursor.getColumnIndex(Phone.LABEL));
                        if (label != null)
                            builder.withValue(Phone.LABEL, label);

                    } else if (mType.equals(Email.CONTENT_ITEM_TYPE)) {
                        String address = localCursor.getString(localCursor.getColumnIndex(Email.DATA));
                        if (address != null)
                            builder.withValue(Email.DATA, address);
                        int type = localCursor.getInt(localCursor.getColumnIndex(Email.TYPE));
                        builder.withValue(Email.TYPE, type);
                        String label = localCursor.getString(localCursor.getColumnIndex(Email.LABEL));
                        if (label != null)
                            builder.withValue(Email.LABEL, label);
                        String display_name = localCursor.getString(localCursor.getColumnIndex(Email.DISPLAY_NAME));
                        if (display_name != null)
                            builder.withValue(Email.DISPLAY_NAME, display_name);

                    } else if (mType.equals(Organization.CONTENT_ITEM_TYPE)) {
                        String company = localCursor.getString(localCursor.getColumnIndex(Organization.COMPANY));
                        if (company != null)
                            builder.withValue(Organization.COMPANY, company);
                        int type = localCursor.getInt(localCursor.getColumnIndex(Organization.TYPE));
                        builder.withValue(Organization.TYPE, type);
                        String label = localCursor.getString(localCursor.getColumnIndex(Organization.LABEL));
                        if (label != null)
                            builder.withValue(Organization.LABEL, label);
                        String title = localCursor.getString(localCursor.getColumnIndex(Organization.TITLE));
                        if (title != null)
                            builder.withValue(Organization.TITLE, title);
                        String department = localCursor.getString(localCursor.getColumnIndex(Organization.DEPARTMENT));
                        if (department != null)
                            builder.withValue(Organization.DEPARTMENT, department);
                        String job_desc = localCursor.getString(localCursor.getColumnIndex(Organization.JOB_DESCRIPTION));
                        if (job_desc != null)
                            builder.withValue(Organization.JOB_DESCRIPTION, job_desc);
                        String symbol = localCursor.getString(localCursor.getColumnIndex(Organization.SYMBOL));
                        if (symbol != null)
                            builder.withValue(Organization.SYMBOL, symbol);
                        String ph_name = localCursor.getString(localCursor.getColumnIndex(Organization.PHONETIC_NAME));
                        if (ph_name != null)
                            builder.withValue(Organization.PHONETIC_NAME, ph_name);
                        String office_location = localCursor.getString(localCursor.getColumnIndex(Organization.OFFICE_LOCATION));
                        if (office_location != null)
                            builder.withValue(Organization.OFFICE_LOCATION, office_location);

                    } else if (mType.equals(Im.CONTENT_ITEM_TYPE)) {
                        String data = localCursor.getString(localCursor.getColumnIndex(Im.DATA));
                        if (data != null)
                            builder.withValue(Im.DATA, data);
                        int type = localCursor.getInt(localCursor.getColumnIndex(Im.TYPE));
                        builder.withValue(Im.TYPE, type);
                        String label = localCursor.getString(localCursor.getColumnIndex(Im.LABEL));
                        if (label != null)
                            builder.withValue(Im.LABEL, label);
                        String protocol = localCursor.getString(localCursor.getColumnIndex(Im.PROTOCOL));
                        if (protocol != null)
                            builder.withValue(Im.PROTOCOL, protocol);
                        String custom_protocol = localCursor.getString(localCursor.getColumnIndex(Im.CUSTOM_PROTOCOL));
                        if (custom_protocol != null)
                            builder.withValue(Im.CUSTOM_PROTOCOL, custom_protocol);

                    } else if (mType.equals(Nickname.CONTENT_ITEM_TYPE)) {
                        String name = localCursor.getString(localCursor.getColumnIndex(Nickname.NAME));
                        if (name != null)
                            builder.withValue(Nickname.NAME, name);
                        int type = localCursor.getInt(localCursor.getColumnIndex(Nickname.TYPE));
                        builder.withValue(Nickname.TYPE, type);
                        String label = localCursor.getString(localCursor.getColumnIndex(Nickname.LABEL));
                        if (label != null)
                            builder.withValue(Nickname.LABEL, label);

                    } else if (mType.equals(Note.CONTENT_ITEM_TYPE)) {
                        String note = localCursor.getString(localCursor.getColumnIndex(Note.NOTE));
                        if (note != null)
                            builder.withValue(Note.NOTE, note);

                    } else if (mType.equals(StructuredPostal.CONTENT_ITEM_TYPE)) {
                        String form_address = localCursor.getString(localCursor.getColumnIndex(StructuredPostal.FORMATTED_ADDRESS));
                        if (form_address != null)
                            builder.withValue(StructuredPostal.FORMATTED_ADDRESS, form_address);
                        int type = localCursor.getInt(localCursor.getColumnIndex(StructuredPostal.TYPE));
                        builder.withValue(StructuredPostal.TYPE, type);
                        String label = localCursor.getString(localCursor.getColumnIndex(StructuredPostal.LABEL));
                        if (label != null)
                            builder.withValue(StructuredPostal.LABEL, label);
                        String street = localCursor.getString(localCursor.getColumnIndex(StructuredPostal.STREET));
                        if (street != null)
                            builder.withValue(StructuredPostal.STREET, street);
                        String pobox = localCursor.getString(localCursor.getColumnIndex(StructuredPostal.POBOX));
                        if (pobox != null)
                            builder.withValue(StructuredPostal.POBOX, pobox);
                        String neibo = localCursor.getString(localCursor.getColumnIndex(StructuredPostal.NEIGHBORHOOD));
                        if (neibo != null)
                            builder.withValue(StructuredPostal.NEIGHBORHOOD, neibo);
                        String city = localCursor.getString(localCursor.getColumnIndex(StructuredPostal.CITY));
                        if (city != null)
                            builder.withValue(StructuredPostal.CITY, city);
                        String region = localCursor.getString(localCursor.getColumnIndex(StructuredPostal.REGION));
                        if (region != null)
                            builder.withValue(StructuredPostal.REGION, region);
                        String postcode = localCursor.getString(localCursor.getColumnIndex(StructuredPostal.POSTCODE));
                        if (postcode != null)
                            builder.withValue(StructuredPostal.POSTCODE, postcode);
                        String country = localCursor.getString(localCursor.getColumnIndex(StructuredPostal.COUNTRY));
                        if (country != null)
                            builder.withValue(StructuredPostal.COUNTRY, country);

                    } else if (mType.equals(Website.CONTENT_ITEM_TYPE)) {
                        String url = localCursor.getString(localCursor.getColumnIndex(Website.URL));
                        if (url != null)
                            builder.withValue(Website.URL, url);
                        int type = localCursor.getInt(localCursor.getColumnIndex(Website.TYPE));
                        builder.withValue(Website.TYPE, type);
                        String label = localCursor.getString(localCursor.getColumnIndex(Website.LABEL));
                        if (label != null)
                            builder.withValue(Website.LABEL, label);

                    } else if (mType.equals(Event.CONTENT_ITEM_TYPE)) {
                        String start_date = localCursor.getString(localCursor.getColumnIndex(Event.START_DATE));
                        if (start_date != null)
                            builder.withValue(Event.START_DATE, start_date);
                        int type = localCursor.getInt(localCursor.getColumnIndex(Event.TYPE));
                        builder.withValue(Event.TYPE, type);
                        String label = localCursor.getString(localCursor.getColumnIndex(Event.LABEL));
                        if (label != null)
                            builder.withValue(Event.LABEL, label);

                    } else if (mType.equals(Relation.CONTENT_ITEM_TYPE)) {
                        String name = localCursor.getString(localCursor.getColumnIndex(Relation.NAME));
                        if (name != null)
                            builder.withValue(Relation.NAME, name);
                        int type = localCursor.getInt(localCursor.getColumnIndex(Relation.TYPE));
                        builder.withValue(Relation.TYPE, type);
                        String label = localCursor.getString(localCursor.getColumnIndex(Relation.LABEL));
                        if (label != null)
                            builder.withValue(Relation.LABEL, label);

                    } else if (mType.equals(GroupMembership.CONTENT_ITEM_TYPE)) {

                        long group_row_id = localCursor.getLong(localCursor.getColumnIndex(GroupMembership.GROUP_ROW_ID));
                        builder.withValue(GroupMembership.GROUP_ROW_ID, group_row_id);

                    }

                    ops.add(builder.build());
                }

                ContentProviderResult[] result = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                localCursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("a", "+++ recover" + (i++));

        }

        try {
            cursor.close();
            localSQLiteDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dbFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("a", "+++ on recover time is " + (System.currentTimeMillis() - t));

    }

    private static final String mExportingFileName = "111111118.vcf";

    
    private int backupSms() {
     h.sendMessage(h.obtainMessage(1000));
        
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return -1;
        }
        
        File sdpath = Environment.getExternalStorageDirectory();
        
        String fileName = "_otaupdate_backup_sms.csv";
        Uri localUri = Uri.parse("content://sms");
        Cursor localCursor = getContentResolver().query(localUri, null, null,
                null, null);
        if (localCursor == null || localCursor.getCount() == 0) {
            return 0;
        }

        int m;

        
        File backFile = new File(sdpath, fileName);
        
        if (backFile.exists()){
            backFile.delete();
        }
        SQLiteDatabase localSQLiteDatabase = SQLiteDatabase.openDatabase(
                backFile.getAbsolutePath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        
        String sql1 = "create table smstable (_id INTEGER primary key,thread_id INTEGER,address TEXT,person INTEGER,date INTEGER,protocol INTEGER,read INTEGER,status INTEGER,type INTEGER,reply_path_present INTEGER,subject TEXT,body TEXT,service_center TEXT,locked INTEGER,error_code INTEGER,seen INTEGER)";
        localSQLiteDatabase.execSQL(sql1);

        String sql2 = "create table threadtable (_id INTEGER primary key,date INTEGER,message_count INTEGER,recipient_ids INTEGER,snippet TEXT,snippet_cs INTEGER,read INTEGER,type INTEGER,error INTEGER,has_attachmen INTEGER)";
        localSQLiteDatabase.execSQL(sql2);
        
        int n = 0;
        m = localCursor.getCount();
        while (localCursor.moveToNext()) {
            Object[] arrayOfObject = new Object[14];
            arrayOfObject[0] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("_id")));
            arrayOfObject[1] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("thread_id")));
            arrayOfObject[2] = localCursor.getString(localCursor
                    .getColumnIndex("address"));
            arrayOfObject[3] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("person")));
            arrayOfObject[4] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("date")));
            arrayOfObject[5] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("protocol")));
            arrayOfObject[6] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("read")));
            arrayOfObject[7] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("status")));
            arrayOfObject[8] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("type")));
            arrayOfObject[9] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("reply_path_present")));
            arrayOfObject[10] = localCursor.getString(localCursor
                    .getColumnIndex("subject"));
            arrayOfObject[11] = localCursor.getString(localCursor
                    .getColumnIndex("body"));
            arrayOfObject[12] = localCursor.getString(localCursor
                    .getColumnIndex("service_center"));
            arrayOfObject[13] = Long.valueOf(localCursor.getLong(localCursor
                    .getColumnIndex("locked")));
            
            String sql3 = "insert into smstable (_id,thread_id,address,person,date,protocol,read,status,type,reply_path_present,subject,body,service_center,locked)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            localSQLiteDatabase.execSQL(sql3, arrayOfObject);
            n++;
            

            mProgressDialogForReadVCard.incrementProgressBy(1);
        }
        try {
            localSQLiteDatabase.close();
            localCursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mProgressDialogForReadVCard.dismiss();
        
        return 1;
    
    }
    private int contactsBackup() {
        // mProgressDialogForReadVCard.setMax(32);
        mProgressDialogForReadVCard.setMessage("backup contacts. " );
        
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            return -1;
        }

        String backRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();

        String fileName = mExportingFileName;
        Uri localUri = ContactsContract.Data.CONTENT_URI;
        Cursor localCursor = getContentResolver().query(localUri, null, null, null, null);
        if (localCursor == null || localCursor.getCount() == 0) {
            return 0;
        }

        File dbFile = new File(backRootPath, fileName);
        if (dbFile.exists()) {
            boolean del = dbFile.delete();
            if (!del) {
                return 0;
            }
        }

        SQLiteDatabase localSQLiteDatabase = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);

        String[] columns = localCursor.getColumnNames();
        StringBuilder sb = new StringBuilder("create table data(");
        for (int i = 0; i < columns.length; i++) {
            if (!columns[i].equals(ContactsContract.CommonDataKinds.Photo.PHOTO))
                sb.append(columns[i]).append(" TEXT");
            else
                sb.append(columns[i]).append(" BLOB");

            if (i == columns.length - 1) {
                sb.append(")");
            } else {
                sb.append(",");
            }

        }

        localSQLiteDatabase.execSQL(sb.toString());

        int s = 0;
        int c = localCursor.getCount();
        Log.d("a", "+++ local count is " + c);

        while (localCursor.moveToNext()) {
            ContentValues cv = new ContentValues();

            for (int i = 0; i < columns.length; i++) {

                if (!columns[i].equals(ContactsContract.CommonDataKinds.Photo.PHOTO) && (localCursor.getString(localCursor.getColumnIndex(columns[i])) != null)) {
                    String rv = localCursor.getString(localCursor.getColumnIndex(columns[i]));
                    if (rv != null && !columns[i].equals("_id"))
                        cv.put(columns[i], rv);
                }
            }

            try {
                localSQLiteDatabase.insertOrThrow("data", null, cv);
                Log.d("a", "++++ . " + s);
                mProgressDialogForReadVCard.incrementProgressBy(1);
                s++;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            localCursor.close();
            localCursor = localSQLiteDatabase.query(true, "data", new String[] {
                    "contact_id", RawContacts.ACCOUNT_TYPE, RawContacts.ACCOUNT_NAME
            }, null, null, null, null, null, null);
            if (localCursor == null || localCursor.getCount() <= 0) {
                s = 0;
            } else {
                s = localCursor.getCount();
            }
            localCursor.close();
            localSQLiteDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        Log.e("a", "+++ backup end. ");
        return 1;

    }

}
