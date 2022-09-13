//
//  main.cpp
//  DiyVector
//
//  Created by Whitney Kenner on 9/13/22.
//

#include <iostream>
#include "vectorFunctions.hpp"

int main(int argc, const char * argv[]) {
    size_t initialCapacity;
    MyVector vector1;
    int userPushBack;
    int valueAtIndex = -1;
    int valueAtIndex2 = -1;
    // going to recieve the capacity as input from the user
    std::cout << "Please enter your desired capacity: ";
    std::cin >> initialCapacity;
    
    vector1 = MakeVector(initialCapacity);
    
    std::cout << "\n What would you like to push back to the vector? ";
    std::cin >> userPushBack;
    
    PushBack(vector1, userPushBack);
    
    
    
    
    //Some testing things
    PushBack(vector1, 4);
    PushBack(vector1, 6);
    PushBack(vector1, 6);
    PushBack(vector1, 7);
    PrintVector(vector1);
    
    valueAtIndex = Get(vector1, 2);
    std::cout << "\n The value at the index location 2 is: \n";
    std::cout << valueAtIndex <<std::endl;
    
    Set(vector1, 2, 14);
    std:: cout << "\n My updated set vector is: \n";
    PrintVector(vector1);
    
    valueAtIndex2 = Get(vector1, 2);
    std::cout << "\n The NEW value at the index location 2 is: \n";
    std::cout << valueAtIndex2 <<std::endl;
    
    return 0;
}
