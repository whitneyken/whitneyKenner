//
//  PointerPrac.hpp
//  PointerPrac
//
//  Created by Whitney Kenner on 9/12/22.
//

#ifndef PointerPrac_hpp
#define PointerPrac_hpp
#pragma once
#include <stdio.h>
#include <vector>
#include <iostream>

struct MyVector {
  double* data;
  int size;
  int capacity;
};


void PrintVector (const MyVector& vect);

double arrayModSum (MyVector& input);

void GrowMyVector(MyVector& input);
#endif /* PointerPrac_hpp */
