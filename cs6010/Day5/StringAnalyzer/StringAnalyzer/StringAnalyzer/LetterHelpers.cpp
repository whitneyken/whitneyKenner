//
//  LetterHelpers.cpp
//  StringAnalyzer
//
//  Created by Whitney Kenner on 8/29/22.
//

#include "LetterHelpers.hpp"
#include "WordHelpers.hpp"
#include <cstdio>
#include <string>
#include <iostream>
#include <cmath>

//this function is used to determine what is punctuation
bool isPunctuation(char c){
    bool punctuationOrNot = false;
    if(c == '!' || c == '?' || c == '.' || c == ',')
        punctuationOrNot = true;
    return punctuationOrNot;
}
//this boolean determines if something is a vowel
bool isVowel(char c){
    bool vowelOrNot = false;
    if (c == 'a' || c == 'A' || c == 'e'|| c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U' || c == 'y' || c == 'Y')
        vowelOrNot = true;
    return vowelOrNot;
}
//this function determines if something is a constant or not
bool isConsonant (char c){
    bool constantOrNot = false;
    if (isVowel(c) == false && isPunctuation(c) == false && c != ' ')
        constantOrNot = true;
    return constantOrNot;
}

//this function helps calculate the number of sentences
bool isTerminator(char c){
    bool terminatorOrNot = false;
    if(c == '!' || c == '?' || c == '.')
        terminatorOrNot = true;
    return terminatorOrNot;
}

//this function calculates the number of vowels
int numVowels (std::string sentence){
    int vowelCounter = 0;
    bool isCharAVowel;
    for(int i = 0; i <= (sentence.length() - 1); i++){
        if ((isCharAVowel = isVowel(sentence[i])) == true)
        vowelCounter++;
    }
    return vowelCounter;
}
//this function calculates the number of constants
int numConsonants (std::string sentence){
    int consonantCounter = 0;
    bool isCharAConstant;
    for(int i = 0; i <= (sentence.length()-1); i++){
        if((isCharAConstant = isConsonant(sentence[i])) == true)
            consonantCounter++;
    }
        return consonantCounter;
}
