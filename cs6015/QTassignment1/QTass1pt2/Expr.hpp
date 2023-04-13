//
//  Expr.hpp
//  MSDscript
//
//  Created by Whitney Kenner on 1/18/23.
//

#ifndef Expr_hpp
#define Expr_hpp

#include <stdio.h>
#include <string>
#include <stdexcept>
#include <iostream>
#include <sstream>
#include "pointer.h"

class Env;
class Val;
/**
 * \file Expr.hpp
 * \brief header file containing Expr class and method declarations and all inheriting classes and methods
 */
//declaring precedence among the different expression types
typedef enum {
  prec_none,      // = 0
  prec_add,       // = 1
  prec_mult       // = 2
} precedence_t;


/**
 * \class Expr base class Expr with many virtual methods implemented by the derived classes
 */

CLASS(Expr){
public:
    virtual ~Expr() { };
    virtual bool equals(PTR(Expr) e) = 0;
    virtual PTR(Val) interp(PTR(Env) env) = 0;
//    virtual PTR(Expr) subst(std::string variable, PTR(Expr) e) = 0;
    virtual void print(std::ostream& cout) = 0;
    std::string toString();
    void prettyPrint(std::ostream& cout);
    virtual void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) = 0;
    std::string prettyToString();
    
};
/**
 * \class Num derived class Num which stores a single int as a member variable and implements parent Expr methods
 */
class NumExpr : public Expr{
public:
    int rep; //!< stored int value of a Num Expr
        
        
    NumExpr(int value);
    bool equals(PTR(Expr) e) override;
    PTR(Val) interp(PTR(Env) env) override;
//    PTR(Expr) subst(std::string variable, PTR(Expr) e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
};
/**
 * \class BoolExpr derived class BoolExpr which stores a string as a member variable and implements parent Expr methods
 */
 class BoolExpr : public Expr{
 public:
     bool boolExprVal; //!< stored boolean value of a boolean Expr

     BoolExpr(bool inputBool);
     bool equals(PTR(Expr) e) override;
     PTR(Val) interp(PTR(Env) env) override;
//     PTR(Expr) subst(std::string variable, PTR(Expr) e) override;
     void print(std::ostream& cout) override;
     void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;

 };
/**
* \class IfExpr derived class IfExpr which stores an Expr as a conditional. If the conditional evaluates to true, then "then" will be interpreted, else "else" will be interpreted
*/
 class IfExpr : public Expr{
 public:
     PTR(Expr) ifExpr;
     PTR(Expr) thenExpr;
     PTR(Expr) elseExpr;

     IfExpr(PTR(Expr) ifExprInput, PTR(Expr) thenExprInput, PTR(Expr) elseExprInput);
     bool equals(PTR(Expr) e) override;
     PTR(Val) interp(PTR(Env) env) override;
//     PTR(Expr) subst(std::string variable, PTR(Expr) e) override;
     void print(std::ostream& cout) override;
     void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;

 };
/**
* \class EqExpr derived class EqExpr which stores an Expr as a lhs and an Expr as a rhs member variable and implements parent Expr methods
*/
class EqExpr : public Expr{
public:
    PTR(Expr) lhs; //!< expression stored on the left hand side of this EqExpr
    PTR(Expr) rhs; //!< expression stored on the right hand side of this EqExpr

    EqExpr(PTR(Expr) leftSide, PTR(Expr) rightSide);
    bool equals(PTR(Expr) e) override;
    PTR(Val) interp(PTR(Env) env) override;
//    PTR(Expr) subst(std::string variable, PTR(Expr) e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
};
/**
 * \class Add derived class Add which stores an Expr as a lhs and an Expr as a rhs member variable and implements parent Expr methods
 */
class AddExpr : public Expr{
public:
    PTR(Expr) lhs; //!< expression stored on the left hand side of this Add Expr
    PTR(Expr) rhs; //!< expression stored on the right hand side of this Add Expr
       
    AddExpr(PTR(Expr) leftSide, PTR(Expr) rightSide);
    bool equals(PTR(Expr) e) override;
    PTR(Val) interp(PTR(Env) env) override;
//    PTR(Expr) subst(std::string variable, PTR(Expr) e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
        
};
/**
 * \class Mult derived class Mult which stores an Expr as a lhs and an Expr as a rhs member variable and implements parent Expr methods
 */
class MultExpr : public Expr{
public:
    PTR(Expr) lhs; //!< expression stored on the left hand side of this mult Expr
    PTR(Expr) rhs; //!< expression stored on the right hand side of this mult Expr

    MultExpr(PTR(Expr) leftSide, PTR(Expr) rightSide);
    bool equals(PTR(Expr) e) override;
    PTR(Val) interp(PTR(Env) env) override;
//    PTR(Expr) subst(std::string variable, PTR(Expr) e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
};
/**
 * \class Variable derived class Variable which stores a single string as a member variable and implements parent Expr methods
 */
class VariableExpr : public Expr{
public:
    std::string var; //!< stored string value of a Variable Expr
    
    VariableExpr(std::string input);
    bool equals(PTR(Expr) e) override;
    PTR(Val) interp(PTR(Env) env) override;
//    PTR(Expr) subst(std::string variable, PTR(Expr) e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
};
/**
 * \class _let derived class which stores a variable as a member variable, a rhs Expr and a body Expr and implements parent Expr methods.
 * the lhs of a binding is always a name and not an Expr
 */
class LetExpr : public Expr{
public:
    std::string name; //!< stored variable of the "name" of a variable to be replaced if found in the body
    PTR(Expr) rhs; //!< an expression that will replace the name if found in the body
    PTR(Expr) body; //!< the body of the let expression where the name will be replaced with the rhs if found

    LetExpr(std::string vName, PTR(Expr) rightSide, PTR(Expr) letBody);
    bool equals(PTR(Expr) e) override;
    PTR(Val) interp(PTR(Env) env) override;
//    PTR(Expr) subst(std::string inputName, PTR(Expr) e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;



};

class FunExpr : public Expr{
public:
    std::string formalArg;
    PTR(Expr) body;

    FunExpr(std::string inputArg, PTR(Expr) e);
    bool equals(PTR(Expr) e) override;
    PTR(Val) interp(PTR(Env) env) override;
//    PTR(Expr) subst(std::string inputName, PTR(Expr) e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
};

class CallExpr : public Expr{
public:
    PTR(Expr) toBeCalled;
    PTR(Expr) actualArg;

    CallExpr(PTR(Expr) inputCall, PTR(Expr) inputArg);
    bool equals(PTR(Expr) e) override;
    PTR(Val) interp(PTR(Env) env) override;
//    PTR(Expr) subst(std::string inputName, PTR(Expr) e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;

};


#endif /* Expr_hpp */
