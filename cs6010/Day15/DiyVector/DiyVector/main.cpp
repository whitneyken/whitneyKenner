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
    int userPushBack;
    int valueAtIndex = -1;
    int valueAtIndex2 = -1;
    
    RunTests();
    // going to recieve the capacity as input from the user
    std::cout << "Please enter your desired capacity: ";
    std::cin >> initialCapacity;
    
    MyVector vector1 (initialCapacity);
    
    std::cout << "\n What would you like to push back to the vector? ";
    std::cin >> userPushBack;
    
    //PushBack(vector1, userPushBack);
    vector1.PushBack(userPushBack);
    
    

    //Some testing things
    vector1.PushBack(4);
    vector1.PushBack(5);
    vector1.PushBack(6);
    vector1.PushBack(7);
    
    valueAtIndex = vector1.Get(2);
    std::cout << "\n The value at the index location 2 is: \n";
    std::cout << valueAtIndex <<std::endl;
    
    vector1.Set(2, 14);
    std:: cout << "\n My updated set vector is: \n";
    vector1.PrintVector();
    
    valueAtIndex2 = vector1.Get(2);
    std::cout << "\n The NEW value at the index location 2 is: \n";
    std::cout << valueAtIndex2 <<std::endl;
    vector1.PushBack(4);
    vector1.PushBack(6);
    vector1.PushBack(6);
    vector1.PushBack(7);
    //std::cout << "The new capacity is: " << vector1.capacity << std::endl;
    
    return 0;
}
