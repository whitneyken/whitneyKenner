//
//  vectorFunctions.cpp
//  DiyVector
//
//  Created by Whitney Kenner on 9/13/22.
//

#include "vectorFunctions.hpp"

MyVector MakeVector (size_t initialCapacity){
    MyVector tempVector;
    tempVector.capacity = initialCapacity;
    tempVector.size = 0;
    tempVector.data = new double[initialCapacity];
    return tempVector;
}

void PrintVector (const MyVector& vec){
    for (int i = 0; i < vec.size; i++) {
        std::cout << vec.data[i] << " ";
    }
    std::cout << std::endl;
}

void FreeVector (MyVector& input){
    delete [] input.data;
    input.data = nullptr;
}

//this will work like the std::vector push back
//first it will check if the capacity is equal to the size
//if it is equal, call the expand capacity function
//then push back
void PushBack (MyVector& vecInput, int input){
    if (vecInput.capacity == vecInput.size) {
        GrowMyVector(vecInput);
    }
    vecInput.data[vecInput.size] = input;
    vecInput.size += 1;
}
//this function will work like pop back and remove the last element
//this happens by not truly removing the element, but by reducing size
void PopBack (MyVector& vec){
    vec.size -= 1;
}
//Will work like [] does for std::vector
//First we make sure index is within bounds
//Then get value and return
int Get (const MyVector& vec, int index){
    int valueAtIndex = -1;
    if (index > (vec.size + 1)) {
        std::cout << "\n You have entered an invalid index \n";
        exit(1);
    }
    valueAtIndex = vec.data[index];
    return valueAtIndex;
}
//This function will change the element at the input index location to the new value
//If the index is higher than the size, it will exit the program
void Set (MyVector& vec, int index, int newValue){
    if (index > (vec.size + 1)) {
        std::cout << "\n You have entered an invalid index \n";
        exit(2);
    }
    vec.data[index] = newValue;
}

//this function grows the vector if the size is equal to the capacity. It passes by reference so the changes are made to the vector itself
void GrowMyVector (MyVector& vec){
    if (vec.size == vec.capacity) {
        vec.capacity *= 2;
        double* tempArray = new double[vec.capacity];
        for (int i = 0; i < vec.size; i++) {
            tempArray[i] = vec.data[i];
        }
        delete [] vec.data;
        vec.data = tempArray;
        tempArray = nullptr;
    }
}
