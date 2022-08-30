//
//  PoliFunctions.cpp
//  PoliStructs
//
//  Created by Whitney Kenner on 8/30/22.
//

#include "PoliFunctions.hpp"
#include <iostream>

//A boolean to determine if the politician is Javacans
bool IsJavacans(Politician politician){
    bool isJavacans = false;
    if (politician.partyAffiliation == "Javacans") {
        isJavacans = true;
    }
    return isJavacans;
}
std::vector <Politician> Javacans(std::vector<Politician> politicians){
    std::vector<Politician> JavacansPoliticians;
    for (Politician pols: politicians) {
        if (IsJavacans(pols) == true) {
            JavacansPoliticians.push_back(pols);
        }
    }
    return JavacansPoliticians;
}
bool IsFederal(Politician politician){
    bool isFederal = false;
    if (politician.stateOrFed == "Federal") {
        isFederal = true;
    }
    return isFederal;
}

std::vector <Politician> FederalCplusers(std::vector<Politician> politician){
    std::vector<Politician> fedAndCplusers;
    for (Politician pols: politician) {
        if (IsJavacans(pols) == false && IsFederal(pols) == true) {
            fedAndCplusers.push_back(pols);
        }
    }
    return fedAndCplusers;
}

//test cases
bool contains(std::vector<Politician> vector, std::string inputName){
    bool containsName = false;
    for (Politician pols: vector) {
        if(pols.name == inputName) {
            containsName = true;
        }
    }
    return containsName;
}




