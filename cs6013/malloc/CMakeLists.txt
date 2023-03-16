cmake_minimum_required(VERSION 3.24)
project(malloc)

set(CMAKE_CXX_STANDARD 17)

add_executable(malloc main.cpp Malloc.cpp Malloc.h HashMap.cpp HashMap.h)
