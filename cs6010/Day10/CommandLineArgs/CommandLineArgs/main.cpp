//
//  main.cpp
//  CommandLineArgs
//
//  Created by Whitney Kenner on 9/2/22.
//

#include <iostream>

int main(int argc,  char ** argv) {
    //printing out the command line arguments!!!!!!
    //TO THE COMMAND LINE
    
    /* the first element of the argv array is "./main" since that is the first thing we enter. so we could can have it start with 1 to avoid printing that out again*/
    
    for (int i = 1; i < argc; i++) {
        std::cout << argv[i] << " ";
    }
    std::cout << "\n";
    return 0;
}
