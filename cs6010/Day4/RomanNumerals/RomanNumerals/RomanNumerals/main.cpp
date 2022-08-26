//
//  main.cpp
//  RomanNumerals
//
//  Created by Whitney Kenner on 8/25/22.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    // declare variables
    int userInputNumber;
    
    // get a number input from the user to convert to roman numerals
    std::cout << "Enter a positive number: \n";
    std::cin >> userInputNumber;
    
    //now we divide the number and generate the roman numeral version
    //first I will create if statements that will lead to while loops
    if (userInputNumber <= 0) {
        std::cout << "Invalid input \n";
    } else {
        if (userInputNumber >= 1000) {
            while (userInputNumber >= 1000) {
                std::cout << "M";
                userInputNumber -= 1000;
            }
        }
        if (userInputNumber >= 900){
            std::cout << "CM";
            userInputNumber -= 900;
        }
        if (userInputNumber >= 500){
            std::cout << "D";
            userInputNumber -= 500;
        }
        if (userInputNumber >= 400) {
            std::cout << "CD";
            userInputNumber -= 400;
        }
        if (userInputNumber >= 100) {
            while (userInputNumber >= 100) {
                std::cout << "C";
                userInputNumber -= 100;
            }
        }
        if (userInputNumber >= 90) {
            std::cout << "XC";
            userInputNumber -= 90;
        }
        if (userInputNumber >= 50) {
            std::cout << "L";
            userInputNumber -= 50;
        }
        if (userInputNumber >= 40) {
            std::cout << "XL";
            userInputNumber -= 40;
        }
        if (userInputNumber >= 10) {
            while (userInputNumber >= 10) {
                std::cout << "X";
                userInputNumber -= 10;
            }
        }
        if (userInputNumber >= 9) {
            std::cout << "IX";
            userInputNumber -= 9;
        }
        if (userInputNumber >= 5) {
            std::cout << "V";
            userInputNumber -= 5;
        }
        if (userInputNumber >= 4) {
            std::cout << "IV";
            userInputNumber -= 4;
        }
        if (userInputNumber >= 1) {
            while (userInputNumber >= 1) {
                std::cout << "I";
                userInputNumber -= 1;
            }
        }
    }
    std::cout << std::endl;
    

    
    return 0;
}
