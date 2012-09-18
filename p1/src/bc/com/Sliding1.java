package bc.com;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.android.internal.widget.SlidingTab;

public class Sliding1 extends Activity implements View.OnClickListener, SlidingTab.OnTriggerListener {
    private SlidingTab mIncomingCallWidget;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.sliding1);

        mIncomingCallWidget = (SlidingTab) findViewById(R.id.incomingCallWidget);
        mIncomingCallWidget.setLeftTabResources(R.drawable.ic_jog_dial_answer, com.android.internal.R.drawable.jog_tab_target_green, com.android.internal.R.drawable.jog_tab_bar_left_answer,
                com.android.internal.R.drawable.jog_tab_left_answer);
        mIncomingCallWidget.setRightTabResources(R.drawable.ic_jog_dial_decline, com.android.internal.R.drawable.jog_tab_target_red, com.android.internal.R.drawable.jog_tab_bar_right_decline,
                com.android.internal.R.drawable.jog_tab_right_decline);

        mIncomingCallWidget.setVisibility(View.VISIBLE);

        mIncomingCallWidget.setLeftHintText(R.string.alarm1);
        mIncomingCallWidget.setRightHintText(R.string.app_name);

        mIncomingCallWidget.setOnTriggerListener(this);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onGrabbedStateChange(View arg0, int arg1) {
        // TODO Auto-generated method stub
        
    }

    private void hideIncomingCallWidget() {
        if (mIncomingCallWidget.getVisibility() != View.VISIBLE
                || mIncomingCallWidget.getAnimation() != null) {
            // Widget is already hidden or in the process of being hidden
            return;
        }
        // Hide the incoming call screen with a transition
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(250);
        anim.setAnimationListener(new AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                // hide the incoming call UI.
                mIncomingCallWidget.clearAnimation();
                mIncomingCallWidget.setVisibility(View.GONE);
            }
        });
        mIncomingCallWidget.startAnimation(anim);
    }

    @Override
    public void onTrigger(View arg0, int whichHandle) {
        // TODO Auto-generated method stub


        switch (whichHandle) {
            case SlidingTab.OnTriggerListener.LEFT_HANDLE:

                hideIncomingCallWidget();

                // ...and also prevent it from reappearing right away.
                // (This covers up a slow response from the radio; see updateState().)

                break;

            case SlidingTab.OnTriggerListener.RIGHT_HANDLE:

                hideIncomingCallWidget();

                // ...and also prevent it from reappearing right away.
                // (This covers up a slow response from the radio; see updateState().)
                
                break;

            default:
                break;
        }

        // Regardless of what action the user did, be sure to clear out
        // the hint text we were displaying while the user was dragging.
    
        
    }

}
