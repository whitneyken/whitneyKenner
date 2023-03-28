//
// Created by Whitney Kenner on 2/20/23.
//

#ifndef MSDSCRIPT_PARSE_H
#define MSDSCRIPT_PARSE_H



/**
 * \file Parse.h
 * \brief header file containing Parse method declarations for handling user input
 */

#include <iostream>
class Expr;

Expr *parse(std::istream &in);
Expr *parseString(std::string string);
Expr* parseNum(std::istream &in);
//make static later?
void consume(std::istream &in, int expected);
//make static later?
void skipWhitespace(std::istream &in);
Expr *parseAddend(std::istream &in);
Expr *parseVariable(std::istream &in);
Expr* parseExpr(std::istream &in);
Expr* parseMulticand(std::istream &in);


#endif //MSDSCRIPT_PARSE_H
