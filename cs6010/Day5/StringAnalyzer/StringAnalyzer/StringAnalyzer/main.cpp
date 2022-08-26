//
//  main.cpp
//  StringAnalyzer
//
//  Created by Whitney Kenner on 8/26/22.
//

#include <iostream>
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
//this function helps calculate the number of sentences
bool isTerminator(char c){
    bool terminatorOrNot = false;
    if(c == '!' || c == '?' || c == '.')
        terminatorOrNot = true;
    return terminatorOrNot;
}
//this function is used to determine what is punctuation
bool isPunctuation(char c){
    bool punctuationOrNot = false;
    if(c == '!' || c == '?' || c == '.' || c == ',')
        punctuationOrNot = true;
    return punctuationOrNot;
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
//this boolean determines if something is a vowel
bool isVowel(char c){
    bool vowelOrNot = false;
    if (c == 'a' || c == 'A' || c == 'e'|| c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U' || c == 'y' || c == 'Y')
        vowelOrNot = true;
    return vowelOrNot;
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
//this function determines if something is a constant or not
bool isConsonant (char c){
    bool constantOrNot = false;
    if (isVowel(c) == false && isPunctuation(c) == false && c != ' ')
        constantOrNot = true;
    return constantOrNot;
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
        if (userInputString == "done")
            break;
    
        //here we refernece all the functions
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
