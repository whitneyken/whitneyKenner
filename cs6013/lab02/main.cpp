#include <iostream>
#include <unistd.h>
#include "string"


int main(int argc, char **argv) {
    int fds[2];
    int size = 0;

    //handling if an invalid number of arguments is passed in
    if (argc == 1) {
        std::cout << "invalid number of arguments" << std::endl;
        exit(1);
    }

    //initializing the pipe and checking for failure
    if (pipe(fds) < 0) {
        perror("pipe failed for some reason");
        exit(1);
    }

    //syscall to fork
    pid_t child_pid = fork();
    //checking for forking failure
    if (child_pid == -1) {
        perror("fork failed for some reason");
        exit(1);
    } else if (child_pid > 0) {
        std::cout << "parent " << getpid() << std::endl;
        size = strlen(argv[1]);
        if (close(fds[0]) == -1) {
            perror("failed to close read end of fds in parent");
            exit(1);
        }
        if (write(fds[1], &size, sizeof(size)) == -1) {
            perror("write failure");
            exit(1);
        }
        if (write(fds[1], argv[1], size) == -1) {
            perror("write failure");
            exit(1);
        }
        //could also use wait(nullptr), but this will wait for multiple children to stop, not a specific one
        if (waitpid(child_pid, nullptr, 0) == -1) {
            perror("waiting for child error");
            exit(1);
        }
        if (close(fds[1]) == -1) {
            perror("failed to close write end of fds in parent");
            exit(1);
        }
    } else {
        if (close(fds[1]) == -1) {
            perror("failed to close write end of fds in child");
            exit(1);
        }
        //read from the pipe, first to get the string length and then to get teh string
        if (read(fds[0], &size, sizeof(size)) == -1) {
            perror("read failure");
            exit(1);
        }
        char string[size];
        if (read(fds[0], string, size) == -1) {
            perror("read failure");
            exit(1);
        }
        if (close(fds[0]) == -1) {
            perror("failed to close read end of fds in child");
            exit(1);
        }
        std::cout << "child " << getpid() << std::endl;
        std::cout << "the passed in message is: " << string << " the number of chars in this word is: "
                  << sizeof(string) << std::endl;
        std::exit(0);
    }

    return 0;
}
