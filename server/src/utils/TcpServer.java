package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.jetbrains.annotations.NotNull;
import pojo.CodeTable;
import pojo.RequestTable;
import pojo.Response;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * @author BowenDeng
 */
public class TcpServer extends Thread{
    private final Socket socket;
    private BufferedWriter bufferedWriter;

    TcpServer(Socket socket){
        this.socket = socket;
        this.start();
    }

    private boolean isExists(@NotNull File folder) throws IOException {
        if(!folder.exists()){
            handleError("\"" + folder.getPath() + "\" does not exist");
            return false;
        }

        if (!folder.isDirectory()){
            handleError("\"" + folder.getName() + "\" is not a directory");
            return false;
        }
        return true;
    }

    private void handleCd(String filePath) throws IOException {
        File folder = new File(filePath);

        boolean preCheck = isExists(folder);
        if(!preCheck){
            return;
        }

        final Response response = new Response();
        response.setCode(CodeTable.SUCCESS);
        response.setMode(CodeTable.CD);
        response.setMsg(filePath);

        bufferedWriter.write(JSON.toJSONString(response));
    }

    private void handleLs(String filePath) throws IOException {
        File folder = new File(filePath);

        boolean preCheck = isExists(folder);
        if(!preCheck){
            return;
        }

        Response response = new Response();
        response.setCode(CodeTable.SUCCESS);
        response.setMode(CodeTable.LS);

        File[] tempList = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(tempList).length; i++) {
            response.getFileName()[i] = tempList[i].getName();
            if (tempList[i].isFile()) {
                response.getType()[i] = "file";
            }
            if (tempList[i].isDirectory()) {
                response.getType()[i] = "dir";
            }
        }

        bufferedWriter.write(JSON.toJSONString(response));
    }

    private void handleGet(){

    }

    private void handleError(String msg) throws IOException {
        Response response = new Response();
        response.setCode(CodeTable.ERROR);
        response.setMsg(msg);
        bufferedWriter.write(JSON.toJSONString(response));
    }

    @Override
    @SuppressWarnings("all")
    public void run() {
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(true){
                String request = bufferedReader.readLine();
                RequestTable requestTable =
                        JSON.parseObject(request, new TypeReference<RequestTable>(){});
                switch (requestTable.getMode()){
                    case CodeTable.LS -> handleCd(requestTable.getFilePath());
                    case CodeTable.CD -> handleLs(requestTable.getFilePath());
                    case CodeTable.GET -> handleGet();
                    default -> handleError("mode \"" + requestTable.getMode() +
                            "\" cannot be recognized as any command or service, " +
                            "please check if it is a spelling error");
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: failed to establish input stream");
            throw new RuntimeException(e);
        }
    }
}

