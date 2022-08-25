//
//  main.cpp
//  MagicDates
//
//  Created by Whitney Kenner on 8/25/22.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    // insert code here...
    // Goal is to convert from US numeric format into an english sentence format
    std:: string usNumericDate;
    std:: string month;
    std:: string day;
    std:: string year;
    std:: string lastTwoDigitsOfYear;
    int intDay;
    int intMonth;
    int intYear;
    int intLastTwoDigitsOfYear;
    
    
    std::cout << "Please enter a US numeric date in the format (mm/dd/yyyy): \n";
    std::cin >> usNumericDate;
    
    //Find the location of the slashes
    size_t firstSlash = usNumericDate.find("/");
    
    size_t secondSlash = usNumericDate.find("/", firstSlash + 1);

    //now we need to break up our string into substrings
    month = usNumericDate.substr(0, firstSlash);
    
    day = usNumericDate.substr(firstSlash + 1, 2);
    
    year = usNumericDate.substr(secondSlash + 1, 4);
    
    lastTwoDigitsOfYear = year.substr(2, 2);
    //Now we need to convert these strings to ints
    
    intDay = std::stoi(day);
    intMonth = std::stoi(month);
    intYear = std::stoi(year);
    intLastTwoDigitsOfYear = std::stoi(lastTwoDigitsOfYear);
    
    
    /*first we create something so that if the numbers enetered are out of scope, you will be told this is an invalid date*/
    if (intDay > 31 || intDay < 1 || intMonth > 12 || intMonth < 1 || intYear > 9999 || intYear < 1000) {
        std::cout << "You have entered an invalid date! \n";
    } else if (intDay * intMonth == intLastTwoDigitsOfYear) {
        std::cout << usNumericDate << " is a magic date! \n";
    }else{
        std::cout << usNumericDate <<" is NOT a magic date. \n";
    }
    
    return 0;
}
