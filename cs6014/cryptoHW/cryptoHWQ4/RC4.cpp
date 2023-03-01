//
// Created by Whitney Kenner on 3/1/23.
//

#include "RC4.h"

std::vector<uint8_t> encrypt(std::vector<uint8_t> input, std::string inputKey) {
    std::string keyString = inputKey;
    std::vector<uint8_t> key = std::vector<uint8_t> (keyString.begin(), keyString.end());
    int keyLength = keyString.size();
    std::uint8_t State[256];


    //making state
    for (int i = 0; i < 256; i++) {
        State[i] = (i);
    }


    int j = 0;

    //key scheduling
    for (int i = 0; i < 256; i++) {
        j = (j + State[i] + key[i % keyLength]) % 256;
        std::swap(State[i], State[j]);
    }

    int i = 0;
    j = 0;
    std::vector<uint8_t > output;


    //making output based on key stream
    for (uint8_t byte : input) {
        i = (i + 1) % 256;
        j = (j + State[i]) % 256;
        std::swap(State[i], State[j]);
        int k  = State[(State[i] + State[j]) % 256];
        output.push_back(byte xor k);
    }


    return output;
}