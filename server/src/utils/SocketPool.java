package utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author BowenDeng
 */
public class SocketPool {
    private static int port;

    private static int maxConnectionNumber;
    private static ServerSocket serverSocket;

    public static void init()throws IOException{
        initProperties();
        serverSocket = new ServerSocket(port);
        while(true){
            Socket socket = serverSocket.accept();
            TcpServer tcpServer = new TcpServer(socket);
            InetAddress addr = socket.getInetAddress();
            String id = addr.getHostName();

        }
    }

    private static void initProperties(){
        String portSrc = null;

        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src:\\resources\\client.properties"));
            portSrc = properties.getProperty("port");
        } catch (IOException e) {
            System.out.println("ERROR: failed to load properties file at " +
                    "src:resources/client.properties, please check if it exists");
        }

        if(portSrc == null) {
            portSrc = "2021";
        }

        port = Integer.parseInt(portSrc);
    }

}
