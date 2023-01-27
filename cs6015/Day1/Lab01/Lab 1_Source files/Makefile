# Makefile for Lab01 cs6015
#
# To build a target, enter
# make <target>
#
#targets:
# build- builds everything
# clean- deletes everything (.o files, and program)
# run- executes the program  

CXX = C++
CFLAGS = -std=c++11
CXXSOURCE = main.cpp Caps.cpp Clothing.cpp Pants.cpp
HEADERS = Caps.h Clothing.h Pants.h
OBJS = main.o Caps.o Clothing.o Pants.o
COMP = -c
LINK = -o

build: program
program: $(OBJS)
	$(CXX) $(CFLAGS) $(LINK) program $(CXXSOURCE)
main.o: $(HEADERS)
Caps.o: $(HEADERS)
Clothing.o: $(HEADERS)
Pants.o: $(HEADERS) 
run: program
	./program
printM: 
	echo "CXX = C++"
	echo "CFLAGS = -std=c++11"
	echo "CXXSOURCE = main.cpp Caps.cpp Clothing.cpp Pants.cpp"
	echo "HEADERS = Caps.h Clothing.h Pants.h"
	echo "OBJS = main.o Caps.o Clothing.o Pants.o"
	echo "COMP = -c"
	echo "LINK = -o" 
clean:
	rm -rf $(OBJS) program
.PHONY: clean run build printM
