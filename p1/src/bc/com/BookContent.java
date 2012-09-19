package bc.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BookContent extends Activity {
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.book_content);

        // 设置标题
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.content_title);
        ((TextView) findViewById(R.id.content_title_text)).setText(intent
                .getStringExtra("contenName"));
        ((ImageView) findViewById(R.id.back_icon))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        finish();
                    }
                });
        setupView();

    }

    private void setupView() {
        // 设置内容
        TextView v = (TextView) findViewById(R.id.content);
        v.setText(intent.getStringExtra("content"));
    }
}
