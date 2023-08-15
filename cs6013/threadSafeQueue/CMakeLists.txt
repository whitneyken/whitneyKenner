cmake_minimum_required(VERSION 3.24)
project(threadSafeQueue)

set(CMAKE_CXX_STANDARD 17)

add_executable(threadSafeQueue main.cpp SerialQueue.hpp SerialQueueTests.cpp ConcurrentQueue.h ConcurrentQueueTest.cpp)
