//
// Created by Whitney Kenner on 4/6/23.
//

#pragma once

#include <iostream>
#include <mutex>

template<typename T>
class ConcurrentQueue {
private:
    struct Node {
        T data;
        Node *next;
    };
    std::mutex mutex;
    std::condition_variable c;
    Node *head;
    Node *tail;
    int size_;

public:
    ConcurrentQueue()
            : head(new Node{T{}, nullptr}), size_(0) {
        tail = head;
    }


    void enqueue(const T &x) {
        Node *newNode = new Node{x, nullptr};
        if (newNode == nullptr) {
            throw std::runtime_error("invalid new node initialization. New node = nullptr");
        }
        //critical section
        {
            std::unique_lock<std::mutex> tLock(mutex);
            tail->next = newNode;
            tail = newNode;
            size_++;
        }
    }

    //dequeue(T* ret) removes a node from the head of the linked list,
    // and returns the data in the variable ret. If the queue is empty,
    // dequeue returns false. If an element was dequeued successfully, dequeue returns true.
    bool dequeue(T *ret) {
        {
            //above bracket scope for unique lock
            std::unique_lock<std::mutex> hLock(mutex);
            Node *toDelete = head->next;
            if (toDelete == nullptr || size_ == 0) {
                return false;
            }
            *ret = toDelete->data;
            if (toDelete->next == nullptr) {
                tail = head;
            }
            head->next = toDelete->next;
            delete toDelete;
            size_--;
            return true;
        }

    }

    ~ConcurrentQueue() {

        while (head) {
            Node *temp = head->next;
            delete head;
            head = temp;
        }
    }

    int size() const { return size_; }

};