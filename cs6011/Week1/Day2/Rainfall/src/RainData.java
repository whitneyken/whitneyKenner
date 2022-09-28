import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.regex.MatchResult;

public class RainData {
    String cityName;
    ArrayList<String> month = new ArrayList<String>();
    ArrayList<Integer> year = new ArrayList<Integer>();
    ArrayList<Double> rainFall = new ArrayList<Double>();


    public RainData(String filename) throws FileNotFoundException {
        //File myObj = new File("src/rainfall_data.txt"); //tried both this one and the line below and neither work
        FileInputStream fin = new FileInputStream("rainfall_data.txt");
        Scanner myReader = new Scanner(fin);
        cityName = myReader.nextLine();
        String oneMonth = new String();
        int oneYear;
        double oneRainFall;
        while(myReader.hasNextLine()){
            month.add(myReader.next());
            year.add(Integer.parseInt(myReader.next()));
            rainFall.add(Double.parseDouble(myReader.next()));
        }
    }
}
