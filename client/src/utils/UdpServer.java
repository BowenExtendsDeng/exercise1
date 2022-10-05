package utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author BowenDeng
 */
public class UdpServer {

    public static byte[] getDatagram(int port) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(port);
        byte[] bytes = new byte[1024];

        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        datagramSocket.receive(datagramPacket);

        byte[] data = datagramPacket.getData();

        datagramSocket.close();

        return data;
    }

    public static byte[] getDatagram() throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(2020);
        byte[] bytes = new byte[1024];

        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        datagramSocket.receive(datagramPacket);

        byte[] data = datagramPacket.getData();

        datagramSocket.close();

        return data;
    }
}
