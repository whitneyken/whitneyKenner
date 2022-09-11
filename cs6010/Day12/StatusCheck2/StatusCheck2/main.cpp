//
//  main.cpp
//  StatusCheck2
//
//  Created by Whitney Kenner on 9/7/22.
//

#include <iostream>
#include <vector>
#include <string>
#include <fstream>

//    Part 4
//    Write a short program that will count the number of vowels in a text.  Follow these steps to implement your program:
//
//    Write a function that takes in a character and returns whether that character is a vowel.
bool IsVowel (char c){
    if (c > 'A' && c < 'Z') {
        c = islower(c);
    }
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        return true;
        //Technically the return true and return false are unnecessary
    }
    return false;
}
//    Write a function that takes in a word and returns the number of vowels in that word.
//    When declaring this function, make sure that the word parameter is not copied.
    int NumVowels (const std::string& input){
        int vowelCounter = 0;
        for (int i = 0; i < input.length(); i++) {
            if (IsVowel(input[i])) {
                vowelCounter++;
            }
        }
        return vowelCounter;
    }

int main(int argc, const char * argv[]) {
    // Status check part 1
    
    
    
    //Status check part 2
//    Part 2
//    Assuming you have the following files in your directory, show how you would compile them (on the command line) to create myProg:
//
//    my_lib.h, my_lib.cpp
//    main.cpp
    
    /* On the command line we would run:
     $ clang++ -c main.cpp
     $ clang++ -c my_lib.cpp
     $ clang++ -o MyProg main.o my_lib.o
     */
    
//    part 3
//    Short answers:
//
//    a) What is the difference between:
//
//    An array and a structure?
    
    /* an array is made up of references to specific memory locations whereas structures are not. structures can also be composed of multiple variable times and methods can be used that are not associated with arrays.*/
    
//    An array and a vector?
    
    /* an array has a fixed size whereas a vector size can change during run time. similarly to above, there are methods that can be used with vectors but not with arrays AND arrays contain references to specific memory locations whereas vectors do not*/
    
//    b) Create a structure that will hold information about a dog, including its name, age, and whether the dog is vaccinated.  Create a variable of this type and give it initial values.
    
     struct Dog{
        std::string name;
        int age;
        bool isVaccinated;
     };
     
    Dog poodle = {"Max", 4, true};
     //or:
    Dog labrador;
    labrador.name = "Sam";
    labrador.age = 7;
    labrador.isVaccinated = false;
     
     
//
//    c) Create a (single) variable to hold information about multiple dogs. (You do not need to initialize it.)
//
    std::vector<Dog> dogs;
     
    
//part 4:
    //    Open and read in data from the file star_wars.txt - store the data as a list of words.
    //    Count the total number of vowels in the list of words and print that information.
    std::ifstream myFile ( "star_wars.txt" );
    
    std::vector<std::string> allTheWords;
    std::string singleWord;
    
    while (myFile >> singleWord) {
        allTheWords.push_back(singleWord);
    }
    

    return 0;
}
