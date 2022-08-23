//
//  main.cpp
//  GiveChange
//
//  Created by Whitney Kenner on 8/23/22.
//this program will compute the fewest number of coins that can be returned as change


#include <iostream>

int main(int argc, const char * argv[]) {
    // first we declare variables
    int itemPrice;
    int amountPaidForItem;
    int changeOwed;
    int remainingChange;
    int totalQuarters;
    int totalDimes;
    int totalNickels;
    int totalPennies;
    
    //then we recieve input on what the user paid
    std::cout << "Enter the price of your purchased item in cents: ";
    std::cin >> itemPrice;
    std::cout << "\n Enter the amount you paid in cents: ";
    std::cin >> amountPaidForItem;
    //now calculate the change they will need to recieve
    changeOwed = amountPaidForItem - itemPrice;
    std::cout <<"\n The change owed is: " << changeOwed;
    //calculate quarters owed and remainder
    totalQuarters = changeOwed / 25;
    std::cout << "\n Quarters: " << totalQuarters << std::endl;
    remainingChange = changeOwed % 25;
    //calculate dimes owed and remainder
    totalDimes = remainingChange / 10;
    std::cout << "\n Dimes: " << totalDimes << std::endl;
    remainingChange = remainingChange % 10;
    //calculate nickels owed and remainder
    totalNickels = remainingChange / 5;
    std::cout << "\n Nickels: " << totalNickels << std::endl;
    remainingChange = remainingChange % 5;
    //total pennies owed is the remaining change
    totalPennies = remainingChange;
    std::cout << "\n Pennies: " << totalPennies << std::endl;
    
    
    
    return 0;
}
