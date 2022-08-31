//
//  CardsFunctions.hpp
//  Cards
//
//  Created by Whitney Kenner on 8/30/22.
//

#ifndef CardsFunctions_hpp
#define CardsFunctions_hpp
#pragma once
#include <stdio.h>
#include <string>

/* suits will be an int for now, they are 0-3 and can currently be used as an index number to pull from a vector of strings to get a string of the associated suit */
struct Card{
    int cardValue;
    std::string cardStringValue;
    int suit;
};

std::vector<Card> MakeDeckOfCards();

void printDeckOfCards(std::vector<Card>);

#endif /* CardsFunctions_hpp */
