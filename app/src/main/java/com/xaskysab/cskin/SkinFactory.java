package com.xaskysab.cskin;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.View;

import com.xaskysab.splugin.RecourcesUtil;
import com.xaskysab.splugin.SPluginUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class SkinFactory implements LayoutInflaterFactory {

    String apkPath;

    List<SkinSwap> skinSwaps = new ArrayList<>();

    List<String> prefixList = Arrays.asList("android.widget.", "android.view.", "android.webkit.");


    public SkinFactory(String apkPath) {
        this.apkPath = apkPath;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        View view = null;
        if (!name.contains(".")) {
            for (String prefix : prefixList) {
                view = createView(prefix + name, context, attrs);
                if (view != null) {
                    changeSkin(context, view, attrs);
                    break;
                }
            }
        } else {
            view = createView(name, context, attrs);
            changeSkin(context, view, attrs);
        }

        return view;
    }

    private void changeSkin(Context context, View view, AttributeSet attrs) {

        Resources resources = RecourcesUtil.getResources(context, apkPath);
        String packName = SPluginUtil.getPackageInfo(context, apkPath).packageName;

        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attributeName = attrs.getAttributeName(i);


            AbAttributControl ac = null;

            if ("background".equals(attributeName)) {
                String attributeValue = attrs.getAttributeValue(i);
                int oldResourceId = Integer.valueOf(attributeValue.substring(1));
                String entryName = context.getResources().getResourceEntryName(oldResourceId);
                String typeName = context.getResources().getResourceTypeName(oldResourceId);

                ac = new BackgroupAttribute(resources, packName, entryName, typeName);


            } else if ("textColor".equals(attributeName)) {
                String attributeValue = attrs.getAttributeValue(i);
                int oldResourceId = Integer.valueOf(attributeValue.substring(1));
                String entryName = context.getResources().getResourceEntryName(oldResourceId);
                String typeName = context.getResources().getResourceTypeName(oldResourceId);
                ac = new TextAttribute(resources, packName, entryName, typeName);

            }

            if (ac != null) {

                skinSwaps.add(new SkinSwap(view, ac));
            }
        }






    }

    private View createView(String name, Context context, AttributeSet attrs) {

        View view = null;

        try {
            Class<? extends View> viewCls = (Class<? extends View>) Class.forName(name);
            Constructor<? extends View> constructor = viewCls.getConstructor(Context.class, AttributeSet.class);
            constructor.setAccessible(true);
            view = constructor.newInstance(context, attrs);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void update(){
        for (SkinSwap skinSwap : skinSwaps) {
            skinSwap.apply();
        }

    }
}
