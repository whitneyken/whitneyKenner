import java.sql.SQLOutput;
import java.lang.String;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //this prints out hello world
        System.out.println("Hello world!");
        int[] myNums = {2, 6, 18, 0, 14, 7, 11, 9, 4, 61};
        int myNumsCounter = 0;
        //This for loop prints out my random values as well as adds the values together to be printed out
        for (int i = 0; i < myNums.length; i++) {
            System.out.println(myNums[i]);
            myNumsCounter += myNums[i];
        }
        System.out.println("The sum of values in this array is: ");
        System.out.println(myNumsCounter);

        //ask a user to input their name and age and then print whether they are old enough to vote & which
        // generation they are in
        Scanner myInput = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = myInput.nextLine();
        //This receives input from the user with their name and age
        System.out.println("Please enter your age: ");
        int age = myInput.nextInt();

        //prints out whether they are old enough to vote
        if (age >= 18){
            System.out.println("You are old enough to vote!");
        }else{
            System.out.println("You are not old enough to vote.");
        }
        //prints out what generation they are part of
        if (age > 80){
            System.out.println("You are part of the greatest generation");
        } else if (age > 60 && age <= 80) {
            System.out.println("You are a baby boomer");
        } else if (age > 40 && age <= 60) {
            System.out.println("You are part of generation X");
        } else if (age > 20 && age <= 40) {
            System.out.println("You are a millennial");
        }else
            System.out.println("You are an iGen");

    }

}