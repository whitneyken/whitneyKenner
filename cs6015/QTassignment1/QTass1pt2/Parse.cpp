//
// Created by Whitney Kenner on 2/20/23.
//

#include "Parse.h"
#include "Expr.hpp"
//handle a parse keyword instead of individually handling elsewhere




PTR(Expr)parseFunction(std::istream &istream);

/**
 * \file Parse.cpp
 * \brief a cpp file containing all of the method implementations for Parse class
 */

PTR(Expr)parse(std::istream &in) {
    PTR(Expr) e = parseExpr(in);

    skipWhitespace(in);
    if(!in.eof()){
        throw std::runtime_error("unexpected token after parse");
    }
    return e;
}

PTR(Expr)parseString(std::string string) {
    std::stringstream st(string);
    PTR(Expr)e = parseExpr(st);
    if(!st.eof()){
        throw std::runtime_error("unexpected token after parse");
    }
    return e;
}

PTR(Expr)parseNum(std::istream &in) {
    long num = 0;
    bool isNegative = false;
    if (in.peek() == '-') {
        isNegative = true;
        consume(in, '-');
    }
    if (in.peek() == EOF || (in.peek() == ' ')) {
        throw std::runtime_error("invalid input");
    }
    while (1) {
        int nextChar = in.peek();
        if (isdigit(nextChar)) {
            consume(in, nextChar);
            num = num * 10 + (nextChar - '0');
        } else {
            break;
        }
    }
    if (isNegative) {
        num = -num;
    }
//    if ((signed int)num > INT_MAX || (signed int)num < INT_MIN){
//        throw std::runtime_error("input number out of range");
//    }
    return NEW( NumExpr)((int) num);
}


void consume(std::istream &in, int expect) {
    int nextChar = in.get();
    if (nextChar != expect) {
        throw std::runtime_error("consume mismatch");
    }
}

void skipWhitespace(std::istream &in) {
    while (1) {
        int nextChar = in.peek();
        if (!isspace(nextChar)) {
            break;
        }
        consume(in, nextChar);
    }
}

PTR(Expr)parseExpr(std::istream &in) {
    PTR(Expr)e;
    e = parseComparg(in);
    skipWhitespace(in);
    int nextChar = in.peek();
    if (nextChar == '=') {
        consume(in, '=');
        consume(in, '=');
        PTR(Expr)rhs = parseExpr(in);
        return NEW( EqExpr)(e, rhs);
    }else {
        return e;
    }

}

PTR(Expr)parseComparg(std::istream &in) {
    PTR(Expr)e;
    e = parseAddend(in);
    skipWhitespace(in);
    int nextChar = in.peek();
    if (nextChar == '+') {
        consume(in, '+');
        PTR(Expr)rhs = parseComparg(in);
        return NEW( AddExpr)(e, rhs);
    } else
        return e;

}

PTR(Expr)parseAddend(std::istream &in) {
    PTR(Expr)e;
    e = parseMulticand(in);
    skipWhitespace(in);
    int nextChar = in.peek();
    if (nextChar == '*') {
        consume(in, '*');
        PTR(Expr)rhs = parseAddend(in);
        return NEW( MultExpr)(e, rhs);
    } else
        return e;

}

PTR(Expr)parseVariable(std::istream &in) {
    std::stringstream variableName;

    while (1) {
        int nextChar = in.peek();
        if (isalpha(nextChar)) {
            consume(in, nextChar);
            variableName.put(static_cast<char>(nextChar));
        } else if (nextChar == '_') {
            throw std::runtime_error("invalid input");
        } else {
            break;
        }
    }
    return NEW( VariableExpr)(variableName.str());
}


PTR(Expr)parseLet(std::istream &in) {
    PTR(Expr)name;
    PTR(Expr)rhs;
    PTR(Expr)body;
    skipWhitespace(in);
    //handle name
    name = parseVariable(in);
    skipWhitespace(in);
    consume(in, '=');
    rhs = parseExpr(in);

    skipWhitespace(in);
    consume(in, '_');
    consume(in, 'i');
    consume(in, 'n');

    body = parseExpr(in);

    return NEW( LetExpr)(name->toString(), rhs, body);
}

std::string parseKeyword(std::istream &in) {
    std::string keyword;
    while (true) {
        int nextChar = in.peek();
        if (isalpha(nextChar)) {
            consume(in, nextChar);
            keyword += static_cast<char>(nextChar);
        } else {
            break;
        }
    }
    return keyword;
}

PTR(Expr)parseMulticand(std::istream &in) {
    PTR(Expr)e = parseInner(in);
    skipWhitespace(in);
    while (in.peek() == '(') {
        consume(in, '(');
        PTR(Expr)actualArg = parseExpr(in);
        skipWhitespace(in);
        consume(in, ')');
        e = NEW( CallExpr)(e, actualArg);
    }
    return e;

}

//add variable or let
PTR(Expr)parseInner(std::istream &in) {
    skipWhitespace(in);
    int nextChar = in.peek();
    if ((nextChar == '-') || isdigit(nextChar)) {
        return parseNum(in);
    } else if (nextChar == '_') {
        consume(in, '_');
        //handle a parse keyword instead of individually handling elsewhere
        std::string keyword = parseKeyword(in);
        if (keyword == "true") {
            return NEW( BoolExpr)(true);
        } else if (keyword == "false") {
            return NEW( BoolExpr)(false);
        } else if (keyword == "if") {
            return parseIf(in);
        } else if (keyword == "let") {
            return parseLet(in);
        } else if (keyword == "fun") {
            return parseFunction(in);
        } else {
            throw std::runtime_error("invalid keyword input");
        }
    } else if (isalpha(nextChar)) {
        return parseVariable(in);
    } else if (nextChar == '(') {
        consume(in,
                '(');
        PTR(Expr)e = parseExpr(in);
        skipWhitespace(in);
        nextChar = in.get();
        if (nextChar != ')')
            throw std::runtime_error("missing close parenthesis");
        return
                e;
    } else {
        consume(in, nextChar
        );
        throw std::runtime_error("invalid input");
    }
}

PTR(Expr)parseFunction(std::istream &in) {
    PTR(Expr)formalArg;
    PTR(Expr)body;
    skipWhitespace(in);
    consume(in, '(');
    //handle name
    skipWhitespace(in);
    formalArg = parseVariable(in);
    skipWhitespace(in);
    consume(in, ')');
    body = parseExpr(in);

    return NEW( FunExpr)(formalArg->toString(), body);
}

PTR(Expr)parseIf(std::istream &in) {
    PTR(Expr)ifExpr;
    PTR(Expr)thenExpr;
    PTR(Expr)elseExpr;
    //handle name
    ifExpr = parseExpr(in);
    skipWhitespace(in);
    consume(in, '_');
    consume(in, 't');
    consume(in, 'h');
    consume(in, 'e');
    consume(in, 'n');
    thenExpr = parseExpr(in);
    skipWhitespace(in);
    consume(in, '_');
    consume(in, 'e');
    consume(in, 'l');
    consume(in, 's');
    consume(in, 'e');

    elseExpr = parseExpr(in);

    return NEW( IfExpr)(ifExpr, thenExpr, elseExpr);
}


