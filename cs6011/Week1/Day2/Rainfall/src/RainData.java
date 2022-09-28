import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class RainData {
    String cityName;
    ArrayList<String> month = new ArrayList<String>();
    ArrayList<Integer> year = new ArrayList<Integer>();
    ArrayList<Double> rainFall = new ArrayList<Double>();


    public RainData(String filename) throws FileNotFoundException {
        Scanner fileReader = new Scanner( new FileReader( filename) );
        cityName = fileReader.nextLine();
        while(fileReader.hasNextLine()){
            month.add(fileReader.next());
            year.add(Integer.parseInt(fileReader.next()));
            rainFall.add(Double.parseDouble(fileReader.next()));
        }
    }
}
