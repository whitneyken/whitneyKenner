//
//  CardsFunctions.cpp
//  Cards
//
//  Created by Whitney Kenner on 8/30/22.
//

#include "CardsFunctions.hpp"
#include <vector>
#include <iostream>


//Make card deck making function
std::vector<Card> MakeDeckOfCards(){
    std::vector<Card> fullDeckOfCards;
    for (int cardNum = 1; cardNum <= 14; cardNum++) {
        for (int suit = 0; suit <= 3; suit++) {
            Card c;
            c.cardValue = cardNum;
            c.suit = suit;
            
            if (cardNum == 11) {
                c.cardStringValue = "J";
            }else if (cardNum == 12){
                c.cardStringValue = "Q";
            }else if (cardNum == 13){
                c.cardStringValue = "K";
            }else if (cardNum == 14){
                c.cardStringValue = "A";
            }else{
                c.cardStringValue = std::to_string(cardNum);
            }
            fullDeckOfCards.push_back(c);
        }
    }
    return fullDeckOfCards;
}

//This function prints the deck of cards
void printDeckOfCards(std::vector<Card> deckToPrint){
    
    std::vector<std::string> suits = {"Hearts","Spades","Clubs","Diamonds"};
    
    for (int num = 0; num < deckToPrint.size(); num++) {
        std::cout << deckToPrint[num].cardStringValue << " of " << suits[deckToPrint[num].suit] << std::endl;
    }
}
