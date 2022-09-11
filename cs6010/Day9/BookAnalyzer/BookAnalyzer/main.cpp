//
//  main.cpp
//  BookAnalyzer
//
//  Created by Whitney Kenner on 9/1/22.
//

#include <iostream>
#include "AnalyzerFunctions.hpp"


int main(int argc, const char * argv[]) {
    //declared variables
    std::fstream myFile;
    std::string chosenBook;
    std::string wordToFind;
    std::string singleWord;
    std::vector<std::string> allTheWords;
    std::vector<std::string> titleName;
    std::vector<std::string> authorName;
    int numWords = 0;
    int numChars = 0;
    std::string shortestWord;
    std::string longestWord;
    std::vector<keyWord> keyWordInfo;
    
        
   //These are my sample book files
    myFile.open("Whitney.txt", std::ios::out);
    if (myFile.is_open()) {
        myFile << "Hello world! This is my first line of code\n";
        myFile << "Hello world! This is my second line of code\n";
        myFile << "Hello world! This is my first line of code\n";
        myFile << "Hello world! This is my second line of code\n";
        myFile << "Title: Brave New World \n";
        myFile << "Author: Whitney Elizabeth Kenner \n";
        myFile << "Release date: 1567 of february. \n";
        myFile << "squarely inside the brackets \n";
        myFile << "squid and socks \n";
        myFile.flush();
        myFile.close();
    }
    
    /* first the user enters in the name of a text file and then the word they want to find*/
    std::cout << "Please enter the name of a file containing one of our avaiable book (including '.txt' at the end): \n";
    std::cin >> chosenBook;
    
    myFile.open(chosenBook, std::ios::in);
    if (!myFile.is_open()) {
        std::cout << "Failure! Wo do not have that book. \n";
        exit(1);
    }else{
        std::cout << "Success!\n";
    }
    std::cout << "Please enter a word to search for in the book: \n";
    std::cin >> wordToFind;
    
    
    //function to get all the words
    while (myFile >> singleWord) {
        allTheWords.push_back(singleWord);
    }
    
    //all of our functions

    titleName = (FindTitleVector(allTheWords));
    authorName = (FindAuthorVector(allTheWords));
    numWords = CountWords(allTheWords);
    numChars = CountChars(allTheWords);
    shortestWord = FindShortestWord(allTheWords);
    longestWord = FindLongestWord(allTheWords);
    keyWordInfo = FindKeyWords(allTheWords, wordToFind);
   
    //Printing the results
    std::cout << "Statistics for ";
    printStringVector(titleName);
    std::cout << " by ";
    printStringVector(authorName);
    std::cout << std::endl;
    std:: cout << "Number of words: " << numWords << "\n";
    std:: cout << "Number of characters: " << numChars << "\n";
    std::cout << "The shortest word is: " << "\"" << shortestWord << "\"" << ", and the longest word is: " << "\"" << longestWord << "\"" << "\n";
    PrintKeyWordDetails(keyWordInfo, wordToFind, numChars);
    
    
}
