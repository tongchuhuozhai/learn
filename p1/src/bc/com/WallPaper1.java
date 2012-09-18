package bc.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.android.internal.telephony.Phone;

import android.app.Activity;
import android.app.IWallpaperManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class WallPaper1 extends Activity {
    IWallpaperManager mService;
    public void onCreate(Bundle b) {

        super.onCreate(b);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        IBinder binder = ServiceManager.getService(Context.WALLPAPER_SERVICE);
//        mService = IWallpaperManager.Stub.asInterface(binder);
//       
//
//        Drawable drawable = getWallpaper();
//        WallpaperService w;
//        drawable.setAlpha(0XA0);
        
//        Drawable drawable1 = (Drawable)
//        Bitmap bit = getCurrentWallpaperLocked(this);
//        Drawable dra =  new BitmapDrawable(bit);  
        
//        mMainFrame.setBackgroundDrawable(dra);
        setContentView(R.layout.wallpaper1);
        
        int flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
            flags |= WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
        getWindow().addFlags(flags);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES);

        // Run in a 32-bit window, which improves the appearance of some
        // semitransparent artwork in the in-call UI (like the CallCard
        // photo borders).
//        getWindow().setFormat(PixelFormat.RGBX_8888);
//        Window win = getWindow();
////      win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
////      | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON        
////      );
//      
//      WindowManager.LayoutParams winP = win.getAttributes();
//      winP.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
//      winP.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
//      winP.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//      
//      winP.dimAmount = 0.4f;
////      winP.buttonBrightness = 
//      
//      win.setAttributes(winP);
//      View fo = getCurrentFocus();
//      if(fo != null) fo.clearFocus();
////        LayoutInflater inf = LayoutInflater.from(this);
//        
//        
//        WallpaperManager wq = (WallpaperManager)getSystemService(WALLPAPER_SERVICE);
//        setContentView(inf.inflate(R.layout.wallpaper1, null));
//        View mMainFrame = findViewById(R.id.layout1);
       
//        mMainFrame.setBackgroundDrawable(wq.getDrawable());
//
//        WallpaperManager a;
//        // a.peekDrawable()
//        setWallpaperDimension();
    }
    
    private void setWallpaperDimension()
    {
        WallpaperManager wpm = (WallpaperManager) getSystemService(WALLPAPER_SERVICE);
        Display display = getWindowManager().getDefaultDisplay();
        boolean isPortrait = display.getWidth() < display.getHeight();
        
        final int width = isPortrait ? display.getWidth() : display.getHeight();
        final int height = isPortrait ? display.getHeight() : display
                .getWidth();
        wpm.suggestDesiredDimensions(width * 2, height);
    }
    
    
    public static InputStream getDefaultWallpaperStream(Context context)
    {
        InputStream is = null;
        String DEFAULT_WALLPAPERS = "/system/media/launcher/default_wallpapers.xml";
    
        String content = "";
    BufferedReader reader = null;
    try 
    {
        reader = new BufferedReader(new FileReader(DEFAULT_WALLPAPERS));
        String line;
        while ((line = reader.readLine()) != null)
        {
            content += line;
        }
        content = content.replaceAll("(.*?)<!--(.*?)-->(.*?)", "$1$3");
        
        StringBuilder input = new StringBuilder(content);
        Pattern pattern = Pattern.compile("<default_image>(.*?)</default_image>", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        
        String path = "";
        if (matcher.find())
        {
            int start = matcher.start();
            int end = matcher.end();
            path = input.substring(start, end).replaceAll("<default_image>", "").replaceAll("</default_image>", "");
        }
        
        if(path != null && path.length() > 0)
        {
            File f = new File(path);
            if(f.exists())
            {
                is = new FileInputStream(f);
            }else
            {
                //if default_image is not exist
                is = context.getResources().openRawResource(com.android.internal.R.drawable.default_wallpaper);
            }
        }else
        {
            //if default_image is empty
            is = context.getResources().openRawResource(com.android.internal.R.drawable.default_wallpaper);
        }
    } catch (Throwable e) 
    {
        //error user system default image
        is = context.getResources().openRawResource(com.android.internal.R.drawable.default_wallpaper);
    }

    if (reader != null)
    {
        try
        {
            reader.close();
        } catch (Exception e)
        {
        }
    }
    
        return is;
    }


    String TAG = "ab";

    private Bitmap getCurrentWallpaperLocked(Context context) {
        try {
            Bundle params = new Bundle();
            ParcelFileDescriptor fd = mService.getWallpaper(null, params);
//            ParcelFileDescriptor fd = getWallpaper();
            if (fd != null) {
                int width = params.getInt("width", 0);
                int height = params.getInt("height", 0);
                
                if (width <= 0 || height <= 0) {
                    // Degenerate case: no size requested, just load
                    // bitmap as-is.
                    Bitmap bm = BitmapFactory.decodeFileDescriptor(
                            fd.getFileDescriptor(), null, null);
                    try {
                        fd.close();
                    } catch (IOException e) {
                    }
                    if (bm != null) {
                        bm.setDensity(DisplayMetrics.DENSITY_DEVICE);
                    }
                    return bm;
                }
                
                // Load the bitmap with full color depth, to preserve
                // quality for later processing.
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inDither = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bm = BitmapFactory.decodeFileDescriptor(
                        fd.getFileDescriptor(), null, options);
                try {
                    fd.close();
                } catch (IOException e) {
                }
                
                return generateBitmap(context, bm, width, height);
            }
        } catch (RemoteException e) {
        }
        return null;
    }
    private Bitmap getDefaultWallpaperLocked(Context context) {

        try {
            
            InputStream is = getDefaultWallpaperStream(context);

//            InputStream is = context.getResources().openRawResource(
//
//            com.android.internal.R.drawable.default_wallpaper);

            if (is != null) {

                int width = mService.getWidthHint();

                int height = mService.getHeightHint();

                if (width <= 0 || height <= 0) {

                    // Degenerate case: no size requested, just load

                    // bitmap as-is.

                    Bitmap bm = BitmapFactory.decodeStream(is, null, null);

                    try {

                        is.close();

                    } catch (IOException e) {

                    }

                    if (bm != null) {

                        bm.setDensity(DisplayMetrics.DENSITY_DEVICE);

                    }

                    return bm;

                }

                // Load the bitmap with full color depth, to preserve

                // quality for later processing.

                BitmapFactory.Options options = new BitmapFactory.Options();

                options.inDither = false;

                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                Bitmap bm = BitmapFactory.decodeStream(is, null, options);

                try {

                    is.close();

                } catch (IOException e) {

                }

                try {

                    return generateBitmap(context, bm, width, height);

                } catch (OutOfMemoryError e) {

                    Log.w(TAG, "Can't generate default bitmap", e);

                    return bm;

                }

            }

        } catch (RemoteException e) {

        }

        return null;

    }

    static Bitmap generateBitmap(Context context, Bitmap bm, int width, int height) {

        if (bm == null) {

            return bm;

        }

        bm.setDensity(DisplayMetrics.DENSITY_DEVICE);

        // This is the final bitmap we want to return.

        // XXX We should get the pixel depth from the system (to match the

        // physical display depth), when there is a way.

        Bitmap newbm = Bitmap.createBitmap(width, height,

        Bitmap.Config.RGB_565);

        newbm.setDensity(DisplayMetrics.DENSITY_DEVICE);

        Canvas c = new Canvas(newbm);

        c.setDensity(DisplayMetrics.DENSITY_DEVICE);

        Rect targetRect = new Rect();

        targetRect.left = targetRect.top = 0;

        targetRect.right = bm.getWidth();

        targetRect.bottom = bm.getHeight();

        int deltaw = width - targetRect.right;

        int deltah = height - targetRect.bottom;

        if (deltaw > 0 || deltah > 0) {

            // We need to scale up so it covers the entire

            // area.

            float scale = 1.0f;

            if (deltaw > deltah) {

                scale = width / (float) targetRect.right;

            } else {

                scale = height / (float) targetRect.bottom;

            }

            targetRect.right = (int) (targetRect.right * scale);

            targetRect.bottom = (int) (targetRect.bottom * scale);

            deltaw = width - targetRect.right;

            deltah = height - targetRect.bottom;

        }

        targetRect.offset(deltaw / 2, deltah / 2);

        Paint paint = new Paint();

        paint.setFilterBitmap(true);

        paint.setDither(true);

        c.drawBitmap(bm, null, targetRect, paint);

        bm.recycle();

        return newbm;

    }
}
