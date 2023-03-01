#include <iostream>
#include <array>

using Block = std::array<uint8_t, 8>;
#define MAXARRAYSIZE 256


Block *generateKey(std::string password) {
    uint8_t key[8] = {0, 0, 0, 0, 0, 0, 0, 0};
    for (int i = 0; i < password.size(); i++) {
        key[i % 8] = key[i % 8] ^ password[i];
    }
    return reinterpret_cast<Block *>(key);
}

void getUnshuffledArray(std::byte *array) {
    for (int i = 0; i < MAXARRAYSIZE; i++) {
        array[i] = static_cast<std::byte>(i);
    }
}

void copyArray(const std::byte *copyFrom, std::byte *copyTo) {
    for (int i = 0; i < MAXARRAYSIZE; i++) {
        copyTo[i] = copyFrom[i];
    }
}

void shuffleArray(std::byte *array) {
    for (int i = MAXARRAYSIZE - 1; i > 0; i--) {
        int j = rand() % i;
        std::swap(array[i], array[j]);
    }
}

void printBlock(const Block message) {
    for (int i = 0; i < message.size(); ++i) {
        std::cout << message[i];
    }
    std::cout << std::endl;
}

void createDecryptArrays(const std::byte *encryptArray, std::byte *decryptArray) {
    for (int i = 0; i < MAXARRAYSIZE; ++i) {
        decryptArray[(int) encryptArray[i]] = static_cast<std::byte>(i);
    }
}

int main() {
    srand(clock());
    std::string password = "yeehaw69";
    int numLoops = 16;

    Block *key = generateKey(password);

    std::byte unshuffledArray[256];
    std::byte shuffledArray1[256];
    std::byte shuffledArray2[256];
    std::byte shuffledArray3[256];
    std::byte shuffledArray4[256];
    std::byte shuffledArray5[256];
    std::byte shuffledArray6[256];
    std::byte shuffledArray7[256];

    getUnshuffledArray(unshuffledArray);
    copyArray(unshuffledArray, shuffledArray1);
    shuffleArray(shuffledArray1);
    copyArray(shuffledArray1, shuffledArray2);
    shuffleArray(shuffledArray2);
    copyArray(shuffledArray2, shuffledArray3);
    shuffleArray(shuffledArray3);
    copyArray(shuffledArray3, shuffledArray4);
    shuffleArray(shuffledArray4);
    copyArray(shuffledArray4, shuffledArray5);
    shuffleArray(shuffledArray5);
    copyArray(shuffledArray5, shuffledArray6);
    shuffleArray(shuffledArray6);
    copyArray(shuffledArray6, shuffledArray7);
    shuffleArray(shuffledArray7);




    std::byte decryptUnshuffledArray[256];
    std::byte decryptShuffledArray1[256];
    std::byte decryptShuffledArray2[256];
    std::byte decryptShuffledArray3[256];
    std::byte decryptShuffledArray4[256];
    std::byte decryptShuffledArray5[256];
    std::byte decryptShuffledArray6[256];
    std::byte decryptShuffledArray7[256];

    createDecryptArrays(unshuffledArray, decryptUnshuffledArray);
    createDecryptArrays(shuffledArray1, decryptShuffledArray1);
    createDecryptArrays(shuffledArray2, decryptShuffledArray2);
    createDecryptArrays(shuffledArray3, decryptShuffledArray3);
    createDecryptArrays(shuffledArray4, decryptShuffledArray4);
    createDecryptArrays(shuffledArray5, decryptShuffledArray5);
    createDecryptArrays(shuffledArray6, decryptShuffledArray6);
    createDecryptArrays(shuffledArray7, decryptShuffledArray7);



    std::byte *encryptionTables[] = {unshuffledArray, shuffledArray1, shuffledArray2, shuffledArray3, shuffledArray4,
                           shuffledArray5, shuffledArray6, shuffledArray7};
    std::byte *decryptionTables[] = {decryptUnshuffledArray, decryptShuffledArray1, decryptShuffledArray2, decryptShuffledArray3, decryptShuffledArray4, decryptShuffledArray5, decryptShuffledArray6, decryptShuffledArray7};

    std::string stringMessage = "whitneyk";
    Block message;
    for (int i = 0; i < message.size(); ++i) {
        message[i] = stringMessage[i];
    }



    //encryption
    for (int i = 0; i < numLoops; i++) {
        //xor current message
        for (int i = 0; i < message.size(); i++) {
            message[i] = message[i] ^ key->at(i );
        }
        //substitution table
        for (int i = 0; i < message.size(); i++) {
            message[i] = (char) encryptionTables[i][message[i]];
        }
        //rotation (bitwise)
        Block copyBlock = message;
        for (int i = 0; i < message.size(); ++i) {
            copyBlock[i] = (message[i] & 0x80) >> 7;
            message[i] = (message[i] & 0x7F) << 1;
        }
        message[7] = message[7] | copyBlock[0];
        for (int i = 0; i < message.size() - 1; ++i) {
            message[i] = message[i] | copyBlock[i + 1];
        }
    }

    for (int i = 0; i < numLoops; i++) {
        //decryption
        //rotation(bitwise)
        Block copyBlockDecrypt = message;
        for (int i = 0; i < message.size(); ++i) {
            copyBlockDecrypt[i] = (message[i] & 0x01) << 7;
//            message[i] = (message[i] & 0xFE) >> 1;
            message[i] = (message[i] >> 1) & 0x7F;

        }
        message[0] = message[0] | copyBlockDecrypt[7];
        for (int i = 1; i < message.size(); ++i) {
            message[i] = message[i] | copyBlockDecrypt[i - 1];
        }
        //substitution table
        for (int i = 0; i < message.size(); i++) {
            message[i] = (char) decryptionTables[i][message[i]];
        }
        //xor current message
        for (int i = 0; i < message.size(); i++) {
            message[i] = message[i] ^ key->at(i);
        }
    }

    printBlock(message);
    return 0;
}


