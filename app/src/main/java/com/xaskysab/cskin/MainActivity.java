package com.xaskysab.cskin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends BaseSkinActivity {

    Button button;

    ProgressBar probar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        probar = (ProgressBar) findViewById(R.id.probar);

        button.setOnClickListener(v -> {
            probar.setVisibility(View.VISIBLE);
            update();
            probar.setVisibility(View.GONE);
        });


    }

}
