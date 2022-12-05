package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class QuickSortTimingExperiment {

  static int twoPowEighteen = 262144;
  static final ArrayList<Integer> averageArray = SortUtil.generateAverageCase(twoPowEighteen);
  public Comparator<Integer> comp = Integer::compareTo;


  private static final int ITER_COUNT = 1000;

  public static void main(String[] args) {
    // you spin me round baby, right round
    long startTime = System.nanoTime();
    while (System.nanoTime() - startTime < 1_000_000_000)
      ;

    try (FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) { // open up a file writer so we can write
                                                                                // to file.
      for (int exp = 10; exp <= 18; exp++) { // This is used as the exponent to calculate the size of the set.
        int size = (int) Math.pow(2, exp); // or ..

        // Do the experiment multiple times, and average out the results
        long totalTime = 0;

        for (int iter = 0; iter < ITER_COUNT; iter++) {
          // SET UP!
          ArrayList<Integer> copyOfSet = new ArrayList<>(size);
          for (int i = 0; i < size; i++) {
            copyOfSet.add(i, averageArray.get(i));
          }
          //int findElement = random.nextInt(size); // This gets me a random int between 0 and size;

          // TIME IT!
          long start = System.nanoTime();
          SortUtil.quicksort(copyOfSet, Integer::compareTo, "random");
          long stop = System.nanoTime();
          totalTime += stop - start;
        }
        double averageTime = totalTime / (double) ITER_COUNT;
        System.out.println(size + "\t" + averageTime); // print to console
        fw.write(size + "\t" + averageTime + "\n"); // write to file.
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    Charter charter = new Charter();
    charter.createChart("contains_experiment.tsv", "chart.png");
  }
}
