//
//  main.cpp
//  StatusCheck1
//
//  Created by Whitney Kenner on 8/29/22.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    int inputNumber = 0;
    int addedInputNumber = 0;
    
    // insert code here...
    for (int i = 0; i <= 3; i++) {
        std::cout << "Please enter a number between 1 and 10 (or -99 to quit): \n";
        std::cin >> inputNumber;
        if (inputNumber == -99 || inputNumber > 10 || inputNumber < 1) {
            break;
        }else
            addedInputNumber += inputNumber;
    }
    std::cout << addedInputNumber <<std::endl;
    
    
    return 0;
}
