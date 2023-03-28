#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>

extern char** environ;
static const char * sh = "/bin/sh";
void success(){
  const char  * const argv[2] = {sh, NULL};
  puts("successful login!\n");
  execve(sh, argv, environ);
}

void failure(){
  puts("wrong password\n");
}


int login(){
  char password[32];
  int fd = open("password.txt", O_RDONLY);
  printf("enter your password:\n");
  int pwLen = read(fd, password, 1000); //just read the whole file...
  close(fd);
  return memcmp(password, "superSecretPassword", pwLen) == 0;
}


int main(){
  int res = login();
  if(res){
	success();
  } else {
	failure();
  }

  puts("exiting in main\n");
  return 0;
}
