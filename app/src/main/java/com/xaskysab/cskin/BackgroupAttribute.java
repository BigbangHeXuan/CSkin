package com.xaskysab.cskin;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import com.xaskysab.App;



public class BackgroupAttribute extends AbAttributControl {


    public BackgroupAttribute(Resources resources, String packageName, String entryName, String typeName) {
        super(resources, packageName, entryName, typeName);
    }

    @Override
    public void apply(View view) {

        if ("color".equals(typeName)) {
            view.setBackgroundColor(resources.getColor(resourceId));

        } else if ("drawable".equals(typeName)) {
            view.setBackground(resources.getDrawable(resourceId));
        }

    }
}
