"use strict";
//getting the elements we need from the html
let username = document.getElementById("name");
let chatroom = document.getElementById("chatroom");
let input = document.getElementById("input");
let sendButton = document.getElementById("send");
let establishChatBtn = document.getElementById("submit");
let display1 = document.getElementById("sq1");
let display2 = document.getElementById("sq2");
let leaveBtn = document.getElementById("leaveBtn");

//Web socket variables
let ws = new WebSocket("ws://localhost:8080");
ws.onopen = handleConnectCB;
ws.onmessage = handleMessageFromWsCB;
let wsOpen = false;


//some event listeners for the various fields
establishChatBtn.addEventListener("click", establishChatCB);
sendButton.addEventListener("click", handleSendButtonCB);
input.addEventListener("keypress", handleSendButtonCB);
leaveBtn.onclick = handleLeaveCB;


console.log(username.value);
console.log(chatroom.value);

function handleLeaveCB(event) {
  ws.send("leave " + username.value + " " + chatroom.value);
  ws.close();
}

function handleSendButtonCB(event) {
    if (event.key === 'Enter' || event.type === 'click') {
        event.preventDefault();
        if (wsOpen && chatroom != null && username != null) {
            ws.send(username.value + " " + input.value);
            console.log(input.value);
            console.log("Sent input from text box")
            input.value = "";
        } else {
            console.log("WS not open...");
        }
    }
}

function establishChatCB() {
    let name = username.value;
    //Verifies a username was entered
    if (name.length === 0) {
        alert("A valid username must be entered");
        username.value = "<Try again>";
        username.select();
    }
    let room = chatroom.value;
    //Verifies the room is all lowercase
    for (let i = 0; i < room.length; i++) {
        if (room.charAt(i) < 'a' || room.charAt(i) > 'z') {
            alert("Chat room must be only lower case");
            chatroom.value = "<Try again>";
            chatroom.select();
        }
    }
    //Verifies something was entered
    if (room.length === 0) {
        alert("A valid chatroom must be entered");
        chatroom.value = "<Try again>";
        chatroom.select();
    }
    if (wsOpen && chatroom != null && username != null) {
        ws.send("join " + name + " " + room);
        console.log("WS request sent");
    } else {
        console.log("WS not open...");
    }
}

function handleConnectCB() {
  console.log("Something is working!");
    wsOpen = true;
}

function handleMessageFromWsCB(event) {
    console.log(event);
    let response = JSON.parse(event.data);
    if (response.type === "join") {
        let joinText = document.createElement('p');
        let nameText = document.createElement('p');
        joinText.textContent = response.timeStamp + "- " + response.user + " has joined the chat";
        nameText.textContent = response.user;
        nameText.setAttribute("id", response.user);
        display1.appendChild(nameText);
        display2.appendChild(joinText);

    } else if (response.type === "leave") {
        let leaveText = document.createElement('p');
        leaveText.textContent = response.timeStamp + "- " + response.user + " has left the chat";
        display2.appendChild(leaveText);
        let leaveUser = document.getElementById(response.user);
        display1.removeChild(leaveUser);
    } else {
        let chatMessage = document.createElement('p');
        chatMessage.textContent = response.timeStamp + "- " + response.user + ": " + response.message ;
        display2.append(chatMessage);
    }
    display2.scrollTop = display2.scrollHeight;
}

window.onbeforeunload = handleLeaveCB;
