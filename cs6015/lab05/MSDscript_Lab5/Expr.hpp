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

class Expr{
public:
    virtual bool equals(Expr *e) = 0;
    virtual int interp() = 0;
    virtual bool hasVariable() = 0;
    virtual Expr* subst(std::string variable, Expr *e) = 0;
    virtual void print(std::ostream& cout) = 0;
    std::string toString();
    void prettyPrint(std::ostream& cout);
    virtual void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) = 0;
    std::string prettyToString();
    
};
/**
 * \class Num derived class Num which stores a single int as a member variable and implements parent Expr methods
 */
class Num : public Expr{
public:
    int val; //!< stored int value of a Num Expr
        
        
    Num(int value);
    bool equals(Expr *e) override;
    int interp() override;
    bool hasVariable() override;
    Expr* subst(std::string variable, Expr *e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
};
/**
 * \class Add derived class Add which stores an Expr as a lhs and an Expr as a rhs member variable and implements parent Expr methods
 */
class Add : public Expr{
public:
    Expr *lhs; //!< expression stored on the left hand side of this Add Expr
    Expr *rhs; //!< expression stored on the right hand side of this Add Expr
       
    Add(Expr *leftSide, Expr *rightSide);
    bool equals(Expr *e) override;
    int interp() override;
    bool hasVariable() override;
    Expr* subst(std::string variable, Expr *e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
        
};
/**
 * \class Mult derived class Mult which stores an Expr as a lhs and an Expr as a rhs member variable and implements parent Expr methods
 */
class Mult : public Expr{
public:
    Expr *lhs; //!< expression stored on the left hand side of this mult Expr
    Expr *rhs; //!< expression stored on the right hand side of this mult Expr

    Mult(Expr *leftSide, Expr *rightSide);
    bool equals(Expr *e) override;
    int interp() override;
    bool hasVariable() override;
    Expr* subst(std::string variable, Expr *e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
};
/**
 * \class Variable derived class Variable which stores a single string as a member variable and implements parent Expr methods
 */
class Variable : public Expr{
public:
    std::string var; //!< stored string value of a Variable Expr
    
    Variable(std::string input);
    bool equals(Expr *e) override;
    int interp() override;
    bool hasVariable() override;
    Expr* subst(std::string variable, Expr *e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) override;
};
/**
 * \class _let derived class which stores a variable as a member variable, a rhs Expr and a body Expr and implements parent Expr methods.
 * the lhs of a binding is always a name and not an Expr
 */
class Let : public Expr{
public:
    std::string name; //!< stored variable of the "name" of a variable to be replaced if found in the body
    Expr *rhs; //!< an expression that will replace the name if found in the body
    Expr *body; //!< the body of the let expression where the name will be replaced with the rhs if found

    Let(std::string name, Expr *rightSide, Expr *letBody);
    bool equals(Expr *e) override;
    int interp() override;
    bool hasVariable() override;
    Expr* subst(std::string inputName, Expr *e) override;
    void print(std::ostream& cout) override;
    void prettyPrintAt(std::ostream& cout, precedence_t, std::streampos& position, bool letPar) override;



};


#endif /* Expr_hpp */
