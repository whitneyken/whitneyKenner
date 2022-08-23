//
//  main.cpp
//  Averages
//
//  Created by Whitney Kenner on 8/23/22.
/* Group lab done with Madsion Klein. I completed the gradebook portion and she completed the unit conversion portion*/

#include <iostream>

int main(int argc, const char * argv[]) {
    // first we declare the variables
    int score1;
    int score2;
    int score3;
    int score4;
    int score5;
    
    // then we have the student enter their 5 assignment scores
    std::cout << "Assignment score 1: ";
    std::cin >> score1;
    std::cout << "\n Assignment score 2: ";
    std::cin >> score2;
    std::cout << "\n Assignment score 3: ";
    std::cin >> score3;
    std::cout << "\n Assignment score 4: ";
    std::cin >> score4;
    std::cout << "\n Assignment score 5: ";
    std::cin >> score5;
    //now we calculate the average of these scores and then print it for the user
    float averageOfAllScores = (score1 + score2 + score3 + score4 + score5) / 5.0;
    std::cout << "\n The average of your scores is: " << averageOfAllScores << std::endl;
    
    return 0;
}
