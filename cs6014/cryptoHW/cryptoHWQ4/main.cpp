#include "RC4.h"



int main() {
    //OG encryption/decryption showing that using the same key works!!
    //encryption
    std::cout << "**********************************"<< std::endl;
    std::cout << "correct encryption/decryption with same key: " << std::endl;
    std::cout << "**********************************"<< std::endl;
    std::cout << std::endl;
    std::string message = "babyhitmeonemoretime";
    std::vector<uint8_t> messageVector = std::vector<uint8_t>(message.begin(), message.end());
    std::string myKey = "password";

    std::vector<uint8_t> cypherText1 = encrypt(messageVector, myKey);
    std::cout << "      cypher 1 text: ";
    for (char c: cypherText1) {
        std::cout << c;
    }
    std::cout << std::endl;
    //decryption
    std::vector<uint8_t> decryptedMessage = encrypt(cypherText1, myKey);
    std::cout <<"       decrypted message: ";
    for (char c: decryptedMessage) {
        std::cout << c;
    }
    std::cout << std::endl << std::endl;




    //below is showing that decrypting a message with a different key than the encryption key does not reveal the plaintext.
    //encryption
    std::cout << "**********************************"<< std::endl;
    std::cout << "incorrect encryption/decryption with different key: " << std::endl;
    std::cout << "**********************************"<< std::endl;
    std::cout << std::endl;

    std::string plaintText = "squilliamFancyson";
    std::vector<uint8_t> plainTextVector = std::vector<uint8_t>(plaintText.begin(), plaintText.end());
    std::string correctKey = "password";
    std::cout << "      encrypted message 1: ";
    std::vector<uint8_t> encryptedVector = encrypt(plainTextVector, correctKey);
    for (char c: encryptedVector) {
        std::cout << c;
    }
    //failed decryption
    std::string plainText2 = "doyouremember";
    std::vector<uint8_t> plainTextVector2 = std::vector<uint8_t>(plainText2.begin(), plainText2.end());
    std::string badKey = "partyTime";
    std::cout << std::endl;
    std::vector<uint8_t> dencryptedVector2 = encrypt(plainTextVector2, badKey);
    std::cout <<"      decrypted vector with wrong key: ";
    for (char c: dencryptedVector2) {
        std::cout << c;
    }
    std::cout << std::endl << std::endl;



    //commented out code below is verifying that 2 messages (using the same key) can be xor-ed together and then xor-ed
    //with the plain text of 1 of the inputs to get the plain text of the other message

    std::cout << "**********************************" << std::endl;
    std::cout << "attacker deciphering original message through xor: " << std::endl;
    std::cout << "**********************************"<< std::endl;
    std::cout << std::endl;

    //1st message
    std::string attackPlainText1 = "hellomyfroend";
    std::vector<uint8_t> plainTextAttackVector1 = std::vector<uint8_t>(attackPlainText1.begin(), attackPlainText1.end());
    std::string userKey = "password";

    std::cout << "      cypher text of input 1: ";
    std::vector<uint8_t> cypherTextAttackVector1 = encrypt(plainTextAttackVector1, userKey);
    for (char c: cypherTextAttackVector1) {
        std::cout << c;
    }
    std::cout << std::endl;

    //2nd message
    std::string attackPlainText2 = "Iamatthegroov";
    std::vector<uint8_t> plainTextAttackVector2 = std::vector<uint8_t>(attackPlainText2.begin(), attackPlainText2.end());

    std::cout << std::endl;
    std::vector<uint8_t> cypherTextAttackVector2 = encrypt(plainTextAttackVector2, userKey);
    std::cout <<"       decrypted vector: ";
    for (char c: cypherTextAttackVector1) {
        std::cout << c;
    }
    std::cout << std::endl;

    //xor 2 cyphertexts together
    for (int i = 0; i < attackPlainText1.size(); ++i) {
        cypherTextAttackVector2[i] = cypherTextAttackVector1[i] xor cypherTextAttackVector2[i];
    }
    //xor plaintext of message 2 with cypher text to get OG plain text message 1
    for (int i = 0; i < attackPlainText1.size(); ++i) {
        cypherTextAttackVector2[i] = plainTextAttackVector2[i] xor cypherTextAttackVector2[i];
    }

    std::cout <<"       xored 2 vector: ";
    for (char c: cypherTextAttackVector2) {
        std::cout << c;
    }
    std::cout << std::endl;

    //Modify part of a message using a bit-flipping attack. For example, try sending the message
    // "Your salary is $1000" encrypted with RC4. Modify the cyphertext so that when decrypted is
    // says that your salary is 9999 instead. Hint: this should just require using xor.

    //modification of a message using a bit-flipping attack
    std::cout << "**********************************"<< std::endl;
    std::cout << "bit flipping attack on cypher message: " << std::endl;
    std::cout << "**********************************"<< std::endl;
    std::cout << std::endl;
    std::string untouchedMessage = "Your salary is $1000";
    std::vector<uint8_t> untouchedMessageVector = std::vector<uint8_t>(untouchedMessage.begin(), untouchedMessage.end());
    std::string untouchedKey = "myfavekey";

    std::vector<uint8_t> cypherText = encrypt(untouchedMessageVector, untouchedKey);
    std::cout << "      cypher text before attack: ";
    for (char c: cypherText) {
        std::cout << c;
    }
    std::cout << std::endl;
    //20
    std::vector<uint8_t> attackVector = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0x08, 0x09, 0x09, 0x9};
    for (int i = 0; i < cypherText.size(); ++i) {
        cypherText[i] = cypherText[i] ^ attackVector[i];
    }

    //decryption after attack
    std::vector<uint8_t> decryptedMessageAfterAttack = encrypt(cypherText, untouchedKey);
    std::cout <<"       decrypted message AFTER attack: ";
    for (char c: decryptedMessageAfterAttack) {
        std::cout << c;
    }
    std::cout << std::endl << std::endl;







    return 0;
}






