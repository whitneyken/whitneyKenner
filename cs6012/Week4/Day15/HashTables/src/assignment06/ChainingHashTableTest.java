package assignment06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashTableTest {
    ChainingHashTable hashTestBad;
    ChainingHashTable hashTestMed;
    ChainingHashTable hashTestGood;
    BadHashFunctor badFunctor;
    MediocreHashFunctor medFunctor;
    GoodHashFunctor goodFunctor;
    ArrayList<String> myStrings;
    ArrayList<String> sameStrings;
    ArrayList<String> nullArrayList;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        badFunctor = new BadHashFunctor();
        medFunctor = new MediocreHashFunctor();
        goodFunctor = new GoodHashFunctor();
        hashTestBad = new ChainingHashTable(10, badFunctor);
        hashTestMed = new ChainingHashTable(100, medFunctor);
        hashTestGood = new ChainingHashTable(100, goodFunctor);
        myStrings = new ArrayList<>();
        myStrings.add("whit");
        myStrings.add("brown");
        myStrings.add("lady");
        myStrings.add("dog");
        myStrings.add("underwear");
        myStrings.add("market");
        sameStrings = new ArrayList<>();
        sameStrings.add("meow");
        sameStrings.add("meow");
        sameStrings.add("meow");
        sameStrings.add("meow");
        sameStrings.add("meow");
        //nullArrayList = new ArrayList<>();



    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        badFunctor = null;
        hashTestBad = null;
        myStrings = null;
        sameStrings = null;
        medFunctor = null;
    }


    //the size is set to 0 in the constructor so this verifies that the constructor works
    @Test
    void testConstructor() {
        //tests size being set to 0
        assertEquals(0, hashTestBad.size());

        //Assertions.assertThrows(NullPointerException.class, () -> );
    }

    //tests that add works
    @Test
    void testAdd() {
        //test that add works
        Assertions.assertTrue(hashTestBad.add("whit"));
        //test the size is modified with adding
        Assertions.assertEquals(1, hashTestBad.size());
        //test duplicates are not added
        Assertions.assertFalse(hashTestBad.add("whit"));
        //test that something with the same hash value is still added
        Assertions.assertTrue(hashTestBad.add("where"));
        //assert a null string returns false
        Assertions.assertThrows(NullPointerException.class, () -> hashTestBad.add(null));
    }

    //tests add all
    @Test
    void testAddAll() {
        //add all returns true with all new strings
        Assertions.assertTrue(hashTestBad.addAll(myStrings));
        //the size is accurately updated
        Assertions.assertEquals(6, myStrings.size());
        //returns false when already added strings try to be added
        Assertions.assertFalse(hashTestBad.addAll(myStrings));
        //returns true when a bunch of the same strings are added
        Assertions.assertTrue(hashTestBad.addAll(sameStrings));
        //returns the size updated correctly when there are a ton of duplicates
        Assertions.assertEquals(7, hashTestBad.size());
        hashTestBad.display();

    }

    //tests clear
    @Test
    void testClear() {
        //add all returns true with all new strings
        Assertions.assertTrue(hashTestBad.addAll(myStrings));
        //clear
        hashTestBad.clear();
        //assert the size is now 0 after clearing
        Assertions.assertEquals(0, hashTestBad.size());
    }


    //test contains
    @Test
    void testContains() {
        //add all returns true with all new strings
        Assertions.assertTrue(hashTestBad.addAll(myStrings));
        //something contained returns true
        Assertions.assertTrue(hashTestBad.contains("dog"));
        //something not contained returns false
        Assertions.assertFalse(hashTestBad.contains("dogs"));
        //a null string is not contained
        Assertions.assertThrows(NullPointerException.class, ()-> hashTestBad.contains(null));
    }

    //test contains all
    @Test
    void testContainsAll() {
        //add all returns true with all new strings
        Assertions.assertTrue(hashTestBad.addAll(myStrings));
        //test contains all when all are contained
        Assertions.assertTrue(hashTestBad.containsAll(myStrings));
        //test contains all returns false when not contained
        Assertions.assertFalse(hashTestBad.containsAll(sameStrings));
        hashTestBad.addAll(sameStrings);
        //test it will return contains all if all the same string
        Assertions.assertTrue(hashTestBad.containsAll(sameStrings));
        Assertions.assertThrows(NullPointerException.class, () -> hashTestBad.containsAll(nullArrayList));
    }

    //test remove
    @Test
    void testRemove() {
        //add all returns true with all new strings
        Assertions.assertTrue(hashTestBad.addAll(myStrings));
        //check size before removal
        Assertions.assertEquals(6, hashTestBad.size());
        //check remove returned true
        Assertions.assertTrue(hashTestBad.remove("whit"));
        //check the size was adjusted
        Assertions.assertEquals(5, hashTestBad.size());
        //check that attempting to remove the same thing will return false
        Assertions.assertFalse(hashTestBad.remove("whit"));
        //check that attempting to remove a null string will return false
        Assertions.assertThrows(NullPointerException.class, () -> hashTestBad.remove(null));

    }

    //testRemoveAll
    @Test
    void testRemoveAll(){
        //add all returns true with all new strings
        Assertions.assertTrue(hashTestBad.addAll(myStrings));
        //check that trying to remove items that aren't contained returns false
        Assertions.assertFalse(hashTestBad.removeAll(sameStrings));
        //check that removing a null arraylist returns false
        Assertions.assertThrows(NullPointerException.class, () -> hashTestBad.removeAll(nullArrayList));
        //check that removing things contained returns true
        Assertions.assertTrue(hashTestBad.removeAll(myStrings));
        //check that we can remove things already removed
        Assertions.assertFalse(hashTestBad.removeAll(myStrings));
        //assert the size has been changed
        Assertions.assertEquals(0, hashTestBad.size());


    }
    //tests is empty
    @Test
    void testIsEmpty(){
        //add all returns true with all new strings
        Assertions.assertTrue(hashTestBad.addAll(myStrings));
        //assert this hash table is not empty
        Assertions.assertFalse(hashTestBad.isEmpty());
        //remove everything
        Assertions.assertTrue(hashTestBad.removeAll(myStrings));
        //assert is empty now
        Assertions.assertTrue(hashTestBad.isEmpty());

    }

    //testing my collisions algorithm
    @Test
    void testNumCollisions(){
        //add all returns true with all new strings
//        Assertions.assertTrue(hashTestBad.addAll(myStrings));
//        Assertions.assertTrue(hashTestBad.addAll(sameStrings));
//        hashTestBad.display();
//        hashTestBad.numCollisions();




        Assertions.assertTrue(hashTestMed.addAll(myStrings));
        hashTestMed.display();
        hashTestMed.numCollisions();
    }
}