//
//  main.cpp
//  PoliStructs
//
//  Created by Whitney Kenner on 8/30/22.
//

#include <iostream>
#include <cstring>
#include <vector>
#include "PoliFunctions.hpp"

int main(int argc, const char * argv[]) {
    // declare variables
    std::vector<Politician> allPoliticians;
    std::vector<Politician> javacansPoliticians;
    std::vector<Politician> IsFederalAndCplusers;
    //define our politicians
    Politician politician1 {"Felix", "Cplusers", "State"};
    Politician politician2 {"Whitney", "Cplusers", "Federal"};
    Politician politician3 {"Nicole", "Javacans", "State"};
    Politician politician4 {"Amelia", "Javacans", "Federal"};
    Politician politician5 {"Celeste", "Cplusers", "State"};
    Politician politician6 {"Chase", "Cplusers", "Federal"};
    Politician politician7 {"Aiden", "Javacans", "State"};
    Politician politician8 {"Nick", "Javacans", "state"};
    
    allPoliticians = {politician1, politician2, politician3, politician4, politician5, politician6, politician7, politician8};
    
    javacansPoliticians = Javacans(allPoliticians);
    IsFederalAndCplusers = FederalCplusers(allPoliticians);
    
    std::cout << "Javacans politicians: " << std::endl;
    for (Politician pols:javacansPoliticians) {
        std::cout << pols.name <<", ";
    }
    std::cout << " \n Federal and Cpluser politicians: " << std::endl;
    for (Politician pols:IsFederalAndCplusers) {
        std::cout << pols.name << ", ";
    }
    
    //test cases
    if (contains(javacansPoliticians, "Felix") == true) {
        std::cout << "Failed test case 1";
    }
    if (contains(javacansPoliticians, "Amelia") == false) {
        std::cout << "Failed test case 2";
    }
    if (contains(IsFederalAndCplusers, "Whitney") == false) {
        std::cout << "Failed test case 3";
    }
    if (contains(IsFederalAndCplusers, "Nick") == true) {
        std::cout << "Failed test case 4";
    }
    if (contains(IsFederalAndCplusers, "Chase") != true) {
        std::cout << "Failed test case 5";
    }
    if (contains(javacansPoliticians, "Amelia") != true) {
        std::cout << "Failed test case 6";
    }

}
