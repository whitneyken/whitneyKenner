package com.example.androidchatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    public static ArrayList<String> allMessages = new ArrayList<>();
    public static ListView listView_;
    public static ArrayAdapter adapter_;
    String userName;
    String chatRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView chatServer = findViewById(R.id.chatServer);

        Bundle extras = getIntent().getExtras();


        if (extras != null){
            chatRoom = extras.getString("chatRoom");
            userName = extras.getString("userName");
            chatServer.setText("Chat Room: " + chatRoom);
            MainActivity.ws_.sendText("join " + userName + " " + chatRoom);
        }
         listView_ = findViewById(R.id.listView);
        adapter_ = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allMessages);
        listView_.setAdapter(adapter_);

    }


    public void sendTextOnClick(View view){
        TextInputEditText inputText = findViewById(R.id.textInput);
        if (!inputText.getText().equals("")) {
            MainActivity.ws_.sendText(userName + " " + inputText.getText());
            inputText.setText("");
        }

    }
    public void LeaveOnClick(View view){
        //MainActivity.ws_.sendText("leave " + userName + " " + chatRoom);
        MainActivity.ws_.sendClose();
    }
}