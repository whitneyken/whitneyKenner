//
// Created by Whitney Kenner on 3/9/23.
//

#include "Val.h"
#include "Expr.hpp"
#include "Env.h"
NumVal::NumVal (int value){
    val = value;
}

PTR(Val)NumVal::addTo (PTR(Val)otherVal){
    PTR(NumVal)otherNum = CAST(NumVal)(otherVal);
    if (otherNum == nullptr){
        throw std::runtime_error("cannot add a non number value to a number value");
    } else{
        if (((long)val + (long)otherNum->val) > INT_MAX || ((long)val + (long)otherNum->val < INT_MIN)){
            throw std::runtime_error("Added to value too- cannot be represented in type 'int'");
        }
        return NEW(NumVal)((unsigned )val + (unsigned )otherNum->val);
    }
}

PTR(Val)NumVal::multWith(PTR(Val)otherVal) {
    PTR(NumVal)otherNum = CAST(NumVal)(otherVal);
    if (otherNum == nullptr){
        throw std::runtime_error("cannot multiply a non number value to a number value");
    } else{
        if (((long)this->val * (long)otherNum->val) > INT_MAX || ((long)this->val * (long)otherNum->val < INT_MIN)){
            throw std::runtime_error("Multiplied to value too high- cannot be represented in type 'int'");
        }
        return NEW(NumVal)((unsigned) this->val *(unsigned)otherNum->val);
    }
}

std::string NumVal::toString() {
    return std::to_string(val);
}

bool NumVal::equals(PTR(Val)otherVal) {
    PTR(NumVal)otherNum = CAST(NumVal)(otherVal);
    if (otherNum == nullptr){
        return false;
    } else{
        return (val == otherNum->val);
    }
}

bool NumVal::isTrue() {
    throw std::runtime_error("NumVal cannot be true");
}
PTR(Val)NumVal::call(PTR(Val)actual_arg){
    throw std::runtime_error("NumVal cannot execute function 'call'");
}


BoolVal::BoolVal(bool inputBool) {
    if (inputBool == true){
        boolString = "_true";
    } else {
        boolString = "_false";
    }
}

std::string BoolVal::toString() {
    return this->boolString;
}
PTR(Val) BoolVal::addTo (PTR(Val)otherVal) {
    throw std::runtime_error("cannot addTo a Bool value");
}
PTR(Val) BoolVal::multWith(PTR(Val)otherVal) {
    throw std::runtime_error("cannot MultWith a Bool value");
}
bool BoolVal::equals(PTR(Val)otherVal) {
    PTR(BoolVal)otherBool = CAST(BoolVal)(otherVal);
    if (otherBool == nullptr){
        return false;
    } else{
        return (boolString == otherBool->boolString);
    }
}
bool BoolVal::isTrue() {
    return this->boolString == "_true";
}
PTR(Val)BoolVal::call(PTR(Val)actual_arg){
    throw std::runtime_error("BoolVal cannot execute function 'call'");
}

FunVal::FunVal(std::string inputArg, PTR(Expr)inputBody, PTR(Env) env){
    formalArgVal = inputArg;
    bodyVal = inputBody;
    funEnv = env;
}
std::string FunVal::toString() {
    return "[function]";
}
PTR(Val) FunVal::addTo (PTR(Val)otherVal) {
    throw std::runtime_error("cannot addTo a FunVal");
}
PTR(Val) FunVal::multWith(PTR(Val)otherVal) {
    throw std::runtime_error("cannot multWith a FunVal");
}
bool FunVal::equals(PTR(Val)otherVal) {
    PTR(FunVal)l = CAST(FunVal)(otherVal);
    if (l == nullptr) {
        return false;
    } else {
        return (formalArgVal == l->formalArgVal) && bodyVal->equals(l->bodyVal);
    }
}
bool FunVal::isTrue() {
    throw std::runtime_error("FunVal cannot be true");
}
PTR(Val)FunVal::call(PTR(Val)actual_arg){
    //subst actual arg in body
    //interp
    //return val
    return bodyVal->interp(NEW(ExtendedEnv)(formalArgVal, actual_arg, funEnv));

}