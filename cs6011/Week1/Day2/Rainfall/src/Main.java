import java.io.FileNotFoundException;

public class Main {
    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");

    String fileName = new String("rainfall_data.txt");
    RainData myData;
        myData = new RainData(fileName);
        double fourth = myData.rainFall.get(3);
        System.out.println(fourth);
}
}