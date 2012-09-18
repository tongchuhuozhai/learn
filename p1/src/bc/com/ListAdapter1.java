package bc.com;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter1 extends BaseAdapter {

	List<String> data;
	private Context ctx;
	View[] v1;

	public ListAdapter1(Context ctx1, List<String> s1) {
		data = s1;
		ctx = ctx1;

		int count = data.size();
		v1 = new View[count];
		for (int i = 0; i < count; i++) {
			v1[i] = LayoutInflater.from(ctx).inflate(
					R.layout.list_item_adapter, null);
			// 向视图中添加数据
			((TextView) v1[i].findViewById(R.id.adapte_text)).setText(data
					.get(i));
		}
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
		return v1[index];
	}

}
