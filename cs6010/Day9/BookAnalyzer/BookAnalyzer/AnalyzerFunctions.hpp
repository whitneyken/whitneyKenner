//
//  AnalyzerFunctions.hpp
//  BookAnalyzer
//
//  Created by Whitney Kenner on 9/1/22.
//

#ifndef AnalyzerFunctions_hpp
#define AnalyzerFunctions_hpp

#include <stdio.h>
#include <fstream>
#include <cstdlib>
#include <string>
#include <vector>
#include <iostream>

void printStringVector(const std::vector<std::string>& input);

std::vector<std::string> FindTitleVector (const std::vector<std::string>& input);

int FindTitleIndex(const std::vector<std::string>& input);
int FindAuthorIndex(const std::vector<std::string>& input);
int FindReleaseDateIndex(const std::vector<std::string>& input);
std::vector<std::string> FindAuthorVector (const                std::vector<std::string>& input);
int CountWords (const std::vector<std::string>& input);

int CountChars(const std::vector<std::string>& input);

std::string FindShortestWord (const std::vector<std::string>& input);
std::string FindLongestWord (const std::vector<std::string>& input);

#endif /* AnalyzerFunctions_hpp */
