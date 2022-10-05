package utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author BowenDeng
 */
public class SocketPool {
    private static int PORT;

    private static ConcurrentHashMap<String, TcpServer> map = new ConcurrentHashMap<>();
    private static ServerSocket serverSocket;

    public static void init()throws IOException{
        initProperties();
        serverSocket = new ServerSocket(PORT);
        while(true){
            Socket socket = serverSocket.accept();
            TcpServer tcpServer = new TcpServer(socket);
            InetAddress addr = socket.getInetAddress();
            String id = addr.get
            map.put(, tcpServer);

        }
    }

    private static void initProperties(){
        String port = null;
        String maxConnection = null;
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src:\\resources\\client.properties"));
            port = properties.getProperty("port");
        } catch (IOException e) {
            System.out.println("ERROR: failed to load properties file at " +
                    "src:resources/client.properties, please check if it exists");
        }

        if(port == null) {
            port = "2021";
        }

        PORT = Integer.parseInt(port);
    }

}
