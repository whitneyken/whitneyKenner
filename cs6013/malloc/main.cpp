#include <iostream>
#include "HashMap.h"
#include "Malloc.h"
#include "Tests.h"

#include <unistd.h>

int main() {
    Malloc myMalloc = Malloc();
    //the 3 commented lines below are to show the work flow for a single call to allocate and deallocate

//    size_t sample1 = 20;
//    void *sample1Addy = myMalloc.allocate(sample1);
//    myMalloc.deallocate(sample1Addy);


    //uncomment the lines below for unit testing and benchmark timing testing (see Tests.cpp)

    runTests();

    runBenchmarks();

    return 0;
}
