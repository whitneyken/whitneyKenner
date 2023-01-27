import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;


public class RainData {
    String cityName;

    static int sizeOfVector = 0;
    ArrayList<String> month = new ArrayList<String>();
    ArrayList<Integer> year = new ArrayList<Integer>();
    ArrayList<Double> rainFall = new ArrayList<Double>();


    //This is my constructor, it takes in the file name and uses that to open a file reader which is opened by a
    // scanner. the first line is saved as the city name, then a while loop checks if there is another line and then
    // the next string is added to the month array list, the next int is added to the year array list, and the
    //next double is added to the rainfall array list
    public RainData(String filename) throws FileNotFoundException {
        Scanner fileRead = new Scanner(new FileReader(filename));
        cityName = fileRead.nextLine();
        while (fileRead.hasNext()) {
            month.add(fileRead.next());
            year.add(Integer.parseInt(fileRead.next()));
            rainFall.add(parseDouble(fileRead.next()));
            sizeOfVector++;
        }
        fileRead.close();
    }


    //This method prints out the information in our rainData class, it prints each month, year and rainfall line by line
    public void printToFile(String filename) throws IOException {
        //This array is used later to print off monthly rain, it is also passed into a monthly averages function to make lovely nested for loops
        double monthlyRain[] = new double[12];
        String monthStrings[] = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        PrintWriter fileWrite = new PrintWriter(new FileWriter(filename));
        fileWrite.write(cityName);
        fileWrite.println();
        double avgRain = averageRainfall();
        fileWrite.write("The overall average rainfall amount is: " + avgRain + " inches.");
        fileWrite.println();
        monthlyRain = averageMonthlyRainfall(monthStrings);
        //This for loop will print out the average rainfall for each month
        for (int j = 0; j < monthlyRain.length; j++) {
            fileWrite.write("The average rainfall amount for " + monthStrings[j] + " is " + monthlyRain[j] + " inches.");
            fileWrite.println();
        }
        fileWrite.flush();
        fileWrite.close();
    }

    //This function returns the total average rainfall
    public double averageRainfall() {
        double totalRain = 0;
        for (int i = 0; i < sizeOfVector; i++) {
            totalRain += rainFall.get(i);
        }
        totalRain /= sizeOfVector;
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        totalRain = parseDouble(numberFormat.format(totalRain));
        return totalRain;
    }

    //This lovely function of for loops goes through each month in my vector of months within my class and then checks each
// one to see which month string it is equal to. if it is equal, it will add the rain to a monthly rain array at that
// "month index" and adds 1 to a counter for each month stored in array. the final for loop divides the total rain at
//each index by it's associated number of months in the numMonths array. then we take only 2 digits from this. the
// monthly rain array is then returned
    public double[] averageMonthlyRainfall(String[] monthStrings) {
        double monthlyRain[] = new double[12];
        int numMonths[] = new int[12];
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        for (int i = 0; i < sizeOfVector; i++) {
            for (int j = 0; j < monthStrings.length; j++) {
                if (monthStrings[j].equals(month.get(i))) {
                    monthlyRain[j] += rainFall.get(i);
                    numMonths[j]++;
                }
            }
        }
        for (int k = 0; k < monthlyRain.length; k++) {
            monthlyRain[k] /= numMonths[k];
            monthlyRain[k] = parseDouble(numberFormat.format(monthlyRain[k]));
        }
        return monthlyRain;
    }


}
