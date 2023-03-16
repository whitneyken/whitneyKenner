//
// Created by Whitney Kenner on 3/14/23.
//

#ifndef MALLOC_MALLOC_H
#define MALLOC_MALLOC_H
#include <cstddef>

class HashMap;

class Malloc {
public:
    HashMap myHash;


    Malloc();
    size_t getPagesToAllocate(size_t bytes);
    void *allocate(size_t bytesToAllocate);
    void deallocate(void *ptr);

};
#endif //MALLOC_MALLOC_H
