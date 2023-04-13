//
// Created by Whitney Kenner on 3/9/23.
//

#ifndef MSDSCRIPT_VAL_H
#define MSDSCRIPT_VAL_H
#include <iostream>
#include <string>
#include "pointer.h"
class Expr;
class Env;

CLASS(Val) {

public:
    virtual ~Val() { };
    virtual std::string toString() = 0;
    virtual bool equals(PTR(Val)otherVal)= 0;
    virtual PTR(Val) multWith(PTR(Val)otherVal) = 0;
    virtual PTR(Val) addTo (PTR(Val)otherVal) = 0;
    virtual bool isTrue() = 0;
    virtual PTR(Val)call(PTR(Val)actual_arg) = 0;
};

class NumVal : public Val{
public:
    int val; //!< stored int value of a NumVal
public:
    NumVal(int value);
    std::string toString() override;
    PTR(Val) addTo (PTR(Val)otherVal) override;
    PTR(Val) multWith(PTR(Val)otherVal) override;
    bool equals(PTR(Val)otherVal) override;
    bool isTrue() override;
    PTR(Val)call(PTR(Val)actual_arg) override;

};

class BoolVal : public Val{
public:
    std::string boolString;
public:
    BoolVal(bool inputBool);
    std::string toString() override;
    PTR(Val) addTo (PTR(Val)otherVal) override;
    PTR(Val) multWith(PTR(Val)otherVal) override;
    bool equals(PTR(Val)otherVal) override;
    bool isTrue() override;
    PTR(Val)call(PTR(Val)actual_arg) override;


};

class FunVal : public Val{
public:
    std::string formalArgVal;
    PTR(Expr) bodyVal;
    PTR(Env) funEnv;
public:
    FunVal(std::string inputArg, PTR(Expr)inputBody, PTR(Env) env);
    std::string toString() override;
    PTR(Val) addTo (PTR(Val)otherVal) override;
    PTR(Val) multWith(PTR(Val)otherVal) override;
    bool equals(PTR(Val)otherVal) override;
    bool isTrue() override;
    PTR(Val)call(PTR(Val)actual_arg) override;
};
#endif //MSDSCRIPT_VAL_H
