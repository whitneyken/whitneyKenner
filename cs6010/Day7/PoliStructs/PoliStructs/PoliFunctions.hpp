//
//  PoliFunctions.hpp
//  PoliStructs
//
//  Created by Whitney Kenner on 8/30/22.

#ifndef PoliFunctions_h
#define PoliFunctions_h

#pragma once

#include <vector>
#include <iostream>

//defining the structure politicians
struct Politician{
    std::string name;
    std::string partyAffiliation;
    std::string stateOrFed;
};
//declaring the Javacans function
std::vector <Politician> Javacans(std::vector<Politician> politician);

bool IsJavacans(Politician politition);

std::vector <Politician> FederalCplusers(std::vector<Politician> politician);
bool IsFederal(Politician politician);

bool contains(std::vector<Politician>, std::string inputName);

#endif

