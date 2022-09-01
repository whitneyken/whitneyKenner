//
//  PokerFunctions.cpp
//  Poker
//
//  Created by Whitney Kenner on 8/31/22.
//

#include "PokerFunctions.hpp"

//Make card deck making function
std::vector<Card> MakeDeckOfCards(){
    std::vector<Card> deck;
    for (int cardNum = 1; cardNum <= 13; cardNum++) {
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
            }else if (cardNum == 1){
                c.cardStringValue = "A";
            }else{
                c.cardStringValue = std::to_string(cardNum);
            }
            deck.push_back(c);
        }
    }
    return deck;
}

//This function prints the deck of cards
/* passing the parameters in by reference makes it so a copy is not made of the deck and it will not be changed through this process*/
void printCards(const std::vector<Card>& deckToPrint){
    
    std::vector<std::string> suits = {"Hearts","Spades","Clubs","Diamonds"};
    
    for (int num = 0; num < deckToPrint.size(); num++) {
        Card currentCard = deckToPrint[num];
        
        std::cout << currentCard.cardStringValue << " of " << suits[currentCard.suit] << std::endl;
    }
}
//This function will swap cards
void SwapCard (Card& a, Card& b){
    Card tempCard = b;
    b = a;
    a = tempCard;
}

//This function will shuffle the deck
void shuffleTheDeck (std::vector<Card>& deckToShuffle){
    for (int i = 51; i > 0; i--) {
        int j = rand() % i;
        SwapCard(deckToShuffle[i], deckToShuffle[j]);
        /*Card tempCard = deckToShuffle[j];
        deckToShuffle[j] = deckToShuffle[i];
        deckToShuffle[i] = tempCard;*/
    }
}

//This function draws 5 cards off the top of the shuffled deck
std::vector<Card> DrawFiveCards (std::vector<Card> fullDeck){
    std::vector<Card> buildingHand;
    for (int i = 0; i < 5; i++) {
        buildingHand.push_back(fullDeck[i]);
    }
    return buildingHand;
}
//This function checks if the current hand is a flush
bool IsFlush (std::vector<Card> currentHand){
    for (int i = 1; i < currentHand.size(); i++) {
        if (currentHand[0].suit != currentHand[i].suit) {
            return false;
        }
    }
    return true;
}
//This function checks if the current hand is a straight
bool IsStraight (std::vector<Card> currentHand){
    Sort(currentHand);
    if (currentHand[0].cardValue == 1 && currentHand[1].cardValue == 10 ) {
        for (int i = 2; i < 4; i++) {
            if ((currentHand[i -1].cardValue +1) != currentHand[i].cardValue) {
                return false;
            }
        }
    }else {
        for (int i = 1; i < currentHand.size(); i++) {
            if ((currentHand[i - 1].cardValue + 1) != currentHand[i].cardValue) {
                return false;
            }
        }
    }
    return true;
//send to sort before checking if it's striaght
}
//sort function for sorting a hand of cards
void Sort (std::vector<Card>& unsortedHand){
    for (int i = 0; i < (unsortedHand.size()-1); i++) {
        for (int j = (i + 1); j < unsortedHand.size(); j++) {
            if ((unsortedHand[i].cardValue) > (unsortedHand[j].cardValue)) {
                SwapCard(unsortedHand[i], unsortedHand[j]);
            }
        }
    }
}
//Check if it's a straight flush
bool IsStraightFlush (std::vector<Card> & currentHand){
    if (IsStraight(currentHand) && IsFlush(currentHand)) {
        return true;
    }
    return false;
}
//function to determine if hand is a royal flush
bool IsRoyalFlush (std::vector<Card> & currentHand){
    Sort(currentHand);
    if (IsFlush(currentHand) && IsStraight(currentHand)) {
        if (currentHand[0].cardValue == 1 && currentHand[1].cardValue == 10) {
            return true;
        }
    }
    return false;
}
/*bool IsRoyals (std::vector<Card> & currentHand){
    for (int i = 0; i < currentHand.size(); i++) {
        if (currentHand[i].cardValue < 10) {
            return false;
        }
    }
    return true;
}*/
//Function to check if our hand is a full house

bool IsFullHouse (std::vector<Card> & currentHand){
    std::vector<int> numOfCardValues {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    for (int i = 0; i < currentHand.size(); i++) {
        numOfCardValues[(currentHand[i].cardValue) - 1 ]++;
    }
    int numMults = 0;
    for (int j = 0; j < numOfCardValues.size(); j++) {
        if (numOfCardValues[j] != 0) {
            numMults++;
            if (numMults > 2) {
                return false;
            }
        }
    }
    return true;
}
        
//stats
/* in the tracking vector, index 0 tracks # of flushes, 1 tracks # of straights, 2 tracks straight flushes, 3 tracks royal flushes, 4 tracks full house*/
std::vector<float> checkStats (std::vector<Card>& inputDeck, int numberOfRuns){
    std::vector<Card> currentHand;
    std::vector<float> trackingSpecialHands {0, 0, 0, 0, 0};
    for (int i = 0; i < numberOfRuns; i++) {
        shuffleTheDeck(inputDeck);
        currentHand = DrawFiveCards(inputDeck);
        if (IsFlush(currentHand)) {
            trackingSpecialHands[0]++;
        }
        if (IsStraight(currentHand)) {
            trackingSpecialHands[1]++;
        }
        if (IsStraightFlush(currentHand)) {
            trackingSpecialHands[2]++;
        }
        if (IsRoyalFlush(currentHand)) {
            trackingSpecialHands[3]++;
        }
        if (IsFullHouse(currentHand)) {
            trackingSpecialHands[4]++;
        }
        
    }
    return trackingSpecialHands;
}
void runTests(){
//test deck 1 for flushes
    std::vector<Card> testFlushDeck;
    Card card1 = {4, "4", 0};
    Card card2 = {5, "5", 0};
    Card card3 = {8, "8", 0};
    Card card4 = {10, "10", 0};
    Card card5 = {12, "Q", 0};

    testFlushDeck.push_back(card1);
    testFlushDeck.push_back(card2);
    testFlushDeck.push_back(card3);
    testFlushDeck.push_back(card4);
    testFlushDeck.push_back(card5);
        
    if (!IsFlush(testFlushDeck)) {
        std::cout << "is flush failed! \n";
        exit(1);
    }

    //test deck 2 for straights
    std::vector<Card> testStraightDeck;
    Card card1b = {8, "8", 3};
    Card card2b = {5, "5", 2};
    Card card3b = {6, "6", 0};
    Card card4b = {9, "9", 1};
    Card card5b = {7, "7", 1};

    testStraightDeck.push_back(card1b);
    testStraightDeck.push_back(card2b);
    testStraightDeck.push_back(card3b);
    testStraightDeck.push_back(card4b);
    testStraightDeck.push_back(card5b);

    if (!IsStraight(testStraightDeck)) {
        std::cout << "Is straight failed";
        exit(1);
    }
     
    //test deck for straight flush
    std::vector<Card> testStraightFlushTestDeck;
    Card card1c = {8, "8", 0};
    Card card2c = {5, "5", 0};
    Card card3c = {6, "6", 0};
    Card card4c = {9, "9", 0};
    Card card5c = {7, "7", 0};

    testStraightFlushTestDeck.push_back(card1c);
    testStraightFlushTestDeck.push_back(card2c);
    testStraightFlushTestDeck.push_back(card3c);
    testStraightFlushTestDeck.push_back(card4c);
    testStraightFlushTestDeck.push_back(card5c);

    if (!IsStraightFlush(testStraightFlushTestDeck)) {
        std::cout << "Is straight flush failed";
        exit(1);
    }
    //test deck for royal flush

    std::vector<Card> royalFlushTestDeck;
    Card card1d = {1, "A", 0};
    Card card2d = {10, "10", 0};
    Card card3d = {12, "Q", 0};
    Card card4d = {11, "J", 0};
    Card card5d = {13, "K", 0};

    royalFlushTestDeck.push_back(card1d);
    royalFlushTestDeck.push_back(card2d);
    royalFlushTestDeck.push_back(card3d);
    royalFlushTestDeck.push_back(card4d);
    royalFlushTestDeck.push_back(card5d);

    if (!IsRoyalFlush(royalFlushTestDeck)) {
        std::cout << "Is royal flush failed! \n";
        exit(1);
    }

    //deck to test full house function
    std::vector<Card> fullHouseTestDeck;

    Card card1e = {4, "4", 0};
    Card card2e = {4, "4", 2};
    Card card3e = {4, "4", 2};
    Card card4e = {11, "J", 3};
    Card card5e = {11, "J", 0};

    fullHouseTestDeck.push_back(card1e);
    fullHouseTestDeck.push_back(card2e);
    fullHouseTestDeck.push_back(card3e);
    fullHouseTestDeck.push_back(card4e);
    fullHouseTestDeck.push_back(card5e);

    if (!IsFullHouse(fullHouseTestDeck)) {
        std::cout << "Full house failed";
        exit(1);
    }
    std::cout << "Run tests passed! \n";
}
