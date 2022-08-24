//
//  main.cpp
//  ForLoopPractice
//
//  Created by Whitney Kenner on 8/24/22.
//

#include <iostream>
#include <math.h>

int main(int argc, const char * argv[]) {
    // starting by printing the numbers 1-10 using a for loop

    for (int forCounter = 1; forCounter <= 10; forCounter++) {
        std::cout << forCounter << "\n";
    }
    //next we print the numbers but in a while loop
    int whileCounter = 1;
    while (whileCounter <= 10) {
        std::cout << whileCounter << "\n";
        whileCounter++;
    }
    //I think the for loop was more appropriate because it is more succinct
    //prompt the user to enter 2 numbers and then type out all numbers between them
    int firstEnteredNumber;
    int secondEnteredNumber;
    do {
        std::cout <<"Enter a number: ";
        std::cin >> firstEnteredNumber;
        std::cout <<"\n Enter a second (higher) number: ";
        std::cin >> secondEnteredNumber;
    } while (firstEnteredNumber > secondEnteredNumber);
    while (firstEnteredNumber <= secondEnteredNumber) {
        std::cout <<firstEnteredNumber << std::endl;
        firstEnteredNumber++;
    
    }
    
     /*Now print all the odd numbers between 1 and 20. come up with 2 solutions, that uses a loop and an if statement, and 1 that doesn't require an if statement. which is better?*/
    
        for (int oddNumberCounter = 1; oddNumberCounter <= 20; oddNumberCounter += 2) {
            std::cout << oddNumberCounter << "\n";
        }
    int whileOddNumberCounter = 1;
    while (whileOddNumberCounter <= 20) {
        if (whileOddNumberCounter % 2 != 0) {
            std::cout<< whileOddNumberCounter << std::endl;
        }
        whileOddNumberCounter++;
    }
    //The for loop is MUCH more simple in my opinion, the layered loop/if statement is dumb
    //Ask a user to enter positive numbers to add up. Keep reading and adding numbers until the user enters a number that is less than 0, then print the sum. For example, if the user entered 1 2 3 -1, you should print 6. This one is a little tricky!
    
    int newNumberEntered = 0;
    int newNumberPlusOldNumbers = 0;
    while (newNumberEntered >= 0) {
        std::cout <<"\n Enter a number: ";
        std::cin>> newNumberEntered;
        if (newNumberEntered >= 0) {
            newNumberPlusOldNumbers = newNumberPlusOldNumbers + newNumberEntered;
        }
    }
    std::cout<<"\n The total addition of your numbers is: " << newNumberPlusOldNumbers << std::endl;
    
    //making a multiplication table
    for (int y = 1; y <= 5; y++) {
        for (int x = 1; x <= 5; x++){
            std::cout << x * y << " ";
        
        }
        std::cout<< std::endl;
    }
        

}
