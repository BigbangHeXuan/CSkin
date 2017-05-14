package com.xaskysab;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.xaskysab.cskin.ApkSkin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        try {
            InputStream inputStream = getAssets().open("skin01-debug.apk");
            FileOutputStream fileOutputStream = new FileOutputStream(ApkSkin.path);

            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

            int len = -1;
            byte[] bytes = new byte[1024];
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }

            bos.close();
            bis.close();
            fileOutputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
