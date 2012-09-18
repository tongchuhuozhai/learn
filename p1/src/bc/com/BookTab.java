package bc.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class BookTab extends TabActivity {

	private ListView listView;
	private TabHost tabHost;

	private ListData listData;
	private TextView textView;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intent = getIntent();

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
  
		// 获取全局tab
		tabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.book_tab,
				tabHost.getTabContentView(), true);

		// 标题
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.content_title);
		((TextView) findViewById(R.id.content_title_text)).setText(intent
				.getStringExtra("title"));
		ImageView back = (ImageView) findViewById(R.id.back_icon);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		// 添加标签内容
		addTab(intent.getStringExtra("tab1"), R.id.tab1_list);
		addTab(intent.getStringExtra("tab2"), R.id.tab2_list);
		addTab(intent.getStringExtra("tab3"), R.id.tab3_list);
		addTab(intent.getStringExtra("tab4"), R.id.tab4_list);
		addTab(intent.getStringExtra("tab5"), R.id.tab5_list);

		// 第三个标签
//		String data = "观";
//		setData(R.id.tab3_text, data);
//		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("心经")
//				.setContent(R.id.tab3_text));
	}

	private void addTab(String fileName, int contentId) {
		XMLParserAPN parser = getParser(fileName);
		putAdaptDataToAdapter(contentId, getAdaptData(parser));
		tabHost.addTab(tabHost.newTabSpec("aa").setIndicator(
				parser.getDataByTag("root").get("name")).setContent(contentId));

	}

	private XMLParserAPN getParser(String fileName) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		final XMLParserAPN parser = new XMLParserAPN(inputStream);
		return parser;
	}

	private void setData(int contentId, String data) {
		textView = (TextView) findViewById(contentId);
		textView.setText(data);
	}

	private ListData getAdaptData(final XMLParserAPN parser) {
		int tagCount = parser.getTagCount("chapter");
		final String[] strings = new String[tagCount];
		for (int i = 0; i < tagCount; i++) {
			strings[i] = parser.getDataByTag1("chapter", i).get("name");
		}

		// 组织列表数据
		ListData listData = new ListData();
		listData.name = strings;
		listData.listen = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> convert, View arg1,
					int index, long arg3) {
				String content = parser.getValueByTag1("chapter", index);
				Intent i = new Intent(BookTab.this, BookContent.class);
				i.putExtra("contenName", strings[index]);
				i.putExtra("content", content);
				startActivityForResult(i, 0);
			}
		};

		return listData;
	}

	private void putAdaptDataToAdapter(int contentId, ListData listData) {
		listView = (ListView) findViewById(contentId);

		List<String> adapterData = new ArrayList<String>();
		// 将成员变量缓冲到本地, 有利于提高速度
		String[] s1 = listData.name;
		for (int i = 0; i < s1.length; i++) {
			adapterData.add(s1[i]);
		}
		ListAdapter1 adapter = new ListAdapter1(this, adapterData);

		listView.setOnItemClickListener(listData.listen);
		listView.setAdapter(adapter);
	}


	class ListData {
		String[] name;
		OnItemClickListener listen;
	}

}
