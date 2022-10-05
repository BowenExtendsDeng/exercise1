package utils;

import java.io.*;
import java.net.Socket;
import java.util.Properties;


/**
 * Create a tcp docket to communicate with server
 * @author BowenDeng
 */
public class TcpClient {
    /**
     * port of fileServer
     */
    private static String port;
    /**
     * ip address of designated fileServer
     */
    private static String ip;
    /**
     * static socket to communicate with a fileServer
     */
    private static Socket socket;

    /**
     * stream to receive result from fileServer
     */
    private static BufferedReader bufferedReader;

    /**
     * Receiving response from fileServer
     * @throws IOException throw exception when stream cannot set up
     */
     public static String receiveMessage() throws IOException {
        bufferedReader =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = bufferedReader.readLine();
        bufferedReader.close();
        return str;
    }

    /**
     * to try to get connection with a designated file server
     */
    private static void initConnection(){
        try{
            socket = new Socket(ip, Integer.parseInt(port));
        }catch(IOException e){
            System.out.println("ERROR: Failed to connect fileServer, please check your properties");
        }
    }

    /**
     * to read param from properties. when a param is missing, it will init by default value
     * default ip = 127.0.0.1
     * default port = 2021
     */
    private static void initProperties(){
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src:\\resources\\client.properties"));
            port = properties.getProperty("port");
            ip = properties.getProperty("ip");
        } catch (IOException e) {
            System.out.println("ERROR: failed to load properties file at " +
                    "src:resources/client.properties, please check if it exists");
        }
        if(port == null) {
            port = "2021";
        }
        if(ip == null){
            ip = "127.0.0.1";
        }
    }

    /**
     * Sending commands to fileServer
     * @param msg processed message from task manager
     * @throws IOException throw exception when stream cannot reach the server
     */
    public static void sendMessage(String msg) throws IOException {
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write(msg);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * Close streams and shut down the socket
     * @throws IOException throw exception when shut down process interrupted
     */
    public static void closeConnection() throws IOException {
        bufferedReader.close();
        socket.shutdownOutput();
        socket.shutdownInput();
        try{
            socket.close();
        }catch (IOException e){
            System.out.println("ERROR: Failed when trying to close socket");
        }
    }

    /**
     * init TCP client
     * @throws IOException throw exception when one of the method below has error
     */
    public static void init() throws IOException {
        initProperties();
        initConnection();
        receiveMessage();
    }

    public static boolean isConnectionAlive(){
        return socket != null;
    }
}
