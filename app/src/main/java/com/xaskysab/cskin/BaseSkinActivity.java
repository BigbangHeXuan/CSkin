package com.xaskysab.cskin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;



public class BaseSkinActivity extends Activity {

    private SkinFactory skinFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skinFactory = new SkinFactory(ApkSkin.path);
        LayoutInflaterCompat.setFactory(getLayoutInflater(), skinFactory);

    }


    public void update() {

        skinFactory.update();
    }
}
