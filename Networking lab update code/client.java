import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter you message: ");
            String s = sc.nextLine();
            byte b[] = s.getBytes();
            InetAddress a = InetAddress.getByName("localhost");
            DatagramPacket dp = new DatagramPacket(b, b.length, a, 2222);
            ds.send(dp);
            System.out.println("Message has been sent: ");
            ds.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
