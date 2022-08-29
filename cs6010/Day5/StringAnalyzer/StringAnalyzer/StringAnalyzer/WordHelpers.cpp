//
//  WordHelpers.cpp
//  StringAnalyzer
//
//  Created by Whitney Kenner on 8/29/22.
//

#include "WordHelpers.hpp"
#include "LetterHelpers.hpp"
#include <string>
#include <cmath>

//This function counts the number of words in a sentence
int numWords (std::string sentence){
    int wordCounter = 1;
    for (int i = 0; i <= (sentence.length() - 1); i++) {
        if (sentence[i] == ' ') {
            wordCounter++;
        }
    }
    return wordCounter;
}

//this function is used to calculate the number of sentences
int numSentences (std::string sentence){
    int sentenceCounter = 0;
    bool isCharPunctuation;
    for (int i = 0; i <= (sentence.length() - 1); i++) {
        if ((isCharPunctuation = isTerminator(sentence[i])) == true)
            sentenceCounter++;
    }
    return sentenceCounter;
}

//this function determines average word length
double averageWordLength (std::string sentence){
    float numberOfWords = 0;
    float numberOfVowels = 0;
    float numberOfConsonants = 0;
    float readingLevel = 0;
    
    numberOfWords = numWords(sentence);
    numberOfVowels = numVowels(sentence);
    numberOfConsonants = numConsonants(sentence);
    readingLevel = ((numberOfVowels + numberOfConsonants) / numberOfWords);
    return readingLevel;
}
//this determines the average vowels per word
double averageVowelsPerWord (std::string sentence){
    float numberOfWords = 0;
    float numberOfVowels = 0;
    float avgVowelsPerWord = 0;
    
    numberOfWords = numWords(sentence);
    numberOfVowels = numVowels(sentence);
    avgVowelsPerWord = (numberOfVowels / numberOfWords);
    
    return  avgVowelsPerWord;
}

