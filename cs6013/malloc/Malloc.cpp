//
// Created by Whitney Kenner on 3/14/23.
//
#include "HashMap.h"
#include "Malloc.h"


//4 kb const page size
const int PAGESIZE = 4096;
const int INITIALCAPACITY = 100;

Malloc::Malloc() {
    myHash = HashMap(INITIALCAPACITY);
}
//free up memory stored in member hashMap
Malloc::~Malloc() {
    for (int i = 0; i < myHash.capacity; ++i) {
        if (myHash.array[i].size != 0 && myHash.array[i].size != -1){
            if (munmap(myHash.array[i].address, myHash.array[i].size) == -1){
                std::cerr << "failed to clear memory in DESTRUCTOR" << std::endl;
                exit(1);
            }
        }
    }
    //clear out memory mapped for the hashmap itself
    if (munmap(myHash.array, myHash.capacity * sizeof(HashNode)) == -1){
        perror("failed to munmap hashMap within Malloc class");
        exit(1);
    }
    myHash.array = nullptr;
}
//takes the number of bytes and returns the bytes needed rounded up to the nearest page size
size_t Malloc::getPagesToAllocate(size_t bytes) {
    //-1 for the edge case in which the size required is exactly a page size
    return ((bytes + (PAGESIZE - 1)) / PAGESIZE) * PAGESIZE;
}

//allocates bytes by calling mmap and returns a pointer to the address in memory
void *Malloc::allocate(size_t bytesToAllocate) {
    if (bytesToAllocate <= 0){
        throw std::runtime_error("invalid number of bytes passed to allocate");
    }
    size_t pages = getPagesToAllocate(bytesToAllocate);
    void *addressPointer = mmap(NULL, pages, PROT_READ | PROT_WRITE, MAP_ANONYMOUS | MAP_PRIVATE, 0, 0);
    if (addressPointer == MAP_FAILED){
        perror("mmap failed");
        exit(1);
    }
    if (!(myHash.insertNode(addressPointer, pages))) {
        std::cerr << "failed to store node in HashMap" << std::endl;
        exit(1);
    }
    return addressPointer;
}

//deallocate takes in a pointer to the address of memory to be deallocated, uses the member variable hash map to
//retrieve the size of memory size allocated and then call munmap() syscall to deallocate
void Malloc::deallocate(void *ptr) {
    size_t size = myHash.deleteNode(ptr);
    if (size == -1){
        throw std::runtime_error("failed to delete node");
    }
    if (munmap(ptr, size) == -1){
        perror("failed to munmap");
        exit(1);
    }

}



