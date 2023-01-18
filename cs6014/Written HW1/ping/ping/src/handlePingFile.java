import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class handlePingFile {

    public static void handlePingFile(String name) throws IOException {
        float smallestValue = 10000000;
        float addedValues = 0;
        int totalForAvg = 0;
        File fileToOpen = new File(name);
        Scanner scanner = new Scanner(fileToOpen);
        while (scanner.hasNextLine()){
            String currentString = scanner.nextLine();
            String[] stringArrayValue = currentString.split("time=");
            if (stringArrayValue.length > 1){
                String[] stringTimeValue = stringArrayValue[1].split(" ms");
                float pulledValue = Float.parseFloat(stringTimeValue[0]);
                if (pulledValue < smallestValue){
                    smallestValue = pulledValue;
                }
                addedValues += pulledValue;
                totalForAvg++;
            }
        }
        handleOutput(smallestValue, addedValues, totalForAvg);


    }

    private static void handleOutput(float smallestValue, float addedValues, int totalForAvg) throws IOException {
        File outputFile = new File("vaticanPingResponse.txt");
        FileWriter writer = new FileWriter(outputFile);
        writer.write("the smallest delay is " + smallestValue + " which is what will be assumed to be a queuing delay of 0 \n");
        addedValues = addedValues - (smallestValue*totalForAvg);
        writer.write("the average delay (with the smallest value detracted from all delays) is " + addedValues/totalForAvg);
        writer.flush();
        writer.close();
    }
}
