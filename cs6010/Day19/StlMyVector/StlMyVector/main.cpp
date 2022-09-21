//
//  main.cpp
//  StlMyVector
//
//  Created by Whitney Kenner on 9/20/22.
//

#include "VectorFunctions.hpp"
#include <iostream>

int main(int argc, const char * argv[]) {
    
    RunTests();
    
    MyVector<float> testvec1(5);
    testvec1.PushBack(4.0);
    testvec1.PushBack(9.2);
    testvec1.PushBack(12.5);
    testvec1.PushBack(1.1);
    testvec1.PushBack(1.4);
    
    //testing for each
    for (auto& el : testvec1) {
        std::cout << el << " ";
    }
    std::cout << std::endl;
    
    //testing std::sort
    std::sort(testvec1.begin(), testvec1.end());
    
    for (auto& el : testvec1) {
        std::cout  << el << " ";
    }
    std::cout << std::endl;
    //testing std::min element
    //Have to dereference so it isn't just the address
    float minElem = *std::min_element(testvec1.begin(), testvec1.end());
    
    std::cout << "The smallest element is: " << minElem;
    std::cout << std::endl;
    
    //testing std::accumulate
    
    float vec1Sum = std::accumulate(testvec1.begin(), testvec1.end(), 0.0);
    std::cout << "The sum of the vector elements is: " << vec1Sum << std::endl;
    
  //testing std::count if
    MyVector<int> testvec2(5);
    testvec2.PushBack(4);
    testvec2.PushBack(9);
    testvec2.PushBack(12);
    testvec2.PushBack(1);
    testvec2.PushBack(2);
    
    size_t numEvens = std::count_if(testvec2.begin(), testvec2.end(), [](int num) {return num % 2 == 0;});
    
    std::cout << "The number of even numbers in this vector of ints is: " << numEvens << std::endl;
    
    testvec2.Set(1, 6);
    
    int numEvens2 = std::count_if(testvec2.begin(), testvec2.end(), isEven);

    std::cout << "The new number of even numbers in this vector is: " << numEvens2 <<std::endl;
    
    //This is for the challenge question of remove if
    
    std::remove_if(testvec2.begin(), testvec2.end(), [](int num) {return num % 2 == 0;});
    
    for (auto& el : testvec2) {
        std::cout << el << " ";
    }
}
