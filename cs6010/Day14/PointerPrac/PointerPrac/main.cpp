//
//  main.cpp
//  PointerPrac
//
//  Created by Whitney Kenner on 9/12/22.
//

#include <iostream>
#include "PointerPrac.hpp"

int main(int argc, const char * argv[]) {

    MyVector vec1;
    int arraySize;
    double arrayValue = 1.0;

    std::cout << "Please enter in the size of your array: \n";
    std::cin >> arraySize;
    vec1.data = new double[arraySize];
    vec1.size = 0;
    vec1.capacity = arraySize;

    //In this loop I add 1 to each element within my vector
    for (size_t i = 0; i < vec1.capacity; i++) {
      vec1.data[i] = 1.0;
      vec1.size ++;
    }
    //print function to test
    PrintVector(vec1);

    //This uses the array sum to add 1 to each of the elements within my vector and returns h
    arrayValue = arrayModSum(vec1);

    std::cout << "initialArrayValue = " << arrayValue << std::endl;

    int oldCapcity = vec1.capacity;

    if (vec1.size == vec1.capacity) {
      GrowMyVector(vec1);
    }

    if(vec1.capacity / 2 != oldCapcity){
      std::cerr << "capacity failed" << std::endl;
      exit(-1);
    }

    std::cout << "Your new vector capacity is: " << vec1.capacity << "\n and your new size is: " << vec1.size << std::endl;
    PrintVector(vec1);

    //This for loop fills the rest of the elements in my vector with -1s
    for (size_t i = vec1.size; i < vec1.capacity; i++){
    vec1.data[i] = -1.0;
    vec1.size ++;
     }
     //Printing new (full) vector
     std::cout << "New full vector: \n";
     PrintVector(vec1);

    return 0;
}
