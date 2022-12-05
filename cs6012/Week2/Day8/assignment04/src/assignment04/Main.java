package assignment04;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayOfInts = new ArrayList<>();
        arrayOfInts.add(2);
        arrayOfInts.add(-1);
        arrayOfInts.add(1);
        arrayOfInts.add(5);
        arrayOfInts.add(4);
        arrayOfInts.add(3);
        arrayOfInts.add(-2);
        arrayOfInts.add(-5);
        arrayOfInts.add(0);

        Comparator<Integer> comp = Integer::compareTo;

        SortUtil.quicksort(arrayOfInts, comp, "first");
        System.out.println(arrayOfInts);


    }
}