package lab01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiffUtilTest {
    private int[] arr1, arr2, arr3, arr4, arr5;



    @BeforeEach
    protected void setUp() {
        arr1 = new int[0];
        arr2 = new int[] { 3, 3, 3 };
        arr3 = new int[] { 52, 4, -8, 0, -17 };
        arr4 = new int[] {-1, -22, -5, -10};
        arr5 = new int[] {-1, 1, 9, 2, 16};


    }

    @AfterEach
    void tearDown() {
        arr1 = null;
        arr2 = null;
        arr3 = null;
        arr4 = null;
        arr5 = null;
    }
    @Test
    public void emptyArray() {
        assertEquals(-1, DiffUtil.findSmallestDiff(arr1));
    }
    @Test
    public void allArrayElementsEqual() {
        assertEquals(0, DiffUtil.findSmallestDiff(arr2));
    }
    @Test
    public void smallRandomArrayElements() {
        assertEquals(4, DiffUtil.findSmallestDiff(arr3));
    }
    @Test
    public void handlesRandomNegatives(){
        assertEquals(4, DiffUtil.findSmallestDiff(arr4));
    }
    @Test
    public void firstTwoElements(){
        assertEquals(1, DiffUtil.findSmallestDiff(arr5));
    }

}