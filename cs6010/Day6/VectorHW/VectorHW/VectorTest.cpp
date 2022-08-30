/*
  Author: Daniel Kopta and ??
  July 2017
  
  CS 6010 Fall 2019
  Vector util library tests
  Compile this file together with your VectorUtil library with the following command:
  clang++ -std=c++11 VectorTest.cpp VectorUtil.cpp
  Most of the provided tests will fail until you have provided correct 
  implementations for the VectorUtil library functions.
  You will need to provide more thorough tests.
*/

#include <iostream>
#include <string>

// Include the VectorUtil library
#include "VectorUtil.h"

/*
 * Helper function for failing a test.
 * Prints a message and exits the program.
 */
void ErrorExit( std::string message )
{
  std::cerr << "Failed test: " << message << std::endl;
  exit(1); // Causes the entire program to exit.
}


int main()
{
  
  // Set up some input vectors for testing purposes.

  // We can use list initialization syntax:
  vector<int> v1 = {3, 1, 0, -1, 5};

  // Or we can repeatedly push_back items
  vector<int> v2;
  v2.push_back(1);
  v2.push_back(2);
  v2.push_back(3);

  // When testing, be sure to check boundary conditions, such as an empty vector
  vector<int> empty;
  
  vector<int> v4 = {45};
  /* 
   * Contains tests 
   */

  // v1 doesn't contain 4, so this should return false
  if( Contains(v1, 4) ) {
    ErrorExit( "Contains() - test 1" );
  }

  // v1 does contain -1, so this should return true
  if(!Contains(v1, -1)) {
    ErrorExit("Contains() - test 2");
  }

  /* 
   * The vector 'empty' doesn't contain anything, so this should return false
   * The specific value we're looking for here (99) is not important in this test. 
   * This test is designed to find any general errors caused by the array being empty. 
   * That type of error is unlikely to depend on the value we are looking for.
  */
  if( Contains(empty, 99) ) {
    ErrorExit("Contains() - empty");
  }
  
  // TODO: Add your own tests that thoroughly exercise your VectorUtil library.
    //Tests for FindMin
    if ( FindMin(v1) != -1) {
        ErrorExit("Failed to find lowest number- test 1");
    }
    
    if ( FindMin(v2) != 1) {
        ErrorExit("Failed to find lowest number- test 2");
    }
    //Tests for FindMax
    if ( FindMax(v1) != 5) {
        ErrorExit("Failed to find max number- test 1");
    }
    if ( FindMax(v2) != 3) {
        ErrorExit("Failed to find max number- test 2");
    }
    //Tests for Average
    if ( Average(v1) != 1) {
        ErrorExit("Failed to find average- test 1");
    }
    if ( Average(v2) != 2) {
        ErrorExit("Failed to find average- test 2");
    }
    //tests for IsSorted
    if ( IsSorted(v1) == true) {
        ErrorExit("Unsorted - test 1");
    }
    if ( IsSorted(v2) == false) {
        ErrorExit("Unsorted - test 2");
    }
    if ( IsSorted(empty) == false) {
        ErrorExit("Unsorted - test 3");
    }
    if ( IsSorted(v4) == false) {
        ErrorExit("Unsorted - test 4");
    }
    
  // Since any failed test exits the program, if we made it this far, we passed all tests.
  std::cout << "All tests passed!\n";

}
