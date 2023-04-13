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
#include "pointer.h"
class Expr;

PTR(Expr)parse(std::istream &in);
PTR(Expr)parseString(std::string string);
PTR(Expr) parseNum(std::istream &in);
void consume(std::istream &in, int expected);
void skipWhitespace(std::istream &in);
PTR(Expr)parseAddend(std::istream &in);
PTR(Expr)parseVariable(std::istream &in);
PTR(Expr) parseExpr(std::istream &in);
PTR(Expr) parseMulticand(std::istream &in);
PTR(Expr) parseInner(std::istream &in);
std::string parseKeyword(std::istream &istream);
PTR(Expr)parseIf(std::istream &istream);
PTR(Expr)parseComparg(std::istream &in);

#endif //MSDSCRIPT_PARSE_H
