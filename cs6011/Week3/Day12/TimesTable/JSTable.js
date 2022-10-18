"use strict";





function main(){
  let timesTable = document.createElement("TABLE");
  let tBody = document.createElement("tbody");
  let selected = null;

for(let i = 0; i < 10; i++){
  let row = document.createElement("tr");
  for(let j = 0; j < 10; j++){
    let cell = document.createElement("td");
    let cellText = document.createTextNode((i+1) * (j + 1));
    cell.style.background = "white";
    cell.appendChild(cellText);
    cell.id = String(i) + String (j);
    cell.addEventListener("mouseenter", handleMouseOver);
    cell.addEventListener("mouseleave", handleMouseOut);
    cell.addEventListener("click", handleClick);

    cell.style.padding = "15px";
    cell.style.textAlign = "center";

    row.appendChild(cell);
  }
  tBody.appendChild(row);
}
timesTable.appendChild(tBody);

function handleClick(event){
  event.target.style.background = "palevioletred";
  if (selected != null){

        let previous = document.getElementById(selected);
        previous.style.background = "white";
        previous.style.fontWeight = "normal";

        // if (event.target.id == selected) {
        //   selected.style.backgroundColor = "white";
        //   selected.style.fontWeight = "normal";
        // }
  }
  selected = event.target.id;
}

document.body.appendChild(timesTable);
window.setInterval(sunsetColors, 1000);



let button = document.createElement("BUTTON");
let text = document.createTextNode("Click here to toggle background color");
button.appendChild(text);
document.body.appendChild(button);
button.addEventListener("click", toggleTime);

}


function handleMouseOut(event){
  if (event.target.style.background == "pink") {
    event.target.style.background = "white";
    event.target.style.fontWeight = "normal";
 }

}

function handleMouseOver(event) {
  if (event.target.style.background == "white") {
    event.target.style.background = "Pink";
    event.target.style.fontWeight = "bold";
  }
}

let colors = ['cornsilk', 'lightgoldenrodyellow', 'moccasin', 'peachpuff', 'pink', 'sandybrown', 'salmon'];
let colorIndex = 0;

function sunsetColors() {
  if (toggle) {

  if (colorIndex == colors.length - 1) {
    colorIndex = 0;
  }else {
    colorIndex += 1;
  }
document.body.style.background = colors[colorIndex];
}
}

let toggle = false;

function toggleTime(){
  if (toggle) {
    toggle =false;
  }else {
    toggle = true;
  }
}

window.onload = main;
