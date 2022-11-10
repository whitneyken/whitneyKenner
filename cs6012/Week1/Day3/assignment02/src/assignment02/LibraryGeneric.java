package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Comparator;

public class LibraryGeneric<T> {

    private ArrayList<LibraryBookGeneric<T>> library;

    public LibraryGeneric() {
        library = new ArrayList<>();
    }

    /**
     * Add the specified book to the library, assume no duplicates.
     *
     * @param isbn   -- ISBN of the book to be added
     * @param author -- author of the book to be added
     * @param title  -- title of the book to be added
     */
    public void add(long isbn, String author, String title) {
        library.add(new LibraryBookGeneric<>(isbn, author, title));
    }

    /**
     * Add the list of library books to the library, assume no duplicates.
     *
     * @param list -- list of library books to be added
     */
    public void addAll(ArrayList<LibraryBookGeneric<T>> list) {
        library.addAll(list);
    }

    /**
     * Add books specified by the input file. One book per line with ISBN, author,
     * and title separated by tabs.
     * <p>
     * If file does not exist or format is violated, do nothing.
     *
     * @param filename
     */
    public void addAll(String filename) {
        ArrayList<LibraryBookGeneric<T>> toBeAdded = new ArrayList<>();

        try (Scanner fileIn = new Scanner(new File(filename))) {

            int lineNum = 1;

            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();

                try (Scanner lineIn = new Scanner(line)) {
                    lineIn.useDelimiter("\\t");

                    if (!lineIn.hasNextLong()) {
                        throw new ParseException("ISBN", lineNum);
                    }
                    long isbn = lineIn.nextLong();

                    if (!lineIn.hasNext()) {
                        throw new ParseException("Author", lineNum);
                    }
                    String author = lineIn.next();

                    if (!lineIn.hasNext()) {
                        throw new ParseException("Title", lineNum);
                    }
                    String title = lineIn.next();
                    toBeAdded.add(new LibraryBookGeneric<>(isbn, author, title));
                }
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + " Nothing added to the library.");
            return;
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
                    + ". Nothing added to the library.");
            return;
        }

        library.addAll(toBeAdded);
    }

    /**
     * Returns the holder of the library book with the specified ISBN.
     * <p>
     * If no book with the specified ISBN is in the library, returns null.
     *
     * @param isbn -- ISBN of the book to be looked up
     */
    public T lookup(long isbn) {
        //iterate through the library books and check if the isbn matches the one past in
        for (LibraryBookGeneric<T> libraryBook : library) {
            if (libraryBook.getIsbn() == isbn) {
                //If it does match, return the string name of the holder
                return libraryBook.getHolder();
            }
        }
        return null;
    }

    /**
     * Returns the list of library books checked out to the specified holder.
     * <p>
     * If the specified holder has no books checked out, returns an empty list.
     *
     * @param holder -- holder whose checked out books are returned
     */
    //Checks if the holder of each book is equal to the requested holder and then adds to an array list of the
    //library books checked out to that holder which is returned
    public ArrayList<LibraryBookGeneric<T>> lookup(T holder) {
        ArrayList<LibraryBookGeneric<T>> booksCheckedOutToHolder = new ArrayList<>();
        //For each book we verify that the holder is not null and check if the holder is the holder we are looking for
        for (LibraryBookGeneric<T> lb : library) {
            if (lb.getHolder() != null && lb.getHolder().equals(holder)) {
                //If the holder of a book is the one we are checking for we add it to our array list
                // of books checked out to this person
                booksCheckedOutToHolder.add(lb);
            }
        }
        return booksCheckedOutToHolder;
    }

    /**
     * Sets the holder and due date of the library book with the specified ISBN.
     * <p>
     * If no book with the specified ISBN is in the library, returns false.
     * <p>
     * If the book with the specified ISBN is already checked out, returns false.
     * <p>
     * Otherwise, returns true.
     *
     * @param isbn   -- ISBN of the library book to be checked out
     * @param holder -- new holder of the library book
     * @param month  -- month of the new due date of the library book
     * @param day    -- day of the new due date of the library book
     * @param year   -- year of the new due date of the library book
     */

    //the parameters passed in (isbn, holder, month, day, year) are used to call check out book in
    // the library book class
    public boolean checkout(long isbn, T holder, int month, int day, int year) {
        //For each book in the library
        for (LibraryBookGeneric<T> lb : library) {
            //If the isbn matches the one to be checked out and the holder of this book is null
            // (meaning it's not already checked out)
            if (lb.getIsbn() == isbn && lb.getHolder() == null) {
                //Call the library book check out method passing in the holder and date
                lb.checkBookOut(holder, month, day, year);
                //return true if successfully checked out
                return true;
            }

        }
        return false;
    }

    /**
     * Unsets the holder and due date of the library book.
     * <p>
     * If no book with the specified ISBN is in the library, returns false.
     * <p>
     * If the book with the specified ISBN is already checked in, returns false.
     * <p>
     * Otherwise, returns true.
     *
     * @param isbn -- ISBN of the library book to be checked in
     */

    //This method will check in the given book if it is not already checked in and will return
    public boolean checkin(long isbn) {
        //For each book in the library
        for (LibraryBookGeneric<T> lb : library) {
            //If the isbn matches and the book is checked out (verified by the getHolder not being null)
            if (lb.getIsbn() == isbn && lb.getHolder() != null) {
                //Call the library book checkBookIn method
                lb.checkBookIn();
                //Return true if checked out
                return true;
            }
        }

        return false;
    }

    /**
     * Unsets the holder and due date for all library books checked out be the
     * specified holder.
     * <p>
     * If no books with the specified holder are in the library, returns false;
     * <p>
     * Otherwise, returns true.
     *
     * @param holder -- holder of the library books to be checked in
     */
    //Checks eah library book holder to see if it matches the parameter holder and if so, checks it in and returns true
    public boolean checkin(T holder) {
        boolean returnedBook = false;
        //For each book in the library
        for (LibraryBookGeneric<T> lb : library) {
            //if the holder does not equal null and the holder is the holder input in the parameters
            if (lb.getHolder() != null && lb.getHolder().equals(holder)) {
                //Calls check book in from library book
                lb.checkBookIn();
                returnedBook = true;
            }
        }
        return returnedBook;
    }

    /**
     * Returns the list of library books, sorted by ISBN (smallest ISBN first).
     */
    public ArrayList<LibraryBookGeneric<T>> getInventoryList() {
        ArrayList<LibraryBookGeneric<T>> libraryCopy = new ArrayList<>(library);
        //Creates the comparator that we will pass to the sort method
        OrderByIsbn comparator = new OrderByIsbn();
        //passing our arraylist and comparator to sort
        sort(libraryCopy, comparator);

        return libraryCopy;
    }

    /**
     * Returns the list of library books, sorted by author
     */
    public ArrayList<LibraryBookGeneric<T>> getOrderedByAuthor() {
        ArrayList<LibraryBookGeneric<T>> libraryCopy = new ArrayList<>(library);
        //Creates the comparator that we will pass to the sort method
        OrderByAuthor comparator = new OrderByAuthor();
        //passing our arraylist and comparator to sort
        sort(libraryCopy, comparator);
        return libraryCopy;
    }

    /**
     * Returns the list of library books whose due date is older than the input
     * date. The list is sorted by date (oldest first).
     * <p>
     * If no library books are overdue, returns an empty list.
     */
    public ArrayList<LibraryBookGeneric<T>> getOverdueList(int month, int day,
                                                           int year) {
        ArrayList<LibraryBookGeneric<T>> overDueBooks = new ArrayList<>();
        //A new gregorian calendar with the entered date by the librarian
        GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
        //For each book we have, we check if it is checked out and if it's due date has passed
        for (LibraryBookGeneric<T> book : library) {
            if (book.getCheckedOutStatus() && book.getDueDate().compareTo(dueDate) < 0) {
                overDueBooks.add(book); //then we add that book to the arraylist of overdue books
            }
        }
        OrderByDueDate comparator = new OrderByDueDate();
        sort(overDueBooks, comparator);
        return overDueBooks;
    }


    /**
     * Performs a SELECTION SORT on the input ArrayList.
     * 1. Find the smallest item in the list.
     * 2. Swap the smallest item with the first item in the list.
     * 3. Now let the list be the remaining unsorted portion
     * (second item to Nth item) and repeat steps 1, 2, and 3.
     */
    private static <ListType> void sort(ArrayList<ListType> list,
                                        Comparator<ListType> c) {
        for (int i = 0; i < list.size() - 1; i++) {
            int j, minIndex;
            for (j = i + 1, minIndex = i; j < list.size(); j++)
                if (c.compare(list.get(j), list.get(minIndex)) < 0)
                    minIndex = j;
            ListType temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    /**
     * Comparator that defines an ordering among library books using the ISBN.
     */
    protected class OrderByIsbn implements Comparator<LibraryBookGeneric<T>> {

        /**
         * Returns a negative value if lhs is smaller than rhs. Returns a positive
         * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
         */
        public int compare(LibraryBookGeneric<T> lhs,
                           LibraryBookGeneric<T> rhs) {
            return (int) (lhs.getIsbn() - rhs.getIsbn());
        }
    }

    /**
     * Comparator that defines an ordering among library books using the author,  and book title as a tie-breaker.
     */

    protected class OrderByAuthor implements
            Comparator<LibraryBookGeneric<T>> {


        //This method compares the 2 passed in library books and returns an int of + num, -num, or 0
        // depending on whether they are >, <, or =
        @Override
        public int compare(LibraryBookGeneric<T> o1, LibraryBookGeneric<T> o2) {
            //If the authors are the same, compare the title
            if (o1.getAuthor().equals(o2.getAuthor())) {
                return o1.getTitle().compareTo(o2.getAuthor());
                //If the authors are not the same, compare the authors
            } else {
                return o1.getAuthor().compareTo(o2.getAuthor());
            }
        }

        // FILL IN
    }

    /**
     * Comparator that defines an ordering among library books using the due date.
     */
    protected class OrderByDueDate implements Comparator<LibraryBookGeneric<T>> {
        @Override
        public int compare(LibraryBookGeneric<T> o1, LibraryBookGeneric<T> o2) {
            //Will compare the due dates of the 2 passed in books
            return o1.getDueDate().compareTo(o2.getDueDate());
        }

    }

}
