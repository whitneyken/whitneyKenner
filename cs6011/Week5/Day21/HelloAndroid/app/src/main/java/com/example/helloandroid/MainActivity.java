package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean firstClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleClick(View view) {
        Log.i("Wk:MainActivity", "button was pressed");
        if (firstClick) {
            TextView roomName = findViewById(R.id.helloWorldTxt);
            roomName.setText("Enter a room name");
            firstClick = false;
        }else{
            //switch to 2nd activity
            Intent intent = new Intent(this, AnotherActivity.class);
            startActivity(intent);
        }

    }
}