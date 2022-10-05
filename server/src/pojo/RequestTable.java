package pojo;

import lombok.Data;
import lombok.Getter;

/**
 * @author BowenDeng
 */
@Data
@Getter
public class RequestTable {

    private static final int LS = 0;
    private static final int GET = 1;
    private static final int BYE = 2;
    private final String filePath;
    private final int mode;

    public RequestTable(String filePath, int mode){
        this.mode = mode;
        this.filePath = filePath;
    }

    public RequestTable(int mode){
        this.mode = mode;
        filePath = "";
    }
}
