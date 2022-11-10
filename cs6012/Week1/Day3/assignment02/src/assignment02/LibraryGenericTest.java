package assignment02;

import assignment02.LibraryGeneric;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LibraryGenericTest {

    @Test
    public void stringLibraryTest() {
        // test a library that uses names (String) to id patrons
        LibraryGeneric<String> lib = new LibraryGeneric<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        String patron1 = "Jane Doe";

        assertTrue(lib.checkout(9780330351690L, patron1, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron1, 1, 1, 2008));

        var booksCheckedOut1 = lib.lookup(patron1);
        assertEquals(booksCheckedOut1.size(), 2);
        assertTrue(booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut1.get(0).getHolder(), patron1);
        assertEquals(booksCheckedOut1.get(0).getDueDate(), new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut1.get(1).getHolder(),patron1);
        assertEquals(booksCheckedOut1.get(1).getDueDate(),new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron1));

    }

    @Test
    public void phoneNumberTest(){
        // test a library that uses phone numbers (PhoneNumber) to id patrons
        var lib = new LibraryGeneric<PhoneNumber>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        PhoneNumber patron2 = new PhoneNumber("801.555.1234");

        assertTrue(lib.checkout(9780330351690L, patron2, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron2, 1, 1, 2008));

        ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib.lookup(patron2);

        assertEquals(booksCheckedOut2.size(), 2);
        assertTrue(booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut2.get(0).getHolder(),patron2);
        assertEquals(booksCheckedOut2.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut2.get(1).getHolder(), patron2);
        assertEquals(booksCheckedOut2.get(1).getDueDate(), new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron2));

    }
    @Test
    public void stringAndPhoneNumberTests(){
        //Tests a large library with strings
                // test a medium library
        LibraryGeneric<String> lib = new LibraryGeneric<>();
        lib.addAll("Mushroom_Publishing.txt");
        //tests checkout method under Library class
        assert(lib.checkout( 9781843190011L, "Amy Winehouse", 1, 1, 2022));
        assert(lib.checkout(9781843193319L, "Stan Lee", 1, 4, 2022));
        assert (lib.checkout(9781843192701L, "Stan Lee", 3, 4, 2022));
        //tests the lookup method by holder
        ArrayList<LibraryBookGeneric<String>> stanLeesBooks = lib.lookup("Stan Lee");
        //This also tests the getTitle method in Book
        assert(stanLeesBooks.get(0).getTitle().equals("The Lily and the Bull"));
        //tests the lookup method by isbn
        String holderByIsbn = lib.lookup(9781843190011L);
        assert (holderByIsbn.equals("Amy Winehouse"));
        //check get holder and get Gregorian calendar
        assert(stanLeesBooks.get(0).getHolder().equals("Stan Lee"));
        assert (stanLeesBooks.get(0).getDueDate() != null);

        //tests check in method under library class
        //test check in for 1 book and test check in for 1 holder
        lib.checkin(9781843190011L);
        assert (lib.lookup(9781843190011L) == null);

        lib.checkin("Stan Lee");
        assert (lib.lookup(9781843192701L) == null);




        //To test on numbers
                // test a medium library
        LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<>();
        lib2.addAll("Mushroom_Publishing.txt");

        PhoneNumber num1 = new PhoneNumber("801.349.9957");
        PhoneNumber num2 = new PhoneNumber("801.643.9805");
        //tests checkout method under Library class
        assert(lib2.checkout( 9781843190011L, num1, 1, 1, 2022));
        assert(lib2.checkout(9781843193319L, num2, 1, 4, 2022));
        assert (lib2.checkout(9781843192701L, num2, 3, 4, 2022));
        //tests the lookup method by holder
        ArrayList<LibraryBookGeneric<PhoneNumber>> num2Books = lib2.lookup(num2);
        //This also tests the getTitle method in Book
        assert(num2Books.get(0).getTitle().equals("The Lily and the Bull"));
        //tests the lookup method by isbn
        PhoneNumber PNHolderByIsbn = lib2.lookup(9781843190011L);
        assert (PNHolderByIsbn.equals(num1));
        //check get holder and get Gregorian calendar
        assert(num2Books.get(0).getHolder().equals(num2));
        assert (num2Books.get(0).getDueDate() != null);

        //tests check in method under library class
        //test check in for 1 book and test check in for 1 holder
        lib2.checkin(9781843190011L);
        assert (lib2.lookup(9781843190011L) == null);

        lib2.checkin(num2);
        assert (lib2.lookup(9781843192701L) == null);
    }
    //Tests an empty library
    @Test
    public void testEmptyLibrary(){
                LibraryGeneric<String> lib = new LibraryGeneric<>();
        assertNull(lib.lookup(978037429279L));

        ArrayList<LibraryBookGeneric<String>> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);

        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));
    }

    @Test
    public void testIsbnSort(){
        //Create a library
        LibraryGeneric<String> myLib = new LibraryGeneric<>();
        myLib.addAll("Mushroom_Publishing.txt");
        ArrayList<LibraryBookGeneric<String>> myList = myLib.getInventoryList();
        //The minus 1 is so we only compare through the last element and don't get an index out of bounds exception
        for (int i = 0; i < myList.size() -1; i++){
            //This asserts that every isbn in the list is less than the next isbn in the list
            assert (myList.get(i).getIsbn() < myList.get(i+1).getIsbn());
        }
    }
    @Test
    public void testAuthorSort(){
        LibraryGeneric<PhoneNumber> newLib = new LibraryGeneric<>();
        newLib.addAll("Mushroom_Publishing.txt");
        ArrayList<LibraryBookGeneric<PhoneNumber>> bookList = newLib.getOrderedByAuthor();
        //The minus 1 is so we only compare through the last element and don't get an index out of bounds exception
        for (int i = 0; i < bookList.size() - 1; i++){
            assert (bookList.get(i).getAuthor().compareTo(bookList.get(i + 1).getAuthor()) <= 0);
        }
    }
    //This tests the overdue list method to verify that it returns an arraylist of overdue book
    //Starting with the most overdue in index 0 and increasing from there
    @Test
    public void testGetOverDueList(){
        LibraryGeneric<String> lib = new LibraryGeneric<>();
        lib.addAll("Mushroom_Publishing.txt");
        lib.checkout(9781843193319L, "Sarah", 1,2, 2021);
        lib.checkout(9781843192954L, "Max", 1, 2, 2020);
        lib.checkout(9781843192701L, "Dana", 1, 2, 2019);
        lib.checkout(9781843192039L, "Will", 1, 2, 2018);
        lib.checkout(9781843192022L, "Hannah", 1, 2, 2017);
        lib.checkout(9781843191230L, "Noah", 1, 2, 2016);
        lib.checkout(9781843190998L, "Lily", 1, 2, 2015);
        lib.checkout(9781843190936L, "Sam", 1, 2, 2014);
        lib.checkout(9781843190875L, "Candy", 1, 2, 2023);


        ArrayList<LibraryBookGeneric<String>> overdueBooks = lib.getOverdueList(1, 2, 2022);
        //The minus 1 is so we only compare through the last element and don't get an index out of bounds exception
        for (int i = 0; i < overdueBooks.size() - 1; i++){
            //This asserts that each book is more (or the same) overdue as the book in the next highest index location
            assert (overdueBooks.get(i).getDueDate().compareTo(overdueBooks.get(i + 1).getDueDate()) <= 0);
        }
    }
}