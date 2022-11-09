package lab03;

import java.util.*;

public class Main {
    //private static final int ITER_COUNT = 100;


    public static void main(String[] args) {
        //I am going to use reverse as my method to test
        ArrayList<Integer> list = new ArrayList<>();
        //This array list will be used to store the elapsed times
        ArrayList<Long> listOfElapsedTimes = new ArrayList<>();
        //This list size is so we can modify it to check different size values
        int listSize = 1048576;
        long totalTime = 0;
        //This for loop fills the contents of the list with a series of ints from 0 to the size of the list
        for (int i = 0; i < listSize; i++){
            list.add(i);
        }

        int numTimesRun = 0;
        while(numTimesRun < 5000){
            long startTime = System.nanoTime();
            Collections.reverse(list);
            long timeElapsed = System.nanoTime() - startTime;
            //Store the elapsed time in the arraylist
            listOfElapsedTimes.add(timeElapsed);
            //System.out.println("the time elapsed for this iteration is: " + timeElapsed);
            numTimesRun++;
        }

        //Now we calculate the average:
        for (int i = 0; i < listOfElapsedTimes.size(); i++){
            totalTime += listOfElapsedTimes.get(i);
        }
        long avgTime = totalTime/listOfElapsedTimes.size();

        System.out.println("After 5,000 runs, the average run time is: " + avgTime + " nanoseconds");

    }



}