package com.xaskysab.cskin;

import android.view.View;

import java.util.List;



public class SkinSwap {
    View view;
    AbAttributControl ac;

    public SkinSwap(View view, AbAttributControl attributControl) {
        this.view = view;
        this.ac = attributControl;
    }

    public void apply() {

        ac.apply(view);


    }


}



