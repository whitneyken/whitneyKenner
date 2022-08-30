//
//  main.cpp
//  VectorPractice
//
//  Created by Whitney Kenner on 8/29/22.
//

#include <iostream>
#include <vector>
#include "VectorFunctions.hpp"


int main(int argc, const char * argv[]) {
    std::vector <int> intSumVector = {9, 6, 4, 8, 3};
    int sumTotal = 0;
    
    
    
    sumTotal = Sum(intSumVector);
    
    assert(Sum(intSumVector) == 30);
    std::cout << sumTotal << std::endl;
    
    std::string stringToConvert = "Howdy-doo";
    std::vector<char> convertedVector = StringToVec(stringToConvert);
    
    //text to check my string to vector function (and print)
    
    for (int index = 0; index < convertedVector.size(); index++) {
        std::cout << convertedVector[index];
        assert(stringToConvert[index] == convertedVector[index]);
    }
    std::cout << std::endl;
    
    //reverse function that reverses a vector of ints
    std::vector<int> originalNumbers = {9, 4, 3, 6, 8, 2};
    std::vector<int> reversedNumbers = reverse(originalNumbers);
    
    for (int index = 0; index < reversedNumbers.size(); index++) {
        std::cout << reversedNumbers[index];
    }
    std::cout << std::endl;

    
    return 0;
}
