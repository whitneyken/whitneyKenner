//
//  AnalyzerFunctions.cpp
//  BookAnalyzer
//
//  Created by Whitney Kenner on 9/1/22.
//

#include "AnalyzerFunctions.hpp"
//This is a function to print a string vector
void printStringVector(const std::vector<std::string>& input){
    for (std::string elem: input){
        std::cout << elem << " ";
    }
}

//This is a function to print the title of the book
std::vector<std::string> FindTitleVector (const std::vector<std::string>& input){
    size_t indexOfTitle = -1;
    size_t indexOfAuthor = -1;
    
    std::string stringTitleName;
    std::vector<std::string> titleName;
    indexOfTitle = FindTitleIndex(input);
    indexOfAuthor = FindAuthorIndex(input);
    if (indexOfTitle == -1 || indexOfAuthor == -1 || (indexOfAuthor - indexOfTitle) > 100) {
        stringTitleName = "Unknown title";
        titleName.push_back(stringTitleName);
        return titleName;
    }else {
        for (int k = (indexOfTitle + 1); k < indexOfAuthor; k++) {
            titleName.push_back(input[k]);
        }
        return titleName;
    }
    
}
//This function finds the index of the title
int FindTitleIndex(const std::vector<std::string>& input){
    size_t indexOfTitle = -1;
    for (int i = 0; i <input.size(); i++) {
        if (input[i] == "Title:") {
            indexOfTitle = i;
        }
    }
    return indexOfTitle;
}
//This function finds the index of the author
int FindAuthorIndex(const std::vector<std::string>& input){
    size_t indexOfAuthor = -1;
    for (int j = 0; j < input.size(); j++) {
        if (input[j] == "Author:") {
            indexOfAuthor = j;
        }
    }
    return indexOfAuthor;
}
//This function finds the index of the release date
int FindReleaseDateIndex(const std::vector<std::string>& input){
    size_t indexOfReleaseDate = -1;
    for (int k = 0; k < input.size(); k++) {
        if (input[k] == "Release" && input[k + 1] == "date:") {
            indexOfReleaseDate = k;
        }
    }
    return indexOfReleaseDate;
}

//This function finds the author
std::vector<std::string> FindAuthorVector (const std::vector<std::string>& input){
    size_t indexOfAuthor = -1;
    size_t indexOfReleaseDate = -1;
    
    std::string stringAuthorName;
    std::vector<std::string> authorName;
    indexOfAuthor = FindAuthorIndex(input);
    indexOfReleaseDate = FindReleaseDateIndex(input);
    if (indexOfReleaseDate == -1 || indexOfAuthor == -1 || (indexOfReleaseDate - indexOfAuthor) > 100) {
        stringAuthorName = "Unknown author";
        authorName.push_back(stringAuthorName);
        return authorName;
    }else {
        for (int k = (indexOfAuthor + 1); k < indexOfReleaseDate; k++) {
            authorName.push_back(input[k]);
        }
        return authorName;
    }
    
}
//This function counts the words
int CountWords (const std::vector<std::string>& input){
    int wordCounter = 0;
    for (std::string elem: input) {
        wordCounter++;
    }
    return wordCounter;
}
//This function counts the characters
int CountChars(const std::vector<std::string>& input){
    int charCounter = 0;
    for (int i = 0; i < input.size(); i++) {
        for (int j = 0; j < input[i].size(); j++) {
            charCounter++;
        }
    }
    return charCounter;
}
//This function will find the shortest word in the text
std::string FindShortestWord (const std::vector<std::string>& input){
    std::string shortestWord = input[0];
    for (std::string elem: input) {
        if (shortestWord.size() > elem.size()) {
            shortestWord = elem;
        }
    }
    return shortestWord;
}
//This function will find the longest word in the text
std::string FindLongestWord (const std::vector<std::string>& input){
    std::string longestWord = input[0];
    for (std::string elem: input) {
        if (longestWord.size() < elem.size()) {
            longestWord = elem;
        }
    }
    return longestWord;
}
/* this function will find the keyword entered by the user as well as it's index location within the vector*/
std::vector<keyWord> FindKeyWords (const std::vector<std::string>& input, const std::string& word){
    std::vector<keyWord> keyWords;
    keyWord oneKeyWord;
    for (int i = 0; i < input.size(); i++) {
        if (input[i] == word) {
            oneKeyWord.indexLocation = i;
            oneKeyWord.threeWords = (input[i-1] + input[i] + input[i+1]);
            keyWords.push_back(oneKeyWord);
        }
    }
    return keyWords;
}
/* This function is to calculate and print the details of the word we searched for*/
void PrintKeyWordDetails (const std::vector<keyWord>& input, const std::string& word, int numWords){
    std::cout << "The word " << "'" << word << "'" << " appears " << (input.size()-1) << " times \n";
    for (int i = 0; i < input.size(); i++) {
        std::cout << "at " << (((input.indexLocation[i])/numWords)*100) << "%: " << input.threeWords[i] << "\n";
    }
}

