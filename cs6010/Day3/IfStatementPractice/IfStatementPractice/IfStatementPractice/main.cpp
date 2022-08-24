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
    else if (userAge > 60 && userAge <= 80){
        std::cout <<"\n I'm sorry, you are a baby boomer! \n";
    }
    else if (userAge > 40 && userAge <= 60){
        std::cout <<"\n You are part of generation X! \n";
    }
    else if (userAge > 20 && userAge <= 40){
        std::cout <<"\n You are a millenial! \n";
    }
    else{
        std::cout <<"\n You are an iKid! \n";
    }
    //Part 2 of lab
    //now recieve user input on what day it is
    char isTodayWeekday;
    char isTodayHoliday;
    char doesUserHaveChildren;
    
    do {
        std::cout << "\n Is it a weekday? (Y/N): ";
        std::cin >> isTodayWeekday;
    } while (isTodayWeekday != 'Y' && isTodayWeekday != 'N' && isTodayWeekday != 'y' && isTodayWeekday != 'n');
    do {
        std::cout << "\n Is it a holiday? (Y/N): ";
        std::cin >> isTodayHoliday;
    } while (isTodayHoliday != 'Y' && isTodayHoliday != 'N' && isTodayHoliday != 'y' && isTodayHoliday != 'n');
    do {
        std::cout <<("\n Do you have young children? (Y/N): ");
        std::cin >> doesUserHaveChildren;
    } while (doesUserHaveChildren != 'Y' && doesUserHaveChildren != 'N' && doesUserHaveChildren != 'y' && doesUserHaveChildren != 'n');
    /*Now we are going to make a bunch of if else statements to figure oout if this person gets to sleep in*/
    
    bool yesChildren = (doesUserHaveChildren == 'Y' || doesUserHaveChildren == 'y');
    bool isWeekday = (isTodayWeekday == 'Y' || isTodayWeekday == 'y');
    bool isNotHoliday = (isTodayHoliday == 'N' || isTodayHoliday == 'n');
    
    if (yesChildren) {
        std::cout <<("\n You do not get to sleep in today. \n");
    }else if (isWeekday && isNotHoliday){
        std::cout <<("\n You do not get to sleep in today. \n");
    }else{
        std::cout <<("\n Congratulations! You get to sleep in! \n");
    }
             
    return 0;
}
