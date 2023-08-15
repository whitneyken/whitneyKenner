#pragma once
#include <iostream>

template <typename T>
class SerialQueue{
private:
  struct Node{
	T data;
	Node* next;
  };

  Node* head;
  Node* tail;
  int size_;

public:
  SerialQueue()
	:head(new Node{T{}, nullptr}), size_(0)
  {
	tail = head;
  }

//serial enqueuing
  void enqueue(const T& x){
      Node *newNode = new Node{x, nullptr};
      if (newNode == nullptr){
          throw std::runtime_error("invalid new node initialization. New node = nullptr");
      }
      tail->next = newNode;
      tail = newNode;

      size_++;
  }
  //dequeue(T* ret) removes a node from the head of the linked list,
  // and returns the data in the variable ret. If the queue is empty,
  // dequeue returns false. If an element was dequeued successfully, dequeue returns true.
  bool dequeue(T* ret){
      if (size_ == 0){
          return false;
      } else{
          Node *toDelete = head->next;
          if (toDelete == nullptr){
              return false;
          }
          *ret = toDelete->data;
          head->next = toDelete->next;
          delete toDelete;
          size_ --;
          return true;
      }
  }

  //destructor
  ~SerialQueue(){

	while(head){
	  Node* temp = head->next;
	  delete head;
	  head = temp;
	}
  }

  int size() const{ return size_;}  
};
