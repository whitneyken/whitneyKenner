#include <iostream>
#include <vector>
#include "shelpers.hpp"
#include <unistd.h>
#include <readline/readline.h>
//list of
//char buffer = MAX_INPUT;
//char *cwd = getcwd(buffer, buffer.)

int main() {

    while (true) {
        std::string temp = "> ";
        const char* prompt = temp.c_str();
        //readline allows for tab autocompletion
        char *buffer = readline(prompt);
        std::vector<std::string> tokens = tokenize(buffer);
        std::vector<Command> commands = getCommands(tokens);

        for (int i = 0; i < commands.size(); i++) {
            Command command = commands[i];

            //handle an exit command
            if (command.exec == "exit") {
                exit(0);
            }
            //handle if user executable input is "cd", if this is the case, we do not want to fork/execvp
            if (command.exec == "cd") {
                //if the command has no parameter, change directories to the home directory
                if (command.argv[1] == nullptr) {
                    /*The getenv() function searches the environment list to find the
                     environment variable name, and returns a pointer to the
                     corresponding value string. */
                    if (chdir(getenv("HOME")) < 0) {
                        perror("cd home failed for some reason");
                        exit(1);
                    }
                    //otherwise a directory has been provided to move to
                } else {
                    const char *directory = command.argv[1];
                    //chdir() is the system call that will change the working directory
                    if (chdir(directory) == -1) {
                        perror("invalid file request");
                        exit(1);
                    }
                }
                //otherwise it is not a cd command and it is time to fork and then execvp the command
            } else {
                //call to fork
                pid_t child_pid = fork();
                if (child_pid == -1) {
                    perror("fork failed for some reason");
                    exit(1);
                } else if (child_pid > 0) {
                    //in the parent
                    //waits for all children to finish
                    if (wait(nullptr) == -1) {
                        perror("waiting for child error");
                        exit(1);
                    }
                    //if the fdStdin does not equal 0, it is attached to a file or file descriptor and needs to be closed
                    if (command.fdIn != 0) {
                        if (close(command.fdIn) < 0) {
                            perror("failed to close stdin file descriptor in parent");
                            exit(1);
                        }
                    }
                    //if the fdStdout does not equal 1, it is attached to a file or file descriptor and needs to be closed
                    if (command.fdOut != 1) {
                        if (close(command.fdOut) < 0) {
                            perror("failed to close stdout file descriptor in parent");
                            exit(1);
                        }
                    }
                } else {
                    //inside the child
                    /*dup2 call uses the descriptor number specified by the user, If the copy is successfully created,
                     then the original and copy file descriptors may be used interchangeably.
                    They both refer to the same open file description and thus share file offset and file status flags.*/
                    if (dup2(command.fdOut, STDOUT_FILENO) < 0) {
                        perror("dup2 fdStout failed");
                        exit(1);
                    }
                    if (dup2(command.fdIn, STDIN_FILENO) < 0) {
                        perror("dup2 fdStdin failed");
                        exit(1);
                    }
                    /*When execvp() is executed, the program file given by the first argument will be loaded into the
                     caller's address space and over-write the program there. Then, the second argument will be
                     provided to the program and starts the execution. As a result, once the specified program file
                     starts its execution, the original program in the caller's address space is gone and is replaced
                     by the new program.*/
                    char *cStarExecutable = const_cast<char *>(command.exec.c_str());
                    if ((execvp(cStarExecutable, const_cast<char **>(command.argv.data()))) == -1) {
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

