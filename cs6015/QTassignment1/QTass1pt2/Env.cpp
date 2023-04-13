//
// Created by Whitney Kenner on 3/30/23.
//


#include "Env.h"
EmptyEnv::EmptyEnv() {
}


PTR(Val) EmptyEnv::lookup(std::string findName){
    throw std::runtime_error("free variable: "+ findName);
}
ExtendedEnv::ExtendedEnv(std::string inputName, PTR(Val) rhs, PTR(Env) env){
    name = inputName;
    val = rhs;
    rest = env;
}

PTR(Val) ExtendedEnv::lookup(std::string findName) {
    if (findName == name){
        return val;
    } else{
        return rest->lookup(findName);
    }
}