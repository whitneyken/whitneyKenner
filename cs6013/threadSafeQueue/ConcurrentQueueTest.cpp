//
// Created by Whitney Kenner on 4/7/23.
//
#include "ConcurrentQueue.h"
#include <thread>
#include <vector>

static ConcurrentQueue<int> queue;

//driver enqueue function to call enqueue within queue class
static void enQueue(int iterations) {
    for (int i = 0; i < iterations; ++i) {
        queue.enqueue(i);
    }
}
//driver dequeue function to call dequeue within queue class
static void deQueue(int iterations) {
    int removed;
    for (int i = 0; i < iterations; ++i) {
        queue.dequeue(&removed);
    }
}

bool testQueue(int num_producers, int num_consumers, int num_ints) {


    //Create an std::vector of std::threads & reserve space in this vector for all producer and consumer threads
    std::vector<std::thread> allThreads(num_consumers + num_producers);

    for (int i = 0; i < num_producers; ++i) {
        std::thread newThread(enQueue, num_ints);
        allThreads.push_back(std::move(newThread));
    }

    for (int i = 0; i < num_consumers; ++i) {
        std::thread newThread(deQueue, num_ints);
        allThreads.push_back(std::move(newThread));
    }
    //Wait for all threads to join.
    for (int i = 0; i < allThreads.size(); ++i) {
        if (allThreads[i].joinable())
            allThreads.at(i).join();
    }
    int finalSize = (num_producers - num_consumers) * num_ints;
    if (finalSize < 0){
        finalSize = 0;
    }

    //Returns 1 if the number of elements in the queue matches (num_producers - num_consumers)*num_ints,
    // 0 otherwise. This basically tests if we did the right number of inserts and deletes into the queue.
    return (finalSize == queue.size());
}