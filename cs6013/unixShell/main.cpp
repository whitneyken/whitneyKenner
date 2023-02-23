#include <iostream>
#include <vector>
#include "shelpers.hpp"
#include <unistd.h>
#include <cstring>

int main() {
    std::string input;
    while (std::getline(std::cin, input)) {
        std::cout << "your input is: " + input << std::endl;
        std::vector<std::string> tokens = tokenize(input);
        std::cout << "the tokens are: " << tokens[0] << " the token size is: " << tokens.size() << std::endl;
        std::vector<Command> commands = getCommands(tokens);
        std::cout << "commands size is: " << commands.size() << std::endl;
        //handle exit command
        if (commands[0].exec == "exit") {
            exit(0);
        }
        for (int i = 0; i < commands.size(); i++) {
            Command argument = commands[i];

            if (argument.exec == "cd") {
                if (argument.argv[1] == nullptr) {
                    std::cout << "Hello from inside the cd home thing";
                    //const char* homeDir = "HOME";
                    if (chdir(getenv("HOME")) < 0) {
                        perror("cd home failed for some reason");
                        exit(1);
                    }
                } else {
                    const char *directory = argument.argv[1];
                    char *path = getenv(directory);
                    if (path == nullptr) {
                        perror("invalid path requested");
                        exit(1);
                    }
                    if (chdir(directory) == -1) {
                        std::cout << "argument 1 is: " << argument.argv[1];
                        perror("cd to directory failed for some reason");
                        exit(1);
                    }
                }
            } else {
                pid_t child_pid = fork();
                if (child_pid < 0) {
                    perror("fork failed for some reason");
                    exit(1);
                } else if (child_pid > 0) {


                    std::cout << "done waiting for child??" << std::endl;

                    if (wait(nullptr) == -1) {
                        perror("waiting for child error");
                        exit(1);
                    }
                    //we are in parent-wait for child to complete
                    if (argument.fdStdin != 0) {
                        if (close(argument.fdStdin) < 0) {
                            perror("failed to close stdin file descriptor in parent");
                            exit(1);
                        }
                    }
                    if (argument.fdStdout != 1) {
                        if (close(argument.fdStdout) < 0) {
                            perror("failed to close stdout file descriptor in parent");
                            exit(1);
                        }
                    }

                } else {
                    //handle dup2 in child
                    if (dup2(argument.fdStdout, STDOUT_FILENO) < 0) {
                        perror("dup2 fdStout failed");
                        exit(1);
                    }
                    if (dup2(argument.fdStdin, STDIN_FILENO) < 0) {
                        perror("dup2 fdStdin failed");
                        exit(1);
                    }

                    //in the child-execute command
                    char *cStarExecutable = const_cast<char *>(argument.exec.c_str());
                    if ((execvp(cStarExecutable, const_cast<char **>(argument.argv.data()))) == -1) {
                        perror("execvp failure");
                        exit(1);
                    }

                    std::exit(0);


                }

            }
        }
    }
    return 0;
}

