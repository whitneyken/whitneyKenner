//package assignment02;
//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//public class LibraryBook extends Book {
//    private String holder;
//    private GregorianCalendar dueDate;
//
//
//    public LibraryBook(long isbn, String author, String title) {
//        super(isbn, author, title);
//    }
//
//    //Returns the user who checked out the library book
//    public String getHolder() {
//        return holder;
//
//    }
//
//    //returns the due date of the given library book
//    public GregorianCalendar getDueDate() {
//        return dueDate;
//    }
//
//    //This is the method for returning a book to the library
//    public void checkBookIn() {
//        holder = null;
//        dueDate = null;
//    }
//
//    //This is the  method for checking a book out from the library
//    public void checkBookOut(String holderName, int month, int day, int year) {
//        holder = holderName;
//        //I THINK this will set the due date to be 14 days from the day it is checked out?
//        dueDate = new GregorianCalendar(year, month, day);
//
//    }
//}
