//
//  LetterHelpers.hpp
//  StringAnalyzer
//
//  Created by Whitney Kenner on 8/29/22.
//
#pragma include once

#include <string>
#include <cstdio>
#include <iostream>
#include <cmath>

bool isPunctuation(char c);

bool isVowel(char c);

bool isConsonant (char c);

int numVowels (std::string sentence);

int numConsonants (std::string sentence);

bool isTerminator(char c);

