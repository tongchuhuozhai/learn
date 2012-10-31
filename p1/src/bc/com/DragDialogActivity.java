package bc.com;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

public class DragDialogActivity extends Activity implements View.OnTouchListener{
    int mTrackingPosition;
    Dialog mExpandedDialog;
    Display mDisplay;
    WindowManager.LayoutParams mExpandedParams;
    DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_diallog_activity);
       
        
        WindowManager.LayoutParams lp;
        int pixelFormat;
        
        mExpandedDialog = new ExpandedDialog(this);
        mDisplay = ((WindowManager)getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        /// ---------- Expanded View --------------
        pixelFormat = PixelFormat.RGB_332; //TRANSLUCENT;
        mDisplay.getMetrics(mDisplayMetrics);
        lp = mExpandedDialog.getWindow().getAttributes();
        lp.x = 0;
        mTrackingPosition = lp.y = mDisplayMetrics.heightPixels; // sufficiently large negative
        lp.flags = 0
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_DITHER
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.format = pixelFormat;
        lp.gravity = Gravity.TOP | Gravity.FILL_HORIZONTAL;
        lp.y= 0;
        mExpandedParams = lp;
        mExpandedParams.flags &= ~WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mExpandedParams.flags |= WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;

        View v = LayoutInflater.from(this).inflate(R.layout.drag_dialog, null);
        Button b1 = (Button)v.findViewById(R.id.test);
        b1.setOnTouchListener(this);
        mExpandedDialog.setContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        
        mExpandedDialog.getWindow().setAttributes(mExpandedParams);
        mExpandedDialog.show();
    }
    

    private class ExpandedDialog extends Dialog {
        ExpandedDialog(Context context) {
            super(context, android.R.style.Theme_Translucent_NoTitleBar);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            int y1 = (int)event.getRawY();
            int h = mDisplay.getHeight();
            int delta = h - y1;
            mExpandedParams.y = -delta;
            mExpandedDialog.getWindow().setAttributes(mExpandedParams);
        }
        return false;
    }
}