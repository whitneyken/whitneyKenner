//
//  WordHelpers.cpp
//  StringAnalyzer
//
//  Created by Whitney Kenner on 8/29/22.
//

#include "WordHelpers.hpp"
#include "LetterHelpers.hpp"

//This function counts the number of words in a sentence
int numWords (std::string sentence){
    int wordCounter = 1;
    for (int i = 0; i < sentence.length(); i++) {
        if (sentence[i] == ' ') {
            wordCounter++;
        }
    }
    return wordCounter;
}

//this function is used to calculate the number of sentences
int numSentences (std::string sentence){
    int sentenceCounter = 0;
    for (int i = 0; i < sentence.length(); i++) {
        if (isTerminator(sentence[i]))
            sentenceCounter++;
    }
    return sentenceCounter;
}

//this function determines average word length
double averageWordLength (std::string sentence){
    int numberOfWords = 0;
    int numberOfVowels = 0;
    int numberOfConsonants = 0;
    float readingLevel = 0;
    
    numberOfWords = numWords(sentence);
    numberOfVowels = numVowels(sentence);
    numberOfConsonants = numConsonants(sentence);
    readingLevel = (numberOfVowels + numberOfConsonants + 0.0) / numberOfWords;
    return readingLevel;
}
//this determines the average vowels per word
double averageVowelsPerWord (std::string sentence){
    int numberOfWords = 0;
    int numberOfVowels = 0;
    float avgVowelsPerWord = 0;
    
    numberOfWords = numWords(sentence);
    numberOfVowels = numVowels(sentence);
    avgVowelsPerWord = (static_cast<float>(numberOfVowels))/ numberOfWords;
    
    return  avgVowelsPerWord;
}

