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
    
        
   //These are my sample files
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
    myFile.open("Spooky.txt", std::ios::out);
    if (myFile.is_open()) {
        myFile << "What a beautiful day in the neighborhood \n";
        myFile << "I can't wait to repot my plants. \n";
        myFile.flush();
        myFile.close();
    }
    myFile.open("Halloween.txt", std::ios::out);
    if (myFile.is_open()) {
        myFile << "Some important things I have to say\n";
        myFile << "It is almost butternut squash season, the best time of yeat! \n";
        myFile.flush();
        myFile.close();
    }
    /* first the user enters in the name of a text file and then the word they want to find*/
    std::cout << "Please enter the name of a file containing one of our avaiable books: \n";
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
    //printStringVector(allTheWords);

    titleName = (FindTitleVector(allTheWords));
    
    printStringVector(titleName);
    
    authorName = (FindAuthorVector(allTheWords));
    
    printStringVector(authorName);
    
    
    
}
