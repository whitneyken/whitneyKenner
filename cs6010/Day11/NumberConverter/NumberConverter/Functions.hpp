//
//  Functions.hpp
//  NumberConverter
//
//  Created by Whitney Kenner on 9/6/22.
//

#ifndef Functions_hpp
#define Functions_hpp
#pragma once
#include <stdio.h>
#include <string>
#include <math.h>
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <vector>

int StringToInt(std::string input, int base);
std::string IntToDecimalString (int input);
std::string IntToBinaryString (int input);
std::string IntToHexString (int input);
void runTests ();

#endif /* Functions_hpp */
