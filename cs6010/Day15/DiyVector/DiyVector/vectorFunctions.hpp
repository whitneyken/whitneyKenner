//
//  vectorFunctions.hpp
//  DiyVector
//
//  Created by Whitney Kenner on 9/13/22.
//

#ifndef vectorFunctions_hpp
#define vectorFunctions_hpp
#pragma once
#include <stdio.h>
#include <iostream>

//This is our vector struct
class MyVector{
private:
    double* data;
    size_t capacity;
    size_t size;
    
public:
    //This is my constructor
    MyVector (size_t initialCapacity);
    //These are my methods
    int Get (int index);
    
    void Set (int index, int newValue);
    
    void PushBack (int input);
    
    void PopBack ();
    
    void GrowMyVector ();
    
    void FreeVector ();
    
    void PrintVector () const;
    
    int GetSize ();
    
    int GetCapacity ();
};


#endif /* vectorFunctions_hpp */
