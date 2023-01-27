package assignment06;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MediocreHashFunctor medHash = new MediocreHashFunctor();
        GoodHashFunctor goodHash = new GoodHashFunctor();

        int capacity = 10000;

        int size = 0;

        int numCollisions = 0;

        BadHashFunctor badHash = new BadHashFunctor();
        ChainingHashTable hashTable = new ChainingHashTable(capacity, goodHash);

        long totalTime = 0;


        for (int i = 0; i < 10; i++) {
            int counter = 0;
            size+=1000;

            while (counter < 100) {
                ArrayList<String> stringList = StringList.buildStringList(size);
                hashTable.addAll(stringList);
                Random rand = new Random(System.nanoTime());
                String toTest = stringList.get(rand.nextInt(size));
                //System.out.println("The size is: " + hashTable.size());
                long start = System.nanoTime();
                hashTable.contains(toTest);
                long stop = System.nanoTime();


                //System.out.println("The size is now " + hashTable.size());
                totalTime += stop - start;
                //numCollisions += hashTable.numCollisions();
                hashTable.clear();
                //System.out.println("The size is now " + hashTable.size());


                counter++;
            }

            System.out.println("The total amount of time for a size of " + size + " is " + totalTime/100);
        }

    }


    private static void shuffleStrings(ArrayList<String> stringList){
        Random rand = new Random(1);
        for (int i = stringList.size() - 2; i > 0; i--) {
            String temp = stringList.get(i);
            stringList.set(i, stringList.get(rand.nextInt(i+1)));
            stringList.set(i+1, temp);
        }
    }
}