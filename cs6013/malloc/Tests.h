//
// Created by Whitney Kenner on 3/17/23.
//

#ifndef MALLOC_TESTS_H
#define MALLOC_TESTS_H

#include <string>
#include <assert.h>
#include <iostream>
#include <chrono>
#include <vector>


class testSquid {
public:
    int tentacles;
    std::string name;
public:
    testSquid();
};
void runTests();
void runBenchmarks();



#endif //MALLOC_TESTS_H
