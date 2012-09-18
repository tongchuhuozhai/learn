package bc.com;

import java.io.File;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MD5 extends Activity implements OnClickListener {
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.md5);

        findViewById(R.id.md5_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        // File big = new File("E:/s100/N.ES1010101.V2.1.rar");
        // File big = new File( "/mnt/sdcard/temp.txt");
        File big = new File("/mnt/sdcard/M.ES1010101.V1.0.zip");

        if (!big.exists()) {
            Log.d("a", "+++++ not exist");
            return;
        }
        Log.d("a", "+++++ a");
        String md5 = MD5Util.md5sum("/mnt/sdcard/M.ES1010101.V1.0.zip");
        Log.d("a", "+++++ a " + md5);
    }

//    void f() {
//        Runtime ex = Runtime.getRuntime();
//        String cmdBecomeSu = "su";
//        String script = "busybox chmod a+rw /dev/pmem";
//
//        try {
//            java.lang.Process runsum = ex.exec(cmdBecomeSu);
//            int exitVal = 0;
//
//            final OutputStreamWriter out = new OutputStreamWriter(runsum.getOutputStream());
//            // Write the script to be executed
//            out.write(script);
//            // Ensure that the last character is an "enter"
//            out.write("\n");
//            out.flush();
//            // Terminate the "su" process
//            out.write("exit\n");
//            out.flush();
//
//            exitVal = runsum.waitFor();
//            if (exitVal == 0) {
//                Log.e("Debug", "Successfully to su");
//            }
//        } catch (Exception e) {
//            Log.e("Debug", "Fails to su");
//        }
//    }
}
