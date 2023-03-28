#include "functions.h"

bool search1 ( string a[], int size, string key)
{
  bool found = false;
  int i;

  a[size] = key;
  for ( i = 0; key != a[i]; i++ )
    ;
  if ( i < size ) {
    found = true;
  }

  return found;
}

bool search2 ( string a[], int size, string key)
{
  bool found = false;
  int low = 0, high = size - 1;

  while ( high >= low ) {
    int mid = ( low + high ) / 2;
    if ( key < a[mid] )
      high = mid - 1;
    else if ( key > a[mid] )
      low = mid + 1;
    else {
      found = true;
      break;
    }
  }
  return found;
}



void sort1(string a[], int n){
 int i,j;
 int iMin;
 
 for (j = 0; j < n-1; j++) {
  iMin = j;
   for ( i = j+1; i < n; i++) {
    if (a[i] < a[iMin]) {
     iMin = i;
    }
   }
 
   if(iMin != j) {
    swap(a[j], a[iMin]);
   }
 }

}

void sort2(string a[], int n){
 for(int x=0; x<n; x++){
  for(int y=0; y<n-1; y++){
   if(a[y]>a[y+1]){
    string temp = a[y+1];
    a[y+1] = a[y];
    a[y] = temp;
   }
  }
 }
}


/* Helper function for finding the min of two strings */
string min(string x, string y)
{
    if(x < y)
    {
        return x;
    }
    else
    {
        return y;
    }
}

/* left is the index of the leftmost element of the subarray; right is one
 * past the index of the rightmost element */
void sort3_helper(string input[], int left, int right, string scratch[])
{
    /* base case: one element */
    if(right == left + 1)
    {
        return;
    }
    else
    {
        int i = 0;
        int length = right - left;
        int midpoint_distance = length/2;
        /* l and r are to the positions in the left and right subarrays */
        int l = left, r = left + midpoint_distance;

        /* sort each subarray */
        sort3_helper(input, left, left + midpoint_distance, scratch);
        sort3_helper(input, left + midpoint_distance, right, scratch);

        /* merge the arrays together using scratch for temporary storage */
        for(i = 0; i < length; i++)
        {
            /* Check to see if any elements remain in the left array; if so,
             * we check if there are any elements left in the right array; if
             * so, we compare them.  Otherwise, we know that the merge must
             * use take the element from the left array */
            if(l < left + midpoint_distance &&
                    (r == right || min(input[l], input[r]) == input[l]))
            {
                scratch[i] = input[l];
                l++;
            }
            else
            {
                scratch[i] = input[r];
                r++;
            }
        }
        /* Copy the sorted subarray back to the input */
        for(i = left; i < right; i++)
        {
            input[i] = scratch[i - left];
        }
    }
}

/* returns true on success.*/

int sort3(string input[], int size)
{
    string scratch[size];
    if(scratch != nullptr)
    {
        sort3_helper(input, 0, size, scratch);
        return 1;
    }
    else
    {
        return 0;
    }
}

