package utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.util.Properties;

/**
 * @author BowenDeng
 */
public class UdpClient {
    /**
     * udp socket instance
     */
    DatagramSocket socket;

    /**
     * packet to transport by the udp socket
     */
    DatagramPacket packet;

    /**
     * calling this method will automatically sending a udp packet
     * @param ip server ip address
     * @param content packet content
     * @throws IOException throw this exception when stream is interrupted
     */
    public UdpClient(String ip, byte[] content) throws IOException {
        socket = new DatagramSocket(2020);
        packet = new DatagramPacket(content, content.length,
                InetAddress.getByName(ip),
                2020);
        socket.send(packet);

        socket.close();
    }
}
