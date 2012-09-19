package bc.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Contacts.People;
import android.provider.Contacts.Phones;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends ListActivity {
    protected MyLogger logger = MyLogger.getLogger();

    private static final int CONTACT_SEARCH = 1000;
    public final static int CHECK_CHANGEED = CONTACT_SEARCH + 1;

    private final String SELECTED_FLAG_ARRAY = "checked_a_array";
    private final String PERSONS_ARRAY = "persons_array";
    private final String SEARCH_FINISH = "search_finish";

    // private final Uri CONTACT_URI = Uri.parse("content://contacts/combined");
    private final Uri CONTACT_PHONE_URI = Uri
            .parse("content://contacts/people"); // People.CONTENT_URI;
    private final Uri CONTACT_SIM_URI = Uri.parse("content://icc/adn"); // sim
    private final Uri CONTACT_SIM_URIA = Phones.CONTENT_URI;
    private ArrayList<String> personsArrayList = new ArrayList<String>();
    private ListView personsListView;
    private Button cancelButton;
    private Button confirmButton;
    private View emptyView;

    private boolean contactFlag = false;

    private boolean[] selectedArray;
    private HashMap<String, Integer> phoneMap = new HashMap<String, Integer>();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        // 标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.p_contact);
        ((TextView) findViewById(R.id.right_text_title))
                .setText(R.string.contact_title);
        ((ImageView) findViewById(R.id.back_icon))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        setupView();
        if (bundle == null || bundle.getBoolean(SEARCH_FINISH) == false) {
            logger.i("--------ContactActivity first enter------");
            // 初次进入时, 发送查询通讯录请求, 200毫秒使界面完全画出来, 避免对话框遗留在本界面
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doSearchContacts();
                    logger.i("----------------searching finished ------------");
                    selectedArray = new boolean[personsArrayList.size()];
                    handler.sendEmptyMessageDelayed(CONTACT_SEARCH, 50);
                    contactFlag = true;
                }
            }).start();
        } else {
            logger.i("--------ContactActivity HW switch------");
            // 获取切换前保存数据
            personsArrayList = bundle.getStringArrayList(PERSONS_ARRAY);
            selectedArray = bundle.getBooleanArray(SELECTED_FLAG_ARRAY);

            setupListContent(selectedArray, personsArrayList);
            contactFlag = true;
        }
    }

    private void setupView() {
        emptyView = LayoutInflater.from(this).inflate(R.layout.contact_wait,
                null);
        addContentView(emptyView, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));
        personsListView = getListView();
        personsListView.setEmptyView(emptyView);
        confirmButton = (Button) findViewById(R.id.content_backpage);
        confirmButton.setText(R.string.dialogConfirm);
        confirmButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取选中联系人
                if (personsArrayList != null && contactFlag) {
                    StringBuffer selectedPersons = new StringBuffer();
                    int count = personsArrayList.size();
                    if (count > 0) {
                        for (int i = 0; i < count; i++) {
                            if (selectedArray[i] == true) {
                                if (selectedPersons.length() == 0) {
                                    selectedPersons
                                            .append((String) personsArrayList
                                                    .get(i));
                                } else {
                                    selectedPersons
                                            .append(',' + (String) personsArrayList
                                                    .get(i));
                                }
                            }
                        }
                        if (selectedPersons.length() == 0) {
                            Toast.makeText(ContactActivity.this,
                                    R.string.select_contacts, 3000).show();
                        } else {
                            // 向调用页返回数据
                            Intent intent = getIntent();
                            intent.putExtra("selected Persons",
                                    selectedPersons.toString());
                            logger.i("selectedPersons is: "
                                    + selectedPersons.toString());
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(ContactActivity.this,
                                R.string.no_contact, 3000).show();
                    }
                }
            }
        });

        cancelButton = (Button) findViewById(R.id.content_nextpage);
        cancelButton.setText(R.string.dialogCanncel);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void searchContacts(Uri mContacts) {
        ContentResolver resolver = getContentResolver();
        String columns[] = new String[] { People._ID, People.NAME,
                People.NUMBER };
        Cursor cur = resolver
                .query(mContacts, columns, null, null, People.NAME);
        if (cur.moveToFirst()) {
            do {
                String name = cur.getString(cur.getColumnIndex(People.NAME));
                String temp = cur.getString(cur.getColumnIndex(People.NUMBER));
                if (temp != null && name != null) {
                    String phoneNo = temp;
                    if (temp.contains("-")) {
                        phoneNo = temp.replaceAll("-", "");
                    }
                    if (checkMobilePhone(phoneNo)) {
                        // 截取手机号(即去掉+86)
                        Pattern pattern = Pattern
                                .compile("1(3[4-9]|5[012789]|8[78])\\d{8}$");
                        Matcher matcher = pattern.matcher(phoneNo);
                        StringBuffer phoneNoMached = new StringBuffer();
                        while (matcher.find()) {
                            phoneNoMached.append(matcher.group());
                        }
                        String phoneNoMachedString = phoneNoMached.toString();
                        if (!phoneMap.containsKey(phoneNoMachedString)) {
                            if (name != null && !"".equals(name)) {
                                personsArrayList.add(phoneNoMachedString
                                        .concat("[").concat(name).concat("]"));

                            } else {
                                personsArrayList.add(phoneNoMachedString);
                            }
                            phoneMap.put(phoneNoMachedString, 1);
                        }
                    }
                }
            } while (cur.moveToNext());
        }
        if (cur != null) {
            cur.close();
        }
        return;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        logger.d("enter onListItemClick ");
        if (v != null) {
            CheckBox cb = (CheckBox) (v.findViewById(R.id.contact_check));
            if (cb.isChecked()) {
                cb.setChecked(false);
            } else {
                cb.setChecked(true);
            }
        }
    }

    private void doSearchContacts() {
        TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (!(telManager.getSimCountryIso() == null || "".equals(telManager
                .getSimCountryIso().trim()))) {
            searchContacts(CONTACT_PHONE_URI);
            searchContacts(CONTACT_SIM_URI);
            searchContacts(CONTACT_SIM_URIA);
        }
    }

    private void setupListContent(boolean[] selectedArray,
            ArrayList<String> persons) {

        if (personsArrayList.size() == 0) {
            emptyView.findViewById(R.id.process_bar).setVisibility(View.GONE);
            emptyView.findViewById(R.id.no_content_tip).setVisibility(
                    View.VISIBLE);
        } else {
            if (persons != null && selectedArray != null) {
                setListAdapter(new ContactAdapter(ContactActivity.this,
                        persons, selectedArray, handler));
            } else {
                logger.e("----no content in persons, but size is not 0-----");
            }

        }
    }

    // 判断是否为移动手机号
    private static boolean checkMobilePhone(String phone) {
        /*
         * 匹配移动手机号 "^1(3[4-9]|5[012789]|8[78])\d{8}$"以代码为准
         * 
         * 匹配电信手机号 "^18[09]\d{8}$"
         * 
         * 匹配联通手机号"^1(3[0-2]|5[56]|8[56])\d{8}$"
         * 
         * 匹配CDMA手机号 "^1[35]3\d{8}$"
         */
        Pattern pattern = Pattern
                .compile("^(\\+86)?1(3[4-9]|5[012789]|8[78])\\d{8}$");
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case CONTACT_SEARCH:
                // 首次进入时, 用异步线程查询通讯录, 以防页面被阻塞
                setupListContent(selectedArray, personsArrayList);
                break;
            case CHECK_CHANGEED:
                // 保存选中项和选中状态
                View view = (View) msg.obj;
                selectedArray[personsListView
                        .getPositionForView((View) msg.obj)] = ((CheckBox) view
                        .findViewById(R.id.contact_check)).isChecked();
                break;
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 横竖屏切换时, 保存联系人和选中状态
        outState.putStringArrayList(PERSONS_ARRAY, personsArrayList);
        outState.putBooleanArray(SELECTED_FLAG_ARRAY, selectedArray);
        outState.putBoolean(SEARCH_FINISH, contactFlag);
    }
}
