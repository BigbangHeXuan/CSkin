package com.xaskysab.cskin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TextAttribute extends AbAttributControl {


    public TextAttribute(Resources resources, String packageName, String entryName, String typeName) {
        super(resources, packageName, entryName, typeName);
    }

    @Override
    public void apply(View view) {

        if ("color".equals(typeName)) {

            try {

                Method method = view.getClass().getMethod("setTextColor", int.class);

                method.invoke(view, resources.getColor(resourceId));

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }

    }
}
