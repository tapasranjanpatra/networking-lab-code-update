   import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) {
        String host = "10.10.32.191";
        int port = 9999;
        String fileName = null;
        Socket clientSocket = null;
        DataOutputStream dos = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try {
            clientSocket = new Socket(host, port);
            System.out.println("Connected successfully with " + host + " and port " + port);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter File name: ");
            fileName = scanner.nextLine();

            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File not found: " + fileName);
                System.exit(1);
            }

            long fileSize = file.length();

            dos = new DataOutputStream(clientSocket.getOutputStream());
            dos.writeUTF(file.getName());
            dos.writeLong(fileSize);

            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);

            byte[] buffer = new byte[1024 * 1024]; 
            int bytesRead;
            long totalBytesRead = 0;

            while ((bytesRead = bis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }

            System.out.println("File transfer complete");
        } catch (UnknownHostException e) {
            System.out.println("Unable to connect: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error in file transfer: " + e.getMessage());
            System.exit(1);
        } finally {
            try {
                if (dos != null)
                    dos.close();
                if (bis != null)
                    bis.close();
                if (clientSocket != null)
                    clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
