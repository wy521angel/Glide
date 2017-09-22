package com.example.wy521angel.glidetest;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class PermissionUtil {

    /**
     * 跳转到某个页面时检查权限
     *
     * @param activity
     * @param checkedMinVersion
     * @param permissions
     */
    public static void chekPermissions(Activity activity, int checkedMinVersion, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return;
        }
        if (Build.VERSION.SDK_INT >= checkedMinVersion) {
            int checkPermissionLocation;
            ArrayList<String> checkedPermissions = new ArrayList<>();

            for (String permission : permissions) {
                checkPermissionLocation = ContextCompat.checkSelfPermission(activity, permission);
                if (checkPermissionLocation != PackageManager.PERMISSION_GRANTED) {
                    checkedPermissions.add(permission);
                }
            }
            if (checkedPermissions.size() > 0) {
                ActivityCompat.requestPermissions(activity, checkedPermissions.toArray(new String[checkedPermissions.size()]), 0);
            }
        }

    }

    public static boolean isPermissions(Activity activity, int checkedMinVersion, String permission){
        if(permission == null){
            return false;
        }
        if(Build.VERSION.SDK_INT >= checkedMinVersion){
            int checkPermissionLocation;
            checkPermissionLocation = ContextCompat.checkSelfPermission(activity, permission);
            if(checkPermissionLocation != PackageManager.PERMISSION_GRANTED){
                return  false;
            }
        }
        return true;
    }

}
