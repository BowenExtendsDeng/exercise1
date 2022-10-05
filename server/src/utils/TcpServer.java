package utils;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * @author BowenDeng
 */
public class TcpServer extends Thread{
    private final Socket socket;

    private BufferedReader bufferedReade;
    private BufferedWriter bufferedWriter;

    TcpServer(Socket socket){
        this.socket = socket;
        this.start();
    }

    public void run() {
        try {
            bufferedReade =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(true){

            }
        } catch (IOException e) {
            System.out.println("ERROR: failed to establish input stream");
            throw new RuntimeException(e);
        }
    }
}

