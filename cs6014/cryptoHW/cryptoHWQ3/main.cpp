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
    std::byte *decryptionTables[] = {decryptUnshuffledArray, decryptShuffledArray1, decryptShuffledArray2,
                                     decryptShuffledArray3, decryptShuffledArray4, decryptShuffledArray5,
                                     decryptShuffledArray6, decryptShuffledArray7};

    std::string stringMessage = "whitneyk";
    Block message;
    for (int i = 0; i < message.size(); ++i) {
        message[i] = stringMessage[i];
    }



    //encryption
    for (int i = 0; i < numLoops; i++) {
        //xor current message
        for (int j = 0; j < message.size(); j++) {
            message[j] = message[j] ^ key->at(j);
        }
        //substitution table
        for (int k = 0; k < message.size(); k++) {
            message[k] = (char) encryptionTables[k][message[k]];
        }
        //rotation (bitwise)
        Block copyBlock = message;
        for (int l = 0; l < message.size(); ++l) {
            copyBlock[l] = (message[l] & 0x80) >> 7;
            message[l] = (message[l] & 0x7F) << 1;
        }
        message[7] = message[7] | copyBlock[0];
        for (int m = 0; m < message.size() - 1; ++m) {
            message[m] = message[m] | copyBlock[m + 1];
        }
    }
    std::cout << "message after encryption: ";
    printBlock(message);
    //commented out line to show what happens when a single bit is flipped
    //message[5] = message[5] ^ 0x40;
    for (int i = 0; i < numLoops; i++) {
        //decryption
        //rotation(bitwise)
        Block copyBlockDecrypt = message;
        for (int j = 0; j < message.size(); ++j) {
            copyBlockDecrypt[j] = (message[j] & 0x01) << 7;
            message[j] = (message[j] >> 1) & 0x7F;

        }
        message[0] = message[0] | copyBlockDecrypt[7];
        for (int k = 1; k < message.size(); ++k) {
            message[k] = message[k] | copyBlockDecrypt[k - 1];
        }
        //substitution table
        for (int l = 0; l < message.size(); l++) {
            message[l] = (char) decryptionTables[l][message[l]];
        }
        //xor current message
        for (int m = 0; m < message.size(); m++) {
            message[m] = message[m] ^ key->at(m);
        }
    }

    std::cout << "message after decryption: ";
    printBlock(message);
    return 0;
}


