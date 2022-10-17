"use strict";
	let squids;
	//squids = {};
	squids = { "description" : "this is a very scary squad of squids"};
	let thingsWhitneyLikes;
	thingsWhitneyLikes = ["mushrooms", 6, true, 7.777, squids];
	thingsWhitneyLikes.push("Circles");
	console.log(thingsWhitneyLikes);
	
	function f(a, b){
		return a+b;
	}
	console.log(f(3, 4));

	let additionFunction = function(a, b){
		return a + b;
	}
	console.log(additionFunction(2, 3));

	console.log(additionFunction(3.33, 4.44));
	console.log(additionFunction("Squirrel", "socks"));
	console.log(additionFunction(4, 5.89));
	console.log(additionFunction(4.99, "sandals"));
	console.log(additionFunction(7, "sad"));

//Test your functions using ints, floating point numbers, strings, and a mix of these. What do you observe?
