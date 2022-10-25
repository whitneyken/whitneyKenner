import java.io.*;
import java.net.Socket;

public class HandleResponse {


    public void handleResponse(Socket clientSocket, String fileName) throws IOException, InterruptedException {
        File file = new File(fileName);
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
            //inputStream.transferTo(outputStream);
            for( int i = 0; i < file.length(); i++ ) {
                try {
                    Thread.sleep(10); // Maybe add <- if images are still loading too quickly...
                }catch (Exception e){
                    System.out.println("failed to sleep");
                }
                outputStream.write( inputStream.read() );
                outputStream.flush();

            }

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
}
