//
//  main.cpp
//  RoadTripCalculator
//
//  Created by Whitney Kenner on 8/23/22.
//This program calculates the total cost of a road trip

#include <iostream>

int main(int argc, const char * argv[]) {
    // collect information from user aboout their roadtrip
    //start by declaring variables
    int drivingDistanceInMiles;
    float mpgEfficiencyOfVehicle;
    float costOfGasInDollarsPerGallon;
    //Now begin asking the user for the input
    std::cout << "Enter the driving distance in miles of your road trip: ";
    std::cin >> drivingDistanceInMiles;
    std::cout << "\n Enter the miles per gallon efficiency of your vehicle: ";
    std::cin >> mpgEfficiencyOfVehicle;
    std::cout << "\n Enter in the cost of gas in dollars per gallon: ";
    std::cin >> costOfGasInDollarsPerGallon;
    //now make calculations based on the information entered
    float numberOfGallonsUsed = drivingDistanceInMiles / mpgEfficiencyOfVehicle;
    float totalCostOfTrip = numberOfGallonsUsed * costOfGasInDollarsPerGallon;
    //now we will print out the toal cost of the trip
    std::cout << "\n The total cost of yout road trip in dollars is: " << totalCostOfTrip << std::endl;
    return 0;
}
 
