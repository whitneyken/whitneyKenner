package assignment05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class BinarySearchTreeTest {
    BinarySearchTree<Integer> intSearchTree;
    BinarySearchTree<Integer> otherIntSearchTree;
    BinarySearchTree<Integer> emptySearchTree;
    List<Integer> collectionOfInts;
    List<Integer> secondCollectionOfInts;
    List<Integer> nullList;
    List<Integer> errorIntList;



    @BeforeEach
    void setUp() {
        intSearchTree = new BinarySearchTree<>();
        otherIntSearchTree = new BinarySearchTree<>();
        emptySearchTree = new BinarySearchTree<>();
        collectionOfInts = Arrays.asList(4, 16, 3, 44, 0);
        nullList = null;
        errorIntList = Arrays.asList(null, 22, 2, 5, 6, 6);
        secondCollectionOfInts = Arrays.asList(7, 8, 22, 11, 9, 9, -2);
    }

    @AfterEach
    void tearDown() {
        intSearchTree = null;
        collectionOfInts = null;
        errorIntList = null;
        secondCollectionOfInts = null;
    }

    //test for the add method
    @Test
    void testAdd() {
        //tests that regular add returns true
        Assertions.assertTrue(intSearchTree.add(3));
        //tests that trying to add a duplicate returns false
        Assertions.assertFalse(intSearchTree.add(3));
        Assertions.assertTrue(intSearchTree.add(0));
        //tests that trying to add a null item throws a null pointer exception
        Assertions.assertThrows(NullPointerException.class, () -> intSearchTree.add(null));
        Assertions.assertTrue(intSearchTree.add(99));
    }

    //tests for the addAll method
    @Test
    void testAddAll() {
        //tests that true is returned when something is added
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        //tests that a null pointer exception is thrown when a null list is added
        Assertions.assertThrows(NullPointerException.class, () -> intSearchTree.addAll(nullList));
        //tests that false is returned when the search tree is unchanged
        Assertions.assertFalse(intSearchTree.addAll(collectionOfInts));
        //tests that if an individual item within a list is null, that a null pointer exception is thrown
        Assertions.assertThrows(NullPointerException.class, () -> intSearchTree.addAll(errorIntList));
    }

    //tests that clear sets the root to null
    @Test
    void testClear() {
        //just to makes sure that items were added
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        intSearchTree.clear();
        //assert the tree is null after clearing it
        Assertions.assertNull(intSearchTree.root());

    }

    //tests that contains returns true if the item is contained, false if not contained, or throws
    //a null pointer exception if the item is null
    @Test
    void testContains() {
        //verify adding works
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        //assert that contains returns true for something that has been added
        Assertions.assertTrue(intSearchTree.contains(4));
        //assert false is returned when the item is not contained
        Assertions.assertFalse(intSearchTree.contains(9));
        //assert an exception is thrown if a null item is searched for
        Assertions.assertThrows(NullPointerException.class, () -> intSearchTree.contains(null));


    }

    @Test
    void testContainsAll() {
        //verify adding works
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        //verify contains all returns true when all elements are contained
        Assertions.assertTrue(intSearchTree.containsAll(collectionOfInts));
        //verify contains all returns false if not all elements are contained
        Assertions.assertFalse(intSearchTree.containsAll(secondCollectionOfInts));
        //verify that a null pointer exception is thrown if a null item is checked
        Assertions.assertThrows(NullPointerException.class, () -> intSearchTree.containsAll(errorIntList));
        //verify that a null collection throws an exception
        Assertions.assertThrows(NullPointerException.class, () -> intSearchTree.containsAll(nullList));
    }

    //test that the proper element is returned for the first method
    @Test
    void testFirst() {
        //verify adding works
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        Assertions.assertTrue(otherIntSearchTree.addAll(secondCollectionOfInts));
        //verify the smallest value is returned
        Assertions.assertEquals(0, intSearchTree.first());
        Assertions.assertEquals(-2, otherIntSearchTree.first());
        //tests that a null pointer exception is thrown if the tree is null
        Assertions.assertThrows(NoSuchElementException.class, () -> emptySearchTree.first());
    }

    @Test
    void testIsEmpty() {
        //verify adding works
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        //test that an empty tree is empty and a non-empty tree is NOT empty
        Assertions.assertTrue(emptySearchTree.isEmpty());
        Assertions.assertFalse(intSearchTree.isEmpty());
        //tests that after a tree is cleared, it will return empty
        intSearchTree.clear();
        Assertions.assertTrue(intSearchTree.isEmpty());


    }

    //tests that the largest element is returned when last is called
    @Test
    void testLast() {
        //verify adding works
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        Assertions.assertTrue(otherIntSearchTree.addAll(secondCollectionOfInts));
        //verify the largest value is returned
        Assertions.assertEquals(44, intSearchTree.last());
        Assertions.assertEquals(22, otherIntSearchTree.last());
        //tests that a null pointer exception is thrown if the tree is null
        Assertions.assertThrows(NoSuchElementException.class, () -> emptySearchTree.last());
    }

    @Test
    void testRemove() {
        //verify adding works
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        Assertions.assertTrue(otherIntSearchTree.addAll(secondCollectionOfInts));
        //verify the largest value is returned
        Assertions.assertTrue(intSearchTree.remove(3));
        Assertions.assertFalse(intSearchTree.remove(98));
        Assertions.assertTrue(otherIntSearchTree.remove(-2));
        Assertions.assertFalse(otherIntSearchTree.remove(-2));
        //test on a null item
        Assertions.assertThrows(NullPointerException.class, () -> intSearchTree.remove(null));
        //test on an empty tree
        Assertions.assertFalse(emptySearchTree.remove(5));
    }

    @Test
    void testRemoveAll() {
        //verify adding works
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        Assertions.assertTrue(otherIntSearchTree.addAll(secondCollectionOfInts));
        Assertions.assertTrue(intSearchTree.removeAll(collectionOfInts));
        Assertions.assertEquals(0, intSearchTree.size());
        Assertions.assertFalse(intSearchTree.removeAll(collectionOfInts));
        intSearchTree.add(22);
        Assertions.assertTrue(intSearchTree.removeAll(secondCollectionOfInts));
        Assertions.assertThrows(NullPointerException.class, () -> intSearchTree.removeAll(errorIntList));
    }

    @Test
    void testSize() {
        //verify adding works
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        Assertions.assertTrue(otherIntSearchTree.addAll(secondCollectionOfInts));
        //assert size works
        Assertions.assertEquals(5, intSearchTree.size());
        Assertions.assertEquals(6, otherIntSearchTree.size());
        intSearchTree.remove(44);
        Assertions.assertEquals(4, intSearchTree.size());
        intSearchTree.removeAll(collectionOfInts);
        Assertions.assertEquals(0, intSearchTree.size());
        otherIntSearchTree.clear();
        Assertions.assertEquals(0, otherIntSearchTree.size());
    }

    @Test
    void testToArrayList() {
        //verify adding works
        Assertions.assertTrue(intSearchTree.addAll(collectionOfInts));
        Assertions.assertTrue(otherIntSearchTree.addAll(secondCollectionOfInts));
        ArrayList<Integer> myList= intSearchTree.toArrayList();
        for (int i = 1; i < myList.size(); i++){
            Assertions.assertTrue(myList.get(i -1) < myList.get(i));
        }

        System.out.println(otherIntSearchTree.toArrayList());
    }

}