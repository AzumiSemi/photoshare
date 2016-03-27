package com.github.shawn.cameran;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(this);

        Button gallery = (Button) findViewById(R.id.gallery);
        gallery.setOnClickListener(this);

        Button share = (Button) findViewById(R.id.share);
        share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "push", Toast.LENGTH_SHORT).show();
        if (v.getId() == R.id.camera) {
        } else if (v.getId() == R.id.gallery) {
        } else {
        }
    }
}