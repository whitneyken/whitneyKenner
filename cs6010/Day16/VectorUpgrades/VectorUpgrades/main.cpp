//
//  main.cpp
//  VectorUpgrades
//
//  Created by Whitney Kenner on 9/14/22.
//

#include <iostream>
#include "vectorFunctions.hpp"


int main(int argc, const char * argv[]) {
    
    RunTests();
//    size_t initialCapacity;
//    int userPushBack;
//    int valueAtIndex = -1;
//    int valueAtIndex2 = -1;
//    // going to recieve the capacity as input from the user
//    std::cout << "Please enter your desired capacity: ";
//    std::cin >> initialCapacity;

    MyVector vector1(5);
    
    MyVector vector2(5);


    //Some testing things
    vector1.PushBack(4);
    vector1.PushBack(5);
    vector1.PushBack(6);
    vector1.PushBack(7);
    vector1.PushBack(7);
    
    vector2.PushBack(1);
//    vector2.PushBack(1);
//    vector2.PushBack(1);
//    vector2.PushBack(1);
//    vector2.PushBack(1);
//
//    vector2 = vector1;
    vector2 = vector1;
    
    MyVector vector3 (vector1);
    assert(vector1.Get(4) == vector3.Get(4));
    
    vector1.PrintVector();
    vector3.PrintVector();
    
    vector3[3] = 2;

    std::cout << vector3[3] << std::endl;
    std::cout << vector1[3] << std::endl;
    

    MyVector vector4 (vector3);
    
    vector4.PopBack();
    vector4.PopBack();
    
  
}
