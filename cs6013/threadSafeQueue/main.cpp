#include <iostream>
#include <string>

bool testQueue(int num_producers, int num_consumers, int num_ints);
void serialRunTests();
int main(int argc, char **argv) {
//use argv to read in num_producers, num_consumers,
    if (argc != 4){
        std::cout << "An invalid number of arguments was entered" << std::endl;
    } else{
        //testing concurrent queue
        if (testQueue(std::stoi(argv[1]), std::stoi(argv[2]), std::stoi(argv[3]))){
            std::cout << "Success! multithreading worked" << std::endl;
        }else{
            std::cout << "bummer, final queue size does not match expected queue size" << std::endl;
        }
    }
    //testing Serial queue
    serialRunTests();
}
