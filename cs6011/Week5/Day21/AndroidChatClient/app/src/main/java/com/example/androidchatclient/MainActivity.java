package com.example.androidchatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    public static  WebSocket ws_;
    private boolean isValidChatRoom = false;
    private boolean isValidUserName = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ws_ = new WebSocketFactory().createSocket("ws://10.0.2.2:8080/endpoint", 1000);
        } catch (IOException e) {
            Log.e("Dd:", "WS error");
        }
        ws_.addListener(new MyWebSocket());
        ws_.connectAsynchronously();
    }

    public void onEnterChatClick(View view) {
        EditText userName = findViewById(R.id.userNameInput);
        String stringUser = userName.getText().toString();
        EditText chatRoom = findViewById(R.id.chatRoomInput);
        String stringChat = chatRoom.getText().toString();

        for (int i = 0; i < stringChat.length(); i++) {
            if (stringChat.charAt(i) < 'a' || stringChat.charAt(i) > 'z') {
                Context context = getApplicationContext();
                CharSequence text = "Chat room must be all lower case characters";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else if (stringChat.equals("")) {
                Context context = getApplicationContext();
                CharSequence text = "Must enter a valid chat name";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                isValidChatRoom = true;
            }
            if (stringUser.equals("")) {
                Context context = getApplicationContext();
                CharSequence text = "Must enter a valid user name";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            } else {
                isValidUserName = true;

            }
            if (isValidChatRoom && isValidUserName) {

                Intent intent = new Intent(this, ChatActivity.class);
                intent.putExtra("userName", stringUser);
                intent.putExtra("chatRoom", stringChat);
                startActivity(intent);
            }
        }
    }


}