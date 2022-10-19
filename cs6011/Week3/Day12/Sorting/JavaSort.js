"use strict";




//we are creating a javascript sorting program

function main(){
  //This is a selection of arrays for testing
  let intArray = [3, 5, 7, 8, 1, 12];
  let stringArray = ['squids', 'french', 'square', 'Endless', 'Friends', 'endless'];
  let floatArray = [8.0, 7.77, 3.2, 0.5, 9.82, 2.99];
  let friend1 = {fname: "Felix", lname: "Ye"};
  let friend2 = {fname: "Whitney", lname: "Kenner"};
  let friend3 = {fname: "Colin", lname: "Chen"};
  let friend4 = {fname: "Nathan", lname: "Chen"};
  let friend5 = {fname: "Ellie", lname: "Fuller"};

  let friendArray = [friend1, friend2, friend3, friend4, friend5];


  //printing the pre and post sorted arrays
  console.log("data: ", friendArray);
  selectionSort(friendArray, comparePeople);
  console.log("sorted data: ", friendArray);


}

//This method sorts basic arrays
function selectionSort(array, compare){
  for(let i = 0; i < array.length; i++){
    //we call find min location to find the index of the smallest value to
    //replaces out index i with the next smallest
    let s = findMinLocation(array, i, compare);
    let temp = array[i];
    array[i] = array[s];
    array[s] = temp;
    }
}

//this finds the smallest number in an array and returns it
function findMinLocation(array, iteration, compare){
  let smallestIndex = iteration;
  for(let i = iteration + 1; i < array.length; i ++){
    if(compare(array[i], array[smallestIndex])){
      smallestIndex = i;

    }
  }
  return smallestIndex;
}

function compareTo(a, b){
  return(a <= b);

}

function comparePeople(person1, person2){
  if (person1.lname == person2.lname) {
    return (person1.fname < person2.fname);

  }
  return(person1.lname < person2.lname);
}



window.onload = main;
