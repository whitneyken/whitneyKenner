#include <iostream>
#include <fstream>
#include <string.h>
#include "functions.h"

using namespace std;

#define MAXREC  20000
#define input1 "file01" // "file1"
#define input2 "file02" // "file2"
#define out1 "out1"
#define out2 "out2"


#define search search1 //search2 
#define sort sort1  //sort2 //sort3

int readFile(string input, string a[])
{
  int size =0;
  ifstream infile(input.c_str());

  if(!infile.is_open())
  {
    cerr<<"Error in opening file \""<<input<<"\""<<endl;
    return -1;
  }
  string line;
  while(getline(infile, line))
  {
    a[size] = line;
    size= size +1 ;
  }
  infile.close();
  return size;

}

void printArray(string a[], int size)
{
  for(int i=0; i<size; i++)
  {
    cout<<a[i]<<endl;
  }
}

bool find_print_add_records(string a1[], string a2[], int size1, int size2, string output){
 ofstream outfile(output.c_str());
 if(!outfile.is_open())
  {
    cerr<<"Error in opening file \""<<output<<"\""<<endl;
    return false;
  }

 for ( int i= 0 ; i < size1 ; i++ ){
  if ( search(a2, size2, a1[i]) == false){
   outfile <<a1[i]<<endl;
  }
 }
 return true;
}

int main()
{
  string records1[MAXREC], records2[MAXREC]; //arrays to hold records in file1 and file2 respectively
  int size1, size2; //number of records in file1 and in file2 respectively
  size1=0; 
  size2=0;

  //reading files
  size1 = readFile(input1, records1);
  size2 = readFile(input2, records2);

  //sorting arrays
  sort(records1, size1);
  sort(records2, size2);

  //search for add-ons and print to output files
  bool find1 = find_print_add_records(records1, records2, size1, size2, out1);
  bool find2 = find_print_add_records(records2, records1, size2, size1, out2);

  return 0;
}
