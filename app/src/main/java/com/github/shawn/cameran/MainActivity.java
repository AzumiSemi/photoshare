package com.github.shawn.cameran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String hoge = "hoge";
        Log.d("Trunk", "onCreate");
        hoge = "hoga";


    }
}