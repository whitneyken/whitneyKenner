//
// Created by Whitney Kenner on 4/6/23.
//
#include "SerialQueue.hpp"
#include <iostream>

void serialRunTests() {
    //Be sure to include tests for both static and dynamic allocation, enqueue, dequeue, and dynamic deallocation.
    SerialQueue<int> staticSerialQueue = SerialQueue<int>();

    assert(staticSerialQueue.size() == 0);
    std::cout << "Static allocation: " << std::endl;
    for (int eIndex = 0; eIndex < 500; eIndex++) {
        staticSerialQueue.enqueue(eIndex);
    }

    assert(staticSerialQueue.size() == 500);
    for (int dIndex = 0; dIndex < 500; dIndex++) {
        int pointerToInt = 0;
        assert(staticSerialQueue.dequeue(&pointerToInt) == true);
        assert(pointerToInt == dIndex);
    }



    //dynamic serial queue
    std::cout << "Dynamic allocation: " << std::endl;
   auto dynamicSerialQueue = new SerialQueue<int>();

    for (int eIndex = 0; eIndex < 500; eIndex++) {
        dynamicSerialQueue->enqueue(eIndex);
    }

    assert(dynamicSerialQueue->size() == 500);
    for (int dIndex = 0; dIndex < 500; dIndex++) {
        int pointerToInt = 0;
        assert(dynamicSerialQueue->dequeue(&pointerToInt) == true);
        assert(pointerToInt == dIndex);
    }
    delete dynamicSerialQueue;

}
