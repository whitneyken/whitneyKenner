import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket servSock;
    private Socket clientSocket;
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

    public void handleRequest() throws IOException {
        clientSocket = servSock.accept();
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
        String fileName = arrOfFirstLine[1];

        //after we have determined the file name, we need to check if the file exists
        if (fileName.equals("/")) {
            fileName = "index.html";
        } else {
            fileName = fileName.substring(1);
        }
        //substring taking off first slash
        file = new File(fileName);
    }

    public void handleResponse() throws IOException {

        int fileStatus = 200;
        String message = "OK";
        String fileType = "text/" + getFileExtension(file);

        if (!file.exists()) {
            fileStatus = 404;
            System.out.println("\tFile NOT found");
            message = "File NOT found";
        }

        //if the file exists, we should return it
        OutputStream outputStream = clientSocket.getOutputStream();

        PrintWriter writer = new PrintWriter(outputStream);

        // Build Response
        writer.write("HTTP/1.1 " + fileStatus + " " + message + "\n");

        System.out.println("\tGenerating response:");
        System.out.println("\t\tHTTP/1.1 " + fileStatus + " " + message);

        if (fileStatus == 200) {
            //Content type as a variable, so that the css can be pulled up as well
            writer.write("Content-Type: " + fileType + "\n");
            writer.write("Content-Size: " + file.length() + "\n");
            writer.write("\n");
            writer.flush();
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.transferTo(outputStream);

            System.out.println("\t\tContent-Type: " + fileType);
            System.out.println("\t\tContent-Size: " + file.length());
        }
        System.out.println("-------------------------------------------");
        writer.flush();
        outputStream.close();
        clientSocket.close();

    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".") + 1;
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }

    public void closeClient() throws IOException {
        clientSocket.close();
        if (!clientSocket.isClosed()) {
            throw new IOException("Failure to close client socket ");
        }
    }

}
