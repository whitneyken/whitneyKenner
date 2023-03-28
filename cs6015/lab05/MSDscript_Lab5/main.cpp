//
//  main.cpp
//  MSDscript
//
//  Created by Whitney Kenner on 1/13/23.
//
#include "cmdline.hpp"
#include "Parse.h"
#include "Expr.hpp"
#include <iostream>

/**
 * \mainpage msdscript
 * \author Whitney Kenner
 * \date 02-07-2023
 */

int main(int argc, char **argv) {
    try {

        run_mode_t runType = use_arguments(argc, argv);

        if (runType == do_interp) {
            Expr *e = parseExpr(std::cin);
            //std::cout << "Expr before interp: " << std::endl;
            //e->print(std::cout);
            //std::cout << std::endl;
            //std::cout << "Expr after interp: " << std::endl;
            //check if an error is thrown by interp and then throw it

            int value = e->interp();

            std::cout << value << "\n";
            //<< std::endl;
            exit(0);

        } else if (runType == do_print) {
            Expr *e = parseExpr(std::cin);
            //std::cout << "Printed Expr: " << std::endl;
            e->print(std::cout);
            std::cout << std::endl;
            exit(0);
        } else if (runType == do_pretty_print) {
            Expr *e = parseExpr(std::cin);
            std::cout <<  e->prettyToString() << "\n";
            exit(0);
        } else {
            std::cerr << argv[1] << " is an invalid argument, please try again." << std::endl;
            exit(1);
        }
    } catch (std::runtime_error exn) {
        std::cerr << exn.what() << "\n";
        return 1;
    }
}

