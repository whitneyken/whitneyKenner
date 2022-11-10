package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<T> extends Book {

    private T holder;
    private GregorianCalendar dueDate;
    private boolean isCheckedOut = false;

    public LibraryBookGeneric(long isbn, String author, String title) {
        //Calls the super constructor
        super(isbn, author, title);
    }

    //Returns the user who checked out the library book (of any type)
    public T getHolder() {
        return holder;

    }

    //returns the due date of the given library book
    public GregorianCalendar getDueDate() {
        return dueDate;
    }

    //This is the method for returning a book to the library
    public void checkBookIn() {
        holder = null;
        dueDate = null;
        isCheckedOut = false;
    }

    //This is the  method for checking a book out from the library
    public void checkBookOut(T holderName, int month, int day, int year) {
        holder = holderName;
        //sets the books due date
        dueDate = new GregorianCalendar(year, month, day);
        isCheckedOut = true;

    }
    //This boolean will return if a book is checked out or not
    public boolean getCheckedOutStatus(){return isCheckedOut;};


}
