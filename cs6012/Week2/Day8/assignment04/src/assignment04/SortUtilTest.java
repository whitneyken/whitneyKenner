package assignment04;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SortUtilTest {
    static int twoPowTen = 1024;
    static int twoPowFourteen = 16384;

    ArrayList<Integer> smallestBestArray;
    ArrayList<Integer> bestArray;
    ArrayList<Integer> smallestAverageArray;
    ArrayList<Integer> averageArray;
    ArrayList<Integer> smallestWorstArray;
    ArrayList<Integer> worstArray;

    Comparator<Integer> integerComparator;
    ArrayList<Integer> emptyTestArray;
    ArrayList<Integer> arrayOf2;
    ArrayList<Integer> arrayOf1;


    @BeforeEach
    void setUp() {
        //comparator
        integerComparator = Integer::compareTo;
        //all best case arrays
        smallestBestArray = SortUtil.generateBestCase(twoPowTen);
        bestArray = SortUtil.generateBestCase(twoPowFourteen);


        //all the average case arrays

        averageArray = SortUtil.generateAverageCase(twoPowFourteen);
        smallestAverageArray = SortUtil.generateAverageCase(twoPowTen);


        //all the worst case arrays
        worstArray = SortUtil.generateWorstCase(twoPowFourteen);
        smallestWorstArray = SortUtil.generateWorstCase(twoPowTen);

        emptyTestArray = new ArrayList<>();
        arrayOf2 = new ArrayList<>(2);
        arrayOf2.add(100);
        arrayOf2.add(11);

        arrayOf1 = new ArrayList<>(1);
        arrayOf1.add(90);
    }

    @AfterEach
    void tearDown() {
        bestArray = null;
        averageArray = null;
        worstArray = null;
        smallestBestArray = null;
        integerComparator = null;
        emptyTestArray = null;
        arrayOf2 = null;
        smallestAverageArray = null;
        smallestWorstArray = null;


    }

    @org.junit.jupiter.api.Test
    void mergeSort() {
    }

    @org.junit.jupiter.api.Test
    void quickSort() {


    }

    //testing empty arrays
    @Test
    void testQuickSortOnEmptyArrayLists() {
        //tests empty arrayLists for all types of pivot
        //all should throw an index out of bounds exception
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> SortUtil.quicksort(emptyTestArray, integerComparator, "first"));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> SortUtil.quicksort(emptyTestArray, integerComparator, "middle"));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> SortUtil.quicksort(emptyTestArray, integerComparator, "random"));

    }

    //tests so
    @Test
    void testQuickSortOnSortedArrayListsFirstPivot() {
        //testing the sorted arrays
        SortUtil.quicksort(smallestBestArray, integerComparator, "first");
        for (int i = 1; i < smallestBestArray.size(); i++) {
            Assertions.assertTrue(smallestBestArray.get(i - 1) <= smallestBestArray.get(i));
        }
        SortUtil.quicksort(bestArray, integerComparator, "first");
        for (int i = 1; i < bestArray.size(); i++) {
            Assertions.assertTrue(bestArray.get(i - 1) <= bestArray.get(i));
        }

    }

    @Test
    void testQuickSortOnSortedArrayListsMiddlePivot() {
        //testing on the middle pivot
        SortUtil.quicksort(smallestBestArray, integerComparator, "middle");
        for (int i = 1; i < smallestBestArray.size(); i++) {
            Assertions.assertTrue(smallestBestArray.get(i - 1) <= smallestBestArray.get(i));
        }
        SortUtil.quicksort(bestArray, integerComparator, "middle");
        for (int i = 1; i < bestArray.size(); i++) {
            Assertions.assertTrue(bestArray.get(i - 1) <= bestArray.get(i));
        }
    }

    @Test
    void testQuickSortOnSortedArrayListsRandomPivot() {
        //testing on random pivot
        SortUtil.quicksort(smallestBestArray, integerComparator, "random");
        for (int i = 1; i < smallestBestArray.size(); i++) {
            Assertions.assertTrue(smallestBestArray.get(i - 1) <= smallestBestArray.get(i));
        }
        SortUtil.quicksort(bestArray, integerComparator, "random");
        for (int i = 1; i < bestArray.size(); i++) {
            Assertions.assertTrue(bestArray.get(i - 1) <= bestArray.get(i));
        }
    }

    @Test
    void testQuickSortOnAverageArrayListsFirstPivot() {
        //testing the smallest arrays
        SortUtil.quicksort(smallestAverageArray, integerComparator, "first");
        for (int i = 1; i < smallestAverageArray.size(); i++) {
            Assertions.assertTrue(smallestAverageArray.get(i - 1) <= smallestAverageArray.get(i));
        }
        SortUtil.quicksort(averageArray, integerComparator, "first");
        for (int i = 1; i < averageArray.size(); i++) {
            Assertions.assertTrue(averageArray.get(i - 1) <= averageArray.get(i));
        }
    }

    @Test
    void testQuickSortOnAverageArrayListsMiddlePivot() {
        //testing on the middle pivot
        SortUtil.quicksort(smallestAverageArray, integerComparator, "middle");
        for (int i = 1; i < smallestAverageArray.size(); i++) {
            Assertions.assertTrue(smallestAverageArray.get(i - 1) <= smallestAverageArray.get(i));
        }
        SortUtil.quicksort(averageArray, integerComparator, "middle");
        for (int i = 1; i < averageArray.size(); i++) {
            Assertions.assertTrue(averageArray.get(i - 1) <= averageArray.get(i));
        }
    }

    @Test
    void testQuickSortOnAverageArrayListsRandomPivot() {
        //testing on the middle pivot
        SortUtil.quicksort(smallestAverageArray, integerComparator, "random");
        for (int i = 1; i < smallestAverageArray.size(); i++) {
            Assertions.assertTrue(smallestAverageArray.get(i - 1) <= smallestAverageArray.get(i));
        }
        SortUtil.quicksort(averageArray, integerComparator, "random");
        for (int i = 1; i < averageArray.size(); i++) {
            Assertions.assertTrue(averageArray.get(i - 1) <= averageArray.get(i));
        }
    }

    @Test
    void testQuickSortOnWorstArrayListsFirstPivot() {
        //testing on the middle pivot
        SortUtil.quicksort(smallestWorstArray, integerComparator, "first");
        for (int i = 1; i < smallestWorstArray.size(); i++) {
            Assertions.assertTrue(smallestWorstArray.get(i - 1) <= smallestWorstArray.get(i));
        }
        SortUtil.quicksort(worstArray, integerComparator, "first");
        for (int i = 1; i < worstArray.size(); i++) {
            Assertions.assertTrue(worstArray.get(i - 1) <= worstArray.get(i));
        }
    }


    @Test
    void testQuickSortOnWorstArrayListsMiddlePivot() {
        //testing on the middle pivot
        SortUtil.quicksort(smallestWorstArray, integerComparator, "middle");
        for (int i = 1; i < smallestWorstArray.size(); i++) {
            Assertions.assertTrue(smallestWorstArray.get(i - 1) <= smallestWorstArray.get(i));
        }
        SortUtil.quicksort(worstArray, integerComparator, "middle");
        for (int i = 1; i < worstArray.size(); i++) {
            Assertions.assertTrue(worstArray.get(i - 1) <= worstArray.get(i));
        }
    }


    @Test
    void testQuickSortOnWorstArrayListsRandomPivot() {
        //testing on the middle pivot
        SortUtil.quicksort(smallestWorstArray, integerComparator, "random");
        for (int i = 1; i < smallestWorstArray.size(); i++) {
            Assertions.assertTrue(smallestWorstArray.get(i - 1) <= smallestWorstArray.get(i));
        }
        SortUtil.quicksort(worstArray, integerComparator, "random");
        for (int i = 1; i < worstArray.size(); i++) {
            Assertions.assertTrue(worstArray.get(i - 1) <= worstArray.get(i));
        }
    }

    @Test
    void testQuickSortOnArrayOfTwoFirstPivot() {
        SortUtil.quicksort(arrayOf2, integerComparator, "first");
        Assertions.assertTrue(arrayOf2.get(0) < arrayOf2.get(1));
    }

    @Test
    void testQuickSortOnArrayOfTwoMiddlePivot() {
        SortUtil.quicksort(arrayOf2, integerComparator, "middle");
        Assertions.assertTrue(arrayOf2.get(0) < arrayOf2.get(1));
    }

    @Test
    void testQuickSortOnArrayOfTwoRandomPivot() {
        SortUtil.quicksort(arrayOf2, integerComparator, "random");
        Assertions.assertTrue(arrayOf2.get(0) < arrayOf2.get(1));
    }

    @Test
    void testQuickSortOnArrayOfOneFirstPivot() {
        SortUtil.quicksort(arrayOf1, integerComparator, "first");
        assertEquals(1, arrayOf1.size());
    }

    @Test
    public void mergeSortEmptyTest() {

        // Testing empty arraylist input
        assertThrows(IndexOutOfBoundsException.class, () -> {
            SortUtil.mergesort(emptyTestArray, Integer::compareTo);
        });

    }

    @Test
    public void mergeSortBestCaseTest() {
        SortUtil.mergesort(bestArray, Integer::compareTo);
        for (int i = 0; i < bestArray.size() - 1; i++) {
            Assertions.assertTrue(bestArray.get(i) < bestArray.get(i + 1));
        }
    }

    @Test
    public void mergeSortAverageCaseTest() {
        SortUtil.mergesort(averageArray, Integer::compareTo);
        for (int i = 0; i < averageArray.size() - 1; i++) {
            Assertions.assertTrue(averageArray.get(i) < averageArray.get(i + 1));
        }
    }

    @Test
    public void mergeSortWorstCaseTest() {

        // Before sorting making sure the arraylist is indeed in descending order
        for (int i = 0; i < worstArray.size() - 1; i++) {
            Assertions.assertFalse(worstArray.get(i) < worstArray.get(i + 1));
        }

        SortUtil.mergesort(worstArray, Integer::compareTo);

        // After sorting testing to see it changed to ascending order
        for (int i = 0; i < worstArray.size() - 1; i++) {
            Assertions.assertTrue(worstArray.get(i) < worstArray.get(i + 1));
        }

        SortUtil.mergesort(arrayOf2, Integer::compareTo);

        // Testing to see a two element array and how the sort handles it
        Assertions.assertEquals(arrayOf2.size(), 2);
        Assertions.assertTrue(worstArray.get(0) < worstArray.get(1));


        // Testing to see how the sort handles a single element arraylist
        SortUtil.mergesort(arrayOf1, Integer::compareTo);
        Assertions.assertEquals(arrayOf1.size(), 1);
    }
}