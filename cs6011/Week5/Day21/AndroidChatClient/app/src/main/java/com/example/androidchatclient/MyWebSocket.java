package com.example.androidchatclient;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class MyWebSocket extends WebSocketAdapter {


    @Override
    public void onTextMessage(WebSocket websocket, String text) throws Exception {
        super.onTextMessage(websocket, text);
        JSONObject theMessage = new JSONObject(text);
        Log.d("WK.ws", "onTextMessage: recieved the message: " + theMessage);
        String typeOfMessage = (String) theMessage.get("type");
        Log.d("WK.ws", "onTextMessage: The type os message is: " + typeOfMessage);
        String stringToPost = "";
        String user = (String) theMessage.get("user");
        if (typeOfMessage.equals("join")) {
            stringToPost = user + " has joined the chat";
        }else if(typeOfMessage.equals("leave")){
            stringToPost = user + " has left the chat";
        }else{
            String recievedMessage = (String) theMessage.get("message");
            stringToPost = user + ": " + recievedMessage;
        }
        ChatActivity.allMessages.add(stringToPost);
        ChatActivity.listView_.post(() -> ChatActivity.adapter_.notifyDataSetChanged());
        ChatActivity.listView_.smoothScrollToPosition(ChatActivity.adapter_.getCount() );

    }

    @Override
    public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
        super.onConnectError(websocket, exception);
        Log.d("WK.ws", "onConnectError: " + exception);
        //Make an alert dialogue??

    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        super.onConnected(websocket, headers);
        Log.d("WK.ws", "onConnected: " + websocket.isOpen());
    }
}
