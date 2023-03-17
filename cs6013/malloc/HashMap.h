//
// Created by Whitney Kenner on 3/15/23.
//

#ifndef MALLOC_HASHMAP_H
#define MALLOC_HASHMAP_H
#include <cstddef>
#include <sys/mman.h>
#include <iostream>


class HashNode{
public:
    void* address;
    size_t size;

    HashNode(void* add, size_t size);
};

class HashMap {
public:
    HashNode* array;
    int capacity;
    int currentSize;

public:
    HashMap();
    ~HashMap();
    HashMap(int initialCapacity);
    size_t hashCode(long address);
    bool insertNode(void* addressPointer, size_t sizeInPages);
    size_t deleteNode(void *addressPointer);
    void growHashMap();
};


#endif //MALLOC_HASHMAP_H
