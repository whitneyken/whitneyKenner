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


let gameOver = false;

//maybe function to create enemies and update enemies in animate
  let enemies = [];


function main(){
  //setting up our enemy

  generateEnemies(enemies);


  window.requestAnimationFrame (animate);

}


window.onload = main;

let goingRight = true;



function animate(){
  erase();
  ctx.drawImage(squidImage, squidImage.xPos - 62, squidImage.yPos - 50, 85, 115);
  for (let enemy of enemies) {
    ctx.drawImage(enemy.img, enemy.xPos, enemy.yPos, 75, 100);
}
//now we need to make the enemies chase the squid
moveEnemies(enemies);

  checkCollision(enemies)
   if(!gameOver){
     window.requestAnimationFrame (animate);
   }
}
function moveEnemies(enemies){

  for(let enemy of enemies){
    if(squidImage.xPos > enemy.xPos){
      enemy.xPos += enemy.inherantSpeed;
    }else if (squidImage.xPos < enemy.xPos) {
      enemy.xPos -= enemy.inherantSpeed;
    }
    if (squidImage.yPos > enemy.yPos) {
      enemy.yPos += enemy.inherantSpeed;

    }else if (squidImage.yPos < enemy.yPos) {
      enemy.yPos -= enemy.inherantSpeed;
    }
  }



}
//This creates 10 enemies and randomly picks their location on the screen
function generateEnemies(enemies){
  for (let i = 0; i < 15; i++) {
    let enemy = {};
    enemy.img = new Image();
    enemy.img.src = "enemy.png";
    enemy.xPos = Math.floor(Math.random() * cHeight);
    enemy.yPos = Math.floor(Math.random() * cWidth);
    enemy.inherantSpeed = Math.random() * 12;
    enemies.push (enemy);


  }
      console.log(enemies);
}

function checkCollision(enemies){
  for(let enemy of enemies){
    let yDistance = squidImage.yPos - enemy.yPos;
    let xDistance = squidImage.xPos - enemy.xPos;
    if ((yDistance < 10 && yDistance > -10) && (xDistance < 10 && xDistance > -10)) {
    gameOver = true;
    }
  }
}

function erase(){

  let background = document.createElement("img");
  background.src = "okie.jpg";
  let pat = ctx.createPattern(background, "no-repeat");

  ctx.rect(0, 0, cWidth, cHeight);
  ctx.fillStyle = pat;
  ctx.fill();

}



function trackMouse(e){

  squidImage.xPos = e.x;
  squidImage.yPos = e.y;
}
document.onmousemove = trackMouse;
