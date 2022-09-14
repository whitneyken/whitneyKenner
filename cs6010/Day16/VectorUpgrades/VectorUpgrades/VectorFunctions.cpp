//
//  VectorFunctions.cpp
//  VectorUpgrades
//
//  Created by Whitney Kenner on 9/14/22.
//


#include "vectorFunctions.hpp"
//This is my constructor
MyVector::MyVector (size_t initialCapacity){
    capacity = initialCapacity;
    size = 0;
    data = new double[initialCapacity];
}


void MyVector::PrintVector () const{
    for (int i = 0; i < this->size; i++) {
        std::cout << this->data[i] << " ";
    }
    std::cout << std::endl;
}
//This is my deconstructor
MyVector::~MyVector (){
    delete [] this->data;
    this->data = nullptr;
}

//this will work like the std::vector push back
//first it will check if the capacity is equal to the size
//if it is equal, call the expand capacity function
//then push back
void MyVector::PushBack (int input){
    if (this->capacity == this->size) {
        this->GrowMyVector();
    }
    this->data[this->size] = input;
    this->size += 1;
}
//this function will work like pop back and remove the last element
//this happens by not truly removing the element, but by reducing size
void MyVector::PopBack (){
    this->size -= 1;
}
//Will work like [] does for std::vector
//First we make sure index is within bounds
//Then get value and return
int MyVector::Get (int index){
    int valueAtIndex = -1;
    if (index > (this->size + 1)) {
        std::cout << "\n You have entered an invalid index \n";
        exit(1);
    }
    valueAtIndex = this->data[index];
    return valueAtIndex;
}
//This function will change the element at the input index location to the new value
//If the index is higher than the size, it will exit the program
void MyVector::Set (int index, int newValue){
    if (index > (this->size + 1)) {
        std::cout << "\n You have entered an invalid index \n";
        exit(2);
    }
    this->data[index] = newValue;
}

//this function grows the vector if the size is equal to the capacity. It passes by reference so the changes are made to the vector itself
void MyVector::GrowMyVector (){
    if (this->size == this->capacity) {
        this->capacity *= 2;
        double* tempArray = new double[this->capacity];
        for (int i = 0; i < this->size; i++) {
            tempArray[i] = this->data[i];
        }
        delete [] this->data;
        this->data = tempArray;
        tempArray = nullptr;
    }
}
int MyVector::GetSize (){
    int size = this->size;
    return size;
}

int MyVector::GetCapacity (){
    int capacity = this->capacity;
    return capacity;
}


//MY TEST CASES
void RunTests (){
    MyVector testvec1(6);
    testvec1.PushBack(4);
    testvec1.PushBack(9);
    testvec1.PushBack(12);
    testvec1.PushBack(1);
    
    //These asserts test that my constructor and the functions Get, GetSize, GetCapacity, and PushBack work
    assert((testvec1.Get(0)) == 4);
    assert(testvec1.GetSize() == 4);
    assert(testvec1.GetCapacity() == 6);
    
    testvec1.PushBack(4);
    testvec1.PushBack(9);
    testvec1.PushBack(12);
    testvec1.PushBack(1);
    
    //These asserts test that GrowMyVector function is called when the vector is pushbacked beyond capacity
    assert((testvec1.Get(4)) == 4);
    assert(testvec1.GetSize() == 8);
    assert(testvec1.GetCapacity() == 12);
    
    //This assert will verify that the PopBack function works and the size is adjusted accordingly
    testvec1.PopBack();
    
    assert(testvec1.GetSize() == 7);
    
    //The assert below will verify that when a vector uses the overloaded = operator, that it appropriately adjusts a vectors size, capacity and data values.
    MyVector testvec2(5);
    
    testvec2.PushBack(3);
    testvec2.PushBack(3);
    testvec2.PushBack(3);
    testvec2.PushBack(3);
    testvec2.PushBack(3);
    testvec2.PushBack(3);
    testvec2.PushBack(3);
    
    
    testvec2 = testvec1;
    assert(testvec1.Get(2) == testvec2.Get(2));
    assert(testvec1.GetSize() == testvec2.GetSize());
    assert(testvec1.GetCapacity() == testvec2.GetCapacity());
    
    //This will assert that my copy constructor works
    
    MyVector testvec3 (testvec2);
    
    assert(testvec2.Get(3) == testvec3.Get(3));
    assert(testvec2.GetSize() == testvec3.GetSize());
    assert(testvec2.GetCapacity() == testvec3.GetCapacity());
}











//This is my first operator overload so that you can use the = sign to assign the value of 1 vector to another
MyVector& MyVector::operator = (const MyVector& vect2){
    if (*data == *vect2.data) {
        return *this;
    }
    capacity = vect2.capacity;
    data = new double[capacity];
    //Should this be vect2.size or just size????
    for (size_t i = 0; i < vect2.size; i++) {
        data[i] = vect2.data[i];
    }
    return *this;
}

//This is the copy constructor

MyVector::MyVector(const MyVector& vect2){
    capacity = vect2.capacity;
    size = vect2.size;
    data = new double[capacity];
    for (size_t i = 0; i < vect2.size; i++) {
        data[i] = vect2.data[i];
    }
}
