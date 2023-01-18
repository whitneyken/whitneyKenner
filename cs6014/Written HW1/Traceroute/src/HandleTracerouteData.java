import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HandleTracerouteData {

    public static void HandleTracerouteData(String filename) {
        HashMap<String, Float> dataMap = new HashMap<>();
        try {
            File myFile = new File(filename);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                String singleLine = myScanner.nextLine();
                String[] arrOfStrings = singleLine.split("\\(");
                //handle all 3 packets on 1 line
                if (arrOfStrings.length > 1) {
                    String[] ipAddress = arrOfStrings[1].split("\\)");
                    System.out.println(ipAddress[0]);
                    String[] delayStringArray = ipAddress[1].split(" ms");
                    float totalDelay = (float) 0;
                    int numForAveraging = 0;
                    int numStars = 0;
                    try {
                        for (int i = 0; i < delayStringArray.length; i++) {
                            System.out.println("s is: " + delayStringArray[i]);
                            String singleString = delayStringArray[i];
                            if (singleString.contains("*")){
                                numStars++;
                                break;
                            }else {
                                totalDelay += Float.parseFloat(singleString);
                                numForAveraging++;
                            }
                        }
                        if ((numForAveraging + numStars) == 3){
                            dataMap.put(ipAddress[0], (totalDelay/numForAveraging));
                        }else {
                            //handle when there is another line
                            while (numForAveraging + numStars < 3) {
                                String secondaryLine = myScanner.nextLine();
                                String[] splitSecondLine = secondaryLine.split("\\)  ");
                                if (splitSecondLine.length > 1){
                                    String[] values = splitSecondLine[1].split(" ms");
                                    for (int i = 0; i < values.length; i++){
                                        String currentVal = values[i];
                                        if (currentVal.contains("*")){
                                            numStars++;
                                        }else {
                                            totalDelay += Float.parseFloat(currentVal);
                                            numForAveraging++;
                                        }
                                    }
                                }

                            }
                            dataMap.put(ipAddress[0], (totalDelay/numForAveraging));
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Exception " + e);
                    }
                    System.out.println("total delay: " + totalDelay);

                } else{
                    if (arrOfStrings[0].contains("*")){
                        System.out.println("This address did not return a ping");
                    }
                }
                handleOutput(dataMap);
                //handle split packets
            }
            myScanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void handleOutput(HashMap<String, Float> dataMap) throws IOException {
        File outputFile = new File("output2.txt");
        FileWriter writer = new FileWriter(outputFile);
        for (Map.Entry<String, Float> mapElement : dataMap.entrySet()){
            String key = mapElement.getKey();
            float value = mapElement.getValue();
            writer.write(key + " " + value + "\n");
        }
        writer.flush();
        writer.close();
    }
}
