package bc.com;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ThreadTest extends Activity {

    String LOG_TAG = "p1";
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_thread_test);
        textView = (TextView) findViewById(R.id.thread_text);
        button = (Button) findViewById(R.id.thread_button);
        String a = "critical";

        Runnable task = new Thread1(a);
        final Thread t1 = new Thread(task);
        t1.start();

        Runnable task2 = new Thread2(a);
        Thread t2 = new Thread(task2);
        t2.start();

        Thread thisThread = Thread.currentThread();

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                t1.interrupt();
            }
        });
    }

    class Thread1 implements Runnable {
        String n;

        public Thread1(String s) {
            n = s;
        }

        @Override
        public void run() {
            while (true) {
                Log.d(LOG_TAG, "thread 1 start.");
                synchronized (n) {
                    try {
                        Log.d(LOG_TAG, "thread 1 waitting lock... ");
                        n.wait();
                        Log.d(LOG_TAG, "thread 1 get lock.  ");
                    } catch (Exception e) {
                        Log.d(LOG_TAG, "thread 1 interrupt.  ");
                        e.printStackTrace();
                    }
                }

                Log.d(LOG_TAG, "thread 1 finished one time ");
            }
        }
    }

    class Thread2 extends Thread {
        String n;

        public Thread2(String s) {
            n = s;
        }

        @Override
        public void run() {
            Log.d(LOG_TAG, "thread2 run ... ");
            SystemClock.sleep(10000);

            synchronized (n) {
                n.notifyAll();
                Log.d(LOG_TAG, "thread 2 release lock. ");
            }
        }
    }
}