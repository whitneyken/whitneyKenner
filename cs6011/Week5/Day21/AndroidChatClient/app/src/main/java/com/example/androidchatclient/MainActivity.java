package com.example.androidchatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onEnterChatClick(View view) {
        EditText userName = (EditText) findViewById(R.id.userNameInput);
        String stringUser = userName.getText().toString();
        EditText chatRoom = (EditText) findViewById(R.id.chatRoomInput);
        String stringChat = chatRoom.getText().toString();
        for (int i = 0; i < stringChat.length(); i++) {
            if (stringChat.charAt(i) < 'a' || stringChat.charAt(i) > 'z') {
                Context context = getApplicationContext();
                CharSequence text = "Chat room must be all lower case characters";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        if (stringUser.equals("")) {
            Context context = getApplicationContext();
            CharSequence text = "Must enter a valid user name";
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

            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("userName", stringUser);
            intent.putExtra("chatRoom", stringChat);
            startActivity(intent);
        }
    }
}