package com.xaskysab.splugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.Map;



public class SPluginUtil {


    static Map<String, PackageInfo> packageInfoMap = new HashMap<>();


    public static PackageInfo getPackageInfo(Context context, String apkPath) {

        PackageManager packageManager = context.getPackageManager();

        PackageInfo packageInfo = packageInfoMap.get(apkPath);
        if (packageInfo == null) {

            packageInfo = packageManager.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);

            packageInfoMap.put(apkPath, packageInfo);

        }


        return packageInfo;

    }


}
