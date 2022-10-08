package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.jetbrains.annotations.NotNull;
import pojo.CodeTable;
import pojo.RequestTable;
import pojo.Response;
import utils.TcpClient;

import java.io.IOException;


/**
 * @author BowenDeng
 */
public class TaskManager {
    public static void commandHandler(String @NotNull [] command) throws IOException {
        switch (command[0]) {
            case "bye", "exit", "quit" -> bye();
            case "cd" -> changeDir(command);
            case "ls" -> listDir();
            case "get" -> getFile(command);
            default -> System.out.println("\"" + command[0] + "\" cannot be recognized " +
                    "as any command or service, please check if it is a spelling error");
        }
    }

    private static void listDir() throws IOException {

        TcpClient.sendMessage(JSON.toJSONString(new RequestTable(Terminal.getTitle(), CodeTable.LS)));
        Response response =
                JSON.parseObject(TcpClient.receiveMessage(), new TypeReference<Response>(){});

        if(response.getCode() != CodeTable.SUCCESS){
            System.out.println(response.getMsg());
            return;
        }

        for (int i = 0; i < response.getFileName().length; i++){
            System.out.println("<" + response.getType()[i] +
                    "> " + response.getFileName()[i]);
        }
    }

    private static void changeDir(String @NotNull [] path) throws IOException {
        if(path.length > 2){
            System.out.println("invalid input! Too many parameters");
            return;
        }else if(path.length == 1){
            System.out.println("invalid input! You have to type the path you want to go to");
            return;
        }

        TcpClient.sendMessage(JSON.toJSONString(new RequestTable(Terminal.getTitle(), CodeTable.CD)));
        Response response =
                JSON.parseObject(TcpClient.receiveMessage(), new TypeReference<Response>(){});

        if(response.getCode() != CodeTable.ERROR){
            System.out.println(response.getMsg());
            return;
        }

        Terminal.setTitle(response.getMsg());
    }
    private static void getFile(String[] fileName){
    }

    private static void bye(){
        try {
            if(TcpClient.isConnectionAlive()){
                TcpClient.closeConnection();
            }
            System.exit(0);
        } catch(IOException e){
            System.out.println("ERROR: failed to close the connection, stream maybe still in use");
        }
    }
}
