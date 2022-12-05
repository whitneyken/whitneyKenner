package assignment04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class SortUtil {
    private static int thresholdValue = 25;


    //Driver method for mergeSort. takes in an array list (that we want sorted) as a parameter, as well as a comparator
    //to be used in the actual sorting
    //returns nothing because the method is void and the list itself will be updated
    public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comp) throws IndexOutOfBoundsException{
        //throws an exception if an empty listed is passed in
        if (list.isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        //if the list size is less than 2, it is already sorted and can be returned
        if (list.size() < 2){
            return;
        }
        //tempArray made in the driver method so we don't waste time/resources creating new
        // tempArray every recursive call
        T[] tempArray = (T[]) new Object[list.size()];
        //calling the recursive method of mergeSort (I believe?)
        mergesort(list, tempArray, 0, tempArray.length - 1, comp);

    }

    private static <T> T[] arrayListToArray(ArrayList<T> list) {
        T[] arrayCopy = (T[]) new Object[list.size()];
        for (int index = 0; index < list.size(); index++) {
            arrayCopy[index] = list.get(index);
        }
        return arrayCopy;
    }

    //the recursive method of merge sort. takes in the list we are sorting, the temp array we are going to sort into, the
    //smallest index of our array (0), the last index of our array (length - 1), and the comparator used for sorting
    private static <T> void mergesort(ArrayList<T> list, Object[] tempArray, int left, int right, Comparator<? super T> comp) {
        //the base case, once we have called merge sort to the lowest level, this will be once we have called merge sort down
        //to the lowest level and we are sorting 1 element (i.e. the right and left side equal each other)
        if (right - left + 1 <= thresholdValue || left >= right) {
            insertionSort(list, left, right, comp);
            return;
        }
        //the center must be calculated each time as the array is split in half, this will change the center
        int center = (left + right) / 2;
        //recursively calling mergeSort on each half
        mergesort(list, tempArray, left, center, comp);
        mergesort(list, tempArray, center + 1, right, comp);
        //where the actual merge portion happens and data is copied over into the tempArray
        merge(list, tempArray, left, center, right, comp);

    }

    //This method performs the actual merging after the recursive splitting of the list is complete. it takes in the
    //original list, the array we will sort into, the left, center and right end of the portion of the array we are merging
    //as well as the comparator
    private static <T> void merge(ArrayList<T> list, Object[] tempArray, int left, int center, int right, Comparator<? super T> comp) {
        //this n dictates the length of what we are merging
        int n = right - left + 1;
        //this is the index of the array we are merging elements into
        int index = 0;
        //the starting value on the left
        int leftHalfIndex = left;
        //the starting value on the right
        int rightHalfIndex = center + 1;
        //while we have not reached the end of each respective side of the array we are sorting
        while (leftHalfIndex <= center && rightHalfIndex <= right) {
            //compare the 2 values based on the passed in comparator
            //if this given element at the index on the left side is less than the element at the index on the right side
            if (comp.compare(list.get(leftHalfIndex), list.get(rightHalfIndex)) < 0) {
                //set the tempArray at the given index to the value from the left side
                tempArray[index] = list.get(leftHalfIndex);
                //update the index for the left side
                leftHalfIndex++;
            } else {
                //otherwise set the tempArray at the given index to the value from the right side
                tempArray[index] = list.get(rightHalfIndex);
                //update the index for the right side
                rightHalfIndex++;
            }
            //either way we update the index value for the temp array afterwards
            index++;
        }
        //the next 2 while loops are reached once either of the above halves have reached their end
        //for this while loop, if the left hand has not reached the center, all remaining elements are copied over
        while (leftHalfIndex <= center) {
            tempArray[index] = list.get(leftHalfIndex);
            leftHalfIndex++;
            index++;
        }
        //if the right hand side has not reached the end, all remaining elements will be copied over
        while (rightHalfIndex <= right) {
            tempArray[index] = list.get(rightHalfIndex);
            rightHalfIndex++;
            index++;
        }
        //n needs to be updated every time (at top of this method). you cannot use tempArray.length or it will try to
        // copy over null values
        //this copies over the values from the sorted tempArray into the original arrayList
        for (index = 0; index < n; index++) {
            list.set(left + index, (T) tempArray[index]);
        }
    }

    //insertion sort is called once merge sort reaches a certain threshold value. the array is passed it along with the
    // left and right index so we know where to perform insertion sort. the comparator is passed in to perform the comparisons
    private static <T> void insertionSort(ArrayList<T> list, int left, int right, Comparator<? super T> comp) {
        //this for loop will iterate from the left index to the right index
        for (int index = left + 1; index <= right; index++) {
            //this for loop will set another index ot the value of the incrementing index and then decrement from there,
            // comparing the value of the element at this decrementing index to the value of the one before it
            for (int decrementingInnerIndex = index; decrementingInnerIndex > left && (comp.compare((list.get(decrementingInnerIndex - 1)), list.get(decrementingInnerIndex)) > 0); decrementingInnerIndex--) {
                //if the 2 elements are out of order, SWAP
                swapArrayListElements(list, decrementingInnerIndex, decrementingInnerIndex - 1);
            }
            //System.out.println(list);
        }
    }

    //this method swaps values at the provided indices for arrayLists. the array list is passed in along with the 2 indices
    //that should be swapped
    private static <T> void swapArrayListElements(ArrayList<T> list, int index, int index2) {
        //swap
        T temp = list.get(index);
        list.set(index, list.get(index2));
        list.set(index2, temp);
    }

    //a swap method for arrays, take an array, and the 2 indexes of the elements to be swapped as parameters
    private static <T> void swapArrayElements(Object[] list, int index, int index2) {
        //swap
        T temp = (T) list[index];
        list[index] = list[index2];
        list[index2] = temp;

    }

    //The driver method takes in the array list of elements to be sorted, a given comparator for how to sort them, and
    //a string of the pivot type which will be used to determine which of the 3 pivot options are used
    public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comp, String pivotType) throws IndexOutOfBoundsException{
        //throws an exception if an empty listed is passed in
        if (list.isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        //if the list size is less than 2, it is already sorted and can be returned
        if (list.size() < 2){
            return;
        }
        //copy everything over into an array to be handled by quickSort
        T[] tempArray = arrayListToArray(list);
        //this is a call to the method that will recursively call quickSort
        quicksort(tempArray, 0, tempArray.length - 1, comp, pivotType);
        //here the data is copied over from the tempArray back into the original array
        for (int k = 0; k < tempArray.length; k++) {
            list.set(k, tempArray[k]);
        }

    }

    //This separate setPivot method sets the specific type of pivot that quickSort will use based on a user passed in string
    //The method takes in the array to be sorted as input and a string of the desired pivot type, as well as the given low and
    // high index so that we have an appropriate pivot within the given range of each call to quicksort
    private static void setPivot(Object[] array, String pivotType, int lowIndex, int highIndex) {
        //one of the pivot options: the first element
        if (pivotType.equals("first")) {
            //will be swapped with the element at the end
            swapArrayElements(array, lowIndex, highIndex);
            //another pivot option: middle
        } else if (pivotType.equals("middle")) {
            int middle = ((highIndex + lowIndex) / 2);
            //swap middle with the last element
            swapArrayElements(array, middle, highIndex);
            //otherwise random element
        } else {
            Random random = new Random(System.currentTimeMillis());
            int randomIndex = random.nextInt(lowIndex, highIndex);
            //swapped with last element
            swapArrayElements(array, randomIndex, highIndex);
        }
    }
    //driver method that doesn't take a string for autograder lol
    private static <T> void quicksort(Object[] tempArray, int low, int high, Comparator<? super T> comp) {
        //this is the base case: once low is greater than or equal to the high index, aka everything has been sorted
        if (low >= high) {
            //if base case is met, break out
            return;
        }
        String pivotType = "middle";
        //this method does the actual partitioning /sorting component within quicksort
        int pIndex = partition(tempArray, low, high, comp, pivotType);
        //recursive calls on each half of the array
        quicksort(tempArray, low, pIndex - 1, comp, pivotType);
        quicksort(tempArray, pIndex + 1, high, comp, pivotType);
    }

    //the quicksort method, takes in the array, the low inde, the high index, the provided comparator, and the pivot type
    private static <T> void quicksort(Object[] tempArray, int low, int high, Comparator<? super T> comp, String pivotType) {
        //this is the base case: once low is greater than or equal to the high index, aka everything has been sorted
        if (low >= high) {
            //if base case is met, break out
            return;
        }
        //this method does the actual partitioning /sorting component within quicksort
        int pIndex = partition(tempArray, low, high, comp, pivotType);
        //recursive calls on each half of the array
        quicksort(tempArray, low, pIndex - 1, comp, pivotType);
        quicksort(tempArray, pIndex + 1, high, comp, pivotType);
    }


    //the partition method does the primary sorting in quickSort. takes in the array we will be modifying, the high and
    // low index, the comparator, and the string of the pivot type
    private static <T> int partition(Object[] tempArray, int lowIndex, int highIndex, Comparator<? super T> comp, String pivotType) {
        //the low index is designated as our left index/"pointer"
        int leftIndexPointer = lowIndex;
        //call to our set pivot method which sets the pivot based on the user input string
        setPivot(tempArray, pivotType, lowIndex, highIndex);
        //pull the T pivot to use in comparison
        T pivot = (T) tempArray[highIndex];
        //the high index is designated as our right index/pointer
        int rightIndexPointer = highIndex;
        //while the left and right index pointers have not met
        while (leftIndexPointer < rightIndexPointer) {
            //while the element at the left index pointer is less than the pivot
            while (comp.compare((T) tempArray[leftIndexPointer], pivot) <= 0 && leftIndexPointer < rightIndexPointer) {
                //move the left index pointer to the next element (to the right)
                leftIndexPointer++;
            }
            //while the element at the left index pointer is greater than the pivot
            while (comp.compare((T) tempArray[rightIndexPointer], pivot) >= 0 && leftIndexPointer < rightIndexPointer) {
                //move the right index pointer to the next element (to the left)
                rightIndexPointer--;
            }
            //this is reached once the right and left pointers point at elements that need to be swapped
            //swap elements
            swapArrayElements(tempArray, rightIndexPointer, leftIndexPointer);
        }
        //this is the final thing reached once everything has been placed in relation to the pivot (and the pivot is in it's correct location
        swapArrayElements(tempArray, leftIndexPointer, highIndex);
        //returns the index of the pivot
        return leftIndexPointer;

    }


    public static ArrayList<Integer> generateBestCase(int size){
        ArrayList<Integer> bestCaseArray = new ArrayList<>();
        for (int i = 0; i < size; i++){
            bestCaseArray.add(i);

        }
        return bestCaseArray;
    }

    public static ArrayList<Integer> generateAverageCase(int size) {
        ArrayList<Integer> averageCaseArray = new ArrayList<>();
        Random rand = new Random(1);
        for (int i = 0; i < size; i++) {
            averageCaseArray.add(i);

        }
        for (int i = averageCaseArray.size() - 1; i > 0; i--) {
            swapArrayListElements(averageCaseArray, i, rand.nextInt(i + 1));
        }
        return averageCaseArray;
    }

    public static ArrayList<Integer> generateWorstCase(int size){
        ArrayList<Integer> worstCaseArray = new ArrayList<>();
        for (int i = size -1; i > 0; i--){
            worstCaseArray.add(i);
        }
        return worstCaseArray;
    }

}
