package bc.com;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import bc.com.MainActivity.ActivityEntity;

public class MainListAdapter extends BaseAdapter {
	Context c;
	ArrayList<ActivityEntity> listE;

	public MainListAdapter(Context c, ArrayList<ActivityEntity> listE) {
		this.c = c;
		this.listE = listE;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listE.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder vh = new ViewHolder();
		// 获取视图
		if (convertView == null) {
			convertView = LayoutInflater.from(c).inflate(R.layout.list_a, null);
			vh.tv = (TextView) convertView.findViewById(R.id.list_id_id2);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}

		// 设置数据
		vh.tv.setText(listE.get(position).data);
		return convertView;
	}

	class ViewHolder {
		TextView tv;
	}
}
