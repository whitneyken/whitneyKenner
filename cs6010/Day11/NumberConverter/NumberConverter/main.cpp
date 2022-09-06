//
//  main.cpp
//  NumberConverter
//
//  Created by Whitney Kenner on 9/6/22.
// Collaborated with Felix Ye
//

#include <iostream>
#include "Functions.hpp"

int main(int argc, const char * argv[]) {
    // Declare variables
    std::string stringOfDigits;
    int digitBase;
    int numericValue;
    
    
    //recieve the string of digits and the base from the user
    std::cout << "Please enter your string of digits: ";
    std::cin >> stringOfDigits;
    
    std::cout << "Please enter the base that number uses: ";
    std::cin >> digitBase;
    
    /* This if statment will exit the program if the user enters an invalid base*/
    if (digitBase != 2 && digitBase != 10 && digitBase != 16) {
        std::cout << "You have entered an invalid base \n";
        return 1;
    }
    //This is the function that calculates the int
    numericValue = StringToInt(stringOfDigits, digitBase);
    
    std::cout << "The number you entered as an integer is: " << numericValue << std::endl;
    
    //part 2
    int enteredNum;
    std::string decimalString;
    std::string binaryString;
    
    std::cout << "Please enter an integer ";
    std::cin >> enteredNum;
    
    decimalString = IntToDecimalString(enteredNum);
    
    std::cout << "\n Your entered number as a string is: " << decimalString << std::endl;
    
    binaryString = IntToBinaryString(enteredNum);
    
    std::cout << "\n Your entered number as a binary string is: " << binaryString << std::endl;
    

    
    
    return 0;
}
