package com.xaskysab.cskin;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import com.xaskysab.App;


public abstract class AbAttributControl {

    Resources resources;
    String packageName;
    int resourceId;
    String entryName;
    String typeName;

    public AbAttributControl(Resources resources, String packageName, String entryName, String typeName) {
        this.resources = resources;
        this.packageName = packageName;
        this.entryName = entryName;
        this.typeName = typeName;

        this.resourceId = resources.getIdentifier(entryName, typeName, packageName);
    }


    public abstract void apply(View view);

}
