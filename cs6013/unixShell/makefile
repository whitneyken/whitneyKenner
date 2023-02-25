CXX = c++
CFLAGS = -std=c++14
COMP = -c
LINK = -o

build: shell
shell: main.o shelpers.o
	$(CXX) $(CFLAGS) -o shell main.o shelpers.o -lreadline
main.o: main.cpp
	$(CXX) $(CFLAGS) $(COMP) main.cpp
shelpers.o: shelpers.cpp shelpers.hpp
	$(CXX) $(CFLAGS) $(COMP) shelpers.cpp

clean:
	rm -rf main.o shelpers.o shell
