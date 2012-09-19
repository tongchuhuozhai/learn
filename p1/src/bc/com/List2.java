package bc.com;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class List2 extends BaseActivity {
    ListView lv;

    Listennerm d;
    private OnItemClickListener itemListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> convert, View arg1, int index,
                long arg3) {
            ListView s = (ListView) convert;
            d.sendMessage(d.obtainMessage(2001));

        }
    };

    private Listener1 listener = new Listener1() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 2001:
                Intent i = new Intent(List2.this, dd.class);
                startActivityForResult(i, 0);
                break;
            default:
                break;
            }
        }
    };;;

    @Override
    public void onCreate(Bundle v) {
        super.onCreate(v);
        super.init(R.layout.list2);

        setupView();

        // 获取消息处理器
        d = ApplicationEntry.getListennerm();
        d.setListener(listener);
    }

    private void setupView() {
        List<String> adapterData = new ArrayList<String>();
        adapterData.add("abcd");
        adapterData.add("abcd");
        adapterData.add("abcd");
        ListAdapter1 adapter = new ListAdapter1(this, adapterData);

        lv = (ListView) findViewById(R.id.list2);
        lv.setOnItemClickListener(itemListener);
        lv.setAdapter(adapter);
    }
}
