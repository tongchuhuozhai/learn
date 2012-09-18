package bc.com;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GalleryAdapter1 extends BaseAdapter {

	List<String> data;
	private Context ctx;

	public GalleryAdapter1(Context ctx1, List<String> s1) {
		this.data = s1;
		this.ctx = ctx1;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		data.get(index);
		return null;
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup vg) {
		view = LayoutInflater.from(ctx).inflate(R.layout.g3_adpter, null);

		// 向视图中添加数据
		TextView textView = (TextView) view.findViewById(R.id.a_text);
		textView.setText(data.get(index));
		
		// 由xml文件展开的图片作为背景
		view.setBackgroundResource(R.drawable.gallery_item_background);
		return view;
	}

}
