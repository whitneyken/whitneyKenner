//
//  VectorFunctions.hpp
//  VectorUpgrades
//
//  Created by Whitney Kenner on 9/14/22.
//

#ifndef VectorFunctions_hpp
#define VectorFunctions_hpp

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
    //copy constructor
    MyVector(const MyVector& vect2);
    //These are my methods
    int Get (int index);
    
    void Set (int index, int newValue);
    
    void PushBack (int input);
    
    void PopBack ();
    
    void GrowMyVector ();
    
    ~MyVector ();
    
    void PrintVector () const;
    
    int GetSize ();
    
    int GetCapacity ();
    
    MyVector& operator = (const MyVector& vect2);
    
};

void RunTests ();

#endif /* VectorFunctions_hpp */
