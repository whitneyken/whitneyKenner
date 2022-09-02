//
//  main.cpp
//  Cards
//
//  Created by Whitney Kenner on 8/30/22.
//

#include <iostream>
#include "CardsFunctions.hpp"
#include <vector>


int main(int argc, const char * argv[]) {
    //declare variable
    std::vector<Card> createdCardDeck;
    
    //Make deck
    createdCardDeck = MakeDeckOfCards();

    //Print deck
    printDeckOfCards(createdCardDeck);
    

    
   
    return 0;
}
