#include <iostream>
#include "HashMap.h"
#include "Malloc.h"

#include <unistd.h>

int main() {
    Malloc myMalloc = Malloc();






    std::cout << "before adding anything: " << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;
    size_t sample1 = 20;
    size_t sample2 = 300;
    size_t sample3 = 2;
    size_t sample4 = 99;
    size_t sample5 = 50;
    size_t sample6 = 500;

    void *sample1Addy = myMalloc.allocate(sample1);
    std::cout << "After adding 1 thing: " << std::endl;
    std::cout << "the address is: " << sample1Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;

    void *sample2Addy = myMalloc.allocate(sample2);
    std::cout << "After adding 2 thing: " << std::endl;
    std::cout << "the address is: " << sample2Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;

    void *sample3Addy = myMalloc.allocate(sample3);
    std::cout << "After adding 3 thing: " << std::endl;
    std::cout << "the address is: " << sample3Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;

    void *sample4Addy = myMalloc.allocate(sample4);
    std::cout << "After adding 4 thing: " << std::endl;
    std::cout << "the address is: " << sample4Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;

    void *sample5Addy = myMalloc.allocate(sample5);
    std::cout << "After adding 5 thing: " << std::endl;
    std::cout << "the address is: " << sample5Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;

    void *sample6Addy = myMalloc.allocate(sample6);
    std::cout << "After adding 6 thing: " << std::endl;
    std::cout << "the address is: " << sample6Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;

    //deallocate:
    myMalloc.deallocate(sample1Addy);
    std::cout << "After deallocating 1 thing: " << std::endl;
    //std::cout << "the address is: " << sample1Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;

    //deallocate:
    myMalloc.deallocate(sample2Addy);
    std::cout << "After deallocating 2 thing: " << std::endl;
    //std::cout << "the address is: " << sample1Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;
    //deallocate:
    myMalloc.deallocate(sample3Addy);
    std::cout << "After deallocating 3 thing: " << std::endl;
    //std::cout << "the address is: " << sample1Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;
    //deallocate:
    myMalloc.deallocate(sample4Addy);
    std::cout << "After deallocating 4 thing: " << std::endl;
    //std::cout << "the address is: " << sample1Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;
    //deallocate:
    myMalloc.deallocate(sample5Addy);
    std::cout << "After deallocating 5 thing: " << std::endl;
    //std::cout << "the address is: " << sample1Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;

    //deallocate:
    myMalloc.deallocate(sample6Addy);
    std::cout << "After deallocating 6 thing: " << std::endl;
    //std::cout << "the address is: " << sample1Addy << std::endl;
    std::cout << "the size is now: " << myMalloc.myHash.currentSize << std::endl;
    std::cout << "the capacity is now: " << myMalloc.myHash.capacity << std::endl;


    return 0;
}
