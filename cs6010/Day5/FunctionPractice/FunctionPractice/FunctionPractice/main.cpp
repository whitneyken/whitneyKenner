//
//  main.cpp
//  FunctionPractice
//
//  Created by Whitney Kenner on 8/26/22.
//

#include <iostream>
#include <cmath>
#include <ctime>


float hypotenuseCalculator (float sideOne, float sideTwo){
    float hypotenuse = sqrt((sideOne * sideOne)+ (sideTwo * sideTwo));
    return hypotenuse;
}

bool isCapitalized (std::string string){
    if (isupper(string[0]) ) {
        return true;
    }else{
        return false;
    }
}

std::string boolToString(bool isCapitalized){
    std::string trueString = "true";
    std::string falseString = "false";
    
    if (isCapitalized == true) {
        return trueString;
    }else{
        return falseString;
    }
}



int main(int argc, const char * argv[]) {
    //declared variables
    float lengthOfSideOne = 0;
    float lengthOfSideTwo = 0;
    float hypotenuse = 0;
    
    
    /* first function we will call is to find the hypotenuse based on 2 entered lengths by the use*/
    std::cout << "Enter in the length of side one: ";
    std::cin >> lengthOfSideOne;
    
    std::cout << "\n Enter in the length of side two: ";
    std::cin >> lengthOfSideTwo;
    
    hypotenuse = sqrt((lengthOfSideOne * lengthOfSideOne) + (lengthOfSideTwo * lengthOfSideTwo));
    
    std::cout << "\n The length of your hypotenuse is: " << hypotenuse << std::endl;
    /* this next function will ask the user to input the speed they are going (a double) and the angle they are going (a double, that's the angle in radians). Then use the std::cos and std::sin functions and the formulas x = speed*cos(angle), y = speed*sin(angle) to print out their x and y velocity */
    
    double speedOfUser = 0;
    double angleOfUser = 0;
    double xVelocity = 0;
    double yVelocity = 0;
    
    
    std::cout << "Enter in your speed: ";
    std::cin >> speedOfUser;
    
    std::cout << "\n Enter in the angle you are going in: ";
    std::cin >> angleOfUser;
    
    xVelocity = speedOfUser*cos(angleOfUser);
    yVelocity = speedOfUser*sin(angleOfUser);
    
    std::cout << "\n Your x velocity is: " << xVelocity << " and your y velocity is: " << yVelocity << std::endl;
    
    /* Confirm that the code works as expected. Which functions are being called in the Example code? (We haven't talked about what the ampersand means yet, but we can ignore it for now.) */
    
    std::time_t result = std::time(nullptr);
    std::cout << std::asctime(std::localtime(&result))
                 << result << " seconds since the Epoch\n";
    //time(), asctime(), and localtime() are functions! 3 total functions in this
    
    /* Write a function that performs the hypotenuse task described above, but does not read from std::cin. What parameters should it take, and what will it return? Why wouldn't you want to get the input from std::cin inside your function?*/
    
    float partTwoLengthOfSideOne = 0;
    float partTwoLengthOfSideTwo = 0;
    
    std::cout << "Enter in the length of side one: ";
    std::cin >> partTwoLengthOfSideOne;
    
    std::cout << "\n Enter in the length of side two: ";
    std::cin >> partTwoLengthOfSideTwo;
    
    float hypotenuseLength = hypotenuseCalculator(partTwoLengthOfSideOne, partTwoLengthOfSideTwo);
    std::cout << "\n The length of your hypotenuse is: " << hypotenuseLength << std::endl;
    /* you wouldn't want to recieve the user input inside of the function because you really only want to do one thing, it would get messy having a function perform multiple tasks and it would make less reusable. In this example you wouldn't be able to use it to calculate the hypotenuse from hardcoded variables, you would be stuck getting user input every time*/
    
    /* Why would it be difficult to turn the speed/velocity task above into a function? What imperfect solutions can you come up with that wrap that code into one (or more) functions?*/
    /* The difficulty with creating a function for the speed/velocity task is we would have 2 variables to return, so it would have to be returned as an array (which we don't know how to do now) OR have 2 separate functions for the x velocity and y velocity that would return each individual variable*/
    /* Write a function isCapitalized that takes in a string parameter and returns whether or not the string starts with a capital letter.*/
    /* Write a function boolToString that takes in a Boolean parameter and returns the string "true" or "false" depending on its value. Use this function to display the results of testing the isCapitalized function.*/
    
    std::string userInputString;
    bool isFirstCharCapital;
    std::string trueOrFalseString;
    
    std::cout << "Please enter a string: ";
    std::cin >> userInputString;
    
    isFirstCharCapital = isCapitalized(userInputString);
    trueOrFalseString = boolToString(isFirstCharCapital);
    
    
    std::cout << "Your first letter is a capital: " << trueOrFalseString << std::endl;
    
    return 0;
}
