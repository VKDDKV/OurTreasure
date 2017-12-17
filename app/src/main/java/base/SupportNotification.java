package base;

import android.annotation.TargetApi;
import android.app.Notification;
import android.os.Build;

/**
 * Created by nguyentviet3513 on 12/17/2017.
 */

public class SupportNotification {

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public final static Notification getNotification(Notification.Builder builder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return builder.build();
        } else {
            return builder.getNotification();
        }
    }
}
