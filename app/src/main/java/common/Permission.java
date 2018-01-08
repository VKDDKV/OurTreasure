package common;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by nguyentviet3513 on 12/23/2017.
 */

public class Permission {
    private boolean checkPermission(String permission, Context applicationContext) {
        int permissionCheck =
                ContextCompat.checkSelfPermission(
                        applicationContext, permission);
        return (permissionCheck ==
                PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission(String permissionName, int permissionRequestCode, Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{permissionName}, permissionRequestCode);
    }
}
