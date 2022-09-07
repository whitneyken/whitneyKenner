//
//  Functions.cpp
//  NumberRepresentations
//
//  Created by Whitney Kenner on 9/7/22.
//

#include "Functions.hpp"

//Function to check if the added numbers are within our tolerance
bool approxEquals( double num1, double num2, double tolerance ){
    if (std::abs(num2 - num1) <= tolerance) {
        return true;
    }
    return false;
}
