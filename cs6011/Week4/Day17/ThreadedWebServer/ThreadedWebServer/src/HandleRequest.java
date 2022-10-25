import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HandleRequest {
    String fileName;

    public String handleRequest(Socket clientSocket) throws IOException {

        System.out.println("Accepted");
        System.out.println("-------------------------------------------");

        // Parsing HTTP request
        // use a scanner to read the info from the input stream
        Scanner inputScanner = new Scanner(clientSocket.getInputStream());

        System.out.println("\nabout to read first line\n");
        String firstLine = inputScanner.nextLine();
        System.out.println("\tHTTP REQUEST:\n\t\t" + firstLine);

        //Then we need to parse through the first line and determine what file is wanted
        String[] arrOfFirstLine = firstLine.split(" ", 3);
        fileName = arrOfFirstLine[1];

        //after we have determined the file name, we need to check if the file exists
        if (fileName.equals("/")) {
            fileName = "index.html";
        } else {
            fileName = fileName.substring(1);
        }
        return fileName;
    }


}
