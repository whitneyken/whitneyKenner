//
//  Functions.cpp
//  NumberConverter
//
//  Created by Whitney Kenner on 9/6/22.
//

#include "Functions.hpp"

/* this function will convert the users string into an int. Different paths will be based on the base the user input*/
int StringToInt(std::string input, int base){
    int numericValue = 0;
    if (base == 10) {
        numericValue = std::stoi(input);
    }else if (base == 16){
        int value = 0;
        int powerCounter10 = (input.size()-1);
        for (int i = 0; i < input.size(); i++) {
            char c = input[i];
            //converts upper to lower
            if (c >= 'A' && c <= 'F') {
                c = tolower(c);
            }//converts to int
            if (c >= 'a' && c <= 'f') {
                value = c - 'a' + 10;
            }else{
                value = c - '0';
            }
            //This equation calculates the int value of each piece
            numericValue += value * pow(16, powerCounter10);
            powerCounter10 --;
        }
        return numericValue;
    }else{//this is for base size 2
        int powerCounter2 = input.size()-1;
        for (int j = 0; j < input.size(); j++) {
            char c2 = input[j] - '0';
            numericValue += c2 * pow(2, powerCounter2);
            powerCounter2 --;
        }
    }
    return numericValue;
}
//This function will convert from an int to decimal string
std::string IntToDecimalString (int input){
    std::string intString = std::to_string(input);
    return intString;
}
//This function will convert from an int to binary string
std::string IntToBinaryString (int input){
    std::string backwardString;
    std::string forwardString;
    while (input > 0) {
        if ((input % 2) == 0) {
            backwardString += '0';
        }else{
            backwardString += '1';
        }
        input = (input / 2);
    }//This for loop reverses the order since it is backwards
    for (int i = backwardString.size(); i > 0; i--) {
        forwardString += backwardString[i];
    }
    return forwardString;
}
