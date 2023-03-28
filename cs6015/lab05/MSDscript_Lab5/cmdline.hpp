//
//  cmdline.hpp
//  MSDscript
//
//  Created by Whitney Kenner on 1/13/23.
//

#ifndef cmdline_hpp
#define cmdline_hpp

#include <stdio.h>
#include <string>
#include <iostream>
/**
 * \file cmdline.hpp
 * \brief header file containing a use arguments method signature for handling command line arguments
 */
typedef enum {

    do_nothing, // = 0
    do_interp, // = 1
    do_print, // = 2
    do_pretty_print, // = 3

} run_mode_t;

run_mode_t use_arguments(int argc, char** argv);
#endif /* cmdline_hpp */
