package com.xaskysab.splugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.annotation.CheckResult;
import android.util.Log;

import com.xaskysab.cskin.ApkSkin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class RecourcesUtil {

    static Map<String, Resources> resourcesMap = new HashMap<>();

    public static AssetManager getAssetManager(String apkPath) throws Exception {

        AssetManager assetManager = AssetManager.class.newInstance();

        Method method = AssetManager.class.getMethod("addAssetPath", String.class);

        method.invoke(assetManager, apkPath);

        return assetManager;

    }

    @CheckResult
    public static Resources getResources(Context context, String apkPath) {
        try {
            AssetManager assetManager = getAssetManager(apkPath);
            Resources resources = context.getResources();
            return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static int getPluginResourceId(Context context, String apkPath, int oldResourceId) {

        Resources resources = context.getResources();
        String colorName = resources.getResourceName(oldResourceId);
        String entryName = resources.getResourceEntryName(oldResourceId);
        String typeName = resources.getResourceTypeName(oldResourceId);

        Log.e("MyLog", colorName + "," + entryName + "," + typeName);

        Resources pluginResources = resourcesMap.get(apkPath);
        if (pluginResources == null) {

            pluginResources = RecourcesUtil.getResources(context, apkPath);

            resourcesMap.put(apkPath, pluginResources);

        }

        String packageName = SPluginUtil.getPackageInfo(context, apkPath).packageName;
        int colorId = pluginResources.getIdentifier(entryName, typeName, packageName);
        Log.e("MyLog", colorId + "");


        return colorId;

    }


}
