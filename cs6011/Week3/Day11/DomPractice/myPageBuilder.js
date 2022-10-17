"use strict";
	





	let myH = document.createElement( 'h');
	let myHeaderText = document.createTextNode('Hello, this is my first header file');
	myH.appendChild(myHeaderText);
	myH.style.fontSize = "xx-large";
	myH.style.color = "green";
	myH.style.border = "thick solid maroon";
	let myP = document.createElement( 'p' );
	let myText = document.createTextNode( 'this is my first paragraph, welcome to whitney learns html, today we will learn the basic principles of html');
	myP.appendChild(myText);
	let myP2 = document.createElement( 'p' );
	let myText2 = document.createTextNode( 'if you would like to learn more about html, please view the following resource page: ');
	myP2.appendChild(myText2);

	let myA = document.createElement( 'a' );
	let myLinkText = document.createTextNode('Resources');
	myA.appendChild(myLinkText);
	myA.href = "https://www.w3schools.com/html/html_intro.asp";


	let myImage = document.createElement( 'img');
	myImage.src = "https://www.udacity.com/blog/wp-content/uploads/2020/06/HTML_Blog-scaled.jpeg.webp";
	myImage.style.height = "150px";
	

	let myP3 = document.createElement ('p');
	let myText3 = document.createTextNode('the following is an unsorted list of some of the things we will be learning: ');
	myP3.appendChild(myText3);
	let unList = document.createElement( 'ul');
	let item1 = document.createElement('li');
	let textItem1 = document.createTextNode('Common tags');
	let item2 = document.createElement('li');
	let textItem2 = document.createTextNode('Attributes');
	let item3 = document.createElement('li');
	let textItem3 = document.createTextNode('CSS');



	item1.appendChild(textItem1);
	item2.appendChild(textItem2);
	item3.appendChild(textItem3);
	unList.appendChild(item1);
	unList.appendChild(item2);
	unList.appendChild(item3);
	
	document.body.appendChild(myH);
	document.body.appendChild(myP);
	document.body.appendChild(myP2);
	document.body.appendChild(myA);
	document.body.appendChild(myImage);
	document.body.appendChild(myP3);
	document.body.appendChild(unList);
	
	document.body.style.backgroundColor = "beige";
