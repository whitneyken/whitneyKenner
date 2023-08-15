
#include <iostream>
#include <thread>
#include <vector>
#include <omp.h>
#include <chrono>
#include <mutex>

int globalSum;
int globalOmpSum;

//struct for returning multiple items
template <typename T>
struct threadingData{
    long long timeElapsed;
    T endSum;
};

//built in openMP reduction
template <typename T>
threadingData<T> parallel_sum_omp_builtin(T a[], size_t N, size_t num_threads){
    int localSum;
    auto startTime = std::chrono::high_resolution_clock::now();
    omp_set_num_threads(num_threads);
#pragma omp parallel for reduction(+:localSum)
    for (int i = 0; i < N; ++i) {
        localSum += a[i];
    }
    auto endTime = std::chrono::high_resolution_clock::now();
    auto timePassed = std::chrono::duration_cast<std::chrono::nanoseconds>(endTime - startTime);
    threadingData <T>threadingResults;
    threadingResults.endSum = localSum;
    threadingResults.timeElapsed = timePassed.count();
    return threadingResults;
}

//custom openMP reduction
template <typename T>
threadingData<T> parallel_sum_omp1(T a[], size_t N, size_t num_threads){
    omp_set_num_threads(num_threads);
    auto startTime = std::chrono::high_resolution_clock::now();
#pragma omp parallel
    {
        int localSum= 0;
#pragma omp for
        for (int i = 0; i < N; ++i) {
            localSum += a[i];
        }
#pragma omp atomic
        globalOmpSum += localSum;
    }
#pragma only one
    auto endTime = std::chrono::high_resolution_clock::now();
    auto timePassed = std::chrono::duration_cast<std::chrono::nanoseconds>(endTime - startTime);
    threadingData <T>threadingResults;
    threadingResults.timeElapsed = timePassed.count();
    threadingResults.endSum = globalOmpSum;
    return threadingResults;
}


std::mutex lock;
template<typename T>
void threadingPortion(T a[], int ID, size_t N, size_t numThreads){
    int localSum= 0;
    for (int i = ID; i < N; i +=numThreads) {
        localSum += a[i];
    }
    lock.lock();
    globalSum += localSum;
    lock.unlock();
}

//compute the sum of the elements of a in parallel using num_threads threads and return two
// values: the sum of the array elements, and the time taken to calculate the sum.
template<typename T>
threadingData<T> parallel_sum_std(T a[], size_t N, size_t num_threads) {
    auto startTime = std::chrono::high_resolution_clock::now();
    std::vector<std::thread> allThreads;
    for (int ID = 0; ID < num_threads; ++ID) {
        allThreads.emplace_back(threadingPortion<T>, a, ID, N, num_threads);
    }
    for (int i = 0; i < num_threads; ++i) {
        if (allThreads[i].joinable()){
            allThreads[i].join();
        }
    }
    auto endTime = std::chrono::high_resolution_clock::now();
    auto timePassed = std::chrono::duration_cast<std::chrono::nanoseconds>(endTime - startTime);
    threadingData <T>threadingResults;
    threadingResults.timeElapsed = timePassed.count();
    threadingResults.endSum = globalSum;
    return threadingResults;
}

int main() {
    size_t sizeOfArray = 10000;
    int array[sizeOfArray];
    //size_t numThreads = 9;

//strong scaling - fixed size input with threads increasing from 1-16 and a range of sizes
    for (int i = 0; i < sizeOfArray; i++) {
        array[i] = 1;
    }
    for (int numThreads = 1; numThreads < 17; ++numThreads) {


        threadingData<int> results = parallel_sum_std(array, sizeOfArray, numThreads);


        std::cout << "the total sum is: " << results.endSum << std::endl;
        std::cout << "the total time elapsed is: " << results.timeElapsed << std::endl;
        globalSum = 0;
    }

//    for (int numThreads = 1; numThreads < 17; ++numThreads) {
        // threadingData<int> openResults = parallel_sum_omp1(array, sizeOfArray, numThreads);
        // std::cout << "The total sum from the openMP is: " << openResults.endSum << std::endl;
        // std::cout << "the total time elapsed from openMP is: " << openResults.timeElapsed << std::endl;
//        globalOmpSum = 0;
//    }

//    for (int numThreads = 1; numThreads < 17; ++numThreads) {
        // threadingData<int> builtResults = parallel_sum_omp_builtin(array, sizeOfArray, numThreads);
        // std::cout << "The total sum from the openMP builtin is: " << builtResults.endSum << std::endl;
        // std::cout << "the total time elapsed from openMP builtin is: " << builtResults.timeElapsed << std::endl;

//    }

    return 0;
}
