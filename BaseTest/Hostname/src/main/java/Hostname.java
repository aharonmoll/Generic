import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by aharon on 11/12/15.
 */
public class Hostname {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        //InetAddress host = InetAddress.getLoopbackAddress();
        InetAddress host = InetAddress.getLocalHost();
        String hostAddress = host.getHostAddress();
        String hostname = host.getHostName();
        byte[] address = host.getAddress();
        System.out.println("HostAddress="+hostAddress);
        System.out.println("Hostname="+hostname);
        System.out.println("address="+address);

        Enumeration<NetworkInterface> netEnum = NetworkInterface.getNetworkInterfaces();
        while (netEnum.hasMoreElements()) {
            NetworkInterface networkInterface = netEnum.nextElement();
            String desc = networkInterface.toString();
            if (desc != null)
                System.out.println(desc);
        }
    }
}
