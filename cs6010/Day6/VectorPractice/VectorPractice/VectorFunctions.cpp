//
//  VectorFunctions.cpp
//  VectorPractice
//
//  Created by Whitney Kenner on 8/29/22.
//

#include "VectorFunctions.hpp"
#include <string>

//this calculates the sum of my vector
int Sum(std::vector<int> sumVector){
    int sumTotal = 0;
    for (int index = 0; index < sumVector.size(); index++) {
        sumTotal += sumVector[index];
    }
    return sumTotal;
}
//This converts my string into a vector
std::vector<char> StringToVec(std::string inputString){
    std::vector<char> newVector;
    for (int index = 0; index < inputString.length(); index++) {
        newVector.push_back(inputString[index]);
    }
    return newVector;
}
//This reverses the order of a vector of ints
std::vector<int> reverse(std::vector<int> numbers){
    std::vector<int> reversedVector;
    for (int index = (numbers.size() - 1); index >= 0; index--) {
        reversedVector.push_back(numbers[index]);
    }
    return reversedVector;
}
