//
//  VectorFunctions.cpp
//  TemplatizeYourVector
//
//  Created by Whitney Kenner on 9/15/22.
//

#include "VectorFunctions.hpp"
#include <iostream>


//MY TEST CASES
void RunTests (){
    MyVector<int> testvec1(6);
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
    MyVector<int> testvec2(5);
    
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
    
    //These assert swill verify that the [] operator works appropriately and does not change the original of a copied vector
    testvec3[3] = 40;
    assert(testvec3.Get(3) == 40);
    assert(testvec3.Get(3) != testvec2.Get(3));
    
    //To assert my == and += operator overloads work
    MyVector testvec4 (testvec3);
    
    assert(testvec4 == testvec3);
    assert(testvec1 != testvec4);
    
    //asserts for <, >, <=, >=
    
    assert(testvec2 < testvec1);
    assert(testvec1 > testvec2);
    assert(testvec4 <= testvec3);
    assert(testvec2 <= testvec1);
    assert(testvec1 >= testvec2);
    assert(testvec2 >= testvec1);
    
    
    //Now that the class MyVector has been switched over to be template typename T, the following tests will be to verify that the methods work on different types
    
    MyVector<char> charVector1(6);
    MyVector<char> charVector2 = charVector1;
    
    assert(charVector1 == charVector2);
    
    charVector1.PushBack('g');
    charVector1.PushBack('t');
    charVector1.PushBack('f');
    charVector1.PushBack('p');
    
    assert(charVector1.Get(3) == 'p');
    
    charVector1.Set(3, 'r');
    
    assert(charVector1.Get(3) == 'r');
    assert(charVector1 != charVector2);
    
    charVector1.PopBack();
    
    assert(charVector1.GetSize() == 3);
    assert(charVector1.GetCapacity() == 6);
    
    charVector1.PushBack('g');
    charVector1.PushBack('t');
    charVector1.PushBack('f');
    charVector1.PushBack('p');
    
    assert(charVector1.GetCapacity() == 12);
    
    MyVector<char> charVector3(5);
    charVector3.PushBack('a');
    charVector3.PushBack('b');
    charVector3.PushBack('c');
    charVector3.PushBack('d');
    charVector3.PushBack('e');
    
    MyVector<char> charVector4(5);
    charVector4.PushBack('a');
    charVector4.PushBack('b');
    charVector4.PushBack('c');
    charVector4.PushBack('c');
    charVector4.PushBack('e');
    
    assert(charVector3 > charVector4);
    
    charVector4.Set(3, 'd');
    
    assert(charVector3 == charVector4);
}
