import service.Terminal;
import utils.TcpClient;

import java.io.IOException;

/**
 * This is the entrance of remote-device-viewer's client, only used as a launcher
 * @author BowenDeng
 */
public class Main {
    /**
     * main function
     * @param args input from console
     */
    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        TcpClient.init();
        while(true){
            Terminal.initService();
        }
    }
}
