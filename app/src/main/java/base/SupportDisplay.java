package base;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.MotionEvent;

/**
 * Created by nguyentviet3513 on 12/17/2017.
 */

public class SupportDisplay {
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public final static void getSize(Display display, Point outSize) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(outSize);
        } else {
            outSize.x = display.getWidth();
            outSize.y = display.getHeight();
        }
    }
}
