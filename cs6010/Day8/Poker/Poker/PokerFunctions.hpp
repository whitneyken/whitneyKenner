//
//  PokerFunctions.hpp
//  Poker
//
//  Created by Whitney Kenner on 8/31/22.
//

#ifndef PokerFunctions_hpp
#define PokerFunctions_hpp

#pragma once
#include <stdio.h>
#include <string>
#include <vector>
#include <iostream>
#include <cstdlib>
#include <stdlib.h>
#include <time.h>

/* suits will be an int for now, they are 0-3 and can currently be used as an index number to pull from a vector of strings to get a string of the associated suit */
struct Card{
    int cardValue;
    std::string cardStringValue;
    int suit;
};

std::vector<Card> MakeDeckOfCards();

void printCards(const std::vector<Card>& input);

void shuffleTheDeck (std::vector<Card>& input);

std::vector<Card> DrawFiveCards (std::vector<Card> input);

bool IsFlush (std::vector<Card> input);

bool IsStraight (std::vector<Card> input);

void SwapCard (Card& a, Card& b);

void Sort (std::vector<Card>& input);

bool IsStraightFlush (std::vector<Card> & input);

bool IsRoyalFlush (std::vector<Card> & input);

bool IsRoyals (std::vector<Card> & input);

bool IsFullHouse (std::vector<Card> & input);

std::vector<float> checkStats (std::vector<Card>& input, int num);

void runTests ();

#endif /* PokerFunctions_hpp */
