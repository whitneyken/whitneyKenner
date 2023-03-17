//
// Created by Whitney Kenner on 3/17/23.
//

#include "Tests.h"
#include "HashMap.h"
#include "Malloc.h"
//squids for testing new and free
testSquid::testSquid(){
    tentacles = 100;
    name = "david";
}
//run tests to verify allocate, deallocate and associated hashmap methods
void runTests() {
    std::cout << "------------------------In testing-----------------------" << std::endl;
    Malloc testMalloc = Malloc();
    //test initiating Malloc object and HashMap work
    assert(testMalloc.myHash.currentSize == 0);
    assert(testMalloc.myHash.capacity == 100);
    assert(testMalloc.myHash.array != nullptr);

    //test an individual insertion and deletion
    size_t testTwentyBytesToAdd = 20;
    void *addressOfTwentyBytesAdded = testMalloc.allocate(testTwentyBytesToAdd);
    size_t indexOfTwentyBytes = testMalloc.myHash.hashCode(reinterpret_cast<long>(addressOfTwentyBytesAdded));

    //the address was stored at the index we expect
    assert(addressOfTwentyBytesAdded == testMalloc.myHash.array[indexOfTwentyBytes].address);
    assert(testMalloc.myHash.currentSize == 1);
    testMalloc.deallocate(addressOfTwentyBytesAdded);
    assert(testMalloc.myHash.currentSize == 0);

    //test adding and removing things
    const int NUMALLOCATIONS = 1000;

    void *arrayOfPointers[NUMALLOCATIONS];
    int arrayOfInts[NUMALLOCATIONS];
    for (int index = 0; index < NUMALLOCATIONS; ++index) {
        arrayOfInts[index] = index + 1;
        arrayOfPointers[index] = nullptr;
    }
    for (int iterations = 0; iterations < NUMALLOCATIONS; iterations++) {
        arrayOfPointers[iterations] = testMalloc.allocate(arrayOfInts[iterations]);
    }
    assert(testMalloc.myHash.currentSize == 1000);
    assert(testMalloc.myHash.capacity == 3200);
    for (int iterations = 0; iterations < NUMALLOCATIONS; iterations++) {
        testMalloc.deallocate(arrayOfPointers[iterations]);
    }
    assert(testMalloc.myHash.currentSize == 0);
    assert(testMalloc.myHash.capacity == 3200);

    //testing deallocating the same thing twice
    try {
        size_t testTwentyBytesToAdd = 20;
        void *addressOfTwentyBytesAdded = testMalloc.allocate(testTwentyBytesToAdd);
        testMalloc.deallocate(addressOfTwentyBytesAdded);
        testMalloc.deallocate(addressOfTwentyBytesAdded);
    } catch (std::runtime_error &e) {
        std::cout << "failed successfully: " << e.what() << std::endl;
    }
    //testing passing 0 bytes to allocate
    try {
        void *addressOfNullAllocate = testMalloc.allocate(0);
        std::cout << addressOfNullAllocate;
    }catch (std::runtime_error &e){
        std::cout << "failed successfully: " << e.what() << std::endl;
    }
    //testing trying to store the same pointer twice is not allowed
    try {
        testMalloc.myHash.insertNode(addressOfTwentyBytesAdded, 4096);
        testMalloc.myHash.insertNode(addressOfTwentyBytesAdded, 4096);
    }catch (std::runtime_error &e){
        std::cout << "failed successfully: " << e.what() << std::endl;
    }


    std::cout << std::endl;
}


//real malloc (tested using a call to new) runs in about 5-10% the amount of time as my malloc allocate implementation
//real free  call runs in about ~4-6% the amount of time as my malloc's call to deallocate
void runBenchmarks() {
    std::cout << "------------------------In Benchmarks-----------------------" << std::endl;
    std::cout << "(Results time in microseconds)" << std::endl;
    Malloc benchMalloc = Malloc();
    const int BENCHMARKITER = 100000;

    //timing testing comparing my Malloc vs. real malloc

    void *arrayOfPointers[BENCHMARKITER];
    int arrayOfInts[BENCHMARKITER];
    for (int index = 1; index <= BENCHMARKITER; ++index) {
        arrayOfInts[index - 1] = index;
        arrayOfPointers[index - 1] = nullptr;
    }



    //timing for MY malloc implementation
    auto startTime = std::chrono::high_resolution_clock::now();
    for (int iterations = 0; iterations < BENCHMARKITER; iterations++) {
        arrayOfPointers[iterations] = benchMalloc.allocate(arrayOfInts[iterations]);
    }
    auto stopTime = std::chrono::high_resolution_clock::now();
    auto timeElapsed = duration_cast<std::chrono::microseconds>(stopTime - startTime);
    std::cout << BENCHMARKITER << " allocates using MY Malloc took..." << timeElapsed.count() << " μs" << std::endl;



    //timing for real malloc implementation
    testSquid *arrayOfSquids[BENCHMARKITER];
    auto startTimeM = std::chrono::high_resolution_clock::now();
    for (int index = 0; index < BENCHMARKITER; index++) {
        arrayOfSquids[index] = new testSquid();
    }
    auto stopTimeM = std::chrono::high_resolution_clock::now();
    auto timeElapsedM = duration_cast<std::chrono::microseconds>(stopTimeM - startTimeM);
    std::cout << BENCHMARKITER << " allocates using the new took..." << timeElapsedM.count()  << " μs" <<std::endl;

    std::cout << "New ran in " << (timeElapsedM.count()*100) / timeElapsed.count() << "% of the amount of time my Malloc allocate implementation ran" << std::endl;

    //timing testing comparing my deallocate vs. free
    //my deallocate
    auto startTimeDe = std::chrono::high_resolution_clock::now();
    for (int iterations = 0; iterations < BENCHMARKITER; iterations++) {
        benchMalloc.deallocate(arrayOfPointers[iterations]);
    }
    auto stopTimeDe = std::chrono::high_resolution_clock::now();
    auto timeElapsedDe = duration_cast<std::chrono::microseconds>(stopTimeDe - startTimeDe);
    std::cout << BENCHMARKITER << " deallocates using the my Malloc took..." << timeElapsedDe.count()  << " μs" <<std::endl;



    //using free
    auto startTimeFree = std::chrono::high_resolution_clock::now();
    for (int iterations = 0; iterations < BENCHMARKITER; iterations++) {
         free(arrayOfSquids[iterations]);
    }
    auto stopTimeFree = std::chrono::high_resolution_clock::now();
    auto timeElapsedFree = duration_cast<std::chrono::microseconds>(stopTimeFree - startTimeFree);
    std::cout << BENCHMARKITER << " deallocates using free took..." << timeElapsedFree.count()  << " μs" <<std::endl;

    std::cout << "Free ran in " << (timeElapsedFree.count()*100) / timeElapsedDe.count() << "% of the amount of time my Malloc deallocate implementation ran" << std::endl;
    std::cout << "------------------------------------------------------------" << std::endl;
}