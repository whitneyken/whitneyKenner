package assignment03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class BinarySearchSetTest {

    @Test
    //This test will test the add method
    public void testAddOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        //assert the size is 0 on creation
        Assertions.assertEquals(0, setOfInts.size());
        //assert that capacity is 10 on creation
        Assertions.assertEquals(10, setOfInts.capacity());
        int firstInt = 1;
        setOfInts.add(firstInt);
        //assert that an element is properly added
        Assertions.assertEquals(1, setOfInts.get(0));
        //add a bunch more ints
        int secondInt = 0;
        setOfInts.add(secondInt);
        int thirdInt = 2;
        setOfInts.add(thirdInt);
        int fourthInt = -1;
        setOfInts.add(fourthInt);
        int fifthInt = 3;
        setOfInts.add(fifthInt);
        int sixthInt = -3;
        setOfInts.add(sixthInt);
        //assert that elements are being sorted as they are added
        Assertions.assertEquals(-3, setOfInts.get(0));
        Assertions.assertEquals(3, setOfInts.get(5));
        //assert the size is correct
        Assertions.assertEquals(6, setOfInts.size());
        int ineligibleInt = 1;
        int ineligibleInt2 = -3;
        int ineligibleInt3 = 0;
        setOfInts.add(ineligibleInt);
        setOfInts.add(ineligibleInt2);
        setOfInts.add(ineligibleInt3);
        //assert that ineligible ints are not added
        Assertions.assertEquals(6, setOfInts.size());

    }

    @Test
    public void testAddAllOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        List<Integer> list1 = Arrays.asList(2, 3, 6, 9, 1, 5, 90, 13, 44, 0, -4, 4, 4, 4, 4);
        //assert the initial size and capacity
        Assertions.assertEquals(0, setOfInts.size());
        Assertions.assertEquals(10, setOfInts.capacity());
        //assert that elements were able to be added
        Assertions.assertTrue(setOfInts.addAll(list1));
        //assert that the duplicate elements were not added
        Assertions.assertEquals(12, setOfInts.size());
        //assert the capacity has doubled
        Assertions.assertEquals(20, setOfInts.capacity());

        List<Integer> list2 = Arrays.asList(4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
        setOfInts.addAll(list2);
        //assert this did nothing
        Assertions.assertEquals(12, setOfInts.size());
    }

    @Test
    public void testFirstOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        //assertion that the capacity is set on construction
        try {
            //testing first() when it does not exist
            setOfInts.first();
        } catch (NoSuchElementException e) {
            System.out.println("An element that does not exist");
        }
        int firstInt = 20;
        setOfInts.add(firstInt);
        //assert that the first one is properly returned
        Assertions.assertEquals(20, setOfInts.first());
        int secondInt = -40;
        setOfInts.add(secondInt);
        //assert the first was replaced
        Assertions.assertEquals(-40, setOfInts.first());
    }

    @Test
    public void testLastOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        try {
            //testing last() when it does not exist
            setOfInts.last();
        } catch (NoSuchElementException e) {
            System.out.println("Cannot delete an element that doesn't exist");
        }
        int firstInt = -100;
        setOfInts.add(firstInt);
        //testing last() when it is also the first
        Assertions.assertEquals(-100, setOfInts.last());

    }

    @Test
    public void testClearOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        List<Integer> list1 = Arrays.asList(2, 3, 6, 9, 1, 5, 90, 13, 44, 0, -4, 4, 4, 4, 4);
        setOfInts.addAll(list1);
        setOfInts.clear();
        //assert the array has been properly cleared
        Assertions.assertEquals(0, setOfInts.size());
        Assertions.assertEquals(10, setOfInts.capacity());
        Assertions.assertNull(setOfInts.get(0));

    }

    @Test
    public void testContainsOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        List<Integer> list1 = Arrays.asList(2, 3, 6, 9, 1, 5, 90, 13, 44, 0, -4, 4, 4, 4, 4);
        setOfInts.addAll(list1);
        //assert contains properly works
        Assertions.assertTrue(setOfInts.contains(4));
        Assertions.assertFalse(setOfInts.contains(-1));

    }

    @Test
    public void testContainsAllOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        List<Integer> list1 = Arrays.asList(2, 3, 6, 9, 1, 5, 90, 13, 44, 0, -4, 4, 4, 4, 4);
        List<Integer> list2 = Arrays.asList(1, 44, 4, 2); //all contained within larger list
        List<Integer> list3 = Arrays.asList(5, 88, -9, 11, 222); //only one is contained
        setOfInts.addAll(list1);
        //assert that at least 1 element is contained within the array
        Assertions.assertTrue(setOfInts.containsAll(list2));
        Assertions.assertFalse(setOfInts.containsAll(list3));

    }

    @Test
    public void testIsEmptyOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        List<Integer> list1 = Arrays.asList(2, 3, 6, 9, 1, 5, 90, 13, 44, 0, -4, 4, 4, 4, 4);
        //assert the array is empty
        Assertions.assertTrue(setOfInts.isEmpty());
        setOfInts.addAll(list1);
        //assert it is no longer empty
        Assertions.assertFalse(setOfInts.isEmpty());

    }

    @Test
    public void testIteratorOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        List<Integer> list1 = Arrays.asList(2, 3, 6, 9, 1, 5, 90, 13, 44, 0, -4, 4, 4, 4, 4);
        setOfInts.addAll(list1);
        Assertions.assertEquals(12, setOfInts.size());
       Iterator<Integer> myIterator =  setOfInts.iterator();
       //assert iterator has next
       try {
           while (myIterator.hasNext()) {
               int nextInt = myIterator.next();
               //remove all odd elements
               if (nextInt == 90){
                   myIterator.remove();
               }
               //myIterator.remove();
           }
       }catch (NoSuchElementException e){
           System.out.println("Caught an out of bounds element");
       }
       Assertions.assertFalse(setOfInts.contains(90));
        System.out.println(Arrays.toString(setOfInts.toArray()));
        System.out.println("My set size is: " + setOfInts.size());
//        for (int i = 0; i < setOfInts.size() -1; i++){
//            Assertions.assertTrue((setOfInts.get(i) % 2) != 0);
//        }
       }


    @Test
    public void testRemoveOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        List<Integer> list1 = Arrays.asList(2, 3, 6, 9, 1, 5, 90, 13, 44, 0, -4, 4, 4, 4, 4);
        setOfInts.addAll(list1);
        setOfInts.remove(9);
        //assert the element has been succesfully removed
        Assertions.assertFalse(setOfInts.contains(9));
        //assert the size was properly adjusted
        Assertions.assertEquals(11, setOfInts.size());
        setOfInts.remove(44);
        //assert can independently remove multiple elements
        Assertions.assertFalse(setOfInts.contains(44));
        setOfInts.remove(2);
        //assert you can remove the first element
        Assertions.assertFalse(setOfInts.contains(2));
        setOfInts.remove(4);
        //assert you can remove the last one
        Assertions.assertFalse(setOfInts.contains(4));
    }

    @Test
    public void testRemoveAllOnInts() {
        BinarySearchSet<Integer> setOfInts = new BinarySearchSet<>();
        List<Integer> list1 = Arrays.asList(2, 3, 6, 9, 1, 5, 90, 13, 44, 0, -4, 4, 4, 4, 4);
        List<Integer> list2 = Arrays.asList(1, 44, 4, 2); //all contained within larger list
        List<Integer> list3 = Arrays.asList(5, 88, -9, 11, 222); //only one is contained
        setOfInts.addAll(list1);
        //assert removeAll returns true when an element is removed
        Assertions.assertTrue(setOfInts.removeAll(list2));
        //assert size was accurately reduced
        Assertions.assertEquals(8, setOfInts.size());
        //assert it returns true even when not all elements are contained within the array
        Assertions.assertTrue(setOfInts.removeAll(list3));
        //System.out.println(setOfInts);
        //assert everything is removed
        Assertions.assertTrue(setOfInts.removeAll(list1));
        //assert this is empty after removing everything
        Assertions.assertTrue(setOfInts.isEmpty());
    }



    @Test
    public void testSquids(){
        //tests to check this works on my custom comparator and object
        Squid squid = new Squid  (10, 12, "Steve");
        Squid squid2 = new Squid (8, 8, "Chase");
        Squid squid3 = new Squid (0, 3, "Whit");
        Squid squid4 = new Squid (100, 1, "Felix");
        Squid squid5 = new Squid (4, 4, "Nicole");
        Squid squid6 = new Squid (11, 14, "Celeste");
        List<Squid> list1 = Arrays.asList(squid, squid2, squid3, squid4, squid5, squid6);
        BinarySearchSet<Squid> squidList = new BinarySearchSet<>(new SquidComparator());
        squidList.add(squid);
        //check that add works
        Assertions.assertEquals(1, squidList.size());
        squidList.addAll(list1);
        //check that addAll works
        Assertions.assertEquals(6, squidList.size());
        //check that contains works
        Assertions.assertTrue(squidList.contains(squid4));
        squidList.remove(squid6);
        //check that remove works
        Assertions.assertFalse(squidList.contains(squid6));
        squidList.removeAll(list1);
        //check remove all works
        Assertions.assertEquals(0, squidList.size());
        //check isEmpty
        Assertions.assertTrue(squidList.isEmpty());

    }
}