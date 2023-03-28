
#makefile for cs6015 msd script project
#
#to build a target, enter
# make <target>
#
#targets:
# build- builds everything
# clean- deletes everything (.o files and executable)
# run- executes the program


CXX = c++
CFLAGS = -std=c++14
CXXSOURCE = main.cpp cmdline.cpp Expr.cpp Tests.cpp Parse.cpp
HEADERS = cmdline.hpp Expr.hpp catch.h Parse.h
OBJS = main.o cmdline.o Expr.o Tests.o Parse.o
COMP = -c
LINK = -o
LIBDIR = ../Library_MSDscript

build: msdscript
msdscript: main.cpp
	$(CXX) $(CFLAGS) $(LINK) msdscript -L$(LIBDIR) -l_msdscript
	


printM: 
	echo "CXX = C++"
	echo "CFLAGS = -std=c++11"
	echo "CXXSOURCE = main.cpp cmdline.cpp Expr.cpp"
	echo "HEADERS = cmdline.hpp Expr.hpp"
	echo "OBJS = main.o cmdline.o Expr.o"
	echo "COMP = -c"
	echo "LINK = -o"
	echo "LIBDIR = directory location of msdscript library"

runTests: test_msdscipt.o exec.o
	$(CXX) $(CFLAGS) $(LINK) testRandom test_msdscript.o exec.o
test_msdscipt.o: test_msdscript.cpp
	$(CXX) $(CFLAGS) $(COMP) test_msdscript.cpp
exec.o: exec.cpp exec.h
	$(CXX) $(CFLAGS) $(COMP) exec.cpp



clean:
	rm -rf $(OBJS) msdscript testRandom exec.o test_msdscript.o
	
.PHONY: clean run build printM test
.PHONY: test
test: msdscript
	./msdscript --test
.PHONY: doc
doc:
	cd documentation && doxygen

