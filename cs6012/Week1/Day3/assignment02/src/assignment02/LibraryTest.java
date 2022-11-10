//package assignment02;
//
//import assignment02.Library;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LibraryTest {
//
//    @Test
//    public void testEmpty() {
//        Library lib = new Library();
//        assertNull(lib.lookup(978037429279L));
//
//        ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
//        assertEquals(booksCheckedOut.size(), 0);
//
//        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
//        assertFalse(lib.checkin(978037429279L));
//        assertFalse(lib.checkin("Jane Doe"));
//    }
//
//    @Test
//    public void testNonEmpty() {
//
//        var lib = new Library();
//        // test a small library
//        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
//        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
//        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
//
//        assertNull(lib.lookup(9780330351690L)); //not checked out
//        var res = lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
//        assertTrue(res);
//        var booksCheckedOut = lib.lookup("Jane Doe");
//        assertEquals(booksCheckedOut.size(), 1);
//        assertEquals(booksCheckedOut.get(0),new Book(9780330351690L, "Jon Krakauer", "Into the Wild"));
//        assertEquals(booksCheckedOut.get(0).getHolder(), "Jane Doe");
//        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
//        res = lib.checkin(9780330351690L);
//        assertTrue(res);
//        res = lib.checkin("Jane Doe");
//        assertFalse(res);
//    }
//
//    @Test
//    public void testLargeLibrary(){
//        // test a medium library
//        var lib = new Library();
//        lib.addAll("Mushroom_Publishing.txt");
//        //tests checkout method under Library class
//        assert(lib.checkout( 9781843190011L, "Amy Winehouse", 1, 1, 2022));
//        assert(lib.checkout(9781843193319L, "Stan Lee", 1, 4, 2022));
//        assert (lib.checkout(9781843192701L, "Stan Lee", 3, 4, 2022));
//        //tests the lookup method by holder
//        ArrayList<LibraryBook> stanLeesBooks = lib.lookup("Stan Lee");
//        //This also tests the getTitle method in Book
//        assert(stanLeesBooks.get(0).getTitle().equals("The Lily and the Bull"));
//        //tests the lookup method by isbn
//        String holderByIsbn = lib.lookup(9781843190011L);
//        assert (holderByIsbn.equals("Amy Winehouse"));
//        //check get holder and get Gregorian calendar
//        assert(stanLeesBooks.get(0).getHolder().equals("Stan Lee"));
//        assert (stanLeesBooks.get(0).getDueDate() != null);
//
//        //tests check in method under library class
//        //test check in for 1 book and test check in for 1 holder
//        lib.checkin(9781843190011L);
//        assert (lib.lookup(9781843190011L) == null);
//
//        lib.checkin("Stan Lee");
//        assert (lib.lookup(9781843192701L) == null);
//    }
//
//}