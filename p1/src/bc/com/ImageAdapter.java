package bc.com;

import java.util.Arrays;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	int mGalleryItemBackground;
	private Context mContext;
	private Integer[][] mImageIds;

	public ImageAdapter(Context c, Integer[][] mImageIds) {
		this.mImageIds = mImageIds;
		mContext = c;
	}

	public int getCount() {
		return mImageIds[0].length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.image_adapter, null);

		ImageView image1 = (ImageView) convertView.findViewById(R.id.img1);
		image1.setBackgroundResource(mImageIds[0][position]);

		ImageView image2 = (ImageView) convertView.findViewById(R.id.img2);
		image2.setBackgroundResource(mImageIds[1][position]);

		return convertView;
	}

}
