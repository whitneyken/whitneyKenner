//
//  main.cpp
//  Palindromes
//
//  Created by Whitney Kenner on 8/25/22.
// Partnered with Lauryn C. Hansen

#include <iostream>

int main(int argc, const char * argv[]) {
    // declare variables
    std::string userEnteredString;
    std::string reversedString;
    
    
    std::cout << "Enter a word: \n";
    std::cin >> userEnteredString;
    
    //need to find string length
    //Now we need to reverse the string
    for ( int i = userEnteredString.length () - 1; i >= 0; i--) {
        reversedString += userEnteredString[i];
    }
    // now verify the entered original string against the reversed one
    if (reversedString == userEnteredString) {
        std::cout << userEnteredString << " is a palindrome!\n";
    } else {
        std::cout << userEnteredString << " is NOT a palindrome!\n";
    }
    
    return 0;
}
