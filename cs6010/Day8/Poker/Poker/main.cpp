//
//  main.cpp
//  Poker
//
//  Created by Whitney Kenner on 8/31/22.
// collaboration with Felix Ye
//
//Over 1000000 runs:
//
//Percentage of flushes: 0.2027%
//Percentage of straights: 0.4025%
//Percentage of straight flushes: 0.0012%
//Percentage of royal flushes: 0.0001%
//Percentage of full houses: 0.1699%

#include <iostream>
#include "PokerFunctions.hpp"

int main(int argc, const char * argv[]) {
        srand(time(NULL));
        //declare variables
        std::vector<Card> createdCardDeck;
        std::vector<Card> currentHand;
        std::vector<float> trackingStats {0, 0, 0, 0, 0};
        
        createdCardDeck = MakeDeckOfCards();
    int numberOfHands = 1000000;
        runTests();
    
    trackingStats = checkStats(createdCardDeck, numberOfHands);
    std::cout << "Percentage of flushes: " << (trackingStats[0]/numberOfHands)*100 << "\n";
    std::cout << "Percentage of straights: " << (trackingStats[1]/numberOfHands)*100 << "\n";
    std::cout << "Percentage of straight flushes: " << (trackingStats[2]/numberOfHands)*100 << "\n";
    std::cout << "Percentage of royal flushes: " << (trackingStats[3]/numberOfHands)*100 << "\n";
    std::cout << "Percentage of full houses: " << (trackingStats[4]/numberOfHands)*100 << "\n";
        //shuffleTheDeck(createdCardDeck);
    

        //printCards(createdCardDeck);
    
        //currentHand = DrawFiveCards(createdCardDeck);
    
        //printCards(currentHand);
        //std::cout << "\n";
    
        //Sort(currentHand);
        //printCards(currentHand);
    
    
    
    
    
}
