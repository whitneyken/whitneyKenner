//
//  main.cpp
//  NumberRepresentations
//
//  Created by Whitney Kenner on 9/7/22.


#include <iostream>
#include <fstream>
#include "Functions.hpp"

int main(int argc, const char * argv[]) {
    // Part 1
    int a[] = { 7, 2, -56, 32, 1, 7, -2, 7, 9 };
    
    std::cout << "The size of a char is :" << sizeof(char) << "\n";
    std::cout << "The size of an int is :" << sizeof(int) << "\n";
    std::cout << "The size of a string is :" << sizeof(std::string) << "\n";
    std::cout << "The size of an int vector is :" << sizeof(std::vector<int>) << "\n";
    std::cout << "The size of a string vector is :" << sizeof(std::vector<std::string>) << "\n";
    std::cout << "The size of a char vector is :" << sizeof(std::vector<char>) << "\n";
    std:: cout << "The size of array a[] is: " << sizeof( a ) << "\n";
    /* The array displays a size of 32 because each int is 4 bits so 4 * 8 = 32*/
    //Checking for int types that have fixed and known size
    std::cout << "The size of an int8_t is :" << sizeof(int8_t) << "\n";
    std::cout << "The size of an int16_t is :" << sizeof(int16_t) << "\n";
    std::cout << "The size of an int32_t is :" << sizeof(int32_t) << "\n";
    std::cout << "The size of an uint8_t is :" << sizeof(uint8_t) << "\n";
    std::cout << "The size of an uint16_t is :" << sizeof(uint16_t) << "\n";
    std::cout << "The size of an uint32_t is :" << sizeof(uint32_t) << "\n";
    //Max and min of different unsiged set int types
    uint8_t maxNum1 = 0xFF;
    uint8_t minNum1 = 0x0;
    //maxNum1 += 0x1;
    //minNum1 -= 0x1;
    
    std::cout << "The max uint8_t is: " << +maxNum1 << std::endl;
    std::cout << "The min uint8_t is: " << +minNum1 << std::endl;
    
    uint16_t maxNum2 = 0xFFFF;
    uint16_t minNum2 = 0x0;
    //maxNum2 += 0x1;
    //minNum2 -= 0x1;
    
    std::cout << "The max uint16_t is: " << +maxNum2 << std::endl;
    std::cout << "The min uint16_t is: " << +minNum2 << std::endl;
    
    uint64_t maxNum3 = 0xFFFFFFFFFFFFFFFF;
    uint64_t minNum3 = 0x0;
    //maxNum3 += 0x1;
    //minNum3 -= 0x1;
    
    std::cout << "The max uint64_t is: " << +maxNum3 << std::endl;
    std::cout << "The min uint64_t is: " << +minNum3 << std::endl;
    
    //Max and min of different siged set int types
    
    int8_t maxNum4 = 0x7F;
    int8_t minNum4 = 0x80;
    //maxNum4 += 0x1;
    //minNum4 -= 0x1;
    
    std::cout << "The max int8_t is: " << +maxNum4 << std::endl;
    std::cout << "The min int8_t is: " << +minNum4 << std::endl;
    
    int16_t maxNum5 = 0x7FFF;
    int16_t minNum5 = 0x8000;
    //maxNum5 += 0x1;
    //minNum5 -= 0x1;
    
    std::cout << "The max int16_t is: " << +maxNum5 << std::endl;
    std::cout << "The min int16_t is: " << +minNum5 << std::endl;
    
    int64_t maxNum6 = 0x7FFFFFFFFFFFFFFF;
    int64_t minNum6 = 0x8000000000000000;
    //maxNum6 += 0x1;
    //minNum6 -= 0x1;
    
    std::cout << "The max int64_t is: " << +maxNum6 << std::endl;
    std::cout << "The min int64_t is: " << +minNum6 << std::endl;
    
    /* Adding 1 to all my max variables switched all my unsigned ints to 0 and switched all my signed ints to the lowest possible negative number, which makes sense in the context of what is happening to the binary numbers when you add 1*/
    /* When you subtract 1 from every minimum number, the min number becomes the max number (also makes sense in light of what id happening to the binary code)*/
    /* When I print with the undefined number sanitizer turned on, it prints out a summary stating that there is a signed integer overflow on the signed int64_t when I subtract from the min and I get the sam ewarning for the max when I add one to int64_t*/
    
    
    //Part 2: floating point
    double addition1 = 0.1 + 0.2;
    //assert(addition1 == 0.3);
    
    //std::cout << std::setprecision(18) << addition1;
    //A double has 15 decimal digits of precision, and a float only has 7, hence the difference in value
    //std::cout << std::abs(addition1);
    
    
    if (approxEquals(addition1, 0.3, .001)) {
        std::cout << "This is within the acceptable range \n";
    }else{
        std::cout << "This is NOT within the acceptable range \n";
    }
    
    //Part 3

    std::string fileName = "UTF-8-demo.txt";
    std::ifstream characterStream (fileName);
    char singleChar;
    std::string allTheChars;
    int asciiCounter = 0;
    int unicodeCounter = 0;

    if (!characterStream.is_open()) {
        std::cout << "File fails to open. \n";
        exit(1);
    }

    while (characterStream >> singleChar) {
        std::cout << singleChar << std::endl;
        if (singleChar < 0) {
            unicodeCounter++;
        }else{
            asciiCounter++;
        }
    }
    std::cout << "The number of unicode characters is: " << unicodeCounter << std::endl;
    std::cout << "The number of ascii characters is: " << asciiCounter << std::endl;
    /* When printing, the ascii characters are printed out as characters but the unicode characters are printed out as "/someint". This matches my expectations because I wouldn't expect Xcode to be able to print those characters unless I include a specific header file or use some sort of built in functiont to convert it. I am printing out the code that would signify the character, not the character itself*/
    return 0;
}
