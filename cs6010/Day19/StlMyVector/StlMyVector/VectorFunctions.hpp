//
//  VectorFunctions.hpp
//  StlMyVector
//
//  Created by Whitney Kenner on 9/20/22.
//

#ifndef VectorFunctions_hpp
#define VectorFunctions_hpp

#include <iostream>
#include <cstdlib>
#include <numeric>

//This is our vector class
//template name T to make it so we can take in different variables
template<typename T>
class MyVector{
private:
    T* data;
    size_t capacity;
    size_t size;
    
public:
    //This is my constructor
    MyVector (size_t initialCapacity);
    //copy constructor
    MyVector(const MyVector<T>& vect2);
    //My destructor
    ~MyVector ();
    
    //These are my methods
    int Get (int index) const;
    
    void Set (T index, T newValue);
    
    void PushBack (T input);
    
    void PopBack ();
    
    void GrowMyVector ();
    
    void PrintVector () const;
    
    int GetSize ();
    
    int GetCapacity ();
    
    MyVector<T>& operator = (const MyVector<T>& vect2);
    
    T& operator [] (int index);
    
    bool operator == (const MyVector<T>& vect2);
    
    bool operator != (const MyVector<T>& vect2);
    
    bool operator <(const MyVector<T>& vect2);
    
    bool operator >(const MyVector<T>& vect2);
    
    bool operator <=(const MyVector<T>& vect2);
    
    bool operator >=(const MyVector<T>& vect2);
    
    T* begin (){return data;};
    
    T* end (){return data + size;};
    
    const T* begin () const {return data;};
    
    const T* end () const {return data + size;};
};

//This is my constructor
template <typename T>
MyVector<T>::MyVector (size_t initialCapacity){
    capacity = initialCapacity;
    size = 0;
    data = new T[initialCapacity];
}

//This is the copy constructor
template <typename T>
MyVector<T>::MyVector(const MyVector<T>& vect2){
    capacity = vect2.capacity;
    size = vect2.size;
    data = new T[capacity];
    for (size_t i = 0; i < vect2.size; i++) {
        data[i] = vect2.data[i];
    }
}

//This is my destructor
template <typename T>
MyVector<T>::~MyVector (){
    delete [] this->data;
    this->data = nullptr;
}

//Will work like [] does for std::vector
//First we make sure index is within bounds
//Then get value and return
template <typename T>
int MyVector<T>::Get (int index) const{
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
template <typename T>
void MyVector<T>::Set (T index, T newValue){
    if (index >= (this->size)) {
        std::cout << "\n You have entered an invalid index \n";
        exit(2);
    }
    this->data[index] = newValue;
}

//this will work like the std::vector push back
//first it will check if the capacity is equal to the size
//if it is equal, call the expand capacity function
//then push back

template <typename T>
void MyVector<T>::PushBack (T input){
    if (this->capacity == this->size) {
        this->GrowMyVector();
    }
    this->data[this->size] = input;
    this->size += 1;
}

//this function will work like pop back and remove the last element
//this happens by not truly removing the element, but by reducing size
template <typename T>
void MyVector<T>::PopBack (){
    this->size -= 1;
}

//this function grows the vector if the size is equal to the capacity. It passes by reference so the changes are made to the vector itself
template <typename T>
void MyVector<T>::GrowMyVector (){
    if (this->size == this->capacity) {
        this->capacity *= 2;
        T* tempArray = new T [this->capacity];
        for (int i = 0; i < this->size; i++) {
            tempArray[i] = this->data[i];
        }
        delete [] this->data;
        this->data = tempArray;
        tempArray = nullptr;
    }
}

template <typename T>
void MyVector<T>::PrintVector () const{
    for (int i = 0; i < this->size; i++) {
        std::cout << this->data[i] << " ";
    }
    std::cout << std::endl;
}

template <typename T>
int MyVector<T>::GetSize (){
    int size = this->size;
    return size;
}

template <typename T>
int MyVector<T>::GetCapacity (){
    int capacity = this->capacity;
    return capacity;
}

//This is my first operator overload so that you can use the = sign to assign the value of 1 vector to another
template <typename T>
MyVector<T>& MyVector<T>::operator = (const MyVector<T>& vect2){
    if (*data == *vect2.data) {
        return *this;
    }
    capacity = vect2.capacity;
    data = new T [capacity];
    //Should this be vect2.size or just size????
    for (size_t i = 0; i < vect2.size; i++) {
        data[i] = vect2.data[i];
    }
    return *this;
}

//operator overload for []. Works like [] for std::vector
template <typename T>
T& MyVector<T>::operator [] (int index){
    return this->data[index];
}

template <typename T>
bool MyVector<T>::operator == (const MyVector<T>& vect2){
    if (size != vect2.size) {
        return false;
    }
    for (size_t i = 0; i < size; i++) {
        if (data[i] != vect2.data[i]) {
            return false;
        }
    }
    
    return true;
}

//This method uses the above == overloaded operator to check if something is not equal
template <typename T>
bool MyVector<T>::operator != (const MyVector<T>& vect2){
    return !(*this == vect2);
}

//Writing an operator overload for <
template <typename T>
bool MyVector<T>::operator <(const MyVector<T>& vect2){
    if (size != vect2.size) {
        std::cout << "Invalid comparison '<' \n";
        exit(1);
    }
    for (size_t i = 0; i < size; i++) {
        if (data[i] > vect2.data[i]) {
            return false;
        }
    }
    return true;
}

//Writing an operator overload for >
template <typename T>
bool MyVector<T>::operator >(const MyVector<T>& vect2){
    if (size != vect2.size) {
        std::cout << "Invalid comparison '>' \n";
        exit(1);
    }
    for (size_t i = 0; i < size; i++) {
        if (data[i] < vect2.data[i]) {
            return false;
        }
    }
    return true;
}

//Writing an operator overload for <=
template <typename T>
bool MyVector<T>::operator <=(const MyVector<T>& vect2){
    if (*this == vect2 || *this < vect2) {
        return true;
    }
    return false;
}

//Writing an operator overload for >=
template <typename T>
bool MyVector<T>::operator >=(const MyVector<T>& vect2){
    if (*this == vect2 || *this > vect2) {
        return true;
    }
    return false;
}


bool isEven (int input);

void RunTests ();




#endif /* VectorFunctions_hpp */

