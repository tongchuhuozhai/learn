package bc.com;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class BrowsePic extends Activity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.browse);
        
        //获得Gallery对象  
        Gallery g = (Gallery) findViewById(R.id.gallerya);  
  
        //添加ImageAdapter给Gallery对象 注意哦Gallery类并没有setAdapter这个方法 这个方法是从AbsSpinner类继承的  
        g.setAdapter(new ImageAdapter(this));  
  
        //设置Gallery的背景图片  
//        g.setBackgroundResource(R.drawable.l2);  
  
        //设置Gallery的事件监听  
        g.setOnItemClickListener(new GalleryItemListener());  
    }
    
    class GalleryItemListener implements OnItemClickListener {  
        public void onItemClick(AdapterView<?> parent, View view, int position,  
                long id) {  
//            Toast.makeText(BrowsePic.this, "你选择了" + (position + 1) + " 号图片",  
//                    Toast.LENGTH_SHORT).show();  
            
        }  
    }  

    public class ImageAdapter extends BaseAdapter {

        // 定义Context
        private Context mContext;
        // 定义整型数组 即图片源
        private Integer[] mImageIds = {
                R.drawable.baiziming,
                R.drawable.wansheng,
                R.drawable.dabeixin,
                R.drawable.dabeione,
                R.drawable.yaoshi,
                R.drawable.dabeitwo,
                R.drawable.muxin,
                R.drawable.wuzi,
                R.drawable.dabeithress,
                R.drawable.dabeizhou,
                
                R.drawable.aa1,
                R.drawable.aa2,
                R.drawable.aa3,
                R.drawable.aa4,
                R.drawable.aa5,
                R.drawable.aa6,
                R.drawable.aa7,
                R.drawable.aa8,
                R.drawable.aa9,
                R.drawable.aa10,
                
                R.drawable.amitabha_heart,
                R.drawable.bhaisajya,
                R.drawable.cunde_a,
                
                R.drawable.jian_yi_qie_e_que,
                R.drawable.liu_zi_1,
                R.drawable.liu_zi_2,
                R.drawable.liu_zi_3,
                R.drawable.liu_zi_da_ming,
                R.drawable.ru_yi_bao_lun_wang,
                R.drawable.wang_shen,
                
                R.drawable.xiao_zai_ji_xiang,
                R.drawable.cunde , R.drawable.l9,R.drawable.l8,R.drawable.l7,R.drawable.l6,R.drawable.l5,R.drawable.l4,
                R.drawable.l3, R.drawable.l2,R.drawable.lyz1 
             };

        // 声明ImageAdapter
        public ImageAdapter(Context c) {
            mContext = c;
        }

        // 获取图片的个数
        public int getCount() {
            return mImageIds.length;
        }

        // 获取图片在库中的位置
        public Object getItem(int position) {
            return position;
        }

        // 获取图片ID
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(mContext);

            // 给ImageView设置资源
            imageview.setImageResource(mImageIds[position]);
            // 设置布局 图片120*120
            imageview.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.FILL_PARENT, Gallery.LayoutParams.FILL_PARENT));
            // 设置显示比例类型
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageview;
        }

    }

}
