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
struct MyVector{
    double* data;
    size_t capacity;
    size_t size;
    
};

MyVector MakeVector (size_t initialCapacity);

void FreeVector (MyVector vec);

void PushBack (MyVector& vec, int input);

void PopBack (MyVector& vec);

void PrintVector (const MyVector& vec);

int Get (const MyVector& vec, int index);

void Set (MyVector& vec, int index, int newValue);

void GrowMyVector (MyVector& vec);

#endif /* vectorFunctions_hpp */
