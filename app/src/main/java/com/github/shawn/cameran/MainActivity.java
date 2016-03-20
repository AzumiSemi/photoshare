package com.github.shawn.cameran;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View.OnClickListener mOnCheckListneer = new View.OnClickListener(){
        @Override
                public void onClick(View v) {

            Toast.makeText(MainActivity.this, "押されました", Toast.LENGTH_SHORT).show();
                Log.d("Trunk", "    ");
                Log.d("Trunk", "");

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.camera);
            Button camera = (Button) findViewById(R.id.camera);
            camera.setOnClickListener(mOnCheckListneer);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        camera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            Toast.makeText(MainActivity.this, "押されました", Toast.LENGTH_SHORT).show();
    }
}