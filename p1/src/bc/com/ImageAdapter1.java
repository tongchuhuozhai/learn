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

public class ImageAdapter1 extends BaseAdapter {
	int mGalleryItemBackground;
	private Context mContext;
	private Integer[] mImageIds;
	View[] adaptItemView;

	public ImageAdapter1(Context c, Integer[] mImageIds) {
		this.mImageIds = mImageIds;
		mContext = c;
		
		adaptItemView = new View[getCount()];
		
		for(int i=0; i< getCount(); i++){
			adaptItemView[i] = LayoutInflater.from(mContext).inflate(
					R.layout.gallery_image_item_adapter, null);
			ImageView itemImage = (ImageView) adaptItemView[i].findViewById(R.id.img1);
			itemImage.setBackgroundResource(mImageIds[i]);
		}


	}

	public int getCount() {
		return mImageIds.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// convertView = LayoutInflater.from(mContext).inflate(
		// R.layout.gallery_image_item_adapter, null);
		//
		// ImageView image1 = (ImageView) convertView.findViewById(R.id.img1);
		// image1.setBackgroundResource(mImageIds[0][position]);
		//
		// ImageView image2 = (ImageView) convertView.findViewById(R.id.img2);
		// image2.setBackgroundResource(mImageIds[1][position]);

		return adaptItemView[position];
	}

}
