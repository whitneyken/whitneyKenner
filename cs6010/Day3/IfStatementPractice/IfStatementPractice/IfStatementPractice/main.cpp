//
//  main.cpp
//  IfStatementPractice
//
//  Created by Whitney Kenner on 8/24/22.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    //declare variables
    int userAge;
    
    // prompt the user for their age
    do {
    std::cout << "Enter your age: ";
    std::cin >> userAge;
    } while (userAge < 0);
    //determine if the user is old enough to vote and run for senate and print it
    if (userAge >= 18){
        std::cout << "\n You are old enough to vote! \n";
    }
    if (userAge >= 30){
        std::cout << "\n You are old enough to run for senate! \n";
    }
    //determine the generation of the user
    if (userAge > 80){
        std::cout <<"\n You are part of the greatest generation! \n";
    }
    else if (userAge > 60 && userAge < 80){
        std::cout <<"\n I'm sorry, you are a baby boomer! \n";
    }
    else if (userAge > 40 && userAge < 60){
        std::cout <<"\n You are part of generation X! \n";
    }
    else if (userAge > 20 && userAge < 40){
        std::cout <<"\n You are a millenial! \n";
    }
    else{
        std::cout <<"\n You are an iKid! \n";
    }
    return 0;
}
