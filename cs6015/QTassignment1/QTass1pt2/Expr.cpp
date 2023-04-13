//
//  Expr.cpp
//  MSDscript
//
//  Created by Whitney Kenner on 1/18/23.
//
//

#include "Expr.hpp"
#include "Val.h"
#include "Env.h"
/**
 * \file Expr.cpp
 * \brief a cpp file containing all of the method implementations for Expr 
 */

/**
 * \brief Return an std::string of the expression calling the method
 * \return std::string of the calling Expr
 */
std::string Expr::toString() {
    std::stringstream st("");
    this->print(st);
    return st.str();
}

/**
 * \brief passes the calling Expr in a "pretty" format (no unnecessary parentheses and spaces around operatives) into a passed in std::ostream
 * \param cout  ostream passed in and then passed on to the method prettyPrintAt in inheriting base classes of Expr
 */
void Expr::prettyPrint(std::ostream &cout) {
    std::streampos streamPos = 0;
    bool parenthesis = false;

    this->prettyPrintAt(cout, prec_none, streamPos, parenthesis);
}

/**
 * \brief Return an std::string in a "pretty" format of the expression calling the method. (no unnecessary parentheses and spaces around operatives)
 * \return std::string of the calling Expr in a "pretty" format
 */
std::string Expr::prettyToString() {
    std::stringstream st("");
    this->prettyPrint(st);
    return st.str();
}


NumExpr::NumExpr(int value) {
//    if ((unsigned) value > INT_MAX || value < INT_MIN){
//        throw std::runtime_error("Invalid number input- out of integer range");
//    }
    rep = value;
}

/**
 * \brief checks the equality of 2 num Expr based on their stored member variable int val
 * \param e the 2nd Expr passed in and compared to the calling Expr to check for equality
 * \return a boolean indicating whether the calling expression and passed in expression are equal
 */
bool NumExpr::equals(PTR(Expr)e) {
    PTR(NumExpr) n = CAST(NumExpr)(e);
    if (n == nullptr) {
        return false;
    } else {
        return rep == n->rep;
    }
}

/**
 * \brief interprets the value of a Num expression by returning the value of the stored member variable (int val) as a NumVal
 * \return an int of the member variable val of this Num Expr as a NumVal
 */
PTR(Val)NumExpr::interp(PTR(Env) env) {
    return NEW(NumVal)(this->rep);
}


/**
 * \brief substitutes any instances of a passed in string existing within the calling Expr (as part of a Variable Expr) with the passed in Expr. this returns the updated Expr. In this case the Num Expr will never be modified as it cannot contain a Variable Expr and will therefor always return "this"
 * \param variable this passed in string is the Variable member variable that will be replaced with the 2nd parameter Expr if it exists within this Expr (it will not exist within the Num Expr)
 * \param e the 2nd parameter passed in is the Expr that will replace the variable with the value of the passed in string if it is found. this will not be used by this method.
 * \return Expr of this Num Expr as a new Num Expr (unmodified other than new)
 */
//PTR(Expr)NumExpr::subst(std::string variable, PTR(Expr)e) {
//    return NEW(NumExpr)(this->rep);
//}

/**
 * \brief passes the value stored in this Num Expr into a passed in std::ostream
 * \param cout an output stream passed in to take in the Num Expr val
 */
void NumExpr::print(std::ostream &cout) {
    cout << std::to_string(this->rep);
}

/**
 * \brief this method is used by pretty print to pass the Num Expr to an ostream for printing. The precedence of the caller Expr is not used
 * \param cout an output stream passed in to take in the Num Expr val
 * \param prec the precedence of the calling Expr. does not matter for Num Expr as it makes no impact on what is added to the ostream
 * \param position the position is an std::streampos for proper indentation in Let Expressions, it is not used here
 * \param letPar a boolean for whethere a Let expression should include parentheses, this is not used here
 */
void NumExpr::prettyPrintAt(std::ostream &cout, precedence_t prec, std::streampos &position, bool letPar) {
    cout << std::to_string(this->rep);
}


BoolExpr::BoolExpr(bool inputBool) {
    boolExprVal = inputBool;
}

/**
 * \brief checks the equality of 2 bool Expr based on their stored member variable boolExprVal
 * \param e the 2nd Expr passed in and compared to the calling Expr to check for equality
 * \return a boolean indicating whether the calling expression and passed in expression are equal
 */
bool BoolExpr::equals(PTR(Expr)e) {
    PTR(BoolExpr) a = CAST(BoolExpr)(e);
    if (a == nullptr) {
        return false;
    } else {
        return (this->boolExprVal == a->boolExprVal);
    }
}

/**
 * \brief interprets the value of a Bool Expr expression by returning the value of the stored member variable (bool) as a BoolVal
 * \return an BoolVal of the member variable boolExprVal of this BoolExpr as a BoolVal
 */
PTR(Val)BoolExpr::interp(PTR(Env) env) {
    return NEW(BoolVal)(this->boolExprVal);
}

/**
 * \brief substitutes any instances of a passed in string existing within the calling Expr (as part of a Variable Expr) with the passed in Expr. this returns the updated Expr. In this case the BoolExpr will never be modified as it cannot contain a Variable Expr and will therefor always return "this"
 * \param variable this passed in string is the Variable member variable that will be replaced with the 2nd parameter Expr if it exists within this Expr (it will not exist within the BoolExpr)
 * \param e the 2nd parameter passed in is the Expr that will replace the variable with the value of the passed in string if it is found. this will not be used by this method.
 * \return Expr of this BoolExpr as a new BoolExpr (unmodified other than new)
 */
//PTR(Expr)BoolExpr::subst(std::string variable, PTR(Expr)e) {
//    return NEW(BoolExpr)(this->boolExprVal);
//}

/**
 * \brief passes the value stored in this BoolExpr into a passed in std::ostream
 * \param cout an output stream passed in to take in the Num Expr val
 */
void BoolExpr::print(std::ostream &cout) {
    if (boolExprVal == true) {
        cout << " _true ";
    } else {
        cout << " _false ";
    }
}

/**
 * \brief this method is used by pretty print to pass the BoolExpr to an ostream for printing. The precedence of the caller Expr is not used
 * \param cout an output stream passed in to take in the Num Expr val
 * \param prec the precedence of the calling Expr. does not matter for Bool Expr as it makes no impact on what is added to the ostream
 * \param position the position is an std::streampos for proper indentation in Let Expressions, it is not used here
 * \param letPar a boolean for whethere a Let expression should include parentheses, this is not used here
 */
void BoolExpr::prettyPrintAt(std::ostream &cout, precedence_t prec, std::streampos &position, bool letPar) {
    if (boolExprVal == true) {
        cout << " _true ";
    } else {
        cout << " _false ";
    }
}


IfExpr::IfExpr(PTR(Expr)ifExprInput, PTR(Expr)thenExprInput, PTR(Expr)elseExprInput) {
    ifExpr = ifExprInput;
    thenExpr = thenExprInput;
    elseExpr = elseExprInput;
}

/**
 * \brief checks the equality of 2 Expr based on their stored if, then and else Expressions
 * \param e the 2nd Expr passed in and compared to the calling Expr to check for equality
 * \return a boolean indicating whether the calling expression and passed in expression are equal
 */
bool IfExpr::equals(PTR(Expr)e) {
    PTR(IfExpr) a = CAST(IfExpr)(e);
    if (a == nullptr) {
        return false;
    } else {
        return (this->ifExpr->equals(a->ifExpr) && this->thenExpr->equals(a->thenExpr) &&
                this->elseExpr->equals(a->elseExpr));
    }
}

/**
 * \brief interprets the value of a IfExpr expression by checking whether the IfExpr evaluates to true and if so returns the interpreted then statement, otherwise returns the interpreted else statement
 * \return the interpreted then Expr or else Expr
 */
PTR(Val)IfExpr::interp(PTR(Env) env) {
    if (ifExpr->interp(env)->isTrue()) {
        return thenExpr->interp(env);
    } else {
        return elseExpr->interp(env);
    }
}


/**
 * \brief substitutes any instances of a passed in string existing within the calling Expr with the passed in Expr IF the variable string is found. this returns a new IfExpr whether or not the contents are updated
 * \param variable this passed in string is the Variable Expr member variable that will be replaced if found in the Expr. it is passed on to the If, Then and Else Expr in an iterative call to subst()
 * \param e the 2nd parameter passed in is the Expr that will replace the variable with the value of the passed in string if it is found. it is passed on to the If, Then and Else Expr in an iterative call to subst()
 * \return Expr of this IfExpr as a new IfExpr (whether the variable has been updated with an Expr or not)
 */
//PTR(Expr)IfExpr::subst(std::string variable, PTR(Expr)e) {
//    return NEW (IfExpr)(this->ifExpr->subst(variable, e), this->thenExpr->subst(variable, e),
//                      this->elseExpr->subst(variable, e));
//}

/**
 * \brief passes parentheses around this Expr to the passed in ostream and iteratively calls print on the If, Then, and Else
 * \param cout an output stream passed in to take in parentheses and to be passed on to the iterative calls
 */
void IfExpr::print(std::ostream &cout) {
    cout << "(_if ";
    this->ifExpr->print(cout);
    cout << " _then ";
    this->thenExpr->print(cout);
    cout << " _else ";
    this->elseExpr->print(cout);
    cout << ")";
}

/**
 * \brief this method is used by pretty print to pass the IfExpr to an ostream for printing.  the precedence of the calling Expr is also passed in and used to compare precedence in order to determine whether parentheses should be printed around this Expr or not
 * \param cout an output stream passed in to take in potential parentheses and be passed on to the rhs and lhs
 * \param prec the precedence of the calling Expr. this is compared with the precedence of add and if it is greater or equal to add precedence, parentheses are added to the ostream around this Expr
 * \param position the position is an std::streampos for proper indentation in Let Expressions, it is not used here
 * \param letPar a boolean for whether a Let expression should include parentheses, the right hand side will pass on true so a rhs let will have parentheses
 */
void IfExpr::prettyPrintAt(std::ostream &cout, precedence_t prec, std::streampos &position, bool letPar) {
    cout << "(_if ";
    this->ifExpr->prettyPrint(cout);
    cout << " _then ";
    this->thenExpr->prettyPrint(cout);
    cout << " _else ";
    this->elseExpr->prettyPrint(cout);
    cout << ")";
}


EqExpr::EqExpr(PTR(Expr)leftSide, PTR(Expr)rightSide) {
    lhs = leftSide;
    rhs = rightSide;
}

/**
 * \brief checks the equality of 2 Expr based on their stored rhs and lhs Expr
 * \param e the 2nd Expr passed in and compared to the calling Expr to check for equality
 * \return a boolean indicating whether the calling expression and passed in expression are equal
 */
bool EqExpr::equals(PTR(Expr)e) {
    PTR(EqExpr) a = CAST(EqExpr)(e);
    if (a == nullptr) {
        return false;
    } else {
        return (this->rhs->equals(a->rhs) && this->lhs->equals(a->lhs));
    }
}

/**
 * \brief interprets the value of a EqExpr expression by returning whether the rhs and lhs are equal
 * \return a BoolVal on whether the rhs and lhs are equal
 */
PTR(Val)EqExpr::interp(PTR(Env) env) {
    return NEW(BoolVal)(rhs->interp(env)->equals(lhs->interp(env)));
}

/**
 * \brief substitutes any instances of a passed in string existing within the calling Expr with the passed in Expr IF the variable string is found. this returns a new EqExpr whether or not the contents are updated
 * \param variable this passed in string is the Variable Expr member variable that will be replaced if found in the Expr. it is passed on to the lhs and rhs in an iterative call to subst()
 * \param e the 2nd parameter passed in is the Expr that will replace the variable with the value of the passed in string if it is found. it is passed on to the lhs and rhs in an iterative call to subst()
 * \return Expr of this EqExpr as a new EqExpr (whether the variable has been updated with an Expr or not)
 */
//PTR(Expr)EqExpr::subst(std::string variable, PTR(Expr)e) {
//
//    return (NEW (EqExpr)(this->lhs->subst(variable, e), this->rhs->subst(variable, e)));
//}

/**
 * \brief passes parentheses around this Expr to the passed in ostream and iteratively calls print on the lhs and rhs
 * \param cout an output stream passed in to take in parentheses and to be passed on to the iterative calls
 */
void EqExpr::print(std::ostream &cout) {
    cout << "(";
    this->lhs->print(cout);
    cout << "==";
    (this->rhs->print(cout));
    cout << ")";
}

/**
 * \brief this method is used by pretty print to pass the EqExpr to an ostream for printing.  the precedence of the calling Expr is also passed in and used to compare precedence in order to determine whether parentheses should be printed around this Expr or not
 * \param cout an output stream passed in to take in potential parentheses and be passed on to the rhs and lhs
 * \param prec the precedence of the calling Expr. this is compared with the precedence of add and if it is greater or equal to add precedence, parentheses are added to the ostream around this Expr
 * \param position the position is an std::streampos for proper indentation in Let Expressions, it is not used here
 * \param letPar a boolean for whether a Let expression should include parentheses, the right hand side will pass on true so a rhs let will have parentheses
 */
void EqExpr::prettyPrintAt(std::ostream &cout, precedence_t prec, std::streampos &position, bool letPar) {
    //figure out pretty print stuff later
    //if prec >= prec_add, add parentheses
    cout << "(";
    this->lhs->prettyPrint(cout);
    cout << " == ";
    (this->rhs->prettyPrint(cout));
    //figure out pretty print stuff later
    cout << ")";
}


//Add constructor
AddExpr::AddExpr(PTR(Expr)leftSide, PTR(Expr)rightSide) {
    this->lhs = leftSide;
    this->rhs = rightSide;
}

/**
 * \brief checks the equality of 2 Add Expr by calling equals on the lhs and rhs to check for equality of the Expr on each side. returns false if the Expr type being compared are not the same or they are not equal. equals() is called on the rhs and lhs to determine equality iteratively through the Expr.
 * \param e the 2nd Expr passed in and compared to the calling Expr to check for equality
 * \return a boolean indicating whether the calling expression and passed in expression are equal
 */
bool AddExpr::equals(PTR(Expr)e) {
    PTR(AddExpr) a = CAST(AddExpr)(e);
    if (a == nullptr) {
        return false;
    } else {
        return this->lhs->equals(a->lhs) && this->rhs->equals(a->rhs);
    }
}

/**
 * \brief interprets the value of a Add Expr by returning the addition of interp() on the lhs and rhs
 * \return an int of the interpreted values with the calling Add Expr
 */
PTR(Val)AddExpr::interp(PTR(Env) env) {
    return (this->lhs->interp(env)->addTo(this->rhs->interp(env)));
}

/**
 * \brief substitutes any instances of a passed in string existing within the calling Expr with the passed in Expr IF the variable string is found. this returns a new Add Expr whether or not the contents are updated
 * \param variable this passed in string is the Variable Expr member variable that will be replaced if found in the Expr. it is passed on to the lhs and rhs in an iterative call to subst()
 * \param e the 2nd parameter passed in is the Expr that will replace the variable with the value of the passed in string if it is found. it is passed on to the lhs and rhs in an iterative call to subst()
 * \return Expr of this Add Expr as a new Add Expr (whether the variable has been updated with an Expr or not)
 */
//PTR(Expr)AddExpr::subst(std::string variable, PTR(Expr)e) {
//    return (NEW (AddExpr)(this->lhs->subst(variable, e), this->rhs->subst(variable, e)));
//}

/**
 * \brief passes parentheses around this Expr to the passed in ostream and iteratively calls print on the lhs and rhs
 * \param cout an output stream passed in to take in parentheses and to be passed on to the iterative calls
 */
void AddExpr::print(std::ostream &cout) {
    cout << "(";
    this->lhs->print(cout);
    cout << "+";
    (this->rhs->print(cout));
    cout << ")";
}

/**
 * \brief this method is used by pretty print to pass the Add Expr to an ostream for printing.  the precedence of the calling Expr is also passed in and used to compare precedence in order to determine whether parentheses should be printed around this Expr or not
 * \param cout an output stream passed in to take in potential parentheses and be passed on to the rhs and lhs
 * \param prec the precedence of the calling Expr. this is compared with the precedence of add and if it is greater or equal to add precedence, parentheses are added to the ostream around this Expr
 * \param position the position is an std::streampos for proper indentation in Let Expressions, it is not used here
 * \param letPar a boolean for whether a Let expression should include parentheses, the right hand side will pass on true so a rhs let will have parentheses
 */
void AddExpr::prettyPrintAt(std::ostream &cout, precedence_t prec, std::streampos &position, bool letPar) {
    if (prec >= prec_add) {
        cout << "(";
    }

    this->lhs->prettyPrintAt(cout, prec_add, position, true);
    cout << " + ";
    this->rhs->prettyPrintAt(cout, prec_none, position, false);
    if (prec >= prec_add) {
        cout << ")";
    }
}


MultExpr::MultExpr(PTR(Expr)leftSide, PTR(Expr)rightSide) {
    this->lhs = leftSide;
    this->rhs = rightSide;
}

/**
 * \brief checks the equality of 2 Mult Expr by calling equals on the lhs and rhs to check for equality of the Expr on each side. returns false if the Expr type being compared are not the same or they are not equal. equals() is called on the rhs and lhs to determine equality iteratively through the Expr.
 * \param e the 2nd Expr passed in and compared to the calling Expr to check for equality
 * \return a boolean indicating whether the calling expression and passed in expression are equal
 */
bool MultExpr::equals(PTR(Expr)e) {
    PTR(MultExpr) m = CAST(MultExpr)(e);
    if (m == nullptr) {
        return false;
    } else {
        return this->lhs->equals(m->lhs) && this->rhs->equals(m->rhs);
    }
}

/**
 * \brief interprets the value of a Mult  Expr by returning the multiplication of interp() on the lhs and rhs
 * \return an int of the interpreted values with the calling Mult Expr
 */
PTR(Val)MultExpr::interp(PTR(Env) env) {
    return (this->lhs->interp(env)->multWith(this->rhs->interp(env)));
}

/**
 * \brief substitutes any instances of a passed in string existing within the calling Expr with the passed in Expr IF the variable string is found. this returns a new Mult Expr whether or not the contents are updated
 * \param variable this passed in string is the Variable Expr member variable that will be replaced if found in the Expr. it is passed on to the lhs and rhs in an iterative call to subst()
 * \param e the 2nd parameter passed in is the Expr that will replace the variable with the value of the passed in string if it is found. it is passed on to the lhs and rhs in an iterative call to subst()
 * \return Expr of this Num Expr as a new Num Expr (whether the variable has been updated with an Expr or not)
 */
//PTR(Expr)MultExpr::subst(std::string variable, PTR(Expr)e) {
//    return (NEW (MultExpr)(this->lhs->subst(variable, e), this->rhs->subst(variable, e)));
//}

/**
 * \brief passes parentheses around this Expr to the passed in ostream and iteratively calls print on the lhs and rhs
 * \param cout an output stream passed in to take in parentheses and to be passed on to the iterative calls
 */
void MultExpr::print(std::ostream &cout) {
    cout << "(";
    this->lhs->print(cout);
    cout << "*";
    (this->rhs->print(cout));
    cout << ")";
}

/**
 * \brief this method is used by pretty print to pass the Mult Expr to an ostream for printing.  the precedence of the calling Expr is also passed in and used to compare precedence in order to determine whether parentheses should be printed around this Expr or not
 * \param cout an output stream passed in to take in potential parentheses and be passed on to the rhs and lhs
 * \param prec the precedence of the calling Expr. this is compared with the precedence of Mult and if it is greater or equal to Mult precedence, parentheses are added to the ostream around this Expr
 * \param position the position is an std::streampos for proper indentation in Let Expressions, it is not used here
 * \param letPar a boolean for whether a Let expression should include parentheses, the left hand side will pass on true so a lhs let will have parentheses. if the calling expression is an add, the right hand side will also be passed true
 */
void MultExpr::prettyPrintAt(std::ostream &cout, precedence_t prec, std::streampos &position, bool letPar) {
    if (prec == prec_mult) {
        cout << "(";
        //casting to check what the child thing is
    }
    this->lhs->prettyPrintAt(cout, prec_mult, position, true);
    cout << " * ";
    if (prec == prec_add) {
        this->rhs->prettyPrintAt(cout, prec_add, position, true);
    } else {
        this->rhs->prettyPrintAt(cout, prec_add, position, false);
    }
    if (prec >= prec_mult) {
        cout << ")";
    }
}


VariableExpr::VariableExpr(std::string input) {
    this->var = input;
}

/**
 * \brief checks the equality of 2 Expr. returns false if both are not Variable Expr or if they are not equal Variable Exprs
 * \param e the 2nd Expr passed in and compared to the calling Expr to check for equality
 * \return a boolean indicating whether the calling expression and passed in expression are equal
 */
bool VariableExpr::equals(PTR(Expr)e) {
    PTR(VariableExpr) m = CAST(VariableExpr)(e);
    if (m == nullptr) {
        return false;
    } else {
        return this->var == m->var;
    }
}

/**
 * \brief Variable Expr cannot be interpreted so this method call on a function throws an runtime error
 * \return an int is listed as the return type but nothing is returned as a runtime error is thrown
 */
PTR(Val)VariableExpr::interp(PTR(Env) env) {
    return env->lookup(var);
}


/**
 * \brief substitutes any instances of a passed in string existing within the calling Expr (as part of a Variable Expr) with the passed in Expr. If the string passed in matches this Variable's string, a new Variable is created replacing this variable with the passed in Expr. If the passed in string does not match this Variable string, then a new Variable with the same value is returned
 * \param variable this passed in string is the Variable member variable that will be replaced with the 2nd parameter Expr if it exists within this Expr
 * \param e the 2nd parameter passed in is the Expr that will replace the variable with the value of the passed in string if it is found.
 * \return an Expr that is either a new Variable that is the same as this Variable or returns the passed in Expr
 */
//PTR(Expr)VariableExpr::subst(std::string variable, PTR(Expr)e) {
//    if (this->var == variable) {
//        return e;
//    } else {
//        return NEW(VariableExpr)(this->var);
//    }
//}

/**
 * \brief passes the value stored in this Variable Expr into a passed in std::ostream
 * \param cout an output stream passed in to take in the Variable Expr val
 */
void VariableExpr::print(std::ostream &cout) {
    cout << this->var;
}

/**
 * \brief this method is used by pretty print to pass the Variable Expr to an ostream for printing. The precedence of the caller Expr is not used
 * \param cout an output stream passed in to take in the Variable Expr val
 * \param prec the precedence of the calling Expr. does not matter for Variable Expr as it makes no impact on what is added to the ostream
 * \param position the position is an std::streampos for proper indentation in Let Expressions, it is not used here
 * \param letPar a boolean for whethere a Let expression should include parentheses, this is not used here
 */
void VariableExpr::prettyPrintAt(std::ostream &cout, precedence_t prec, std::streampos &position, bool letPar) {
    cout << this->var;
}


LetExpr::LetExpr(std::string vName, PTR(Expr)rightSide, PTR(Expr)letBody) {
    this->name = vName;
    this->rhs = rightSide;
    this->body = letBody;
}

/**
 * \brief checks the equality of 2 Exprs by calling equals on the var, rhs, and body to check for equality of the Expr on each side. returns false if the Expr type being compared are not the same or they are not equal. equals() is called on the var, body  and lhs to determine equality iteratively through the Expr.
 * \param e the 2nd Expr passed in and compared to the calling Expr to check for equality
 * \return a boolean indicating whether the calling expression and passed in expression are equal
 */
bool LetExpr::equals(PTR(Expr)e) {
    PTR(LetExpr) l = CAST(LetExpr)(e);
    if (l == nullptr) {
        return false;
    } else {
        return (this->name == l->name) && this->rhs->equals(l->rhs) && this->body->equals(l->body);
    }
}

/**
 * \brief interprets the value of a _let Expr by returning a new _let Expr that will remain unchanged if the name string
 * is not found in the body, or a new _let with an updated body where the variable name is replaced with the lhs Expr.
 * \return an int of the interpreted values with the calling _let Expr
 */
PTR(Val)LetExpr::interp(PTR(Env) env) {
    PTR(Val) rhs_val = rhs->interp(env);
    PTR(Env) new_env = NEW(ExtendedEnv)(name, rhs_val, env);
    return body->interp(new_env);
}


/**
 * \brief substitutes any instances of a passed in string existing within the calling Expr (as part of a Variable Expr) with the passed in Expr.
 * If the string passed in matches a variable in the body but NOT the lhs (or name) of the let expression, a new Variable is created replacing this variable with the passed in Expr.
 * If the passed in string does not match this Variable string, then a new Variable with the same value is returned. if the passed in name
 * matches the name of the let in the body, the upper name will be ignored and only the parseInner name will be passed on to the body
 * \param inputName this passed in string is the Variable member variable that will be replaced with the 2nd parameter Expr if it exists within this Expr
 * \param e the 2nd parameter passed in is the Expr that will replace the variable with the value of the passed in string if it is found.
 * \return an Expr that is either a new Expr that is the same as this Expr or returns an updated Expr with modified values
 */
//PTR(Expr)LetExpr::subst(std::string inputName, PTR(Expr)e) {
//    //bind same variable-> don't substitute in the body
//    if (this->name == inputName) {
//        return (NEW(LetExpr)(this->name, this->rhs->subst(inputName, e), this->body));
//        //bind different variable-> DO substitute in the body
//        //return (new Let(this->name, this->rhs->subst(variable, e) && this->rhs->, this->body));
//    } else {
//        return (NEW(LetExpr)(this->name, this->rhs->subst(inputName, e), this->body->subst(inputName, e)));
//    }
//}

/**
 * \brief prints a let Expr by  printing "_let" followed by the name member variable of the let, printing "_in" and then
 * calling print on the rhs and body of the let
 * \param cout an output stream passed in to take in the string values to print
 */
void LetExpr::print(std::ostream &cout) {
    cout << "(_let " << name << "=";
    this->rhs->print(cout);
    cout << " _in ";
    this->body->print(cout);
    cout << ")";
}

/**
 * \brief this method is used by pretty print to pass the Let Expr to an ostream for printing.
 * A boolean is passed to this method that is used to determine whether this let Expr will have surrounding parentheses
 * \param cout an output stream passed in to take in potential parentheses and be passed on to the rhs and body
 * \param prec the precedence of the calling Expr. this is not used in let prettyPrintAt
 * \param position an std::stream used to store the position within the stream so that the Let Expr can be properly indented
 * \param letPar a boolean indicating whether the let should be surrounded by parentheses or not
 */
void LetExpr::prettyPrintAt(std::ostream &cout, precedence_t prec, std::streampos &position, bool letPar) {
    if (letPar) {
        cout << "(";
    }
    std::streampos tempPos = cout.tellp();
    long indent = tempPos - position;
    cout << "_let " << name << " = ";
    rhs->prettyPrintAt(cout, prec_none, position, false);
    cout << "\n";
    position = cout.tellp();
    cout << std::string(indent, ' ');
    cout << "_in  ";
    body->prettyPrintAt(cout, prec_none, position, false);
    if (letPar) {
        cout << ")";
    }
}

FunExpr::FunExpr(std::string inputArg, PTR(Expr)e) {
    formalArg = inputArg;
    body = e;
}

bool FunExpr::equals(PTR(Expr)e) {
    PTR(FunExpr) l = CAST(FunExpr)(e);
    if (l == nullptr) {
        return false;
    } else {
        return (this->formalArg == l->formalArg) && this->body->equals(l->body);
    }
}

PTR(Val)FunExpr::interp(PTR(Env) env) {
    return NEW(FunVal)(formalArg, body, env);
}

//PTR(Expr)FunExpr::subst(std::string inputName, PTR(Expr)e) {
//    if (formalArg == inputName){
//        return NEW(FunExpr)(inputName, body);
//    } else {
//        return NEW(FunExpr)(formalArg, body->subst(inputName, e));
//    }
//}

void FunExpr::print(std::ostream &cout) {
    cout << "_fun (" + formalArg + ")";
    body->print(cout);
}

void FunExpr::prettyPrintAt(std::ostream &cout, precedence_t prec, std::streampos &position, bool letPar) {
    cout << "_fun (" + formalArg + ")";
    body->prettyPrint(cout);
}

CallExpr::CallExpr(PTR(Expr)inputCall, PTR(Expr)inputArg){
    toBeCalled = inputCall;
    actualArg = inputArg;
}
bool CallExpr::equals(PTR(Expr)e) {
    PTR(CallExpr) c = CAST(CallExpr)(e);
    if (c == nullptr) {
        return false;
    } else {
        return toBeCalled->equals(c->toBeCalled) && actualArg->equals(c->actualArg);
    }
}
PTR(Val) CallExpr::interp(PTR(Env) env) {
    return toBeCalled->interp(env)->call(actualArg->interp(env));
}
//PTR(Expr) CallExpr::subst(std::string inputName, PTR(Expr)e) {
//    return NEW(CallExpr)(toBeCalled->subst(inputName, e), actualArg->subst(inputName, e));
//}
void CallExpr::print(std::ostream& cout) {
    toBeCalled->print(cout);
    cout << "(";
    actualArg->print(cout);
    cout << ")";
}
void CallExpr::prettyPrintAt(std::ostream& cout, precedence_t prec, std::streampos& position, bool letPar) {
    toBeCalled->prettyPrint(cout);
    cout << "(";
    actualArg->prettyPrint(cout);
    cout << ")";
}