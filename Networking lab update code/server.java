import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class server {
    public static void main(String[] args)
    {
        try {
            DatagramSocket ds = new DatagramSocket(2222);
            byte b[] = new byte[1000];
            DatagramPacket dp = new DatagramPacket(b, b.length);
            ds.receive(dp);
            String s = new String(dp.getData());
            System.out.println("Received Message: " + s);
            ds.close();
        }
        catch (Exception e)
        {
            System.out.println(e);

        }

    }
}
