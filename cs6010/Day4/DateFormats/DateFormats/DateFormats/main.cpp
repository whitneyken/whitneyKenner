//
//  main.cpp
//  DateFormats
//
//  Created by Whitney Kenner on 8/25/22.


//collaborated with Lauryn C. Hansen

#include <iostream>
#include <string>



int main(int argc, const char * argv[]) {
    // Goal is to convert from US numeric format into an english sentence format
    std:: string usNumericDate;
    std:: string month;
    std:: string day;
    std:: string year;
    int intDay;
    int intMonth;
    int intYear;
    
    
    std::cout << "Please enter a US numeric date in the format (mm/dd/yyyy): \n";
    std::cin >> usNumericDate;
    
    //Find the location of the slashes
    size_t firstSlash = usNumericDate.find("/");
    
    size_t secondSlash = usNumericDate.find("/", firstSlash + 1);

    //now we need to break up our string into substrings
    month = usNumericDate.substr(0, firstSlash);
    
    day = usNumericDate.substr(firstSlash + 1, 2);
    
    year = usNumericDate.substr(secondSlash + 1, 4);
    
    //Now we need to convert these strings to ints
    
    intDay = std::stoi(day);
    intMonth = std::stoi(month);
    intYear = std::stoi(year);
    
    //first we create something so that if the numbers enetered are out of scope, you will be told this is an invalid date
        if (intDay > 31 || intDay < 1 || intMonth > 12 || intMonth < 1 || intYear > 9999 || intYear < 1000) {
        std::cout << "You have entered an invalid date! \n";
            //Now we determine what month it is
    } else if(intMonth == 1){
        std::cout << "January " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 2){
        std::cout << "February " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 3){
        std::cout << "March " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 4){
        std::cout << "April " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 5){
        std::cout << "May " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 6){
        std::cout << "June " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 7){
        std::cout << "July " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 8){
        std::cout << "August " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 9){
        std::cout << "September " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 10){
        std::cout << "October " << intDay << ", " << intYear << std:: endl;
    }else if (intMonth == 11){
        std::cout << "November " << intDay << ", " << intYear << std:: endl;
    }else{
        std::cout << "December " << intDay << ", " << intYear << std:: endl;
    }
    
    return 0;
}
