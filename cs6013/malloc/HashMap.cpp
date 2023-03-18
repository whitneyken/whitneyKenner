//
// Created by Whitney Kenner on 3/15/23.
//

#include "HashMap.h"
const int TOMBSTONE = -1;

HashNode::HashNode(void *inputAdd, size_t inputSize) {
    this->address = inputAdd;
    this->size = inputSize;
}

//default Hashmap constructor if no capacity is passed -> intentionally set to very small value for debugging purposes,
//not used by Malloc
HashMap::HashMap() {
    HashMap(10);
}

//constructor that is called by Malloc
HashMap::HashMap(int initialCapacity) {
    this->capacity = initialCapacity;
    this->currentSize = 0;
    this->array = static_cast<HashNode *>(mmap(nullptr, capacity * sizeof(HashNode), PROT_READ | PROT_WRITE,
                                                                                 MAP_ANONYMOUS | MAP_PRIVATE, 0, 0));
    if (array == MAP_FAILED) {
        perror("mmap failed");
        exit(EXIT_FAILURE);
    }
    for (int index = 0; index < capacity; ++index) {
        array[index].address = nullptr;
        array[index].size = 0;
    }
}

//destructor-> memory inside goes out of scope if it is not stored anywhere external
HashMap::~HashMap() {
    this->array = nullptr;
}


//Hash Code from https://gist.github.com/badboy/6267743
size_t HashMap::hashCode(long address) {
    address = (~address) + (address << 18); // key = (key << 18) - key - 1;
    address = address ^ (address >> 31);
    address = address * 21; // key = (key + (key << 2)) + (key << 4);
    address = address ^ (address >> 11);
    address = address + (address << 6);
    address = address ^ (address >> 22);
    return (size_t) abs(address) % capacity;
}

//takes in address pointer and sizeInPages, stores this information at a specific location based on the hashcode and
// returns a bool indicating whether the insert was successful or not
bool HashMap::insertNode(void *addressPointer, size_t sizeInPages) {
    //double size if size has reached 1/2 capacity
    if (this->currentSize >= this->capacity / 2) {
        growHashMap();
    }
    //get hashcode and attempt to store
    int hashIndex = hashCode(reinterpret_cast<long>(addressPointer));
    while (array[hashIndex].address != nullptr && array[hashIndex].size != -1) {
        if (array[hashIndex].address == addressPointer) {
            throw std::runtime_error("address already in use");
        }
        hashIndex++;
        hashIndex %= capacity;
    }
    if (array[hashIndex].address == nullptr || array[hashIndex].size == TOMBSTONE) {
        this->currentSize++;
        array[hashIndex].address = addressPointer;
        array[hashIndex].size = sizeInPages;
        return true;
    }
    return false;


}

size_t HashMap::deleteNode(void *addressPointer) {
    size_t hashIndex = hashCode(reinterpret_cast<long>(addressPointer));
    // probe for passed in address using hashcode and linear probing
    while (array[hashIndex].address != nullptr) {
        if (array[hashIndex].address == addressPointer) {
            //address and size set to (void*) -1 and -1 respectively indicate a lazy delete
            array[hashIndex].address = (void *) -1;
            int numPages = array[hashIndex].size;
            array[hashIndex].size = TOMBSTONE;
            currentSize--;
            return numPages;
        }
        hashIndex++;
        hashIndex %= capacity;
    }
    return -1;
}

//doubles HashMap capacity and inserts all nodes from former HashMap
void HashMap::growHashMap() {
    HashMap twiceCapacityHashMap = HashMap(this->capacity * 2);
    for (int index = 0; index < capacity; ++index) {
        //insert only if a node that was inserted AND not lazy deleted
        if (this->array[index].size != TOMBSTONE && this->array[index].size != 0) {
            if (!(twiceCapacityHashMap.insertNode(this->array[index].address, this->array[index].size))) {
                std::cerr << "Failure to add node when growing HashMap" << std::endl;
                exit(1);
            }
        }
    }
    std::swap(twiceCapacityHashMap.array, this->array);
    std::swap(twiceCapacityHashMap.capacity, this->capacity);
    if (munmap(twiceCapacityHashMap.array, sizeof(HashNode) * twiceCapacityHashMap.capacity) == -1){
        perror("failed to munmap");
        exit(1);
    }
}

