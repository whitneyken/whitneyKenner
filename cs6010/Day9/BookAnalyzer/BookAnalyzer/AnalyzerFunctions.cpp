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
    if ((indexOfAuthor - indexOfTitle) > 100) {
        stringTitleName = "Unknown title";
        titleName.push_back(stringTitleName);
        return titleName;
    }else if (indexOfTitle == -1 || indexOfAuthor == -1) {
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
int FindTitleIndex(const std::vector<std::string>& input){
    size_t indexOfTitle = -1;
    for (int i = 0; i <input.size(); i++) {
        if (input[i] == "Title:") {
            indexOfTitle = i;
        }
    }
    return indexOfTitle;
}
int FindAuthorIndex(const std::vector<std::string>& input){
    size_t indexOfAuthor = -1;
    for (int j = 0; j < input.size(); j++) {
        if (input[j] == "Author:") {
            indexOfAuthor = j;
        }
    }
    return indexOfAuthor;
}
int FindReleaseDateIndex(const std::vector<std::string>& input){
    size_t indexOfReleaseDate = -1;
    for (int k = 0; k < input.size(); k++) {
        if (input[k] == "Release" && input[k + 1] == "date:") {
            indexOfReleaseDate = k;
        }
    }
    return indexOfReleaseDate;
}
std::vector<std::string> FindAuthorVector (const std::vector<std::string>& input){
    size_t indexOfAuthor = -1;
    size_t indexOfReleaseDate = -1;
    
    std::string stringAuthorName;
    std::vector<std::string> authorName;
    indexOfAuthor = FindAuthorIndex(input);
    indexOfReleaseDate = FindReleaseDateIndex(input);
    if ((indexOfReleaseDate - indexOfAuthor) > 100) {
        stringAuthorName = "Unknown author";
        authorName.push_back(stringAuthorName);
        return authorName;
    }else if (indexOfReleaseDate == -1 || indexOfAuthor == -1) {
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
