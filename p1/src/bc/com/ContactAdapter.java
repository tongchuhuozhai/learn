package bc.com;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {
	private ArrayList<String> arraylist;
	private boolean[] selectedArray;
	private Context cxt;

	private Handler handler;
	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View position) {
			handler.sendMessage(handler.obtainMessage(
					ContactActivity.CHECK_CHANGEED, position));
		}
	};

	public ContactAdapter(Context c, ArrayList<String> persons, boolean[] s,
			Handler handler) {
		this.cxt = c;
		arraylist = persons;
		selectedArray = s;
		this.handler = handler;
	}

	@Override
	public int getCount() {
		return arraylist.size();
	}

	@Override
	public Object getItem(int position) {
		return arraylist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(cxt).inflate(
					R.layout.a_contact_item_check_adapter, null);
			holder.title = (TextView) convertView.findViewById(R.id.context_text);
			holder.desc = (CheckBox) convertView
					.findViewById(R.id.contact_check);
			holder.desc.setOnClickListener(listener);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (selectedArray != null) {
			holder.desc.setChecked(selectedArray[position]);
		}

		if (arraylist != null) {
			holder.title.setText(arraylist.get(position));
		}

		return convertView;
	}

	static class ViewHolder {
		TextView title;
		CheckBox desc;
	}
}
