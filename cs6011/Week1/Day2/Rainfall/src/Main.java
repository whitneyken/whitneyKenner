import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {

    String fileName = new String("rainfall_data.txt");
    String writeFile = new String("rainfall_results.txt");
    RainData myData;
        myData = new RainData(fileName);
        myData.printToFile(writeFile);
}




}