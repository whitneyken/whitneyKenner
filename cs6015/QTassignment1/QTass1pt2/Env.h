//
// Created by Whitney Kenner on 3/30/23.
//

#ifndef MSDSCRIPT_ENV_H
#define MSDSCRIPT_ENV_H

#include "pointer.h"
#include <string>

class Val;
class Expr;


CLASS(Env) {
public:
    static PTR(Env) empty;
    virtual PTR(Val) lookup(std::string findName) = 0;
};

class EmptyEnv : public Env{
public:
    EmptyEnv();
    PTR(Val) lookup(std::string findName) override;
};
class ExtendedEnv : public Env{
public:
    std::string name;
    PTR(Val) val;
    PTR(Env) rest;
public:
    ExtendedEnv(std::string inputName, PTR(Val) rhs, PTR(Env) env);
    PTR(Val) lookup(std::string findName) override;
};

#endif //MSDSCRIPT_ENV_H
