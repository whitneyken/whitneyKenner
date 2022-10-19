"use strict";

//setting up the canvas and context
let canvas = document.getElementById("canvas");
let ctx = canvas.getContext('2d');
let cWidth = canvas.width;
let cHeight = canvas.height;


//Setting up our squid character
let squidImage = new Image();
squidImage.src = "happySquid.PNG";
squidImage.xPos = 0;
squidImage.xPos = 50;




//maybe function to create enemies and update enemies in animate
  let enemies = [];


function main(){
  //setting up our enemy

  generateEnemies(enemies);
  //ctx.drawImage(squidImage, 20, 20, 85, 115);
  // for (let enemy of enemies) {
  //   ctx.drawImage(enemy.img, enemy.xPos, enemy.yPos);
  // }

  window.requestAnimationFrame (animate);

}


window.onload = main;

let goingRight = true;



// function animate(){
//
//
// }

function animate(){
  erase();
  ctx.drawImage(squidImage, squidImage.xPos - 62, squidImage.yPos - 50, 85, 115);
for (let enemy of enemies) {
  ctx.drawImage(enemy.img, enemy.xPos, enemy.yPos, 75, 100);
}
//now we need to make the enemies chase the squid
moveEnemies(enemies);



//   ctx.drawImage(squidImage, squidImage.xPos - 30, squidImage.yPos - 20, 100, 150);
//   if (goingRight) {
//     squidImage.xPos += 5;
//   }else {
//     squidImage.xPos -= 5;
//   }
//
//   if (squidImage.xPos <= (0)) {
//     goingRight = true;
//   }else if squidImage.(xPos >= (cWidth - 75)) {
//     goingRight = false;
//   }
   window.requestAnimationFrame (animate);
//
}
function moveEnemies(enemies){

  for(let enemy of enemies){
    if(squidImage.xPos > enemy.xPos){
      enemy.xPos += Math.random() * 10;
    }else if (squidImage.xPos < enemy.xPos) {
      enemy.xPos -= Math.random() * 10;
    }
    if (squidImage.yPos > enemy.yPos) {
      enemy.yPos += Math.random() * 10;
    }else if (squidImage.yPos < enemy.yPos) {
      enemy.yPos -= Math.random() * 10;
    }
  }
// for(let enemy of enemies){
//   if (squidImage.yPos > enemy.yPos) {
//     enemy.yPos += Math.random() * 10;
//   }else if (squidImage.yPos < enemy.yPos) {
//     enemy.yPos -= Math.random() * 10;
//   }
// }



}
//This creates 10 enemies and randomly picks their location on the screen
function generateEnemies(enemies){
  for (let i = 0; i < 10; i++) {
    let enemy = {};
    enemy.img = new Image();
    enemy.img.src = "enemy.png";
    enemy.xPos = Math.floor(Math.random() * cHeight);
    enemy.yPos = Math.floor(Math.random() * cWidth);
    enemy.id = i;
    enemies.push (enemy);


  }
      console.log(enemies);
}


function erase(){
  // ctx.fillStyle = pat;
  // ctx.fillRect(0, 0, cWidth, cHeight);
  // ctx.fill();
  //
  let background = document.createElement("img");
  background.src = "okie.jpg";
  let pat = ctx.createPattern(background, "no-repeat");

  ctx.rect(0, 0, cWidth, cHeight);
  ctx.fillStyle = pat;
  ctx.fill();

}


function trackMouse(e){
  // erase();
  // ctx.drawImage(squidImage, e.x - 62, e.y - 50, 85, 115);
  squidImage.xPos = e.x;
  squidImage.yPos = y.x;
}
document.onmousemove = trackMouse;
