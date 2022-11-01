package com.example.androidchatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView chatServer = findViewById(R.id.chatServer);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String chatRoom = extras.getString("chatRoom");
            String userName = extras.getString("userName");
            chatServer.setText("Chat Room: " + chatRoom);
        }
    }
}