#!/bin/sh
set -e
if test "$CONFIGURATION" = "Debug"; then :
  cd /Users/whitneykenner/whitneyKenner/cs6010/Day18/FinalProject/xcode
  make -f /Users/whitneykenner/whitneyKenner/cs6010/Day18/FinalProject/xcode/CMakeScripts/ReRunCMake.make
fi
if test "$CONFIGURATION" = "Release"; then :
  cd /Users/whitneykenner/whitneyKenner/cs6010/Day18/FinalProject/xcode
  make -f /Users/whitneykenner/whitneyKenner/cs6010/Day18/FinalProject/xcode/CMakeScripts/ReRunCMake.make
fi
if test "$CONFIGURATION" = "MinSizeRel"; then :
  cd /Users/whitneykenner/whitneyKenner/cs6010/Day18/FinalProject/xcode
  make -f /Users/whitneykenner/whitneyKenner/cs6010/Day18/FinalProject/xcode/CMakeScripts/ReRunCMake.make
fi
if test "$CONFIGURATION" = "RelWithDebInfo"; then :
  cd /Users/whitneykenner/whitneyKenner/cs6010/Day18/FinalProject/xcode
  make -f /Users/whitneykenner/whitneyKenner/cs6010/Day18/FinalProject/xcode/CMakeScripts/ReRunCMake.make
fi

