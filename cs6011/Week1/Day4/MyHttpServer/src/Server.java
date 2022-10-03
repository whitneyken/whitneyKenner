import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public ServerSocket servSock;
    public Socket clientSocket;
    File file;


    public Server(int port) {
        try {
            //Setting up server
            servSock = new ServerSocket(port);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void waitForClient() throws IOException {
        System.out.print("Waiting for client...");
        clientSocket =  servSock.accept();
        System.out.println("Accepted");
        System.out.println("-------------------------------------------");
    }

    public void handleRequest() throws IOException {
        // Parsing HTTP request
        // create a reader to get all the info from the clientSocket
            InputStreamReader input = new InputStreamReader(clientSocket.getInputStream());

            // use a scanner to read the info from the input stream
            Scanner inputScanner = new Scanner(input);

            // reading the first line
            String firstLine = inputScanner.nextLine();
            System.out.println("\tHTTP REQUEST:\n\t\t" + firstLine);

            //Then we need to parse through the first line and determine what file is wanted
            String[] arrOfFirstLine = firstLine.split(" ", 3);
            String fileName = arrOfFirstLine[1];

            //after we have determined the file name, we need to check if the file exists
            if (fileName.equals("/")) {
                fileName = "index.html";
            }
            file = new File(fileName);
        //IDK how to make a generic exception catch in addition to this one
    }

    public void handleResponse() throws IOException {

        int fileStatus = 200;
        String message = "OK";
        if (!file.exists()) {
            fileStatus = 404;
            System.out.println("\tFile NOT found");
            message = "File NOT found";
        }

        //if the file exists, we should return it
        //Scanner fileRead = new Scanner( new FileReader( filename) );
        OutputStream outputStream = clientSocket.getOutputStream();

        PrintWriter writer = new PrintWriter(outputStream);

        // Build Response
        writer.write("HTTP/1.1 " + fileStatus + " " + message);

        System.out.println("\tGenerating response:");
        System.out.println("\t\tHTTP/1.1 " + fileStatus + " " + message);

        if (fileStatus == 200) {
            writer.write("Content-Type: text/html");
            writer.write("Content-Size: " + file.length());
            Scanner fileScanner = new Scanner(new FileReader(file));

            System.out.println("\t\tContent-Type: text/html");
            System.out.println("\t\tContent-Size: " + file.length());

            while (fileScanner.hasNextLine()) {
                writer.println(fileScanner.nextLine());
            }
        }
        System.out.println("-------------------------------------------");
        writer.flush();
        writer.close();
        outputStream.flush();
        outputStream.close();

    }

    public void closeClient() throws IOException {
        clientSocket.close();
        if (!clientSocket.isClosed()) {
            throw new IOException("Failure to close client socket ");
        }
    }

}
