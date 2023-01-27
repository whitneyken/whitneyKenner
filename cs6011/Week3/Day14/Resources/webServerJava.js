"use strict";

let xValue = document.getElementById('xVal');
let yValue = document.getElementById('yVal');
let result = document.getElementById('resultTA');
let wsResult = document.getElementById('wsResultTA');
let button = document.getElementById( "button");

xValue.addEventListener("keypress", handleKeyPressCB);
yValue.addEventListener("keypress", handleKeyPressCB);
button.addEventListener("click", handleKeyPressCB);




function handleAjaxErrorCB(){
	console.log("an ajax error occurred");

}

function handleAjaxSuccessCB(){
	console.log("Got a response from the server");
	result.value = this.responseText;
}






let wsOpen = false;

function handleConnectCB( event ) {
    wsOpen = true;
}

function handleMessageFromWsCB( event ) {
    wsResultTA.value = event.data;
}


function handleKeyPressCB(event){

    if(event.keycode == 13 || event.type == "click"){
      //display the data in the text area
      let x =  parseFloat( xValue.value);
      if (isNaN(x)){
        alert("Please make sure x is a number");
        xValue.value = "<Enter a valid number>";
        xValue.select(); //highlights the text field
        event.preventDefault();
        return;
      }
      let y =  parseFloat( yValue.value);
      if (isNaN(y)){
        alert("Please make sure y is a number");
        yValue.value = "<Enter a valid number>";
        yValue.select(); //highlights the text field
        event.preventDefault();
        return;
      }
      let request = new XMLHttpRequest();
      request.open("GET", "http://localhost:8080/calculate?x=" + x + "&y=" + y); //called a query stream
      request.addEventListener("error", handleAjaxErrorCB);
      request.addEventListener("load", handleAjaxSuccessCB);
      request.send("x+y");

    // make websocket request to get calculation

            if (wsOpen) {
                ws.send(x + " " + y);
            } else {
                wsResultTA.value = "WS is not open...";
            }

      }
}

let ws = new WebSocket( "ws://localhost:8080" );
ws.onopen = handleConnectCB;
ws.onmessage = handleMessageFromWsCB;
