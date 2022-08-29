//
//  main.cpp
//  StringAnalyzer
//
//  Created by Whitney Kenner on 8/26/22.
//

#include <iostream>
#include <string>
#include <cmath>
#include "LetterHelpers.hpp"
#include "WordHelpers.hpp"

int main(int argc, const char * argv[]) {
    //declare variables
    std::string userInputString;
    int numberOfWords = 0;
    int numberOfSentences = 0;
    int numberOfVowels = 0;
    int numberOfConsonants = 0;
    float readingLevel = 0;
    float avgVowelsPerWord = 0;
    // first get a string from the user.
    do {
        std::cout << "Enter a string containing one or more sentences: ";
        std::getline(std::cin, userInputString);
        
        //this if statement will make it exit if the user enters done
        if (userInputString == "done"){
            std::cout << "Goodbye! \n";
            break;
        }
        //here we reference all the functions
        numberOfWords = numWords(userInputString);
        numberOfSentences = numSentences(userInputString);
        numberOfVowels = numVowels(userInputString);
        numberOfConsonants = numConsonants(userInputString);
        readingLevel = averageWordLength(userInputString);
        avgVowelsPerWord = averageVowelsPerWord(userInputString);
        
        //printing out all of the results
        std::cout << "Analysis: " << std::endl << "Number of words: " << numberOfWords << "\n Number of sentences: " << numberOfSentences <<"\n Number of vowels: " << numberOfVowels << "\n Number of consonants: " << numberOfConsonants << "\n Reading level (average word length): " << readingLevel << "\n Average vowels per word: " << avgVowelsPerWord << std::endl;
        //while true makes it repeat unless the user enters "done"
    } while (true);
    return 0;
}
