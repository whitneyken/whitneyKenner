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

int StringToInt(std::string input, int base);
std::string IntToDecimalString (int input);
std::string IntToBinaryString (int input);

#endif /* Functions_hpp */
