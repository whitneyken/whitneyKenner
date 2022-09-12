//
//  PointerPrac.cpp
//  PointerPrac
//
//  Created by Whitney Kenner on 9/12/22.
//

#include "PointerPrac.hpp"

//This is a function to print vectors
void PrintVector (const MyVector& vect){
      std::cout << "in PrintVector()" << std::endl;
  for (size_t i = 0; i < vect.size; i++) {
    std::cout << vect.data[i] << std::endl;
  }
}

//This function adds 1 to each element in my vector and then calculates the sum of those elements
double arrayModSum (MyVector& input){
  std::cout << "in arrayModSum()" << std::endl;
  double vectorSum = 0;
  for (size_t i = 0; i < input.size; i++) {
    input.data[i] += 1.0;
    vectorSum += input.data[i];
  }
  return vectorSum;
}
//This function grows the cpacity of my vector by 2
void GrowMyVector(MyVector& input){
  input.capacity *= 2;
  double* tempArray = new double[(input.capacity)];

  for (size_t i = 0; i < input.size; i++) {
    tempArray[i] = input.data[i];
  }
  delete [] input.data;
  input.data = tempArray;
  tempArray = nullptr;
}
